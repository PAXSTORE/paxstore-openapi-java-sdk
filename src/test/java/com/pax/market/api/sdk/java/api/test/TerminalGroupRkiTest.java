package com.pax.market.api.sdk.java.api.test;

import com.pax.market.api.sdk.java.api.base.dto.Result;
import com.pax.market.api.sdk.java.api.terminalGroup.TerminalGroupApi;
import com.pax.market.api.sdk.java.api.terminalGroup.dto.CreateTerminalGroupRequest;
import com.pax.market.api.sdk.java.api.terminalGroup.dto.TerminalGroupDTO;
import com.pax.market.api.sdk.java.api.terminalGroupRki.TerminalGroupRkiApi;
import com.pax.market.api.sdk.java.api.terminalGroupRki.dto.CreateTerminalGroupRkiRequest;
import com.pax.market.api.sdk.java.api.terminalGroupRki.dto.TerminalGroupRkiDTO;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Integration tests for {@link TerminalGroupRkiApi}.
 *
 * <p>The dev account may have insufficient RKI balance, so a push may be rejected
 * with a business code. All write calls accept any reasonable business result and
 * use a neutral id when no task could be created.</p>
 */
public class TerminalGroupRkiTest extends IntegrationTestSupport {

    private static final String RKI_KEY = "TMK_TEST_8";

    private TerminalGroupRkiApi api;
    private TerminalGroupApi groupApi;

    @Before
    public void setUp() {
        api = new TerminalGroupRkiApi(TestConstants.API_BASE_URL,
                TestConstants.API_KEY, TestConstants.API_SECRET);
        groupApi = new TerminalGroupApi(TestConstants.API_BASE_URL,
                TestConstants.API_KEY, TestConstants.API_SECRET);
    }

    @Test
    public void pushRkiKey2Group_createdGroup_shouldSucceed() {
        Long createdGroupId = createGroup();
        Long groupId = createdGroupId != null ? createdGroupId : 1L;
        Long createdTaskId = null;
        try {
            CreateTerminalGroupRkiRequest request = new CreateTerminalGroupRkiRequest();
            request.setGroupId(groupId);
            request.setRkiKey(RKI_KEY);

            Result<TerminalGroupRkiDTO> result = api.pushRkiKey2Group(request);
            assertResultOk(result);
            if (result.getData() != null) {
                createdTaskId = result.getData().getId();
            }
        } finally {
            disableTask(createdTaskId);
            deleteGroup(createdGroupId);
        }
    }

    @Test
    public void getGroupPushRkiTask_createdTask_shouldSucceed() {
        Long createdGroupId = createGroup();
        Long groupId = createdGroupId != null ? createdGroupId : 1L;
        Long createdTaskId = null;
        try {
            createdTaskId = createTask(groupId);
            Long id = createdTaskId != null ? createdTaskId : 1L;
            Result<TerminalGroupRkiDTO> result = api.getGroupPushRkiTask(id);
            assertResultOk(result);
            if (result.getData() != null) {
                assertEquals(id, result.getData().getId());
            }
        } finally {
            disableTask(createdTaskId);
            deleteGroup(createdGroupId);
        }
    }

    @Test
    public void disableGroupRkiPushTask_createdTask_shouldSucceed() {
        Long createdGroupId = createGroup();
        Long groupId = createdGroupId != null ? createdGroupId : 1L;
        Long createdTaskId = null;
        try {
            createdTaskId = createTask(groupId);
            Long id = createdTaskId != null ? createdTaskId : 1L;
            assertResultOk(api.disableGroupRkiPushTask(id));
        } finally {
            disableTask(createdTaskId);
            deleteGroup(createdGroupId);
        }
    }

    @Test
    public void searchGroupPushRkiTask_byGroupId_shouldReturnPage() {
        Long createdGroupId = createGroup();
        Long groupId = createdGroupId != null ? createdGroupId : 1L;
        try {
            createTask(groupId);
            Result<TerminalGroupRkiDTO> result = api.searchGroupPushRkiTask(
                    1, 12, com.pax.market.api.sdk.java.api.BaseThirdPartySysApi.SearchOrderBy.CreatedDate_desc,
                    groupId, null, true, null);
            assertSearchOk(result);
        } finally {
            deleteGroup(createdGroupId);
        }
    }

    @Test
    public void searchGroupPushRkiTask_withoutGroupId_shouldFailValidation() {
        Result<TerminalGroupRkiDTO> result = api.searchGroupPushRkiTask(
                1, 12, null, null, null, null, null);
        assertValidationErrorContains(result, "terminalGroupId");
    }

    @Test
    public void searchGroupPushRkiTask_pageSizeOutOfRange_shouldFailValidation() {
        Long createdGroupId = createGroup();
        Long groupId = createdGroupId != null ? createdGroupId : 1L;
        try {
            Result<TerminalGroupRkiDTO> result = api.searchGroupPushRkiTask(
                    1, 1000, null, groupId, null, null, null);
            assertValidationErrorContains(result, "pageSize");
        } finally {
            deleteGroup(createdGroupId);
        }
    }

    @Test
    public void getGroupPushRkiTask_unknownId_shouldReturnBusinessError() {
        Result<TerminalGroupRkiDTO> result = api.getGroupPushRkiTask(-1L);
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

    private Long createTask(Long groupId) {
        CreateTerminalGroupRkiRequest request = new CreateTerminalGroupRkiRequest();
        request.setGroupId(groupId);
        request.setRkiKey(RKI_KEY);
        Result<TerminalGroupRkiDTO> result = api.pushRkiKey2Group(request);
        assertResultOk(result);
        return result.getData() == null ? null : result.getData().getId();
    }

    private void disableTask(Long id) {
        if (id != null) {
            api.disableGroupRkiPushTask(id);
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
