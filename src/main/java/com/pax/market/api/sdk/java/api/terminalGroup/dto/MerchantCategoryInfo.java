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
import java.util.Date;

/**
 * @Description
 * @Author: Shawn
 * @Date: 2019/11/26 9:51
 * @Version 1.0
 */
@Getter
@Setter
public class MerchantCategoryInfo implements Serializable {

    private static final long serialVersionUID = -5851843819972585429L;
    private Long id;
    private String name;
    private String remarks;
    private Date createdDate;
    private Date updatedDate;
}
