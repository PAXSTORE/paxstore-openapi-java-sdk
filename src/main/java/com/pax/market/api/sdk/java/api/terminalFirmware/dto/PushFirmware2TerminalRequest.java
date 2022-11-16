package com.pax.market.api.sdk.java.api.terminalFirmware.dto;

import java.util.Date;

/**
 * The type Push firmware 2 terminal request.
 *
 * @author liukai
 * {@code @date} 2019 /5/23
 */
public class PushFirmware2TerminalRequest extends PushFirmwareTaskBasicRequest {

    private static final long serialVersionUID = 1L;

    private boolean wifiOnly;
    private Date effectiveTime;
    private Date expiredTime;

    /**
     * Whether to download over Wi-Fi or Cable network only, don’t allow to download over the cellular network
     *
     * @return the boolean
     */
    public boolean isWifiOnly() {
        return wifiOnly;
    }

    /**
     * Sets wifi only.
     *
     * @param wifiOnly the wifi only
     */
    public void setWifiOnly(boolean wifiOnly) {
        this.wifiOnly = wifiOnly;
    }

    /**
     * Gets effective time.
     *
     * @return the effective time
     */
    public Date getEffectiveTime() {
        return effectiveTime;
    }

    /**
     * Sets effective time.
     *
     * @param effectiveTime the effective time
     */
    public void setEffectiveTime(Date effectiveTime) {
        this.effectiveTime = effectiveTime;
    }

    /**
     * Gets expired time.
     *
     * @return the expired time
     */
    public Date getExpiredTime() {
        return expiredTime;
    }

    /**
     * Sets expired time.
     *
     * @param expiredTime the expired time
     */
    public void setExpiredTime(Date expiredTime) {
        this.expiredTime = expiredTime;
    }
}
