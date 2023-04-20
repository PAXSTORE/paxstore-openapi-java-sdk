package com.pax.market.api.sdk.java.api.terminalRki.dto;


import java.io.Serializable;

/**
 * @author liukai
 * @date 2020/2/7
 */
public class PushRkiBasicRequest implements Serializable {

    private static final long serialVersionUID = 5497430690607921010L;
    private String tid;
    private String serialNo;
    private String rkiKey;

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

    public String getRkiKey() {
        return rkiKey;
    }

    public void setRkiKey(String rkiKey) {
        this.rkiKey = rkiKey;
    }
}
