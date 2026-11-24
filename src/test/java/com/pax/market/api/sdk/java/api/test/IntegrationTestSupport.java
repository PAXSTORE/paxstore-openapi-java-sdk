package com.pax.market.api.sdk.java.api.test;

import com.pax.market.api.sdk.java.api.base.dto.PageInfo;
import com.pax.market.api.sdk.java.api.base.dto.Result;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.Assert.*;

/**
 * Common helpers for the live integration tests.
 *
 * <p>These tests call the real dev environment configured in {@link TestConstants}.
 * Shared test identifiers are kept in the TestData helper class so they are easy to
 * review and update when the dev data set changes.</p>
 */
public abstract class IntegrationTestSupport {

    private static final AtomicInteger SEQUENCE = new AtomicInteger();
    private static final DateTimeFormatter SUFFIX_FORMAT =
            DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

    protected static String uniqueSuffix() {
        return LocalDateTime.now().format(SUFFIX_FORMAT)
                + "-" + UUID.randomUUID().toString().substring(0, 8)
                + "-" + SEQUENCE.incrementAndGet();
    }

    protected static String uniqueName(String prefix) {
        return prefix + "-" + uniqueSuffix();
    }

    protected static <T extends Serializable> void assertOk(Result<T> result) {
        assertBusinessCode(0, result);
    }

    protected static <T extends Serializable> T assertSuccess(Result<T> result) {
        assertOk(result);
        assertNotNull("Result data must not be null", result.getData());
        return result.getData();
    }

    protected static <T extends Serializable> Result<T> assertBusinessCode(int expected, Result<T> result) {
        assertNotNull("Result must not be null", result);
        String detail = result.getMessage();
        if (result.getValidationErrors() != null && !result.getValidationErrors().isEmpty()) {
            detail = String.valueOf(result.getValidationErrors());
        }
        assertEquals("Unexpected business code (detail=" + detail + ")",
                expected, result.getBusinessCode());
        return result;
    }

    protected static <T extends Serializable> void assertValidationErrorContains(Result<T> result, String field) {
        assertNotNull("Result must not be null", result);
        assertEquals(-1, result.getBusinessCode());
        List<String> errors = result.getValidationErrors();
        assertNotNull("Validation errors must not be null", errors);
        assertTrue("Expected validation error for field '" + field + "' but got " + errors,
                errors.stream().anyMatch(e -> e != null && e.contains(field)));
    }

    protected static <T extends Serializable> void assertPage(Result<T> result) {
        assertNotNull("Result must not be null", result);
        assertEquals("Unexpected business code: " + result.getMessage(),
                0, result.getBusinessCode());
        PageInfo<T> pageInfo = result.getPageInfo();
        assertNotNull("PageInfo must not be null", pageInfo);
        assertTrue("pageNo must be positive", pageInfo.getPageNo() >= 1);
        assertTrue("limit must be positive", pageInfo.getLimit() >= 1);
        assertNotNull("dataSet must not be null", pageInfo.getDataSet());
        assertTrue("totalCount must not be negative", pageInfo.getTotalCount() != null
                && pageInfo.getTotalCount() >= 0);
    }

    protected static <T extends Serializable> void assertPageHasData(Result<T> result) {
        assertPage(result);
        assertFalse("Expected at least one record", result.getPageInfo().getDataSet().isEmpty());
    }
}
