package com.pax.market.api.sdk.java.api.emm.emmDevice.dto;

import java.io.Serializable;
import java.util.Date;

public class EmmDeviceDTO implements Serializable {


    private static final long serialVersionUID = 8511872278212941794L;

    private Long id;

    private String name;

    private String serialNo;

    private EmmModelDTO model;

    private EmmResellerDTO reseller;

    private EmmMerchantDTO merchant;

    private Date registerTime;

    private String status;

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

    public Date getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "EmmDeviceDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", serialNo='" + serialNo + '\'' +
                ", model=" + model +
                ", reseller=" + reseller +
                ", merchant=" + merchant +
                ", registerTime=" + registerTime +
                ", status='" + status + '\'' +
                '}';
    }
}
