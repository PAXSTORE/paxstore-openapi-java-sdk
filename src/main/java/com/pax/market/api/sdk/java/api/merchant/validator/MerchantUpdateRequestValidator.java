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

package com.pax.market.api.sdk.java.api.merchant.validator;

import com.pax.market.api.sdk.java.api.constant.Constants;
import com.pax.market.api.sdk.java.api.merchant.dto.MerchantUpdateRequest;
import com.pax.market.api.sdk.java.api.util.StringUtils;
import com.pax.market.api.sdk.java.api.validate.Validators;

import java.util.ArrayList;
import java.util.List;

import static com.pax.market.api.sdk.java.api.util.MessageBundleUtils.getMessage;

/**
 * @author shifan
 * @date 2023/2/22
 */
public class MerchantUpdateRequestValidator {

    public static List<String> validate(Long merchantId, MerchantUpdateRequest validateTarget) {
        List<String> validationErrs = new ArrayList<>();

        if (merchantId == null || merchantId < 0L) {
            validationErrs.add(getMessage("parameter.id.invalid", "merchantId"));
        }
        if(validateTarget == null) {
            validationErrs.add(getMessage("parameter.not.null", "merchantUpdateRequest"));
        }else {
            validationErrs.addAll(Validators.validateStrNullAndMax(validateTarget.getName(), "name", Constants.MAX_64));
            validationErrs.addAll(Validators.validateStrMax(validateTarget.getEmail(), "email", Constants.MAX_255));
            if(!StringUtils.isValidEmailAddress(validateTarget.getEmail())) {
                validationErrs.add(getMessage("parameter.email.invalid"));
            }
            validationErrs.addAll(Validators.validateStrNullAndMax(validateTarget.getResellerName(), "resellerName", Constants.MAX_64));
            validationErrs.addAll(Validators.validateStrMax(validateTarget.getContact(), "contact", Constants.MAX_64));
            validationErrs.addAll(Validators.validateStrMax(validateTarget.getCountry(), "country", Constants.MAX_64));
            validationErrs.addAll(Validators.validateStrMax(validateTarget.getPhone(), "phone", Constants.MAX_32));
            validationErrs.addAll(Validators.validateStrMax(validateTarget.getProvince(), "province", Constants.MAX_64));
            validationErrs.addAll(Validators.validateStrMax(validateTarget.getCity(), "city", Constants.MAX_255));
            validationErrs.addAll(Validators.validateStrMax(validateTarget.getPostcode(), "postcode", Constants.MAX_16));
            validationErrs.addAll(Validators.validateStrMax(validateTarget.getAddress(), "address", Constants.MAX_255));
            validationErrs.addAll(Validators.validateStrMax(validateTarget.getDescription(), "description", Constants.MAX_3000));

        }
        return validationErrs;
    }

}