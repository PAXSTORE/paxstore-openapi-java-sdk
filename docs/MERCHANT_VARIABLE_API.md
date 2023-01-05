## MerchantVariable API

All merchant variable related APIs are encapsulated in classes *
com.pax.market.api.sdk.java.api.merchantVariable.MerchantVariableApi*.

**Constructors of MerchantVariableApi**

```
public MerchantVariableApi(String baseUrl, String apiKey, String apiSecret);
```

**Constructor parameters description**

|Name|Type|Description|
|:---|:---|:---|
|baseUrl|String|the base url of REST API|
|apiKey|String|the apiKey of marketplace, get this key from PAXSTORE admin console, refer to chapter Apply access rights|
|apiSecret|String|apiSecret, get api secret from PAXSTORE admin console, refer to chapter Apply access rights|

### Search merchant variable by merchantId, packageName, key, source

The search merchant variable API allows third party system to search merchant variable by merchantId, packageName, key, source

**API**

```
public Result<MerchantVariableDTO> searchMerchantVariable(int pageNo, int pageSize, SearchOrderBy orderBy, Long merchantId, String packageName, String key, VariableSource source)
```

**Input parameter(s) description**

|Parameter Name|Type|Nullable|Description|
|:---|:---|:---|:---|
|pageNo|int|false|page number, value must >=1|
|pageSize|int|false|the record number per page, range is 1 to 100|
|orderBy|SearchOrderBy|true|the sort order by field name, if this parameter is null the search result will order by created date descend. The value of this parameter can be one of SearchOrderBy.Variable_asc and SearchOrderBy.Variable_desc.|
|merchantId|Long|false|The id of merchant|
|packageName|String|true|The package name required to get the merchant variable|
|key|String|true|The variable key|
|source|VariableSource|true|The source of variable, the value can be one of M(market), C(merchant)|

**Sample codes**

```
MerchantVariableApi merchantVariableApi = new MerchantVariableApi("https://api.whatspos.com/p-market-api", "RCA9MDH6YN3WSSGPW6TJ", "TUNLDZVZECHNKZ4FW07XFCKN2W0N8ZDEA5ENKZYN");
Result<MerchantVariableDTO> result = merchantVariableApi.searchMerchantVariable(1,2,MerchantVariableApi.SearchOrderBy.Variable_asc, 113781L, null,null , MerchantVariableApi.VariableSource.MERCHANT);
```

**Server side validation failed sample result(JSON formatted)**

```
{
	"businessCode": 1720,
	"message": "Merchant doesn't exist"
}
```

**Successful sample result(JSON formatted)**

```
{
	"businessCode": 0,
	"pageInfo": {
		"limit": 2,
    "pageNo": 1,
    "totalCount": 2,
    "hasNext": false,
		"dataset": [{
      "id": 1000000009,
      "appPackageName": "",
      "type": "T",
      "key": "key1",
      "value": "value1",
      "remarks": "R1",
      "source": "C",
      "createdDate": 1666160856000,
      "updatedDate": 1666160856000
    },
    {
      "id": 1000000010,
      "appPackageName": "",
      "type": "T",
      "key": "key2",
      "value": "value2",
      "remarks": "R2",
      "source": "C",
      "createdDate": 1666161025000,
      "updatedDate": 1666161025000
    }]
	}
}
```

The type in dataSet is MerchantVariableDTO. And the structure like below.

| Property Name  | Type   | Description                                    |
| -------------- | ------ | ---------------------------------------------- |
| id             | Long   | The id of merchant variable                    |
| appPackageName | String | The app package name                           |
| appName        | String | The app name                                   |
| type           | String | Merchant variable type, T(text) or P(password) |
| key            | String | Merchant variable key                          |
| value          | String | Merchant variable value                        |
| remarks        | String | Comment                                        |
| source         | String | Source type,M(Market) or C(Merchant)           |
| createdDate    | Long   |                                                |
| updatedDate    | Long   |                                                |

**Possible client validation errors**

> <font color=red>pageNo:must be greater than or equal to 1</font>   
> <font color=red>pageSize:must be greater than or equal to 1</font>   
> <font color=red>pageSize:must be less than or equal to 100</font>  
> <font color=red>Parameter merchantId cannot be null at same time!</font>

### Create a merchant variable

**API**

```
public Result<String> createMerchantVariable(MerchantVariableCreateRequest createRequest)
```

**Input parameter(s) description**

| Parameter Name | Type                          | Nullable | Description                                         |
| :------------- | :---------------------------- | :------- | :-------------------------------------------------- |
| createRequest  | MerchantVariableCreateRequest | false    | the create request object, the structure like below |

Structure of class MerchantVariableCreateRequest

| Property Name | Type | Nullable|Description |
|:--- | :---|:---|:---|
| merchantId    |Long|False|the id of merchant|
|variableList|List\<ParameterVariable\>|false|List of parametervariables,the structure like below|

Structure of class ParameterVariable

| Property Name | Type   | Nullable | Description                                    |
| :------------ | :----- | :------- | :--------------------------------------------- |
| packageName   | String | true     | The app package name                           |
| type          | String | true     | Merchant variable type, T(text) or P(password), When it is empty, the default value is "T"

|
| key           | String | false    | Merchant variable key                          |
| value         | String | true     | Merchant variable value                        |
| remarks       | String | false    | Comment                                        |

**Sample codes**

```
MerchantVariableApi merchantVariableApi = new MerchantVariableApi("https://api.whatspos.com/p-market-api", "RCA9MDH6YN3WSSGPW6TJ", "TUNLDZVZECHNKZ4FW07XFCKN2W0N8ZDEA5ENKZYN");
Long merchantId = 113781L;
MerchantVariableCreateRequest createRequest = new MerchantVariableCreateRequest();
ParameterVariable parameterVariable1 = new ParameterVariable();
parameterVariable1.setKey("testCreateMerchantVariable-key1");
parameterVariable1.setValue("testCreateMerchantVariable-value1");
parameterVariable1.setRemarks("今日头条app testCreateApi3");
ParameterVariable parameterVariable2 = new ParameterVariable();
parameterVariable2.setKey("testCreateMerchantVariable-key2");
parameterVariable2.setValue("testCreateMerchantVariable-value2");
parameterVariable2.setPackageName("");
parameterVariable2.setRemarks("testCreateApi2");
List<ParameterVariable> variableList = new ArrayList<>();
variableList.add(parameterVariable1);
variableList.add(parameterVariable2);
createRequest.setMerchantId(merchantId);
createRequest.setVariableList(variableList);
Result<String> createResult = merchantVariableApi.createMerchantVariable(createRequest);
```

**Client side validation failed sample result(JSON formatted)**

```
{
	"businessCode": -1,
	"validationErrors": ["Parameter merchantVariableCreateRequest cannot be null!"]
}
```

**Server side validation failed sample result(JSON formatted)**

```
{
	"businessCode": 1720,
	"message": "Merchant doesn't exist"
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
> <font color=red>Parameter merchantId cannot be null and cannot be less than 1!</font>



**Possible business codes**

| Business Code | Message                                                      | Description |
| :------------ | :----------------------------------------------------------- | :---------- |
| 135           | Request parameter is missing or invalid                      |             |
| 1000          | App not found                                                |             |
| 1010          | App is invalid                                               |             |
| 1720          | Merchant doesn't exist                                       |             |
| 13001         | Variable key is mandatory                                    |             |
| 13002         | Variable key is invalid, only letters, numbers, dash, underline and dot is allowed |             |
| 13003         | Invalid variable type, only text(T) and password(P) is allowed |             |
| 13004         | Variable value is too long                                   |             |
| 13011         | Variable remarks is too long                                 |             |
| 13015         | Variable key is too long                                     |             |
| 13016         | Variable with same key and application already exist         |             |
| 13200         | Merchant parameter variable invalid                          |             |

### Update merchant variable by id

Update merchant variable by id.

**API**

```
public Result<String> updateMerchantVariable(Long merchantVariableId, MerchantVariableUpdateRequest updateRequest)
```

**Input parameter(s) description**

|Parameter Name|Type|Nullable|Description|
|:---|:---|:---|:---|
|merchantVariableId|Long|false|the id of merchant variable|
|updateRequest|MerchantVariableUpdateRequest|false|The parameterVariable request object. The structure shows below.|

Structure of class MerchantVariableUpdateRequest

| Property Name | Type   | Nullable | Description                                    |
| :------------ | :----- | :------- | :--------------------------------------------- |
| packageName   | String | true     | The name of param template                     |
| type          | String | true     | Merchant variable type, T(text) or P(password), When it is empty, the default value is "T"

|
| key           | String | false    | Merchant variable key                          |
| value         | String | true     | Merchant variable value                        |
| remarks       | String | true     | Comment                                        |

Note: updateRequest cannot be empty

**Sample codes**

```
MerchantVariableApi merchantVariableApi = new MerchantVariableApi("https://api.whatspos.com/p-market-api", "RCA9MDH6YN3WSSGPW6TJ", "TUNLDZVZECHNKZ4FW07XFCKN2W0N8ZDEA5ENKZYN");
Long merchantVariableId = 1000000008L;
MerchantVariableUpdateRequest updateRequest = new MerchantVariableUpdateRequest();
updateRequest.setKey("testUpdateVariable-key1");
updateRequest.setValue("testUpdateVariable-value1");
updateRequest.setRemarks("updateRemarks1");
updateRequest.setPackageName("com.jbangit.csapp");
Result<String> updateResult = merchantVariableApi.updateMerchantVariable(merchantVariableId,updateRequest);
```

**Client side validation failed sample result(JSON formatted)**

```
{
	"businessCode": -1,
	"validationErrors": ["Parameter merchantVariableId cannot be null and cannot be less than 1!"]
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

> <font color="red">Parameter merchantVariableId cannot be null and cannot be less than 1!</font>

**Possible business codes**

|Business Code|Message|Description|
|:---|:---|:---|
|113|Your request is invalid, please try again or contact marketplace administrator|&nbsp;|
| 13001         | Variable key is mandatory                                    |             |
| 13002         | Variable key is invalid, only letters, numbers, dash, underline and dot is allowed |             |
| 13003         | Invalid variable type, only text(T) and password(P) is allowed |             |
| 13004         | Variable value is too long                                   |             |
| 13011         | Variable remarks is too long                                 |             |
| 13015         | Variable key is too long                                     ||
|13016|Variable with same key and application already exist||
|13100|Variable not found||
|13200|Merchant parameter variable invalid||

### Delete merchant variable by merchant variable id

The delete merchant variable API allows third party system to delete merchant variable.

**API**

```
public Result<String> deleteMerchantVariable(Long merchantVariableId)
```

**Input parameter(s) description**

|Parameter Name|Type|Nullable|Description|
|:---|:---|:---|:---|
|merchantVariableId|Long|false|The merchant variable id|

**Sample codes**

```
MerchantVariableApi merchantVariableApi = new MerchantVariableApi("https://api.whatspos.com/p-market-api", "RCA9MDH6YN3WSSGPW6TJ", "TUNLDZVZECHNKZ4FW07XFCKN2W0N8ZDEA5ENKZYN");
Long merchantVariableId = 1000000008L;
Result<String> deleteResult = merchantVariableApi.deleteMerchantVariable(merchantVariableId);
```

**Client side validation failed sample result(JSON formatted)**

```
{
	"businessCode": -1,
	"validationErrors": ["Parameter merchantVariableId cannot be null and cannot be less than 1!"]
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



### Batch  delete merchant variable

**API**

```
public Result<String> batchDeletionMerchantVariable(MerchantVariableDeleteRequest batchDeleteRequest)
```

**Input parameter(s) description**

| Parameter Name       | Type                                   | Nullable | Description                                                 |
| :------------------- | :------------------------------------- | :------- | :---------------------------------------------------------- |
| batchDeleteRequest | MerchantVariableDeleteRequest | false     | The batchDeletionRequest object. The structure shows below. |

Structure of class MerchantVariableDeleteRequest

| Property Name | Type       | Nullable | Description                     |
| :------------ | :--------- | :------- | :------------------------------ |
| variableIds   | List\<Long\> | false    | The ids of merchant variable id |

**Sample codes**

```
MerchantVariableApi merchantVariableApi = new MerchantVariableApi("https://api.whatspos.com/p-market-api", "RCA9MDH6YN3WSSGPW6TJ", "TUNLDZVZECHNKZ4FW07XFCKN2W0N8ZDEA5ENKZYN");
MerchantVariableDeleteRequest batchDeletionRequest = new MerchantVariableDeleteRequest();
List<Long> variableIds = new ArrayList<>();
variableIds.add(1000000007L);
variableIds.add(1000000006L);
batchDeletionRequest.setVariableIds(variableIds);
Result<String> batchDeletionResult = merchantVariableApi.batchDeletionMerchantVariable(batchDeletionRequest);
```

**Client side validation failed sample result(JSON formatted)**

```
{
	"businessCode": -1,
	"validationErrors": ["Parameter merchantVariableDeleteRequest cannot be null!"]
}
```

**Successful sample result(JSON formatted)**

```
{
	"businessCode": 0
}
```

**Possible validation errors**

> <font color=red>Parameter merchantVariableDeleteRequest cannot be null!</font>   
> <font color=red>variableIds cannot be empty!</font> 



| Business Code | Message                                                      | Description |
| :------------ | :----------------------------------------------------------- | :---------- |
| 113           | Your request is invalid, please try again or contact marketplace administrator |             |
| 13100         | Variable not found                                           |             |
