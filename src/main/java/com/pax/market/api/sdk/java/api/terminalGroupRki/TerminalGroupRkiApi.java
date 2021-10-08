/*
 * *******************************************************************************
 * COPYRIGHT
 *               PAX TECHNOLOGY, Inc. PROPRIETARY INFORMATION
 *   This software is supplied under the terms of a license agreement or
 *   nondisclosure agreement with PAX  Technology, Inc. and may not be copied
 *   or disclosed except in accordance with the terms in that agreement.
 *
 *      Copyright (C) 2017 PAX Technology, Inc. All rights reserved.
 * *******************************************************************************
 */

package com.pax.market.api.sdk.java.api.terminalGroupRki;

import com.google.gson.Gson;
import com.pax.market.api.sdk.java.api.BaseThirdPartySysApi;
import com.pax.market.api.sdk.java.api.base.dto.PageRequestDTO;
import com.pax.market.api.sdk.java.api.base.dto.Response;
import com.pax.market.api.sdk.java.api.base.dto.Result;
import com.pax.market.api.sdk.java.api.base.request.SdkRequest;
import com.pax.market.api.sdk.java.api.client.ThirdPartySysApiClient;
import com.pax.market.api.sdk.java.api.constant.Constants;
import com.pax.market.api.sdk.java.api.terminalGroupRki.dto.CreateTerminalGroupRkiRequest;
import com.pax.market.api.sdk.java.api.terminalGroupRki.dto.TerminalGroupRkiDTO;
import com.pax.market.api.sdk.java.api.terminalGroupRki.dto.TerminalGroupRkiPageResponse;
import com.pax.market.api.sdk.java.api.terminalGroupRki.dto.TerminalGroupRkiResponse;
import com.pax.market.api.sdk.java.api.util.EnhancedJsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

/**
 * @author shifan
 * @date 2021/9/24
 */
public class TerminalGroupRkiApi extends BaseThirdPartySysApi {

    private static final Logger logger = LoggerFactory.getLogger(TerminalGroupRkiApi.class);


    public TerminalGroupRkiApi(String baseUrl, String apiKey, String apiSecret) {
        super(baseUrl, apiKey, apiSecret);
    }

    public TerminalGroupRkiApi(String baseUrl, String apiKey, String apiSecret, TimeZone timeZone) {
        super(baseUrl, apiKey, apiSecret, timeZone);
    }

    private static final String GET_TERMINAL_GROUP_RKI_URL = "/v1/3rdsys/terminalGroupRki/{groupRkiId}";
    private static final String SEARCH_TERMINAL_GROUP_RKI_URL = "/v1/3rdsys/terminalGroupRki";
    private static final String CREATE_TERMINAL_GROUP_RKI_URL = "/v1/3rdsys/terminalGroupRki";
    private static final String SUSPEND_TERMINAL_GROUP_RKI_URL = "/v1/3rdsys/terminalGroupRki/{groupRkiId}/suspend";

    public Result<TerminalGroupRkiDTO> searchGroupPushRkiTask(int pageNo, int pageSize, SearchOrderBy orderBy , Long groupId, Boolean pendingOnly, Boolean historyOnly, String keyWords){
        logger.debug("groupId="+groupId);
        List<String> validationErrId = validateId(groupId, "parameter.terminalGroupId.invalid");
        ThirdPartySysApiClient client = new ThirdPartySysApiClient(getBaseUrl(), getApiKey(), getApiSecret());
        PageRequestDTO page = new PageRequestDTO();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        if (orderBy != null) {
            page.setOrderBy(orderBy.val());
        }
        List<String> validationErrs = validate(page);
        validationErrs.addAll(validationErrId);
        if (validationErrs.size() > 0) {
            return new Result<TerminalGroupRkiDTO>(validationErrs);
        }

        SdkRequest request = getPageRequest(SEARCH_TERMINAL_GROUP_RKI_URL, page);
        request.setRequestMethod(SdkRequest.RequestMethod.GET);
        if (groupId !=null){
            request.addRequestParam("groupId", groupId.toString());
        }
        if(pendingOnly!=null) {
            request.addRequestParam("pendingOnly", pendingOnly.toString());
        }
        if(historyOnly!=null) {
            request.addRequestParam("historyOnly", historyOnly.toString());
        }
        if(keyWords!=null) {
            request.addRequestParam("keyWords", keyWords);
        }

        TerminalGroupRkiPageResponse resp = EnhancedJsonUtils.fromJson(client.execute(request), TerminalGroupRkiPageResponse.class);
        return new Result<TerminalGroupRkiDTO>(resp);
    }


    public Result<TerminalGroupRkiDTO> getGroupPushRkiTask(Long groupPushRkiTaskId){
        logger.debug("groupPushRkiTaskId="+groupPushRkiTaskId);
        List<String> validationErrs = validateId(groupPushRkiTaskId, "parameter.groupPushRkiTaskId.invalid");
        if(validationErrs.size()>0) {
            return new Result<TerminalGroupRkiDTO>(validationErrs);
        }
        ThirdPartySysApiClient client = new ThirdPartySysApiClient(getBaseUrl(), getApiKey(), getApiSecret());
        SdkRequest request = createSdkRequest(GET_TERMINAL_GROUP_RKI_URL.replace("{groupRkiId}", groupPushRkiTaskId+""));
        request.setRequestMethod(SdkRequest.RequestMethod.GET);
        TerminalGroupRkiResponse resp = EnhancedJsonUtils.fromJson(client.execute(request), TerminalGroupRkiResponse.class);
        return new Result<TerminalGroupRkiDTO>(resp);
    }

    public Result<TerminalGroupRkiDTO> pushRkiKey2Group(CreateTerminalGroupRkiRequest createRequest){
        List<String> validationErrs = validateCreateGroupTerminalRki(createRequest);

        if(validationErrs.size()>0) {
            return new Result<TerminalGroupRkiDTO>(validationErrs);
        }
        ThirdPartySysApiClient client = new ThirdPartySysApiClient(getBaseUrl(), getApiKey(), getApiSecret());
        SdkRequest request = createSdkRequest(CREATE_TERMINAL_GROUP_RKI_URL);
        request.setRequestMethod(SdkRequest.RequestMethod.POST);
        request.addHeader(Constants.CONTENT_TYPE, Constants.CONTENT_TYPE_JSON);
        request.setRequestBody(new Gson().toJson(createRequest, CreateTerminalGroupRkiRequest.class));
        Response response = EnhancedJsonUtils.fromJson(client.execute(request), Response.class);
        Result<TerminalGroupRkiDTO> result = new Result<TerminalGroupRkiDTO>(response);
        return result;
    }

    private List<String> validateCreateGroupTerminalRki(CreateTerminalGroupRkiRequest request) {
        List<String> validationErrs = new ArrayList<String>();
        if(request == null) {
            validationErrs.add(getMessage("parameter.groupPushRkiRequest.null"));
        }else {
            validationErrs.addAll(validate(request));
            if(request.getGroupId() == null || request.getGroupId() < 0L) {
                validationErrs.add(getMessage("parameter.terminalGroupId.invalid"));
            }
        }
        return validationErrs;
    }


    public Result<TerminalGroupRkiDTO> disableGroupRkiPushTask(Long groupPushRkiTaskId){
        logger.debug("groupPushRkiTaskId="+groupPushRkiTaskId);
        List<String> validationErrs = validateId(groupPushRkiTaskId, "parameter.groupPushRkiTaskId.invalid");
        if(validationErrs.size()>0) {
            return new Result<TerminalGroupRkiDTO>(validationErrs);
        }
        ThirdPartySysApiClient client = new ThirdPartySysApiClient(getBaseUrl(), getApiKey(), getApiSecret());
        SdkRequest request = createSdkRequest(SUSPEND_TERMINAL_GROUP_RKI_URL.replace("{groupRkiId}", groupPushRkiTaskId+""));
        request.setRequestMethod(SdkRequest.RequestMethod.POST);
        request.addHeader(Constants.CONTENT_TYPE, Constants.CONTENT_TYPE_JSON);
        TerminalGroupRkiResponse resp = EnhancedJsonUtils.fromJson(client.execute(request), TerminalGroupRkiResponse.class);
        return new Result<TerminalGroupRkiDTO>(resp);
    }

}
