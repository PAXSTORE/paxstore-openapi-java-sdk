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


import java.io.Serializable;

/**
 * @Description
 * @Author: Shawn
 * @Date: 2020/3/19
 */
public class TerminalConfigUpdateRequest implements Serializable {
    private static final long serialVersionUID = -4927756870104996001L;
    private Boolean allowReplacement;
    private Boolean automaticTimezoneEnable;
    private String timeZone;

    public Boolean getAllowReplacement() {
        return allowReplacement;
    }

    public void setAllowReplacement(Boolean allowReplacement) {
        this.allowReplacement = allowReplacement;
    }

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
}
