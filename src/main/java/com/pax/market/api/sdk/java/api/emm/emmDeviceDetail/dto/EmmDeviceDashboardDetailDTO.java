package com.pax.market.api.sdk.java.api.emm.emmDeviceDetail.dto;

import java.io.Serializable;
import java.util.Date;

public class EmmDeviceDashboardDetailDTO implements Serializable {

    private static final long serialVersionUID = -2186252813732786954L;

    private Long terminalId;

    private String key;

    private String value;

    private Date syncDate;

    public Long getTerminalId() {
        return terminalId;
    }

    public void setTerminalId(Long terminalId) {
        this.terminalId = terminalId;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Date getSyncDate() {
        return syncDate;
    }

    public void setSyncDate(Date syncDate) {
        this.syncDate = syncDate;
    }

    @Override
    public String toString() {
        return "EmmDeviceDashboardDetailDTO{" +
                "terminalId=" + terminalId +
                ", key='" + key + '\'' +
                ", value='" + value + '\'' +
                ", syncDate=" + syncDate +
                '}';
    }
}
