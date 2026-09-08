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

package com.pax.market.api.sdk.java.api.terminal.validator;

import com.pax.market.api.sdk.java.api.terminal.dto.TerminalGeoFenceRequest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.pax.market.api.sdk.java.api.util.MessageBundleUtils.getMessage;
import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.apache.commons.lang3.StringUtils.trim;

/**
 * Validator for setting a terminal geographic fence (safe-range).
 * <p>
 * Two modes are supported:
 * <ul>
 *   <li>Template mode: provide {@code templateName} + {@code geofenceType} (P/C/B) to reference an existing
 *       reseller geofence template. {@code lat}/{@code lng}/{@code radius} are ignored.</li>
 *   <li>Direct center-point mode: leave {@code templateName} empty and provide {@code lat}/{@code lng}/{@code radius}
 *       with {@code geofenceType}=P.</li>
 * </ul>
 *
 * @author sdk
 */
public class TerminalGeoFenceRequestValidator {

    private static final String GEOFENCE_TYPE_CENTER_POINT = "P";
    private static final String GEOFENCE_TYPE_CUSTOMIZE_COORDINATE_POINT = "C";
    private static final String GEOFENCE_TYPE_BOUNDARIES_GEOFENCING = "B";

    /** Mirror of the server side safe radius bounds(in meters). */
    private static final int TERMINAL_SAFE_RADIUS_MIN = 500;
    private static final int TERMINAL_SAFE_RADIUS_MAX = 999999;

    private static final List<String> SUPPORTED_GEOFENCE_TYPES = Arrays.asList(
            GEOFENCE_TYPE_CENTER_POINT,
            GEOFENCE_TYPE_CUSTOMIZE_COORDINATE_POINT,
            GEOFENCE_TYPE_BOUNDARIES_GEOFENCING);

    private TerminalGeoFenceRequestValidator() {
    }

    public static List<String> validate(TerminalGeoFenceRequest validateTarget) {
        List<String> validationErrs = new ArrayList<>();
        if (validateTarget == null) {
            validationErrs.add(getMessage("parameter.not.null", "terminalGeoFenceRequest"));
            return validationErrs;
        }
        String geofenceType = trim(validateTarget.getGeofenceType());
        String templateName = trim(validateTarget.getTemplateName());

        if (isBlank(geofenceType)) {
            validationErrs.add(getMessage("parameter.not.empty", "geofenceType"));
            return validationErrs;
        }
        if (!SUPPORTED_GEOFENCE_TYPES.contains(geofenceType)) {
            validationErrs.add(getMessage("parameter.geofenceType.invalid"));
            return validationErrs;
        }

        if (isBlank(templateName)) {
            //direct center-point mode
            if (!GEOFENCE_TYPE_CENTER_POINT.equals(geofenceType)) {
                validationErrs.add(getMessage("terminal.geofence.centerPoint.typeOnly"));
                return validationErrs;
            }
            validateCenterPointGeometry(validateTarget, validationErrs);
        }
        return validationErrs;
    }

    private static void validateCenterPointGeometry(TerminalGeoFenceRequest validateTarget, List<String> validationErrs) {
        if (validateTarget.getLat() == null) {
            validationErrs.add(getMessage("parameter.not.null", "lat"));
        } else if (validateTarget.getLat() > 90 || validateTarget.getLat() < -90) {
            validationErrs.add(getMessage("terminal.geofence.lat.invalid"));
        }
        if (validateTarget.getLng() == null) {
            validationErrs.add(getMessage("parameter.not.null", "lng"));
        } else if (validateTarget.getLng() > 180 || validateTarget.getLng() < -180) {
            validationErrs.add(getMessage("terminal.geofence.lng.invalid"));
        }
        if (validateTarget.getRadius() == null) {
            validationErrs.add(getMessage("parameter.not.null", "radius"));
        } else if (validateTarget.getRadius() < TERMINAL_SAFE_RADIUS_MIN
                || validateTarget.getRadius() > TERMINAL_SAFE_RADIUS_MAX) {
            validationErrs.add(getMessage("terminal.geofence.radius.invalid"));
        }
    }
}
