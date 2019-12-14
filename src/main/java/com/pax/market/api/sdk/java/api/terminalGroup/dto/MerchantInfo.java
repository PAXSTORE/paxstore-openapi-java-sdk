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
package com.pax.market.api.sdk.java.api.terminalGroup.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @Description
 * @Author: Shawn
 * @Date: 2019/11/26 9:50
 * @Version 1.0
 */
@Getter
@Setter
public class MerchantInfo implements Serializable {

    private static final long serialVersionUID = -3601003893594426917L;
    private Long id;
    private String identifier;        // identifier
    private String name;        // name
    private ResellerInfo reseller;        // reseller_id 父类
    private String country;        // country
    private String province;        // province
    private String city;        // city
    private String postcode;        // postal_code
    private String address;        // address
    private String contact;        // contact
    private String email;        // email
    private String userStatus;
    private String phone;        // phone
    private String status;         // status
    private String description;        // description
    private Long createdDate;
    private Long updatedDate;
    private Boolean createUserFlag; //是否在更新商户的时候创建用户
    private List<MerchantCategoryInfo> merchantCategoryList;        // 分类
    private Integer terminalCount;

    @Override
    public String toString() {
        return "MerchantInfo{" +
                "id=" + id +
                ", identifier='" + identifier + '\'' +
                ", name='" + name + '\'' +
                ", reseller=" + reseller +
                ", country='" + country + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", postcode='" + postcode + '\'' +
                ", address='" + address + '\'' +
                ", contact='" + contact + '\'' +
                ", email='" + email + '\'' +
                ", userStatus='" + userStatus + '\'' +
                ", phone='" + phone + '\'' +
                ", status='" + status + '\'' +
                ", description='" + description + '\'' +
                ", createdDate=" + createdDate +
                ", updatedDate=" + updatedDate +
                ", createUserFlag=" + createUserFlag +
                ", merchantCategoryList=" + merchantCategoryList +
                ", terminalCount=" + terminalCount +
                '}';
    }
}
