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

import com.pax.market.api.sdk.java.api.terminalApk.dto.FileParameter;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Create Terminal Group Apk Request
 *
 * @author shifan
 * {@code @Date:} 2019/11/29 16:09
 */
public class CreateTerminalGroupApkRequest implements Serializable {

    private static final long serialVersionUID = -5645272376227693380L;

    @NotNull
    private Long groupId;

    private String pushTemplateName;

    @NotBlank
    private String packageName;

    private String version;

    private String templateName;

    private Map<String, String> parameters;
    private List<FileParameter> base64FileParameters;
    private Boolean inheritPushHistory;
    private boolean forceUpdate;
    private boolean wifiOnly;
    private Date effectiveTime;
    private Date expiredTime;

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public String getPushTemplateName() {
        return pushTemplateName;
    }

    public void setPushTemplateName(String pushTemplateName) {
        this.pushTemplateName = pushTemplateName;
    }

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

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
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

    public Boolean getInheritPushHistory() {
        return inheritPushHistory;
    }

    public void setInheritPushHistory(Boolean inheritPushHistory) {
        this.inheritPushHistory = inheritPushHistory;
    }


}
