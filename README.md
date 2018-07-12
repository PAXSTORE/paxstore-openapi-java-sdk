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

## [Reseller APIs](RESELLER_API.md)  

## [Merchant APIs](MERCHANT_API.md)  

## [Terminal APIs](TERMINAL_API.md)

## [Country Code](COUNTRY_CODE.md)




