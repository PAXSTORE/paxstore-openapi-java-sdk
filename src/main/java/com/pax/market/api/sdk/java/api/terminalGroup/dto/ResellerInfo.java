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
import java.util.List;

/**
 * @Description
 * @Author: Shawn
 * @Date: 2019/11/26 9:48
 * @Version 1.0
 */
@Getter
@Setter
public class ResellerInfo implements Serializable {

    private static final long serialVersionUID = -7154601057819226024L;

    private Long id;
    private Long marketId;
    private Long parentId;
    private String parentIds;
    private String name;        // name
    private String phone;        // phone
    private String country;        // country
    private String province;        // province
    private String city;        // city
    private String postcode;        // postcode
    private String address;        // address
    private String company;        // company
    private String contact;        // contact
    private String email;
    private String userStatus;
    private String status;        // status
    private Long createdDate;
    private Long updatedDate;
    private ResellerInfo parent;
    private List<ResellerInfo> children;
    private List<MerchantInfo> merchantList;
    private Integer terminalCount;
    private ResellerRkiInfo resellerRki;

    @Override
    public String toString() {
        return "ResellerInfo{" +
                "id=" + id +
                ", marketId=" + marketId +
                ", parentId=" + parentId +
                ", parentIds='" + parentIds + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", country='" + country + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", postcode='" + postcode + '\'' +
                ", address='" + address + '\'' +
                ", company='" + company + '\'' +
                ", contact='" + contact + '\'' +
                ", email='" + email + '\'' +
                ", userStatus='" + userStatus + '\'' +
                ", status='" + status + '\'' +
                ", createdDate=" + createdDate +
                ", updatedDate=" + updatedDate +
                ", parent=" + parent +
                ", children=" + children +
                ", merchantList=" + merchantList +
                ", terminalCount=" + terminalCount +
                ", resellerRki=" + resellerRki +
                '}';
    }
}
