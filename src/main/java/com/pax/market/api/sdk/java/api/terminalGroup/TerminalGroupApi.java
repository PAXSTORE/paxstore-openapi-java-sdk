/*
 * ********************************************************************************
 * COPYRIGHT
 *               PAX TECHNOLOGY, Inc. PROPRIETARY INFORMATION
 *   This software is supplied under the terms of a license agreement or
 *   nondisclosure agreement with PAX  Technology, Inc. and may not be copied
 *   or disclosed except in accordance with the terms in that agreement.
 *
 *      Copyright (C) 2017 PAX Technology, Inc. All rights reserved.
 * ********************************************************************************
 */
package com.pax.market.api.sdk.java.api.terminalGroup;

import com.google.gson.Gson;
import com.pax.market.api.sdk.java.api.BaseThirdPartySysApi;
import com.pax.market.api.sdk.java.api.base.dto.EmptyResponse;
import com.pax.market.api.sdk.java.api.base.dto.PageRequestDTO;
import com.pax.market.api.sdk.java.api.base.dto.Result;
import com.pax.market.api.sdk.java.api.base.request.SdkRequest;
import com.pax.market.api.sdk.java.api.client.ThirdPartySysApiClient;
import com.pax.market.api.sdk.java.api.constant.Constants;
import com.pax.market.api.sdk.java.api.terminal.TerminalApi;
import com.pax.market.api.sdk.java.api.terminal.dto.TerminalDTO;
import com.pax.market.api.sdk.java.api.terminal.dto.TerminalPageResponse;
import com.pax.market.api.sdk.java.api.terminalGroup.dto.*;
import com.pax.market.api.sdk.java.api.util.EnhancedJsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Locale;

/**
 * @Description
 * @Author: Shawn
 * @Date: 2019/11/26 9:36
 * @Version 7.1
 */
public class TerminalGroupApi extends BaseThirdPartySysApi {
    private static final Logger logger = LoggerFactory.getLogger(TerminalGroupApi.class);

    private static final String GET_TERMINAL_GROUP_URL = "/v1/3rdsys/terminalGroups/{groupId}";
    private static final String SEARCH_TERMINAL_GROUP_URL = "/v1/3rdsys/terminalGroups";
    private static final String CREATE_TERMINAL_GROUP_URL = "/v1/3rdsys/terminalGroups";
    private static final String SEARCH_TERMINAL_URL = "/v1/3rdsys/terminalGroups/terminal";
    private static final String UPDATE_TERMINAL_GROUP_URL = "/v1/3rdsys/terminalGroups/{groupId}";
    private static final String ACTIVE_TERMINAL_GROUP_URL = "/v1/3rdsys/terminalGroups/{groupId}/active";
    private static final String DISABLE_TERMINAL_GROUP_URL = "/v1/3rdsys/terminalGroups/{groupId}/disable";
    private static final String DELETE_TERMINAL_GROUP_URL = "/v1/3rdsys/terminalGroups/{groupId}";
    private static final String SEARCH_TERMINAL_IN_GROUP_URL = "/v1/3rdsys/terminalGroups/{groupId}/terminals";
    private static final String ADD_TERMINAL_IN_GROUP_URL = "/v1/3rdsys/terminalGroups/{groupId}/terminals";
    private static final String REMOVE_TERMINAL_OUT_GROUP_URL = "/v1/3rdsys/terminalGroups/{groupId}/terminals";



    public TerminalGroupApi(String baseUrl, String apiKey, String apiSecret) {
        super(baseUrl, apiKey, apiSecret);
    }

    public TerminalGroupApi(String baseUrl, String apiKey, String apiSecret, Locale locale) {
        super(baseUrl, apiKey, apiSecret, locale);
    }

    public Result<TerminalGroupDTO> searchTerminalGroup(int pageNo, int pageSize,TerminalGroupSearchOrderBy orderBy ,
                                                        String status, String modelIds, String resellerIds, String name, String includePushTasks, String isDynamic){

        ThirdPartySysApiClient client = new ThirdPartySysApiClient(getBaseUrl(), getApiKey(), getApiSecret());
        PageRequestDTO page = new PageRequestDTO();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        if (orderBy != null) {
            page.setOrderBy(orderBy.val());
        }
        List<String> validationErrs = validate(page);
        if (validationErrs.size() > 0) {
            return new Result<TerminalGroupDTO>(validationErrs);
        }
        SdkRequest request = getPageRequest(SEARCH_TERMINAL_GROUP_URL, page);

        request.setRequestMethod(SdkRequest.RequestMethod.GET);
        if (status !=null){
            request.addRequestParam("status", status);
        }
        if(modelIds!=null) {
            request.addRequestParam("modelIds", modelIds);
        }
        if(resellerIds!=null) {
            request.addRequestParam("resellerIds", resellerIds);
        }
        if(name!=null) {
            request.addRequestParam("name", name);
        }
        if(includePushTasks!=null) {
            request.addRequestParam("includePushTasks", includePushTasks);
        }
        if(isDynamic!=null) {
            request.addRequestParam("isDynamic", isDynamic);
        }
        TerminalGroupPageResponse terminalGroupPageResponse = EnhancedJsonUtils.fromJson(client.execute(request), TerminalGroupPageResponse.class);
        Result<TerminalGroupDTO> result = new Result<TerminalGroupDTO>(terminalGroupPageResponse);
        return result;


    }

    public Result<TerminalGroupDTO> getTerminalGroup(Long groupId){
        validateTerminalGroupId(groupId);
        ThirdPartySysApiClient client = new ThirdPartySysApiClient(getBaseUrl(), getApiKey(), getApiSecret());
        SdkRequest request = createSdkRequest(GET_TERMINAL_GROUP_URL.replace("{groupId}", groupId.toString()+""));
        request.setRequestMethod(SdkRequest.RequestMethod.GET);
        TerminalGroupResponse resp = EnhancedJsonUtils.fromJson(client.execute(request), TerminalGroupResponse.class);
        Result<TerminalGroupDTO> result = new Result<TerminalGroupDTO>(resp);
        return result;
    }

    public Result<TerminalGroupDTO> createTerminalGroup(CreateTerminalGroupRequest createRequest){
        List<String> validationErrs = validateCreate( createRequest,"parameter.terminalGroupCreateRequest.null");
        if(validationErrs.size()>0) {
            return new Result<TerminalGroupDTO>(validationErrs);
        }

        ThirdPartySysApiClient client = new ThirdPartySysApiClient(getBaseUrl(), getApiKey(), getApiSecret());
        SdkRequest request = createSdkRequest(CREATE_TERMINAL_GROUP_URL);
        request.setRequestMethod(SdkRequest.RequestMethod.POST);
        request.addHeader(Constants.CONTENT_TYPE, Constants.CONTENT_TYPE_JSON);
        request.setRequestBody(new Gson().toJson(createRequest, CreateTerminalGroupRequest.class));
        TerminalGroupResponse terminalGroupResponse = EnhancedJsonUtils.fromJson(client.execute(request), TerminalGroupResponse.class);
        Result<TerminalGroupDTO> result = new Result<TerminalGroupDTO>(terminalGroupResponse);
        return result;
    }

    public  Result<TerminalDTO> searchTerminal(int pageNo, int pageSize, TerminalApi.TerminalSearchOrderBy orderBy,String status,
                                             String modelId, String resellerId, String merchantId, String serialNo, String excludeGroupId){

        ThirdPartySysApiClient client = new ThirdPartySysApiClient(getBaseUrl(), getApiKey(), getApiSecret());
        PageRequestDTO page = new PageRequestDTO();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        if (orderBy != null) {
            page.setOrderBy(orderBy.val());
        }
        List<String> validationErrs = validate(page);
        if (validationErrs.size() > 0) {
            return new Result<TerminalDTO>(validationErrs);
        }
        SdkRequest request = getPageRequest(SEARCH_TERMINAL_URL, page);

        request.setRequestMethod(SdkRequest.RequestMethod.GET);
        if (status !=null){
            request.addRequestParam("status", status);
        }
        if(modelId!=null) {
            request.addRequestParam("modelId", modelId);
        }
        if(resellerId!=null) {
            request.addRequestParam("resellerId", resellerId);
        }
        if(merchantId!=null) {
            request.addRequestParam("merchantId", merchantId);
        }
        if(serialNo!=null) {
            request.addRequestParam("serialNo", serialNo);
        }
        if(excludeGroupId!=null) {
            request.addRequestParam("excludeGroupId", excludeGroupId);
        }
        TerminalPageResponse terminalPageResponse = EnhancedJsonUtils.fromJson(client.execute(request), TerminalPageResponse.class);
        Result<TerminalDTO> result = new Result<TerminalDTO>(terminalPageResponse);
        return result;
    }


    public Result<TerminalGroupDTO> updateTerminalGroup(Long groupId ,UpdateTerminalGroupRequest updateRequest){
        List<String> validationErrs = validateUpdate( groupId,updateRequest,"parameter.terminalGroupId.invalid","parameter.terminalGroupUpdateRequest.null");
        if(validationErrs.size()>0) {
            return new Result<TerminalGroupDTO>(validationErrs);
        }

        ThirdPartySysApiClient client = new ThirdPartySysApiClient(getBaseUrl(), getApiKey(), getApiSecret());

        SdkRequest request = createSdkRequest(UPDATE_TERMINAL_GROUP_URL.replace("{groupId}", groupId.toString()+""));
        request.setRequestMethod(SdkRequest.RequestMethod.PUT);
        request.addHeader(Constants.CONTENT_TYPE, Constants.CONTENT_TYPE_JSON);
        request.setRequestBody(new Gson().toJson(updateRequest, UpdateTerminalGroupRequest.class));
        TerminalGroupResponse terminalGroupResponse = EnhancedJsonUtils.fromJson(client.execute(request), TerminalGroupResponse.class);
        Result<TerminalGroupDTO> result = new Result<TerminalGroupDTO>(terminalGroupResponse);
        return result;
    }

    public Result<String> activeGroup(Long groupId){
        validateGroupId(groupId);
        ThirdPartySysApiClient client = new ThirdPartySysApiClient(getBaseUrl(), getApiKey(), getApiSecret());
        SdkRequest request = createSdkRequest(ACTIVE_TERMINAL_GROUP_URL.replace("{groupId}", groupId.toString()));
        request.setRequestMethod(SdkRequest.RequestMethod.POST);
        return emptyResult(client,request);
    }

    public Result<String> disableGroup(Long groupId){
        validateGroupId(groupId);
        ThirdPartySysApiClient client = new ThirdPartySysApiClient(getBaseUrl(), getApiKey(), getApiSecret());
        SdkRequest request = createSdkRequest(DISABLE_TERMINAL_GROUP_URL.replace("{groupId}", groupId.toString()));
        request.setRequestMethod(SdkRequest.RequestMethod.POST);
        return emptyResult(client,request);
    }

    public Result<String> deleteGroup(Long groupId){
        validateGroupId(groupId);
        ThirdPartySysApiClient client = new ThirdPartySysApiClient(getBaseUrl(), getApiKey(), getApiSecret());
        SdkRequest request = createSdkRequest(DELETE_TERMINAL_GROUP_URL.replace("{groupId}", groupId.toString()));
        request.setRequestMethod(SdkRequest.RequestMethod.DELETE);
        return emptyResult(client,request);
    }


    public  Result<TerminalDTO> searchTerminalsInGroup(int pageNo, int pageSize, TerminalApi.TerminalSearchOrderBy orderBy,
                                                       Long groupId, String serialNo, String merchantIds, String merchantId){

        ThirdPartySysApiClient client = new ThirdPartySysApiClient(getBaseUrl(), getApiKey(), getApiSecret());
        PageRequestDTO page = new PageRequestDTO();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        if (orderBy != null) {
            page.setOrderBy(orderBy.val());
        }
        List<String> validationErrs = validate(page);
        if (validationErrs.size() > 0) {
            return new Result<TerminalDTO>(validationErrs);
        }
        SdkRequest request = getPageRequest(SEARCH_TERMINAL_IN_GROUP_URL.replace("{groupId}", groupId.toString()), page);

        request.setRequestMethod(SdkRequest.RequestMethod.GET);
        if (serialNo !=null){
            request.addRequestParam("serialNo", serialNo);
        }
        if(merchantIds!=null) {
            request.addRequestParam("merchantIds", merchantIds);
        }
        if(merchantId!=null) {
            request.addRequestParam("merchantId", merchantId);
        }

        TerminalPageResponse terminalPageResponse = EnhancedJsonUtils.fromJson(client.execute(request), TerminalPageResponse.class);
        Result<TerminalDTO> result = new Result<TerminalDTO>(terminalPageResponse);
        return result;
    }

    public Result<String> addGroupInTerminal(Long groupId, TerminalGroupRequest addInGroupRequest){
        validateGroupId(groupId);

        ThirdPartySysApiClient client = new ThirdPartySysApiClient(getBaseUrl(), getApiKey(), getApiSecret());
        SdkRequest request = createSdkRequest(ADD_TERMINAL_IN_GROUP_URL.replace("{groupId}", groupId.toString()));
        request.setRequestMethod(SdkRequest.RequestMethod.POST);
        request.addHeader(Constants.CONTENT_TYPE, Constants.CONTENT_TYPE_JSON);
        request.setRequestBody(new Gson().toJson(addInGroupRequest, TerminalGroupRequest.class));
        return emptyResult(client,request);
    }

    public Result<String> removeGroupOutTerminal(Long groupId, TerminalGroupRequest removeGroupRequest){
        validateGroupId(groupId);

        ThirdPartySysApiClient client = new ThirdPartySysApiClient(getBaseUrl(), getApiKey(), getApiSecret());
        SdkRequest request = createSdkRequest(REMOVE_TERMINAL_OUT_GROUP_URL.replace("{groupId}", groupId.toString()));
        request.setRequestMethod(SdkRequest.RequestMethod.PUT);
        request.addHeader(Constants.CONTENT_TYPE, Constants.CONTENT_TYPE_JSON);
        request.setRequestBody(new Gson().toJson(removeGroupRequest, TerminalGroupRequest.class));
        return emptyResult(client,request);
    }

    private Result<TerminalGroupDTO>  validateTerminalGroupId(Long groupId){
        logger.debug("groupId="+groupId);
        List<String> validationErrs = validateId(groupId, "parameter.terminalGroupId.invalid");
        if(validationErrs.size()>0) {
            return new Result<TerminalGroupDTO>(validationErrs);
        }else return null;
    }

    private Result<String>  validateGroupId(Long groupId){
        logger.debug("groupId="+groupId);
        List<String> validationErrs = validateId(groupId, "parameter.terminalGroupId.invalid");
        if(validationErrs.size()>0) {
            return new Result<String>(validationErrs);
        }else return null;
    }

    private Result<String>  emptyResult(ThirdPartySysApiClient client,SdkRequest request) {
        EmptyResponse emptyResponse =  EnhancedJsonUtils.fromJson(client.execute(request), EmptyResponse.class);
        return  new Result<String>(emptyResponse);
    }

    public enum TerminalGroupSearchOrderBy {
        Name("name"),
        CreatedDate_desc("createdDate DESC"),
        CreatedDate_asc("createdDate ASC");

        private String val;

        private TerminalGroupSearchOrderBy(String orderBy) {
            this.val = orderBy;
        }

        public String val() {
            return this.val;
        }

    }
}
