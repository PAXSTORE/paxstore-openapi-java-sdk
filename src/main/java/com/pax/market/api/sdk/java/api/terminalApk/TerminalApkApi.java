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

import com.pax.market.api.sdk.java.api.app.dto.AppPageDTO;
import com.pax.market.api.sdk.java.api.base.dto.PageRequestDTO;
import com.pax.market.api.sdk.java.api.terminalApk.dto.*;
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
	
	private static final String SEARCH_TERMINAL_APK_LIST_URL = "/v1/3rdsys/terminalApks";
	private static final String CREATE_TERMINAL_APK_URL = "/v1/3rdsys/terminalApks";
	private static final String GET_TERMINAL_APK_URL = "/v1/3rdsys/terminalApks/{terminalApkId}";
	private static final String SUSPEND_TERMINAL_APK_URL = "/v1/3rdsys/terminalApks/suspend";
	private static final String UNINSTALL_TERMINAL_APK_URL = "/v1/3rdsys/terminalApks/uninstall";

	private static final String TEMPLATE_NAME_DELIMITER = "|";
	
	private static final int MAX_TEMPLATE_SIZE = 10;

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
            page.setOrderBy(orderBy.val);
        }

        List<String> validationErrs = validate(page);
        if(validationErrs.size()>0) {
            return new Result<TerminalApkDTO>(validationErrs);
        }
        if(StringUtils.isEmpty(terminalTid)) {
            validationErrs.add(super.getMessage("parameter.searchTerminalApk.terminalTid.empty"));
            return new Result<TerminalApkDTO>(validationErrs);
        }

        SdkRequest request = getPageRequest(SEARCH_TERMINAL_APK_LIST_URL, page);
        request.addRequestParam("terminalTid", terminalTid);
        request.addRequestParam("appPackageName", appPackageName);
        if(status != null){
            request.addRequestParam("status", status.val);
        }

        TerminalApkPageResponse pageResponse = EnhancedJsonUtils.fromJson(client.execute(request), TerminalApkPageResponse.class);
        Result<TerminalApkDTO> result = new Result<TerminalApkDTO>(pageResponse);

        return result;
    }
	
	public Result<TerminalApkDTO> createTerminalApk(CreateTerminalApkRequest createTerminalApkRequest){
		List<String> validationErrs = validateCreateTerminalApk(createTerminalApkRequest);
		
		if(validationErrs.size()>0) {
			return new Result<TerminalApkDTO>(validationErrs);
		}
		ThirdPartySysApiClient client = new ThirdPartySysApiClient(getBaseUrl(), getApiKey(), getApiSecret());
		SdkRequest request = createSdkRequest(CREATE_TERMINAL_APK_URL);
		request.setRequestMethod(RequestMethod.POST);
		request.addHeader(Constants.CONTENT_TYPE, Constants.CONTENT_TYPE_JSON);
		request.setRequestBody(new Gson().toJson(createTerminalApkRequest, CreateTerminalApkRequest.class));
		TerminalApkResponse resp = EnhancedJsonUtils.fromJson(client.execute(request), TerminalApkResponse.class);
        Result<TerminalApkDTO> result = new Result<TerminalApkDTO>(resp);
        return result;
	}

	public Result<TerminalApkDTO> getTerminalApk(Long terminalApkId){
        logger.debug("terminalApkId="+terminalApkId);
        List<String> validationErrs = validateId(terminalApkId, "parameter.terminalApkId.invalid");
        if(validationErrs.size()>0) {
            return new Result<TerminalApkDTO>(validationErrs);
        }
        ThirdPartySysApiClient client = new ThirdPartySysApiClient(getBaseUrl(), getApiKey(), getApiSecret());
        SdkRequest request = createSdkRequest(GET_TERMINAL_APK_URL.replace("{terminalApkId}", terminalApkId+""));
        request.setRequestMethod(RequestMethod.GET);
        TerminalApkResponse resp = EnhancedJsonUtils.fromJson(client.execute(request), TerminalApkResponse.class);
        Result<TerminalApkDTO> result = new Result<TerminalApkDTO>(resp);
        return result;
    }

	public Result<String> suspendTerminalApk(UpdateTerminalApkRequest suspendTerminalApkRequest){
        List<String> validationErrs = validateSuspendTerminalApk(suspendTerminalApkRequest);

        if(validationErrs.size()>0) {
            return new Result<String>(validationErrs);
        }
        ThirdPartySysApiClient client = new ThirdPartySysApiClient(getBaseUrl(), getApiKey(), getApiSecret());
        SdkRequest request = createSdkRequest(SUSPEND_TERMINAL_APK_URL);
        request.setRequestMethod(RequestMethod.POST);
        request.addHeader(Constants.CONTENT_TYPE, Constants.CONTENT_TYPE_JSON);
        request.setRequestBody(new Gson().toJson(suspendTerminalApkRequest, UpdateTerminalApkRequest.class));
        Response resp = EnhancedJsonUtils.fromJson(client.execute(request), Response.class);
        Result<String> result = new Result<String>(resp);
        return result;
    }

    public Result<String> uninstallTerminalApk(UpdateTerminalApkRequest uninstallTerminalApkRequest){
        List<String> validationErrs = validateUninstallTerminalApk(uninstallTerminalApkRequest);

        if(validationErrs.size()>0) {
            return new Result<String>(validationErrs);
        }
        ThirdPartySysApiClient client = new ThirdPartySysApiClient(getBaseUrl(), getApiKey(), getApiSecret());
        SdkRequest request = createSdkRequest(UNINSTALL_TERMINAL_APK_URL);
        request.setRequestMethod(RequestMethod.POST);
        request.addHeader(Constants.CONTENT_TYPE, Constants.CONTENT_TYPE_JSON);
        request.setRequestBody(new Gson().toJson(uninstallTerminalApkRequest, UpdateTerminalApkRequest.class));
        Response resp = EnhancedJsonUtils.fromJson(client.execute(request), Response.class);
        Result<String> result = new Result<String>(resp);
        return result;
    }
	
	private List<String> validateCreateTerminalApk(CreateTerminalApkRequest createTerminalApkRequest) {
		List<String> validationErrs = new ArrayList<String>();
		if(createTerminalApkRequest == null) {
			validationErrs.add(super.getMessage("parameter.createTerminalApkRequest.null"));
		}else {
			validationErrs.addAll(validate(createTerminalApkRequest));
			if(StringUtils.isEmpty(createTerminalApkRequest.getSerialNo()) && StringUtils.isEmpty(createTerminalApkRequest.getTid())) {
				validationErrs.add(super.getMessage("parameter.createTerminalApkRequest.sn.tid.empty"));
			}
			if(!StringUtils.isEmpty(createTerminalApkRequest.getTemplateName())) {
				if(createTerminalApkRequest.getTemplateName().split("\\"+TEMPLATE_NAME_DELIMITER).length>MAX_TEMPLATE_SIZE) {
					validationErrs.add(super.getMessage("parameter.createTerminalApkRequest.template.name.toolong"));
				}
			}
		}
		return validationErrs;
	}

	private List<String> validateSuspendTerminalApk(UpdateTerminalApkRequest suspendTerminalApkRequest) {
        List<String> validationErrs = new ArrayList<String>();
        if(suspendTerminalApkRequest == null) {
            validationErrs.add(super.getMessage("parameter.suspendTerminalApkRequest.null"));
        }else {
            validationErrs.addAll(validate(suspendTerminalApkRequest));
            if(StringUtils.isEmpty(suspendTerminalApkRequest.getSerialNo()) && StringUtils.isEmpty(suspendTerminalApkRequest.getTid())) {
                validationErrs.add(super.getMessage("parameter.suspendTerminalApkRequest.sn.tid.empty"));
            }
        }
        return validationErrs;
    }

    private List<String> validateUninstallTerminalApk(UpdateTerminalApkRequest uninstallTerminalApkRequest) {
        List<String> validationErrs = new ArrayList<String>();
        if(uninstallTerminalApkRequest == null) {
            validationErrs.add(super.getMessage("parameter.uninstallTerminalApkRequest.null"));
        }else {
            validationErrs.addAll(validate(uninstallTerminalApkRequest));
            if(StringUtils.isEmpty(uninstallTerminalApkRequest.getSerialNo()) && StringUtils.isEmpty(uninstallTerminalApkRequest.getTid())) {
                validationErrs.add(super.getMessage("parameter.uninstallTerminalApkRequest.sn.tid.empty"));
            }
        }
        return validationErrs;
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
