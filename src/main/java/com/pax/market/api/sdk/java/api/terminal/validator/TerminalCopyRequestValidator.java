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
import com.pax.market.api.sdk.java.api.terminal.dto.TerminalCopyRequest;
import com.pax.market.api.sdk.java.api.util.StringUtils;
import com.pax.market.api.sdk.java.api.validate.Validators;

import java.util.ArrayList;
import java.util.List;

import static com.pax.market.api.sdk.java.api.util.MessageBundleUtils.getMessage;

/**
 * @author shifan
 * @date 2023/2/22
 */
public class TerminalCopyRequestValidator {

    public static List<String> validate(TerminalCopyRequest validateTarget) {
        List<String> validationErrs = new ArrayList<>();
        if (validateTarget == null){
            validationErrs.add(getMessage("parameter.not.null", "terminalCopyRequest"));
        }else {
            validationErrs.addAll(Validators.validateLongNull(validateTarget.getTerminalId(), "terminalId"));
            validationErrs.addAll(Validators.validateStrNullAndMax(validateTarget.getName(), "name", Constants.MAX_64));
            validationErrs.addAll(Validators.validateRange(validateTarget.getTid(), "tid", Constants.MIN_8, Constants.MAX_16));
            validationErrs.addAll(Validators.validateStrMax(validateTarget.getSerialNo(), "serialNo", Constants.MAX_32));
            if (StringUtils.isBlank(validateTarget.getStatus())){
                validationErrs.add(getMessage("parameter.not.null","status"));
            }

        }
        return validationErrs;
    }
}