package com.pax.market.api.sdk.java.api.emm.emmPolicy.dto;

import java.io.Serializable;
import java.util.List;

public class ResellerEmmPolicyCreateRequest implements Serializable {

    private static final long serialVersionUID = 9075668676654078617L;

    private String resellerName;

    private PolicyUpdatedContentDTO contentInfo;

    private List<LockedPolicyUpdateDTO> lockedPolicyList;

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
        return "ResellerEmmPolicyCreateRequest{" +
                "resellerName='" + resellerName + '\'' +
                ", contentInfo=" + contentInfo +
                ", lockedPolicyList=" + lockedPolicyList +
                ", inheritFlag=" + inheritFlag +
                '}';
    }
}
