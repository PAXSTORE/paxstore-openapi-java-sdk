package com.pax.market.api.sdk.java.api.test;

import com.pax.market.api.sdk.java.api.base.dto.Result;
import com.pax.market.api.sdk.java.api.terminalVariable.TerminalVariableApi;
import com.pax.market.api.sdk.java.api.terminalVariable.dto.TerminalParameterVariableDeleteRequest;
import com.pax.market.api.sdk.java.api.terminalVariable.dto.TerminalParameterVariableRequest;
import com.pax.market.api.sdk.java.api.variable.dto.ParameterVariable;
import com.pax.market.api.sdk.java.api.variable.dto.ParameterVariableDTO;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Integration tests for {@link TerminalVariableApi}.
 *
 * <p>Write operations create variables for the known dev terminal using unique
 * keys and clean up afterwards. The create API returns no id, so tests resolve
 * ids by querying the terminal's variables for the unique key.</p>
 */
public class TerminalVariableApiTest extends IntegrationTestSupport {

    private TerminalVariableApi api;

    @Before
    public void setUp() {
        api = new TerminalVariableApi(TestConstants.API_BASE_URL,
                TestConstants.API_KEY, TestConstants.API_SECRET);
    }

    private static ParameterVariable variable(String key, String value) {
        ParameterVariable pv = new ParameterVariable();
        pv.setKey(key);
        pv.setValue(value);
        pv.setRemarks("integration test " + key);
        return pv;
    }

    private void createVariables(String... keys) {
        List<ParameterVariable> variables = new ArrayList<>();
        for (String key : keys) {
            variables.add(variable(key, "value-" + key));
        }
        TerminalParameterVariableRequest request = new TerminalParameterVariableRequest();
        request.setTid(TestData.TERMINAL_TID);
        request.setVariableList(variables);
        assertOk(api.createTerminalVariable(request));
    }

    private Long tryFindVariableIdByKey(String key) {
        Result<ParameterVariableDTO> page = api.getTerminalVariable(
                1, 50, null, TestData.TERMINAL_TID, null, null, key, null);
        if (page.getBusinessCode() != 0 || page.getPageInfo() == null
                || page.getPageInfo().getDataSet() == null) {
            return null;
        }
        for (ParameterVariableDTO dto : page.getPageInfo().getDataSet()) {
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
                api.deleteTerminalVariable(id);
            }
        }
    }

    @Test
    public void getTerminalVariable_shouldReturnPage() {
        Result<ParameterVariableDTO> result = api.getTerminalVariable(
                1, 2, TerminalVariableApi.SearchOrderBy.Variable_asc,
                TestData.TERMINAL_TID, null, null, null, null);
        assertPage(result);
    }

    @Test
    public void createTerminalVariable_shouldSucceed() {
        String key = uniqueName("it-tv-create");
        try {
            createVariables(key);
            assertNotNull(findVariableIdByKey(key));
        } finally {
            deleteVariables(key);
        }
    }

    @Test
    public void updateTerminalVariable_shouldSucceed() {
        String key = uniqueName("it-tv-update");
        try {
            createVariables(key);
            Long id = findVariableIdByKey(key);

            ParameterVariable update = variable(key, "updated-value");
            assertOk(api.updateTerminalVariable(id, update));
        } finally {
            deleteVariables(key);
        }
    }

    @Test
    public void deleteTerminalVariable_shouldSucceed() {
        String key = uniqueName("it-tv-delete");
        createVariables(key);
        Long id = findVariableIdByKey(key);
        assertOk(api.deleteTerminalVariable(id));
        assertNull("Variable should be gone after delete", tryFindVariableIdByKey(key));
    }

    @Test
    public void batchDeletionTerminalVariable_shouldSucceed() {
        String key1 = uniqueName("it-tv-batch-1");
        String key2 = uniqueName("it-tv-batch-2");
        createVariables(key1, key2);

        Long id1 = findVariableIdByKey(key1);
        Long id2 = findVariableIdByKey(key2);
        TerminalParameterVariableDeleteRequest request = new TerminalParameterVariableDeleteRequest();
        request.setVariableIds(Arrays.asList(id1, id2));
        assertOk(api.batchDeletionTerminalVariable(request));
        assertNull(tryFindVariableIdByKey(key1));
        assertNull(tryFindVariableIdByKey(key2));
    }

    @Test
    public void getTerminalVariable_noIdentifier_shouldFail() {
        Result<ParameterVariableDTO> result = api.getTerminalVariable(
                1, 10, null, null, null, null, null, null);
        assertNotNull(result);
        assertTrue("Request without tid/serialNo should fail, got " + result.getBusinessCode(),
                result.getBusinessCode() != 0);
    }

    @Test
    public void deleteTerminalVariable_invalidId_shouldFail() {
        Result<String> result = api.deleteTerminalVariable(-1L);
        assertNotNull(result);
        assertTrue("Invalid id should fail, got " + result.getBusinessCode(),
                result.getBusinessCode() != 0);
    }
}
