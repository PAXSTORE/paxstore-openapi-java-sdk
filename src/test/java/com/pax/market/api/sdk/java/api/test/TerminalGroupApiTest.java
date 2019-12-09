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
import com.pax.market.api.sdk.java.api.terminalGroup.TerminalGroupApi;
import com.pax.market.api.sdk.java.api.terminalGroup.dto.CreateTerminalGroupRequest;
import com.pax.market.api.sdk.java.api.terminalGroup.dto.TerminalGroupDTO;
import com.pax.market.api.sdk.java.api.terminalGroup.dto.TerminalGroupRequest;
import com.pax.market.api.sdk.java.api.terminalGroup.dto.UpdateTerminalGroupRequest;
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
    public void testGetTerminalGroup() {
        Long groupId = 16526L;
        Result<TerminalGroupDTO> result = terminalGroupApi.getTerminalGroup(groupId);
        logger.debug("Result of  terminal Group: {}", result.toString());
        Assert.assertTrue(result.getBusinessCode() == 0);
    }

    @Test
    public void testCreateTerminalGroup() {
        CreateTerminalGroupRequest createRequest = new CreateTerminalGroupRequest();
        createRequest.setName("3RDAPITestCreateGroup");
        createRequest.setModelId(1L);
        createRequest.setResellerId(51785L);
        createRequest.setDescription("TEST");
        createRequest.setStatus("A");
        createRequest.setContainSubResellerTerminal(true);

        Result<TerminalGroupDTO> result = terminalGroupApi.createTerminalGroup(createRequest);
        logger.debug("Result of create Terminal Group: {}", result.toString());
        Assert.assertTrue(result.getBusinessCode() == 0);
    }

    @Test
    public void testUpdateTerminalGroup() {
        //更新终端分组-当不是未激活状态时，只能修改name、desc，传其余值默认不修改，未激活,且分组下没有终端时均可修改
        Long groupId = 16532L;
        UpdateTerminalGroupRequest updateRequest = new UpdateTerminalGroupRequest();
        updateRequest.setName("3RDAPITestGroup-update-haveTerminal");
        updateRequest.setMerchantIds("");
        updateRequest.setModelId(2L);
        updateRequest.setResellerId(2L);
        updateRequest.setDescription("test-3rd-update-test1111");

        Result<TerminalGroupDTO> result = terminalGroupApi.updateTerminalGroup(groupId, updateRequest);
        logger.debug("Result of update Terminal Group: {}", result.toString());
        Assert.assertTrue(result.getBusinessCode() == 0);
    }

    @Test
    public void testActiveGroup() {

        Long groupId = 16530L;
        Result<String> result = terminalGroupApi.activeGroup(groupId);
        logger.debug("Result of update Terminal Group: {}", result.toString());
        Assert.assertTrue(result.getBusinessCode() == 0);
    }

    @Test
    public void testDisableGroup() {
        Long groupId = 16530L;
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
    public void testAddGroupInTerminal() {
        Long groupId = 16532L;
        TerminalGroupRequest addInRequest = new TerminalGroupRequest();
        Set<Long> terminalIds = new HashSet<>();
        terminalIds.add(255976L);
        terminalIds.add(461720L);
        terminalIds.add(461706L);
        addInRequest.setTerminalIds(terminalIds);
        Result<String> result = terminalGroupApi.addGroupInTerminal(groupId, addInRequest);
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
        Result<String> result = terminalGroupApi.removeGroupOutTerminal(groupId, removeRequest);
        logger.debug("Result of remove Terminal Group: {}", result.toString());
        Assert.assertTrue(result.getBusinessCode() == 0);
    }

}
