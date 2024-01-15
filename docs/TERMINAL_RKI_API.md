## TerminalRki API

All the push RKI to terminal related APIs are encapsulated in the class *
com.pax.market.api.sdk.java.api.terminalRki.TerminalRkiApi*.

**Introduction**

With the growing maturity of the PAX RKI solution. PAX already provides customers with remote key injection services.
Through the remote key update, the POS operation and maintenance company replaces the terminal with the LKI for the
local key update, reducing its operation and maintenance costs. The Global administrator can manage the RKI server in
the global center, And assign one instance to the marketplace. Reseller admin configures RKI user token and gets key
template to identify by request RKI server. Push RKI task to terminal. You can get error detail from the UI page If the
bind failed. When an error occurred during the download phase. Please go to the terminal detail page download the logcat
file.

**Constructors of TerminalRkiApi**

```
public TerminalRkiApi(String baseUrl, String apiKey, String apiSecret);
```

**Constructor parameters description**

| Name | Type | Description |
| :--- | :--- | :--- |
| baseUrl | String | the base url of REST API |
|apiKey|String|the apiKey of marketplace, get this key from PAXSTORE admin console, refer to chapter Apply access rights|
|apiSecret|String|apiSecret, get api secret from PAXSTORE admin console, refer to chapter Apply access rights|

### Push Rki

Push Rki API allow the third party system push a Rki to terminal.

**API**

```
public Result<String> pushRki2Terminal(PushRki2TerminalRequest pushRki2TerminalRequest)
```

**Input parameter(s) description**

|Parameter Name|Type|Nullable|Description|
|:---|:---|:---|:---|
|pushRki2TerminalRequest|PushRki2TerminalRequest|false|The push Rki request object. The structure shows below.|

Structure of class PushRki2TerminalRequest

| Property Name | Type   | Nullable | Description                                                                  |
|:--------------|:-------|:---------|:-----------------------------------------------------------------------------|
| tid           | String | true     | The tid of terminal                                                          |
| serialNo      | String | true     | The serial number of terminal                                                |
| rkiKey        | String | false    | The rki key which indicate you want to push RKI Key template to the terminal |
| effectiveTime | Date   | true     | The time when to start the push task                                         |
| expiredTime   | Date   | true     | The time when to stop the push task                                          |

Note: tid and serialNo cannot be empty at same time.

**Sample codes**

```
TerminalRkiApi terminalRkiApi = new TerminalRkiApi("https://api.whatspos.com/p-market-api", "RCA9MDH6YN3WSSGPW6TJ", "TUNLDZVZECHNKZ4FW07XFCKN2W0N8ZDEA5ENKZYN");
PushRki2TerminalRequest pushRki2TerminalRequest = new PushRki2TerminalRequest();
pushRki2TerminalRequest.setTid("ABC09098989");
pushRki2TerminalRequest.setRkiKey("PIN_TEST");
terminalRkiApi.pushRki2Terminal(pushRki2TerminalRequest);
```

**Client side validation failed sample result(JSON formatted)**

```
{
	"businessCode": -1,
	"validationErrors": ["The property serialNo and tid in pushRki2TerminalRequest cannot be blank at same time!"]
}
```

**Server side validation failed sample result(JSON formatted)**

```
{
	"businessCode": 2028,
	"message": "Terminal not found"
}
```

**Successful sample result(JSON formatted)**

```
{
	"businessCode": 0,
	"data": {
        "id": 51741,	
    }
}
```

**Possible validation errors**

> <font color=red>Parameter pushRki2TerminalRequest cannot be null!</font>  
> <font color=red>The property serialNo and tid in pushRki2TerminalRequest cannot be blank at same time!</font>
> <font color=red>rkiKey:may not be empty</font>


**Possible business codes**

|Business Code|Message|Description|
|:---|:---|:---|
|2028|Terminal not found|Please check the value of tid or serialNo|
|2056|The rkiKey cannot be empty|&nbsp;|
|2026|Tid and serialNo cannot empty at same time|&nbsp;|
|9293|Reseller RKI user token is not ready|&nbsp;|
|2053|Pending push RKI task for this terminal already exists|&nbsp;|
|2054|Active push RKI task for this terminal already exists|&nbsp;|

### Search Rki push history

The search Rki push history API allows third party system to search pushed rki task list to the specified terminal by page.

<font color=red>tips：</font> Compatible with older versions, returns Status : A(Active) when the status parameter is C(Completed)  

**API**

```
public Result<PushRkiTaskDTO> searchPushRkiTasks(int pageNo, int pageSize, SearchOrderBy orderBy,
                                                                      String terminalTid, String rkiKey, PushStatus status)
```

**Input parameter(s) description**

| Name| Type | Nullable|Description |
|:--- | :---|:---|:---|
|pageNo|int|false|page number, value must >=1|
|pageSize|int|false|the record number per page, range is 1 to 100|
|orderBy|SearchOrderBy|true|the sort order by field name, if this parameter is null the search result will order by created date descend. The value of this parameter can be one of SearchOrderBy.CreatedDate_desc and SearchOrderBy.CreatedDate_asc.|
|terminalTid|String|false|search filter by terminal tid|
|rkiKey|String|true|search filter by rki key|
|status|PushStatus|true|the push status<br/> the value can be PushStatus.Active, PushStatus.Suspend, PushStatus.Completed|

**Sample codes**

```
TerminalRkiApi terminalRkiApi = new TerminalRkiApi("https://api.whatspos.com/p-market-api", "RCA9MDH6YN3WSSGPW6TJ", "TUNLDZVZECHNKZ4FW07XFCKN2W0N8ZDEA5ENKZYN");
Result<PushRkiTaskDTO> result = terminalRkiApi.searchPushRkiTasks(1,12,SearchOrderBy.CreatedDate_desc,
                                terminalTid, rkiKey, PushStatus.Active);
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
			"id": 17850,
            "rkiKey": "PIN_TEST",
            "terminalSN": "87879696",
            "status": "A",
            "actionStatus": 2,
            "errorCode": 0
		}]
	}
}
```

The type in dataSet is PushRkiTaskDTO. And the structure like below.

|Name|Type|Description|
|:---|:---|:---|
|id|Long|the id of push Rki record|
|rkiKey|String|the key of RKI|
|terminalSN|String|the serialNo of terminal|
|status|String|the status of push Rki, value can be one of A(Active) and S(Suspend)|
|actionStatus|String|the action status, please refer to [Action Status](APPENDIX.md#user-content-action-status)|
|activatedDate|Date|the push Rki activated date|
|remarks|String|the push Rki result remarks|
|errorCode|int|the error code, please refer to [Action Error Codes](APPENDIX.md#user-content-action-error-codes)|

**Possible client validation errors**

> <font color=red>pageNo:must be greater than or equal to 1</font>   
> <font color=red>pageSize:must be greater than or equal to 1</font>   
> <font color=red>pageSize:must be less than or equal to 100</font>

### Get push Rki history by id

Get terminal push Rki history by id.

**API**

```
public Result<PushRkiTaskDTO> getPushRkiTask(Long pushRkiTaskId)
```

**Input parameter(s) description**

|Parameter Name|Type|Nullable|Description|
|:---|:---|:---|:---|
|pushRkiTaskId|Long|false|the id of push Rki|

**Sample codes**

```
TerminalRkiApi terminalRkiApi = new TerminalRkiApi("https://api.whatspos.com/p-market-api", "RCA9MDH6YN3WSSGPW6TJ", "TUNLDZVZECHNKZ4FW07XFCKN2W0N8ZDEA5ENKZYN");
Result<PushRkiTaskDTO> result = terminalRkiApi.getPushRkiTask(17850L);
```

**Client side validation failed sample result(JSON formatted)**

```
{
	"businessCode": -1,
	"validationErrors": ["Parameter pushRkiTaskId cannot be null and cannot be less than 1!"]
}
```

**Server side validation failed sample result(JSON formatted)**

```
{
	"businessCode": 2051,
	"message": "Terminal Rki task not found"
}
```

**Successful sample result(JSON formatted)**

```
{
	"businessCode": 0,
	"data": {
		"id": 17850,
        "terminalSN": "87879696",
        "status": "A",
        "actionStatus": 2,
        "errorCode": 0
	}
}
```

<br>
The type of data is PushRkiTaskDTO, and the structure shows below.

|Name|Type|Description|
|:---|:---|:---|
|id|Long|the id of push Rki record|
|rkiKey|String|the key of RKI|
|terminalSN|String|the serialNo of terminal|
|status|String|the status of terminal apk, value can be one of A(Active) and S(Suspend)|
|actionStatus|String|the action status, please refer to [Action Status](APPENDIX.md#user-content-action-status)|
|errorCode|int|the error code, please refer to [Action Error Codes](APPENDIX.md#user-content-action-error-codes)|
|activatedDate|Date|the push Rki activated date|
|remarks|String|the push Rki result remarks|

**Possible client validation errors**


> <font color="red">Parameter pushRkiTaskId cannot be null and cannot be less than 1!</font>


**Possible business codes**

|Business Code|Message|Description|
|:---|:---|:---|
|2051|Terminal Rki task not found|&nbsp;|

**Possible action status**

|action status|status|Description|
|:---|:---|:---|
|0|None|The push task no start|
|1|Pending|The push task staring|
|2|Succeed|The push task is succeed|
|3|Failed|The push task is failed|
|4|Watting|The push task is watting, no need push|

**Possible error codes**

|Error Code|Description|
|:---|:---|
|1|Download error|
|2|Install error|
|12|The push is disabled|
|27|Unable To Bind Terminal RKI Key|

### Disable Rki push by serial number(TID) and Rki name

This api allows the third Party system disable an exist push by specifying the serial number of terminal and the Rki
name.

**API**

```
public Result<String> disablePushRkiTask(DisablePushRkiTask disablePushRkiTask)
```

**Input parameter(s) description**

|Parameter Name|Type|Nullable|Description|
|:---|:---|:---|:---|
|disablePushRkiTask|DisablePushRkiTask|false|The disable request object. The structure shows below.|

Structure of class DisablePushRkiTask

|Property Name|Type|Nullable|Description|
|:---|:---|:---|:---|
|tid|String|true|The tid of terminal|
|serialNo|String|true|The serial number of terminal|
|rkiKey|String|false|The rki key which indicate you want to suspend the terminal push rki task|

Note: tid and serialNo cannot be empty at same time.

**Sample codes**

```
TerminalRkiApi terminalRkiApi = new TerminalRkiApi("https://api.whatspos.com/p-market-api", "RCA9MDH6YN3WSSGPW6TJ", "TUNLDZVZECHNKZ4FW07XFCKN2W0N8ZDEA5ENKZYN");
DisablePushRkiTask disablePushRkiTask = new DisablePushRkiTask();
disablePushRkiTask.setTid("ABC09098989");
disablePushRkiTask.setRkiKey("PIN_TEST");
terminalRkiApi.disablePushRkiTask(disablePushRkiTask);
```

**Client side validation failed sample result(JSON formatted)**

```
{
	"businessCode": -1,
	"validationErrors": ["The property serialNo and tid in disablePushRkiTask cannot be blank at same time!"]
}
```

**Server side validation failed sample result(JSON formatted)**

```
{
	"businessCode": 2057,
	"message": "Unfinished terminal push Rki not found"
}
```

**Successful sample result(JSON formatted)**

```
{
	"businessCode": 0
}
```

**Possible validation errors**

> <font color=red>Parameter suspendTerminalApkRequest cannot be null!</font>  
> <font color=red>The property serialNo and tid in suspendTerminalApkRequest cannot be blank at same time!</font>
> <font color=red>fmName:may not be empty</font>


**Possible business codes**

|Business Code|Message|Description|
|:---|:---|:---|
|2028|Terminal not found|Please check the value of tid or serialNo|
|2026|Tid and serialNo cannot empty at same time||
|2056|The rkiKey cannot be empty||
|2057|Unfinished terminal push Rki not found|
