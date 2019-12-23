/*
 * ********************************************************************************
 * COPYRIGHT
 *               PAX TECHNOLOGY, Inc. PROPRIETARY INFORMATION
 *   This software is supplied under the terms of a license agreement or
 *   nondisclosure agreement with PAX  Technology, Inc. and may not be copied
 *   or disclosed except in accordance with the terms in that agreement.
 *
 *      Copyright (C) 2017 PAX Technology, Inc. All rights reserved.
 * ********************************************************************************
 */
package com.pax.market.api.sdk.java.api.test;

import com.pax.market.api.sdk.java.api.base.dto.Result;
import com.pax.market.api.sdk.java.api.pushHistory.PushHistoryApi;
import com.pax.market.api.sdk.java.api.pushHistory.dto.AppPushHistoryDTO;
import com.pax.market.api.sdk.java.api.util.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.util.Date;

public class PushHistoryApiTest {
    private static final Logger logger = LoggerFactory.getLogger(PushHistoryApiTest.class.getSimpleName());

    private static PushHistoryApi pushHistoryApi;

    @Before
    public void init(){
        pushHistoryApi = new PushHistoryApi(TestConstants.API_BASE_URL, TestConstants.API_KEY, TestConstants.API_SECRET);
    }

    @Test
    public void searchAppPushHistory(){
        Result<AppPushHistoryDTO> result = pushHistoryApi.searchAppPushHistory(1, 1000,  "com.pax.android.demoapp", "", null, null, null, null);
        logger.debug("Result of search App push History: {}",result.toString());
        Assert.assertEquals(0, result.getBusinessCode());
    }

    @Test
    public void searchLastAppPushHistory() throws ParseException {
        Result<AppPushHistoryDTO> result = pushHistoryApi.searchLastAppPushHistory(1, 1000,  "com.pax.android.demoapp", "", null, null, StringUtils.parseDateTime("2019-10-20 00:00:00"), null);
        logger.debug("Result of search Last App push History: {}",result.toString());
        Assert.assertEquals(0, result.getBusinessCode());
    }
}
