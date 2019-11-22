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
import com.pax.market.api.sdk.java.api.terminalVariable.TerminalVariableApi;
import com.pax.market.api.sdk.java.api.terminalVariable.dto.ParameterVariable;
import com.pax.market.api.sdk.java.api.terminalVariable.dto.ParameterVariableDTO;
import com.pax.market.api.sdk.java.api.terminalVariable.dto.TerminalParameterVariableDeleteRequest;
import com.pax.market.api.sdk.java.api.terminalVariable.dto.TerminalParameterVariableRequest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PushHistoryApiTest {
    private static final Logger logger = LoggerFactory.getLogger(PushHistoryApiTest.class.getSimpleName());

    private static PushHistoryApi pushHistoryApi;

    @Before
    public void init(){
        pushHistoryApi = new PushHistoryApi("http://localhost:8080/p-market-api", "0TAJEJK00R8YYK3HY0LI", "PMJ81GE1V0OCMTE7IFJBKPBE0RU0UGUGIBNJAJ5O");
    }

    @Test
    public void testGetTerminalVariable(){
        Result<AppPushHistoryDTO> result = pushHistoryApi.searchAppPushHistory(1, 10, PushHistoryApi.PushHistorySearchOrderBy.AppPushTime, "com.pax.posviewer", "", PushHistoryApi.PushStatus.Success, null);
        Assert.assertEquals(0, result.getBusinessCode());
    }
}
