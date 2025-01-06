package com.pax.market.api.sdk.java.api.emm.emmDevice.dto;

import java.io.Serializable;

public class EmmResellerDTO implements Serializable {

    private static final long serialVersionUID = 4902682778383017467L;

    private Long id;

    private String name;

    private String parentIds;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParentIds() {
        return parentIds;
    }

    public void setParentIds(String parentIds) {
        this.parentIds = parentIds;
    }

    @Override
    public String toString() {
        return "EmmResellerDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", parentIds='" + parentIds + '\'' +
                '}';
    }
}
