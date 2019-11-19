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
package com.pax.market.api.sdk.java.api.entityAttribute.dto;

import java.io.Serializable;
import java.util.List;

/**
 * @Description
 * @Author: Shawn
 * @Date: 2019/11/19 10:10
 * @Version 7.1
 */
public class EntityAttributeLabelUpdateRequest implements Serializable {

    private static final long serialVersionUID = 5680619007929979788L;

    private List<EntityAttributeLabelInfo> entityAttributeLabelList;

    public List<EntityAttributeLabelInfo> getEntityAttributeLabelList() {
        return entityAttributeLabelList;
    }

    public void setEntityAttributeLabelList(List<EntityAttributeLabelInfo> entityAttributeLabelList) {
        this.entityAttributeLabelList = entityAttributeLabelList;
    }
}
