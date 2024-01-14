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
package com.pax.market.api.sdk.java.api.constant;

/**
 * The type Result code.
 */
public interface ResultCode {
    /**
     * The constant SUCCESS.
     */
    int SUCCESS = 0;
    /**
     * The constant SDK_PARAM_ERROR.
     */
    int SDK_PARAM_ERROR = 16100;
    /**
     * The constant SDK_UNINIT.
     */
    int SDK_UNINIT = 16101;
    /**
     * The constant SDK_DEC_ERROR.
     */
    int SDK_DEC_ERROR = 16102;
    /**
     * The constant SDK_JSON_ERROR.
     */
    int SDK_JSON_ERROR = 16103;
    /**
     * The constant SDK_CONNECT_TIMEOUT.
     */
    int SDK_CONNECT_TIMEOUT = 16104;
    /**
     * The constant SDK_UN_CONNECT.
     */
    int SDK_UN_CONNECT = 16105;
    /**
     * The constant SDK_RQUEST_EXCEPTION.
     */
    int SDK_RQUEST_EXCEPTION = 16106;

    /**
     * The constant SDK_UNZIP_FAILED.
     */
    int SDK_UNZIP_FAILED = 16107;
    /**
     * The constant SDK_MD_FAILED.
     */
    int SDK_MD_FAILED = 16108;
    /**
     * The constant SDK_REPLACE_VARIABLES_FAILED.
     */
    int SDK_REPLACE_VARIABLES_FAILED = 16109;
    /**
     * The constant SDK_INIT_FAILED.
     */
    int SDK_INIT_FAILED = 16110;
    /**
     * The constant FILE_NOT_FOUND.
     */
    int SDK_FILE_NOT_FOUND = 16111;

    int BAD_GATEWAY = 16112;
    int GATEWAY_TIMEOUT = 16113;
}