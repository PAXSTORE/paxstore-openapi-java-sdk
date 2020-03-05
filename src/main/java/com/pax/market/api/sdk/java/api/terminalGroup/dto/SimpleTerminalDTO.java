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
package com.pax.market.api.sdk.java.api.terminalGroup.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @Description
 * @Author: Shawn
 * @Date: 2020/3/5
 */
@Getter
@Setter
@ToString
public class SimpleTerminalDTO implements Serializable {
    private static final long serialVersionUID = 1614462888971498901L;
    private Long id;

    private String name;

    private String tid;

    private String serialNo;

    private String status;

    private String merchantName;

    private String modelName;

    private String resellerName;

    private String location;
}
