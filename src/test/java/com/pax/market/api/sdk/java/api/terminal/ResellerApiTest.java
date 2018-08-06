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
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pax.market.api.sdk.java.api.base.dto.Result;
import com.pax.market.api.sdk.java.api.reseller.ResellerApi;
import com.pax.market.api.sdk.java.api.reseller.ResellerApi.ResellerSearchOrderBy;
import com.pax.market.api.sdk.java.api.reseller.ResellerApi.ResellerStatus;
import com.pax.market.api.sdk.java.api.reseller.dto.ResellerCreateRequest;
import com.pax.market.api.sdk.java.api.reseller.dto.ResellerDTO;
import com.pax.market.api.sdk.java.api.reseller.dto.ResellerPageDTO;
import com.pax.market.api.sdk.java.api.reseller.dto.ResellerUpdateRequest;
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
	
    public static void init(){
    	resellerApi = new  ResellerApi("http://localhost:8080/p-market-api", "RCA9MDH6YN3WSSGPW6TJ", "TUNLDZVZECHNKZ4FW07XFCKN2W0N8ZDEA5ENKZYN");
    }
    
    public static void testSearchReseller() {
    	Result<ResellerPageDTO> result = resellerApi.searchReseller(1, 10, ResellerSearchOrderBy.Contact, null, ResellerStatus.Suspend);
    	logger.info(result.toString());
        
        System.out.println(EnhancedJsonUtils.toJson(result));
    	System.out.println("search result="+result);
    }
    
    public static void testGetReseller() {
    	Result<ResellerDTO> result = resellerApi.getReseller(-17850111L);
    	System.out.println(EnhancedJsonUtils.toJson(result));
    	System.out.println("get result="+result);
    }
    
    public static void testCreateReseller() {
    	ResellerCreateRequest request = new ResellerCreateRequest();
    	request.setName("reseller_abc");
    	request.setContact("Sam");
    	request.setCountry("CN");
    	request.setEmail("sam@gmail.com");
    	request.setPhone("87879696");
    	request.setParentResellerName("New York");
    	LinkedHashMap<String,String> attrs = new LinkedHashMap<String,String>();
    	attrs.put("111", "tan2");
    	request.setEntityAttributeValues(attrs);
    	Result<ResellerDTO> result = resellerApi.createReseller(request);
    	if(result.getBusinessCode() == 0) {
    		newResellerId = result.getData().getId();
    	}
    	System.out.println(EnhancedJsonUtils.toJson(result));
    	System.out.println("create reseller result="+result);
    }
    
    public static void testUpdateReseller() {
    	ResellerUpdateRequest request = new ResellerUpdateRequest();
    	request.setName("FVFFF");
    	request.setContact("FFF");
    	request.setCountry("CN");
    	request.setEmail("FF@1234.COM");
    	request.setPhone("87879696");
    	request.setParentResellerName("New York");
//    	LinkedHashMap<String,String> attrs = new LinkedHashMap<String,String>();
//    	attrs.put("111", "tan2");
//    	request.setEntityAttributeValues(attrs);
    	
    	LinkedHashMap<String,String> attrs = new LinkedHashMap<String,String>();
    	attrs.put("111", "tan2");
//    	request.setEntityAttributeValues(attrs);
    	
    	
    	Result<ResellerDTO> result = resellerApi.updateReseller(17850L, request);
    	System.out.println("update result="+result);
    	System.out.println(EnhancedJsonUtils.toJson(result));
    }
    
    public static void testActivateReseller() {
    	Result<String> result = resellerApi.activateReseller(51739L);
    	System.out.println("activate result="+result);
    	System.out.println(EnhancedJsonUtils.toJson(result));
    }
    
    public static void testDisableReseller() {
    	Result<String> result = resellerApi.disableReseller(51739L);
    	System.out.println("disable result="+result);
    	System.out.println(EnhancedJsonUtils.toJson(result));
    }
    
    public static void testDeleteReseller() {
    	Result<String> result = resellerApi.deleteReseller(51738111L);
    	System.out.println("Delete result="+result);
    	System.out.println(EnhancedJsonUtils.toJson(result));
    }
    
    public static void main(String[] args){
        init();
        testSearchReseller();
//        
//        testCreateReseller();
//        testActivateReseller();
//        testUpdateReseller();
//        testGetReseller();
//        testActivateReseller();
//        testDisableReseller();
//        testDeleteReseller();
        
    }
}
