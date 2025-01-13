package com.pax.market.api.sdk.java.api.emm.emmPolicy.validator;

import com.google.common.collect.Lists;
import com.pax.market.api.sdk.java.api.constant.Constants;
import com.pax.market.api.sdk.java.api.emm.emmPolicy.dto.MerchantEmmPolicyCreateRequest;
import com.pax.market.api.sdk.java.api.validate.Validators;
import org.apache.commons.lang3.BooleanUtils;

import java.util.List;
import java.util.Objects;

import static com.pax.market.api.sdk.java.api.util.MessageBundleUtils.getMessage;

public class MerchantEmmPolicyCreateRequestValidator {

    public static List<String> validate(MerchantEmmPolicyCreateRequest validateTarget) {

        List<String> validationErrs = Lists.newArrayList();

        if (Objects.isNull(validateTarget)) {
            validationErrs.add(getMessage("parameter.not.null", "merchantEmmPolicyCreateRequest"));
        } else {
            validationErrs.addAll(Validators.validateStrNullAndMax(validateTarget.getResellerName(), "resellerName", Constants.MAX_64));
            validationErrs.addAll(Validators.validateStrNullAndMax(validateTarget.getMerchantName(), "merchantName", Constants.MAX_64));
            if (BooleanUtils.isFalse(validateTarget.getInheritFlag())) {
                validationErrs.addAll(Validators.validateObject(validateTarget.getContentInfo(), "contentInfo"));
            }
            validationErrs.addAll(Validators.validateObject(validateTarget.getInheritFlag(), "inheritFlag"));
        }
        return validationErrs;
    }
}
