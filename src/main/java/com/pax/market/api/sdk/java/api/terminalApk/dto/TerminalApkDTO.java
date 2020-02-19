package com.pax.market.api.sdk.java.api.terminalApk.dto;

import java.io.Serializable;


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
    private Long activatedDate;
    private Long effectiveTime;
    private String status;
    private int actionStatus;
    private Long actionTime;
    private int errorCode;
    private TerminalApkParamDTO terminalApkParam;

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

    public Long getActivatedDate() {
        return activatedDate;
    }

    public void setActivatedDate(Long activatedDate) {
        this.activatedDate = activatedDate;
    }

    public Long getEffectiveTime() {
        return effectiveTime;
    }

    public void setEffectiveTime(Long effectiveTime) {
        this.effectiveTime = effectiveTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getActionStatus() {
        return actionStatus;
    }

    public void setActionStatus(int actionStatus) {
        this.actionStatus = actionStatus;
    }

    public Long getActionTime() {
        return actionTime;
    }

    public void setActionTime(Long actionTime) {
        this.actionTime = actionTime;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public TerminalApkParamDTO getTerminalApkParam() {
        return terminalApkParam;
    }

    public void setTerminalApkParam(TerminalApkParamDTO terminalApkParam) {
        this.terminalApkParam = terminalApkParam;
    }

    @Override
    public String toString() {
        return "TerminalApkDTO{" +
                "id=" + id +
                ", terminalSN='" + terminalSN + '\'' +
                ", apkPackageName='" + apkPackageName + '\'' +
                ", apkVersionCode=" + apkVersionCode +
                ", apkVersionName='" + apkVersionName + '\'' +
                ", activatedDate=" + activatedDate +
                ", effectiveTime=" + effectiveTime +
                ", status='" + status + '\'' +
                ", actionStatus=" + actionStatus +
                ", actionTime=" + actionTime +
                ", errorCode=" + errorCode +
                ", terminalApkParam=" + terminalApkParam +
                '}';
    }
}
