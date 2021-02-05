## Terminal APIs

Terminal APIs allow thirdparty system search terminals, get a terminal, create a terminal, update a terminal, activate a terminal, disable a terminal and delete a exist terminal.

All the terminal APIs are in the class *com.pax.market.api.sdk.java.api.terminal.TerminalApi*.   

**Constructors of TerminalApi**

```
public TerminalApi(String baseUrl, String apiKey, String apiSecret);
```

**Constructor parameters description**

|Name|Type|Description|
|:----|:----|:----|
|baseUrl|String|the base url of REST API|
|apiKey|String|the apiKey of marketplace, get this key from PAXSTORE admin console, refer to chapter Apply access rights|
|apiSecret|String|apiSecret, get api secret from PAXSTORE admin console, refer to chapter Apply access rights|



### Search terminals

The search terminal API allow the thirdparty system search terminals by page. 
Note: This result of this API does not include the geolocation, firmware and installed application, if you need those 3 information in result please use another search terminals API

**API**

```
public Result<TerminalDTO> searchTerminal(int pageNo, int pageSize, TerminalSearchOrderBy orderBy, TerminalStatus status, String snNameTID)
```

**Input parameter(s) description**

| Name| Type | Nullable|Description |
|:---- | :----|:----|:----|
|pageNo|int|false|page number, value must >=1|
|pageSize|int|false|the record number per page, range is 1 to 100|
|orderBy|TerminalSearchOrderBy|true|the sort order by field name, value can be one of TerminalSearchOrderBy.Name, TerminalSearchOrderBy.Tid and TerminalSearchOrderBy.SerialNo. If pass null parameter the search result will order by id by default.|
|status|TerminalStatus|true|the terminal status<br/> the value can be TerminalStatus.Active, TerminalStatus.Inactive, TerminalStatus.Suspend|
|snNameTID|String|true|search by serial number,name and TID|

**Sample codes**

```
TerminalApi terminalApi = new TerminalApi("https://api.whatspos.com/p-market-api", "RCA9MDH6YN3WSSGPW6TJ", "TUNLDZVZECHNKZ4FW07XFCKN2W0N8ZDEA5ENKZYN");
Result<TerminalDTO> result = terminalApi.searchTerminal(1, 10, null, TerminalStatus.Active, "sn0101012225");
```

**Client side validation failed sample result(JSON formatted)**

```
{
	"businessCode": -1,
	"validationErrors": ["pageSize:must be greater than or equal to 1", "pageNo:must be greater than or equal to 1"]
}
```

**Succssful sample result(JSON formatted)**

```
{
	"businessCode": 0,
	"pageInfo": {
		"pageNo": 1,
		"limit": 10,
		"totalCount": 1,
		"hasNext": false,
		"dataSet": [{
			"id": 907558,
			"name": "testcreateterminal_023",
			"tid": "FATIAP0T",
			"serialNo": "sn0101012225",
			"status": "A",
			"merchantName": "KFC",
			"modelName": "A920",
			"resellerName": "New York",
			"location": "USA"
		}]
	}
}
```

The type in dataSet of result is TerminalDTO. The structure shows below.

Structure of class TerminalDTO

|Property Name|Type|Description|
|:---|:---|:---|
|id|Long|Terminal's id.|
|name|String|Terminal's name.|
|tid|String|Terminal's tid.|
|serialNo|String|Serial number of terminal.|
|status|String|Status of terminal. Value can be one of A(active), P(Pendding) and S(Suspend).|
|merchantName|String|The merchant of terminal belongs to.|
|modelName|String|Model name of terminal.|
|resellerName|String|The reseller of terminal belongs to.|
|createdDate|Date|The create time|
|lastActiveTime|Date|The activation time|
|location|String|The location|
|geoLocation|TerminalLocationDTO| The geo location of the terminal|
|installedFirmware|TerminalInstalledFirmwareDTO| The installed firmware of the terminal|
|installedApks|List\<TerminalInstalledApkDTO\>| The installed applications of the terminal|
|terminalDetail|TerminalDetailDTO| The terminal detail info |
|terminalAccessory|TerminalAccessoryDTO| The terminal accessory info |

Structure of class TerminalLocationDTO

|Property Name|Type|Description|
|:---|:---|:---|
|lat|Double|The latitude of geo location|
|lng|Double|The longitude of geo location|

Structure of class TerminalInstalledFirmwareDTO  

|Property Name|Type|Description|
|:---|:---|:---|
|firmwareName|String|Firmware name|
|installTime|Date|Firmware installed date|


Structure of class TerminalInstalledApkDTO  

|Property Name|Type|Description|
|:---|:---|:---|
|appName|String|Application name|
|packageName|String|Package name of application|
|versionName|String|Version name of application|
|versionCode|Long|Version code of application|
|packageName|String|Package name of application|
|installTime|Date|Installed time of application|

Structure of class TerminalDetailDTO

| Property Name    | Type   | Description                  |
| :--------------- | :----- | :--------------------------- |
| pn               | String | Terminal's pn                |
| osVersion        | String | Terminal's android version   |
| imei             | String | Terminal's IMEI              |
| screenResolution | String | Terminal's screen resolution |
| language         | String | Terminal's language          |
| ip               | String | Terminal's network ip        |
| timeZone         | String | Terminal's time zone         |
| macAddress       | String | Terminal's mac address       |
| iccid            | String | Terminal's Iccid             |
| cellid           | String | Terminal's Cellid            |

Structure of class TerminalAccessoryDTO

| Property Name       | Type                     | Description                                         |
| :------------------ | :----------------------- | :-------------------------------------------------- |
| relatedTerminalName | String                   | The accessory information terminal name             |
| basic               | TerminalDeviceSimpleDTO  | The basic information of the accessory device       |
| hardware            | TerminalDeviceSimpleDTO  | The hardware information of the accessory device    |
| installApps         | TerminalDeviceSimpleDTO  | The installApps information of the accessory device |
| history             | TerminalDeviceHistoryDTO | The history information of the accessory device     |

Structure of class TerminalDeviceSimpleDTO

| Property Name | Type   | Description                       |
| :------------ | :----- | :-------------------------------- |
| name          | String | The accessory information name    |
| content       | String | The accessory information content |

Structure of class TerminalDeviceHistoryDTO

| Property Name | Type   | Description                                                  |
| :------------ | :----- | :----------------------------------------------------------- |
| name          | String | The accessory information name                               |
| version       | String | The accessory information version                            |
| status        | String | The status of the related historical push of the accessory device |
| installTime   | Date   | The accessory information install time                       |
| fileSize      | Long   | The size of the file pushed by the accessory device          |
| fileType      | String | The type of the file pushed by the accessory device          |
| source        | String | The file source                                              |
| remarks       | String | The remarks information                                      |

**Possible validation errors**

> <font color=red>pageSize:must be greater than or equal to 1</font>   
> <font color=red>pageNo:must be greater than or equal to 1</font>   
> <font color=red>pageSize:must be less than or equal to 100</font>  

### Search terminals include geo location, installed app and firmware  
This API is similar to the search terminals API, it has additional 3 parameters, the details please refer to the Input parameter(s) description

**API**  
```
public Result<TerminalDTO> searchTerminal(int pageNo, int pageSize, TerminalSearchOrderBy orderBy, TerminalStatus status, String snNameTID, boolean includeGeoLocation, boolean includeInstalledApks, boolean includeInstalledFirmware)
```

**Input parameter(s) description**

| Name| Type | Nullable|Description |
|:---- | :----|:----|:----|
|pageNo|int|false|page number, value must >=1|
|pageSize|int|false|the record number per page, range is 1 to 100|
|orderBy|TerminalSearchOrderBy|true|the sort order by field name, value can be one of TerminalSearchOrderBy.Name, TerminalSearchOrderBy.Tid and TerminalSearchOrderBy.SerialNo. If pass null parameter the search result will order by id by default.|
|status|TerminalStatus|true|the terminal status<br/> the value can be TerminalStatus.Active, TerminalStatus.Inactive, TerminalStatus.Suspend|
|snNameTID|String|true|search by serial number,name and TID|
|includeGeoLocation|boolean|true|whether to include geo location information in search result|
|includeInstalledApks|boolean|true|whether to include install applications in search result|
|includeInstalledFirmware|boolean|true|whether to include the firmware version of the terminal in search result|

**Sample codes**

```
TerminalApi terminalApi = new TerminalApi("https://api.whatspos.com/p-market-api", "RCA9MDH6YN3WSSGPW6TJ", "TUNLDZVZECHNKZ4FW07XFCKN2W0N8ZDEA5ENKZYN");
Result<TerminalDTO> result = terminalApi.searchTerminal(1, 10, null, TerminalStatus.Active, "sn0101012225", true, true, true);
```

**Client side validation failed sample result(JSON formatted)**

```
{
	"businessCode": -1,
	"validationErrors": ["pageSize:must be greater than or equal to 1", "pageNo:must be greater than or equal to 1"]
}
```

**Succssful sample result(JSON formatted)**

```
{
	"businessCode": 0,
	"pageInfo": {
		"pageNo": 1,
		"limit": 10,
		"totalCount": 1,
		"hasNext": false,
		"dataSet": [{
			"id": 907558,
			"name": "testcreateterminal_023",
			"tid": "FATIAP0T",
			"serialNo": "sn0101012225",
			"status": "A",
			"merchantName": "KFC",
			"modelName": "A920",
			"resellerName": "New York",
			"location": "USA",
			"geoLocation": {
				"lng": 120.77595,
				"lat": 31.308021
			},
			"installedFirmware": {
				"firmwareName": "A930_PayDroid_7.1.1_Virgo_customer_res_pax_20180925",
				"installTime": null

			},
			"installedApks": [{
				"appName": "WSPLink",
				"packageName": "com.soundpayments.wsplink",
				"installTime": 1563639530000,
				"versionName": "10.01.00.00",
				"versionCode": 10010000
			}, {
				"appName": "NeptuneService",
				"packageName": "com.pax.ipp.neptune",
				"installTime": 1230692400000,
				"versionName": "V3.05.00_20190523",
				"versionCode": 33
			}, {
				"appName": "releasedemo1",
				"packageName": "com.pax.new.release.demo1",
				"installTime": 1563639280000,
				"versionName": "V3.02.00_20190129",
				"versionCode": 11
			}]
			}
		}]
	}
}
```

The type in dataSet of result is TerminalDTO.



**Possible validation errors**

> <font color=red>pageSize:must be greater than or equal to 1</font>   
> <font color=red>pageNo:must be greater than or equal to 1</font>   
> <font color=red>pageSize:must be less than or equal to 100</font>  



### Get a terminal

The get terminal API allows the thirdparty system get a terminal by terminal id. If the termianl does not exist the data field in result is null.

**API**

```
public Result<TerminalDTO> getTerminal(Long terminalId);
public Result<TerminalDTO> getTerminal(Long terminalId, boolean includeDetailInfo);
```

**Input parameter(s) description**

|Parameter Name|Type|Nullable|Description|
|:---|:---|:---|:---|
|terminalId|Long|false|The terminal id.|


**Sample codes**

```
TerminalApi terminalApi = new TerminalApi("https://api.whatspos.com/p-market-api", "RCA9MDH6YN3WSSGPW6TJ", "TUNLDZVZECHNKZ4FW07XFCKN2W0N8ZDEA5ENKZYN");
//simple info
Result<TerminalDTO> result = terminalApi.getTerminal(908627L);
//includeDetailInfo, whether to return Detail Info 
Result<TerminalDTO> result = terminalApi.getTerminal(908627L,true);
```

**Client side validation failed sample result(JSON formatted)**

```
{
	"businessCode": -1,
	"validationErrors": ["Parameter terminalId cannot be null and cannot be less than 1!"]
}
```

**Server side validation failed sample result(JSON formatted)**

```
{
	"businessCode": 1801,
	"message": "Terminal doesn't exist"
}
```

**Successful sample result(JSON formatted)**

```
{
	"businessCode": 0,
	"data": {
		"id": 908627,
		"name": "test8000999",
		"tid": "BTG7KFTY",
		"serialNo": "TEST8000999",
		"status": "A",
		"merchantName": "pax",
		"modelName": "A930",
		"resellerName": "Jesse",
		"location": "",
		"createdDate": 1552536099000,
		"lastActiveTime": 1552536095000
	}
}
```

```HTML
{
	"businessCode": 0,
	"data": {
		"id": 908627,
		"name": "test8000999",
		"tid": "BTG7KFTY",
		"serialNo": "TEST8000999",
		"status": "A",
		"merchantName": "pax",
		"modelName": "A930",
		"resellerName": "Jesse",
		"location": "",
		"createdDate": 1552536099000,
		"lastActiveTime": 1552536095000,
		"terminalDetail": {
			"pn": "A920-3AW-RD5-21EU",
			"screenResolution": "720px * 1280px",
			"language": "English",
			"ip": "192.168.5.213",
			"timeZone": "GMT +08:00",
			"macAddress": "A4:D4:B2:4C:14:FE"
		},
		"terminalAccessory": {
			"basic": [{
				"name": "SN",
				"content": "1140073435"
			}, {
				"name": "MACH",
				"content": "Q20"
			}, {
				"name": "OS",
				"content": "Prolin2.7.66"
			}],
			"hardware": [{
				"name": "PCD",
				"content": "Y"
			}, {
				"name": "MSR",
				"content": "Y"
			}, {
				"name": "SCI",
				"content": "Y"
			}, {
				"name": "BOARDID",
				"content": "Q20_M06_P00"
			}, {
				"name": "BLUETOOTH"
			}, {
				"name": "ETHERNET"
			}, {
				"name": "WIFI"
			}, {
				"name": "WIRELESS"
			}, {
				"name": "MODEM"
			}, {
				"name": "PRINTER"
			}, {
				"name": "BARCODE"
			}, {
				"name": "FLASH",
				"content": "128MB"
			}, {
				"name": "FREEFLASH",
				"content": "58.41M"
			}, {
				"name": "RAM",
				"content": "246.50MB"
			}, {
				"name": "SECURITY LEVEL",
				"content": "1"
			}, {
				"name": "SECURITY MODE",
				"content": "2"
			}, {
				"name": "TOUCHSCREEN",
				"content": "Y"
			}, {
				"name": "CIPHER_CHIP"
			}],
			"installApps": [{
				"name": "browser",
				"content": "2.00.10"
			}],
			"history": [{
				"name": "MAINAPP/config.xml",
				"status": "Success",
				"installTime": 1588053636000,
				"fileSize": 102,
				"fileType": "Private file",
				"source": "Remote Upgrade"
			}, {
				"name": "MAINAPP/sys_param.p",
				"status": "Success",
				"installTime": 1588053633000,
				"fileSize": 135,
				"fileType": "Private file",
				"source": "Remote Upgrade"
			}, {
				"name": "MAINAPP/sys_cap.p",
				"status": "Success",
				"installTime": 1588053630000,
				"fileSize": 234,
				"fileType": "Private file",
				"source": "Remote Upgrade"
			}, {
				"name": "browser.aip",
				"status": "Success",
				"installTime": 1588053240000,
				"fileSize": 2165940,
				"fileType": "Application",
				"source": "Local Upgrade"
			}, {
				"name": "prolin-pelican-2.7.66.8833R_SIG.zip",
				"version": "2.00.10",
				"status": "Success",
				"installTime": 1588041153000,
				"fileSize": 17890812,
				"fileType": "Firmware",
				"source": "Local Upgrade"
			}]
		}
	}
}
```



The type of data in result is TerminalDTO. Its structure already shows in search terminal API.


**Possible validation errors**

> <font color=red>Parameter terminalId cannot be null and cannot be less than 1!</font>  

**Possible business codes**

|Business Code|Message|Description|
|:---|:---|:---|
|1801|Terminal doesn't exist|&nbsp;|


### Create a terminal

Create merchant API allow the thirdparty system create a terminal remotely.


**API**

```
public Result<TerminalDTO> createTerminal(TerminalCreateRequest terminalCreateRequest)
```

**Input parameter(s) description**

|Parameter Name|Type|Nullable|Description|
|:---|:---|:---|:---|
|terminalCreateRequest|TerminalCreateRequest|false|The create request object. The structure shows below.|

Structure of class TerminalCreateRequest

|Property Name|Type|Nullable|Description|
|:---|:---|:---|:---|
|name|String|false|The name of terminal, max length is 64.|
|tid|String|true|The tid of terminal. If it is empty system will generate a tid when creating. And the length range is from 8 to 16.|
|serialNo|String|true|The serial number of terminal. If the status is active the serial number is mandatory.|
|merchantName|String|true|The merchant of terminal belongs to. If the initial is active then merchantName is mandatory. The max length is 64. Make sure the merchant belongs to the given reseller|
|resellerName|String|false|The reseller of terminal belongs to. Max length is 64.|
|modelName|String|false|The model name of terminal. Max length is 64.|
|location|String|true|The location of terminal, max length is 32.|
|status|String|true|Status of terminal, valus can be one of A(Active) and P(Pendding). If status is null the initial status is P(Pendding) when creating.|


**Sample codes**

```
TerminalApi terminalApi = new TerminalApi("https://api.whatspos.com/p-market-api", "RCA9MDH6YN3WSSGPW6TJ", "TUNLDZVZECHNKZ4FW07XFCKN2W0N8ZDEA5ENKZYN");
TerminalCreateRequest createReq = new TerminalCreateRequest();
createReq.setName("KFC-TML-001");
createReq.setMerchantName("KFC");
createReq.setResellerName("New York");
createReq.setLocation("USA");
createReq.setSerialNo("sn0101012236");
createReq.setModelName("A920");
createReq.setStatus(TerminalStatus.Active);
Result<TerminalDTO> result = terminalApi.createTerminal(createReq);
```

**Client side validation failed sample result(JSON formatted)**

```
{
	"businessCode": -1,
	"validationErrors": ["modelName:may not be empty", "name:may not be empty"]
}
```

**Server side validation failed sample result(JSON formatted)**

```
{
	"businessCode": 1740,
	"message": "Your terminal (SN:sn0101012237) already exists"
}
```

**Successful sample result(JSON formatted)**

```
{
	"businessCode": 0,
	"data": {
		"id": 907560,
		"name": "KFC-TML-001",
		"tid": "53K5M9OS",
		"serialNo": "sn0101012236",
		"status": "A",
		"merchantName": "KFC",
		"modelName": "A920",
		"resellerName": "New York",
		"location": "USA"
	}
}
```

The type of data in result is same as search terminal API.

**Possible validation errors**

> <font color=red>Parameter terminalCreateRequest cannot be null!</font>  
> <font color=red>modelName:may not be empty</font>  
> <font color=red>resellerName:may not be empty</font>  
> <font color=red>name:may not be empty</font>  
> <font color=red>name:length must be between 0 and 64</font>  
> <font color=red>tid:length must be between 8 and 15</font>  
> <font color=red>serialNo:length must be between 0 and 32</font>  
> <font color=red>merchantName:length must be between 0 and 64</font>  
> <font color=red>resellerName:length must be between 0 and 64</font>  
> <font color=red>modelName:length must be between 0 and 64</font>  
> <font color=red>location:length must be between 0 and 32</font>  


**Possible business codes**

|Business Code|Message|Description|
|:---|:---|:---|
|1740|Your terminal (SN:xxxxxx) already exists|&nbsp;|
|1759|Reseller doesn't exist|&nbsp;|
|1720|Merchant doesn't exist|&nbsp;|
|1937|Merchant is not belong to the given Reseller!|&nbsp;|
|1700|Model doesn't exist|&nbsp;|
|1817|Terminal name is mandatory|&nbsp;|
|1818|Terminal name is too long|&nbsp;|
|1803|Terminal model is mandatory|&nbsp;|
|2326|Terminal reseller is mandatory|&nbsp;|
|1806|Terminal SN is too long|&nbsp;|
|2312|Terminal Serial No. accept alphanumeric|Alphanumeric and max length is 16|
|1807|Terminal model is too long|&nbsp;|
|1808|Terminal merchant is too long|&nbsp;|
|1809|Terminal location is too long|&nbsp;|
|1804|Terminal merchant is mandatory|&nbsp;|
|1802|Terminal SN is mandatory|&nbsp;|
|1828|TID already used|&nbsp;|
|2349|Terminal TID length is 8 to 15|&nbsp;|
|1737|The associated merchant is not activate|&nbsp;|
|1773|The associated reseller is not activate|&nbsp;|
|2412|Your terminal SN not exist in asset|&nbsp;|


### Update a terminal

Update terminal API allows the thirdparty system update a exist terminal by terminal id.

**API**

```
public Result<TerminalDTO> updateTerminal(Long terminalId,TerminalUpdateRequest terminalUpdateRequest)
```

**Input parameter(s) description**

|Parameter Name|Type|Nullable|Description|
|:---|:---|:---|:---|
|terminalId|Long|false|Terminal's id.|
|terminalUpdateRequest|TerminalUpdateRequest|false|Update terminal request object. The structure shows below.|

Structure of class TerminalUpdateRequest

|Property Name|Type|Nullable|Description|
|:---|:---|:---|:---|
|name|String|false|The name of terminal, max length is 64.|
|tid|String|true|The tid of terminal. If it is empty system will generate a tid when creating. And the length range is from 8 to 15.|
|serialNo|String|true|The serial number of terminal. If the status is active the serial number is mandatory.|
|merchantName|String|true|The merchant of terminal belongs to. If the initial is active then merchantName is mandatory. The max length is 64. Make sure the merchant belongs to the given reseller|
|resellerName|String|false|The reseller of terminal belongs to. Max length is 64.|
|modelName|String|false|The model name of terminal. Max length is 64.|
|location|String|true|The location of terminal, max length is 32.|


**Sample codes**

```
TerminalApi terminalApi = new TerminalApi("https://api.whatspos.com/p-market-api", "RCA9MDH6YN3WSSGPW6TJ", "TUNLDZVZECHNKZ4FW07XFCKN2W0N8ZDEA5ENKZYN");
TerminalUpdateRequest updateReq = new TerminalUpdateRequest();
updateReq.setName("KFC-TML-001");
updateReq.setModelName("A920");
updateReq.setLocation("CN");
updateReq.setSerialNo("sn010101211226");
updateReq.setResellerName("New York");
updateReq.setMerchantName("KFC");
Result<TerminalDTO> result = terminalApi.updateTerminal(907560L, updateReq);
```

**Client side validation failed sample result(JSON formatted)**

```
{
	"businessCode": -1,
	"validationErrors": ["Parameter terminalId cannot be null and cannot be less than 1!"]
}
```

**Server side validation failed sample result(JSON formatted)**

```
{
	"businessCode": 1804,
	"message": "Terminal merchant is mandatory"
}
```

**Successful sample result(JSON formatted)**

```
{
	"businessCode": 0,
	"data": {
		"id": 907560,
		"name": "KFC-TML-001",
		"tid": "53K5M9OS",
		"serialNo": "sn010101211226",
		"status": "S",
		"merchantName": "KFC",
		"modelName": "A920",
		"resellerName": "New York",
		"location": "CN"
	}
}
```

The type of data in result is same as search terminal API.

**Possible client validation errors**

> <font color=red>Parameter terminalUpdateRequest cannot be null!</font>  
> <font color=red>Parameter terminalId cannot be null and cannot be less than 1!</font>
> <font color=red>modelName:may not be empty</font>  
> <font color=red>resellerName:may not be empty</font>  
> <font color=red>name:may not be empty</font>  
> <font color=red>name:length must be between 0 and 64</font>  
> <font color=red>tid:length must be between 8 and 15</font>  
> <font color=red>serialNo:length must be between 0 and 32</font>  
> <font color=red>merchantName:length must be between 0 and 64</font>  
> <font color=red>resellerName:length must be between 0 and 64</font>  
> <font color=red>modelName:length must be between 0 and 64</font>  
> <font color=red>location:length must be between 0 and 32</font>  

**Possible business codes**

|Business Code|Message|Description|
|:---|:---|:---|
|1759|Reseller doesn't exist|&nbsp;|
|1720|Merchant doesn't exist|&nbsp;|
|1937|Merchant is not belong to the given Reseller!|&nbsp;|
|1700|Model doesn't exist|&nbsp;|
|1800|Terminal not found|&nbsp;|
|1817|Terminal name is mandatory|&nbsp;|
|1818|Terminal name is too long|&nbsp;|
|1803|Terminal model is mandatory|&nbsp;|
|2326|Terminal reseller is mandatory|&nbsp;|
|1806|Terminal SN is too long|&nbsp;|
|2312|Terminal Serial No. accept alphanumeric|Alphanumeric and max length is 16|
|1807|Terminal model is too long|&nbsp;|
|1808|Terminal merchant is too long|&nbsp;|
|1809|Terminal location is too long|&nbsp;|
|2401|Terminal TID is invalid|&nbsp;|
|1929|The terminal is not inactive,model cannot be updated!|&nbsp;|
|1811|The terminal has already been activated,unable to update reseller.|&nbsp;|
|1928|The terminal is active,terminal SN cannot be updated!|&nbsp;|
|1804|Terminal merchant is mandatory|&nbsp;|
|1737|The associated merchant is not activate|&nbsp;|
|1813|Push task has already been added, unable to update model.|&nbsp;|
|1814|Push task has already been added,unable to update reseller.|&nbsp;|
|1828|TID already used|&nbsp;|
|2349|Terminal TID length is 8 to 15|&nbsp;|
|1737|The associated merchant is not activate|&nbsp;|
|1773|The associated reseller is not activate|&nbsp;|
|1740|Your terminal (SN:{0}) already exists|&nbsp;|
|2412|Your terminal SN not exist in asset|&nbsp;|


Note: The string {0} in the message of business code 1740 will be replaced by the SN in request.


### Activate a terminal

Activate terminal API allows the thirdparty system to activate a terminal by terminal id.


**API**

```
public Result<String> activateTerminal(Long terminalId) 
```

**Input parameter(s) description**

|Parameter Name|Type|Nullable|Description|
|:---|:---|:---|:---|
|terminalId|Long|false|The terminal id.|


**Sample codes**

```
TerminalApi terminalApi = new TerminalApi("https://api.whatspos.com/p-market-api", "RCA9MDH6YN3WSSGPW6TJ", "TUNLDZVZECHNKZ4FW07XFCKN2W0N8ZDEA5ENKZYN");
Result<String> result = terminalApi.activateTerminal(907560L);
```

**Client validation failed sample result(JSON formatted)**

```
{
	"businessCode": -1,
	"validationErrors": ["Parameter terminalId cannot be null and cannot be less than 1!"]
}
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
	"businessCode": 0
}
```

**Possible client validation errors**

> <font color=red>Parameter terminalId cannot be null and cannot be less than 1!</font>  

**Possible business codes**

|Business Code|Message|Description|
|:---|:---|:---|
|1800|Terminal not found|&nbsp;|
|1893|The terminal has already been activated!|&nbsp;|
|1802|Terminal SN is mandatory|&nbsp;|
|1804|Terminal merchant is mandatory|&nbsp;|
|1700|Model doesn't exist|&nbsp;|
|1713|The associated model is not activate|&nbsp;|


### Disable a terminal  

The disable terminal API allows the thirdparty system disable a terminal by terminal id.
If disable successfully there's not response content from remote server.

**API**

```
public Result<String> disableTerminal(Long terminalId)
```


**Input parameter(s) description**

|Parameter Name|Type|Nullable|Description|
|:---|:---|:---|:---|
|terminalId|Long|false|The terminal id.|

**Sample codes**

```
TerminalApi terminalApi = new TerminalApi("https://api.whatspos.com/p-market-api", "RCA9MDH6YN3WSSGPW6TJ", "TUNLDZVZECHNKZ4FW07XFCKN2W0N8ZDEA5ENKZYN");
Result<String> result = terminalApi.disableTerminal(907560L);
```

**Client validation failed sample result(JSON formatted)**

```
{
	"businessCode": -1,
	"validationErrors": ["Parameter terminalId cannot be null and cannot be less than 1!"]
}
```

**Server side validation failed sample result(JSON formatted)**

```
{
	"businessCode": 1888,
	"message": "The terminal is not active,unable to disable!"
}
```

**Successful sample result(JSON formatted)**

```
{
	"businessCode": 0
}
```

**Possible client validation errors**

> <font color=red>Parameter terminalId cannot be null and cannot be less than 1!</font>  

**Possible business codes**

|Business Code|Message|Description|
|:---|:---|:---|
|1800|Terminal not found|&nbsp;|
|1888|The terminal is not active,unable to disable!|&nbsp;|


### Move a terminal  

The move terminal API allows the thirdparty system move a terminal to another reseller and merchant by terminal id.
If the terminal is not applicable for the exist groups after move, the terminal will be removed from the groups. 
If move successfully there's not response content from remote server.

**API**

```
public Result<String> moveTerminal(Long terminalId, String resellerName, String merchantName)
```


**Input parameter(s) description**

|Parameter Name|Type|Nullable|Description|
|:---|:---|:---|:---|
|terminalId|Long|false|The terminal id.|
|resellerName|String|false|The target reseller name to move.|
|merchantName|String|false|The target merchant name to move.|


**Sample codes**

```
TerminalApi terminalApi = new TerminalApi("https://api.whatspos.com/p-market-api", "RCA9MDH6YN3WSSGPW6TJ", "TUNLDZVZECHNKZ4FW07XFCKN2W0N8ZDEA5ENKZYN");
Result<String> result = terminalApi.moveTerminal(907560L, "targetReseller", "targetMerchant");
```

**Client validation failed sample result(JSON formatted)**

```
{
	"businessCode": -1,
	"validationErrors": ["Parameter terminalId cannot be null and cannot be less than 1!"]
}
```

**Server side validation failed sample result(JSON formatted)**

```
{
	"businessCode": 2402,
	"message": "The terminal is not active or disabled!"
}
```

**Successful sample result(JSON formatted)**

```
{
	"businessCode": 0
}
```

**Possible client validation errors**

> <font color=red>Parameter terminalId cannot be null and cannot be less than 1!</font>  
> <font color=red>resellerName:may not be empty</font>  
> <font color=red>merchantName:may not be empty</font>  

**Possible business codes**

|Business Code|Message|Description|
|:---|:---|:---|
|1800|Terminal not found|&nbsp;|
|2402|The terminal is not active or disabled, unable to move!|&nbsp;|
|1759|Reseller doesn't exist|&nbsp;|
|1720|Merchant doesn't exist|&nbsp;|
|1937|Merchant is not belong to the given Reseller!|&nbsp;|
|1737|The associated merchant is not activate|&nbsp;|
|1773|The associated reseller is not activate|&nbsp;|


### Delete a terminal  

Delete terminal API allows the thirdparty system delete a exist terminal by terminal id.
If delete successfully there's no response content from remote server.

**API**

```
public Result<String> deleteTerminal(Long terminalId)
```


**Input parameter(s) description**

|Parameter Name|Type|Nullable|Description|
|:---|:---|:---|:---|
|terminalId|Long|false|The terminal id.|


**Sample codes**

```
TerminalApi terminalApi = new TerminalApi("https://api.whatspos.com/p-market-api", "RCA9MDH6YN3WSSGPW6TJ", "TUNLDZVZECHNKZ4FW07XFCKN2W0N8ZDEA5ENKZYN");
Result<String> result = terminalApi.deleteTerminal(907560L);
```

**Client validation failed sample result(JSON formatted)**

```
{
	"businessCode": -1,
	"validationErrors": ["Parameter terminalId cannot be null and cannot be less than 1!"]
}
```

**Server side validation failed sample result(JSON formatted)**

```
{
	"businessCode": 1877,
	"message": "The terminal is active,unable to delete!"
}
```

**Successful sample result(JSON formatted)**

```
{
	"businessCode": 0
}
```

**Possible client validation errors**

> <font color=red>Parameter terminalId cannot be null and cannot be less than 1!</font>  

**Possible business codes**

|Business Code|Message|Description|
|:---|:---|:---|
|1800|Terminal not found|&nbsp;|
|1877|The terminal is active,unable to delete!|&nbsp;|



### Batch add terminal to group  

Batch add terminal to group API allows the thirdparty system to add terminals to one or more groups.

**API**

```
public Result<String> batchAddTerminalToGroup(TerminalGroupRequest groupRequest)
```

**Input parameter(s) description**

| Parameter Name | Type                 | Nullable | Description                                                  |
| :------------- | :------------------- | :------- | :----------------------------------------------------------- |
| groupRequest   | TerminalGroupRequest | false    | add terminals to group request object. The structure shows below. |

Structure of class TerminalGroupRequest

| Property Name | Type      | Nullable | Description      |
| :------------ | :-------- | :------- | :--------------- |
| terminalIds   | Set\<Long\> | false    | terminal id list |
| groupIds      | Set\<Long\> | false    | group id list    |

**Sample codes**

```
TerminalApi terminalApi = new TerminalApi("https://api.whatspos.com/p-market-api", "RCA9MDH6YN3WSSGPW6TJ", "TUNLDZVZECHNKZ4FW07XFCKN2W0N8ZDEA5ENKZYN");
TerminalGroupRequest groupRequest = new TerminalGroupRequest();
Set<Long> terminalIds = new HashSet<>();
terminalIds.add(909744L);
terminalIds.add(909742L);
Set<Long> groupIds = new HashSet<>();
groupIds.add(16529L);
groupIds.add(16527L);
groupRequest.setTerminalIds(terminalIds);
groupRequest.setGroupIds(groupIds);
Result<String> result = terminalApi.batchAddTerminalToGroup(groupRequest);
```

**Client validation failed sample result(JSON formatted)**

```
{
	"businessCode": -1,
	"validationErrors": ["Parameter terminalGroupRequest cannot be null!"]
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

**Possible client validation errors**

> <font color=red>Parameter terminalGroupRequest cannot be null!</font>  

**Possible business codes**

| Business Code | Message                                                      | Description |
| :------------ | :----------------------------------------------------------- | :---------- |
| 1800          | Terminal not found                                           |             |
| 1810          | Terminal is not active                                       |             |
| 2150          | Terminal group not found                                     |             |
| 2163          | Terminal reseller mismatched                                 |             |
| 2164          | Terminal model mismatched                                    |             |
| 2167          | Terminal group exceeded the max terminal count limit, please create new terminal group to put the terminal |             |

### Update terminal configuration

Update terminal configuration like whether allow terminal replacement by API or input serial number on terminal.

**API**

```
public Result<String> updateTerminalConfig(Long terminalId, TerminalConfigUpdateRequest terminalConfigUpdateRequest)
```

**Input parameter(s) description**

| Parameter Name              | Type                        | Nullable | Description                                                  |
| :-------------------------- | :-------------------------- | :------- | :----------------------------------------------------------- |
| terminalId                  | Long                        | false    | Terminal's id.                                               |
| terminalConfigUpdateRequest | TerminalConfigUpdateRequest | false    | Update terminal config request object. The structure shows below. |

Structure of class TerminalRemoteConfigRequest

| Property Name    | Type    | Nullable | Description                                                  |
| :--------------- | :------ | :------- | :----------------------------------------------------------- |
| allowReplacement | Boolean | false    | Whether allow terminal replacement by API or input serial number on termial |

**Sample codes**

```
TerminalApi terminalApi = new TerminalApi("https://api.whatspos.com/p-market-api", "RCA9MDH6YN3WSSGPW6TJ", "TUNLDZVZECHNKZ4FW07XFCKN2W0N8ZDEA5ENKZYN");
Long terminalId = 909744L;
TerminalConfigUpdateRequest terminalConfigUpdateRequest = new TerminalConfigUpdateRequest();
terminalConfigUpdateRequest.setAllowReplacement(true);
Result<String> result = terminalApi.updateTerminalConfig(terminalId,terminalConfigUpdateRequest);
```

**Client side validation failed sample result(JSON formatted)**

```
{
	"businessCode": -1,
	"validationErrors": ["Parameter terminalId cannot be null and cannot be less than 1!"]
}
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
	"businessCode": 0
}
```

**Possible client validation errors**

> <font color=red>Parameter terminalId cannot be null and cannot be less than 1!</font>  

**Possible business codes**

| Business Code | Message                                                      | Description |
| :------------ | :----------------------------------------------------------- | :---------- |
| 1800          | Terminal not found                                           |             |
| 1838          | It is not allowed to change the terminal level "Terminal Replacement" status. please make sure reseller level terminal replacement settings are enabled. |             |

### Get terminal configuration

Get terminal configuration.

**API**

```
public Result<TerminalConfigDTO> getTerminalConfig(Long terminalId)
```

**Input parameter(s) description**

| Parameter Name | Type | Nullable | Description    |
| :------------- | :--- | :------- | :------------- |
| terminalId     | Long | false    | Terminal's id. |

**Sample codes**

```
TerminalApi terminalApi = new TerminalApi("https://api.whatspos.com/p-market-api", "RCA9MDH6YN3WSSGPW6TJ", "TUNLDZVZECHNKZ4FW07XFCKN2W0N8ZDEA5ENKZYN");
Result<TerminalConfigDTO> result = terminalApi.getTerminalConfig(909744L);
```

**Client side validation failed sample result(JSON formatted)**

```
{
	"businessCode": -1,
	"validationErrors": ["Parameter terminalId cannot be null and cannot be less than 1!"]
}
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
	"data": {
		"allowReplacement": true
	}
}
```

**Possible client validation errors**

> <font color=red>Parameter terminalId cannot be null and cannot be less than 1!</font>  

**Possible business codes**

| Business Code | Message                | Description |
| :------------ | :--------------------- | :---------- |
| 1800          | Terminal not found     |             |
| 1801          | Terminal doesn't exist |             |

### Get terminal PED information

Get terminal PED information by terminal id.

**API**

```
public Result<TerminalPedDTO> getTerminalPed(Long terminalId)
```

**Input parameter(s) description**

| Parameter Name | Type | Nullable | Description    |
| :------------- | :--- | :------- | :------------- |
| terminalId     | Long | false    | Terminal's id. |

**Sample codes**

```
TerminalApi terminalApi = new TerminalApi("https://api.whatspos.com/p-market-api", "RCA9MDH6YN3WSSGPW6TJ", "TUNLDZVZECHNKZ4FW07XFCKN2W0N8ZDEA5ENKZYN");
Result<TerminalPedDTO> result = terminalApi.getTerminalPed(909755L);
```

**Client side validation failed sample result(JSON formatted)**

```
{
	"businessCode": -1,
	"validationErrors": ["Parameter terminalId cannot be null and cannot be less than 1!"]
}
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
	"data": {
		"info": "[{\"SK/TPK\": [{\"kcv\": \"B03273DC0000000000\", \"slot\": \"2\"}]}, {\"TDES DUKPT\": [{\"ksi\": \"FFFF539SD99336FCA00001\", \"slot\": \"1\"}, {\"ksi\": \"FFFF98765D400CB600001\", \"slot\": \"3\"}, {\"ksi\": \"FFFF9876D5400CB200001\", \"slot\": \"4\"}, {\"ksi\": \"FFFF98765D43347000001\", \"slot\": \"5\"}, {\"ksi\": \"FFFF987654C92DA200001\", \"slot\": \"11\"}]}]"
	}
}
```

**Possible client validation errors**

> <font color=red>Parameter terminalId cannot be null and cannot be less than 1 !</font>  

**Possible business codes**

| Business Code | Message            | Description |
| :------------ | :----------------- | :---------- |
| 1800          | Terminal not found |             |



### Move terminal

Move a terminal to another reseller and merchant

**API**

```
public Result<String> moveTerminal(Long terminalId, String resellerName, String merchantName)
```

**Input parameter(s) description**

| Parameter Name | Type | Nullable | Description    |
| :------------- | :--- | :------- | :------------- |
| terminalId     | Long | false    | Terminal's id. |
| resellerName| String | false | The target reseller name the terminal move to|
| merchantName| String | false | The target merchant name the terminal move to|



**Sample codes**

```
TerminalApi terminalApi = new TerminalApi("https://api.whatspos.com/p-market-api", "RCA9MDH6YN3WSSGPW6TJ", "TUNLDZVZECHNKZ4FW07XFCKN2W0N8ZDEA5ENKZYN");
Result<String> result = terminalApi.moveTerminal(terminalId, "PAX", "6666");
```

**Client side validation failed sample result(JSON formatted)**

```
{
	"businessCode": -1,
	"validationErrors": ["Parameter resellerName is mandatory!"]
}
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
}
```

**Possible client validation errors**

> <font color=red>Parameter terminalId cannot be null and cannot be less than 1!</font>  
> <font color=red>Parameter resellerName is mandatory!</font>  
> <font color=red>Parameter merchantName is mandatory!</font> 

**Possible business codes**

| Business Code | Message            | Description |
| :------------ | :----------------- | :---------- |
| 1800          | Terminal not found |             |
| 1759          | Reseller doesn't exist |             |
| 1720          | Merchant doesn't exist |             |
| 1937          | Merchant is not belong to the given Reseller! |             |





### Push Command to Terminal

Push lock, unlock and restart command to terminal

**API**

```
public Result<String> pushCmdToTerminal(Long terminalId, TerminalPushCmd command)
```

**Input parameter(s) description**

| Parameter Name              | Type                        | Nullable | Description                     |
| :-------------------------- | :-------------------------- | :------- | :------------------------------ |
| terminalId                  | Long                        | false    | Terminal's id.                  |
| command | TerminalPushCmd | false    | Value can be TerminalPushCmd.Lock, TerminalPushCmd.Unlock and TerminalPushCmd.Restart |

**Sample codes**

```
TerminalApi terminalApi = new TerminalApi("https://api.whatspos.com/p-market-api", "RCA9MDH6YN3WSSGPW6TJ", "TUNLDZVZECHNKZ4FW07XFCKN2W0N8ZDEA5ENKZYN");
Result<String> result = terminalApi.pushCmdToTerminal(terminalId, TerminalPushCmd.Lock);
```

**Client side validation failed sample result(JSON formatted)**

```
{
	"businessCode": -1,
	"validationErrors": ["Parameter terminalId cannot be null and cannot be less than 1!"]
}
```

**Server side validation failed sample result(JSON formatted)**

```
{
	"businessCode": 1801,
	"message": "Terminal doesn't exist"
}
```

**Successful sample result(JSON formatted)**

```
{
	"businessCode": 0,
}
```

**Possible client validation errors**

> <font color=red>Parameter terminalId cannot be null and cannot be less than 1!</font>  
> <font color=red>Parameter command cannot be null!</font> 

**Possible business codes**

| Business Code | Message                                  | Description |
| :------------ | :--------------------------------------- | :---------- |
| 135           | Request parameter is missing or invalid  |             |
| 997           | Malformed or illegal request             |             |
| 1801          | Terminal doesn't exist                   |             |
| 1896          | The terminal is offline                  |             |
| 15094         | Terminal is locked                       |             |
| 15095         | Terminal has been unlocked               |             |
| 15096         | The terminal is being locked or unlocked |             |
| 15099         | Terminal restart in progress             |             |

