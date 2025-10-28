package com.pax.market.api.sdk.java.api.emm.emmPolicy.dto;

import java.io.Serializable;
import java.util.List;

public class DeviceEmmPolicyCreateRequest implements Serializable {

    private static final long serialVersionUID = -3057462843191254292L;

    private String serialNo;

    private PolicyUpdatedContentDTO contentInfo;

    private List<LockedPolicyUpdateDTO> lockedPolicyList;

    private Boolean inheritFlag;

    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
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
        return "DeviceEmmPolicyCreateRequest{" +
                "serialNo='" + serialNo + '\'' +
                ", contentInfo=" + contentInfo +
                ", lockedPolicyList=" + lockedPolicyList +
                ", inheritFlag=" + inheritFlag +
                '}';
    }
}
