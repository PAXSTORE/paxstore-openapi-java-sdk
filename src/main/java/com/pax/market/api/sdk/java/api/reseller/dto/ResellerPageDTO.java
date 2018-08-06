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

/**
 *
 * @author tanjie
 * @date 2018-07-04
 */
public class ResellerPageDTO implements Serializable{

	private static final long serialVersionUID = 5074496728674812671L;
	
	protected Long id;

	protected String name;        // name
	protected String phone;        // phone
	protected String country;        // country
	protected String postcode;        // postcode
	protected String address;        // address
	protected String company;        // company
	protected String contact;        // contact
	protected String email;
	protected String status;        // status
	
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
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "ResellerPageDTO [id=" + id + ", name=" + name + ", phone=" + phone + ", country=" + country
				+ ", postcode=" + postcode + ", address=" + address + ", company=" + company + ", contact=" + contact
				+ ", email=" + email + ", status=" + status + "]";
	}
	
	

}
