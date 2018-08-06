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

import java.util.Locale;
import java.util.ResourceBundle;

import org.hibernate.validator.resourceloading.PlatformResourceBundleLocator;

/**
 *
 * @author tanjie
 * @date 2018-07-04
 */
public class MessageBoudleUtil {
	public static PlatformResourceBundleLocator prbl=new PlatformResourceBundleLocator( "ValidationMessages" );
	
	public static String getMessage(String key) {
		ResourceBundle rb=prbl.getResourceBundle(Locale.ENGLISH);
		return rb.getString(key);
	}
	
	public static String getMessage(String key, Locale locale) {
		ResourceBundle rb=prbl.getResourceBundle(locale);
		return rb.getString(key);
	}

}
