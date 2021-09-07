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
package com.pax.market.api.sdk.java.api.terminal.dto;

import java.io.Serializable;


import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author tanjie
 * @date 2018-07-03
 */
public class TerminalUpdateRequest implements Serializable{
	
	private static final long serialVersionUID = 748906161939046192L;

	@NotBlank
	@Length(max=64)
	protected String name;

	@Length(max=16, min=8)
	protected String tid;
    
	@Length(max=32)
	protected String serialNo;
    
	@Length(max=64)
	protected String merchantName;
	
	@NotBlank
	@Length(max=64)
	protected String resellerName;
    
    @Length(max=64)
    protected String modelName;

	@Length(max=64)
	protected String location;

	@Length(max=500)
	protected String remark;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTid() {
		return tid;
	}

	public void setTid(String tid) {
		this.tid = tid;
	}

	public String getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}

	
	
	public String getMerchantName() {
		return merchantName;
	}

	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}
	
	

	public String getResellerName() {
		return resellerName;
	}

	public void setResellerName(String resellerName) {
		this.resellerName = resellerName;
	}

	@Override
	public String toString() {
		return "TerminalUpdateRequest [name=" + name + ", tid=" + tid + ", serialNo=" + serialNo + ", merchantName="
				+ merchantName + ", resellerName=" + resellerName + ", modelName=" + modelName + ", location="
				+ location + ", remark=" + remark +"]";
	}

	
	
	
	
}
