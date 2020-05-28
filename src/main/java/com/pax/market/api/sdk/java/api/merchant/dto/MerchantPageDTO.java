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

/**
 *
 * @author tanjie
 * @date 2018-07-04
 */
public class MerchantPageDTO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 9209079158329227994L;
	
	protected Long id;
	protected String name;        // name
	protected SimpleResellerDTO reseller;        // reseller_id 父类
	protected String country;        // country
	protected String province;        // province
	protected String postcode;        // postal_code
	protected String address;        // address
	protected String contact;        // contact
	protected String email;        // email
	protected String phone;        // phone
	protected String status;         // status
	protected String description;        // description
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public SimpleResellerDTO getReseller() {
		return reseller;
	}
	public void setReseller(SimpleResellerDTO reseller) {
		this.reseller = reseller;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
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
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "MerchantPageDTO [id=" + id + ", name=" + name + ", reseller=" + reseller + ", country=" + country
				+ ", province=" + province +", postcode=" + postcode+ ", address=" + address + ", contact=" + contact + ", email=" + email
				+ ", phone=" + phone + ", status=" + status + ", description=" + description + "]";
	}
}
