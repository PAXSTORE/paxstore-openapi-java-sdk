package com.pax.market.api.sdk.java.api.emm.emmDevice.validator;

import com.google.common.collect.Lists;
import com.pax.market.api.sdk.java.api.constant.Constants;
import com.pax.market.api.sdk.java.api.emm.emmApp.dto.EmmAppCreateRequest;
import com.pax.market.api.sdk.java.api.emm.emmDevice.dto.EmmDeviceRegisterQRCodeCreateRequest;
import com.pax.market.api.sdk.java.api.validate.Validators;

import java.util.List;
import java.util.Objects;

import static com.pax.market.api.sdk.java.api.util.MessageBundleUtils.getMessage;

public class EmmDeviceRegisterQRCodeCreateRequestValidator {

    public static List<String> validate(EmmDeviceRegisterQRCodeCreateRequest validateTarget) {

        List<String> validationErrs = Lists.newArrayList();

        if (Objects.isNull(validateTarget)) {
            validationErrs.add(getMessage("parameter.not.null", "emmDeviceRegisterQRCodeCreateRequest"));
        } else {
            validationErrs.addAll(Validators.validateStrNullAndMax(validateTarget.getResellerName(), "resellerName", Constants.MAX_64));
            validationErrs.addAll(Validators.validateStrNullAndMax(validateTarget.getMerchantName(), "merchantName", Constants.MAX_64));
            validationErrs.addAll(Validators.validateStr(validateTarget.getType(), "parameter.not.null", "type"));
            validationErrs.addAll(Validators.validateObject(validateTarget.getExpireDate(), "expireDate"));
        }
        return validationErrs;

    }

}
