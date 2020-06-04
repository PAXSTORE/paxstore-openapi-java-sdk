package com.pax.market.api.sdk.java.api.test;

import com.pax.market.api.sdk.java.api.base.dto.Result;
import com.pax.market.api.sdk.java.api.goinsight.GoInsightApi;
import com.pax.market.api.sdk.java.api.goinsight.dto.DataQueryResultDTO;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.TimeZone;

/**
 * @author liukai
 * @date 2020/3/23
 */
public class GoInsightApiTest {

    private static final Logger logger = LoggerFactory.getLogger(AppApiTest.class.getSimpleName());

    private GoInsightApi goInsightApi;

    private static final TimeZone tz = TimeZone.getTimeZone("Etc/GMT-1");
    private static final GoInsightApi.TimestampRangeType rangeType = GoInsightApi.TimestampRangeType.TODAY;
    private static final String queryCode = "hd77smoq";

    @Before
    public void init() {
        goInsightApi = new GoInsightApi(TestConstants.API_BASE_URL, TestConstants.API_KEY, TestConstants.API_SECRET, tz);
    }

    @Test
    public void testSearchDataOnlyQueryCode() {
        Result<DataQueryResultDTO> result = goInsightApi.findDataFromInsight(queryCode);
        logger.debug("Result of search data from insight: {}",result.toString());
        Assert.assertTrue(result.getBusinessCode() == 0);
    }

    @Test
    public void testSearchDataNoPage() {
        Result<DataQueryResultDTO> result = goInsightApi.findDataFromInsight(queryCode, rangeType);
        logger.debug("Result of search data from insight: {}",result.toString());
        Assert.assertTrue(result.getBusinessCode() == 0);
    }

    @Test
    public void testSearchData() {
        Result<DataQueryResultDTO> result = goInsightApi.findDataFromInsight(queryCode, rangeType,1,10);
        logger.debug("Result of search data from insight: {}",result.toString());
        Assert.assertTrue(result.getBusinessCode() == 0);
    }

    public static void main(String[] args) {
        String[] ids = TimeZone.getAvailableIDs();
        for (int i = 0; i<ids.length; i++) {
            int offset = TimeZone.getTimeZone(ids[i]).getRawOffset();
            System.out.println(i+" "+ids[i]+" "+offset / 1000 + "\t");
        }
    }
}