package com.pax.market.api.sdk.java.api.test;

import com.pax.market.api.sdk.java.api.base.dto.Result;
import com.pax.market.api.sdk.java.api.pushHistory.PushHistoryApi;
import com.pax.market.api.sdk.java.api.pushHistory.PushHistoryApi.PushStatus;
import com.pax.market.api.sdk.java.api.pushHistory.dto.ParameterPushHistoryDTO;
import com.pax.market.api.sdk.java.api.util.StringUtils;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Integration tests for {@link PushHistoryApi}.
 */
public class PushHistoryApiTest extends IntegrationTestSupport {

    private static final String PACKAGE_NAME = "com.pax.android.demoapp";

    private PushHistoryApi pushHistoryApi;

    @Before
    public void setUp() {
        pushHistoryApi = new PushHistoryApi(TestConstants.API_BASE_URL,
                TestConstants.API_KEY, TestConstants.API_SECRET);
    }

    @Test
    public void searchParameterPushHistory_shouldReturnPage() throws Exception {
        Result<ParameterPushHistoryDTO> result = pushHistoryApi.searchParameterPushHistory(
                1, 10, PACKAGE_NAME, null,
                PushStatus.Success, StringUtils.parseDateTime("2019-11-20 00:00:00"));
        assertPage(result);
    }

    @Test
    public void searchLatestParameterPushHistory_shouldReturnPage() throws Exception {
        Result<ParameterPushHistoryDTO> result = pushHistoryApi.searchLatestParameterPushHistory(
                1, 10, PACKAGE_NAME, null,
                PushStatus.Success, StringUtils.parseDateTime("2019-11-20 00:00:00"));
        assertPage(result);
    }

    @Test
    public void searchParameterPushHistory_pageSizeOutOfRange_shouldFailValidation() {
        Result<ParameterPushHistoryDTO> result = pushHistoryApi.searchParameterPushHistory(
                1, 0, PACKAGE_NAME, null, null, null);
        assertValidationErrorContains(result, "pageSize");
    }
}
