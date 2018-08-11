package com.pax.market.api.sdk.java.api.merchant.category.dto;

import java.io.Serializable;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

public class MerchantCategoryUpdateRequest implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4530338819891172935L;
	
	@NotBlank
	@Length(max=128)
	private String name;
	
	@Length(max=255)
	private String remarks;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	

}
