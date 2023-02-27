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

package com.pax.market.api.sdk.java.api.terminalFirmware.validator;

import com.pax.market.api.sdk.java.api.terminalFirmware.dto.PushFirmwareTaskBasicRequest;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

import static com.pax.market.api.sdk.java.api.util.MessageBundleUtils.getMessage;

/**
 * @author shifan
 * @date 2023/2/23
 */
public class PushFirmwareTaskBasicRequestValidator {

    public static List<String> validate(PushFirmwareTaskBasicRequest validateTarget, String paramName) {

        List<String> validationErrs = new ArrayList<>();

        if(validateTarget == null) {
            validationErrs.add(getMessage("parameter.not.null", paramName));
        }else {
            if (StringUtils.isBlank(validateTarget.getFmName())){
                validationErrs.add(getMessage("parameter.not.null", "fmName"));
            }
            if(StringUtils.isEmpty(validateTarget.getSerialNo()) &&StringUtils.isEmpty(validateTarget.getTid())) {
                validationErrs.add(getMessage("parameter.sn.tid.empty"));
            }
        }
        return validationErrs;
    }
}