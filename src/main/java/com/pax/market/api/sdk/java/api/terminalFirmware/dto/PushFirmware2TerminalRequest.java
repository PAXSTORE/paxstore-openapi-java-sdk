package com.pax.market.api.sdk.java.api.terminalFirmware.dto;

import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;

/**
 * @author liukai
 * @date 2019/5/23
 */
public class PushFirmware2TerminalRequest implements Serializable {


    private static final long serialVersionUID = -7281022470088330469L;

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
