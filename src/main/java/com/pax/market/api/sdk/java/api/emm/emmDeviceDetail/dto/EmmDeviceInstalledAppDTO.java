package com.pax.market.api.sdk.java.api.emm.emmDeviceDetail.dto;

import java.io.Serializable;
import java.util.Date;

public class EmmDeviceInstalledAppDTO implements Serializable {

    private static final long serialVersionUID = -759874756210966159L;

    private Long id;

    private Long terminalId;

    private String name;

    private String packageName;

    private String version;

    private String type;

    private Long size;

    private String iconUrl;

    private Date installTime;

    private Date lastTimeUpdate;

    private Boolean isLauncher;

    private Boolean isDefaultLauncher;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTerminalId() {
        return terminalId;
    }

    public void setTerminalId(Long terminalId) {
        this.terminalId = terminalId;
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

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public Date getInstallTime() {
        return installTime;
    }

    public void setInstallTime(Date installTime) {
        this.installTime = installTime;
    }

    public Date getLastTimeUpdate() {
        return lastTimeUpdate;
    }

    public void setLastTimeUpdate(Date lastTimeUpdate) {
        this.lastTimeUpdate = lastTimeUpdate;
    }

    public Boolean getLauncher() {
        return isLauncher;
    }

    public void setLauncher(Boolean launcher) {
        isLauncher = launcher;
    }

    public Boolean getDefaultLauncher() {
        return isDefaultLauncher;
    }

    public void setDefaultLauncher(Boolean defaultLauncher) {
        isDefaultLauncher = defaultLauncher;
    }

    @Override
    public String toString() {
        return "EmmDeviceInstalledAppDTO{" +
                "id=" + id +
                ", terminalId=" + terminalId +
                ", name='" + name + '\'' +
                ", packageName='" + packageName + '\'' +
                ", version='" + version + '\'' +
                ", type='" + type + '\'' +
                ", size=" + size +
                ", iconUrl='" + iconUrl + '\'' +
                ", installTime=" + installTime +
                ", lastTimeUpdate=" + lastTimeUpdate +
                ", isLauncher=" + isLauncher +
                ", isDefaultLauncher=" + isDefaultLauncher +
                '}';
    }
}
