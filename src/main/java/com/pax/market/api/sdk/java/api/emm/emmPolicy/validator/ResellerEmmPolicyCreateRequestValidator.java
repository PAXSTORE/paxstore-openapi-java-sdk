package com.pax.market.api.sdk.java.api.emm.emmPolicy.validator;

import com.google.common.collect.Lists;
import com.pax.market.api.sdk.java.api.constant.Constants;
import com.pax.market.api.sdk.java.api.emm.emmPolicy.dto.ResellerEmmPolicyCreateRequest;
import com.pax.market.api.sdk.java.api.validate.Validators;
import org.apache.commons.lang3.BooleanUtils;

import java.util.List;
import java.util.Objects;

import static com.pax.market.api.sdk.java.api.util.MessageBundleUtils.getMessage;

public class ResellerEmmPolicyCreateRequestValidator {

    public static List<String> validate(ResellerEmmPolicyCreateRequest validateTarget) {

        List<String> validationErrs = Lists.newArrayList();

        if (Objects.isNull(validateTarget)) {
            validationErrs.add(getMessage("parameter.not.null", "resellerEmmPolicyCreateRequest"));
        } else {
            validationErrs.addAll(Validators.validateStrNullAndMax(validateTarget.getResellerName(), "resellerName", Constants.MAX_64));
            if (BooleanUtils.isFalse(validateTarget.getInheritFlag())) {
                validationErrs.addAll(Validators.validateObject(validateTarget.getContentInfo(), "contentInfo"));
            }
            validationErrs.addAll(Validators.validateObject(validateTarget.getInheritFlag(), "inheritFlag"));
        }
        return validationErrs;
    }

}
