package com.pax.market.api.sdk.java.api.emm.emmDevice.dto;

import java.io.Serializable;

public class EmmDeviceResetPasswordRequest implements Serializable {

    private static final long serialVersionUID = 2576059029146958658L;

    private String password;

    private Boolean lockNow = true;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getLockNow() {
        return lockNow;
    }

    public void setLockNow(Boolean lockNow) {
        this.lockNow = lockNow;
    }

    @Override
    public String toString() {
        return "EmmDeviceResetPasswordRequest{" +
                "password='" + password + '\'' +
                ", lockNow=" + lockNow +
                '}';
    }
}
