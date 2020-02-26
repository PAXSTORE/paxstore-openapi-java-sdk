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
package com.pax.market.api.sdk.java.api.entityAttribute.dto;

import com.pax.market.api.sdk.java.api.entityAttribute.EntityAttributeApi;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @Description
 * @Author: Shawn
 * @Date: 2019/11/19 9:43
 * @Version 7.1
 */
@Getter
@Setter
public class EntityAttributeUpdateRequest implements Serializable {
    private static final long serialVersionUID = 4334504898768526684L;

    private EntityAttributeApi.EntityInputType inputType;
    private Integer minLength;
    private Integer maxLength;
    private boolean required;
    private String regex;
    private String selector;
    private String defaultLabel;

}
