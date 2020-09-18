## TerminalGroup API

All APIs related to terminal grouping operations are encapsulated in this class *com.pax.market.api.sdk.java.api.terminalGroup.TerminalGroupApi*.

**Constructors of TerminalGroupApi**

```
public TerminalGroupApi(String baseUrl, String apiKey, String apiSecret);
```

**Constructor parameters description**

|Name|Type|Description|
|:---|:---|:---|
|baseUrl|String|the base url of REST API|
|apiKey|String|the apiKey of marketplace, get this key from PAXSTORE admin console, refer to chapter Apply access rights|
|apiSecret|String|apiSecret, get api secret from PAXSTORE admin console, refer to chapter Apply access rights|



### Search terminal group

The search terminal group API allows third party system to Search by terminal group status or group name or resellers or models or dynamic group by page.

**API**

```
public Result<TerminalGroupDTO> searchTerminalGroup(int pageNo, int pageSize, TerminalGroupSearchOrderBy orderBy, TerminalGroupStatus status, String name, String resellerNames, String modelNames, Boolean isDynamic)
```

**Input parameter(s) description**  


|Parameter Name|Type|Nullable|Description|
|:---|:---|:---|:---|
|pageNo|int|false|page number, value must >=1|
|pageSize|int|false|the record number per page, range is 1 to 100|
|orderBy|TerminalGroupSearchOrderBy|true|the sort order by field name, if this parameter is null the search result will order by created date descend. The value of this parameter can be one of TerminalGroupSearchOrderBy.CreatedDate_desc and TerminalGroupSearchOrderBy.CreatedDate_asc and TerminalGroupSearchOrderBy.Name.|
|status|TerminalGroupStatus|true|The value of status can be one of  TerminalGroupStatus.PENDING and TerminalGroupStatus.ACTIVE and TerminalGroupStatus.SUSPEND.|
|name|String|true|The name of group|
|resellerNames|String|true|The names of reseller. Multiple names can be separated by ','.                                                           For example, 'resellerName1,resellerName2'|
|modelNames|String|true|The names of model. Multiple names can be separated by ','.                                                           For example, 'modelName1,modelName2'|
|isDynamic|Boolean|true|True or false dynamic grouping|


**Sample codes**

```
TerminalGroupApi terminalGroupApi = new TerminalGroupApi("https://api.whatspos.com/p-market-api", "RCA9MDH6YN3WSSGPW6TJ", "TUNLDZVZECHNKZ4FW07XFCKN2W0N8ZDEA5ENKZYN");
Result<TerminalGroupDTO> result =  terminalGroupApi.searchTerminalGroup(1,5, TerminalGroupApi.TerminalGroupSearchOrderBy.CreatedDate_asc, TerminalGroupApi.TerminalGroupStatus.ACTIVE,null,"test-8992","A920,E800",true);
```

**Client side validation failed sample result(JSON formatted)**

```
{
	"businessCode": -1,
	"validationErrors": ["pageNo:must be greater than or equal to 1"]
}
```

**Server side validation failed sample result(JSON formatted)**

```
{
	"businessCode": 2202,
	"message": "Terminal group status is invalid"
}
```

**Successful sample result(JSON formatted)**

```
{
	"businessCode": 0,
	"pageInfo": {
		"pageNo": 1,
		"limit": 5,
		"totalCount": 1,
		"hasNext": false,
		"dataSet": [{
			"terminalCount": 0,
            "resellerName": "test-8992",
            "description": "reer",
            "updatedDate": 1577153179000,
            "containSubResellerTerminal": false,
            "modelName": "A920",
            "createdDate": 1574751213000,
            "merchantNames": ["test-01"],
            "name": "3RD-Dy-create",
            "dynamic": true,
            "id": 16531,
            "status": "A",
            "createdByResellerId": 2
		}]
	}
}
```

The type in dataSet is TerminalGroupDTO. And the structure like below.

| Name                       | Type         | Description                                                  |
| :------------------------- | :----------- | :----------------------------------------------------------- |
| id                         | Long         | the id of terminal group                                     |
| resellerName               | String       | the name of reseller                                         |
| modelName                  | String       | the name of model                                            |
| name                       | String       | the name of terminal group                                   |
| status                     | String       | the status of terminal group,value can be one of A(Active) and S(Suspend) and P(Pending) |
| description                | String       | Description of terminal group                                |
| createdByResellerId        | Long         | the id of the reseller that created the terminal group       |
| createdDate                | Long         |                                                              |
| updatedDate                | Long         |                                                              |
| terminalCount              | int          | Number of terminals in the terminal group                    |
| dynamic                    | Boolean      | Dynamic group or general group                               |
| containSubResellerTerminal | Boolean      | Include sub resellers or not                                 |
| merchantNames              | List\<String\> | the merchant names                                           |

**Possible validation errors**

> <font color=red>pageNo:must be greater than or equal to 1</font>   
> <font color=red>pageSize:must be greater than or equal to 1</font>   
> <font color=red>pageSize:must be less than or equal to 100</font>  

**Possible business codes**

| Business Code | Message                          | Description |
| :------------ | :------------------------------- | :---------- |
| 2202          | Terminal group status is invalid |             |


### Get terminal group

Get terminal group by id.
**API**

```
 public Result<TerminalGroupDTO> getTerminalGroup(Long groupId)
```

**Input parameter(s) description**

| Parameter Name | Type | Nullable | Description              |
| :------------- | :--- | :------- | :----------------------- |
| groupId        | Long | false    | the id of terminal group |

**Sample codes**

```
TerminalGroupApi terminalGroupApi = new TerminalGroupApi("https://api.whatspos.com/p-market-api", "RCA9MDH6YN3WSSGPW6TJ", "TUNLDZVZECHNKZ4FW07XFCKN2W0N8ZDEA5ENKZYN");
Result<TerminalGroupDTO> result = terminalGroupApi.getTerminalGroup(groupId);
```

**Client side validation failed sample result(JSON formatted)**

```
{
	"businessCode": -1,
	"validationErrors": ["Terminal Group Id  cannot be null and cannot be less than 1!"]
}
```

**Server side validation failed sample result(JSON formatted)**

```
{
	"businessCode": 2150,
	"message": "Terminal group not found"
}
```

**Successful sample result**

```
{
	"businessCode": 0,
	"data": {
		"modelName": "A920",
		"createdDate": 1574751213000,
		"terminalCount": 0,
		"resellerName": "test-8992",
		"name": "3RD-create",
		"description": "reer",
		"dynamic": true,
		"id": 16531,
		"updatedDate": 1577153179000,
		"containSubResellerTerminal": false,
		"status": "A",
		"createdByResellerId": 2
	}
}
```

The type in data is TerminalGroupDTO. And the structure like below.

| Name                       | Type         | Description                                                  |
| :------------------------- | :----------- | :----------------------------------------------------------- |
| id                         | Long         | the id of terminal group                                     |
| resellerName               | String       | the name of reseller                                         |
| modelName                  | String       | the name of model                                            |
| name                       | String       | the name of terminal group                                   |
| status                     | String       | the status of terminal group,value can be one of A(Active) and S(Suspend) and P(Pending) |
| description                | String       | Description of terminal group                                |
| createdByResellerId        | Long         | the id of the reseller that created the terminal group       |
| createdDate                | Long         |                                                              |
| updatedDate                | Long         |                                                              |
| terminalCount              | int          | Number of terminals in the terminal group                    |
| dynamic                    | Boolean      | Dynamic group or general group                               |
| containSubResellerTerminal | Boolean      | Include sub resellers or not                                 |
| merchantNames              | List\<String\> | the merchant names                                           |

**Possible client validation errors**  

> <font color="red">Parameter groupId cannot be null and cannot be less than 1!</font>

**Possible business codes**

| Business Code | Message                  | Description |
| :------------ | :----------------------- | :---------- |
| 2150          | Terminal group not found |             |

### Create a terminal group

**API**

```
public Result<TerminalGroupDTO> createTerminalGroup(CreateTerminalGroupRequest createRequest)
```

**Input parameter(s) description**

|Parameter Name|Type|Nullable|Description|
|:---|:---|:---|:---|
|createRequest|CreateTerminalGroupRequest|false|the create request object, the structure like below|

Structure of class CreateTerminalGroupRequest

| Property Name              | Type         | Nullable | Description                                                  |
| :------------------------- | :----------- | :------- | :----------------------------------------------------------- |
| name                       | String       | false    | the name of group                                          |
| modelName                  | String       | false    | the model name, only the same model termina can be add to this group                                |
| resellerName               | String       | false    | reseller name, only the terminals in this reseller can be add to this group|
| description                | String       | true     |                                                              |
| status                     | String       | true     | the status of terminal group,the values can be 'P' and 'A', if the value is null will create group with default status P(Pending)|
| dynamic                    | Boolean      | true     | Indicate whether the group is dynamic group or general group, the default value is false (general group)                                   |
| containSubResellerTerminal | Boolean      | true     | Indicate whether to conatin sub reseller's termnal for dynamic group, this property is for dynamic group, if the value is null will use the default value false                                   |
| merchantNameList           | List\<String\> | true     | merchant names, only terminals belong to those merchant can be add to group, this property is for dynamic group                                                            |

**Sample codes**

```
TerminalGroupApi terminalGroupApi = new TerminalGroupApi("https://api.whatspos.com/p-market-api", "RCA9MDH6YN3WSSGPW6TJ", "TUNLDZVZECHNKZ4FW07XFCKN2W0N8ZDEA5ENKZYN");
  CreateTerminalGroupRequest createRequest = new CreateTerminalGroupRequest();
  createRequest.setName("test-create");
  createRequest.setModelName("A920");
  createRequest.setResellerName("test-8992");
  createRequest.setDescription("TEST");
  createRequest.setStatus("P");
  //or createRequest.setStatus(TerminalGroupApi.TerminalGroupStatus.Pending.val());
  createRequest.setContainSubResellerTerminal(false);
  List<String> merchantNames = new ArrayList<>();
  merchantNames.add("test-01");
  merchantNames.add("test-02");
  createRequest.setMerchantNameList(merchantNames);
  createRequest.setDynamic(true);
Result<TerminalGroupDTO> result = terminalGroupApi.createTerminalGroup(createRequest);
```

**Client side validation failed sample result(JSON formatted)**

```
{
	"businessCode": -1,
	"validationErrors": ["Parameter terminalGroupCreateRequest cannot be null!"]
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
		"terminalCount": 0,
		"resellerName": "test-8992",
		"description": "TEST",
		"updatedDate": 1583217982007,
		"containSubResellerTerminal": false,
		"modelName": "A920",
		"createdDate": 1583217982007,
		"merchantNames": ["test-01","test-02"],
		"name": "test-create",
		"dynamic": true,
		"id": 16583,
		"status": "P",
		"createdByResellerId": 2
	}
}
```

The type in data is TerminalGroupDTO. And the structure like below.

| Name                       | Type         | Description                                                  |
| :------------------------- | :----------- | :----------------------------------------------------------- |
| id                         | Long         | the id of terminal group                                     |
| resellerName               | String       | the name of reseller                                         |
| modelName                  | String       | the name of model                                            |
| name                       | String       | the name of terminal group                                   |
| status                     | String       | the status of terminal group,value can be one of A(Active) and S(Suspend) and P(Pending) |
| description                | String       | Description of terminal group                                |
| createdByResellerId        | Long         | the id of the reseller that created the terminal group       |
| createdDate                | Long         |                                                              |
| updatedDate                | Long         |                                                              |
| terminalCount              | int          | Number of terminals in the terminal group                    |
| dynamic                    | Boolean      | Dynamic group or general group                               |
| containSubResellerTerminal | Boolean      | Include sub resellers or not                                 |
| merchantNames              | List\<String\> | the merchant names                                           |

**Possible client validation errors**


> <font color="red">Parameter CreateTerminalGroupRequest can not be empty!</font>

**Possible business codes**

|Business Code|Message|Description|
|:---|:---|:---|
|1759| Reseller doesn't exist                       ||
|1762| Reseller name is mandatory                   ||
|1720| Merchant doesn't exist                       ||
|1937| Merchant is not belong to the given Reseller ||
|1700| Model doesn't exist                          ||
|2151| Terminal group name is mandatory             ||
|2152| Terminal group model is mandatory            ||
|2153| Terminal group reseller is mandatory         ||
|2154| Terminal group name is too long              ||
|2156| Terminal group name already exists           ||
|2161| Terminal group description is too long       ||
|1854| The type of merchant id  is wrong            ||
|1720| Merchant doesn't exist                       ||
|1737| The associated merchant is not activate      ||
|1713| The associated model is not activate         ||
|1773| The associated reseller is not activate      ||




### Search terminal

This API is provided to the third party system to add terminals and search terminals in the terminal group.


**API**

```
public Result<TerminalDTO> searchTerminal(int pageNo, int pageSize, TerminalApi.TerminalSearchOrderBy orderBy,TerminalStatus status, String modelName,  String resellerName, String merchantName, String serialNo, String excludeGroupId)
```

**Input parameter(s) description**  

| Parameter Name | Type                              | Nullable | Description                                                  |
| :------------- | :-------------------------------- | :------- | :----------------------------------------------------------- |
| pageNo         | int                               | false    | page number, value must >=1                                  |
| pageSize       | int                               | false    | the record number per page, range is 1 to 100                |
| orderBy        | TerminalApi.TerminalSearchOrderBy | true     | the sort order by field name, if this parameter is null the search result will order by created date descend. The value of this parameter can be one of TerminalApi.TerminalSearchOrderBy.Name and TerminalApi.TerminalSearchOrderBy.Tid and TerminalApi.TerminalSearchOrderBy.SerialNo. |
| status         | TerminalStatus                    | true     | Terminal status. The value can be one of  TerminalApi.TerminalStatus.Active and TerminalApi.TerminalStatus.Inactive and TerminalApi.TerminalStatus.Suspend |
| modelName      | String                            | true     | the model name                                               |
| resellerName   | String                            | true     | the reseller name                                            |
| serialNo       | String                            | true     | the serial number of terminal                                |
| excludeGroupId | String                            | true     | group id which the terminals in this group will be excluded from search result. This search parameter does not support dynamic group |

**Sample codes**

```
TerminalGroupApi terminalGroupApi = new TerminalGroupApi("https://api.whatspos.com/p-market-api", "RCA9MDH6YN3WSSGPW6TJ", "TUNLDZVZECHNKZ4FW07XFCKN2W0N8ZDEA5ENKZYN");
Result<TerminalDTO> result = terminalGroupApi.searchTerminal(1,5, TerminalApi.TerminalSearchOrderBy.Name, TerminalApi.TerminalStatus.Active,"A920","test-8992",null,null);
```

**Client side validation failed sample result(JSON formatted)**

```
{
	"businessCode": -1,
	"validationErrors": ["pageNo:must be greater than or equal to 1"]
}
```

**Server side validation failed sample result(JSON formatted)**

```
{
	"businessCode": 2203,
	"message": "Terminal status is invalid"
}
```

**Successful sample result(JSON formatted)**

```
{
	"businessCode": 0,
	"pageInfo": {
		"pageNo": 1,
		"limit": 5,
		"totalCount": 2,
		"hasNext": false,
		"dataset": [{
			"modelName": "A920",
			"resellerName": "test-8992",
			"name": "12312SSASAQSAQWS",
			"location": "",
			"id": 909753,
			"tid": "12312SSASAQSAQWS",
			"serialNo": "12312SSASAQSAQWS",
			"status": "A",
			"merchantName": "merchant a"
		}, {
			"modelName": "A920",
			"resellerName": "test-8992",
			"name": "test8992",
			"location": "",
			"id": 909744,
			"tid": "DONP3PIU",
			"serialNo": "1223131",
			"status": "A",
			"merchantName": "merchant b"
		}]
	}
}
```

The type in dataSet is SimpleTerminalDTO. And the structure like below.

| Property Name | Type   | Description             |
| ------------- | ------ | ----------------------- |
| id            | Long   | The id of terminal      |
| name          | String | The app package name    |
| tid           | String | The app name            |
| serialNo      | String | Terminal variable key   |
| status        | String | Terminal variable value |
| merchantName  | String | Comment                 |
| modelName     | String | Source type             |
| resellerName  | Long   |                         |
| location      | Long   |                         |

**Possible validation errors**

> <font color=red>pageNo:must be greater than or equal to 1</font>   
> <font color=red>pageSize:must be greater than or equal to 1</font>   
> <font color=red>pageSize:must be less than or equal to 100</font>  



### Update terminal group

This api allows the third party system update terminal group.


**API**

```
public Result<TerminalGroupDTO> updateTerminalGroup(Long groupId ,UpdateTerminalGroupRequest updateRequest)
```

**Input parameter(s) description**  


|Parameter Name|Type|Nullable|Description|
|:---|:---|:---|:---|
|groupId|Long|false|The id of terminal group|
|updateRequest|UpdateTerminalGroupRequest|false||


Structure of class UpdateTerminalGroupRequest

|Property Name|Type|Nullable|Description|
|:---|:---|:---|:---|
|name|String|true|the name of terminal group, if the value is null API won't update the origial value of this property|
|description|String|true|the description of terminal group|
|modelName|String|true| the name of model, if the value is null API won't update the origial value of this property|
|resellerName|String|true|the name of reseller, if the value is null API won't update the origial value of this property|
|merchantNameList|List\<String\>|true|the name of merchants|

Note: name, description, modelName, resellerName, and merchantNameList cannot be empty at same time. When it is not inactive, only name and description can be modified.

**Sample codes**

```
TerminalGroupApi terminalGroupApi = new TerminalGroupApi("https://api.whatspos.com/p-market-api", "RCA9MDH6YN3WSSGPW6TJ", "TUNLDZVZECHNKZ4FW07XFCKN2W0N8ZDEA5ENKZYN");
        Long groupId = 16576L;
        UpdateTerminalGroupRequest updateRequest = new UpdateTerminalGroupRequest();
        updateRequest.setName("3rdsUpdateTerminalGroupName");
        List<String> merchantNamesList = new ArrayList<>();
        merchantNamesList.add("testDelete6");
        merchantNamesList.add("12348");
        updateRequest.setMerchantNameList(merchantNamesList);
        updateRequest.setModelName("E800");
        updateRequest.setResellerName("Shawn-test-8992");
        updateRequest.setDescription("test-3rd-api-update-UPDATE");
        Result<TerminalGroupDTO> result = terminalGroupApi.updateTerminalGroup(groupId, updateRequest);
```

**Client side validation failed sample result(JSON formatted)**

```
{
	"businessCode": -1,
	"validationErrors": ["Parameter terminalGroupUpdateRequest cannot be null!"]
}
```

**Server side validation failed sample result(JSON formatted)**

```
{
	"businessCode": 2150,
	"message": "Terminal group not found"
}
```

**Successful sample result(JSON formatted)**

```
{
	"businessCode": 0,
	"data": {
	    "modelName": "E800",
		"createdDate": 1583214740000,
		"terminalCount": 0,
		"resellerName": "Shawn-test-8992",
		"name": "3rdsUpdateTerminalGroupName",
		"description": "test-3rd-api-update-UPDATE",
		"dynamic": false,
		"id": 16576,
		"updatedDate": 1583375601713,
		"containSubResellerTerminal": true,
		"status": "P",
		"createdByResellerId": 2
	}
}
```

**Possible validation errors**

> <font color=red>Parameter UpdateTerminalGroupRequestcannot be null!</font>  

**Possible business codes**

|Business Code|Message|Description|
|:---|:---|:---|
|2150|Terminal group not found||
|135|Request parameter is missing or invalid||
|1700|Model doesn't exist||
|1759|Reseller doesn't exist||
|1720|Merchant doesn't exist||
|1737|The associated merchant is not activate||
|1813|Push task has already been added, unable to update model||
|1814|Push task has already been added,unable to update reseller||
|1815|Terminal has already been added,unable to update model||
|1816|Terminal has already been added,unable to update reseller||
|1833|Push task has already been added, unable to update merchant||
|1834|Terminal has already been added,unable to update merchant||
|1937|Merchant is not belong to the given Reseller||
|2156|Terminal group name already exists||
|13102|Terminal group is not inactive, only name and description can be modified||



### Active group

This api allows the third party system active terminal group.

**API**

```
public Result<String> activeGroup(Long groupId)
```

**Input parameter(s) description**  

| Parameter Name | Type | Nullable | Description              |
| :------------- | :--- | :------- | :----------------------- |
| groupId        | Long | false    | The id of terminal group |

**Sample codes**

```
TerminalGroupApi terminalGroupApi = new TerminalGroupApi("https://api.whatspos.com/p-market-api", "RCA9MDH6YN3WSSGPW6TJ", "TUNLDZVZECHNKZ4FW07XFCKN2W0N8ZDEA5ENKZYN");
Result<String> result = terminalGroupApi.activeGroup(16549L);
```

**Client side validation failed sample result(JSON formatted)**

```
{
	"businessCode": -1,
	"validationErrors": ["Terminal Group Id  cannot be null and cannot be less than 1!"]
}
```

**Server side validation failed sample result(JSON formatted)**

```
{
	"businessCode": 2150,
	"message": "Terminal group not found"
}
```

**Successful sample result(JSON formatted)**

```
{
	"businessCode": 0
}
```

**Possible business codes**

| Business Code | Message                                 | Description |
| :------------ | :-------------------------------------- | :---------- |
| 2150          | Terminal group not found                |             |
| 1700          | Model doesn't exist                     |             |
| 1713          | The associated model is not activate    |             |
| 1759          | Reseller doesn't exist                  |             |
| 1773          | The associated reseller is not activate |             |
| 2158          | Terminal group has been activated       |             |



### Disable group

This api allows the third party system disable terminal group.

**API**

```
public Result<String> disableGroup(Long groupId)
```

**Input parameter(s) description**  

| Parameter Name | Type | Nullable | Description              |
| :------------- | :--- | :------- | :----------------------- |
| groupId        | Long | false    | The id of terminal group |

**Sample codes**

```
TerminalGroupApi terminalGroupApi = new TerminalGroupApi("https://api.whatspos.com/p-market-api", "RCA9MDH6YN3WSSGPW6TJ", "TUNLDZVZECHNKZ4FW07XFCKN2W0N8ZDEA5ENKZYN");
Result<String> result = terminalGroupApi.disableGroup(16549L);
```

**Client side validation failed sample result(JSON formatted)**

```
{
	"businessCode": -1,
	"validationErrors": ["Terminal Group Id  cannot be null and cannot be less than 1!"]
}
```

**Server side validation failed sample result(JSON formatted)**

```
{
	"businessCode": 2150,
	"message": "Terminal group not found"
}
```

**Successful sample result(JSON formatted)**

```
{
	"businessCode": 0
}
```

**Possible business codes**

| Business Code | Message                               | Description |
| :------------ | :------------------------------------ | :---------- |
| 2150          | Terminal group not found              |             |
| 2155          | Terminal group has been not activated |             |



### Delete group

This api allows the third party system delete terminal group

**API**

```
public Result<String> deleteGroup(Long groupId)
```

**Input parameter(s) description**  

| Parameter Name | Type | Nullable | Description              |
| :------------- | :--- | :------- | :----------------------- |
| groupId        | Long | false    | The id of terminal group |

**Sample codes**

```
TerminalGroupApi terminalGroupApi = new TerminalGroupApi("https://api.whatspos.com/p-market-api", "RCA9MDH6YN3WSSGPW6TJ", "TUNLDZVZECHNKZ4FW07XFCKN2W0N8ZDEA5ENKZYN");
Result<String> result = terminalGroupApi.deleteGroup(16549L);
```

**Client side validation failed sample result(JSON formatted)**

```
{
	"businessCode": -1,
	"validationErrors": ["Terminal Group Id  cannot be null and cannot be less than 1!"]
}
```

**Server side validation failed sample result(JSON formatted)**

```
{
	"businessCode": 2150,
	"message": "Terminal group not found"
}
```

**Successful sample result(JSON formatted)**

```
{
	"businessCode": 0
}
```

**Possible business codes**

| Business Code | Message                                       | Description |
| :------------ | :-------------------------------------------- | :---------- |
| 2150          | Terminal group not found                      |             |
| 2165          | The terminal group is active,unable to delete |             |



### Search terminals in group

This API is provided to the third party system to search terminals in the current terminal group.

**API**

```
public  Result<SimpleTerminalDTO> searchTerminalsInGroup(int pageNo, int pageSize, TerminalApi.TerminalSearchOrderBy orderBy, Long groupId, String serialNo, String merchantNames)
```

**Input parameter(s) description**  

| Parameter Name | Type                              | Nullable | Description                                                  |
| :------------- | :-------------------------------- | :------- | :----------------------------------------------------------- |
| pageNo         | int                               | false    | page number, value must >=1                                  |
| pageSize       | int                               | false    | the record number per page, range is 1 to 100               |
| orderBy        | TerminalApi.TerminalSearchOrderBy | true     | the sort order by field name, if this parameter is null the search result will order by created date descend. The value of this parameter can be one of TerminalApi.TerminalSearchOrderBy.Name and TerminalApi.TerminalSearchOrderBy.Tid and TerminalApi.TerminalSearchOrderBy.SerialNo. |
| groupId        | Long                              | false     | the id of terminal group                                     |
| serialNo       | String                            | true     | the serial number of terminal                                |
| merchantNames  | String                            | true     | the name of merchants                                        |

**Sample codes**

```
TerminalGroupApi terminalGroupApi = new TerminalGroupApi("https://api.whatspos.com/p-market-api", "RCA9MDH6YN3WSSGPW6TJ", "TUNLDZVZECHNKZ4FW07XFCKN2W0N8ZDEA5ENKZYN");
Result<SimpleTerminalDTO> result = terminalGroupApi.searchTerminalsInGroup(1,5, TerminalApi.TerminalSearchOrderBy.SerialNo,16541L,null,"12343543,123445489");
```

**Client side validation failed sample result(JSON formatted)**

```
{
	"businessCode": -1,
	"validationErrors": ["pageNo:must be greater than or equal to 1"]
}
```

**Server side validation failed sample result(JSON formatted)**

```
{
	"businessCode": 2150,
	"message": "Terminal group not found"
}
```

**Successful sample result(JSON formatted)**

```
{
	"businessCode": 0,
	"pageInfo": {
		"pageNo": 1,
		"limit": 2,
		"totalCount": 28,
		"hasNext": true,
		"dataset":  [{
		"modelName": "A920",
		"resellerName": "GLOBAL",
		"name": "ET",
		"id": 908629,
		"tid": "QAOEF6BX",
		"serialNo": "90010001",
		"status": "A",
		"merchantName": "12343543"
	}, {
		"modelName": "A920",
		"resellerName": "GLOBAL",
		"name": "ET",
		"id": 908630,
		"tid": "1D6VP0U7",
		"serialNo": "90010002",
		"status": "A",
		"merchantName": "12343543"
	}]}
}
```

The type in dataSet is SimpleTerminalDTO. And the structure like below.

| Property Name | Type   | Description             |
| ------------- | ------ | ----------------------- |
| id            | Long   | The id of terminal      |
| name          | String | The app package name    |
| tid           | String | The app name            |
| serialNo      | String | Terminal variable key   |
| status        | String | Terminal variable value |
| merchantName  | String | Comment                 |
| modelName     | String | Source type             |
| resellerName  | Long   |                         |
| location      | Long   |                         |

**Possible validation errors**

> <font color=red>pageNo:must be greater than or equal to 1</font>   
> <font color=red>pageSize:must be greater than or equal to 1</font>   
> <font color=red>pageSize:must be less than or equal to 100</font>  

**Possible business codes**

| Business Code | Message                                     | Description |
| :------------ | :------------------------------------------ | :---------- |
| 1835          | Dynamic group cannot be queried by merchant |             |
| 2150          | Terminal group not found                    |             |



### Add terminal to group

This api allows the third party system add terminal to group.

**API**

```
 public Result<String> addTerminalToGroup(Long groupId, Set<Long> terminalIds)
```

**Input parameter(s) description**  

| Parameter Name | Type      | Nullable | Description                                    |
| :------------- | :-------- | :------- | :--------------------------------------------- |
| groupId        | Long      | false    | The id of terminal group                       |
| terminalIds    | Set\<Long\> | false    | terminal ids to be added to the terminal group |

**Sample codes**

```
TerminalGroupApi terminalGroupApi = new TerminalGroupApi("https://api.whatspos.com/p-market-api", "RCA9MDH6YN3WSSGPW6TJ", "TUNLDZVZECHNKZ4FW07XFCKN2W0N8ZDEA5ENKZYN");
        Long groupId = 1L;
        Set<Long> terminalIds = new HashSet<>();
        terminalIds.add(908654L);
        terminalIds.add(908655L);
        terminalIds.add(908656L);
        Result<String> result = terminalGroupApi.addTerminalToGroup(groupId, terminalIds);
```

**Client side validation failed sample result(JSON formatted)**

```
{
	"businessCode": -1,
	"validationErrors": ["Terminal Group Id  cannot be null and cannot be less than 1!"]
}
```

**Server side validation failed sample result(JSON formatted)**

```
{
	"businessCode": 2150,
	"message": "Terminal group not found"
}
```

**Successful sample result(JSON formatted)**

```
{
	"businessCode": 0
}
```

**Possible business codes**

| Business Code | Message                                                      | Description |
| :------------ | :----------------------------------------------------------- | :---------- |
| 1800          | Terminal not found                                           |             |
| 1810          | Terminal is not active                                       |             |
| 2150          | Terminal group not found                                     |             |
| 2163          | Terminal reseller mismatched                                 |             |
| 2164          | Terminal model mismatched                                    |             |
| 2167          | Terminal group exceeded the max terminal count limit, please create new terminal group to put the terminal |             |
| 2184          | Terminal Ids is mandatory                                    |             |
| 2173          | Dynamic group can not add terminals                          |             |

### Remove terminal out group

This api allows the third party system remove terminal out group.

**API**

```
public Result<String> removeTerminalOutGroup(Long groupId, Set<Long> terminalIds)
```

**Input parameter(s) description**  

| Parameter Name | Type      | Nullable | Description                                    |
| :------------- | :-------- | :------- | :--------------------------------------------- |
| groupId        | Long      | false    | The id of terminal group                       |
| terminalIds    | Set\<Long\> | false    | terminal ids to be added to the terminal group |

**Sample codes**

```
TerminalGroupApi terminalGroupApi = new TerminalGroupApi("https://api.whatspos.com/p-market-api", "RCA9MDH6YN3WSSGPW6TJ", "TUNLDZVZECHNKZ4FW07XFCKN2W0N8ZDEA5ENKZYN");
        Long groupId = 1L;
        Set<Long> terminalIds = new HashSet<>();
        terminalIds.add(908654L);
        terminalIds.add(908655L);
        terminalIds.add(908656L);
        Result<String> result = terminalGroupApi.removeTerminalOutGroup(groupId, terminalIds);
```

**Client side validation failed sample result(JSON formatted)**

```
{
	"businessCode": -1,
	"validationErrors": ["Terminal Group Id  cannot be null and cannot be less than 1!"]
}
```

**Server side validation failed sample result(JSON formatted)**

```
{
	"businessCode": 2150,
	"message": "Terminal group not found"
}
```

**Successful sample result(JSON formatted)**

```
{
	"businessCode": 0
}
```

**Possible business codes**

| Business Code | Message                                | Description |
| :------------ | :------------------------------------- | :---------- |
| 2150          | Terminal group not found               | 1           |
| 2184          | Terminal Ids is mandatory              | 1           |
| 2174          | Dynamic group can not delete terminals | 1           |