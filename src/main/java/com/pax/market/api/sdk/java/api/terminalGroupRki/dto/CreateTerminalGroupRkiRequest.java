/*
 * *******************************************************************************
 * COPYRIGHT
 *               PAX TECHNOLOGY, Inc. PROPRIETARY INFORMATION
 *   This software is supplied under the terms of a license agreement or
 *   nondisclosure agreement with PAX  Technology, Inc. and may not be copied
 *   or disclosed except in accordance with the terms in that agreement.
 *
 *      Copyright (C) 2017 PAX Technology, Inc. All rights reserved.
 * *******************************************************************************
 */

package com.pax.market.api.sdk.java.api.terminalGroupRki.dto;

import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;

/**
 * Create group push RKI request
 * @author shifan
 * @date 2021/9/26
 */
public class CreateTerminalGroupRkiRequest implements Serializable {
    private static final long serialVersionUID = -1854602426261944169L;

    private Long groupId;
    @NotBlank
    private String rkiKey;

    /**
     * Gets groupId.
     *
     * @return the groupId
     */
    public Long getGroupId() {
        return groupId;
    }

    /**
     * Sets the groupId.
     *
     * @param groupId the groupId
     */
    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    /**
     * Gets rkiKey.
     *
     * @return the rkiKey
     */
    public String getRkiKey() {
        return rkiKey;
    }

    /**
     * Sets the rkiKey.
     *
     * @param rkiKey the rkiKey
     */
    public void setRkiKey(String rkiKey) {
        this.rkiKey = rkiKey;
    }
}
