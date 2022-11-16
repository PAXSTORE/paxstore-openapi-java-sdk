package com.pax.market.api.sdk.java.api.terminalFirmware.dto;

import java.io.Serializable;

/**
 * @author liukai
 * @date 2019/7/24
 */
public class PushFirmwareTaskDTO implements Serializable {

    private static final long serialVersionUID = 8829969550106590199L;

    private Long id;
    private String terminalSN;
    private String fmName;
    private Long activatedDate;
    private Boolean wifiOnly;
    private Long effectiveTime;
    private Long expiredTime;
    private String status;
    private int actionStatus;
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

    public String getFmName() {
        return fmName;
    }

    public void setFmName(String fmName) {
        this.fmName = fmName;
    }

    public Long getActivatedDate() {
        return activatedDate;
    }

    public void setActivatedDate(Long activatedDate) {
        this.activatedDate = activatedDate;
    }

    public Boolean getWifiOnly() {
        return wifiOnly;
    }

    public void setWifiOnly(Boolean wifiOnly) {
        this.wifiOnly = wifiOnly;
    }

    public Long getEffectiveTime() {
        return effectiveTime;
    }

    public void setEffectiveTime(Long effectiveTime) {
        this.effectiveTime = effectiveTime;
    }

    public Long getExpiredTime() {
        return expiredTime;
    }

    public void setExpiredTime(Long expiredTime) {
        this.expiredTime = expiredTime;
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

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    @Override
    public String toString() {
        return "PushFirmwareTaskDTO{" +
                "id=" + id +
                ", terminalSN='" + terminalSN + '\'' +
                ", fmName='" + fmName + '\'' +
                ", activatedDate=" + activatedDate +
                ", wifiOnly=" + wifiOnly +
                ", effectiveTime=" + effectiveTime +
                ", expiredTime=" + expiredTime +
                ", status='" + status + '\'' +
                ", actionStatus=" + actionStatus +
                ", errorCode=" + errorCode +
                '}';
    }
}
