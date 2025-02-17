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
import com.pax.market.api.sdk.java.api.factoryModel.FactoryModelApi;
import com.pax.market.api.sdk.java.api.factoryModel.dto.FactoryDTO;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class FactoryModelApiTest {
    private static final Logger logger = LoggerFactory.getLogger(FactoryModelApiTest.class.getSimpleName());

    static FactoryModelApi factoryModelApi;

    @Before
    public void init(){
        factoryModelApi = new FactoryModelApi(TestConstants.API_BASE_URL, TestConstants.API_KEY, TestConstants.API_SECRET);
    }

    @Test
    public void testSearchFactoryModels(){
        Result<FactoryDTO> result = factoryModelApi.searchFactoryModels(1,5, FactoryModelApi.SearchOrderBy.name_asc, null, null, null);
        logger.debug("Result of  factory models: {}",result.toString());
        Assert.assertTrue(result.getBusinessCode() == 0);
    }



}
