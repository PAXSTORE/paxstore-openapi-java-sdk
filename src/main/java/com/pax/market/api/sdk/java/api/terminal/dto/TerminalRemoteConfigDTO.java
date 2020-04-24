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
 * @Date: 2020/4/24
 */

public class TerminalRemoteConfigDTO implements Serializable {

    private static final long serialVersionUID = 6509938259306387562L;
    private Boolean allowRemoteChange;

    public Boolean getAllowRemoteChange() {
        return allowRemoteChange;
    }

    public void setAllowRemoteChange(Boolean allowRemoteChange) {
        this.allowRemoteChange = allowRemoteChange;
    }
}
