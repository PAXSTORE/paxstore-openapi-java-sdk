/*
 * *******************************************************************************
 * COPYRIGHT
 *               PAX TECHNOLOGY, Inc. PROPRIETARY INFORMATION
 *   This software is supplied under the terms of a license agreement or
 *   nondisclosure agreement with PAX  Technology, Inc. and may not be copied
 *   or disclosed except in accordance with the terms in that agreement.
 *
 *      Copyright (C) 2026 PAX Technology, Inc. All rights reserved.
 * *******************************************************************************
 */
package com.pax.market.api.sdk.java.api.merchant.validator;

import com.pax.market.api.sdk.java.api.constant.Constants;
import com.pax.market.api.sdk.java.api.validate.Validators;

import java.util.List;

/**
 * Validator for moving a merchant to another reseller.
 */
public class MerchantMoveRequestValidator {

    public static List<String> validate(Long merchantId, String targetResellerName) {
        List<String> validationErrs = Validators.validateId(merchantId, "parameter.id.invalid", "merchantId");
        validationErrs.addAll(Validators.validateStrNullAndMax(targetResellerName, "targetResellerName", Constants.MAX_64));
        return validationErrs;
    }
}
