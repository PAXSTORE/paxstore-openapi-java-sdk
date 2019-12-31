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
package com.pax.market.api.sdk.java.api.pushHistory.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * The type App push history.
 */
public class ParameterPushHistoryDTO implements Serializable {
    private static final long serialVersionUID = -7972757906620772968L;

    private Long terminalId;
    private String serialNo;
    private String appName;
    private String versionName;
    private Date pushStartTime;
    private Date appPushTime;
    private String appPushStatus;
    private String appPushError;
    private Date parameterPushTime;
    private String parameterPushStatus;
    private String parameterPushError;
    private String parameterValues;
    private String parameterVariables;
    private String pushType;

    /**
     * Gets terminal id.
     *
     * @return the terminal id
     */
    public Long getTerminalId() {
        return terminalId;
    }

    /**
     * Sets terminal id.
     *
     * @param terminalId the terminal id
     */
    public void setTerminalId(Long terminalId) {
        this.terminalId = terminalId;
    }

    /**
     * Gets serial no.
     *
     * @return the serial no
     */
    public String getSerialNo() {
        return serialNo;
    }

    /**
     * Sets serial no.
     *
     * @param serialNo the serial no
     */
    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    /**
     * Gets app name.
     *
     * @return the app name
     */
    public String getAppName() {
        return appName;
    }

    /**
     * Sets app name.
     *
     * @param appName the app name
     */
    public void setAppName(String appName) {
        this.appName = appName;
    }

    /**
     * Gets version name.
     *
     * @return the version name
     */
    public String getVersionName() {
        return versionName;
    }

    /**
     * Sets version name.
     *
     * @param versionName the version name
     */
    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    /**
     * Gets push start time.
     *
     * @return the push start time
     */
    public Date getPushStartTime() {
        return pushStartTime;
    }

    /**
     * Sets push start time.
     *
     * @param pushStartTime the push start time
     */
    public void setPushStartTime(Date pushStartTime) {
        this.pushStartTime = pushStartTime;
    }

    /**
     * Gets app push time.
     *
     * @return the app push time
     */
    public Date getAppPushTime() {
        return appPushTime;
    }

    /**
     * Sets app push time.
     *
     * @param appPushTime the app push time
     */
    public void setAppPushTime(Date appPushTime) {
        this.appPushTime = appPushTime;
    }

    /**
     * Gets app push status.
     *
     * @return the app push status
     */
    public String getAppPushStatus() {
        return appPushStatus;
    }

    /**
     * Sets app push status.
     *
     * @param appPushStatus the app push status
     */
    public void setAppPushStatus(String appPushStatus) {
        this.appPushStatus = appPushStatus;
    }

    /**
     * Gets app push error.
     *
     * @return the app push error
     */
    public String getAppPushError() {
        return appPushError;
    }

    /**
     * Sets app push error.
     *
     * @param appPushError the app push error
     */
    public void setAppPushError(String appPushError) {
        this.appPushError = appPushError;
    }

    /**
     * Gets parameter push time.
     *
     * @return the parameter push time
     */
    public Date getParameterPushTime() {
        return parameterPushTime;
    }

    /**
     * Sets parameter push time.
     *
     * @param parameterPushTime the parameter push time
     */
    public void setParameterPushTime(Date parameterPushTime) {
        this.parameterPushTime = parameterPushTime;
    }

    /**
     * Gets parameter push status.
     *
     * @return the parameter push status
     */
    public String getParameterPushStatus() {
        return parameterPushStatus;
    }

    /**
     * Sets parameter push status.
     *
     * @param parameterPushStatus the parameter push status
     */
    public void setParameterPushStatus(String parameterPushStatus) {
        this.parameterPushStatus = parameterPushStatus;
    }

    /**
     * Gets parameter push error.
     *
     * @return the parameter push error
     */
    public String getParameterPushError() {
        return parameterPushError;
    }

    /**
     * Sets parameter push error.
     *
     * @param parameterPushError the parameter push error
     */
    public void setParameterPushError(String parameterPushError) {
        this.parameterPushError = parameterPushError;
    }

    /**
     * Gets parameter values.
     *
     * @return the parameter values
     */
    public String getParameterValues() {
        return parameterValues;
    }

    /**
     * Sets parameter values.
     *
     * @param parameterValues the parameter values
     */
    public void setParameterValues(String parameterValues) {
        this.parameterValues = parameterValues;
    }

    /**
     * Gets parameter variables.
     *
     * @return the parameter variables
     */
    public String getParameterVariables() {
        return parameterVariables;
    }

    /**
     * Sets parameter variables.
     *
     * @param parameterVariables the parameter variables
     */
    public void setParameterVariables(String parameterVariables) {
        this.parameterVariables = parameterVariables;
    }

    /**
     * Gets push type.
     *
     * @return the push type
     */
    public String getPushType() {
        return pushType;
    }

    /**
     * Sets push type.
     *
     * @param pushType the push type
     */
    public void setPushType(String pushType) {
        this.pushType = pushType;
    }

    @Override
    public String toString() {
        return "AppPushHistoryDTO{" +
                "terminalId=" + terminalId +
                ", serialNo='" + serialNo + '\'' +
                ", appName='" + appName + '\'' +
                ", versionName='" + versionName + '\'' +
                ", pushStartTime=" + pushStartTime +
                ", appPushTime=" + appPushTime +
                ", appPushStatus='" + appPushStatus + '\'' +
                ", appPushError='" + appPushError + '\'' +
                ", parameterPushTime=" + parameterPushTime +
                ", parameterPushStatus='" + parameterPushStatus + '\'' +
                ", parameterPushError='" + parameterPushError + '\'' +
                ", parameterValues='" + parameterValues + '\'' +
                ", parameterVariables='" + parameterVariables + '\'' +
                ", pushType='" + pushType + '\'' +
                '}';
    }
}
