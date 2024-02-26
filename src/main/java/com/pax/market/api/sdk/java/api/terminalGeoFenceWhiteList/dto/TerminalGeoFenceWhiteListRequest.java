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
 * @create 2024/2/23
 */
public class TerminalGeoFenceWhiteListRequest implements Serializable {
    private static final long serialVersionUID = 9132219581674985388L;
    private String serialNo;

    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

}
