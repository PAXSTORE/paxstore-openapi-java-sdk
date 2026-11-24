package com.pax.market.api.sdk.java.api.test;

import com.pax.market.api.sdk.java.api.base.dto.Result;
import com.pax.market.api.sdk.java.api.merchantVariable.MerchantVariableApi;
import com.pax.market.api.sdk.java.api.merchantVariable.MerchantVariableApi.VariableSource;
import com.pax.market.api.sdk.java.api.merchantVariable.dto.MerchantVariableCreateRequest;
import com.pax.market.api.sdk.java.api.merchantVariable.dto.MerchantVariableDTO;
import com.pax.market.api.sdk.java.api.merchantVariable.dto.MerchantVariableDeleteRequest;
import com.pax.market.api.sdk.java.api.merchantVariable.dto.MerchantVariableUpdateRequest;
import com.pax.market.api.sdk.java.api.variable.dto.ParameterVariable;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Integration tests for {@link MerchantVariableApi}.
 *
 * <p>Write operations create their own variables with unique keys and clean up
 * afterwards. Because the create API does not return the generated id, tests
 * resolve it by searching for the unique key.</p>
 */
public class MerchantVariableApiTest extends IntegrationTestSupport {

    private MerchantVariableApi api;

    @Before
    public void setUp() {
        api = new MerchantVariableApi(TestConstants.API_BASE_URL,
                TestConstants.API_KEY, TestConstants.API_SECRET);
    }

    private static ParameterVariable variable(String key, String value) {
        ParameterVariable pv = new ParameterVariable();
        pv.setKey(key);
        pv.setValue(value);
        pv.setRemarks("integration test " + key);
        return pv;
    }

    private void createVariables(List<ParameterVariable> variables) {
        MerchantVariableCreateRequest request = new MerchantVariableCreateRequest();
        request.setMerchantId(TestData.MERCHANT_ID);
        request.setVariableList(variables);
        assertOk(api.createMerchantVariable(request));
    }

    private Long tryFindVariableIdByKey(String key) {
        Result<MerchantVariableDTO> page = api.searchMerchantVariable(
                1, 10, null, TestData.MERCHANT_ID, null, key, null);
        if (page.getBusinessCode() != 0 || page.getPageInfo() == null
                || page.getPageInfo().getDataSet() == null) {
            return null;
        }
        for (MerchantVariableDTO dto : page.getPageInfo().getDataSet()) {
            if (key.equals(dto.getKey())) {
                return dto.getId();
            }
        }
        return null;
    }

    private Long findVariableIdByKey(String key) {
        Long id = tryFindVariableIdByKey(key);
        assertNotNull("Created variable not found by key: " + key, id);
        return id;
    }

    private void deleteVariables(String... keys) {
        for (String key : keys) {
            Long id = tryFindVariableIdByKey(key);
            if (id != null) {
                api.deleteMerchantVariable(id);
            }
        }
    }

    @Test
    public void searchMerchantVariable_shouldReturnPage() {
        Result<MerchantVariableDTO> result = api.searchMerchantVariable(1, 2,
                MerchantVariableApi.SearchOrderBy.Variable_asc,
                TestData.MERCHANT_ID, null, null, VariableSource.MERCHANT);
        assertPage(result);
    }

    @Test
    public void createMerchantVariable_shouldSucceed() {
        String key1 = uniqueName("it-mv-create-1");
        String key2 = uniqueName("it-mv-create-2");
        List<ParameterVariable> variables = new ArrayList<>();
        variables.add(variable(key1, "value1"));
        ParameterVariable second = variable(key2, "value2");
        second.setPackageName("");
        variables.add(second);

        try {
            createVariables(variables);
            assertNotNull(findVariableIdByKey(key1));
            assertNotNull(findVariableIdByKey(key2));
        } finally {
            deleteVariables(key1, key2);
        }
    }

    @Test
    public void updateMerchantVariable_shouldSucceed() {
        String key = uniqueName("it-mv-update");
        try {
            createVariables(Arrays.asList(variable(key, "original-value")));
            Long id = findVariableIdByKey(key);

            MerchantVariableUpdateRequest update = new MerchantVariableUpdateRequest();
            update.setKey(key);
            update.setValue("updated-value");
            update.setRemarks("updated remarks");
            assertOk(api.updateMerchantVariable(id, update));
        } finally {
            deleteVariables(key);
        }
    }

    @Test
    public void deleteMerchantVariable_shouldSucceed() {
        String key = uniqueName("it-mv-delete");
        createVariables(Arrays.asList(variable(key, "value")));
        Long id = findVariableIdByKey(key);
        assertOk(api.deleteMerchantVariable(id));
        assertNull("Variable should be gone after delete", tryFindVariableIdByKey(key));
    }

    @Test
    public void batchDeletionMerchantVariable_shouldSucceed() {
        String key1 = uniqueName("it-mv-batch-1");
        String key2 = uniqueName("it-mv-batch-2");
        List<ParameterVariable> variables = new ArrayList<>();
        variables.add(variable(key1, "value1"));
        variables.add(variable(key2, "value2"));
        createVariables(variables);

        Long id1 = findVariableIdByKey(key1);
        Long id2 = findVariableIdByKey(key2);
        MerchantVariableDeleteRequest request = new MerchantVariableDeleteRequest();
        request.setVariableIds(Arrays.asList(id1, id2));
        assertOk(api.batchDeletionMerchantVariable(request));
        assertNull(tryFindVariableIdByKey(key1));
        assertNull(tryFindVariableIdByKey(key2));
    }

    @Test
    public void searchMerchantVariable_noMerchantId_shouldFailValidation() {
        Result<MerchantVariableDTO> result = api.searchMerchantVariable(
                1, 10, null, null, null, null, null);
        assertBusinessCode(-1, result);
    }

    @Test
    public void searchMerchantVariable_pageSizeOutOfRange_shouldFailValidation() {
        Result<MerchantVariableDTO> result = api.searchMerchantVariable(
                1, 1000, null, TestData.MERCHANT_ID, null, null, null);
        assertValidationErrorContains(result, "pageSize");
    }

    @Test
    public void deleteMerchantVariable_invalidId_shouldFailValidation() {
        assertBusinessCode(-1, api.deleteMerchantVariable(-1L));
    }
}
