## TerminalVariable API

All terminal variable related APIs are encapsulated in classes *com.pax.market.api.sdk.java.api.terminalVariable.TerminalVariableApi*.

**Constructors of TerminalApkParameter**

```
public TerminalVariableApi(String baseUrl, String apiKey, String apiSecret);
```

**Constructor parameters description**

|Name|Type|Description|
|:---|:---|:---|
|baseUrl|String|the base url of REST API|
|apiKey|String|the apiKey of marketplace, get this key from PAXSTORE admin console, refer to chapter Apply access rights|
|apiSecret|String|apiSecret, get api secret from PAXSTORE admin console, refer to chapter Apply access rights|

### Get terminal variable by tid , serialNo , packageName, key, source

The get terminal variable API allows third party system to get terminal variable by tid , serialNo , packageName, key, source

**API**

```
public Result<ParameterVariableDTO> getTerminalVariable(int pageNo, int pageSize , SearchOrderBy orderBy,String tid , String serialNo , String packageName, String key, VariableSource source)
```

**Input parameter(s) description**  


|Parameter Name|Type|Nullable|Description|
|:---|:---|:---|:---|
|pageNo|int|false|page number, value must >=1|
|pageSize|int|false|the record number per page, range is 1 to 1000|
|orderBy|SearchOrderBy|true|the sort order by field name, if this parameter is null the search result will order by created date descend. The value of this parameter can be one of SearchOrderBy.Variable_asc and SearchOrderBy.Variable_desc.|
|tid|String|true|The tid of terminal, tid and serialNo cannot be empty at same time|
|serialNo|String|true|The serial number of terminal|
|packageName|String|true|The package name required to get the terminal variable|
|key|String|true|The terminal variable key|
|source|VariableSource|true|The variable source|

**Sample codes**

```
TerminalVariableApi terminalVariableApi = new TerminalVariableApi("https://api.whatspos.com/p-market-api", "RCA9MDH6YN3WSSGPW6TJ", "TUNLDZVZECHNKZ4FW07XFCKN2W0N8ZDEA5ENKZYN");
Result<ParameterVariableDTO> result = terminalVariableApi.getTerminalVariable(1,2,TerminalVariableApi.SearchOrderBy.Variable_asc,"124465D345",null,null,null,null);
```

**Server side validation failed sample result(JSON formatted)**

```
{
	"businessCode": 1800,
	"message": "Terminal not found"
}
```

**Successful sample result(JSON formatted)**

```
{
	"businessCode": 0,
	"pageInfo": {
		"pageNo": 1,
		"limit": 2,
		"totalCount": 10,
		"hasNext": true,
		"dataset": [{
			"createdDate": 1519609650000,
			"appName": null,
			"appPackageName": null,
			"id": 1000000169,
			"source": "M",
			"updatedDate": 1519609650000,
			"value": "www",
			"key": "MARKET_DOMAIN",
			"remarks": "This variable is used in all apps of the market terminals"
		}, {
			"createdDate": 1519609650000,
			"appName": "一键清理大师",
			"appPackageName": "cn.com.opda.android.clearmaster",
			"id": 1000000162,
			"source": "M",
			"updatedDate": 1519803201000,
			"value": "Global",
			"key": "MARKET_NAME",
			"remarks": "This variable is only used in the specified app of the market terminals"
		}]
	}
}
```

The type in dataSet is ParameterVariableDTO. And the structure like below.

| Property Name  | Type   | Description                 |
| -------------- | ------ | --------------------------- |
| id             | Long   | The id of terminal variable |
| appPackageName | String | The app package name        |
| appName        | String | The app name                |
| key            | String | Terminal variable key       |
| value          | String | Terminal variable value     |
| remarks        | String | Comment                     |
| source         | String | Source type                 |
| createdDate    | Long   |                             |
| updatedDate    | Long   |                             |

**Possible client validation errors**  

> <font color=red>pageNo:must be greater than or equal to 1</font>   
> <font color=red>pageSize:must be greater than or equal to 1</font>   
> <font color=red>pageSize:must be less than or equal to 1000</font>  
> <font color=red>Parameter tid and serialNo cannot be null at same time!</font> 



### Create a terminal variable


**API**

```
public Result<String> createTerminalVariable(TerminalParameterVariableRequest createRequest)
```

**Input parameter(s) description**

| Parameter Name | Type                             | Nullable | Description                                         |
| :------------- | :------------------------------- | :------- | :-------------------------------------------------- |
| createRequest  | TerminalParameterVariableRequest | false    | the create request object, the structure like below |

Structure of class TerminalParameterVariableRequest

| Property Name | Type | Nullable|Description |
|:--- | :---|:---|:---|
|tid|String|true|the tid of terminal|
|serialNo|String|true|the serial number of terminal|
|variableList|List\<ParameterVariable\>|false|List of parametervariables,the structure like below|

Structure of class ParameterVariable

| Property Name | Type   | Nullable | Description             |
| :------------ | :----- | :------- | :---------------------- |
| packageName   | String | true | The app package name    |
| key           | String | false | Terminal variable key   |
| value         | String | true     | Terminal variable value |
| remarks       | String | false    | Comment                 |

**Sample codes**

```
TerminalVariableApi terminalVariableApi = new TerminalVariableApi("https://api.whatspos.com/p-market-api", "RCA9MDH6YN3WSSGPW6TJ", "TUNLDZVZECHNKZ4FW07XFCKN2W0N8ZDEA5ENKZYN");
TerminalParameterVariableRequest createRequest = new TerminalParameterVariableRequest();
ParameterVariable parameterVariable1 = new ParameterVariable();
parameterVariable1.setKey("testUpdateVariable-key-WITHOUT-APPiD-BO-testAdDvALIDATE");
parameterVariable1.setValue("testUpdateVariable-value1");
parameterVariable1.setRemarks("今日头条app testCreateApi3");
ParameterVariable parameterVariable2 = new ParameterVariable();
parameterVariable2.setKey("testCreateVariable1Api4-BO");
parameterVariable2.setValue("testApiCreate4");
parameterVariable2.setPackageName("");
parameterVariable2.setRemarks("今日头条app testCreateApi4");
List<ParameterVariable> variableList = new ArrayList<>();
variableList.add(parameterVariable1);
variableList.add(parameterVariable2);
createRequest.setTid("124465D345");
createRequest.setVariableList(variableList);
Result<String> createResult = terminalVariableApi.createTerminalVariable(createRequest);
```

**Client side validation failed sample result(JSON formatted)**

```
{
	"businessCode": -1,
	"validationErrors": ["Parameter terminalVariableRequest cannot be null!"]
}
```

**Server side validation failed sample result(JSON formatted)**

```
{
	"businessCode": 2026,
	"message": "Tid and serialNo cannot be empty at same time"
}
```

**Successful sample result**

```
{
	"businessCode": 0
}
```

**Possible validation errors**

> <font color=red>variableList can not be empty</font> 
> <font color=red>The parameter serialNo and tid  cannot be blank at same time!</font> 



**Possible business codes**

| Business Code | Message                                                      | Description |
| :------------ | :----------------------------------------------------------- | :---------- |
| 113           | Your request is invalid, please try again or contact marketplace administrator |             |
| 1000          | App not found                                                |             |
| 1800          | Terminal not found                                           |             |
| 2026          | Tid and serialNo cannot be empty at same time                |             |
| 13101         | Invalid terminal parameter variables                         |             |



### Update terminal variable by id

Update terminal variable  by id.


**API**

```
public Result<String> updateTerminalVariable(Long terminalVariableId, ParameterVariable updateRequest)
```

**Input parameter(s) description**

|Parameter Name|Type|Nullable|Description|
|:---|:---|:---|:---|
|terminalVariableId|Long|false|the id of terminal variable|
|updateRequest|ParameterVariable|false|The parameterVariable request object. The structure shows below.|

Structure of class ParameterVariable

| Property Name | Type   | Nullable | Description                |
| :------------ | :----- | :------- | :------------------------- |
| packageName   | String | true     | The name of param template |
| key           | String | false    | Terminal variable key      |
| value         | String | true     | Terminal variable value    |
| remarks       | String | true     | Comment                    |

Note: parameterVariable cannot be empty

**Sample codes**

```
TerminalVariableApi terminalVariableApi = new TerminalVariableApi("https://api.whatspos.com/p-market-api", "RCA9MDH6YN3WSSGPW6TJ", "TUNLDZVZECHNKZ4FW07XFCKN2W0N8ZDEA5ENKZYN");
Long terminalVariableId = 1000001148L;
ParameterVariable updateRequest = new ParameterVariable();
updateRequest.setKey("testUpdateVariable-key2-BO-UPDATE");
updateRequest.setValue("testUpdateVariable-value1");
updateRequest.setRemarks("updateRemarks1");
updateRequest.setPackageName("com.ss.android.article.lite");
Result<String> updateResult = terminalVariableApi.updateTerminalVariable(terminalVariableId,updateRequest);
```

**Client side validation failed sample result(JSON formatted)**

```
{
	"businessCode": -1,
	"validationErrors": ["Terminal variable Id  cannot be null and cannot be less than 1!"]
}
```

**Server side validation failed sample result(JSON formatted)**

```
{
	"businessCode": 13000
	"message"::"Variable not found"
}
```

**Successful sample result(JSON formatted)**

```
{
	"businessCode": 0
}
```

> <font color="red">Parameter terminalVariableId cannot be null and cannot be less than 1!</font>

**Possible business codes**

|Business Code|Message|Description|
|:---|:---|:---|
|113|Your request is invalid, please try again or contact marketplace administrator|&nbsp;|
|13100|Variable not found||
|13101|Invalid terminal parameter variables||



### Delete terminal variable by terminal variable id

The delete terminal variable API allows third party system to delete terminal variable.

**API**

```
public Result<String> deleteTerminalVariable(Long terminalVariableId)
```

**Input parameter(s) description**  


|Parameter Name|Type|Nullable|Description|
|:---|:---|:---|:---|
|terminalVariableId|Long|false|The terminal variable id|

**Sample codes**

```
TerminalVariableApi terminalVariableApi = new TerminalVariableApi("https://api.whatspos.com/p-market-api", "RCA9MDH6YN3WSSGPW6TJ", "TUNLDZVZECHNKZ4FW07XFCKN2W0N8ZDEA5ENKZYN");
Long terminalVariableId = 1000001156L;
Result<String> deleteResult = terminalVariableApi.deleteTerminalVariable(terminalVariableId);
```

**Client side validation failed sample result(JSON formatted)**

```
{
	"businessCode": -1,
	"validationErrors": ["Terminal variable Id  cannot be null and cannot be less than 1!"]
}
```

**Server side validation failed sample result(JSON formatted)**

```
{
	"businessCode": 13000,
	"message": "Variable not found"
}
```

**Successful sample result(JSON formatted)**

```
{
	"businessCode": 0
}
```

| Business Code | Message                                                      | Description |
| :------------ | :----------------------------------------------------------- | :---------- |
| 113           | Your request is invalid, please try again or contact marketplace administrator |             |
| 13100         | Variable not found                                           |             |



### Batch  delete terminalvariable 

**API**

```
public Result<String> batchDeletionTerminalVariable(TerminalParameterVariableDeleteRequest batchDeletionRequest)
```

**Input parameter(s) description** 

| Parameter Name       | Type                                   | Nullable | Description                                                 |
| :------------------- | :------------------------------------- | :------- | :---------------------------------------------------------- |
| batchDeletionRequest | TerminalParameterVariableDeleteRequest | false     | The batchDeletionRequest object. The structure shows below. |

Structure of class ParameterVariable

| Property Name | Type       | Nullable | Description                     |
| :------------ | :--------- | :------- | :------------------------------ |
| variableIds   | List\<Long\> | false    | The ids of terminal variable id |

**Sample codes**

```
TerminalVariableApi terminalVariableApi = new TerminalVariableApi("https://api.whatspos.com/p-market-api", "RCA9MDH6YN3WSSGPW6TJ", "TUNLDZVZECHNKZ4FW07XFCKN2W0N8ZDEA5ENKZYN");
TerminalParameterVariableDeleteRequest batchDeletionRequest = new TerminalParameterVariableDeleteRequest();
List<Long> variableIds = new ArrayList<>();
variableIds.add(1000001137L);
variableIds.add(32423523L);
batchDeletionRequest.setVariableIds(variableIds);
Result<String> batchDeletionResult = terminalVariableApi.batchDeletionTerminalVariable(batchDeletionRequest);
```

**Client side validation failed sample result(JSON formatted)**

```
{
	"businessCode": -1,
	"validationErrors": ["Parameter batchDeletionRequest cannot be null!"]
}
```

**Successful sample result(JSON formatted)**

```
{
	"businessCode": 0
}
```


**Possible validation errors**

> <font color=red>Parameter batchDeletionRequest cannot be null!</font>   
> <font color=red>variableIds cannot be empty!</font> 

