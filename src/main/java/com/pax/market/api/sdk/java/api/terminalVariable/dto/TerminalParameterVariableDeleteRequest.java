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
package com.pax.market.api.sdk.java.api.terminalVariable.dto;

import java.io.Serializable;
import java.util.List;

/**
 * @Description
 * @Author: Shawn
 * @Date: 2019/11/11 10:42
 * @Version 1.0
 */

public class TerminalParameterVariableDeleteRequest implements Serializable{
    private static final long serialVersionUID = 8725073457016401570L;

    private List<Long> variableIds;

    public List<Long> getVariableIds() {
        return variableIds;
    }

    public void setVariableIds(List<Long> variableIds) {
        this.variableIds = variableIds;
    }
}
