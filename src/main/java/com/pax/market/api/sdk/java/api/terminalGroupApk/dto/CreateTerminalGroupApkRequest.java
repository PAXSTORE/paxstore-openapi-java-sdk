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

    /**
     * Gets group id.
     *
     * @return the group id
     */
    public Long getGroupId() {
        return groupId;
    }

    /**
     * Sets group id.
     *
     * @param groupId the group id
     */
    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    /**
     * Gets push template name.
     *
     * @return the push template name
     */
    public String getPushTemplateName() {
        return pushTemplateName;
    }

    /**
     * Sets push template name.
     *
     * @param pushTemplateName the push template name
     */
    public void setPushTemplateName(String pushTemplateName) {
        this.pushTemplateName = pushTemplateName;
    }

    /**
     * Gets package name.
     *
     * @return the package name
     */
    public String getPackageName() {
        return packageName;
    }

    /**
     * Sets package name.
     *
     * @param packageName the package name
     */
    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    /**
     * Gets version.
     *
     * @return the version
     */
    public String getVersion() {
        return version;
    }

    /**
     * Sets version.
     *
     * @param version the version
     */
    public void setVersion(String version) {
        this.version = version;
    }

    /**
     * Gets template name.
     *
     * @return the template name
     */
    public String getTemplateName() {
        return templateName;
    }

    /**
     * Sets template name.
     *
     * @param templateName the template name
     */
    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    /**
     * Gets parameters.
     *
     * @return the parameters
     */
    public Map<String, String> getParameters() {
        return parameters;
    }

    /**
     * Sets parameters.
     *
     * @param parameters the parameters
     */
    public void setParameters(Map<String, String> parameters) {
        this.parameters = parameters;
    }

    /**
     * Gets base 64 file parameters.
     *
     * @return the base 64 file parameters
     */
    public List<FileParameter> getBase64FileParameters() {
        return base64FileParameters;
    }

    /**
     * Sets base 64 file parameters.
     *
     * @param base64FileParameters the base 64 file parameters
     */
    public void setBase64FileParameters(List<FileParameter> base64FileParameters) {
        this.base64FileParameters = base64FileParameters;
    }

    /**
     * Gets inherit push history.
     *
     * @return the inherit push history
     */
    public Boolean getInheritPushHistory() {
        return inheritPushHistory;
    }

    /**
     * Sets inherit push history.
     *
     * @param inheritPushHistory the inherit push history
     */
    public void setInheritPushHistory(Boolean inheritPushHistory) {
        this.inheritPushHistory = inheritPushHistory;
    }

    /**
     * Is force update boolean.
     *
     * @return the boolean
     */
    public boolean isForceUpdate() {
        return forceUpdate;
    }

    /**
     * Sets force update.
     *
     * @param forceUpdate the force update
     */
    public void setForceUpdate(boolean forceUpdate) {
        this.forceUpdate = forceUpdate;
    }

    /**
     * Is wifi only boolean.
     *
     * @return the boolean
     */
    public boolean isWifiOnly() {
        return wifiOnly;
    }

    /**
     * Sets wifi only.
     *
     * @param wifiOnly the wifi only
     */
    public void setWifiOnly(boolean wifiOnly) {
        this.wifiOnly = wifiOnly;
    }

    /**
     * Gets effective time.
     *
     * @return the effective time
     */
    public Date getEffectiveTime() {
        return effectiveTime;
    }

    /**
     * Sets effective time.
     *
     * @param effectiveTime the effective time
     */
    public void setEffectiveTime(Date effectiveTime) {
        this.effectiveTime = effectiveTime;
    }

    /**
     * Gets expired time.
     *
     * @return the expired time
     */
    public Date getExpiredTime() {
        return expiredTime;
    }

    /**
     * Sets expired time.
     *
     * @param expiredTime the expired time
     */
    public void setExpiredTime(Date expiredTime) {
        this.expiredTime = expiredTime;
    }
}
