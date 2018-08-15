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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pax.market.api.sdk.java.api.base.dto.Result;
import com.pax.market.api.sdk.java.api.merchant.MerchantApi;
import com.pax.market.api.sdk.java.api.merchant.MerchantApi.MerchantSearchOrderBy;
import com.pax.market.api.sdk.java.api.merchant.MerchantApi.MerchantStatus;
import com.pax.market.api.sdk.java.api.merchant.dto.MerchantCreateRequest;
import com.pax.market.api.sdk.java.api.merchant.dto.MerchantDTO;
import com.pax.market.api.sdk.java.api.merchant.dto.MerchantPageDTO;
import com.pax.market.api.sdk.java.api.merchant.dto.MerchantUpdateRequest;
import com.pax.market.api.sdk.java.api.util.EnhancedJsonUtils;


/**
 *
 * @author tanjie
 * @date 2018-07-03
 */
public class MerchantApiTest {
private static final Logger logger = LoggerFactory.getLogger(MerchantApiTest.class.getSimpleName());
	
	static MerchantApi merchantApi;
	
	static Long newMerchantId = 0L;
	
    public static void init(){
    	merchantApi = new  MerchantApi("http://localhost:8080/p-market-api", "RCA9MDH6YN3WSSGPW6TJ", "TUNLDZVZECHNKZ4FW07XFCKN2W0N8ZDEA5ENKZYN");
    }
    
    public static void testSearchMerchant() {
    	Result<MerchantPageDTO> result = merchantApi.searchMerchant(1, 10, MerchantSearchOrderBy.Contact, "KFC_Nanjing", MerchantStatus.Inactive);
    	logger.info(result.toString());
    	System.out.println("search result="+result);
    	System.out.println(EnhancedJsonUtils.toJson(result));
    }
    
    
    
    public static void testActivateMerchant() {
//    	Result<String> result = merchantApi.activateMerchant(72594L);
    	Result<String> result = merchantApi.activateMerchant(72513L);
    	System.out.println("activate result="+result);
    	System.out.println(EnhancedJsonUtils.toJson(result));
    }
    
    public static void testDisableMerchant() {
    	Result<String> result = merchantApi.disableMerchant(72594L);
    	System.out.println("disable result="+result);
    	System.out.println(EnhancedJsonUtils.toJson(result));
    }
    
    public static void testCreateMerchant() {
    	MerchantCreateRequest request = new MerchantCreateRequest();
    	request.setName("KFC_Nanjing");
    	request.setEmail("ta@pax.com");
    	request.setResellerName("New York");
    	request.setContact("sam");
    	request.setCountry("CN");
    	
    	request.setPostcode("5652");
    	request.setPhone("444888");
    	request.setAddress("Jiangsu suzhou city Xinghongjie 328#");
    	request.setDescription("Merchant KFC Nanjing");
    	LinkedHashMap<String,String> attrs = new LinkedHashMap<String,String>();
    	attrs.put("456", "tan");
    	request.setEntityAttributeValues(attrs);
    	String[] categoryNames = {"Fast Food"};
    	request.setMerchantCategoryNames(Arrays.asList(categoryNames));
    	
    	
    	Result<MerchantDTO> result = merchantApi.createMerchant(request);
    	if(result.getBusinessCode() == 0) {
    		newMerchantId = result.getData().getId();
    	}
    	System.out.println("create result="+result);
    	System.out.println(EnhancedJsonUtils.toJson(result));
    }
    public static void testGetMerchant() {    	
//    	Result<MerchantDTO> result = merchantApi.getMerchant(72590L);
    	Result<MerchantDTO> result = merchantApi.getMerchant(725901L);
    	System.out.println("get result="+result);
    	System.out.println(EnhancedJsonUtils.toJson(result));
    }
    
    public static void testUpdateMerchant() {
    	MerchantUpdateRequest request = new MerchantUpdateRequest();
//    	request.setName("KFC Suzhou");
    	request.setName("KFC_Nanjing");
    	request.setEmail("jack@kfc.com.cn");
    	
    	request.setResellerName("New York");
    	
    	
    	request.setContact("Jack");
    	request.setCountry("CN");
    	
    	request.setPhone("444866");
    	request.setPostcode("5652");
    	String[] categoryNames = {"Fast Food"};
    	request.setAddress("Jiangsu suzhou city Xinghongjie 328#");
    	request.setDescription("Merchant KFC Nanjing");
    	request.setMerchantCategoryNames(Arrays.asList(categoryNames));
    	LinkedHashMap<String,String> attrs = new LinkedHashMap<String,String>();
    	attrs.put("456", "tan2");
    	request.setEntityAttributeValues(attrs);
    	Result<MerchantDTO> result = merchantApi.updateMerchant(72513L, request);
    	System.out.println("update result="+result);
    	System.out.println(EnhancedJsonUtils.toJson(result));
    }
    
    public static void testDelete() {
    	Result<String> result = merchantApi.deleteMerchant(72593111L);
    	System.out.println("delete result="+result);
    	System.out.println(EnhancedJsonUtils.toJson(result));
    }
    
    public static void main(String[] args){
        init();
        testSearchMerchant();
//        
//        testCreateMerchant();
//        testUpdateMerchant();
//        testActivateMerchant();
//        testDisableMerchant();
//        testGetMerchant();
//        testDelete();
    }
}
