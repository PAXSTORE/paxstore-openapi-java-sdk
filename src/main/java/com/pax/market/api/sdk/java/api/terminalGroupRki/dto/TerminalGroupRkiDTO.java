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

    /**
     * Gets id.
     *
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the id.
     *
     * @param id the id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets rkiKey.
     *
     * @return the rkiKey
     */
    public String getRkiKey() {
        return rkiKey;
    }

    /**
     * Sets the rkiKey.
     *
     * @param rkiKey the rkiKey
     */
    public void setRkiKey(String rkiKey) {
        this.rkiKey = rkiKey;
    }

    /**
     * Gets activatedDate.
     *
     * @return the activatedDate
     */
    public Long getActivatedDate() {
        return activatedDate;
    }

    /**
     * Sets the activatedDate.
     *
     * @param activatedDate the activatedDate
     */
    public void setActivatedDate(Long activatedDate) {
        this.activatedDate = activatedDate;
    }

    /**
     * Gets effectiveTime.
     *
     * @return the effectiveTime
     */
    public Long getEffectiveTime() {
        return effectiveTime;
    }

    /**
     * Sets the effectiveTime.
     *
     * @param effectiveTime the effectiveTime
     */
    public void setEffectiveTime(Long effectiveTime) {
        this.effectiveTime = effectiveTime;
    }

    /**
     * Gets status.
     *
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the status.
     *
     * @param status the status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Gets actionStatus.
     *
     * @return the actionStatus
     */
    public int getActionStatus() {
        return actionStatus;
    }

    /**
     * Sets the actionStatus.
     *
     * @param actionStatus the actionStatus
     */
    public void setActionStatus(int actionStatus) {
        this.actionStatus = actionStatus;
    }

    /**
     * Gets errorCode.
     *
     * @return the errorCode
     */
    public int getErrorCode() {
        return errorCode;
    }

    /**
     * Sets the errorCode.
     *
     * @param errorCode the errorCode
     */
    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    /**
     * Gets remarks.
     *
     * @return the remarks
     */
    public String getRemarks() {
        return remarks;
    }

    /**
     * Sets the remarks.
     *
     * @param remarks the remarks
     */
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    /**
     * Gets pendingCount.
     *
     * @return the pendingCount
     */
    public int getPendingCount() {
        return pendingCount;
    }

    /**
     * Sets the pendingCount.
     *
     * @param pendingCount the pendingCount
     */
    public void setPendingCount(int pendingCount) {
        this.pendingCount = pendingCount;
    }

    /**
     * Gets successCount.
     *
     * @return the successCount
     */
    public int getSuccessCount() {
        return successCount;
    }

    /**
     * Sets the successCount.
     *
     * @param successCount the successCount
     */
    public void setSuccessCount(int successCount) {
        this.successCount = successCount;
    }

    /**
     * Gets failedCount.
     *
     * @return the failedCount
     */
    public int getFailedCount() {
        return failedCount;
    }

    /**
     * Sets the failedCount.
     *
     * @param failedCount the failedCount
     */
    public void setFailedCount(int failedCount) {
        this.failedCount = failedCount;
    }

    /**
     * Gets completed.
     *
     * @return the completed
     */
    public Boolean getCompleted() {
        return completed;
    }

    /**
     * Sets the completed.
     *
     * @param completed the completed
     */
    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    /**
     * Gets pushLimit.
     *
     * @return the pushLimit
     */
    public int getPushLimit() {
        return pushLimit;
    }

    /**
     * Sets the pushLimit.
     *
     * @param pushLimit the pushLimit
     */
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
