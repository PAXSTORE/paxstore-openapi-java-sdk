package com.pax.market.api.sdk.java.api.merchantCategory;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;

import com.pax.market.api.sdk.java.api.base.dto.Result;
import com.pax.market.api.sdk.java.api.merchant.category.MerchantCategoryApi;
import com.pax.market.api.sdk.java.api.merchant.category.dto.MerchantCategoryCreateRequest;
import com.pax.market.api.sdk.java.api.merchant.category.dto.MerchantCategoryDTO;
import com.pax.market.api.sdk.java.api.merchant.category.dto.MerchantCategoryUpdateRequest;
import com.pax.market.api.sdk.java.api.util.CryptoUtils;

public class MerchantCategoryApiTest {
	
	static MerchantCategoryApi merchantCategoryApi;
	
	
    public static void init(){
    	merchantCategoryApi = new  MerchantCategoryApi("http://localhost:8080/p-market-api", "RCA9MDH6YN3WSSGPW6TJ", "TUNLDZVZECHNKZ4FW07XFCKN2W0N8ZDEA5ENKZYN");
    }
	
	public static void testGetCategories() {
		Result<ArrayList<MerchantCategoryDTO>> obj = merchantCategoryApi.getMerchantCategories("ab");
		System.out.println(obj);
	}
	
	public static void testCreateCategory() {
		MerchantCategoryCreateRequest createRequest = new MerchantCategoryCreateRequest();
		createRequest.setName("abcdef22");
		createRequest.setRemarks("fefef");
		Result<MerchantCategoryDTO> result = merchantCategoryApi.createMerchantCategory(createRequest);
		System.out.println(result);
	}
	
	public static void testUpdateCategory() {
		MerchantCategoryUpdateRequest updateRequest = new MerchantCategoryUpdateRequest();
		updateRequest.setName("Fast Foods");
		updateRequest.setRemarks("fefef");
		Result<MerchantCategoryDTO> result = merchantCategoryApi.updateMerchantCategory(5L,updateRequest);
		System.out.println(result);
	}
	
	public static void testDeleteCategory() {
		Result<String> result = merchantCategoryApi.deleteMerchantCategory(1L);
		System.out.println(result);
	}
	
	public static void testBatchCreate() {
		List<MerchantCategoryCreateRequest> batchCreateRequest = new ArrayList<MerchantCategoryCreateRequest>();
		MerchantCategoryCreateRequest create1 = new MerchantCategoryCreateRequest();
		create1.setName("d");
		create1.setRemarks("fefef");
		batchCreateRequest.add(create1);
		
		MerchantCategoryCreateRequest create2 = new MerchantCategoryCreateRequest();
		create2.setName("cc");
//		create2.setRemarks("ccd");
		batchCreateRequest.add(create2);
		
		MerchantCategoryCreateRequest create3 = new MerchantCategoryCreateRequest();
		create3.setName("cc");
		create3.setRemarks("fefef");
		batchCreateRequest.add(create3);
		
		MerchantCategoryCreateRequest create4 = new MerchantCategoryCreateRequest();
		create4.setName("abcdef226");
		create4.setRemarks("fefef");
		batchCreateRequest.add(create4);
		Result<String> result = merchantCategoryApi.batchCreateMerchantCategory(batchCreateRequest,false);
		System.out.println(result);
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
