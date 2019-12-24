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
package com.pax.market.api.sdk.java.api.pushHistory;


import com.pax.market.api.sdk.java.api.BaseThirdPartySysApi;
import com.pax.market.api.sdk.java.api.base.dto.PageRequestDTO;
import com.pax.market.api.sdk.java.api.base.dto.Result;
import com.pax.market.api.sdk.java.api.base.request.SdkRequest;
import com.pax.market.api.sdk.java.api.client.ThirdPartySysApiClient;
import com.pax.market.api.sdk.java.api.constant.Constants;
import com.pax.market.api.sdk.java.api.pushHistory.dto.AppPushHistoryDTO;
import com.pax.market.api.sdk.java.api.pushHistory.dto.AppPushHistoryPageResponse;
import com.pax.market.api.sdk.java.api.util.EnhancedJsonUtils;
import com.pax.market.api.sdk.java.api.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.List;
import java.util.Locale;


/**
 * The type Push history api.
 */
public class PushHistoryApi extends BaseThirdPartySysApi {
    private static final Logger logger = LoggerFactory.getLogger(PushHistoryApi.class.getSimpleName());
    private static final String SEARCH_APP_PUSH_HISTORY_URL = "/v1/3rdsys/app/push/history";


    /**
     * Instantiates a new Push history api.
     *
     * @param baseUrl   the base url
     * @param apiKey    the api key
     * @param apiSecret the api secret
     */
    public PushHistoryApi(String baseUrl, String apiKey, String apiSecret) {
        super(baseUrl, apiKey, apiSecret);
    }


    /**
     * Instantiates a new Push history api.
     *
     * @param baseUrl   the base url
     * @param apiKey    the api key
     * @param apiSecret the api secret
     * @param locale    the locale
     */
    public PushHistoryApi(String baseUrl, String apiKey, String apiSecret, Locale locale) {
        super(baseUrl, apiKey, apiSecret, locale);
    }


    /**
     * Search app push history result.
     *
     * @param pageNo              the page no
     * @param pageSize            the page size
     * @param packageName         the package name
     * @param snNameTID           the sn name tid
     * @param appPushStatus       the app push status
     * @param parameterPushStatus the parameter push status
     * @param appPushTime         the app push time
     * @param parameterPushTime   the parameter push time
     * @return the result
     */
    public Result<AppPushHistoryDTO> searchAppPushHistory(int pageNo, int pageSize, String packageName, String snNameTID, PushStatus appPushStatus, PushStatus parameterPushStatus, Date appPushTime, Date parameterPushTime) {
        logger.debug("packageName="+packageName);
        List<String> validationErrsP = validateStr(packageName, "parameter.packageName.null");
        if(validationErrsP.size()>0){
            return new Result<AppPushHistoryDTO>(validationErrsP);
        }
        ThirdPartySysApiClient client = new ThirdPartySysApiClient(getBaseUrl(), getApiKey(), getApiSecret());
        PageRequestDTO page = new PageRequestDTO();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        List<String> validationErrs = validate(page);
        if (validationErrs.size() > 0) {
            return new Result<AppPushHistoryDTO>(validationErrs);
        }
        SdkRequest request = getPageRequest(SEARCH_APP_PUSH_HISTORY_URL, page);
        if (appPushStatus != null) {
            request.addRequestParam("appPushStatus", String.valueOf(appPushStatus.val));
        }
        if (parameterPushStatus != null) {
            request.addRequestParam("parameterPushStatus", String.valueOf(parameterPushStatus.val));
        }
        if (appPushTime != null) {
            request.addRequestParam("appPushTime", StringUtils.formatDateTime(appPushTime, Constants.TIMEZONE_DATE_TIME_FORMAT));
        }
        if (parameterPushTime != null) {
            request.addRequestParam("parameterPushTime", StringUtils.formatDateTime(parameterPushTime, Constants.TIMEZONE_DATE_TIME_FORMAT));
        }
        request.addRequestParam("packageName", packageName);
        request.addRequestParam("snNameTID", snNameTID);

        AppPushHistoryPageResponse appPushHistoryPageResponse = EnhancedJsonUtils.fromJson(client.execute(request), AppPushHistoryPageResponse.class);

        return new Result<AppPushHistoryDTO>(appPushHistoryPageResponse);
    }

    /**
     * Search last app push history result.
     *
     * @param pageNo              the page no
     * @param pageSize            the page size
     * @param packageName         the package name
     * @param snNameTID           the sn name tid
     * @param appPushStatus       the app push status
     * @param parameterPushStatus the parameter push status
     * @param appPushTime         the app push time
     * @param parameterPushTime   the parameter push time
     * @return the result
     */
    public Result<AppPushHistoryDTO> searchLastAppPushHistory(int pageNo, int pageSize, String packageName, String snNameTID, PushStatus appPushStatus, PushStatus parameterPushStatus, Date appPushTime, Date parameterPushTime) {
        logger.debug("packageName="+packageName);
        List<String> validationErrsP = validateStr(packageName, "parameter.packageName.null");
        if(validationErrsP.size()>0){
            return new Result<AppPushHistoryDTO>(validationErrsP);
        }
        ThirdPartySysApiClient client = new ThirdPartySysApiClient(getBaseUrl(), getApiKey(), getApiSecret());
        PageRequestDTO page = new PageRequestDTO();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        List<String> validationErrs = validate(page);
        if (validationErrs.size() > 0) {
            return new Result<AppPushHistoryDTO>(validationErrs);
        }
        SdkRequest request = getPageRequest(SEARCH_APP_PUSH_HISTORY_URL, page);
        if (appPushStatus != null) {
            request.addRequestParam("appPushStatus", String.valueOf(appPushStatus.val));
        }
        if (parameterPushStatus != null) {
            request.addRequestParam("parameterPushStatus", String.valueOf(parameterPushStatus.val));
        }
        if (appPushTime != null) {
            request.addRequestParam("appPushTime", StringUtils.formatDateTime(appPushTime, Constants.TIMEZONE_DATE_TIME_FORMAT));
        }
        if (parameterPushTime != null) {
            request.addRequestParam("parameterPushTime", StringUtils.formatDateTime(parameterPushTime, Constants.TIMEZONE_DATE_TIME_FORMAT));
        }
        request.addRequestParam("packageName", packageName);
        request.addRequestParam("snNameTID", snNameTID);
        request.addRequestParam("onlyLastPushHistory", "true");

        AppPushHistoryPageResponse appPushHistoryPageResponse = EnhancedJsonUtils.fromJson(client.execute(request), AppPushHistoryPageResponse.class);

        return new Result<AppPushHistoryDTO>(appPushHistoryPageResponse);
    }

    /**
     * The enum Push status.
     */
    public enum PushStatus {
        /**
         * Success push status.
         */
        Success(2),
        /**
         * Failed push status.
         */
        Failed(3);
        private Integer val;

        private PushStatus(Integer status) {
            this.val = status;
        }

        /**
         * Val integer.
         *
         * @return the integer
         */
        public Integer val() {
            return this.val;
        }
    }
}
