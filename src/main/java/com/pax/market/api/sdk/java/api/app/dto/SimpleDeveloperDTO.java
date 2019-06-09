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
public class SimpleDeveloperDTO implements Serializable {

    private static final long serialVersionUID = 1634477032409700318L;
    private String realName;
    private String nickname;
    private String phone;
    private String email;
    private String companyName;

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    @Override
    public String toString() {
        return "SimpleDeveloperDTO{" +
                "realName='" + realName + '\'' +
                ", nickname='" + nickname + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", companyName='" + companyName + '\'' +
                '}';
    }
}
