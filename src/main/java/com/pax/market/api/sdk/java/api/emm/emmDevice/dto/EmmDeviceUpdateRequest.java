package com.pax.market.api.sdk.java.api.emm.emmDevice.dto;

import java.io.Serializable;

public class EmmDeviceUpdateRequest implements Serializable {

    private static final long serialVersionUID = -601221214257004654L;

    private String deviceName;

    private String resellerName;

    private String merchantName;

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
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
        return "EmmDeviceUpdateRequest{" +
                "deviceName='" + deviceName + '\'' +
                ", resellerName='" + resellerName + '\'' +
                ", merchantName='" + merchantName + '\'' +
                '}';
    }

}
