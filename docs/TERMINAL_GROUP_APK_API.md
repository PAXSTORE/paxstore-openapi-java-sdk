## TerminalGroupApk API

All the push terminal group apk to terminal related APIs are encapsulated in the class *
com.pax.market.api.sdk.java.api.terminalGroupApk.TerminalGroupApk*.

**Constructors of TerminalGroupApkApi**

```
public TerminalGroupApkApi(String baseUrl, String apiKey, String apiSecret);
```

**Constructor parameters description**

|Name|Type|Description|
|:---|:---|:---|
|baseUrl|String|the base url of REST API|
|apiKey|String|the apiKey of marketplace, get this key from PAXSTORE admin console, refer to chapter Apply access rights|
|apiSecret|String|apiSecret, get api secret from PAXSTORE admin console, refer to chapter Apply access rights|

### Get terminal group apk

Get terminal group apk by groupApkId and pidList

**API**

```
 public Result<SimpleTerminalGroupApkDTO> getTerminalGroupApk(Long groupApkId)
 public Result<SimpleTerminalGroupApkDTO> getTerminalGroupApk(Long groupApkId, List<String> pidList)
```

**Input parameter(s) description**

|Parameter Name|Type|Nullable|Description|
|:---|:---|:---|:---|
|groupApkId|Long|false|the id of terminalGroupApk|
|pidList|List\<String\>|true|the pid of the configured parameters to return|

**Sample codes**

```
TerminalGroupApkApi terminalGroupApkApi = new TerminalGroupApkApi("https://api.whatspos.com/p-market-api", "RCA9MDH6YN3WSSGPW6TJ", "TUNLDZVZECHNKZ4FW07XFCKN2W0N8ZDEA5ENKZYN");
List<String> pidList = new ArrayList<>();
pidList.add("sys.cap.test01");
pidList.add("sys.cap.test02");
pidList.add("sys.cap.test03");
Result<SimpleTerminalGroupApkDTO> result = terminalGroupApkApi.getTerminalGroupApk(17850L, pidList);
```

**Client side validation failed sample result(JSON formatted)**

```
{
	"businessCode": -1,
	"validationErrors": ["Terminal group Apk Id  cannot be null and cannot be less than 1!"]
}
```

**Server side validation failed sample result(JSON formatted)**

```
{
	"businessCode": 2101,
	"message": "Group app not found"
}
```

**Successful sample result(JSON formatted)**

```
{
	"businessCode": 0,
	"data": {
    	"groupApkParam": {
			"failedCount": 0,
			"filteredCount": 0,
			"pendingCount": 8,
			"paramTemplateName": "PassWord_Param02.xml",
			"successCount": 0
		},
		"apkVersionName": "10.3.8.30",
		"failedCount": 0,
		"filteredCount": 0,
		"actionStatus": 1,
		"apkPackageName": "com.baidu.tieba",
		"pendingCount": 8,
		"effectiveTime": 1576118640000,
		"apkVersionCode": 167968776,
		"successCount": 0,
		"id": 1728,
		"updatedDate": 1576118649000,
		"status": "A"
    }
}
```

The type of data is SimpleTerminalGroupApkDTO,TerminalGroupApkParamDTO , and Refer to Search terminal group apk Api for
structure .

**Possible validation errors**

> <font color="red">Parameter terminalGroupApkId cannot be null and cannot be less than 1!</font>

**Possible business codes**

| Business Code | Message             | Description |
| :------------ | :------------------ | :---------- |
| 2101          | Group app not found |             |

### Search terminal group apk

The search terminal group apk API allows third party system to search group apks to the specified group by page.
**API**

```
public Result<SimpleTerminalGroupApkDTO> searchTerminalGroupApk(int pageNo, int pageSize, SearchOrderBy orderBy, Long groupId, Boolean pendingOnly, Boolean historyOnly, String keyWords)
```

**Input parameter(s) description**

| Name        | Type          | Nullable | Description                                                                                                                                                                                                                                                       |
|:------------|:--------------|:---------|:------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| pageNo      | int           | false    | page number, value must >=1                                                                                                                                                                                                                                       |
| pageSize    | int           | false    | the record number per page, range is 1 to 100                                                                                                                                                                                                                     |
| orderBy     | SearchOrderBy | true     | the sort order by field name, if this parameter is null the search result will order by created date descend. The value of this parameter can be one of TerminalGroupApkApi.SearchOrderBy.CreatedDate_desc and TerminalGroupApkApi.SearchOrderBy.CreatedDate_asc. |
| groupId     | Long          | false    | the id of the group                                                                                                                                                                                                                                               |
| pendingOnly | Boolean       | true     | Indicate whether to search the pending push task only                                                                                                                                                                                                             |
| historyOnly | Boolean       | true     | Indicate whether to search history push task only                                                                                                                                                                                                                 |
| keyWords    | String        | true     | Key words， it will match APP's package name, APK's name, APK's short description and APK's description                                                                                                                                                            |

**Sample codes**

```
TerminalGroupApkApi terminalGroupApkApi = new TerminalGroupApkApi("https://api.whatspos.com/p-market-api", "RCA9MDH6YN3WSSGPW6TJ", "TUNLDZVZECHNKZ4FW07XFCKN2W0N8ZDEA5ENKZYN");
Result<SimpleTerminalGroupApkDTO> result = terminalGroupApkApi.searchTerminalGroupApk(1,1, TerminalGroupApkApi.SearchOrderBy.CreatedDate_asc,16526L,true,null,null);
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

**Successful sample result**

```
{
	"businessCode": 0,
	"pageInfo": {
		"pageNo": 1,
		"limit": 1,
		"totalCount": 3,
		"hasNext": true,
		"dataSet": [{
		"groupApkParam": {
			"failedCount": 0,
			"filteredCount": 0,
			"pendingCount": 0,
			"paramTemplateName": "paramTemplate.xml",
			"successCount": 0
		},
		"apkVersionName": "5.01.07",
		"failedCount": 0,
		"filteredCount": 0,
		"actionStatus": 0,
		"apkPackageName": "com.pax.android.demoapp",
		"pendingCount": 0,
		"apkVersionCode": 57,
		"successCount": 0,
		"id": 1644,
		"updatedDate": 1562219872000,
		"status": "P"
	}]
	}
}
```

The type in dataSet is SimpleTerminalGroupApkDTO. And the structure like below.

| Name           | Type                     | Description                                                  |
| :------------- | :----------------------- | :----------------------------------------------------------- |
| id             | Long                     | the id of terminal group apk                                 |
| apkPackageName | String                   | the packageName of terminal group apk                        |
| apkVersionName | String                   | the version name of terminal group apk                       |
| apkVersionCode | Long                     | the version code of terminal group apk                       |
| effectiveTime  | String                   |                                                              |
| expiredTime    | String                   |                                                              |
| updatedDate    | String                   |                                                              |
| actionStatus   | int                      | the action status, value can be 0 and 1, please refer to [Action Status](APPENDIX.md#user-content-action-status) |
| status         | String                   | the push status                                              |
| pendingCount   | int                      |                                                              |
| successCount   | int                      |                                                              |
| failedCount    | int                      |                                                              |
| filteredCount  | int                      |                                                              |
| groupApkParam  | TerminalGroupApkParamDTO | the structure like below                                     |

The type in data is TerminalGroupApkParamDTO. And the structure like below.

| Name                 | Type                 | Description                       |
| :------------------- |:---------------------| :-------------------------------- |
| paramTemplateName    | String               |                                   |
| configuredParameters | Map\<String,String\> | Configuration parameters in param |
| pendingCount         | int                  |                                   |
| successCount         | int                  |                                   |
| failedCount          | int                  |                                   |
| filteredCount        | int                  |                                   |

**Possible client validation errors**

> <font color=red>pageNo:must be greater than or equal to 1</font>   
> <font color=red>pageSize:must be greater than or equal to 1</font>   
> <font color=red>pageSize:must be less than or equal to 100</font>

**Possible business codes**

| Business Code | Message                  | Description |
| :------------ | :----------------------- | :---------- |
| 2150          | Terminal group not found |             |

### Create and active group apk

Create and active a group push apk task by CreateTerminalGroupApkRequest.

**API**

```
public Result<SimpleTerminalGroupApkDTO> createAndActiveGroupApk(CreateTerminalGroupApkRequest createRequest)
```

**Input parameter(s) description**

|Parameter Name|Type|Nullable|Description|
|:---|:---|:---|:---|
|createRequest|CreateTerminalGroupApkRequest|false||

Structure of class CreateTerminalGroupApkRequest.

| Property Name             | Type                  | Nullable | Description                                                                                                                                                                                                                                                                                                                                        |
|:--------------------------|:----------------------|:---------|:---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| groupId                   | Long                  | false    | The id of the terminal group                                                                                                                                                                                                                                                                                                                       |
| pushTemplateName          | String                | true     | the name of the push Template                                                                                                                                                                                                                                                                                                                      |
| packageName               | String                | false    | the package name of push apk                                                                                                                                                                                                                                                                                                                       |
| version                   | String                | true     | The package name which indicate the application you want to push                                                                                                                                                                                                                                                                                   |
| templateName              | String                | true     | The template file name of paramter application. The template file name can be found in the detail of the parameter application. If user want to push more than one template the please use &#124; to concact the different template file names like tempate1.xml&#124;template2.xml&#124;template3.xml, the max size of template file names is 10. |
| parameters                | Map\<String, String\> | true     | The parameter key and value, the key the PID in template                                                                                                                                                                                                                                                                                           |
| base64FileParameters      | List\<FileParameter\> | true     | The parameter of file type, the max counter of file type parameter is 10, and the max size of each parameter file is 500kb                                                                                                                                                                                                                         |
| inheritPushHistory        | Boolean               | true     | Whether to inherit the latest success push history parameters, inherited parameter values have lower priority than passed-in parameter values                                                                                                                                                                                                      |
| forceUpdate               | Boolean               | true     | Whether to force the app to update                                                                                                                                                                                                                                                                                                                 |
| wifiOnly                  | Boolean               | true     | Whether to download over Wi-Fi or Cable network only, don’t allow to download over the cellular network                                                                                                                                                                                                                                            |
| effectiveTime             | Date                  | true     | The time when to start the push task                                                                                                                                                                                                                                                                                                               |
| expiredTime               | Date                  | true     | The time when to stop the push task                                                                                                                                                                                                                                                                                                                |
| validateUndefinedParameter  | Boolean               | true     | Whether to validate undefined parameters                                                                                                                                                                                                                                                                                                             |

Structure of class FileParameter

| Property Name | Type   | Nullable | Description                                             |
| :------------ | :----- | :------- | :------------------------------------------------------ |
| pid           | String | true     | The PID in template                                     |
| fileName      | String | true     | The parameter of file type, file name containing suffix |
| fileData      | String | true     | The parameter of file type, file base64 data            |

**Sample codes**

```
TerminalGroupApkApi terminalGroupApkApi = new TerminalGroupApkApi("https://api.whatspos.com/p-market-api", "RCA9MDH6YN3WSSGPW6TJ", "TUNLDZVZECHNKZ4FW07XFCKN2W0N8ZDEA5ENKZYN");
CreateTerminalGroupApkRequest createRequest = new CreateTerminalGroupApkRequest();
createRequest.setGroupId(16543L); 
createRequest.setPushTemplateName("testCreate3RD-result-api-test-CREATEbY-newest-12334111");
createRequest.setPackageName("com.baidu.tieba");
createRequest.setTemplateName("123 (3).xml");
//  Map<String, String> parameters = new HashMap<String, String>();
//  parameters.put("sys_F1_sys_cap_test01", "abc");
//  parameters.put("sys_F1_sys_cap_test02", "123");
//  parameters.put("sys_F1_sys_cap_password", "123");
createRequest.setParameters(null);
//  FileParameter fileParameter = new FileParameter();
//  fileParameter.setPid("PID.cardBinFile");
//  fileParameter.setFileName("cardBinFile.jpeg");
// fileParameter.setFileData("data:image/jpeg;base64,/9j/4AAQSkZJR==");
// List<FileParameter> base64FileParameters = new ArrayList<>();
// base64FileParameters.add(fileParameter);
createRequest.setBase64FileParameters(null);
Result<SimpleTerminalGroupApkDTO> result = terminalGroupApkApi.createAndActiveGroupApk(createRequest);
```

**Client side validation failed sample result(JSON formatted)**

```
{
	"businessCode": -1,
	"validationErrors": ["Parameter terminalGroupApkCreateRequest cannot be null!"]
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
		"groupApkParam": {
			"failedCount": 0,
			"filteredCount": 0,
			"pendingCount": 0,
			"paramTemplateName": "123 (3).xml",
			"successCount": 0
		},
		"apkVersionName": "10.3.8.30",
		"failedCount": 0,
		"filteredCount": 0,
		"actionStatus": 0,
		"apkPackageName": "com.baidu.tieba",
		"pendingCount": 0,
		"effectiveTime": 1583396340000,
		"apkVersionCode": 167968776,
		"successCount": 0,
		"id": 1743,
		"updatedDate": 1583396371789,
		"status": "A"
	}
}
```

<br>
The type of data is SimpleTerminalGroupApkDTO,TerminalGroupApkParamDTO , and Refer to Search terminal group apk Api for structure .

**Possible client validation errors**


> <font color="red">Parameter createRequest cannot be null!</font>

**Possible business codes**

| Business Code | Message                                                                                      | Description |
|:--------------|:---------------------------------------------------------------------------------------------|:------------|
| 135           | Request parameter is missing or invalid                                                      | &nbsp;      |
| 1111          | Selected parameter templates exceeded the max limit                                          ||
| 2027          | Package name is mandatory                                                                    ||
| 2029          | Apk not found                                                                                ||
| 2030          | Parameter template not found                                                                 ||
| 2031          | Template Name cannot be empty                                                                ||
| 2032          | API does not support push non free applicationAPI does not support push non free application ||
| 2150          | Terminal group not found                                                                     ||
| 13100         | Invalid application parameter variables                                                      ||

### Suspend terminal group apk

This api allows the third party system suspend apk of group push task.

**API**

```
public Result<SimpleTerminalGroupApkDTO> suspendTerminalGroupApk(Long groupApkId)
```

**Input parameter(s) description**

|Parameter Name|Type|Nullable|Description|
|:---|:---|:---|:---|
|groupApkId|Long|false|the id of the group push apk task|

**Sample codes**

```
TerminalGroupApkApi terminalGroupApkApi = new TerminalGroupApkApi("https://api.whatspos.com/p-market-api", "RCA9MDH6YN3WSSGPW6TJ", "TUNLDZVZECHNKZ4FW07XFCKN2W0N8ZDEA5ENKZYN");
Result<SimpleTerminalGroupApkDTO> result = terminalGroupApkApi.suspendTerminalGroupApk(1743L);
```

**Client side validation failed sample result(JSON formatted)**

```
{
	"businessCode": -1,
	"validationErrors": ["Terminal group Apk Id  cannot be null and cannot be less than 1!"]
}
```

**Server side validation failed sample result(JSON formatted)**

```
{
	"businessCode": 2101,
	"message": "Group app not found"
}
```

**Successful sample result(JSON formatted)**

```
{
	"businessCode": 0
	"data": {
		"groupApkParam": {
			"failedCount": 0,
			"filteredCount": 0,
			"pendingCount": 0,
			"paramTemplateName": "123 (3).xml",
			"successCount": 0
		},
		"apkVersionName": "10.3.8.30",
		"failedCount": 0,
		"filteredCount": 0,
		"actionStatus": 0,
		"apkPackageName": "com.baidu.tieba",
		"pendingCount": 0,
		"effectiveTime": 1583396760000,
		"apkVersionCode": 167968776,
		"successCount": 0,
		"id": 1743,
		"updatedDate": 1583398464000,
		"status": "S"
	}
}
```

The type of data is SimpleTerminalGroupApkDTO,TerminalGroupApkParamDTO , and Refer to Search terminal group apk Api for
structure .

**Possible business codes**

|Business Code|Message|Description|
|:---|:---|:---|
|2101|Group app not found||
|2110|Group app is not active||

### Delete terminal group apk

This api allows the third party system delete apk of group push task.

**API**

```
public Result<String> deleteTerminalGroupApk(Long groupApkId)
```

**Input parameter(s) description**

|Parameter Name|Type|Nullable|Description|
|:---|:---|:---|:---|
|groupApkId|Long|false|the id of the group push apk task|

**Sample codes**

```
TerminalGroupApkApi terminalGroupApkApi = new TerminalGroupApkApi("https://api.whatspos.com/p-market-api", "RCA9MDH6YN3WSSGPW6TJ", "TUNLDZVZECHNKZ4FW07XFCKN2W0N8ZDEA5ENKZYN");
Result<String> result = terminalGroupApkApi.deleteTerminalGroupApk(1743L);
```

**Client side validation failed sample result(JSON formatted)**

```
{
	"businessCode": -1,
	"validationErrors": ["Terminal group Apk Id  cannot be null and cannot be less than 1!"]
}
```

**Server side validation failed sample result(JSON formatted)**

```
{
	"businessCode": 2101,
	"message": "Group app not found"
}
```

**Successful sample result(JSON formatted)**

```
{
	"businessCode": 0
}
```

**Possible business codes**

|Business Code|Message|Description|
|:---|:---|:---|
|2101|Group app not found||