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
import com.pax.market.api.sdk.java.api.util.EnhancedJsonUtils;
import com.pax.market.api.sdk.java.api.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
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
        List<String> validationErrs = validateCreateTerminalFirmware(pushFirmware2TerminalRequest);

        if(validationErrs.size()>0) {
            return new Result<PushFirmwareTaskDTO>(validationErrs);
        }
        ThirdPartySysApiClient client = new ThirdPartySysApiClient(getBaseUrl(), getApiKey(), getApiSecret());
        SdkRequest request = createSdkRequest(CREATE_TERMINAL_FIRMWARE_URL);
        request.setRequestMethod(SdkRequest.RequestMethod.POST);
        request.addHeader(Constants.CONTENT_TYPE, Constants.CONTENT_TYPE_JSON);
        request.setRequestBody(new Gson().toJson(pushFirmware2TerminalRequest, PushFirmware2TerminalRequest.class));
        Response response = EnhancedJsonUtils.fromJson(client.execute(request), Response.class);
        Result<PushFirmwareTaskDTO> result = new Result<PushFirmwareTaskDTO>(response);
        return result;
    }

    public Result<PushFirmwareTaskDTO> searchPushFirmwareTasks(int pageNo, int pageSize, SearchOrderBy orderBy,
                                                               String terminalTid, String fmName, PushStatus status){
        ThirdPartySysApiClient client = new ThirdPartySysApiClient(getBaseUrl(), getApiKey(), getApiSecret());
        PageRequestDTO page = new PageRequestDTO();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        if(orderBy != null) {
            page.setOrderBy(orderBy.val());
        }

        List<String> validationErrs = validate(page);
        if(validationErrs.size()>0) {
            return new Result<PushFirmwareTaskDTO>(validationErrs);
        }
        if(StringUtils.isEmpty(terminalTid)) {
            validationErrs.add(super.getMessage("parameter.searchPushFirmwareTasks.terminalTid.empty"));
            return new Result<PushFirmwareTaskDTO>(validationErrs);
        }

        SdkRequest request = getPageRequest(SEARCH_TERMINAL_FIRMWARE_LIST_URL, page);
        request.addRequestParam("terminalTid", terminalTid);
        request.addRequestParam("fmName", fmName);
        if(status != null){
            request.addRequestParam("status", status.val());
        }

        PushFirmwareTaskPageResponse pageResponse = EnhancedJsonUtils.fromJson(client.execute(request), PushFirmwareTaskPageResponse.class);
        Result<PushFirmwareTaskDTO> result = new Result<PushFirmwareTaskDTO>(pageResponse);

        return result;
    }

    public Result<PushFirmwareTaskDTO> getPushFirmwareTask(Long pushFirmwareTaskId){
        logger.debug("pushFirmwareTaskId="+pushFirmwareTaskId);
        List<String> validationErrs = validateId(pushFirmwareTaskId, "parameter.pushFirmwareTaskId.invalid");
        if(validationErrs.size()>0) {
            return new Result<PushFirmwareTaskDTO>(validationErrs);
        }
        ThirdPartySysApiClient client = new ThirdPartySysApiClient(getBaseUrl(), getApiKey(), getApiSecret());
        SdkRequest request = createSdkRequest(GET_TERMINAL_FIRMWARE_URL.replace("{terminalFirmwareId}", pushFirmwareTaskId+""));
        request.setRequestMethod(SdkRequest.RequestMethod.GET);
        PushFirmwareTaskResponse resp = EnhancedJsonUtils.fromJson(client.execute(request), PushFirmwareTaskResponse.class);
        Result<PushFirmwareTaskDTO> result = new Result<PushFirmwareTaskDTO>(resp);
        return result;
    }

    public Result<String> disablePushFirmwareTask(DisablePushFirmwareTask disablePushFirmwareTask){
        List<String> validationErrs = validateDisablePushFirmware(disablePushFirmwareTask);

        if(validationErrs.size()>0) {
            return new Result<String>(validationErrs);
        }
        ThirdPartySysApiClient client = new ThirdPartySysApiClient(getBaseUrl(), getApiKey(), getApiSecret());
        SdkRequest request = createSdkRequest(SUSPEND_TERMINAL_FIRMWARE_URL);
        request.setRequestMethod(SdkRequest.RequestMethod.POST);
        request.addHeader(Constants.CONTENT_TYPE, Constants.CONTENT_TYPE_JSON);
        request.setRequestBody(new Gson().toJson(disablePushFirmwareTask, DisablePushFirmwareTask.class));
        Response resp = EnhancedJsonUtils.fromJson(client.execute(request), Response.class);
        Result<String> result = new Result<String>(resp);
        return result;
    }

    private List<String> validateCreateTerminalFirmware(PushFirmware2TerminalRequest createTerminalFirmwareRequest) {
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

    private List<String> validateDisablePushFirmware(DisablePushFirmwareTask disablePushFirmwareTask) {
        List<String> validationErrs = new ArrayList<String>();
        if(disablePushFirmwareTask == null) {
            validationErrs.add(super.getMessage("parameter.disablePushFirmwareTask.null"));
        }else {
            validationErrs.addAll(validate(disablePushFirmwareTask));
            if(StringUtils.isEmpty(disablePushFirmwareTask.getSerialNo()) && StringUtils.isEmpty(disablePushFirmwareTask.getTid())) {
                validationErrs.add(super.getMessage("parameter.disablePushFirmwareTask.sn.tid.empty"));
            }
        }
        return validationErrs;
    }

}
