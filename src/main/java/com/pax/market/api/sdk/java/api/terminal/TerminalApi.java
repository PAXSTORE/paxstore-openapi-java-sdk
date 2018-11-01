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
package com.pax.market.api.sdk.java.api.terminal;


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
import com.pax.market.api.sdk.java.api.terminal.dto.TerminalCreateRequest;
import com.pax.market.api.sdk.java.api.terminal.dto.TerminalDTO;
import com.pax.market.api.sdk.java.api.terminal.dto.TerminalPageResponse;
import com.pax.market.api.sdk.java.api.terminal.dto.TerminalResponseDTO;
import com.pax.market.api.sdk.java.api.terminal.dto.TerminalUpdateRequest;
import com.pax.market.api.sdk.java.api.util.EnhancedJsonUtils;

/**
 *
 * @author tanjie
 * @date 2018-07-02
 */
public class TerminalApi extends BaseThirdPartySysApi{
	private static final Logger logger = LoggerFactory.getLogger(TerminalApi.class.getSimpleName());
	protected static final String SEARCH_TERMINAL_URL = "/v1/3rdsys/terminals";
	
	protected static final String GET_TERMINAL_URL = "/v1/3rdsys/terminals/{terminalId}";
	
	protected static final String ACTIVE_TERMINAL_URL = "/v1/3rdsys/terminals/{terminalId}/active";
	
	protected static final String DISABLE_TERMINAL_URL = "/v1/3rdsys/terminals/{terminalId}/disable";
	
	protected static final String DELETE_TERMINAL_URL = "/v1/3rdsys/terminals/{terminalId}";
	
	protected static final String CREATE_TERMINAL_URL = "/v1/3rdsys/terminals";
	
	protected static final String UPDATE_TERMINAL_URL = "/v1/3rdsys/terminals/{terminalId}";
	

	public TerminalApi(String baseUrl, String apiKey, String apiSecret) {
		super(baseUrl, apiKey, apiSecret);
	}
	
	public TerminalApi(String baseUrl, String apiKey, String apiSecret, Locale locale) {
		super(baseUrl, apiKey, apiSecret, locale);
	}
	
	
	public Result<TerminalDTO> searchTerminal(int pageNo, int pageSize, TerminalSearchOrderBy orderBy, TerminalStatus status, String snNameTID) {
		logger.debug("status="+status);
		ThirdPartySysApiClient client = new ThirdPartySysApiClient(getBaseUrl(), getApiKey(), getApiSecret());
		PageRequestDTO page = new PageRequestDTO();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		if(orderBy!=null) {
			page.setOrderBy(orderBy.val);
		}
		List<String> validationErrs = validate(page);
		if(validationErrs.size()>0) {
			return new Result<TerminalDTO>(validationErrs);
		}
		SdkRequest request = getPageRequest(SEARCH_TERMINAL_URL, page);
		if(status!=null) {
			request.addRequestParam("status", status.val);
		}
		request.addRequestParam("snNameTID", snNameTID);
		request.addRequestParam("serialNo", snNameTID);
		
        TerminalPageResponse terminalPageDTO = EnhancedJsonUtils.fromJson(client.execute(request), TerminalPageResponse.class);
        Result<TerminalDTO> result = new Result<TerminalDTO>(terminalPageDTO);

        return result;
	}
	

	public Result<TerminalDTO> getTerminal(Long terminalId) {
		logger.debug("terminalId="+terminalId);
		List<String> validationErrs = validateId(terminalId, "parameter.terminalId.invalid");
		if(validationErrs.size()>0) {
			return new Result<TerminalDTO>(validationErrs);
		}
		ThirdPartySysApiClient client = new ThirdPartySysApiClient(getBaseUrl(), getApiKey(), getApiSecret());
		SdkRequest request = createSdkRequest(GET_TERMINAL_URL.replace("{terminalId}", terminalId.toString()));
		TerminalResponseDTO terminalResponse = EnhancedJsonUtils.fromJson(client.execute(request), TerminalResponseDTO.class);
		Result<TerminalDTO> result = new Result<TerminalDTO>(terminalResponse);
		return result;
	}
	
	public Result<String> activateTerminal(Long terminalId) {
		logger.debug("terminalId="+terminalId);
		List<String> validationErrs = validateId(terminalId, "parameter.terminalId.invalid");
		if(validationErrs.size()>0) {
			return new Result<String>(validationErrs);
		}
		ThirdPartySysApiClient client = new ThirdPartySysApiClient(getBaseUrl(), getApiKey(), getApiSecret());
		SdkRequest request = createSdkRequest(ACTIVE_TERMINAL_URL.replace("{terminalId}", terminalId.toString()));
		request.setRequestMethod(RequestMethod.PUT);
		EmptyResponse emptyResponse =  EnhancedJsonUtils.fromJson(client.execute(request), EmptyResponse.class);
		Result<String> result = new Result<String>(emptyResponse);
		return result;
	}
	
	public Result<String> disableTerminal(Long terminalId) {
		logger.debug("terminalId="+terminalId);
		List<String> validationErrs = validateId(terminalId, "parameter.terminalId.invalid");
		if(validationErrs.size()>0) {
			return new Result<String>(validationErrs);
		}
		ThirdPartySysApiClient client = new ThirdPartySysApiClient(getBaseUrl(), getApiKey(), getApiSecret());
		SdkRequest request = createSdkRequest(DISABLE_TERMINAL_URL.replace("{terminalId}", terminalId.toString()));
		request.setRequestMethod(RequestMethod.PUT);
		EmptyResponse emptyResponse =  EnhancedJsonUtils.fromJson(client.execute(request), EmptyResponse.class);
		Result<String> result = new Result<String>(emptyResponse);
		return result;
	}
	
	public Result<String> deleteTerminal(Long terminalId) {
		logger.debug("terminalId="+terminalId);
		List<String> validationErrs = validateId(terminalId, "parameter.terminalId.invalid");
		if(validationErrs.size()>0) {
			return new Result<String>(validationErrs);
		}
		ThirdPartySysApiClient client = new ThirdPartySysApiClient(getBaseUrl(), getApiKey(), getApiSecret());
		SdkRequest request = createSdkRequest(DELETE_TERMINAL_URL.replace("{terminalId}", terminalId.toString()));
		request.setRequestMethod(RequestMethod.DELETE);
		EmptyResponse emptyResponse =  EnhancedJsonUtils.fromJson(client.execute(request), EmptyResponse.class);
		Result<String> result = new Result<String>(emptyResponse);
		return result;
	}
	
	public Result<TerminalDTO> createTerminal(TerminalCreateRequest terminalCreateRequest){
		List<String> validationErrs = validateCreate(terminalCreateRequest, "parameter.terminalCreateRequest.null");
		if(validationErrs.size()>0) {
			return new Result<TerminalDTO>(validationErrs);
		}
		ThirdPartySysApiClient client = new ThirdPartySysApiClient(getBaseUrl(), getApiKey(), getApiSecret());
		SdkRequest request = createSdkRequest(CREATE_TERMINAL_URL);
		request.setRequestMethod(RequestMethod.POST);
		request.setRequestBody(new Gson().toJson(terminalCreateRequest, TerminalCreateRequest.class));
		request.addHeader(Constants.CONTENT_TYPE, Constants.CONTENT_TYPE_JSON);
		TerminalResponseDTO terminalResponse =  EnhancedJsonUtils.fromJson(client.execute(request), TerminalResponseDTO.class);
		Result<TerminalDTO> result = new Result<TerminalDTO>(terminalResponse);
		return result;
	}
	
	public Result<TerminalDTO> updateTerminal(Long terminalId,TerminalUpdateRequest terminalUpdateRequest){
		logger.debug("terminalId="+terminalId);
		List<String> validationErrs = validateUpdate(terminalId, terminalUpdateRequest, "parameter.terminalId.invalid", "parameter.terminalUpdateRequest.null");
		if(validationErrs.size()>0) {
			return new Result<TerminalDTO>(validationErrs);
		}
		ThirdPartySysApiClient client = new ThirdPartySysApiClient(getBaseUrl(), getApiKey(), getApiSecret());
		SdkRequest request = createSdkRequest(UPDATE_TERMINAL_URL.replace("{terminalId}", terminalId.toString()));
		request.setRequestMethod(RequestMethod.PUT);
		request.addHeader(Constants.CONTENT_TYPE, Constants.CONTENT_TYPE_JSON);
		request.setRequestBody(new Gson().toJson(terminalUpdateRequest, TerminalUpdateRequest.class));
		TerminalResponseDTO terminalResponse =  EnhancedJsonUtils.fromJson(client.execute(request), TerminalResponseDTO.class);
		Result<TerminalDTO> result = new Result<TerminalDTO>(terminalResponse);
		return result;
	}
	
	public enum TerminalStatus {
		Active("A"),
		Inactive("P"),
		Suspend("S");
		private String val;
		private TerminalStatus(String status) {
			this.val = status;
		}
		public String val() {
			return this.val;
		}
	}
	
	public enum TerminalSearchOrderBy {
		Name("name"),
		Tid("tid"),
		SerialNo("serialNo");
		
		private String val;
		
		private TerminalSearchOrderBy(String orderBy) {
			this.val = orderBy;
		}
		public String val() {
			return this.val;
		}
		
	}

}
