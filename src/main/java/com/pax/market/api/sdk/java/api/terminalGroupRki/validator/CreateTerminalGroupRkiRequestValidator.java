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

package com.pax.market.api.sdk.java.api.terminalGroupRki.validator;

import com.pax.market.api.sdk.java.api.terminalGroupRki.dto.CreateTerminalGroupRkiRequest;
import com.pax.market.api.sdk.java.api.validate.Validators;

import java.util.ArrayList;
import java.util.List;

import static com.pax.market.api.sdk.java.api.util.MessageBundleUtils.getMessage;

/**
 * @author shifan
 * @date 2023/2/23
 */
public class CreateTerminalGroupRkiRequestValidator {
    public static List<String> validate(CreateTerminalGroupRkiRequest validateTarget) {

        List<String> validationErrs = new ArrayList<>();
        if (validateTarget == null){
            validationErrs.add(getMessage("parameter.not.null", "groupPushRkiRequest"));
        }else {
            validationErrs.addAll(Validators.validateId(validateTarget.getGroupId(),"parameter.id.invalid", "terminalGroupId" ));
            validationErrs.addAll(Validators.validateObject(validateTarget.getRkiKey(), "rkiKey"));

        }
        return validationErrs;

    }
}