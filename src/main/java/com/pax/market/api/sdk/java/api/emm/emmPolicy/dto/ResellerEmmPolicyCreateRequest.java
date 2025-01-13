package com.pax.market.api.sdk.java.api.emm.emmPolicy.dto;

import java.io.Serializable;

public class ResellerEmmPolicyCreateRequest implements Serializable {

    private static final long serialVersionUID = 3891346535774093566L;

    private String resellerName;

    private PolicyUpdatedContentDTO contentInfo;

    private Boolean inheritFlag;

    public String getResellerName() {
        return resellerName;
    }

    public void setResellerName(String resellerName) {
        this.resellerName = resellerName;
    }

    public PolicyUpdatedContentDTO getContentInfo() {
        return contentInfo;
    }

    public void setContentInfo(PolicyUpdatedContentDTO contentInfo) {
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
        return "ResellerEmmPolicyCreateRequest{" +
                "resellerName='" + resellerName + '\'' +
                ", contentInfo=" + contentInfo +
                ", inheritFlag=" + inheritFlag +
                '}';
    }
}
