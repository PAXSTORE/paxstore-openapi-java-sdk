package com.pax.market.api.sdk.java.api.terminalFirmware.dto;

import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;

/**
 * @author liukai
 * @date 2019/7/24
 */
public class PushFirmwareTaskBasicRequest implements Serializable {

    private static final long serialVersionUID = 140544918229486863L;
    private String tid;
    private String serialNo;
    @NotBlank
    private String fmName;

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    public String getFmName() {
        return fmName;
    }

    public void setFmName(String fmName) {
        this.fmName = fmName;
    }
}
