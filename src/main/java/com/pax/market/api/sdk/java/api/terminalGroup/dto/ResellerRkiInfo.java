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

/**
 * @Description
 * @Author: Shawn
 * @Date: 2019/11/26 9:54
 * @Version 1.0
 */
@Getter
@Setter
public class ResellerRkiInfo implements Serializable {

    private static final long serialVersionUID = 770682520693193685L;
    private Long resellerId;
    private String token;

    private Boolean allowChildUse;

    @Override
    public String toString() {
        return "ResellerRkiInfo{" +
                "resellerId=" + resellerId +
                ", token='" + token + '\'' +
                ", allowChildUse=" + allowChildUse +
                '}';
    }
}
