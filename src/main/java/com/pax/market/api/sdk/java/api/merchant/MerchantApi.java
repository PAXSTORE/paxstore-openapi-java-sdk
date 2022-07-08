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
package com.pax.market.api.sdk.java.api.merchant;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.pax.market.api.sdk.java.api.BaseThirdPartySysApi;
import com.pax.market.api.sdk.java.api.base.dto.EmptyResponse;
import com.pax.market.api.sdk.java.api.base.dto.PageRequestDTO;
import com.pax.market.api.sdk.java.api.base.dto.Result;
import com.pax.market.api.sdk.java.api.base.request.SdkRequest;
import com.pax.market.api.sdk.java.api.base.request.SdkRequest.RequestMethod;
import com.pax.market.api.sdk.java.api.client.ThirdPartySysApiClient;
import com.pax.market.api.sdk.java.api.constant.Constants;
import com.pax.market.api.sdk.java.api.merchant.dto.MerchantCreateRequest;
import com.pax.market.api.sdk.java.api.merchant.dto.MerchantDTO;
import com.pax.market.api.sdk.java.api.merchant.dto.MerchantPageResponse;
import com.pax.market.api.sdk.java.api.merchant.dto.MerchantResponseDTO;
import com.pax.market.api.sdk.java.api.merchant.dto.MerchantUpdateRequest;
import com.pax.market.api.sdk.java.api.util.EnhancedJsonUtils;
import com.pax.market.api.sdk.java.api.util.MessageBoudleUtil;
import com.pax.market.api.sdk.java.api.util.StringUtils;
import com.pax.market.api.sdk.java.api.merchant.dto.MerchantPageDTO;

/**
 *
 * @author tanjie
 * @date 2018-07-03
 */
public class MerchantApi extends BaseThirdPartySysApi{
	private static final Logger logger = LoggerFactory.getLogger(MerchantApi.class.getSimpleName());
	
	private static final String SEARCH_MERCHANT_URL = "/v1/3rdsys/merchants";
	private static final String GET_MERCHANT_URL = "/v1/3rdsys/merchants/{merchantId}";
	private static final String CREATE_MERCHANT_URL = "/v1/3rdsys/merchants";
	private static final String UPDATE_MERCHANT_URL = "/v1/3rdsys/merchants/{merchantId}";
	private static final String ACTIVATE_MERCHANT_URL = "/v1/3rdsys/merchants/{merchantId}/active";
	private static final String DISABLE_MERCHANT_URL = "/v1/3rdsys/merchants/{merchantId}/disable";
	private static final String DELETE_MERCHANT_URL = "/v1/3rdsys/merchants/{merchantId}";
	private static final String REPLACE_MERCHANT_EMAIL_URL = "/v1/3rdsys/merchants/{merchantId}/replaceEmail";

	public MerchantApi(String baseUrl, String apiKey, String apiSecret) {
		super(baseUrl, apiKey, apiSecret);
	}
	
	public MerchantApi(String baseUrl, String apiKey, String apiSecret, Locale locale) {
		super(baseUrl, apiKey, apiSecret, locale);
	}
	
	public Result<MerchantPageDTO>  searchMerchant(int pageNo, int pageSize, MerchantSearchOrderBy orderBy, String name, MerchantStatus status) {
		logger.debug("name="+name+"|status="+status);
		ThirdPartySysApiClient client = new ThirdPartySysApiClient(getBaseUrl(), getApiKey(), getApiSecret());
		PageRequestDTO page = new PageRequestDTO();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		if(orderBy != null) {
			page.setOrderBy(orderBy.val);
		}
		
		List<String> validationErrs = validate(page);
		if(validationErrs.size()>0) {
			return new Result<MerchantPageDTO>(validationErrs);
		}
		SdkRequest request = getPageRequest(SEARCH_MERCHANT_URL, page);
		request.addRequestParam("name", name);
		if(status!=null) {
			request.addRequestParam("status", status.val);
		}
        MerchantPageResponse merchantPageDTO = EnhancedJsonUtils.fromJson(client.execute(request), MerchantPageResponse.class);
        Result<MerchantPageDTO> result = new Result<MerchantPageDTO>(merchantPageDTO);
        return result;
	}
	

	
	public Result<MerchantDTO>  getMerchant(Long merchantId) {
		logger.debug("merchantId="+merchantId);
		List<String> validationErrs = validateId(merchantId, "parameter.merchantId.invalid");
		if(validationErrs.size()>0) {
			return new Result<MerchantDTO>(validationErrs);
		}
		ThirdPartySysApiClient client = new ThirdPartySysApiClient(getBaseUrl(), getApiKey(), getApiSecret());
		SdkRequest request = createSdkRequest(GET_MERCHANT_URL.replace("{merchantId}", merchantId+""));
		request.setRequestMethod(RequestMethod.GET);
        MerchantResponseDTO merchantResponseDTO = EnhancedJsonUtils.fromJson(client.execute(request), MerchantResponseDTO.class);
        Result<MerchantDTO> result = new Result<MerchantDTO>(merchantResponseDTO);
        return result;
	}
	
	public Result<MerchantDTO>  createMerchant(MerchantCreateRequest merchantCreateRequest) {
		List<String> validationErrs = validateCreate(merchantCreateRequest, "parameter.merchantCreateRequest.null");
		
		if(validationErrs.size()>0) {
			return new Result<MerchantDTO>(validationErrs);
		}
		ThirdPartySysApiClient client = new ThirdPartySysApiClient(getBaseUrl(), getApiKey(), getApiSecret());
		SdkRequest request = createSdkRequest(CREATE_MERCHANT_URL);
		request.setRequestMethod(RequestMethod.POST);
		request.addHeader(Constants.CONTENT_TYPE, Constants.CONTENT_TYPE_JSON);
		request.setRequestBody(new Gson().toJson(merchantCreateRequest, MerchantCreateRequest.class));
		MerchantResponseDTO merchantResponseDTO = EnhancedJsonUtils.fromJson(client.execute(request), MerchantResponseDTO.class);
        Result<MerchantDTO> result = new Result<MerchantDTO>(merchantResponseDTO);
        return result;
	}
	
	public Result<MerchantDTO>  updateMerchant(Long merchantId, MerchantUpdateRequest merchantUpdateRequest) {
		logger.debug("merchantId="+merchantId);
		List<String> validationErrs = validateUpdate(merchantId, merchantUpdateRequest, "parameter.merchantId.invalid", "parameter.merchantUpdateRequest.null");
		if(validationErrs.size()>0) {
			return new Result<MerchantDTO>(validationErrs);
		}
		ThirdPartySysApiClient client = new ThirdPartySysApiClient(getBaseUrl(), getApiKey(), getApiSecret());
		SdkRequest request = createSdkRequest(UPDATE_MERCHANT_URL.replace("{merchantId}", merchantId+""));
		request.setRequestMethod(RequestMethod.PUT);
		request.addHeader(Constants.CONTENT_TYPE, Constants.CONTENT_TYPE_JSON);
		request.setRequestBody(new Gson().toJson(merchantUpdateRequest, MerchantUpdateRequest.class));
		MerchantResponseDTO merchantResponseDTO = EnhancedJsonUtils.fromJson(client.execute(request), MerchantResponseDTO.class);
        Result<MerchantDTO> result = new Result<MerchantDTO>(merchantResponseDTO);
        return result;
	}
	
	public Result<String> activateMerchant(Long merchantId) {
		logger.debug("merchantId="+merchantId);
		List<String> validationErrs = validateId(merchantId, "parameter.merchantId.invalid");
		if(validationErrs.size()>0) {
			return new Result<String>(validationErrs);
		}
		ThirdPartySysApiClient client = new ThirdPartySysApiClient(getBaseUrl(), getApiKey(), getApiSecret());
		SdkRequest request = createSdkRequest(ACTIVATE_MERCHANT_URL.replace("{merchantId}", merchantId.toString()));
		request.setRequestMethod(RequestMethod.PUT);
		EmptyResponse emptyResponse =  EnhancedJsonUtils.fromJson(client.execute(request), EmptyResponse.class);
		Result<String> result = new Result<String>(emptyResponse);
		return result;
	}
	
	public Result<String> disableMerchant(Long merchantId) {
		logger.debug("merchantId="+merchantId);
		List<String> validationErrs = validateId(merchantId, "parameter.merchantId.invalid");
		if(validationErrs.size()>0) {
			return new Result<String>(validationErrs);
		}
		ThirdPartySysApiClient client = new ThirdPartySysApiClient(getBaseUrl(), getApiKey(), getApiSecret());
		SdkRequest request = createSdkRequest(DISABLE_MERCHANT_URL.replace("{merchantId}", merchantId.toString()));
		request.setRequestMethod(RequestMethod.PUT);
		EmptyResponse emptyResponse =  EnhancedJsonUtils.fromJson(client.execute(request), EmptyResponse.class);
		Result<String> result = new Result<String>(emptyResponse);
		return result;
	}
	
	public Result<String> deleteMerchant(Long merchantId) {
		logger.debug("merchantId="+merchantId);
		List<String> validationErrs = validateId(merchantId, "parameter.merchantId.invalid");
		if(validationErrs.size()>0) {
			return new Result<String>(validationErrs);
		}
		ThirdPartySysApiClient client = new ThirdPartySysApiClient(getBaseUrl(), getApiKey(), getApiSecret());
		SdkRequest request = createSdkRequest(DELETE_MERCHANT_URL.replace("{merchantId}", merchantId.toString()));
		request.setRequestMethod(RequestMethod.DELETE);
		EmptyResponse emptyResponse =  EnhancedJsonUtils.fromJson(client.execute(request), EmptyResponse.class);
		Result<String> result = new Result<String>(emptyResponse);
		return result;
	} 
	
	public Result<String> replaceMerchantEmail(Long merchantId, String email, boolean createUser){
		logger.debug("merchantId="+merchantId);
		List<String> validationErrs = validateId(merchantId, "parameter.merchantId.invalid");
		if(StringUtils.isNotBlank(email) && !StringUtils.isValidEmailAddress(email)) {
			validationErrs.add(MessageBoudleUtil.getMessage("parameter.email.invalid", Locale.getDefault()));
		}
		if(StringUtils.length(email) > 255) {
			validationErrs.add(MessageBoudleUtil.getMessage("parameter.email.toolong", Locale.getDefault()));
		}
		if(validationErrs.size()>0) {
			return new Result<String>(validationErrs);
		}
		ThirdPartySysApiClient client = new ThirdPartySysApiClient(getBaseUrl(), getApiKey(), getApiSecret());
		SdkRequest request = createSdkRequest(REPLACE_MERCHANT_EMAIL_URL.replace("{merchantId}", merchantId.toString()));
		request.setRequestMethod(RequestMethod.POST);
		request.addHeader(Constants.CONTENT_TYPE, Constants.CONTENT_TYPE_JSON);
		Map<String, Object> requestBodyMap = new HashMap<String, Object>();
		requestBodyMap.put("email", email);
		requestBodyMap.put("createUser", createUser);
		request.setRequestBody(new Gson().toJson(requestBodyMap, Map.class));

		EmptyResponse emptyResponse =  EnhancedJsonUtils.fromJson(client.execute(request), EmptyResponse.class);
		Result<String> result = new Result<String>(emptyResponse);
		return result;
	}
	
	public enum MerchantStatus {
		Active("A"),
		Inactive("P"),
		Suspend("S");
		private String val;
		private MerchantStatus(String status) {
			this.val = status;
		}
		public String val() {
			return this.val;
		}
	}
	
	public enum MerchantSearchOrderBy {
		Name("name"),
		Phone("phone"),
		Contact("contact");
		private String val;
		private MerchantSearchOrderBy(String orderBy) {
			this.val = orderBy;
		}
		public String val() {
			return this.val;
		}
	}

}
