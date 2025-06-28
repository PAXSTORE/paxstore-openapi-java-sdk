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

import ch.qos.logback.classic.LoggerContext;
import com.pax.market.api.sdk.java.api.base.dto.Result;
import com.pax.market.api.sdk.java.api.terminalApk.TerminalApkApi;
import com.pax.market.api.sdk.java.api.terminalApk.dto.CreateTerminalApkRequest;
import com.pax.market.api.sdk.java.api.terminalApk.dto.FileParameter;
import com.pax.market.api.sdk.java.api.terminalApk.dto.TerminalApkDTO;
import com.pax.market.api.sdk.java.api.terminalApk.dto.UpdateTerminalApkRequest;
import com.pax.market.api.sdk.java.api.util.EnhancedJsonUtils;
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
    public void testSearchTerminalApkList() {
        String terminalTid = "LNFAJ1HC";
        String testPackageName = "com.orange.onekeylockscreen";
        Result<TerminalApkDTO> result = terminalApkApi.searchTerminalApk(1,1,TerminalApkApi.SearchOrderBy.CreatedDate_desc,
                terminalTid, testPackageName, TerminalApkApi.PushStatus.Active);
        logger.info("Result of search terminalApk:{}", result.toString());
        Assert.assertTrue(result.getBusinessCode() == 0);
    }
    @Test
    public void testSearchTerminalApkListTidContainPidList() {
        String terminalTid = "LNFAJ1HC";
        String testPackageName = "com.orange.onekeylockscreen";
        List<String> pidList = new ArrayList<>();
        pidList.add("sys_F2_pid3");
        Result<TerminalApkDTO> result = terminalApkApi.searchTerminalApk(1,1,TerminalApkApi.SearchOrderBy.CreatedDate_desc,
                terminalTid, testPackageName, TerminalApkApi.PushStatus.Active, pidList);
        logger.debug("Result of search terminalApk:{}", EnhancedJsonUtils.toJson(result));

        Assert.assertTrue(result.getBusinessCode() == 0);
    }
    @Test
    public void testSearchTerminalApkListContainPidList() {
        String serialNo= "TESTCLIENT";
        String testPackageName = "com.orange.onekeylockscreen";
        List<String> pidList = new ArrayList<>();
        pidList.add("sys_F2_pid3");
        Result<TerminalApkDTO> result = terminalApkApi.searchTerminalApk(1,1,TerminalApkApi.SearchOrderBy.CreatedDate_desc,
                null, testPackageName, TerminalApkApi.PushStatus.Active, serialNo, pidList);
        logger.info("Result of search terminalApk:{}", result.toString());
        Assert.assertTrue(result.getBusinessCode() == 0);
    }
    
    @Test
    public void testCreateUninstallSuspendTerminalApk() {
        String terminalTid = "FH6XJ2J6";
        String testPackageName = "com.alibaba.android.rimet";
    	CreateTerminalApkRequest createTerminalApkRequest = new CreateTerminalApkRequest();
    	createTerminalApkRequest.setTid(terminalTid);
//    	createTerminalApkRequest.setTid("S9F0RA7V");
    	
    	
    	createTerminalApkRequest.setPackageName(testPackageName);
    	
//    	createTerminalApkRequest.setPackageName("com.pax.android.lm");
    	
    	createTerminalApkRequest.setVersion("6.5.5");
    	createTerminalApkRequest.setTemplateName("combine.xml");
    	Map<String, String> parameters = new HashMap<String, String>();
    	parameters.put("F1_PID2", "T1test3rd");

/*        FileParameter fileParameter = new FileParameter();
        fileParameter.setPid("PID.cardBinFile");
        fileParameter.setFileName("cardBinFile.jpeg");
        fileParameter.setFileData("data:image/jpeg;base64,/9j/4AAQSkZJR==");
        List<FileParameter> base64FileParameters = new ArrayList<>();
        base64FileParameters.add(fileParameter);
        createTerminalApkRequest.setBase64FileParameters(base64FileParameters);*/

    	createTerminalApkRequest.setParameters(parameters);
    	Result<TerminalApkDTO> result = terminalApkApi.createTerminalApk(createTerminalApkRequest);
    	Assert.assertTrue(result.getBusinessCode() == 0);
    	logger.info(result.toString());

/*    	//Test Get
        Result<TerminalApkDTO> newTerminalApkDTO = terminalApkApi.getTerminalApk(result.getData().getId());
        Assert.assertTrue(newTerminalApkDTO.getBusinessCode() == 0);

    	//Test Suspend
        UpdateTerminalApkRequest updateTerminalApkRequest = new UpdateTerminalApkRequest();
        updateTerminalApkRequest.setTid(terminalTid);
        updateTerminalApkRequest.setPackageName(testPackageName);

        Result<String> suspendResult = terminalApkApi.disableApkPush(updateTerminalApkRequest);
        Assert.assertTrue(suspendResult.getBusinessCode() == 0);
        logger.info(suspendResult.toString());

        //Test Uninstall
        Result<String> uninstallResult = terminalApkApi.uninstallApk(updateTerminalApkRequest);
        Assert.assertTrue(uninstallResult.getBusinessCode() == 0 || uninstallResult.getBusinessCode() == 2037);
        logger.info(uninstallResult.toString());*/
    }

    @Test
    public void testUninstallTerminalApk() {
        UpdateTerminalApkRequest updateTerminalApkRequest = new UpdateTerminalApkRequest();
        updateTerminalApkRequest.setTid("PUBDXO6SE");
        updateTerminalApkRequest.setPackageName("zz.dela.cmcc.traffic");

        Result<String> uninstallResult = terminalApkApi.uninstallApk(updateTerminalApkRequest);
        Assert.assertTrue(uninstallResult.getBusinessCode() == 0);
        logger.info(uninstallResult.toString());
    }

    @Test
    public void testCreate(){
        String terminalTid = "SXDFPOM0";
        String testPackageName = "com.baidu.tieba";
        CreateTerminalApkRequest createTerminalApkRequest = new CreateTerminalApkRequest();
        createTerminalApkRequest.setTid(terminalTid);
//    	createTerminalApkRequest.setTid("S9F0RA7V");
  //      createTerminalApkRequest.setPushTemplateName("8799");


        createTerminalApkRequest.setPackageName(testPackageName);

//    	createTerminalApkRequest.setPackageName("com.pax.android.lm");

    //    createTerminalApkRequest.setVersion("10.3.8.30");
   // 	createTerminalApkRequest.setTemplateName("10个text字段 - 副本2.xml");
    //	createTerminalApkRequest.setTemplateName("PassWord_Param02.xml");
        Map<String, String> parameters = new HashMap<String, String>();
        parameters.put("sys_F1_sys_cap_test01", "abc");
        parameters.put("sys_F1_sys_cap_test02", "123");
        parameters.put("sys_F1_sys_cap_password", "123");

        FileParameter fileParameter = new FileParameter();
        fileParameter.setPid("PID.cardBinFile");
        fileParameter.setFileName("cardBinFile.jpeg");
        fileParameter.setFileData("data:image/jpeg;base64,/9j/4AAQSkZJR==");
        List<FileParameter> base64FileParameters = new ArrayList<>();
        base64FileParameters.add(fileParameter);
        createTerminalApkRequest.setBase64FileParameters(null);

        createTerminalApkRequest.setParameters(null);
        Result<TerminalApkDTO> result = terminalApkApi.createTerminalApk(createTerminalApkRequest);
        Assert.assertTrue(result.getBusinessCode() == 0);
        logger.info(result.toString());
    }
}