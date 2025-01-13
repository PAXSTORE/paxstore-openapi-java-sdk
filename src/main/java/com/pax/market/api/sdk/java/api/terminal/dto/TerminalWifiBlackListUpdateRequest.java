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

public class TerminalWifiBlackListUpdateRequest extends TerminalConfigUpdateRequest {

    private static final long serialVersionUID = 1L;

    private List<BlackListConfig> blackList;

    public static class BlackListConfig implements Serializable {
        private String wifiName;

        public String getWifiName() {
            return wifiName;
        }

        public void setWifiName(String wifiName) {
            this.wifiName = wifiName;
        }
    }

    public List<BlackListConfig> getBlackList() {
        return blackList;
    }

    public void setBlackList(List<BlackListConfig> blackList) {
        this.blackList = blackList;
    }
}
