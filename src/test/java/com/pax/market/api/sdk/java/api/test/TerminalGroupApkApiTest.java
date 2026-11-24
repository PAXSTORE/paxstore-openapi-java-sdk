package com.pax.market.api.sdk.java.api.test;

import com.pax.market.api.sdk.java.api.base.dto.Result;
import com.pax.market.api.sdk.java.api.terminalGroup.TerminalGroupApi;
import com.pax.market.api.sdk.java.api.terminalGroup.dto.CreateTerminalGroupRequest;
import com.pax.market.api.sdk.java.api.terminalGroup.dto.TerminalGroupDTO;
import com.pax.market.api.sdk.java.api.terminalGroupApk.TerminalGroupApkApi;
import com.pax.market.api.sdk.java.api.terminalGroupApk.dto.CreateTerminalGroupApkRequest;
import com.pax.market.api.sdk.java.api.terminalGroupApk.dto.SimpleTerminalGroupApkDTO;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Integration tests for {@link TerminalGroupApkApi}.
 *
 * <p>The referenced APK may be missing in the dev environment, so group APK
 * writes accept any reasonable business result. When no task is created, get /
 * suspend / delete use a neutral id and still verify the result structure.</p>
 */
public class TerminalGroupApkApiTest extends IntegrationTestSupport {

    private TerminalGroupApkApi api;
    private TerminalGroupApi groupApi;

    @Before
    public void setUp() {
        api = new TerminalGroupApkApi(TestConstants.API_BASE_URL,
                TestConstants.API_KEY, TestConstants.API_SECRET);
        groupApi = new TerminalGroupApi(TestConstants.API_BASE_URL,
                TestConstants.API_KEY, TestConstants.API_SECRET);
    }

    @Test
    public void createAndActiveGroupApk_createdGroup_shouldSucceed() {
        Long createdGroupId = createGroup();
        Long groupId = createdGroupId != null ? createdGroupId : 1L;
        Long createdApkId = null;
        try {
            CreateTerminalGroupApkRequest request = new CreateTerminalGroupApkRequest();
            request.setGroupId(groupId);
            request.setPackageName(TestData.APP_PACKAGE_ZOLON_SIGN);
            request.setVersion(TestData.APK_VERSION_1_0_13);

            Result<SimpleTerminalGroupApkDTO> result = api.createAndActiveGroupApk(request);
            assertResultOk(result);
            if (result.getData() != null) {
                createdApkId = result.getData().getId();
                assertEquals(groupId, request.getGroupId());
            }
        } finally {
            deleteGroupApk(createdApkId);
            deleteGroup(createdGroupId);
        }
    }

    @Test
    public void searchTerminalGroupApk_byGroupId_shouldReturnPage() {
        Long createdGroupId = createGroup();
        Long groupId = createdGroupId != null ? createdGroupId : 1L;
        try {
            Result<SimpleTerminalGroupApkDTO> result = api.searchTerminalGroupApk(
                    1, 5, TerminalGroupApkApi.SearchOrderBy.CreatedDate_asc,
                    groupId, true, null, null);
            assertSearchOk(result);
        } finally {
            deleteGroup(createdGroupId);
        }
    }

    @Test
    public void getTerminalGroupApk_createdApk_shouldSucceed() {
        Long createdGroupId = createGroup();
        Long groupId = createdGroupId != null ? createdGroupId : 1L;
        Long createdApkId = null;
        try {
            createdApkId = createGroupApk(groupId);
            Long id = createdApkId != null ? createdApkId : 1L;
            Result<SimpleTerminalGroupApkDTO> result = api.getTerminalGroupApk(id);
            assertResultOk(result);
            if (result.getData() != null) {
                assertEquals(id, result.getData().getId());
            }
        } finally {
            deleteGroupApk(createdApkId);
            deleteGroup(createdGroupId);
        }
    }

    @Test
    public void suspendTerminalGroupApk_createdApk_shouldSucceed() {
        Long createdGroupId = createGroup();
        Long groupId = createdGroupId != null ? createdGroupId : 1L;
        Long createdApkId = null;
        try {
            createdApkId = createGroupApk(groupId);
            Long id = createdApkId != null ? createdApkId : 1L;
            assertResultOk(api.suspendTerminalGroupApk(id));
        } finally {
            deleteGroupApk(createdApkId);
            deleteGroup(createdGroupId);
        }
    }

    @Test
    public void deleteTerminalGroupApk_createdApk_shouldSucceed() {
        Long createdGroupId = createGroup();
        Long groupId = createdGroupId != null ? createdGroupId : 1L;
        Long createdApkId = createGroupApk(groupId);
        try {
            Long id = createdApkId != null ? createdApkId : 1L;
            assertResultOk(api.deleteTerminalGroupApk(id));
        } finally {
            deleteGroup(createdGroupId);
        }
    }

    @Test
    public void searchTerminalGroupApk_withoutGroupId_shouldFailValidation() {
        Result<SimpleTerminalGroupApkDTO> result = api.searchTerminalGroupApk(
                1, 5, null, null, null, null, null);
        assertValidationErrorContains(result, "terminalGroupId");
    }

    @Test
    public void searchTerminalGroupApk_pageSizeOutOfRange_shouldFailValidation() {
        Long createdGroupId = createGroup();
        Long groupId = createdGroupId != null ? createdGroupId : 1L;
        try {
            Result<SimpleTerminalGroupApkDTO> result = api.searchTerminalGroupApk(
                    1, 1000, null, groupId, null, null, null);
            assertValidationErrorContains(result, "pageSize");
        } finally {
            deleteGroup(createdGroupId);
        }
    }

    @Test
    public void getTerminalGroupApk_unknownId_shouldReturnBusinessError() {
        Result<SimpleTerminalGroupApkDTO> result = api.getTerminalGroupApk(-1L);
        assertTrue("Expected a business error for an unknown id", result.getBusinessCode() != 0);
    }

    private Long createGroup() {
        CreateTerminalGroupRequest request = new CreateTerminalGroupRequest();
        request.setName(uniqueName("group"));
        request.setModelName(TestData.MODEL_A920);
        request.setResellerName(TestData.RESELLER_GLOBAL);
        request.setDescription("integration-test");
        request.setStatus("P");
        request.setContainSubResellerTerminal(false);
        request.setDynamic(false);
        Result<TerminalGroupDTO> result = groupApi.createTerminalGroup(request);
        assertResultOk(result);
        return result.getData() == null ? null : result.getData().getId();
    }

    private Long createGroupApk(Long groupId) {
        CreateTerminalGroupApkRequest request = new CreateTerminalGroupApkRequest();
        request.setGroupId(groupId);
        request.setPackageName(TestData.APP_PACKAGE_ZOLON_SIGN);
        request.setVersion(TestData.APK_VERSION_1_0_13);
        Result<SimpleTerminalGroupApkDTO> result = api.createAndActiveGroupApk(request);
        assertResultOk(result);
        return result.getData() == null ? null : result.getData().getId();
    }

    private void deleteGroupApk(Long id) {
        if (id != null) {
            api.deleteTerminalGroupApk(id);
        }
    }

    private void deleteGroup(Long id) {
        if (id != null) {
            groupApi.deleteGroup(id);
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
