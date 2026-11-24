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


import com.pax.market.api.sdk.java.api.base.dto.Result;
import com.pax.market.api.sdk.java.api.merchant.MerchantApi;
import com.pax.market.api.sdk.java.api.merchant.MerchantApi.MerchantSearchOrderBy;
import com.pax.market.api.sdk.java.api.merchant.MerchantApi.MerchantStatus;
import com.pax.market.api.sdk.java.api.merchant.dto.MerchantCreateRequest;
import com.pax.market.api.sdk.java.api.merchant.dto.MerchantDTO;
import com.pax.market.api.sdk.java.api.merchant.dto.MerchantPageDTO;
import com.pax.market.api.sdk.java.api.merchant.dto.MerchantUpdateRequest;
import com.pax.market.api.sdk.java.api.reseller.ResellerApi;
import com.pax.market.api.sdk.java.api.reseller.dto.ResellerCreateRequest;
import com.pax.market.api.sdk.java.api.reseller.dto.ResellerDTO;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


/**
 *
 * @author tanjie
 * @date 2018-07-03
 */
public class MerchantApiTest {
	
	private static final Logger logger = LoggerFactory.getLogger(MerchantApiTest.class.getSimpleName());
	
	private MerchantApi merchantApi;
	
	private ResellerApi resellerApi;

	static Long newMerchantId = 0L;

	private static final int CONCURRENT_THREADS = 3;
	private static final String DEFAULT_PARENT_RESELLER = "PAX";
	private static final int BUSINESS_CODE_MERCHANT_MOVE_IN_PROGRESS = 1895;
	private static final int DISABLE_RETRY_INTERVAL_MS = 1000;
	private static final int DISABLE_RETRY_MAX_ATTEMPTS = 10;
	
	@Before
    public void init(){
    	merchantApi = new  MerchantApi(TestConstants.API_BASE_URL, TestConstants.API_KEY, TestConstants.API_SECRET);
    	resellerApi = new ResellerApi(TestConstants.API_BASE_URL, TestConstants.API_KEY, TestConstants.API_SECRET);
    }
    
    @Test
    public void testSearchMerchant() {
    	Result<MerchantPageDTO> result = merchantApi.searchMerchant(1, 10, MerchantSearchOrderBy.Contact, "", MerchantStatus.Active, Boolean.FALSE);
    	logger.debug("Result of search merchant: {}", result.toString()); 
    	Assert.assertTrue(result.getBusinessCode() == 0);
    }
    
    @Test
    public void testMoveMerchantClientValidation() {
    	Result<String> nullIdResult = merchantApi.moveMerchant(null, "Reseller B");
    	Assert.assertTrue(nullIdResult.getBusinessCode() == -1);

    	Result<String> blankNameResult = merchantApi.moveMerchant(1000000000L, "");
    	Assert.assertTrue(blankNameResult.getBusinessCode() == -1);

    	StringBuilder tooLongName = new StringBuilder();
    	for (int i = 0; i < 65; i++) {
    		tooLongName.append('a');
    	}
    	Result<String> tooLongResult = merchantApi.moveMerchant(1000000000L, tooLongName.toString());
    	Assert.assertTrue(tooLongResult.getBusinessCode() == -1);
    }

    @Test
    public void testCreateUpdateActiveDisableDeleteAllSuccess() {
    	//Create
    	MerchantCreateRequest request = new MerchantCreateRequest();
    	request.setName("merchant to delete");
    	request.setEmail("ta@pax.com");
    	request.setResellerName("Global");
    	request.setContact("sam");
    	request.setCountry("CN");
		request.setProvince("CN_JL");
    	request.setPhone("444888");
    	request.setAddress("Jiangsu suzhou city Xinghongjie 328#");
    	request.setDescription("Merchant KFC Nanjing");
    	LinkedHashMap<String,String> attrs = new LinkedHashMap<String,String>();
    	request.setEntityAttributeValues(attrs);
//    	String[] categoryNames = {"Fast Food"};
//    	request.setMerchantCategoryNames(Arrays.asList(categoryNames));
    	
    	
    	Result<MerchantDTO> createResult = merchantApi.createMerchant(request);
    	logger.debug("Result of create merchant: {}", createResult);
    	Assert.assertTrue(createResult.getBusinessCode() == 0);
    	Long merchantId = createResult.getData().getId();
    	
    	//update
    
    	MerchantUpdateRequest updateRequest = new MerchantUpdateRequest();
    	updateRequest.setName("merchant to delete2");
    	updateRequest.setContact("Jack");
    	updateRequest.setCountry("CN");
    	updateRequest.setProvince("CN_JL");
    	updateRequest.setPhone("444866");
    	updateRequest.setPostcode("5652");
//    	String[] categoryNames = {"Fast Food"};
    	updateRequest.setAddress("Jiangsu suzhou city Xinghongjie 328#");
    	updateRequest.setDescription("Merchant KFC Nanjing");
    	
    	updateRequest.setCreateUserFlag(true);
//    	updateRequest.setMerchantCategoryNames(Arrays.asList(categoryNames));
    	LinkedHashMap<String,String> attrs2 = new LinkedHashMap<String,String>();
    	attrs2.put("test", "test02");
    	updateRequest.setEntityAttributeValues(attrs2);
    	Result<MerchantDTO> updateResult = merchantApi.updateMerchant(merchantId, updateRequest);
    	logger.debug("Result of update merchant result={0}", updateResult.toString());
    	
    	Assert.assertTrue(updateResult.getBusinessCode() == 0);
    	
    	
    	Result<String> activateResult = merchantApi.activateMerchant(merchantId);
    	logger.debug("Result of activate merchant: {}",activateResult.toString());
    	Assert.assertTrue(activateResult.getBusinessCode() == 0);
    	
    	Result<MerchantDTO> getResult = merchantApi.getMerchant(merchantId);
    	logger.debug("Result of get merchant: {}", getResult.toString());
    	
    	Result<String> replaceEmailResult = merchantApi.replaceMerchantEmail(merchantId, "lisi@163.com", true);
    	logger.debug("Result of replace merchant email: {}", replaceEmailResult.toString());
    	
    	
    	Result<String> disableResult = merchantApi.disableMerchant(merchantId);
    	logger.debug("Result of disable merchant: {}",disableResult.toString());
    	Assert.assertTrue(disableResult.getBusinessCode() == 0);
    	
    	
    	Result<String> result = merchantApi.deleteMerchant(merchantId);
    	logger.debug("Result of delete merchant: {}", result.toString());
    	
    }
    
    private String uniqueName(String prefix) {
    	return prefix + "-" + System.nanoTime() + "-" + Thread.currentThread().getId();
    }

    private void assertSuccess(String step, Result<?> result) {
    	logger.debug("{} result: {}", step, result);
    	Assert.assertTrue(step + " failed: " + result, result.getBusinessCode() == 0);
    }

    private void assertDisableSuccessWithRetry(Long merchantId) throws InterruptedException {
    	Result<String> result = null;
    	for (int attempt = 1; attempt <= DISABLE_RETRY_MAX_ATTEMPTS; attempt++) {
    		result = merchantApi.disableMerchant(merchantId);
    		logger.debug("disableMerchant attempt {} result: {}", attempt, result);
    		if (result.getBusinessCode() != BUSINESS_CODE_MERCHANT_MOVE_IN_PROGRESS) {
    			break;
    		}
    		if (attempt < DISABLE_RETRY_MAX_ATTEMPTS) {
    			logger.info("Merchant {} is still being moved, retry disable in {}ms ({}/{})",
    					merchantId, DISABLE_RETRY_INTERVAL_MS, attempt, DISABLE_RETRY_MAX_ATTEMPTS);
    			Thread.sleep(DISABLE_RETRY_INTERVAL_MS);
    		}
    	}
    	Assert.assertTrue("disableMerchant failed after retries: " + result, result.getBusinessCode() == 0);
    }

    private ResellerDTO createActiveReseller(String prefix) {
    	String name = uniqueName(prefix);
    	ResellerCreateRequest request = new ResellerCreateRequest();
    	request.setName(name);
    	request.setEmail(name + "@example.com");
    	request.setParentResellerName(System.getProperty("test.parent.reseller", DEFAULT_PARENT_RESELLER));
    	request.setContact("sdk-test");
    	request.setCountry("CN");
    	request.setPhone("88888888");
    	request.setPostcode("215000");
    	request.setCompany("PAX");
    	request.setAddress("Suzhou");
    	request.setActivateWhenCreate(Boolean.TRUE);
    	Result<ResellerDTO> result = resellerApi.createReseller(request);
    	assertSuccess("createActiveReseller", result);
    	Assert.assertNotNull("Created reseller has no data: " + result, result.getData());
    	return result.getData();
    }

    private void safeDeleteMerchant(Long merchantId) {
    	try {
    		Result<String> result = merchantApi.deleteMerchant(merchantId);
    		if (result.getBusinessCode() != 0) {
    			logger.warn("Cleanup merchant {} failed: {}", merchantId, result);
    		}
    	} catch (Exception e) {
    		logger.warn("Cleanup merchant {} threw exception", merchantId, e);
    	}
    }

    private void safeDeleteReseller(Long resellerId) {
    	try {
    		Result<String> disableResult = resellerApi.disableReseller(resellerId);
    		if (disableResult.getBusinessCode() != 0) {
    			logger.warn("Cleanup disable reseller {} failed: {}", resellerId, disableResult);
    		}
    		Result<String> deleteResult = resellerApi.deleteReseller(resellerId);
    		if (deleteResult.getBusinessCode() != 0) {
    			logger.warn("Cleanup delete reseller {} failed: {}", resellerId, deleteResult);
    		}
    	} catch (Exception e) {
    		logger.warn("Cleanup reseller {} threw exception", resellerId, e);
    	}
    }

    private void runMerchantLifecycle(String prefix, String sourceResellerName, String targetResellerName) throws InterruptedException {
    	String name = uniqueName(prefix);
    	MerchantCreateRequest request = new MerchantCreateRequest();
    	request.setName(name);
    	request.setEmail(name + "@example.com");
    	request.setResellerName(sourceResellerName);
    	request.setContact("sam");
    	request.setCountry("CN");
    	request.setProvince("CN_JL");
    	request.setPhone("444888");
    	request.setAddress("Jiangsu suzhou city Xinghongjie 328#");
    	request.setDescription("Merchant created by MerchantApiTest");
    	request.setEntityAttributeValues(new LinkedHashMap<String, String>());

    	Result<MerchantDTO> createResult = merchantApi.createMerchant(request);
    	assertSuccess("createMerchant", createResult);
    	Assert.assertNotNull("Created merchant has no data: " + createResult, createResult.getData());

    	Long merchantId = createResult.getData().getId();
    	boolean deleted = false;
    	try {
			assertSuccess("activateMerchant", merchantApi.activateMerchant(merchantId));
			assertSuccess("moveMerchant", merchantApi.moveMerchant(merchantId, targetResellerName));
    		assertDisableSuccessWithRetry(merchantId);
    		assertSuccess("deleteMerchant", merchantApi.deleteMerchant(merchantId));
    		deleted = true;
    	} finally {
    		if (!deleted) {
    			safeDeleteMerchant(merchantId);
    		}
    	}
    }

    @Test
    public void testMerchantLifecycle() throws InterruptedException {
    	ResellerDTO source = null;
    	ResellerDTO target = null;
    	try {
    		source = createActiveReseller("lifecycle-src");
    		target = createActiveReseller("lifecycle-target");
    		runMerchantLifecycle("lifecycle-merchant", source.getName(), target.getName());
    	} finally {
    		if (target != null) {
    			safeDeleteReseller(target.getId());
    		}
    		if (source != null) {
    			safeDeleteReseller(source.getId());
    		}
    	}
    }

    @Test
    public void testMerchantLifecycleConcurrent() throws InterruptedException {
    	ResellerDTO source = null;
    	ResellerDTO target = null;
    	ExecutorService executor = Executors.newFixedThreadPool(CONCURRENT_THREADS);
    	final CountDownLatch latch = new CountDownLatch(CONCURRENT_THREADS);
    	final ConcurrentLinkedQueue<Throwable> errors = new ConcurrentLinkedQueue<Throwable>();
    	try {
    		source = createActiveReseller("concurrent-src");
    		target = createActiveReseller("concurrent-target");
    		final String sourceName = source.getName();
    		final String targetName = target.getName();
    		for (int i = 0; i < CONCURRENT_THREADS; i++) {
    			final int index = i;
    			executor.submit(new Runnable() {
    				@Override
    				public void run() {
    					try {
    						runMerchantLifecycle("concurrent-merchant-" + index, sourceName, targetName);
    					} catch (Throwable t) {
    						errors.add(t);
    					} finally {
    						latch.countDown();
    					}
    				}
    			});
    		}
    		Assert.assertTrue("Concurrent lifecycles did not finish in time", latch.await(180, TimeUnit.SECONDS));
    		Assert.assertTrue("Concurrent lifecycle failures: " + errors, errors.isEmpty());
    	} finally {
    		executor.shutdownNow();
    		if (target != null) {
    			safeDeleteReseller(target.getId());
    		}
    		if (source != null) {
    			safeDeleteReseller(source.getId());
    		}
    	}
    }

}
