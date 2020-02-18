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

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import com.pax.market.api.sdk.java.api.merchant.MerchantApi.MerchantStatus;

/**
 *
 * @author tanjie
 * @date 2018-07-03
 */
public class MerchantCreateRequest implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3538521345884170907L;
	
	@NotBlank
	@Length(max=64)
	private String name;

	@NotBlank
	@Length(max=255)
	@Email
	private String email;
	
	@NotBlank
	@Length(max=64)
    private String resellerName;
	
    @NotBlank
	@Length(max=64)
	private String contact;

    @NotBlank
	@Length(max=64)
    private String country;
	
    @NotBlank
    @Length(max=32)
	private String phone;

	@NotBlank
	@Length(max=64)
	private String province;
	
    @Length(max=16)
	private String postcode;
	
//    private String city;
//    
    @Length(max=255)
    private String address;
    
    @Length(max=3000)
    private String description;
    
    private Boolean createUserFlag = Boolean.FALSE;

    private List<String> merchantCategoryNames;
	
	private LinkedHashMap<String, String> entityAttributeValues;
	
	private Boolean activateWhenCreate = Boolean.FALSE;
	
	private String status;

	
	public Boolean getCreateUserFlag() {
		return createUserFlag;
	}

	public void setCreateUserFlag(Boolean createUserFlag) {
		this.createUserFlag = createUserFlag;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getResellerName() {
		return resellerName;
	}

	public void setResellerName(String resellerName) {
		this.resellerName = resellerName;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}


	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<String> getMerchantCategoryNames() {
		return merchantCategoryNames;
	}

	public void setMerchantCategoryNames(List<String> merchantCategoryNames) {
		this.merchantCategoryNames = merchantCategoryNames;
	}

	public LinkedHashMap<String, String> getEntityAttributeValues() {
		return entityAttributeValues;
	}

	public void setEntityAttributeValues(LinkedHashMap<String, String> entityAttributeValues) {
		this.entityAttributeValues = entityAttributeValues;
	}

	public Boolean getActivateWhenCreate() {
		return activateWhenCreate;
	}

	public void setActivateWhenCreate(Boolean activateWhenCreate) {
		this.activateWhenCreate = activateWhenCreate;
		if(activateWhenCreate) {
			this.status = MerchantStatus.Active.val();
		}
	}

	public String getStatus() {
		return status;
	}

}
