/*
 * ******************************************************************************
 * COPYRIGHT
 *               PAX TECHNOLOGY, Inc. PROPRIETARY INFORMATION
 *   This software is supplied under the terms of a license agreement or
 *   nondisclosure agreement with PAX  Technology, Inc. and may not be copied
 *   or disclosed except in accordance with the terms in that agreement.
 *
 *      Copyright (C) 2023 PAX Technology, Inc. All rights reserved.
 * ******************************************************************************
 */


package com.pax.market.api.sdk.java.api.terminalGeoFenceWhiteList.dto;

import java.io.Serializable;

/**
 * @author shifan
 * @create 2024/2/26
 */

public class TerminalGeoFenceWhiteListDTO implements Serializable {
    private static final long serialVersionUID = 3553295751513412933L;

    private Long terminalId;
    private String serialNo;

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

    @Override
    public String toString() {
        return "TerminalGeoFenceWhiteListDTO{" +
                "terminalId=" + terminalId +
                ", serialNo='" + serialNo + '\'' +
                '}';
    }
}
