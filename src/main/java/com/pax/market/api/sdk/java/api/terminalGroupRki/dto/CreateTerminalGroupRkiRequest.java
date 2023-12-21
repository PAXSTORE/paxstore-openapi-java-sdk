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


import org.apache.commons.lang3.time.DateFormatUtils;

import java.io.Serializable;
import java.util.Date;

/**
 * Create group push RKI request
 *
 * @author shifan
 * {@code @date} 2021 /9/26
 */
public class CreateTerminalGroupRkiRequest implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long groupId;
    private String rkiKey;
    private String effectiveTime;
    private String expiredTime;

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

    /**
     * Gets effective time.
     *
     * @return the effective time
     */
    public String getEffectiveTime() {
        return effectiveTime;
    }

    /**
     * Sets effective time.
     *
     * @param effectiveTime the effective time
     */
    public void setEffectiveTime(Date effectiveTime) {
        if(effectiveTime != null) {
            this.effectiveTime = DateFormatUtils.ISO_8601_EXTENDED_DATETIME_TIME_ZONE_FORMAT.format(effectiveTime);
        }
    }

    /**
     * Gets expired time.
     *
     * @return the expired time
     */
    public String getExpiredTime() {
        return expiredTime;
    }

    /**
     * Sets expired time.
     *
     * @param expiredTime the expired time
     */
    public void setExpiredTime(Date expiredTime) {
        if(expiredTime != null) {
            this.expiredTime = DateFormatUtils.ISO_8601_EXTENDED_DATETIME_TIME_ZONE_FORMAT.format(expiredTime);
        }

    }
}
