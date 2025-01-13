package com.pax.market.api.sdk.java.api.emm.emmDevice;

import com.google.gson.Gson;
import com.pax.market.api.sdk.java.api.BaseThirdPartySysApi;
import com.pax.market.api.sdk.java.api.base.dto.EmptyResponse;
import com.pax.market.api.sdk.java.api.base.dto.PageRequestDTO;
import com.pax.market.api.sdk.java.api.base.dto.Result;
import com.pax.market.api.sdk.java.api.base.request.SdkRequest;
import com.pax.market.api.sdk.java.api.client.ThirdPartySysApiClient;
import com.pax.market.api.sdk.java.api.constant.Constants;
import com.pax.market.api.sdk.java.api.emm.emmApp.EmmAppApi;
import com.pax.market.api.sdk.java.api.emm.emmDevice.dto.*;
import com.pax.market.api.sdk.java.api.emm.emmDevice.validator.*;
import com.pax.market.api.sdk.java.api.util.EnhancedJsonUtils;
import com.pax.market.api.sdk.java.api.validate.Validators;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Locale;

public class EmmDeviceApi extends BaseThirdPartySysApi {

    private static final Logger logger = LoggerFactory.getLogger(EmmAppApi.class);

    private static final String CREATE_REGISTER_QR_CODE_URL = "/v1/3rdsys/emm/devices/register-qrcode";
    private static final String SEARCH_EMM_DEVICE_URL = "/v1/3rdsys/emm/devices";
    private static final String GET_EMM_DEVICE_URL = "/v1/3rdsys/emm/devices/{deviceId}";
    private static final String UPDATE_EMM_DEVICE_URL = "/v1/3rdsys/emm/devices/{deviceId}";
    private static final String BATCH_MOVE_EMM_DEVICE_URL = "/v1/3rdsys/emm/devices/batch/move";
    private static final String DELETE_EMM_DEVICE_URL = "/v1/3rdsys/emm/devices/{deviceId}";
    private static final String BATCH_DELETE_EMM_DEVICE_URL = "/v1/3rdsys/emm/devices/batch/delete";
    private static final String REBOOT_EMM_DEVICE_URL = "/v1/3rdsys/emm/devices/{deviceId}/reboot";
    private static final String LOCK_EMM_DEVICE_SCREEN_URL = "/v1/3rdsys/emm/devices/{deviceId}/lockscreen";
    private static final String RESET_EMM_DEVICE_PASSWORD_URL = "/v1/3rdsys/emm/devices/{deviceId}/resetpw";
    private static final String START_EMM_DEVICE_LOST_MODE_URL = "/v1/3rdsys/emm/devices/{deviceId}/startlost";
    private static final String STOP_EMM_DEVICE_LOST_MODE_URL = "/v1/3rdsys/emm/devices/{deviceId}/stoplost";
    private static final String SUBMIT_EMM_ZTE_QUICK_UPLOAD_RECORD_URL = "/v1/3rdsys/emm/devices/zte/quick-upload";


    public EmmDeviceApi(String baseUrl, String apiKey, String apiSecret) {
        super(baseUrl, apiKey, apiSecret);
    }

    public EmmDeviceApi(String baseUrl, String apiKey, String apiSecret, Locale locale) {
        super(baseUrl, apiKey, apiSecret, locale);
    }

    public Result<EmmDeviceRegisterQRCodeCreateDTO> createRegisterQRCode(EmmDeviceRegisterQRCodeCreateRequest emmDeviceRegisterQRCodeCreateRequest) {

        List<String> validationErrs = EmmDeviceRegisterQRCodeCreateRequestValidator.validate(emmDeviceRegisterQRCodeCreateRequest);
        if (!validationErrs.isEmpty()) {
            return new Result<>(validationErrs);
        }
        logger.debug("request= {}", new Gson().toJson(emmDeviceRegisterQRCodeCreateRequest, EmmDeviceRegisterQRCodeCreateRequest.class));

        ThirdPartySysApiClient client = new ThirdPartySysApiClient(getBaseUrl(), getApiKey(), getApiSecret());
        SdkRequest request = createSdkRequest(CREATE_REGISTER_QR_CODE_URL);
        request.setRequestMethod(SdkRequest.RequestMethod.POST);
        request.addHeader(Constants.CONTENT_TYPE, Constants.CONTENT_TYPE_JSON);
        request.setRequestBody(new Gson().toJson(emmDeviceRegisterQRCodeCreateRequest, EmmDeviceRegisterQRCodeCreateRequest.class));
        EmmDeviceRegisterQRCodeCreateResponse emmDeviceRegisterQRCodeCreateResponse = EnhancedJsonUtils.fromJson(client.execute(request), EmmDeviceRegisterQRCodeCreateResponse.class);
        return new Result<>(emmDeviceRegisterQRCodeCreateResponse);
    }


    public Result<EmmDeviceDTO> searchEmmDevice(int pageNo,
                                                int pageSize,
                                                EmmDeviceSearchOrderBy orderBy,
                                                String name,
                                                String serialNo,
                                                String mfrName,
                                                String modelName,
                                                String resellerName,
                                                String merchantName,
                                                EmmDeviceStatus status) {
        ThirdPartySysApiClient client = new ThirdPartySysApiClient(getBaseUrl(), getApiKey(), getApiSecret());
        PageRequestDTO page = new PageRequestDTO();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        if (orderBy != null) {
            page.setOrderBy(orderBy.val);
        }
        List<String> validationErrs = Validators.validatePageRequest(page);
        if (!validationErrs.isEmpty()) {
            return new Result<>(validationErrs);
        }

        SdkRequest request = getPageRequest(SEARCH_EMM_DEVICE_URL, page);

        if (name != null) {
            request.addRequestParam("name", name);
        }
        if (serialNo != null) {
            request.addRequestParam("serialNo", serialNo);
        }
        if (mfrName != null) {
            request.addRequestParam("mfrName", mfrName);
        }
        if (modelName != null) {
            request.addRequestParam("modelName", modelName);
        }
        if (resellerName != null) {
            request.addRequestParam("resellerName", resellerName);
        }
        if (merchantName != null) {
            request.addRequestParam("merchantName", merchantName);
        }
        if (status != null) {
            request.addRequestParam("status", status.val);
        }

        EmmDevicePageResponse emmDevicePageResponse = EnhancedJsonUtils.fromJson(client.execute(request), EmmDevicePageResponse.class);

        return new Result<>(emmDevicePageResponse);
    }

    public Result<EmmDeviceDetailDTO> getEmmDevice(Long deviceId) {
        logger.debug("deviceId= {}", deviceId);

        List<String> validationErrs = Validators.validateId(deviceId, "parameter.id.invalid", "deviceId");
        if (!validationErrs.isEmpty()) {
            return new Result<>(validationErrs);
        }

        ThirdPartySysApiClient client = new ThirdPartySysApiClient(getBaseUrl(), getApiKey(), getApiSecret());
        SdkRequest request = createSdkRequest(GET_EMM_DEVICE_URL.replace("{deviceId}", String.valueOf(deviceId)));
        request.setRequestMethod(SdkRequest.RequestMethod.GET);
        request.addHeader(Constants.CONTENT_TYPE, Constants.CONTENT_TYPE_JSON);
        EmmDeviceDetailResponse emmDeviceDetailResponse = EnhancedJsonUtils.fromJson(client.execute(request), EmmDeviceDetailResponse.class);
        return new Result<>(emmDeviceDetailResponse);
    }

    public Result<String> updateEmmDevice(Long deviceId, EmmDeviceUpdateRequest emmDeviceUpdateRequest) {
        logger.debug("deviceId= {}", deviceId);

        List<String> validationErrsI = Validators.validateId(deviceId, "parameter.id.invalid", "deviceId");
        if (!validationErrsI.isEmpty()) {
            return new Result<>(validationErrsI);
        }

        List<String> validationErrs = EmmDeviceUpdateRequestValidator.validate(emmDeviceUpdateRequest);
        if (!validationErrs.isEmpty()) {
            return new Result<>(validationErrs);
        }
        logger.debug("emmDeviceUpdateRequest= {}", new Gson().toJson(emmDeviceUpdateRequest, EmmDeviceUpdateRequest.class));

        ThirdPartySysApiClient client = new ThirdPartySysApiClient(getBaseUrl(), getApiKey(), getApiSecret());
        SdkRequest request = createSdkRequest(UPDATE_EMM_DEVICE_URL.replace("{deviceId}", String.valueOf(deviceId)));
        request.setRequestMethod(SdkRequest.RequestMethod.PUT);
        request.addHeader(Constants.CONTENT_TYPE, Constants.CONTENT_TYPE_JSON);
        request.setRequestBody(new Gson().toJson(emmDeviceUpdateRequest, EmmDeviceUpdateRequest.class));
        EmptyResponse emptyResponse = EnhancedJsonUtils.fromJson(client.execute(request), EmptyResponse.class);
        return new Result<>(emptyResponse);
    }


    public Result<String> batchMoveEmmDevice(EmmDeviceBatchMoveRequest emmDeviceBatchMoveRequest) {
        List<String> validationErrs = EmmDeviceBatchMoveRequestValidator.validate(emmDeviceBatchMoveRequest);
        if (!validationErrs.isEmpty()) {
            return new Result<>(validationErrs);
        }

        logger.debug("emmDeviceBatchMoveRequest= {}", new Gson().toJson(emmDeviceBatchMoveRequest, EmmDeviceBatchMoveRequest.class));

        ThirdPartySysApiClient client = new ThirdPartySysApiClient(getBaseUrl(), getApiKey(), getApiSecret());
        SdkRequest request = createSdkRequest(BATCH_MOVE_EMM_DEVICE_URL);
        request.setRequestMethod(SdkRequest.RequestMethod.PUT);
        request.addHeader(Constants.CONTENT_TYPE, Constants.CONTENT_TYPE_JSON);
        request.setRequestBody(new Gson().toJson(emmDeviceBatchMoveRequest, EmmDeviceBatchMoveRequest.class));
        EmptyResponse emptyResponse = EnhancedJsonUtils.fromJson(client.execute(request), EmptyResponse.class);
        return new Result<>(emptyResponse);
    }

    public Result<String> deleteEmmDevice(Long deviceId) {
        logger.debug("deviceId= {}", deviceId);

        List<String> validationErrs = Validators.validateId(deviceId, "parameter.id.invalid", "deviceId");
        if (!validationErrs.isEmpty()) {
            return new Result<>(validationErrs);
        }

        ThirdPartySysApiClient client = new ThirdPartySysApiClient(getBaseUrl(), getApiKey(), getApiSecret());
        SdkRequest request = createSdkRequest(DELETE_EMM_DEVICE_URL.replace("{deviceId}", String.valueOf(deviceId)));
        request.setRequestMethod(SdkRequest.RequestMethod.DELETE);
        request.addHeader(Constants.CONTENT_TYPE, Constants.CONTENT_TYPE_JSON);
        EmptyResponse emptyResponse = EnhancedJsonUtils.fromJson(client.execute(request), EmptyResponse.class);
        return new Result<>(emptyResponse);
    }

    public Result<String> batchDeleteEmmDevice(EmmDeviceBatchDeleteRequest emmDeviceBatchDeleteRequest) {

        List<String> validationErrs = EmmDeviceBatchDeleteRequestValidator.validate(emmDeviceBatchDeleteRequest);
        if (!validationErrs.isEmpty()) {
            return new Result<>(validationErrs);
        }

        logger.debug("emmDeviceBatchDeleteRequest= {}", new Gson().toJson(emmDeviceBatchDeleteRequest, EmmDeviceBatchDeleteRequest.class));

        ThirdPartySysApiClient client = new ThirdPartySysApiClient(getBaseUrl(), getApiKey(), getApiSecret());
        SdkRequest request = createSdkRequest(BATCH_DELETE_EMM_DEVICE_URL);
        request.setRequestMethod(SdkRequest.RequestMethod.DELETE);
        request.addHeader(Constants.CONTENT_TYPE, Constants.CONTENT_TYPE_JSON);
        request.setRequestBody(new Gson().toJson(emmDeviceBatchDeleteRequest, EmmDeviceBatchDeleteRequest.class));
        EmptyResponse emptyResponse = EnhancedJsonUtils.fromJson(client.execute(request), EmptyResponse.class);
        return new Result<>(emptyResponse);
    }


    public Result<String> rebootEmmDevice(Long deviceId) {
        logger.debug("deviceId= {}", deviceId);

        List<String> validationErrs = Validators.validateId(deviceId, "parameter.id.invalid", "deviceId");
        if (!validationErrs.isEmpty()) {
            return new Result<>(validationErrs);
        }

        ThirdPartySysApiClient client = new ThirdPartySysApiClient(getBaseUrl(), getApiKey(), getApiSecret());
        SdkRequest request = createSdkRequest(REBOOT_EMM_DEVICE_URL.replace("{deviceId}", String.valueOf(deviceId)));
        request.setRequestMethod(SdkRequest.RequestMethod.PUT);
        request.addHeader(Constants.CONTENT_TYPE, Constants.CONTENT_TYPE_JSON);
        EmptyResponse emptyResponse = EnhancedJsonUtils.fromJson(client.execute(request), EmptyResponse.class);
        return new Result<>(emptyResponse);
    }

    public Result<String> lockEmmDeviceScreen(Long deviceId) {
        logger.debug("deviceId= {}", deviceId);

        List<String> validationErrs = Validators.validateId(deviceId, "parameter.id.invalid", "deviceId");
        if (!validationErrs.isEmpty()) {
            return new Result<>(validationErrs);
        }

        ThirdPartySysApiClient client = new ThirdPartySysApiClient(getBaseUrl(), getApiKey(), getApiSecret());
        SdkRequest request = createSdkRequest(LOCK_EMM_DEVICE_SCREEN_URL.replace("{deviceId}", String.valueOf(deviceId)));
        request.setRequestMethod(SdkRequest.RequestMethod.PUT);
        request.addHeader(Constants.CONTENT_TYPE, Constants.CONTENT_TYPE_JSON);
        EmptyResponse emptyResponse = EnhancedJsonUtils.fromJson(client.execute(request), EmptyResponse.class);
        return new Result<>(emptyResponse);
    }


    public Result<String> resetEmmDevicePassword(Long deviceId, EmmDeviceResetPasswordRequest emmDeviceResetPasswordRequest) {
        logger.debug("deviceId= {}", deviceId);

        List<String> validationErrsI = Validators.validateId(deviceId, "parameter.id.invalid", "deviceId");
        if (!validationErrsI.isEmpty()) {
            return new Result<>(validationErrsI);
        }

        List<String> validationErrs = EmmDeviceResetPasswordRequestValidator.validate(emmDeviceResetPasswordRequest);
        if (!validationErrs.isEmpty()) {
            return new Result<>(validationErrs);
        }
        logger.debug("emmDeviceResetPasswordRequest= {}", new Gson().toJson(emmDeviceResetPasswordRequest,EmmDeviceResetPasswordRequest.class));

        ThirdPartySysApiClient client = new ThirdPartySysApiClient(getBaseUrl(), getApiKey(), getApiSecret());
        SdkRequest request = createSdkRequest(RESET_EMM_DEVICE_PASSWORD_URL.replace("{deviceId}", String.valueOf(deviceId)));
        request.setRequestMethod(SdkRequest.RequestMethod.PUT);
        request.addHeader(Constants.CONTENT_TYPE, Constants.CONTENT_TYPE_JSON);
        request.setRequestBody(new Gson().toJson(emmDeviceResetPasswordRequest, EmmDeviceResetPasswordRequest.class));
        EmptyResponse emptyResponse = EnhancedJsonUtils.fromJson(client.execute(request), EmptyResponse.class);
        return new Result<>(emptyResponse);
    }

    public Result<String> startEmmDeviceLostMode(Long deviceId, EmmDeviceLostModeRequest emmDeviceLostModeRequest) {
        logger.debug("deviceId= {}", deviceId);

        List<String> validationErrsI = Validators.validateId(deviceId, "parameter.id.invalid", "deviceId");
        if (!validationErrsI.isEmpty()) {
            return new Result<>(validationErrsI);
        }

        List<String> validationErrs = EmmDeviceLostModeRequestValidator.validate(emmDeviceLostModeRequest);
        if (!validationErrs.isEmpty()) {
            return new Result<>(validationErrs);
        }

        logger.debug("emmDeviceLostModeRequest= {}", new Gson().toJson(emmDeviceLostModeRequest,EmmDeviceLostModeRequest.class));

        ThirdPartySysApiClient client = new ThirdPartySysApiClient(getBaseUrl(), getApiKey(), getApiSecret());
        SdkRequest request = createSdkRequest(START_EMM_DEVICE_LOST_MODE_URL.replace("{deviceId}", String.valueOf(deviceId)));
        request.setRequestMethod(SdkRequest.RequestMethod.PUT);
        request.addHeader(Constants.CONTENT_TYPE, Constants.CONTENT_TYPE_JSON);
        request.setRequestBody(new Gson().toJson(emmDeviceLostModeRequest,EmmDeviceLostModeRequest.class));
        EmptyResponse emptyResponse = EnhancedJsonUtils.fromJson(client.execute(request), EmptyResponse.class);
        return new Result<>(emptyResponse);
    }
    public Result<String> stopEmmDeviceLostMode(Long deviceId) {
        logger.debug("deviceId= {}", deviceId);

        List<String> validationErrs = Validators.validateId(deviceId, "parameter.id.invalid", "deviceId");
        if (!validationErrs.isEmpty()) {
            return new Result<>(validationErrs);
        }

        ThirdPartySysApiClient client = new ThirdPartySysApiClient(getBaseUrl(), getApiKey(), getApiSecret());
        SdkRequest request = createSdkRequest(STOP_EMM_DEVICE_LOST_MODE_URL.replace("{deviceId}", String.valueOf(deviceId)));
        request.setRequestMethod(SdkRequest.RequestMethod.PUT);
        request.addHeader(Constants.CONTENT_TYPE, Constants.CONTENT_TYPE_JSON);
        EmptyResponse emptyResponse = EnhancedJsonUtils.fromJson(client.execute(request), EmptyResponse.class);
        return new Result<>(emptyResponse);
    }

    public Result<String> submitEmmZteQuickUploadRecord(EmmZteQuickUploadRecordCreateRequest emmZteQuickUploadRecordCreateRequest) {

        List<String> validationErrs = EmmZteQuickUploadRecordCreateRequestValidator.validate(emmZteQuickUploadRecordCreateRequest);
        if (!validationErrs.isEmpty()) {
            return new Result<>(validationErrs);
        }

        logger.debug("emmZteQuickUploadRecordCreateRequest= {}", new Gson().toJson(emmZteQuickUploadRecordCreateRequest, EmmZteQuickUploadRecordCreateRequest.class));

        ThirdPartySysApiClient client = new ThirdPartySysApiClient(getBaseUrl(), getApiKey(), getApiSecret());
        SdkRequest request = createSdkRequest(SUBMIT_EMM_ZTE_QUICK_UPLOAD_RECORD_URL);
        request.setRequestMethod(SdkRequest.RequestMethod.POST);
        request.addHeader(Constants.CONTENT_TYPE, Constants.CONTENT_TYPE_JSON);
        request.setRequestBody(new Gson().toJson(emmZteQuickUploadRecordCreateRequest, EmmZteQuickUploadRecordCreateRequest.class));
        EmptyResponse emptyResponse = EnhancedJsonUtils.fromJson(client.execute(request), EmptyResponse.class);
        return new Result<>(emptyResponse);
    }



    public enum EmmDeviceSearchOrderBy {
        EmmDeviceSN_desc("a.serial_no DESC"),
        EmmDeviceSN_asc("a.serial_no ASC"),
        EmmDeviceRegisterTime_desc("a.register_time DESC"),
        EmmDeviceRegisterTime_asc("a.register_time ASC");
        private String val;

        EmmDeviceSearchOrderBy(String orderBy) {
            this.val = orderBy;
        }

        public String val() {
            return this.val;
        }
    }


    public enum EmmDeviceType {
        COMPANY_OWNER("C");
        private String val;

        EmmDeviceType(String sign) {
            this.val = sign;
        }

        public String val() {
            return this.val;
        }
    }

    public enum EmmDeviceStatus {
        UN_CERTIFICATED("U"),
        ACTIVE("A"),
        LOST("L");
        private String val;

        EmmDeviceStatus(String sign) {
            this.val = sign;
        }

        public String val() {
            return this.val;
        }
    }

    public enum EmmZteIdentifierType {

        IMEI("I"),
        SERIAL_NUMBER("S");

        private String val;

        EmmZteIdentifierType(String sign) {
            this.val = sign;
        }

        public String val() {
            return this.val;
        }
    }



}
