package com.pax.market.api.sdk.java.api.test;

import com.pax.market.api.sdk.java.api.base.dto.Result;
import com.pax.market.api.sdk.java.api.terminalFirmware.TerminalFirmwareApi;
import com.pax.market.api.sdk.java.api.terminalFirmware.dto.DisablePushFirmwareTask;
import com.pax.market.api.sdk.java.api.terminalFirmware.dto.PushFirmware2TerminalRequest;
import com.pax.market.api.sdk.java.api.terminalFirmware.dto.PushFirmwareTaskDTO;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author liukai
 * @date 2019/5/23
 */
public class TerminalFirmwareApiTest {

    private static final Logger logger = LoggerFactory.getLogger(TerminalFirmwareApiTest.class.getSimpleName());

    static TerminalFirmwareApi terminalFirmwareApi;

    @Before
    public void init(){
        terminalFirmwareApi = new TerminalFirmwareApi(TestConstants.API_BASE_URL, TestConstants.API_KEY, TestConstants.API_SECRET);
    }

    @Test
    public void testCreateTerminalFirmware() {
        String terminalTid = "GAPXQNMQ";
        String fmName = "PayDroid_5.1.1_Aquarius_V09.0.00_20190508";
        PushFirmware2TerminalRequest pushFirmware2TerminalRequest = new PushFirmware2TerminalRequest();
        pushFirmware2TerminalRequest.setTid(terminalTid);
        pushFirmware2TerminalRequest.setFmName(fmName);

        Result<PushFirmwareTaskDTO> result = terminalFirmwareApi.pushFirmware2Terminal(pushFirmware2TerminalRequest);
        Assert.assertTrue(result.getBusinessCode() == 0);
        logger.info(result.toString());

        //Test Get
        Result<PushFirmwareTaskDTO> newPushFmTask = terminalFirmwareApi.getPushFirmwareTask(result.getData().getId());
        Assert.assertTrue(newPushFmTask.getBusinessCode() == 0);

        //Test Suspend
        DisablePushFirmwareTask disablePushFirmwareTask = new DisablePushFirmwareTask();
        disablePushFirmwareTask.setTid(terminalTid);
        disablePushFirmwareTask.setFmName(fmName);

        Result<String> suspendResult = terminalFirmwareApi.disablePushFirmwareTask(disablePushFirmwareTask);
        Assert.assertTrue(suspendResult.getBusinessCode() == 0);
        logger.info(suspendResult.toString());
    }

    @Test
    public void testSearchTerminalFirmwareList() {
        String terminalTid = "GAPXQNMQ";
        Result<PushFirmwareTaskDTO> result = terminalFirmwareApi.searchPushFirmwareTasks(1,12,TerminalFirmwareApi.SearchOrderBy.CreatedDate_desc,
                terminalTid, "", TerminalFirmwareApi.PushStatus.Active);
        logger.debug("Result of search terminalFm:{}", result.toString());
        Assert.assertTrue(result.getBusinessCode() == 0);
    }

    @Test
    public void testSearchTerminalFirmwareListBySerialNoAndTID() {
        String terminalTid = "GAPXQNMQ";
        String terminalSN = "GAPXQNMQSN";
        Result<PushFirmwareTaskDTO> result = terminalFirmwareApi.searchPushFirmwareTasks(1,12,TerminalFirmwareApi.SearchOrderBy.CreatedDate_desc,
                terminalTid, "", TerminalFirmwareApi.PushStatus.Active,terminalSN);
        logger.debug("Result of search terminalFm:{}", result.toString());
        Assert.assertTrue(result.getBusinessCode() == 0);
    }
}