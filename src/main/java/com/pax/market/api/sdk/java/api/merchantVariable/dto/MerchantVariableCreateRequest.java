/*
 * *******************************************************************************
 * COPYRIGHT
 *               PAX TECHNOLOGY, Inc. PROPRIETARY INFORMATION
 *   This software is supplied under the terms of a license agreement or
 *   nondisclosure agreement with PAX  Technology, Inc. and may not be copied
 *   or disclosed except in accordance with the terms in that agreement.
 *
 *      Copyright (C) 2022 PAX Technology, Inc. All rights reserved.
 * *******************************************************************************
 */

package com.pax.market.api.sdk.java.api.merchantVariable.dto;

import com.pax.market.api.sdk.java.api.variable.dto.ParameterVariable;

import java.io.Serializable;
import java.util.List;

/**
 * @author shifan
 * @date 2022/10/19
 */

public class MerchantVariableCreateRequest implements Serializable {
    private static final long serialVersionUID = 6692976867891052265L;

    private Long merchantId;
    private List<ParameterVariable> variableList;

    public Long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    public List<ParameterVariable> getVariableList() {
        return variableList;
    }

    public void setVariableList(List<ParameterVariable> variableList) {
        this.variableList = variableList;
    }
}