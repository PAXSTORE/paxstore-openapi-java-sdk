package com.pax.market.api.sdk.java.api.terminalRki.dto;

import org.apache.commons.lang3.time.DateFormatUtils;

import java.util.Date;

/**
 * The type Push rki 2 terminal request.
 *
 * @author liukai
 * {@code @date} 2020 /2/7
 */
public class PushRki2TerminalRequest extends PushRkiBasicRequest {

    private static final long serialVersionUID = 1L;

    private String effectiveTime;
    private String expiredTime;

    /**
     * Gets effective time.
     *
     * @return the effective time
     */
    public String getEffectiveTime() {
        return effectiveTime;
    }

    /**
     * Sets effective time.
     *
     * @param effectiveTime the effective time
     */
    public void setEffectiveTime(Date effectiveTime) {
        if(effectiveTime != null) {
            this.effectiveTime = DateFormatUtils.ISO_8601_EXTENDED_DATETIME_TIME_ZONE_FORMAT.format(effectiveTime);
        }
    }

    /**
     * Gets expired time.
     *
     * @return the expired time
     */
    public String getExpiredTime() {
        return expiredTime;
    }

    /**
     * Sets expired time.
     *
     * @param expiredTime the expired time
     */
    public void setExpiredTime(Date expiredTime) {
        if(expiredTime != null) {
            this.expiredTime = DateFormatUtils.ISO_8601_EXTENDED_DATETIME_TIME_ZONE_FORMAT.format(expiredTime);
        }
    }
}
