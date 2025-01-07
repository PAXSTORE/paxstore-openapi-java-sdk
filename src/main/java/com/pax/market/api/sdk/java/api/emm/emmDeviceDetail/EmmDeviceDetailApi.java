package com.pax.market.api.sdk.java.api.emm.emmDeviceDetail;

import com.pax.market.api.sdk.java.api.BaseThirdPartySysApi;
import com.pax.market.api.sdk.java.api.base.dto.PageRequestDTO;
import com.pax.market.api.sdk.java.api.base.dto.Result;
import com.pax.market.api.sdk.java.api.base.request.SdkRequest;
import com.pax.market.api.sdk.java.api.client.ThirdPartySysApiClient;
import com.pax.market.api.sdk.java.api.constant.Constants;
import com.pax.market.api.sdk.java.api.emm.emmDeviceDetail.dto.*;
import com.pax.market.api.sdk.java.api.util.EnhancedJsonUtils;
import com.pax.market.api.sdk.java.api.validate.Validators;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Locale;

public class EmmDeviceDetailApi extends BaseThirdPartySysApi {

    private static final Logger logger = LoggerFactory.getLogger(EmmDeviceDetailApi.class);

    private static final String GET_EMM_DEVICE_DASHBOARD_DETAIL_URL = "/v1/3rdsys/emm/device/detail/{deviceId}";
    private static final String GET_EMM_DEVICE_DASHBOARD_MONITOR_URL = "/v1/3rdsys/emm/device/detail/{deviceId}/monitor";
    private static final String GET_EMM_APP_DETAIL_URL = "/v1/3rdsys/emm/device/detail/{deviceId}/installed-apps";


    public EmmDeviceDetailApi(String baseUrl, String apiKey, String apiSecret) {
        super(baseUrl, apiKey, apiSecret);
    }

    public EmmDeviceDetailApi(String baseUrl, String apiKey, String apiSecret, Locale locale) {
        super(baseUrl, apiKey, apiSecret, locale);
    }

    public Result<EmmDeviceDashboardDetailDTO> getEmmDeviceDashboardDetail(Long deviceId) {
        logger.debug("deviceId= {}", deviceId);

        List<String> validationErrs = Validators.validateId(deviceId, "parameter.id.invalid", "deviceId");

        if (!validationErrs.isEmpty()) {
            return new Result<>(validationErrs);
        }

        ThirdPartySysApiClient client = new ThirdPartySysApiClient(getBaseUrl(), getApiKey(), getApiSecret());
        SdkRequest request = createSdkRequest(GET_EMM_DEVICE_DASHBOARD_DETAIL_URL.replace("{deviceId}", String.valueOf(deviceId)));
        request.setRequestMethod(SdkRequest.RequestMethod.GET);
        request.addHeader(Constants.CONTENT_TYPE, Constants.CONTENT_TYPE_JSON);
        EmmDeviceDashboardDetailPageResponse emmDeviceDashboardDetailPageResponse = EnhancedJsonUtils.fromJson(client.execute(request), EmmDeviceDashboardDetailPageResponse.class);
        return new Result<>(emmDeviceDashboardDetailPageResponse);
    }


    public Result<EmmDeviceDashboardMonitorDTO> getEmmDeviceDashboardMonitor(Long deviceId) {
        logger.debug("deviceId= {}", deviceId);

        List<String> validationErrs = Validators.validateId(deviceId, "parameter.id.invalid", "deviceId");

        if (!validationErrs.isEmpty()) {
            return new Result<>(validationErrs);
        }

        ThirdPartySysApiClient client = new ThirdPartySysApiClient(getBaseUrl(), getApiKey(), getApiSecret());
        SdkRequest request = createSdkRequest(GET_EMM_DEVICE_DASHBOARD_MONITOR_URL.replace("{deviceId}", String.valueOf(deviceId)));
        request.setRequestMethod(SdkRequest.RequestMethod.GET);
        request.addHeader(Constants.CONTENT_TYPE, Constants.CONTENT_TYPE_JSON);
        EmmDeviceDashboardMonitorResponse emmDeviceDashboardMonitorResponse = EnhancedJsonUtils.fromJson(client.execute(request), EmmDeviceDashboardMonitorResponse.class);
        return new Result<>(emmDeviceDashboardMonitorResponse);
    }

    public Result<EmmDeviceInstalledAppDTO> searchDeviceInstalledApp(int pageNo,
                                                                     int pageSize,
                                                                     EmmDeviceInstalledAppOrderBy orderBy,
                                                                     Long deviceId) {
        logger.debug("deviceId= {}", deviceId);

        List<String> validationErrsI = Validators.validateId(deviceId, "parameter.id.invalid", "deviceId");

        if (!validationErrsI.isEmpty()) {
            return new Result<>(validationErrsI);
        }

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

        SdkRequest request = getPageRequest(GET_EMM_APP_DETAIL_URL.replace("{deviceId}", String.valueOf(deviceId)), page);

        EmmDeviceInstalledAppPageResponse emmDeviceInstalledAppPageResponse = EnhancedJsonUtils.fromJson(client.execute(request), EmmDeviceInstalledAppPageResponse.class);

        return new Result<>(emmDeviceInstalledAppPageResponse);
    }


    public enum EmmDeviceInstalledAppOrderBy {
        AppName_desc("a.name DESC"),
        AppName_asc("a.name ASC"),
        AppSize_desc("a.size DESC"),
        AppSize_asc("a.size ASC"),
        AppInstallTime_desc("a.install_time DESC"),
        AppInstallTime_asc("a.install_time ASC");
        private String val;

        private EmmDeviceInstalledAppOrderBy(String orderBy) {
            this.val = orderBy;
        }

        public String val() {
            return this.val;
        }
    }


}
