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

import com.pax.market.api.sdk.java.api.base.dto.Result;
import com.pax.market.api.sdk.java.api.terminalGroupRki.TerminalGroupRkiApi;
import com.pax.market.api.sdk.java.api.terminalGroupRki.dto.CreateTerminalGroupRkiRequest;
import com.pax.market.api.sdk.java.api.terminalGroupRki.dto.TerminalGroupRkiDTO;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * push terminal group rki test
 * @author shifan
 * @date 2021/9/24
 */
public class TerminalGroupRkiTest {

    private static final Logger logger = LoggerFactory.getLogger(TerminalGroupRkiTest.class.getSimpleName());

    static TerminalGroupRkiApi terminalGroupRkiApi;

    @Before
    public void init(){
        terminalGroupRkiApi = new TerminalGroupRkiApi(TestConstants.API_BASE_URL, TestConstants.API_KEY, TestConstants.API_SECRET);
    }


    @Test
    public void testSearchGroupTerminalRkiTaskList() {
        Result<TerminalGroupRkiDTO> result = terminalGroupRkiApi.searchGroupPushRkiTask(1,12,TerminalGroupRkiApi.SearchOrderBy.CreatedDate_desc,16601L,null , true, null);
        logger.debug("Result of search group terminalRki:{}", result.toString());
        Assert.assertTrue(result.getBusinessCode() == 0);
    }


    @Test
    public void testGetGroupTerminalRkiTask() {
        Result<TerminalGroupRkiDTO> result = terminalGroupRkiApi.getGroupPushRkiTask(6L);
        logger.debug("Result of get group terminalRki:{}", result.toString());
        Assert.assertTrue(result.getBusinessCode() == 0);
    }

    @Test
    public void testCreateGroupTerminalRkiTask() {
        CreateTerminalGroupRkiRequest createRequest = new CreateTerminalGroupRkiRequest();
        createRequest.setGroupId(16601L);
        createRequest.setRkiKey("TMK_TEST_8");
        Result<TerminalGroupRkiDTO> result = terminalGroupRkiApi.pushRkiKey2Group(createRequest);
        logger.debug("Result of create push group terminalRki:{}", result.toString());
        Assert.assertTrue(result.getBusinessCode() == 0);
    }

    @Test
    public void testDisableGroupTerminalRkiTask() {
        Result<TerminalGroupRkiDTO> result = terminalGroupRkiApi.disableGroupRkiPushTask(6L);
        logger.debug("Result of disable push group terminalRki:{}", result.toString());
        Assert.assertTrue(result.getBusinessCode() == 0);
    }


}
