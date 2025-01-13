# PAXSTORE Open API Java SDK


<br/>
<br/>

## Security Announcement  
This Open API SDK depends on log4j-1.2.7. It won't affected by the vulnerability CVE-2021-44228. But a similar vulnerability(CVE-2021-4104) is found in log4j 1.2.x. If the developer is not using JMSAppender it won't affected. Also developer can remove JMSAppender using command(zip -q -d log4j-1.2.17.jar org/apache/log4j/net/JMSAppender.class). Below is the description about CVE-2021-44228.  

```  
JMSAppender in Log4j 1.2 is vulnerable to deserialization of untrusted data when the attacker has write access to the Log4j configuration. The attacker can provide TopicBindingName and TopicConnectionFactoryBindingName configurations causing JMSAppender to perform JNDI requests that result in remote code execution in a similar fashion to CVE-2021-44228. Note this issue only affects Log4j 1.2 when specifically configured to use JMSAppender, which is not the default. Apache Log4j 1.2 reached end of life in August 2015. Users should upgrade to Log4j 2 as it addresses numerous other issues from the previous versions.
```

The SDK is using slf4j as the log facade and the log4j as the log implementation. Developers can using other log implementation to replace the log4j.
From version 8.3.0 we removed log4j dependecy from this SDK, developer need to choose the log implementation by self.


## Introduction

PAXSTORE exposes reseller, merchant and terminal related APIs for thirdparty system convenience. So that the authorized thirdparty system can do operations for reseller, merchant and terminal without logging into PAXSTORE's admin console. The exposed API is RESTful formatted. PAXSTORE provides the Java SDK to simplify the remote invoke.  



## [Documents for Old Versions](docs/DOCUMENTS_OLD.md)


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
|rateLimit|string(int format)|The maximum number of requests you're permitted to make per 10 minutes.|
|rateLimitRemain|string(int format)|The number of requests remaining in the current rate limit window.|
|rateLimitReset|string(long format)|The time at which the current rate limit window resets in UTC epoch millisecond.|
|rateLimit|string(int format)|The maximum number of requests you're permitted to make per 10 minutes.|
|rateLimitRemain|string(int format)|The number of requests remaining in the current rate limit window.|
|rateLimitReset|string(long format)|The time at which the current rate limit window resets in UTC epoch millisecond.|

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
|0||Successful API call.|
|101|Invalid request method|The request method is not correct|
|102|Access token is missing||
|103|Access token is invalid||
|104|Client key is missing or invalid||
|105|External System Integration not enable||
|108|Marketplace is not available||
|109|Marketplace is not active||
|113|Request parameter is missing or invalid|                                                              |
|129|Authentication failed||
|130|Marketplace has not been activated||
|131|Insufficient access right||
|136|Current marketplace is in migration progress, please try later||
|429|Too many request|Request number exceed the maximum number in the current rate limit window|
|997|Malformed or illegal request|The JSON in request body is not a valid JSON|
|998|Bad request||
|999|Unknown error|Unknow error, please contact with support.|
|1005|Marketplace not found||
|1006|Marketplace is expired||
|1309|Marketplace has been suspended||
|1759|Reseller doesn't exist||
|9101|License has been expired||
|9102|License is not effective||
|9103|Licensee mismatched||
|9104|License not found||
|13103|You are not allowed to call this API because of calling frequently||
|13107|IP address not found||
|13108|Source IP not allowed||
|16103|JSON error!||
|16104|Connection timeout!|Encounter SocketTimeoutException.|
|16105|Cannot connect to remote server!|The remote server is down or the constructor argument *baseUrl* is not correct.|
|16106|Request error!|Please check the error log or send the error log to support.|
|16111|BaseUrl not correct!|The API BaseUrl may not correct|

<br/>
<br/>


## Request rate limit

The rate of successful authorised api requests is 3000 per 10min, which means that all third party systems using the same External System Access Key and External System Access Secre from one same reseller share the quota of 3000 requests per 10 minutes.
Successfully authorised connections must be used with the correct External System Access Key and External System Access Secret, and the Exit IP of the external system integration server must be successfully configured in the External System Configuration.

Failed requests with unsuccessful authorisation will first report an error code as below, and the rate of failed requests with unsuccessful authorisation is limited to 20 requests every 30 minutes, and after reaching 20 requests in 30mins, a mandatory wait of 30 minutes is required for the next attempt, in order to prevent customers from unlimited requests for guessing the real key Secret or other important information, and protect the security of the customer's system integration.

The returned HTTP headers of any API request show your current rate limit status:

|Header Name|Description|
|:--|:--|
|X-RateLimit-Limit|The maximum number of requests you're permitted to make per 10 minutes|
|X-RateLimit-Remaining|The number of requests remaining in the current rate limit window|
|X-RateLimit-Reset|The time at which the current rate limit window resets in UTC epoch millisecond|

The above 3 response headers are encapsulated in class of *com.pax.market.api.sdk.java.api.base.dto.Result*. 

If you exceed the rate limit, an error response code is 429:


## Configure API connect timeout and read timeout

The default value of connect timeout and read timeout is 30000(milliseconds). The connect/read timeout configuration is SDK level not API level.
The default retry times is 5. And the max retry times is 5.


Sample of configure connect timeout, read timeout and retry times

```
AppApi appApi = new AppApi(TestConstants.API_BASE_URL, TestConstants.API_KEY, TestConstants.API_SECRET);
appApi.setSDKConnectTimeout(5000);
appApi.setSDKReadTimeout(5000);
appApi.setRetryTimes(3);

```



## Apply access rights

If the thirdparty systems want to call the REST APIs they must enable external system access in PAXSTORE admin console for the certain marketplaces and get the access key and access secret.  

Below are the step for enabling external system access and get access key and access secret.

### Step 1

Log in to PAXSTORE admin console as Super Admin or Market Admin and click **General Setting** in left menu.



### Step 2

Click the left tab **External System** to show the external system configuration page.

From page we know the external system access is disabled by default. To enable it please click the enable/disable switch. And once user clicked the switch it will pop up a confirm dialog to let user confirm.


Click OK button to continue enabling the external system access. Click the CANCEL button to cancel current operation to keep external system access disabled.

After click OK button the external system access is enabled and the access key is shown in the page. But the access secret is replaced by asterisks for security purpose.


### Step 3

Click the eye icon in external system configuration page to get the access secret.  It will show a dialog. 

Please click OK button. And it will show the access secret instead of asterisks.

For security purpose it only allow user to see the access secret one time. When user next time log in the access secret is replaced by asterisks again and no eye icon beside it. If user want to get the access secret again he/she must click the RESET button to get the new access key and access secret.  

Please keep the access key and access secret safely. Once the access key or access secret leaks please goto external system configuration page to disable external system access or reset the access key and access secret.



<br>
<br>

## Integrate with SDK  

The minimal JDK version is 1.8.   
To integrate with this SDK please add below dependency by updating the pom.xml file in your maven project.

```
<dependency>
    <groupId>com.whatspos.sdk</groupId>
    <artifactId>3rdsys-openapi</artifactId>
    <version>9.7.0</version>
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

## [Changelog](docs/CHANGELOG.md)



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

## [Merchant Category APIs](docs/MERCHANT_CATEGORY_API.md)

## [Merchant Variable APIs](docs/MERCHANT_VARIABLE_API.md)

## [Terminal Apk APIs](docs/TERMINAL_APK_API.md)

## [Terminal Firmware APIs](docs/TERMINAL_FIRMWARE_API.md)

## [App APIs](docs/APP_API.md)

## [Push Template APIs](docs/TERMINAL_APK_PARAMETER_API.md)

## [Terminal Estate APIs](docs/TERMINAL_ESTATE_API.md)

## [Terminal Variable APIs](docs/TERMINAL_VARIABLE_API.md)

## [Push History APIs](docs/PUSH_HISTORY_API.md)

## [Terminal Group APIs](docs/TERMINAL_GROUP_API.md)  

## [Terminal Group Push APIs](docs/TERMINAL_GROUP_APK_API.md)

## [Entity Attribute APIs](docs/ENTITY_ATTRIBUTE_API.md)

## [Terminal Group RKI APIs](docs/TERMINAL_GROUP_RKI_API.md)

## [Terminal RKI APIs](docs/TERMINAL_RKI_API.md)

## [GoInsight APIs](docs/GOINSIGHT_API.md)

## [Terminal Geofence WhiteList APIs](docs/TERMINAL_GEOFENCE_WHITELIST_API.md)

## [EMM App APIs](docs/EMM_APP_API.md)

## [EMM Device APIs](docs/EMM_DEVICE_API.md)

## [EMM Device Detail APIs](docs/EMM_DEVICE_DETAIL_API.md)

## [EMM Policy APIs](docs/EMM_POLICY_API.md)





