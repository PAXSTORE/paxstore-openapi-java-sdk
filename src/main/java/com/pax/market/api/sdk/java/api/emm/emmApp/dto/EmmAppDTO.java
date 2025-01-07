package com.pax.market.api.sdk.java.api.emm.emmApp.dto;

import java.io.Serializable;

public class EmmAppDTO implements Serializable {

    private static final long serialVersionUID = 5575816159928472647L;

    private Long id;

    private String name;

    private String packageName;

    private String iconUrl;

    private String type;

    private String developerName;

    private Boolean supportManagedConfig;

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

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDeveloperName() {
        return developerName;
    }

    public void setDeveloperName(String developerName) {
        this.developerName = developerName;
    }

    public Boolean getSupportManagedConfig() {
        return supportManagedConfig;
    }

    public void setSupportManagedConfig(Boolean supportManagedConfig) {
        this.supportManagedConfig = supportManagedConfig;
    }

    @Override
    public String toString() {
        return "EmmAppDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", packageName='" + packageName + '\'' +
                ", iconUrl='" + iconUrl + '\'' +
                ", type='" + type + '\'' +
                ", developerName='" + developerName + '\'' +
                ", supportManagedConfig=" + supportManagedConfig +
                '}';
    }


}
