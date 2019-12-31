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

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @Description
 * @Author: Shawn
 * @Date: 2019/11/19 9:19
 * @Version 7.1
 */

@Getter
@Setter
public class EntityAttributeCreateRequest implements Serializable {
    private static final long serialVersionUID = -2639872173785717404L;

    private String entityType;
    private String inputType;
    private Integer minLength;
    private Integer maxLength;
    private boolean required;
    private String regex;
    private String selector;
    private String key;
    private String defaultLabel;

}
