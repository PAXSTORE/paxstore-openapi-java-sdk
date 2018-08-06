# PAXSTORE Open API Java SDK

<br/>
<br/>

## Introduction

PAXSTORE exposes reseller, merchant and terminal related APIs for thridparty system convenience. So that the authorized thirdparty system can do operations for reseller, merchant and terminal without logging into PAXSTORE's admin console. The exposed API is RESTful formatted. PAXSTORE provides the Java SDK to simplify the remote invoke.
<br>
<br>

## Overview

All the APIs of this SDK will return the object *com.pax.market.api.sdk.java.api.base.dto.Result*.   
When using the SDK to call the REST APIs it will do basic validations like required validation and length validation for the inputted parameter(s) before the SDK send the request to remote server. And if the basic validation failed SDK will return the *Result* object with businessCode=-1 and the validationErrors.  

Below is the structure of class *com.pax.market.api.sdk.java.api.base.dto.Result*

|Property|Type|Description|
|:--|:--|:--|
|businessCode|int|The business code, it reprensent the API invoke result. 0 means invoke the API success, if it is -1 means the the parameter length and required validation failed. For other business codes please refer to the message property|
|message|String|The description of businessCode|
|validationErrors|List|Client side validation errors.|
|data|T(generic)|The actural DTO, the structure will be described in each APIs. And for pagination search the search result data will be in another property *pageInfo&lt;T&gt;*|
|pageInfo|PageInfo&lt;T&gt;|The search result. If the operation is a search operation the data property is null. For the structure of PageInfo please refer to below|
<br>
Structure of PageInfo

|Property|Type|Description|
|:--|:--|:--|
|pageNo|int|current page number|
|limit|int|page size|
|orderBy|String|order by|
|totalCount|Long|total match record number|
|hasNext|boolean|indicate whether there's next page|
|dataSet|List&lt;T&gt;|data list of current page|
<br>
Below figure listed the global business codes, those business codes may appear in every result of API call. This document won't list those business codes in the following API chapters when introducing the APIs.

|Business Code|Message|Description|
|:--|:--|:--|
|0||Successful API call.
|16105|Cannot connect to remote server!|The remote server is down or the constructor argument *baseUrl* is not correct.|
|16104|Connection timeout!|Encounter SocketTimeoutException.|
|16106|Request error!|Please check the error log or send the error log to support.|
|16103|JSON error!||
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

If the thirdparty systems want to call the REST APIs they must enable external system access in PAXSTORE admin console for the certain marketplaces and get the access key and access secret.  

Below are the step for enabling external system access and get access key and access secret.

### Step 1

Log in to PAXSTORE admin console as Super Admin or Market Admin and click General Setting in left menu.

![](/docs/assets/sc-1.png)

### Step 2

Click the left tab External System to show the external system configuration page like below screenshot.

![](/docs/assets/sc-2.png)

From above screenshot we know the external system access is disabled by default. To enable it please click the enable/disable switch. And once user clicked the switch it will pop up a confirm dialog to let user confirm.

![](/docs/assets/sc-3.png)

Click OK button to continue enabling the external system access. Click the CANCEL button to cancel current operation to keep external system access disabled.

After click OK button the external system access is enabled and the access key is shown in the page. But the access secret is replaced by asterisks for security purpose.

![](/docs/assets/sc-4.png)

### Step 3

Click the eye icon in external system configuration page to get the access secret.  It will show a dialog like below screenshot. 

![](/docs/assets/sc-5.png)

Please click OK button. And it will show the access secret instead of asterisks.

![](/docs/assets/sc-6.png)

For security purpose it only allow user to see the access secret one time. When user next time log in the access secret is replaced by asterisks again and no eye icon beside it. If user want to get the access secret again he/she must click the RESET button to get the new access key and access secret.  

Please keep the access key and access secret safely. Once the access key or access secret leaks please goto external system configuration page to disable external system access or reset the access key and access secret.



<br>
<br>

## Intergrate with SDK

Update maven's settings.xml add a new repository https://dl.bintray.com/paxstore-support/paxstore/  
Update pom.xml add SDK dependency for your java project.

```
<dependency>
    <groupId>com.pax.market</groupId>
    <artifactId>paxstore-openapi-java-sdk</artifactId>
    <version>6.0</version>
</dependency>
```

<br/>

## Test Environment

Log into Admin Center of demo marketplace using marketplace admin account. Enable the 3rd system access for current marketplace and get key and secret.  

Base URL of API: https://api.whatspos.com/p-market-api  

## Sample Code  

```
import com.pax.market.api.sdk.java.api.base.dto.Result;
import com.pax.market.api.sdk.java.api.reseller.ResellerApi;
import com.pax.market.api.sdk.java.api.reseller.ResellerApi.ResellerSearchOrderBy;
import com.pax.market.api.sdk.java.api.reseller.ResellerApi.ResellerStatus;
import com.pax.market.api.sdk.java.api.reseller.dto.ResellerPageDTO;

public class Sample {
	
	private String BASEURL = "https://api.whatspos.com/p-market-api";
	private String KEY = "YOUR KEY";
	private String SECRET = "YOUR SECRET";
	
	public Result<ResellerPageDTO> getResellers() {
    	    ResellerApi resellerApi = new  ResellerApi(BASEURL, KEY, SECRET);
    	    Result<ResellerPageDTO> resellerList = resellerApi.searchReseller(1, 10, ResellerSearchOrderBy.Name, "reseller", ResellerStatus.Active);
    	return resellerList;
	}
}
```



## License

See the [Apache 2.0 license](LICENSE) file for details.

    Copyright 2018 PAX Computer Technology(Shenzhen) CO., LTD ("PAX")

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at following link.

         http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

<br/>

## [Reseller APIs](docs/RESELLER_API.md)  

## [Merchant APIs](docs/MERCHANT_API.md)  

## [Terminal APIs](docs/TERMINAL_API.md)

## [Country Codes](docs/COUNTRY_CODE.md)




