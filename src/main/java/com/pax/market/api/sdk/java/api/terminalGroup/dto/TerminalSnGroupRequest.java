package com.pax.market.api.sdk.java.api.terminalGroup.dto;

import java.io.Serializable;
import java.util.Set;

/**
 * @author shifan
 * @date 2024/7/18
 */

public class TerminalSnGroupRequest implements Serializable {

    private Set<String> serialNos;
    private Set<Long> groupIds;

    public Set<String> getSerialNos() {
        return serialNos;
    }

    public void setSerialNos(Set<String> serialNos) {
        this.serialNos = serialNos;
    }

    public Set<Long> getGroupIds() {
        return groupIds;
    }

    public void setGroupIds(Set<Long> groupIds) {
        this.groupIds = groupIds;
    }

    @Override
    public String toString() {
        return "TerminalSnGroupRequest{" +
                "serialNos=" + serialNos +
                ", groupIds=" + groupIds +
                '}';
    }
}
