package com.pax.market.api.sdk.java.api.emm.emmApp;

import com.google.gson.Gson;
import com.pax.market.api.sdk.java.api.BaseThirdPartySysApi;
import com.pax.market.api.sdk.java.api.base.dto.EmptyResponse;
import com.pax.market.api.sdk.java.api.base.dto.PageRequestDTO;
import com.pax.market.api.sdk.java.api.base.dto.Result;
import com.pax.market.api.sdk.java.api.base.request.SdkRequest;
import com.pax.market.api.sdk.java.api.client.ThirdPartySysApiClient;
import com.pax.market.api.sdk.java.api.constant.Constants;
import com.pax.market.api.sdk.java.api.emm.emmApp.dto.*;
import com.pax.market.api.sdk.java.api.emm.emmApp.validator.EmmAppCreateRequestValidator;
import com.pax.market.api.sdk.java.api.util.EnhancedJsonUtils;
import com.pax.market.api.sdk.java.api.validate.Validators;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Locale;

public class EmmAppApi extends BaseThirdPartySysApi {

    private static final Logger logger = LoggerFactory.getLogger(EmmAppApi.class);

    private static final String EMM_APP_URL = "/v1/3rdsys/emm/apps";
    private static final String GET_EMM_APP_DETAIL_URL = "/v1/3rdsys/emm/apps/{appId}";
    private static final String DELETE_EMM_APP_URL = "/v1/3rdsys/emm/apps/{appId}";
    private static final String SEARCH_RESELLER_SUBSCRIBE_EMM_APP_URL = "/v1/3rdsys/emm/apps/subscription";
    private static final String RESELLER_SUBSCRIBE_EMM_APP_URL = "/v1/3rdsys/emm/apps/{appId}/subscribe";
    private static final String RESELLER_UNSUBSCRIBE_EMM_APP_URL = "/v1/3rdsys/emm/apps/{appId}/unsubscribe";
    private static final String GET_PERMISSION_LIST_FOR_EMM_APP_URL = "/v1/3rdsys/emm/apps/{appId}/permissions";
    private static final String GET_AVAILABLE_TEST_TRACK_VERSION_LIST_APP_URL = "/v1/3rdsys/emm/apps/{appId}/available/test/versions";

    public EmmAppApi(String baseUrl, String apiKey, String apiSecret) {
        super(baseUrl, apiKey, apiSecret);
    }

    public EmmAppApi(String baseUrl, String apiKey, String apiSecret, Locale locale) {
        super(baseUrl, apiKey, apiSecret, locale);
    }

    public Result<EmmAppDTO> searchEmmApp(int pageNo,
                                          int pageSize,
                                          EmmAppSearchOrderBy orderBy,
                                          String resellerName,
                                          String keyWords,
                                          EmmAppType type) {
        List<String> validationErrsR = Validators.validateStrNullAndMax(resellerName, "resellerName", Constants.MAX_64);
        if (!validationErrsR.isEmpty()) {
            return new Result<>(validationErrsR);
        }

        ThirdPartySysApiClient client = new ThirdPartySysApiClient(getBaseUrl(), getApiKey(), getApiSecret());
        PageRequestDTO page = new PageRequestDTO();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        if (orderBy != null) {
            page.setOrderBy(orderBy.val);
        }
        List<String> validationErrs = Validators.validatePageRequest(page);
        if (!validationErrs.isEmpty()) {
            return new Result<>(validationErrs);
        }

        SdkRequest request = getPageRequest(EMM_APP_URL, page);

        request.addRequestParam("resellerName", resellerName);
        if (keyWords != null) {
            request.addRequestParam("keyWords", keyWords);
        }
        if (type != null) {
            request.addRequestParam("appType", String.valueOf(type.val));
        }

        EmmAppPageResponse emmAppPageResponse = EnhancedJsonUtils.fromJson(client.execute(request), EmmAppPageResponse.class);

        return new Result<>(emmAppPageResponse);
    }


    public Result<EmmAppDTO> createEmmApp(EmmAppCreateRequest emmAppCreateRequest) {
        List<String> validationErrs = EmmAppCreateRequestValidator.validate(emmAppCreateRequest);
        if (!validationErrs.isEmpty()) {
            return new Result<>(validationErrs);
        }

        logger.debug("emmAppCreateRequest= {}", new Gson().toJson(emmAppCreateRequest, EmmAppCreateRequest.class));

        ThirdPartySysApiClient client = new ThirdPartySysApiClient(getBaseUrl(), getApiKey(), getApiSecret());
        SdkRequest request = createSdkRequest(EMM_APP_URL);
        request.setRequestMethod(SdkRequest.RequestMethod.POST);
        request.addHeader(Constants.CONTENT_TYPE, Constants.CONTENT_TYPE_JSON);
        request.setRequestBody(new Gson().toJson(emmAppCreateRequest, EmmAppCreateRequest.class));
        EmmAppResponse emmAppResponse = EnhancedJsonUtils.fromJson(client.execute(request), EmmAppResponse.class);
        return new Result<>(emmAppResponse);
    }


    public Result<EmmAppDetailDTO> getEmmAppDetail(Long appId) {
        logger.debug("appId= {}", appId);

        List<String> validationErrs = Validators.validateId(appId, "parameter.id.invalid", "appId");
        if (!validationErrs.isEmpty()) {
            return new Result<>(validationErrs);
        }

        ThirdPartySysApiClient client = new ThirdPartySysApiClient(getBaseUrl(), getApiKey(), getApiSecret());
        SdkRequest request = createSdkRequest(GET_EMM_APP_DETAIL_URL.replace("{appId}", String.valueOf(appId)));
        request.setRequestMethod(SdkRequest.RequestMethod.GET);
        request.addHeader(Constants.CONTENT_TYPE, Constants.CONTENT_TYPE_JSON);
        EmmAppDetailResponse emmAppDetailResponse = EnhancedJsonUtils.fromJson(client.execute(request), EmmAppDetailResponse.class);
        return new Result<>(emmAppDetailResponse);
    }

    public Result<String> removeEmmApp(Long appId, String resellerName) {
        logger.debug("appId= {}, resellerName= {}", appId, resellerName);

        List<String> validationErrsI = Validators.validateId(appId, "parameter.id.invalid", "appId");
        if (!validationErrsI.isEmpty()) {
            return new Result<>(validationErrsI);
        }

        List<String> validationErrR = Validators.validateStrNullAndMax(resellerName, "resellerName", Constants.MAX_64);
        if (!validationErrR.isEmpty()) {
            return new Result<>(validationErrR);
        }

        ThirdPartySysApiClient client = new ThirdPartySysApiClient(getBaseUrl(), getApiKey(), getApiSecret());
        SdkRequest request = createSdkRequest(DELETE_EMM_APP_URL.replace("{appId}", String.valueOf(appId)));
        request.setRequestMethod(SdkRequest.RequestMethod.DELETE);
        request.addRequestParam("resellerName", resellerName);
        EmptyResponse emptyResponse = EnhancedJsonUtils.fromJson(client.execute(request), EmptyResponse.class);
        return new Result<>(emptyResponse);
    }

    public Result<SubscribeEmmAppDTO> searchSubscribeEmmApp(int pageNo,
                                                            int pageSize,
                                                            String name,
                                                            Boolean isSubscribed) {
        logger.debug("name={}, isSubscribed={}", name, isSubscribed);

        List<String> validationErrI = Validators.validateObject(isSubscribed, "isSubscribed");
        if (!validationErrI.isEmpty()) {
            return new Result<>(validationErrI);
        }

        ThirdPartySysApiClient client = new ThirdPartySysApiClient(getBaseUrl(), getApiKey(), getApiSecret());
        PageRequestDTO page = new PageRequestDTO();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);

        List<String> validationErrs = Validators.validatePageRequest(page);
        if (!validationErrs.isEmpty()) {
            return new Result<>(validationErrs);
        }

        SdkRequest request = getPageRequest(SEARCH_RESELLER_SUBSCRIBE_EMM_APP_URL, page);

        request.addRequestParam("isSubscribed", String.valueOf(isSubscribed));
        if (name != null) {
            request.addRequestParam("name", name);
        }
        SubscribeEmmAppPageResponse subscribeEmmAppPageResponse = EnhancedJsonUtils.fromJson(client.execute(request), SubscribeEmmAppPageResponse.class);
        return new Result<>(subscribeEmmAppPageResponse);
    }

    public Result<String> subscribeEmmApp(Long appId) {
        logger.debug("appId= {}", appId);

        List<String> validationErrsI = Validators.validateId(appId, "parameter.id.invalid", "appId");
        if (!validationErrsI.isEmpty()) {
            return new Result<>(validationErrsI);
        }

        ThirdPartySysApiClient client = new ThirdPartySysApiClient(getBaseUrl(), getApiKey(), getApiSecret());

        SdkRequest request = createSdkRequest(RESELLER_SUBSCRIBE_EMM_APP_URL.replace("{appId}", String.valueOf(appId)));
        request.setRequestMethod(SdkRequest.RequestMethod.PUT);
        request.addHeader(Constants.CONTENT_TYPE, Constants.CONTENT_TYPE_JSON);

        EmptyResponse emptyResponse = EnhancedJsonUtils.fromJson(client.execute(request), EmptyResponse.class);
        return new Result<>(emptyResponse);
    }

    public Result<String> unSubscribeEmmApp(Long appId) {
        logger.debug("appId= {}", appId);

        List<String> validationErrsI = Validators.validateId(appId, "parameter.id.invalid", "appId");
        if (!validationErrsI.isEmpty()) {
            return new Result<>(validationErrsI);
        }

        ThirdPartySysApiClient client = new ThirdPartySysApiClient(getBaseUrl(), getApiKey(), getApiSecret());

        SdkRequest request = createSdkRequest(RESELLER_UNSUBSCRIBE_EMM_APP_URL.replace("{appId}", String.valueOf(appId)));
        request.setRequestMethod(SdkRequest.RequestMethod.PUT);
        request.addHeader(Constants.CONTENT_TYPE, Constants.CONTENT_TYPE_JSON);

        EmptyResponse emptyResponse = EnhancedJsonUtils.fromJson(client.execute(request), EmptyResponse.class);
        return new Result<>(emptyResponse);
    }


    public Result<EmmAppPermissionDTO> getEmmAppPermissionList(Long appId) {
        logger.debug("appId= {}", appId);

        List<String> validationErrs = Validators.validateId(appId, "parameter.id.invalid", "appId");
        if (!validationErrs.isEmpty()) {
            return new Result<>(validationErrs);
        }

        ThirdPartySysApiClient client = new ThirdPartySysApiClient(getBaseUrl(), getApiKey(), getApiSecret());
        SdkRequest request = createSdkRequest(GET_PERMISSION_LIST_FOR_EMM_APP_URL.replace("{appId}", String.valueOf(appId)));
        request.setRequestMethod(SdkRequest.RequestMethod.GET);
        request.addHeader(Constants.CONTENT_TYPE, Constants.CONTENT_TYPE_JSON);
        EmmAppPermissionResponse emmAppPermissionResponse = EnhancedJsonUtils.fromJson(client.execute(request), EmmAppPermissionResponse.class);
        return new Result<>(emmAppPermissionResponse);
    }

    public Result<EmmAppAvailableTestVersionDTO> getAvailableTestTrackVersionList(Long appId) {
        logger.debug("appId= {}", appId);

        List<String> validationErrs = Validators.validateId(appId, "parameter.id.invalid", "appId");
        if (!validationErrs.isEmpty()) {
            return new Result<>(validationErrs);
        }

        ThirdPartySysApiClient client = new ThirdPartySysApiClient(getBaseUrl(), getApiKey(), getApiSecret());
        SdkRequest request = createSdkRequest(GET_AVAILABLE_TEST_TRACK_VERSION_LIST_APP_URL.replace("{appId}", String.valueOf(appId)));
        request.setRequestMethod(SdkRequest.RequestMethod.GET);
        request.addHeader(Constants.CONTENT_TYPE, Constants.CONTENT_TYPE_JSON);
        EmmAppAvailableTestVersionResponse emmAppAvailableTestVersionResponse = EnhancedJsonUtils.fromJson(client.execute(request), EmmAppAvailableTestVersionResponse.class);
        return new Result<>(emmAppAvailableTestVersionResponse);
    }



    public enum EmmAppSearchOrderBy {
        EmmAppName_desc("a.name DESC"),
        EmmAppName_asc("a.name ASC"),
        EmmUpdatedDate_desc("a.updated_date DESC"),
        EmmUpdatedDate_asc("a.updated_date ASC");
        private String val;
        private EmmAppSearchOrderBy(String orderBy) {
            this.val = orderBy;
        }
        public String val() {
            return this.val;
        }
    }

    public enum EmmAppType {
        GOOGLE("G"),
        PRIVATE("P");
        private String val;
        private EmmAppType(String sign) {
            this.val = sign;
        }
        public String val() {
            return this.val;
        }
    }

}
