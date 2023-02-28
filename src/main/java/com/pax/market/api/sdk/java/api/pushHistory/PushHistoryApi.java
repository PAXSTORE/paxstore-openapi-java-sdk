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


import com.google.gson.reflect.TypeToken;
import com.pax.market.api.sdk.java.api.BaseThirdPartySysApi;
import com.pax.market.api.sdk.java.api.base.dto.PageRequestDTO;
import com.pax.market.api.sdk.java.api.base.dto.Result;
import com.pax.market.api.sdk.java.api.base.request.SdkRequest;
import com.pax.market.api.sdk.java.api.client.ThirdPartySysApiClient;
import com.pax.market.api.sdk.java.api.constant.Constants;
import com.pax.market.api.sdk.java.api.pushHistory.dto.OptimizedParameterPushHistoryDTO;
import com.pax.market.api.sdk.java.api.pushHistory.dto.ParameterPushHistoryDTO;
import com.pax.market.api.sdk.java.api.pushHistory.dto.ParameterPushHistoryPageResponse;
import com.pax.market.api.sdk.java.api.util.EnhancedJsonUtils;
import com.pax.market.api.sdk.java.api.util.StringUtils;
import com.pax.market.api.sdk.java.api.validate.Validators;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;
import java.util.Locale;


/**
 * The type Push history api.
 */
public class PushHistoryApi extends BaseThirdPartySysApi {
    private static final Logger logger = LoggerFactory.getLogger(PushHistoryApi.class.getSimpleName());
    private static final String SEARCH_APP_PUSH_HISTORY_URL = "/v1/3rdsys/parameter/push/history";


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
     * Search parameter push history result.
     *
     * @param pageNo      the page no
     * @param pageSize    the page size
     * @param packageName the package name
     * @param serialNo    the serial no
     * @param pushStatus  the push status
     * @param pushTime    the push time
     * @return the result
     */
    public Result<ParameterPushHistoryDTO> searchParameterPushHistory(int pageNo, int pageSize, String packageName, String serialNo, PushStatus pushStatus, Date pushTime) {
        Type responseType = new TypeToken<ParameterPushHistoryPageResponse<ParameterPushHistoryDTO>>(){}.getType();
        return searchParameterPushHistory(pageNo, pageSize, packageName, serialNo, pushStatus, pushTime, "false", "false", responseType);
    }

    /**
     * Search optimized parameter push history result.
     *
     * @param pageNo      the page no
     * @param pageSize    the page size
     * @param packageName the package name
     * @param serialNo    the serial no
     * @param pushStatus  the push status
     * @param pushTime    the push time
     * @return the result
     */
    public Result<OptimizedParameterPushHistoryDTO> searchOptimizedParameterPushHistory(int pageNo, int pageSize, String packageName, String serialNo, PushStatus pushStatus, Date pushTime) {
        Type responseType = new TypeToken<ParameterPushHistoryPageResponse<OptimizedParameterPushHistoryDTO>>(){}.getType();
        return searchParameterPushHistory(pageNo, pageSize, packageName, serialNo, pushStatus, pushTime, "false", "true", responseType);
    }

    /**
     * Search latest parameter push history result.
     *
     * @param pageNo      the page no
     * @param pageSize    the page size
     * @param packageName the package name
     * @param serialNo    the serial no
     * @param pushStatus  the push status
     * @param pushTime    the push time
     * @return the result
     */
    public Result<ParameterPushHistoryDTO> searchLatestParameterPushHistory(int pageNo, int pageSize, String packageName, String serialNo, PushStatus pushStatus, Date pushTime) {
        Type responseType = new TypeToken<ParameterPushHistoryPageResponse<ParameterPushHistoryDTO>>(){}.getType();
        return searchParameterPushHistory(pageNo, pageSize, packageName, serialNo, pushStatus, pushTime, "true", "false", responseType);
    }

    /**
     * Search latest optimized parameter push history result.
     *
     * @param pageNo      the page no
     * @param pageSize    the page size
     * @param packageName the package name
     * @param serialNo    the serial no
     * @param pushStatus  the push status
     * @param pushTime    the push time
     * @return the result
     */
    public Result<OptimizedParameterPushHistoryDTO> searchLatestOptimizedParameterPushHistory(int pageNo, int pageSize, String packageName, String serialNo, PushStatus pushStatus, Date pushTime) {
        Type responseType = new TypeToken<ParameterPushHistoryPageResponse<OptimizedParameterPushHistoryDTO>>(){}.getType();
        return searchParameterPushHistory(pageNo, pageSize, packageName, serialNo, pushStatus, pushTime, "true", "true", responseType);
    }

    private <T extends Serializable> Result<T> searchParameterPushHistory(int pageNo, int pageSize, String packageName, String serialNo, PushStatus pushStatus, Date pushTime, String onlyLastPushHistory, String optimizeParameters, Type responseType) {
        List<String> validationErrsP = Validators.validateStr(packageName, "parameter.not.null", "packageName");
        if(!validationErrsP.isEmpty()){
            return new Result<>(validationErrsP);
        }
        ThirdPartySysApiClient client = new ThirdPartySysApiClient(getBaseUrl(), getApiKey(), getApiSecret());
        PageRequestDTO page = new PageRequestDTO();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        List<String> validationErrs = Validators.validatePageRequest(page);
        if (!validationErrs.isEmpty()) {
            return new Result<>(validationErrs);
        }
        SdkRequest request = getPageRequest(SEARCH_APP_PUSH_HISTORY_URL, page);
        if (pushStatus != null) {
            request.addRequestParam("pushStatus", String.valueOf(pushStatus.val));
        }
        if (pushTime != null) {
            request.addRequestParam("pushTime", StringUtils.formatDateTime(pushTime, Constants.TIMEZONE_DATE_TIME_FORMAT));
        }
        request.addRequestParam("packageName", packageName);
        request.addRequestParam("serialNo", serialNo);
        request.addRequestParam("onlyLastPushHistory", onlyLastPushHistory);
        request.addRequestParam("optimizeParameters", optimizeParameters);

        ParameterPushHistoryPageResponse<T> parameterPushHistoryPageResponse = EnhancedJsonUtils.fromJson(client.execute(request), responseType);
        return new Result<>(parameterPushHistoryPageResponse);
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
