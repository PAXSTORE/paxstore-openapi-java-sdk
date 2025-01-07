package com.pax.market.api.sdk.java.api.emm.emmDevice.dto;

import com.pax.market.api.sdk.java.api.emm.emmDevice.EmmDeviceApi;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.io.Serializable;
import java.util.Date;

public class EmmDeviceRegisterQRCodeCreateRequest implements Serializable {

    private static final long serialVersionUID = 2620471794476555310L;

    private String resellerName;

    private String merchantName;

    private String type;

    private String expireDate;

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

    public String getType() {
        return type;
    }

    public void setType(EmmDeviceApi.EmmDeviceType type) {
        if (type != null) {
            this.type = type.val();
        }
    }

    public String getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
        if(expireDate != null) {
            this.expireDate = DateFormatUtils.ISO_8601_EXTENDED_DATETIME_TIME_ZONE_FORMAT.format(expireDate);
        }
    }

    @Override
    public String toString() {
        return "EmmDeviceRegisterQRCodeCreateRequest{" +
                "resellerName='" + resellerName + '\'' +
                ", merchantName='" + merchantName + '\'' +
                ", type='" + type + '\'' +
                ", expireDate=" + expireDate +
                '}';
    }
}
