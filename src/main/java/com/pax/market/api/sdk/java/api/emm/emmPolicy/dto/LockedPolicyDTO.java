package com.pax.market.api.sdk.java.api.emm.emmPolicy.dto;

import java.io.Serializable;

public class LockedPolicyDTO implements Serializable {

    private static final long serialVersionUID = -8737023663297593529L;

    private String key;

    private Boolean lockFlag;

    private String lockedByReseller;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Boolean getLockFlag() {
        return lockFlag;
    }

    public void setLockFlag(Boolean lockFlag) {
        this.lockFlag = lockFlag;
    }

    public String getLockedByReseller() {
        return lockedByReseller;
    }

    public void setLockedByReseller(String lockedByReseller) {
        this.lockedByReseller = lockedByReseller;
    }

    @Override
    public String toString() {
        return "LockedPolicyDTO{" +
                "key='" + key + '\'' +
                ", lockFlag=" + lockFlag +
                ", lockedByReseller='" + lockedByReseller + '\'' +
                '}';
    }
}
