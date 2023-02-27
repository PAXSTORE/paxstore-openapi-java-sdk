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

import com.pax.market.api.sdk.java.api.merchant.category.dto.MerchantCategoryCreateRequest;
import com.pax.market.api.sdk.java.api.util.StringUtils;
import com.pax.market.api.sdk.java.api.validate.Validators;

import java.util.ArrayList;
import java.util.List;

import static com.pax.market.api.sdk.java.api.util.MessageBundleUtils.getMessage;

/**
 * @author shifan
 * @date 2023/2/27
 */
public class MerchantCategoryBatchCreateRequestValidator {
    private static final int MAX_LENGTH_CATEGORY_NAME = 128;
    private static final int MAX_LENGTH_CATEGORY_REMARKS = 255;

    public static List<String> validate(List<MerchantCategoryCreateRequest> batchCreateRequest) {
        List<String> validationErrs = new ArrayList<>();
        if (batchCreateRequest == null || batchCreateRequest.isEmpty()) {
            validationErrs.add(getMessage("parameter.not.null", "merchantCategoryBatchCreateRequest"));
        } else {
            for (MerchantCategoryCreateRequest category : batchCreateRequest) {
                if (StringUtils.isEmpty(category.getName())) {
                    validationErrs.add(getMessage("merchantCategory.name.null"));
                }
                if (category.getName() != null && category.getName().length() > MAX_LENGTH_CATEGORY_NAME) {
                    validationErrs.add(getMessage("parameter.too.long", "merchant category name", MAX_LENGTH_CATEGORY_NAME));
                }
                if (category.getRemarks() != null && category.getRemarks().length() > MAX_LENGTH_CATEGORY_REMARKS) {
                    validationErrs.add(getMessage("parameter.too.long", "merchant category remarks", MAX_LENGTH_CATEGORY_REMARKS));
                }
            }
        }

        return validationErrs;
    }
}