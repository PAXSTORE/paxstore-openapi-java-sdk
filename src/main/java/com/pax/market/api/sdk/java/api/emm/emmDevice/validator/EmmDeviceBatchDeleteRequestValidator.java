package com.pax.market.api.sdk.java.api.emm.emmDevice.validator;

import com.google.common.collect.Lists;
import com.pax.market.api.sdk.java.api.constant.Constants;
import com.pax.market.api.sdk.java.api.emm.emmDevice.dto.EmmDeviceBatchDeleteRequest;
import com.pax.market.api.sdk.java.api.emm.emmDevice.dto.EmmDeviceBatchMoveRequest;
import com.pax.market.api.sdk.java.api.validate.Validators;

import java.util.List;
import java.util.Objects;

import static com.pax.market.api.sdk.java.api.util.MessageBundleUtils.getMessage;

public class EmmDeviceBatchDeleteRequestValidator {

    public static List<String> validate(EmmDeviceBatchDeleteRequest validateTarget) {

        List<String> validationErrs = Lists.newArrayList();

        if (Objects.isNull(validateTarget)) {
            validationErrs.add(getMessage("parameter.not.null", "emmDeviceBatchDeleteRequest"));
        } else {
            validationErrs.addAll(Validators.validateObject(validateTarget.getDeviceIds(), "deviceIds"));
        }
        return validationErrs;

    }

}
