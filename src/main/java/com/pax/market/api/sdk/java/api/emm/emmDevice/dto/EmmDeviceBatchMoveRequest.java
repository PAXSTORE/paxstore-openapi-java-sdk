package com.pax.market.api.sdk.java.api.emm.emmDevice.dto;

import java.io.Serializable;
import java.util.List;

public class EmmDeviceBatchMoveRequest implements Serializable {

    private static final long serialVersionUID = -3478719924869785455L;

    private List<Long> deviceIds;

    private String resellerName;

    private String merchantName;

    public List<Long> getDeviceIds() {
        return deviceIds;
    }

    public void setDeviceIds(List<Long> deviceIds) {
        this.deviceIds = deviceIds;
    }

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

    @Override
    public String toString() {
        return "EmmDeviceBatchMoveRequest{" +
                "deviceIds=" + deviceIds +
                ", resellerName='" + resellerName + '\'' +
                ", merchantName='" + merchantName + '\'' +
                '}';
    }
}
