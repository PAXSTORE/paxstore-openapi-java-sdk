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
package com.pax.market.api.sdk.java.api.merchant.dto;

import java.util.LinkedHashMap;
import java.util.List;



/**
 *
 * @author tanjie
 * @date 2018-07-03
 */
public class MerchantDTO extends MerchantPageDTO{

	private static final long serialVersionUID = -643337628724546554L;
	
	private LinkedHashMap<String, String> entityAttributeValues;
	private List<MerchantCategoryDTO> merchantCategory;
	
	public List<MerchantCategoryDTO> getMerchantCategory() {
		return merchantCategory;
	}

	public void setMerchantCategory(List<MerchantCategoryDTO> merchantCategory) {
		this.merchantCategory = merchantCategory;
	}

	public LinkedHashMap<String, String> getEntityAttributeValues() {
		return entityAttributeValues;
	}

	public void setEntityAttributeValues(LinkedHashMap<String, String> entityAttributeValues) {
		this.entityAttributeValues = entityAttributeValues;
	}

	@Override
	public String toString() {
		return "MerchantDTO [entityAttributeValues=" + entityAttributeValues + ", merchantCategory=" + merchantCategory
				+ ", id=" + id + ", name=" + name + ", reseller=" + reseller + ", country=" + country + ",province="+province+ ",city="+city+", postcode="
				+ postcode + ", address=" + address + ", contact=" + contact + ", email=" + email + ", phone=" + phone
				+ ", status=" + status + ", description=" + description + "]";
	}
}
