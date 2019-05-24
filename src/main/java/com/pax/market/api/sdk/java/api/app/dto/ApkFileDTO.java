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
public class ApkFileDTO implements Serializable {


    private static final long serialVersionUID = 8317264284300627278L;
    private String permissions;
    private String paxPermission;

    public String getPermissions() {
        return permissions;
    }

    public void setPermissions(String permissions) {
        this.permissions = permissions;
    }

    public String getPaxPermission() {
        return paxPermission;
    }

    public void setPaxPermission(String paxPermission) {
        this.paxPermission = paxPermission;
    }

    @Override
    public String toString() {
        return "ApkFileDTO{" +
                "permissions='" + permissions + '\'' +
                ", paxPermission='" + paxPermission + '\'' +
                '}';
    }
}
