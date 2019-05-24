package com.pax.market.api.sdk.java.api.test;

import com.pax.market.api.sdk.java.api.base.dto.Result;
import com.pax.market.api.sdk.java.api.terminalFirmware.TerminalFirmwareApi;
import com.pax.market.api.sdk.java.api.terminalFirmware.dto.CreateTerminalFirmwareRequest;
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
        CreateTerminalFirmwareRequest createTerminalFirmwareRequest = new CreateTerminalFirmwareRequest();
        createTerminalFirmwareRequest.setTid("PUBDXO6SE");
        createTerminalFirmwareRequest.setFmName("PayDroid_5.1.1_Aquarius_V02.3.05_20170831");

        Result<String> result = terminalFirmwareApi.createTerminalFirmware(createTerminalFirmwareRequest);
        Assert.assertTrue(result.getBusinessCode() == 0);
        logger.info(result.toString());
    }

}
