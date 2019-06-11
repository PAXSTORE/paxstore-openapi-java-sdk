package com.pax.market.api.sdk.java.api.terminalApk.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * @author liukai
 * @date 2019/6/3
 */
public class TerminalApkDTO implements Serializable {


    private static final long serialVersionUID = 4826102239579200022L;

    private Long id;
    private String terminalSN;
    private String apkPackageName;
    private Long apkVersionCode;
    private String apkVersionName;
    private Date activatedDate;
    private Date effectiveTime;
    private String status;
    private Long terminalId;
    private int actionStatus;
    private Date actionTime;
    private int errorCode;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTerminalSN() {
        return terminalSN;
    }

    public void setTerminalSN(String terminalSN) {
        this.terminalSN = terminalSN;
    }

    public String getApkPackageName() {
        return apkPackageName;
    }

    public void setApkPackageName(String apkPackageName) {
        this.apkPackageName = apkPackageName;
    }

    public Long getApkVersionCode() {
        return apkVersionCode;
    }

    public void setApkVersionCode(Long apkVersionCode) {
        this.apkVersionCode = apkVersionCode;
    }

    public String getApkVersionName() {
        return apkVersionName;
    }

    public void setApkVersionName(String apkVersionName) {
        this.apkVersionName = apkVersionName;
    }

    public Date getActivatedDate() {
        return activatedDate;
    }

    public void setActivatedDate(Date activatedDate) {
        this.activatedDate = activatedDate;
    }

    public Date getEffectiveTime() {
        return effectiveTime;
    }

    public void setEffectiveTime(Date effectiveTime) {
        this.effectiveTime = effectiveTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getTerminalId() {
        return terminalId;
    }

    public void setTerminalId(Long terminalId) {
        this.terminalId = terminalId;
    }

    public int getActionStatus() {
        return actionStatus;
    }

    public void setActionStatus(int actionStatus) {
        this.actionStatus = actionStatus;
    }

    public Date getActionTime() {
        return actionTime;
    }

    public void setActionTime(Date actionTime) {
        this.actionTime = actionTime;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }
}
