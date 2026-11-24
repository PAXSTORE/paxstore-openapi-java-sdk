package com.pax.market.api.sdk.java.api.test;

import com.pax.market.api.sdk.java.api.base.dto.Result;
import com.pax.market.api.sdk.java.api.entityAttribute.EntityAttributeApi;
import com.pax.market.api.sdk.java.api.entityAttribute.EntityAttributeApi.EntityAttributeType;
import com.pax.market.api.sdk.java.api.entityAttribute.EntityAttributeApi.EntityInputType;
import com.pax.market.api.sdk.java.api.entityAttribute.EntityAttributeApi.SearchOrderBy;
import com.pax.market.api.sdk.java.api.entityAttribute.dto.EntityAttributeCreateRequest;
import com.pax.market.api.sdk.java.api.entityAttribute.dto.EntityAttributeDTO;
import com.pax.market.api.sdk.java.api.entityAttribute.dto.EntityAttributeLabelInfo;
import com.pax.market.api.sdk.java.api.entityAttribute.dto.EntityAttributeLabelUpdateRequest;
import com.pax.market.api.sdk.java.api.entityAttribute.dto.EntityAttributeUpdateRequest;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.*;

/**
 * Integration tests for {@link EntityAttributeApi}.
 *
 * <p>The dev marketplace enforces a short maximum length on the attribute key,
 * so write tests use a compact unique key instead of the long shared suffix.</p>
 */
public class EntityAttributeApiTest extends IntegrationTestSupport {

    private EntityAttributeApi entityAttributeApi;

    @Before
    public void setUp() {
        entityAttributeApi = new EntityAttributeApi(TestConstants.API_BASE_URL,
                TestConstants.API_KEY, TestConstants.API_SECRET);
    }

    private static String uniqueKey(String prefix) {
        return prefix + UUID.randomUUID().toString().substring(0, 8);
    }

    private EntityAttributeDTO createAttribute(String key) {
        EntityAttributeCreateRequest create = new EntityAttributeCreateRequest();
        create.setEntityType(EntityAttributeType.Reseller);
        create.setDefaultLabel("it-default-" + key);
        create.setInputType(EntityInputType.Text);
        create.setKey(key);
        create.setMaxLength(5);
        create.setMinLength(1);
        create.setRequired(false);
        return assertSuccess(entityAttributeApi.createEntityAttribute(create));
    }

    @Test
    public void getEntityAttribute_shouldReturnAttribute() {
        Result<EntityAttributeDTO> page = entityAttributeApi.searchEntityAttributes(
                1, 1, SearchOrderBy.EntityType_asc, null, null);
        assertPageHasData(page);
        Long id = page.getPageInfo().getDataSet().get(0).getId();
        EntityAttributeDTO dto = assertSuccess(entityAttributeApi.getEntityAttribute(id));
        assertEquals(id, dto.getId());
    }

    @Test
    public void searchEntityAttributes_shouldReturnPage() {
        Result<EntityAttributeDTO> result = entityAttributeApi.searchEntityAttributes(
                1, 15, SearchOrderBy.EntityType_asc, null, null);
        assertPageHasData(result);
    }

    @Test
    public void createEntityAttribute_shouldSucceed() {
        String key = uniqueKey("ea");
        EntityAttributeDTO created = createAttribute(key);
        assertNotNull(created.getId());
        try {
            assertEquals(key, created.getKey());
            assertEquals(EntityAttributeType.Reseller.val(), created.getEntityType());
        } finally {
            assertOk(entityAttributeApi.deleteEntityAttribute(created.getId()));
        }
    }

    @Test
    public void updateEntityAttribute_shouldSucceed() {
        String key = uniqueKey("eu");
        EntityAttributeDTO created = createAttribute(key);
        Long id = created.getId();
        try {
            EntityAttributeUpdateRequest update = new EntityAttributeUpdateRequest();
            update.setInputType(EntityInputType.Text);
            update.setMaxLength(6);
            update.setMinLength(2);
            update.setRequired(false);
            update.setDefaultLabel("it-updated-" + key);
            assertOk(entityAttributeApi.updateEntityAttribute(id, update));
        } finally {
            assertOk(entityAttributeApi.deleteEntityAttribute(id));
        }
    }

    @Test
    public void updateEntityAttributeLabel_shouldSucceed() {
        String key = uniqueKey("el");
        EntityAttributeDTO created = createAttribute(key);
        Long id = created.getId();
        try {
            EntityAttributeLabelInfo jp = new EntityAttributeLabelInfo();
            jp.setLabel("テストラベル-jp");
            jp.setLocale("jp");
            EntityAttributeLabelInfo en = new EntityAttributeLabelInfo();
            en.setLabel("it-label-en-" + key);
            en.setLocale("en");
            EntityAttributeLabelInfo zh = new EntityAttributeLabelInfo();
            zh.setLabel("测试标签-" + key);
            zh.setLocale("zh_CN");

            List<EntityAttributeLabelInfo> labels = new ArrayList<>();
            labels.add(jp);
            labels.add(en);
            labels.add(zh);
            EntityAttributeLabelUpdateRequest request = new EntityAttributeLabelUpdateRequest();
            request.setEntityAttributeLabelList(labels);
            assertOk(entityAttributeApi.updateEntityAttributeLabel(id, request));
        } finally {
            assertOk(entityAttributeApi.deleteEntityAttribute(id));
        }
    }

    @Test
    public void deleteEntityAttribute_shouldSucceed() {
        EntityAttributeDTO created = createAttribute(uniqueKey("ed"));
        assertOk(entityAttributeApi.deleteEntityAttribute(created.getId()));
    }

    @Test
    public void getEntityAttribute_invalidId_shouldFailValidation() {
        assertBusinessCode(-1, entityAttributeApi.getEntityAttribute(-1L));
    }
}
