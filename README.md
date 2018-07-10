# PAXSTORE 3rd System Java SDK

1. Introduction
2. Overview
3. Apply access rights
4. Intergrate with SDK
4. Manage resellers
5. Manage merchants
6. Manage terminals
7. Global business codes

## Introduction

PAXSTORE exposes some reseller, merchant and terminal related APIs so that the external application can do operations on reseller, merchant and terminal without logging into PAXSTORE'a admin console. The exposed API is REST formatted. PAXSTORE provides the SDK to simplify the remote invoke.
<br>
<br>

## Overview

Description of SDK return object *com.pax.market.api.sdk.java.api.base.dto.Result*

|Property|Type|Description|
|:--|:--|:--|
|businessCode|int|the business code, it reprensent the API invoke result. 0 means invoke the API success, if it is -1 means the the parameter length and required validation failed. For other business codes please refer to the message property|
|message|String|the description of businessCode|
|validationErrors|List|validation errors. Before SDK send requests to remote server SDK will do basic validations like isrequired validation, length validation|
|data|T(generic)|the actural DTO, for search operation the result is in property pageInfo, and data property is null|
|pageInfo|PageInfo<T>|the search result. If the operation is a search operation the data property is null. For the structure of PageInfo please refer to below|

Structure of PageInfo

|Property|Type|Description|
|:--|:--|:--|
|pageNo|int|current page number|
|limit|int|page size|
|orderBy|String|order by|
|totalCount|Long|total match record number|
|hasNext|boolean|indicate whether there's next page|
|dataSet|List|data list of current page|



## Apply access rights

<br>
<br>

## Intergrate with SDK

Update pom.xml add SDK dependency for your java project.

```
<dependency>
	<groupId>p-market-sdk</groupId>
	<artifactId>com.pax.market</artifactId>
    <version>6.00.00</version>
</dependency>
```

<br/>

## Manage resellers

All the reseller related APIs are encapsulated in the class com.pax.market.api.sdk.java.api.reseller.ResellerApi.

Constructores of ResellerAPI

```
public ResellerApi(String baseUrl, String appKey, String appSecret);
public ResellerApi(String baseUrl, String appKey, String appSecret, Locale locale);
```

Constructor parameters

|Name|Type|Description|
|:--|:--|:--|
|baseUrl|String|the base url of REST API|
|appKey|String|the appkey of marketplace, get this key from PAXSTORE admin console, refe to chapter Apply access rights|
|appSecret|String|appSecret, get app secret from PAXSTORE admin console, refer to chapter Apply access rights|
|locale|Locale|the locale, the default locale is Locale.ENGLISH, the language of message and errors in return object is depend on locale|

<br>

### Search resellers

API

```
public Result<ResellerPageDTO>  searchReseller(int pageNo, int pageSize, String orderBy, String name, ResellerStatus status)
```

<br>
Input parameter(s) description

| Name| Type | Nullable|Description |
|:--- | :---|:---|:---|
|pageNo|int|false|page number, value must >=1|
|pageSize|int|false|the record number per page, range is 1 to 1000|
|orderBy|String|true|the sort order property|
|status|ResellerStatus|true|the reseller status<br/> the value can be ResellerStatus.Active, ResellerStatus.Inactive, ResellerStatus.Suspend|

<br>
Sample codes

```
ResellerApi resellerApi = new  ResellerApi("http://localhost:8080/p-market-api", "RCA9MDH6YN3WSSGPW6TJ", "TUNLDZVZECHNKZ4FW07XFCKN2W0N8ZDEA5ENKZYN");
Result<ResellerPageDTO> result = resellerApi.searchReseller(1, 10, null, "super", ResellerStatus.Suspend);
```

<br>
Sample result(JSON formatted)

```
{
	"businessCode": 0,
	"pageInfo": {
		"pageNo": 1,
		"limit": 10,
		"totalCount": 2,
		"hasNext": false,
		"dataSet": [{
			"id": 17850,
			"name": "FVFFF",
			"phone": "87879696",
			"country": "CN",
			"contact": "FFF",
			"email": "FF@1234.COM",
			"status": "S"
		}, {
			"id": 8736,
			"name": "Max",
			"phone": "123",
			"country": "CN",
			"postcode": "123",
			"contact": "Max",
			"email": "zhihao_w@qq.com",
			"status": "S"
		}]
	}
}
```

<br>

The type of object in dataSet is ResellerPageDTO. And the structure shows like below.

|Property Name|Type|Description|
|:--|:--|:--|
|id|Long|the id of reseller|
|name|String|the name of reseller|
|phone|String|the phone number of reseller|
|country|String|the country code|
|postcode|String|the postcode of reseller|
|email|String|the email of reseller|
|status|String|the status of reseller, value can be one of A(Active), P(Pendding) and S(Suspend)|
<br>

Possible validation errors

> <font color="red">pageSize:must be greater than or equal to 1</font><br>
> <font color="red">pageNo:must be greater than or equal to 1</font><br>
> <font color="red">pageSize:must be less than or equal to 1000</font>




### Get a reseller

Get reseller by reseller id. 

API

```
public Result<ResellerDTO>  getReseller(Long resellerId)
```

Input parameter(s) description

|Parameter Name|Type|Nullable|Description|
|:--|:--|:--|:--|
|resellerId|Long|false|the id of reseller|

Sample codes

```
ResellerApi resellerApi = new  ResellerApi("http://localhost:8080/p-market-api", "RCA9MDH6YN3WSSGPW6TJ", "TUNLDZVZECHNKZ4FW07XFCKN2W0N8ZDEA5ENKZYN");
Result<ResellerDTO> result = resellerApi.getReseller(17850L);
```

Sample result(JSON formatted)

```
{
	"businessCode": 0,
	"data": {
		"entityAttributeValues": {
			"111": "hello"
		},
		"parent": {
			"id": 4151,
			"name": "New York"
		},
		"id": 17850,
		"name": "FVFFF",
		"phone": "87879696",
		"country": "CN",
		"contact": "FFF",
		"email": "sum@gmail.com",
		"status": "S"
	}
}
```

<br>
The type of data is ResellerDTO, and the structure shows below.

|Name|Type|Description|
|:--|:--|:--|
|id|Long|the id of reseller|
|name|String|the name of reseller|
|phone|String|the phone number of reseller|
|country|String|the country code|
|postcode|String|the postcode of reseller|
|email|String|the email of reseller|
|status|String|the status of reseller, value can be one of A(Active), P(Pendding) and S(Suspend)|
|parent|SimpleResellerDTO|reseller's parent|
|entityAttributeValues|LinkedHashMap&lt;String, String&gt;|dynamic attributes|
<br>
Structure of SimpleResellerDTO

|Name|Type|Description|
|:--|:--|:--|
|id|Long|the id of reseller|
|name|String|the name of reseller|



Possible validation errors


> <font color="red">Parameter resellerId cannot be null and cannot be less than 1!</font>


Possible business codes

|Business Code|Message|Description|
|:--|:--|:--|
|1759|Reseller doesn't exist|&nbsp;|

### Create a reseller

API

```
public Result<ResellerDTO>  createReseller(ResellerCreateRequest resellerCreateRequest) 
```

Input parameter(s) description

|Parameter Name|Type|Nullable|Description|
|:--|:--|:--|:--|
|resellerCreateRequest|ResellerCreateRequest|false|the create request object, the structure like below|

Structure of class ResellerCreateRequest

|Property Name|Type|Nullable|Description|
|:--|:--|:--|:--|
|name|String|false|Name of reseller, max length is 64.|
|email|String|false|Email of reseller, max length is 255.|
|country|String|false|Country code of reseller, max length is 64.|
|contact|String|false|contact of reseller, max length is 64.|
|phone|String|true|Phone number of reseller, max length is 32. Sample value 400-86554555.|
|postcode|String|true|Post code, max length is 32. Sample value 510250.|
|address|String|true|Address of reseller, max length is 255.|
|company|String|true|Company of reseller, max length is 255.|
|parentResellerName|String|true|Parent reseller name, if it is empty will set the root reseller of current marketplace as the parent reseller|
|entityAttributeValues|LinkedHashMap&lt;String, String&gt;|false|Dynamic attributes. Whether the attributes is required or not depends on the attributes configuration.|

Sample codes

```
ResellerApi resellerApi = new  ResellerApi("http://localhost:8080/p-market-api", "RCA9MDH6YN3WSSGPW6TJ", "TUNLDZVZECHNKZ4FW07XFCKN2W0N8ZDEA5ENKZYN");
ResellerCreateRequest request = new ResellerCreateRequest();
request.setName("reseller_abc");
request.setContact("Sam");
request.setCountry("CN");
request.setEmail("sam@gmail.com");
request.setPhone("87879696");
request.setParentResellerName("New York");
LinkedHashMap<String,String> attrs = new LinkedHashMap<String,String>();
attrs.put("code", "ABC");
request.setEntityAttributeValues(attrs);
Result<ResellerDTO> result = resellerApi.createReseller(request);
```

Sample result(JSON formatted)

```
{
	"businessCode": 0,
	"data": {
		"entityAttributeValues": {
			"111": "tan2"
		},
		"parent": {
			"id": 4151,
			"name": "New York"
		},
		"id": 51741,
		"name": "reseller_abc",
		"phone": "87879696",
		"country": "CN",
		"contact": "Sam",
		"email": "sam@gmail.com",
		"status": "P"
	}
}
```

Type of data is ResellerDTO, same as the API get reseller.

Possible validation errors
> <font color="red">Parameter resellerCreateRequest cannot be null!</font><br/>
> <font color="red">contact:may not be empty</font><br/>
> <font color="red">email:may not be empty</font><br/>
> <font color="red">name:may not be empty</font><br/>
> <font color="red">country:may not be empty</font><br/>
> <font color="red">name:length must be between 0 and 64</font><br/>
> <font color="red">email:length must be between 0 and 255</font><br/>
> <font color="red">country:length must be between 0 and 64</font><br/>
> <font color="red">contact:length must be between 0 and 64</font><br/>
> <font color="red">phone:length must be between 0 and 32</font><br/>
> <font color="red">postcode:length must be between 0 and 16</font><br/>
> <font color="red">address:length must be between 0 and 255</font><br/>
> <font color="red">company:length must be between 0 and 255</font><br/>
> <font color="red">parentResellerName:length must be between 0 and 64</font><br/>
> <font color="red">email:not a well-formed email address</font>


Possible business codes

|Business Code|Message|Description|
|:--|:--|:--|
|1778|Parent reseller not found|&nbsp;|
|1779|Parent reseller is not active|&nbsp;|
|1782|At most 10 level resellers are supported|&nbsp;|
|1762|Reseller name is mandatory|&nbsp;|
|1763|Reseller contact is mandatory|&nbsp;|
|1764|Reseller phone is mandatory|&nbsp;|
|1765|Reseller email is mandatory|&nbsp;|
|1606|Country is mandatory|&nbsp;|
|1767|Reseller name is too long|&nbsp;|
|1768|Reseller contact is too long|&nbsp;|
|1769|Reseller phone is too long|&nbsp;|
|1770|Reseller email is too long|&nbsp;|
|1618|Postcode is too long|&nbsp;|
|1619|Address is too long|&nbsp;|
|1771|Reseller company is too long|&nbsp;|
|1105|Email is invalid|&nbsp;|
|1112|Phone No. is invalid|&nbsp;|
|1624|The name cannot contain special characters|&nbsp;|
|3400|Country code is invalid|&nbsp;|

### Update a reseller

API

```
public Result<ResellerDTO>  updateReseller(Long resellerId, ResellerUpdateRequest resellerUpdateRequest)
```

Input parameter(s) description

|Parameter Name|Type|Nullable|Description|
|:--|:--|:--|:--|
|resellerId|Long|false|Reseller's id.|
|resellerUpdateRequest|ResellerUpdateRequest|false|The update request object, the structure like below|

Structure of class ResellerUpdateRequest

|Property Name|Type|Nullable|Description|
|:--|:--|:--|:--|
|name|String|false|Name of reseller, max length is 64.|
|email|String|false|Email of reseller, max length is 255.|
|country|String|false|Country code of reseller, max length is 64.|
|contact|String|false|contact of reseller, max length is 64.|
|phone|String|true|Phone number of reseller, max length is 32. Sample value 400-86554555.|
|postcode|String|true|Post code, max length is 32. Sample value 510250.|
|address|String|true|Address of reseller, max length is 255.|
|company|String|true|Company of reseller, max length is 255.|
|parentResellerName|String|true|Parent reseller name, if it is empty will set the root reseller of current marketplace as the parent reseller|
|entityAttributeValues|LinkedHashMap&lt;String, String&gt;|false|Dynamic attributes. Whether the attributes is required or not depends on the attributes configuration.|

Sample codes

```
ResellerApi resellerApi = new  ResellerApi("http://localhost:8080/p-market-api", "RCA9MDH6YN3WSSGPW6TJ", "TUNLDZVZECHNKZ4FW07XFCKN2W0N8ZDEA5ENKZYN");
ResellerUpdateRequest request = new ResellerUpdateRequest();
request.setName("FVFFF");
request.setContact("FFF");
request.setCountry("CN");
request.setEmail("FF@1234.COM");
request.setPhone("87879696");
request.setParentResellerName("New York");
LinkedHashMap<String,String> attrs = new LinkedHashMap<String,String>();
attrs.put("code", "XY");
request.setEntityAttributeValues(attrs);
Result<ResellerDTO> result = resellerApi.updateReseller(17850L, request);
```

Sample result

```
{
	"businessCode": 0,
	"data": {
		"entityAttributeValues": {
			"code": "XY"
		},
		"parent": {
			"id": 4151,
			"name": "New York"
		},
		"id": 17850,
		"name": "FVFFF",
		"phone": "87879696",
		"country": "CN",
		"contact": "FFF",
		"email": "FF@1234.COM",
		"status": "S"
	}
}
```

Type of data is ResellerDTO, same as the API get reseller.

Possible validation errors
> <font color="red">Parameter resellerId cannot be null and cannot be less than 1!</font><br/>
> <font color="red">Parameter resellerUpdateRequest cannot be null!</font><br/>
> <font color="red">contact:may not be empty</font><br/>
> <font color="red">email:may not be empty</font><br/>
> <font color="red">name:may not be empty</font><br/>
> <font color="red">country:may not be empty</font><br/>
> <font color="red">name:length must be between 0 and 64</font><br/>
> <font color="red">email:length must be between 0 and 255</font><br/>
> <font color="red">country:length must be between 0 and 64</font><br/>
> <font color="red">contact:length must be between 0 and 64</font><br/>
> <font color="red">phone:length must be between 0 and 32</font><br/>
> <font color="red">postcode:length must be between 0 and 16</font><br/>
> <font color="red">address:length must be between 0 and 255</font><br/>
> <font color="red">company:length must be between 0 and 255</font><br/>
> <font color="red">parentResellerName:length must be between 0 and 64</font><br/>
> <font color="red">email:not a well-formed email address</font>

<br>
Possible business codes

|Business Code|Message|Description|
|:--|:--|:--|
|1759|Reseller doesn't exist|&nbsp;|
|1778|Parent reseller not found|&nbsp;|
|1762|Reseller name is mandatory|&nbsp;|
|1764|Reseller phone is mandatory|&nbsp;|
|1606|Country is mandatory|&nbsp;|
|1763|Reseller contact is mandatory|&nbsp;|
|1765|Reseller email is mandatory|&nbsp;|
|1767|Reseller name is too long|&nbsp;|
|1769|Reseller phone is too long|&nbsp;|
|1768|Reseller contact is too long|&nbsp;|
|1618|Postcode is too long|&nbsp;|
|1619|Address is too long|&nbsp;|
|1771|Reseller company is too long|&nbsp;|
|1770|Reseller email is too long|&nbsp;|
|1105|Email is invalid|&nbsp;|
|1112|Phone No. is invalid|&nbsp;|
|1624|The name cannot contain special characters|&nbsp;|
|3400|Country code is invalid|&nbsp;|





### Activate a reseller

API

```
public Result<String> activateReseller(Long resellerId)
```

Input parameter(s) description

|Parameter Name|Type|Nullable|Description|
|:--|:--|:--|:--|
|resellerId|Long|false|The reseller's id.|

Sample codes

```
ResellerApi resellerApi = new  ResellerApi("http://localhost:8080/p-market-api", "RCA9MDH6YN3WSSGPW6TJ", "TUNLDZVZECHNKZ4FW07XFCKN2W0N8ZDEA5ENKZYN");
Result<String> result = resellerApi.activateReseller(51739L);
```

Sample result

```
{
	"businessCode": 0
}
```

Note: There's no response from remote server if activate operation success

Possible validation errors
> <font color="red">Parameter resellerId cannot be null and cannot be less than 1!</font>



Possible business codes

|BusinessCode|Message|Description|
|:--|:--|:--|
|1759|Reseller doesn't exist|The input reseller id not correct.|
|1891|The reseller has already been activated!|&nbsp;|
|1894|The reseller's parent is not active|&nbsp;|



### Disable a reseller

API

```
public Result<String> disableReseller(Long resellerId)
```

Input parameter(s) description

|Parameter Name|Type|Nullable|Description|
|:--|:--|:--|:--|
|resellerId|Long|false|The reseller's id.|

Sample codes

```
ResellerApi resellerApi = new  ResellerApi("http://localhost:8080/p-market-api", "RCA9MDH6YN3WSSGPW6TJ", "TUNLDZVZECHNKZ4FW07XFCKN2W0N8ZDEA5ENKZYN");
Result<String> result = resellerApi.disableReseller(51739L);
```

Sample result

```
{
	"businessCode": 0
}
```

Note: There's no response from remote server if disable operation success

Possible validation errors
> <font color="red">Parameter resellerId cannot be null and cannot be less than 1!</font>



Possible business codes

|BusinessCode|Message|Description|
|:--|:--|:--|
|1759|Reseller doesn't exist|The input reseller id not correct.|
|1886|The reseller is not active,unable to disable!|&nbsp;|
|1793|The reseller has active merchants|&nbsp;|
|1794|The reseller has active terminals|&nbsp;|
|1795|The reseller has active terminal groups|&nbsp;|
|1781|The reseller has active sub-resellers|&nbsp;|

### Delete a reseller

API

```
public Result<String> deleteReseller(Long resellerId)
```

Input parameter(s) description

|Parameter Name|Type|Nullable|Description|
|:--|:--|:--|:--|
|resellerId|Long|false|The reseller's id.|

Sample codes

```
ResellerApi resellerApi = new  ResellerApi("http://localhost:8080/p-market-api", "RCA9MDH6YN3WSSGPW6TJ", "TUNLDZVZECHNKZ4FW07XFCKN2W0N8ZDEA5ENKZYN");
Result<String> result = resellerApi.deleteReseller(51739L);
```

Sample result

```
{
	"businessCode": 0
}
```

Note: There's no response from remote server if delete operation success

Possible validation errors

> <font color="red">Parameter resellerId cannot be null and cannot be less than 1!</font>

Possible business codes

|BusinessCode|Message|Description|
|:--|:--|:--|
|1759|Reseller doesn't exist|The input reseller id not correct.|
|1875|The reseller is active,unable to delete!|&nbsp;|
|1775|Not allowed to delete the reseller of current user|&nbsp;|
|1761|Reseller has been used by merchant|&nbsp;|
|1785|The reseller has been used by terminal|&nbsp;|
|1788|The reseller has been used by terminal group|&nbsp;|
|1780|The reseller has sub-resellers|&nbsp;|


[merchant](merchant_api.md)




