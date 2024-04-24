package com.pax.market.api.sdk.java.api.terminalGeoFenceWhiteList;

import com.google.gson.Gson;
import com.pax.market.api.sdk.java.api.BaseThirdPartySysApi;
import com.pax.market.api.sdk.java.api.base.dto.EmptyResponse;
import com.pax.market.api.sdk.java.api.base.dto.PageRequestDTO;
import com.pax.market.api.sdk.java.api.base.dto.Result;
import com.pax.market.api.sdk.java.api.base.request.SdkRequest;
import com.pax.market.api.sdk.java.api.client.ThirdPartySysApiClient;
import com.pax.market.api.sdk.java.api.constant.Constants;
import com.pax.market.api.sdk.java.api.terminalGeoFenceWhiteList.dto.TerminalGeoFenceWhiteListDTO;
import com.pax.market.api.sdk.java.api.terminalGeoFenceWhiteList.dto.TerminalGeoFenceWhiteListPageResponse;
import com.pax.market.api.sdk.java.api.terminalGeoFenceWhiteList.dto.TerminalGeoFenceWhiteListRequest;
import com.pax.market.api.sdk.java.api.util.EnhancedJsonUtils;
import com.pax.market.api.sdk.java.api.validate.Validators;

import java.util.List;
import java.util.Locale;


public class TerminalGeoFenceWhiteListApi extends BaseThirdPartySysApi {

    private static final String SEARCH_TERMINAL_GEOFENCE_WHITELIST_URL = "/v1/3rdsys/terminal/geofence/whitelist";
    private static final String CREATE_TERMINAL_GEOFENCE_WHITELIST_URL = "/v1/3rdsys/terminal/geofence/whitelist";
    private static final String DELETE_TERMINAL_GEOFENCE_WHITELIST_URL = "/v1/3rdsys/terminal/geofence/whitelist";
    public TerminalGeoFenceWhiteListApi(String baseUrl, String apiKey, String apiSecret) {
        super(baseUrl, apiKey, apiSecret);
    }

    public TerminalGeoFenceWhiteListApi(String baseUrl, String apiKey, String apiSecret, Locale locale) {
        super(baseUrl, apiKey, apiSecret, locale);
    }


    public Result<TerminalGeoFenceWhiteListDTO> searchGeoFenceWhiteList(int pageNo, int pageSize, SearchOrderBy orderBy, String serialNo){
        ThirdPartySysApiClient client = new ThirdPartySysApiClient(getBaseUrl(), getApiKey(), getApiSecret());
        PageRequestDTO page = new PageRequestDTO();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        if (orderBy != null) {
            page.setOrderBy(orderBy.val());
        }
        List<String> validationErrs = Validators.validatePageRequest(page);
        if (!validationErrs.isEmpty()) {
            return new Result<TerminalGeoFenceWhiteListDTO>(validationErrs);
        }
        SdkRequest request = getPageRequest(SEARCH_TERMINAL_GEOFENCE_WHITELIST_URL, page);

        request.setRequestMethod(SdkRequest.RequestMethod.GET);
        if (serialNo !=null){
            request.addRequestParam("serialNo", serialNo);
        }

        TerminalGeoFenceWhiteListPageResponse response = EnhancedJsonUtils.fromJson(client.execute(request), TerminalGeoFenceWhiteListPageResponse.class);
        return new Result<TerminalGeoFenceWhiteListDTO>(response);
    }

    public Result<String> createGeoFenceWhiteList(TerminalGeoFenceWhiteListRequest createRequest){
        List<String> validationErrs = Validators.validateObject(createRequest, "createRequest");
        validationErrs.addAll(Validators.validateStr(createRequest.getSerialNo(), "parameter.not.null", "serialNo"));
        if(!validationErrs.isEmpty()) {
            return new Result<>(validationErrs);
        }
        ThirdPartySysApiClient client = new ThirdPartySysApiClient(getBaseUrl(), getApiKey(), getApiSecret());
        SdkRequest request = createSdkRequest(CREATE_TERMINAL_GEOFENCE_WHITELIST_URL);
        request.setRequestMethod(SdkRequest.RequestMethod.POST);
        request.addHeader(Constants.CONTENT_TYPE, Constants.CONTENT_TYPE_JSON);
        request.setRequestBody(new Gson().toJson(createRequest, TerminalGeoFenceWhiteListRequest.class));
        return emptyResult(client, request);
    }

    public Result<String> deleteGeoFenceWhiteList(TerminalGeoFenceWhiteListRequest deleteRequest) {

        List<String> validationErrs = Validators.validateObject(deleteRequest, "request");
         validationErrs.addAll(Validators.validateStr(deleteRequest.getSerialNo(), "parameter.not.null", "serialNo"));
        if(!validationErrs.isEmpty()) {
            return new Result<>(validationErrs);
        }


        ThirdPartySysApiClient client = new ThirdPartySysApiClient(getBaseUrl(), getApiKey(), getApiSecret());
        SdkRequest request = createSdkRequest(DELETE_TERMINAL_GEOFENCE_WHITELIST_URL);
        request.setRequestMethod(SdkRequest.RequestMethod.DELETE);
        request.addHeader(Constants.CONTENT_TYPE, Constants.CONTENT_TYPE_JSON);
        request.setRequestBody(new Gson().toJson(deleteRequest, TerminalGeoFenceWhiteListRequest.class));
        return emptyResult(client, request);
    }


    public enum SearchOrderBy {
        CreatedDate_desc("t.created_date DESC"),
        CreatedDate_asc("t.created_date ASC");
        private String val;
        private SearchOrderBy(String orderBy) {
            this.val = orderBy;
        }
        public String val(){
            return this.val;
        }
    }

    private Result<String> emptyResult(ThirdPartySysApiClient client, SdkRequest request) {
        EmptyResponse emptyResponse = EnhancedJsonUtils.fromJson(client.execute(request), EmptyResponse.class);
        return new Result<String>(emptyResponse);
    }

}
