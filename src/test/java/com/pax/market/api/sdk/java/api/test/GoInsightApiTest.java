package com.pax.market.api.sdk.java.api.test;

import com.pax.market.api.sdk.java.api.base.dto.Result;
import com.pax.market.api.sdk.java.api.goInsight.GoInsightApi;
import com.pax.market.api.sdk.java.api.goInsight.dto.DataQueryResultDTO;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author liukai
 * @date 2020/3/23
 */
public class GoInsightApiTest {

    private static final Logger logger = LoggerFactory.getLogger(AppApiTest.class.getSimpleName());

    private GoInsightApi goInsightApi;

    @Before
    public void init() {
        goInsightApi = new GoInsightApi(TestConstants.API_BASE_URL, TestConstants.API_KEY, TestConstants.API_SECRET);
    }

    @Test
    public void testSearchData() {
        Result<DataQueryResultDTO> result = goInsightApi.findDataFromInsight("hd77smoq",2,10);
        logger.debug("Result of search data from insight: {}",result.toString());
        Assert.assertTrue(result.getBusinessCode() == 0);
    }
}