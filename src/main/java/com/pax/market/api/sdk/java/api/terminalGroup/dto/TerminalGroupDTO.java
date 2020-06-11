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
 * @Date: 2019/11/26 9:37
 * @Version 7.1
 */

public class TerminalGroupDTO implements Serializable {


    private static final long serialVersionUID = -2375133176041768065L;
    private Long id;
    private String resellerName;
    private String modelName;
    private String name;
    private String status;
    private String description;
    private Long createdByResellerId;
    private Long createdDate;
    private Long updatedDate;
    private int terminalCount;
    private Boolean dynamic;
    private Boolean containSubResellerTerminal;
    private List<String> merchantNames;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getResellerName() {
        return resellerName;
    }

    public void setResellerName(String resellerName) {
        this.resellerName = resellerName;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getCreatedByResellerId() {
        return createdByResellerId;
    }

    public void setCreatedByResellerId(Long createdByResellerId) {
        this.createdByResellerId = createdByResellerId;
    }

    public Long getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Long createdDate) {
        this.createdDate = createdDate;
    }

    public Long getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Long updatedDate) {
        this.updatedDate = updatedDate;
    }

    public int getTerminalCount() {
        return terminalCount;
    }

    public void setTerminalCount(int terminalCount) {
        this.terminalCount = terminalCount;
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

    public List<String> getMerchantNames() {
        return merchantNames;
    }

    public void setMerchantNames(List<String> merchantNames) {
        this.merchantNames = merchantNames;
    }

    @Override
    public String toString() {
        return "TerminalGroupDTO{" +
                "id=" + id +
                ", resellerName='" + resellerName + '\'' +
                ", modelName='" + modelName + '\'' +
                ", name='" + name + '\'' +
                ", status='" + status + '\'' +
                ", description='" + description + '\'' +
                ", createdByResellerId=" + createdByResellerId +
                ", createdDate=" + createdDate +
                ", updatedDate=" + updatedDate +
                ", terminalCount=" + terminalCount +
                ", dynamic=" + dynamic +
                ", containSubResellerTerminal=" + containSubResellerTerminal +
                ", merchantNames=" + merchantNames +
                '}';
    }
}
