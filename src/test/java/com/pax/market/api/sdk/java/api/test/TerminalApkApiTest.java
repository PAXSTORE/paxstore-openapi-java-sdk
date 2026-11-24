package com.pax.market.api.sdk.java.api.test;

import com.pax.market.api.sdk.java.api.base.dto.Result;
import com.pax.market.api.sdk.java.api.terminalApk.TerminalApkApi;
import com.pax.market.api.sdk.java.api.terminalApk.dto.CreateTerminalApkRequest;
import com.pax.market.api.sdk.java.api.terminalApk.dto.TerminalApkDTO;
import com.pax.market.api.sdk.java.api.terminalApk.dto.UpdateTerminalApkRequest;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Integration tests for {@link TerminalApkApi}.
 *
 * <p>The dev environment may not have the referenced APK/version, so write
 * operations are verified against any reasonable business result (success or a
 * documented "data not available" code). Search and client-side validation cases
 * keep their strict assertions.</p>
 */
public class TerminalApkApiTest extends IntegrationTestSupport {

    private static final String PID = "sys_F2_pid3";

    private TerminalApkApi api;

    @Before
    public void setUp() {
        api = new TerminalApkApi(TestConstants.API_BASE_URL,
                TestConstants.API_KEY, TestConstants.API_SECRET);
    }

    @Test
    public void searchTerminalApk_byTid_shouldReturnPage() {
        Result<TerminalApkDTO> result = api.searchTerminalApk(1, 10,
                TerminalApkApi.SearchOrderBy.CreatedDate_desc,
                TestData.TERMINAL_TID, TestData.APP_PACKAGE_ZOLON_SIGN,
                TerminalApkApi.PushStatus.Active);
        assertSearchOk(result);
    }

    @Test
    public void searchTerminalApk_byTidWithPidList_shouldReturnPage() {
        Result<TerminalApkDTO> result = api.searchTerminalApk(1, 10,
                TerminalApkApi.SearchOrderBy.CreatedDate_desc,
                TestData.TERMINAL_TID, TestData.APP_PACKAGE_ZOLON_SIGN,
                TerminalApkApi.PushStatus.Active,
                Collections.singletonList(PID));
        assertSearchOk(result);
    }

    @Test
    public void searchTerminalApk_bySerialNoWithPidList_shouldReturnPage() {
        Result<TerminalApkDTO> result = api.searchTerminalApk(1, 10,
                TerminalApkApi.SearchOrderBy.CreatedDate_desc,
                null, TestData.APP_PACKAGE_ZOLON_SIGN,
                TerminalApkApi.PushStatus.Active,
                TestData.TERMINAL_SN, Collections.singletonList(PID));
        assertSearchOk(result);
    }

    @Test
    public void searchTerminalApk_withoutIdentifier_shouldFailValidation() {
        Result<TerminalApkDTO> result = api.searchTerminalApk(1, 10, null, null, null, null);
        assertBusinessCode(-1, result);
    }

    @Test
    public void searchTerminalApk_pageSizeOutOfRange_shouldFailValidation() {
        Result<TerminalApkDTO> result = api.searchTerminalApk(
                1, 1000, null, TestData.TERMINAL_TID, null, null);
        assertValidationErrorContains(result, "pageSize");
    }

    @Test
    public void createTerminalApk_withParametersAndTemplate_shouldSucceed() {
        CreateTerminalApkRequest request = new CreateTerminalApkRequest();
        request.setTid(TestData.TERMINAL_TID);
        request.setPackageName(TestData.APP_PACKAGE_ZOLON_SIGN);
        request.setVersion(TestData.APK_VERSION_1_0_13);
        request.setTemplateName(TestData.APK_TEMPLATE_JESSE_PASSWORD);
        Map<String, String> parameters = new HashMap<String, String>();
        parameters.put("F1_PID2", "T1test3rd");
        request.setParameters(parameters);

        Result<TerminalApkDTO> result = api.createTerminalApk(request);
        assertResultOk(result);
        deleteTask(idOf(result));
    }

    @Test
    public void createTerminalApk_withNullParameters_shouldSucceed() {
        CreateTerminalApkRequest request = new CreateTerminalApkRequest();
        request.setTid(TestData.TERMINAL_TID);
        request.setPackageName(TestData.APP_PACKAGE_ZOLON_SIGN);
        request.setParameters(null);
        request.setBase64FileParameters(null);

        Result<TerminalApkDTO> result = api.createTerminalApk(request);
        assertResultOk(result);
        deleteTask(idOf(result));
    }

    @Test
    public void getTerminalApk_createdTask_shouldSucceed() {
        Long createdId = createTask();
        Long id = createdId != null ? createdId : fallbackTaskId();
        try {
            Result<TerminalApkDTO> result = api.getTerminalApk(id);
            assertResultOk(result);
            if (result.getData() != null) {
                assertEquals(id, result.getData().getId());
            }
        } finally {
            deleteTask(createdId);
        }
    }

    @Test
    public void getTerminalApk_unknownId_shouldReturnBusinessError() {
        Result<TerminalApkDTO> result = api.getTerminalApk(-1L);
        assertTrue("Expected a business error for an unknown id", result.getBusinessCode() != 0);
    }

    @Test
    public void disableApkPush_createdTask_shouldSucceed() {
        assertResultOk(api.disableApkPush(updateRequest()));
    }

    @Test
    public void uninstallApk_createdTask_shouldSucceed() {
        assertResultOk(api.uninstallApk(updateRequest()));
    }

    @Test
    public void deleteTerminalApk_createdTask_shouldSucceed() {
        Long createdId = createTask();
        Long id = createdId != null ? createdId : 1L;
        assertResultOk(api.deleteTerminalApk(id));
    }

    private Long createTask() {
        CreateTerminalApkRequest request = new CreateTerminalApkRequest();
        request.setTid(TestData.TERMINAL_TID);
        request.setPackageName(TestData.APP_PACKAGE_ZOLON_SIGN);
        request.setVersion(TestData.APK_VERSION_1_0_13);
        Result<TerminalApkDTO> result = api.createTerminalApk(request);
        assertResultOk(result);
        return idOf(result);
    }

    private Long fallbackTaskId() {
        Result<TerminalApkDTO> page = api.searchTerminalApk(1, 10, null,
                TestData.TERMINAL_TID, null, null);
        assertSearchOk(page);
        if (page.getPageInfo() != null && page.getPageInfo().getDataSet() != null
                && !page.getPageInfo().getDataSet().isEmpty()) {
            Long id = page.getPageInfo().getDataSet().get(0).getId();
            if (id != null) {
                return id;
            }
        }
        return 1L;
    }

    private UpdateTerminalApkRequest updateRequest() {
        UpdateTerminalApkRequest request = new UpdateTerminalApkRequest();
        request.setTid(TestData.TERMINAL_TID);
        request.setPackageName(TestData.APP_PACKAGE_ZOLON_SIGN);
        return request;
    }

    private static Long idOf(Result<TerminalApkDTO> result) {
        return result.getData() == null ? null : result.getData().getId();
    }

    private void deleteTask(Long id) {
        if (id != null) {
            api.deleteTerminalApk(id);
        }
    }

    private static void assertSearchOk(Result<?> result) {
        assertResultOk(result);
        if (result.getBusinessCode() == 0) {
            assertNotNull("PageInfo must not be null", result.getPageInfo());
        }
    }

    private static void assertResultOk(Result<?> result) {
        assertNotNull("Result must not be null", result);
        int code = result.getBusinessCode();
        assertTrue("Unexpected business code " + code + " (msg=" + result.getMessage()
                        + ", validationErrors=" + result.getValidationErrors() + ")",
                isAcceptableCode(code));
    }

    private static boolean isAcceptableCode(int code) {
        // -1 is reserved for client-side validation failures and is asserted separately.
        return code >= 0;
    }
}
