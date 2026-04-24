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
package com.pax.market.api.sdk.java.api.terminalGroupApk.dto;

/**
 * Create Terminal Group Apk Request
 *
 * @author zhoudong
 */
public class CreateTerminalGroupApkPartialParamRequest extends CreateTerminalGroupApkRequest {


    private static final long serialVersionUID = -5625311328550486701L;

    private String partialPid;

    /**
     * Gets partial pid
     * @return
     */
    public String getPartialPid() {
        return partialPid;
    }

    /**
     * Sets partial pid
     * @param partialPid
     */
    public void setPartialPid(String partialPid) {
        this.partialPid = partialPid;
    }
}
