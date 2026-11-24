package com.pax.market.api.sdk.java.api.test;

import com.pax.market.api.sdk.java.api.base.dto.Result;
import com.pax.market.api.sdk.java.api.goinsight.GoInsightApi;
import com.pax.market.api.sdk.java.api.goinsight.GoInsightApi.TimestampRangeType;
import com.pax.market.api.sdk.java.api.goinsight.dto.CustomColName;
import com.pax.market.api.sdk.java.api.goinsight.dto.DataQueryResultDTO;
import com.pax.market.api.sdk.java.api.goinsight.dto.GoInsightCustomFilter;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TimeZone;

import static org.junit.Assert.*;

/**
 * Integration tests for {@link GoInsightApi}.
 *
 * <p>The GoInsight value-added service may not be enabled on the dev marketplace
 * and the endpoint is rate limited (36022). All query tests therefore accept the
 * documented business codes 0 / 24012 / 36022 while still checking the SDK
 * returns a well-formed result.</p>
 */
public class GoInsightApiTest extends IntegrationTestSupport {

    private static final int CODE_SERVICE_NOT_ENABLED = 24012;
    private static final int CODE_RATE_LIMITED = 36022;
    private static final TimeZone TZ = TimeZone.getTimeZone("Etc/GMT-1");
    private static final TimestampRangeType RANGE = TimestampRangeType.TODAY;

    private GoInsightApi goInsightApi;

    @Before
    public void setUp() {
        goInsightApi = new GoInsightApi(TestConstants.API_BASE_URL,
                TestConstants.API_KEY, TestConstants.API_SECRET, TZ);
    }

    private void assertGoInsightResult(Result<DataQueryResultDTO> result) {
        assertNotNull(result);
        assertTrue("Unexpected business code: " + result.getBusinessCode(),
                result.getBusinessCode() == 0
                        || result.getBusinessCode() == CODE_SERVICE_NOT_ENABLED
                        || result.getBusinessCode() == CODE_RATE_LIMITED);
    }

    @Test
    public void findDataFromInsight_queryCodeOnly_shouldReturnResult() {
        assertGoInsightResult(goInsightApi.findDataFromInsight(TestData.GOINSIGHT_QUERY_CODE));
    }

    @Test
    public void findDataFromInsight_withRangeType_shouldReturnResult() {
        assertGoInsightResult(goInsightApi.findDataFromInsight(
                TestData.GOINSIGHT_QUERY_CODE, RANGE));
    }

    @Test
    public void findDataFromInsight_withPaging_shouldReturnResult() {
        assertGoInsightResult(goInsightApi.findDataFromInsight(
                TestData.GOINSIGHT_QUERY_CODE, RANGE, 1, 10));
    }

    @Test
    public void findDataFromInsight_withCustomFilters_shouldReturnResult() {
        List<GoInsightCustomFilter> filters = new ArrayList<>();
        GoInsightCustomFilter terminals = new GoInsightCustomFilter();
        terminals.setCloName(CustomColName.TERMINAL);
        terminals.setFilterValue("SN123,SN456,SN789");
        GoInsightCustomFilter resellers = new GoInsightCustomFilter();
        resellers.setCloName(CustomColName.RESELLER);
        resellers.setFilterValue("sub2_reseller1");
        filters.add(terminals);
        filters.add(resellers);

        assertGoInsightResult(goInsightApi.findDataFromInsight(
                TestData.GOINSIGHT_QUERY_CODE, RANGE, filters, false, 1, 10));
    }

    @Test
    public void findDataFromInsight_pageSizeOutOfRange_shouldFailValidation() {
        Result<DataQueryResultDTO> result = goInsightApi.findDataFromInsight(
                TestData.GOINSIGHT_QUERY_CODE, TimestampRangeType.LAST_WEEK,
                Collections.<GoInsightCustomFilter>emptyList(), false, 1, 1001);
        assertBusinessCode(-1, result);
    }
}
