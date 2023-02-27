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

package com.pax.market.api.sdk.java.api.merchant.category.validator;

import com.pax.market.api.sdk.java.api.constant.Constants;
import com.pax.market.api.sdk.java.api.merchant.category.dto.MerchantCategoryCreateRequest;
import com.pax.market.api.sdk.java.api.validate.Validators;

import java.util.ArrayList;
import java.util.List;

import static com.pax.market.api.sdk.java.api.util.MessageBundleUtils.getMessage;

/**
 * @author shifan
 * @date 2023/2/27
 */
public class MerchantCategoryCreateRequestValidator {

    public static List<String> validate(MerchantCategoryCreateRequest validateTarget) {
        List<String> validationErrs =new ArrayList<>();
        if (validateTarget == null) {
            validationErrs.add(getMessage("parameter.not.null", "merchantCategoryCreateRequest"));
        } else {
            validationErrs.addAll(Validators.validateStrNullAndMax(validateTarget.getName(), "name", Constants.MAX_128));
            validationErrs.addAll(Validators.validateStrMax(validateTarget.getRemarks(), "remarks", Constants.MAX_255));

        }
        return validationErrs;
    }
}