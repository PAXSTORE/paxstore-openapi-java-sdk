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
package com.pax.market.api.sdk.java.api.reseller.dto;

import org.hibernate.validator.constraints.Length;

/**
 *
 * @author tanjie
 * @date 2018-07-03
 */
public class ResellerCreateRequest extends ResellerUpdateRequest{

	private static final long serialVersionUID = -898044224050582642L;
	
	@Length(max=64)
	private String parentResellerName;
	
	public String getParentResellerName() {
		return parentResellerName;
	}

	public void setParentResellerName(String parentResellerName) {
		this.parentResellerName = parentResellerName;
	}

}
