package com.pax.market.api.sdk.java.api.terminalFirmware;

import com.google.gson.Gson;
import com.pax.market.api.sdk.java.api.BaseThirdPartySysApi;
import com.pax.market.api.sdk.java.api.base.dto.PageRequestDTO;
import com.pax.market.api.sdk.java.api.base.dto.Response;
import com.pax.market.api.sdk.java.api.base.dto.Result;
import com.pax.market.api.sdk.java.api.base.request.SdkRequest;
import com.pax.market.api.sdk.java.api.client.ThirdPartySysApiClient;
import com.pax.market.api.sdk.java.api.constant.Constants;
import com.pax.market.api.sdk.java.api.terminalFirmware.dto.*;
import com.pax.market.api.sdk.java.api.terminalFirmware.validator.PushFirmwareTaskBasicRequestValidator;
import com.pax.market.api.sdk.java.api.util.EnhancedJsonUtils;
import com.pax.market.api.sdk.java.api.validate.Validators;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Locale;

/**
 * @author liukai
 * @date 2019/5/23
 */
public class TerminalFirmwareApi extends BaseThirdPartySysApi {
    private static final Logger logger = LoggerFactory.getLogger(TerminalFirmwareApi.class);

    private static final String CREATE_TERMINAL_FIRMWARE_URL = "/v1/3rdsys/terminalFirmwares";
    private static final String SEARCH_TERMINAL_FIRMWARE_LIST_URL = "/v1/3rdsys/terminalFirmwares";
    private static final String GET_TERMINAL_FIRMWARE_URL = "/v1/3rdsys/terminalFirmwares/{terminalFirmwareId}";
    private static final String SUSPEND_TERMINAL_FIRMWARE_URL = "/v1/3rdsys/terminalFirmwares/suspend";

    public TerminalFirmwareApi(String baseUrl, String apiKey, String apiSecret) {
        super(baseUrl, apiKey, apiSecret);
    }

    public TerminalFirmwareApi(String baseUrl, String apiKey, String apiSecret, Locale locale) {
        super(baseUrl, apiKey, apiSecret, locale);
    }

    public Result<PushFirmwareTaskDTO> pushFirmware2Terminal(PushFirmware2TerminalRequest pushFirmware2TerminalRequest){
        List<String> validationErrs = PushFirmwareTaskBasicRequestValidator.validate(pushFirmware2TerminalRequest, "pushFirmware2TerminalRequest");
        if(!validationErrs.isEmpty()) {
            return new Result<>(validationErrs);
        }
        ThirdPartySysApiClient client = new ThirdPartySysApiClient(getBaseUrl(), getApiKey(), getApiSecret());
        SdkRequest request = createSdkRequest(CREATE_TERMINAL_FIRMWARE_URL);
        request.setRequestMethod(SdkRequest.RequestMethod.POST);
        request.addHeader(Constants.CONTENT_TYPE, Constants.CONTENT_TYPE_JSON);
        request.setRequestBody(new Gson().toJson(pushFirmware2TerminalRequest, PushFirmware2TerminalRequest.class));
        Response response = EnhancedJsonUtils.fromJson(client.execute(request), Response.class);
        return new Result<PushFirmwareTaskDTO>(response);
    }

    public Result<PushFirmwareTaskDTO> searchPushFirmwareTasks(int pageNo, int pageSize, SearchOrderBy orderBy,
                                                               String terminalTid, String fmName, PushStatus status, String serialNo){
        ThirdPartySysApiClient client = new ThirdPartySysApiClient(getBaseUrl(), getApiKey(), getApiSecret());
        PageRequestDTO page = new PageRequestDTO();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        if(orderBy != null) {
            page.setOrderBy(orderBy.val());
        }

        SdkRequest request = getPageRequest(SEARCH_TERMINAL_FIRMWARE_LIST_URL, page);
        request.addRequestParam("terminalTid", terminalTid);
        request.addRequestParam("serialNo", serialNo);
        request.addRequestParam("fmName", fmName);
        if(status != null){
            request.addRequestParam("status", status.val());
        }

        PushFirmwareTaskPageResponse pageResponse = EnhancedJsonUtils.fromJson(client.execute(request), PushFirmwareTaskPageResponse.class);
        return new Result<PushFirmwareTaskDTO>(pageResponse);
    }

    public Result<PushFirmwareTaskDTO> searchPushFirmwareTasks(int pageNo, int pageSize, SearchOrderBy orderBy,
                                                               String terminalTid, String fmName, PushStatus status){

        logger.debug("terminalTid= {}", terminalTid);
        List<String> validationErrs = Validators.validateStr(terminalTid, "parameter.not.null", "terminalTid");
        if(!validationErrs.isEmpty()) {
            return new Result<>(validationErrs);
        }

        return searchPushFirmwareTasks(pageNo, pageSize, orderBy, terminalTid, fmName, status,null);
    }

    public Result<PushFirmwareTaskDTO> getPushFirmwareTask(Long pushFirmwareTaskId){
        logger.debug("pushFirmwareTaskId= {}", pushFirmwareTaskId);
        List<String> validationErrs = Validators.validateId(pushFirmwareTaskId, "parameter.id.invalid", "pushFirmwareTaskId");
        if(!validationErrs.isEmpty()) {
            return new Result<>(validationErrs);
        }
        ThirdPartySysApiClient client = new ThirdPartySysApiClient(getBaseUrl(), getApiKey(), getApiSecret());
        SdkRequest request = createSdkRequest(GET_TERMINAL_FIRMWARE_URL.replace("{terminalFirmwareId}", pushFirmwareTaskId+""));
        request.setRequestMethod(SdkRequest.RequestMethod.GET);
        PushFirmwareTaskResponse resp = EnhancedJsonUtils.fromJson(client.execute(request), PushFirmwareTaskResponse.class);
        Result<PushFirmwareTaskDTO> result = new Result<PushFirmwareTaskDTO>(resp);
        return result;
    }

    public Result<String> disablePushFirmwareTask(DisablePushFirmwareTask disablePushFirmwareTask){
        List<String> validationErrs = PushFirmwareTaskBasicRequestValidator.validate(disablePushFirmwareTask, "disablePushFirmwareTask");
        if(!validationErrs.isEmpty()) {
            return new Result<>(validationErrs);
        }
        ThirdPartySysApiClient client = new ThirdPartySysApiClient(getBaseUrl(), getApiKey(), getApiSecret());
        SdkRequest request = createSdkRequest(SUSPEND_TERMINAL_FIRMWARE_URL);
        request.setRequestMethod(SdkRequest.RequestMethod.POST);
        request.addHeader(Constants.CONTENT_TYPE, Constants.CONTENT_TYPE_JSON);
        request.setRequestBody(new Gson().toJson(disablePushFirmwareTask, DisablePushFirmwareTask.class));
        Response resp = EnhancedJsonUtils.fromJson(client.execute(request), Response.class);
        return new Result<String>(resp);
    }


}
