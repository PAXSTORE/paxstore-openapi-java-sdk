package com.pax.market.api.sdk.java.api.merchant.category;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.pax.market.api.sdk.java.api.merchant.category.validator.MerchantCategoryBatchCreateRequestValidator;
import com.pax.market.api.sdk.java.api.merchant.category.validator.MerchantCategoryCreateRequestValidator;
import com.pax.market.api.sdk.java.api.merchant.category.validator.MerchantCategoryUpdateRequestValidator;
import com.pax.market.api.sdk.java.api.validate.Validators;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.pax.market.api.sdk.java.api.BaseThirdPartySysApi;
import com.pax.market.api.sdk.java.api.base.dto.EmptyResponse;
import com.pax.market.api.sdk.java.api.base.dto.Result;
import com.pax.market.api.sdk.java.api.base.request.SdkRequest;
import com.pax.market.api.sdk.java.api.base.request.SdkRequest.RequestMethod;
import com.pax.market.api.sdk.java.api.client.ThirdPartySysApiClient;
import com.pax.market.api.sdk.java.api.constant.Constants;
import com.pax.market.api.sdk.java.api.merchant.category.dto.MerchantCategoryListResponseDTO;
import com.pax.market.api.sdk.java.api.merchant.category.dto.MerchantCategoryResponseDTO;
import com.pax.market.api.sdk.java.api.merchant.category.dto.MerchantCategoryUpdateRequest;
import com.pax.market.api.sdk.java.api.merchant.category.dto.MerchantCategoryCreateRequest;
import com.pax.market.api.sdk.java.api.merchant.category.dto.MerchantCategoryDTO;
import com.pax.market.api.sdk.java.api.util.EnhancedJsonUtils;
import com.pax.market.api.sdk.java.api.util.StringUtils;

public class MerchantCategoryApi extends BaseThirdPartySysApi{
	private static final Logger logger = LoggerFactory.getLogger(MerchantCategoryApi.class);
	
	private static final String GET_CATEGORIES_URL = "/v1/3rdsys/merchantCategories";
	
	private static final String CREATE_CATEGORY_URL = "/v1/3rdsys/merchantCategories";
	
	private static final String UPDATE_CATEGORY_URL = "/v1/3rdsys/merchantCategories/{merchantCategoryId}";
	
	private static final String DELETE_CATEGORY_URL = "/v1/3rdsys/merchantCategories/{merchantCategoryId}";
	
	private static final String BATCH_CREATE_CATEGORY_URL = "/v1/3rdsys/merchantCategories/batch";

	public MerchantCategoryApi(String baseUrl, String apiKey, String apiSecret) {
		super(baseUrl, apiKey, apiSecret);
	}
	
	public MerchantCategoryApi(String baseUrl, String apiKey, String apiSecret, Locale locale) {
		super(baseUrl, apiKey, apiSecret, locale);
	}
	
	public Result<ArrayList<MerchantCategoryDTO>> getMerchantCategories(String name) {
		ThirdPartySysApiClient client = new ThirdPartySysApiClient(getBaseUrl(), getApiKey(), getApiSecret());
		SdkRequest request = this.createSdkRequest(GET_CATEGORIES_URL);
		if(!StringUtils.isEmpty(name)) {
			request.addRequestParam("name", name);
		}
		MerchantCategoryListResponseDTO categoryList = EnhancedJsonUtils.fromJson(client.execute(request), MerchantCategoryListResponseDTO.class);
		Result<ArrayList<MerchantCategoryDTO>> result = new Result<ArrayList<MerchantCategoryDTO>>(categoryList);
		return result;
	}
	
	public Result<MerchantCategoryDTO> createMerchantCategory(MerchantCategoryCreateRequest merchantCategoryCreateRequest){
		List<String> validationErrs = MerchantCategoryCreateRequestValidator.validate(merchantCategoryCreateRequest);
		if(!validationErrs.isEmpty()) {
			return new Result<>(validationErrs);
		}
		ThirdPartySysApiClient client = new ThirdPartySysApiClient(getBaseUrl(), getApiKey(), getApiSecret());
		SdkRequest request = createSdkRequest(CREATE_CATEGORY_URL);
		request.setRequestMethod(RequestMethod.POST);
		request.addHeader(Constants.CONTENT_TYPE, Constants.CONTENT_TYPE_JSON);
		request.setRequestBody(new Gson().toJson(merchantCategoryCreateRequest, MerchantCategoryCreateRequest.class));
		MerchantCategoryResponseDTO merchantCategoryResponseDTO = EnhancedJsonUtils.fromJson(client.execute(request), MerchantCategoryResponseDTO.class);
        Result<MerchantCategoryDTO> result = new Result<MerchantCategoryDTO>(merchantCategoryResponseDTO);
        return result;
	}
	
	public Result<MerchantCategoryDTO> updateMerchantCategory(Long merchantCategoryId, MerchantCategoryUpdateRequest merchantCategoryUpdateRequest){
		List<String> validationErrs = MerchantCategoryUpdateRequestValidator.validate(merchantCategoryId, merchantCategoryUpdateRequest);
		
		if(!validationErrs.isEmpty()) {
			return new Result<>(validationErrs);
		}
		ThirdPartySysApiClient client = new ThirdPartySysApiClient(getBaseUrl(), getApiKey(), getApiSecret());
		
		SdkRequest request = createSdkRequest(UPDATE_CATEGORY_URL.replace("{merchantCategoryId}", merchantCategoryId+""));
		
		request.setRequestMethod(RequestMethod.PUT);
		request.addHeader(Constants.CONTENT_TYPE, Constants.CONTENT_TYPE_JSON);
		request.setRequestBody(new Gson().toJson(merchantCategoryUpdateRequest, MerchantCategoryUpdateRequest.class));
		MerchantCategoryResponseDTO merchantCategoryResponseDTO = EnhancedJsonUtils.fromJson(client.execute(request), MerchantCategoryResponseDTO.class);
        Result<MerchantCategoryDTO> result = new Result<MerchantCategoryDTO>(merchantCategoryResponseDTO);
        return result;
	}
	
	public Result<String> deleteMerchantCategory(Long merchantCategoryId) {
		List<String> validationErrs = Validators.validateId(merchantCategoryId, "parameter.id.invalid","merchantCategoryId");
		
		if(!validationErrs.isEmpty()) {
			return new Result<>(validationErrs);
		}
		
		ThirdPartySysApiClient client = new ThirdPartySysApiClient(getBaseUrl(), getApiKey(), getApiSecret());
		SdkRequest request = createSdkRequest(DELETE_CATEGORY_URL.replace("{merchantCategoryId}", merchantCategoryId.toString()));
		request.setRequestMethod(RequestMethod.DELETE);
		EmptyResponse emptyResponse =  EnhancedJsonUtils.fromJson(client.execute(request), EmptyResponse.class);
		return new Result<String>(emptyResponse);
	}
	
	public Result<ArrayList<MerchantCategoryDTO>> batchCreateMerchantCategory(List<MerchantCategoryCreateRequest> merchantCategoryBatchCreateRequest, boolean skipExist){
		List<String> validationErrs = MerchantCategoryBatchCreateRequestValidator.validate(merchantCategoryBatchCreateRequest);
		if(!validationErrs.isEmpty()) {
			return new Result<>(validationErrs);
		}
		ThirdPartySysApiClient client = new ThirdPartySysApiClient(getBaseUrl(), getApiKey(), getApiSecret());
		SdkRequest request = createSdkRequest(BATCH_CREATE_CATEGORY_URL);
		request.setRequestMethod(RequestMethod.POST);
		request.addRequestParam("skipExist", skipExist+"");
		request.addHeader(Constants.CONTENT_TYPE, Constants.CONTENT_TYPE_JSON);
		request.setRequestBody(new Gson().toJson(merchantCategoryBatchCreateRequest, List.class));
		MerchantCategoryListResponseDTO categoryList = EnhancedJsonUtils.fromJson(client.execute(request), MerchantCategoryListResponseDTO.class);
		return new Result<ArrayList<MerchantCategoryDTO>>(categoryList);
	}

}
