/*
 * *******************************************************************************
 * COPYRIGHT
 *               PAX TECHNOLOGY, Inc. PROPRIETARY INFORMATION
 *   This software is supplied under the terms of a license agreement or
 *   nondisclosure agreement with PAX  Technology, Inc. and may not be copied
 *   or disclosed except in accordance with the terms in that agreement.
 *
 *      Copyright (C) 2023 PAX Technology, Inc. All rights reserved.
 * *******************************************************************************
 */

package com.pax.market.api.sdk.java.api.terminal.dto;


import java.io.Serializable;

/**
 *
 * @author shifan
 * @date 2023/4/20
 */
public class TerminalNetworkDTO implements Serializable {
    private static final long serialVersionUID = 8242949321843336127L;
    private Long id;
    private String tid;
    private String serialNo;
    private String status;
    private Float battery;
    private Integer onlineStatus;
    private String network;
    private String macAddress;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Float getBattery() {
        return battery;
    }

    public void setBattery(Float battery) {
        this.battery = battery;
    }

    public Integer getOnlineStatus() {
        return onlineStatus;
    }

    public void setOnlineStatus(Integer onlineStatus) {
        this.onlineStatus = onlineStatus;
    }

    public String getNetwork() {
        return network;
    }

    public void setNetwork(String network) {
        this.network = network;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    @Override
    public String toString() {
        return "TerminalNetworkDTO{" +
                "id=" + id +
                ", tid='" + tid + '\'' +
                ", serialNo='" + serialNo + '\'' +
                ", status='" + status + '\'' +
                ", battery=" + battery +
                ", onlineStatus=" + onlineStatus +
                ", network='" + network + '\'' +
                ", macAddress='" + macAddress + '\'' +
                '}';
    }
}