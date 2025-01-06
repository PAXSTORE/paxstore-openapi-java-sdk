/*
 * *******************************************************************************
 * COPYRIGHT
 *               PAX TECHNOLOGY, Inc. PROPRIETARY INFORMATION
 *   This software is supplied under the terms of a license agreement or
 *   nondisclosure agreement with PAX  Technology, Inc. and may not be copied
 *   or disclosed except in accordance with the terms in that agreement.
 *
 *      Copyright (C) 2022 PAX Technology, Inc. All rights reserved.
 * *******************************************************************************
 */

package com.pax.market.api.sdk.java.api.terminal.dto;

import java.io.Serializable;

/**
 * @author shifan
 * @date 2022/10/20
 */
public class TerminalSystemUsageDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Double totalCpuUsage = 0D;
    private Long totalStorageUsage = 0L;
    private Long totalRamUsage;
    private Long totalRAM;
    private Long totalStorage;

    public Double getTotalCpuUsage() {
        return totalCpuUsage;
    }

    public void setTotalCpuUsage(Double totalCpuUsage) {
        this.totalCpuUsage = totalCpuUsage;
    }

    public Long getTotalStorageUsage() {
        return totalStorageUsage;
    }

    public void setTotalStorageUsage(Long totalStorageUsage) {
        this.totalStorageUsage = totalStorageUsage;
    }

    public Long getTotalRamUsage() {
        return totalRamUsage;
    }

    public void setTotalRamUsage(Long totalRamUsage) {
        this.totalRamUsage = totalRamUsage;
    }

    public Long getTotalRAM() {
        return totalRAM;
    }

    public void setTotalRAM(Long totalRAM) {
        this.totalRAM = totalRAM;
    }

    public Long getTotalStorage() {
        return totalStorage;
    }

    public void setTotalStorage(Long totalStorage) {
        this.totalStorage = totalStorage;
    }
}