package com.pax.market.api.sdk.java.api.emm.emmDevice.validator;

import com.google.common.collect.Lists;
import com.pax.market.api.sdk.java.api.constant.Constants;
import com.pax.market.api.sdk.java.api.emm.emmDevice.dto.EmmDeviceResetPasswordRequest;
import com.pax.market.api.sdk.java.api.validate.Validators;

import java.util.List;
import java.util.Objects;

import static com.pax.market.api.sdk.java.api.util.MessageBundleUtils.getMessage;

public class EmmDeviceResetPasswordRequestValidator {

    public static List<String> validate(EmmDeviceResetPasswordRequest validateTarget) {

        List<String> validationErrs = Lists.newArrayList();

        if (Objects.isNull(validateTarget)) {
            validationErrs.add(getMessage("parameter.not.null", "emmDeviceResetPasswordRequest"));
        } else {
            validationErrs.addAll(Validators.validateStr(validateTarget.getPassword(), "parameter.not.null", "password"));
            validationErrs.addAll(Validators.validateObject(validateTarget.getLockNow(), "lockNow"));
        }
        return validationErrs;

    }

}
