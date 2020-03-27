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

/**
 * @Description
 * @Author: Shawn
 * @Date: 2020/3/19
 */
public class TerminalRemoteConfigRequest implements Serializable {
    private static final long serialVersionUID = -4927756870104996001L;
    private boolean allowRemoteChange;

    public boolean isAllowRemoteChange() {
        return allowRemoteChange;
    }

    public void setAllowRemoteChange(boolean allowRemoteChange) {
        this.allowRemoteChange = allowRemoteChange;
    }
}
