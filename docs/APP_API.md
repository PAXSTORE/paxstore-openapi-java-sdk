## Application APIs

The app APIs allow thirdparty system search apps.
All the app APIs are in the class *com.pax.market.api.sdk.java.api.app.AppApi*.

**Constructors of AppAPI**

```
public AppApi(String baseUrl, String apiKey, String apiSecret);
```

Constructor parameters description

| Name      | Type   | Description                                                                                               |
|:----------|:-------|:----------------------------------------------------------------------------------------------------------|
| baseUrl   | String | the base url of REST API                                                                                  |
| apiKey    | String | the apiKey of marketplace, get this key from PAXSTORE admin console, refer to chapter Apply access rights |
| apiSecret | String | apiSecret, get api secret from PAXSTORE admin console, refer to chapter Apply access rights               |

### Search apps

The search apps API allows thirdparty system to search apps for page.

**API**

```
public Result<AppPageDTO>  searchApp(int pageNo, int pageSize, AppSearchOrderBy orderBy, String name, AppOsType osType, AppChargeType chargeType,AppBaseType baseType, AppStatus appStatus, ApkStatus apkStatus,Boolean specificReseller,Boolean specificMerchantCategory, Boolean includeSubscribedApp, String resellerName, 
String modelName);
public Result<AppPageDTO>  searchApp(int pageNo, int pageSize, AppSearchOrderBy orderBy, String name, AppOsType osType, AppChargeType chargeType,AppBaseType baseType, AppStatus appStatus, ApkStatus apkStatus,Boolean specificReseller,Boolean specificMerchantCategory, Boolean includeSubscribedApp, String resellerName);
public Result<AppPageDTO>  searchApp(int pageNo, int pageSize, AppSearchOrderBy orderBy,
String name, AppOsType osType, AppChargeType chargeType, AppBaseType baseType, AppStatus appStatus, ApkStatus apkStatus,Boolean specificReseller,Boolean specificMerchantCategory, Boolean includeSubscribedApp);
public Result<AppPageDTO>  searchApp(int pageNo, int pageSize, AppSearchOrderBy orderBy,
String name, AppOsType osType, AppChargeType chargeType, AppBaseType baseType, AppStatus appStatus, ApkStatus apkStatus,Boolean specificReseller,Boolean pecificMerchantCategory);
```

**Input parameter(s) description**

| Name                     | Type             | Nullable | Description                                                  |
| :----------------------- | :--------------- | :------- | :----------------------------------------------------------- |
| pageNo                   | int              | false    | page number, value must >=1                                  |
| pageSize                 | int              | false    | the record number per page, range is 1 to 100                |
| orderBy                  | AppSearchOrderBy | true     | the sort order by field name, if this parameter is null the search result will order by created date descend. The value of this parameter can be one of AppSearchOrderBy.AppName_desc, AppSearchOrderBy.AppName_asc, AppSearchOrderBy.Emial_desc, AppSearchOrderBy.Emial_asc, AppSearchOrderBy.UpdatedDate_desc and AppSearchOrderBy.UpdatedDate_asc. |
| name                     | String           | true     | search filter by app name(parsed from apk file）, package name or the developer's name |
| appStatus                | AppStatus        | true     | the app status<br/> the value can be AppStatus.Active, AppStatus.Suspend |
| apkStatus                | ApkStatus        | true     | the apk status<br/> the value can be ApkStatus.Pending, ApkStatus.Online, ApkStatus.Rejected, ApkStatus.Offline |
| osType                   | AppOsType        | true     | the app osType<br/> the value can be AppOsType.Android, AppOsType.Traditional |
| baseType                 | AppBaseType      | true     | the app baseType<br/> the value can be AppBaseType.Normal, AppBaseType.Parameter |
| chargeType               | AppChargeType    | true     | the app chargeType<br/> the value can be AppChargeType.Free, AppChargeType.Charging |
| specificReseller         | Boolean          | true     | specific reseller<br/> value can be one of true(yes) and false(no) |
| specificMerchantCategory | Boolean          | true     | specific merchant category<br/> value can be one of true(yes) and false(no) |
| includeSubscribedApp     | Boolean          | true     | include the subscribed app, value can be one of true(yes) and false(no) |
| resellerName             | String           | true     | search filter by reseller name, search out the app to which the reseller belongs |
| modelName                | String           | true     | search filter by model name, search out the app to which the model belongs |

**Sample codes**

```
AppApi AppApi = new  AppApi("https://api.whatspos.com/p-market-api", "RCA9MDH6YN3WSSGPW6TJ", "TUNLDZVZECHNKZ4FW07XFCKN2W0N8ZDEA5ENKZYN");
Result<AppPageDTO> result = AppApi.searchApp(1, 10, AppSearchOrderBy.UpdatedDate_desc,"", AppOsType.Android, AppChargeType.Free, AppBaseType.Normal, AppStatus.Active, ApkStatus.Online, false, false, null, null);
```

**Client side validation failed sample result(JSON formatted)**

```
{
	"businessCode": -1,
	"validationErrors": ["pageNo:must be greater than or equal to 1"]
}
```

**Successful sample result**

```
{
	"businessCode": 0,
	"pageInfo": {
		"pageNo": 1,
		"limit": 12,
		"totalCount": 1,
		"hasNext": false,
		"dataSet": [{
			"id":1000006322,
            "name":"sample",
            "packageName":"com.snatik.storage.sample",
            "status":"A",
            "osType":"A",
            "specificReseller":false,
            "chargeType":0,
            "price":null,
            "downloads":4,
            "apkList":[
                 {
                    "name":"sample",
                    "fileSize":2545215,
                    "status":"O",
                    "versionCode":1,
                    "versionName":null,
                    "apkType":"N",
                    "apkFileType":"A",
                    "apkFile":{
                        "permissions":"WRITE_EXTERNAL_STORAGE,MAGCARD,UPDATE_APP,PRINTER,UPDATE_FIRM,ICC,SYSSIG,PICC,RECV_BOOT_COMPLETED,PED",
                        "paxPermission":null
                    },
                    "osType":"A"
                 }
            ]
		}]
	}
}
```

The type in dataSet is AppPageDTO. And the structure like below.

| Property Name    | Type           | Description            |
|:-----------------|:---------------|:-----------------------|
| id               | Long           | The id of app.         |
| name             | String         | The name of app.       |
| packageName      | String         | The packageName of app. |
| status           | String         | Status of app. Value can be one of A(Active) and S(Suspend) |
| osType           | String         | OsType of app. Value can be one of A(Android) and T(Traditional) |
| chargeType       | Integer        | chargeType of app. Value can be one of 0(Free) and 1(Charing) |
| specificReseller | Boolean        | whether make app specific reseller. |
| downloads        | Long           | downloads of app.      |
| apkList          | List\<ApkDTO\> | App version list.      |
| entityAttributeValues          | LinkedHashMap<String, String> | App Entity Attributes. |

The structure of class ApkDTO

| Property Name | Type       | Description                                                                          |
|:--------------|:-----------|:-------------------------------------------------------------------------------------|
| name          | String     | Apk name                                                                             |
| fileSize      | Long       | Apk file size（byte）                                                                  |
| status        | String     | Status of apk. Value can be one of P(Pending), O(Online), R(Rejected) and U(Offline) |
| versionCode   | Long       | version code of apk.                                                                 |
| versionName   | String     | version name of apk.                                                                 |
| apkType       | String     | base type of apk. Value can be one of N(Normal) and P(Parameter)                     |
| apkFileType   | String     | file type of apk. Value can be one of A(Android), P(Prolin) and B(Broadpos)          |
| apkFile       | ApkFileDTO | install package file of apk.                                                         |

The structure of class ApkFileDTO

| Property Name | Type   | Description               |
|:--------------|:-------|:--------------------------|
| permissions   | String | Android OS Authorization. |
| paxPermission | String | Paydroid Authorizationr.  |

**Possible client validation errors**

> <font color=red>pageNo:must be greater than or equal to 1</font>   
> <font color=red>pageSize:must be greater than or equal to 1</font>   
> <font color=red>pageSize:must be less than or equal to 100</font>

### Search apps includes subscribed apps

The search apps API allows thirdparty system to search apps for page, it has additional 1 parameter, the details please
refer to the Input parameter(s) description.

**API**

```
public Result<AppPageDTO>  searchApp(int pageNo, int pageSize, AppSearchOrderBy orderBy,
                                    String name, AppOsType osType, AppChargeType chargeType,
                                    AppBaseType baseType, AppStatus appStatus, ApkStatus apkStatus,
                                    Boolean specificReseller, Boolean specificMerchantCategory, Boolean includeSubscribedApp)
```

**Input parameter(s) description**

| Name                     | Type             | Nullable | Description                                                                                                                                                                                                                                                                                                                                           |
|:-------------------------|:-----------------|:---------|:------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| pageNo                   | int              | false    | page number, value must >=1                                                                                                                                                                                                                                                                                                                           |
| pageSize                 | int              | false    | the record number per page, range is 1 to 1000                                                                                                                                                                                                                                                                                                        |
| orderBy                  | AppSearchOrderBy | true     | the sort order by field name, if this parameter is null the search result will order by created date descend. The value of this parameter can be one of AppSearchOrderBy.AppName_desc, AppSearchOrderBy.AppName_asc, AppSearchOrderBy.Emial_desc, AppSearchOrderBy.Emial_asc, AppSearchOrderBy.UpdatedDate_desc and AppSearchOrderBy.UpdatedDate_asc. |
| name                     | String           | true     | search filter by app name(parsed from apk file）, package name or the developer's name                                                                                                                                                                                                                                                                 |
| appStatus                | AppStatus        | true     | the app status<br/> the value can be AppStatus.Active, AppStatus.Suspend                                                                                                                                                                                                                                                                              |
| apkStatus                | ApkStatus        | true     | the apk status<br/> the value can be ApkStatus.Pending, ApkStatus.Online, ApkStatus.Rejected, ApkStatus.Offline                                                                                                                                                                                                                                       |
| osType                   | AppOsType        | true     | the app osType<br/> the value can be AppOsType.Android, AppOsType.Traditional                                                                                                                                                                                                                                                                         |
| baseType                 | AppBaseType      | true     | the app baseType<br/> the value can be AppBaseType.Normal, AppBaseType.Parameter                                                                                                                                                                                                                                                                      |
| chargeType               | AppChargeType    | true     | the app chargeType<br/> the value can be AppChargeType.Free, AppChargeType.Charging                                                                                                                                                                                                                                                                   |
| specificReseller         | Boolean          | true     | specific reseller<br/> make app private to some reseller, the value can be true or false                                                                                                                                                                                                                                                              |
| specificMerchantCategory | Boolean          | true     | sperific merchant category<br/> make app only visible by specific merchants in store client, the value can be true or false                                                                                                                                                                                                                           |
| includeSubscribedApp     | Boolean          | true     | whether to include the subscribed applications from global market                                                                                                                                                                                                                                                                                     |

**Sample codes**

```
AppApi AppApi = new  AppApi("https://api.whatspos.com/p-market-api", "RCA9MDH6YN3WSSGPW6TJ", "TUNLDZVZECHNKZ4FW07XFCKN2W0N8ZDEA5ENKZYN");
Result<AppPageDTO> result = AppApi.searchApp(1, 10, AppSearchOrderBy.UpdatedDate_desc,
                                                 "", AppOsType.Android, AppChargeType.Free, AppBaseType.Normal, 
                                                 AppStatus.Active, ApkStatus.Online, false, false, true);
```

**Client side validation failed sample result(JSON formatted)**

```
{
	"businessCode": -1,
	"validationErrors": ["pageNo:must be greater than or equal to 1"]
}
```

**Successful sample result**

```
{
    "businessCode": 0,
    "pageInfo": {
        "pageNo": 1,
        "limit": 12,
        "totalCount": 2,
        "hasNext": false,
        "dataSet": [
            {
                "id":1000006322,
                "name":"sample",
                "packageName":"com.snatik.storage.sample",
                "status":"A",
                "osType":"A",
                "specificReseller":false,
                "chargeType":0,
                "price":null,
                "downloads":4,
                "apkList":[
                    {
                        "status":"O",
                        "versionCode":1,
                        "versionName":null,
                        "apkType":"N",
                        "apkFileType":"A",
                        "apkFile":{
                            "permissions":"WRITE_EXTERNAL_STORAGE,MAGCARD,UPDATE_APP,PRINTER,UPDATE_FIRM,ICC,SYSSIG,PICC,RECV_BOOT_COMPLETED,PED",
                            "paxPermission":null
                        },
                        "osType":"A"
                    }
                ]
            },
            {
                "id":1000141820,
                "name":"test12312",
                "packageName":"com.tencent.qqlive",
                "status":"A",
                "osType":"A",
                "specificReseller":false,
                "chargeType":0,
                "downloads":0,
                "apkList":[
                    {
                        "status":"O",
                        "versionCode":20510,
                        "versionName":"7.7.9.20510",
                        "apkType":"N",
                        "apkFileType":"A",
                        "apkFile":{
                            "permissions":"WRITE_EXTERNAL_STORAGE,MAGCARD,UPDATE_APP,PRINTER,UPDATE_FIRM,ICC,SYSSIG,PICC,RECV_BOOT_COMPLETED,PED",
                            "paxPermission":""
                        },
                        "osType":"A"
                    }]
            }]
    }
}

```

The type in dataSet of result is AppPageDTO.

**Possible client validation errors**

> <font color=red>pageNo:must be greater than or equal to 1</font>   
> <font color=red>pageSize:must be greater than or equal to 1</font>   
> <font color=red>pageSize:must be less than or equal to 1000</font>  

### Get App Cost

Get app cost by resellerId and appId.

**API**

```
public Result<AppCostDTO> getAppCost(Long resellerId, Long appId) {
```

**Input parameter(s) description**

| Parameter Name | Type | Nullable | Description  |
| :------------- | :--- | :------- | :----------- |
| resellerId     | Long | false    | Reseller Id. |
| appId          | Long | false    | App Id       |

**Sample codes**

```
AppApi appApi = new AppApi("https://api.whatspos.com/p-market-api", "RCA9MDH6YN3WSSGPW6TJ", "TUNLDZVZECHNKZ4FW07XFCKN2W0N8ZDEA5ENKZYN");
Result<AppCostDTO> result = appApi.getAppCost(1L, 1L);
```

**Client side validation failed sample result(JSON formatted)**

```
{
	"businessCode": -1,
	"validationErrors": ["The property appId can't be empty!","The property resellerId can't be empty!"]
}
```

**Server side validation failed sample result(JSON formatted)**

```
{
	"businessCode": 1759,
	"message": "Reseller doesn't exist"
}
```

**Successful sample result(JSON formatted)**

```
{
	"businessCode": 0,
	"data": {
		"paid": true,
		"chargeType": 1,
		"price": 8.88,
		"currency": 58703872,
		"freeTrialDay" : 5
	}
}
```

The type in dataSet of result is AppCostDTO. The structure shows below.

Structure of class AppCostDTO

| Property Name | Type       | Description                                                  |
| :------------ | :--------- | :----------------------------------------------------------- |
| paid          | Boolean    | The app is paid                                              |
| chargeType    | Integer    | ChargeType of app, 0 is totally free and 1 is one time purchase and 2 is require subscription and 3 is app purchase and 4 is monthly fee |
| price         | BigDecimal | The app price                                                |
| text          | String     | The app text                                                 |
| currency      | String     | The app price currency                                       |
| freeTrialDay  | Integer    | The app free trial days                                      |

**Possible client validation errors**

> <font color=red>Parameter appId cannot be empty!</font>
>
> <font color=red>Parameter resellerId cannot be empty!</font>

**Possible business codes**

| Business Code | Message                | Description |
| :------------ | :--------------------- | :---------- |
| 1759          | Reseller doesn't exist |             |
| 1000          | App not found          |             |

### Search Apk Param Pid List

**API**

```
public Result<ApkParamPidDTO> searchApkParamPidList(String paramTemplateName, String packageName, String versionName)
```

**Input parameter(s) description**

| Parameter Name    | Type   | Nullable | Description                               |
| :---------------- | :----- | :------- | :---------------------------------------- |
| paramTemplateName | String | false    | Apk paramTemplateName, example: 1.xml     |
| packageName       | String | false    | Apk packageName, example: com.app.package |
| versionName       | String | false    | Apk versionName, example: 1.0             |

**Sample codes**

```
AppApi appApi = new AppApi("https://api.whatspos.com/p-market-api", "RCA9MDH6YN3WSSGPW6TJ", "TUNLDZVZECHNKZ4FW07XFCKN2W0N8ZDEA5ENKZYN");
String paramTemplateName = "jesse_password.xml";
String packageName = "com.orange.onekeylockscreen";
String versionName = "2.5.7";
Result<ApkParamPidDTO> result = appApi.searchApkParamPidList(paramTemplateName, packageName, versionName);
```

**Client side validation failed sample result(JSON formatted)**

```
{
	"businessCode": -1,
	"validationErrors": ["Parameter templateName cannot be null!"]
}
```

**Server side validation failed sample result(JSON formatted)**

```
{
	"businessCode": 1003,
	"message": "APK not found"
}
```

**Successful sample result**

```
{
    "businessCode": 0,
    "data": {
        "pidList": [
            "pid1 (sys_F2)",
            "pid2 (sys_F2)",
            "pid3 (sys_F2)",
            "pid4 (sys_F2)",
            "pid5 (sys_F2)",
            "pid6 (sys_F2)",
            "pid7 (sys_F2)",
            "pid8 (sys_F2)",
            "pid9 (sys_F2)",
            "pid10[1] (sys_F2)",
            "pid10[2] (sys_F2)",
            "pid10[3] (sys_F2)",
            "pid11[1] (sys_F2)",
            "pid11[2] (sys_F2)",
            "pid12 (sys_F2)"
        ]
    },
    "rateLimit": "3000",
    "rateLimitRemain": "2999",
    "rateLimitReset": "1754567994813"
}
```



The type in dataSet is ApkParameterPidDTO. And the structure like below.

| Property Name | Type         | Description              |
| ------------- | ------------ | ------------------------ |
| pidList       | List<String> | The pidList of Apk param |

**Possible validation errors**

> <font color="red">Parameter templateName cannot be null!</font>  
> <font color=red>Parameter packageName cannot be null!</font>  
> <font color=red>Parameter versionName cannot be null!</font>  

**Possible business codes**

| Business Code | Message                                                      | Description |
| :------------ | :----------------------------------------------------------- | :---------- |
| 1003          | APK not found                                                |             |
| 1111          | Selected parameter templates exceeded the max limit          |             |
| 1254          | Parameter template not found                                 |             |
| 12541         | Parameter template {0} can not be found in application {1}({2}) |             |
