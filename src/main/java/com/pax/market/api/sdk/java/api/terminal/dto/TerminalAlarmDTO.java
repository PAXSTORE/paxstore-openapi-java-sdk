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
 * @author tanjie
 * @date 2018-07-02
 */
public class TerminalAlarmDTO implements Serializable {

    private static final long serialVersionUID = -2073460668951107317L;

    private Long terminalId;
    private String serialNo;
    private String name;
    private String tid;
    private Date alertTime;

    public Long getTerminalId() {
        return terminalId;
    }

    public void setTerminalId(Long terminalId) {
        this.terminalId = terminalId;
    }

    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public Date getAlertTime() {
        return alertTime;
    }

    public void setAlertTime(Date alertTime) {
        this.alertTime = alertTime;
    }

    @Override
    public String toString() {
        return "TerminalAlarmDTO{" +
                "terminalId=" + terminalId +
                ", serialNo='" + serialNo + '\'' +
                ", name='" + name + '\'' +
                ", tid='" + tid + '\'' +
                ", alertTime=" + alertTime +
                '}';
    }
}
