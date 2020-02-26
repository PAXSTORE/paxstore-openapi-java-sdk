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
import com.pax.market.api.sdk.java.api.terminalApk.dto.FileParameter;
import com.pax.market.api.sdk.java.api.terminalGroupApk.TerminalGroupApkApi;
import com.pax.market.api.sdk.java.api.terminalGroupApk.dto.CreateTerminalGroupApkRequest;
import com.pax.market.api.sdk.java.api.terminalGroupApk.dto.SimpleTerminalGroupApkDTO;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description
 * @Author: Shawn
 * @Date: 2019/11/29 15:29
 * @Version 7.1
 */
public class TerminalGroupApkApiTest {
    private static final Logger logger = LoggerFactory.getLogger(TerminalGroupApkApiTest.class.getSimpleName());

    static TerminalGroupApkApi terminalGroupApkApi;
    static Long GroupApkId =1728L;
    @Before
    public void init() {
        terminalGroupApkApi = new TerminalGroupApkApi(TestConstants.API_BASE_URL, TestConstants.API_KEY, TestConstants.API_SECRET);
    }

    @Test
    public void testCreateTerminalGroupApk() {
        CreateTerminalGroupApkRequest createRequest = new CreateTerminalGroupApkRequest();
        createRequest.setGroupId(16543L); //test Dy Group
      //  createRequest.setPushTemplateName("8799");
        createRequest.setPackageName(null);

      //  createRequest.setTemplateName("PassWord_Param02.xml");
        Map<String, String> parameters = new HashMap<String, String>();
        parameters.put("sys_F1_sys_cap_test01", "abc");
        parameters.put("sys_F1_sys_cap_test02", "123");
        parameters.put("sys_F1_sys_cap_password", "123");
        createRequest.setParameters(null);

        FileParameter fileParameter = new FileParameter();
        fileParameter.setPid("PID.cardBinFile");
        fileParameter.setFileName("cardBinFile.jpeg");
        fileParameter.setFileData("data:image/jpeg;base64,/9j/4AAQSkZJR==");
        List<FileParameter> base64FileParameters = new ArrayList<>();
        base64FileParameters.add(fileParameter);
        createRequest.setBase64FileParameters(null);
        Result<SimpleTerminalGroupApkDTO> result = terminalGroupApkApi.createAndActiveGroupApk(createRequest);
        logger.debug("Result of create terminal Group Apk: {}", result.toString());
        Assert.assertTrue(result.getBusinessCode() == 0);
    }

    @Test
    public void  testSearchTerminalGroupApk() {
        Result<SimpleTerminalGroupApkDTO> result = terminalGroupApkApi.searchTerminalGroupApk(1,5, TerminalGroupApkApi.SearchOrderBy.CreatedDate_asc,16526L,true,null,null);
        logger.debug("Result of  terminal Group Apk list: {}", result.toString());
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
