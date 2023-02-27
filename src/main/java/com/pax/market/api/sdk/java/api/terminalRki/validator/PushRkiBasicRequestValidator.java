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

package com.pax.market.api.sdk.java.api.terminalRki.validator;

import com.pax.market.api.sdk.java.api.terminalRki.dto.PushRkiBasicRequest;
import com.pax.market.api.sdk.java.api.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

import static com.pax.market.api.sdk.java.api.util.MessageBundleUtils.getMessage;

/**
 * @author shifan
 * @date 2023/2/22
 */
public class PushRkiBasicRequestValidator {

    public static List<String> validate(PushRkiBasicRequest validateTarget, String paramName) {
        List<String> validationErrs = new ArrayList<>();

        if(validateTarget == null) {
            validationErrs.add(getMessage("parameter.not.null", paramName));
        }else {
            if (StringUtils.isBlank(validateTarget.getRkiKey())){
                validationErrs.add(getMessage("parameter.not.null", "rkiKey"));
            }
            if(StringUtils.isEmpty(validateTarget.getSerialNo()) && StringUtils.isEmpty(validateTarget.getTid())) {
                validationErrs.add(getMessage("parameter.sn.tid.empty"));
            }
        }

        return validationErrs;
    }
}