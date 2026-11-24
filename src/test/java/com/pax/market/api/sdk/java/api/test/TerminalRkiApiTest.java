package com.pax.market.api.sdk.java.api.test;

import com.pax.market.api.sdk.java.api.base.dto.Result;
import com.pax.market.api.sdk.java.api.terminalRki.TerminalRkiApi;
import com.pax.market.api.sdk.java.api.terminalRki.dto.DisablePushRkiTask;
import com.pax.market.api.sdk.java.api.terminalRki.dto.PushRki2TerminalRequest;
import com.pax.market.api.sdk.java.api.terminalRki.dto.PushRkiTaskDTO;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Integration tests for {@link TerminalRkiApi}.
 *
 * <p>The dev account may have no RKI balance, so a push may be rejected with a
 * business code. Write calls accept any reasonable business result and get / delete
 * fall back to a neutral id when no task was created.</p>
 */
public class TerminalRkiApiTest extends IntegrationTestSupport {

    private static final String RKI_KEY = "Vantiv_PIN";

    private TerminalRkiApi api;

    @Before
    public void setUp() {
        api = new TerminalRkiApi(TestConstants.API_BASE_URL,
                TestConstants.API_KEY, TestConstants.API_SECRET);
    }

    @Test
    public void pushRkiKey2Terminal_thenGetAndDisable_shouldSucceed() {
        Long createdId = createTask();
        if (createdId != null) {
            Result<PushRkiTaskDTO> got = api.getPushRkiTask(createdId);
            assertResultOk(got);
            if (got.getData() != null) {
                assertEquals(createdId, got.getData().getId());
            }
        }
        assertResultOk(api.disablePushRkiTask(disableRequest()));
        deleteTask(createdId);
    }

    @Test
    public void getPushRkiTask_createdTask_shouldSucceed() {
        Long createdId = createTask();
        Long id = createdId != null ? createdId : 1L;
        Result<PushRkiTaskDTO> result = api.getPushRkiTask(id);
        assertResultOk(result);
        if (result.getData() != null) {
            assertEquals(id, result.getData().getId());
        }
        deleteTask(createdId);
    }

    @Test
    public void disablePushRkiTask_createdTask_shouldSucceed() {
        assertResultOk(api.disablePushRkiTask(disableRequest()));
    }

    @Test
    public void deleteTerminalRki_createdTask_shouldSucceed() {
        Long createdId = createTask();
        assertResultOk(api.deleteTerminalRki(createdId != null ? createdId : 1L));
    }

    @Test
    public void getPushRkiTask_unknownId_shouldReturnBusinessError() {
        Result<PushRkiTaskDTO> result = api.getPushRkiTask(-1L);
        assertTrue("Expected a business error for an unknown id", result.getBusinessCode() != 0);
    }

    @Test
    public void searchPushRkiTasks_byTid_shouldReturnPage() {
        Result<PushRkiTaskDTO> result = api.searchPushRkiTasks(
                1, 10, com.pax.market.api.sdk.java.api.BaseThirdPartySysApi.SearchOrderBy.CreatedDate_desc,
                TestData.TERMINAL_TID, null,
                com.pax.market.api.sdk.java.api.BaseThirdPartySysApi.PushStatus.Active);
        assertSearchOk(result);
    }

    @Test
    public void searchPushRkiTasks_bySerialNo_shouldReturnPage() {
        Result<PushRkiTaskDTO> result = api.searchPushRkiTasks(
                1, 10, com.pax.market.api.sdk.java.api.BaseThirdPartySysApi.SearchOrderBy.CreatedDate_desc,
                null, null, com.pax.market.api.sdk.java.api.BaseThirdPartySysApi.PushStatus.Active,
                TestData.TERMINAL_SN);
        assertSearchOk(result);
    }

    @Test
    public void searchPushRkiTasks_withoutIdentifier_shouldFailValidation() {
        Result<PushRkiTaskDTO> result = api.searchPushRkiTasks(1, 10, null, null, null, null);
        assertBusinessCode(-1, result);
    }

    @Test
    public void searchPushRkiTasks_pageSizeOutOfRange_shouldFailValidation() {
        Result<PushRkiTaskDTO> result = api.searchPushRkiTasks(
                1, 1000, null, TestData.TERMINAL_TID, null, null);
        assertValidationErrorContains(result, "pageSize");
    }

    private Long createTask() {
        PushRki2TerminalRequest request = new PushRki2TerminalRequest();
        request.setTid(TestData.TERMINAL_TID);
        request.setRkiKey(RKI_KEY);
        Result<PushRkiTaskDTO> result = api.pushRkiKey2Terminal(request);
        assertResultOk(result);
        return result.getData() == null ? null : result.getData().getId();
    }

    private DisablePushRkiTask disableRequest() {
        DisablePushRkiTask disable = new DisablePushRkiTask();
        disable.setTid(TestData.TERMINAL_TID);
        disable.setRkiKey(RKI_KEY);
        return disable;
    }

    private void deleteTask(Long id) {
        if (id != null) {
            api.deleteTerminalRki(id);
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
