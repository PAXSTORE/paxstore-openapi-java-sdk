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

/**
 *
 * @author tanjie
 * @date 2018-07-02
 */
public class BaseDTO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3859362371212701596L;
	private int businessCode;
	private String message;
	
	private String rateLimit;
	private String rateLimitRemain;
	private String rateLimitReset;
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getBusinessCode() {
		return businessCode;
	}
	public void setBusinessCode(int businessCode) {
		this.businessCode = businessCode;
	}
	public String getRateLimit() {
		return rateLimit;
	}
	public void setRateLimit(String rateLimit) {
		this.rateLimit = rateLimit;
	}
	public String getRateLimitRemain() {
		return rateLimitRemain;
	}
	public void setRateLimitRemain(String rateLimitRemain) {
		this.rateLimitRemain = rateLimitRemain;
	}
	public String getRateLimitReset() {
		return rateLimitReset;
	}
	public void setRateLimitReset(String rateLimitReset) {
		this.rateLimitReset = rateLimitReset;
	}

	
	
}
