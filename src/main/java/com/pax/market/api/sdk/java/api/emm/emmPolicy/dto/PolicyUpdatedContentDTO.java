package com.pax.market.api.sdk.java.api.emm.emmPolicy.dto;


import java.io.Serializable;
import java.util.List;

public class PolicyUpdatedContentDTO implements Serializable {

    private static final long serialVersionUID = -8135891087772853155L;

    private Boolean adjustVolumeDisabled;

    private AdvancedSecurityOverrides advancedSecurityOverrides;

    private AlwaysOnVpnPackage alwaysOnVpnPackage;

    private String appAutoUpdatePolicy;

    private String autoDateAndTimeZone;

    private Boolean bluetoothDisabled;

    private String cameraAccess;

    private Boolean cellBroadcastsConfigDisabled;

    private Boolean dataRoamingDisabled;

    private DeviceRadioState deviceRadioState;

    private String encryptionPolicy;

    private Boolean factoryResetDisabled;

    private Boolean funDisabled;

    private Boolean installAppsDisabled;

    private List<String> keyguardDisabledFeatures;

    private String locationMode;

    private Long maximumTimeToLock;

    private String microphoneAccess;

    private Integer minimumApiLevel;

    private Boolean mobileNetworksConfigDisabled;

    private Boolean mountPhysicalMediaDisabled;

    private Boolean networkEscapeHatchEnabled;

    private Boolean networkResetDisabled;

    private List<NetworkConfiguration> networkConfigurations;

    private Boolean outgoingCallsDisabled;

    private PasswordRequirements passwordPolicies;

    private List<PermissionGrant> permissionGrants;

    private PackageNameList permittedAccessibilityServices;

    private PackageNameList permittedInputMethods;

    private String playStoreMode;

    private String printingPolicy;

    private Boolean screenCaptureDisabled;

    private Boolean setWallpaperDisabled;

    private Boolean shareLocationDisabled;

    private Boolean smsDisabled;

    private List<String> stayOnPluggedModes;

    private SystemUpdate systemUpdate;

    private Boolean uninstallAppsDisabled;

    private Boolean vpnConfigDisabled;

    private DeviceConnectivityManagement deviceConnectivityManagement;

    private String deviceOwnerLockScreenInfo;

    private List<PolicyEnforcementRule> policyEnforcementRules;

    private List<ApplicationPolicy> applications;

    public Boolean getAdjustVolumeDisabled() {
        return adjustVolumeDisabled;
    }

    public void setAdjustVolumeDisabled(Boolean adjustVolumeDisabled) {
        this.adjustVolumeDisabled = adjustVolumeDisabled;
    }

    public AdvancedSecurityOverrides getAdvancedSecurityOverrides() {
        return advancedSecurityOverrides;
    }

    public void setAdvancedSecurityOverrides(AdvancedSecurityOverrides advancedSecurityOverrides) {
        this.advancedSecurityOverrides = advancedSecurityOverrides;
    }

    public AlwaysOnVpnPackage getAlwaysOnVpnPackage() {
        return alwaysOnVpnPackage;
    }

    public void setAlwaysOnVpnPackage(AlwaysOnVpnPackage alwaysOnVpnPackage) {
        this.alwaysOnVpnPackage = alwaysOnVpnPackage;
    }

    public String getAppAutoUpdatePolicy() {
        return appAutoUpdatePolicy;
    }

    public void setAppAutoUpdatePolicy(String appAutoUpdatePolicy) {
        this.appAutoUpdatePolicy = appAutoUpdatePolicy;
    }

    public String getAutoDateAndTimeZone() {
        return autoDateAndTimeZone;
    }

    public void setAutoDateAndTimeZone(String autoDateAndTimeZone) {
        this.autoDateAndTimeZone = autoDateAndTimeZone;
    }

    public Boolean getBluetoothDisabled() {
        return bluetoothDisabled;
    }

    public void setBluetoothDisabled(Boolean bluetoothDisabled) {
        this.bluetoothDisabled = bluetoothDisabled;
    }

    public String getCameraAccess() {
        return cameraAccess;
    }

    public void setCameraAccess(String cameraAccess) {
        this.cameraAccess = cameraAccess;
    }

    public Boolean getCellBroadcastsConfigDisabled() {
        return cellBroadcastsConfigDisabled;
    }

    public void setCellBroadcastsConfigDisabled(Boolean cellBroadcastsConfigDisabled) {
        this.cellBroadcastsConfigDisabled = cellBroadcastsConfigDisabled;
    }

    public Boolean getDataRoamingDisabled() {
        return dataRoamingDisabled;
    }

    public void setDataRoamingDisabled(Boolean dataRoamingDisabled) {
        this.dataRoamingDisabled = dataRoamingDisabled;
    }

    public DeviceRadioState getDeviceRadioState() {
        return deviceRadioState;
    }

    public void setDeviceRadioState(DeviceRadioState deviceRadioState) {
        this.deviceRadioState = deviceRadioState;
    }

    public String getEncryptionPolicy() {
        return encryptionPolicy;
    }

    public void setEncryptionPolicy(String encryptionPolicy) {
        this.encryptionPolicy = encryptionPolicy;
    }

    public Boolean getFactoryResetDisabled() {
        return factoryResetDisabled;
    }

    public void setFactoryResetDisabled(Boolean factoryResetDisabled) {
        this.factoryResetDisabled = factoryResetDisabled;
    }

    public Boolean getFunDisabled() {
        return funDisabled;
    }

    public void setFunDisabled(Boolean funDisabled) {
        this.funDisabled = funDisabled;
    }

    public Boolean getInstallAppsDisabled() {
        return installAppsDisabled;
    }

    public void setInstallAppsDisabled(Boolean installAppsDisabled) {
        this.installAppsDisabled = installAppsDisabled;
    }

    public List<String> getKeyguardDisabledFeatures() {
        return keyguardDisabledFeatures;
    }

    public void setKeyguardDisabledFeatures(List<String> keyguardDisabledFeatures) {
        this.keyguardDisabledFeatures = keyguardDisabledFeatures;
    }

    public String getLocationMode() {
        return locationMode;
    }

    public void setLocationMode(String locationMode) {
        this.locationMode = locationMode;
    }

    public Long getMaximumTimeToLock() {
        return maximumTimeToLock;
    }

    public void setMaximumTimeToLock(Long maximumTimeToLock) {
        this.maximumTimeToLock = maximumTimeToLock;
    }

    public String getMicrophoneAccess() {
        return microphoneAccess;
    }

    public void setMicrophoneAccess(String microphoneAccess) {
        this.microphoneAccess = microphoneAccess;
    }

    public Integer getMinimumApiLevel() {
        return minimumApiLevel;
    }

    public void setMinimumApiLevel(Integer minimumApiLevel) {
        this.minimumApiLevel = minimumApiLevel;
    }

    public Boolean getMobileNetworksConfigDisabled() {
        return mobileNetworksConfigDisabled;
    }

    public void setMobileNetworksConfigDisabled(Boolean mobileNetworksConfigDisabled) {
        this.mobileNetworksConfigDisabled = mobileNetworksConfigDisabled;
    }

    public Boolean getMountPhysicalMediaDisabled() {
        return mountPhysicalMediaDisabled;
    }

    public void setMountPhysicalMediaDisabled(Boolean mountPhysicalMediaDisabled) {
        this.mountPhysicalMediaDisabled = mountPhysicalMediaDisabled;
    }

    public Boolean getNetworkEscapeHatchEnabled() {
        return networkEscapeHatchEnabled;
    }

    public void setNetworkEscapeHatchEnabled(Boolean networkEscapeHatchEnabled) {
        this.networkEscapeHatchEnabled = networkEscapeHatchEnabled;
    }

    public Boolean getNetworkResetDisabled() {
        return networkResetDisabled;
    }

    public void setNetworkResetDisabled(Boolean networkResetDisabled) {
        this.networkResetDisabled = networkResetDisabled;
    }

    public List<NetworkConfiguration> getNetworkConfigurations() {
        return networkConfigurations;
    }

    public void setNetworkConfigurations(List<NetworkConfiguration> networkConfigurations) {
        this.networkConfigurations = networkConfigurations;
    }

    public Boolean getOutgoingCallsDisabled() {
        return outgoingCallsDisabled;
    }

    public void setOutgoingCallsDisabled(Boolean outgoingCallsDisabled) {
        this.outgoingCallsDisabled = outgoingCallsDisabled;
    }

    public PasswordRequirements getPasswordPolicies() {
        return passwordPolicies;
    }

    public void setPasswordPolicies(PasswordRequirements passwordPolicies) {
        this.passwordPolicies = passwordPolicies;
    }

    public List<PermissionGrant> getPermissionGrants() {
        return permissionGrants;
    }

    public void setPermissionGrants(List<PermissionGrant> permissionGrants) {
        this.permissionGrants = permissionGrants;
    }

    public PackageNameList getPermittedAccessibilityServices() {
        return permittedAccessibilityServices;
    }

    public void setPermittedAccessibilityServices(PackageNameList permittedAccessibilityServices) {
        this.permittedAccessibilityServices = permittedAccessibilityServices;
    }

    public PackageNameList getPermittedInputMethods() {
        return permittedInputMethods;
    }

    public void setPermittedInputMethods(PackageNameList permittedInputMethods) {
        this.permittedInputMethods = permittedInputMethods;
    }

    public String getPlayStoreMode() {
        return playStoreMode;
    }

    public void setPlayStoreMode(String playStoreMode) {
        this.playStoreMode = playStoreMode;
    }

    public String getPrintingPolicy() {
        return printingPolicy;
    }

    public void setPrintingPolicy(String printingPolicy) {
        this.printingPolicy = printingPolicy;
    }

    public Boolean getScreenCaptureDisabled() {
        return screenCaptureDisabled;
    }

    public void setScreenCaptureDisabled(Boolean screenCaptureDisabled) {
        this.screenCaptureDisabled = screenCaptureDisabled;
    }

    public Boolean getSetWallpaperDisabled() {
        return setWallpaperDisabled;
    }

    public void setSetWallpaperDisabled(Boolean setWallpaperDisabled) {
        this.setWallpaperDisabled = setWallpaperDisabled;
    }

    public Boolean getShareLocationDisabled() {
        return shareLocationDisabled;
    }

    public void setShareLocationDisabled(Boolean shareLocationDisabled) {
        this.shareLocationDisabled = shareLocationDisabled;
    }

    public Boolean getSmsDisabled() {
        return smsDisabled;
    }

    public void setSmsDisabled(Boolean smsDisabled) {
        this.smsDisabled = smsDisabled;
    }

    public List<String> getStayOnPluggedModes() {
        return stayOnPluggedModes;
    }

    public void setStayOnPluggedModes(List<String> stayOnPluggedModes) {
        this.stayOnPluggedModes = stayOnPluggedModes;
    }

    public SystemUpdate getSystemUpdate() {
        return systemUpdate;
    }

    public void setSystemUpdate(SystemUpdate systemUpdate) {
        this.systemUpdate = systemUpdate;
    }

    public Boolean getUninstallAppsDisabled() {
        return uninstallAppsDisabled;
    }

    public void setUninstallAppsDisabled(Boolean uninstallAppsDisabled) {
        this.uninstallAppsDisabled = uninstallAppsDisabled;
    }

    public Boolean getVpnConfigDisabled() {
        return vpnConfigDisabled;
    }

    public void setVpnConfigDisabled(Boolean vpnConfigDisabled) {
        this.vpnConfigDisabled = vpnConfigDisabled;
    }

    public DeviceConnectivityManagement getDeviceConnectivityManagement() {
        return deviceConnectivityManagement;
    }

    public void setDeviceConnectivityManagement(DeviceConnectivityManagement deviceConnectivityManagement) {
        this.deviceConnectivityManagement = deviceConnectivityManagement;
    }

    public String getDeviceOwnerLockScreenInfo() {
        return deviceOwnerLockScreenInfo;
    }

    public void setDeviceOwnerLockScreenInfo(String deviceOwnerLockScreenInfo) {
        this.deviceOwnerLockScreenInfo = deviceOwnerLockScreenInfo;
    }

    public List<PolicyEnforcementRule> getPolicyEnforcementRules() {
        return policyEnforcementRules;
    }

    public void setPolicyEnforcementRules(List<PolicyEnforcementRule> policyEnforcementRules) {
        this.policyEnforcementRules = policyEnforcementRules;
    }

    public List<ApplicationPolicy> getApplications() {
        return applications;
    }

    public void setApplications(List<ApplicationPolicy> applications) {
        this.applications = applications;
    }

    @Override
    public String toString() {
        return "PolicyUpdatedContentDTO{" +
                "adjustVolumeDisabled=" + adjustVolumeDisabled +
                ", advancedSecurityOverrides=" + advancedSecurityOverrides +
                ", alwaysOnVpnPackage=" + alwaysOnVpnPackage +
                ", appAutoUpdatePolicy='" + appAutoUpdatePolicy + '\'' +
                ", autoDateAndTimeZone='" + autoDateAndTimeZone + '\'' +
                ", bluetoothDisabled=" + bluetoothDisabled +
                ", cameraAccess='" + cameraAccess + '\'' +
                ", cellBroadcastsConfigDisabled=" + cellBroadcastsConfigDisabled +
                ", dataRoamingDisabled=" + dataRoamingDisabled +
                ", deviceRadioState=" + deviceRadioState +
                ", encryptionPolicy='" + encryptionPolicy + '\'' +
                ", factoryResetDisabled=" + factoryResetDisabled +
                ", funDisabled=" + funDisabled +
                ", installAppsDisabled=" + installAppsDisabled +
                ", keyguardDisabledFeatures=" + keyguardDisabledFeatures +
                ", locationMode='" + locationMode + '\'' +
                ", maximumTimeToLock=" + maximumTimeToLock +
                ", microphoneAccess='" + microphoneAccess + '\'' +
                ", minimumApiLevel=" + minimumApiLevel +
                ", mobileNetworksConfigDisabled=" + mobileNetworksConfigDisabled +
                ", mountPhysicalMediaDisabled=" + mountPhysicalMediaDisabled +
                ", networkEscapeHatchEnabled=" + networkEscapeHatchEnabled +
                ", networkResetDisabled=" + networkResetDisabled +
                ", networkConfigurations=" + networkConfigurations +
                ", outgoingCallsDisabled=" + outgoingCallsDisabled +
                ", passwordPolicies=" + passwordPolicies +
                ", permissionGrants=" + permissionGrants +
                ", permittedAccessibilityServices=" + permittedAccessibilityServices +
                ", permittedInputMethods=" + permittedInputMethods +
                ", playStoreMode='" + playStoreMode + '\'' +
                ", printingPolicy='" + printingPolicy + '\'' +
                ", screenCaptureDisabled=" + screenCaptureDisabled +
                ", setWallpaperDisabled=" + setWallpaperDisabled +
                ", shareLocationDisabled=" + shareLocationDisabled +
                ", smsDisabled=" + smsDisabled +
                ", stayOnPluggedModes=" + stayOnPluggedModes +
                ", systemUpdate=" + systemUpdate +
                ", uninstallAppsDisabled=" + uninstallAppsDisabled +
                ", vpnConfigDisabled=" + vpnConfigDisabled +
                ", deviceConnectivityManagement=" + deviceConnectivityManagement +
                ", deviceOwnerLockScreenInfo='" + deviceOwnerLockScreenInfo + '\'' +
                ", policyEnforcementRules=" + policyEnforcementRules +
                ", applications=" + applications +
                '}';
    }

    public static class NetworkConfiguration implements Serializable {

        private static final long serialVersionUID = 3634670332752051714L;

        private String ssid;

        private String cipherType;

        private String password;

        private String proxyType;

        private String hostName;

        private Integer port;

        private String pacUrl;

        public String getSsid() {
            return ssid;
        }

        public void setSsid(String ssid) {
            this.ssid = ssid;
        }

        public String getCipherType() {
            return cipherType;
        }

        public void setCipherType(String cipherType) {
            this.cipherType = cipherType;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getProxyType() {
            return proxyType;
        }

        public void setProxyType(String proxyType) {
            this.proxyType = proxyType;
        }

        public String getHostName() {
            return hostName;
        }

        public void setHostName(String hostName) {
            this.hostName = hostName;
        }

        public Integer getPort() {
            return port;
        }

        public void setPort(Integer port) {
            this.port = port;
        }

        public String getPacUrl() {
            return pacUrl;
        }

        public void setPacUrl(String pacUrl) {
            this.pacUrl = pacUrl;
        }

        @Override
        public String toString() {
            return "NetworkConfiguration{" +
                    "ssid='" + ssid + '\'' +
                    ", cipherType='" + cipherType + '\'' +
                    ", password='" + password + '\'' +
                    ", proxyType='" + proxyType + '\'' +
                    ", hostName='" + hostName + '\'' +
                    ", port=" + port +
                    ", pacUrl='" + pacUrl + '\'' +
                    '}';
        }
    }

    public static class AdvancedSecurityOverrides implements Serializable {

        private static final long serialVersionUID = -5976517613135919015L;

        private String developerSettings;

        private String googlePlayProtectVerifyApps;

        private String untrustedAppsPolicy;

        public String getDeveloperSettings() {
            return developerSettings;
        }

        public void setDeveloperSettings(String developerSettings) {
            this.developerSettings = developerSettings;
        }

        public String getGooglePlayProtectVerifyApps() {
            return googlePlayProtectVerifyApps;
        }

        public void setGooglePlayProtectVerifyApps(String googlePlayProtectVerifyApps) {
            this.googlePlayProtectVerifyApps = googlePlayProtectVerifyApps;
        }

        public String getUntrustedAppsPolicy() {
            return untrustedAppsPolicy;
        }

        public void setUntrustedAppsPolicy(String untrustedAppsPolicy) {
            this.untrustedAppsPolicy = untrustedAppsPolicy;
        }

        @Override
        public String toString() {
            return "AdvancedSecurityOverrides{" +
                    "developerSettings='" + developerSettings + '\'' +
                    ", googlePlayProtectVerifyApps='" + googlePlayProtectVerifyApps + '\'' +
                    ", untrustedAppsPolicy='" + untrustedAppsPolicy + '\'' +
                    '}';
        }

    }

    public static class AlwaysOnVpnPackage implements Serializable {

        private static final long serialVersionUID = -4200057555236034198L;

        private Boolean lockdownEnabled;

        private String packageName;

        public Boolean getLockdownEnabled() {
            return lockdownEnabled;
        }

        public void setLockdownEnabled(Boolean lockdownEnabled) {
            this.lockdownEnabled = lockdownEnabled;
        }

        public String getPackageName() {
            return packageName;
        }

        public void setPackageName(String packageName) {
            this.packageName = packageName;
        }

        @Override
        public String toString() {
            return "AlwaysOnVpnPackage{" +
                    "lockdownEnabled=" + lockdownEnabled +
                    ", packageName='" + packageName + '\'' +
                    '}';
        }
    }


    public static class PermissionGrant implements Serializable {

        private static final long serialVersionUID = 4173582954820570246L;

        private String permission;

        private String policy;

        public String getPermission() {
            return permission;
        }

        public void setPermission(String permission) {
            this.permission = permission;
        }

        public String getPolicy() {
            return policy;
        }

        public void setPolicy(String policy) {
            this.policy = policy;
        }

        @Override
        public String toString() {
            return "PermissionGrant{" +
                    "permission='" + permission + '\'' +
                    ", policy='" + policy + '\'' +
                    '}';
        }
    }

    public static class AppPermissionGrant implements Serializable {

        private static final long serialVersionUID = -8567519558161331006L;

        private String permission;

        private String policy;

        public String getPermission() {
            return permission;
        }

        public void setPermission(String permission) {
            this.permission = permission;
        }

        public String getPolicy() {
            return policy;
        }

        public void setPolicy(String policy) {
            this.policy = policy;
        }

        @Override
        public String toString() {
            return "AppPermissionGrant{" +
                    "permission='" + permission + '\'' +
                    ", policy='" + policy + '\'' +
                    '}';
        }
    }

    public static class DeviceRadioState implements Serializable {

        private static final long serialVersionUID = 7379023722941893992L;

        private String airplaneModeState;

        private String minimumWifiSecurityLevel;

        public String getAirplaneModeState() {
            return airplaneModeState;
        }

        public void setAirplaneModeState(String airplaneModeState) {
            this.airplaneModeState = airplaneModeState;
        }

        public String getMinimumWifiSecurityLevel() {
            return minimumWifiSecurityLevel;
        }

        public void setMinimumWifiSecurityLevel(String minimumWifiSecurityLevel) {
            this.minimumWifiSecurityLevel = minimumWifiSecurityLevel;
        }

        @Override
        public String toString() {
            return "DeviceRadioState{" +
                    "airplaneModeState='" + airplaneModeState + '\'' +
                    ", minimumWifiSecurityLevel='" + minimumWifiSecurityLevel + '\'' +
                    '}';
        }
    }


    public static class PasswordRequirements implements Serializable {

        private static final long serialVersionUID = -1312881632840464032L;

        private Integer maximumFailedPasswordsForWipe;

        private String passwordExpirationTimeout;

        private Integer passwordHistoryLength;

        private Integer passwordMinimumLength;

        private Integer passwordMinimumLetters;

        private Integer passwordMinimumLowerCase;

        private Integer passwordMinimumNumeric;

        private Integer passwordMinimumSymbols;

        private Integer passwordMinimumUpperCase;

        private String passwordQuality;

        private String requirePasswordUnlock;

        public Integer getMaximumFailedPasswordsForWipe() {
            return maximumFailedPasswordsForWipe;
        }

        public void setMaximumFailedPasswordsForWipe(Integer maximumFailedPasswordsForWipe) {
            this.maximumFailedPasswordsForWipe = maximumFailedPasswordsForWipe;
        }

        public String getPasswordExpirationTimeout() {
            return passwordExpirationTimeout;
        }

        public void setPasswordExpirationTimeout(String passwordExpirationTimeout) {
            this.passwordExpirationTimeout = passwordExpirationTimeout;
        }

        public Integer getPasswordHistoryLength() {
            return passwordHistoryLength;
        }

        public void setPasswordHistoryLength(Integer passwordHistoryLength) {
            this.passwordHistoryLength = passwordHistoryLength;
        }

        public Integer getPasswordMinimumLength() {
            return passwordMinimumLength;
        }

        public void setPasswordMinimumLength(Integer passwordMinimumLength) {
            this.passwordMinimumLength = passwordMinimumLength;
        }

        public Integer getPasswordMinimumLetters() {
            return passwordMinimumLetters;
        }

        public void setPasswordMinimumLetters(Integer passwordMinimumLetters) {
            this.passwordMinimumLetters = passwordMinimumLetters;
        }

        public Integer getPasswordMinimumLowerCase() {
            return passwordMinimumLowerCase;
        }

        public void setPasswordMinimumLowerCase(Integer passwordMinimumLowerCase) {
            this.passwordMinimumLowerCase = passwordMinimumLowerCase;
        }

        public Integer getPasswordMinimumNumeric() {
            return passwordMinimumNumeric;
        }

        public void setPasswordMinimumNumeric(Integer passwordMinimumNumeric) {
            this.passwordMinimumNumeric = passwordMinimumNumeric;
        }

        public Integer getPasswordMinimumSymbols() {
            return passwordMinimumSymbols;
        }

        public void setPasswordMinimumSymbols(Integer passwordMinimumSymbols) {
            this.passwordMinimumSymbols = passwordMinimumSymbols;
        }

        public Integer getPasswordMinimumUpperCase() {
            return passwordMinimumUpperCase;
        }

        public void setPasswordMinimumUpperCase(Integer passwordMinimumUpperCase) {
            this.passwordMinimumUpperCase = passwordMinimumUpperCase;
        }

        public String getPasswordQuality() {
            return passwordQuality;
        }

        public void setPasswordQuality(String passwordQuality) {
            this.passwordQuality = passwordQuality;
        }

        public String getRequirePasswordUnlock() {
            return requirePasswordUnlock;
        }

        public void setRequirePasswordUnlock(String requirePasswordUnlock) {
            this.requirePasswordUnlock = requirePasswordUnlock;
        }

        @Override
        public String toString() {
            return "PasswordRequirements{" +
                    "maximumFailedPasswordsForWipe=" + maximumFailedPasswordsForWipe +
                    ", passwordExpirationTimeout='" + passwordExpirationTimeout + '\'' +
                    ", passwordHistoryLength=" + passwordHistoryLength +
                    ", passwordMinimumLength=" + passwordMinimumLength +
                    ", passwordMinimumLetters=" + passwordMinimumLetters +
                    ", passwordMinimumLowerCase=" + passwordMinimumLowerCase +
                    ", passwordMinimumNumeric=" + passwordMinimumNumeric +
                    ", passwordMinimumSymbols=" + passwordMinimumSymbols +
                    ", passwordMinimumUpperCase=" + passwordMinimumUpperCase +
                    ", passwordQuality='" + passwordQuality + '\'' +
                    ", requirePasswordUnlock='" + requirePasswordUnlock + '\'' +
                    '}';
        }
    }

    public static class PackageNameList implements Serializable {

        private static final long serialVersionUID = -1819967960845757140L;

        private List<String> packageNames;

        public List<String> getPackageNames() {
            return packageNames;
        }

        public void setPackageNames(List<String> packageNames) {
            this.packageNames = packageNames;
        }

        @Override
        public String toString() {
            return "PackageNameList{" +
                    "packageNames=" + packageNames +
                    '}';
        }
    }


    public static class SystemUpdate implements Serializable {

        private static final long serialVersionUID = 7322621186762678500L;

        private Integer startMinutes;

        private Integer endMinutes;

        private List<FreezePeriod> freezePeriods;

        private String type;

        public Integer getStartMinutes() {
            return startMinutes;
        }

        public void setStartMinutes(Integer startMinutes) {
            this.startMinutes = startMinutes;
        }

        public Integer getEndMinutes() {
            return endMinutes;
        }

        public void setEndMinutes(Integer endMinutes) {
            this.endMinutes = endMinutes;
        }

        public List<FreezePeriod> getFreezePeriods() {
            return freezePeriods;
        }

        public void setFreezePeriods(List<FreezePeriod> freezePeriods) {
            this.freezePeriods = freezePeriods;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        @Override
        public String toString() {
            return "SystemUpdate{" +
                    "startMinutes=" + startMinutes +
                    ", endMinutes=" + endMinutes +
                    ", freezePeriods=" + freezePeriods +
                    ", type='" + type + '\'' +
                    '}';
        }
    }

    public static class FreezePeriod implements Serializable {

        private static final long serialVersionUID = 4186159997107773454L;

        private CustomDate startDate;

        private CustomDate endDate;


        public CustomDate getStartDate() {
            return startDate;
        }

        public void setStartDate(CustomDate startDate) {
            this.startDate = startDate;
        }

        public CustomDate getEndDate() {
            return endDate;
        }

        public void setEndDate(CustomDate endDate) {
            this.endDate = endDate;
        }

        @Override
        public String toString() {
            return "FreezePeriod{" +
                    "startDate=" + startDate +
                    ", endDate=" + endDate +
                    '}';
        }
    }

    public static class CustomDate implements Serializable {

        private static final long serialVersionUID = 6411088519531718018L;

        private Integer month;

        private Integer day;

        public Integer getMonth() {
            return month;
        }

        public void setMonth(Integer month) {
            this.month = month;
        }

        public Integer getDay() {
            return day;
        }

        public void setDay(Integer day) {
            this.day = day;
        }

        @Override
        public String toString() {
            return "CustomDate{" +
                    "month=" + month +
                    ", day=" + day +
                    '}';
        }
    }


    public static class DeviceConnectivityManagement implements Serializable {

        private static final long serialVersionUID = 3640956673001472790L;

        private String tetheringSettings;

        private String usbDataAccess;

        public String getTetheringSettings() {
            return tetheringSettings;
        }

        public void setTetheringSettings(String tetheringSettings) {
            this.tetheringSettings = tetheringSettings;
        }

        public String getUsbDataAccess() {
            return usbDataAccess;
        }

        public void setUsbDataAccess(String usbDataAccess) {
            this.usbDataAccess = usbDataAccess;
        }

        @Override
        public String toString() {
            return "DeviceConnectivityManagement{" +
                    "tetheringSettings='" + tetheringSettings + '\'' +
                    ", usbDataAccess='" + usbDataAccess + '\'' +
                    '}';
        }
    }


    public static class PolicyEnforcementRule implements Serializable {

        private static final long serialVersionUID = 4146725886172999028L;

        private BlockAction blockAction;

        private String settingName;

        private WipeAction wipeAction;

        public BlockAction getBlockAction() {
            return blockAction;
        }

        public void setBlockAction(BlockAction blockAction) {
            this.blockAction = blockAction;
        }

        public String getSettingName() {
            return settingName;
        }

        public void setSettingName(String settingName) {
            this.settingName = settingName;
        }

        public WipeAction getWipeAction() {
            return wipeAction;
        }

        public void setWipeAction(WipeAction wipeAction) {
            this.wipeAction = wipeAction;
        }

        @Override
        public String toString() {
            return "PolicyEnforcementRule{" +
                    "blockAction=" + blockAction +
                    ", settingName='" + settingName + '\'' +
                    ", wipeAction=" + wipeAction +
                    '}';
        }
    }


    public static class BlockAction implements Serializable {

        private static final long serialVersionUID = -1079419829667274233L;

        private Integer blockAfterDays;

        public Integer getBlockAfterDays() {
            return blockAfterDays;
        }

        public void setBlockAfterDays(Integer blockAfterDays) {
            this.blockAfterDays = blockAfterDays;
        }

        @Override
        public String toString() {
            return "BlockAction{" +
                    "blockAfterDays=" + blockAfterDays +
                    '}';
        }
    }

    public static class WipeAction implements Serializable {

        private static final long serialVersionUID = -2972158679910165650L;

        private Boolean preserveFrp;

        private Integer wipeAfterDays;

        public Boolean getPreserveFrp() {
            return preserveFrp;
        }

        public void setPreserveFrp(Boolean preserveFrp) {
            this.preserveFrp = preserveFrp;
        }

        public Integer getWipeAfterDays() {
            return wipeAfterDays;
        }

        public void setWipeAfterDays(Integer wipeAfterDays) {
            this.wipeAfterDays = wipeAfterDays;
        }

        @Override
        public String toString() {
            return "WipeAction{" +
                    "preserveFrp=" + preserveFrp +
                    ", wipeAfterDays=" + wipeAfterDays +
                    '}';
        }
    }



    public static class ApplicationPolicy implements Serializable {

        private static final long serialVersionUID = -3948387274780073519L;

        private String packageName;

        private String autoUpdateMode;

        private String defaultPermissionPolicy;

        private String installPriority;

        private String installType;

        private Boolean lockFlag;

        private List<AppPermissionGrant> permissionGrants;

        public String getPackageName() {
            return packageName;
        }

        public void setPackageName(String packageName) {
            this.packageName = packageName;
        }

        public String getAutoUpdateMode() {
            return autoUpdateMode;
        }

        public void setAutoUpdateMode(String autoUpdateMode) {
            this.autoUpdateMode = autoUpdateMode;
        }

        public String getDefaultPermissionPolicy() {
            return defaultPermissionPolicy;
        }

        public void setDefaultPermissionPolicy(String defaultPermissionPolicy) {
            this.defaultPermissionPolicy = defaultPermissionPolicy;
        }

        public String getInstallPriority() {
            return installPriority;
        }

        public void setInstallPriority(String installPriority) {
            this.installPriority = installPriority;
        }

        public String getInstallType() {
            return installType;
        }

        public void setInstallType(String installType) {
            this.installType = installType;
        }

        public Boolean getLockFlag() {
            return lockFlag;
        }

        public void setLockFlag(Boolean lockFlag) {
            this.lockFlag = lockFlag;
        }

        public List<AppPermissionGrant> getPermissionGrants() {
            return permissionGrants;
        }

        public void setPermissionGrants(List<AppPermissionGrant> permissionGrants) {
            this.permissionGrants = permissionGrants;
        }

        @Override
        public String toString() {
            return "ApplicationPolicy{" +
                    "packageName='" + packageName + '\'' +
                    ", autoUpdateMode='" + autoUpdateMode + '\'' +
                    ", defaultPermissionPolicy='" + defaultPermissionPolicy + '\'' +
                    ", installPriority='" + installPriority + '\'' +
                    ", installType='" + installType + '\'' +
                    ", lockFlag=" + lockFlag +
                    ", permissionGrants=" + permissionGrants +
                    '}';
        }
    }

}
