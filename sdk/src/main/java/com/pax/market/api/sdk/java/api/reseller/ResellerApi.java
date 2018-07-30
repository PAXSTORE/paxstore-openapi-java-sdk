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

import java.util.List;
import java.util.Locale;

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
import com.pax.market.api.sdk.java.api.reseller.dto.ResellerCreateRequest;
import com.pax.market.api.sdk.java.api.reseller.dto.ResellerDTO;
import com.pax.market.api.sdk.java.api.reseller.dto.ResellerPageDTO;
import com.pax.market.api.sdk.java.api.reseller.dto.ResellerPageResponse;
import com.pax.market.api.sdk.java.api.reseller.dto.ResellerResponse;
import com.pax.market.api.sdk.java.api.reseller.dto.ResellerUpdateRequest;
import com.pax.market.api.sdk.java.api.util.EnhancedJsonUtils;

/**
 *
 * @author tanjie
 * @date 2018-07-03
 */
public class ResellerApi extends BaseThirdPartySysApi{
	private static final Logger logger = LoggerFactory.getLogger(ResellerApi.class.getSimpleName());
	
	private static final String SEARCH_RESELLER_URL = "/v1/3rdsys/resellers";
	private static final String GET_RESELLER_URL = "/v1/3rdsys/resellers/{resellerId}";
	private static final String CREATE_RESELLER_URL = "/v1/3rdsys/resellers";
	private static final String UPDATE_RESELLER_URL = "/v1/3rdsys/resellers/{resellerId}";
	private static final String ACTIVATE_RESELLER_URL = "/v1/3rdsys/resellers/{resellerId}/active";
	private static final String DISABLE_RESELLER_URL = "/v1/3rdsys/resellers/{resellerId}/disable";
	private static final String DELETE_RESELLER_URL = "/v1/3rdsys/resellers/{resellerId}";

	public ResellerApi(String baseUrl, String appKey, String appSecret) {
		super(baseUrl, appKey, appSecret);
	}
	
	public ResellerApi(String baseUrl, String appKey, String appSecret, Locale locale) {
		super(baseUrl, appKey, appSecret, locale);
	}
	
	public Result<ResellerPageDTO>  searchReseller(int pageNo, int pageSize, ResellerSearchOrderBy orderBy, String name, ResellerStatus status) {
		logger.debug("name="+name+"|status="+status);
		ThirdPartySysApiClient client = new ThirdPartySysApiClient(getBaseUrl(), getAppKey(), getAppSecret());
		PageRequestDTO page = new PageRequestDTO();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		if(orderBy!=null) {
			page.setOrderBy(orderBy.val);
		}
		List<String> validationErrs = validate(page);
		if(validationErrs.size()>0) {
			return new Result<ResellerPageDTO>(validationErrs);
		}
		SdkRequest request = getPageRequest(SEARCH_RESELLER_URL, page);
		request.addRequestParam("name", name);
		if(status!=null) {
			request.addRequestParam("status", status.val);
		}
		ResellerPageResponse resellerPageDTO = EnhancedJsonUtils.fromJson(client.execute(request), ResellerPageResponse.class);
        Result<ResellerPageDTO> result = new Result<ResellerPageDTO>(resellerPageDTO);
        return result;
	}
	
	public Result<ResellerDTO>  getReseller(Long resellerId) {
		logger.debug("resellerId="+resellerId);
		List<String> validationErrs = validateId(resellerId, "parameter.resellerId.invalid");
		if(validationErrs.size()>0) {
			return new Result<ResellerDTO>(validationErrs);
		}
		ThirdPartySysApiClient client = new ThirdPartySysApiClient(getBaseUrl(), getAppKey(), getAppSecret());
		SdkRequest request = createSdkRequest(GET_RESELLER_URL.replace("{resellerId}", resellerId+""));
		request.setRequestMethod(RequestMethod.GET);
        ResellerResponse resellerResponseDTO = EnhancedJsonUtils.fromJson(client.execute(request), ResellerResponse.class);
        Result<ResellerDTO> result = new Result<ResellerDTO>(resellerResponseDTO);
        return result;
	}
	
	public Result<ResellerDTO>  createReseller(ResellerCreateRequest resellerCreateRequest) {
		List<String> validationErrs = validateCreate(resellerCreateRequest, "parameter.resellerCreateRequest.null");
		
		if(validationErrs.size()>0) {
			return new Result<ResellerDTO>(validationErrs);
		}
		ThirdPartySysApiClient client = new ThirdPartySysApiClient(getBaseUrl(), getAppKey(), getAppSecret());
		SdkRequest request = createSdkRequest(CREATE_RESELLER_URL);
		request.setRequestMethod(RequestMethod.POST);
		request.addHeader(Constants.CONTENT_TYPE, Constants.CONTENT_TYPE_JSON);
		request.setRequestBody(new Gson().toJson(resellerCreateRequest, ResellerCreateRequest.class));
		ResellerResponse resellerResponseDTO = EnhancedJsonUtils.fromJson(client.execute(request), ResellerResponse.class);
        Result<ResellerDTO> result = new Result<ResellerDTO>(resellerResponseDTO);
        return result;
	}
	
	public Result<ResellerDTO>  updateReseller(Long resellerId, ResellerUpdateRequest resellerUpdateRequest) {
		logger.debug("resellerId="+resellerId);
		List<String> validationErrs = validateUpdate(resellerId, resellerUpdateRequest, "parameter.resellerId.invalid", "parameter.resellerUpdateRequest.null");
		if(validationErrs.size()>0) {
			return new Result<ResellerDTO>(validationErrs);
		}
		ThirdPartySysApiClient client = new ThirdPartySysApiClient(getBaseUrl(), getAppKey(), getAppSecret());
		SdkRequest request = createSdkRequest(UPDATE_RESELLER_URL.replace("{resellerId}", resellerId+""));
		request.setRequestMethod(RequestMethod.PUT);
		request.addHeader(Constants.CONTENT_TYPE, Constants.CONTENT_TYPE_JSON);
		request.setRequestBody(new Gson().toJson(resellerUpdateRequest, ResellerUpdateRequest.class));
		ResellerResponse resellerResponseDTO = EnhancedJsonUtils.fromJson(client.execute(request), ResellerResponse.class);
        Result<ResellerDTO> result = new Result<ResellerDTO>(resellerResponseDTO);
        return result;
	}
	
	public Result<String> activateReseller(Long resellerId) {
		logger.debug("resellerId="+resellerId);
		List<String> validationErrs = validateId(resellerId, "parameter.resellerId.invalid");
		if(validationErrs.size()>0) {
			return new Result<String>(validationErrs);
		}
		ThirdPartySysApiClient client = new ThirdPartySysApiClient(getBaseUrl(), getAppKey(), getAppSecret());
		SdkRequest request = createSdkRequest(ACTIVATE_RESELLER_URL.replace("{resellerId}", resellerId.toString()));
		request.setRequestMethod(RequestMethod.PUT);
		EmptyResponse emptyResponse =  EnhancedJsonUtils.fromJson(client.execute(request), EmptyResponse.class);
		Result<String> result = new Result<String>(emptyResponse);
		return result;
	}
	
	public Result<String> disableReseller(Long resellerId) {
		logger.debug("resellerId="+resellerId);
		List<String> validationErrs = validateId(resellerId, "parameter.resellerId.invalid");
		if(validationErrs.size()>0) {
			return new Result<String>(validationErrs);
		}
		ThirdPartySysApiClient client = new ThirdPartySysApiClient(getBaseUrl(), getAppKey(), getAppSecret());
		SdkRequest request = createSdkRequest(DISABLE_RESELLER_URL.replace("{resellerId}", resellerId.toString()));
		request.setRequestMethod(RequestMethod.PUT);
		EmptyResponse emptyResponse =  EnhancedJsonUtils.fromJson(client.execute(request), EmptyResponse.class);
		Result<String> result = new Result<String>(emptyResponse);
		return result;
	}
	
	public Result<String> deleteReseller(Long resellerId) {
		logger.debug("resellerId="+resellerId);
		List<String> validationErrs = validateId(resellerId, "parameter.resellerId.invalid");
		if(validationErrs.size()>0) {
			return new Result<String>(validationErrs);
		}
		ThirdPartySysApiClient client = new ThirdPartySysApiClient(getBaseUrl(), getAppKey(), getAppSecret());
		SdkRequest request = createSdkRequest(DELETE_RESELLER_URL.replace("{resellerId}", resellerId.toString()));
		request.setRequestMethod(RequestMethod.DELETE);
		EmptyResponse emptyResponse =  EnhancedJsonUtils.fromJson(client.execute(request), EmptyResponse.class);
		Result<String> result = new Result<String>(emptyResponse);
		return result;
	} 
	
	public enum ResellerStatus {
		Active("A"),
		Inactive("P"),
		Suspend("S");
		private String val;
		private ResellerStatus(String status) {
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
		private String val;
		private ResellerSearchOrderBy(String orderBy) {
			this.val = orderBy;
		}
		public String val() {
			return this.val;
		}
	}

}
