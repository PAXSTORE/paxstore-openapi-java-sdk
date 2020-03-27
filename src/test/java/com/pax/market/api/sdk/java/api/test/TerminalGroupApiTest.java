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
import com.pax.market.api.sdk.java.api.terminalGroup.TerminalGroupApi;
import com.pax.market.api.sdk.java.api.terminalGroup.dto.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
        Result<TerminalGroupDTO> result = terminalGroupApi.searchTerminalGroup(1,5, TerminalGroupApi.TerminalGroupSearchOrderBy.CreatedDate_asc, TerminalGroupApi.TerminalGroupStatus.Active,null,"Shawn-test-8992","A920,wqe&#44qwe",true);
        logger.debug("Result of search terminal Group list: {}", result.toString());
        Assert.assertTrue(result.getBusinessCode() == 0);
    }


    @Test
    public void testGetTerminalGroup() {
        Long groupId = 16531L;
        Result<TerminalGroupDTO> result = terminalGroupApi.getTerminalGroup(groupId);
        logger.debug("Result of  terminal Group: {}", result.toString());
        Assert.assertTrue(result.getBusinessCode() == 0);
    }

    @Test
    public void testCreateTerminalGroup() {
        CreateTerminalGroupRequest createRequest = new CreateTerminalGroupRequest();
        createRequest.setName("testApi4-create");
        createRequest.setModelName("A920");
        createRequest.setResellerName("Shawn-test-8992");
        createRequest.setDescription("TEST");
        createRequest.setStatus("P");
        createRequest.setContainSubResellerTerminal(false);
        List<String> merchantNames = new ArrayList<>();
        merchantNames.add("shawn-01");
        createRequest.setMerchantNameList(merchantNames);
        createRequest.setDynamic(true);

        Result<TerminalGroupDTO> result = terminalGroupApi.createTerminalGroup(createRequest);
        logger.debug("Result of create Terminal Group: {}", result.toString());
        Assert.assertTrue(result.getBusinessCode() == 0);
    }

    @Test
    public void testSearchTerminal(){

        String modelName = "A920";
        String resellerName="Shawn-test-8992";
        String serialNo = "123124";
        String excludeGroupId = "16541";

        Result<SimpleTerminalDTO> result = terminalGroupApi.searchTerminal(1,5, TerminalApi.TerminalSearchOrderBy.Name, TerminalApi.TerminalStatus.Active, modelName,resellerName,null,excludeGroupId);
        logger.debug("Result of search Terminal : {}", result.toString());
        Assert.assertTrue(result.getBusinessCode() == 0);
    }

    @Test
    public void testUpdateTerminalGroup() {
        //更新终端分组-当不是未激活状态时，只能修改name、desc，传其余值默认不修改，未激活,且分组下没有终端时均可修改
        //更新 name，modelId，resellerId 若没有传则使用原来的默认不变,在后端做了校验3rds可不传，若传值则确保未修改
        Long groupId = 16576L;
        UpdateTerminalGroupRequest updateRequest = new UpdateTerminalGroupRequest();
        updateRequest.setName("3rdsUpdateTerminalGroupName");
        List<String> merchantNamesList = new ArrayList<>();
        merchantNamesList.add("testDelete6");
        merchantNamesList.add("12348");
        updateRequest.setMerchantNameList(merchantNamesList);
        updateRequest.setModelName("E800");
        updateRequest.setResellerName("Shawn-test-8992");
        updateRequest.setDescription("test-3rd-api-update-UPDATE");

        Result<TerminalGroupDTO> result = terminalGroupApi.updateTerminalGroup(groupId, null);
        logger.debug("Result of update Terminal Group: {}", result.toString());
        Assert.assertTrue(result.getBusinessCode() == 0);
    }

    @Test
    public void testActiveGroup() {

        Long groupId = 16549L;
        Result<String> result = terminalGroupApi.activeGroup(null);
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
        Long groupId = 16541L;
        Result<SimpleTerminalDTO> result = terminalGroupApi.searchTerminalsInGroup(1,5, TerminalApi.TerminalSearchOrderBy.SerialNo,groupId,null,"12343543,123445489");
        logger.debug("Result of search Terminals in Group: {}", result.toString());
        Assert.assertTrue(result.getBusinessCode() == 0);
    }

    @Test
    public void testAddGroupInTerminal() {
        Long groupId = 1L;
        Set<Long> terminalIds = new HashSet<>();
        terminalIds.add(908654L);
        terminalIds.add(908655L);
        terminalIds.add(908656L);
        Result<String> result = terminalGroupApi.addTerminalToGroup(groupId, terminalIds);
        logger.debug("Result of add in Terminal Group: {}", result.toString());
        Assert.assertTrue(result.getBusinessCode() == 0);
    }

    @Test
    public void testRemoveGroupTerminal() {
        Long groupId = 16532L;
        Set<Long> terminalIds = new HashSet<>();
        terminalIds.add(255976L);
        terminalIds.add(461720L);
        terminalIds.add(461706L);
        Result<String> result = terminalGroupApi.removeTerminalOutGroup(groupId, terminalIds);
        logger.debug("Result of remove Terminal Group: {}", result.toString());
        Assert.assertTrue(result.getBusinessCode() == 0);
    }

}
