/*
 * *******************************************************************************
 * COPYRIGHT
 *               PAX TECHNOLOGY, Inc. PROPRIETARY INFORMATION
 *   This software is supplied under the terms of a license agreement or
 *   nondisclosure agreement with PAX  Technology, Inc. and may not be copied
 *   or disclosed except in accordance with the terms in that agreement.
 *
 *      Copyright (C) 2022 PAX Technology, Inc. All rights reserved.
 * *******************************************************************************
 */

package com.pax.market.api.sdk.java.api.test;

import com.pax.market.api.sdk.java.api.base.dto.Result;
import com.pax.market.api.sdk.java.api.merchantVariable.MerchantVariableApi;
import com.pax.market.api.sdk.java.api.merchantVariable.dto.MerchantVariableCreateRequest;
import com.pax.market.api.sdk.java.api.merchantVariable.dto.MerchantVariableDTO;
import com.pax.market.api.sdk.java.api.merchantVariable.dto.MerchantVariableDeleteRequest;
import com.pax.market.api.sdk.java.api.merchantVariable.dto.MerchantVariableUpdateRequest;
import com.pax.market.api.sdk.java.api.variable.dto.ParameterVariable;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author shifan
 * @date 2022/10/19
 */
public class MerchantVariableApiTest {
    private static final Logger logger = LoggerFactory.getLogger(TerminalVariableApiTest.class.getSimpleName());

    static MerchantVariableApi merchantVariableApi;

    @Before
    public void init(){
        merchantVariableApi = new MerchantVariableApi(TestConstants.API_BASE_URL, TestConstants.API_KEY, TestConstants.API_SECRET);
    }


    @Test
    public void testSearchTerminalVariable(){
        Long merchantId = 113781L;
        Result<MerchantVariableDTO> result = merchantVariableApi.searchMerchantVariable(1,2,MerchantVariableApi.SearchOrderBy.Variable_asc, merchantId, null,null , MerchantVariableApi.VariableSource.MERCHANT);
        logger.debug("Result of merchant variable: {}",result.toString());
        Assert.assertEquals(0, result.getBusinessCode());

    }


    @Test
    public void  testCreateMerchantVariable() throws IOException {
        Long merchantId = 113781L;
        MerchantVariableCreateRequest createRequest = new MerchantVariableCreateRequest();
        ParameterVariable parameterVariable1 = new ParameterVariable();
        parameterVariable1.setKey("testCreateMerchantVariable-key1");
        parameterVariable1.setValue("testCreateMerchantVariable-value1");
        parameterVariable1.setRemarks("今日头条app testCreateApi3");
        ParameterVariable parameterVariable2 = new ParameterVariable();
        parameterVariable2.setKey("testCreateMerchantVariable-key2");
        parameterVariable2.setValue("testCreateMerchantVariable-value2");
        parameterVariable2.setPackageName("");
        parameterVariable2.setRemarks("testCreateApi2");
        List<ParameterVariable> variableList = new ArrayList<>();
        variableList.add(parameterVariable1);
        variableList.add(parameterVariable2);
        createRequest.setMerchantId(merchantId);
        createRequest.setVariableList(variableList);
        Result<String> createResult = merchantVariableApi.createMerchantVariable(createRequest);
        logger.debug("Result of create merchant variable: {}",createResult.toString());
        Assert.assertEquals(0, createResult.getBusinessCode());

    }

    @Test
    public void  testUpdateTerminalVariable() throws IOException {
        Long merchantVariableId = 1000000008L;
        MerchantVariableUpdateRequest updateRequest = new MerchantVariableUpdateRequest();
        updateRequest.setKey("testUpdateVariable-key1");
        updateRequest.setValue("testUpdateVariable-value1");
        updateRequest.setRemarks("updateRemarks1");
        updateRequest.setPackageName("com.jbangit.csapp");
        Result<String> updateResult = merchantVariableApi.updateMerchantVariable(merchantVariableId,updateRequest);
        logger.debug("Result of update merchant variable: {}",updateResult.toString());
        Assert.assertEquals(0, updateResult.getBusinessCode());

    }

    @Test
    public void  testDeleteMerchantVariable() throws IOException {
        Long merchantVariableId = 1000000008L;
        Result<String> deleteResult = merchantVariableApi.deleteMerchantVariable(merchantVariableId);
        logger.debug("Result of delete merchant variable: {}",deleteResult.toString());
        Assert.assertEquals(0, deleteResult.getBusinessCode());
    }

    @Test
    public void  testBatchDeletionMerchantVariable() throws IOException {
        MerchantVariableDeleteRequest batchDeletionRequest = new MerchantVariableDeleteRequest();
        List<Long> variableIds = new ArrayList<>();
        variableIds.add(1000000007L);
        variableIds.add(1000000006L);
        batchDeletionRequest.setVariableIds(variableIds);
        Result<String> batchDeletionResult = merchantVariableApi.batchDeletionMerchantVariable(batchDeletionRequest);
        logger.debug("Result of batch deletion merchant variable: {}",batchDeletionResult.toString());
        Assert.assertEquals(0, batchDeletionResult.getBusinessCode());
    }
}