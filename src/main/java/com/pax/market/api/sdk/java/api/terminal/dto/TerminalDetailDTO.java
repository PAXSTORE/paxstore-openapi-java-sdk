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
 * @author shifan
 * @date 2020/12/1
 */
public class TerminalDetailDTO implements Serializable {

    private static final long serialVersionUID = -5613136468738069509L;
    private String pn;
    private String osVersion;
    private String imei;
    private String screenResolution;
    private String language;
    private String ip;
    private String timeZone;
    private String macAddress;
    private String iccid;
    private String cellid;

    public String getPn() {
        return pn;
    }

    public void setPn(String pn) {
        this.pn = pn;
    }

    public String getOsVersion() {
        return osVersion;
    }

    public void setOsVersion(String osVersion) {
        this.osVersion = osVersion;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getScreenResolution() {
        return screenResolution;
    }

    public void setScreenResolution(String screenResolution) {
        this.screenResolution = screenResolution;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    public String getIccid() {
        return iccid;
    }

    public void setIccid(String iccid) {
        this.iccid = iccid;
    }

    public String getCellid() {
        return cellid;
    }

    public void setCellid(String cellid) {
        this.cellid = cellid;
    }

    @Override
    public String toString() {
        return "TerminalDetailDTO{" +
                "pn='" + pn + '\'' +
                ", osVersion='" + osVersion + '\'' +
                ", imei='" + imei + '\'' +
                ", screenResolution='" + screenResolution + '\'' +
                ", language='" + language + '\'' +
                ", ip='" + ip + '\'' +
                ", timeZone='" + timeZone + '\'' +
                ", macAddress='" + macAddress + '\'' +
                ", iccid='" + iccid + '\'' +
                ", cellid='" + cellid + '\'' +
                '}';
    }
}
