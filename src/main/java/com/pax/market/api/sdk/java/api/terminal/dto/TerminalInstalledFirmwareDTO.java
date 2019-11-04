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
import java.util.Date;

/**
 * The type Terminal installed firmware dto.
 */
public class TerminalInstalledFirmwareDTO implements Serializable {
    private static final long serialVersionUID = -1L;

    private String firmwareName;
    private Date installTime;

    /**
     * Gets firmware name.
     *
     * @return the firmware name
     */
    public String getFirmwareName() {
        return firmwareName;
    }

    /**
     * Sets firmware name.
     *
     * @param firmwareName the firmware name
     */
    public void setFirmwareName(String firmwareName) {
        this.firmwareName = firmwareName;
    }

    /**
     * Gets install time.
     *
     * @return the install time
     */
    public Date getInstallTime() {
        return installTime;
    }

    /**
     * Sets install time.
     *
     * @param installTime the install time
     */
    public void setInstallTime(Date installTime) {
        this.installTime = installTime;
    }

    @Override
    public String toString() {
        return "TerminalInstalledFirmwareDTO{" +
                "firmwareName='" + firmwareName + '\'' +
                ", installTime=" + installTime +
                '}';
    }
}
