package com.pax.market.api.sdk.java.api.test;

import static org.junit.Assert.*;

import com.pax.market.api.sdk.java.api.app.AppApi;
import com.pax.market.api.sdk.java.api.app.AppApi.*;
import com.pax.market.api.sdk.java.api.app.dto.*;
import com.pax.market.api.sdk.java.api.base.dto.Result;
import org.junit.Before;
import org.junit.Test;

/**
 * Integration tests for {@link AppApi}.
 */
public class AppApiTest extends IntegrationTestSupport {

    private static final int CODE_APP_NOT_FOUND = 1000;
    private static final int CODE_APK_NOT_FOUND = 1002;
    private static final int CODE_PARAM_TEMPLATE_NOT_FOUND = 12541;

    private AppApi appApi;

    @Before
    public void setUp() {
        appApi = new AppApi(TestConstants.API_BASE_URL,
                TestConstants.API_KEY, TestConstants.API_SECRET);
    }

    @Test
    public void searchApp_shouldReturnPage() {
        Result<AppPageDTO> result = appApi.searchApp(1, 3, AppSearchOrderBy.UpdatedDate_desc,
                "", AppOsType.Android, AppChargeType.Free, AppBaseType.Normal,
                null, ApkStatus.Online, false, null);
        assertPage(result);
        for (AppPageDTO app : result.getPageInfo().getDataSet()) {
            if (app.getName() != null) {
                assertFalse("App name must not be blank", app.getName().isEmpty());
            }
            if (app.getPackageName() != null) {
                assertFalse("Package name must not be blank", app.getPackageName().isEmpty());
            }
        }
    }

    @Test
    public void searchApp_byName_shouldFilterResults() {
        Result<AppPageDTO> result = appApi.searchApp(1, 10, AppSearchOrderBy.AppName_asc,
                "signrotate", null, null, null, null, null, false, null);
        assertPage(result);
        for (AppPageDTO app : result.getPageInfo().getDataSet()) {
            String name = app.getName() == null ? "" : app.getName().toLowerCase();
            String packageName = app.getPackageName() == null ? "" : app.getPackageName().toLowerCase();
            assertTrue("Unexpected app in name filter: " + app,
                    name.contains("signrotate") || packageName.contains("signrotate"));
        }
    }

    @Test
    public void searchApp_includeSubscribed_shouldReturnPage() {
        Result<AppPageDTO> result = appApi.searchApp(1, 5, AppSearchOrderBy.UpdatedDate_desc,
                "", AppOsType.Android, AppChargeType.Free, AppBaseType.Normal,
                AppStatus.Active, ApkStatus.Online, false, null, true);
        assertPage(result);
    }

    @Test
    public void searchApp_withResellerName_shouldReturnPage() {
        Result<AppPageDTO> result = appApi.searchApp(1, 20, AppSearchOrderBy.UpdatedDate_desc,
                "", null, null, null, AppStatus.Active, ApkStatus.Online,
                false, null, false, TestData.RESELLER_GLOBAL);
        assertPage(result);
    }

    @Test
    public void searchApp_pageSizeOutOfRange_shouldFailValidation() {
        Result<AppPageDTO> result = appApi.searchApp(1, 101, null, null, null, null,
                null, null, null, null, null);
        assertValidationErrorContains(result, "pageSize");
    }

    @Test
    public void getAppCost_shouldReturnResult() {
        Result<AppCostDTO> originalIds = appApi.getAppCost(1L, 1L);
        assertNotValidation("getAppCost(1,1)", originalIds);
        assertTrue("Unexpected business code: " + originalIds,
                originalIds.getBusinessCode() == 0
                        || originalIds.getBusinessCode() == CODE_APP_NOT_FOUND);

        AppPageDTO app = findFirstAppWithApks();
        if (app == null) {
            return;
        }
        Result<AppCostDTO> result = appApi.getAppCost(1L, app.getId());
        assertNotValidation("getAppCost(realAppId)", result);
        if (result.getBusinessCode() == 0 && result.getData() != null) {
            assertNotNull("paid flag must be present when data exists", result.getData().getPaid());
        }
    }

    @Test
    public void getAppCost_unknownIds_shouldReturnBusinessCode() {
        Result<AppCostDTO> result = appApi.getAppCost(-1L, -1L);
        assertTrue("Unknown app should not return success", result.getBusinessCode() != 0);
    }

    @Test
    public void searchApkParamPidList_shouldReturnResult() {
        Result<ApkParamPidDTO> result = appApi.searchApkParamPidList(
                TestData.APK_TEMPLATE_JESSE_PASSWORD,
                TestData.APP_PACKAGE_ZOLON_SIGN,
                TestData.APK_VERSION_1_0_13);
        assertNotNull(result);
        int code = result.getBusinessCode();
        assertTrue("Unexpected business code: " + result,
                code == 0 || code == CODE_APK_NOT_FOUND
                        || code == CODE_PARAM_TEMPLATE_NOT_FOUND);
        if (code == 0) {
            assertNotNull(result.getData());
            assertNotNull("pidList must be present", result.getData().getPidList());
        }
    }

    @Test
    public void searchApkSupportedModels_knownPackage_shouldReturnModels() {
        AppPageDTO app = findFirstAppWithApks();
        if (app == null) {
            return;
        }
        Result<ApkModelDTO> result = appApi.searchApkSupportedModels(
                app.getPackageName(), app.getApkList().get(0).getVersionName());
        assertNotValidation("searchApkSupportedModels", result);
        if (result.getBusinessCode() == 0) {
            assertNotNull("modelList must be present", result.getData());
            assertNotNull("modelList must be present", result.getData().getModelList());
        }
    }

    @Test
    public void searchApkSupportedModels_unknownPackage_shouldReturnBusinessCode() {
        Result<ApkModelDTO> result = appApi.searchApkSupportedModels(
                "com.package.does.not.exist", "1.0.0");
        assertTrue("Unknown APK should not return success", result.getBusinessCode() != 0);
        assertNotNull(result.getMessage());
    }

    private AppPageDTO findFirstAppWithApks() {
        Result<AppPageDTO> page = appApi.searchApp(1, 20, AppSearchOrderBy.UpdatedDate_desc,
                "", null, null, null, null, null, false, null);
        assertPage(page);
        for (AppPageDTO app : page.getPageInfo().getDataSet()) {
            if (app.getPackageName() != null && app.getApkList() != null
                    && !app.getApkList().isEmpty()) {
                return app;
            }
        }
        return null;
    }

    private static void assertNotValidation(String step, Result<?> result) {
        assertNotNull(step + " returned null result", result);
        assertTrue(step + " failed validation: " + result, result.getBusinessCode() != -1);
    }
}
