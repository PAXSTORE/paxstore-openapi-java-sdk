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
import java.util.List;

/**
 * @Description
 * @Author: Shawn
 * @Date: 2019/11/15 16:10
 * @Version 7.1
 */

@Getter
@Setter
public class EntityAttributeDTO implements Serializable {

    private static final long serialVersionUID = 8784795559463211636L;
    private Long id;
    private String entityType;
    private String inputType;
    private Integer minLength;
    private Integer maxLength;
    private boolean required;
    private String regex;
    private String selector;
    private String key;
    private int index;
    private String defaultLabel;
    private List<EntityAttributeLabelInfo> entityAttributeLabelList;

    @Override
    public String toString() {
       return "EntityAttributeDTO {" +
                "id=" + id +
                ", entityType='" + entityType + '\'' +
                ", inputType='" + inputType + '\'' +
                ", minLength='" + minLength + '\'' +
                ", maxLength='" + maxLength + '\'' +
                ", required=" + required +
                ", regex=" + regex +
                ", selector=" + selector +
                ", key=" + key +
                ", index=" + index +
                ", defaultLabel=" + defaultLabel +
                ", entityAttributeLabelList=" + entityAttributeLabelList +
                '}';
    }
}
