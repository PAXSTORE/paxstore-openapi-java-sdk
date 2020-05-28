## Merchant Category APIs


The merchant category APIs allow thirdparty system get merchant categories by name, create merchante category, update merchant category, delete merchant category and batch create merchant categories.  

All the merchant APIs are in the class *com.pax.market.api.sdk.java.api.merchant.category.MerchantCategoryApi*.   

**Constructors of MerchantCategoryApi**

```
public MerchantCategoryApi(String baseUrl, String apiKey, String apiSecret);  
public MerchantCategoryApi(String baseUrl, String apiKey, String apiSecret, Locale locale);
```

Constructor parameters description   

|Name|Type|Description|
|:---|:---|:---|
|baseUrl|String|the base url of REST API|
|apiKey|String|the apiKey of marketplace, get this key from PAXSTORE admin console, refer to chapter Apply access rights|
|apiSecret|String|apiSecret, get api secret from PAXSTORE admin console, refer to chapter Apply access rights|
|locale|Locale|the locale, the default locale is Locale.ENGLISH, the language of message and errors in return object depend on locale|

### Get merchant categories

The get merchant categories API allows thirdparty system to search the merchant categories by name. 

**API**

```
public Result<ArrayList<MerchantCategoryDTO>> getMerchantCategories(String name)
```

**Input parameter(s) description**

| Name| Type | Nullable|Description |
|:--- | :---|:---|:---|
|name|String|true|name of merchant category, if name is null API will return all the merchant categories|


**Sample codes**

```
MerchantCategoryApi merchantCategoryApi = new  MerchantCategoryApi("https://api.whatspos.com/p-market-api", "RCA9MDH6YN3WSSGPW6TJ", "TUNLDZVZECHNKZ4FW07XFCKN2W0N8ZDEA5ENKZYN");
Result<ArrayList<MerchantCategoryDTO>> obj = merchantCategoryApi.getMerchantCategories("Fast Foods");
```



**Successful sample result(JSON formatted)**

```
{
	"businessCode": 0,
	"data": [{
		"id": 2,
		"name": "Fast Foods",
		"remarks": "Fast Food remarks"
	}]
}
```

The structure of the class MerchantCategoryDTO like below.


|Property Name|Type|Description|
|:---|:---|:---|
|id|Long|The id of merchant category.|
|name|String|The name of merchant category.|
|remarks|String|The remarks of merchant category.|








### Create a merchant category



**API**

```
public Result<MerchantCategoryDTO> createMerchantCategory(MerchantCategoryCreateRequest merchantCategoryCreateRequest) 
```

**Input parameter(s) description**

|Parameter Name|Type|Nullable|Description|
|:---|:---|:---|:---|
|merchantCategoryCreateRequest|MerchantCategoryCreateRequest|false|The object of create request. The structure refer to below.|

Structure of class MerchantCategoryCreateRequest

|Property Name|Type|Nullable|Description|
|:---|:---|:---|:---|
|name|String|false|Name of merchant category,max length is 128.|
|remarks|String|true|Remarks of merchant category, max length is 255.|



**Sample codes**

```
MerchantCategoryApi merchantCategoryApi = new  MerchantCategoryApi("https://api.whatspos.com/p-market-api", "RCA9MDH6YN3WSSGPW6TJ", "TUNLDZVZECHNKZ4FW07XFCKN2W0N8ZDEA5ENKZYN");
MerchantCategoryCreateRequest createRequest = new MerchantCategoryCreateRequest();
createRequest.setName("Retail");
createRequest.setRemarks("Retail");
Result<MerchantCategoryDTO> result = merchantCategoryApi.createMerchantCategory(createRequest);
```

**Client side validation failed sample result(JSON formatted)**

```
{
	"businessCode": -1,
	"validationErrors": ["Parameter merchantCategoryCreateRequest cannot be null!"]
}
```

**Server side validation failed sample result(JSON formatted)**

```
{
	"businessCode": 16001,
	"message": "Merchant category name already exists"
}
```

**Successful sample result(JSON formatted)**

```
{
	"businessCode": 0,
	"data": {
		"id": 4,
		"name": "Retail",
		"remarks": "Retail"
	}
}
```



**Possible client validation errors**
> <font color=red>Parameter merchantCategoryCreateRequest cannot be null!</font>  
> <font color=red>name:may not be empty</font>  
> <font color=red>name:length must be between 0 and 128</font>  
> <font color=red>remarks:length must be between 0 and 255</font>  
 

**Possible business codes**  

|Business Code|Message|Description|
|:---|:---|:---|
|16001|Merchant category name already exists|&nbsp;|
|16002|Merchant category name is mandatory|&nbsp;|
|16003|Merchant category name is too long|&nbsp;|
|16004|Merchant category remarks is too long|&nbsp;|


### Update a merchant category

Update merchant category API allows the thirdparty system update a exist merchant category by id.

**API**

```
public Result<MerchantCategoryDTO> updateMerchantCategory(Long merchantCategoryId, MerchantCategoryUpdateRequest merchantCategoryUpdateRequest)
```

**Input parameter(s) description**

|Parameter Name|Type|Nullable|Description|
|:---|:---|:---|:---|
|merchantCategoryId|Long|false|The id of merchant category.|
|merchantCategoryUpdateRequest|MerchantCategoryUpdateRequest|false|The update request object. The structure shows below.|

Structure of class MerchantCategoryUpdateRequest

|Property Name|Type|Nullable|Description|
|:---|:---|:---|:---|
|name|String|false|Merchant category name, max length is 128.|
|remarks|String|true|Remarks of merchant category, max length is 255.|



**Sample codes**

```
MerchantCategoryApi merchantCategoryApi = new  MerchantCategoryApi("https://api.whatspos.com/p-market-api", "RCA9MDH6YN3WSSGPW6TJ", "TUNLDZVZECHNKZ4FW07XFCKN2W0N8ZDEA5ENKZYN");
MerchantCategoryUpdateRequest updateRequest = new MerchantCategoryUpdateRequest();
updateRequest.setName("Retail");
updateRequest.setRemarks("This is a retail category");
Result<MerchantCategoryDTO> result = merchantCategoryApi.updateMerchantCategory(2L,updateRequest);
```

**Client side validation failed sample result(JSON formatted)**

```
{
	"businessCode": -1,
	"validationErrors": ["Parameter merchantCategoryId cannot be null and cannot be less than 1"]
}
```

**Server side validation failed sample result(JSON formatted)**

```
{
	"businessCode": 16001,
	"message": "Merchant category name already exists"
}
```

**Succsssful sample result(JSON formatted)**

```
{
	"businessCode": 0,
	"data": {
		"id": 2,
		"name": "Retail",
		"remarks": "This is a retail category"
	}
}
```





**Possible client validation errors**

> <font color=red>Parameter merchantCategoryId cannot be null and cannot be less than 1</font>  
> <font color=red>Parameter merchantCategoryUpdateRequest cannot be null!</font>  
> <font color=red>name:may not be empty</font>  
> <font color=red>name:length must be between 0 and 128</font>  
> <font color=red>remarks:length must be between 0 and 255</font> 



**Possible business codes**

|Business Code|Message|Description|
|:---|:---|:---|
|16000|Merchant category not found|&nbsp;|
|16001|Merchant category name already exists|&nbsp;|
|16002|Merchant category name is mandatory|&nbsp;|
|16003|Merchant category name is too long|&nbsp;|
|16004|Merchant category remarks is too long|&nbsp;|







### Delete a merchant category

Delete merchant category API allows the thirdparty system delete a exist merchant category by id.


**API**  

```
public Result<String> deleteMerchantCategory(Long merchantCategoryId)
```

**Input parameter(s) description**

|Parameter Name|Type|Nullable|Description|
|:---|:---|:---|:---|
|merchantCategoryId|Long|false|The merchant category id.|


**Sample codes**

```
MerchantCategoryApi merchantCategoryApi = new  MerchantCategoryApi("https://api.whatspos.com/p-market-api", "RCA9MDH6YN3WSSGPW6TJ", "TUNLDZVZECHNKZ4FW07XFCKN2W0N8ZDEA5ENKZYN");
Result<String> result = merchantCategoryApi.deleteMerchantCategory(12L);
```

**Client side validation failed sample result(JSON formatted)**

```
{
	"businessCode": -1,
	"validationErrors": ["Parameter merchantCategoryId cannot be null and cannot be less than 1!"]
}
```

**Server side validation failed sample result(JSON formatted)**

```
{
	"businessCode": 16000,
	"message": "Merchant category not found"
}
```

**Successful sample result(JSON formatted)**

```
{
	"businessCode": 0
}
```

**Possible client validation errors**

> <font color=red>Parameter merchantCategoryId cannot be null and cannot be less than 1!</font>  


**Possible business codes**

|Business Code|Message|Description|
|:---|:---|:---|
|16000|Merchant category not found|&nbsp;|





### Batch create a merchant categories



**API**

```
public Result<ArrayList<MerchantCategoryDTO>> batchCreateMerchantCategory(List<MerchantCategoryCreateRequest> merchantCategoryBatchCreateRequest, boolean skipExist)
```

**Input parameter(s) description**

|Parameter Name|Type|Nullable|Description|
|:---|:---|:---|:---|
|merchantCategoryBatchCreateRequest|List&lt;MerchantCategoryCreateRequest&gt;|false|List of merchant category to create|
|skipExist|boolean|true|if the value is true, then the name of category in create list exist in system will skip when create, if the value is false then all the categories in create list won't created in system. The default value is false.|





**Sample codes**

```
MerchantCategoryApi merchantCategoryApi = new  MerchantCategoryApi("https://api.whatspos.com/p-market-api", "RCA9MDH6YN3WSSGPW6TJ", "TUNLDZVZECHNKZ4FW07XFCKN2W0N8ZDEA5ENKZYN");
List<MerchantCategoryCreateRequest> batchCreateRequest = new ArrayList<MerchantCategoryCreateRequest>();
MerchantCategoryCreateRequest create1 = new MerchantCategoryCreateRequest();
create1.setName("Retail");
create1.setRemarks("Retail remarks");
batchCreateRequest.add(create1);
MerchantCategoryCreateRequest create2 = new MerchantCategoryCreateRequest();
create2.setName("Fast food");
batchCreateRequest.add(create2);
MerchantCategoryCreateRequest create3 = new MerchantCategoryCreateRequest();
create3.setName("Realty");
create3.setRemarks("Realty");
batchCreateRequest.add(create3);
Result<ArrayList<MerchantCategoryDTO>> result = merchantCategoryApi.batchCreateMerchantCategory(batchCreateRequest,false);
```

**Client side validation failed sample result(JSON formatted)**

```
{
	"businessCode": -1,
	"validationErrors": ["All the category names in the list cannot be blank!"]
}
```

**Server side validation failed sample result(JSON formatted)**

```
{
	"businessCode": 16009,
	"message": "Merchant name(s) Retail in create list already exists in system"
}
```


**Successful sample result(JSON formatted)**

```
{
	"businessCode": 0,
	"data": [{
		"id": 1,
		"name": "restaurant",
		"remarks": "restaurant"
	}, {
		"id": 2,
		"name": "Fast Foods",
		"remarks": "Fast Food remarks"
	}, {
		"id": 3,
		"name": "Retail",
		"remarks": "Retail"
	}]
}
```



**Possible client validation errors**
> <font color=red>Parameter merchantCategoryBatchCreateRequest cannot be null and empty!</font>  
> <font color=red>All the category names in the list cannot be blank!</font>  
> <font color=red>Merchant category name '[NAME]' is too long!</font>  
> <font color=red>Merchant category remarks '[REMARKS]' is too long!</font>  

Note: [NAME] and [REMARKS] will be replaced by name and remarks of merchant category in batch create list.
 

**Possible business codes**  

|Business Code|Message|Description|
|:---|:---|:---|
|16005|Batch create merchant category list is empty|&nbsp;|
|16006|All the names in batch create list cannot be empty|&nbsp;|
|16007|Merchant category names {0} in create list is too long|Note: {0} in message will be replaced by category name(s)|
|16008|Merchant category remarks {0} in create list is too longg|Note: {0} in message will be replaced by category remarks(s)|
|16009|Merchant category name(s) {0} in create list already exists in system|Note: {0} in the message will be replaced by merchante category name in the create list which is already exist in system.|
















