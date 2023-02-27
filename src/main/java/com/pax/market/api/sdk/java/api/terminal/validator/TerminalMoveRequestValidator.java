/*
 * *******************************************************************************
 * COPYRIGHT
 *               PAX TECHNOLOGY, Inc. PROPRIETARY INFORMATION
 *   This software is supplied under the terms of a license agreement or
 *   nondisclosure agreement with PAX  Technology, Inc. and may not be copied
 *   or disclosed except in accordance with the terms in that agreement.
 *
 *      Copyright (C) 2023 PAX Technology, Inc. All rights reserved.
 * *******************************************************************************
 */

package com.pax.market.api.sdk.java.api.terminal.validator;

import com.pax.market.api.sdk.java.api.constant.Constants;
import com.pax.market.api.sdk.java.api.validate.Validators;

import java.util.List;


/**
 * @author shifan
 * @date 2023/2/23
 */
public class TerminalMoveRequestValidator {

    public static List<String> validate(Long terminalId, String resellerName, String merchantName) {

        List<String> validationErrs = Validators.validateId(terminalId, "parameter.id.invalid","terminalId");
        validationErrs.addAll(Validators.validateStrNullAndMax(resellerName, "resellerName", Constants.MAX_64));
        validationErrs.addAll(Validators.validateStrNullAndMax(merchantName, "merchantName", Constants.MAX_64));

        return validationErrs;
    }
}