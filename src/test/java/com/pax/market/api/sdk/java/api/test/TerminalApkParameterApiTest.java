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
import com.pax.market.api.sdk.java.api.terminalApkParameter.TerminalApkParameterApi;
import com.pax.market.api.sdk.java.api.terminalApkParameter.dto.ApkParameterDTO;
import com.pax.market.api.sdk.java.api.terminalApkParameter.dto.CreateApkParameterRequest;


import com.pax.market.api.sdk.java.api.terminalApkParameter.dto.UpdateApkParameterRequest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.util.*;


/**
 * @Description
 * @Author: Shawn
 * @Date: 2019/10/31 10:58
 * @Version 7.0
 */
public class TerminalApkParameterApiTest {

    private static final Logger logger = LoggerFactory.getLogger(TerminalApkParameterApiTest.class.getSimpleName());

    static TerminalApkParameterApi terminalApkParameterApi;




    @Before
    public void init(){
        terminalApkParameterApi = new TerminalApkParameterApi(TestConstants.API_BASE_URL, TestConstants.API_KEY, TestConstants.API_SECRET, Locale.ENGLISH);
    }


    @Test
    public void testSearchTerminalApkParameter(){
//        Result<ApkParameterDTO> result = terminalApkParameterApi
//                .searchTerminalApkParameter(1,2, TerminalApkParameterApi.SearchOrderBy.ApkParameter_asc,null,
//                        "com.ss.android.article.lite","6.6.4");

        Result<ApkParameterDTO> result = terminalApkParameterApi
                .searchTerminalApkParameter(1,0, TerminalApkParameterApi.SearchOrderBy.ApkParameter_asc,null,
                        null,"6.6.4");
        logger.debug("Result of search terminal Apk Parameter: {}",result.toString());
        Assert.assertTrue(result.getBusinessCode() == 0);
    }

    @Test
    public void  testCreateTerminalApkParameter() throws IOException {
        CreateApkParameterRequest createApkParameterRequest = new CreateApkParameterRequest();
        createApkParameterRequest.setParamTemplateName("3243.xml");
        createApkParameterRequest.setName("testCreate3RD-result-api-test-CREATEbY-newest-12334111");
        createApkParameterRequest.setPackageName("com.baidu.tieba");
        createApkParameterRequest.setVersion("10.3.8.30");
        Map<String, String> parameters = new HashMap<String, String>();
    	parameters.put("sys.cap.emvParamCheckType", "abc");
        createApkParameterRequest.setParameters(parameters);

        FileParameter fileParameter = new FileParameter();
        fileParameter.setPid("wsplink.param.img");
        fileParameter.setFileName("cardBinFile.jpeg");
        fileParameter.setFileData("data:image/jpeg;base64,/9j/4AAQSkZJR==");
        List<FileParameter> base64FileParameters = new ArrayList<>();
        base64FileParameters.add(fileParameter);
        createApkParameterRequest.setBase64FileParameters(base64FileParameters);

        Result<String> result = terminalApkParameterApi.createApkParameter(createApkParameterRequest);
        logger.debug("Result of  terminal apk parameter: {}",result.toString());
        Assert.assertTrue(result.getBusinessCode() == 0);
    }

    @Test
    public void  testUpdateTerminalApkParameter() throws IOException {
       Long apkParameterId = 1149L;

        UpdateApkParameterRequest updateApkParameterRequest = new UpdateApkParameterRequest();
        updateApkParameterRequest.setParamTemplateName("1000084085_(3).xml|schema1.xml");
        Map<String, String> parameters = new HashMap<String, String>();
        parameters.put("sys.param.acqInsCode", "abc");
        parameters.put("wsplink_F1_wsplink_param_password", "123");
        parameters.put("wsplink_F1_wsplink_param_TID", "abc");
        parameters.put("wsplink_F1_wsplink_param_posID", "abc");
        parameters.put("wsplink_F1_wsplink_param_Token", "abc");
        parameters.put("wsplink_F1_wsplink_param_authURL0", "abc");
        parameters.put("wsplink_F1_wsplink_param_settingsPassword", "123");
        updateApkParameterRequest.setParameters(parameters);
        Result<String> result = terminalApkParameterApi.updateApkParameter(apkParameterId,updateApkParameterRequest);
        logger.debug("Result of  terminal apk update parameter: {}",result.toString());
        Assert.assertTrue(result.getBusinessCode() == 0);
    }

    @Test
    public void testDeleteTerminalApkParameter() throws IOException {
        Long apkParameterId = 1150L;
        Result<String> deleteResult = terminalApkParameterApi.deleteApkParameter(apkParameterId);
        logger.debug("Result of delete terminal apk parameter: {}",deleteResult.toString());
        Assert.assertTrue(deleteResult.getBusinessCode() == 0);
    }
}
