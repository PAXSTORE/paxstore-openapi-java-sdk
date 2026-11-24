/*
 * *******************************************************************************
 * COPYRIGHT
 *               PAX TECHNOLOGY, Inc. PROPRIETARY INFORMATION
 *   This software is supplied under the terms of a license agreement or
 *   nondisclosure agreement with PAX  Technology, Inc. and may not be copied
 *   or disclosed except in accordance with the terms in that agreement.
 *
 *      Copyright (C) 2026 PAX Technology, Inc. All rights reserved.
 * *******************************************************************************
 */
package com.pax.market.api.sdk.java.api.merchant.dto;

import java.io.Serializable;

/**
 * Request body for moving a merchant to another reseller.
 */
public class MerchantMoveRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Name of the target reseller that the merchant will be moved to. Max length: 64 characters.
     */
    private String targetResellerName;

    /**
     * Gets target reseller name.
     *
     * @return the target reseller name
     */
    public String getTargetResellerName() {
        return targetResellerName;
    }

    /**
     * Sets target reseller name.
     *
     * @param targetResellerName the target reseller name
     */
    public void setTargetResellerName(String targetResellerName) {
        this.targetResellerName = targetResellerName;
    }

    @Override
    public String toString() {
        return "MerchantMoveRequest{" +
                "targetResellerName='" + targetResellerName + '\'' +
                '}';
    }
}
