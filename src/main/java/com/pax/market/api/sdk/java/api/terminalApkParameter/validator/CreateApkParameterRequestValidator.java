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
import com.pax.market.api.sdk.java.api.terminalApkParameter.dto.CreateApkParameterRequest;
import com.pax.market.api.sdk.java.api.util.FileUtils;
import com.pax.market.api.sdk.java.api.validate.Validators;

import java.util.ArrayList;
import java.util.List;

import static com.pax.market.api.sdk.java.api.util.MessageBundleUtils.getMessage;

/**
 * @author shifan
 * @date 2023/2/23
 */
public class CreateApkParameterRequestValidator {
    private static final int MAX_FILE_TYPE_PARAMETER_COUNTER = 10;
    private static final int MAX_FILE_TYPE_PARAMETER_SIZE = 500;
    public static List<String> validate(CreateApkParameterRequest validateTarget) {

        List<String> validationErrs = new ArrayList<>();
        if (validateTarget == null){
            validationErrs.add(getMessage("parameter.not.null", "apkParameterCreateRequest"));
        }else {

            if(validateTarget.getParameters() == null && validateTarget.getBase64FileParameters()==null) {
                validationErrs.add(getMessage("parametersBase64FileParameters.not.null.atsame"));
            }
            if(validateTarget.getBase64FileParameters() != null && !validateTarget.getBase64FileParameters().isEmpty()) {
                if(validateTarget.getBase64FileParameters().size()>MAX_FILE_TYPE_PARAMETER_COUNTER) {
                    validationErrs.add(getMessage("parametersBase64FileParameters.over.maxCounter"));
                }
                for(FileParameter fileParameter: validateTarget.getBase64FileParameters()) {
                    if(FileUtils.getBase64FileSizeKB(fileParameter.getFileData()) > MAX_FILE_TYPE_PARAMETER_SIZE) {
                        validationErrs.add(getMessage("parametersBase64FileParameters.over.maxSize"));
                        break;
                    }
                }
            }

            validationErrs.addAll(Validators.validateObject(validateTarget.getPackageName(),"packageName"));
            validationErrs.addAll(Validators.validateObject(validateTarget.getVersion(),"version"));
            validationErrs.addAll(Validators.validateObject(validateTarget.getName(),"name"));
            validationErrs.addAll(Validators.validateObject(validateTarget.getParamTemplateName(),"paramTemplateName"));

        }

        return validationErrs;
    }

}