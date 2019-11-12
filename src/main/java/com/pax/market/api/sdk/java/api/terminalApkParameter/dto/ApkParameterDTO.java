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
package com.pax.market.api.sdk.java.api.terminalApkParameter.dto;



import com.pax.market.api.sdk.java.api.app.dto.ApkDTO;

import java.io.Serializable;
import java.util.Date;


/**
 * @Description
 * @Author: Shawn
 * @Date: 2019/10/29 16:27
 * @Version 1.0
 */
public class ApkParameterDTO implements Serializable {


    private static final long serialVersionUID = -1798365156333350261L;
    private Long id;
    private ApkDTO apk;
    private String name;
    private String paramTemplateName;
    private Long createdDate;
    private Long updatedDate;
    private Boolean apkAvailable;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ApkDTO getApk() {
        return apk;
    }

    public void setApk(ApkDTO apk) {
        this.apk = apk;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParamTemplateName() {
        return paramTemplateName;
    }

    public void setParamTemplateName(String paramTemplateName) {
        this.paramTemplateName = paramTemplateName;
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

    public Boolean getApkAvailable() {
        return apkAvailable;
    }

    public void setApkAvailable(Boolean apkAvailable) {
        this.apkAvailable = apkAvailable;
    }
}
