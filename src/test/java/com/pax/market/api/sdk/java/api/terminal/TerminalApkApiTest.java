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

package com.pax.market.api.sdk.java.api.terminal;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pax.market.api.sdk.java.api.base.dto.Result;
import com.pax.market.api.sdk.java.api.terminal.TerminalApi.TerminalStatus;
import com.pax.market.api.sdk.java.api.terminal.dto.TerminalCreateRequest;
import com.pax.market.api.sdk.java.api.terminal.dto.TerminalDTO;
import com.pax.market.api.sdk.java.api.terminalApk.TerminalApkApi;
import com.pax.market.api.sdk.java.api.terminalApk.dto.CreateTerminalApkRequest;
import com.pax.market.api.sdk.java.api.util.EnhancedJsonUtils;

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
	
    public static void init(){
    	terminalApkApi = new  TerminalApkApi("http://localhost:8080/p-market-api", "RCA9MDH6YN3WSSGPW6TJ", "TUNLDZVZECHNKZ4FW07XFCKN2W0N8ZDEA5ENKZYN");
    }
    
    public static void testCreateTerminalApk() {
    	CreateTerminalApkRequest createTerminalApkRequest = new CreateTerminalApkRequest();
    	createTerminalApkRequest.setTid("ABC09098989");
    	createTerminalApkRequest.setPackageName("com.baidu.map");
    	createTerminalApkRequest.setTemplateName("template_map");
    	Map<String, String> parameters = new HashMap<String, String>();
    	parameters.put("PID.locationCode", "cn_js_sz");
    	parameters.put("PID.showtraffic", "true");
    	createTerminalApkRequest.setParameters(parameters);
    	terminalApkApi.createTerminalApk(createTerminalApkRequest);
    }


}
