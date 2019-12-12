## PushHistory API

Push App to terminal related APIs are encapsulated in the class *com.pax.market.api.sdk.java.api.pushHistory.PushHistoryApi*.

**Constructors of TerminalApkApi**

```
public PushHistoryApi(String baseUrl, String apiKey, String apiSecret);
public PushHistoryApi(String baseUrl, String apiKey, String apiSecret, Locale locale);
```

**Constructor parameters description**

|Name|Type|Description|
|:---|:---|:---|
|baseUrl|String|the base url of REST API|
|apiKey|String|the apiKey of marketplace, get this key from PAXSTORE admin console, refe to chapter Apply access rights|
|apiSecret|String|apiSecret, get api secret from PAXSTORE admin console, refer to chapter Apply access rights|
|locale|Locale|the locale, the default locale is Locale.ENGLISH, the language of message and errors in return object depend on locale|

### **SearchAppPushHistory**

SearchAppPushHistory API allow the third party system  to find the push history of app

**API**

```
public Result<AppPushHistoryDTO> searchAppPushHistory(int pageNo, int pageSize, PushHistorySearchOrderBy orderBy, String packageName, String snNameTID, PushStatus appPushStatus, PushStatus parameterPushStatus)
```

**Input parameter(s) description**  

| Name                | Type                     | Nullable | Description                                                  |
| :------------------ | :----------------------- | :------- | :----------------------------------------------------------- |
| pageNo              | int                      | false    | page number, value must >=1                                  |
| pageSize            | int                      | false    | the record number per page, range is 1 to 1000               |
| orderBy             | PushHistorySearchOrderBy | true     | the sort order by field name, if this parameter is null the search result will order by created date descend. The value of this parameter can be one of PushHistorySearchOrderBy.AppPushTime and PushHistorySearchOrderBy.SerialNo. |
| packageName         | String                   | false    | search filter by terminal tid                                |
| snNameTID           | String                   | true     | search filter by app packageName                             |
| appPushStatus       | PushStatus               | true     | the push status  the value can be PushStatus.Active, PushStatus.Suspend |
| parameterPushStatus | PushStatus               | true     |                                                              |




**Sample codes**

```
PushHistoryApi pushHistoryApi = new PushHistoryApi("https://api.whatspos.com/p-market-api", "RCA9MDH6YN3WSSGPW6TJ", "TUNLDZVZECHNKZ4FW07XFCKN2W0N8ZDEA5ENKZYN");
Result<AppPushHistoryDTO> result = pushHistoryApi.searchAppPushHistory(1, 2, PushHistoryApi.PushHistorySearchOrderBy.AppPushTime, "com.baidu.tieba", "", null, null);
```

**Client side validation failed sample result(JSON formatted)**

```
{
	"businessCode": -1,
	"validationErrors": ["packageName cannot be null!"]
}
```

**Successful sample result(JSON formatted)**

```
{
	"businessCode": 0,
	"pageInfo": {
		"pageNo": 1,
		"limit": 2,
		"totalCount": 586,
		"hasNext": true,
		"dataSet": [{
		"parameterPushStatus": "None",
		"appPushStatus": "Failed",
		"appName": "百度贴吧",
		"appPushTime": 1575855301000,
		"pushStartTime": 1575627420000,
		"terminalId": 461706,
		"versionName": "10.3.8.30",
		"serialNo": "1150000070",
		"pushType": "Group",
		"appPushError": "任务已删除"
	}, {
		"parameterPushStatus": "None",
		"appPushStatus": "Failed",
		"appName": "百度贴吧",
		"appPushTime": 1575855301000,
		"pushStartTime": 1575627420000,
		"terminalId": 461720,
		"versionName": "10.3.8.30",
		"serialNo": "1140000570",
		"pushType": "Group",
		"appPushError": "任务已删除"
	}]
	}
}
```

