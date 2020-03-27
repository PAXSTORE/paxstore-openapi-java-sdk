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
package com.pax.market.api.sdk.java.api.terminalGroupApk.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Map;

@Getter
@Setter
public class TerminalGroupApkParamDTO implements Serializable {

    private static final long serialVersionUID = -2819937434488271303L;
    private String paramTemplateName;
    private Map<String, String> configuredParameters;
    private int pendingCount;
    private int successCount;
    private int failedCount;

    @Override
    public String toString() {
        return "TerminalGroupApkParamDTO{" +
                "paramTemplateName='" + paramTemplateName + '\'' +
                ", configuredParameters=" + configuredParameters +
                ", pendingCount=" + pendingCount +
                ", successCount=" + successCount +
                ", failedCount=" + failedCount +
                '}';
    }
}
