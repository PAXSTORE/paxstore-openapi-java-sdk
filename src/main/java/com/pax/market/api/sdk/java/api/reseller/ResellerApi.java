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
package com.pax.market.api.sdk.java.api.reseller;

import com.google.gson.Gson;
import com.pax.market.api.sdk.java.api.BaseThirdPartySysApi;
import com.pax.market.api.sdk.java.api.base.dto.EmptyResponse;
import com.pax.market.api.sdk.java.api.base.dto.PageRequestDTO;
import com.pax.market.api.sdk.java.api.base.dto.Result;
import com.pax.market.api.sdk.java.api.base.request.SdkRequest;
import com.pax.market.api.sdk.java.api.base.request.SdkRequest.RequestMethod;
import com.pax.market.api.sdk.java.api.client.ThirdPartySysApiClient;
import com.pax.market.api.sdk.java.api.constant.Constants;
import com.pax.market.api.sdk.java.api.reseller.dto.*;
import com.pax.market.api.sdk.java.api.reseller.validator.ResellerCreateRequestValidator;
import com.pax.market.api.sdk.java.api.reseller.validator.ResellerUpdateRequestValidator;
import com.pax.market.api.sdk.java.api.util.EnhancedJsonUtils;
import com.pax.market.api.sdk.java.api.util.MessageBundleUtils;
import com.pax.market.api.sdk.java.api.util.StringUtils;
import com.pax.market.api.sdk.java.api.validate.Validators;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * @author tanjie
 * @date 2018-07-03
 */
public class ResellerApi extends BaseThirdPartySysApi {
    private static final Logger logger = LoggerFactory.getLogger(ResellerApi.class.getSimpleName());

    private static final String SEARCH_RESELLER_URL = "/v1/3rdsys/resellers";
    private static final String GET_RESELLER_URL = "/v1/3rdsys/resellers/{resellerId}";
    private static final String CREATE_RESELLER_URL = "/v1/3rdsys/resellers";
    private static final String UPDATE_RESELLER_URL = "/v1/3rdsys/resellers/{resellerId}";
    private static final String ACTIVATE_RESELLER_URL = "/v1/3rdsys/resellers/{resellerId}/active";
    private static final String DISABLE_RESELLER_URL = "/v1/3rdsys/resellers/{resellerId}/disable";
    private static final String DELETE_RESELLER_URL = "/v1/3rdsys/resellers/{resellerId}";
    private static final String REPLACE_RESELLER_EMAIL_URL = "/v1/3rdsys/resellers/{resellerId}/replaceEmail";
    private static final String SEARCH_RESELLER_RKI_KET_TEMPLATE_LIST_URL = "/v1/3rdsys/resellers/{resellerId}/rki/template";

    public ResellerApi(String baseUrl, String apiKey, String apiSecret) {
        super(baseUrl, apiKey, apiSecret);
    }

    public ResellerApi(String baseUrl, String apiKey, String apiSecret, Locale locale) {
        super(baseUrl, apiKey, apiSecret, locale);
    }

    public Result<ResellerPageDTO> searchReseller(int pageNo, int pageSize, ResellerSearchOrderBy orderBy, String name, ResellerStatus status) {
        logger.debug("name= {} | status= {}", name, status);
        ThirdPartySysApiClient client = new ThirdPartySysApiClient(getBaseUrl(), getApiKey(), getApiSecret());
        PageRequestDTO page = new PageRequestDTO();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        if (orderBy != null) {
            page.setOrderBy(orderBy.val);
        }
        List<String> validationErrs = Validators.validatePageRequest(page);
        if (validationErrs.size() > 0) {
            return new Result<>(validationErrs);
        }
        SdkRequest request = getPageRequest(SEARCH_RESELLER_URL, page);
        request.addRequestParam("name", name);
        if (status != null) {
            request.addRequestParam("status", status.val);
        }
        ResellerPageResponse resellerPageDTO = EnhancedJsonUtils.fromJson(client.execute(request), ResellerPageResponse.class);
        return new Result<>(resellerPageDTO);
    }

    public Result<ResellerDTO> getReseller(Long resellerId) {
        logger.debug("resellerId= {}", resellerId);
        List<String> validationErrs = Validators.validateId(resellerId, "parameter.id.invalid", "resellerId");
        if (validationErrs.size() > 0) {
            return new Result<>(validationErrs);
        }
        ThirdPartySysApiClient client = new ThirdPartySysApiClient(getBaseUrl(), getApiKey(), getApiSecret());
        SdkRequest request = createSdkRequest(GET_RESELLER_URL.replace("{resellerId}", resellerId + ""));
        request.setRequestMethod(RequestMethod.GET);
        ResellerResponse resellerResponseDTO = EnhancedJsonUtils.fromJson(client.execute(request), ResellerResponse.class);
        return new Result<>(resellerResponseDTO);
    }

    public Result<ResellerDTO> createReseller(ResellerCreateRequest resellerCreateRequest) {
        List<String> validationErrs = ResellerCreateRequestValidator.validate(resellerCreateRequest);
        if (validationErrs.size() > 0) {
            return new Result<>(validationErrs);
        }
        ThirdPartySysApiClient client = new ThirdPartySysApiClient(getBaseUrl(), getApiKey(), getApiSecret());
        SdkRequest request = createSdkRequest(CREATE_RESELLER_URL);
        request.setRequestMethod(RequestMethod.POST);
        request.addHeader(Constants.CONTENT_TYPE, Constants.CONTENT_TYPE_JSON);
        request.setRequestBody(new Gson().toJson(resellerCreateRequest, ResellerCreateRequest.class));
        ResellerResponse resellerResponseDTO = EnhancedJsonUtils.fromJson(client.execute(request), ResellerResponse.class);
        return new Result<>(resellerResponseDTO);
    }

    public Result<ResellerDTO> updateReseller(Long resellerId, ResellerUpdateRequest resellerUpdateRequest) {
        logger.debug("resellerId= {}", resellerId);
        List<String> validationErrs = ResellerUpdateRequestValidator.validate(resellerId, resellerUpdateRequest);
        if (validationErrs.size() > 0) {
            return new Result<>(validationErrs);
        }
        ThirdPartySysApiClient client = new ThirdPartySysApiClient(getBaseUrl(), getApiKey(), getApiSecret());
        SdkRequest request = createSdkRequest(UPDATE_RESELLER_URL.replace("{resellerId}", resellerId + ""));
        request.setRequestMethod(RequestMethod.PUT);
        request.addHeader(Constants.CONTENT_TYPE, Constants.CONTENT_TYPE_JSON);
        request.setRequestBody(new Gson().toJson(resellerUpdateRequest, ResellerUpdateRequest.class));
        ResellerResponse resellerResponseDTO = EnhancedJsonUtils.fromJson(client.execute(request), ResellerResponse.class);
        return new Result<>(resellerResponseDTO);
    }

    public Result<String> activateReseller(Long resellerId) {
        logger.debug("resellerId= {}", resellerId);
        List<String> validationErrs = Validators.validateId(resellerId, "parameter.id.invalid","resellerId");
        if (validationErrs.size() > 0) {
            return new Result<>(validationErrs);
        }
        ThirdPartySysApiClient client = new ThirdPartySysApiClient(getBaseUrl(), getApiKey(), getApiSecret());
        SdkRequest request = createSdkRequest(ACTIVATE_RESELLER_URL.replace("{resellerId}", resellerId.toString()));
        request.setRequestMethod(RequestMethod.PUT);
        EmptyResponse emptyResponse = EnhancedJsonUtils.fromJson(client.execute(request), EmptyResponse.class);
        return new Result<>(emptyResponse);
    }

    public Result<String> disableReseller(Long resellerId) {
        logger.debug("resellerId= {}", resellerId);
        List<String> validationErrs = Validators.validateId(resellerId, "parameter.id.invalid", "resellerId");
        if (validationErrs.size() > 0) {
            return new Result<>(validationErrs);
        }
        ThirdPartySysApiClient client = new ThirdPartySysApiClient(getBaseUrl(), getApiKey(), getApiSecret());
        SdkRequest request = createSdkRequest(DISABLE_RESELLER_URL.replace("{resellerId}", resellerId.toString()));
        request.setRequestMethod(RequestMethod.PUT);
        EmptyResponse emptyResponse = EnhancedJsonUtils.fromJson(client.execute(request), EmptyResponse.class);
        return new Result<>(emptyResponse);
    }

    public Result<String> deleteReseller(Long resellerId) {
        logger.debug("resellerId= {}", resellerId);
        List<String> validationErrs = Validators.validateId(resellerId, "parameter.id.invalid", "resellerId");
        if (validationErrs.size() > 0) {
            return new Result<>(validationErrs);
        }
        ThirdPartySysApiClient client = new ThirdPartySysApiClient(getBaseUrl(), getApiKey(), getApiSecret());
        SdkRequest request = createSdkRequest(DELETE_RESELLER_URL.replace("{resellerId}", resellerId.toString()));
        request.setRequestMethod(RequestMethod.DELETE);
        EmptyResponse emptyResponse = EnhancedJsonUtils.fromJson(client.execute(request), EmptyResponse.class);
        return new Result<>(emptyResponse);
    }

    public Result<String> replaceResellerEmail(Long resellerId, String email) {
        logger.debug("resellerId={}", resellerId);
        List<String> validationErrs = Validators.validateId(resellerId, "parameter.id.invalid","resellerId");
        if (!StringUtils.isValidEmailAddress(email)) {
            validationErrs.add(MessageBundleUtils.getMessage("parameter.email.invalid"));
        }
        if (email != null && email.length() > Constants.MAX_255) {
            validationErrs.add(MessageBundleUtils.getMessage("parameter.too.long", "email"));
        }
        if (validationErrs.size() > 0) {
            return new Result<>(validationErrs);
        }
        Map<String, String> requestBodyMap = new HashMap<>();
        requestBodyMap.put("email", email);
        ThirdPartySysApiClient client = new ThirdPartySysApiClient(getBaseUrl(), getApiKey(), getApiSecret());
        SdkRequest request = createSdkRequest(REPLACE_RESELLER_EMAIL_URL.replace("{resellerId}", resellerId.toString()));
        request.setRequestMethod(RequestMethod.POST);
        request.addHeader(Constants.CONTENT_TYPE, Constants.CONTENT_TYPE_JSON);
        request.setRequestBody(new Gson().toJson(requestBodyMap, Map.class));
        EmptyResponse emptyResponse = EnhancedJsonUtils.fromJson(client.execute(request), EmptyResponse.class);
        return new Result<>(emptyResponse);
    }

    public Result<ResellerRkiKeyPageDTO> searchResellerRkiKeyList(Long resellerId, int pageNo, int pageSize, String rkiKey) {
        ThirdPartySysApiClient client = new ThirdPartySysApiClient(getBaseUrl(), getApiKey(), getApiSecret());
        PageRequestDTO page = new PageRequestDTO();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);

        List<String> validationErrs = Validators.validatePageRequest(page);
        if (validationErrs.size() > 0) {
            return new Result<>(validationErrs);
        }
        SdkRequest request = getPageRequest(SEARCH_RESELLER_RKI_KET_TEMPLATE_LIST_URL.replace("{resellerId}", resellerId.toString()), page);
        request.addRequestParam("key", rkiKey);
        ResellerRkiKeyPageResponse pageResponse = EnhancedJsonUtils.fromJson(client.execute(request), ResellerRkiKeyPageResponse.class);
        return new Result<>(pageResponse);
    }

    public enum ResellerStatus {
        Active("A"),
        Inactive("P"),
        Suspend("S");
        private final String val;

		ResellerStatus(String status) {
            this.val = status;
        }

        public String val() {
            return this.val;
        }
    }

    public enum ResellerSearchOrderBy {
        Name("name"),
        Contact("contact"),
        Phone("phone");
        private final String val;

		ResellerSearchOrderBy(String orderBy) {
            this.val = orderBy;
        }

        public String val() {
            return this.val;
        }
    }


}
