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

package com.pax.market.api.sdk.java.api.terminal.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * The type Terminal installed apk dto.
 */
public class TerminalInstalledApkDTO implements Serializable {
    private static final long serialVersionUID = -1L;

    private String appName;
    private String packageName;
    private String versionName;
    private Long versionCode;
    private Date installTime;

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
     * Gets package name.
     *
     * @return the package name
     */
    public String getPackageName() {
        return packageName;
    }

    /**
     * Sets package name.
     *
     * @param packageName the package name
     */
    public void setPackageName(String packageName) {
        this.packageName = packageName;
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
     * Gets version code.
     *
     * @return the version code
     */
    public Long getVersionCode() {
        return versionCode;
    }

    /**
     * Sets version code.
     *
     * @param versionCode the version code
     */
    public void setVersionCode(Long versionCode) {
        this.versionCode = versionCode;
    }

    /**
     * Gets install time.
     *
     * @return the install time
     */
    public Date getInstallTime() {
        return installTime;
    }

    /**
     * Sets install time.
     *
     * @param installTime the install time
     */
    public void setInstallTime(Date installTime) {
        this.installTime = installTime;
    }

    @Override
    public String toString() {
        return "TerminalInstalledApkDTO{" +
                "appName='" + appName + '\'' +
                ", packageName='" + packageName + '\'' +
                ", versionName='" + versionName + '\'' +
                ", versionCode=" + versionCode +
                ", installTime=" + installTime +
                '}';
    }
}
