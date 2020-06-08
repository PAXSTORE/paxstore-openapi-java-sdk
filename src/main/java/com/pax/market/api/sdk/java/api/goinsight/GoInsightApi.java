package com.pax.market.api.sdk.java.api.goinsight;

import com.pax.market.api.sdk.java.api.BaseThirdPartySysApi;
import com.pax.market.api.sdk.java.api.base.dto.Result;
import com.pax.market.api.sdk.java.api.base.request.SdkRequest;
import com.pax.market.api.sdk.java.api.client.ThirdPartySysApiClient;
import com.pax.market.api.sdk.java.api.goinsight.dto.DataQueryResponse;
import com.pax.market.api.sdk.java.api.goinsight.dto.DataQueryResultDTO;
import com.pax.market.api.sdk.java.api.util.EnhancedJsonUtils;

import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

/**
 * @author liukai
 * @date 2020/3/23
 */
public class GoInsightApi extends BaseThirdPartySysApi {
    private final static String SEARCH_GO_INSIGHT_DATA_URL = "/v1/3rdsys/goInsight/data/app-biz";
    private final static int QUERY_CODE_LENGTH = 8;

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
        return findDataFromInsight(queryCode, null,null, null);
    }

    public Result<DataQueryResultDTO> findDataFromInsight(String queryCode, TimestampRangeType rangeType){
        return findDataFromInsight(queryCode, rangeType,null, null);
    }

    public Result<DataQueryResultDTO> findDataFromInsight(String queryCode, TimestampRangeType rangeType, Integer pageNo, Integer pageSize){
        List<String> validationErrs = validateStr(queryCode, "parameter.queryCode.invalid");
        if (queryCode != null && queryCode.length() != QUERY_CODE_LENGTH){
            validationErrs.add(getMessage("parameter.queryCode.length.invalid"));
        }
        if (pageSize != null && (pageSize <=0 || pageSize > 1000)){
            validationErrs.add(getMessage("parameter.pageSize.length.invalid"));
        }
        if (validationErrs.size() > 0) {
            return new Result<DataQueryResultDTO>(validationErrs);
        }
        ThirdPartySysApiClient client = new ThirdPartySysApiClient(getBaseUrl(), getApiKey(), getApiSecret());
        SdkRequest request = createSdkRequest(SEARCH_GO_INSIGHT_DATA_URL.replace("{queryCode}", queryCode));
        request.addRequestParam("queryCode", queryCode);
        if(pageNo != null && pageNo > 0 && pageSize != null && pageSize > 0){
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
        YESTERDAY("p1d"),
        /**
         * pass 1 week.
         */
        LAST_WEEK("p1w"),
        /**
         * pass 1 month.
         */
        LAST_MONTH("p1m"),
        /**
         * pass 1 year.
         */
        LAST_YEAR("p1y"),
        /**
         * recent 1 day.
         */
        RECENT_DAY("r1d"),
        /**
         * recent 1 week.
         */
        RECENT_WEEK("r1w"),
        /**
         * recent 1 month.
         */
        RECENT_MONTH("r1m"),
        /**
         * recent 1 year.
         */
        RECENT_YEAR("r1y"),
        /**
         * this day.
         */
        TODAY("t1d"),
        /**
         * this week.
         */
        THIS_WEEK("t1w"),
        /**
         * this month.
         */
        THIS_MONTH("t1m"),
        /**
         * this year.
         */
        THIS_YEAR("t1y");

        TimestampRangeType(String val) {
            this.val = val;
        }
        private final String val;
        public String val() {
            return this.val;
        }
    }
}
