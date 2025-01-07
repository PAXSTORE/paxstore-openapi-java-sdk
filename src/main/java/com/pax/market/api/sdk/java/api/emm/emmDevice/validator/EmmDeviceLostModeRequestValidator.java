package com.pax.market.api.sdk.java.api.emm.emmDevice.validator;

import com.google.common.collect.Lists;
import com.pax.market.api.sdk.java.api.constant.Constants;
import com.pax.market.api.sdk.java.api.emm.emmDevice.dto.EmmDeviceBatchMoveRequest;
import com.pax.market.api.sdk.java.api.emm.emmDevice.dto.EmmDeviceLostModeRequest;
import com.pax.market.api.sdk.java.api.validate.Validators;

import java.util.List;
import java.util.Objects;

import static com.pax.market.api.sdk.java.api.util.MessageBundleUtils.getMessage;

public class EmmDeviceLostModeRequestValidator {

    public static List<String> validate(EmmDeviceLostModeRequest validateTarget) {

        List<String> validationErrs = Lists.newArrayList();

        if (Objects.isNull(validateTarget)) {
            validationErrs.add(getMessage("parameter.not.null", "emmDeviceLostModeRequest"));
        } else {
            validationErrs.addAll(Validators.validateStrNullAndMax(validateTarget.getLostMessage(), "lostMessage", Constants.MAX_64));
            validationErrs.addAll(Validators.validateStrNullAndMax(validateTarget.getLostPhoneNumber(), "lostPhoneNumber", Constants.MAX_32));
        }
        return validationErrs;

    }

}
