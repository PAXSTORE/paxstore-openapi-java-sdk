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

package com.pax.market.api.sdk.java.api.terminalApk;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.pax.market.api.sdk.java.api.BaseThirdPartySysApi;
import com.pax.market.api.sdk.java.api.base.dto.Response;
import com.pax.market.api.sdk.java.api.base.dto.Result;
import com.pax.market.api.sdk.java.api.base.request.SdkRequest;
import com.pax.market.api.sdk.java.api.base.request.SdkRequest.RequestMethod;
import com.pax.market.api.sdk.java.api.client.ThirdPartySysApiClient;
import com.pax.market.api.sdk.java.api.constant.Constants;
import com.pax.market.api.sdk.java.api.terminalApk.dto.CreateTerminalApkRequest;
import com.pax.market.api.sdk.java.api.util.EnhancedJsonUtils;
import com.pax.market.api.sdk.java.api.util.StringUtils;

/**
 * Description
 *
 * @author tanjie
 * @date 2018-06-06
 */
public class TerminalApkApi extends BaseThirdPartySysApi{
	
	private static final Logger logger = LoggerFactory.getLogger(TerminalApkApi.class);
	
	private static final String CREATE_TERMINAL_APK_URL = "/v1/3rdsys/terminalApks";

	public TerminalApkApi(String baseUrl, String apiKey, String apiSecret) {
		super(baseUrl, apiKey, apiSecret);
	}
	
	public TerminalApkApi(String baseUrl, String apiKey, String apiSecret, Locale locale) {
		super(baseUrl, apiKey, apiSecret, locale);
	}
	
	
	public Result<String> createTerminalApk(CreateTerminalApkRequest createTerminalApkRequest){
		List<String> validationErrs = validateCreateTerminalApk(createTerminalApkRequest);
		
		if(validationErrs.size()>0) {
			return new Result<String>(validationErrs);
		}
		ThirdPartySysApiClient client = new ThirdPartySysApiClient(getBaseUrl(), getApiKey(), getApiSecret());
		SdkRequest request = createSdkRequest(CREATE_TERMINAL_APK_URL);
		request.setRequestMethod(RequestMethod.POST);
		request.addHeader(Constants.CONTENT_TYPE, Constants.CONTENT_TYPE_JSON);
		request.setRequestBody(new Gson().toJson(createTerminalApkRequest, CreateTerminalApkRequest.class));
		Response resp = EnhancedJsonUtils.fromJson(client.execute(request), Response.class);
        Result<String> result = new Result<String>(resp);
        return result;
	}
	
	private List<String> validateCreateTerminalApk(CreateTerminalApkRequest createTerminalApkRequest) {
		List<String> validationErrs = new ArrayList<String>();
		if(createTerminalApkRequest == null) {
			validationErrs.add(super.getMessage("parameter.createTerminalApkRequest.null"));
		}else {
			validate(createTerminalApkRequest);
			if(createTerminalApkRequest.getParameters()!=null && createTerminalApkRequest.getParameters().isEmpty()) {
				validationErrs.add(super.getMessage("parameter.createTerminalApkRequest.parameters.empty"));
			}
			if(StringUtils.isEmpty(createTerminalApkRequest.getSerialNo()) && StringUtils.isEmpty(createTerminalApkRequest.getTid())) {
				validationErrs.add(super.getMessage("parameter.createTerminalApkRequest.sn.tid.empty"));
			}
		}
		return validationErrs;
	}

}
