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

import java.io.Serializable;
import java.util.List;

/**
 * @author shifan
 * @date 2022/10/19
 */
public class MerchantVariableDeleteRequest implements Serializable {
    private static final long serialVersionUID = -6416122344595993811L;
    private List<Long> variableIds;

    public List<Long> getVariableIds() {
        return variableIds;
    }

    public void setVariableIds(List<Long> variableIds) {
        this.variableIds = variableIds;
    }
}