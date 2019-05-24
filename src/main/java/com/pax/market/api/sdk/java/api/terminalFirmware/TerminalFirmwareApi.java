package com.pax.market.api.sdk.java.api.terminalFirmware;

import com.google.gson.Gson;
import com.pax.market.api.sdk.java.api.BaseThirdPartySysApi;
import com.pax.market.api.sdk.java.api.base.dto.Response;
import com.pax.market.api.sdk.java.api.base.dto.Result;
import com.pax.market.api.sdk.java.api.base.request.SdkRequest;
import com.pax.market.api.sdk.java.api.client.ThirdPartySysApiClient;
import com.pax.market.api.sdk.java.api.constant.Constants;
import com.pax.market.api.sdk.java.api.terminalFirmware.dto.CreateTerminalFirmwareRequest;
import com.pax.market.api.sdk.java.api.util.EnhancedJsonUtils;
import com.pax.market.api.sdk.java.api.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * @author liukai
 * @date 2019/5/23
 */
public class TerminalFirmwareApi extends BaseThirdPartySysApi {

    private static final String CREATE_TERMINAL_FIRMWARE_URL = "/v1/3rdsys/terminalFirmwares";

    public TerminalFirmwareApi(String baseUrl, String apiKey, String apiSecret) {
        super(baseUrl, apiKey, apiSecret);
    }

    public TerminalFirmwareApi(String baseUrl, String apiKey, String apiSecret, Locale locale) {
        super(baseUrl, apiKey, apiSecret, locale);
    }

    public Result<String> createTerminalFirmware(CreateTerminalFirmwareRequest createTerminalFirmwareRequest){
        List<String> validationErrs = validateCreateTerminalFirmware(createTerminalFirmwareRequest);

        if(validationErrs.size()>0) {
            return new Result<String>(validationErrs);
        }
        ThirdPartySysApiClient client = new ThirdPartySysApiClient(getBaseUrl(), getApiKey(), getApiSecret());
        SdkRequest request = createSdkRequest(CREATE_TERMINAL_FIRMWARE_URL);
        request.setRequestMethod(SdkRequest.RequestMethod.POST);
        request.addHeader(Constants.CONTENT_TYPE, Constants.CONTENT_TYPE_JSON);
        request.setRequestBody(new Gson().toJson(createTerminalFirmwareRequest, CreateTerminalFirmwareRequest.class));
        Response response = EnhancedJsonUtils.fromJson(client.execute(request), Response.class);
        Result<String> result = new Result<String>(response);
        return result;
    }

    private List<String> validateCreateTerminalFirmware(CreateTerminalFirmwareRequest createTerminalFirmwareRequest) {
        List<String> validationErrs = new ArrayList<String>();
        if(createTerminalFirmwareRequest == null) {
            validationErrs.add(super.getMessage("parameter.createTerminalFirmwareRequest.null"));
        }else {
            validationErrs.addAll(validate(createTerminalFirmwareRequest));
            if(StringUtils.isEmpty(createTerminalFirmwareRequest.getSerialNo()) && StringUtils.isEmpty(createTerminalFirmwareRequest.getTid())) {
                validationErrs.add(super.getMessage("parameter.createTerminalFirmwareRequest.sn.tid.empty"));
            }
        }
        return validationErrs;
    }

}
