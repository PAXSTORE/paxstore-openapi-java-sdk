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


import com.pax.market.api.sdk.java.api.variable.dto.ParameterVariable;

import java.io.Serializable;
import java.util.List;

/**
 * @Description
 * @Author: Shawn
 * @Date: 2019/11/11 10:40
 * @Version 1.0
 */

public class TerminalParameterVariableRequest implements Serializable{
    private static final long serialVersionUID = 4501382736321506678L;
    private String tid;
    private String serialNo;
    private List<ParameterVariable> variableList;

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    public List<ParameterVariable> getVariableList() {
        return variableList;
    }

    public void setVariableList(List<ParameterVariable> variableList) {
        this.variableList = variableList;
    }
}
