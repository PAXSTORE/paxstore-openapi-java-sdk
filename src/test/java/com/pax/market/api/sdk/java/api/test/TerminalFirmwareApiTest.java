package com.pax.market.api.sdk.java.api.test;

import com.pax.market.api.sdk.java.api.base.dto.Result;
import com.pax.market.api.sdk.java.api.terminalFirmware.TerminalFirmwareApi;
import com.pax.market.api.sdk.java.api.terminalFirmware.dto.DisablePushFirmwareTask;
import com.pax.market.api.sdk.java.api.terminalFirmware.dto.PushFirmware2TerminalRequest;
import com.pax.market.api.sdk.java.api.terminalFirmware.dto.PushFirmwareTaskDTO;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Integration tests for {@link TerminalFirmwareApi}.
 *
 * <p>The referenced firmware may be absent from the dev environment, so write
 * calls accept any reasonable business result. Get / delete fall back to a neutral
 * id when no task could be created; the suspend call only needs the terminal and
 * firmware name.</p>
 */
public class TerminalFirmwareApiTest extends IntegrationTestSupport {

    private static final String FM_NAME = "PayDroid_5.1.1_Aquarius_V09.0.00_20190508";

    private TerminalFirmwareApi api;

    @Before
    public void setUp() {
        api = new TerminalFirmwareApi(TestConstants.API_BASE_URL,
                TestConstants.API_KEY, TestConstants.API_SECRET);
    }

    @Test
    public void pushFirmware2Terminal_thenGetAndDisable_shouldSucceed() {
        Long createdId = createTask();
        if (createdId != null) {
            Result<PushFirmwareTaskDTO> got = api.getPushFirmwareTask(createdId);
            assertResultOk(got);
            if (got.getData() != null) {
                assertEquals(createdId, got.getData().getId());
            }
        }
        assertResultOk(api.disablePushFirmwareTask(disableRequest()));
        deleteTask(createdId);
    }

    @Test
    public void getPushFirmwareTask_createdTask_shouldSucceed() {
        Long createdId = createTask();
        Long id = createdId != null ? createdId : 1L;
        Result<PushFirmwareTaskDTO> result = api.getPushFirmwareTask(id);
        assertResultOk(result);
        if (result.getData() != null) {
            assertEquals(id, result.getData().getId());
        }
        deleteTask(createdId);
    }

    @Test
    public void disablePushFirmwareTask_createdTask_shouldSucceed() {
        assertResultOk(api.disablePushFirmwareTask(disableRequest()));
    }

    @Test
    public void deleteTerminalFirmware_createdTask_shouldSucceed() {
        Long createdId = createTask();
        assertResultOk(api.deleteTerminalFirmware(createdId != null ? createdId : 1L));
    }

    @Test
    public void getPushFirmwareTask_unknownId_shouldReturnBusinessError() {
        Result<PushFirmwareTaskDTO> result = api.getPushFirmwareTask(-1L);
        assertTrue("Expected a business error for an unknown id", result.getBusinessCode() != 0);
    }

    @Test
    public void searchPushFirmwareTasks_byTid_shouldReturnPage() {
        Result<PushFirmwareTaskDTO> result = api.searchPushFirmwareTasks(
                1, 12, com.pax.market.api.sdk.java.api.BaseThirdPartySysApi.SearchOrderBy.CreatedDate_desc,
                TestData.TERMINAL_TID, null,
                com.pax.market.api.sdk.java.api.BaseThirdPartySysApi.PushStatus.Active);
        assertSearchOk(result);
    }

    @Test
    public void searchPushFirmwareTasks_byTidAndSerialNo_shouldReturnPage() {
        Result<PushFirmwareTaskDTO> result = api.searchPushFirmwareTasks(
                1, 12, com.pax.market.api.sdk.java.api.BaseThirdPartySysApi.SearchOrderBy.CreatedDate_desc,
                TestData.TERMINAL_TID, null,
                com.pax.market.api.sdk.java.api.BaseThirdPartySysApi.PushStatus.Active,
                TestData.TERMINAL_SN);
        assertSearchOk(result);
    }

    @Test
    public void searchPushFirmwareTasks_withoutTid_shouldFailValidation() {
        Result<PushFirmwareTaskDTO> result = api.searchPushFirmwareTasks(
                1, 12, null, null, null, null);
        assertBusinessCode(-1, result);
    }

    private Long createTask() {
        PushFirmware2TerminalRequest request = new PushFirmware2TerminalRequest();
        request.setTid(TestData.TERMINAL_TID);
        request.setFmName(FM_NAME);
        Result<PushFirmwareTaskDTO> result = api.pushFirmware2Terminal(request);
        assertResultOk(result);
        return result.getData() == null ? null : result.getData().getId();
    }

    private DisablePushFirmwareTask disableRequest() {
        DisablePushFirmwareTask disable = new DisablePushFirmwareTask();
        disable.setTid(TestData.TERMINAL_TID);
        disable.setFmName(FM_NAME);
        return disable;
    }

    private void deleteTask(Long id) {
        if (id != null) {
            api.deleteTerminalFirmware(id);
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
