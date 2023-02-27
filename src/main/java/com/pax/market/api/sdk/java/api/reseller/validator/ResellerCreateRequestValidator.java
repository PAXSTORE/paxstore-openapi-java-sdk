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

package com.pax.market.api.sdk.java.api.reseller.validator;

import com.pax.market.api.sdk.java.api.constant.Constants;
import com.pax.market.api.sdk.java.api.reseller.dto.ResellerCreateRequest;
import com.pax.market.api.sdk.java.api.util.MessageBundleUtils;
import com.pax.market.api.sdk.java.api.util.StringUtils;
import com.pax.market.api.sdk.java.api.validate.Validators;

import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author shifan
 * @date 2023/2/16
 */
public class ResellerCreateRequestValidator {


    public static List<String> validate(ResellerCreateRequest validateTarget) {
        List<String> validationErrs = new ArrayList<>();
        if (validateTarget == null){
            validationErrs.addAll(Validators.validateObject(validateTarget, "resellerCreateRequest"));
        }else {
            validationErrs.addAll(Validators.validateRange(validateTarget.getParentResellerName(), "parentResellerName", Constants.MIN_0, Constants.MAX_64));

            validationErrs.addAll(Validators.validateStrNullAndMax(validateTarget.getName(), "email", Constants.MAX_255));

            if(!StringUtils.isValidEmailAddress(validateTarget.getEmail())) {
                validationErrs.add(MessageBundleUtils.getMessage("parameter.email.invalid"));
            }

            validationErrs.addAll(Validators.validateStrNullAndMax(validateTarget.getName(), "name", Constants.MAX_64));
            validationErrs.addAll(Validators.validateStrNullAndMax(validateTarget.getCountry(), "country", Constants.MAX_64));
            validationErrs.addAll(Validators.validateStrNullAndMax(validateTarget.getContact(), "concat", Constants.MAX_64));
            validationErrs.addAll(Validators.validateStrNullAndMax(validateTarget.getPhone(), "phone", Constants.MAX_32));
            validationErrs.addAll(Validators.validateStrMax(validateTarget.getPostcode(), "postcode", Constants.MAX_16));
            validationErrs.addAll(Validators.validateStrMax(validateTarget.getAddress(), "address", Constants.MAX_255));
            validationErrs.addAll(Validators.validateStrMax(validateTarget.getCompany(), "company", Constants.MAX_255));
        }

        return validationErrs;


    }




}