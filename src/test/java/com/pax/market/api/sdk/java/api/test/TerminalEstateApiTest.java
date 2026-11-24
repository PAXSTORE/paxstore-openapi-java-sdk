package com.pax.market.api.sdk.java.api.test;

import com.pax.market.api.sdk.java.api.base.dto.Result;
import com.pax.market.api.sdk.java.api.terminalEstate.TerminalEstateApi;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Integration tests for {@link TerminalEstateApi}.
 */
public class TerminalEstateApiTest extends IntegrationTestSupport {

    private TerminalEstateApi terminalEstateApi;

    @Before
    public void setUp() {
        terminalEstateApi = new TerminalEstateApi(TestConstants.API_BASE_URL,
                TestConstants.API_KEY, TestConstants.API_SECRET);
    }

    @Test
    public void verifyTerminalEstate_shouldSucceed() {
        Result<String> result = terminalEstateApi.verifyTerminalEstate(TestData.TERMINAL_SN);
        assertOk(result);
    }

    @Test
    public void verifyTerminalEstate_emptySerialNo_shouldFailValidation() {
        Result<String> result = terminalEstateApi.verifyTerminalEstate("");
        assertBusinessCode(-1, result);
    }
}
