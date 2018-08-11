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
|:--|:--|:--|
|baseUrl|String|the base url of REST API|
|apiKey|String|the apiKey of marketplace, get this key from PAXSTORE admin console, refe to chapter Apply access rights|
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
Result<ArrayList<MerchantCategoryDTO>> obj = merchantCategoryApi.getMerchantCategories("fast food");
```



**Successful sample result**

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

The structure of the class MerchantCategoryDTO like below.


|Property Name|Type|Description|
|:--|:--|:--|
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
|:--|:--|:--|:--|
|merchantCategoryCreateRequest|MerchantCategoryCreateRequest|false|The object of create request. The structure refer to below.|

Structure of class MerchantCategoryCreateRequest

|Property Name|Type|Nullable|Description|
|:--|:--|:--|:--|
|name|String|false|Name of merchant category,max length is 128.|
|remarks|String|false|true|Remarks of merchant category, max length is 255.|



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
	"validationErrors": ["name:may not be empty", "country:may not be empty", "email:may not be empty", "resellerName:may not be empty", "contact:may not be empty"]
}
```

**Server side validation failed sample result(JSON formatted)**

```
{
	"businessCode": 1721,
	"message": "Merchant name already exists"
}
```

**Successful sample result(JSON formatted)**

```
{
	"businessCode": 0,
	"data": {
		"entityAttributeValues": {
			"tag": "suzh"
		},
		"merchantCategory": [{
			"id": 1,
			"name": "Fast Food"
		}],
		"id": 72594,
		"name": "KFC Suzhou",
		"reseller": {
			"id": 4151,
			"name": "New York"
		},
		"country": "CN",
		"contact": "sam",
		"email": "sam@pax.com",
		"phone": "444888",
		"status": "P"
	}
}
```

The type of data in result is same as the get reseller API.

**Possible client validation errors**
> <font color=red>Parameter merchantCategoryCreateRequest cannot be null!</font>  
> <font color=red>name:may not be empty</font>  
> <font color=red>country:may not be empty</font>  
> <font color=red>email:may not be empty</font>  
> <font color=red>resellerName:may not be empty</font>  
> <font color=red>contact:may not be empty</font>  
> <font color=red>name:length must be between 0 and 64</font>  
> <font color=red>email:length must be between 0 and 255</font>  
> <font color=red>resellerName:length must be between 0 and 64</font>  
> <font color=red>contact:length must be between 0 and 64</font>  
> <font color=red>country:length must be between 0 and 64</font>  
> <font color=red>phone:length must be between 0 and 32</font>  
> <font color=red>postcode:length must be between 0 and 16</font>  
> <font color=red>address:length must be between 0 and 255</font>  
> <font color=red>email:not a well-formed email address</font>  
> <font color=red>description:length must be between 0 and 3000</font>  

**Possible business codes**

|Business Code|Message|Description|
|:--|:--|:--|
|1721|Merchant name already exists|&nbsp;|
|1759|Reseller doesn't exist|&nbsp;|
|16000|Merchant category not found|&nbsp;|
|1723|Merchant name is mandatory|&nbsp;|
|1725|Merchant reseller is mandatory|&nbsp;|
|1606|Country is mandatory|&nbsp;|
|1726|Merchant contact is mandatory|&nbsp;|
|1727|Merchant email is mandatory|&nbsp;|
|1728|Merchant phone is mandatory|&nbsp;|
|1729|Merchant name is too long|&nbsp;|
|1731|Merchant reseller is too long|&nbsp;|
|1618|Postcode is too long|&nbsp;|
|1619|Address is too long|&nbsp;|
|1732|Merchant contact is too long|&nbsp;|
|1733|Merchant email is too long|&nbsp;|
|1734|Merchant phone is too long|&nbsp;|
|1736|Merchant description is too long|&nbsp;|
|1105|Email is invalid|&nbsp;|
|1112|Phone No. is invalid|&nbsp;|
|3400|Country code is invalid|&nbsp;|


### Update a merchant

Update merchant API allows the thirdparty system update a exist merchant.

**API**

```
public Result<MerchantDTO>  updateMerchant(Long merchantId, MerchantUpdateRequest merchantUpdateRequest)
```

**Input parameter(s) description**

|Parameter Name|Type|Nullable|Description|
|:--|:--|:--|:--|
|merchantId|Long|false|The id of merchant.|
|merchantUpdateRequest|MerchantUpdateRequest|false|The update request object. The structure shows below.|

Structure of class MerchantUpdateRequest

|Property Name|Type|Nullable|Description|
|:--|:--|:--|:--|
|name|String|false|Merchant name, max length is 64.|
|email|String|false|Email of merchant, max length is 255.|
|resellerName|String|false|Reseller name of merchant, max length is 64. Make sure the reseller exist.|
|contact|String|false|Contact of merchant, max length is 64.|
|country|String|false|Country code of merchant, max length is 64. Please refer to country codes table.|
|phone|String|true|Phone number of merchant, max length is 32.|
|postcode|String|true|Postcode of merchant, max length is 16.|
|address|String|true|Address of merchant, max length is 255.|
|description|String|true|Description of merchant, max length is 3000.|
|merchantCategoryNames|List&lt;String&gt;|true|Merchant categories. Make sure the categories are available.|
|entityAttributeValues|LinkedHashMap&lt;String, String&gt;|true|Dynamic attributes of merchant. Whether the attribute is required or not depend on the configuration of attribute.|


**Sample codes**

```
MerchantApi merchantApi = new  MerchantApi("https://api.whatspos.com/p-market-api", "RCA9MDH6YN3WSSGPW6TJ", "TUNLDZVZECHNKZ4FW07XFCKN2W0N8ZDEA5ENKZYN");
MerchantUpdateRequest request = new MerchantUpdateRequest();
request.setName("KFC Suzhou");
request.setResellerName("New York");
request.setCountry("CN");
request.setContact("Jack");
request.setEmail("jack@kfc.com.cn");
request.setPhone("86869999");
String[] categoryNames = {"Fast Food"};
request.setMerchantCategoryNames(Arrays.asList(categoryNames));
LinkedHashMap<String,String> attrs = new LinkedHashMap<String,String>();
attrs.put("tag", "suzh");
request.setEntityAttributeValues(attrs);
Result<MerchantDTO> result = merchantApi.updateMerchant(72594L, request);
```

**Client side validation failed sample result(JSON formatted)**

```
{
	"businessCode": -1,
	"validationErrors": ["country:may not be empty"]
}
```

**Server side validation failed sample result(JSON formatted)**

```
{
	"businessCode": 1112,
	"message": "Phone No. is invalid"
}
```

**Succsssful sample result(JSON formatted)**

```
{
	"businessCode": 0,
	"data": {
		"entityAttributeValues": {
			"tag": "suzh"
		},
		"merchantCategory": [{
			"id": 1,
			"name": "Fast Food"
		}],
		"id": 72594,
		"name": "KFC Suzhou",
		"reseller": {
			"id": 4151,
			"name": "New York"
		},
		"country": "CN",
		"contact": "Jack",
		"email": "jack@kfc.com.cn",
		"phone": "86869999",
		"status": "P"
	}
}
```



The data type in result is same as get merchant API.

**Possible client validation errors**

> <font color=red>Parameter merchantCreateRequest cannot be null!</font>  
> <font color=red>name:may not be empty</font>  
> <font color=red>country:may not be empty</font>  
> <font color=red>email:may not be empty</font>  
> <font color=red>resellerName:may not be empty</font>  
> <font color=red>contact:may not be empty</font>  
> <font color=red>name:length must be between 0 and 64</font>  
> <font color=red>email:length must be between 0 and 255</font>  
> <font color=red>resellerName:length must be between 0 and 64</font>  
> <font color=red>contact:length must be between 0 and 64</font>  
> <font color=red>country:length must be between 0 and 64</font>  
> <font color=red>phone:length must be between 0 and 32</font>  
> <font color=red>postcode:length must be between 0 and 16</font>  
> <font color=red>address:length must be between 0 and 255</font>  
> <font color=red>email:not a well-formed email address</font>  
> <font color=red>description:length must be between 0 and 3000</font>  


**Possible business codes**

|Business Code|Message|Description|
|:--|:--|:--|
|1720|Merchant doesn't exist|&nbsp;|
|1721|Merchant name already exists|&nbsp;|
|1759|Reseller doesn't exist|&nbsp;|
|16000|Merchant category not found|&nbsp;|
|1723|Merchant name is mandatory|&nbsp;|
|1606|Country is mandatory|&nbsp;|
|1726|Merchant contact is mandatory|&nbsp;|
|1727|Merchant email is mandatory|&nbsp;|
|1728|Merchant phone is mandatory|&nbsp;|
|1725|Merchant reseller is mandatory|&nbsp;|
|1729|Merchant name is too long|&nbsp;|
|1618|Postcode is too long|&nbsp;|
|1619|Address is too long|&nbsp;|
|1732|Merchant contact is too long|&nbsp;|
|1733|Merchant email is too long|&nbsp;|
|1734|Merchant phone is too long|&nbsp;|
|1736|Merchant description is too long|&nbsp;|
|1105|Email is invalid|&nbsp;|
|1112|Phone No. is invalid|&nbsp;|
|3400|Country code is invalid|&nbsp;|
|1927|The merchant is not inactive,reseller cannot be updated!|&nbsp;|
|1759|Reseller doesn't exist|&nbsp;|
|1773|The associated reseller is not activate|&nbsp;|





### Activate a merchant

Activate merchant API allows the thirdparty system activate a inactive merchant. 
If activate successfully there's no response content from remote server.

**API**

```
public Result<String> activateMerchant(Long merchantId)
```

**Input parameter(s) description**

|Parameter Name|Type|Nullable|Description|
|:--|:--|:--|:--|
|merchantId|Long|false|The merchant id.|

**Sample codes**

```
MerchantApi merchantApi = new  MerchantApi("https://api.whatspos.com/p-market-api", "RCA9MDH6YN3WSSGPW6TJ", "TUNLDZVZECHNKZ4FW07XFCKN2W0N8ZDEA5ENKZYN");
Result<String> result = merchantApi.activateMerchant(72590L);
```

**Client side validation failed sample result(JSON formatted)**

```
{
	"businessCode": -1,
	"validationErrors": ["Parameter merchantId cannot be null and cannot be less than 1!"]
}
```

**Server side validation failed sample result(JSON formatted)**

```
{
	"businessCode": 1892,
	"message": "The merchant has already been activated!"
}
```


**Successful sample result(JSON formatted)**

```
{
	"businessCode": 0
}
```


**Possible client validation errors**
> <font color=red>Parameter merchantId cannot be null and cannot be less than 1!</font>  


**Possible business codes**

|Business Code|Message|Description|
|:--|:--|:--|
|1720|Merchant doesn't exist|&nbsp;|
|1759|Reseller doesn't exist|&nbsp;|
|1773|The associated reseller is not activate|&nbsp;|
|1892|The merchant has already been activated!|&nbsp;|


### Disable a merchant

Disable merchant API allows the thirdparty system disable a Active/Pendding merchant.
If disable successfully there's not response content from remote server.

**API**

```
public Result<String> disableMerchant(Long merchantId)
```

**Input parameter(s) description**

|Parameter Name|Type|Nullable|Description|
|:--|:--|:--|:--|
|merchantId|Long|false|The merchant id.|


**Sample codes**

```
MerchantApi merchantApi = new  MerchantApi("https://api.whatspos.com/p-market-api", "RCA9MDH6YN3WSSGPW6TJ", "TUNLDZVZECHNKZ4FW07XFCKN2W0N8ZDEA5ENKZYN");
Result<String> result = merchantApi.disableMerchant(72594L);
```

**Client side validation failed sample result(JSON formatted)**

```
{
	"businessCode": -1,
	"validationErrors": ["Parameter merchantId cannot be null and cannot be less than 1!"]
}
```

**Server side validation failed sample result(JSON formatted)**

```
{
	"businessCode": 1887,
	"message": "The merchant is not active,unable to disable!"
}
```

**Successful sample result(JSON formatted)**

```
{
	"businessCode": 0
}
```



**Possible client validation errors**

> <font color=red>Parameter merchantId cannot be null and cannot be less than 1!</font>  


**Possible business codes**

|Business Code|Message|Description|
|:--|:--|:--|
|1720|Merchant doesn't exist|&nbsp;|
|1887|The merchant is not active,unable to disable!|&nbsp;|
|1797|The merchant has active terminals|&nbsp;|



### Delete a merchant

Delete merchant API allows the thirdparty system delete a exist merchant.
If delete successfully there's no response content from remote server.

**API**

```
public Result<String> deleteMerchant(Long merchantId)
```

**Input parameter(s) description**

|Parameter Name|Type|Nullable|Description|
|:--|:--|:--|:--|
|merchantId|Long|false|The merchant id.|


**Sample codes**

```
MerchantApi merchantApi = new  MerchantApi("https://api.whatspos.com/p-market-api", "RCA9MDH6YN3WSSGPW6TJ", "TUNLDZVZECHNKZ4FW07XFCKN2W0N8ZDEA5ENKZYN");
Result<String> result = merchantApi.deleteMerchant(72593L);
```

**Client side validation failed sample result(JSON formatted)**

```
{
	"businessCode": -1,
	"validationErrors": ["Parameter merchantId cannot be null and cannot be less than 1!"]
}
```

**Server side validation failed sample result(JSON formatted)**

```
{
	"businessCode": 1720,
	"message": "Merchant doesn't exist"
}
```

**Successful sample result(JSON formatted)**

```
{
	"businessCode": 0
}
```

**Possible client validation errors**

> <font color=red>Parameter merchantId cannot be null and cannot be less than 1!</font>  


**Possible business codes**

|Business Code|Message|Description|
|:--|:--|:--|
|1720|Merchant doesn't exist|&nbsp;|
|1876|The merchant is active,unable to delete!|&nbsp;|
|1786|The merchant has been used by terminal|&nbsp;|






















