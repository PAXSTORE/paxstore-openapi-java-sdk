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

package com.pax.market.api.sdk.java.api.terminalApkParameter.validator;

import com.pax.market.api.sdk.java.api.terminalApk.dto.FileParameter;
import com.pax.market.api.sdk.java.api.terminalApkParameter.dto.UpdateApkParameterRequest;
import com.pax.market.api.sdk.java.api.util.FileUtils;
import com.pax.market.api.sdk.java.api.validate.Validators;

import java.util.ArrayList;
import java.util.List;

import static com.pax.market.api.sdk.java.api.util.MessageBundleUtils.getMessage;

/**
 * @author shifan
 * @date 2023/2/23
 */
public class UpdateApkParameterRequestValidator {
    private static final int MAX_FILE_TYPE_PARAMETER_COUNTER = 10;
    private static final int MAX_FILE_TYPE_PARAMETER_SIZE = 500;

    public static List<String> validate(Long apkParameterId, UpdateApkParameterRequest validateTarget) {

        List<String> validationErrs = Validators.validateId(apkParameterId, "parameter.id.invalid", "apkParameterId");
        if (validateTarget == null) {
            validationErrs.add(getMessage("parameter.not.null", "apkParameterCreateRequest"));
        } else {
            if (validateTarget.getBase64FileParameters() != null && !validateTarget.getBase64FileParameters().isEmpty()) {
                if (validateTarget.getBase64FileParameters().size() > MAX_FILE_TYPE_PARAMETER_COUNTER) {
                    validationErrs.add(getMessage("parametersBase64FileParameters.over.maxCounter"));
                }
                for (FileParameter fileParameter : validateTarget.getBase64FileParameters()) {
                    if (FileUtils.getBase64FileSizeKB(fileParameter.getFileData()) > MAX_FILE_TYPE_PARAMETER_SIZE) {
                        validationErrs.add(getMessage("parametersBase64FileParameters.over.maxSize"));
                        break;
                    }
                }
            }

        }

        return validationErrs;
    }
}