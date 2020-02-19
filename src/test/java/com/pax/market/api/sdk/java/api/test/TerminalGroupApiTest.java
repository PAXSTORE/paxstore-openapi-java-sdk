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
import com.pax.market.api.sdk.java.api.terminal.TerminalApi;
import com.pax.market.api.sdk.java.api.terminal.dto.TerminalDTO;
import com.pax.market.api.sdk.java.api.terminalGroup.TerminalGroupApi;
import com.pax.market.api.sdk.java.api.terminalGroup.dto.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Set;

/**
 * @Description
 * @Author: Shawn
 * @Date: 2019/11/26 10:32
 * @Version 7.1
 */
public class TerminalGroupApiTest {
    private static final Logger logger = LoggerFactory.getLogger(TerminalGroupApiTest.class.getSimpleName());

    static TerminalGroupApi terminalGroupApi;

    @Before
    public void init() {
        terminalGroupApi = new TerminalGroupApi(TestConstants.API_BASE_URL, TestConstants.API_KEY, TestConstants.API_SECRET);
    }

    @Test
    public void testSearchTerminalGroup(){
        Result<TerminalGroupDTO> result = terminalGroupApi.searchTerminalGroup(1,5, TerminalGroupApi.TerminalGroupSearchOrderBy.CreatedDate_asc,null,null,null,null,"false","true");
        logger.debug("Result of search terminal Group list: {}", result.toString());
        Assert.assertTrue(result.getBusinessCode() == 0);
    }


    @Test
    public void testGetTerminalGroup() {
        Long groupId = 16540L;
        Result<TerminalGroupDTO> result = terminalGroupApi.getTerminalGroup(groupId);
        logger.debug("Result of  terminal Group: {}", result.toString());
        Assert.assertTrue(result.getBusinessCode() == 0);
    }

    @Test
    public void testCreateTerminalGroup() {
        CreateTerminalGroupRequest createRequest = new CreateTerminalGroupRequest();
        createRequest.setName("3RDAPITestCreateGroup-testADDMerchant-tyeqw");
        createRequest.setModelId(1L);
        createRequest.setResellerId(2L);
        createRequest.setDescription("TEST");
        createRequest.setStatus("P");
        createRequest.setContainSubResellerTerminal(false);
        createRequest.setMerchantIds("1");
        createRequest.setDynamic(true);

        Result<TerminalGroupDTO> result = terminalGroupApi.createTerminalGroup(createRequest);
        logger.debug("Result of create Terminal Group: {}", result.toString());
        Assert.assertTrue(result.getBusinessCode() == 0);
    }

    @Test
    public void testSearchTerminal(){
        String status = "A";
        String modelId = "1";
        String resellerId="2";
        String merchantId = null;
        String serialNo = null;
        String excludeGroupId = "16541";

        Result<TerminalDTO> result = terminalGroupApi.searchTerminal(1,20, TerminalApi.TerminalSearchOrderBy.Name,status,modelId,resellerId,null,null,excludeGroupId);
        logger.debug("Result of search Terminal : {}", result.toString());
        Assert.assertTrue(result.getBusinessCode() == 0);
    }

    @Test
    public void testUpdateTerminalGroup() {
        //更新终端分组-当不是未激活状态时，只能修改name、desc，传其余值默认不修改，未激活,且分组下没有终端时均可修改
        //更新 name，modelId，resellerId 若没有传则使用原来的默认不变,在后端做了校验3rds可不传，若传值则确保未修改
        Long groupId = 16549L;
        UpdateTerminalGroupRequest updateRequest = new UpdateTerminalGroupRequest();
      //  updateRequest.setName("3RDAPITestGroup-update-haveTerminal-test-221");
        updateRequest.setMerchantIds("72635,72621,72585,72584,72583");
        updateRequest.setModelId(3906L);
        updateRequest.setResellerId(2L);
        updateRequest.setDescription("test-3rd-update-UPDATE");

        Result<TerminalGroupDTO> result = terminalGroupApi.updateTerminalGroup(groupId, updateRequest);
        logger.debug("Result of update Terminal Group: {}", result.toString());
        Assert.assertTrue(result.getBusinessCode() == 0);
    }

    @Test
    public void testActiveGroup() {

        Long groupId = 16549L;
        Result<String> result = terminalGroupApi.activeGroup(groupId);
        logger.debug("Result of update Terminal Group: {}", result.toString());
        Assert.assertTrue(result.getBusinessCode() == 0);
    }

    @Test
    public void testDisableGroup() {
        Long groupId = 16533L;
        Result<String> result = terminalGroupApi.disableGroup(groupId);
        logger.debug("Result of update Terminal Group: {}", result.toString());
        Assert.assertTrue(result.getBusinessCode() == 0);
    }

    @Test
    public void testDeleteGroup() {

        Long groupId = 16530L;
        Result<String> result = terminalGroupApi.deleteGroup(groupId);
        logger.debug("Result of update Terminal Group: {}", result.toString());
        Assert.assertTrue(result.getBusinessCode() == 0);
    }

    @Test
    public void testSearchTerminalsInGroup(){
        Long groupId = 16539L;
        Result<TerminalDTO> result = terminalGroupApi.searchTerminalsInGroup(1,5, TerminalApi.TerminalSearchOrderBy.Name,groupId,null,"72638","");
        logger.debug("Result of search Terminals in Group: {}", result.toString());
        Assert.assertTrue(result.getBusinessCode() == 0);
    }

    @Test
    public void testAddGroupInTerminal() {
        Long groupId = 16533L;
        TerminalGroupRequest addInRequest = new TerminalGroupRequest();
        Set<Long> terminalIds = new HashSet<>();
        terminalIds.add(908654L);
        terminalIds.add(908655L);
        terminalIds.add(908656L);
        addInRequest.setTerminalIds(terminalIds);
        Result<String> result = terminalGroupApi.addTerminalInGroup(groupId, addInRequest);
        logger.debug("Result of add in Terminal Group: {}", result.toString());
        Assert.assertTrue(result.getBusinessCode() == 0);
    }

    @Test
    public void testRemoveGroupTerminal() {
        Long groupId = 16532L;
        TerminalGroupRequest removeRequest = new TerminalGroupRequest();
        Set<Long> terminalIds = new HashSet<>();
        terminalIds.add(255976L);
        terminalIds.add(461720L);
        terminalIds.add(461706L);
        removeRequest.setTerminalIds(terminalIds);
        Result<String> result = terminalGroupApi.removeTerminalOutGroup(groupId, removeRequest);
        logger.debug("Result of remove Terminal Group: {}", result.toString());
        Assert.assertTrue(result.getBusinessCode() == 0);
    }

}
