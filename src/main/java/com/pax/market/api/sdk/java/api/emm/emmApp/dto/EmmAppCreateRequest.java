package com.pax.market.api.sdk.java.api.emm.emmApp.dto;

import java.io.Serializable;

public class EmmAppCreateRequest implements Serializable {

    private static final long serialVersionUID = 8505656274464601169L;

    private String resellerName;

    private String packageName;

    public String getResellerName() {
        return resellerName;
    }

    public void setResellerName(String resellerName) {
        this.resellerName = resellerName;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    @Override
    public String toString() {
        return "EmmAppCreateRequest{" +
                "resellerName='" + resellerName + '\'' +
                ", packageName='" + packageName + '\'' +
                '}';
    }
}
