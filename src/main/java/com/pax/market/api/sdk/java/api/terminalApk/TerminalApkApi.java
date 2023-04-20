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

import com.google.gson.Gson;
import com.pax.market.api.sdk.java.api.BaseThirdPartySysApi;
import com.pax.market.api.sdk.java.api.base.dto.PageRequestDTO;
import com.pax.market.api.sdk.java.api.base.dto.Response;
import com.pax.market.api.sdk.java.api.base.dto.Result;
import com.pax.market.api.sdk.java.api.base.request.SdkRequest;
import com.pax.market.api.sdk.java.api.base.request.SdkRequest.RequestMethod;
import com.pax.market.api.sdk.java.api.client.ThirdPartySysApiClient;
import com.pax.market.api.sdk.java.api.constant.Constants;
import com.pax.market.api.sdk.java.api.terminalApk.dto.*;
import com.pax.market.api.sdk.java.api.terminalApk.validator.CreateTerminalApkRequestValidator;
import com.pax.market.api.sdk.java.api.terminalApk.validator.UpdateTerminalApkRequestValidator;
import com.pax.market.api.sdk.java.api.util.EnhancedJsonUtils;
import com.pax.market.api.sdk.java.api.util.StringUtils;
import com.pax.market.api.sdk.java.api.validate.Validators;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import java.util.Locale;

/**
 * Description
 *
 * @author tanjie
 * @date 2018-06-06
 */
public class TerminalApkApi extends BaseThirdPartySysApi{
	
	private static final Logger logger = LoggerFactory.getLogger(TerminalApkApi.class);
	
	private static final String SEARCH_TERMINAL_APK_LIST_URL = "/v1/3rdsys/terminalApks";
	private static final String CREATE_TERMINAL_APK_URL = "/v1/3rdsys/terminalApks";
	private static final String GET_TERMINAL_APK_URL = "/v1/3rdsys/terminalApks/{terminalApkId}";
	private static final String SUSPEND_TERMINAL_APK_URL = "/v1/3rdsys/terminalApks/suspend";
	private static final String UNINSTALL_TERMINAL_APK_URL = "/v1/3rdsys/terminalApks/uninstall";


	public TerminalApkApi(String baseUrl, String apiKey, String apiSecret) {
		super(baseUrl, apiKey, apiSecret);
	}
	
	public TerminalApkApi(String baseUrl, String apiKey, String apiSecret, Locale locale) {
		super(baseUrl, apiKey, apiSecret, locale);
	}

	public Result<TerminalApkDTO> searchTerminalApk(int pageNo, int pageSize, SearchOrderBy orderBy,
                                                    String terminalTid, String appPackageName, PushStatus status){
        ThirdPartySysApiClient client = new ThirdPartySysApiClient(getBaseUrl(), getApiKey(), getApiSecret());
        PageRequestDTO page = new PageRequestDTO();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        if(orderBy != null) {
            page.setOrderBy(orderBy.val());
        }

        List<String> validationErrs = Validators.validatePageRequest(page);
        if(!validationErrs.isEmpty()) {
            return new Result<>(validationErrs);
        }
        if(StringUtils.isEmpty(terminalTid)) {
            validationErrs.add(getMessage("parameter.not.null", "terminalTid"));
            return new Result<>(validationErrs);
        }

        SdkRequest request = getPageRequest(SEARCH_TERMINAL_APK_LIST_URL, page);
        request.addRequestParam("terminalTid", terminalTid);
        request.addRequestParam("appPackageName", appPackageName);
        if(status != null){
            request.addRequestParam("status", status.val());
        }

        TerminalApkPageResponse pageResponse = EnhancedJsonUtils.fromJson(client.execute(request), TerminalApkPageResponse.class);
        return new Result<>(pageResponse);
    }
	
	public Result<TerminalApkDTO> createTerminalApk(CreateTerminalApkRequest createTerminalApkRequest){
	    List<String> validationErrs = CreateTerminalApkRequestValidator.validate(createTerminalApkRequest);
		if(!validationErrs.isEmpty()) {
			return new Result<>(validationErrs);
		}
		ThirdPartySysApiClient client = new ThirdPartySysApiClient(getBaseUrl(), getApiKey(), getApiSecret());
		SdkRequest request = createSdkRequest(CREATE_TERMINAL_APK_URL);
		request.setRequestMethod(RequestMethod.POST);
		request.addHeader(Constants.CONTENT_TYPE, Constants.CONTENT_TYPE_JSON);
		request.setRequestBody(new Gson().toJson(createTerminalApkRequest, CreateTerminalApkRequest.class));
		TerminalApkResponse resp = EnhancedJsonUtils.fromJson(client.execute(request), TerminalApkResponse.class);
        return new Result<>(resp);
	}

	public Result<TerminalApkDTO> getTerminalApk(Long terminalApkId){
        return getTerminalApk(terminalApkId, null);
    }

    public Result<TerminalApkDTO> getTerminalApk(Long terminalApkId, List<String> pidList){
        logger.debug("terminalApkId= {}", terminalApkId);
        List<String> validationErrs = Validators.validateId(terminalApkId, "parameter.id.invalid", "terminalApkId");
        if(!validationErrs.isEmpty()) {
            return new Result<>(validationErrs);
        }
        ThirdPartySysApiClient client = new ThirdPartySysApiClient(getBaseUrl(), getApiKey(), getApiSecret());
        SdkRequest request = createSdkRequest(GET_TERMINAL_APK_URL.replace("{terminalApkId}", terminalApkId+""));
        request.setRequestMethod(RequestMethod.GET);
        if(pidList != null) {
            request.addRequestParam("pidList", StringUtils.join(pidList, ","));
        }
        TerminalApkResponse resp = EnhancedJsonUtils.fromJson(client.execute(request), TerminalApkResponse.class);
        return new Result<TerminalApkDTO>(resp);
    }

	public Result<String> disableApkPush(UpdateTerminalApkRequest disableTerminalApkRequest){
	    List<String> validationErrs = UpdateTerminalApkRequestValidator.validate(disableTerminalApkRequest, "suspendTerminalApkRequest");
        if(!validationErrs.isEmpty()) {
            return new Result<>(validationErrs);
        }
        ThirdPartySysApiClient client = new ThirdPartySysApiClient(getBaseUrl(), getApiKey(), getApiSecret());
        SdkRequest request = createSdkRequest(SUSPEND_TERMINAL_APK_URL);
        request.setRequestMethod(RequestMethod.POST);
        request.addHeader(Constants.CONTENT_TYPE, Constants.CONTENT_TYPE_JSON);
        request.setRequestBody(new Gson().toJson(disableTerminalApkRequest, UpdateTerminalApkRequest.class));
        Response resp = EnhancedJsonUtils.fromJson(client.execute(request), Response.class);
        return new Result<String>(resp);
    }

    public Result<String> uninstallApk(UpdateTerminalApkRequest uninstallApkRequest){
	    List<String> validationErrs = UpdateTerminalApkRequestValidator.validate(uninstallApkRequest, "uninstallApkRequest");
        if(!validationErrs.isEmpty()) {
            return new Result<>(validationErrs);
        }
        ThirdPartySysApiClient client = new ThirdPartySysApiClient(getBaseUrl(), getApiKey(), getApiSecret());
        SdkRequest request = createSdkRequest(UNINSTALL_TERMINAL_APK_URL);
        request.setRequestMethod(RequestMethod.POST);
        request.addHeader(Constants.CONTENT_TYPE, Constants.CONTENT_TYPE_JSON);
        request.setRequestBody(new Gson().toJson(uninstallApkRequest, UpdateTerminalApkRequest.class));
        Response resp = EnhancedJsonUtils.fromJson(client.execute(request), Response.class);
        return new Result<>(resp);
    }

    public enum SearchOrderBy {
        CreatedDate_desc("a.created_date DESC"),
        CreatedDate_asc("a.created_date ASC");
        private String val;
        private SearchOrderBy(String orderBy) {
            this.val = orderBy;
        }
        public String val(){
            return this.val;
        }
    }

    public enum PushStatus {
        Active("A"),
        Suspend("S");
        private String val;
        private PushStatus(String status) {
            this.val = status;
        }
        public String val() {
            return this.val;
        }
    }
}
