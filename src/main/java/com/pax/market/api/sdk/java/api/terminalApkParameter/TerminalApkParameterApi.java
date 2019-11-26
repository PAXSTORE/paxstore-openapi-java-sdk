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
package com.pax.market.api.sdk.java.api.terminalApkParameter;

import com.google.gson.Gson;
import com.pax.market.api.sdk.java.api.BaseThirdPartySysApi;

import com.pax.market.api.sdk.java.api.base.dto.EmptyResponse;
import com.pax.market.api.sdk.java.api.base.dto.Result;
import com.pax.market.api.sdk.java.api.base.request.SdkRequest;
import com.pax.market.api.sdk.java.api.client.ThirdPartySysApiClient;
import com.pax.market.api.sdk.java.api.constant.Constants;
import com.pax.market.api.sdk.java.api.terminalApkParameter.dto.*;
import com.pax.market.api.sdk.java.api.util.EnhancedJsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.Locale;

/**
 * @Description
 * @Author: Shawn
 * @Date: 2019/10/30 16:23
 * @Version 1.0
 */
public class TerminalApkParameterApi extends BaseThirdPartySysApi {
    private static final Logger logger = LoggerFactory.getLogger(TerminalApkParameterApi.class);

    private static final String GET_TERMINAL_APK_PARAMETER_URL = "/v1/3rdsys/apkParameters";
    private static final String CREATE_APK_PARAMETER_URL = "/v1/3rdsys/apkParameters";
    private static final String UPDATE_APK_PARAMETER_URL = "/v1/3rdsys/apkParameters/{apkParameterId}";
    private static final String DELETE_APK_PARAMETER_URL = "/v1/3rdsys/apkParameters/{apkParameterId}";

    public TerminalApkParameterApi(String baseUrl, String apiKey, String apiSecret) {
        super(baseUrl, apiKey, apiSecret);
    }

    public TerminalApkParameterApi(String baseUrl, String apiKey, String apiSecret, Locale locale) {
        super(baseUrl, apiKey, apiSecret, locale);
    }


    public Result<ApkParameterDTO> getTerminalApkParameter(String templateName ,String packageName, String versionName){

        ThirdPartySysApiClient client = new ThirdPartySysApiClient(getBaseUrl(), getApiKey(), getApiSecret());
        SdkRequest request = createSdkRequest(GET_TERMINAL_APK_PARAMETER_URL);
        if(templateName!=null) {
            request.addRequestParam("templateName", templateName);
        }
        if(packageName!=null) {
            request.addRequestParam("packageName", packageName);
        }
        if(versionName!=null) {
            request.addRequestParam("versionName", versionName);
        }
        request.setRequestMethod(SdkRequest.RequestMethod.GET);

        ApkParameterPageResponse resp = EnhancedJsonUtils.fromJson(client.execute(request), ApkParameterPageResponse.class);
        Result<ApkParameterDTO> result = new Result<ApkParameterDTO>(resp);
        return result;
    }

    public Result<String> createApkParameter(CreateApkParameterRequest createApkParameterRequest){
        ThirdPartySysApiClient client = new ThirdPartySysApiClient(getBaseUrl(), getApiKey(), getApiSecret());
        SdkRequest request = createSdkRequest(CREATE_APK_PARAMETER_URL);
        request.setRequestMethod(SdkRequest.RequestMethod.POST);
        request.addHeader(Constants.CONTENT_TYPE, Constants.CONTENT_TYPE_JSON);
        request.setRequestBody(new Gson().toJson(createApkParameterRequest, CreateApkParameterRequest.class));
        EmptyResponse emptyResponse =  EnhancedJsonUtils.fromJson(client.execute(request), EmptyResponse.class);
        Result<String> result = new Result<String>(emptyResponse);
        return result;
    }


    public Result<String> updateApkParameter(Long apkParameterId,UpdateApkParameterRequest updateApkParameterRequest){

        ThirdPartySysApiClient client = new ThirdPartySysApiClient(getBaseUrl(), getApiKey(), getApiSecret());
        SdkRequest request = createSdkRequest(UPDATE_APK_PARAMETER_URL.replace("{apkParameterId}",apkParameterId+""));
        request.setRequestMethod(SdkRequest.RequestMethod.PUT);
        request.addHeader(Constants.CONTENT_TYPE, Constants.CONTENT_TYPE_JSON);
        request.setRequestBody(new Gson().toJson(updateApkParameterRequest, UpdateApkParameterRequest.class));
        EmptyResponse emptyResponse =  EnhancedJsonUtils.fromJson(client.execute(request), EmptyResponse.class);
        Result<String> result = new Result<String>(emptyResponse);
        return result;
    }

    public Result<String> deleteApkParameter(Long apkParameterId){
        ThirdPartySysApiClient client = new ThirdPartySysApiClient(getBaseUrl(), getApiKey(), getApiSecret());
        SdkRequest request = createSdkRequest(DELETE_APK_PARAMETER_URL.replace("{apkParameterId}",apkParameterId+""));
        request.setRequestMethod(SdkRequest.RequestMethod.DELETE);
        EmptyResponse emptyResponse =  EnhancedJsonUtils.fromJson(client.execute(request), EmptyResponse.class);
        return  new Result<String>(emptyResponse);
    }
}
