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

import java.io.Serializable;
import java.util.LinkedHashMap;

import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;


/**
 *
 * @author tanjie
 * @date 2018-07-03
 */
public class ResellerUpdateRequest implements Serializable{
	
	private static final long serialVersionUID = 5335902988326152825L;

	@NotBlank
	@Length(max=64)
	private String name;
	
	@NotBlank
	@Length(max=255)
	@Email
	private String email;
	
	@NotBlank
	@Length(max=64)
	private String country;
	
	@NotBlank
	@Length(max=64)
	private String contact;
	
	@Length(max=32)
    private String phone;

	@Length(max=16)
    private String postcode;
	
	@Length(max=255)
    private String address;

	@Length(max=255)
    private String company;
	
	@Length(max=64)
	private String parentResellerName;

	private LinkedHashMap<String, String> entityAttributeValues;

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

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
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

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public LinkedHashMap<String, String> getEntityAttributeValues() {
		return entityAttributeValues;
	}

	public void setEntityAttributeValues(LinkedHashMap<String, String> entityAttributeValues) {
		this.entityAttributeValues = entityAttributeValues;
	}

	public String getParentResellerName() {
		return parentResellerName;
	}

	public void setParentResellerName(String parentResellerName) {
		this.parentResellerName = parentResellerName;
	}

	@Override
	public String toString() {
		return "ResellerUpdateRequest [name=" + name + ", email=" + email + ", country=" + country + ", contact="
				+ contact + ", phone=" + phone + ", postcode=" + postcode + ", address=" + address + ", company="
				+ company + ", parentResellerName=" + parentResellerName + ", entityAttributeValues="
				+ entityAttributeValues + "]";
	}

	
	
	
	
	
	
}
