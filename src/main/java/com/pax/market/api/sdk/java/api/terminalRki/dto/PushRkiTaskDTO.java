package com.pax.market.api.sdk.java.api.terminalRki.dto;

import java.io.Serializable;

/**
 * @author liukai
 * @date 2020/2/7
 */
public class PushRkiTaskDTO implements Serializable {

    private static final long serialVersionUID = -9153084186205390661L;

    private Long id;
    private String terminalSN;
    private String rkiKey;
    private Long activatedDate;
    private Long effectiveTime;
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

    public String getRkiKey() {
        return rkiKey;
    }

    public void setRkiKey(String rkiKey) {
        this.rkiKey = rkiKey;
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

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }
}
