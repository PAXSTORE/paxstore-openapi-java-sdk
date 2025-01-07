package com.pax.market.api.sdk.java.api.emm.emmPolicy.dto;

import java.io.Serializable;

public class EmmPolicyDTO implements Serializable {

    private static final long serialVersionUID = -7223565286879068635L;

    private Integer customPolicyCount;

    private String name;

    private PolicyContentDTO contentInfo;

    private Boolean inheritFlag;

    public Integer getCustomPolicyCount() {
        return customPolicyCount;
    }

    public void setCustomPolicyCount(Integer customPolicyCount) {
        this.customPolicyCount = customPolicyCount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PolicyContentDTO getContentInfo() {
        return contentInfo;
    }

    public void setContentInfo(PolicyContentDTO contentInfo) {
        this.contentInfo = contentInfo;
    }

    public Boolean getInheritFlag() {
        return inheritFlag;
    }

    public void setInheritFlag(Boolean inheritFlag) {
        this.inheritFlag = inheritFlag;
    }

    @Override
    public String toString() {
        return "EmmPolicyDTO{" +
                "customPolicyCount=" + customPolicyCount +
                ", name='" + name + '\'' +
                ", contentInfo=" + contentInfo +
                ", inheritFlag=" + inheritFlag +
                '}';
    }
}
