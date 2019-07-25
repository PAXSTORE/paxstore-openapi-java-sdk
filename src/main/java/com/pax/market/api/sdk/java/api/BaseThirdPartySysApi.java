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

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pax.market.api.sdk.java.api.base.dto.PageRequestDTO;
import com.pax.market.api.sdk.java.api.base.request.SdkRequest;
import com.pax.market.api.sdk.java.api.util.MessageBoudleUtil;

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
    
    public static int connectTimeout = 0;
    public static int readTimeout = 0;
    public static int retryTimes = 0;


    public BaseThirdPartySysApi(String baseUrl, String apiKey, String apiSecret) {
    	if(baseUrl.endsWith("/")) {
    		baseUrl = baseUrl.substring(0, baseUrl.length()-1);
    	}
        this.baseUrl = baseUrl;
        this.apiKey = apiKey;
        this.apiSecret = apiSecret;
        factory = Validation.buildDefaultValidatorFactory();
    }
    
    public BaseThirdPartySysApi(String baseUrl, String apiKey, String apiSecret, Locale locale) {
    	if(baseUrl.endsWith("/")) {
    		baseUrl = baseUrl.substring(0, baseUrl.length()-1);
    	}
        this.baseUrl = baseUrl;
        this.apiKey = apiKey;
        this.apiSecret = apiSecret;
        if(locale!=null) {
        	Locale.setDefault(locale);
        }
        factory = Validation.buildDefaultValidatorFactory();
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
    	return request;
    }

	
	protected SdkRequest getPageRequest(String requestUrl, PageRequestDTO page) {
		logger.debug("pageSize="+page.getPageSize());
		logger.debug("pageNo"+page.getPageNo());
		logger.debug("orderBy"+page.getOrderBy());
		SdkRequest request = createSdkRequest(requestUrl);
		request.addRequestParam("limit", page.getPageSize()+"");
		request.addRequestParam("pageNo", page.getPageNo()+"");
		request.addRequestParam("orderBy", page.getOrderBy());
		return request;
	}
	
	private static ValidatorFactory factory = null;
	
	protected static String getMessage(String key) {
		return MessageBoudleUtil.getMessage(key, Locale.getDefault());
	}

    protected static <T> List<String> validate(T t) {
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<T>> constraintViolations = validator.validate(t);
        List<String> messageList = new ArrayList<String>();
        for (ConstraintViolation<T> constraintViolation : constraintViolations) {
            messageList.add(constraintViolation.getPropertyPath().toString()+":"+constraintViolation.getMessage());
        }
        return messageList;
    }
    
    protected static List<String> validateId(Long id, String errorMsgKey){
    	List<String> validationErrs = new ArrayList<String>();
		if(id == null || id<0L) {
			validationErrs.add(getMessage(errorMsgKey));
		}
		return validationErrs;
	}
    
    protected static <T> List<String> validateCreate(T createReq, String beanEmptyMsgKey){
    	List<String> validationErrs = new ArrayList<String>();
		if(createReq== null) {
			validationErrs.add(getMessage(beanEmptyMsgKey));
			return validationErrs;
		}else {
			return validate(createReq);
		}
	} 
	
	protected static List<String> validateUpdate(Long id, Object updateReq, String idInvalidMsgKey, String beanEmptyMsgKey){
		List<String> validationErrs = new ArrayList<String>();
		if(id == null || id<0L) {
			validationErrs.add(getMessage(idInvalidMsgKey));
		}
		if(updateReq == null) {
			validationErrs.add(getMessage(beanEmptyMsgKey));
		}else {
			validationErrs.addAll(validate(updateReq));
		}
		return validationErrs;
	}
	
	public void setAPIGlobalConnectTimeout(int timeout) {
		this.connectTimeout = timeout;
	}
	
	public void setAPIGlobalReadTimeout(int timeout) {
		this.readTimeout = timeout;
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
