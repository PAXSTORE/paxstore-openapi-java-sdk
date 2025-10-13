package com.pax.market.api.sdk.java.api.emm.emmPolicy.dto;

import java.io.Serializable;

public class LockedPolicyDTO implements Serializable {

    private static final long serialVersionUID = -1779263548478334876L;

    private String key;

    private Boolean lockFlag;

    private String lockedByReseller;

    private String lockedByMerchant;

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

    public String getLockedByMerchant() {
        return lockedByMerchant;
    }

    public void setLockedByMerchant(String lockedByMerchant) {
        this.lockedByMerchant = lockedByMerchant;
    }

    @Override
    public String toString() {
        return "LockedPolicyDTO{" +
                "key='" + key + '\'' +
                ", lockFlag=" + lockFlag +
                ", lockedByReseller='" + lockedByReseller + '\'' +
                ", lockedByMerchant='" + lockedByMerchant + '\'' +
                '}';
    }
}
