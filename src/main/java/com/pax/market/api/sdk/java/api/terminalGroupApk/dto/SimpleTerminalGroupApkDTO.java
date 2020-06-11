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

import java.io.Serializable;

/**
 * @Description
 * @Author: Shawn
 * @Date: 2019/11/29 11:06
 * @Version 7.1
 */

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
    private TerminalGroupApkParamDTO groupApkParam;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getApkPackageName() {
        return apkPackageName;
    }

    public void setApkPackageName(String apkPackageName) {
        this.apkPackageName = apkPackageName;
    }

    public Long getApkVersionCode() {
        return apkVersionCode;
    }

    public void setApkVersionCode(Long apkVersionCode) {
        this.apkVersionCode = apkVersionCode;
    }

    public String getApkVersionName() {
        return apkVersionName;
    }

    public void setApkVersionName(String apkVersionName) {
        this.apkVersionName = apkVersionName;
    }

    public Long getEffectiveTime() {
        return effectiveTime;
    }

    public void setEffectiveTime(Long effectiveTime) {
        this.effectiveTime = effectiveTime;
    }

    public Long getExpiredTime() {
        return expiredTime;
    }

    public void setExpiredTime(Long expiredTime) {
        this.expiredTime = expiredTime;
    }

    public Long getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Long updatedDate) {
        this.updatedDate = updatedDate;
    }

    public int getActionStatus() {
        return actionStatus;
    }

    public void setActionStatus(int actionStatus) {
        this.actionStatus = actionStatus;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getPendingCount() {
        return pendingCount;
    }

    public void setPendingCount(int pendingCount) {
        this.pendingCount = pendingCount;
    }

    public int getSuccessCount() {
        return successCount;
    }

    public void setSuccessCount(int successCount) {
        this.successCount = successCount;
    }

    public int getFailedCount() {
        return failedCount;
    }

    public void setFailedCount(int failedCount) {
        this.failedCount = failedCount;
    }

    public TerminalGroupApkParamDTO getGroupApkParam() {
        return groupApkParam;
    }

    public void setGroupApkParam(TerminalGroupApkParamDTO groupApkParam) {
        this.groupApkParam = groupApkParam;
    }

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
                ", groupApkParam=" + groupApkParam +
                '}';
    }
}
