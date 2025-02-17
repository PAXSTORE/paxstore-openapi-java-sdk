package com.pax.market.api.sdk.java.api.factoryModel;

import com.pax.market.api.sdk.java.api.BaseThirdPartySysApi;
import com.pax.market.api.sdk.java.api.base.dto.PageRequestDTO;
import com.pax.market.api.sdk.java.api.base.dto.Result;
import com.pax.market.api.sdk.java.api.base.request.SdkRequest;
import com.pax.market.api.sdk.java.api.client.ThirdPartySysApiClient;
import com.pax.market.api.sdk.java.api.factoryModel.dto.FactoryDTO;
import com.pax.market.api.sdk.java.api.factoryModel.dto.FactoryPageResponse;
import com.pax.market.api.sdk.java.api.util.EnhancedJsonUtils;
import com.pax.market.api.sdk.java.api.validate.Validators;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @author shifan
 * @date 2025/2/17
 */
public class FactoryModelApi extends BaseThirdPartySysApi {

    private static final Logger logger = LoggerFactory.getLogger(FactoryModelApi.class.getSimpleName());

    private static final String SEARCH_FACTORY_MODELS_URL = "/v1/3rdsys/factory/models";

    public FactoryModelApi(String baseUrl, String apiKey, String apiSecret) {
        super(baseUrl, apiKey, apiSecret);
    }

    public Result<FactoryDTO> searchFactoryModels(int pageNo, int pageSize,
                                                  SearchOrderBy orderBy, String factoryName,
                                                  String modelName, String productType) {
        logger.debug("factoryName="+factoryName+"|modelName="+modelName+"|productType="+productType);
        ThirdPartySysApiClient client = new ThirdPartySysApiClient(getBaseUrl(), getApiKey(), getApiSecret());
        PageRequestDTO page = new PageRequestDTO();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        if(orderBy != null) {
            page.setOrderBy(orderBy.val);
        }

        List<String> validationErrs = Validators.validatePageRequest(page);
        if(!validationErrs.isEmpty()) {
            return new Result<FactoryDTO>(validationErrs);
        }
        SdkRequest request = getPageRequest(SEARCH_FACTORY_MODELS_URL, page);
        request.addRequestParam("factoryName", factoryName);
        request.addRequestParam("modelName", factoryName);
        request.addRequestParam("productType", productType);
        FactoryPageResponse factoryPageResponse = EnhancedJsonUtils.fromJson(client.execute(request), FactoryPageResponse.class);
        return new Result<FactoryDTO>(factoryPageResponse);
    }

    public enum SearchOrderBy {
        name_desc("a.name DESC"),
        name_asc("a.name ASC");
        private String val;
        private SearchOrderBy(String orderBy) {
            this.val = orderBy;
        }
        public String val(){
            return this.val;
        }
    }
}
