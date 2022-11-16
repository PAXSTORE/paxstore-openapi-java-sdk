/*
 * *******************************************************************************
 * COPYRIGHT
 *               PAX TECHNOLOGY, Inc. PROPRIETARY INFORMATION
 *   This software is supplied under the terms of a license agreement or
 *   nondisclosure agreement with PAX  Technology, Inc. and may not be copied
 *   or disclosed except in accordance with the terms in that agreement.
 *
 *      Copyright (C) 2017 PAX Technology, Inc. All rights reserved.
 * *******************************************************************************
 */

package com.pax.market.api.sdk.java.api.terminalGroupRki.dto;

import java.io.Serializable;

/**
 * @author shifan
 * @date 2021/9/24
 */
public class TerminalGroupRkiDTO implements Serializable {
    private static final long serialVersionUID = -9102103786313332658L;
    private Long id;
    private String rkiKey;
    private Long activatedDate;
    private Long effectiveTime;
    private Long expiredTime;
    private String status;
    private int actionStatus;
    private int errorCode;
    private String remarks;

    private int pendingCount;
    private int successCount;
    private int failedCount;
    private Boolean completed;
    private int pushLimit;

    public TerminalGroupRkiDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRkiKey() {
        return rkiKey;
    }

    public void setRkiKey(String rkiKey) {
        this.rkiKey = rkiKey;
    }

    public Long getActivatedDate() {
        return activatedDate;
    }

    public void setActivatedDate(Long activatedDate) {
        this.activatedDate = activatedDate;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getActionStatus() {
        return actionStatus;
    }

    public void setActionStatus(int actionStatus) {
        this.actionStatus = actionStatus;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
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

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    public int getPushLimit() {
        return pushLimit;
    }

    public void setPushLimit(int pushLimit) {
        this.pushLimit = pushLimit;
    }

    @Override
    public String toString() {
        return "TerminalGroupRkiDTO{" +
                "id=" + id +
                ", rkiKey='" + rkiKey + '\'' +
                ", activatedDate=" + activatedDate +
                ", effectiveTime=" + effectiveTime +
                ", expiredTime=" + expiredTime +
                ", status='" + status + '\'' +
                ", actionStatus=" + actionStatus +
                ", errorCode=" + errorCode +
                ", remarks='" + remarks + '\'' +
                ", pendingCount=" + pendingCount +
                ", successCount=" + successCount +
                ", failedCount=" + failedCount +
                ", completed=" + completed +
                ", pushLimit=" + pushLimit +
                '}';
    }
}
