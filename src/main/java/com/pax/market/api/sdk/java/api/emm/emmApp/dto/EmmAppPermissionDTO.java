package com.pax.market.api.sdk.java.api.emm.emmApp.dto;

import java.io.Serializable;
import java.util.List;

public class EmmAppPermissionDTO implements Serializable {


    private static final long serialVersionUID = -8006461827691065052L;

    private List<String> permissions;

    public List<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<String> permissions) {
        this.permissions = permissions;
    }

    @Override
    public String toString() {
        return "EmmAppPermissionDTO{" +
                "permissions=" + permissions +
                '}';
    }

}
