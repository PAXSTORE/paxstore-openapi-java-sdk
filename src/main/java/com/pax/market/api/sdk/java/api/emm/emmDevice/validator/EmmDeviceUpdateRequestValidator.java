package com.pax.market.api.sdk.java.api.emm.emmDevice.validator;

import com.google.common.collect.Lists;
import com.pax.market.api.sdk.java.api.constant.Constants;
import com.pax.market.api.sdk.java.api.emm.emmDevice.dto.EmmDeviceRegisterQRCodeCreateRequest;
import com.pax.market.api.sdk.java.api.emm.emmDevice.dto.EmmDeviceUpdateRequest;
import com.pax.market.api.sdk.java.api.validate.Validators;

import java.util.List;
import java.util.Objects;

import static com.pax.market.api.sdk.java.api.util.MessageBundleUtils.getMessage;

public class EmmDeviceUpdateRequestValidator {

    public static List<String> validate(EmmDeviceUpdateRequest validateTarget) {

        List<String> validationErrs = Lists.newArrayList();
        if (Objects.isNull(validateTarget)) {
            validationErrs.add(getMessage("parameter.not.null", "emmDeviceUpdateRequest"));
        } else {
            validationErrs.addAll(Validators.validateStrNullAndMax(validateTarget.getResellerName(), "resellerName", Constants.MAX_64));
            validationErrs.addAll(Validators.validateStrNullAndMax(validateTarget.getMerchantName(), "merchantName", Constants.MAX_64));
            validationErrs.addAll(Validators.validateStrNullAndMax(validateTarget.getDeviceName(), "deviceName", Constants.MAX_64));
        }
        return validationErrs;
    }

}
