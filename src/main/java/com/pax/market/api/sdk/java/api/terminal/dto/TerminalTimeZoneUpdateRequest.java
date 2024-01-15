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

    private Boolean automaticTimezoneEnable;
    private String timeZone;
    private Boolean isDelete;

    public Boolean getAutomaticTimezoneEnable() {
        return automaticTimezoneEnable;
    }

    public void setAutomaticTimezoneEnable(Boolean automaticTimezoneEnable) {
        this.automaticTimezoneEnable = automaticTimezoneEnable;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public Boolean getDelete() {
        return isDelete;
    }

    public void setDelete(Boolean delete) {
        isDelete = delete;
    }
}
