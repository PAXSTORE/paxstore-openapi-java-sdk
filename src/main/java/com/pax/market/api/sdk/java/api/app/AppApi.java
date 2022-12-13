package com.pax.market.api.sdk.java.api.app;

import com.pax.market.api.sdk.java.api.BaseThirdPartySysApi;
import com.pax.market.api.sdk.java.api.app.dto.AppPageDTO;
import com.pax.market.api.sdk.java.api.app.dto.AppPageResponse;
import com.pax.market.api.sdk.java.api.base.dto.PageRequestDTO;
import com.pax.market.api.sdk.java.api.base.dto.Result;
import com.pax.market.api.sdk.java.api.base.request.SdkRequest;
import com.pax.market.api.sdk.java.api.client.ThirdPartySysApiClient;
import com.pax.market.api.sdk.java.api.util.EnhancedJsonUtils;

import java.util.List;
import java.util.Locale;

/**
 * @author liukai
 * @date 2019/5/23
 */
public class AppApi extends BaseThirdPartySysApi {

    private static final String SEARCH_APP_URL = "/v1/3rdsys/apps";

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

        List<String> validationErrs = validate(page);
        if(validationErrs.size()>0) {
            return new Result<AppPageDTO>(validationErrs);
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
        AppName_desc("CONVERT( app.name USING gbk ) COLLATE gbk_chinese_ci DESC"),
        AppName_asc("CONVERT( app.name USING gbk ) COLLATE gbk_chinese_ci ASC"),
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
