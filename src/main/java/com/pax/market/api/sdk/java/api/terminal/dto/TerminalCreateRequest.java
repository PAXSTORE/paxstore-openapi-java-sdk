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

import com.pax.market.api.sdk.java.api.terminal.TerminalApi.TerminalStatus;

/**
 *
 * @author tanjie
 * @date 2018-07-03
 */
public class TerminalCreateRequest extends TerminalUpdateRequest{
	
	private static final long serialVersionUID = -139162061549080838L;
	
	private String status;

	public String getStatus() {
		return status;
	}

	public void setStatus(TerminalStatus status) {
		this.status = status.val();
	}
	

}
