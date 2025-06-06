## EMM Application APIs

EMM Application APIs allow thirdparty system search EMM apps, create EMM app, get EMM app detail,
remove EMM app, search subscribed EMM apps, subscribe EMM app, unsubscribe EMM app, get EMM app permission list.

All the EMM app APIs are in the class *com.pax.market.api.sdk.java.api.emm.emmApp.EmmAppApi*.

**Constructors of EmmAppAPI**

```
public EmmAppApi(String baseUrl, String apiKey, String apiSecret);
```

Constructor parameters description


| Name      | Type   | Description                                                                                               |
| :---------- | :------- | :---------------------------------------------------------------------------------------------------------- |
| baseUrl   | String | the base url of REST API                                                                                  |
| apiKey    | String | the apiKey of marketplace, get this key from PAXSTORE admin console, refer to chapter Apply access rights |
| apiSecret | String | apiSecret, get api secret from PAXSTORE admin console, refer to chapter Apply access rights               |

### Search EMM apps

The search EMM apps API allows thirdparty system search EMM apps by page.

**API**

```
public Result<EmmAppDTO> searchEmmApp(int pageNo, int pageSize, EmmAppSearchOrderBy orderBy, String resellerName, String keyWords, EmmAppType type);
```

**Input parameter(s) description**


| Name            | Type                | Nullable | Description                                                                                                                                                                                                                                                                                                                 |
|:----------------| :-------------------- | :--------- |:----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| pageNo          | int                 | false    | page number, value must >=1                                                                                                                                                                                                                                                                                                 |
| pageSize        | int                 | false    | the record number per page, range is 1 to 100                                                                                                                                                                                                                                                                               |
| orderBy         | EmmAppSearchOrderBy | true     | the sort order by field name, if this parameter is null the search result will order by created date descend. The value of this parameter can be one of EmmAppSearchOrderBy.EmmAppName_desc, EmmAppSearchOrderBy.EmmAppName_asc, EmmAppSearchOrderBy.EmmUpdatedDate_desc, EmmAppSearchOrderBy.EmmUpdatedDate_asc            |
| resellerName    | String              | false    | search EMM apps under the reseller or it's sub-resellers                                                                                                                                                                                                                                                                    |
| keyWords        | String              | true     | search by EMM app name or package name                                                                                                                                                                                                                                                                                      |
| type            | EmmAppType          | true     | the EMM app type, the value can be one of EmmAppType.GOOGLE, EmmAppType.PRIVATE                                                                                                                                                                                                                                             |

**Sample codes**

```
EmmAppApi emmAppApi = new  EmmAppApi("https://api.whatspos.com/p-market-api", "RCA9MDH6YN3WSSGPW6TJ", "TUNLDZVZECHNKZ4FW07XFCKN2W0N8ZDEA5ENKZYN");
Result<EmmAppDTO> result = emmAppApi.searchEmmApp(1, 10, EmmAppSearchOrderBy.EmmAppName_desc, null, null, EmmAppType.GOOGLE);
```

**Client side validation failed sample result(JSON formatted)**

```
{
	"businessCode": -1,
	"validationErrors": ["Parameter pageNo Minimum cannot be less than 1!"]
}
```

**Server side validation failed sample result(JSON formatted)**

```
{
	"businessCode": 1759,
	"message": "Reseller doesn't exist"
}
```

**Successful sample result**

```
{
    "businessCode": 0,
    "pageInfo": {
        "pageNo": 1,
        "limit": 10,
        "totalCount": 1,
        "hasNext": false,
        "dataSet": [
            {
                "id": 1646028514132005,
                "name": "Paperpile",
                "packageName": "com.paperpile",
                "iconUrl": "https://lh3.googleusercontent.com/88ZF_xZwM2DRA6xdFEz-JdV2PtpLUaT3LWkYN86mCaz8sc5lRo4L-uuqgZLk-VlTeVkd",
                "type": "G",
                "developerName": "Paperpile LLC",
                "supportManagedConfig": false
            }
        ]
    }
}
```

The type in dataSet is EmmAppDTO. The structure like below.


| Property Name        | Type    | Description                                                   |
| :--------------------- | :-------- | :-------------------------------------------------------------- |
| id                   | Long    | The id of EMM app                                             |
| name                 | String  | The name of EMM app                                           |
| packageName          | String  | The packageName of EMM app                                    |
| iconUrl              | String  | The icon url of EMM app                                       |
| type                 | String  | Type of EMM app. Value can be one of G(Google) and P(Private) |
| developerName        | String  | The developer name of EMM app                                 |
| supportManagedConfig | Boolean | Whether support managed config                                |

**Possible client validation errors**

> <font color=red>Parameter pageNo Minimum cannot be less than 1!</font>
> <font color=red>Parameter pageSize must be range is 1 to 100!</font>
> <font color=red>Parameter resellerName cannot be null!</font>
> <font color=red>Parameter resellerName is too long, maxlength is 64!</font>

**Possible business codes**


| Business Code | Message                                    | Description |
| :-------------- | :------------------------------------------- | :------------ |
| 1759          | Reseller doesn't exist                     |             |
| 1762          | Reseller name is mandatory                 |             |
| 1767          | Reseller name is too long                  |             |
| 61654         | EMM for Android not subscribed             |             |
| 61655         | EMM for Android not distributed            |             |
| 61656         | The market is not bound to EMM for Android |             |

### Create EMM app

Create EMM app API allow the thirdparty system create an emm app remotely.

**API**

```
public Result<EmmAppDTO> createEmmApp(EmmAppCreateRequest emmAppCreateRequest);
```

**Input parameter(s) description**


| Name          | Type                | Nullable | Description                                          |
| :-------------- | :-------------------- | :--------- | :----------------------------------------------------- |
| createRequest | EmmAppCreateRequest | false    | The create request object. The structure shows below |

Structure of class EmmAppCreateRequest


| Property Name | Type   | Nullable | Description                                          |
| :-------------- | :------- | :--------- | :----------------------------------------------------- |
| resellerName  | String | false    | The reseller of EMM app belongs to. Max length is 64 |
| packageName   | String | false    | The package name of EMM app. Max length is 128       |

**Sample codes**

```
EmmAppApi emmAppApi = new  EmmAppApi("https://api.whatspos.com/p-market-api", "RCA9MDH6YN3WSSGPW6TJ", "TUNLDZVZECHNKZ4FW07XFCKN2W0N8ZDEA5ENKZYN");
EmmAppCreateRequest emmAppCreateRequest = new EmmAppCreateRequest();
emmAppCreateRequest.setResellerName("PAX");
emmAppCreateRequest.setPackageName("com.paperpile");
Result<EmmAppDTO> result = emmAppApi.createEmmApp(emmAppCreateRequest);
```

**Client side validation failed sample result(JSON formatted)**

```
{
	"businessCode": -1,
	"validationErrors": ["Parameter resellerName cannot be null!"]
}
```

**Server side validation failed sample result(JSON formatted)**

```
{
	"businessCode": 61619,
	"message": "App already exists"
}
```

**Successful sample result**

```
{
	"businessCode": 0,
	"data": {
		"id": 1646711153885223,
		"name": "Paperpile",
		"packageName": "com.paperpile",
		"iconUrl": "https://lh3.googleusercontent.com/88ZF_xZwM2DRA6xdFEz-JdV2PtpLUaT3LWkYN86mCaz8sc5lRo4L-uuqgZLk-VlTeVkd",
		"type": "G",
		"developerName": "Paperpile LLC",
		"supportManagedConfig": false
	}
}
```

The type of data in result is same as search emm app API.

**Possible validation errors**

> <font color=red>Parameter emmAppCreateRequest cannot be null!</font>
> <font color=red>Parameter resellerName cannot be null!</font>
> <font color=red>Parameter resellerName is too long, maxlength is 64!</font>
> <font color=red>Parameter packageName cannot be null!</font>
> <font color=red>Parameter packageName is too long, maxlength is 128!</font>

**Possible business codes**


| Business Code | Message                             | Description |
| :-------------- | :------------------------------------ | :------------ |
| 1759          | Reseller doesn't exist              |             |
| 1762          | Reseller name is mandatory          |             |
| 1767          | Reseller name is too long           |             |
| 61619         | App already exists                  |             |
| 61620         | App package name is invalid         |             |
| 61633         | The app type does not match the app |             |
| 61655         | EMM for Android not distributed     |             |
| 61660         | EMM app package name is mandatory   |             |
| 61661         | EMM app package name is too long    |             |

### Get EMM app detail by app id.

The get EMM app detail API allows the thirdparty system get EMM app detail by app id.

**API**

```
public Result<EmmAppDetailDTO> getEmmAppDetail(Long appId)
```

**Input parameter(s) description**


| Parameter Name | Type | Nullable | Description       |
| :--------------- | :----- | :--------- | :------------------ |
| appId          | Long | false    | The id of EMM app |

**Sample codes**

```
EmmAppApi emmAppApi = new  EmmAppApi("https://api.whatspos.com/p-market-api", "RCA9MDH6YN3WSSGPW6TJ", "TUNLDZVZECHNKZ4FW07XFCKN2W0N8ZDEA5ENKZYN");
Result<EmmAppDetailDTO> result = emmAppApi.getEmmAppDetail(1646714366722087L);
```

**Client side validation failed sample result(JSON formatted)**

```
{
	"businessCode": -1,
	"validationErrors": ["Parameter appId cannot be null and cannot be less than 1!"]
}
```

**Server side validation failed sample result(JSON formatted)**

```
{
	"businessCode": 61621,
	"message": "App not exists"
}
```

**Successful sample result(JSON formatted)**

```
{
	"businessCode": 0,
	"data": {
		"id": 1646714366722087,
		"name": "Paperpile",
		"packageName": "com.paperpile",
		"iconUrl": "https://lh3.googleusercontent.com/88ZF_xZwM2DRA6xdFEz-JdV2PtpLUaT3LWkYN86mCaz8sc5lRo4L-uuqgZLk-VlTeVkd",
		"type": "G",
		"developerName": "Paperpile LLC",
		"supportManagedConfig": false,
		"minAndroidSdkVersion": 21,
		"screenshotUrls": [
			"https://lh3.googleusercontent.com/mJFB69rzYWUI30gmABdX3QgBg_o2dsFUF7f2Lod8nUzqueApTgWYuBN2qzgZFV7p0UWl",
			"https://lh3.googleusercontent.com/hc7DegrA3Ja2KIwGgj5zP1JRMWPO1w8KSk5fhFIvJhs_-32dp4s1NMBl8BjPeC8s05k",
			"https://lh3.googleusercontent.com/WIRcVQAQgOPcHAvVI_Xzl_jN5nCq-YWv7aRj9YhInhrB4DoIN9ezeT_7Bz04KFygDOE",
			"https://lh3.googleusercontent.com/P3Y8yKvR1d3QARpF0mWddD6VjqYNtOseYO5QagVKZTbMWybwbQKLSvy-Be-Tuh6tnF0",
			"https://lh3.googleusercontent.com/mq02PIn63UJfQOfisxhuItciIP3J-5tkS0L1dutwSplMsTMtspl9UHMvpuDu2w424Uyp",
			"https://lh3.googleusercontent.com/ktcX1VNFuCQZXDHD_cXuN4FXEw9MkdZhYW_bBe90OTH0S6KQbor9XTf0RoeMdkplxOE"
		],
		"updateTime": 1698336000000,
		"appPricing": "F",
		"fullDescription": "Get a head start for you research and finally beat the paper chaos on your desk. With Paperpile you have all your research PDFs in one place — nice and tidy. Paperpile makes it easier than ever to collect, manage, read, and annotate your papers.\n\nFIND & COLLECT\n\n- Search millions of papers from 20,000+ academic journals right in the app.\n\n- Add new papers to your collection with one tap and the PDF will be downloaded automatically.\n\n- Save directly from your browser to your Paperpile library\n\n- User your university's off-campus proxy access to get restricted content behind paywalls.\n\nMANAGE & ORGANIZE\n\n- Bring structure to your library with folders and subfolders\n\n- Organize your library visually with colored labels\n\n- Mark important papers with stars.\n\n- Search your library to find the paper you need fast.\n\nREAD & ANNOTATE\n\n- Read like on paper with a fast and slick PDF viewer experience\n\n- Highlight important sections in different colors\n\n- Add notes and never forget an important idea.\n\n- Add hand-drawn annotations",
		"appVersions": [
			"1.3.8"
		]
	}
}
```

The type in data is EmmAppDetailDTO. The structure like below.


| Property Name        | Type           | Description                                                                                        |
| :--------------------- | :--------------- | :--------------------------------------------------------------------------------------------------- |
| id                   | Long           | The id of EMM app                                                                                  |
| name                 | String         | The name of EMM app                                                                                |
| packageName          | String         | The packageName of EMM app                                                                         |
| iconUrl              | String         | The icon url of EMM app                                                                            |
| type                 | String         | Type of EMM app. Value can be one of G(Google) and P(Private)                                      |
| developerName        | String         | The developer name of EMM app                                                                      |
| supportManagedConfig | Boolean        | Whether support managed config                                                                     |
| minAndroidSdkVersion | Integer        | The app min Android SDK version,there is a correspondence between numbers and Android SDK versions |
| screenshotUrls       | List\<String\> | List of screenshot links representing the app                                                      |
| updateTime           | Date           | The update time of EMM app                                                                         |
| appPricing           | String         | Whether this app is free(F), free with in-app purchases(FP), or paid(P)                            |
| fullDescription      | String         | Full app description, if available                                                                 |
| appVersions          | List\<String\> | Versions currently available for this app                                                          |

**Possible validation errors**

> <font color=red>Parameter appId cannot be null and cannot be less than 1!</font>

**Possible business codes**


| Business Code | Message        | Description |
| :-------------- | :--------------- | :------------ |
| 61621         | App not exists |             |

### Remove EMM app

The remove EMM app API allows the thirdparty system remove EMM app by appId and resellerName.

**API**

```
public Result<String> removeEmmApp(Long appId, String resellerName)
```

**Input parameter(s) description**


| Parameter Name | Type   | Nullable | Description                        |
| :--------------- | :------- | :--------- | :----------------------------------- |
| appId          | Long   | false    | The id of EMM app                  |
| resellerName   | String | false    | The reseller of EMM app belongs to |

**Sample codes**

```
EmmAppApi emmAppApi = new  EmmAppApi("https://api.whatspos.com/p-market-api", "RCA9MDH6YN3WSSGPW6TJ", "TUNLDZVZECHNKZ4FW07XFCKN2W0N8ZDEA5ENKZYN");
Result<String> result = emmAppApi.removeEmmApp(1646714366722087L, "PAX");
```

**Client side validation failed sample result(JSON formatted)**

```
{
	"businessCode": -1,
	 validationErrors=["Parameter appId cannot be null and cannot be less than 1!"]
}
```

**Server side validation failed sample result(JSON formatted)**

```
{
	"businessCode": 61621,
	"message": "App not exists"
}
```

**Successful sample result(JSON formatted)**

```
{
	"businessCode": 0,
}
```

**Possible client validation errors**

> <font color=red>Parameter appId cannot be null and cannot be less than 1!</font>
> <font color=red>Parameter resellerName cannot be null!</font>
> <font color=red>Parameter resellerName is too long, maxlength is 64!</font>

**Possible business codes**


| Business Code | Message                    | Description |
| :-------------- | :--------------------------- | :------------ |
| 1759          | Reseller doesn't exist     |             |
| 1762          | Reseller name is mandatory |             |
| 1767          | Reseller name is too long  |             |
| 61621         | App not exists             |             |

### Search subscribed EMM apps

The search subscribed EMM apps API allows thirdparty system search current reseller subscribed EMM apps by page.

**API**

```
public Result<SubscribeEmmAppDTO> searchSubscribedEmmApp(int pageNo, int pageSize,String name, Boolean isSubscribed);
```

**Input parameter(s) description**


| Name         | Type    | Nullable | Description                                   |
| :------------- | :-------- | :--------- | :---------------------------------------------- |
| pageNo       | int     | false    | page number, value must >=1                   |
| pageSize     | int     | false    | the record number per page, range is 1 to 100 |
| name         | String  | true     | search by EMM app name                        |
| isSubscribed | Boolean | false    | whether subscribe EMM app                     |

**Sample codes**

```
EmmAppApi emmAppApi = new  EmmAppApi("https://api.whatspos.com/p-market-api", "RCA9MDH6YN3WSSGPW6TJ", "TUNLDZVZECHNKZ4FW07XFCKN2W0N8ZDEA5ENKZYN");
Result<SubscribeEmmAppDTO> result = emmAppApi.searchSubscribeEmmApp(1, 5, null, true);
```

**Client side validation failed sample result(JSON formatted)**

```
{
	"businessCode": -1,
	"validationErrors": ["Parameter pageNo Minimum cannot be less than 1!"]
}
```

**Server side validation failed sample result(JSON formatted)**

```
{
	"businessCode": 131,
	"message": "Insufficient access right"
}
```

**Successful sample result**

```
{
	"businessCode": 0,
	"dataset": [
		{
			"id": 1646714366722087,
			"name": "Paperpile",
			"packageName": "com.paperpile",
			"type": "G",
			"iconUrl": "https://lh3.googleusercontent.com/88ZF_xZwM2DRA6xdFEz-JdV2PtpLUaT3LWkYN86mCaz8sc5lRo4L-uuqgZLk-VlTeVkd"
		}
	],
	"limit": 10,
	"pageNo": 1,
	"totalCount": 1,
	"hasNext": false
}
```

The type in dataSet is SubscribeEmmAppDTO. The structure like below.


| Property Name | Type   | Description                                                   |
| :-------------- | :------- | :-------------------------------------------------------------- |
| id            | Long   | The id of EMM app                                             |
| name          | String | The name of EMM app                                           |
| packageName   | String | The package name of EMM app                                   |
| iconUrl       | String | The icon url of EMM app                                       |
| type          | String | Type of EMM app. Value can be one of G(Google) and P(Private) |

**Possible client validation errors**

> <font color=red>Parameter pageNo Minimum cannot be less than 1!</font>
> <font color=red>Parameter pageSize must be range is 1 to 100!</font>
> <font color=red>Parameter isSubscribed cannot be null</font>

**Possible business codes**


| Business Code | Message                   | Description |
| :-------------- | :-------------------------- | :------------ |
| 131           | Insufficient access right |             |

### Subscribe EMM app

The subscribe EMM app API allows the thirdparty system subscribe current reseller EMM app.

**API**

```
public Result<String> subscribeEmmApp(Long appId)
```

**Input parameter(s) description**


| Parameter Name | Type | Nullable | Description       |
| :--------------- | :----- | :--------- | :------------------ |
| appId          | Long | false    | The id of EMM app |

**Sample codes**

```
EmmAppApi emmAppApi = new  EmmAppApi("https://api.whatspos.com/p-market-api", "RCA9MDH6YN3WSSGPW6TJ", "TUNLDZVZECHNKZ4FW07XFCKN2W0N8ZDEA5ENKZYN");
Result<String> result = emmAppApi.subscribeEmmApp(1646714366722087L);
```

**Client side validation failed sample result(JSON formatted)**

```
{
	"businessCode": -1,
	 validationErrors=["Parameter appId cannot be null and cannot be less than 1!"]
}
```

**Server side validation failed sample result(JSON formatted)**

```
{
	"businessCode": 61621,
	"message": "App not exists"
}
```

**Successful sample result(JSON formatted)**

```
{
	"businessCode": 0,
}
```

**Possible client validation errors**

> <font color=red>Parameter appId cannot be null and cannot be less than 1!</font>

**Possible business codes**


| Business Code | Message                    | Description |
| :-------------- | :--------------------------- | :------------ |
| 131           | Insufficient access right  |             |
| 1759          | Reseller doesn't exist     |             |
| 1762          | Reseller name is mandatory |             |
| 1767          | Reseller name is too long  |             |
| 61621         | App not exists             |             |

### Unsubscribe EMM app

The unsubscribe EMM app API allows the thirdparty system unsubscribe current reseller EMM app.

**API**

```
public Result<String> unSubscribeEmmApp(Long appId)
```

**Input parameter(s) description**


| Parameter Name | Type | Nullable | Description       |
| :--------------- | :----- | :--------- | :------------------ |
| appId          | Long | false    | The id of EMM app |

**Sample codes**

```
EmmAppApi emmAppApi = new  EmmAppApi("https://api.whatspos.com/p-market-api", "RCA9MDH6YN3WSSGPW6TJ", "TUNLDZVZECHNKZ4FW07XFCKN2W0N8ZDEA5ENKZYN");
Result<String> result = emmAppApi.unSubscribeEmmApp(1646714366722087L);
```

**Client side validation failed sample result(JSON formatted)**

```
{
	"businessCode": -1,
	 validationErrors=["Parameter appId cannot be null and cannot be less than 1!"]
}
```

**Server side validation failed sample result(JSON formatted)**

```
{
	"businessCode": 61621,
	"message": "App not exists"
}
```

**Successful sample result(JSON formatted)**

```
{
	"businessCode": 0,
}
```

**Possible client validation errors**

> <font color=red>Parameter appId cannot be null and cannot be less than 1!</font>

**Possible business codes**


| Business Code | Message                    | Description |
| :-------------- | :--------------------------- | :------------ |
| 131           | Insufficient access right  |             |
| 1759          | Reseller doesn't exist     |             |
| 1762          | Reseller name is mandatory |             |
| 1767          | Reseller name is too long  |             |
| 61621         | App not exists             |             |

### Get EMM app permission list.

The get EMM app permission list API allows the thirdparty system get EMM app permission list by app id.

**API**

```
public Result<EmmAppPermissionDTO> getEmmAppPermissionList(Long appId)
```

**Input parameter(s) description**


| Parameter Name | Type | Nullable | Description       |
| :--------------- | :----- | :--------- | :------------------ |
| appId          | Long | false    | The id of EMM app |

**Sample codes**

```
EmmAppApi emmAppApi = new  EmmAppApi("https://api.whatspos.com/p-market-api", "RCA9MDH6YN3WSSGPW6TJ", "TUNLDZVZECHNKZ4FW07XFCKN2W0N8ZDEA5ENKZYN");
Result<EmmAppPermissionDTO> result = emmAppApi.getEmmAppPermissionList(1646714366722087L);
```

**Client side validation failed sample result(JSON formatted)**

```
{
	"businessCode": -1,
	"validationErrors": ["Parameter appId cannot be null and cannot be less than 1!"]
}
```

**Server side validation failed sample result(JSON formatted)**

```
{
	"businessCode": 61621,
	"message": "App not exists"
}
```

**Successful sample result(JSON formatted)**

```
{
	"businessCode": 0,
	"data": {
		"permissions": [
			"android.permission.ACCESS_NETWORK_STATE",
			"android.permission.ACCESS_WIFI_STATE",
			"android.permission.BROADCAST_CLOSE_SYSTEM_DIALOGS",
			"android.permission.CHANGE_NETWORK_STATE",
			"android.permission.CHANGE_WIFI_MULTICAST_STATE",
			"android.permission.CHANGE_WIFI_STATE",
			"android.permission.FOREGROUND_SERVICE",
			"android.permission.INTERNET",
			"android.permission.READ_EXTERNAL_STORAGE",
			"android.permission.RECORD_AUDIO",
			"android.permission.SYSTEM_ALERT_WINDOW",
			"android.permission.WAKE_LOCK",
			"android.permission.WRITE_EXTERNAL_STORAGE",
			"com.android.vending.CHECK_LICENSE",
			"com.google.android.c2dm.permission.RECEIVE",
			"com.google.android.finsky.permission.BIND_GET_INSTALL_REFERRER_SERVICE",
			"com.google.android.gms.permission.AD_ID"
		]
	}
}
```

The type of data in result is EmmAppPermissionDTO. The structure like below.


| Property Name | Type           | Description                |
| :-------------- | :--------------- | :--------------------------- |
| permissions   | List\<String\> | The permissions of emm app |

**Possible validation errors**

> <font color=red>Parameter appId cannot be null and cannot be less than 1!</font>

**Possible business codes**


| Business Code | Message                                    | Description |
| :-------------- | :------------------------------------------- | :------------ |
| 61621         | App not exists                             |             |
| 61654         | EMM for Android not subscribed             |             |
| 61656         | The market is not bound to EMM for Android |             |

### Get EMM app available test track version list.

The get EMM app available test track version list API allows the thirdparty system get EMM app available test track version list by app id.

**API**

```
public Result<EmmAppAvailableTestVersionDTO> getAvailableTestTrackVersionList(Long appId)
```

**Input parameter(s) description**


| Parameter Name | Type | Nullable | Description       |
| :--------------- | :----- | :--------- | :------------------ |
| appId          | Long | false    | The id of EMM app |

**Sample codes**

```
EmmAppApi emmAppApi = new  EmmAppApi("https://api.whatspos.com/p-market-api", "RCA9MDH6YN3WSSGPW6TJ", "TUNLDZVZECHNKZ4FW07XFCKN2W0N8ZDEA5ENKZYN");
Result<EmmAppAvailableTestVersionDTO> result = emmAppApi.getAvailableTestTrackVersionList(1646714366722087L);
```

**Client side validation failed sample result(JSON formatted)**

```
{
	"businessCode": -1,
	"validationErrors": ["Parameter appId cannot be null and cannot be less than 1!"]
}
```

**Server side validation failed sample result(JSON formatted)**

```
{
	"businessCode": 61621,
	"message": "App not exists"
}
```

**Successful sample result(JSON formatted)**

```
{
	"businessCode": 0,
	"data": {
		"appAvailableTestVersionList": [
			{
				"trackId": -1,
				"trackAlias": "Production",
				"versionName": "1.0.11"
			},
			{
				"trackId": 1681160455323739,
				"trackAlias": "alpha",
				"versionName": "1.0.12"
			}
		]
	}
}
```

The type of data in result is EmmAppAvailableTestVersionDTO. The structure like below.


| Property Name               | Type                                 | Description                           |
|:----------------------------|:-------------------------------------|:--------------------------------------|
| appAvailableTestVersionList | List\<EmmAppAvailableTestVersion\>   | The available test version of emm app |

Structure of class EmmAppAvailableTestVersion


| Property Name | Type   | Description                      |
|:--------------|:-------|:---------------------------------|
| trackId       | Long   | The track id of emm app          |
| trackAlias    | String | The track alias of emm app       |
| versionName   | String | The track version of emm app     |

**Possible validation errors**

> <font color=red>Parameter appId cannot be null and cannot be less than 1!</font>

**Possible business codes**

| Business Code   | Message                                     | Description |
|:----------------|:--------------------------------------------| :------------ |
| 61621           | App not exists                              |             |
| 61654           | EMM for Android not subscribed              |             |
| 61656           | The market is not bound to EMM for Android  |             |
