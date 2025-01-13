package com.pax.market.api.sdk.java.api.emm.emmDevice.dto;

import com.pax.market.api.sdk.java.api.emm.emmDevice.EmmDeviceApi;

import java.io.Serializable;

public class EmmZteQuickUploadRecordCreateRequest implements Serializable {

    private static final long serialVersionUID = -7586285410580639024L;

    private String resellerName;

    private String merchantName;

    private String identifierType;

    private String manufacturer;

    private String model;

    private String numbers;

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

    public String getIdentifierType() {
        return identifierType;
    }

    public void setIdentifierType(EmmDeviceApi.EmmZteIdentifierType identifierType) {
        if (identifierType != null) {
            this.identifierType = identifierType.val();
        }
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getNumbers() {
        return numbers;
    }

    public void setNumbers(String numbers) {
        this.numbers = numbers;
    }

    @Override
    public String toString() {
        return "EmmZteQuickUploadRecordCreateRequest{" +
                "resellerName='" + resellerName + '\'' +
                ", merchantName='" + merchantName + '\'' +
                ", identifierType='" + identifierType + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", model='" + model + '\'' +
                ", numbers='" + numbers + '\'' +
                '}';
    }
}
