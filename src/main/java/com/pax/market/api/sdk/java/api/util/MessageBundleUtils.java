/*
 * *******************************************************************************
 * COPYRIGHT
 *               PAX TECHNOLOGY, Inc. PROPRIETARY INFORMATION
 *   This software is supplied under the terms of a license agreement or
 *   nondisclosure agreement with PAX  Technology, Inc. and may not be copied
 *   or disclosed except in accordance with the terms in that agreement.
 *
 *      Copyright (C) 2017 PAX Technology, Inc. All rights reserved.
 * *******************************************************************************
 */
package com.pax.market.api.sdk.java.api.util;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Locale;
import java.util.ResourceBundle;

import com.pax.market.api.sdk.java.api.constant.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.commons.lang3.StringUtils;
/**
 * message utils
 * @author tanjie
 * @date 2018-07-04
 */
public class MessageBundleUtils {
    private static final String BASE_NAME = "messages";
    private static final Logger logger = LoggerFactory.getLogger(MessageBundleUtils.class);


    public static String getMessage(String key) {
        return getMessage(key, Locale.getDefault(), null);
    }

    public static String getMessage(String key, Object... args) {
        return getMessage(key, Locale.getDefault(), args);
    }

    public static String getMessage(String key, Locale locale, Object[] args) {
        if (locale == null) {
            locale = Locale.getDefault();
        }
        ResourceBundle rb = ResourceBundle.getBundle(BASE_NAME, locale);
        try {
            return loadArgs(rb.getString(key),  args);
        } catch (Exception mre) {
            logger.warn(mre.getMessage());  // exception is logged
            return key;
        }
    }


    private static String loadArgs(String message, Object[] args){
        if (StringUtils.isNotBlank(message) && args != null){
            return  MessageFormat.format(message, args);
        }
        return message;
    }

    public static void main(String[] args) {

        System.out.println(getMessage("parameter.range.length", "parentResellerName", Constants.MIN_0,  Constants.MAX_64));
    }
}
