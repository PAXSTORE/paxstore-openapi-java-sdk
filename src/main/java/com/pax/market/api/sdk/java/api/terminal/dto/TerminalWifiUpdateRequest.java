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
import java.util.List;

public class TerminalWifiUpdateRequest extends TerminalConfigUpdateRequest {

    private static final long serialVersionUID = 1L;

    private List<WifiConfig> wifiList;

    public List<WifiConfig> getWifiList() {
        return wifiList;
    }

    public void setWifiList(List<WifiConfig> wifiList) {
        this.wifiList = wifiList;
    }

    public static class WifiConfig implements Serializable {
        private String SSID;
        private String password;
        private String cipherType;
        private String proxyType;
        private String hostName;
        private String port;
        private String pacUrl;

        public String getSSID() {
            return SSID;
        }

        public void setSSID(String SSID) {
            this.SSID = SSID;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getCipherType() {
            return cipherType;
        }

        public void setCipherType(String cipherType) {
            this.cipherType = cipherType;
        }

        public String getProxyType() {
            return proxyType;
        }

        public void setProxyType(String proxyType) {
            this.proxyType = proxyType;
        }

        public String getHostName() {
            return hostName;
        }

        public void setHostName(String hostName) {
            this.hostName = hostName;
        }

        public String getPort() {
            return port;
        }

        public void setPort(String port) {
            this.port = port;
        }

        public String getPacUrl() {
            return pacUrl;
        }

        public void setPacUrl(String pacUrl) {
            this.pacUrl = pacUrl;
        }
    }
}
