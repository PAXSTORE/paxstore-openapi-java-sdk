package com.pax.market.api.sdk.java.api.terminalEstate;

import com.pax.market.api.sdk.java.api.BaseThirdPartySysApi;
import com.pax.market.api.sdk.java.api.base.dto.EmptyResponse;
import com.pax.market.api.sdk.java.api.base.dto.Result;
import com.pax.market.api.sdk.java.api.base.request.SdkRequest;
import com.pax.market.api.sdk.java.api.client.ThirdPartySysApiClient;
import com.pax.market.api.sdk.java.api.util.EnhancedJsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Locale;

/**
 * @author liukai
 * @date 2019/10/15
 */
public class TerminalEstateApi extends BaseThirdPartySysApi {

    private static final Logger logger = LoggerFactory.getLogger(TerminalEstateApi.class.getSimpleName());

    protected static final String VERIFY_ESTATE_URL = "/v1/3rdsys/estates/verify/{serialNo}";

    public TerminalEstateApi(String baseUrl, String apiKey, String apiSecret) {
        super(baseUrl, apiKey, apiSecret);
    }

    public TerminalEstateApi(String baseUrl, String apiKey, String apiSecret, Locale locale) {
        super(baseUrl, apiKey, apiSecret, locale);
    }

    public Result<String> verifyTerminalEstate(String serialNo) {
        logger.debug("serialNo="+serialNo);
        List<String> validationErrs = validateStr(serialNo, "parameter.serialNo.invalid");
        if(validationErrs.size()>0) {
            return new Result<String>(validationErrs);
        }
        ThirdPartySysApiClient client = new ThirdPartySysApiClient(getBaseUrl(), getApiKey(), getApiSecret());
        SdkRequest request = createSdkRequest(VERIFY_ESTATE_URL.replace("{serialNo}", serialNo));

        request.setRequestMethod(SdkRequest.RequestMethod.GET);
        EmptyResponse emptyResponse =  EnhancedJsonUtils.fromJson(client.execute(request), EmptyResponse.class);
        Result<String> result = new Result<String>(emptyResponse);
        return result;
    }
}
