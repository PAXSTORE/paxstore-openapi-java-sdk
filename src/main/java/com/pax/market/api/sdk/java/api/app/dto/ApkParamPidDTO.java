package com.pax.market.api.sdk.java.api.app.dto;

import java.io.Serializable;
import java.util.List;

/**
 * @author shifan
 * @date 2025/7/25
 */
public class ApkParamPidDTO implements Serializable {
    private static final long serialVersionUID = -9196935844145409105L;

    private List<String> pidList;

    public List<String> getPidList() {
        return pidList;
    }

    public void setPidList(List<String> pidList) {
        this.pidList = pidList;
    }

    @Override
    public String toString() {
        return "ApkParamPidDTO{" +
                "pidList=" + pidList +
                '}';
    }
}
