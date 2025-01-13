package com.pax.market.api.sdk.java.api.emm.emmDeviceDetail.dto;

import java.io.Serializable;
import java.util.Date;

public class EmmDeviceDashboardMonitorDTO implements Serializable {

    private static final long serialVersionUID = -6548635128970521701L;

    private Long terminalId;

    private Float battery;

    private Long ramUsed;

    private Long storageUsed;

    private Date syncDate;

    public Long getTerminalId() {
        return terminalId;
    }

    public void setTerminalId(Long terminalId) {
        this.terminalId = terminalId;
    }

    public Float getBattery() {
        return battery;
    }

    public void setBattery(Float battery) {
        this.battery = battery;
    }

    public Long getRamUsed() {
        return ramUsed;
    }

    public void setRamUsed(Long ramUsed) {
        this.ramUsed = ramUsed;
    }

    public Long getStorageUsed() {
        return storageUsed;
    }

    public void setStorageUsed(Long storageUsed) {
        this.storageUsed = storageUsed;
    }

    public Date getSyncDate() {
        return syncDate;
    }

    public void setSyncDate(Date syncDate) {
        this.syncDate = syncDate;
    }

    @Override
    public String toString() {
        return "EmmDeviceDashboardMonitorDTO{" +
                "terminalId=" + terminalId +
                ", battery=" + battery +
                ", ramUsed=" + ramUsed +
                ", storageUsed=" + storageUsed +
                ", syncDate=" + syncDate +
                '}';
    }
}
