package com.pax.market.api.sdk.java.api.test;

import static org.junit.Assert.*;

import com.pax.market.api.sdk.java.api.base.dto.Result;
import com.pax.market.api.sdk.java.api.merchant.category.MerchantCategoryApi;
import com.pax.market.api.sdk.java.api.merchant.category.dto.MerchantCategoryDTO;
import com.pax.market.api.sdk.java.api.merchant.category.dto.MerchantCategoryCreateRequest;
import com.pax.market.api.sdk.java.api.merchant.category.dto.MerchantCategoryUpdateRequest;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Integration tests for {@link MerchantCategoryApi}.
 */
public class MerchantCategoryApiTest extends IntegrationTestSupport {

    private MerchantCategoryApi categoryApi;

    @Before
    public void setUp() {
        categoryApi = new MerchantCategoryApi(TestConstants.API_BASE_URL,
                TestConstants.API_KEY, TestConstants.API_SECRET, Locale.ENGLISH);
    }

    @Test
    public void getMerchantCategories_shouldReturnList() {
        Result<ArrayList<MerchantCategoryDTO>> result = categoryApi.getMerchantCategories("");
        ArrayList<MerchantCategoryDTO> list = assertSuccess(result);
        assertFalse("Expected at least one merchant category", list.isEmpty());
    }

    @Test
    public void createMerchantCategory_shouldSucceed() {
        String name = uniqueName("it-category-create");
        MerchantCategoryCreateRequest create = new MerchantCategoryCreateRequest();
        create.setName(name);
        create.setRemarks("created by integration test");

        Long id = assertSuccess(categoryApi.createMerchantCategory(create)).getId();
        assertNotNull(id);
        try {
            ArrayList<MerchantCategoryDTO> matches = assertSuccess(
                    categoryApi.getMerchantCategories(name));
            assertTrue("Created category not found by name",
                    matches.stream().anyMatch(dto -> id.equals(dto.getId())));
        } finally {
            assertOk(categoryApi.deleteMerchantCategory(id));
        }
    }

    @Test
    public void updateMerchantCategory_shouldSucceed() {
        String name = uniqueName("it-category-update");
        MerchantCategoryCreateRequest create = new MerchantCategoryCreateRequest();
        create.setName(name);
        create.setRemarks("original remarks");
        Long id = assertSuccess(categoryApi.createMerchantCategory(create)).getId();
        assertNotNull(id);

        try {
            MerchantCategoryUpdateRequest update = new MerchantCategoryUpdateRequest();
            update.setName(name + "-updated");
            update.setRemarks("updated by integration test");
            MerchantCategoryDTO updated = assertSuccess(
                    categoryApi.updateMerchantCategory(id, update));
            assertEquals(name + "-updated", updated.getName());
            assertEquals("updated by integration test", updated.getRemarks());
        } finally {
            assertOk(categoryApi.deleteMerchantCategory(id));
        }
    }

    @Test
    public void deleteMerchantCategory_shouldSucceed() {
        String name = uniqueName("it-category-delete");
        MerchantCategoryCreateRequest create = new MerchantCategoryCreateRequest();
        create.setName(name);
        create.setRemarks("to be deleted");
        Long id = assertSuccess(categoryApi.createMerchantCategory(create)).getId();
        assertNotNull(id);

        assertOk(categoryApi.deleteMerchantCategory(id));

        ArrayList<MerchantCategoryDTO> matches = assertSuccess(
                categoryApi.getMerchantCategories(name));
        assertFalse("Deleted category is still present",
                matches.stream().anyMatch(dto -> id.equals(dto.getId())));
    }

    @Test
    public void batchCreateMerchantCategory_skipExistingFalse_shouldSucceed() {
        List<MerchantCategoryCreateRequest> requests = new ArrayList<MerchantCategoryCreateRequest>();
        List<String> names = new ArrayList<String>();
        for (int i = 0; i < 3; i++) {
            String name = uniqueName("it-batch-category-" + i);
            names.add(name);
            MerchantCategoryCreateRequest req = new MerchantCategoryCreateRequest();
            req.setName(name);
            req.setRemarks("batch created " + i);
            requests.add(req);
        }

        ArrayList<MerchantCategoryDTO> created = assertSuccess(
                categoryApi.batchCreateMerchantCategory(requests, false));
        assertEquals(3, created.size());
        try {
            for (MerchantCategoryDTO dto : created) {
                assertNotNull(dto.getId());
                assertTrue("Unexpected batch category: " + dto, names.contains(dto.getName()));
            }
        } finally {
            for (MerchantCategoryDTO dto : created) {
                if (dto.getId() != null) {
                    assertOk(categoryApi.deleteMerchantCategory(dto.getId()));
                }
            }
        }
    }

    @Test
    public void batchCreateMerchantCategory_skipExistingTrue_shouldSucceed() {
        String name = uniqueName("it-batch-category-skip");
        MerchantCategoryCreateRequest req = new MerchantCategoryCreateRequest();
        req.setName(name);
        req.setRemarks("batch created with skipExist");
        List<MerchantCategoryCreateRequest> requests = new ArrayList<MerchantCategoryCreateRequest>();
        requests.add(req);

        ArrayList<MerchantCategoryDTO> created = assertSuccess(
                categoryApi.batchCreateMerchantCategory(requests, true));
        assertFalse(created.isEmpty());
        for (MerchantCategoryDTO dto : created) {
            if (dto.getId() != null) {
                assertOk(categoryApi.deleteMerchantCategory(dto.getId()));
            }
        }
    }
}
