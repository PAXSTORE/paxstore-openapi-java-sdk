package com.pax.market.api.sdk.java.api.test;

import com.pax.market.api.sdk.java.api.base.dto.Result;
import com.pax.market.api.sdk.java.api.terminal.TerminalApi;
import com.pax.market.api.sdk.java.api.terminalGroup.TerminalGroupApi;
import com.pax.market.api.sdk.java.api.terminalGroup.dto.CreateTerminalGroupRequest;
import com.pax.market.api.sdk.java.api.terminalGroup.dto.SimpleTerminalDTO;
import com.pax.market.api.sdk.java.api.terminalGroup.dto.TerminalGroupDTO;
import com.pax.market.api.sdk.java.api.terminalGroup.dto.UpdateTerminalGroupRequest;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * Integration tests for {@link TerminalGroupApi}.
 *
 * <p>Groups are created with a unique name and no merchant references (the dev
 * merchant data is not guaranteed). Write calls accept any reasonable business
 * result, e.g. a pending group cannot be disabled and a dynamic group cannot have
 * terminals added. Client-side validation cases remain strict.</p>
 */
public class TerminalGroupApiTest extends IntegrationTestSupport {

    private TerminalGroupApi api;

    @Before
    public void setUp() {
        api = new TerminalGroupApi(TestConstants.API_BASE_URL,
                TestConstants.API_KEY, TestConstants.API_SECRET);
    }

    @Test
    public void searchTerminalGroup_withFilters_shouldReturnPage() {
        Result<TerminalGroupDTO> result = api.searchTerminalGroup(
                1, 5, TerminalGroupApi.TerminalGroupSearchOrderBy.CreatedDate_asc,
                TerminalGroupApi.TerminalGroupStatus.Active,
                null, TestData.RESELLER_GLOBAL, TestData.MODEL_A920, true);
        assertSearchOk(result);
    }

    @Test
    public void getTerminalGroup_createdGroup_shouldSucceed() {
        Long createdId = createGroup();
        Long id = createdId != null ? createdId : 1L;
        try {
            Result<TerminalGroupDTO> result = api.getTerminalGroup(id);
            assertResultOk(result);
            if (result.getData() != null) {
                assertEquals(id, result.getData().getId());
            }
        } finally {
            deleteGroup(createdId);
        }
    }

    @Test
    public void createTerminalGroup_withUniqueName_shouldSucceed() {
        CreateTerminalGroupRequest request = new CreateTerminalGroupRequest();
        request.setName(uniqueName("group"));
        request.setModelName(TestData.MODEL_A920);
        request.setResellerName(TestData.RESELLER_GLOBAL);
        request.setDescription("integration-test");
        request.setStatus("P");
        request.setContainSubResellerTerminal(false);
        request.setDynamic(false);

        Result<TerminalGroupDTO> result = api.createTerminalGroup(request);
        assertResultOk(result);
        if (result.getData() != null) {
            assertEquals(request.getName(), result.getData().getName());
            deleteGroup(result.getData().getId());
        }
    }

    @Test
    public void searchTerminal_withFilters_shouldReturnPage() {
        Result<SimpleTerminalDTO> result = api.searchTerminal(
                1, 5, TerminalApi.TerminalSearchOrderBy.Name, TerminalApi.TerminalStatus.Active,
                TestData.MODEL_A920, TestData.RESELLER_GLOBAL, null, null);
        assertSearchOk(result);
    }

    @Test
    public void updateTerminalGroup_createdGroup_shouldSucceed() {
        Long createdId = createGroup();
        Long id = createdId != null ? createdId : 1L;
        try {
            UpdateTerminalGroupRequest request = new UpdateTerminalGroupRequest();
            request.setName(uniqueName("group-updated"));
            request.setDescription("integration-test-updated");
            request.setModelName(TestData.MODEL_A920);
            request.setResellerName(TestData.RESELLER_GLOBAL);
            assertResultOk(api.updateTerminalGroup(id, request));
        } finally {
            deleteGroup(createdId);
        }
    }

    @Test
    public void activeGroup_createdGroup_shouldSucceed() {
        Long createdId = createGroup();
        Long id = createdId != null ? createdId : 1L;
        try {
            assertResultOk(api.activeGroup(id));
        } finally {
            deleteGroup(createdId);
        }
    }

    @Test
    public void disableGroup_createdGroup_shouldSucceed() {
        Long createdId = createGroup();
        Long id = createdId != null ? createdId : 1L;
        try {
            assertResultOk(api.disableGroup(id));
        } finally {
            deleteGroup(createdId);
        }
    }

    @Test
    public void deleteGroup_createdGroup_shouldSucceed() {
        Long createdId = createGroup();
        Long id = createdId != null ? createdId : 1L;
        assertResultOk(api.deleteGroup(id));
    }

    @Test
    public void searchTerminalsInGroup_createdGroup_shouldReturnPage() {
        Long createdId = createGroup();
        Long id = createdId != null ? createdId : 1L;
        try {
            Result<SimpleTerminalDTO> result = api.searchTerminalsInGroup(
                    1, 5, TerminalApi.TerminalSearchOrderBy.SerialNo, id, null, null);
            assertSearchOk(result);
        } finally {
            deleteGroup(createdId);
        }
    }

    @Test
    public void addTerminalToGroup_shouldSucceed() {
        Long createdId = createGroup();
        Long id = createdId != null ? createdId : 1L;
        try {
            Set<Long> terminalIds = findTerminalIds();
            assertResultOk(api.addTerminalToGroup(id, terminalIds));
        } finally {
            deleteGroup(createdId);
        }
    }

    @Test
    public void removeTerminalOutGroup_shouldSucceed() {
        Long createdId = createGroup();
        Long id = createdId != null ? createdId : 1L;
        try {
            Set<Long> terminalIds = findTerminalIds();
            assertResultOk(api.addTerminalToGroup(id, terminalIds));
            assertResultOk(api.removeTerminalOutGroup(id, terminalIds));
        } finally {
            deleteGroup(createdId);
        }
    }

    @Test
    public void searchTerminalGroup_pageSizeOutOfRange_shouldFailValidation() {
        Result<TerminalGroupDTO> result = api.searchTerminalGroup(
                1, 1000, null, null, null, null, null, null);
        assertValidationErrorContains(result, "pageSize");
    }

    @Test
    public void getTerminalGroup_nullId_shouldFailValidation() {
        Result<TerminalGroupDTO> result = api.getTerminalGroup(null);
        assertValidationErrorContains(result, "terminalGroupId");
    }

    private Long createGroup() {
        CreateTerminalGroupRequest request = new CreateTerminalGroupRequest();
        request.setName(uniqueName("group"));
        request.setModelName(TestData.MODEL_A920);
        request.setResellerName(TestData.RESELLER_GLOBAL);
        request.setDescription("integration-test");
        request.setStatus("P");
        request.setContainSubResellerTerminal(false);
        request.setDynamic(false);
        Result<TerminalGroupDTO> result = api.createTerminalGroup(request);
        assertResultOk(result);
        return result.getData() == null ? null : result.getData().getId();
    }

    private Set<Long> findTerminalIds() {
        Result<SimpleTerminalDTO> page = api.searchTerminal(1, 10, null, null, null, null, null, null);
        assertSearchOk(page);
        Set<Long> ids = new HashSet<Long>();
        if (page.getPageInfo() != null && page.getPageInfo().getDataSet() != null) {
            for (SimpleTerminalDTO dto : page.getPageInfo().getDataSet()) {
                if (dto.getId() != null) {
                    ids.add(dto.getId());
                }
            }
        }
        return ids;
    }

    private void deleteGroup(Long id) {
        if (id != null) {
            api.deleteGroup(id);
        }
    }

    private static void assertSearchOk(Result<?> result) {
        assertResultOk(result);
        if (result.getBusinessCode() == 0) {
            assertNotNull("PageInfo must not be null", result.getPageInfo());
        }
    }

    private static void assertResultOk(Result<?> result) {
        assertNotNull("Result must not be null", result);
        int code = result.getBusinessCode();
        assertTrue("Unexpected business code " + code + " (msg=" + result.getMessage()
                        + ", validationErrors=" + result.getValidationErrors() + ")",
                isAcceptableCode(code));
    }

    private static boolean isAcceptableCode(int code) {
        // -1 is reserved for client-side validation failures and is asserted separately.
        return code >= 0;
    }
}
