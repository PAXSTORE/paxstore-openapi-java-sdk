package com.pax.market.api.sdk.java.api.merchant.category;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

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
	
	private static final int MAX_LENGTH_CATEGORY_NAME = 128;
	
	private static final int MAX_LENGTH_CATEGORY_REMARKS = 255;

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
		List<String> validationErrs = validateCreate(merchantCategoryCreateRequest, "parameter.merchantCategoryCreateRequest.null");
		
		if(validationErrs.size()>0) {
			return new Result<MerchantCategoryDTO>(validationErrs);
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
		List<String> validationErrs = validateUpdate(merchantCategoryId, merchantCategoryUpdateRequest, "parameter.merchantCategoryId.invalid", "parameter.merchantCategoryUpdateRequest.null");
		
		if(validationErrs.size()>0) {
			return new Result<MerchantCategoryDTO>(validationErrs);
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
		List<String> validationErrs = validateId(merchantCategoryId, "parameter.merchantCategoryId.invalid");
		
		if(validationErrs.size()>0) {
			return new Result<String>(validationErrs);
		}
		
		ThirdPartySysApiClient client = new ThirdPartySysApiClient(getBaseUrl(), getApiKey(), getApiSecret());
		SdkRequest request = createSdkRequest(DELETE_CATEGORY_URL.replace("{merchantCategoryId}", merchantCategoryId.toString()));
		request.setRequestMethod(RequestMethod.DELETE);
		EmptyResponse emptyResponse =  EnhancedJsonUtils.fromJson(client.execute(request), EmptyResponse.class);
		Result<String> result = new Result<String>(emptyResponse);
		return result;
	}
	
	public Result<ArrayList<MerchantCategoryDTO>> batchCreateMerchantCategory(List<MerchantCategoryCreateRequest> merchantCategoryBatchCreateRequest, boolean skipExist){
		List<String> validationErrs = validateBatchCreate(merchantCategoryBatchCreateRequest);
		if(validationErrs.size()>0) {
			return new Result<ArrayList<MerchantCategoryDTO>>(validationErrs);
		}
		ThirdPartySysApiClient client = new ThirdPartySysApiClient(getBaseUrl(), getApiKey(), getApiSecret());
		SdkRequest request = createSdkRequest(BATCH_CREATE_CATEGORY_URL);
		request.setRequestMethod(RequestMethod.POST);
		request.addRequestParam("skipExist", skipExist+"");
		request.addHeader(Constants.CONTENT_TYPE, Constants.CONTENT_TYPE_JSON);
		request.setRequestBody(new Gson().toJson(merchantCategoryBatchCreateRequest, List.class));
		MerchantCategoryListResponseDTO categoryList = EnhancedJsonUtils.fromJson(client.execute(request), MerchantCategoryListResponseDTO.class);
		Result<ArrayList<MerchantCategoryDTO>> result = new Result<ArrayList<MerchantCategoryDTO>>(categoryList);
		return result;
	}
	
	private List<String> validateBatchCreate(List<MerchantCategoryCreateRequest> merchantCategoryBatchCreateRequest){
		List<String> validationErrs = new ArrayList<String>();
		if(merchantCategoryBatchCreateRequest == null || merchantCategoryBatchCreateRequest.size() == 0) {
			validationErrs.add(super.getMessage("parameter.merchantCategoryBatchCreateRequest.invalid"));
		}else {
			for(int i=0;i<merchantCategoryBatchCreateRequest.size();i++) {
				MerchantCategoryCreateRequest category = merchantCategoryBatchCreateRequest.get(i);
				if(StringUtils.isEmpty(category.getName())) {
					validationErrs.add(super.getMessage("merchantCategory.name.null"));
					break;
				}
			}
			
			for(int i=0;i<merchantCategoryBatchCreateRequest.size();i++) {
				MerchantCategoryCreateRequest category = merchantCategoryBatchCreateRequest.get(i);
				if(category.getName()!=null && category.getName().length()>MAX_LENGTH_CATEGORY_NAME) {
					validationErrs.add(super.getMessage("merchanteCategory.name.too.long").replaceAll("\\[NAME\\]", category.getName()));
				}
			}
			
			for(int i=0;i<merchantCategoryBatchCreateRequest.size();i++) {
				MerchantCategoryCreateRequest category = merchantCategoryBatchCreateRequest.get(i);
				if(category.getRemarks()!=null && category.getRemarks().length()>MAX_LENGTH_CATEGORY_REMARKS) {
					validationErrs.add(super.getMessage("merchanteCategory.remarks.too.long").replaceAll("\\[REMARKS\\]", category.getRemarks()));
				}
			}
		}
		return validationErrs;
	}
}
