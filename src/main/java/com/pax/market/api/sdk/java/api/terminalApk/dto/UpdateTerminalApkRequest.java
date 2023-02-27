package com.pax.market.api.sdk.java.api.terminalApk.dto;

import java.io.Serializable;


/**
 * suspend/uninstall terminal apk request body
 */
public class UpdateTerminalApkRequest implements Serializable{


    private static final long serialVersionUID = 898895155179531805L;
    private String tid;
    private String serialNo;
    private String packageName;

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

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }
}
