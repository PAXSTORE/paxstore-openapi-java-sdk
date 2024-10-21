package com.pax.market.api.sdk.java.api.goinsight;

import com.pax.market.api.sdk.java.api.BaseThirdPartySysApi;
import com.pax.market.api.sdk.java.api.base.dto.Result;
import com.pax.market.api.sdk.java.api.base.request.SdkRequest;
import com.pax.market.api.sdk.java.api.client.ThirdPartySysApiClient;
import com.pax.market.api.sdk.java.api.constant.Constants;
import com.pax.market.api.sdk.java.api.goinsight.dto.DataQueryRequest;
import com.pax.market.api.sdk.java.api.goinsight.dto.DataQueryResponse;
import com.pax.market.api.sdk.java.api.goinsight.dto.DataQueryResultDTO;
import com.pax.market.api.sdk.java.api.goinsight.dto.GoInsightCustomFilter;
import com.pax.market.api.sdk.java.api.util.EnhancedJsonUtils;
import com.pax.market.api.sdk.java.api.validate.Validators;
import com.google.gson.Gson;

import java.util.*;

/**
 * @author liukai
 * @date 2020/3/23
 */
public class GoInsightApi extends BaseThirdPartySysApi {
    private final static String SEARCH_GO_INSIGHT_DATA_URL = "/v1/3rdsys/goInsight/data/app-biz/{queryCode}";
    private final static int QUERY_CODE_LENGTH = 8;
    private final static int MAX_LENGTH = 1000;
    private final static int MAX_CUSTOM_FILTER_LENGTH = 100;

    public GoInsightApi(String baseUrl, String apiKey, String apiSecret) {
        super(baseUrl, apiKey, apiSecret);
    }

    @Deprecated
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
        return findDataFromInsight(queryCode, null, null,null, null);
    }

    public Result<DataQueryResultDTO> findDataFromInsight(String queryCode, TimestampRangeType rangeType){
        return findDataFromInsight(queryCode, rangeType, null,null, null);
    }

    public Result<DataQueryResultDTO> findDataFromInsight(String queryCode, TimestampRangeType rangeType, Integer pageNo, Integer pageSize){
        return findDataFromInsight(queryCode, rangeType, null,pageNo, pageSize);
    }

    public Result<DataQueryResultDTO> findDataFromInsight(String queryCode, TimestampRangeType rangeType,
                                                          List<GoInsightCustomFilter> customFilterList, Integer pageNo, Integer pageSize){
        List<String> validationErrs = Validators.validateStr(queryCode, "parameter.queryCode.invalid");
        if (queryCode != null && queryCode.length() != QUERY_CODE_LENGTH){
            validationErrs.add(getMessage("parameter.queryCode.length.invalid"));
        }
        if (pageSize != null && (pageSize <=0 || pageSize > MAX_LENGTH)){
            validationErrs.add(getMessage("insight.pageSize.length.invalid"));
        }
        if(Objects.nonNull(customFilterList)){
            for(GoInsightCustomFilter filter:customFilterList){
                String filterErr = validateCustomFilter(filter.getCloName(), filter.getFilterValue());
                if(filterErr != null){
                    validationErrs.add(filterErr);
                }
            }
        }
        if (!validationErrs.isEmpty()) {
            return new Result<>(validationErrs);
        }
        ThirdPartySysApiClient client = new ThirdPartySysApiClient(getBaseUrl(), getApiKey(), getApiSecret());
        SdkRequest request = createSdkRequest(SEARCH_GO_INSIGHT_DATA_URL.replace("{queryCode}", queryCode));
        request.setRequestMethod(SdkRequest.RequestMethod.POST);
        request.addHeader(Constants.CONTENT_TYPE, Constants.CONTENT_TYPE_JSON);
        DataQueryRequest queryRequest = new DataQueryRequest();
        if(rangeType != null){
            queryRequest.setTimeRangeType(rangeType.val);
        }
        if(Objects.nonNull(customFilterList)){
            queryRequest.setCustomFilterList(customFilterList);
        }
        if(pageNo != null && pageNo > 0 && pageSize != null){
            queryRequest.setPageNo(pageNo);
            queryRequest.setPageSize(pageSize);
        }
        request.setRequestBody(new Gson().toJson(queryRequest, DataQueryRequest.class));

        DataQueryResponse dataQueryResponse = EnhancedJsonUtils.fromJson(client.execute(request), DataQueryResponse.class);
        return new Result<>(dataQueryResponse);
    }

    private String validateCustomFilter(String colName, String filterValue) {
        if(filterValue != null){
            String[] filters = filterValue.split(",");
            if(filters.length > MAX_CUSTOM_FILTER_LENGTH){
                return String.format("The %s filter value size can not over %d", colName, MAX_CUSTOM_FILTER_LENGTH);
            }
        }
        return null;
    }

    /**
     * search GoInsight data，Timestamp RangeType
     */
    public enum TimestampRangeType {

        LAST_HOUR("p1h"),
        YESTERDAY("p1d"),
        LAST_WEEK("p1w"),
        LAST_MONTH("p1m"),
        LAST_QUARTER("p1q"),
        LAST_YEAR("p1y"),
        LAST_YEAR_BY_QUARTER("p1ybq"),

        RECENT_5_MIN("r5min"),
        RECENT_30_MIN("r30min"),
        RECENT_HOUR("r1h"),
        RECENT_3_HOUR("r3h"),
        RECENT_DAY("r1d"),
        RECENT_2_DAY("r2d"),
        RECENT_5_DAY("r5d"),
        RECENT_WEEK("r1w"),
        RECENT_MONTH("r1m"),
        RECENT_3_MONTH("r3m"),
        RECENT_3_MONTH_BY_WEEK("r3mbw"),
        RECENT_6_MONTH("r6m"),
        RECENT_YEAR("r1y"),
        RECENT_YEAR_BY_QUARTER("r1ybq"),

        THIS_HOUR("t1h"),
        TODAY("t1d"),
        THIS_WEEK("t1w"),
        THIS_MONTH("t1m"),
        THIS_QUARTER("t1q"),
        THIS_QUARTER_BY_WEEK("t1qbw"),
        THIS_YEAR("t1y"),
        THIS_YEAR_BY_QUARTER("t1ybq");

        TimestampRangeType(String val) {
            this.val = val;
        }
        private final String val;
        public String val() {
            return this.val;
        }
    }
}
