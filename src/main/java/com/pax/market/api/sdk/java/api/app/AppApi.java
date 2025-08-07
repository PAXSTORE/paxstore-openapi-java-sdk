package com.pax.market.api.sdk.java.api.app;

import com.google.common.collect.Lists;
import com.pax.market.api.sdk.java.api.BaseThirdPartySysApi;
import com.pax.market.api.sdk.java.api.app.dto.*;
import com.pax.market.api.sdk.java.api.base.dto.PageRequestDTO;
import com.pax.market.api.sdk.java.api.base.dto.Result;
import com.pax.market.api.sdk.java.api.base.request.SdkRequest;
import com.pax.market.api.sdk.java.api.client.ThirdPartySysApiClient;
import com.pax.market.api.sdk.java.api.util.EnhancedJsonUtils;
import com.pax.market.api.sdk.java.api.validate.Validators;

import java.util.List;
import java.util.Locale;

/**
 * @author liukai
 * @date 2019/5/23
 */
public class AppApi extends BaseThirdPartySysApi {

    private static final String SEARCH_APP_URL = "/v1/3rdsys/apps";
    private static final String GET_APP_COST_URL = "/v1/3rdsys/apps/app-cost";

    private static final String SEARCH_APK_PARAM_PID_LIST_URL = "/v1/3rdsys/apps/param/pid/list";

    public AppApi(String baseUrl, String apiKey, String apiSecret) {
        super(baseUrl, apiKey, apiSecret);
    }

    public AppApi(String baseUrl, String apiKey, String apiSecret, Locale locale) {
        super(baseUrl, apiKey, apiSecret, locale);
    }

    public Result<AppPageDTO> searchApp(int pageNo, int pageSize, AppSearchOrderBy orderBy,
                                             String name, AppOsType osType, AppChargeType chargeType,
                                             AppBaseType baseType, AppStatus appStatus, ApkStatus apkStatus,
                                             Boolean specificReseller, Boolean specificMerchantCategory,
                                             Boolean includeSubscribedApp, String resellerName, String modelName){
        ThirdPartySysApiClient client = new ThirdPartySysApiClient(getBaseUrl(), getApiKey(), getApiSecret());
        PageRequestDTO page = new PageRequestDTO();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        if(orderBy != null) {
            page.setOrderBy(orderBy.val);
        }

        List<String> validationErrs = Validators.validatePageRequest(page);
        if(!validationErrs.isEmpty()) {
            return new Result<>(validationErrs);
        }
        SdkRequest request = getPageRequest(SEARCH_APP_URL, page);
        request.addRequestParam("name", name);
        if(apkStatus != null){
            request.addRequestParam("apkStatus", apkStatus.val);
        }
        if(appStatus != null){
            request.addRequestParam("appStatus", appStatus.val);
        }
        if(baseType != null){
            request.addRequestParam("baseType", baseType.val);
        }
        if(chargeType != null){
            request.addRequestParam("chargeType", String.valueOf(chargeType.val));
        }
        if(osType != null){
            request.addRequestParam("osType", osType.val);
        }
        if(specificReseller!=null) {
            request.addRequestParam("specificReseller", String.valueOf(specificReseller));
        }
        if(specificMerchantCategory != null){
            request.addRequestParam("specificMerchantCategory", String.valueOf(specificMerchantCategory));
        }

        if(includeSubscribedApp != null){
            request.addRequestParam("includeSubscribedApp", String.valueOf(includeSubscribedApp));
        }

        if(resellerName != null){
            request.addRequestParam("resellerName", resellerName);
        }


        if(modelName != null){
            request.addRequestParam("modelName", modelName);
        }

        AppPageResponse appPageResponse = EnhancedJsonUtils.fromJson(client.execute(request), AppPageResponse.class);
        Result<AppPageDTO> result = new Result<AppPageDTO>(appPageResponse);

        return result;
    }

    public Result<AppPageDTO> searchApp(int pageNo, int pageSize, AppSearchOrderBy orderBy,
                                        String name, AppOsType osType, AppChargeType chargeType,
                                        AppBaseType baseType, AppStatus appStatus, ApkStatus apkStatus,
                                        Boolean specificReseller, Boolean specificMerchantCategory) {
        return this.searchApp(pageNo, pageSize, orderBy, name, osType, chargeType, baseType, appStatus, apkStatus, specificReseller, specificMerchantCategory, false,null, null);
    }

    public Result<AppPageDTO> searchApp(int pageNo, int pageSize, AppSearchOrderBy orderBy,
                                        String name, AppOsType osType, AppChargeType chargeType,
                                        AppBaseType baseType, AppStatus appStatus, ApkStatus apkStatus,
                                        Boolean specificReseller, Boolean specificMerchantCategory, Boolean includeSubscribedApp) {
        return this.searchApp(pageNo, pageSize, orderBy, name, osType, chargeType, baseType, appStatus, apkStatus, specificReseller, specificMerchantCategory, includeSubscribedApp,null, null);
    }

    public Result<AppPageDTO> searchApp(int pageNo, int pageSize, AppSearchOrderBy orderBy,
                                        String name, AppOsType osType, AppChargeType chargeType,
                                        AppBaseType baseType, AppStatus appStatus, ApkStatus apkStatus,
                                        Boolean specificReseller, Boolean specificMerchantCategory,
                                        Boolean includeSubscribedApp, String resellerName) {
        return this.searchApp(pageNo, pageSize, orderBy, name, osType, chargeType, baseType, appStatus, apkStatus, specificReseller, specificMerchantCategory, includeSubscribedApp,resellerName, null);
    }

    public Result<AppCostDTO> getAppCost(Long resellerId, Long appId) {
        List<String> validationErrs = Validators.validateId(resellerId, "parameter.not.empty", "resellerId");
        validationErrs.addAll(Validators.validateId(appId, "parameter.not.empty", "appId"));
        if (!validationErrs.isEmpty()) {
            return new Result<>(validationErrs);
        }
        ThirdPartySysApiClient client = new ThirdPartySysApiClient(getBaseUrl(), getApiKey(), getApiSecret());
        SdkRequest request = createSdkRequest(GET_APP_COST_URL);
        request.setRequestMethod(SdkRequest.RequestMethod.GET);
        request.addRequestParam("resellerId", resellerId.toString());
        request.addRequestParam("appId", appId.toString());
        AppCostResponse appCostDTO = EnhancedJsonUtils.fromJson(client.execute(request), AppCostResponse.class);
        return new Result<>(appCostDTO);
    }


    public Result<ApkParamPidDTO> searchApkParamPidList(String paramTemplateName, String packageName, String versionName){
        List<String> validationErrs = Lists.newArrayList();
        validationErrs.addAll(Validators.validateStr(paramTemplateName, "parameter.not.null", "paramTemplateName"));
        validationErrs.addAll(Validators.validateStr(packageName, "parameter.not.null", "packageName"));
        validationErrs.addAll(Validators.validateStr(versionName, "parameter.not.null", "versionName"));
        if(!validationErrs.isEmpty()) {
            return new Result<>(validationErrs);
        }
        ThirdPartySysApiClient client = new ThirdPartySysApiClient(getBaseUrl(), getApiKey(), getApiSecret());
        SdkRequest request = createSdkRequest(SEARCH_APK_PARAM_PID_LIST_URL);
        request.setRequestMethod(SdkRequest.RequestMethod.GET);
        request.addRequestParam("paramTemplateName", paramTemplateName);
        request.addRequestParam("packageName", packageName);
        request.addRequestParam("versionName", versionName);
        ApkParamPidResponse resp =  EnhancedJsonUtils.fromJson(client.execute(request), ApkParamPidResponse.class);
        return new Result<ApkParamPidDTO>(resp);
    }

    public enum ApkStatus {
        Pending("P"),
        Online("O"),
        Rejected("R"),
        Offline("U");
        private String val;
        private ApkStatus(String status) {
            this.val = status;
        }
        public String val() {
            return this.val;
        }
    }

    public enum AppStatus {
        Active("A"),
        Suspend("S");
        private String val;
        private AppStatus(String status) {
            this.val = status;
        }
        public String val() {
            return this.val;
        }
    }

    public enum AppBaseType {
        Normal("N"),
        Parameter("P");
        private String val;
        private AppBaseType(String baseType) {
            this.val = baseType;
        }
        public String val() {
            return this.val;
        }
    }

    public enum AppChargeType {
        Free(0),
        Charging(1);
        private Integer val;
        private AppChargeType(Integer chargeType) {
            this.val = chargeType;
        }
        private Integer val() {
            return this.val;
        }
    }

    public enum AppOsType {
        Android("A"),
        Traditional("T");
        private String val;
        private AppOsType(String osType) {
            this.val = osType;
        }
        public String val() {
            return this.val;
        }
    }

    public enum AppSearchOrderBy {
        AppName_desc("app.name DESC"),
        AppName_asc("app.name ASC"),
        UpdatedDate_desc("app.updated_date DESC"),
        UpdatedDate_asc("app.updated_date ASC");
        private String val;
        private AppSearchOrderBy(String orderBy) {
            this.val = orderBy;
        }
        public String val(){
            return this.val;
        }
    }

}
