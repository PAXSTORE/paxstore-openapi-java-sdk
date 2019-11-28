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
 * @Date: 2019/11/26 9:56
 * @Version 1.0
 */
@Getter
@Setter
public class FactoryInfo implements Serializable {
    private static final long serialVersionUID = -4157453767982307189L;

    private Long id;
    private Long marketId = -1L;    // market_id
    private String name;            // name
    private String contact;         // contact
    private String phone;           // phone number
    private String email;           // email
    private String country;         // country
    private String province;        // province
    private String city;            // city
    private String postcode;        // postcode
    private String address;         // address
    private String status;        // status
    private String description;     // description
    private List<ModelInfo> modelList;
    private Long createdDate;
    private Long updatedDate;

    @Override
    public String toString() {
        return "FactoryInfo{" +
                "id=" + id +
                ", marketId=" + marketId +
                ", name='" + name + '\'' +
                ", contact='" + contact + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", country='" + country + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", postcode='" + postcode + '\'' +
                ", address='" + address + '\'' +
                ", status='" + status + '\'' +
                ", description='" + description + '\'' +
                ", modelList=" + modelList +
                ", createdDate=" + createdDate +
                ", updatedDate=" + updatedDate +
                '}';
    }
}
