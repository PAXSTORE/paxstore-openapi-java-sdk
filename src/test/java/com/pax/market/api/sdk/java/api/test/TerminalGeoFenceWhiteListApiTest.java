package com.pax.market.api.sdk.java.api.test;

import com.pax.market.api.sdk.java.api.base.dto.Result;
import com.pax.market.api.sdk.java.api.terminalGeoFenceWhiteList.TerminalGeoFenceWhiteListApi;
import com.pax.market.api.sdk.java.api.terminalGeoFenceWhiteList.TerminalGeoFenceWhiteListApi.SearchOrderBy;
import com.pax.market.api.sdk.java.api.terminalGeoFenceWhiteList.dto.TerminalGeoFenceWhiteListDTO;
import com.pax.market.api.sdk.java.api.terminalGeoFenceWhiteList.dto.TerminalGeoFenceWhiteListRequest;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Integration tests for {@link TerminalGeoFenceWhiteListApi}.
 *
 * <p>The whitelist entry is keyed by the terminal serial number, so tests use
 * the known dev terminal and always remove the entry afterwards.</p>
 */
public class TerminalGeoFenceWhiteListApiTest extends IntegrationTestSupport {

    private TerminalGeoFenceWhiteListApi api;

    @Before
    public void setUp() {
        api = new TerminalGeoFenceWhiteListApi(TestConstants.API_BASE_URL,
                TestConstants.API_KEY, TestConstants.API_SECRET);
    }

    private static TerminalGeoFenceWhiteListRequest request(String serialNo) {
        TerminalGeoFenceWhiteListRequest request = new TerminalGeoFenceWhiteListRequest();
        request.setSerialNo(serialNo);
        return request;
    }

    @Test
    public void searchGeoFenceWhiteList_shouldReturnPage() {
        Result<TerminalGeoFenceWhiteListDTO> result = api.searchGeoFenceWhiteList(
                1, 10, SearchOrderBy.CreatedDate_desc, null);
        assertPage(result);
    }

    @Test
    public void createGeoFenceWhiteList_shouldSucceed() {
        assertOk(api.createGeoFenceWhiteList(request(TestData.TERMINAL_SN)));
        try {
            Result<TerminalGeoFenceWhiteListDTO> page = api.searchGeoFenceWhiteList(
                    1, 10, SearchOrderBy.CreatedDate_desc, TestData.TERMINAL_SN);
            assertPage(page);
        } finally {
            api.deleteGeoFenceWhiteList(request(TestData.TERMINAL_SN));
        }
    }

    @Test
    public void deleteGeoFenceWhiteList_shouldSucceed() {
        assertOk(api.createGeoFenceWhiteList(request(TestData.TERMINAL_SN)));
        try {
            assertOk(api.deleteGeoFenceWhiteList(request(TestData.TERMINAL_SN)));
        } finally {
            api.deleteGeoFenceWhiteList(request(TestData.TERMINAL_SN));
        }
    }

    @Test
    public void createGeoFenceWhiteList_unknownTerminal_shouldReturnBusinessError() {
        Result<String> result = api.createGeoFenceWhiteList(request(uniqueName("it-sn-not-exist")));
        assertTrue("Unknown terminal should not succeed", result.getBusinessCode() != 0);
        assertNotNull(result.getMessage());
    }

    @Test
    public void searchGeoFenceWhiteList_pageSizeOutOfRange_shouldFailValidation() {
        Result<TerminalGeoFenceWhiteListDTO> result = api.searchGeoFenceWhiteList(1, 0, null, null);
        assertValidationErrorContains(result, "pageSize");
    }
}
