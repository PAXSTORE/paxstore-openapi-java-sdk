package com.pax.market.api.sdk.java.api.terminalApk.dto;

import java.io.Serializable;
import java.util.Map;


public class TerminalApkParamDTO implements Serializable {


    private static final long serialVersionUID = 4826102239579200022L;

    private String paramTemplateName;
    private int actionStatus;
    private int errorCode;
    private Map<String, String> configuredParameters;

    public String getParamTemplateName() {
        return paramTemplateName;
    }

    public void setParamTemplateName(String paramTemplateName) {
        this.paramTemplateName = paramTemplateName;
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

    public Map<String, String> getConfiguredParameters() {
        return configuredParameters;
    }

    public void setConfiguredParameters(Map<String, String> configuredParameters) {
        this.configuredParameters = configuredParameters;
    }

    @Override
    public String toString() {
        return "TerminalApkParamDTO{" +
                "paramTemplateName='" + paramTemplateName + '\'' +
                ", actionStatus=" + actionStatus +
                ", errorCode=" + errorCode +
                ", configuredParameters=" + configuredParameters +
                '}';
    }
}
