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
import java.util.Map;


public class TerminalGroupApkParamDTO implements Serializable {

    private static final long serialVersionUID = -2819937434488271303L;
    private String paramTemplateName;
    private Map<String, String> configuredParameters;
    private int pendingCount;
    private int successCount;
    private int failedCount;
    private int filteredCount;

    public String getParamTemplateName() {
        return paramTemplateName;
    }

    public void setParamTemplateName(String paramTemplateName) {
        this.paramTemplateName = paramTemplateName;
    }

    public Map<String, String> getConfiguredParameters() {
        return configuredParameters;
    }

    public void setConfiguredParameters(Map<String, String> configuredParameters) {
        this.configuredParameters = configuredParameters;
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


    public int getFilteredCount() {
        return filteredCount;
    }

    public void setFilteredCount(int filteredCount) {
        this.filteredCount = filteredCount;
    }

    @Override
    public String toString() {
        return "TerminalGroupApkParamDTO{" +
                "paramTemplateName='" + paramTemplateName + '\'' +
                ", configuredParameters=" + configuredParameters +
                ", pendingCount=" + pendingCount +
                ", successCount=" + successCount +
                ", failedCount=" + failedCount +
                ", filteredCount=" + filteredCount +
                '}';
    }
}
