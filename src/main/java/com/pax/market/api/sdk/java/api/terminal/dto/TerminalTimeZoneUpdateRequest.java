/*
 * ********************************************************************************
 * COPYRIGHT
 *               PAX TECHNOLOGY, Inc. PROPRIETARY INFORMATION
 *   This software is supplied under the terms of a license agreement or
 *   nondisclosure agreement with PAX  Technology, Inc. and may not be copied
 *   or disclosed except in accordance with the terms in that agreement.
 *
 *      Copyright (C) 2017 PAX Technology, Inc. All rights reserved.
 * ********************************************************************************
 */
package com.pax.market.api.sdk.java.api.terminal.dto;


public class TerminalTimeZoneUpdateRequest extends TerminalConfigUpdateRequest {

    private static final long serialVersionUID = 1L;

    private Boolean automaticTimeZoneEnable;
    private String timeZone;

    public Boolean getAutomaticTimeZoneEnable() {
        return automaticTimeZoneEnable;
    }

    public void setAutomaticTimeZoneEnable(Boolean automaticTimeZoneEnable) {
        this.automaticTimeZoneEnable = automaticTimeZoneEnable;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }
}
