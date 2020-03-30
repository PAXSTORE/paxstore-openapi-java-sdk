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

import java.time.zone.ZoneRulesProvider;
import java.util.List;
import java.util.Locale;

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

    public Result<DataQueryResultDTO> findDataFromInsight(String queryCode){
        ZoneRulesProvider.getAvailableZoneIds();
        return findDataFromInsight(queryCode, 0, 0);
    }

    public Result<DataQueryResultDTO> findDataFromInsight(String queryCode, Integer pageNo, Integer pageSize){
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

        DataQueryResponse dataQueryResponse = EnhancedJsonUtils.fromJson(client.execute(request), DataQueryResponse.class);
        Result<DataQueryResultDTO> result = new Result<DataQueryResultDTO>(dataQueryResponse);
        return result;
    }

    public static void main(String[] args) {
        System.out.println(ZoneRulesProvider.getAvailableZoneIds());
    }
}
