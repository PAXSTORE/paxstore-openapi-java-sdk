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


import java.util.LinkedHashMap;

import com.pax.market.api.sdk.java.api.merchant.dto.SimpleResellerDTO;

/**
 *
 * @author tanjie
 * @date 2018-07-03
 */
public class ResellerDTO extends  ResellerPageDTO{

		
	/**
	 * 
	 */
	private static final long serialVersionUID = 8969464555556400805L;

	private LinkedHashMap<String, String> entityAttributeValues;
	
	private SimpleResellerDTO parent;

	public LinkedHashMap<String, String> getEntityAttributeValues() {
		return entityAttributeValues;
	}

	public void setEntityAttributeValues(LinkedHashMap<String, String> entityAttributeValues) {
		this.entityAttributeValues = entityAttributeValues;
	}
	
	

	public SimpleResellerDTO getParent() {
		return parent;
	}

	public void setParent(SimpleResellerDTO parent) {
		this.parent = parent;
	}

	@Override
	public String toString() {
		return "ResellerDTO [entityAttributeValues=" + entityAttributeValues + ", parent=" + parent
				+ ", id=" + id + ", name=" + name + ", phone=" + phone + ", country=" + country + ", postcode="
				+ postcode + ", address=" + address + ", company=" + company + ", contact=" + contact + ", email="
				+ email + ", status=" + status + "]";
	}

}
