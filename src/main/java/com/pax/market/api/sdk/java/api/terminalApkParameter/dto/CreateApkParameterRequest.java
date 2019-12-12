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



import com.pax.market.api.sdk.java.api.terminalApk.dto.FileParameter;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @Description
 * @Author: Shawn
 * @Date: 2019/11/1 10:50
 * @Version 1.0
 */

public class CreateApkParameterRequest implements Serializable {
    private static final long serialVersionUID = 7885347596280390769L;


    private String packageName;

    private String version;

    private String name;

    private String paramTemplateName;

    private Map<String, String> parameters;

    private List<FileParameter> base64FileParameters;



    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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

    public List<FileParameter> getBase64FileParameters() {
        return base64FileParameters;
    }

    public void setBase64FileParameters(List<FileParameter> base64FileParameters) {
        this.base64FileParameters = base64FileParameters;
    }
}
