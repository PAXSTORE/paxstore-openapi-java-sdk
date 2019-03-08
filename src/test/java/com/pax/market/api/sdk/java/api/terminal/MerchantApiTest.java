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
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
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
import com.pax.market.api.sdk.java.api.test.TestConstants;
import com.pax.market.api.sdk.java.api.util.EnhancedJsonUtils;


/**
 *
 * @author tanjie
 * @date 2018-07-03
 */
public class MerchantApiTest {
	
	private static final Logger logger = LoggerFactory.getLogger(MerchantApiTest.class.getSimpleName());
	
	private MerchantApi merchantApi;
	
	static Long newMerchantId = 0L;
	
	@Before
    public void init(){
    	merchantApi = new  MerchantApi(TestConstants.API_BASE_URL, TestConstants.API_KEY, TestConstants.API_SECRET);
    }
    
    @Test
    public void testSearchMerchant() {
    	Result<MerchantPageDTO> result = merchantApi.searchMerchant(1, 10, MerchantSearchOrderBy.Contact, "", MerchantStatus.Active);
    	logger.debug("Result of search merchant: {}", result.toString()); 
    	Assert.assertTrue(result.getBusinessCode() == 0);
    }
    
    @Test
    public void testCreateUpdateActiveDisableDeleteAllSuccess() {
    	//Create
    	MerchantCreateRequest request = new MerchantCreateRequest();
    	request.setName("KFC_Nanjing");
    	request.setEmail("ta@pax.com");
    	request.setResellerName("Jesse");
    	request.setContact("sam");
    	request.setCountry("CN");
    	request.setPostcode("5652");
    	request.setPhone("444888");
    	request.setAddress("Jiangsu suzhou city Xinghongjie 328#");
    	request.setDescription("Merchant KFC Nanjing");
    	LinkedHashMap<String,String> attrs = new LinkedHashMap<String,String>();
    	attrs.put("456", "tan");
    	request.setEntityAttributeValues(attrs);
//    	String[] categoryNames = {"Fast Food"};
//    	request.setMerchantCategoryNames(Arrays.asList(categoryNames));
    	
    	
    	Result<MerchantDTO> createResult = merchantApi.createMerchant(request);
    	logger.debug("Result of create merchant: {}", createResult);
    	Assert.assertTrue(createResult.getBusinessCode() == 0);
    	Long merchantId = createResult.getData().getId();
    	
    	//update
    
    	MerchantUpdateRequest updateRequest = new MerchantUpdateRequest();
    	updateRequest.setName("KFC_Nanjing");
    	updateRequest.setContact("Jack");
    	updateRequest.setCountry("CN");
    	updateRequest.setPhone("444866");
    	updateRequest.setPostcode("5652");
//    	String[] categoryNames = {"Fast Food"};
    	updateRequest.setAddress("Jiangsu suzhou city Xinghongjie 328#");
    	updateRequest.setDescription("Merchant KFC Nanjing");
    	
    	updateRequest.setCreateUserFlag(true);
//    	updateRequest.setMerchantCategoryNames(Arrays.asList(categoryNames));
    	LinkedHashMap<String,String> attrs2 = new LinkedHashMap<String,String>();
    	attrs2.put("456", "tan2");
    	updateRequest.setEntityAttributeValues(attrs2);
    	Result<MerchantDTO> updateResult = merchantApi.updateMerchant(merchantId, updateRequest);
    	logger.debug("Result of update merchant result={0}", updateResult.toString());
    	
    	Assert.assertTrue(updateResult.getBusinessCode() == 0);
    	
    	
    	
    	
    }
    
    
    @Test
    public void testActivateMerchant() {
    	Result<String> result = merchantApi.activateMerchant(1000056487L);
    	logger.debug("Result of activate merchant: {}",result.toString());
    	Assert.assertTrue(result.getBusinessCode() == 0);
    }
    
    @Test
    public void testDisableMerchant() {
    	Result<String> result = merchantApi.disableMerchant(1000056487L);
    	logger.debug("Result of disable merchant: {}",result.toString());
    	Assert.assertTrue(result.getBusinessCode() == 0);
    }
    
    @Test
    public void testCreateMerchant() {
    	MerchantCreateRequest request = new MerchantCreateRequest();
    	request.setName("KFC_Nanjing2");
    	request.setEmail("ta@pax.com");
    	request.setResellerName("reseller");
    	request.setContact("sam");
    	request.setCountry("CN");
    	
    	request.setPostcode("5652");
    	request.setPhone("444888");
    	request.setAddress("Jiangsu suzhou city Xinghongjie 328#");
    	request.setDescription("Merchant KFC Nanjing");
    	LinkedHashMap<String,String> attrs = new LinkedHashMap<String,String>();
    	attrs.put("456", "tan");
    	request.setEntityAttributeValues(attrs);
//    	String[] categoryNames = {"Fast Food"};
//    	request.setMerchantCategoryNames(Arrays.asList(categoryNames));
    	request.setActivateWhenCreate(true);
    	
    	Result<MerchantDTO> result = merchantApi.createMerchant(request);
    	if(result.getBusinessCode() == 0) {
    		newMerchantId = result.getData().getId();
    	}
    	logger.debug("Result of create merchant: {}", result.toString());
    }
    
    @Test
    public void testGetMerchant() {    	
//    	Result<MerchantDTO> result = merchantApi.getMerchant(72590L);
    	Result<MerchantDTO> result = merchantApi.getMerchant(725901L);
    	logger.debug("Result of get merchant: {}", result.toString());
    }
    
    @Test
    public void testUpdateMerchant() {
    	MerchantUpdateRequest request = new MerchantUpdateRequest();
//    	request.setName("KFC Suzhou");
    	request.setName("KFC_Nanjing");
//    	request.setEmail("jack@kfc.com.cn");
    	
//    	request.setResellerName("Jesse");
    	
    	
    	request.setContact("Jack");
    	request.setCountry("CN");
    	
    	request.setPhone("444866");
    	request.setPostcode("5652");
//    	String[] categoryNames = {"Fast Food"};
    	request.setAddress("Jiangsu suzhou city Xinghongjie 328#");
    	request.setDescription("Merchant KFC Nanjing");
    	
    	request.setCreateUserFlag(true);
//    	request.setMerchantCategoryNames(Arrays.asList(categoryNames));
    	LinkedHashMap<String,String> attrs = new LinkedHashMap<String,String>();
    	attrs.put("456", "tan2");
    	request.setEntityAttributeValues(attrs);
    	Result<MerchantDTO> result = merchantApi.updateMerchant(1000056495L, request);
    	
    	logger.debug("Result of update merchant: {}", result.toString());
    }
    
    @Test
    public void testDelete() {
    	Result<String> result = merchantApi.deleteMerchant(1000056487L);
    	logger.debug("Result of delete merchant: {}", result.toString());
    }
    
    @Test
    public void testReplaceEmail() {
    	Result<String> result = merchantApi.replaceMerchantEmail(1000056487L, "lisi@163.com", true);
    	logger.debug("Result of replace merchant email: {}", result.toString());
    }
    
}
