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


import org.junit.Assert;
import org.junit.Before;
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

/**
 *
 * @author tanjie
 * @date 2018-07-03
 */
public class ResellerApiTest {
	
	private static final Logger logger = LoggerFactory.getLogger(ResellerApiTest.class.getSimpleName());
	
	ResellerApi resellerApi;
	
	static Long newResellerId = 0L;
	
	@Before
    public void init(){
    	resellerApi = new  ResellerApi(TestConstants.API_BASE_URL, TestConstants.API_KEY, TestConstants.API_SECRET);
    }
    
    @Test
    public void testSearchReseller() {
    	Result<ResellerPageDTO> result = resellerApi.searchReseller(1, 10, ResellerSearchOrderBy.Contact, null, null);
    	logger.debug("Result of search reseller: {}",result.toString());
	    Assert.assertTrue(result.getBusinessCode() == 0);
    }
    
    
    @Test
    public void testCreateReseller() {
    	ResellerCreateRequest request = new ResellerCreateRequest();
    	request.setName("reseller to delete");
    	request.setContact("Sam");
    	request.setCountry("CN");
    	request.setEmail("sam2@gmail.com");
    	request.setPhone("87879696");
    	request.setPostcode("850212");
    	request.setCompany("Cam");
    	request.setAddress("JiangSu Suzhou city xinghujie 203#");
    	request.setParentResellerName("reseller test");
    	request.setActivateWhenCreate(Boolean.FALSE);
    	Result<ResellerDTO> result = resellerApi.createReseller(request);
    	if(result.getBusinessCode() == 0) {
    		newResellerId = result.getData().getId();
    	}
    	logger.debug("Result of create reseller: {}",result.toString());
    	Assert.assertTrue(result.getBusinessCode() == 0);
    	
    	Long resellerId = result.getData().getId();
    	
    	Result<ResellerDTO> getResult = resellerApi.getReseller(resellerId);
    	logger.debug("Result of get reseller: {}",getResult.toString());
    	Assert.assertTrue(getResult.getBusinessCode() == 0);
    	
    	
    	//test update
    	ResellerUpdateRequest updateRequest = new ResellerUpdateRequest();
    	updateRequest.setName("reseller to delete2");
    	updateRequest.setContact("FFF");
    	updateRequest.setCountry("CN");
    	updateRequest.setPhone("87879696");
    	updateRequest.setPostcode("850212");
    	updateRequest.setCompany("Cam");
    	updateRequest.setAddress("JiangSu Suzhou city xinghujie 203#");
    	Result<ResellerDTO> updateResult = resellerApi.updateReseller(resellerId, updateRequest);
    	logger.debug("Result of update reseller: {}",updateResult.toString());
    	Assert.assertTrue(updateResult.getBusinessCode() == 0);
    	
    	
    	//Test activate
    	Result<String> activateResult = resellerApi.activateReseller(resellerId);
    	logger.debug("Result of activate reseller: {}",activateResult.toString());
    	Assert.assertTrue(activateResult.getBusinessCode() == 0);
    	
    	
    	//Test replace email
    	Result<String> replaceEmailResult = resellerApi.replaceResellerEmail(resellerId, "zhangsan@pax.com");
    	logger.debug("Result of replace reseller email: {}",replaceEmailResult.toString());
    	Assert.assertTrue(replaceEmailResult.getBusinessCode() == 0);
    	
    	//Test disable
    	Result<String> disableResult = resellerApi.disableReseller(resellerId);
    	logger.debug("Result of disable reseller: {}",disableResult.toString());
    	Assert.assertTrue(disableResult.getBusinessCode() == 0);
    	
    	//Test delete
    	Result<String> deleteResult = resellerApi.deleteReseller(resellerId);
    	logger.debug("Result of delete reseller: {}",deleteResult.toString());
    	Assert.assertTrue(deleteResult.getBusinessCode() == 0);
    	
    }
    
    
}
