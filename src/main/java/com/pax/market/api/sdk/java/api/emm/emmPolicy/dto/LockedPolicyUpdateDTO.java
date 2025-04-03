package com.pax.market.api.sdk.java.api.emm.emmPolicy.dto;

import java.io.Serializable;

public class LockedPolicyUpdateDTO implements Serializable {

    private static final long serialVersionUID = 6576968919720455021L;

    private String key;

    private Boolean lockFlag;

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

    @Override
    public String toString() {
        return "LockedPolicyUpdateDTO{" +
                "key='" + key + '\'' +
                ", lockFlag=" + lockFlag +
                '}';
    }
}
