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

/**
 * @Description
 * @Author: Shawn
 * @Date: 2020/3/5
 */
public class SimpleTerminalDTO implements Serializable {
    private static final long serialVersionUID = 1614462888971498901L;
    private Long id;

    private String name;

    private String tid;

    private String serialNo;

    private String status;

    private String merchantName;

    private String modelName;

    private String resellerName;

    private String location;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "SimpleTerminalDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", tid='" + tid + '\'' +
                ", serialNo='" + serialNo + '\'' +
                ", status='" + status + '\'' +
                ", merchantName='" + merchantName + '\'' +
                ", modelName='" + modelName + '\'' +
                ", resellerName='" + resellerName + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}
