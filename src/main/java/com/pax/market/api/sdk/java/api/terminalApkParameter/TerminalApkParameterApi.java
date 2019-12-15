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
import com.pax.market.api.sdk.java.api.base.dto.PageRequestDTO;
import com.pax.market.api.sdk.java.api.base.dto.Result;
import com.pax.market.api.sdk.java.api.base.request.SdkRequest;
import com.pax.market.api.sdk.java.api.client.ThirdPartySysApiClient;
import com.pax.market.api.sdk.java.api.constant.Constants;
import com.pax.market.api.sdk.java.api.terminalApk.dto.FileParameter;
import com.pax.market.api.sdk.java.api.terminalApkParameter.dto.*;
import com.pax.market.api.sdk.java.api.util.EnhancedJsonUtils;
import com.pax.market.api.sdk.java.api.util.FileUtils;
import com.pax.market.api.sdk.java.api.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Locale;

/**
 * @Description
 * @Author: Shawn
 * @Date: 2019/10/30 16:23
 * @Version 1.0
 */
public class TerminalApkParameterApi extends BaseThirdPartySysApi {
    private static final Logger logger = LoggerFactory.getLogger(TerminalApkParameterApi.class);

    private static final String GET_TERMINAL_APK_PARAMETER_URL = "/v1/3rdsys/apkParameters/{apkParameterId}";
    private static final String SEARCH_TERMINAL_APK_PARAMETER_URL = "/v1/3rdsys/apkParameters";
    private static final String CREATE_APK_PARAMETER_URL = "/v1/3rdsys/apkParameters";
    private static final String UPDATE_APK_PARAMETER_URL = "/v1/3rdsys/apkParameters/{apkParameterId}";
    private static final String DELETE_APK_PARAMETER_URL = "/v1/3rdsys/apkParameters/{apkParameterId}";

    private static final int MAX_FILE_TYPE_PARAMETER_COUNTER = 10;
    private static final int MAX_FILE_TYPE_PARAMETER_SIZE = 500;

    public TerminalApkParameterApi(String baseUrl, String apiKey, String apiSecret) {
        super(baseUrl, apiKey, apiSecret);
    }

    public TerminalApkParameterApi(String baseUrl, String apiKey, String apiSecret, Locale locale) {
        super(baseUrl, apiKey, apiSecret, locale);
    }


    public Result<ApkParameterDTO> searchTerminalApkParameter(int pageNo, int pageSize , SearchOrderBy orderBy, String templateName , String packageName, String versionName){
        PageRequestDTO page = new PageRequestDTO();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        if(orderBy!=null) {
            page.setOrderBy(orderBy.val);
        }
        List<String> validationErrs = validate(page);
        if(StringUtils.isEmpty(packageName)) {
            validationErrs.add(getMessage("parameter.packageName.mandatory"));
        }
        if(StringUtils.isEmpty(versionName)) {
            validationErrs.add(getMessage("parameter.versionName.mandatory"));
        }
        if(validationErrs.size()>0) {
            return new Result<ApkParameterDTO>(validationErrs);
        }
        ThirdPartySysApiClient client = new ThirdPartySysApiClient(getBaseUrl(), getApiKey(), getApiSecret());
        SdkRequest request = getPageRequest(SEARCH_TERMINAL_APK_PARAMETER_URL, page);
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

    public Result<ApkParameterDTO> getTerminalApkParameter(Long apkParameterId){
        List<String> validationErrs= validateId(apkParameterId, "parameter.terminalApkParameterId.invalid");
        if(validationErrs.size()>0) {
            return new Result<ApkParameterDTO>(validationErrs);
        }
        ThirdPartySysApiClient client = new ThirdPartySysApiClient(getBaseUrl(), getApiKey(), getApiSecret());
        SdkRequest request = createSdkRequest(GET_TERMINAL_APK_PARAMETER_URL.replace("{apkParameterId}", apkParameterId.toString()));
        request.setRequestMethod(SdkRequest.RequestMethod.GET);
        ApkParameterResponse resp = EnhancedJsonUtils.fromJson(client.execute(request), ApkParameterResponse.class);
        Result<ApkParameterDTO> result = new Result<ApkParameterDTO>(resp);
        return result;
    }





    public Result<String> createApkParameter(CreateApkParameterRequest createApkParameterRequest){
        List<String> validationErrs = validateCreate( createApkParameterRequest,"parameter.apkParameterCreateRequest.null");
        if(createApkParameterRequest.getParameters() == null && createApkParameterRequest.getBase64FileParameters()==null) {
            validationErrs.add(getMessage("parametersBase64FileParameters.not.null.atsame"));
        }
        if(createApkParameterRequest.getBase64FileParameters() != null && !createApkParameterRequest.getBase64FileParameters().isEmpty()) {
            if(createApkParameterRequest.getBase64FileParameters().size()>MAX_FILE_TYPE_PARAMETER_COUNTER) {
                validationErrs.add(getMessage("parametersBase64FileParameters.over.maxCounter"));
            }
            for(FileParameter fileParameter: createApkParameterRequest.getBase64FileParameters()) {
                if(FileUtils.getBase64FileSizeKB(fileParameter.getFileData()) > MAX_FILE_TYPE_PARAMETER_SIZE) {
                    validationErrs.add(getMessage("parametersBase64FileParameters.over.maxSize"));
                    break;
                }
            }
        }
        if(validationErrs.size()>0) {
            return new Result<String>(validationErrs);
        }
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
        List<String> validationErrs = validateUpdate( apkParameterId,updateApkParameterRequest,"parameter.terminalApkParameterId.invalid","parameter.apkParameterUpdateRequest.null");
        if(updateApkParameterRequest.getBase64FileParameters() != null && !updateApkParameterRequest.getBase64FileParameters().isEmpty()) {
            if(updateApkParameterRequest.getBase64FileParameters().size()>MAX_FILE_TYPE_PARAMETER_COUNTER) {
                validationErrs.add(getMessage("parametersBase64FileParameters.over.maxCounter"));
            }
            for(FileParameter fileParameter: updateApkParameterRequest.getBase64FileParameters()) {
                if(FileUtils.getBase64FileSizeKB(fileParameter.getFileData()) > MAX_FILE_TYPE_PARAMETER_SIZE) {
                    validationErrs.add(getMessage("parametersBase64FileParameters.over.maxSize"));
                    break;
                }
            }
        }
        if(validationErrs.size()>0) {
            return new Result<String>(validationErrs);
        }

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
        List<String> validationErrs = validateId( apkParameterId,"parameter.terminalApkParameterId.invalid");
        if(validationErrs.size()>0) {
            return new Result<String>(validationErrs);
        }
        ThirdPartySysApiClient client = new ThirdPartySysApiClient(getBaseUrl(), getApiKey(), getApiSecret());
        SdkRequest request = createSdkRequest(DELETE_APK_PARAMETER_URL.replace("{apkParameterId}",apkParameterId+""));
        request.setRequestMethod(SdkRequest.RequestMethod.DELETE);
        EmptyResponse emptyResponse =  EnhancedJsonUtils.fromJson(client.execute(request), EmptyResponse.class);
        return  new Result<String>(emptyResponse);
    }

    public enum SearchOrderBy {
        ApkParameter_desc("a.created_date DESC"),
        ApkParameter_asc("a.created_date ASC");
        private String val;
        private SearchOrderBy(String orderBy) {
            this.val = orderBy;
        }
        public String val(){
            return this.val;
        }
    }
}
