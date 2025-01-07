package com.pax.market.api.sdk.java.api.emm.emmApp.dto;

import java.io.Serializable;

public class EmmAppSubscriptionUpdateRequest implements Serializable {

    private static final long serialVersionUID = 7399285667701998809L;

    private String resellerName;

    public String getResellerName() {
        return resellerName;
    }

    public void setResellerName(String resellerName) {
        this.resellerName = resellerName;
    }

    @Override
    public String toString() {
        return "EmmAppSubscriptionUpdateRequest{" +
                "resellerName='" + resellerName + '\'' +
                '}';
    }
}
