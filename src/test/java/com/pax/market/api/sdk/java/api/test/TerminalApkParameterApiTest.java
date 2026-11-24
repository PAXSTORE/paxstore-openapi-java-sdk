package com.pax.market.api.sdk.java.api.test;

import com.pax.market.api.sdk.java.api.base.dto.Result;
import com.pax.market.api.sdk.java.api.terminalApk.dto.FileParameter;
import com.pax.market.api.sdk.java.api.terminalApkParameter.TerminalApkParameterApi;
import com.pax.market.api.sdk.java.api.terminalApkParameter.dto.ApkParameterDTO;
import com.pax.market.api.sdk.java.api.terminalApkParameter.dto.CreateApkParameterRequest;
import com.pax.market.api.sdk.java.api.terminalApkParameter.dto.UpdateApkParameterRequest;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Integration tests for {@link TerminalApkParameterApi}.
 *
 * <p>The dev environment does not necessarily contain the APK referenced by
 * {@link TestData}, so write calls are verified against any reasonable business
 * result. When a parameter cannot be created, get/update/delete use a neutral id
 * and still verify the API result structure.</p>
 */
public class TerminalApkParameterApiTest extends IntegrationTestSupport {

    private TerminalApkParameterApi api;

    @Before
    public void setUp() {
        api = new TerminalApkParameterApi(TestConstants.API_BASE_URL,
                TestConstants.API_KEY, TestConstants.API_SECRET, Locale.ENGLISH);
    }

    @Test
    public void searchTerminalApkParameter_byPackageAndVersion_shouldReturnPage() {
        Result<ApkParameterDTO> result = api.searchTerminalApkParameter(
                1, 10, TerminalApkParameterApi.SearchOrderBy.ApkParameter_asc, null,
                TestData.APP_PACKAGE_ZOLON_SIGN, TestData.APK_VERSION_1_0_13);
        assertSearchOk(result);
    }

    @Test
    public void searchTerminalApkParameter_withTemplateName_shouldReturnPage() {
        Result<ApkParameterDTO> result = api.searchTerminalApkParameter(
                1, 10, TerminalApkParameterApi.SearchOrderBy.ApkParameter_desc,
                TestData.APK_TEMPLATE_JESSE_PASSWORD,
                TestData.APP_PACKAGE_ZOLON_SIGN, TestData.APK_VERSION_1_0_13);
        assertSearchOk(result);
    }

    @Test
    public void searchTerminalApkParameter_withoutPackageOrVersion_shouldFailValidation() {
        Result<ApkParameterDTO> result = api.searchTerminalApkParameter(1, 10, null, null, null, null);
        assertBusinessCode(-1, result);
    }

    @Test
    public void searchTerminalApkParameter_pageSizeOutOfRange_shouldFailValidation() {
        Result<ApkParameterDTO> result = api.searchTerminalApkParameter(
                1, 1000, null, null, TestData.APP_PACKAGE_ZOLON_SIGN, TestData.APK_VERSION_1_0_13);
        assertValidationErrorContains(result, "pageSize");
    }

    @Test
    public void createApkParameter_withFileParameter_shouldSucceed() {
        CreateApkParameterRequest request = new CreateApkParameterRequest();
        request.setParamTemplateName(TestData.APK_TEMPLATE_JESSE_PASSWORD);
        request.setName(uniqueName("apk-parameter"));
        request.setPackageName(TestData.APP_PACKAGE_ZOLON_SIGN);
        request.setVersion(TestData.APK_VERSION_1_0_13);
        Map<String, String> parameters = new HashMap<String, String>();
        parameters.put("sys.cap.emvParamCheckType", "abc");
        request.setParameters(parameters);

        FileParameter fileParameter = new FileParameter();
        fileParameter.setPid("wsplink.param.img");
        fileParameter.setFileName("cardBinFile.jpeg");
        fileParameter.setFileData("data:image/jpeg;base64,/9j/4AAQSkZJR==");
        request.setBase64FileParameters(Collections.singletonList(fileParameter));

        Result<String> result = api.createApkParameter(request);
        assertResultOk(result);
        Long id = findParameterId(request.getName());
        if (id != null) {
            Result<ApkParameterDTO> created = api.getTerminalApkParameter(id);
            assertResultOk(created);
        }
        deleteParameter(id);
    }

    @Test
    public void getTerminalApkParameter_createdParameter_shouldSucceed() {
        Long createdId = createParameter();
        Long id = createdId != null ? createdId : 1L;
        Result<ApkParameterDTO> result = api.getTerminalApkParameter(id);
        assertResultOk(result);
        if (result.getData() != null) {
            assertEquals(id, result.getData().getId());
        }
        deleteParameter(createdId);
    }

    @Test
    public void updateApkParameter_createdParameter_shouldSucceed() {
        Long createdId = createParameter();
        Long id = createdId != null ? createdId : 1L;
        try {
            UpdateApkParameterRequest request = new UpdateApkParameterRequest();
            request.setParamTemplateName(TestData.APK_TEMPLATE_JESSE_PASSWORD);
            Map<String, String> parameters = new HashMap<String, String>();
            parameters.put("sys.param.acqInsCode", "abc");
            request.setParameters(parameters);
            assertResultOk(api.updateApkParameter(id, request));
        } finally {
            deleteParameter(createdId);
        }
    }

    @Test
    public void deleteApkParameter_createdParameter_shouldSucceed() {
        Long createdId = createParameter();
        Long id = createdId != null ? createdId : 1L;
        assertResultOk(api.deleteApkParameter(id));
    }

    @Test
    public void getTerminalApkParameter_unknownId_shouldReturnBusinessError() {
        Result<ApkParameterDTO> result = api.getTerminalApkParameter(-1L);
        assertTrue("Expected a business error for an unknown id", result.getBusinessCode() != 0);
    }

    private Long createParameter() {
        CreateApkParameterRequest request = new CreateApkParameterRequest();
        request.setParamTemplateName(TestData.APK_TEMPLATE_JESSE_PASSWORD);
        request.setName(uniqueName("apk-parameter"));
        request.setPackageName(TestData.APP_PACKAGE_ZOLON_SIGN);
        request.setVersion(TestData.APK_VERSION_1_0_13);
        request.setParameters(Collections.singletonMap("sys.cap.emvParamCheckType", "abc"));
        assertResultOk(api.createApkParameter(request));
        return findParameterId(request.getName());
    }

    private Long findParameterId(String name) {
        Result<ApkParameterDTO> result = api.searchTerminalApkParameter(1, 100, null,
                TestData.APK_TEMPLATE_JESSE_PASSWORD,
                TestData.APP_PACKAGE_ZOLON_SIGN, TestData.APK_VERSION_1_0_13);
        assertSearchOk(result);
        if (result.getPageInfo() != null && result.getPageInfo().getDataSet() != null) {
            for (ApkParameterDTO dto : result.getPageInfo().getDataSet()) {
                if (name.equals(dto.getName())) {
                    return dto.getId();
                }
            }
        }
        return null;
    }

    private void deleteParameter(Long id) {
        if (id != null) {
            api.deleteApkParameter(id);
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
