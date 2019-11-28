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
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;



/**
 * @Description
 * @Author: Shawn
 * @Date: 2019/10/29 16:27
 * @Version 1.0
 */
@Getter
@Setter
public class ApkParameterDTO implements Serializable {


    private static final long serialVersionUID = -1798365156333350261L;
    private Long id;
    private ApkDTO apk;
    private String name;
    private String paramTemplateName;
    private Long createdDate;
    private Long updatedDate;
    private Boolean apkAvailable;

    @Override
    public String toString() {
        return "ApkParameterDTO {" +
                "id=" + id +
                ", apk='" + apk + '\'' +
                ", name='" + name + '\'' +
                ", paramTemplateName='" + paramTemplateName + '\'' +
                ", createdDate='" + createdDate + '\'' +
                ", updatedDate=" + updatedDate +
                ", apkAvailable=" + apkAvailable +
                '}';
    }
}
