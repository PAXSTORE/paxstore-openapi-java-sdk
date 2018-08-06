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
 * @date 2018-06-21
 */

public class SimpleResellerDTO implements Serializable{

	private static final long serialVersionUID = -1538478075420858382L;


	private Long id;
	

	private String name;
	


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

	@Override
	public String toString() {
		return "SimpleResellerDTO [id=" + id + ", name=" + name + "]";
	}


	
}
