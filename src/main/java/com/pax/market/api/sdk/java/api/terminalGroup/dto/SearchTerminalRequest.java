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
 * @Date: 2019/12/18 12:23
 * @Version 7.1
 */

@Getter
@Setter
public class SearchTerminalRequest implements Serializable {
    private String status;
    private Long modelId;
    private Long resellerId;
    private Long merchantId;
    private String serialNo;
    private Long excludeGroupId;
}
