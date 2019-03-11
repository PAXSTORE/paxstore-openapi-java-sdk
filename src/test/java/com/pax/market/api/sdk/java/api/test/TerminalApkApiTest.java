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
    	terminalApkApi = new  TerminalApkApi(TestConstants.API_BASE_URL, TestConstants.API_KEY, TestConstants.API_SECRET);
    	
    }
    
    @Test
    public void testCreateTerminalApk() {
    	CreateTerminalApkRequest createTerminalApkRequest = new CreateTerminalApkRequest();
    	createTerminalApkRequest.setTid("0000002214479770");
//    	createTerminalApkRequest.setTid("S9F0RA7V");
    	
    	
    	createTerminalApkRequest.setPackageName("com.pax.us.pay.std.vantiv");
    	
//    	createTerminalApkRequest.setPackageName("com.pax.android.lm");
    	
//    	createTerminalApkRequest.setVersion("5.7.3.0");
    	createTerminalApkRequest.setTemplateName("Restaurant.zip");
//    	createTerminalApkRequest.setTemplateName("param02.xml");
    	Map<String, String> parameters = new HashMap<String, String>();
//    	parameters.put("sys.cap.emvParamCheckType", "abc");
    	
    	createTerminalApkRequest.setParameters(parameters);
    	Result<String> result = terminalApkApi.createTerminalApk(createTerminalApkRequest);
    	Assert.assertTrue(result.getBusinessCode() == 0);
    	logger.info(result.toString());
    	
    }


}
