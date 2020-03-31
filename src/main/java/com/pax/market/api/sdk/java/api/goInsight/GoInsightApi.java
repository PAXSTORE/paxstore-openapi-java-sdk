package com.pax.market.api.sdk.java.api.goInsight;

import com.pax.market.api.sdk.java.api.BaseThirdPartySysApi;
import com.pax.market.api.sdk.java.api.base.dto.Result;
import com.pax.market.api.sdk.java.api.base.request.SdkRequest;
import com.pax.market.api.sdk.java.api.client.ThirdPartySysApiClient;
import com.pax.market.api.sdk.java.api.goInsight.dto.DataQueryResponse;
import com.pax.market.api.sdk.java.api.goInsight.dto.DataQueryResultDTO;
import com.pax.market.api.sdk.java.api.util.EnhancedJsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

/**
 * @author liukai
 * @date 2020/3/23
 */
public class GoInsightApi extends BaseThirdPartySysApi {
    private static final Logger logger = LoggerFactory.getLogger(GoInsightApi.class.getSimpleName());
    private final String SEARCH_GO_INSIGHT_DATA_URL = "/v1/3rdsys/goInsight/data/querying/{queryCode}";

    public GoInsightApi(String baseUrl, String apiKey, String apiSecret) {
        super(baseUrl, apiKey, apiSecret);
    }

    public GoInsightApi(String baseUrl, String apiKey, String apiSecret, Locale locale) {
        super(baseUrl, apiKey, apiSecret, locale);
    }

    public GoInsightApi(String baseUrl, String apiKey, String apiSecret, TimeZone timeZone) {
        super(baseUrl, apiKey, apiSecret, timeZone);
    }

    public GoInsightApi(String baseUrl, String apiKey, String apiSecret, Locale locale, TimeZone timeZone) {
        super(baseUrl, apiKey, apiSecret, locale, timeZone);
    }

    public Result<DataQueryResultDTO> findDataFromInsight(String queryCode){
        return findDataFromInsight(queryCode, null,0, 0);
    }

    public Result<DataQueryResultDTO> findDataFromInsight(String queryCode, TimestampRangeType rangeType){
        return findDataFromInsight(queryCode, rangeType,0, 0);
    }

    public Result<DataQueryResultDTO> findDataFromInsight(String queryCode, TimestampRangeType rangeType, Integer pageNo, Integer pageSize){
        logger.debug("queryCode=" + queryCode);
        List<String> validationErrs = validateStr(queryCode, "parameter.queryCode.invalid");
        if (validationErrs.size() > 0) {
            return new Result<DataQueryResultDTO>(validationErrs);
        }
        ThirdPartySysApiClient client = new ThirdPartySysApiClient(getBaseUrl(), getApiKey(), getApiSecret());
        SdkRequest request = createSdkRequest(SEARCH_GO_INSIGHT_DATA_URL.replace("{queryCode}", queryCode));
        if(pageNo > 0 && pageSize > 0){
            request.addRequestParam("pageSize", pageSize+"");
            request.addRequestParam("pageNo", pageNo+"");
        }
        if(rangeType != null){
            request.addRequestParam("timeRangeType", rangeType.val+"");
        }

        DataQueryResponse dataQueryResponse = EnhancedJsonUtils.fromJson(client.execute(request), DataQueryResponse.class);
        Result<DataQueryResultDTO> result = new Result<DataQueryResultDTO>(dataQueryResponse);
        return result;
    }

    /**
     * search GoInsight data，Timestamp RangeType
     */
    public enum TimestampRangeType {

        /**
         * pass 1 day.
         */
        P1D("p1d"),
        /**
         * pass 1 week.
         */
        P1W("p1w"),
        /**
         * pass 1 month.
         */
        P1M("p1m"),
        /**
         * pass 1 year.
         */
        P1Y("p1y"),
        /**
         * recent 1 day.
         */
        R1D("r1d"),
        /**
         * recent 1 week.
         */
        R1W("r1w"),
        /**
         * recent 1 month.
         */
        R1M("r1m"),
        /**
         * recent 1 year.
         */
        R1Y("r1y"),
        /**
         * this day.
         */
        T1D("t1d"),
        /**
         * this week.
         */
        T1W("t1w"),
        /**
         * this month.
         */
        T1M("t1m"),
        /**
         * this year.
         */
        T1Y("t1y");

        TimestampRangeType(String val) {
            this.val = val;
        }
        private final String val;
        public String val() {
            return this.val;
        }
    }
}
