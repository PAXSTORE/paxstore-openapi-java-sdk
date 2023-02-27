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
package com.pax.market.api.sdk.java.api.terminal.dto;


import java.io.Serializable;

/**
 * The type Terminal move request.
 */
public class TerminalMoveRequest implements Serializable{
	
	private static final long serialVersionUID = 748906161939046192L;

	/**
	 * The Reseller name.
	 */
	protected String resellerName;

	/**
	 * The Merchant name.
	 */
	protected String merchantName;


	/**
	 * Gets reseller name.
	 *
	 * @return the reseller name
	 */
	public String getResellerName() {
		return resellerName;
	}

	/**
	 * Sets reseller name.
	 *
	 * @param resellerName the reseller name
	 */
	public void setResellerName(String resellerName) {
		this.resellerName = resellerName;
	}

	/**
	 * Gets merchant name.
	 *
	 * @return the merchant name
	 */
	public String getMerchantName() {
		return merchantName;
	}

	/**
	 * Sets merchant name.
	 *
	 * @param merchantName the merchant name
	 */
	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}


	@Override
	public String toString() {
		return "TerminalMoveRequest{" +
				"resellerName='" + resellerName + '\'' +
				", merchantName='" + merchantName + '\'' +
				'}';
	}
}
