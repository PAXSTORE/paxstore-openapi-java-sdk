package com.pax.market.api.sdk.java.api.emm.emmDevice.dto;

import java.io.Serializable;
import java.util.List;

public class EmmDeviceBatchDeleteRequest implements Serializable {


    private static final long serialVersionUID = -4119297225101851438L;

    private List<Long> deviceIds;

    public List<Long> getDeviceIds() {
        return deviceIds;
    }

    public void setDeviceIds(List<Long> deviceIds) {
        this.deviceIds = deviceIds;
    }

    @Override
    public String toString() {
        return "EmmDeviceBatchDeleteRequest{" +
                "deviceIds=" + deviceIds +
                '}';
    }
}
