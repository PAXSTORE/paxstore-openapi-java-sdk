package com.pax.market.api.sdk.java.api.validate;



import com.pax.market.api.sdk.java.api.base.dto.PageRequestDTO;
import com.pax.market.api.sdk.java.api.constant.Constants;
import com.pax.market.api.sdk.java.api.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

import static com.pax.market.api.sdk.java.api.util.MessageBundleUtils.getMessage;
import static org.apache.commons.lang3.StringUtils.isBlank;

/**
 * Sdk Basic data type verification
 * @author shifan
 */
public final class Validators {

    private Validators() {
    }


    public static List<String> validateId(Long id, String errorMsgKey, String fieldName) {
        List<String> validationErrs = new ArrayList<>();
        if (id == null || id < 0L) {
            validationErrs.add(getMessage(errorMsgKey, fieldName));
        }
        return validationErrs;
    }

    public static List<String> validateId(Long id, String errorMsgKey) {
        List<String> validationErrs = new ArrayList<>();
        if (id == null || id < 0L) {
            validationErrs.add(getMessage(errorMsgKey));
        }
        return validationErrs;
    }


    public static List<String> validateStr(String str, String errorMsgKey) {
        List<String> validationErrs = new ArrayList<>();
        if (isBlank(str)) {
            validationErrs.add(getMessage(errorMsgKey));
        }
        return validationErrs;
    }

    public static List<String> validateStr(String str, String errorMsgKey, String fieldName) {
        List<String> validationErrs = new ArrayList<>();
        if (isBlank(str)) {
            validationErrs.add(getMessage(errorMsgKey, fieldName));
        }
        return validationErrs;
    }


    public static <T> List<String> validateCreate(T createReq, String beanEmptyMsgKey) {
        List<String> validationErrs = new ArrayList<>();
        if (createReq == null) {
            validationErrs.add(getMessage(beanEmptyMsgKey));
            return validationErrs;
        }
        return validationErrs;
    }


    public static List<String> validateUpdate(Long id,
                                              Object updateReq,
                                              String idInvalidMsgKey,
                                              String beanEmptyMsgKey) {
        List<String> validationErrs = new ArrayList<>();
        if (id == null || id < 0L) {
            validationErrs.add(getMessage(idInvalidMsgKey));
        }
        if (updateReq == null) {
            validationErrs.add(getMessage(beanEmptyMsgKey));
        }
        return validationErrs;
    }

    public static List<String> validateObject(Object object, String paramName) {
        List<String> validationErrs = new ArrayList<>();
        if (object == null) {
            validationErrs.add(getMessage("parameter.not.null", paramName));
        }
        return validationErrs;
    }

    public static List<String> validatePageRequest(PageRequestDTO page) {
        List<String> validationErrs = new ArrayList<>();
        if (page == null){
            validationErrs.add(getMessage("parameter.not.null", "page"));
        } else {
            if (page.getPageNo() < Constants.MIN_PAGE_NO){
                validationErrs.add(getMessage("parameter.pageNo.length"));
            }
            if (page.getPageSize() < Constants.MIN_PAGE_SIZE || page.getPageSize() > Constants.MAX_PAGE_SIZE){
                validationErrs.add(getMessage("parameter.pageSize.length"));
            }
        }
        return validationErrs;
    }

    public static List<String> validateStrNullAndMax(String str, String paramName, Integer max) {
        List<String> validationErrs = new ArrayList<>();
        if (StringUtils.isBlank(str)){
            validationErrs.add(getMessage("parameter.not.null", paramName));
        }
        validationErrs.addAll(validateStrMax(str, paramName, max));
        return validationErrs;
    }

    public static List<String> validateStrMax(String str, String paramName, Integer max) {
        List<String> validationErrs = new ArrayList<>();
        if (StringUtils.isNotBlank(str) && str.length() > max) {
            validationErrs.add(getMessage("parameter.too.long", paramName, max));
        }
        return validationErrs;
    }

    public static List<String> validateStrNullAndMin(String str, String paramName, Integer max) {
        List<String> validationErrs = new ArrayList<>();
        if (StringUtils.isBlank(str)){
            validationErrs.add(getMessage("parameter.not.null", paramName));
        }
        validationErrs.addAll(validateStrMin(str, paramName, max));
        return validationErrs;
    }

    public static List<String> validateStrMin(String strName, String paramName, Integer min) {
        List<String> validationErrs = new ArrayList<>();
        if (StringUtils.isNotBlank(strName) && strName.length() < min) {
            validationErrs.add(getMessage("parameter.too.short", paramName, min));
        }
        return validationErrs;
    }



    public static List<String> validateStrNullAndRange(String str, String paramName, Integer min, Integer max) {
        List<String> validationErrs = new ArrayList<>();
        if (StringUtils.isBlank(str)){
            validationErrs.add(getMessage("parameter.not.null", paramName));
        }
        validationErrs.addAll(validateRange(str, paramName, min, max));
        return validationErrs;
    }

    public static List<String> validateRange(String str, String paramName, Integer min, Integer max) {
        List<String> validationErrs = new ArrayList<>();
        if (str != null && (str.length() < min || str.length() > max)) {
            validationErrs.add(getMessage("parameter.range.length", paramName, min, max));
        }
        return validationErrs;
    }

    public static List<String> validateLongNull(Long l, String paramName) {
        List<String> validationErrs = new ArrayList<>();
        if (l == null || l < 0){
            validationErrs.add(getMessage("parameter.not.null", paramName));
        }
        return validationErrs;
    }

}



