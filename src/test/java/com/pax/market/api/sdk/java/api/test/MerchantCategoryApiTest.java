package com.pax.market.api.sdk.java.api.test;


import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.pax.market.api.sdk.java.api.base.dto.Result;
import com.pax.market.api.sdk.java.api.merchant.category.MerchantCategoryApi;
import com.pax.market.api.sdk.java.api.merchant.category.dto.MerchantCategoryCreateRequest;
import com.pax.market.api.sdk.java.api.merchant.category.dto.MerchantCategoryDTO;
import com.pax.market.api.sdk.java.api.merchant.category.dto.MerchantCategoryUpdateRequest;

public class MerchantCategoryApiTest {
	
	private static final Logger logger = LoggerFactory.getLogger(MerchantCategoryApiTest.class);
	
	static MerchantCategoryApi merchantCategoryApi;
	
	@Before
    public void init(){
    	merchantCategoryApi = new  MerchantCategoryApi(TestConstants.API_BASE_URL, TestConstants.API_KEY, TestConstants.API_SECRET, Locale.ENGLISH);
    }
	
	@Test
	public void testGetCategories() {
		Result<ArrayList<MerchantCategoryDTO>> obj = merchantCategoryApi.getMerchantCategories("");
		logger.debug("Result of get merchant categories: {}", obj.toString());
	}
	
	@Test
	public void testCreateCategory() {
		MerchantCategoryCreateRequest createRequest = new MerchantCategoryCreateRequest();
		createRequest.setName("");
		createRequest.setRemarks("Retail");
		Result<MerchantCategoryDTO> result = merchantCategoryApi.createMerchantCategory(createRequest);
		logger.debug("Result of create merchant category: {}", result.toString());
	}
	
	@Test
	public void testUpdateCategory() {
		MerchantCategoryUpdateRequest updateRequest = new MerchantCategoryUpdateRequest();
		updateRequest.setName("Retail3");
		updateRequest.setRemarks("This is a retail category");
		Result<MerchantCategoryDTO> result = merchantCategoryApi.updateMerchantCategory(2L,updateRequest);
		logger.debug("Result of update merchant category: {}", result.toString());
	}
	
	@Test
	public void testDeleteCategory() {
		Result<String> result = merchantCategoryApi.deleteMerchantCategory(12L);
		logger.debug("Result of delete merchant category: {}", result.toString());
	}
	
	@Test
	public void testBatchCreate() {
		List<MerchantCategoryCreateRequest> batchCreateRequest = new ArrayList<MerchantCategoryCreateRequest>();
		MerchantCategoryCreateRequest create1 = new MerchantCategoryCreateRequest();
		create1.setName("Retail11");
		create1.setRemarks("Retail remarks");
		batchCreateRequest.add(create1);
		
		MerchantCategoryCreateRequest create2 = new MerchantCategoryCreateRequest();
		create2.setName("Fast food22");
		create2.setRemarks("Retail remarks");
		batchCreateRequest.add(create2);
		
		MerchantCategoryCreateRequest create3 = new MerchantCategoryCreateRequest();
		create3.setName("Realty33");
		create3.setRemarks("Realty");
		batchCreateRequest.add(create3);
		Result<ArrayList<MerchantCategoryDTO>> result = merchantCategoryApi.batchCreateMerchantCategory(batchCreateRequest,false);
		logger.debug("Result of batch create merchant categories: {}", result.toString());
	}
	
}
