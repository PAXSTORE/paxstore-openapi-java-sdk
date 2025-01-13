package com.pax.market.api.sdk.java.api.emm.emmDevice.dto;

import java.io.Serializable;

public class EmmModelDTO implements Serializable {


    private static final long serialVersionUID = -1710021430721942783L;

    private Long id;

    private String name;

    private String mfrName;

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

    public String getMfrName() {
        return mfrName;
    }

    public void setMfrName(String mfrName) {
        this.mfrName = mfrName;
    }

    @Override
    public String toString() {
        return "EmmModelDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", mfrName='" + mfrName + '\'' +
                '}';
    }

}
