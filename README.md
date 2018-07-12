# PAXSTORE 3rd System Java SDK

<br/>
<br/>

## Introduction

PAXSTORE exposes reseller, merchant and terminal related APIs for thridparty system convenience. So that the authorized thirdparty system can do operations for reseller, merchant and terminal without logging into PAXSTORE's admin console. The exposed API is RESTful formatted. PAXSTORE provides the Java SDK to simplify the remote invoke.
<br>
<br>

## Overview

All the APIs of this SDK will return the object *com.pax.market.api.sdk.java.api.base.dto.Result*. When using the SDK to call the REST APIs it will do basic validations like required validation and length validation for the inputted parameter(s). And if the basic validation failed SDK will return the *Result* object with businessCode=-1 and the validationErrors.  

Below is the structure of class *com.pax.market.api.sdk.java.api.base.dto.Result*

|Property|Type|Description|
|:--|:--|:--|
|businessCode|int|The business code, it reprensent the API invoke result. 0 means invoke the API success, if it is -1 means the the parameter length and required validation failed. For other business codes please refer to the message property|
|message|String|The description of businessCode|
|validationErrors|List|Client side validation errors.|
|data|T(generic)|The actural DTO, the structure will be described in each APIs. And for pagination search the search result data will be in another property *pageInfo&lt;T&gt;*|
|pageInfo|PageInfo<T>|The search result. If the operation is a search operation the data property is null. For the structure of PageInfo please refer to below|
<br>
Structure of PageInfo

|Property|Type|Description|
|:--|:--|:--|
|pageNo|int|current page number|
|limit|int|page size|
|orderBy|String|order by|
|totalCount|Long|total match record number|
|hasNext|boolean|indicate whether there's next page|
|dataSet|List&lt;T&&gt;|data list of current page|
<br>
Below figure listed the global business codes, those business codes may appear in every result of API call. This document won't list those business codes in the following API chapters when introducing the APIs.

|Business Code|Message|Description|
|:--|:--|:--|
|0||Successful API call.
|16105|Cannot connect to remote server!|The remote server is down or the constructor argument *baseUrl* is not correct.|
|16104|Connection timeout!||
|129|Authentication failed||
|104|Client key is missing or invalid||
|108|Marketplace is not available||
|109|Marketplace is not active||
|105|Client key is blocked||
|103|Access token is invalid||
|102|Access token is missing|&nbsp;|
|999|Unknown error|Unknow error, please contact with support.|

<br/>
<br/>

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

## [Reseller APIs](RESELLER_API.md)  

## [Merchant APIs](MERCHANT_API.md)  

## [Terminal APIs](TERMINAL_API.md)

## [Country Codes](COUNTRY_CODE.md)




