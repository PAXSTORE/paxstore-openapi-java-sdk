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


/**
 *
 * @author tanjie
 * @date 2018-07-03
 */
public class MerchantUpdateRequest implements Serializable{

	private static final long serialVersionUID = 3252275956071254725L;
	private String name;
	private String email;
	private String resellerName;
	private String contact;
	private String country;
	private String phone;
	private String province;
	private String postcode;
	private String city;
	private String address;
	private String description;

	private Boolean createUserFlag = Boolean.FALSE;

	private List<String> merchantCategoryNames;

	private LinkedHashMap<String, String> entityAttributeValues;


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

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Override
	public String toString() {
		return "MerchantUpdateRequest{" +
				"name='" + name + '\'' +
				", email='" + email + '\'' +
				", resellerName='" + resellerName + '\'' +
				", contact='" + contact + '\'' +
				", country='" + country + '\'' +
				", phone='" + phone + '\'' +
				", province='" + province + '\'' +
				", postcode='" + postcode + '\'' +
				", city='" + city + '\'' +
				", address='" + address + '\'' +
				", description='" + description + '\'' +
				", createUserFlag=" + createUserFlag +
				", merchantCategoryNames=" + merchantCategoryNames +
				", entityAttributeValues=" + entityAttributeValues +
				'}';
	}
}
