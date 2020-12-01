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
    List<TerminalDeviceDTO> basic;
    List<TerminalDeviceDTO> hardware;
    List<TerminalDeviceDTO> installApps;
    List<TerminalDeviceDTO> history;

    public String getRelatedTerminalName() {
        return relatedTerminalName;
    }

    public void setRelatedTerminalName(String relatedTerminalName) {
        this.relatedTerminalName = relatedTerminalName;
    }

    public List<TerminalDeviceDTO> getBasic() {
        return basic;
    }

    public void setBasic(List<TerminalDeviceDTO> basic) {
        this.basic = basic;
    }

    public List<TerminalDeviceDTO> getHardware() {
        return hardware;
    }

    public void setHardware(List<TerminalDeviceDTO> hardware) {
        this.hardware = hardware;
    }

    public List<TerminalDeviceDTO> getInstallApps() {
        return installApps;
    }

    public void setInstallApps(List<TerminalDeviceDTO> installApps) {
        this.installApps = installApps;
    }

    public List<TerminalDeviceDTO> getHistory() {
        return history;
    }

    public void setHistory(List<TerminalDeviceDTO> history) {
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
