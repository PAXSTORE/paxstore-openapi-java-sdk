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
package com.pax.market.api.sdk.java.api.terminalGroupApk.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description
 * @Author: Shawn
 * @Date: 2019/11/29 11:06
 * @Version 7.1
 */

@Getter
@Setter
public class SimpleTerminalGroupApkDTO implements Serializable {

    private static final long serialVersionUID = -2819937434488271303L;
    private Long id;
    private String apkPackageName;
    private Long apkVersionCode;
    private String apkVersionName;
    private Long effectiveTime;
    private Long expiredTime;
    private Long updatedDate;
    private int actionStatus;
    private String status;
    private int pendingCount;
    private int successCount;
    private int failedCount;

    @Override
    public String toString() {
        return "SimpleTerminalGroupApkDTO{" +
                "id=" + id +
                ", apkPackageName='" + apkPackageName + '\'' +
                ", apkVersionCode=" + apkVersionCode +
                ", apkVersionName='" + apkVersionName + '\'' +
                ", effectiveTime=" + effectiveTime +
                ", expiredTime=" + expiredTime +
                ", updatedDate=" + updatedDate +
                ", actionStatus=" + actionStatus +
                ", status='" + status + '\'' +
                ", pendingCount=" + pendingCount +
                ", successCount=" + successCount +
                ", failedCount=" + failedCount +
                '}';
    }
}
