package com.pax.market.api.sdk.java.api.emm.emmPolicy.dto;

import java.io.Serializable;

public class MerchantEmmPolicyCreateRequest implements Serializable {

    private static final long serialVersionUID = -4046013063756454516L;

    private String resellerName;

    private String merchantName;

    private PolicyUpdatedContentDTO contentInfo;

    private Boolean inheritFlag;

    public String getResellerName() {
        return resellerName;
    }

    public void setResellerName(String resellerName) {
        this.resellerName = resellerName;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
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
        return "MerchantEmmPolicyCreateRequest{" +
                "resellerName='" + resellerName + '\'' +
                ", merchantName='" + merchantName + '\'' +
                ", contentInfo=" + contentInfo +
                ", inheritFlag=" + inheritFlag +
                '}';
    }
}
