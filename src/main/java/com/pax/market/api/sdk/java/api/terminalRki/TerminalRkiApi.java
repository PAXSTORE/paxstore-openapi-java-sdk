package com.pax.market.api.sdk.java.api.terminalRki;

import com.google.gson.Gson;
import com.pax.market.api.sdk.java.api.BaseThirdPartySysApi;
import com.pax.market.api.sdk.java.api.base.dto.PageRequestDTO;
import com.pax.market.api.sdk.java.api.base.dto.Response;
import com.pax.market.api.sdk.java.api.base.dto.Result;
import com.pax.market.api.sdk.java.api.base.request.SdkRequest;
import com.pax.market.api.sdk.java.api.client.ThirdPartySysApiClient;
import com.pax.market.api.sdk.java.api.constant.Constants;
import com.pax.market.api.sdk.java.api.terminalRki.dto.*;
import com.pax.market.api.sdk.java.api.util.EnhancedJsonUtils;
import com.pax.market.api.sdk.java.api.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * @author liukai
 * @date 2020/2/7
 */
public class TerminalRkiApi extends BaseThirdPartySysApi {

    private static final Logger logger = LoggerFactory.getLogger(TerminalRkiApi.class);

    private static final String CREATE_TERMINAL_RKI_KEY_URL = "/v1/3rdsys/terminalRkis";
    private static final String SEARCH_TERMINAL_RKI_KEY_LIST_URL = "/v1/3rdsys/terminalRkis";
    private static final String GET_TERMINAL_RKI_KEY_URL = "/v1/3rdsys/terminalRkis/{terminalRkiId}";
    private static final String SUSPEND_TERMINAL_RKI_KEY_URL = "/v1/3rdsys/terminalRkis/suspend";

    public TerminalRkiApi(String baseUrl, String apiKey, String apiSecret) {
        super(baseUrl, apiKey, apiSecret);
    }

    public TerminalRkiApi(String baseUrl, String apiKey, String apiSecret, Locale locale) {
        super(baseUrl, apiKey, apiSecret, locale);
    }

    public Result<PushRkiTaskDTO> pushRkiKey2Terminal(PushRki2TerminalRequest pushRki2TerminalRequest){
        List<String> validationErrs = validateCreateTerminalRki(pushRki2TerminalRequest);

        if(validationErrs.size()>0) {
            return new Result<PushRkiTaskDTO>(validationErrs);
        }
        ThirdPartySysApiClient client = new ThirdPartySysApiClient(getBaseUrl(), getApiKey(), getApiSecret());
        SdkRequest request = createSdkRequest(CREATE_TERMINAL_RKI_KEY_URL);
        request.setRequestMethod(SdkRequest.RequestMethod.POST);
        request.addHeader(Constants.CONTENT_TYPE, Constants.CONTENT_TYPE_JSON);
        request.setRequestBody(new Gson().toJson(pushRki2TerminalRequest, PushRki2TerminalRequest.class));
        Response response = EnhancedJsonUtils.fromJson(client.execute(request), Response.class);
        Result<PushRkiTaskDTO> result = new Result<PushRkiTaskDTO>(response);
        return result;
    }

    public Result<PushRkiTaskDTO> searchPushRkiTasks(int pageNo, int pageSize, SearchOrderBy orderBy,
                                                               String terminalTid, String rkiKey, PushStatus status){
        ThirdPartySysApiClient client = new ThirdPartySysApiClient(getBaseUrl(), getApiKey(), getApiSecret());
        PageRequestDTO page = new PageRequestDTO();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        if(orderBy != null) {
            page.setOrderBy(orderBy.val());
        }

        List<String> validationErrs = validate(page);
        if(validationErrs.size()>0) {
            return new Result<PushRkiTaskDTO>(validationErrs);
        }
        if(StringUtils.isEmpty(terminalTid)) {
            validationErrs.add(super.getMessage("parameter.searchPushRkiTasks.terminalTid.empty"));
            return new Result<PushRkiTaskDTO>(validationErrs);
        }

        SdkRequest request = getPageRequest(SEARCH_TERMINAL_RKI_KEY_LIST_URL, page);
        request.addRequestParam("terminalTid", terminalTid);
        request.addRequestParam("rkiKey", rkiKey);
        if(status != null){
            request.addRequestParam("status", status.val());
        }

        PushRkiTaskPageResponse pageResponse = EnhancedJsonUtils.fromJson(client.execute(request), PushRkiTaskPageResponse.class);
        Result<PushRkiTaskDTO> result = new Result<PushRkiTaskDTO>(pageResponse);

        return result;
    }

    public Result<PushRkiTaskDTO> getPushRkiTask(Long pushRkiTaskId){
        logger.debug("pushRkiTaskId="+pushRkiTaskId);
        List<String> validationErrs = validateId(pushRkiTaskId, "parameter.pushRkiTaskId.invalid");
        if(validationErrs.size()>0) {
            return new Result<PushRkiTaskDTO>(validationErrs);
        }
        ThirdPartySysApiClient client = new ThirdPartySysApiClient(getBaseUrl(), getApiKey(), getApiSecret());
        SdkRequest request = createSdkRequest(GET_TERMINAL_RKI_KEY_URL.replace("{terminalRkiId}", pushRkiTaskId+""));
        request.setRequestMethod(SdkRequest.RequestMethod.GET);
        PushRkiTaskResponse resp = EnhancedJsonUtils.fromJson(client.execute(request), PushRkiTaskResponse.class);
        Result<PushRkiTaskDTO> result = new Result<PushRkiTaskDTO>(resp);
        return result;
    }

    public Result<String> disablePushRkiTask(DisablePushRkiTask disablePushRkiTask){
        List<String> validationErrs = validateDisablePushRki(disablePushRkiTask);

        if(validationErrs.size()>0) {
            return new Result<String>(validationErrs);
        }
        ThirdPartySysApiClient client = new ThirdPartySysApiClient(getBaseUrl(), getApiKey(), getApiSecret());
        SdkRequest request = createSdkRequest(SUSPEND_TERMINAL_RKI_KEY_URL);
        request.setRequestMethod(SdkRequest.RequestMethod.POST);
        request.addHeader(Constants.CONTENT_TYPE, Constants.CONTENT_TYPE_JSON);
        request.setRequestBody(new Gson().toJson(disablePushRkiTask, DisablePushRkiTask.class));
        Response resp = EnhancedJsonUtils.fromJson(client.execute(request), Response.class);
        Result<String> result = new Result<String>(resp);
        return result;
    }

    private List<String> validateCreateTerminalRki(PushRki2TerminalRequest request) {
        List<String> validationErrs = new ArrayList<String>();
        if(request == null) {
            validationErrs.add(super.getMessage("parameter.pushRki2TerminalRequest.null"));
        }else {
            validationErrs.addAll(validate(request));
            if(StringUtils.isEmpty(request.getSerialNo()) && StringUtils.isEmpty(request.getTid())) {
                validationErrs.add(super.getMessage("parameter.pushRki2TerminalRequest.sn.tid.empty"));
            }
        }
        return validationErrs;
    }

    private List<String> validateDisablePushRki(DisablePushRkiTask disablePushRkiTask) {
        List<String> validationErrs = new ArrayList<String>();
        if(disablePushRkiTask == null) {
            validationErrs.add(super.getMessage("parameter.disablePushRkiTask.null"));
        }else {
            validationErrs.addAll(validate(disablePushRkiTask));
            if(StringUtils.isEmpty(disablePushRkiTask.getSerialNo()) && StringUtils.isEmpty(disablePushRkiTask.getTid())) {
                validationErrs.add(super.getMessage("parameter.disablePushRkiTask.sn.tid.empty"));
            }
        }
        return validationErrs;
    }
}
