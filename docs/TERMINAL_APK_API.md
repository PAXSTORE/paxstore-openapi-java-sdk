## TerminalApk API

All the push apk to terminal related APIs are encapsulated in the class *com.pax.market.api.sdk.java.api.terminalApk.TerminalApkApi*.

**Constructors of TerminalApkApi**

```
public TerminalApkApi(String baseUrl, String apiKey, String apiSecret);
```

**Constructor parameters description**

|Name|Type|Description|
|:---|:---|:---|
|baseUrl|String|the base url of REST API|
|apiKey|String|the apiKey of marketplace, get this key from PAXSTORE admin console, refer to chapter Apply access rights|
|apiSecret|String|apiSecret, get api secret from PAXSTORE admin console, refer to chapter Apply access rights|


### Push apk

Push apk API allow the third party system push a apk to terminal. And max request data size is 5MB.


**API**

```
public Result<String> createTerminalApk(CreateTerminalApkRequest createTerminalApkRequest)
```

**Input parameter(s) description**  


|Parameter Name|Type|Nullable|Description|
|:---|:---|:---|:---|
|createTerminalApkRequest|CreateTerminalApkRequest|false|The create request object. The structure shows below.|


Structure of class TerminalCreateRequest

|Property Name|Type|Nullable|Description|
|:---|:---|:---|:---|
|tid|String|true|The tid of terminal|
|serialNo|String|true|The serial number of terminal|
|packageName|String|false|The package name which indicate the application you want to push to the terminal|
|version|String|true|The version name of application which you want to push, if it is blank API will use the latest version|
|templateName|String|true|The template file name of paramter application. The template file name can be found in the detail of the parameter application. If user want to push more than one template the please use &#124; to concact the different template file names like tempate1.xml&#124;template2.xml&#124;template3.xml, the max size of template file names is 10.|
|parameters|Map\<String, String\>|false|The parameter key and value, the key the PID in template|
|base64FileParameters|List\<FileParameter\>|false|The parameter of file type, the max counter of file type parameter is 10, and the max size of each parameter file is 500kb|
|pushTemplateName|String|true|The push template name|
|inheritPushHistory|Boolean|true|Whether to inherit the latest success push history parameters, inherited parameter values have lower priority than passed-in parameter values|

Structure of class FileParameter

|Property Name|Type|Nullable|Description|
|:---|:---|:---|:---|
|pid|String|true|The PID in template|
|fileName|String|true|The parameter of file type, file name containing suffix|
|fileData|String|true|The parameter of file type, file base64 data|

Note: tid and serialNo cannot be empty at same time.


**Sample codes**

```
TerminalApkApi terminalApkApi = new TerminalApkApi("https://api.whatspos.com/p-market-api", "RCA9MDH6YN3WSSGPW6TJ", "TUNLDZVZECHNKZ4FW07XFCKN2W0N8ZDEA5ENKZYN");
CreateTerminalApkRequest createTerminalApkRequest = new CreateTerminalApkRequest();
createTerminalApkRequest.setTid("ABC09098989");
createTerminalApkRequest.setPackageName("com.baidu.map");
createTerminalApkRequest.setTemplateName("template_map");
Map<String, String> parameters = new HashMap<String, String>();
parameters.put("PID.locationCode", "cn_js_sz");
parameters.put("PID.showtraffic", "true");
FileParameter fileParameter = new FileParameter();
fileParameter.setPid("PID.cardBinFile");
fileParameter.setFileName("cardBinFile.jpeg");
fileParameter.setFileData("data:image/jpeg;base64,/9j/4AAQSkZJR==");
List<FileParameter> base64FileParameters = new ArrayList<>();
base64FileParameters.add(fileParameter);
createTerminalApkRequest.setBase64FileParameters(base64FileParameters);
createTerminalApkRequest.setParameters(parameters);
terminalApkApi.createTerminalApk(createTerminalApkRequest);
```

**Client side validation failed sample result(JSON formatted)**

```
{
	"businessCode": -1,
	"validationErrors": ["The property serialNo and tid in createTerminalApkRequest cannot be blank at same time!"]
}
```

**Server side validation failed sample result(JSON formatted)**

```
{
	"businessCode": 2028,
	"message": "TerminalApk not found"
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

> <font color=red>Parameter createTerminalApkRequest cannot be null!</font>  
> <font color=red>The property parameters of createTerminalApkRequest cannot be empty!</font>  
> <font color=red>The property serialNo and tid in createTerminalApkRequest cannot be blank at same time!</font>  
> <font color=red>packageName:may not be empty</font>  
> <font color="red">The max size of template names is 10!</font>  
> <font color=red>Exceed max counter (10) of file type parameters!</font>  
> <font color=red>Exceed max size (500kb) per file type parameters!</font>


**Possible business codes**

|Business Code|Message|Description|
|:---|:---|:---|
|2028|Terminal not found|Please check the value of tid or serialNo|
|2029|Apk not found|Cannot find apk by packagename and version|
|2030|Parameter template not found|The given template name(s) not exist in system|
|2039|Tid mismatch with serialNo|Please check the value of tid and serialNo|
|13100|Invalid application parameter variables||
|2026|Tid and serialNo cannot empty at same time||
|2027|Package name cannot be empty||
|2001|Terminal app not found||
|2000|Terminal app status is invalid||
|9306|App is not available||
|2022|Same version of pending terminal app already exists||
|2023|Same version of active terminal app already exists||
|1905|Terminal task app parameter is invalid||
|13100|Invalid application parameter variables||
|1111|Selected parameter templates exceeded the max limit||
|2031|Template name cannot be empty|&nbsp;|


### Search apk push history

The search apk push history API allows third party system to search pushed apks to the specified terminal by page.
**API**

```
public Result<TerminalApkDTO> searchTerminalApk(int pageNo, int pageSize, SearchOrderBy orderBy,
                                                    String terminalTid, String appPackageName, PushStatus status)
```

**Input parameter(s) description**

| Name| Type | Nullable|Description |
|:--- | :---|:---|:---|
|pageNo|int|false|page number, value must >=1|
|pageSize|int|false|the record number per page, range is 1 to 100|
|orderBy|SearchOrderBy|true|the sort order by field name, if this parameter is null the search result will order by created date descend. The value of this parameter can be one of SearchOrderBy.CreatedDate_desc and SearchOrderBy.CreatedDate_asc.|
|terminalTid|String|false|search filter by terminal tid|
|appPackageName|String|true|search filter by app packageName|
|status|PushStatus|true|the push status<br/> the value can be PushStatus.Active, PushStatus.Suspend|

**Sample codes**

```
TerminalApkApi terminalApkApi = new TerminalApkApi("https://api.whatspos.com/p-market-api", "RCA9MDH6YN3WSSGPW6TJ", "TUNLDZVZECHNKZ4FW07XFCKN2W0N8ZDEA5ENKZYN");
Result<TerminalApkDTO> result = terminalApkApi.searchTerminalApk(1,12,SearchOrderBy.CreatedDate_desc,
                                terminalTid, testPackageName, PushStatus.Active);
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
            "apkPackageName": "com.pax.demo",
            "apkVersionName": "7.5.0",
            "apkVersionCode": "75",
            "terminalSN": "87879696",
            "status": "A",
            "actionStatus": 2
		}]
	}
}
```

The type in dataSet is TerminalApkDTO. And the structure like below.

|Name|Type|Description|
|:---|:---|:---|
|id|Long|the id of terminal apk|
|apkPackageName|String|the packageName of apk|
|apkVersionName|String|the version name of apk|
|apkVersionCode|Long|the version code of apk|
|terminalSN|String|the serialNo of terminal|
|status|String|the status of terminal apk, value can be one of A(Active) and S(Suspend)|
|actionStatus|String|the action status, please refer to [Action Status](APPENDIX.md#user-content-action-status)|

**Possible client validation errors**  

> <font color=red>pageNo:must be greater than or equal to 1</font>   
> <font color=red>pageSize:must be greater than or equal to 1</font>   
> <font color=red>pageSize:must be less than or equal to 100</font>  

### Get push apk history by id

Get terminal push apk history by id.


**API**

```
public Result<TerminalApkDTO> getTerminalApk(Long terminalApkId);
public Result<TerminalApkDTO> getTerminalApk(Long terminalApkId, List<String> pidList);
```

**Input parameter(s) description**

|Parameter Name|Type|Nullable|Description|
|:---|:---|:---|:---|
|terminalApkId|Long|false|the id of terminalApk|
|pidList|List|true|the pid of the configured parameters to return|

**Sample codes**

```
TerminalApkApi terminalApkApi = new TerminalApkApi("https://api.whatspos.com/p-market-api", "RCA9MDH6YN3WSSGPW6TJ", "TUNLDZVZECHNKZ4FW07XFCKN2W0N8ZDEA5ENKZYN");
Result<TerminalApkDTO> result = terminalApkApi.getTerminalApk(17850L);

List<String> pidList = new ArrayList<>();
pidList.add("sys.cap.test01");
pidList.add("sys.cap.test02");
pidList.add("sys.cap.test03");
Result<TerminalApkDTO> result = terminalApkApi.getTerminalApk(17850L, pidList);
```

**Client side validation failed sample result(JSON formatted)**

```
{
	"businessCode": -1,
	"validationErrors": ["Parameter terminalApkId cannot be null and cannot be less than 1!"]
}
```


**Server side validation failed sample result(JSON formatted)**

```
{
	"businessCode": 2001,
	"message": "Terminal app not found"
}
```

**Successful sample result(JSON formatted)**

```
{
	"businessCode": 0,
	"data": {
		"id": 17850,
		"apkPackageName": "com.pax.demo",
		"apkVersionName": "7.5.0",
		"apkVersionCode": "75",
		"terminalSN": "87879696",
		"status": "A",
		"actionStatus": 2,
		"errorCode": 0
	}
}
```

<br>
The type of data is TerminalApkDTO, and the structure shows below.

|Name|Type|Description|
|:---|:---|:---|
|id|Long|the id of terminal apk|
|apkPackageName|String|the packageName of apk|
|apkVersionName|String|the version name of apk|
|apkVersionCode|Long|the version code of apk|
|terminalSN|String|the serialNo of terminal|
|status|String|the status of terminal apk, value can be one of A(Active) and S(Suspend)|
|actionStatus|String|the action status, please refer to [Action Status](APPENDIX.md#user-content-action-status)|
|errorCode|int|the error code, please refer to [Action Error Codes](APPENDIX.md#user-content-action-error-codes)|


**Possible client validation errors**


> <font color="red">Parameter terminalApkId cannot be null and cannot be less than 1!</font>

**Possible business codes**

|Business Code|Message|Description|
|:---|:---|:---|
|2001|Terminal app not found|&nbsp;|


### Disable app push by serial number(TID) and package name

This api allows the third party system disable an exist push by specifying the serial number of terminal and the app package name. The function of this API is same as the above one.


**API**

```
public Result<String> disableApkPush(UpdateTerminalApkRequest disableTerminalApkRequest)
```

**Input parameter(s) description**  


|Parameter Name|Type|Nullable|Description|
|:---|:---|:---|:---|
|disableTerminalApkRequest|UpdateTerminalApkRequest|false|The disable request object. The structure shows below.|


Structure of class UpdateTerminalApkRequest

|Property Name|Type|Nullable|Description|
|:---|:---|:---|:---|
|tid|String|true|The tid of terminal|
|serialNo|String|true|The serial number of terminal|
|packageName|String|false|The package name which indicate the application you want to suspend the terminal push task|

Note: tid and serialNo cannot be empty at same time.


**Sample codes**

```
TerminalApkApi terminalApkApi = new TerminalApkApi("https://api.whatspos.com/p-market-api", "RCA9MDH6YN3WSSGPW6TJ", "TUNLDZVZECHNKZ4FW07XFCKN2W0N8ZDEA5ENKZYN");
UpdateTerminalApkRequest disableTerminalApkRequest = new UpdateTerminalApkRequest();
disableTerminalApkRequest.setTid("ABC09098989");
disableTerminalApkRequest.setPackageName("com.baidu.map");
terminalApkApi.disableApkPush(disableTerminalApkRequest);
```

**Client side validation failed sample result(JSON formatted)**

```
{
	"businessCode": -1,
	"validationErrors": ["The property serialNo and tid in disableTerminalApkRequest cannot be blank at same time!"]
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
> <font color=red>packageName:may not be empty</font>


**Possible business codes**

|Business Code|Message|Description|
|:---|:---|:---|
|2028|Terminal not found|Please check the value of tid or serialNo|
|2029|Apk not found|Cannot find apk by packagename and version|
|2026|Tid and serialNo cannot empty at same time||
|2027|Package name cannot be empty||
|2038|Unfinished terminal push app not found||
|2039|Tid mismatch with serialNo|Please check the value of tid and serialNo|


### Uninstall app by serial number(TID) and package name

This api allows the third party system uninstall an app from a terminal by specifying the serial number and the package name of app. The function of this api is same as the above one


**API**

```
public Result<String> uninstallApk(UpdateTerminalApkRequest uninstallApkRequest)
```

**Input parameter(s) description**  


|Parameter Name|Type|Nullable|Description|
|:---|:---|:---|:---|
|uninstallApkRequest|UpdateTerminalApkRequest|false|The uninstall request object. The structure shows below.|


Structure of class UpdateTerminalApkRequest

|Property Name|Type|Nullable|Description|
|:---|:---|:---|:---|
|tid|String|true|The tid of terminal|
|serialNo|String|true|The serial number of terminal|
|packageName|String|false|The package name which indicate the application you want to uninstall the terminal apk|

Note: tid and serialNo cannot be empty at same time.


**Sample codes**

```
TerminalApkApi terminalApkApi = new TerminalApkApi("https://api.whatspos.com/p-market-api", "RCA9MDH6YN3WSSGPW6TJ", "TUNLDZVZECHNKZ4FW07XFCKN2W0N8ZDEA5ENKZYN");
UpdateTerminalApkRequest uninstallApkRequest = new UpdateTerminalApkRequest();
uninstallApkRequest.setTid("ABC09098989");
uninstallApkRequest.setPackageName("com.baidu.map");
terminalApkApi.uninstallApk(uninstallApkRequest);
```

**Client side validation failed sample result(JSON formatted)**

```
{
	"businessCode": -1,
	"validationErrors": ["The property serialNo and tid in uninstallTerminalApkRequest cannot be blank at same time!"]
}
```

**Server side validation failed sample result(JSON formatted)**

```
{
	"businessCode": 2037,
	"message": "This app is not installed on the terminal"
}
```

**Successful sample result(JSON formatted)**

```
{
	"businessCode": 0
}
```


**Possible validation errors**

> <font color=red>Parameter uninstallTerminalApkRequest cannot be null!</font>  
> <font color=red>The property serialNo and tid in uninstallTerminalApkRequest cannot be blank at same time!</font> 
> <font color=red>packageName:may not be empty</font>


**Possible business codes**

|Business Code|Message|Description|
|:---|:---|:---|
|2028|Terminal not found|Please check the value of tid or serialNo|
|2029|Apk not found|Cannot find apk by packagename and version|
|2026|Tid and serialNo cannot empty at same time||
|2027|Package name cannot be empty||
|2037|This app is not installed on the terminal||
|2039|Tid mismatch with serialNo|Please check the value of tid and serialNo|
