package com.pax.market.api.sdk.java.api.terminal.dto;

import com.pax.market.api.sdk.java.api.terminal.TerminalApi;

import java.io.Serializable;

public class TerminalLogRequest implements Serializable {
    private static final long serialVersionUID = 999233710066154994L;

    private String type;
    private String beginDate;
    private String endDate;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(String beginDate) {
        this.beginDate = beginDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
