package com.pax.market.api.sdk.java.api.emm.emmDevice.dto;

import java.io.Serializable;

public class EmmDeviceLostModeRequest implements Serializable {

    private static final long serialVersionUID = -5693849006613978024L;

    private String lostMessage;

    private String lostPhoneNumber;

    public String getLostMessage() {
        return lostMessage;
    }

    public void setLostMessage(String lostMessage) {
        this.lostMessage = lostMessage;
    }

    public String getLostPhoneNumber() {
        return lostPhoneNumber;
    }

    public void setLostPhoneNumber(String lostPhoneNumber) {
        this.lostPhoneNumber = lostPhoneNumber;
    }

    @Override
    public String toString() {
        return "EmmDeviceLostModeRequest{" +
                "lostMessage='" + lostMessage + '\'' +
                ", lostPhoneNumber='" + lostPhoneNumber + '\'' +
                '}';
    }
}
