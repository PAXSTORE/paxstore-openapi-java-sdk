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

package com.pax.market.api.sdk.java.api.terminalApk.validator;

import com.pax.market.api.sdk.java.api.constant.Constants;
import com.pax.market.api.sdk.java.api.terminalApk.dto.CreateTerminalApkRequest;
import com.pax.market.api.sdk.java.api.terminalApk.dto.FileParameter;
import com.pax.market.api.sdk.java.api.util.FileUtils;
import com.pax.market.api.sdk.java.api.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

import static com.pax.market.api.sdk.java.api.util.MessageBundleUtils.getMessage;

/**
 * terminal apk create validator
 * @author shifan
 * @date 2023/2/22
 */
public class CreateTerminalApkRequestValidator {

    private static final int MAX_FILE_TYPE_PARAMETER_COUNTER = 10;
    private static final int MAX_FILE_TYPE_PARAMETER_SIZE = 500;
    private static final String TEMPLATE_NAME_DELIMITER = "|";
    private static final int MAX_TEMPLATE_SIZE = 10;

    public static List<String> validate(CreateTerminalApkRequest validateTarget) {

        List<String> validationErrs = new ArrayList<>();

        if(validateTarget == null) {
            validationErrs.add(getMessage("parameter.not.null", "createTerminalApkRequest"));
        }else {

            if (StringUtils.isBlank(validateTarget.getPackageName())){
                validationErrs.add(getMessage("parameter.not.null", "packageName"));
            }
            if(StringUtils.isEmpty(validateTarget.getSerialNo()) && StringUtils.isEmpty(validateTarget.getTid())) {
                validationErrs.add(getMessage("parameter.createTerminalApkRequest.sn.tid.empty"));
            }
            if(!StringUtils.isEmpty(validateTarget.getTemplateName())) {
                if(validateTarget.getTemplateName().split("\\"+ TEMPLATE_NAME_DELIMITER).length > MAX_TEMPLATE_SIZE) {
                    validationErrs.add(getMessage("parameter.createTerminalApkRequest.template.name.toolong"));
                }
            }
            if(validateTarget.getBase64FileParameters() != null
                    && !validateTarget.getBase64FileParameters().isEmpty()) {
                if(validateTarget.getBase64FileParameters().size() > MAX_FILE_TYPE_PARAMETER_COUNTER) {
                    validationErrs.add(getMessage("parametersBase64FileParameters.over.maxCounter"));
                }
                for(FileParameter fileParameter: validateTarget.getBase64FileParameters()) {
                    if(FileUtils.getBase64FileSizeKB(fileParameter.getFileData()) > MAX_FILE_TYPE_PARAMETER_SIZE) {
                        validationErrs.add(getMessage("parametersBase64FileParameters.over.maxSize"));
                        break;
                    }
                }
            }
        }
        return validationErrs;
    }
}