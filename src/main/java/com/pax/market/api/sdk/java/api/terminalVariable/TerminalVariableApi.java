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
package com.pax.market.api.sdk.java.api.terminalVariable;

import com.google.gson.Gson;
import com.pax.market.api.sdk.java.api.BaseThirdPartySysApi;
import com.pax.market.api.sdk.java.api.base.dto.EmptyResponse;
import com.pax.market.api.sdk.java.api.base.dto.Result;
import com.pax.market.api.sdk.java.api.base.request.SdkRequest;
import com.pax.market.api.sdk.java.api.client.ThirdPartySysApiClient;
import com.pax.market.api.sdk.java.api.constant.Constants;
import com.pax.market.api.sdk.java.api.terminalApkParameter.dto.ApkParameterDTO;
import com.pax.market.api.sdk.java.api.terminalApkParameter.dto.ApkParameterResponse;
import com.pax.market.api.sdk.java.api.terminalApkParameter.dto.UpdateApkParameterRequest;
import com.pax.market.api.sdk.java.api.terminalVariable.dto.*;
import com.pax.market.api.sdk.java.api.util.EnhancedJsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Locale;

/**
 * @Description
 * @Author: Shawn
 * @Date: 2019/11/11 18:02
 * @Version 1.0
 */
public class TerminalVariableApi  extends BaseThirdPartySysApi {
    private static final Logger logger = LoggerFactory.getLogger(TerminalVariableApi.class);

    private static final String GET_TERMINAL_VARIABLE_URL = "/v1/3rdsys/terminalVariables";
    private static final String CREATE_TERMINAL_VARIABLE_URL = "/v1/3rdsys/terminalVariables";
    private static final String UPDATE_TERMINAL_VARIABLE_URL = "/v1/3rdsys/terminalVariables/{terminalVariableId}";
    private static final String DELETE_TERMINAL_VARIABLE_URL = "/v1/3rdsys/terminalVariables/{terminalVariableId}";
    private static final String BATCH_DELETION_TERMINAL_VARIABLE_URL = "/v1/3rdsys/terminalVariables/batch/deletion";

    public TerminalVariableApi(String baseUrl, String apiKey, String apiSecret) {
        super(baseUrl, apiKey, apiSecret);
    }

    public TerminalVariableApi(String baseUrl, String apiKey, String apiSecret, Locale locale) {
        super(baseUrl, apiKey, apiSecret, locale);
    }

    public Result<ParameterVariableDTO> getTerminalVariable(String tid , String serialNo , String packageName, String key, String source){

        ThirdPartySysApiClient client = new ThirdPartySysApiClient(getBaseUrl(), getApiKey(), getApiSecret());
        SdkRequest request = createSdkRequest(GET_TERMINAL_VARIABLE_URL);

        if(tid!=null) {
            request.addRequestParam("tid", tid);
        }
        if(serialNo!=null) {
            request.addRequestParam("serialNo", serialNo);
        }
        if(packageName!=null) {
            request.addRequestParam("packageName", packageName);
        }
        if(key!=null) {
            request.addRequestParam("key", key);
        }
        if(source!=null) {
            request.addRequestParam("source", source);
        }
        request.setRequestMethod(SdkRequest.RequestMethod.GET);

        TerminalParameterVariablePageResponse resp = EnhancedJsonUtils.fromJson(client.execute(request), TerminalParameterVariablePageResponse.class);
        Result<ParameterVariableDTO> result = new Result<ParameterVariableDTO>(resp);
        return result;
    }



    public Result<String> createTerminalVariable(TerminalParameterVariableRequest createRequest){
        ThirdPartySysApiClient client = new ThirdPartySysApiClient(getBaseUrl(), getApiKey(), getApiSecret());
        SdkRequest request = createSdkRequest(CREATE_TERMINAL_VARIABLE_URL);
        request.setRequestMethod(SdkRequest.RequestMethod.POST);
        request.addHeader(Constants.CONTENT_TYPE, Constants.CONTENT_TYPE_JSON);
        request.setRequestBody(new Gson().toJson(createRequest, TerminalParameterVariableRequest.class));
        EmptyResponse emptyResponse =  EnhancedJsonUtils.fromJson(client.execute(request), EmptyResponse.class);
        Result<String> result = new Result<String>(emptyResponse);
        return result;
    }

    public Result<String> updateTerminalVariable(Long terminalVariableId, ParameterVariable updateRequest){
        validateTerminalVariableId(terminalVariableId);

        ThirdPartySysApiClient client = new ThirdPartySysApiClient(getBaseUrl(), getApiKey(), getApiSecret());
        SdkRequest request = createSdkRequest(UPDATE_TERMINAL_VARIABLE_URL.replace("{terminalVariableId}",terminalVariableId+""));
        request.setRequestMethod(SdkRequest.RequestMethod.PUT);
        request.addHeader(Constants.CONTENT_TYPE, Constants.CONTENT_TYPE_JSON);
        request.setRequestBody(new Gson().toJson(updateRequest, ParameterVariable.class));
        EmptyResponse emptyResponse =  EnhancedJsonUtils.fromJson(client.execute(request), EmptyResponse.class);
        Result<String> result = new Result<String>(emptyResponse);
        return result;
    }

    public Result<String> deleteTerminalVariable(Long terminalVariableId){
        validateTerminalVariableId(terminalVariableId);
        ThirdPartySysApiClient client = new ThirdPartySysApiClient(getBaseUrl(), getApiKey(), getApiSecret());
        SdkRequest request = createSdkRequest(DELETE_TERMINAL_VARIABLE_URL.replace("{terminalVariableId}",terminalVariableId+""));
        request.setRequestMethod(SdkRequest.RequestMethod.DELETE);
        return emptyResult(client,request);
    }

    public Result<String> batchDeletionTerminalVariable(TerminalParameterVariableDeleteRequest batchDeletionRequest){

        ThirdPartySysApiClient client = new ThirdPartySysApiClient(getBaseUrl(), getApiKey(), getApiSecret());
        SdkRequest request = createSdkRequest(BATCH_DELETION_TERMINAL_VARIABLE_URL);
        request.setRequestMethod(SdkRequest.RequestMethod.POST);
        request.addHeader(Constants.CONTENT_TYPE, Constants.CONTENT_TYPE_JSON);
        request.setRequestBody(new Gson().toJson(batchDeletionRequest, TerminalParameterVariableDeleteRequest.class));
        return emptyResult(client,request);
    }

    private Result<String>  validateTerminalVariableId(Long terminalVariableId){
        logger.debug("terminalVariableId="+terminalVariableId);
        List<String> validationErrs = validateId(terminalVariableId, "parameter.terminalVariableId.invalid");
        if(validationErrs.size()>0) {
            return new Result<String>(validationErrs);
        }else return null;
    }

    private Result<String>  emptyResult(ThirdPartySysApiClient client,SdkRequest request) {
        EmptyResponse emptyResponse =  EnhancedJsonUtils.fromJson(client.execute(request), EmptyResponse.class);
        return  new Result<String>(emptyResponse);
    }
}
