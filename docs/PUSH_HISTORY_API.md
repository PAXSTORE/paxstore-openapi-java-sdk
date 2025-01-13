## PushHistory API

Push history API is used to search the push result. The related APIs are encapsulated in the class *
com.pax.market.api.sdk.java.api.pushHistory.PushHistoryApi*.

**Constructors of TerminalApkApi**

```
public PushHistoryApi(String baseUrl, String apiKey, String apiSecret);
```

**Constructor parameters description**

|Name|Type|Description|
|:---|:---|:---|
|baseUrl|String|the base url of REST API|
|apiKey|String|the apiKey of marketplace, get this key from PAXSTORE admin console, refer to chapter Apply access rights|
|apiSecret|String|apiSecret, get api secret from PAXSTORE admin console, refer to chapter Apply access rights|

### **SearchParameterPushHistory**

SearchParameterPushHistory API allow the third party system to find all the terminal push history of parameter
application

**API**

```
public Result<ParameterPushHistoryDTO> searchParameterPushHistory(int pageNo, int pageSize, String packageName, String serialNo, PushStatus pushStatus, Date pushTime)
```

**Input parameter(s) description**

| Name        | Type       | Nullable | Description                                                  |
| :---------- | :--------- | :------- | :----------------------------------------------------------- |
| pageNo      | int        | false    | page number, value must >=1                                  |
| pageSize    | int        | false    | the record number per page, range is 1 to 100                |
| packageName | String     | false    | search filter by app packageName                             |
| serialNo    | String     | false     | only terminal with specified serialNo will search out        |
| pushStatus  | PushStatus | true     | the push status  the value can be PushStatus.Success, PushStatus.Failed |
| pushTime    | Date       | true     | search the push history after the push time                  |

**Sample codes**

```
PushHistoryApi pushHistoryApi = new PushHistoryApi("https://api.whatspos.com/p-market-api", "RCA9MDH6YN3WSSGPW6TJ", "TUNLDZVZECHNKZ4FW07XFCKN2W0N8ZDEA5ENKZYN");
Result<ParameterPushHistoryDTO> result = pushHistoryApi.searchParameterPushHistory(1, 2,  "com.pax.android.demoapp", "1170000652", PushHistoryApi.PushStatus.Success, StringUtils.parseDateTime("2019-11-20 00:00:00"));
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
  "rateLimit": "",
  "pageNo": 1,
  "limit": 2,
  "hasNext": true,
  "totalCount": 2,
  "dataset": [
    {
      "parameterPushStatus": "Success",
      "appPushStatus": "Success",
      "appName": "PAXSTORE SDK Demo",
      "appPushTime": 1575274370000,
      "pushStartTime": 1575274320000,
      "terminalId": 1013403755,
      "versionName": "7.0.0-inner",
      "parameterTemplateName": "parameter.xml",
      "parameterPushError": null,
      "parameterPushTime": 1575274373000,
      "serialNo": "1170000652",
      "pushType": "Terminal",
      "parameterVariables": "{\"#{test}\": \"44\"}",
      "parameterValues": "{\"sys_F2_sys_param_termId\": \"#{test}\", \"sys_F2_sys_param_merCode\": \"000000000000001\", \"sys_F2_sys_param_merName\": \"Union Pay\", \"sys_F2_sys_param_acqInsCode\": \"00000000000\"}",
      "appPushError": null
    },
    {
      "parameterPushStatus": "Success",
      "appPushStatus": "Success",
      "appName": "PAXSTORE SDK Demo",
      "appPushTime": 1575102052000,
      "pushStartTime": 1575102000000,
      "terminalId": 1013403370,
      "versionName": "5.02.02",
      "parameterTemplateName": "parameter.xml",
      "parameterPushError": null,
      "parameterPushTime": 1575102054000,
      "serialNo": "1170000652",
      "pushType": "Terminal",
      "parameterVariables": "{}",
      "parameterValues": "{\"sys_F1_sys_cap_test01\": \"333\", \"sys_F1_sys_cap_test02\": \"111\"}",
      "appPushError": null
    }
  ]
}

```

The type in dataSet of is ParameterPushHistoryDTO. And the structure shows like below.

|Property Name|Type|Description|
|:--|:--|:--|
|TerminalId |long |the id of terminal|
|SerialNo |string |the serial number of terminal|
|AppName |string |the name of the app pushed|
|VersionName |string |the version name of app|
|PushStartTime |long |the start time of the push, it is millisecond|
|AppPushTime |long |app push time|
|AppPushStatus |string |the push result status, value can be Success and Fail|
|AppPushError |string |the reason of app push fail|
|ParameterTemplateName |string |the pushed parameter template name|
|ParameterPushTime|long |parameter push time|
|ParameterPushStatus|string|the parameter push result status, value can be Success and Fail|
|ParameterPushError|string|the reason of parameter push failed|
|ParameterValues|string |raw parameter values, parameter key is the parameter file id combined parameter pid, parameter
variables are not replaced|
|ParameterVariables|string|parameter variables|
|PushType |string |push type, value can be Terminal or Group|

### **SearchLatestParameterPushHistory**

SearchLatestParameterPushHistory API allow the third party system to find all the terminal latest push history of
parameter application

**API**

```
public Result<ParameterPushHistoryDTO> searchLatestParameterPushHistory(int pageNo, int pageSize, String packageName, String serialNo, PushStatus pushStatus, Date pushTime)
```

**Input parameter(s) description**

| Name        | Type       | Nullable | Description                                                  |
| :---------- | :--------- | :------- | :----------------------------------------------------------- |
| pageNo      | int        | false    | page number, value must >=1                                  |
| pageSize    | int        | false    | the record number per page, range is 1 to 100                |
| packageName | String     | false    | search filter by app packageName                             |
| serialNo    | String     | false     | only terminal with specified serialNo will search out        |
| pushStatus  | PushStatus | true     | the push status  the value can be PushStatus.Success, PushStatus.Failed |
| pushTime    | Date       | true     | search the push history after the push time                  |

**Sample codes**

```
PushHistoryApi pushHistoryApi = new PushHistoryApi("https://api.whatspos.com/p-market-api", "RCA9MDH6YN3WSSGPW6TJ", "TUNLDZVZECHNKZ4FW07XFCKN2W0N8ZDEA5ENKZYN");
Result<ParameterPushHistoryDTO> result = pushHistoryApi.searchLatestParameterPushHistory(1, 2,  "com.pax.android.demoapp", "1170000652", PushHistoryApi.PushStatus.Success, StringUtils.parseDateTime("2019-11-20 00:00:00"));
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
  "rateLimit": "",
  "pageNo": 1,
  "limit": 2,
  "hasNext": true,
  "totalCount": 1,
  "dataset": [
    {
      "parameterPushStatus": "Success",
      "appPushStatus": "Success",
      "appName": "PAXSTORE SDK Demo",
      "appPushTime": 1575274370000,
      "pushStartTime": 1575274320000,
      "terminalId": 1013403755,
      "versionName": "7.0.0-inner",
      "parameterTemplateName": "parameter.xml",
      "parameterPushError": null,
      "parameterPushTime": 1575274373000,
      "serialNo": "1170000652",
      "pushType": "Terminal",
      "parameterVariables": "{\"#{test}\": \"44\"}",
      "parameterValues": "{\"sys_F2_sys_param_termId\": \"#{test}\", \"sys_F2_sys_param_merCode\": \"000000000000001\", \"sys_F2_sys_param_merName\": \"Union Pay\", \"sys_F2_sys_param_acqInsCode\": \"00000000000\"}",
      "appPushError": null
    }
  ]
}

```

The type in dataSet of is ParameterPushHistoryDTO. And the structure shows like below.

|Property Name|Type|Description|
|:--|:--|:--|
|TerminalId |long |the id of terminal|
|SerialNo |string |the serial number of terminal|
|AppName |string |the name of the app pushed|
|VersionName |string |the version name of app|
|PushStartTime |long |the start time of the push, it is millisecond|
|AppPushTime |long |app push time|
|AppPushStatus |string |the push result status, value can be Success and Fail|
|AppPushError |string |the reason of app push fail|
|ParameterTemplateName |string |the pushed parameter template name|
|ParameterPushTime|long |parameter push time|
|ParameterPushStatus|string|the parameter push result status, value can be Success and Fail|
|ParameterPushError|string|the reason of parameter push failed|
|ParameterValues|string |raw parameter values, parameter key is the parameter file id combined parameter pid, parameter
variables are not replaced|
|ParameterVariables|string|parameter variables|
|PushType |string |push type, value can be Terminal or Group|

### **SearchOptimizedParameterPushHistory**

SearchOptimizedParameterPushHistory API allow the third party system to find all the optimized terminal push history of
parameter application

**API**

```
public Result<OptimizedParameterPushHistoryDTO> searchOptimizedParameterPushHistory(int pageNo, int pageSize, String packageName, String serialNo, PushStatus pushStatus, Date pushTime)
```

**Input parameter(s) description**

| Name        | Type       | Nullable | Description                                                  |
| :---------- | :--------- | :------- | :----------------------------------------------------------- |
| pageNo      | int        | false    | page number, value must >=1                                  |
| pageSize    | int        | false    | the record number per page, range is 1 to 100                |
| packageName | String     | false    | search filter by app packageName                             |
| serialNo    | String     | false     | only terminal with specified serialNo will search out        |
| pushStatus  | PushStatus | true     | the push status  the value can be PushStatus.Success, PushStatus.Failed |
| pushTime    | Date       | true     | search the push history after the push time                  |

**Sample codes**

```
PushHistoryApi pushHistoryApi = new PushHistoryApi("https://api.whatspos.com/p-market-api", "RCA9MDH6YN3WSSGPW6TJ", "TUNLDZVZECHNKZ4FW07XFCKN2W0N8ZDEA5ENKZYN");
Result<OptimizedParameterPushHistoryDTO> result = pushHistoryApi.searchOptimizedParameterPushHistory(1, 2,  "com.pax.android.demoapp", "1170000652", PushHistoryApi.PushStatus.Success, StringUtils.parseDateTime("2019-11-20 00:00:00"));
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
  "rateLimit": "",
  "pageNo": 1,
  "limit": 2,
  "hasNext": true,
  "totalCount": 17,
  "dataset": [
    {
      "parameterPushStatus": "Success",
      "appPushStatus": "Success",
      "appName": "PAXSTORE SDK Demo",
      "appPushTime": 1575274370000,
      "pushStartTime": 1575274320000,
      "terminalId": 1013403755,
      "versionName": "7.0.0-inner",
      "parameterTemplateName": "parameter.xml",
      "parameterPushError": null,
      "parameterPushTime": 1575274373000,
      "serialNo": "1170000652",
      "pushType": "Terminal",
      "parameters": "{\"sys.param.termId\": \"44\", \"sys.param.merCode\": \"000000000000001\", \"sys.param.merName\": \"Union Pay\", \"sys.param.acqInsCode\": \"00000000000\"}",
      "appPushError": null
    },
    {
      "parameterPushStatus": "Success",
      "appPushStatus": "Success",
      "appName": "PAXSTORE SDK Demo",
      "appPushTime": 1575102052000,
      "pushStartTime": 1575102000000,
      "terminalId": 1013403370,
      "versionName": "5.02.02",
      "parameterTemplateName": "parameter.xml",
      "parameterPushError": null,
      "parameterPushTime": 1575102054000,
      "serialNo": "1170000652",
      "pushType": "Terminal",
      "parameters": "{\"sys.cap.test01\": \"333\", \"sys.cap.test02\": \"111\"}",
      "appPushError": null
    }
  ]
}

```

The type in dataSet of is OptimizedParameterPushHistoryDTO. And the structure shows like below.

|Property Name|Type|Description|
|:--|:--|:--|
|TerminalId |long |the id of terminal|
|SerialNo |string |the serial number of terminal|
|AppName |string |the name of the app pushed|
|VersionName |string |the version name of app|
|PushStartTime |long |the start time of the push, it is millisecond|
|AppPushTime |long |app push time|
|AppPushStatus |string |the push result status, value can be Success and Fail|
|AppPushError |string |the reason of app push fail|
|ParameterTemplateName |string |the pushed parameter template name|
|ParameterPushTime|long |parameter push time|
|ParameterPushStatus|string|the parameter push result status, value can be Success and Fail|
|ParameterPushError|string|the reason of parameter push failed|
|Parameters|map |optimized parameter values, parameter variables are replaced, parameter key is the parameter pid|
|PushType |string |push type, value can be Terminal or Group|

### **SearchLatestOptimizedParameterPushHistory**

SearchLatestOptimizedParameterPushHistory API allow the third party system to find all the optimized terminal push
history of parameter application

**API**

```
public Result<OptimizedParameterPushHistoryDTO> searchLatestOptimizedParameterPushHistory(int pageNo, int pageSize, String packageName, String serialNo, PushStatus pushStatus, Date pushTime)
```

**Input parameter(s) description**

| Name        | Type       | Nullable | Description                                                  |
| :---------- | :--------- | :------- | :----------------------------------------------------------- |
| pageNo      | int        | false    | page number, value must >=1                                  |
| pageSize    | int        | false    | the record number per page, range is 1 to 100                |
| packageName | String     | false    | search filter by app packageName                             |
| serialNo    | String     | false     | only terminal with specified serialNo will search out        |
| pushStatus  | PushStatus | true     | the push status  the value can be PushStatus.Success, PushStatus.Failed |
| pushTime    | Date       | true     | search the push history after the push time                  |

**Sample codes**

```
PushHistoryApi pushHistoryApi = new PushHistoryApi("https://api.whatspos.com/p-market-api", "RCA9MDH6YN3WSSGPW6TJ", "TUNLDZVZECHNKZ4FW07XFCKN2W0N8ZDEA5ENKZYN");
Result<OptimizedParameterPushHistoryDTO> result = pushHistoryApi.searchLatestOptimizedParameterPushHistory(1, 2,  "com.pax.android.demoapp", "1170000652", PushHistoryApi.PushStatus.Success, StringUtils.parseDateTime("2019-11-20 00:00:00"));
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
  "rateLimit": "",
  "pageNo": 1,
  "limit": 2,
  "hasNext": true,
  "totalCount": 1,
  "dataset": [
    {
      "parameterPushStatus": "Success",
      "appPushStatus": "Success",
      "appName": "PAXSTORE SDK Demo",
      "appPushTime": 1575274370000,
      "pushStartTime": 1575274320000,
      "terminalId": 1013403755,
      "versionName": "7.0.0-inner",
      "parameterTemplateName": "parameter.xml",
      "parameterPushError": null,
      "parameterPushTime": 1575274373000,
      "serialNo": "1170000652",
      "pushType": "Terminal",
      "parameters": "{\"sys.param.termId\": \"44\", \"sys.param.merCode\": \"000000000000001\", \"sys.param.merName\": \"Union Pay\", \"sys.param.acqInsCode\": \"00000000000\"}",
      "appPushError": null
    }
  ]
}

```

The type in dataSet of is OptimizedParameterPushHistoryDTO. And the structure shows like below.

|Property Name|Type|Description|
|:--|:--|:--|
|TerminalId |long |the id of terminal|
|SerialNo |string |the serial number of terminal|
|AppName |string |the name of the app pushed|
|VersionName |string |the version name of app|
|PushStartTime |long |the start time of the push, it is millisecond|
|AppPushTime |long |app push time|
|AppPushStatus |string |the push result status, value can be Success and Fail|
|AppPushError |string |the reason of app push fail|
|ParameterTemplateName |string |the pushed parameter template name|
|ParameterPushTime|long |parameter push time|
|ParameterPushStatus|string|the parameter push result status, value can be Success and Fail|
|ParameterPushError|string|the reason of parameter push failed|
|Parameters|map |optimized parameter values, parameter variables are replaced, parameter key is the parameter pid|
|PushType |string |push type, value can be Terminal or Group|
