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

import java.io.Serializable;

/**
 * @Description
 * @Author: Shawn
 * @Date: 2019/11/19 9:19
 * @Version 7.1
 */


public class EntityAttributeCreateRequest implements Serializable {
    private static final long serialVersionUID = -2639872173785717404L;

    private EntityAttributeApi.EntityAttributeType entityType;
    private EntityAttributeApi.EntityInputType inputType;
    private Integer minLength;
    private Integer maxLength;
    private boolean required;
    private String regex;
    private String selector;
    private String key;
    private String defaultLabel;

    public EntityAttributeApi.EntityAttributeType getEntityType() {
        return entityType;
    }

    public void setEntityType(EntityAttributeApi.EntityAttributeType entityType) {
        this.entityType = entityType;
    }

    public EntityAttributeApi.EntityInputType getInputType() {
        return inputType;
    }

    public void setInputType(EntityAttributeApi.EntityInputType inputType) {
        this.inputType = inputType;
    }

    public Integer getMinLength() {
        return minLength;
    }

    public void setMinLength(Integer minLength) {
        this.minLength = minLength;
    }

    public Integer getMaxLength() {
        return maxLength;
    }

    public void setMaxLength(Integer maxLength) {
        this.maxLength = maxLength;
    }

    public boolean isRequired() {
        return required;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }

    public String getRegex() {
        return regex;
    }

    public void setRegex(String regex) {
        this.regex = regex;
    }

    public String getSelector() {
        return selector;
    }

    public void setSelector(String selector) {
        this.selector = selector;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getDefaultLabel() {
        return defaultLabel;
    }

    public void setDefaultLabel(String defaultLabel) {
        this.defaultLabel = defaultLabel;
    }
}
