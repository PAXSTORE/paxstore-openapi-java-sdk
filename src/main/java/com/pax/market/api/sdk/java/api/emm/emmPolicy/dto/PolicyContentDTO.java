package com.pax.market.api.sdk.java.api.emm.emmPolicy.dto;

import java.io.Serializable;
import java.util.List;

public class PolicyContentDTO implements Serializable {

    private static final long serialVersionUID = 6989867119408868680L;

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

    private Boolean disableNotificationsOnLockScreens;

    private Boolean disableUnredactedNotificationsOnLockScreens;

    private Boolean ignoreTrustAgentStateOnLockScreens;

    private Boolean disableFingerprintSensorOnLockScreens;

    private Boolean disableFaceAuthentication;

    private Boolean disableAllBiometricAuthentication;

    private Boolean disableAllShortcutsOnLockScreen;

    private Boolean disableAllKeyguardFeatures;

    private Boolean kioskCustomLauncherEnabled;

    private KioskCustomization kioskCustomization;

    private Boolean enableRemoteControl;

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

    private List<String> permittedAccessibilityServices;

    private List<String> permittedInputMethods;

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

    public Boolean getDisableNotificationsOnLockScreens() {
        return disableNotificationsOnLockScreens;
    }

    public void setDisableNotificationsOnLockScreens(Boolean disableNotificationsOnLockScreens) {
        this.disableNotificationsOnLockScreens = disableNotificationsOnLockScreens;
    }

    public Boolean getDisableUnredactedNotificationsOnLockScreens() {
        return disableUnredactedNotificationsOnLockScreens;
    }

    public void setDisableUnredactedNotificationsOnLockScreens(Boolean disableUnredactedNotificationsOnLockScreens) {
        this.disableUnredactedNotificationsOnLockScreens = disableUnredactedNotificationsOnLockScreens;
    }

    public Boolean getIgnoreTrustAgentStateOnLockScreens() {
        return ignoreTrustAgentStateOnLockScreens;
    }

    public void setIgnoreTrustAgentStateOnLockScreens(Boolean ignoreTrustAgentStateOnLockScreens) {
        this.ignoreTrustAgentStateOnLockScreens = ignoreTrustAgentStateOnLockScreens;
    }

    public Boolean getDisableFingerprintSensorOnLockScreens() {
        return disableFingerprintSensorOnLockScreens;
    }

    public void setDisableFingerprintSensorOnLockScreens(Boolean disableFingerprintSensorOnLockScreens) {
        this.disableFingerprintSensorOnLockScreens = disableFingerprintSensorOnLockScreens;
    }

    public Boolean getDisableFaceAuthentication() {
        return disableFaceAuthentication;
    }

    public void setDisableFaceAuthentication(Boolean disableFaceAuthentication) {
        this.disableFaceAuthentication = disableFaceAuthentication;
    }

    public Boolean getDisableAllBiometricAuthentication() {
        return disableAllBiometricAuthentication;
    }

    public void setDisableAllBiometricAuthentication(Boolean disableAllBiometricAuthentication) {
        this.disableAllBiometricAuthentication = disableAllBiometricAuthentication;
    }

    public Boolean getDisableAllShortcutsOnLockScreen() {
        return disableAllShortcutsOnLockScreen;
    }

    public void setDisableAllShortcutsOnLockScreen(Boolean disableAllShortcutsOnLockScreen) {
        this.disableAllShortcutsOnLockScreen = disableAllShortcutsOnLockScreen;
    }

    public Boolean getDisableAllKeyguardFeatures() {
        return disableAllKeyguardFeatures;
    }

    public void setDisableAllKeyguardFeatures(Boolean disableAllKeyguardFeatures) {
        this.disableAllKeyguardFeatures = disableAllKeyguardFeatures;
    }

    public Boolean getKioskCustomLauncherEnabled() {
        return kioskCustomLauncherEnabled;
    }

    public void setKioskCustomLauncherEnabled(Boolean kioskCustomLauncherEnabled) {
        this.kioskCustomLauncherEnabled = kioskCustomLauncherEnabled;
    }

    public KioskCustomization getKioskCustomization() {
        return kioskCustomization;
    }

    public void setKioskCustomization(KioskCustomization kioskCustomization) {
        this.kioskCustomization = kioskCustomization;
    }

    public Boolean getEnableRemoteControl() {
        return enableRemoteControl;
    }

    public void setEnableRemoteControl(Boolean enableRemoteControl) {
        this.enableRemoteControl = enableRemoteControl;
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

    public List<String> getPermittedAccessibilityServices() {
        return permittedAccessibilityServices;
    }

    public void setPermittedAccessibilityServices(List<String> permittedAccessibilityServices) {
        this.permittedAccessibilityServices = permittedAccessibilityServices;
    }

    public List<String> getPermittedInputMethods() {
        return permittedInputMethods;
    }

    public void setPermittedInputMethods(List<String> permittedInputMethods) {
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
        return "PolicyContentDTO{" +
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
                ", disableNotificationsOnLockScreens=" + disableNotificationsOnLockScreens +
                ", disableUnredactedNotificationsOnLockScreens=" + disableUnredactedNotificationsOnLockScreens +
                ", ignoreTrustAgentStateOnLockScreens=" + ignoreTrustAgentStateOnLockScreens +
                ", disableFingerprintSensorOnLockScreens=" + disableFingerprintSensorOnLockScreens +
                ", disableFaceAuthentication=" + disableFaceAuthentication +
                ", disableAllBiometricAuthentication=" + disableAllBiometricAuthentication +
                ", disableAllShortcutsOnLockScreen=" + disableAllShortcutsOnLockScreen +
                ", disableAllKeyguardFeatures=" + disableAllKeyguardFeatures +
                ", kioskCustomLauncherEnabled=" + kioskCustomLauncherEnabled +
                ", kioskCustomization=" + kioskCustomization +
                ", enableRemoteControl=" + enableRemoteControl +
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

        private static final long serialVersionUID = -7848700628885488058L;

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

        private static final long serialVersionUID = 7848357866391659487L;

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

        private static final long serialVersionUID = -3627745883128566908L;

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

        private static final long serialVersionUID = -9016662889474222431L;

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

        private static final long serialVersionUID = 5472365662982434515L;

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

        private static final long serialVersionUID = -696415712231841346L;

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

    public static class KioskCustomization implements Serializable {

        private static final long serialVersionUID = 4312678361728332944L;

        private String deviceSettings;

        private String powerButtonActions;

        private String statusBar;

        private String systemErrorWarnings;

        private String systemNavigation;

        public String getDeviceSettings() {
            return deviceSettings;
        }

        public void setDeviceSettings(String deviceSettings) {
            this.deviceSettings = deviceSettings;
        }

        public String getPowerButtonActions() {
            return powerButtonActions;
        }

        public void setPowerButtonActions(String powerButtonActions) {
            this.powerButtonActions = powerButtonActions;
        }

        public String getStatusBar() {
            return statusBar;
        }

        public void setStatusBar(String statusBar) {
            this.statusBar = statusBar;
        }

        public String getSystemErrorWarnings() {
            return systemErrorWarnings;
        }

        public void setSystemErrorWarnings(String systemErrorWarnings) {
            this.systemErrorWarnings = systemErrorWarnings;
        }

        public String getSystemNavigation() {
            return systemNavigation;
        }

        public void setSystemNavigation(String systemNavigation) {
            this.systemNavigation = systemNavigation;
        }

        @Override
        public String toString() {
            return "KioskCustomization{" +
                    "deviceSettings='" + deviceSettings + '\'' +
                    ", powerButtonActions='" + powerButtonActions + '\'' +
                    ", statusBar='" + statusBar + '\'' +
                    ", systemErrorWarnings='" + systemErrorWarnings + '\'' +
                    ", systemNavigation='" + systemNavigation + '\'' +
                    '}';
        }
    }


    public static class PasswordRequirements implements Serializable {

        private static final long serialVersionUID = 6567298730211821211L;

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


    public static class SystemUpdate implements Serializable {

        private static final long serialVersionUID = -3542880474870490645L;

        private String startToEndMinutes;

        private List<FreezePeriod> freezePeriods;

        private String type;

        public String getStartToEndMinutes() {
            return startToEndMinutes;
        }

        public void setStartToEndMinutes(String startToEndMinutes) {
            this.startToEndMinutes = startToEndMinutes;
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
                    "startToEndMinutes='" + startToEndMinutes + '\'' +
                    ", freezePeriods=" + freezePeriods +
                    ", type='" + type + '\'' +
                    '}';
        }
    }

    public static class FreezePeriod implements Serializable {

        private static final long serialVersionUID = 3684597986952827948L;

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

        private static final long serialVersionUID = 2625224063258614677L;

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

        private static final long serialVersionUID = 3764506059876202280L;

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

        private static final long serialVersionUID = -6462113646137744568L;

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

        private static final long serialVersionUID = 8167833491256686492L;

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

        private static final long serialVersionUID = 2896116078013285323L;

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

        private static final long serialVersionUID = -3749713233937660568L;

        private Long appId;

        private String packageName;

        private String autoUpdateMode;

        private String defaultPermissionPolicy;

        private String installPriority;

        private Long accessibleTrackId;

        private String installType;

        private List<AppPermissionGrant> permissionGrants;

        public Long getAppId() {
            return appId;
        }

        public void setAppId(Long appId) {
            this.appId = appId;
        }

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

        public Long getAccessibleTrackId() {
            return accessibleTrackId;
        }

        public void setAccessibleTrackId(Long accessibleTrackId) {
            this.accessibleTrackId = accessibleTrackId;
        }

        public String getInstallType() {
            return installType;
        }

        public void setInstallType(String installType) {
            this.installType = installType;
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
                    "appId=" + appId +
                    ", packageName='" + packageName + '\'' +
                    ", autoUpdateMode='" + autoUpdateMode + '\'' +
                    ", defaultPermissionPolicy='" + defaultPermissionPolicy + '\'' +
                    ", installPriority='" + installPriority + '\'' +
                    ", accessibleTrackId=" + accessibleTrackId +
                    ", installType='" + installType + '\'' +
                    ", permissionGrants=" + permissionGrants +
                    '}';
        }
    }

}
