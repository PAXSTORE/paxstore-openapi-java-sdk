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
import com.pax.market.api.sdk.java.api.entityAttribute.EntityAttributeApi;
import com.pax.market.api.sdk.java.api.entityAttribute.dto.EntityAttributeCreateRequest;
import com.pax.market.api.sdk.java.api.entityAttribute.dto.EntityAttributeDTO;
import com.pax.market.api.sdk.java.api.entityAttribute.dto.EntityAttributeUpdateRequest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Description
 * @Author: Shawn
 * @Date: 2019/11/18 10:08
 * @Version 7.1
 */
public class EntityAttributeApiTest {
    private static final Logger logger = LoggerFactory.getLogger(EntityAttributeApiTest.class.getSimpleName());
    static EntityAttributeApi entityAttributeApi;

    @Before
    public void init(){
        entityAttributeApi = new EntityAttributeApi(TestConstants.API_BASE_URL, TestConstants.API_KEY, TestConstants.API_SECRET);
    }

    @Test
    public void testGetEntityAttribute(){
        Long attributeId =1000000218L;
        Result<EntityAttributeDTO> result = entityAttributeApi.getEntityAttribute(attributeId);
        logger.debug("Result of  Entity Attributes: {}",result.toString());
        Assert.assertTrue(result.getBusinessCode() == 0);
    }

    @Test
    public void testSearchEntityAttribute(){
        Result<EntityAttributeDTO> result = entityAttributeApi.searchEntityAttributes(1,15,EntityAttributeApi.SearchOrderBy.EntityType_asc,null,null);
        logger.debug("Result of Search Entity Attributes: {}",result.toString());
        Assert.assertTrue(result.getBusinessCode() == 0);
    }

    @Test
    public void testCreateEntityAttribute(){
        EntityAttributeCreateRequest createRequest = new EntityAttributeCreateRequest();
        createRequest.setEntityType(EntityAttributeApi.EntityAttributeType.Reseller);
        createRequest.setDefaultLabel("testCreateApi-label");
        createRequest.setInputType(EntityAttributeApi.EntityInputType.Text);
        createRequest.setKey("testCreateApi-key");
        createRequest.setMaxLength(5);
        createRequest.setMinLength(1);
        createRequest.setRequired(false);
    /*    createRequest.setSelector("{\n" +
                "\t\"1\": \"1\",\n" +
                "\t\"te1212121st\": \"test\"\n" +
                "}");*/
        Result<EntityAttributeDTO> result = entityAttributeApi.createEntityAttribute(createRequest);
        logger.debug("Result of create entity attribute: {}",result.toString());
        Assert.assertTrue(result.getBusinessCode() == 0);
    }

    @Test
    public void testUpdateEntityAttribute(){
        Long attributeId = 1000000403L;
        EntityAttributeUpdateRequest updateRequest = new EntityAttributeUpdateRequest();
        updateRequest.setDefaultLabel("testCreateApi-label-update");
        updateRequest.setInputType(EntityAttributeApi.EntityInputType.Text);
        updateRequest.setMaxLength(6);
        updateRequest.setMinLength(2);
        updateRequest.setRequired(false);
        /*updateRequest.setSelector("{1:1}");
        updateRequest.setRegex("");*/
        Result<EntityAttributeDTO> result = entityAttributeApi.updateEntityAttribute(attributeId,updateRequest);
        logger.debug("Result of update entity attribute: {}",result.toString());
        Assert.assertTrue(result.getBusinessCode() == 0);
    }

    @Test
    public void testUpdateEntityAttributeLabel(){
//        Long attributeId = 1000000403L;
//        EntityAttributeLabelInfo labelInfoJp = new EntityAttributeLabelInfo();
//        EntityAttributeLabelInfo labelInfoEn = new EntityAttributeLabelInfo();
//        EntityAttributeLabelInfo labelInfoZh = new EntityAttributeLabelInfo();
//        labelInfoJp.setEntityAttributeId(attributeId);
//        labelInfoJp.setLabel("テストラベル-jp");
//        labelInfoJp.setLocale("jp");
//        labelInfoEn.setEntityAttributeId(attributeId);
//        labelInfoEn.setLabel("testCreateApi-label-en");
//        labelInfoEn.setLocale("en");
//        labelInfoZh.setEntityAttributeId(attributeId);
//        labelInfoZh.setLabel("测试updateAPI-zh");
//        labelInfoZh.setLocale("zh_CN");
//        List<EntityAttributeLabelInfo> entityAttributeLabelList = new ArrayList<>();
//        entityAttributeLabelList.add(labelInfoJp);
//        entityAttributeLabelList.add(labelInfoEn);
//        entityAttributeLabelList.add(labelInfoZh);
//        EntityAttributeLabelUpdateRequest updateRequest = new EntityAttributeLabelUpdateRequest();
//        updateRequest.setEntityAttributeLabelList(entityAttributeLabelList);
        Result<String> result = entityAttributeApi.updateEntityAttributeLabel(null,null);
//        logger.debug("Result of update entity attribute label: {}",result.toString());
//        Assert.assertTrue(result.getBusinessCode() == 0);
    }

    @Test
    public void testDeleteEntityAttribute(){
        Long attributeId = 1000000402L;
        Result<String> result = entityAttributeApi.deleteEntityAttribute(attributeId);
        logger.debug("Result of delete entity attribute: {}",result.toString());
        Assert.assertTrue(result.getBusinessCode() == 0);
    }

}
