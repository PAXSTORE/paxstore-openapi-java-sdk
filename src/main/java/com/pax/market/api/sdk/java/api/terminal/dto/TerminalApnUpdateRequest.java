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

public class TerminalApnUpdateRequest extends TerminalConfigUpdateRequest {

    private static final long serialVersionUID = 1L;

    private List<ApnConfig> apnList;

    public static class ApnConfig implements Serializable {
        private String name;
        private String apn;
        private String type;
        private String proxy;
        private String port;
        private String user;
        private String password;
        private String server;
        private String mmsc;
        private String mmsproxy;
        private String mmsport;
        private String mcc;
        private String mnc;
        private String authtype;
        private String protocol;
        private String roaming_protocol;
        private String mvno_type;
        private String mvno_match_data;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getApn() {
            return apn;
        }

        public void setApn(String apn) {
            this.apn = apn;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getProxy() {
            return proxy;
        }

        public void setProxy(String proxy) {
            this.proxy = proxy;
        }

        public String getPort() {
            return port;
        }

        public void setPort(String port) {
            this.port = port;
        }

        public String getUser() {
            return user;
        }

        public void setUser(String user) {
            this.user = user;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getServer() {
            return server;
        }

        public void setServer(String server) {
            this.server = server;
        }

        public String getMmsc() {
            return mmsc;
        }

        public void setMmsc(String mmsc) {
            this.mmsc = mmsc;
        }

        public String getMmsproxy() {
            return mmsproxy;
        }

        public void setMmsproxy(String mmsproxy) {
            this.mmsproxy = mmsproxy;
        }

        public String getMmsport() {
            return mmsport;
        }

        public void setMmsport(String mmsport) {
            this.mmsport = mmsport;
        }

        public String getMcc() {
            return mcc;
        }

        public void setMcc(String mcc) {
            this.mcc = mcc;
        }

        public String getMnc() {
            return mnc;
        }

        public void setMnc(String mnc) {
            this.mnc = mnc;
        }

        public String getAuthtype() {
            return authtype;
        }

        public void setAuthtype(String authtype) {
            this.authtype = authtype;
        }

        public String getProtocol() {
            return protocol;
        }

        public void setProtocol(String protocol) {
            this.protocol = protocol;
        }

        public String getRoaming_protocol() {
            return roaming_protocol;
        }

        public void setRoaming_protocol(String roaming_protocol) {
            this.roaming_protocol = roaming_protocol;
        }

        public String getMvno_type() {
            return mvno_type;
        }

        public void setMvno_type(String mvno_type) {
            this.mvno_type = mvno_type;
        }

        public String getMvno_match_data() {
            return mvno_match_data;
        }

        public void setMvno_match_data(String mvno_match_data) {
            this.mvno_match_data = mvno_match_data;
        }
    }

    public List<ApnConfig> getApnList() {
        return apnList;
    }

    public void setApnList(List<ApnConfig> apnList) {
        this.apnList = apnList;
    }
}
