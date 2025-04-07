package com.pax.market.api.sdk.java.api.emm.emmPolicy.dto;

import java.io.Serializable;
import java.util.List;

public class MerchantEmmPolicyCreateRequest implements Serializable {

    private static final long serialVersionUID = -8898637992540739976L;

    private String resellerName;

    private String merchantName;

    private PolicyUpdatedContentDTO contentInfo;

    private List<LockedPolicyUpdateDTO> lockedPolicyList;

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

    public List<LockedPolicyUpdateDTO> getLockedPolicyList() {
        return lockedPolicyList;
    }

    public void setLockedPolicyList(List<LockedPolicyUpdateDTO> lockedPolicyList) {
        this.lockedPolicyList = lockedPolicyList;
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
                ", lockedPolicyList=" + lockedPolicyList +
                ", inheritFlag=" + inheritFlag +
                '}';
    }
}
