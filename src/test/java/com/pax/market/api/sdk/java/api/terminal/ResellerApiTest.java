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
package com.pax.market.api.sdk.java.api.terminal;

import java.util.LinkedHashMap;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.pax.market.api.sdk.java.api.base.dto.Result;
import com.pax.market.api.sdk.java.api.reseller.ResellerApi;
import com.pax.market.api.sdk.java.api.reseller.ResellerApi.ResellerSearchOrderBy;
import com.pax.market.api.sdk.java.api.reseller.dto.ResellerCreateRequest;
import com.pax.market.api.sdk.java.api.reseller.dto.ResellerDTO;
import com.pax.market.api.sdk.java.api.reseller.dto.ResellerPageDTO;
import com.pax.market.api.sdk.java.api.reseller.dto.ResellerUpdateRequest;
import com.pax.market.api.sdk.java.api.test.TestConstants;
import com.pax.market.api.sdk.java.api.util.EnhancedJsonUtils;

/**
 *
 * @author tanjie
 * @date 2018-07-03
 */
public class ResellerApiTest {
	private static final Logger logger = LoggerFactory.getLogger(ResellerApiTest.class.getSimpleName());
	
	static ResellerApi resellerApi;
	
	static Long newResellerId = 0L;
	
    public void init(){
    	resellerApi = new  ResellerApi(TestConstants.API_BASE_URL, TestConstants.API_KEY, TestConstants.API_SECRET);
    }
    
    @Test
    public void testSearchReseller() {
    	Result<ResellerPageDTO> result = resellerApi.searchReseller(1, 10, ResellerSearchOrderBy.Contact, null, null);
    	logger.info(result.toString());
        
        System.out.println(EnhancedJsonUtils.toJson(result));
    	System.out.println("search result="+result);
    }
    
    @Test
    public void testGetReseller() {
    	Result<ResellerDTO> result = resellerApi.getReseller(1000005210L);
    	System.out.println(EnhancedJsonUtils.toJson(result));
    	System.out.println("get result="+result);
    }
    
    @Test
    public void testCreateReseller() {
    	ResellerCreateRequest request = new ResellerCreateRequest();
    	request.setName("reseller_003");
    	request.setContact("Sam");
    	request.setCountry("CN");
    	request.setEmail("sam2@gmail.com");
    	request.setPhone("87879696");
    	request.setPostcode("850212");
    	request.setCompany("Cam");
    	request.setAddress("JiangSu Suzhou city xinghujie 203#");
    	request.setParentResellerName("reseller");
    	request.setActivateWhenCreate(Boolean.FALSE);
//    	LinkedHashMap<String,String> attrs = new LinkedHashMap<String,String>();
//    	attrs.put("111", "tan2");
//    	request.setEntityAttributeValues(attrs);
    	Result<ResellerDTO> result = resellerApi.createReseller(request);
    	if(result.getBusinessCode() == 0) {
    		newResellerId = result.getData().getId();
    	}
    	System.out.println(EnhancedJsonUtils.toJson(result));
    	System.out.println("create reseller result="+result);
    }
    
    @Test
    public void testUpdateReseller() {
    	ResellerUpdateRequest request = new ResellerUpdateRequest();
    	request.setName("FVFFF22fef");
    	request.setContact("FFF");
    	request.setCountry("CN");
//    	request.setEmail("FF@1234.COM");
    	request.setPhone("87879696");
    	request.setPostcode("850212");
    	request.setCompany("Cam");
    	request.setAddress("JiangSu Suzhou city xinghujie 203#");
//    	request.setParentResellerName("Global");
//    	request.setParentResellerName("New York");
    	LinkedHashMap<String,String> attrs = new LinkedHashMap<String,String>();
    	attrs.put("111", "tan2");
    	request.setEntityAttributeValues(attrs);
    	
//    	LinkedHashMap<String,String> attrs = new LinkedHashMap<String,String>();
//    	attrs.put("111", "tan2");
//    	request.setEntityAttributeValues(attrs);
    	
    	
    	Result<ResellerDTO> result = resellerApi.updateReseller(1000005221L, request);
    	System.out.println("update result="+result);
    	System.out.println(EnhancedJsonUtils.toJson(result));
    }
    
    @Test
    public void testActivateReseller() {
    	Result<String> result = resellerApi.activateReseller(1000005211L);
    	System.out.println("activate result="+result);
    	System.out.println(EnhancedJsonUtils.toJson(result));
    }
    
    @Test
    public void testDisableReseller() {
    	Result<String> result = resellerApi.disableReseller(1000005211L);
    	System.out.println("disable result="+result);
    	System.out.println(EnhancedJsonUtils.toJson(result));
    }
    
    @Test
    public void testDeleteReseller() {
    	Result<String> result = resellerApi.deleteReseller(1000005212L);
    	System.out.println("Delete result="+result);
    	System.out.println(EnhancedJsonUtils.toJson(result));
    }
    
    @Test
    public void testReplaceEmail() {
    	Result<String> result = resellerApi.replaceResellerEmail(1000005212L, "zhangsan@pax.com");
    	System.out.println("Replace email result="+result);
    	System.out.println(EnhancedJsonUtils.toJson(result));
    }
    
}
