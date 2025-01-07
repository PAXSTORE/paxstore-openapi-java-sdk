package com.pax.market.api.sdk.java.api.emm.emmApp.dto;


import java.io.Serializable;

public class SubscribeEmmAppDTO implements Serializable {

    private static final long serialVersionUID = 238636245787782323L;

    private Long id;

    private String name;

    private String packageName;

    private String type;

    private String iconUrl;

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

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    @Override
    public String toString() {
        return "SubscribeEmmAppDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", packageName='" + packageName + '\'' +
                ", type='" + type + '\'' +
                ", iconUrl='" + iconUrl + '\'' +
                '}';
    }
}
