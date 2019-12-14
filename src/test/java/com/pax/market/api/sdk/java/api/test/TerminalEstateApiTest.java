package com.pax.market.api.sdk.java.api.test;

import com.pax.market.api.sdk.java.api.base.dto.Result;
import com.pax.market.api.sdk.java.api.terminalEstate.TerminalEstateApi;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author liukai
 * @date 2019/10/15
 */
public class TerminalEstateApiTest {

    private static final Logger logger = LoggerFactory.getLogger(TerminalEstateApiTest.class.getSimpleName());

    TerminalEstateApi terminalEstateApi;

    @Before
    public void init() {
        terminalEstateApi = new TerminalEstateApi(TestConstants.API_BASE_URL, TestConstants.API_KEY, TestConstants.API_SECRET);
    }

    @Test
    public void testVerifyTerminalEstateBySN() {
        String terminalSN = "1350001853";
        Result<String> result = terminalEstateApi.verifyTerminalEstate(terminalSN);
        logger.debug("Result of verify terminal estate by SN: {}",result.toString());
        Assert.assertTrue(result.getBusinessCode() == 0);
    }
}
