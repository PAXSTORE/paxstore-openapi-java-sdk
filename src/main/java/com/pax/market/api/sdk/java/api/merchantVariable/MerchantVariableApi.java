/*
 * *******************************************************************************
 * COPYRIGHT
 *               PAX TECHNOLOGY, Inc. PROPRIETARY INFORMATION
 *   This software is supplied under the terms of a license agreement or
 *   nondisclosure agreement with PAX  Technology, Inc. and may not be copied
 *   or disclosed except in accordance with the terms in that agreement.
 *
 *      Copyright (C) 2022 PAX Technology, Inc. All rights reserved.
 * *******************************************************************************
 */

package com.pax.market.api.sdk.java.api.merchantVariable;

import com.google.gson.Gson;
import com.pax.market.api.sdk.java.api.BaseThirdPartySysApi;
import com.pax.market.api.sdk.java.api.base.dto.EmptyResponse;
import com.pax.market.api.sdk.java.api.base.dto.PageRequestDTO;
import com.pax.market.api.sdk.java.api.base.dto.Result;
import com.pax.market.api.sdk.java.api.base.request.SdkRequest;
import com.pax.market.api.sdk.java.api.client.ThirdPartySysApiClient;
import com.pax.market.api.sdk.java.api.constant.Constants;
import com.pax.market.api.sdk.java.api.merchant.MerchantApi;
import com.pax.market.api.sdk.java.api.merchantVariable.dto.*;
import com.pax.market.api.sdk.java.api.util.CryptoUtils;
import com.pax.market.api.sdk.java.api.util.EnhancedJsonUtils;
import com.pax.market.api.sdk.java.api.util.StringUtils;
import com.pax.market.api.sdk.java.api.validate.Validators;
import com.pax.market.api.sdk.java.api.variable.dto.ParameterVariable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Locale;

/**
 * @author shifan
 * @date 2022/10/18
 */
public class MerchantVariableApi extends BaseThirdPartySysApi {
    private static final Logger logger = LoggerFactory.getLogger(MerchantApi.class.getSimpleName());

    private static final String SEARCH_MERCHANT_VARIABLE_URL = "/v1/3rdsys/merchant/variables";
    private static final String CREATE_MERCHANT_VARIABLE_URL = "/v1/3rdsys/merchant/variables";
    private static final String UPDATE_MERCHANT_VARIABLE_URL = "/v1/3rdsys/merchant/variables/{merchantVariableId}";
    private static final String DELETE_MERCHANT_VARIABLE_URL = "/v1/3rdsys/merchant/variables/{merchantVariableId}";
    private static final String BATCH_DELETE_MERCHANT_VARIABLE_URL = "/v1/3rdsys/merchant/variables/batch/deletion";

    public MerchantVariableApi(String baseUrl, String apiKey, String apiSecret) {
        super(baseUrl, apiKey, apiSecret);
    }

    public MerchantVariableApi(String baseUrl, String apiKey, String apiSecret, Locale locale) {
        super(baseUrl, apiKey, apiSecret, locale);
    }

    public Result<MerchantVariableDTO> searchMerchantVariable(int pageNo, int pageSize, SearchOrderBy orderBy, Long merchantId, String packageName, String key, VariableSource source) {
        ThirdPartySysApiClient client = new ThirdPartySysApiClient(getBaseUrl(), getApiKey(), getApiSecret());
        PageRequestDTO page = new PageRequestDTO();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        List<String> validationErrs = Validators.validatePageRequest(page);
        validationErrs.addAll(Validators.validateId(merchantId, "parameter.id.invalid","merchantId"));
        if (!validationErrs.isEmpty()) {
            return new Result<>(validationErrs);
        }
        if (orderBy != null) {
            page.setOrderBy(orderBy.val);
        }
        SdkRequest request = getPageRequest(SEARCH_MERCHANT_VARIABLE_URL, page);

        request.addRequestParam("merchantId", merchantId.toString());

        if (key != null) {
            request.addRequestParam("key", key);
        }
        if (packageName != null) {
            request.addRequestParam("packageName", packageName);
        }
        if (source != null) {
            request.addRequestParam("source", source.val());
        }
        request.setRequestMethod(SdkRequest.RequestMethod.GET);

        MerchantVariablePageResponse resp = EnhancedJsonUtils.fromJson(client.execute(request), MerchantVariablePageResponse.class);
        Result<MerchantVariableDTO> result = new Result<MerchantVariableDTO>(resp);
        return result;
    }


    private void encryptPasswordVariable(ParameterVariable parameterVariable) {
        if (StringUtils.equals("P", parameterVariable.getType()) && StringUtils.isNotEmpty(parameterVariable.getValue())) {
            try {
                parameterVariable.setValue(CryptoUtils.byte2hex(CryptoUtils.aesEncrypt(parameterVariable.getValue().getBytes(StandardCharsets.UTF_8), CryptoUtils.encryptMD5(getApiSecret()))));
            } catch (Exception ignore) {
            }
        }
    }


    public Result<String> createMerchantVariable(MerchantVariableCreateRequest createRequest) {
        List<String> validationErrs = Validators.validateCreate(createRequest, "parameter.merchantVariableCreateRequest.null");
        validationErrs.addAll(Validators.validateId(createRequest.getMerchantId(), "parameter.id.invalid","merchantId"));
        if (createRequest.getVariableList() == null || createRequest.getVariableList().isEmpty()) {
            validationErrs.add(getMessage("parameter.not.null", "merchantVariable list"));
        }
        if (!validationErrs.isEmpty()) {
            return new Result<>(validationErrs);
        }

        for (ParameterVariable parameterVariable : createRequest.getVariableList()) {
            encryptPasswordVariable(parameterVariable);
        }
        ThirdPartySysApiClient client = new ThirdPartySysApiClient(getBaseUrl(), getApiKey(), getApiSecret());
        SdkRequest request = createSdkRequest(CREATE_MERCHANT_VARIABLE_URL);
        request.setRequestMethod(SdkRequest.RequestMethod.POST);
        request.addHeader(Constants.CONTENT_TYPE, Constants.CONTENT_TYPE_JSON);
        request.setRequestBody(new Gson().toJson(createRequest, MerchantVariableCreateRequest.class));
        return emptyResult(client, request);
    }


    public Result<String> updateMerchantVariable(Long merchantVariableId, MerchantVariableUpdateRequest updateRequest) {
        List<String> validationErrs = Validators.validateId(merchantVariableId, "parameter.id.invalid","merchantVariableId" );
        validationErrs.addAll(Validators.validateObject(updateRequest, "merchantVariableUpdateRequest"));
        if (!validationErrs.isEmpty()) {
            return new Result<>(validationErrs);
        }
        encryptPasswordVariable(updateRequest);
        ThirdPartySysApiClient client = new ThirdPartySysApiClient(getBaseUrl(), getApiKey(), getApiSecret());
        SdkRequest request = createSdkRequest(UPDATE_MERCHANT_VARIABLE_URL.replace("{merchantVariableId}", merchantVariableId + ""));
        request.setRequestMethod(SdkRequest.RequestMethod.PUT);
        request.addHeader(Constants.CONTENT_TYPE, Constants.CONTENT_TYPE_JSON);
        request.setRequestBody(new Gson().toJson(updateRequest, MerchantVariableUpdateRequest.class));
        return emptyResult(client, request);
    }

    public Result<String> deleteMerchantVariable(Long merchantVariableId) {
        logger.debug("merchantVariableId= {}", merchantVariableId);
        List<String> validationErrs = Validators.validateId(merchantVariableId, "parameter.id.invalid","merchantVariableId");
        if (!validationErrs.isEmpty()) {
            return new Result<>(validationErrs);
        }
        ThirdPartySysApiClient client = new ThirdPartySysApiClient(getBaseUrl(), getApiKey(), getApiSecret());
        SdkRequest request = createSdkRequest(DELETE_MERCHANT_VARIABLE_URL.replace("{merchantVariableId}", merchantVariableId + ""));
        request.setRequestMethod(SdkRequest.RequestMethod.DELETE);
        return emptyResult(client, request);
    }

    public Result<String> batchDeletionMerchantVariable(MerchantVariableDeleteRequest batchDeleteRequest) {
        List<String> validationErrs = Validators.validateObject(batchDeleteRequest, "merchantVariableDeleteRequest");
        if (batchDeleteRequest != null) {
            if (batchDeleteRequest.getVariableIds() == null || batchDeleteRequest.getVariableIds().isEmpty()) {
                validationErrs.add(getMessage("variableIds.is.empty"));
            }
        }
        if (!validationErrs.isEmpty()) {
            return new Result<>(validationErrs);
        }

        ThirdPartySysApiClient client = new ThirdPartySysApiClient(getBaseUrl(), getApiKey(), getApiSecret());
        SdkRequest request = createSdkRequest(BATCH_DELETE_MERCHANT_VARIABLE_URL);
        request.setRequestMethod(SdkRequest.RequestMethod.DELETE);
        request.addHeader(Constants.CONTENT_TYPE, Constants.CONTENT_TYPE_JSON);
        request.setRequestBody(new Gson().toJson(batchDeleteRequest, MerchantVariableDeleteRequest.class));
        return emptyResult(client, request);
    }


    private Result<String> emptyResult(ThirdPartySysApiClient client, SdkRequest request) {
        EmptyResponse emptyResponse = EnhancedJsonUtils.fromJson(client.execute(request), EmptyResponse.class);
        return new Result<String>(emptyResponse);
    }

    public enum SearchOrderBy {
        Variable_desc("createdDate DESC"),
        Variable_asc("createdDate ASC");
        private String val;

        private SearchOrderBy(String orderBy) {
            this.val = orderBy;
        }

        public String val() {
            return this.val;
        }
    }

    public enum VariableSource {
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