package com.pax.market.api.sdk.java.api.emm.emmDevice.dto;

import java.io.Serializable;

public class EmmMerchantDTO implements Serializable {


    private static final long serialVersionUID = -511623453030136849L;

    private Long id;

    private String name;

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

    @Override
    public String toString() {
        return "EmmMerchantDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
