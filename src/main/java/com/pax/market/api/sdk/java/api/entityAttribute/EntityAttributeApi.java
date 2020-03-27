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
package com.pax.market.api.sdk.java.api.entityAttribute;

import com.google.gson.Gson;
import com.pax.market.api.sdk.java.api.BaseThirdPartySysApi;
import com.pax.market.api.sdk.java.api.base.dto.EmptyResponse;
import com.pax.market.api.sdk.java.api.base.dto.PageRequestDTO;
import com.pax.market.api.sdk.java.api.base.dto.Result;
import com.pax.market.api.sdk.java.api.base.request.SdkRequest;
import com.pax.market.api.sdk.java.api.client.ThirdPartySysApiClient;
import com.pax.market.api.sdk.java.api.constant.Constants;
import com.pax.market.api.sdk.java.api.entityAttribute.dto.*;
import com.pax.market.api.sdk.java.api.terminalVariable.TerminalVariableApi;
import com.pax.market.api.sdk.java.api.util.EnhancedJsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Locale;

/**
 * @Description
 * @Author: Shawn
 * @Date: 2019/11/15 18:05
 * @Version 7.1
 */
public class EntityAttributeApi  extends BaseThirdPartySysApi {
    private static final Logger logger = LoggerFactory.getLogger(TerminalVariableApi.class);

    private static final String GET_ENTITY_ATTRIBUTES_URL = "/v1/3rdsys/attributes/{attributeId}";
    private static final String SEARCH_ENTITY_ATTRIBUTES_URL = "/v1/3rdsys/attributes";
    private static final String CREATE_ENTITY_ATTRIBUTES_URL = "/v1/3rdsys/attributes";
    private static final String UPDATE_ENTITY_ATTRIBUTES_URL = "/v1/3rdsys/attributes/{attributeId}";
    private static final String UPDATE_ENTITY_ATTRIBUTES_LABEL_URL = "/v1/3rdsys/attributes/{attributeId}/label";
    private static final String DELETE_ENTITY_ATTRIBUTES_URL = "/v1/3rdsys/attributes/{attributeId}";


    public EntityAttributeApi(String baseUrl, String apiKey, String apiSecret) {
        super(baseUrl, apiKey, apiSecret);
    }

    public EntityAttributeApi(String baseUrl, String apiKey, String apiSecret, Locale locale) {
        super(baseUrl, apiKey, apiSecret, locale);
    }

    public Result<EntityAttributeDTO> getEntityAttribute(Long attributeId){
        logger.debug("attributeId="+attributeId);
        List<String> validationErrs = validateId(attributeId, "parameter.attributeId.invalid");
        if(validationErrs.size()>0) {
            return new Result<EntityAttributeDTO>(validationErrs);
        }
        ThirdPartySysApiClient client = new ThirdPartySysApiClient(getBaseUrl(), getApiKey(), getApiSecret());
        SdkRequest request = createSdkRequest(GET_ENTITY_ATTRIBUTES_URL.replace("{attributeId}", attributeId+""));
        request.setRequestMethod(SdkRequest.RequestMethod.GET);
        EntityAttributeResponse entityAttributeResponse = EnhancedJsonUtils.fromJson(client.execute(request), EntityAttributeResponse.class);
        Result<EntityAttributeDTO> result = new Result<EntityAttributeDTO>(entityAttributeResponse);
        return result;
    }

    public Result<EntityAttributeDTO> searchEntityAttributes(int pageNo, int pageSize, SearchOrderBy orderBy, String key , EntityAttributeType entityType){
        logger.debug("key="+key+"|entityType="+entityType);
        ThirdPartySysApiClient client = new ThirdPartySysApiClient(getBaseUrl(), getApiKey(), getApiSecret());
        PageRequestDTO page = new PageRequestDTO();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        if(orderBy!=null) {
            page.setOrderBy(orderBy.val);
        }
        List<String> validationErrs = validate(page);
        if(validationErrs.size()>0) {
            return new Result<EntityAttributeDTO>(validationErrs);
        }
        SdkRequest request = getPageRequest(SEARCH_ENTITY_ATTRIBUTES_URL, page);
       if (key !=null){
           request.addRequestParam("key", key);
       }
       if(entityType!=null) {
            request.addRequestParam("entityType", entityType.val());
        }
        EntityAttributePageResponse resp = EnhancedJsonUtils.fromJson(client.execute(request), EntityAttributePageResponse.class);
        Result<EntityAttributeDTO> result = new Result<EntityAttributeDTO>(resp);
        return result;
    }


    public Result<EntityAttributeDTO> createEntityAttribute(EntityAttributeCreateRequest createRequest){
        List<String> validationErrs = validateCreate( createRequest,"parameter.attributeCreateRequest.null");
        if(validationErrs.size()>0) {
            return new Result<EntityAttributeDTO>(validationErrs);
        }

        ThirdPartySysApiClient client = new ThirdPartySysApiClient(getBaseUrl(), getApiKey(), getApiSecret());
        SdkRequest request = createSdkRequest(CREATE_ENTITY_ATTRIBUTES_URL);
        request.setRequestMethod(SdkRequest.RequestMethod.POST);
        request.addHeader(Constants.CONTENT_TYPE, Constants.CONTENT_TYPE_JSON);
        request.setRequestBody(new Gson().toJson(createRequest, EntityAttributeCreateRequest.class));
        EntityAttributeResponse entityAttributeResponse = EnhancedJsonUtils.fromJson(client.execute(request), EntityAttributeResponse.class);
        Result<EntityAttributeDTO> result = new Result<EntityAttributeDTO>(entityAttributeResponse);
        return result;
    }


    public Result<EntityAttributeDTO>  updateEntityAttribute(Long attributeId, EntityAttributeUpdateRequest updateRequest) {
        logger.debug("attributeId="+attributeId);
        List<String> validationErrs = validateUpdate(attributeId, updateRequest,"parameter.attributeId.invalid","parameter.attributeUpdateRequest.null");
        if(validationErrs.size()>0) {
            return new Result<EntityAttributeDTO>(validationErrs);
        }
        ThirdPartySysApiClient client = new ThirdPartySysApiClient(getBaseUrl(), getApiKey(), getApiSecret());
        SdkRequest request = createSdkRequest(UPDATE_ENTITY_ATTRIBUTES_URL.replace("{attributeId}", attributeId+""));
        request.setRequestMethod(SdkRequest.RequestMethod.PUT);
        request.addHeader(Constants.CONTENT_TYPE, Constants.CONTENT_TYPE_JSON);
        request.setRequestBody(new Gson().toJson(updateRequest, EntityAttributeUpdateRequest.class));
        EntityAttributeResponse entityAttributeResponse = EnhancedJsonUtils.fromJson(client.execute(request), EntityAttributeResponse.class);
        Result<EntityAttributeDTO> result = new Result<EntityAttributeDTO>(entityAttributeResponse);
        return result;
    }

    public Result<String>  updateEntityAttributeLabel(Long attributeId, EntityAttributeLabelUpdateRequest updateLabelRequest) {
        List<String> validationErr = validateAttributeId(attributeId);
        if(updateLabelRequest == null) {
            validationErr.add(getMessage("parameter.updateLabelRequest.null"));
        }
        if(!validationErr.isEmpty()){
            return new Result<>(validationErr);
        }
        ThirdPartySysApiClient client = new ThirdPartySysApiClient(getBaseUrl(), getApiKey(), getApiSecret());
        SdkRequest request = createSdkRequest(UPDATE_ENTITY_ATTRIBUTES_LABEL_URL.replace("{attributeId}", attributeId.toString()));
        request.setRequestMethod(SdkRequest.RequestMethod.PUT);
        request.addHeader(Constants.CONTENT_TYPE, Constants.CONTENT_TYPE_JSON);
        request.setRequestBody(new Gson().toJson(updateLabelRequest, EntityAttributeLabelUpdateRequest.class));
        EmptyResponse emptyResponse =  EnhancedJsonUtils.fromJson(client.execute(request), EmptyResponse.class);
        Result<String> result = new Result<String>(emptyResponse);
        return result;
    }

    public Result<String> deleteEntityAttribute(Long attributeId){
        List<String> validationErrs = validateAttributeId(attributeId);
        if(!validationErrs.isEmpty()) {
            return new Result<>(validationErrs);
        }
        ThirdPartySysApiClient client = new ThirdPartySysApiClient(getBaseUrl(), getApiKey(), getApiSecret());
        SdkRequest request = createSdkRequest(DELETE_ENTITY_ATTRIBUTES_URL.replace("{attributeId}", attributeId.toString()));
        request.setRequestMethod(SdkRequest.RequestMethod.DELETE);
        EmptyResponse emptyResponse =  EnhancedJsonUtils.fromJson(client.execute(request), EmptyResponse.class);
        Result<String> result = new Result<String>(emptyResponse);
        return result;
    }

    public enum SearchOrderBy {
        EntityType_desc("a.entity_type DESC"),
        EntityType_asc("a.entity_type ASC");
        private String val;
        private SearchOrderBy(String orderBy) {
            this.val = orderBy;
        }
        public String val(){
            return this.val;
        }
    }

    public enum EntityAttributeType {
        Merchant("Merchant"),
        Reseller("Reseller");
        private String val;
        private EntityAttributeType(String entityType) {
            this.val = entityType;
        }
        public String val(){
            return this.val;
        }
    }

    public enum EntityInputType {
        Text("TEXT"),
        Selector("SELECTOR");
        private String val;
        private EntityInputType(String inputType) {
            this.val = inputType;
        }
        public String val(){
            return this.val;
        }
    }

    private List<String>  validateAttributeId(Long attributeId){
        logger.debug("attributeId="+attributeId);
        List<String> validationErrs = validateId(attributeId,"parameter.attributeId.invalid");
        return validationErrs;
    }

}
