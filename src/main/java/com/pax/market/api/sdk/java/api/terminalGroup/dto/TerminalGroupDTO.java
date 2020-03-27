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
 * @Date: 2019/11/26 9:37
 * @Version 7.1
 */

@Getter
@Setter
public class TerminalGroupDTO implements Serializable {


    private static final long serialVersionUID = -2375133176041768065L;
    private Long id;
    private String resellerName;
    private String modelName;
    private String name;
    private String status;
    private String description;
    private Long createdByResellerId;
    private Long createdDate;
    private Long updatedDate;
    private int terminalCount;
    private Boolean dynamic;
    private Boolean containSubResellerTerminal;
    private List<String> merchantNames;


    @Override
    public String toString() {
        return "TerminalGroupDTO{" +
                "id=" + id +
                ", resellerName='" + resellerName + '\'' +
                ", modelName='" + modelName + '\'' +
                ", name='" + name + '\'' +
                ", status='" + status + '\'' +
                ", description='" + description + '\'' +
                ", createdByResellerId=" + createdByResellerId +
                ", createdDate=" + createdDate +
                ", updatedDate=" + updatedDate +
                ", terminalCount=" + terminalCount +
                ", dynamic=" + dynamic +
                ", containSubResellerTerminal=" + containSubResellerTerminal +
                ", merchantNames=" + merchantNames +
                '}';
    }
}
