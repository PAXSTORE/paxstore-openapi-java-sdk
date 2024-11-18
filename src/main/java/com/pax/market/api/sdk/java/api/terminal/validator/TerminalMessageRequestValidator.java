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
import com.pax.market.api.sdk.java.api.terminal.dto.TerminalMessageRequest;
import com.pax.market.api.sdk.java.api.validate.Validators;

import java.util.ArrayList;
import java.util.List;

import static com.pax.market.api.sdk.java.api.util.MessageBundleUtils.getMessage;

/**
 * @author shifan
 * @date 2023/2/22
 */
public class TerminalMessageRequestValidator {

    public static List<String> validate(TerminalMessageRequest validateTarget, String paramName) {
        List<String> validationErrs = new ArrayList<>();
        if (validateTarget == null){
            validationErrs.add(getMessage("parameter.not.null", paramName));
        }else {
            validationErrs.addAll(Validators.validateStrNullAndMax(validateTarget.getTitle(), "title", Constants.MAX_64));
            validationErrs.addAll(Validators.validateStrNullAndMax(validateTarget.getContent(), "content", Constants.MAX_256));
        }
        return validationErrs;
    }
}