package com.pax.market.api.sdk.java.api.test;

import com.pax.market.api.sdk.java.api.base.dto.Result;
import com.pax.market.api.sdk.java.api.factoryModel.FactoryModelApi;
import com.pax.market.api.sdk.java.api.factoryModel.FactoryModelApi.SearchOrderBy;
import com.pax.market.api.sdk.java.api.factoryModel.dto.FactoryDTO;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Integration tests for {@link FactoryModelApi}.
 */
public class FactoryModelApiTest extends IntegrationTestSupport {

    private FactoryModelApi factoryModelApi;

    @Before
    public void setUp() {
        factoryModelApi = new FactoryModelApi(TestConstants.API_BASE_URL,
                TestConstants.API_KEY, TestConstants.API_SECRET);
    }

    @Test
    public void searchFactoryModels_shouldReturnPage() {
        Result<FactoryDTO> result = factoryModelApi.searchFactoryModels(
                1, 5, SearchOrderBy.name_asc, null, null, null);
        assertPage(result);
    }

    @Test
    public void searchFactoryModels_byModelName_shouldReturnPage() {
        Result<FactoryDTO> result = factoryModelApi.searchFactoryModels(
                1, 10, SearchOrderBy.name_desc, null, TestData.MODEL_A920, null);
        assertPage(result);
    }

    @Test
    public void searchFactoryModels_pageSizeOutOfRange_shouldFailValidation() {
        Result<FactoryDTO> result = factoryModelApi.searchFactoryModels(1, 0, null, null, null, null);
        assertValidationErrorContains(result, "pageSize");
    }
}
