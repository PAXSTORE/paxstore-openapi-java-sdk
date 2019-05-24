/*
 *
 *  * ===========================================================================================
 *  * = COPYRIGHT
 *  *          PAX Computer Technology(Shenzhen) CO., LTD PROPRIETARY INFORMATION
 *  *   This software is supplied under the terms of a license agreement or nondisclosure
 *  *   agreement with PAX Computer Technology(Shenzhen) CO., LTD and may not be copied or
 *  *   disclosed except in accordance with the terms in that agreement.
 *  *     Copyright (C) 2019. PAX Computer Technology(Shenzhen) CO., LTD All rights reserved.
 *  * ===========================================================================================
 *
 */

package com.pax.market.api.sdk.java.api.app.dto;

import java.io.Serializable;

/**
 * @author liukai
 * @date 2019/5/23
 */
public class ApkDTO implements Serializable {

    private static final long serialVersionUID = 758239688663238628L;

    private String status;        // 状态
    private Long versionCode;
    private String versionName;
    private String apkType;       // 应用类型（参数、非参数)
    private String apkFileType;   // A,P,B
    private ApkFileDTO apkFile;
    private String osType;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(Long versionCode) {
        this.versionCode = versionCode;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public String getApkType() {
        return apkType;
    }

    public void setApkType(String apkType) {
        this.apkType = apkType;
    }

    public String getApkFileType() {
        return apkFileType;
    }

    public void setApkFileType(String apkFileType) {
        this.apkFileType = apkFileType;
    }

    public ApkFileDTO getApkFile() {
        return apkFile;
    }

    public void setApkFile(ApkFileDTO apkFile) {
        this.apkFile = apkFile;
    }

    public String getOsType() {
        return osType;
    }

    public void setOsType(String osType) {
        this.osType = osType;
    }

    @Override
    public String toString() {
        return "ApkDTO{" +
                "status='" + status + '\'' +
                ", versionCode=" + versionCode +
                ", versionName='" + versionName + '\'' +
                ", apkType='" + apkType + '\'' +
                ", apkFileType='" + apkFileType + '\'' +
                ", apkFile=" + apkFile +
                ", osType='" + osType + '\'' +
                '}';
    }
}
