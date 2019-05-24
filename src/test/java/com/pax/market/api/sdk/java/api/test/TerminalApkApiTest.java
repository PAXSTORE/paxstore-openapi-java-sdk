/*
 * *******************************************************************************
 * COPYRIGHT
 *               PAX TECHNOLOGY, Inc. PROPRIETARY INFORMATION
 *   This software is supplied under the terms of a license agreement or
 *   nondisclosure agreement with PAX  Technology, Inc. and may not be copied
 *   or disclosed except in accordance with the terms in that agreement.
 *
 *      Copyright (C) 2017 PAX Technology, Inc. All rights reserved.
 * *******************************************************************************
 */

package com.pax.market.api.sdk.java.api.test;

import java.util.HashMap;
import java.util.Map;

import com.pax.market.api.sdk.java.api.terminalApk.dto.UpdateTerminalApkRequest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pax.market.api.sdk.java.api.base.dto.Result;
import com.pax.market.api.sdk.java.api.terminalApk.TerminalApkApi;
import com.pax.market.api.sdk.java.api.terminalApk.dto.CreateTerminalApkRequest;

/**
 * Description
 *
 * @author tanjie
 * @date 2018-06-06
 */
public class TerminalApkApiTest {
	
	private static final Logger logger = LoggerFactory.getLogger(TerminalApiTest.class.getSimpleName());
	
	static TerminalApkApi terminalApkApi;
	
	static Long createdTerminalId = 0L;
	
    
    @Before
    public void init(){
    	terminalApkApi = new TerminalApkApi(TestConstants.API_BASE_URL, TestConstants.API_KEY, TestConstants.API_SECRET);
    	
    }
    
    @Test
    public void testCreateUninstallSuspendTerminalApk() {
        String terminalTid = "PZYL32EZH";
        String testPackageName = "zz.dela.cmcc.traffic";
    	CreateTerminalApkRequest createTerminalApkRequest = new CreateTerminalApkRequest();
    	createTerminalApkRequest.setTid(terminalTid);
//    	createTerminalApkRequest.setTid("S9F0RA7V");
    	
    	
    	createTerminalApkRequest.setPackageName(testPackageName);
    	
//    	createTerminalApkRequest.setPackageName("com.pax.android.lm");
    	
    	createTerminalApkRequest.setVersion("3.4.7");
//    	createTerminalApkRequest.setTemplateName("10个text字段 - 副本2.xml");
//    	createTerminalApkRequest.setTemplateName("param02.xml");
    	Map<String, String> parameters = new HashMap<String, String>();
//    	parameters.put("sys.cap.emvParamCheckType", "abc");
    	
    	createTerminalApkRequest.setParameters(parameters);
    	Result<String> result = terminalApkApi.createTerminalApk(createTerminalApkRequest);
    	Assert.assertTrue(result.getBusinessCode() == 0);
    	logger.info(result.toString());

    	//Test Suspend
        UpdateTerminalApkRequest updateTerminalApkRequest = new UpdateTerminalApkRequest();
        updateTerminalApkRequest.setTid(terminalTid);
        updateTerminalApkRequest.setPackageName(testPackageName);

        Result<String> suspendResult = terminalApkApi.suspendTerminalApk(updateTerminalApkRequest);
        Assert.assertTrue(suspendResult.getBusinessCode() == 0);
        logger.info(suspendResult.toString());

        //Test Uninstall
        Result<String> uninstallResult = terminalApkApi.uninstallTerminalApk(updateTerminalApkRequest);
        Assert.assertTrue(uninstallResult.getBusinessCode() == 0 || uninstallResult.getBusinessCode() == 2037);
        logger.info(uninstallResult.toString());
    }

    @Test
    public void testUninstallTerminalApk() {
        UpdateTerminalApkRequest updateTerminalApkRequest = new UpdateTerminalApkRequest();
        updateTerminalApkRequest.setTid("PUBDXO6SE");
        updateTerminalApkRequest.setPackageName("zz.dela.cmcc.traffic");

        Result<String> uninstallResult = terminalApkApi.uninstallTerminalApk(updateTerminalApkRequest);
        Assert.assertTrue(uninstallResult.getBusinessCode() == 0);
        logger.info(uninstallResult.toString());
    }
}