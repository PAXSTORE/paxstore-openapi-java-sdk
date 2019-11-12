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
package com.pax.market.api.sdk.java.api.terminalApkParameter.dto;


import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Description
 * @Author: Shawn
 * @Date: 2019/11/1 10:50
 * @Version 1.0
 */


public class UpdateApkParameterRequest  implements Serializable {
    private static final long serialVersionUID = -7028462934117194632L;



    private String paramTemplateName;

    private Map<String, String> parameters;



    public String getParamTemplateName() {
        return paramTemplateName;
    }

    public void setParamTemplateName(String paramTemplateName) {
        this.paramTemplateName = paramTemplateName;
    }

    public Map<String, String> getParameters() {
        return parameters;
    }

    public void setParameters(Map<String, String> parameters) {
        this.parameters = parameters;
    }
}
