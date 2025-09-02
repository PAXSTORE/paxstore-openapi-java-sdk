/*
 * ******************************************************************************
 * COPYRIGHT
 *               PAX TECHNOLOGY, Inc. PROPRIETARY INFORMATION
 *   This software is supplied under the terms of a license agreement or
 *   nondisclosure agreement with PAX  Technology, Inc. and may not be copied
 *   or disclosed except in accordance with the terms in that agreement.
 *
 *      Copyright (C) 2017 PAX Technology, Inc. All rights reserved.
 * ******************************************************************************
 */

package com.pax.market.api.sdk.java.api.entityAttribute.dto;

import java.io.Serializable;

/**
 * The type Entity attribute label info.
 */
public class EntityAttributeLabelInfo implements Serializable {

    private static final long serialVersionUID = -1L;
    private String locale;
    private String label;



    /**
     * Gets locale.
     *
     * @return the locale
     */
    public String getLocale() {
        return locale;
    }


    /**
     * Sets locale.
     *
     * @param locale the locale
     */
    public void setLocale(String locale) {
        this.locale = locale;
    }


    /**
     * Gets value.
     *
     * @return the value
     */
    public String getLabel() {
        return label;
    }


    /**
     * Sets value.
     *
     * @param label the value
     */
    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return "EntityAttributeLabelInfo {" +
                "locale='" + locale + '\'' +
                ", label='" + label + '\'' +
                '}';
    }
}
