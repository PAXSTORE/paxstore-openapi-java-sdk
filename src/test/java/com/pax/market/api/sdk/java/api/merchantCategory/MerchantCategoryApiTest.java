package com.pax.market.api.sdk.java.api.merchantCategory;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.pax.market.api.sdk.java.api.base.dto.Result;
import com.pax.market.api.sdk.java.api.merchant.category.MerchantCategoryApi;
import com.pax.market.api.sdk.java.api.merchant.category.dto.MerchantCategoryCreateRequest;
import com.pax.market.api.sdk.java.api.merchant.category.dto.MerchantCategoryDTO;
import com.pax.market.api.sdk.java.api.merchant.category.dto.MerchantCategoryUpdateRequest;
import com.pax.market.api.sdk.java.api.util.CryptoUtils;
import com.pax.market.api.sdk.java.api.util.EnhancedJsonUtils;

public class MerchantCategoryApiTest {
	
	static MerchantCategoryApi merchantCategoryApi;
	
	
    public static void init(){
    	merchantCategoryApi = new  MerchantCategoryApi("http://localhost:8080/p-market-api", "RCA9MDH6YN3WSSGPW6TJ", "TUNLDZVZECHNKZ4FW07XFCKN2W0N8ZDEA5ENKZYN", Locale.ENGLISH);
    }
	
	public static void testGetCategories() {
		Result<ArrayList<MerchantCategoryDTO>> obj = merchantCategoryApi.getMerchantCategories("");
		System.out.println(obj);
	}
	
	public static void testCreateCategory() {
		MerchantCategoryCreateRequest createRequest = new MerchantCategoryCreateRequest();
		createRequest.setName("");
		createRequest.setRemarks("Retail");
		Result<MerchantCategoryDTO> result = merchantCategoryApi.createMerchantCategory(createRequest);
		System.out.println(EnhancedJsonUtils.toJson(result));
    	System.out.println("search result="+result);

	}
	
	public static void testUpdateCategory() {
		MerchantCategoryUpdateRequest updateRequest = new MerchantCategoryUpdateRequest();
		updateRequest.setName("Retail3");
		updateRequest.setRemarks("This is a retail category");
		Result<MerchantCategoryDTO> result = merchantCategoryApi.updateMerchantCategory(2L,updateRequest);
		System.out.println(EnhancedJsonUtils.toJson(result));
	}
	
	public static void testDeleteCategory() {
		Result<String> result = merchantCategoryApi.deleteMerchantCategory(12L);
		System.out.println(EnhancedJsonUtils.toJson(result));
	}
	
	public static void testBatchCreate() {
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
		System.out.println(EnhancedJsonUtils.toJson(result));
	}
	
	
	
	
	public static void main(String[] args) throws IOException, GeneralSecurityException {
		init();
//		testCreateCategory();
//		testGetCategories();
//		testUpdateCategory();
//		testDeleteCategory();
		testBatchCreate();
	
	}
}
