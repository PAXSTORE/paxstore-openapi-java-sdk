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
 * @Date: 2019/11/26 9:55
 * @Version 1.0
 */
@Getter
@Setter
public class ModelInfo implements Serializable {
    private static final long serialVersionUID = -7595283665234038752L;
    private Long id;
    private FactoryInfo factory;        // factory_id 父类
    private String name;                // name
    private String factoryModelName;    // factory model name
    private String platform;            // platform
    private String androidType;
    private String status;         // status
    private String description;         // description

    @Override
    public String toString() {
        return "ModelInfo{" +
                "id=" + id +
                ", factory=" + factory +
                ", name='" + name + '\'' +
                ", factoryModelName='" + factoryModelName + '\'' +
                ", platform='" + platform + '\'' +
                ", androidType='" + androidType + '\'' +
                ", status='" + status + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
