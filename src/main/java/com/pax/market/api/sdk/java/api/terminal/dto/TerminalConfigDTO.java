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
 * @Date: 2020/4/24
 */

public class TerminalConfigDTO implements Serializable {

    private static final long serialVersionUID = 6509938259306387562L;
    private Boolean allowReplacement;
    private Boolean automaticTimezoneEnable;
    private String timeZone;
    private String language;
    /**
     * wifi list
     * */
    private String wifiList;
    /**
     * apn config list
     * */
    private String apnList;
    /**
     * wifi black list
     * */
    private String blackList;


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

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getWifiList() {
        return wifiList;
    }

    public void setWifiList(String wifiList) {
        this.wifiList = wifiList;
    }

    public String getApnList() {
        return apnList;
    }

    public void setApnList(String apnList) {
        this.apnList = apnList;
    }

    public String getBlackList() {
        return blackList;
    }

    public void setBlackList(String blackList) {
        this.blackList = blackList;
    }
}
