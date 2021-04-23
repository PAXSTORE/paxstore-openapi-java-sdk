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
import java.util.List;

/**
 * @author shifan
 * @date 2020/12/1
 */
public class TerminalAccessoryDTO implements Serializable {
    private static final long serialVersionUID = 8439973186104896362L;

    private String relatedTerminalName;
    private List<TerminalDeviceSimpleDTO> basic;
    private List<TerminalDeviceSimpleDTO> hardware;
    private List<TerminalDeviceSimpleDTO> installApps;
    private List<TerminalDeviceHistoryDTO> history;

    public String getRelatedTerminalName() {
        return relatedTerminalName;
    }

    public void setRelatedTerminalName(String relatedTerminalName) {
        this.relatedTerminalName = relatedTerminalName;
    }

    public List<TerminalDeviceSimpleDTO> getBasic() {
        return basic;
    }

    public void setBasic(List<TerminalDeviceSimpleDTO> basic) {
        this.basic = basic;
    }

    public List<TerminalDeviceSimpleDTO> getHardware() {
        return hardware;
    }

    public void setHardware(List<TerminalDeviceSimpleDTO> hardware) {
        this.hardware = hardware;
    }

    public List<TerminalDeviceSimpleDTO> getInstallApps() {
        return installApps;
    }

    public void setInstallApps(List<TerminalDeviceSimpleDTO> installApps) {
        this.installApps = installApps;
    }

    public List<TerminalDeviceHistoryDTO> getHistory() {
        return history;
    }

    public void setHistory(List<TerminalDeviceHistoryDTO> history) {
        this.history = history;
    }

    @Override
    public String toString() {
        return "TerminalAccessoryDTO{" +
                "relatedTerminalName='" + relatedTerminalName + '\'' +
                ", basic=" + basic +
                ", hardware=" + hardware +
                ", installApps=" + installApps +
                ", history=" + history +
                '}';
    }
}
