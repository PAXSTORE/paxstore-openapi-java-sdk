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

import java.io.Serializable;

/**
 * @Description
 * @Author: Shawn
 * @Date: 2019/11/25 15:35
 * @Version 7.1
 */

@Getter
@Setter
public class CreateTerminalGroupRequest implements Serializable {
    private static final long serialVersionUID = 6285480267415151043L;

    private String name;
    private Long modelId;
    private Long resellerId;
    private String description;
    private String status;
    private Boolean dynamic = false;
    private Boolean containSubResellerTerminal = false;
    private String merchantIds;
}
