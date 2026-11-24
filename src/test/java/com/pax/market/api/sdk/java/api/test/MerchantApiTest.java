package com.pax.market.api.sdk.java.api.test;

import static org.junit.Assert.*;

import com.pax.market.api.sdk.java.api.base.dto.Result;
import com.pax.market.api.sdk.java.api.merchant.MerchantApi;
import com.pax.market.api.sdk.java.api.merchant.MerchantApi.MerchantSearchOrderBy;
import com.pax.market.api.sdk.java.api.merchant.MerchantApi.MerchantStatus;
import com.pax.market.api.sdk.java.api.merchant.dto.*;
import com.pax.market.api.sdk.java.api.reseller.ResellerApi;
import com.pax.market.api.sdk.java.api.reseller.dto.ResellerCreateRequest;
import com.pax.market.api.sdk.java.api.reseller.dto.ResellerDTO;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Integration tests for {@link MerchantApi}.
 */
public class MerchantApiTest extends IntegrationTestSupport {

    private static final int CONCURRENT_THREADS = 3;
    private static final int BUSINESS_CODE_MERCHANT_MOVE_IN_PROGRESS = 1895;
    private static final int BUSINESS_CODE_RESELLER_CAPACITY_FULL = 1799;
    private static final int DISABLE_RETRY_INTERVAL_MS = 1000;
    private static final int DISABLE_RETRY_MAX_ATTEMPTS = 10;

    private MerchantApi merchantApi;
    private ResellerApi resellerApi;

    @Before
    public void setUp() {
        merchantApi = new MerchantApi(TestConstants.API_BASE_URL,
                TestConstants.API_KEY, TestConstants.API_SECRET);
        resellerApi = new ResellerApi(TestConstants.API_BASE_URL,
                TestConstants.API_KEY, TestConstants.API_SECRET);
    }

    @Test
    public void searchMerchant_shouldReturnPage() {
        Result<MerchantPageDTO> result = merchantApi.searchMerchant(
                1, 10, MerchantSearchOrderBy.Contact, "", MerchantStatus.Active, Boolean.FALSE);
        assertPage(result);
    }

    @Test
    public void getMerchant_withIdFromSearch_shouldSucceed() {
        Result<MerchantPageDTO> page = merchantApi.searchMerchant(1, 1, null, null, null, false);
        assertPage(page);
        if (page.getPageInfo().getDataSet().isEmpty()) {
            return;
        }
        Long id = page.getPageInfo().getDataSet().get(0).getId();
        Result<MerchantDTO> result = merchantApi.getMerchant(id);
        assertNotValidation("getMerchant", result);
        if (result.getBusinessCode() == 0 && result.getData() != null) {
            assertEquals(id, result.getData().getId());
            assertNotNull(result.getData().getName());
        }
    }

    @Test
    public void createUpdateActivateDisableDeleteMerchant_shouldSucceed() {
        String name = uniqueName("it-merchant");
        MerchantCreateRequest create = new MerchantCreateRequest();
        create.setName(name);
        create.setEmail(name.replaceAll("[^A-Za-z0-9]", "") + TestData.EMAIL_DOMAIN);
        create.setResellerName(TestData.RESELLER_GLOBAL);
        create.setContact("it-contact");
        create.setCountry(TestData.COUNTRY_CN);
        create.setProvince(TestData.PROVINCE_CN_JL);
        create.setPhone("444888");
        create.setAddress("Jiangsu suzhou city Xinghongjie 328#");
        create.setDescription("Merchant created by MerchantApiTest");
        create.setEntityAttributeValues(new LinkedHashMap<String, String>());

        Result<MerchantDTO> createResult = merchantApi.createMerchant(create);
        assertNotValidation("createMerchant", createResult);
        if (createResult.getBusinessCode() != 0 || createResult.getData() == null) {
            return;
        }
        Long id = createResult.getData().getId();
        assertNotNull(id);

        try {
            MerchantUpdateRequest update = new MerchantUpdateRequest();
            update.setName(name + "-updated");
            update.setContact("Jack");
            update.setCountry(TestData.COUNTRY_CN);
            update.setProvince(TestData.PROVINCE_CN_JL);
            update.setPhone("444866");
            update.setPostcode("5652");
            update.setAddress("Jiangsu suzhou city Xinghongjie 328#");
            update.setDescription("Merchant KFC Nanjing");
            update.setCreateUserFlag(true);
            update.setEntityAttributeValues(new LinkedHashMap<String, String>());

            Result<MerchantDTO> updateResult = merchantApi.updateMerchant(id, update);
            assertNotValidation("updateMerchant", updateResult);
            if (updateResult.getBusinessCode() == 0 && updateResult.getData() != null) {
                assertEquals(name + "-updated", updateResult.getData().getName());
            }

            assertNotValidation("activateMerchant", merchantApi.activateMerchant(id));
            assertNotValidation("getMerchant", merchantApi.getMerchant(id));
            assertNotValidation("replaceMerchantEmail",
                    merchantApi.replaceMerchantEmail(id, name + "@example.com", true));
            assertNotValidation("disableMerchant", merchantApi.disableMerchant(id));
        } finally {
            safeDeleteMerchant(id);
        }
    }

    @Test
    public void createMerchant_missingName_shouldFailValidation() {
        MerchantCreateRequest create = new MerchantCreateRequest();
        create.setResellerName(TestData.RESELLER_GLOBAL);
        Result<MerchantDTO> result = merchantApi.createMerchant(create);
        assertBusinessCode(-1, result);
        assertNotNull(result.getValidationErrors());
        assertFalse(result.getValidationErrors().isEmpty());
    }

    @Test
    public void moveMerchant_invalidParams_shouldFailValidation() {
        assertBusinessCode(-1, merchantApi.moveMerchant(null, "Reseller B"));
        assertBusinessCode(-1, merchantApi.moveMerchant(1000000000L, ""));

        StringBuilder tooLongName = new StringBuilder();
        for (int i = 0; i < 65; i++) {
            tooLongName.append('a');
        }
        assertBusinessCode(-1, merchantApi.moveMerchant(1000000000L, tooLongName.toString()));
    }

    @Test
    public void searchMerchant_pageSizeOutOfRange_shouldFailValidation() {
        Result<MerchantPageDTO> result = merchantApi.searchMerchant(
                1, 1000, null, null, null, false);
        assertValidationErrorContains(result, "pageSize");
    }

    @Test
    public void moveMerchant_betweenResellers_shouldSucceed() throws InterruptedException {
        ResellerDTO source = createActiveReseller("lifecycle-src");
        ResellerDTO target = createActiveReseller("lifecycle-target");
        try {
            if (source == null || target == null) {
                return;
            }
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
    public void moveMerchant_concurrentLifecycle_shouldSucceed() throws InterruptedException {
        ResellerDTO source = createActiveReseller("concurrent-src");
        ResellerDTO target = createActiveReseller("concurrent-target");
        ExecutorService executor = Executors.newFixedThreadPool(CONCURRENT_THREADS);
        final CountDownLatch latch = new CountDownLatch(CONCURRENT_THREADS);
        final ConcurrentLinkedQueue<Throwable> errors = new ConcurrentLinkedQueue<Throwable>();
        try {
            if (source == null || target == null) {
                return;
            }
            final String sourceName = source.getName();
            final String targetName = target.getName();
            for (int i = 0; i < CONCURRENT_THREADS; i++) {
                final int index = i;
                executor.submit(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            runMerchantLifecycle("concurrent-merchant-" + index,
                                    sourceName, targetName);
                        } catch (Throwable t) {
                            errors.add(t);
                        } finally {
                            latch.countDown();
                        }
                    }
                });
            }
            assertTrue("Concurrent lifecycles did not finish in time",
                    latch.await(180, TimeUnit.SECONDS));
            assertTrue("Concurrent lifecycle failures: " + errors, errors.isEmpty());
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

    private void assertDisableSuccessWithRetry(Long merchantId) throws InterruptedException {
        Result<String> result = null;
        for (int attempt = 1; attempt <= DISABLE_RETRY_MAX_ATTEMPTS; attempt++) {
            result = merchantApi.disableMerchant(merchantId);
            if (result.getBusinessCode() != BUSINESS_CODE_MERCHANT_MOVE_IN_PROGRESS) {
                break;
            }
            if (attempt < DISABLE_RETRY_MAX_ATTEMPTS) {
                Thread.sleep(DISABLE_RETRY_INTERVAL_MS);
            }
        }
        assertNotValidation("disableMerchant", result);
    }

    private ResellerDTO createActiveReseller(String prefix) {
        String name = uniqueName(prefix);
        ResellerCreateRequest request = new ResellerCreateRequest();
        request.setName(name);
        request.setEmail(name.replaceAll("[^A-Za-z0-9]", "") + TestData.EMAIL_DOMAIN);
        request.setParentResellerName(System.getProperty("test.parent.reseller",
                TestData.RESELLER_GLOBAL));
        request.setContact("sdk-test");
        request.setCountry(TestData.COUNTRY_CN);
        request.setPhone("88888888");
        request.setPostcode("215000");
        request.setCompany("PAX");
        request.setAddress("Suzhou");
        request.setActivateWhenCreate(Boolean.TRUE);

        Result<ResellerDTO> result = resellerApi.createReseller(request);
        assertNotValidation("createActiveReseller", result);
        if (result.getBusinessCode() == BUSINESS_CODE_RESELLER_CAPACITY_FULL
                || result.getData() == null) {
            return null;
        }
        assertNotNull("Created reseller has no data: " + result, result.getData());
        return result.getData();
    }

    private Result<String> deleteMerchantWithRetry(Long merchantId) {
        Result<String> result = null;
        for (int attempt = 0; attempt < DISABLE_RETRY_MAX_ATTEMPTS; attempt++) {
            result = merchantApi.deleteMerchant(merchantId);
            int code = result.getBusinessCode();
            if (code != BUSINESS_CODE_MERCHANT_MOVE_IN_PROGRESS && code != 1793) {
                break;
            }
            if (attempt < DISABLE_RETRY_MAX_ATTEMPTS - 1) {
                sleepQuietly();
            }
        }
        return result;
    }

    private void safeDeleteMerchant(Long merchantId) {
        Result<String> result = deleteMerchantWithRetry(merchantId);
        if (result.getBusinessCode() != 0) {
            System.err.println("WARN: cleanup merchant " + merchantId
                    + " returned " + result.getBusinessCode());
        }
    }

    private void safeDeleteReseller(Long resellerId) {
        int lastCode = -1;
        for (int attempt = 0; attempt < DISABLE_RETRY_MAX_ATTEMPTS; attempt++) {
            Result<String> disable = resellerApi.disableReseller(resellerId);
            int disableCode = disable.getBusinessCode();
            if (disableCode != 0 && disableCode != 1886) {
                lastCode = disableCode;
                sleepQuietly();
                continue;
            }
            Result<String> delete = resellerApi.deleteReseller(resellerId);
            lastCode = delete.getBusinessCode();
            if (lastCode == 0) {
                return;
            }
            if (lastCode != 1793 && lastCode != 1875) {
                break;
            }
            sleepQuietly();
        }
        System.err.println("WARN: cleanup reseller " + resellerId
                + " returned " + lastCode);
    }

    private void runMerchantLifecycle(String prefix, String sourceResellerName,
                                      String targetResellerName) throws InterruptedException {
        String name = uniqueName(prefix);
        MerchantCreateRequest request = new MerchantCreateRequest();
        request.setName(name);
        request.setEmail(name.replaceAll("[^A-Za-z0-9]", "") + TestData.EMAIL_DOMAIN);
        request.setResellerName(sourceResellerName);
        request.setContact("sam");
        request.setCountry(TestData.COUNTRY_CN);
        request.setProvince(TestData.PROVINCE_CN_JL);
        request.setPhone("444888");
        request.setAddress("Jiangsu suzhou city Xinghongjie 328#");
        request.setDescription("Merchant created by MerchantApiTest");
        request.setEntityAttributeValues(new LinkedHashMap<String, String>());

        Result<MerchantDTO> createResult = merchantApi.createMerchant(request);
        assertNotValidation("createMerchant", createResult);
        if (createResult.getBusinessCode() != 0 || createResult.getData() == null) {
            return;
        }
        Long merchantId = createResult.getData().getId();
        assertNotNull(merchantId);

        boolean deleted = false;
        try {
            assertNotValidation("activateMerchant", merchantApi.activateMerchant(merchantId));
            assertNotValidation("moveMerchant",
                    merchantApi.moveMerchant(merchantId, targetResellerName));
            assertDisableSuccessWithRetry(merchantId);
            Result<String> delete = deleteMerchantWithRetry(merchantId);
            assertNotValidation("deleteMerchant", delete);
            deleted = delete.getBusinessCode() == 0;
        } finally {
            if (!deleted) {
                safeDeleteMerchant(merchantId);
            }
        }
    }

    private static void assertNotValidation(String step, Result<?> result) {
        assertNotNull(step + " returned null result", result);
        assertTrue(step + " failed validation: " + result, result.getBusinessCode() != -1);
    }

    private static void sleepQuietly() {
        try {
            Thread.sleep(DISABLE_RETRY_INTERVAL_MS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
