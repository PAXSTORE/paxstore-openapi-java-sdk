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

import java.io.Serializable;
import java.util.Set;

/**
 * @Description Terminal grouping request
 * @Author: Shawn
 * @Date: 2019/11/25 13:59
 * @Version 7.1
 */

public class TerminalGroupRequest implements Serializable {
    private static final long serialVersionUID = -6479044740436049647L;

    private Set<Long> terminalIds;

    private Set<Long> groupIds;

    public Set<Long> getTerminalIds() {
        return terminalIds;
    }

    public void setTerminalIds(Set<Long> terminalIds) {
        this.terminalIds = terminalIds;
    }

    public Set<Long> getGroupIds() {
        return groupIds;
    }

    public void setGroupIds(Set<Long> groupIds) {
        this.groupIds = groupIds;
    }
}
