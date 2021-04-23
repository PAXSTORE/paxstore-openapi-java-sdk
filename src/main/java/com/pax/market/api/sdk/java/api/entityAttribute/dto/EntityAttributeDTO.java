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

import java.io.Serializable;
import java.util.List;

/**
 * @Description
 * @Author: Shawn
 * @Date: 2019/11/15 16:10
 * @Version 7.1
 */


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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEntityType() {
        return entityType;
    }

    public void setEntityType(String entityType) {
        this.entityType = entityType;
    }

    public String getInputType() {
        return inputType;
    }

    public void setInputType(String inputType) {
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

    @Deprecated
    public String getRegex() {
        return regex;
    }

    @Deprecated
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

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getDefaultLabel() {
        return defaultLabel;
    }

    public void setDefaultLabel(String defaultLabel) {
        this.defaultLabel = defaultLabel;
    }

    public List<EntityAttributeLabelInfo> getEntityAttributeLabelList() {
        return entityAttributeLabelList;
    }

    public void setEntityAttributeLabelList(List<EntityAttributeLabelInfo> entityAttributeLabelList) {
        this.entityAttributeLabelList = entityAttributeLabelList;
    }

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
