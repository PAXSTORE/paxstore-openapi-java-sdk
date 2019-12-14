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
package com.pax.market.api.sdk.java.api.base.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 *
 * @author tanjie
 * @date 2018-07-03
 */
public class Result<T extends Serializable> implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6318528536528828759L;
	
	private int businessCode;
	private String message;
	private List<String> validationErrors;
	private T data;
	
	private String rateLimit;
	private String rateLimitRemain;
	private String rateLimitReset;
	
	private PageInfo<T> pageInfo;
	
	public Result() {
		
	}
	
	public Result(List<String> errors) {
		this.businessCode = -1;
		this.validationErrors = errors;
	}
	
	public Result(Response<T> response) {
		this.businessCode = response.getBusinessCode();
		this.message = response.getMessage();
		this.data = response.getData();
		this.rateLimit = response.getRateLimit();
		this.rateLimitRemain = response.getRateLimitRemain();
		this.rateLimitReset = response.getRateLimitReset();
	}
	
	public Result(PageResponse<T> response) {
		this.businessCode = response.getBusinessCode();
		this.message = response.getMessage();
		PageInfo<T> pageInfo = new PageInfo<T>();
		pageInfo.setDataSet(response.getDataset());
		pageInfo.setHasNext(response.isHasNext());
		pageInfo.setLimit(response.getLimit());
		pageInfo.setOrderBy(response.getOrderBy());
		pageInfo.setPageNo(response.getPageNo());
		pageInfo.setTotalCount(response.getTotalCount());
		
		this.rateLimit = response.getRateLimit();
		this.rateLimitRemain = response.getRateLimitRemain();
		this.rateLimitReset = response.getRateLimitReset();
		
		this.pageInfo = pageInfo;
	}
	
	public Result(EmptyResponse response) {
		if(response == null) {
			this.businessCode = 0;
		}else {
			this.businessCode = response.getBusinessCode();
			this.message = response.getMessage();
			this.data = null;
			this.rateLimit = response.getRateLimit();
			this.rateLimitRemain = response.getRateLimitRemain();
			this.rateLimitReset = response.getRateLimitReset();
		}
		
	}
	
	public int getBusinessCode() {
		return businessCode;
	}
	public void setBusinessCode(int businessCode) {
		this.businessCode = businessCode;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public List<String> getValidationErrors() {
		return validationErrors;
	}
	public void setValidationErrors(List<String> validationErrors) {
		this.validationErrors = validationErrors;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}

	public PageInfo<T> getPageInfo() {
		return pageInfo;
	}

	public void setPageInfo(PageInfo<T> pageInfo) {
		this.pageInfo = pageInfo;
	}

	@Override
	public String toString() {
		return "Result [businessCode=" + businessCode + ", message=" + message + ", validationErrors="
				+ validationErrors + ", data=" + data + ", rateLimit=" + rateLimit + ", rateLimitRemain="
				+ rateLimitRemain + ", rateLimitReset=" + rateLimitReset + ", pageInfo=" + pageInfo + "]"+(rateLimitReset!=null&&rateLimitReset.trim().length()>0?(new Date(Long.parseLong(rateLimitReset))).toString():"");
	}


	

}
