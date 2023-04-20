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
package com.pax.market.api.sdk.java.api;

import com.pax.market.api.sdk.java.api.base.dto.PageRequestDTO;
import com.pax.market.api.sdk.java.api.base.request.SdkRequest;
import com.pax.market.api.sdk.java.api.exception.InvalidParamException;
import com.pax.market.api.sdk.java.api.util.MessageBundleUtils;
import com.pax.market.api.sdk.java.api.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 *
 * @author tanjie
 * @date 2018-07-02
 */
public class BaseThirdPartySysApi{
	
	private final Logger logger = LoggerFactory.getLogger(BaseThirdPartySysApi.class);

    /**
     * The constant baseUrl.
     */
    private String baseUrl;
    /**
     * The constant apiKey.
     */
    private String apiKey;
    /**
     * The constant apiSecret.
     */
    private String apiSecret;

    public static int connectTimeout;
    public static int readTimeout;
    public static int retryTimes = 5;

    private TimeZone apiTimeZone = null;

    public BaseThirdPartySysApi(String baseUrl, String apiKey, String apiSecret) {
    	if(baseUrl.endsWith("/")) {
    		baseUrl = baseUrl.substring(0, baseUrl.length()-1);
    	}
        this.baseUrl = baseUrl;
        this.apiKey = apiKey;
        this.apiSecret = apiSecret;
        setDefaultTZ(null);
    }

    public BaseThirdPartySysApi(String baseUrl, String apiKey, String apiSecret, TimeZone timeZone) {
        if(baseUrl.endsWith("/")) {
            baseUrl = baseUrl.substring(0, baseUrl.length()-1);
        }
        this.baseUrl = baseUrl;
        this.apiKey = apiKey;
        this.apiSecret = apiSecret;
        setDefaultTZ(timeZone);
    }

    @Deprecated
    public BaseThirdPartySysApi(String baseUrl, String apiKey, String apiSecret, Locale locale) {
    	if(baseUrl.endsWith("/")) {
    		baseUrl = baseUrl.substring(0, baseUrl.length()-1);
    	}
        this.baseUrl = baseUrl;
        this.apiKey = apiKey;
        this.apiSecret = apiSecret;
        setDefaultLocal(locale);
        setDefaultTZ(null);
    }

    @Deprecated
    public BaseThirdPartySysApi(String baseUrl, String apiKey, String apiSecret, Locale locale, TimeZone timeZone) {
        if(baseUrl.endsWith("/")) {
            baseUrl = baseUrl.substring(0, baseUrl.length()-1);
        }
        this.baseUrl = baseUrl;
        this.apiKey = apiKey;
        this.apiSecret = apiSecret;
        setDefaultLocal(locale);
        setDefaultTZ(timeZone);
    }

    private void setDefaultLocal(Locale locale){
//        if(locale!=null) {
//            Locale.setDefault(locale);
//        }
    }

    private void setDefaultTZ(TimeZone timeZone){
        if(timeZone != null){
            apiTimeZone = timeZone;
        } else {
            apiTimeZone = TimeZone.getTimeZone("UTC");
        }
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public String getApiKey() {
		return apiKey;
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

	public String getApiSecret() {
		return apiSecret;
	}

	public void setApiSecret(String apiSecret) {
		this.apiSecret = apiSecret;
	}

	protected SdkRequest createSdkRequest(String requestMappingUrl) {
    	SdkRequest request = new SdkRequest(requestMappingUrl);
    	request.addHeader("content-language", Locale.getDefault().toString());
    	request.addHeader("Time-Zone", apiTimeZone.getID());
    	return request;
    }


	protected SdkRequest getPageRequest(String requestUrl, PageRequestDTO page) {
		logger.debug("pageSize="+page.getPageSize());
		logger.debug("pageNo"+page.getPageNo());
		logger.debug("orderBy"+page.getOrderBy());
		SdkRequest request = createSdkRequest(requestUrl);
		request.addRequestParam("limit", page.getPageSize()+"");
		request.addRequestParam("pageNo", page.getPageNo()+"");
		if(StringUtils.isNotEmpty(page.getOrderBy())){
            request.addRequestParam("orderBy", page.getOrderBy());
        }
		return request;
	}

	protected static String getMessage(String key) {
		return MessageBundleUtils.getMessage(key);
	}

	protected static String getMessage(String key,  Object... args) {
		return MessageBundleUtils.getMessage(key, args);
	}



    public void setSDKConnectTimeout(int connectTimeout) {
        if(connectTimeout<0) {
            throw new InvalidParamException("timeout can not be negative");
        }
        BaseThirdPartySysApi.connectTimeout = connectTimeout;
    }

    public void setSDKReadTimeout(int readTimeout) {
        if(readTimeout<0) {
            throw new InvalidParamException("timeout can not be negative");
        }
        BaseThirdPartySysApi.readTimeout = readTimeout;
    }

    public void setRetryTimes(int retryTimes) {
        if(retryTimes <1 || retryTimes>5) {
            throw new InvalidParamException("retryTimes cannot less than 0 and grate than 5");
        }
        BaseThirdPartySysApi.retryTimes = retryTimes;
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
