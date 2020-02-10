package com.pax.market.api.sdk.java.api.test;

import com.pax.market.api.sdk.java.api.base.dto.Result;
import com.pax.market.api.sdk.java.api.terminalRki.TerminalRkiApi;
import com.pax.market.api.sdk.java.api.terminalRki.dto.DisablePushRkiTask;
import com.pax.market.api.sdk.java.api.terminalRki.dto.PushRki2TerminalRequest;
import com.pax.market.api.sdk.java.api.terminalRki.dto.PushRkiTaskDTO;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author liukai
 * @date 2020/2/7
 */
public class TerminalRkiApiTest {

    private static final Logger logger = LoggerFactory.getLogger(TerminalRkiApiTest.class.getSimpleName());

    static TerminalRkiApi terminalRkiApi;

    @Before
    public void init(){
        terminalRkiApi = new TerminalRkiApi(TestConstants.API_BASE_URL, TestConstants.API_KEY, TestConstants.API_SECRET);
    }

    @Test
    public void testCreateTerminalRki() {
        String terminalTid = "3KJQMK6C";
        String rkiKey = "Vantiv_PIN";
        PushRki2TerminalRequest pushRki2TerminalRequest = new PushRki2TerminalRequest();
        pushRki2TerminalRequest.setTid(terminalTid);
        pushRki2TerminalRequest.setRkiKey(rkiKey);

        Result<PushRkiTaskDTO> result = terminalRkiApi.pushRkiKey2Terminal(pushRki2TerminalRequest);
        Assert.assertTrue(result.getBusinessCode() == 0);
        logger.info(result.toString());

        //Test Get
        Result<PushRkiTaskDTO> newPushFmTask = terminalRkiApi.getPushRkiTask(result.getData().getId());
        Assert.assertTrue(newPushFmTask.getBusinessCode() == 0);

        //Test Suspend
        DisablePushRkiTask disablePushRkiTask = new DisablePushRkiTask();
        disablePushRkiTask.setTid(terminalTid);
        disablePushRkiTask.setRkiKey(rkiKey);

        Result<String> suspendResult = terminalRkiApi.disablePushRkiTask(disablePushRkiTask);
        Assert.assertTrue(suspendResult.getBusinessCode() == 0);
        logger.info(suspendResult.toString());
    }

    @Test
    public void testSearchTerminalRkiList() {
        String terminalTid = "GAPXQNMQ";
        Result<PushRkiTaskDTO> result = terminalRkiApi.searchPushRkiTasks(1,12,TerminalRkiApi.SearchOrderBy.CreatedDate_desc,
                terminalTid, "", TerminalRkiApi.PushStatus.Active);
        logger.debug("Result of search terminalRki:{}", result.toString());
        Assert.assertTrue(result.getBusinessCode() == 0);
    }
}
