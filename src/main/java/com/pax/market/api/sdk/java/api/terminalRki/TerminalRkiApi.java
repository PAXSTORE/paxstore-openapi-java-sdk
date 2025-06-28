package com.pax.market.api.sdk.java.api.terminalRki;

import com.google.gson.Gson;
import com.pax.market.api.sdk.java.api.BaseThirdPartySysApi;
import com.pax.market.api.sdk.java.api.base.dto.EmptyResponse;
import com.pax.market.api.sdk.java.api.base.dto.PageRequestDTO;
import com.pax.market.api.sdk.java.api.base.dto.Response;
import com.pax.market.api.sdk.java.api.base.dto.Result;
import com.pax.market.api.sdk.java.api.base.request.SdkRequest;
import com.pax.market.api.sdk.java.api.client.ThirdPartySysApiClient;
import com.pax.market.api.sdk.java.api.constant.Constants;
import com.pax.market.api.sdk.java.api.terminalRki.dto.*;
import com.pax.market.api.sdk.java.api.terminalRki.validator.PushRkiBasicRequestValidator;
import com.pax.market.api.sdk.java.api.util.EnhancedJsonUtils;
import com.pax.market.api.sdk.java.api.util.StringUtils;
import com.pax.market.api.sdk.java.api.validate.Validators;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
    private static final String DELETE_TERMINAL_RKI_KEY_URL = "/v1/3rdsys/terminalRkis/{terminalRkiId}";

    public TerminalRkiApi(String baseUrl, String apiKey, String apiSecret) {
        super(baseUrl, apiKey, apiSecret);
    }

    public TerminalRkiApi(String baseUrl, String apiKey, String apiSecret, Locale locale) {
        super(baseUrl, apiKey, apiSecret, locale);
    }

    public Result<PushRkiTaskDTO> pushRkiKey2Terminal(PushRki2TerminalRequest pushRki2TerminalRequest){
        List<String> validationErrs = PushRkiBasicRequestValidator.validate(pushRki2TerminalRequest, "pushRki2TerminalRequest");

        if(!validationErrs.isEmpty()) {
            return new Result<>(validationErrs);
        }
        ThirdPartySysApiClient client = new ThirdPartySysApiClient(getBaseUrl(), getApiKey(), getApiSecret());
        SdkRequest request = createSdkRequest(CREATE_TERMINAL_RKI_KEY_URL);
        request.setRequestMethod(SdkRequest.RequestMethod.POST);
        request.addHeader(Constants.CONTENT_TYPE, Constants.CONTENT_TYPE_JSON);
        request.setRequestBody(new Gson().toJson(pushRki2TerminalRequest, PushRki2TerminalRequest.class));
        PushRkiTaskResponse response = EnhancedJsonUtils.fromJson(client.execute(request), PushRkiTaskResponse.class);
        return new Result<>(response);
    }

    public Result<PushRkiTaskDTO> searchPushRkiTasks(int pageNo, int pageSize, SearchOrderBy orderBy,
                                                     String terminalTid, String rkiKey, PushStatus status){
        return searchPushRkiTasks(pageNo, pageSize, orderBy, terminalTid, rkiKey, status, null);
    }

    public Result<PushRkiTaskDTO> searchPushRkiTasks(int pageNo, int pageSize, SearchOrderBy orderBy,
                                                               String terminalTid, String rkiKey, PushStatus status, String serialNo){
        ThirdPartySysApiClient client = new ThirdPartySysApiClient(getBaseUrl(), getApiKey(), getApiSecret());
        PageRequestDTO page = new PageRequestDTO();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        if(orderBy != null) {
            page.setOrderBy(orderBy.val());
        }

        List<String> validationErrs = Validators.validatePageRequest(page);
        if(!validationErrs.isEmpty()) {
            return new Result<>(validationErrs);
        }

        if(StringUtils.isEmpty(terminalTid) && StringUtils.isEmpty(serialNo)) {
            validationErrs.add(getMessage("parameter.sn.tid.empty"));
            return new Result<>(validationErrs);
        }

        SdkRequest request = getPageRequest(SEARCH_TERMINAL_RKI_KEY_LIST_URL, page);
        request.addRequestParam("terminalTid", terminalTid);
        request.addRequestParam("serialNo", serialNo);
        request.addRequestParam("rkiKey", rkiKey);
        if(status != null){
            request.addRequestParam("status", status.val());
        }

        PushRkiTaskPageResponse pageResponse = EnhancedJsonUtils.fromJson(client.execute(request), PushRkiTaskPageResponse.class);

        return new Result<PushRkiTaskDTO>(pageResponse);
    }

    public Result<PushRkiTaskDTO> getPushRkiTask(Long pushRkiTaskId){
        logger.debug("pushRkiTaskId= {}", pushRkiTaskId);
        List<String> validationErrs = Validators.validateId(pushRkiTaskId, "parameter.id.invalid", "pushRkiTaskId");
        if(!validationErrs.isEmpty()) {
            return new Result<>(validationErrs);
        }
        ThirdPartySysApiClient client = new ThirdPartySysApiClient(getBaseUrl(), getApiKey(), getApiSecret());
        SdkRequest request = createSdkRequest(GET_TERMINAL_RKI_KEY_URL.replace("{terminalRkiId}", pushRkiTaskId+""));
        request.setRequestMethod(SdkRequest.RequestMethod.GET);
        PushRkiTaskResponse resp = EnhancedJsonUtils.fromJson(client.execute(request), PushRkiTaskResponse.class);
        return new Result<>(resp);
    }

    public Result<String> disablePushRkiTask(DisablePushRkiTask disablePushRkiTask){
        List<String> validationErrs = PushRkiBasicRequestValidator.validate(disablePushRkiTask, "disablePushRkiTask");

        if(!validationErrs.isEmpty()) {
            return new Result<>(validationErrs);
        }
        ThirdPartySysApiClient client = new ThirdPartySysApiClient(getBaseUrl(), getApiKey(), getApiSecret());
        SdkRequest request = createSdkRequest(SUSPEND_TERMINAL_RKI_KEY_URL);
        request.setRequestMethod(SdkRequest.RequestMethod.POST);
        request.addHeader(Constants.CONTENT_TYPE, Constants.CONTENT_TYPE_JSON);
        request.setRequestBody(new Gson().toJson(disablePushRkiTask, DisablePushRkiTask.class));
        Response resp = EnhancedJsonUtils.fromJson(client.execute(request), Response.class);
        return new Result<>(resp);
    }

    public Result<String> deleteTerminalRki(Long terminalRkiId) {
        logger.debug("terminalRkiId= {}", terminalRkiId);
        List<String> validationErrs = Validators.validateId(terminalRkiId, "parameter.id.invalid", "terminalRkiId");
        if(!validationErrs.isEmpty()) {
            return new Result<>(validationErrs);
        }
        ThirdPartySysApiClient client = new ThirdPartySysApiClient(getBaseUrl(), getApiKey(), getApiSecret());
        SdkRequest request = createSdkRequest(DELETE_TERMINAL_RKI_KEY_URL.replace("{terminalFirmwareId}", terminalRkiId.toString()));
        request.setRequestMethod(SdkRequest.RequestMethod.DELETE);
        EmptyResponse emptyResponse = EnhancedJsonUtils.fromJson(client.execute(request), EmptyResponse.class);
        return new Result<>(emptyResponse);
    }
}
