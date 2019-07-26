## TerminalFirmware API

All the push firmware to terminal related APIs are encapsulated in the class *com.pax.market.api.sdk.java.api.terminalFirmware.TerminalFirmwareApi*.

**Constructors of TerminalFirmwareApi**

```
public TerminalFirmwareApi(String baseUrl, String apiKey, String apiSecret);
public TerminalFirmwareApi(String baseUrl, String apiKey, String apiSecret, Locale locale);
```

**Constructor parameters description**

| Name | Type | Description |
| :--- | :--- | :--- |
| baseUrl | String | the base url of REST API |
|apiKey|String|the apiKey of marketplace, get this key from PAXSTORE admin console, refe to chapter Apply access rights|
|apiSecret|String|apiSecret, get api secret from PAXSTORE admin console, refer to chapter Apply access rights|
|locale|Locale|the locale, the default locale is Locale.ENGLISH, the language of message and errors in return object depend on locale|


### Push firmware

Push firmware API allow the third party system push a firmware to terminal.


**API**

```
public Result<String> pushFirmware2Terminal(PushFirmware2TerminalRequest pushFirmware2TerminalRequest)
```

**Input parameter(s) description**  


|Parameter Name|Type|Nullable|Description|
|:---|:---|:---|:---|
|pushFirmware2TerminalRequest|PushFirmware2TerminalRequest|false|The push firmware request object. The structure shows below.|


Structure of class PushFirmware2TerminalRequest

|Property Name|Type|Nullable|Description|
|:---|:---|:---|:---|
|tid|String|true|The tid of terminal|
|serialNo|String|true|The serial number of terminal|
|fmName|String|false|The fmName which indicate the firmware you want to push to the terminal|

Note: tid and serialNo cannot be empty at same time.


**Sample codes**

```
TerminalFirmwareApi terminalFirmwareApi = new TerminalFirmwareApi("https://api.whatspos.com/p-market-api", "RCA9MDH6YN3WSSGPW6TJ", "TUNLDZVZECHNKZ4FW07XFCKN2W0N8ZDEA5ENKZYN");
PushFirmware2TerminalRequest createTerminalFirmwareRequest = new PushFirmware2TerminalRequest();
pushFirmware2TerminalRequest.setTid("ABC09098989");
pushFirmware2TerminalRequest.setFmName("A920_PayDroid_4.4.2_Capricorn_V01.1.10_20171226_OTA");
terminalFirmwareApi.pushFirmware2Terminal(pushFirmware2TerminalRequest);
```

**Client side validation failed sample result(JSON formatted)**

```
{
	"businessCode": -1,
	"validationErrors": ["The property serialNo and tid in createTerminalFirmwareRequest cannot be blank at same time!"]
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
	"businessCode": 0
}
```



**Possible validation errors**

> <font color=red>Parameter createTerminalFirmwareRequest cannot be null!</font>  
> <font color=red>The property serialNo and tid in createTerminalFirmwareRequest cannot be blank at same time!</font> 
> <font color=red>fmName:may not be empty</font> 


**Possible business codes**

|Business Code|Message|Description|
|:---|:---|:---|
|2028|Terminal not found|Please check the value of tid or serialNo|
|2033|Firmware name cannot be empty|&nbsp;|
|2034|Firmware not found|Cannot find firmware by fmName|
|2035|Firmware status not online|&nbsp;|
|2036|Firmware model mismatch with terminal model|&nbsp;|
|2026|Tid and serialNo cannot empty at same time|&nbsp;|
|8112|Same version of pending terminal firmware already exists|&nbsp;|
|8113|Same version of active terminal firmware already exists|&nbsp;|


### Search firmware push history

The search firmware push history API allows third party system to search pushed firmwares to the specified terminal by page.
**API**

```
public Result<PushFirmwareTaskDTO> searchPushFirmwareTasks(int pageNo, int pageSize, SearchOrderBy orderBy,
                                                                      String terminalTid, String fmName, PushStatus status)
```

**Input parameter(s) description**

| Name| Type | Nullable|Description |
|:--- | :---|:---|:---|
|pageNo|int|false|page number, value must >=1|
|pageSize|int|false|the record number per page, range is 1 to 1000|
|orderBy|SearchOrderBy|true|the sort order by field name, if this parameter is null the search result will order by created date descend. The value of this parameter can be one of SearchOrderBy.CreatedDate_desc and SearchOrderBy.CreatedDate_asc.|
|terminalTid|String|false|search filter by terminal tid|
|fmName|String|true|search filter by firmware name|
|status|PushStatus|true|the push status<br/> the value can be PushStatus.Active, PushStatus.Suspend|

**Sample codes**

```
TerminalFirmwareApi terminalFirmwareApi = new TerminalFirmwareApi("https://api.whatspos.com/p-market-api", "RCA9MDH6YN3WSSGPW6TJ", "TUNLDZVZECHNKZ4FW07XFCKN2W0N8ZDEA5ENKZYN");
Result<PushFirmwareTaskDTO> result = terminalFirmwareApi.searchPushFirmwareTasks(1,12,SearchOrderBy.CreatedDate_desc,
                                terminalTid, fmName, PushStatus.Active);
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
            "fmName": "PayDroid_5.1.1_Aquarius_V09.0.00_20190508",
            "terminalSN": "87879696",
            "status": "A",
            "actionStatus": 2,
            "errorCode": ""
		}]
	}
}
```

The type in dataSet is PushFirmwareTaskDTO. And the structure like below.

|Name|Type|Description|
|:---|:---|:---|
|id|Long|the id of push firmware record|
|fmName|String|the name of firmware|
|terminalSN|String|the serialNo of terminal|
|status|String|the status of push firmware, value can be one of A(Active) and S(Suspend)|
|actionStatus|String|the push status|

**Possible client validation errors**  

> <font color=red>pageNo:must be greater than or equal to 1</font>   
> <font color=red>pageSize:must be greater than or equal to 1</font>   
> <font color=red>pageSize:must be less than or equal to 1000</font>  

### Get push firmware history by id

Get terminal push firmware history by id.


**API**

```
public Result<PushFirmwareTaskDTO> getPushFirmwareTask(Long pushFirmwareTaskId)
```

**Input parameter(s) description**

|Parameter Name|Type|Nullable|Description|
|:---|:---|:---|:---|
|pushFirmwareTaskId|Long|false|the id of push firmware|

**Sample codes**

```
TerminalFirmwareApi terminalFirmwareApi = new TerminalFirmwareApi("https://api.whatspos.com/p-market-api", "RCA9MDH6YN3WSSGPW6TJ", "TUNLDZVZECHNKZ4FW07XFCKN2W0N8ZDEA5ENKZYN");
Result<PushFirmwareTaskDTO> result = terminalFirmwareApi.getPushFirmwareTask(17850L);
```

**Client side validation failed sample result(JSON formatted)**

```
{
	"businessCode": -1,
	"validationErrors": ["Parameter pushFirmwareTaskId cannot be null and cannot be less than 1!"]
}
```


**Server side validation failed sample result(JSON formatted)**

```
{
	"businessCode": 8108,
	"message": "Terminal firmeware not found"
}
```

**Successful sample result(JSON formatted)**

```
{
	"businessCode": 0,
	"data": {
		"id": 17850,
        "fmName": "PayDroid_5.1.1_Aquarius_V09.0.00_20190508",
        "terminalSN": "87879696",
        "status": "A",
        "actionStatus": 2,
        "errorCode": ""
	}
}
```

<br>
The type of data is PushFirmwareTaskDTO, and the structure shows below.

|Name|Type|Description|
|:---|:---|:---|
|id|Long|the id of push firmware record|
|fmName|String|the name of firmware|
|terminalSN|String|the serialNo of terminal|
|status|String|the status of terminal apk, value can be one of A(Active) and S(Suspend)|
|actionStatus|String|the push status|
|errorCode|String|the error code of push task|


**Possible client validation errors**


> <font color="red">Parameter pushFirmwareTaskId cannot be null and cannot be less than 1!</font>


**Possible business codes**

|Business Code|Message|Description|
|:---|:---|:---|
|8101|Terminal firmware not found|&nbsp;|

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
|13|The firmware is duplicate|
|14|The firmware status invalid|
|15|The firmware model pn mismatch with terminal|
|16|The firmware version mismatch|
|17|The firmware model mismatch with terminal|
|18|The terminal no right to download this firmware|
|19|The firmware already installed|
|20|The firmware file version too low|
|22|The firmware file deleted by user|
|25|The firmware resource mismatch|


### Disable firmware push by serial number(TID) and firmware name

This api allows the third Party system disable an exist push by specifying the serial number of terminal and the firmware name. The function of this API is same as the above one.


**API**

```
public Result<String> disablePushFirmwareTask(DisablePushFirmwareTask disablePushFirmwareTask)
```

**Input parameter(s) description**  


|Parameter Name|Type|Nullable|Description|
|:---|:---|:---|:---|
|disablePushFirmwareTask|DisablePushFirmwareTask|false|The disable request object. The structure shows below.|


Structure of class DisablePushFirmwareTask

|Property Name|Type|Nullable|Description|
|:---|:---|:---|:---|
|tid|String|true|The tid of terminal|
|serialNo|String|true|The serial number of terminal|
|fmName|String|false|The fmName which indicate the firmware you want to suspend the terminal push task|

Note: tid and serialNo cannot be empty at same time.


**Sample codes**

```
TerminalFirmwareApi terminalFirmwareApi = new TerminalFirmwareApi("https://api.whatspos.com/p-market-api", "RCA9MDH6YN3WSSGPW6TJ", "TUNLDZVZECHNKZ4FW07XFCKN2W0N8ZDEA5ENKZYN");
DisablePushFirmwareTask disablePushFirmwareTask = new DisablePushFirmwareTask();
disablePushFirmwareTask.setTid("ABC09098989");
disablePushFirmwareTask.setFmName("A920_PayDroid_4.4.2_Capricorn_V01.1.10_20171226_OTA");
terminalFirmwareApi.disablePushFirmwareTask(disablePushFirmwareTask);
```

**Client side validation failed sample result(JSON formatted)**

```
{
	"businessCode": -1,
	"validationErrors": ["The property serialNo and tid in disablePushFirmwareTask cannot be blank at same time!"]
}
```

**Server side validation failed sample result(JSON formatted)**

```
{
	"businessCode": 2038,
	"message": "Unfinished terminal push app not found"
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
|2033|FmName cannot be empty||
|2034|Firmware not found||
|2040|Unfinished terminal push firmware not found|
