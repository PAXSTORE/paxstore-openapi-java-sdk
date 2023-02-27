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
import com.pax.market.api.sdk.java.api.base.dto.PageRequestDTO;
import com.pax.market.api.sdk.java.api.base.dto.Result;
import com.pax.market.api.sdk.java.api.base.request.SdkRequest;
import com.pax.market.api.sdk.java.api.client.ThirdPartySysApiClient;
import com.pax.market.api.sdk.java.api.constant.Constants;
import com.pax.market.api.sdk.java.api.terminalVariable.dto.*;
import com.pax.market.api.sdk.java.api.util.CryptoUtils;
import com.pax.market.api.sdk.java.api.util.EnhancedJsonUtils;
import com.pax.market.api.sdk.java.api.util.StringUtils;
import com.pax.market.api.sdk.java.api.validate.Validators;
import com.pax.market.api.sdk.java.api.variable.dto.ParameterVariable;
import com.pax.market.api.sdk.java.api.variable.dto.ParameterVariableDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;
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

    public Result<ParameterVariableDTO> getTerminalVariable(int pageNo, int pageSize , SearchOrderBy orderBy, String tid , String serialNo , String packageName, String key, VariableSource source){
        ThirdPartySysApiClient client = new ThirdPartySysApiClient(getBaseUrl(), getApiKey(), getApiSecret());
        PageRequestDTO page = new PageRequestDTO();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        List<String> validationErrs = Validators.validatePageRequest(page);
        if(StringUtils.isEmpty(tid) && StringUtils.isEmpty(serialNo)) {
            validationErrs.add(getMessage("param.tid.serialNo.empty.atSameTime"));
        }
        if(orderBy!=null) {
            page.setOrderBy(orderBy.val);
        }
        SdkRequest request = getPageRequest(GET_TERMINAL_VARIABLE_URL,page);

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
            request.addRequestParam("source", source.val());
        }
        request.setRequestMethod(SdkRequest.RequestMethod.GET);

        TerminalParameterVariablePageResponse resp = EnhancedJsonUtils.fromJson(client.execute(request), TerminalParameterVariablePageResponse.class);
        return new Result<>(resp);
    }

    private void encryptPasswordVariable(ParameterVariable parameterVariable) {
        if (StringUtils.equals("P", parameterVariable.getType()) && StringUtils.isNotEmpty(parameterVariable.getValue())) {
            try {
                parameterVariable.setValue(CryptoUtils.byte2hex(CryptoUtils.aesEncrypt(parameterVariable.getValue().getBytes(StandardCharsets.UTF_8), CryptoUtils.encryptMD5(getApiSecret()))));
            } catch (Exception ignore) {
            }
        }
    }

    public Result<String> createTerminalVariable(TerminalParameterVariableRequest createRequest){
        List<String> validationErrs = Validators.validateCreate(createRequest,"parameter.terminalVariableRequest.null");
        if(StringUtils.isEmpty(createRequest.getTid()) && StringUtils.isEmpty(createRequest.getSerialNo())) {
            validationErrs.add(getMessage("parameter.sn.tid.empty"));
        }
        if(!validationErrs.isEmpty()) {
            return new Result<>(validationErrs);
        }
        for (ParameterVariable parameterVariable : createRequest.getVariableList()) {
            encryptPasswordVariable(parameterVariable);
        }
        ThirdPartySysApiClient client = new ThirdPartySysApiClient(getBaseUrl(), getApiKey(), getApiSecret());
        SdkRequest request = createSdkRequest(CREATE_TERMINAL_VARIABLE_URL);
        request.setRequestMethod(SdkRequest.RequestMethod.POST);
        request.addHeader(Constants.CONTENT_TYPE, Constants.CONTENT_TYPE_JSON);
        request.setRequestBody(new Gson().toJson(createRequest, TerminalParameterVariableRequest.class));
        EmptyResponse emptyResponse =  EnhancedJsonUtils.fromJson(client.execute(request), EmptyResponse.class);
        return new Result<>(emptyResponse);
    }

    public Result<String> updateTerminalVariable(Long terminalVariableId, ParameterVariable updateRequest){
        validateTerminalVariableId(terminalVariableId);
        encryptPasswordVariable(updateRequest);

        ThirdPartySysApiClient client = new ThirdPartySysApiClient(getBaseUrl(), getApiKey(), getApiSecret());
        SdkRequest request = createSdkRequest(UPDATE_TERMINAL_VARIABLE_URL.replace("{terminalVariableId}",terminalVariableId+""));
        request.setRequestMethod(SdkRequest.RequestMethod.PUT);
        request.addHeader(Constants.CONTENT_TYPE, Constants.CONTENT_TYPE_JSON);
        request.setRequestBody(new Gson().toJson(updateRequest, ParameterVariable.class));
        EmptyResponse emptyResponse =  EnhancedJsonUtils.fromJson(client.execute(request), EmptyResponse.class);
        return new Result<>(emptyResponse);
    }

    public Result<String> deleteTerminalVariable(Long terminalVariableId){
        validateTerminalVariableId(terminalVariableId);
        ThirdPartySysApiClient client = new ThirdPartySysApiClient(getBaseUrl(), getApiKey(), getApiSecret());
        SdkRequest request = createSdkRequest(DELETE_TERMINAL_VARIABLE_URL.replace("{terminalVariableId}",terminalVariableId+""));
        request.setRequestMethod(SdkRequest.RequestMethod.DELETE);
        return emptyResult(client,request);
    }

    public Result<String> batchDeletionTerminalVariable(TerminalParameterVariableDeleteRequest batchDeletionRequest){
        List<String> validationErrs = Validators.validateObject(batchDeletionRequest,"batchDeletionRequest");
        if(batchDeletionRequest != null) {
            if(batchDeletionRequest.getVariableIds() == null || batchDeletionRequest.getVariableIds().isEmpty()) {
                validationErrs.add(getMessage("variableIds.is.empty"));
            }
        }
        if(!validationErrs.isEmpty()) {
            return new Result<>(validationErrs);
        }

        ThirdPartySysApiClient client = new ThirdPartySysApiClient(getBaseUrl(), getApiKey(), getApiSecret());
        SdkRequest request = createSdkRequest(BATCH_DELETION_TERMINAL_VARIABLE_URL);
        request.setRequestMethod(SdkRequest.RequestMethod.POST);
        request.addHeader(Constants.CONTENT_TYPE, Constants.CONTENT_TYPE_JSON);
        request.setRequestBody(new Gson().toJson(batchDeletionRequest, TerminalParameterVariableDeleteRequest.class));
        return emptyResult(client,request);
    }

    private Result<String>  validateTerminalVariableId(Long terminalVariableId){
        logger.debug("terminalVariableId= {}", terminalVariableId);
        List<String> validationErrs = Validators.validateId(terminalVariableId, "parameter.id.invalid", "terminalVariableId");
        if(!validationErrs.isEmpty()) {
            return new Result<>(validationErrs);
        }
        return new Result<>();
    }

    private Result<String>  emptyResult(ThirdPartySysApiClient client,SdkRequest request) {
        EmptyResponse emptyResponse =  EnhancedJsonUtils.fromJson(client.execute(request), EmptyResponse.class);
        return  new Result<>(emptyResponse);
    }
    public enum SearchOrderBy {
        Variable_desc("createdDate DESC"),
        Variable_asc("createdDate ASC");
        private String val;
        private SearchOrderBy(String orderBy) {
            this.val = orderBy;
        }
        public String val(){
            return this.val;
        }
    }

    public enum VariableSource {
        TERMINAL("T"),
        MARKET("M"),
        MERCHANT("C");

        private String val;

        private VariableSource(String orderBy) {
            this.val = orderBy;
        }

        public String val() {
            return this.val;
        }

    }

}
