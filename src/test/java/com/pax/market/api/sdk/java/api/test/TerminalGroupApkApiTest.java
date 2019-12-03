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
import com.pax.market.api.sdk.java.api.terminalGroupApk.TerminalGroupApkApi;
import com.pax.market.api.sdk.java.api.terminalGroupApk.dto.SimpleTerminalGroupApkDTO;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Description
 * @Author: Shawn
 * @Date: 2019/11/29 15:29
 * @Version 1.0
 */
public class TerminalGroupApkApiTest {
    private static final Logger logger = LoggerFactory.getLogger(TerminalGroupApkApiTest.class.getSimpleName());

    static TerminalGroupApkApi terminalGroupApkApi;
    static Long GroupApkId =1655L;
    @Before
    public void init() {
        terminalGroupApkApi = new TerminalGroupApkApi(TestConstants.API_BASE_URL, TestConstants.API_KEY, TestConstants.API_SECRET);
    }

    @Test
    public void testCreateTerminalGroupApk() {
        Result<SimpleTerminalGroupApkDTO> result = terminalGroupApkApi.getTerminalGroupApk(GroupApkId);
        logger.debug("Result of  terminal Group Apk: {}", result.toString());
        Assert.assertTrue(result.getBusinessCode() == 0);
    }

    @Test
    public void testGetTerminalGroupApk() {
        Result<SimpleTerminalGroupApkDTO> result = terminalGroupApkApi.getTerminalGroupApk(GroupApkId);
        logger.debug("Result of  terminal Group Apk: {}", result.toString());
        Assert.assertTrue(result.getBusinessCode() == 0);
    }


    @Test
    public void suspendTerminalGroupApk() {
        Result<SimpleTerminalGroupApkDTO> result = terminalGroupApkApi.suspendTerminalGroupApk(GroupApkId);
        logger.debug("Result of Suspend terminal Group Apk: {}", result.toString());
        Assert.assertTrue(result.getBusinessCode() == 0);
    }

    @Test
    public void deleteTerminalGroupApk() {
        Result<String> result = terminalGroupApkApi.deleteTerminalGroupApk(GroupApkId);
        logger.debug("Result of Suspend terminal Group Apk: {}", result.toString());
        Assert.assertTrue(result.getBusinessCode() == 0);
    }


}
