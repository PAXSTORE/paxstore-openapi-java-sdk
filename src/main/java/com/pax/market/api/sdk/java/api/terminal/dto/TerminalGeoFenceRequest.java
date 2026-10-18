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

import com.pax.market.api.sdk.java.api.terminal.TerminalApi;

import java.io.Serializable;

/**
 * The request to set a security geofence for a terminal according to a geofence template.
 *
 * @author sdk
 */
public class TerminalGeoFenceRequest implements Serializable {

	private static final long serialVersionUID = 1L;

	private String templateName;
	private String geofenceType;

	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	public String getGeofenceType() {
		return geofenceType;
	}

	public void setGeofenceType(String geofenceType) {
		this.geofenceType = geofenceType;
	}

	/**
	 * Set geofence type by the enum value, the serialized value is the code(P/C/B).
	 *
	 * @param geofenceType the geofence type
	 */
	public void setGeofenceType(TerminalApi.TerminalGeoFenceType geofenceType) {
		if (geofenceType != null) {
			this.geofenceType = geofenceType.val();
		}
	}
}
