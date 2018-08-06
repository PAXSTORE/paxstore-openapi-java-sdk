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

import org.hibernate.validator.resourceloading.PlatformResourceBundleLocator;
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
     * The constant appKey.
     */
    private String appKey;
    /**
     * The constant appSecret.
     */
    private String appSecret;


    public BaseThirdPartySysApi(String baseUrl, String appKey, String appSecret) {
        this.baseUrl = baseUrl;
        this.appKey = appKey;
        this.appSecret = appSecret;
        factory = Validation.buildDefaultValidatorFactory();
    }
    
    public BaseThirdPartySysApi(String baseUrl, String appKey, String appSecret, Locale locale) {
        this.baseUrl = baseUrl;
        this.appKey = appKey;
        this.appSecret = appSecret;
        if(locale!=null) {
        	Locale.setDefault(locale);
        }
        factory = Validation.buildDefaultValidatorFactory();
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public String getAppKey() {
        return appKey;
    }

    public String getAppSecret() {
        return appSecret;
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

	

}
