package com.pax.market.api.sdk.java.api.emm.emmDeviceDetail.dto;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

public class EmmDeviceLocationDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 604992194977690763L;

    private Long terminalId;

    private Double lat;

    private Double lng;

    private Date updatedDate;

    public Long getTerminalId() {
        return terminalId;
    }

    public void setTerminalId(Long terminalId) {
        this.terminalId = terminalId;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    @Override
    public String toString() {
        return "EmmDeviceLocationDTO{" +
                "terminalId=" + terminalId +
                ", lat=" + lat +
                ", lng=" + lng +
                ", updatedDate=" + updatedDate +
                '}';
    }
}
