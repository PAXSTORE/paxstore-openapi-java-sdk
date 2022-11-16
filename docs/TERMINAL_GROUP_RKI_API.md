## TerminalGroupRki API

All the push RKI to terminal related APIs are encapsulated in the class *
com.pax.market.api.sdk.java.api.terminalGroupRki.TerminalGroupRkiApi*.

**Introduction**

With the growing maturity of the PAX RKI solution. PAX already provides customers with remote key injection services.
Through the remote key update, the POS operation and maintenance company replaces the terminal with the LKI for the
local key update, reducing its operation and maintenance costs. The Global administrator can manage the RKI server in
the global center, And assign one instance to the marketplace. Reseller admin configures RKI user token and gets key
template to identify by request RKI server. Batch push RKI task to terminal group. You can get error detail from the UI
page If the bind failed. When an error occurred during the download phase. Please go to the terminal detail page
download the logcat file.

**Constructors of TerminalGroupRkiApi**

```
public TerminalGroupRkiApi(String baseUrl, String apiKey, String apiSecret);
```

**Constructor parameters description**

| Name | Type | Description |
| :--- | :--- | :--- |
| baseUrl | String | the base url of REST API |
|apiKey|String|the apiKey of marketplace, get this key from PAXSTORE admin console, refer to chapter Apply access rights|
|apiSecret|String|apiSecret, get api secret from PAXSTORE admin console, refer to chapter Apply access rights|

### Search Group Push Rki Task

The search group push rki task API allows third party system to search pushed rki task list to the specified group by
page.

**API**

```
public Result<TerminalGroupRkiDTO> searchGroupPushRkiTask(int pageNo, int pageSize, SearchOrderBy orderBy , Long groupId, Boolean pendingOnly, Boolean historyOnly, String keyWords)
```

**Input parameter(s) description**

| Name        | Type          | Nullable | Description                                                                                                                                                                                                               |
|:------------|:--------------|:---------|:--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| pageNo      | int           | false    | page number, value must >=1                                                                                                                                                                                               |
| pageSize    | int           | false    | the record number per page, range is 1 to 100                                                                                                                                                                             |
| orderBy     | SearchOrderBy | true     | the sort order by field name, if this parameter is null the search result will order by created date descend. The value of this parameter can be one of SearchOrderBy.CreatedDate_desc and SearchOrderBy.CreatedDate_asc. |
| groupId     | Long          | false    | the id of the group                                                                                                                                                                                                       |
| pendingOnly | Boolean       | true     | Indicate whether to search the pending push task only                                                                                                                                                                     |
| historyOnly | Boolean       | true     | Indicate whether to search history push task only                                                                                                                                                                         |
| keyWords    | String        | true     | Key words， it will match rkiKey                                                                                                                                                                                           |

**Sample codes**

```
TerminalGroupRkiApi terminalGroupRkiApi = new TerminalGroupRkiApi("https://api.whatspos.com/p-market-api", "RCA9MDH6YN3WSSGPW6TJ", "TUNLDZVZECHNKZ4FW07XFCKN2W0N8ZDEA5ENKZYN");
Result<TerminalGroupRkiDTO> result = terminalGroupRkiApi.searchGroupPushRkiTask(
1,12,SearchOrderBy.CreatedDate_desc,null, true, null);
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
		"dataSet": [{
				"id": 5,
                "rkiKey": "TEST_KEY1",
                "activatedDate": 1632475214000,
                "effectiveTime": 1632475200000,
                "status": "S",
                "actionStatus": 1,
                "errorCode": 0,
                "pendingCount": 0,
                "successCount": 0,
                "failedCount": 1,
                "pushLimit": 0
		},{
                "id": 4,
                "rkiKey": "TEST_KEY2",
                "activatedDate": 1632475179000,
                "effectiveTime": 1632475140000,
                "status": "S",
                "actionStatus": 1,
                "errorCode": 0,
                "pendingCount": 0,
                "successCount": 0,
                "failedCount": 1,
                "pushLimit": 0
		}]
	}
}
```

The type in dataSet is TerminalGroupRkiDTO. And the structure like below.

| Name          | Type      | Description                                                                                       |
|:--------------|:----------|:--------------------------------------------------------------------------------------------------|
| id            | Long      | the id of group push Rki record                                                                   |
| rkiKey        | String    | the key of RKI                                                                                    |
| status        | String    | the status of push Rki, value can be one of A(Active) and S(Suspend)                              |
| actionStatus  | String    | the action status, please refer to [Action Status](APPENDIX.md#user-content-action-status)        |
| activatedDate | Timestamp | the push Rki activated date                                                                       |
| effectiveTime | Timestamp | the push Rki effective date                                                                       |
| remarks       | String    | the push Rki result remarks                                                                       |
| errorCode     | int       | the error code, please refer to [Action Error Codes](APPENDIX.md#user-content-action-error-codes) |
| pendingCount  | int       |                                                                                                   |
| successCount  | int       |                                                                                                   |
| failedCount   | int       |                                                                                                   |
| completed     | Boolean   | push complete                                                                                     |
| pushLimit     | int       | the push limit count                                                                              |

**Possible client validation errors**

> <font color=red>pageNo:must be greater than or equal to 1</font>   
> <font color=red>pageSize:must be greater than or equal to 1</font>   
> <font color=red>pageSize:must be less than or equal to 100</font>

### Get group push rki task by id

Get group push rki task by id.

**API**

```
public Result<TerminalGroupRkiDTO> getGroupPushRkiTask(Long groupPushRkiTaskId)
```

**Input parameter(s) description**

| Parameter Name     | Type | Nullable | Description                   |
| :----------------- | :--- | :------- | :---------------------------- |
| groupPushRkiTaskId | Long | false    | the id of group push rki task |

**Sample codes**

```
TerminalGroupRkiApi terminalGroupRkiApi = new TerminalGroupRkiApi("https://api.whatspos.com/p-market-api", "RCA9MDH6YN3WSSGPW6TJ", "TUNLDZVZECHNKZ4FW07XFCKN2W0N8ZDEA5ENKZYN");
Result<TerminalGroupRkiDTO> result = terminalGroupRkiApi.getGroupPushRkiTask(6L);
```

**Client side validation failed sample result(JSON formatted)**

```
{
	"businessCode": -1,
	"validationErrors": ["Parameter groupPushRkiTaskId cannot be null and cannot be less than 1!"]
}
```

**Server side validation failed sample result(JSON formatted)**

```
{
	"businessCode": 18002,
	"message": "RKI push task not found"
}
```

**Successful sample result(JSON formatted)**

```
{
	"businessCode": 0,
	"data": {
            "id": 6,
            "rkiKey": "TEST_8",
            "activatedDate": 1632648236000,
            "effectiveTime": 1632648180000,
            "status": "A",
            "actionStatus": 1,
            "errorCode": 0,
            "pendingCount": 1,
            "successCount": 0,
            "failedCount": 0,
            "completed": false,
            "pushLimit": 0
		}
}
```

<br>

The type of data is TerminalGroupRkiDTO, and Refer to search group push rki task Api for structure .

**Possible client validation errors**

> <font color="red">Parameter pushRkiTaskId cannot be null and cannot be less than 1!</font>

**Possible business codes**

| Business Code | Message                     | Description |
| :------------ | :-------------------------- | :---------- |
| 2051          | Terminal Rki task not found | &nbsp;      |

**Possible action status**

| action status | status  | Description                            |
| :------------ | :------ | :------------------------------------- |
| 0             | None    | The push task no start                 |
| 1             | Pending | The push task staring                  |
| 2             | Succeed | The push task is succeed               |
| 3             | Failed  | The push task is failed                |
| 4             | Watting | The push task is watting, no need push |

**Possible error codes**

| Error Code | Description                     |
| :--------- | :------------------------------ |
| 1          | Download error                  |
| 2          | Install error                   |
| 12         | The push is disabled            |
| 27         | Unable To Bind Terminal RKI Key |

### Push rki to group

Push rki to group API allow the third party system push a Rki to group.

**API**

```
public Result<TerminalGroupRkiDTO> pushRkiKey2Group(CreateTerminalGroupRkiRequest createRequest)
```

**Input parameter(s) description**

|Parameter Name|Type|Nullable|Description|
|:---|:---|:---|:---|
|createRequest|CreateTerminalGroupRkiRequest|false|The create request object. The structure shows below.|

Structure of class CreateTerminalGroupRkiRequest

| Property Name | Type   | Nullable | Description                                                                  |
|:--------------|:-------|:---------|:-----------------------------------------------------------------------------|
| groupId       | String | false    | The id of group                                                              |
| rkiKey        | String | false    | The rki key which indicate you want to push RKI Key template to the terminal |
| effectiveTime | Date   | true     | The time when to start the push task                                         |
| expiredTime   | Date   | true     | The time when to stop the push task                                          |

**Sample codes**

```
TerminalGroupRkiApi terminalGroupRkiApi = new TerminalGroupRkiApi("https://api.whatspos.com/p-market-api", "RCA9MDH6YN3WSSGPW6TJ", "TUNLDZVZECHNKZ4FW07XFCKN2W0N8ZDEA5ENKZYN");
CreateTerminalGroupRkiRequest createRequest = new CreateTerminalGroupRkiRequest();
createRequest.setGroupId(16601L);
createRequest.setRkiKey("TEST_8");
Result<TerminalGroupRkiDTO> result = terminalGroupRkiApi.pushRkiKey2Group(createRequest);
```

**Client side validation failed sample result(JSON formatted)**

```
{
	"businessCode": -1,
	"validationErrors": ["Parameter groupPushRkiRequest cannot be null!"]
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
            "id": 6,
            "rkiKey": "TEST_8",
            "activatedDate": 1632648236000,
            "effectiveTime": 1632648180000,
            "status": "A",
            "actionStatus": 1,
            "errorCode": 0,
            "pendingCount": 1,
            "successCount": 0,
            "failedCount": 0,
            "completed": false,
            "pushLimit": 0
	}
}
```

**Possible validation errors**

> <font color=red>Parameter groupPushRkiRequest cannot be null!</font>  
> <font color=red>Terminal Group Id cannot be null and cannot be less than 1！</font>

<br>

The type of data is TerminalGroupRkiDTO, and Refer to search group push rki task Api for structure .

**Possible business codes**

| Business Code | Message                                                     | Description |
|:--------------|:------------------------------------------------------------|:------------|
| 131           | Insufficient access right                                   ||
| 2150          | Terminal group not found                                    | &nbsp;      |
| 2060          | Failed to activate push task: PAX Auth System Connect Error ||
| 9293          | Reseller RKI user token is not ready                        ||
| 18002         | RKI push task not found                                     ||
| 18004         | Pending RKI push task already exist                         | &nbsp;      |
| 18005         | Active RKI push task already exist                          | &nbsp;      |
| 18006         | Invalid RKI push task status                                ||

### Disable Rki push by group push rki task id

This api allows the third Party system disable an exist push by group push rki task id.

**API**

```
public Result<TerminalGroupRkiDTO> disableGroupRkiPushTask(Long groupPushRkiTaskId)
```

**Input parameter(s) description**

|Parameter Name|Type|Nullable|Description|
|:---|:---|:---|:---|
|groupPushRkiTaskId|Long|false|the id of group push rki task|

**Sample codes**

```
TerminalGroupRkiApi terminalGroupRkiApi = new TerminalGroupRkiApi("https://api.whatspos.com/p-market-api", "RCA9MDH6YN3WSSGPW6TJ", "TUNLDZVZECHNKZ4FW07XFCKN2W0N8ZDEA5ENKZYN");
Result<TerminalGroupRkiDTO> result = terminalGroupRkiApi.disableGroupRkiPushTask(6L);
```

**Client side validation failed sample result(JSON formatted)**

```
{
	"businessCode": -1,
	"validationErrors": ["Parameter groupPushRkiTaskId cannot be null and cannot be less than 1!"]
}
```

**Server side validation failed sample result(JSON formatted)**

```
{
	"businessCode": 18002,
	"message": "RKI push task not found"
}
```

**Successful sample result(JSON formatted)**

```
{
	"businessCode": 0,
	"data": {
		"id": 6,
		"rkiKey": "TEST_8",
		"activatedDate": 1632648236000,
		"effectiveTime": 1632648180000,
		"status": "S",
		"actionStatus": 1,
		"errorCode": 0,
		"pendingCount": 0,
		"successCount": 0,
		"failedCount": 0,
		"pushLimit": 0
	}
}
```

**Possible validation errors**

> <font color=red>Parameter groupPushRkiTaskId cannot be null and cannot be less than 1!</font>


**Possible business codes**

|Business Code|Message|Description|
|:---|:---|:---|
|18002|RKI push task not found||
|18003|RKI push task not active||
