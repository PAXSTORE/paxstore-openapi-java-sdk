package com.pax.market.api.sdk.java.api.test;

import static org.junit.Assert.*;

import com.pax.market.api.sdk.java.api.base.dto.Result;
import com.pax.market.api.sdk.java.api.reseller.ResellerApi;
import com.pax.market.api.sdk.java.api.reseller.ResellerApi.ResellerSearchOrderBy;
import com.pax.market.api.sdk.java.api.reseller.dto.*;
import com.pax.market.api.sdk.java.api.reseller.validator.ResellerCreateRequestValidator;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * Integration tests for {@link ResellerApi} against the dev environment.
 */
public class ResellerApiTest extends IntegrationTestSupport {

    private static final int BUSINESS_CODE_RESELLER_CAPACITY_FULL = 1799;
    private static final int RETRY_MAX_ATTEMPTS = 10;

    private ResellerApi resellerApi;

    @Before
    public void setUp() {
        resellerApi = new ResellerApi(TestConstants.API_BASE_URL,
                TestConstants.API_KEY, TestConstants.API_SECRET);
    }

    @Test
    public void searchReseller_shouldReturnPage() {
        Result<ResellerPageDTO> result = resellerApi.searchReseller(
                1, 10, ResellerSearchOrderBy.Contact, null, null, Boolean.FALSE);
        assertPage(result);
    }

    @Test
    public void searchReseller_byName_shouldReturnMatches() {
        Result<ResellerPageDTO> result = resellerApi.searchReseller(
                1, 10, ResellerSearchOrderBy.Name, TestData.RESELLER_GLOBAL, null, false);
        assertPage(result);
        for (ResellerPageDTO dto : result.getPageInfo().getDataSet()) {
            if (dto.getName() != null) {
                assertTrue("Unexpected reseller in name filter: " + dto,
                        dto.getName().contains(TestData.RESELLER_GLOBAL));
            }
        }
    }

    @Test
    public void getReseller_withKnownId_shouldReturnReseller() {
        Result<ResellerPageDTO> page = resellerApi.searchReseller(
                1, 1, ResellerSearchOrderBy.Name, null, null, false);
        assertPage(page);
        if (page.getPageInfo().getDataSet().isEmpty()) {
            return;
        }
        Long id = page.getPageInfo().getDataSet().get(0).getId();
        Result<ResellerDTO> result = resellerApi.getReseller(id);
        assertNotValidation("getReseller", result);
        if (result.getBusinessCode() == 0 && result.getData() != null) {
            assertEquals(id, result.getData().getId());
            assertNotNull(result.getData().getName());
        }
    }

    @Test
    public void searchResellerRkiKeyList_shouldReturnPage() {
        Result<ResellerPageDTO> page = resellerApi.searchReseller(
                1, 1, ResellerSearchOrderBy.Name, null, null, false);
        assertPage(page);
        if (page.getPageInfo().getDataSet().isEmpty()) {
            return;
        }
        Long id = page.getPageInfo().getDataSet().get(0).getId();
        Result<ResellerRkiKeyPageDTO> result = resellerApi.searchResellerRkiKeyList(id, 1, 10, null);
        assertPage(result);
    }

    @Test
    public void createUpdateActivateReplaceEmailDisableDeleteReseller_shouldSucceed() {
        String name = uniqueName("it-reseller");
        ResellerCreateRequest create = new ResellerCreateRequest();
        create.setName(name);
        create.setEmail(name.replaceAll("[^A-Za-z0-9]", "") + TestData.EMAIL_DOMAIN);
        create.setCountry(TestData.COUNTRY_CN);
        create.setContact("it-contact");
        create.setPhone("13800000000");
        create.setParentResellerName(TestData.RESELLER_GLOBAL);

        ResellerDTO created = createResellerOrNull(create);
        if (created == null) {
            return;
        }
        Long id = created.getId();
        assertNotNull(id);
        assertEquals(name, created.getName());

        try {
            ResellerUpdateRequest update = new ResellerUpdateRequest();
            update.setName(name + "-updated");
            update.setCountry(TestData.COUNTRY_CN);
            update.setContact("FFF");
            update.setPhone("87879696");
            update.setPostcode("850212");
            update.setCompany("Cam");
            update.setAddress("JiangSu Suzhou city xinghujie 203#");
            Result<ResellerDTO> updateResult = resellerApi.updateReseller(id, update);
            assertNotValidation("updateReseller", updateResult);
            if (updateResult.getBusinessCode() == 0 && updateResult.getData() != null) {
                assertEquals(name + "-updated", updateResult.getData().getName());
            }

            assertNotValidation("activateReseller", resellerApi.activateReseller(id));
            assertNotValidation("replaceResellerEmail",
                    resellerApi.replaceResellerEmail(id, name + "@example.com"));
            assertNotValidation("disableReseller", resellerApi.disableReseller(id));
        } finally {
            safeDeleteReseller(id);
        }
    }

    @Test
    public void createReseller_missingRequiredField_shouldFailClientValidation() {
        ResellerCreateRequest create = new ResellerCreateRequest();
        create.setCountry(TestData.COUNTRY_CN);
        Result<ResellerDTO> result = resellerApi.createReseller(create);
        assertBusinessCode(-1, result);
        assertNotNull(result.getValidationErrors());
        assertFalse(result.getValidationErrors().isEmpty());
    }

    @Test
    public void createReseller_optionalFieldsEmpty_shouldPassValidationAndCreate() {
        String name = uniqueName("optional-fields-reseller");
        ResellerCreateRequest request = new ResellerCreateRequest();
        request.setName(name);
        request.setEmail(name.replaceAll("[^A-Za-z0-9]", "") + TestData.EMAIL_DOMAIN);
        request.setContact(null);
        request.setCountry(null);
        request.setPhone(null);
        request.setParentResellerName(TestData.RESELLER_GLOBAL);

        List<String> validationErrs = ResellerCreateRequestValidator.validate(request);
        assertTrue("Optional fields must pass client validation: " + validationErrs,
                validationErrs.isEmpty());

        request.setActivateWhenCreate(Boolean.TRUE);
        ResellerDTO created = createResellerOrNull(request);
        if (created == null) {
            return;
        }
        Long id = created.getId();
        assertNotNull(id);
        try {
            assertEquals(name, created.getName());
        } finally {
            safeDeleteReseller(id);
        }
    }

    @Test
    public void searchReseller_pageSizeOutOfRange_shouldFailClientValidation() {
        Result<ResellerPageDTO> result = resellerApi.searchReseller(
                1, 1000, null, null, null, false);
        assertValidationErrorContains(result, "pageSize");
    }

    private ResellerDTO createResellerOrNull(ResellerCreateRequest request) {
        Result<ResellerDTO> result = resellerApi.createReseller(request);
        assertNotValidation("createReseller", result);
        if (result.getBusinessCode() == BUSINESS_CODE_RESELLER_CAPACITY_FULL
                || result.getData() == null) {
            return null;
        }
        return result.getData();
    }

    private void safeDeleteReseller(Long resellerId) {
        int lastCode = -1;
        for (int attempt = 0; attempt < RETRY_MAX_ATTEMPTS; attempt++) {
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

    private static void assertNotValidation(String step, Result<?> result) {
        assertNotNull(step + " returned null result", result);
        assertTrue(step + " failed validation: " + result, result.getBusinessCode() != -1);
    }

    private static void sleepQuietly() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
