package com.pax.market.api.sdk.java.api.emm.emmDevice.dto;

import java.io.Serializable;
import java.util.Date;

public class EmmDeviceDetailDTO implements Serializable {

    private static final long serialVersionUID = 7090323697415577752L;

    private Long id;

    private String name;

    private String serialNo;

    private String type;

    private String status;

    private String securityStatus;

    private Date registerTime;

    private EmmModelDTO model;

    private EmmResellerDTO reseller;

    public String getSecurityStatus() {
        return securityStatus;
    }

    public void setSecurityStatus(String securityStatus) {
        this.securityStatus = securityStatus;
    }

    private EmmMerchantDTO merchant;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }

    public EmmModelDTO getModel() {
        return model;
    }

    public void setModel(EmmModelDTO model) {
        this.model = model;
    }

    public EmmResellerDTO getReseller() {
        return reseller;
    }

    public void setReseller(EmmResellerDTO reseller) {
        this.reseller = reseller;
    }

    public EmmMerchantDTO getMerchant() {
        return merchant;
    }

    public void setMerchant(EmmMerchantDTO merchant) {
        this.merchant = merchant;
    }

    @Override
    public String toString() {
        return "EmmDeviceDetailDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", serialNo='" + serialNo + '\'' +
                ", type='" + type + '\'' +
                ", status='" + status + '\'' +
                ", securityStatus='" + securityStatus + '\'' +
                ", registerTime=" + registerTime +
                ", model=" + model +
                ", reseller=" + reseller +
                ", merchant=" + merchant +
                '}';
    }
}
