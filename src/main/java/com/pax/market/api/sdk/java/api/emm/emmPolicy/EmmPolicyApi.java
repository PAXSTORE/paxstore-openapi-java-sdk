package com.pax.market.api.sdk.java.api.emm.emmPolicy;

import com.google.gson.Gson;
import com.pax.market.api.sdk.java.api.BaseThirdPartySysApi;
import com.pax.market.api.sdk.java.api.base.dto.EmptyResponse;
import com.pax.market.api.sdk.java.api.base.dto.Result;
import com.pax.market.api.sdk.java.api.base.request.SdkRequest;
import com.pax.market.api.sdk.java.api.client.ThirdPartySysApiClient;
import com.pax.market.api.sdk.java.api.constant.Constants;
import com.pax.market.api.sdk.java.api.emm.emmPolicy.dto.EmmPolicyDTO;
import com.pax.market.api.sdk.java.api.emm.emmPolicy.dto.EmmPolicyResponse;
import com.pax.market.api.sdk.java.api.emm.emmPolicy.dto.MerchantEmmPolicyCreateRequest;
import com.pax.market.api.sdk.java.api.emm.emmPolicy.dto.ResellerEmmPolicyCreateRequest;
import com.pax.market.api.sdk.java.api.emm.emmPolicy.validator.MerchantEmmPolicyCreateRequestValidator;
import com.pax.market.api.sdk.java.api.emm.emmPolicy.validator.ResellerEmmPolicyCreateRequestValidator;
import com.pax.market.api.sdk.java.api.util.EnhancedJsonUtils;
import com.pax.market.api.sdk.java.api.validate.Validators;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Locale;

public class EmmPolicyApi extends BaseThirdPartySysApi {

    private static final Logger logger = LoggerFactory.getLogger(EmmPolicyApi.class);

    private static final String GET_RESELLER_EMM_POLICY_URL = "/v1/3rdsys/emm/policy/reseller";
    private static final String GET_MERCHANT_EMM_POLICY_URL = "/v1/3rdsys/emm/policy/merchant";
    private static final String CREATE_RESELLER_EMM_POLICY_URL = "/v1/3rdsys/emm/policy/reseller";
    private static final String CREATE_MERCHANT_EMM_POLICY_URL = "/v1/3rdsys/emm/policy/merchant";


    public EmmPolicyApi(String baseUrl, String apiKey, String apiSecret) {
        super(baseUrl, apiKey, apiSecret);
    }

    public EmmPolicyApi(String baseUrl, String apiKey, String apiSecret, Locale locale) {
        super(baseUrl, apiKey, apiSecret, locale);
    }


    public Result<EmmPolicyDTO> getResellerEmmPolicy(String resellerName) {
        logger.debug("resellerName= {}", resellerName);

        List<String> validationErrR = Validators.validateStrNullAndMax(resellerName, "resellerName", Constants.MAX_64);
        if (!validationErrR.isEmpty()) {
            return new Result<>(validationErrR);
        }

        ThirdPartySysApiClient client = new ThirdPartySysApiClient(getBaseUrl(), getApiKey(), getApiSecret());
        SdkRequest request = createSdkRequest(GET_RESELLER_EMM_POLICY_URL);

        request.setRequestMethod(SdkRequest.RequestMethod.GET);
        request.addHeader(Constants.CONTENT_TYPE, Constants.CONTENT_TYPE_JSON);
        request.addRequestParam("resellerName", resellerName);
        EmmPolicyResponse emmPolicyResponse = EnhancedJsonUtils.fromJson(client.execute(request), EmmPolicyResponse.class);
        return new Result<>(emmPolicyResponse);
    }


    public Result<EmmPolicyDTO> getMerchantEmmPolicy(String resellerName, String merchantName) {
        logger.debug("resellerName= {}, merchantName= {}", resellerName, merchantName);

        List<String> validationErrR = Validators.validateStrNullAndMax(resellerName, "resellerName", Constants.MAX_64);
        if (!validationErrR.isEmpty()) {
            return new Result<>(validationErrR);
        }

        List<String> validationErrM = Validators.validateStrNullAndMax(merchantName, "merchantName", Constants.MAX_64);
        if (!validationErrM.isEmpty()) {
            return new Result<>(validationErrM);
        }

        ThirdPartySysApiClient client = new ThirdPartySysApiClient(getBaseUrl(), getApiKey(), getApiSecret());
        SdkRequest request = createSdkRequest(GET_MERCHANT_EMM_POLICY_URL);

        request.setRequestMethod(SdkRequest.RequestMethod.GET);
        request.addHeader(Constants.CONTENT_TYPE, Constants.CONTENT_TYPE_JSON);
        request.addRequestParam("resellerName", resellerName);
        request.addRequestParam("merchantName", merchantName);
        EmmPolicyResponse emmPolicyResponse = EnhancedJsonUtils.fromJson(client.execute(request), EmmPolicyResponse.class);
        return new Result<>(emmPolicyResponse);
    }

    public Result<String> createResellerEmmPolicy(ResellerEmmPolicyCreateRequest resellerEmmPolicyCreateRequest) {

        List<String> validationErrs = ResellerEmmPolicyCreateRequestValidator.validate(resellerEmmPolicyCreateRequest);

        if (validationErrs.size() > 0) {
            return new Result<>(validationErrs);
        }

        logger.debug("resellerEmmPolicyCreateRequest= {}", new Gson().toJson(resellerEmmPolicyCreateRequest, ResellerEmmPolicyCreateRequest.class));


        ThirdPartySysApiClient client = new ThirdPartySysApiClient(getBaseUrl(), getApiKey(), getApiSecret());
        SdkRequest request = createSdkRequest(CREATE_RESELLER_EMM_POLICY_URL);
        request.setRequestMethod(SdkRequest.RequestMethod.POST);
        request.addHeader(Constants.CONTENT_TYPE, Constants.CONTENT_TYPE_JSON);
        request.setRequestBody(new Gson().toJson(resellerEmmPolicyCreateRequest, ResellerEmmPolicyCreateRequest.class));
        EmptyResponse emptyResponse = EnhancedJsonUtils.fromJson(client.execute(request), EmptyResponse.class);

        return new Result<>(emptyResponse);
    }

    public Result<String> createMerchantEmmPolicy(MerchantEmmPolicyCreateRequest merchantEmmPolicyCreateRequest) {
        List<String> validationErrs = MerchantEmmPolicyCreateRequestValidator.validate(merchantEmmPolicyCreateRequest);

        if (validationErrs.size() > 0) {
            return new Result<>(validationErrs);
        }

        logger.debug("merchantEmmPolicyCreateRequest= {}", new Gson().toJson(merchantEmmPolicyCreateRequest, MerchantEmmPolicyCreateRequest.class));

        ThirdPartySysApiClient client = new ThirdPartySysApiClient(getBaseUrl(), getApiKey(), getApiSecret());

        SdkRequest request = createSdkRequest(CREATE_MERCHANT_EMM_POLICY_URL);
        request.setRequestMethod(SdkRequest.RequestMethod.POST);
        request.addHeader(Constants.CONTENT_TYPE, Constants.CONTENT_TYPE_JSON);
        request.setRequestBody(new Gson().toJson(merchantEmmPolicyCreateRequest, MerchantEmmPolicyCreateRequest.class));
        EmptyResponse emptyResponse = EnhancedJsonUtils.fromJson(client.execute(request), EmptyResponse.class);

        return new Result<>(emptyResponse);
    }






}
