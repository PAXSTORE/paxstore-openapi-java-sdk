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
 * @Date: 2020/5/18
 */
public class TerminalPedDTO implements Serializable {

    private static final long serialVersionUID = -6058398021433436896L;

    private String info;

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return "TerminalPedDTO{" +
                "info='" + info + '\'' +
                '}';
    }
}
