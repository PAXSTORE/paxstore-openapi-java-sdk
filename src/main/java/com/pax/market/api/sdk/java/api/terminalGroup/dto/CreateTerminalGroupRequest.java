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


import java.io.Serializable;
import java.util.List;

/**
 * @Description
 * @Author: Shawn
 * @Date: 2019/11/25 15:35
 * @Version 7.1
 */

public class CreateTerminalGroupRequest implements Serializable {
    private static final long serialVersionUID = 6285480267415151043L;

    private String name;
    private String modelName;
    private String resellerName;
    private String description;
    private String status;
    private Boolean dynamic = false;
    private Boolean containSubResellerTerminal = false;
    private List<String> merchantNameList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getResellerName() {
        return resellerName;
    }

    public void setResellerName(String resellerName) {
        this.resellerName = resellerName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Boolean getDynamic() {
        return dynamic;
    }

    public void setDynamic(Boolean dynamic) {
        this.dynamic = dynamic;
    }

    public Boolean getContainSubResellerTerminal() {
        return containSubResellerTerminal;
    }

    public void setContainSubResellerTerminal(Boolean containSubResellerTerminal) {
        this.containSubResellerTerminal = containSubResellerTerminal;
    }

    public List<String> getMerchantNameList() {
        return merchantNameList;
    }

    public void setMerchantNameList(List<String> merchantNameList) {
        this.merchantNameList = merchantNameList;
    }
}
