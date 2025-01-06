package com.pax.market.api.sdk.java.api.emm.emmDevice.dto;

import java.io.Serializable;
import java.util.Date;

public class EmmDeviceRegisterQRCodeCreateDTO implements Serializable {

    private static final long serialVersionUID = 2146309970051531832L;

    private Long id;

    private String marketName;

    private String resellerName;

    private String merchantName;

    private String deviceType;

    private String registerQRCode;

    private Date expireDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMarketName() {
        return marketName;
    }

    public void setMarketName(String marketName) {
        this.marketName = marketName;
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

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getRegisterQRCode() {
        return registerQRCode;
    }

    public void setRegisterQRCode(String registerQRCode) {
        this.registerQRCode = registerQRCode;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    @Override
    public String toString() {
        return "EmmDeviceRegisterQRCodeCreateDTO{" +
                "id=" + id +
                ", marketName='" + marketName + '\'' +
                ", resellerName='" + resellerName + '\'' +
                ", merchantName='" + merchantName + '\'' +
                ", deviceType='" + deviceType + '\'' +
                ", registerQRCode='" + registerQRCode + '\'' +
                ", expireDate=" + expireDate +
                '}';
    }
}
