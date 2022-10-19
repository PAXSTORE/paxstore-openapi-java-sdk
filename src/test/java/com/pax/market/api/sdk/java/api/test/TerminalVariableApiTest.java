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
import com.pax.market.api.sdk.java.api.terminalVariable.TerminalVariableApi;
import com.pax.market.api.sdk.java.api.variable.dto.ParameterVariable;
import com.pax.market.api.sdk.java.api.variable.dto.ParameterVariableDTO;
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

/**
 * @Description
 * @Author: Shawn
 * @Date: 2019/11/11 18:27
 * @Version 1.0
 */
public class TerminalVariableApiTest {
    private static final Logger logger = LoggerFactory.getLogger(TerminalVariableApiTest.class.getSimpleName());

    static TerminalVariableApi terminalVariableApi;

    @Before
    public void init(){
        terminalVariableApi = new TerminalVariableApi(TestConstants.API_BASE_URL, TestConstants.API_KEY, TestConstants.API_SECRET);
    }

    @Test
    public void testGetTerminalVariable(){
        Result<ParameterVariableDTO> result = terminalVariableApi.getTerminalVariable(1,2,TerminalVariableApi.SearchOrderBy.Variable_asc,"124465D345",null,null,null,null);
        logger.debug("Result of  terminal variable: {}",result.toString());
        Assert.assertTrue(result.getBusinessCode() == 0);
    }


    @Test
    public void  testCreateTerminalVariable() throws IOException {
        TerminalParameterVariableRequest createRequest = new TerminalParameterVariableRequest();
        ParameterVariable parameterVariable1 = new ParameterVariable();
        parameterVariable1.setKey("testUpdateVariable-key-WITHOUT-APPiD-BO");
        parameterVariable1.setValue("testUpdateVariable-value1");
        parameterVariable1.setRemarks("今日头条app testCreateApi3");
        ParameterVariable parameterVariable2 = new ParameterVariable();
        parameterVariable2.setKey("testCreateVariable1Api4-BO");
        parameterVariable2.setValue(null);
        parameterVariable2.setPackageName("");
        parameterVariable2.setRemarks("今日头条app testCreateApi4");
        List<ParameterVariable> variableList = new ArrayList<>();
      //  variableList.add(parameterVariable1);
        variableList.add(parameterVariable2);
        createRequest.setTid("124465D345");
        createRequest.setVariableList(null);
        Result<String> createResult = terminalVariableApi.createTerminalVariable(createRequest);
        logger.debug("Result of create terminal variable: {}",createResult.toString());
        Assert.assertTrue(createResult.getBusinessCode() == 0);

    }

    @Test
    public void  testUpdateTerminalVariable() throws IOException {
        Long terminalVariableId = 1000001148L;

        ParameterVariable updateRequest = new ParameterVariable();
        updateRequest.setKey("testUpdateVariable-key2-appid-BO-UPDATE");
        updateRequest.setValue("testUpdateVariable-value1");
        updateRequest.setRemarks("updateRemarks1");
        updateRequest.setPackageName("com.ss.android.article.lite");

        Result<String> updateResult = terminalVariableApi.updateTerminalVariable(terminalVariableId,updateRequest);
        logger.debug("Result of update terminal variable: {}",updateResult.toString());
        Assert.assertTrue(updateResult.getBusinessCode() == 0);

    }


    @Test
    public void  testDeleteTerminalVariable() throws IOException {
        Long terminalVariableId = 1000001156L;
        Result<String> deleteResult = terminalVariableApi.deleteTerminalVariable(terminalVariableId);
        logger.debug("Result of delete terminal variable: {}",deleteResult.toString());
        Assert.assertTrue(deleteResult.getBusinessCode() == 0);
    }

    @Test
    public void  testBatchDeletionTerminalVariable() throws IOException {
        TerminalParameterVariableDeleteRequest batchDeletionRequest = new TerminalParameterVariableDeleteRequest();
        List<Long> variableIds = new ArrayList<>();
        variableIds.add(1000001137L);
        variableIds.add(32423523L);
        batchDeletionRequest.setVariableIds(variableIds);
        Result<String> batchDeletionResult = terminalVariableApi.batchDeletionTerminalVariable(batchDeletionRequest);
        logger.debug("Result of batch deletion terminal variable: {}",batchDeletionResult.toString());
        Assert.assertTrue(batchDeletionResult.getBusinessCode() == 0);
    }
}
