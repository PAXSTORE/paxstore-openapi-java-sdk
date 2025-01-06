package com.pax.market.api.sdk.java.api.emm.emmApp.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class EmmAppDetailDTO implements Serializable {

    private static final long serialVersionUID = 3430455301709482475L;

    private Long id;

    private String name;

    private String packageName;

    private String iconUrl;

    private String type;

    private String developerName;

    private Boolean supportManagedConfig;

    private Integer minAndroidSdkVersion;

    private List<String> screenshotUrls;

    private Date updateTime;

    private String appPricing;

    private String fullDescription;

    private List<String> appVersions;

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

    public Integer getMinAndroidSdkVersion() {
        return minAndroidSdkVersion;
    }

    public void setMinAndroidSdkVersion(Integer minAndroidSdkVersion) {
        this.minAndroidSdkVersion = minAndroidSdkVersion;
    }

    public List<String> getScreenshotUrls() {
        return screenshotUrls;
    }

    public void setScreenshotUrls(List<String> screenshotUrls) {
        this.screenshotUrls = screenshotUrls;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getAppPricing() {
        return appPricing;
    }

    public void setAppPricing(String appPricing) {
        this.appPricing = appPricing;
    }

    public String getFullDescription() {
        return fullDescription;
    }

    public void setFullDescription(String fullDescription) {
        this.fullDescription = fullDescription;
    }

    public List<String> getAppVersions() {
        return appVersions;
    }

    public void setAppVersions(List<String> appVersions) {
        this.appVersions = appVersions;
    }

    @Override
    public String toString() {
        return "EmmAppDetailDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", packageName='" + packageName + '\'' +
                ", iconUrl='" + iconUrl + '\'' +
                ", type='" + type + '\'' +
                ", developerName='" + developerName + '\'' +
                ", supportManagedConfig=" + supportManagedConfig +
                ", minAndroidSdkVersion=" + minAndroidSdkVersion +
                ", screenshotUrls=" + screenshotUrls +
                ", updateTime=" + updateTime +
                ", appPricing='" + appPricing + '\'' +
                ", fullDescription='" + fullDescription + '\'' +
                ", appVersions=" + appVersions +
                '}';
    }
}
