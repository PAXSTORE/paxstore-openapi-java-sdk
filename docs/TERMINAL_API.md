## Terminal APIs

Terminal APIs allow thirdparty system search terminals, get a terminal, create a terminal, update a terminal, activate a
terminal, disable a terminal and delete a exist terminal.

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
Note: This result of this API does not include the geolocation, firmware and installed application, if you need those 3
information in result please use another search terminals API

**API**

```
public Result<TerminalDTO> searchTerminal(int pageNo, int pageSize, TerminalSearchOrderBy orderBy, TerminalStatus status, String snNameTID)
```
```
public Result<TerminalDTO> searchTerminal(int pageNo, int pageSize, String resellerName, String merchantName, TerminalSearchOrderBy orderBy, TerminalStatus status, String snNameTID)
```

**Input parameter(s) description**

| Name         | Type                  | Nullable | Description                                                                                                                                                                                                       |
|:-------------|:----------------------|:---------|:------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| pageNo       | int                   | false    | page number, value must >=1                                                                                                                                                                                       |
| pageSize     | int                   | false    | the record number per page, range is 1 to 100                                                                                                                                                                     |
| orderBy      | TerminalSearchOrderBy | true     | the sort order by field name, value can be one of TerminalSearchOrderBy.Name, TerminalSearchOrderBy.Tid and TerminalSearchOrderBy.SerialNo. If pass null parameter the search result will order by id by default. |
| resellerName | String                | true     | search terminals under the reseller or it's sub-resellers                                                                                                                                                         |
| merchantName | String                | true     | search terminals under the reseller merchant                                                                                                                                                                      |
| status       | TerminalStatus        | true     | the terminal status<br/> the value can be TerminalStatus.Active, TerminalStatus.Inactive, TerminalStatus.Suspend                                                                                                  |
| snNameTID    | String                | true     | search by serial number,name and TID(exactly match)                                                                                                                                                               |

**Sample codes**

```
TerminalApi terminalApi = new TerminalApi("https://api.whatspos.com/p-market-api", "RCA9MDH6YN3WSSGPW6TJ", "TUNLDZVZECHNKZ4FW07XFCKN2W0N8ZDEA5ENKZYN");
Result<TerminalDTO> result = terminalApi.searchTerminal(1, 10, null, TerminalStatus.Active, "sn0101012225");
```
```
TerminalApi terminalApi = new TerminalApi("https://api.whatspos.com/p-market-api", "RCA9MDH6YN3WSSGPW6TJ", "TUNLDZVZECHNKZ4FW07XFCKN2W0N8ZDEA5ENKZYN");
Result<TerminalDTO> result = terminalApi.searchTerminal(1, 10, null, "New York", "KFC", TerminalStatus.Active, "sn0101012225");
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
			"remark":""
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
|createdDate|Date|The created time|
|updatedDate|Date|The updated time|
|lastActiveTime|Date|The activation time|
|lastAccessTime|Date|The last access time|
|location|String|The location|
|remark|String|The remark|
|geoLocation|TerminalLocationDTO| The geography location of the terminal|
|installedFirmware|TerminalInstalledFirmwareDTO| The installed firmware of the terminal|
|installedApks|List\<TerminalInstalledApkDTO\>| The installed applications of the terminal|
|terminalDetail|TerminalDetailDTO| The terminal detail information |
|terminalAccessoryList|List\<TerminalAccessoryDTO\>| The terminal accessory information list |

Structure of class TerminalLocationDTO

|Property Name|Type|Description|
|:---|:---|:---|
|lat|Double|The latitude of geography location|
|lng|Double|The longitude of geography location|

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

_**tips: Only return this information in getTerminal or getTerminalBySn**_

| Property Name    | Type   | Description                  |
|:-----------------| :----- |:-----------------------------|
| pn               | String | Terminal's pn                |
| osVersion        | String | Terminal's android version   |
| imei             | String | Terminal's IMEI              |
| screenResolution | String | Terminal's screen resolution |
| language         | String | Terminal's language          |
| ip               | String | Terminal's network ip        |
| timeZone         | String | Terminal's time zone         |
| macAddress       | String | Terminal's MAC address       |
| iccid            | String | Terminal's ICCID             |
| iccid2           | String | Terminal's ICCID2            |
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

This API is similar to the search terminals API, it has additional 3 parameters, the details please refer to the Input
parameter(s) description

**API**

```
public Result<TerminalDTO> searchTerminal(int pageNo, int pageSize, TerminalSearchOrderBy orderBy, TerminalStatus status, String snNameTID, boolean includeGeoLocation, boolean includeInstalledApks, boolean includeInstalledFirmware)
```
```
public Result<TerminalDTO> searchTerminal(int pageNo, int pageSize, String resellerName, String merchantName, TerminalSearchOrderBy orderBy, TerminalStatus status, String snNameTID, boolean includeGeoLocation, boolean includeInstalledApks, boolean includeInstalledFirmware)
```

**Input parameter(s) description**

| Name                     | Type | Nullable| Description                                                                                                                                                                                                       |
|:-------------------------| :----|:----|:------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| pageNo                   |int|false| page number, value must >=1                                                                                                                                                                                       |
| pageSize                 |int|false| the record number per page, range is 1 to 100                                                                                                                                                                     |
| orderBy                  |TerminalSearchOrderBy|true| the sort order by field name, value can be one of TerminalSearchOrderBy.Name, TerminalSearchOrderBy.Tid and TerminalSearchOrderBy.SerialNo. If pass null parameter the search result will order by id by default. |
| resellerName             |String|true| search terminals under the reseller or it's sub-resellers                                                                                                                                                         |
| merchantName             |String|true| search terminals under the reseller merchant                                                                                                                                                                      |
| status                   |TerminalStatus|true| the terminal status<br/> the value can be TerminalStatus.Active, TerminalStatus.Inactive, TerminalStatus.Suspend                                                                                                  |
| snNameTID                |String|true| search by serial number,name and TID                                                                                                                                                                              |
| includeGeoLocation       |boolean|true| whether to include geo location information in search result                                                                                                                                                      |
| includeInstalledApks     |boolean|true| whether to include install applications in search result                                                                                                                                                          |
| includeInstalledFirmware |boolean|true| whether to include the firmware version of the terminal in search result                                                                                                                                          |

**Sample codes**

```
TerminalApi terminalApi = new TerminalApi("https://api.whatspos.com/p-market-api", "RCA9MDH6YN3WSSGPW6TJ", "TUNLDZVZECHNKZ4FW07XFCKN2W0N8ZDEA5ENKZYN");
Result<TerminalDTO> result = terminalApi.searchTerminal(1, 10, null, TerminalStatus.Active, "sn0101012225", true, true, true);
```
```
TerminalApi terminalApi = new TerminalApi("https://api.whatspos.com/p-market-api", "RCA9MDH6YN3WSSGPW6TJ", "TUNLDZVZECHNKZ4FW07XFCKN2W0N8ZDEA5ENKZYN");
Result<TerminalDTO> result = terminalApi.searchTerminal(1, 10, null, "New York", "KFC", TerminalStatus.Active, "sn0101012225", true, true, true);
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
			"remark":"",
			"createdDate": 1725591969000,
      "updatedDate": 1725591969000,
      "lastActiveTime": 1725591969000,
      "lastAccessTime": 1725777409853,
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

The get terminal API allows the thirdparty system get a terminal by terminal id. If the termianl does not exist the data
field in result is null.

**API**

```
public Result<TerminalDTO> getTerminal(Long terminalId);
public Result<TerminalDTO> getTerminal(Long terminalId, boolean includeDetailInfoList);
public Result<TerminalDTO> getTerminal(Long terminalId, boolean includeDetailInfoList, boolean includeInstalledApks);
public Result<TerminalDTO> getTerminal(Long terminalId, boolean includeDetailInfoList, boolean includeInstalledApks, boolean includeInstalledFirmware);
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
//includeDetailInfoList, whether to return Detail Info and Accessory List 
Result<TerminalDTO> result = terminalApi.getTerminal(908627L,true);
//includeInstalledApks
Result<TerminalDTO> result = terminalApi.getTerminal(908627L,false, true);
//includeInstalledFirmware
Result<TerminalDTO> result = terminalApi.getTerminal(1654906985381944L,false, false, true);
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
		"remark":"",
		"createdDate": 1552536099000,
		"updatedDate": 1725591969000,
		"lastActiveTime": 1552536095000,
        "lastAccessTime": 1725777409853
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
		"remark":"",
		"createdDate": 1552536099000,
    "updatedDate": 1725591969000,
		"lastActiveTime": 1552536095000,
    "lastAccessTime": 1725777409853,
		"terminalDetail": {
			"pn": "A920-3AW-RD5-21EU",
			"screenResolution": "720px * 1280px",
			"language": "English",
			"ip": "192.168.5.213",
			"timeZone": "GMT +08:00",
      "macAddress": "A4:D4:B2:4C:14:FE",
			"iccid": "89860322472007320056",
			"iccid2": "80000322472007326856"
		},
		"terminalAccessory": [{
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
		},{
            "basic": [{
                "name": "SN",
                "content": "1140073438"
            }, {
                "name": "Q20",
                "content": "Q20"
            }, {
                "name": "OS",
                "content": "Prolin2.7.2"
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
                "content": "Q20_M06_P01"
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
                "content": "256MB"
            }, {
                "name": "FREEFLASH",
                "content": "88.52M"
            }, {
                "name": "RAM",
                "content": "325.50MB"
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
                "name": "NeptuneLite",
                "content": "2.01.01"
            }],
            "history": [{
                "name": "MAINAPP/sys_param.p",
                "status": "Success",
                "installTime": 1651113398000,
                "fileSize": 135,
                "fileType": "Private file",
                "source": "Remote Upgrade"
            }, {
                "name": "MAINAPP/sys_cap.p",
                "status": "Success",
                "installTime": 16511133980000,
                "fileSize": 234,
                "fileType": "Private file",
                "source": "Remote Upgrade"
            }, {
                "name": "browser.aip",
                "status": "Success",
                "installTime": 16511133980000,
                "fileSize": 2165940,
                "fileType": "Application",
                "source": "Local Upgrade"
            }, {
                "name": "prolin-pelican-2.7.66.8833R_SIG.zip",
                "version": "2.00.10",
                "status": "Success",
                "installTime": 1651113948000,
                "fileSize": 17890812,
                "fileType": "Firmware",
                "source": "Local Upgrade"
            }]
        }]
	}
}
```

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
		"remark":"",
		"createdDate": 1552536099000,
		"updatedDate": 1725591969000,
		"lastActiveTime": 1552536095000,
		"lastAccessTime": 1725777409853,
		 "installedApks": [
      {
        "appName": "cloudMsg1",
        "packageName": "app1.clouldmsg.com.cloudmsg1",
        "versionName": "1.0",
        "versionCode": 1,
        "installTime": 1541137822000
      },
      {
        "appName": "sdkDemo",
        "packageName": "com.pax.android.demoapp",
        "versionName": "7.2.3",
        "versionCode": 135,
        "installTime": 1596416582000
      }
    ]
	}
}
```

```
{
    "businessCode": 0,
    "data": {
        "id": 1654906985381944,
        "name": "TESTSYNC1",
        "tid": "LNFAJ1HC",
        "serialNo": "TESTCLIENT",
        "status": "A",
        "merchantName": "gxtest",
        "modelName": "A970",
        "resellerName": "shifan",
        "location": "",
        "createdDate": 1735805945000,
        "updatedDate": 1735805945000,
        "lastActiveTime": 1735805945000,
        "lastAccessTime": 1742458233386,
        "installedFirmware": {
            "firmwareName": "A920Pro_PayDroid_10.0.0_Sagittarius_V1.01_20241205",
            "installTime": 1708041600000
        }
    },
    "rateLimit": "100",
    "rateLimitRemain": "98",
    "rateLimitReset": "1743041736048"
}
```



The type of data in result is TerminalDTO. Its structure already shows in search terminal API.

**Possible validation errors**

> <font color=red>Parameter terminalId cannot be null and cannot be less than 1!</font>

**Possible business codes**

|Business Code|Message|Description|
|:---|:---|:---|
|1801|Terminal doesn't exist|&nbsp;|

### Get a terminal by serial no

The get terminal API allows the thirdparty system get a terminal by terminal serial no. If the termianl does not exist the data
field in result is null.

**API**

```
public Result<TerminalDTO> getTerminalBySn(String serialNo)
public Result<TerminalDTO> getTerminalBySn(String serialNo, boolean includeDetailInfoList)
public Result<TerminalDTO> getTerminalBySn(String serialNo, boolean includeDetailInfoList, boolean includeInstalledApks)
public Result<TerminalDTO> getTerminalBySn(String serialNo, boolean includeDetailInfoList, boolean includeInstalledApks, boolean includeInstalledFirmware)
```

**Input parameter(s) description**

| Parameter Name | Type   |Nullable| Description             |
|:---------------|:-------|:---|:------------------------|
| serialNo       | String |false| The terminal serial no. |

**Sample codes**

```
TerminalApi terminalApi = new TerminalApi("https://api.whatspos.com/p-market-api", "RCA9MDH6YN3WSSGPW6TJ", "TUNLDZVZECHNKZ4FW07XFCKN2W0N8ZDEA5ENKZYN");
//simple info
Result<TerminalDTO> result = terminalApi.getTerminalBySn("SN6132522");
//includeDetailInfoList, whether to return Detail Info and Accessory List 
Result<TerminalDTO> result = terminalApi.getTerminalBySn("SN6132522",true);
//includeInstalledApks
Result<TerminalDTO> result = terminalApi.getTerminalBySn("SN6132522",false,true);
//includeInstalledFirmware
Result<TerminalDTO> result = terminalApi.getTerminalBySn("TESTCLIENT",false,false, true);
```

**Client side validation failed sample result(JSON formatted)**

```
{
	"businessCode": -1,
	"validationErrors": ["Parameter serial no cannot be null!"]
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
		"remark":"",
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
		"remark":"",
		"createdDate": 1552536099000,
		"lastActiveTime": 1552536095000,
		"terminalDetail": {
			"pn": "A920-3AW-RD5-21EU",
			"screenResolution": "720px * 1280px",
			"language": "English",
			"ip": "192.168.5.213",
			"timeZone": "GMT +08:00",
			"macAddress": "A4:D4:B2:4C:14:FE",
			"iccid": "89860322472007320056",
			"iccid2": "80000322472007326856"
		},
		"terminalAccessory": [{
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
		},{
            "basic": [{
                "name": "SN",
                "content": "1140073438"
            }, {
                "name": "Q20",
                "content": "Q20"
            }, {
                "name": "OS",
                "content": "Prolin2.7.2"
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
                "content": "Q20_M06_P01"
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
                "content": "256MB"
            }, {
                "name": "FREEFLASH",
                "content": "88.52M"
            }, {
                "name": "RAM",
                "content": "325.50MB"
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
                "name": "NeptuneLite",
                "content": "2.01.01"
            }],
            "history": [{
                "name": "MAINAPP/sys_param.p",
                "status": "Success",
                "installTime": 1651113398000,
                "fileSize": 135,
                "fileType": "Private file",
                "source": "Remote Upgrade"
            }, {
                "name": "MAINAPP/sys_cap.p",
                "status": "Success",
                "installTime": 16511133980000,
                "fileSize": 234,
                "fileType": "Private file",
                "source": "Remote Upgrade"
            }, {
                "name": "browser.aip",
                "status": "Success",
                "installTime": 16511133980000,
                "fileSize": 2165940,
                "fileType": "Application",
                "source": "Local Upgrade"
            }, {
                "name": "prolin-pelican-2.7.66.8833R_SIG.zip",
                "version": "2.00.10",
                "status": "Success",
                "installTime": 1651113948000,
                "fileSize": 17890812,
                "fileType": "Firmware",
                "source": "Local Upgrade"
            }]
        }]
	}
}
```

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
		"remark":"",
		"createdDate": 1552536099000,
		"lastActiveTime": 1552536095000,
		 "installedApks": [
      {
        "appName": "cloudMsg1",
        "packageName": "app1.clouldmsg.com.cloudmsg1",
        "versionName": "1.0",
        "versionCode": 1,
        "installTime": 1541137822000
      },
      {
        "appName": "sdkDemo",
        "packageName": "com.pax.android.demoapp",
        "versionName": "7.2.3",
        "versionCode": 135,
        "installTime": 1596416582000
      }
    ]
	}
}
```

```
{
    "businessCode": 0,
    "data": {
        "id": 1654906985381944,
        "name": "TESTSYNC1",
        "tid": "LNFAJ1HC",
        "serialNo": "TESTCLIENT",
        "status": "A",
        "merchantName": "gxtest",
        "modelName": "A970",
        "resellerName": "shifan",
        "location": "",
        "createdDate": 1735805945000,
        "updatedDate": 1735805945000,
        "lastActiveTime": 1735805945000,
        "lastAccessTime": 1742458233386,
        "installedFirmware": {
            "firmwareName": "A920Pro_PayDroid_10.0.0_Sagittarius_V1.01_20241205",
            "installTime": 1708041600000
        }
    },
    "rateLimit": "100",
    "rateLimitRemain": "98",
    "rateLimitReset": "1743041736048"
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
|modelName|String|true|The model name of terminal. Max length is 64.|
|location|String|true|The location of terminal, max length is 64.|
|remark|String|true|The remark of terminal, max length is 500.|
|status|String|true|Status of terminal, value can be one of A(Active) and P(Pendding). If status is null the initial status is P(Pendding) when creating.|

**Sample codes**

```
TerminalApi terminalApi = new TerminalApi("https://api.whatspos.com/p-market-api", "RCA9MDH6YN3WSSGPW6TJ", "TUNLDZVZECHNKZ4FW07XFCKN2W0N8ZDEA5ENKZYN");
TerminalCreateRequest createReq = new TerminalCreateRequest();
createReq.setName("KFC-TML-001");
createReq.setMerchantName("KFC");
createReq.setResellerName("New York");
createReq.setLocation("USA");
createReq.setRemark("Terminal Remark");
createReq.setSerialNo("sn0101012236");
createReq.setModelName("A920");
createReq.setStatus(TerminalStatus.Active);
Result<TerminalDTO> result = terminalApi.createTerminal(createReq);
```

**Server side validation failed sample result(JSON formatted)**

```
{
	"businessCode": 2321,
	"message": "Terminal Serial No.sn0101012237 has been registered"
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
		"location": "USA",
		"remark": "Terminal Remark"
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
|2321|Terminal Serial No.{0} has been registered||
|2331|Terminal Serial No.{0} has been registered in the developer sandbox||
|2332|Terminal Serial No.{0} is not available, please contact the support team for further assistance||
|2349|Terminal TID length is 8 to 15|&nbsp;|
|1737|The associated merchant is not activate|&nbsp;|
|1773|The associated reseller is not activate|&nbsp;|
|2412|Your terminal SN not exist in asset|&nbsp;|
|9200|Terminal upper limit exceeded||

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
|tid|String|true|The tid of terminal. If it is empty system will generate a tid when creating. And the length range is from 8 to 16.|
|serialNo|String|true|The serial number of terminal. If the status is active the serial number is mandatory.|
|merchantName|String|true|The merchant of terminal belongs to. If the initial is active then merchantName is mandatory. The max length is 64. Make sure the merchant belongs to the given reseller|
|resellerName|String|false|The reseller of terminal belongs to. Max length is 64.|
|modelName|String|false|The model name of terminal. Max length is 64.|
|location|String|true|The location of terminal, max length is 64.|
|remark|String|true|The remark of terminal, max length is 500.|

**Sample codes**

```
TerminalApi terminalApi = new TerminalApi("https://api.whatspos.com/p-market-api", "RCA9MDH6YN3WSSGPW6TJ", "TUNLDZVZECHNKZ4FW07XFCKN2W0N8ZDEA5ENKZYN");
TerminalUpdateRequest updateReq = new TerminalUpdateRequest();
updateReq.setName("KFC-TML-001");
updateReq.setModelName("A920");
updateReq.setLocation("CN");
updateReq.setRemark("Terminal Remark");
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
		"location": "CN",
		"remark":"Termianl Remark"
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
|1700|Model doesn't exist||
|1713|The associated model is not activate||
|1720|Merchant doesn't exist||
|1737|The associated merchant is not activate||
|1759|Reseller doesn't exist|&nbsp;|
|1773|The associated reseller is not activate||
|1800|Terminal not found|&nbsp;|
|1802|Terminal SN is mandatory||
|1803|Terminal model is mandatory||
|1804|Terminal merchant is mandatory||
|1806|Terminal SN is too long||
|1807|Terminal model is too long||
|1808|Terminal merchant is too long||
|1809|Terminal location is too long||
|1811|The terminal has already been activated,unable to update reseller.||
|1813|Push task has already been added, unable to update model.||
|1814|Push task has already been added,unable to update reseller.||
|1817|Terminal name is mandatory||
|1818|Terminal name is too long||
|1828|TID already used||
|1859|Model {0} is not available, please contact administrator||
|1898|TID cannot be updated when terminal is active||
|1912|Terminal is disabled, TID cannot be updated!||
|1928|The terminal is active,terminal SN cannot be updated!||
|1929|The terminal is not inactive,model cannot be updated!||
|1937|Merchant is not belong to the given Reseller!|&nbsp;|
|2312|Terminal Serial No. accept alphanumeric|&nbsp;Alphanumeric and max length is 16|
|2321|Terminal Serial No.{0} has been registered||
|2331|Terminal Serial No.{0} has been registered in the developer sandbox||
|2332|Terminal Serial No.{0} is not available, please contact the support team for further assistance||
|2326|Terminal reseller is mandatory|&nbsp;|
|2349|Terminal TID length is 8 to 15|&nbsp;|
|2401|Terminal TID is invalid|&nbsp;|
|2412|Your terminal SN not exist in asset|&nbsp;|

### Update a terminal by serial no

Update terminal API allows the thirdparty system update a exist terminal by serial no.

**API**

```
public Result<TerminalDTO> updateTerminalBySn(String serialNo, TerminalUpdateRequest terminalUpdateRequest)
```

**Input parameter(s) description**

|Parameter Name| Type                  |Nullable| Description                                                |
|:---|:----------------------|:---|:-----------------------------------------------------------|
|serialNo| String                |false| Terminal's serial no.                                      |
|terminalUpdateRequest| TerminalUpdateRequest |false| Update terminal request object. The structure shows below. |

Structure of class TerminalUpdateRequest

|Property Name|Type|Nullable|Description|
|:---|:---|:---|:---|
|name|String|false|The name of terminal, max length is 64.|
|tid|String|true|The tid of terminal. If it is empty system will generate a tid when creating. And the length range is from 8 to 16.|
|serialNo|String|true|The serial number of terminal. If the status is active the serial number is mandatory.|
|merchantName|String|true|The merchant of terminal belongs to. If the initial is active then merchantName is mandatory. The max length is 64. Make sure the merchant belongs to the given reseller|
|resellerName|String|false|The reseller of terminal belongs to. Max length is 64.|
|modelName|String|false|The model name of terminal. Max length is 64.|
|location|String|true|The location of terminal, max length is 64.|
|remark|String|true|The remark of terminal, max length is 500.|

**Sample codes**

```
TerminalApi terminalApi = new TerminalApi("https://api.whatspos.com/p-market-api", "RCA9MDH6YN3WSSGPW6TJ", "TUNLDZVZECHNKZ4FW07XFCKN2W0N8ZDEA5ENKZYN");
TerminalUpdateRequest updateReq = new TerminalUpdateRequest();
updateReq.setName("KFC-TML-001");
updateReq.setModelName("A920");
updateReq.setLocation("CN");
updateReq.setRemark("Terminal Remark");
updateReq.setSerialNo("sn010101211226");
updateReq.setResellerName("New York");
updateReq.setMerchantName("KFC");
//Result<TerminalDTO> result = terminalApi.updateTerminalBySn("sn010101211226", updateReq);

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
		"location": "CN",
		"remark":"Termianl Remark"
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
|1700|Model doesn't exist||
|1713|The associated model is not activate||
|1720|Merchant doesn't exist||
|1737|The associated merchant is not activate||
|1759|Reseller doesn't exist|&nbsp;|
|1773|The associated reseller is not activate||
|1800|Terminal not found|&nbsp;|
|1802|Terminal SN is mandatory||
|1803|Terminal model is mandatory||
|1804|Terminal merchant is mandatory||
|1806|Terminal SN is too long||
|1807|Terminal model is too long||
|1808|Terminal merchant is too long||
|1809|Terminal location is too long||
|1811|The terminal has already been activated,unable to update reseller.||
|1813|Push task has already been added, unable to update model.||
|1814|Push task has already been added,unable to update reseller.||
|1817|Terminal name is mandatory||
|1818|Terminal name is too long||
|1828|TID already used||
|1859|Model {0} is not available, please contact administrator||
|1898|TID cannot be updated when terminal is active||
|1912|Terminal is disabled, TID cannot be updated!||
|1928|The terminal is active,terminal SN cannot be updated!||
|1929|The terminal is not inactive,model cannot be updated!||
|1937|Merchant is not belong to the given Reseller!|&nbsp;|
|2312|Terminal Serial No. accept alphanumeric|&nbsp;Alphanumeric and max length is 16|
|2321|Terminal Serial No.{0} already exists|&nbsp;|
|2326|Terminal reseller is mandatory|&nbsp;|
|2349|Terminal TID length is 8 to 15|&nbsp;|
|2350|Terminal Serial No.{0} already exists in other marketplace sandbox|&nbsp;|
|2401|Terminal TID is invalid|&nbsp;|
|2412|Your terminal SN not exist in asset|&nbsp;|

### Copy a terminal

Copy terminal API allows the thirdparty system to copy a terminal by  origin terminal id.

**API**

```
public Result<TerminalDTO> copyTerminal(TerminalCopyRequest terminalCopyRequest)
```

**Input parameter(s) description**

| Parameter Name      | Type                | Nullable | Description                                              |
| :------------------ | :------------------ | :------- | :------------------------------------------------------- |
| terminalCopyRequest | TerminalCopyRequest | false    | copy terminal request object. The structure shows below. |

Structure of class TerminalCopyRequest

| Property Name | Type   | Nullable | Description                                                                                                                                                                                        |
| :------------ | :----- | :------- |:---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| terminalId    | Long   | false    | The  id of terminal                                                                                                                                                                                |
| name          | String | false    | The name of terminal, max length is 64.                                                                                                                                                            |
| tid           | String | true     | The tid of terminal. If it is empty system will generate a tid when creating. And the length range is from 8 to 16.                                                                                |
| serialNo      | String | true     | The serial number of terminal. If the status is active the serial number is mandatory.                                                                                                             |
| status        | String | false    | Status of terminal, value can be one of A(Active) and P(Pendding). A is for Active and P is for Pendding |

**Sample codes**

```
TerminalApi terminalApi = new TerminalApi("https://api.whatspos.com/p-market-api", "RCA9MDH6YN3WSSGPW6TJ", "TUNLDZVZECHNKZ4FW07XFCKN2W0N8ZDEA5ENKZYN");
TerminalCopyRequest copyRequest = new TerminalCopyRequest();
copyRequest.setTerminalId(13453434534L);
copyRequest.setName("COPY_FROM_13453434534");
copyRequest.setSerialNo("TJ0000002");
copyRequest.setStatus(TerminalStatus.Active);
Result<TerminalDTO> copyResult = terminalApi.copyTerminal(copyRequest);
logger.debug("Result of copy terminal: {}",copyResult.toString());
Assert.assertTrue(copyResult.getBusinessCode() == 0);
```

**Client validation failed sample result(JSON formatted)**

```
{
	"businessCode": -1,
	"validationErrors": ["Parameter terminalCopyRequest cannot be null!"]
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
    "id": 1510297435111460,
    "name": "COPY_FROM_909822",
    "tid": "HSZG4FTS",
    "serialNo": "TJ00001002",
    "status": "A",
    "merchantName": "TESTpukMerchant",
    "modelName": "A930",
    "resellerName": "shifan",
    "location": "",
    "remark": "324324223",
    "createdDate": 1666850737890,
    "lastActiveTime": 1666850736044
	}
}
```

**Possible client validation errors**

> <font color=red>Parameter terminalCopyRequest cannot be null!</font>  

**Possible business codes**

| Business Code | Message                       | Description |
| :------------ | :---------------------------- | :---------- |
| 1800          | Terminal not found            |             |
| 1817          | Terminal name is mandatory    |             |
| 1818          | Terminal name is too long     |             |
| 1828          | TID already used              |             |
| 9200          | Terminal upper limit exceeded |             |

## Copy a terminal by serial no

Copy terminal API allows the thirdparty system to copy a terminal by  origin terminal serial no.

**API**

```
public Result<TerminalDTO> copyTerminalBySn(TerminalCopyRequest terminalCopyRequest)
```

**Input parameter(s) description**

| Parameter Name      | Type                | Nullable | Description                                              |
| :------------------ | :------------------ | :------- | :------------------------------------------------------- |
| terminalCopyRequest | TerminalCopyRequest | false    | copy terminal request object. The structure shows below. |

Structure of class TerminalCopyRequest

| Property Name    | Type   | Nullable | Description                                                                                                         |
|:-----------------| :----- |:---------|:--------------------------------------------------------------------------------------------------------------------|
| name             | String | false    | The name of terminal, max length is 64.                                                                             |
| tid              | String | true     | The tid of terminal. If it is empty system will generate a tid when creating. And the length range is from 8 to 16. |
| sourceSerialNo   | String | false    | The serial number of source terminal. The serial number is mandatory.                                               |
| serialNo         | String | false    | The serial number of terminal. If the status is active the serial number is mandatory.                              |
| status           | String | false    | Status of terminal, value can be one of A(Active) and P(Pendding). A is for Active and P is for Pendding            |

**Sample codes**

```
TerminalApi terminalApi = new TerminalApi("https://api.whatspos.com/p-market-api", "RCA9MDH6YN3WSSGPW6TJ", "TUNLDZVZECHNKZ4FW07XFCKN2W0N8ZDEA5ENKZYN");
TerminalCopyRequest copyRequest = new TerminalCopyRequest();
copyRequest.setTerminalId(13453434534L);
copyRequest.setName("COPY_FROM_13453434534");
copyRequest.setSourceSerialNo("TJ0000001");
copyRequest.setSerialNo("TJ0000002");
copyRequest.setStatus(TerminalStatus.Active);
Result<TerminalDTO> copyResult = terminalApi.copyTerminalBySn(copyRequest);
logger.debug("Result of copy terminal: {}",copyResult.toString());
Assert.assertTrue(copyResult.getBusinessCode() == 0);
```

**Client validation failed sample result(JSON formatted)**

```
{
	"businessCode": -1,
	"validationErrors": ["Parameter terminalCopyRequest cannot be null!"]
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
    "id": 1510297435111460,
    "name": "COPY_FROM_909822",
    "tid": "HSZG4FTS",
    "serialNo": "TJ00001002",
    "status": "A",
    "merchantName": "TESTpukMerchant",
    "modelName": "A930",
    "resellerName": "shifan",
    "location": "",
    "remark": "324324223",
    "createdDate": 1666850737890,
    "lastActiveTime": 1666850736044
	}
}
```

**Possible client validation errors**

> <font color=red>Parameter terminalCopyRequest cannot be null!</font>

**Possible business codes**

| Business Code | Message                       | Description |
| :------------ | :---------------------------- | :---------- |
| 1800          | Terminal not found            |             |
| 1817          | Terminal name is mandatory    |             |
| 1818          | Terminal name is too long     |             |
| 1828          | TID already used              |             |
| 9200          | Terminal upper limit exceeded |             |


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

### Activate a terminal by serial no

Activate terminal API allows the thirdparty system to activate a terminal by serial no.

**API**

```
public Result<String> activateTerminalBySn(String serialNo)
```

**Input parameter(s) description**

|Parameter Name| Type   |Nullable| Description             |
|:---|:-------|:---|:------------------------|
|serialNo| String |false| The terminal serial no. |

**Sample codes**

```
TerminalApi terminalApi = new TerminalApi("https://api.whatspos.com/p-market-api", "RCA9MDH6YN3WSSGPW6TJ", "TUNLDZVZECHNKZ4FW07XFCKN2W0N8ZDEA5ENKZYN");
Result<String> result = terminalApi.activateTerminalBySn("SN6132522");
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

### Disable a terminal by serial no

The disable terminal API allows the thirdparty system disable a terminal by serial no.
If disable successfully there's not response content from remote server.

**API**

```
public Result<String> disableTerminalBySn(String serialNo) 
```

**Input parameter(s) description**

| Parameter Name | Type   |Nullable|Description|
|:---------------|:-------|:---|:---|
| serialNo       | String |false|The terminal serial no.|

**Sample codes**

```
TerminalApi terminalApi = new TerminalApi("https://api.whatspos.com/p-market-api", "RCA9MDH6YN3WSSGPW6TJ", "TUNLDZVZECHNKZ4FW07XFCKN2W0N8ZDEA5ENKZYN");
Result<String> result = terminalApi.disableTerminalBySn("TJ00001002");
```

**Client validation failed sample result(JSON formatted)**

```
{
	"businessCode": -1,
	"validationErrors": ["Parameter terminal serial no cannot be null!"]
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


### Move a terminal by serial no

The move terminal API allows the thirdparty system move a terminal to another reseller and merchant by terminal serialNo.
If the terminal is not applicable for the exist groups after move, the terminal will be removed from the groups.
If move successfully there's not response content from remote server.

**API**

```
public Result<String> moveTerminalBySn(String serialNo, String resellerName, String merchantName)
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
Result<String> result = terminalApi.moveTerminalBySn("SN6132522", "targetReseller", "targetMerchant");
```

**Client validation failed sample result(JSON formatted)**

```
{
	"businessCode": -1,
	"validationErrors": ["Parameter terminal serialNo cannot be null!"]
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

### Delete a terminal by serial no

Delete terminal API allows the thirdparty system delete a exist terminal by terminal serial no.
If delete successfully there's no response content from remote server.

**API**

```
public Result<String> deleteTerminalBySn(String serialNo) 
```

**Input parameter(s) description**

| Parameter Name | Type   |Nullable| Description            |
|:---------------|:-------|:---|:-----------------------|
| serialNo       | String |false| The terminal serialNo. |

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

### Batch add terminal to group by serialNo

Batch add terminal to group API allows the thirdparty system to add terminals to one or more groups.

**API**

```
public Result<String> batchAddTerminalToGroupBySn(TerminalSnGroupRequest groupRequest)
```

**Input parameter(s) description**

| Parameter Name | Type                 | Nullable | Description                                                  |
| :------------- | :------------------- | :------- | :----------------------------------------------------------- |
| groupRequest   | TerminalGroupRequest | false    | add terminals to group request object. The structure shows below. |

Structure of class TerminalGroupRequest

| Property Name | Type      | Nullable | Description            |
| :------------ | :-------- | :------- |:-----------------------|
| serialNos   | Set\<Long\> | false    | terminal serialNo list |
| groupIds      | Set\<Long\> | false    | group id list          |
**Sample codes**

```
TerminalApi terminalApi = new TerminalApi("https://api.whatspos.com/p-market-api", "RCA9MDH6YN3WSSGPW6TJ", "TUNLDZVZECHNKZ4FW07XFCKN2W0N8ZDEA5ENKZYN");
TerminalSnGroupRequest groupRequest = new TerminalSnGroupRequest();
Set<String> serialNoList = new HashSet<>();
serialNoList.add("SN6132522");
serialNoList.add("SN6685133");
Set<Long> groupIds = new HashSet<>();
groupIds.add(16529L);
groupIds.add(16527L);
groupRequest.setSerialNos(serialNoList);
groupRequest.setGroupIds(groupIds);
Result<String> result = terminalApi.batchAddTerminalToGroupBySn(groupRequest);
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

| Parameter Name                     | Type                        | Nullable | Description                                                                            |
|:-----------------------------------| :-------------------------- | :------- |:---------------------------------------------------------------------------------------|
| terminalId                         | Long                        | false    | Terminal's id.                                                                         |
| terminalConfigUpdateRequest        | TerminalConfigUpdateRequest | false    | Update terminal config request object. The structure shows below.                      |
| terminalReplacementUpdateRequest   | TerminalReplacementUpdateRequest | false    | Update terminal replacement config request object. The structure shows below.          |
| terminalTimeZoneUpdateRequest      | TerminalTimeZoneUpdateRequest | false    | Update terminal time zone config request object. The structure shows below.            |
| terminalLanguageUpdateRequest      | TerminalLanguageUpdateRequest | false    | Update terminal language config request object. The structure shows below.             |
| terminalWifiUpdateRequest          | TerminalWifiUpdateRequest | false    | Update terminal wifi config request array object. The structure shows below.           |
| terminalApnUpdateRequest           | TerminalApnUpdateRequest | false    | Update terminal apn config request array object. The structure shows below.            |
| terminalWifiBlackListUpdateRequest | TerminalWifiBlackListUpdateRequest | false    | Update terminal wifi blackList config request array object. The structure shows below. |

Structure of class TerminalReplacementUpdateRequest

| Property Name           | Type    | Nullable | Description                                                                                                                                    |
|:------------------------|:--------|:---------|:-----------------------------------------------------------------------------------------------------------------------------------------------|
| allowReplacement        | Boolean | false    | Whether allow terminal replacement by API or input serial number on terminal                                                                   |

Structure of class TerminalTimeZoneUpdateRequest

| Property Name           | Type    | Nullable | Description                                                   |
|:------------------------|:--------|:---------|:--------------------------------------------------------------|
| automaticTimezoneEnable | Boolean | true     | Enable to use the network-provided time zone                  |
| timeZone                | String  | true     | The terminal time zone [Time Zone](APPENDIX.md#time-zone) |

Structure of class TerminalLanguageUpdateRequest

| Property Name | Type   | Nullable | Description                                                                                                       |
|:--------------|:-------|:---------|:------------------------------------------------------------------------------------------------------------------|
| language      | String | false    | language symbol, English US is en-us, Japanese is ja-jp, Chinese is zh-cn, Spanish is es-es, Russian is ru-ru etc |

Structure of class TerminalWifiUpdateRequest

| Property Name | Type    | Nullable | Description                                   |
|:--------------|:--------|:---------|:----------------------------------------------|
| wifi          | String | true     | Array JSON string of WifiConfig Class as bellow |

Structure of class WifiConfig

| Property Name | Type    | Nullable | Description                                                  |
|:--------------|:--------|:---------|:-------------------------------------------------------------|
| ssid          | String  | false    | Wi-Fi Account                                                |
| cipherType    | Integer | false    | Security setting, 0 is None,1 is WEP, 2 is WPA/WPA2 PSK      |
| password      | String  | false    | Wi-Fi Password                                               |
| proxyType     | Integer | false    | Proxy setting,0 is None, 1 is Manual, 2 is Proxy auto-config |
| hostName      | String | true     | Proxy Host Name, it is mandaory when proxyType is 1          |
| port          | Integer | true     | Proxy Port, it is mandaory when proxyType is 1               |
| pacUrl        | String  | true     | Proxy auto-config URL, it is mandaory when proxyType is 2    |

Structure of class TerminalApnUpdateRequest

| Property Name | Type    | Nullable | Description                                  |
|:--------------|:--------|:---------|:---------------------------------------------|
| apn           | String | false    | Array JSON string of ApnConfig Class as bellow |

Structure of class ApnConfig

| Property Name | Type    | Nullable | Description                                                          |
|:--------------|:--------|:---------|:---------------------------------------------------------------------|
| name          | String  | false    | Name                                                                 |
| apn          | String  | false    | APN                                                                  |
| type          | String  | false    | APN type, value should be default,mms,supl,wap,net,ia,ims,hipri,xcap |
| proxy          | String  | true    | Proxy Host                                                           |
| port          | String  | true     | Proxy Port                                                           |
| user          | String  | true     | Username                                                             |
| password          | String  | true     | Password                                                             |
| server          | String  | true     | Server                                                               |
| mmsc          | String  | true     | mmsc                                                                 |
| mmsproxy          | String  | true     | mms proxy                                                            |
| mmsport          | String  | true     | mms port                                                             |
| mcc          | String  | true     | mcc                                                                  |
| mnc          | String  | true     | mnc                                                                  |
| authtype          | Integer | true     | Authentication Type, 0 is  None,1 is PAP,2 is CHAP,3 is PAP/CHAP     |
| protocol          | String  | true     | APN Protocol, value should be IP, IPV6, IPV4V6                       |
| roaming_protocol          | String  | true     | Roaming Protocol, value should be IP, IPV6, IPV4V6                   |
| mvno_type          | String  | true     | MVNO Type, None is "", Other values are spn,imsi,gid,iccid           |
| mvno_match_data          | String  | true     | MVNO Value, value should be IP, IPV6, IPV4V6                         |

Structure of class TerminalWifiBlackListUpdateRequest

| Property Name | Type    | Nullable | Description                                        |
|:--------------|:--------|:---------|:---------------------------------------------------|
| blackList     | String | false    | Array JSON string of BlackListConfig Class as bellow |

Structure of class BlackListConfig

| Property Name | Type    | Nullable | Description                                                          |
|:--------------|:--------|:---------|:---------------------------------------------------------------------|
| wifiName          | String  | false    | Name                                                                 |

**Sample codes**

```
TerminalApi terminalApi = new TerminalApi("https://api.whatspos.com/p-market-api", "RCA9MDH6YN3WSSGPW6TJ", "TUNLDZVZECHNKZ4FW07XFCKN2W0N8ZDEA5ENKZYN");
Long terminalId = 909744L;
TerminalReplacementUpdateRequest terminalReplacementUpdateRequest = new TerminalReplacementUpdateRequest();
terminalReplacementUpdateRequest.setAllowReplacement(true);
Result<String> result = terminalApi.updateTerminalConfig(terminalId,terminalConfigUpdateRequest);

TerminalTimeZoneUpdateRequest terminalTimeZoneUpdateRequest = new TerminalTimeZoneUpdateRequest();
terminalTimeZoneUpdateRequest.setAutomaticTimezoneEnable(false);
terminalTimeZoneUpdateRequest.setTimeZone(TimeZone.getDefault().getID());
Result<String> result = terminalApi.updateTerminalConfig(terminalId,terminalConfigUpdateRequest);

TerminalWifiUpdateRequest terminalWifiUpdateRequest = new TerminalWifiUpdateRequest();
TerminalWifiUpdateRequest.WifiConfig wifiConfig1 = EnhancedJsonUtils.fromJson("{\"SSID\":\"pax20\",\"password\":\"12345678\",\"cipherType\":2,\"proxyType\":0}", TerminalWifiUpdateRequest.WifiConfig.class);
TerminalWifiUpdateRequest.WifiConfig wifiConfig2 = EnhancedJsonUtils.fromJson("{\"SSID\":\"pax30\",\"password\":\"12345678\",\"cipherType\":2,\"proxyType\":0}", TerminalWifiUpdateRequest.WifiConfig.class);
List<TerminalWifiUpdateRequest.WifiConfig> wifiConfigList = new ArrayList<>();
wifiConfigList.add(wifiConfig1);
wifiConfigList.add(wifiConfig2);
terminalWifiUpdateRequest.setWifiList(wifiConfigList);
Result<String> result = terminalApi.updateTerminalConfig(terminalId, terminalWifiUpdateRequest);

TerminalApnUpdateRequest terminalApnUpdateRequest = new TerminalApnUpdateRequest();
TerminalApnUpdateRequest.ApnConfig apnConfig1 = EnhancedJsonUtils.fromJson("{\"name\":\"MyApn20\",\"mcc\":\"101\",\"mnc\":\"102\",\"apn\":\"APN01\",\"user\":\"\",\"proxy\":\"\",\"mmsport\":\"100\",\"mmsc\":\"\",\"authtype\":1,\"type\":\"net\",\"protocol\":\"IP\",\"roaming_protocol\":\"IP\",\"mvno_type\":\"spn\",\"mvno_match_data\":\"\",\"checked\":false}", TerminalApnUpdateRequest.ApnConfig.class);
TerminalApnUpdateRequest.ApnConfig apnConfig2 = EnhancedJsonUtils.fromJson("{\"name\":\"MyApn30\",\"mcc\":\"101\",\"mnc\":\"102\",\"apn\":\"APN01\",\"user\":\"\",\"proxy\":\"\",\"mmsport\":\"100\",\"mmsc\":\"\",\"authtype\":1,\"type\":\"net\",\"protocol\":\"IP\",\"roaming_protocol\":\"IP\",\"mvno_type\":\"spn\",\"mvno_match_data\":\"\",\"checked\":false}", TerminalApnUpdateRequest.ApnConfig.class);
List<TerminalApnUpdateRequest.ApnConfig> apnConfigList = new ArrayList<>();
apnConfigList.add(apnConfig1);
apnConfigList.add(apnConfig2);
terminalApnUpdateRequest.setApnList(apnConfigList);
Result<String> result = terminalApi.updateTerminalConfig(terminalId, terminalApnUpdateRequest);
		
TerminalWifiBlackListUpdateRequest terminalWifiBlackListUpdateRequest = new TerminalWifiBlackListUpdateRequest();
TerminalWifiBlackListUpdateRequest.BlackListConfig blackListConfig1 = EnhancedJsonUtils.fromJson("{\"wifiName\":\"testwifi1\"}", TerminalWifiBlackListUpdateRequest.BlackListConfig.class);
TerminalWifiBlackListUpdateRequest.BlackListConfig blackListConfig2 = EnhancedJsonUtils.fromJson("{\"wifiName\":\"testwifi2\"}", TerminalWifiBlackListUpdateRequest.BlackListConfig.class);
List<TerminalWifiBlackListUpdateRequest.BlackListConfig> blackListConfigList = new ArrayList<>();
blackListConfigList.add(blackListConfig1);
blackListConfigList.add(blackListConfig2);
terminalWifiBlackListUpdateRequest.setBlackList(blackListConfigList);
Result<String> result = terminalApi.updateTerminalConfig(terminalId, terminalWifiBlackListUpdateRequest);		

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
|:--------------| :----------------------------------------------------------- | :---------- |
| 1800          | Terminal not found                                           |             |
| 1838          | It is not allowed to change the terminal level "Terminal Replacement" status. please make sure reseller level terminal replacement settings are enabled. |             |
| 1903          | When the "automaticTimezoneEnable" field is false, the "timeZone" field cannot be empty.                                                                 |             |
| 1904          | Incorrect time zone                                                                 |             |

### Update terminal configuration serialNo

Update terminal configuration like whether allow terminal replacement by API or input serial no on terminal.

**API**

```
public Result<String> updateTerminalConfigBySn(String serialNo, TerminalConfigUpdateRequest terminalConfigUpdateRequest)
```

**Input parameter(s) description**

| Parameter Name                   | Type                             | Nullable | Description                                                                   |
|:---------------------------------|:---------------------------------| :------- |:------------------------------------------------------------------------------|
| serialNo                       | String                           | false    | Terminal's serialNo.                                                                |
| terminalConfigUpdateRequest      | TerminalConfigUpdateRequest      | false    | Update terminal config request object. The structure shows below.             |
| terminalReplacementUpdateRequest | TerminalReplacementUpdateRequest | false    | Update terminal replacement config request object. The structure shows below. |
| terminalTimeZoneUpdateRequest    | TerminalTimeZoneUpdateRequest    | false    | Update terminal time zone config request object. The structure shows below.   |

Structure of class TerminalReplacementUpdateRequest

| Property Name           | Type    | Nullable | Description                                                                                                                                    |
|:------------------------|:--------|:---------|:-----------------------------------------------------------------------------------------------------------------------------------------------|
| allowReplacement        | Boolean | false    | Whether allow terminal replacement by API or input serial number on terminal                                                                   |

Structure of class TerminalTimeZoneUpdateRequest

| Property Name           | Type    | Nullable | Description                                                                                                                                   |
|:------------------------|:--------|:---------|:----------------------------------------------------------------------------------------------------------------------------------------------|
| automaticTimezoneEnable | Boolean | true     | Enable to use the network-provided time zone                                                                                                  |
| timeZone                | String  | true     | The terminal time zone [Time Zone](APPENDIX.md#time-zone)|

**Sample codes**

```
String serialNo = "SN6132522";
TerminalReplacementUpdateRequest terminalReplacementUpdateRequest = new TerminalReplacementUpdateRequest();
terminalReplacementUpdateRequest.setAllowReplacement(true);
Result<String> result = terminalApi.updateTerminalConfigBySn(serialNo,terminalConfigUpdateRequest);

TerminalTimeZoneUpdateRequest terminalTimeZoneUpdateRequest = new TerminalTimeZoneUpdateRequest();
terminalTimeZoneUpdateRequest.setAutomaticTimezoneEnable(false);
terminalTimeZoneUpdateRequest.setTimeZone(TimeZone.getDefault().getID());
Result<String> result = terminalApi.updateTerminalConfigBySn(serialNo,terminalConfigUpdateRequest);
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
|:--------------| :----------------------------------------------------------- | :---------- |
| 1800          | Terminal not found                                           |             |
| 1838          | It is not allowed to change the terminal level "Terminal Replacement" status. please make sure reseller level terminal replacement settings are enabled. |             |
| 1903          | When the "automaticTimezoneEnable" field is false, the "timeZone" field cannot be empty.                                                                 |             |
| 1904          | Incorrect time zone                                                                 |             |


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
		"allowReplacement": false,
		"language": "zh-cn",
		"wifiList": "[{\"password\":\"12345678\",\"cipherType\":\"2\",\"proxyType\":\"0\"},{\"password\":\"12345678\",\"cipherType\":\"2\",\"proxyType\":\"0\"}]",
		"apnList": "[{\"name\":\"MyApn20\",\"apn\":\"APN01\",\"type\":\"net\",\"proxy\":\"\",\"user\":\"\",\"mmsc\":\"\",\"mmsport\":\"100\",\"mcc\":\"101\",\"mnc\":\"102\",\"authtype\":\"1\",\"protocol\":\"IP\",\"roaming_protocol\":\"IP\",\"mvno_type\":\"spn\",\"mvno_match_data\":\"\"},{\"name\":\"MyApn30\",\"apn\":\"APN01\",\"type\":\"net\",\"proxy\":\"\",\"user\":\"\",\"mmsc\":\"\",\"mmsport\":\"100\",\"mcc\":\"101\",\"mnc\":\"102\",\"authtype\":\"1\",\"protocol\":\"IP\",\"roaming_protocol\":\"IP\",\"mvno_type\":\"spn\",\"mvno_match_data\":\"\"}]",
		"blackList": "[{\"wifiName\":\"testwifi1\"},{\"wifiName\":\"testwifi2\"}]"
	},
	"rateLimit": "3000",
	"rateLimitRemain": "2998",
	"rateLimitReset": "1729495336615"
}
```

**Possible client validation errors**

> <font color=red>Parameter terminalId cannot be null and cannot be less than 1!</font>

**Possible business codes**

| Business Code | Message                | Description |
| :------------ | :--------------------- | :---------- |
| 1800          | Terminal not found     |             |
| 1801          | Terminal doesn't exist |             |

### Get terminal configuration by serialNo

Get terminal configuration.

**API**

```
public Result<TerminalConfigDTO> getTerminalConfigBySn(String serialNo)
```

**Input parameter(s) description**

| Parameter Name | Type   | Nullable | Description    |
|:---------------|:-------| :------- | :------------- |
| serialNo       | String | false    | Terminal's serialNo. |

**Sample codes**

```
TerminalApi terminalApi = new TerminalApi("https://api.whatspos.com/p-market-api", "RCA9MDH6YN3WSSGPW6TJ", "TUNLDZVZECHNKZ4FW07XFCKN2W0N8ZDEA5ENKZYN");
Result<TerminalConfigDTO> result = terminalApi.getTerminalConfigBySn("SN6132522");
```

**Client side validation failed sample result(JSON formatted)**

```
{
	"businessCode": -1,
	"validationErrors": ["Parameter terminal SerialNo cannot be null!"]
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

### Get terminal PED information by serialNo

Get terminal PED information by terminal serialNo.

**API**

```
public Result<TerminalPedDTO> getTerminalPedBySn(String serialNo)
```

**Input parameter(s) description**

| Parameter Name | Type   | Nullable | Description    |
| :------------- |:-------| :------- | :------------- |
| serialNo     | String | false    | Terminal's serialNo. |

**Sample codes**

```
TerminalApi terminalApi = new TerminalApi("https://api.whatspos.com/p-market-api", "RCA9MDH6YN3WSSGPW6TJ", "TUNLDZVZECHNKZ4FW07XFCKN2W0N8ZDEA5ENKZYN");
Result<TerminalPedDTO> result = terminalApi.getTerminalPedBySn("SN6132522");
```

**Client side validation failed sample result(JSON formatted)**

```
{
	"businessCode": -1,
	"validationErrors": ["Parameter terminal serialNo cannot be null!"]
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

### Move terminal

Move a terminal to another reseller and merchant

**API**

```
public Result<String> moveTerminalBySn(String serialNo, String resellerName, String merchantName)
```

**Input parameter(s) description**

| Parameter Name | Type   | Nullable | Description    |
| :------------- |:-------| :------- | :------------- |
| serialNo     | String | false    | Terminal's serialNo. |
| resellerName| String | false | The target reseller name the terminal move to|
| merchantName| String | false | The target merchant name the terminal move to|

**Sample codes**

```
TerminalApi terminalApi = new TerminalApi("https://api.whatspos.com/p-market-api", "RCA9MDH6YN3WSSGPW6TJ", "TUNLDZVZECHNKZ4FW07XFCKN2W0N8ZDEA5ENKZYN");
Result<String> result = terminalApi.moveTerminalBySn("SN6132522", "PAX", "6666");
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

| Business Code | Message                                 | Description |
|:--------------|:----------------------------------------| :---------- |
| 135           | Request parameter is missing or invalid |             |
| 997           | Malformed or illegal request            |             |
| 1801          | Terminal doesn't exist                  |             |
| 1896          | The terminal is offline                 |             |
| 15094         | Terminal is locked                      |             |
| 15095         | Terminal has been unlocked              |             |
| 15096         | The terminal is being locked            |             |
| 15097         | The terminal is being unlocked            |             |
| 15099         | Terminal restart in progress            |             |

### Push Command to Terminal by serialNo

Push lock, unlock and restart command to terminal

**API**

```
public Result<String> pushCmdToTerminalBySn(String serialNo, TerminalApi.TerminalPushCmd command)
```

**Input parameter(s) description**

| Parameter Name              | Type            | Nullable | Description                     |
| :-------------------------- |:----------------| :------- | :------------------------------ |
| serialNo                  | String          | false    | Terminal's serialNo.                  |
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
| 135           | Request parameter is missing or invalid |             |
| 997           | Malformed or illegal request            |             |
| 1801          | Terminal doesn't exist                  |             |
| 1896          | The terminal is offline                 |             |
| 15094         | Terminal is locked                      |             |
| 15095         | Terminal has been unlocked              |             |
| 15096         | The terminal is being locked            |             |
| 15097         | The terminal is being unlocked            |             |
| 15099         | Terminal restart in progress            |             |


### Get terminal network information

Get terminal network information by terminal serialNo or tid.

**API**

```
public Result<TerminalNetworkDTO> getTerminalNetwork(String serialNo, String tid)
```

**Input parameter(s) description**

| Parameter Name | Type   | Nullable | Description          |
| :------------- | :----- | :------- | :------------------- |
| serialNo       | String | true     | Terminal's serialNo. |
| tid            | String | true     | Terminal's tid.      |

**Sample codes**

```
TerminalApi terminalApi = new TerminalApi("https://api.whatspos.com/p-market-api", "RCA9MDH6YN3WSSGPW6TJ", "TUNLDZVZECHNKZ4FW07XFCKN2W0N8ZDEA5ENKZYN");
Result<TerminalNetworkDTO> result = terminalApi.getTerminalNetwork("TEST8000999", "BTG7KFTY");
```

**Client side validation failed sample result(JSON formatted)**

```
{
	"businessCode": -1,
	"validationErrors": ["The property serialNo and tid in request cannot be blank at same time!"]
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
	"data: {
	     "id":908627,
	     "tid":"BTG7KFTY",
         "serialNo":"TEST8000999",
         "status":"A",
         "battery":91,
	     "onlineStatus":0,
	     "network":"NETWORK_WIFI"
	     "macAddress":"A1:D1:B2:4C:14:FF"
	     }
}
```

The type in dataSet of result is TerminalDTO. The structure shows below.

Structure of class TerminalNetworkDTO

| Property Name | Type    | Description                                                  |
| :------------ | :------ | :----------------------------------------------------------- |
| id            | Long    | Terminal's id.                                               |
| tid           | String  | Terminal's tid.                                              |
| serialNo      | String  | Terminal's serialNo.                                         |
| status        | String  | Terminal's status.<br/> the value can be TerminalStatus.Active, TerminalStatus.Inactive, TerminalStatus.Suspend |
| battery       | Float   | Terminal's battery.                                          |
| onlineStatus  | Integer | Terminal's online status.<br/> the value can be 0,1,2.<br/>0 represents unknown, 1 represents offline, and 2 represents online |
| network       | String  | Terminal's network status.<br/>the value can be NETWORK_WIFI, NETWORK_5G,NETWORK_4G,NETWORK_3G,NETWORK_2G,<br/>NETWORK_ETHERNET,NETWORK_UNKNOWN |
| macAddress    | String  | Terminal's MAC address info.                                 |



**Possible client validation errors**

> <font color=red>The property serialNo and tid in request cannot be blank at same time! !</font>

**Possible business codes**

| Business Code | Message                    | Description |
| :------------ | :------------------------- | :---------- |
| 2028          | Terminal not found         |             |
| 2039          | Tid mismatch with serialNo |             |

### Push Terminal Message

push terminal message by terminal id.

**API**

```
public Result<String> pushTerminalMessage(Long terminalId, TerminalMessageRequest terminalMessageRequest)
```

**Input parameter(s) description**

| Parameter Name         | Type                   | Nullable | Description    |
| :--------------------- | :--------------------- | :------- | :------------- |
| terminalId             | String                 | false    | Terminal's id. |
| terminalMessageRequest | TerminalMessageRequest | false    |                |

Structure of class TerminalMessageRequest

| Property Name | Type   | Nullable | Description                                 |
| :------------ | :----- | -------- | :------------------------------------------ |
| title         | String | false    | The length limit for message titles is 64   |
| content       | String | false    | The length limit for message content is 256 |

**Sample codes**

```
TerminalApi terminalApi = new TerminalApi("https://api.whatspos.com/p-market-api", "RCA9MDH6YN3WSSGPW6TJ", "TUNLDZVZECHNKZ4FW07XFCKN2W0N8ZDEA5ENKZYN");
Long terminalId = 162006737459222L
TerminalMessageRequest request = new TerminalMessageRequest();
request.setTitle("Test Title");
request.setContent("Test Content");
Result<String> result = terminalApi.pushTerminalMessage(terminalId,request);

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

**Possible business codes**

| Business Code | Message                              | Description |
| :------------ | :----------------------------------- | :---------- |
| 1800          | Terminal not found                   |             |
| 1810          | Terminal is not active               |             |
| 1896          | The terminal is offline!             |             |
| 1803          | Terminal model is mandatory          |             |
| 2123          | Push message title cannot be empty   |             |
| 2124          | Push message title is too long       |             |
| 2125          | Push message content cannot be empty |             |
| 2126          | Push message content is too long     |             |



### Push Terminal Message By Serial No

push terminal message by terminal serial no.

**API**

```
public Result<String> pushTerminalMessageBySn(Long terminalId, TerminalMessageRequest terminalMessageRequest)
```

**Input parameter(s) description**

| Parameter Name         | Type                   | Nullable | Description    |
| :--------------------- | :--------------------- | :------- | :------------- |
| terminalId             | String                 | false    | Terminal's id. |
| terminalMessageRequest | TerminalMessageRequest | false    |                |

Structure of class TerminalMessageRequest

| Property Name | Type   | Nullable | Description                                 |
| :------------ | :----- | -------- | :------------------------------------------ |
| title         | String | false    | The length limit for message titles is 64   |
| content       | String | false    | The length limit for message content is 256 |

**Sample codes**

```
TerminalApi terminalApi = new TerminalApi("https://api.whatspos.com/p-market-api", "RCA9MDH6YN3WSSGPW6TJ", "TUNLDZVZECHNKZ4FW07XFCKN2W0N8ZDEA5ENKZYN");
String serialNo = "TESTBYSN"
TerminalMessageRequest request = new TerminalMessageRequest();
request.setTitle("Test Title");
request.setContent("Test Content");
Result<String> resultBySn = terminalApi.pushTerminalMessageBySn(serialNo,request);

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

**Possible business codes**

| Business Code | Message                              | Description |
| :------------ | :----------------------------------- | :---------- |
| 1800          | Terminal not found                   |             |
| 1810          | Terminal is not active               |             |
| 1896          | The terminal is offline!             |             |
| 1803          | Terminal model is mandatory          |             |
| 2123          | Push message title cannot be empty   |             |
| 2124          | Push message title is too long       |             |
| 2125          | Push message content cannot be empty |             |
| 2126          | Push message content is too long     |             |

### Get terminal system usage by id

Get terminal cpu, ram, storage usage.

**API**

```
public Result<TerminalSystemUsageDTO> getTerminalSystemUsageById(Long terminalId)
```

**Path variable(s) description**

| Parameter Name | Type   | Nullable | Description     |
|:---------------|:-------| :------- |:----------------|
| terminalId     | Long   | true     | Terminal id.    |

**Sample codes**

```
TerminalApi terminalApi = new TerminalApi("https://api.whatspos.com/p-market-api", "RCA9MDH6YN3WSSGPW6TJ", "TUNLDZVZECHNKZ4FW07XFCKN2W0N8ZDEA5ENKZYN");
Result<TerminalSystemUsageDTO> terminalSystemUsage = terminalApi.getTerminalSystemUsageById(1639491781525547L);
```

**Client side validation failed sample result(JSON formatted)**

```
{
	"businessCode": -1,
	"validationErrors": ["The property terminalId can't be empty!"]
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
		"totalCpuUsage": 1.0,
		"totalStorageUsage": 301666304,
		"totalRamUsage": 596234240,
		"totalRAM": 908165120,
		"totalStorage": 3984334848
	}
}
```

The type in dataSet of result is TerminalSystemUsageDTO. The structure shows below.

Structure of class TerminalSystemUsageDTO

| Property Name | Type       | Description                       |
| :------------ |:-----------|:----------------------------------|
| totalCpuUsage           | Double     | total cpu usage, value 1 means 1% |
| totalRamUsage      | Long       | total ram usage, unit is byte.    |
| totalRAM      | Long       | total ram size, unit is byte.     |
| totalStorageUsage        | Long     | total storage usage, unit is byte |
| totalStorage      | Long       | total storage size, unit is byte. |

**Possible client validation errors**

> <font color=red>Parameter terminalId cannot be empty!</font>

**Possible business codes**

| Business Code | Message                    | Description |
| :------------ | :------------------------- | :---------- |
| 2028          | Terminal not found         |             |



### Get terminal system usage by SN

Get terminal cpu, ram, storage usage by terminal serial no.

**API**

```
public Result<TerminalSystemUsageDTO> getTerminalSystemUsageBySn(String serialNo)
```

**Path variable(s) description**

| Parameter Name | Type   | Nullable | Description     |
|:---------------|:-------| :------- |:----------------|
| terminalId     | Long   | true     | Terminal id.    |

**Sample codes**

```
TerminalApi terminalApi = new TerminalApi("https://api.whatspos.com/p-market-api", "RCA9MDH6YN3WSSGPW6TJ", "TUNLDZVZECHNKZ4FW07XFCKN2W0N8ZDEA5ENKZYN");
Result<TerminalSystemUsageDTO> terminalSystemUsage = terminalApi.getTerminalSystemUsageBySn("SUBSN108");
```

**Client side validation failed sample result(JSON formatted)**

```
{
	"businessCode": -1,
	"validationErrors": ["The property serinalNo can't be empty!"]
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
		"totalCpuUsage": 1.0,
		"totalStorageUsage": 301666304,
		"totalRamUsage": 596234240,
		"totalRAM": 908165120,
		"totalStorage": 3984334848
	}
}
```

The type in dataSet of result is TerminalSystemUsageDTO. The structure shows below.

Structure of class TerminalSystemUsageDTO

| Property Name | Type       | Description                       |
| :------------ |:-----------|:----------------------------------|
| totalCpuUsage           | Double     | total cpu usage, value 1 means 1% |
| totalRamUsage      | Long       | total ram usage, unit is byte.    |
| totalRAM      | Long       | total ram size, unit is byte.     |
| totalStorageUsage        | Long     | total storage usage, unit is byte |
| totalStorage      | Long       | total storage size, unit is byte. |

**Possible client validation errors**

> <font color=red>Parameter terminalId cannot be empty!</font>

**Possible business codes**

| Business Code | Message                    | Description |
| :------------ | :------------------------- | :---------- |
| 2028          | Terminal not found         |             |


### Collect Terminal Log

collect terminal log.

**API**

```
public Result<String> collectTerminalLog(Long terminalId, TerminalLogRequest terminalLogRequest)
```

**Input parameter(s) description**

| Parameter Name       | Type               | Nullable | Description    |
| :------------------- | :----------------- | :------- | :------------- |
| terminalId           | Long               | false    | Terminal's id. |
| *terminalLogRequest* | TerminalLogRequest | false    |                |

Structure of class TerminalLogRequest

| Property Name | Type   | Nullable | Description                                                  |
| :------------ | :----- | -------- |:-------------------------------------------------------------|
| type          | String | false    | The type of terminal log, L(Logcat), D(Detail Log), S(Geolocation Log) |
| beginDate     | String | true     | The begin date of terminal detail log, example: "2024-12-01" |
| endDate       | String | true     | The end date of terminal detail log, example: "2024-12-01"   |

**Sample codes**

```
TerminalApi terminalApi = new TerminalApi("https://api.whatspos.com/p-market-api", "RCA9MDH6YN3WSSGPW6TJ", "TUNLDZVZECHNKZ4FW07XFCKN2W0N8ZDEA5ENKZYN");
Long terminalId = "908627L"
TerminalLogRequest request = new TerminalLogRequest();
request.setType("L");
Result<String> result = terminalApi.collectTerminalLog(terminalId,request);

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

> <font color=red>Parameter terminalId cannot be empty!</font>

**Possible business codes**

| Business Code | Message                      | Description |
| :------------ | :--------------------------- | :---------- |
| 1800          | Terminal not found           |             |
| 14001         | Terminal log type is invalid |             |

### Collect Terminal Log By Serial No

collect terminal log by serial no.

**API**

```
public Result<String> collectTerminalLog(String serialNo, TerminalLogRequest terminalLogRequest)
```

**Input parameter(s) description**

| Parameter Name       | Type               | Nullable | Description                |
| :------------------- | :----------------- | :------- | :------------------------- |
| serialNo             | String             | false    | Serial number of terminal. |
| *terminalLogRequest* | TerminalLogRequest | false    |                            |

Structure of class TerminalLogRequest

| Property Name | Type   | Nullable | Description                                                  |
| :------------ | :----- | -------- |:-------------------------------------------------------------|
| type          | String | false    | The type of terminal log, L(Logcat), D(Detail Log), S(Geolocation Log) |
| beginDate     | String | true     | The begin date of terminal detail log, example: "2024-12-01" |
| endDate       | String | true     | The end date of terminal detail log, example: "2024-12-01"   |

**Sample codes**

```
TerminalApi terminalApi = new TerminalApi("https://api.whatspos.com/p-market-api", "RCA9MDH6YN3WSSGPW6TJ", "TUNLDZVZECHNKZ4FW07XFCKN2W0N8ZDEA5ENKZYN");
String serialNo = "SUBSN108"
TerminalLogRequest request = new TerminalLogRequest();
request.setType("L");
Result<String> resultBySn = terminalApi.collectTerminalLogBySn(serialNo,request);

```

**Client side validation failed sample result(JSON formatted)**

```
{
	"businessCode": -1,
	"validationErrors": ["The property serinalNo can't be empty!"]
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

> <font color=red>Parameter serialNo cannot be empty!</font>

**Possible business codes**

| Business Code | Message                      | Description |
| :------------ | :--------------------------- | :---------- |
| 1800          | Terminal not found           |             |
| 14001         | Terminal log type is invalid |             |

### Search Terminal Logs

search terminal logs.

**API**

```
public Result<TerminalLogDTO> searchTerminalLog(int pageNo, int pageSize, Long terminalId)
```

**Input parameter(s) description**

| Parameter Name | Type | Nullable | Description                                   |
| :------------- | :--- |:---------| :-------------------------------------------- |
| pageNo         | int  | true     | page number, value must >=1                   |
| pageSize       | int  | true     | the record number per page, range is 1 to 100 |
| terminalId     | Long | false    | Terminal's id.                                |

**Sample codes**

```
TerminalApi terminalApi = new TerminalApi("https://api.whatspos.com/p-market-api", "RCA9MDH6YN3WSSGPW6TJ", "TUNLDZVZECHNKZ4FW07XFCKN2W0N8ZDEA5ENKZYN");
Long terminalId = "908627L"
Result<TerminalLogDTO> result = terminalApi.searchTerminalLog(1,10,terminalId);

```

**Client side validation failed sample result(JSON formatted)**

```
{
	"businessCode": -1,
	"validationErrors": ["pageSize:must be greater than or equal to 1", "pageNo:must be greater than or equal to 1","Parameter terminalId cannot be null and cannot be less than 1!"]
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
	"pageInfo": {
		"pageNo": 1,
		"limit": 10,
		"totalCount": 1,
		"hasNext": false,
		"dataSet": [{
			"id": 907558,
			"type": "L",
			"title": "logcat-2024-12-03 14:13:36"
		}]
	}
}
```

The type in dataSet of result is TerminalLogDTO. The structure shows below.

Structure of class TerminalLogDTO

| Property Name | Type   | Description           |
| ------------- | ------ | --------------------- |
| id            | Long   | Terminal log's id.    |
| type          | String | Terminal log's type.  |
| title         | String | Terminal log's title. |

**Possible validation errors**

> <font color=red>pageSize:must be greater than or equal to 1</font>   
> <font color=red>pageNo:must be greater than or equal to 1</font>   
> <font color=red>pageSize:must be less than or equal to 100</font>
>
> <font color=red>Parameter terminalId cannot be empty!</font>

**Possible business codes**

| Business Code | Message            | Description |
| :------------ | :----------------- | :---------- |
| 1800          | Terminal not found |             |

### Search Terminal Logs By Serial No

search terminal logs by serial no.

**API**

```
public Result<TerminalLogDTO> searchTerminalLog(int pageNo, int pageSize, String serialNo)
```

**Input parameter(s) description**

| Parameter Name | Type   | Nullable | Description                                   |
| :------------- | :----- |:---------| :-------------------------------------------- |
| pageNo         | int    | true     | page number, value must >=1                   |
| pageSize       | int    | true        | the record number per page, range is 1 to 100 |
| serialNo       | String | false    | The terminal serial no.                       |

**Sample codes**

```
TerminalApi terminalApi = new TerminalApi("https://api.whatspos.com/p-market-api", "RCA9MDH6YN3WSSGPW6TJ", "TUNLDZVZECHNKZ4FW07XFCKN2W0N8ZDEA5ENKZYN");
String serialNo = "SUBSN108"
Result<TerminalLogDTO> result = terminalApi.searchTerminalLog(1,10,serialNo);

```

**Client side validation failed sample result(JSON formatted)**

```
{
	"businessCode": -1,
	"validationErrors": ["pageSize:must be greater than or equal to 1", "pageNo:must be greater than or equal to 1","Parameter terminalId cannot be null and cannot be less than 1!"]
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
	"pageInfo": {
		"pageNo": 1,
		"limit": 10,
		"totalCount": 1,
		"hasNext": false,
		"dataSet": [{
			"id": 907558,
			"type": "L",
			"title": "logcat-2024-12-03 14:13:36"
		}]
	}
}
```

The type in dataSet of result is TerminalLogDTO. The structure shows below.

Structure of class TerminalLogDTO

| Property Name | Type   | Description           |
| ------------- | ------ | --------------------- |
| id            | Long   | Terminal log's id.    |
| type          | String | Terminal log's type.  |
| title         | String | Terminal log's title. |

**Possible validation errors**

> <font color=red>pageSize:must be greater than or equal to 1</font>   
> <font color=red>pageNo:must be greater than or equal to 1</font>   
> <font color=red>pageSize:must be less than or equal to 100</font>
>
> <font color=red>Parameter serialNo cannot be empty!</font>

**Possible business codes**

| Business Code | Message            | Description |
| :------------ | :----------------- | :---------- |
| 1800          | Terminal not found |             |

### Get Terminal Log Download Task

get terminal log download task.

**API**

```
 public Result<DownloadTaskDTO> getTerminalLogDownloadTask(Long terminalId, Long terminalLogId) 
```

**Input parameter(s) description**

| Parameter Name  | Type | Nullable | Description        |
| :-------------- | :--- | :------- | :----------------- |
| terminalId      | Long | false    | Terminal's id.     |
| *terminalLogId* | Long | false    | Terminal log's id. |

**Sample codes**

```
TerminalApi terminalApi = new TerminalApi("https://api.whatspos.com/p-market-api", "RCA9MDH6YN3WSSGPW6TJ", "TUNLDZVZECHNKZ4FW07XFCKN2W0N8ZDEA5ENKZYN");
Long terminalId = "908627L";
Long terminalLogId = "908624L";
Result<DownloadTaskDTO> result = terminalApi.getTerminalLogDownloadTask(terminalId,terminalLogId);

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
		"fileName": "Logcat-A920-IMP8000000020-2024-11-29_15_10_10.zip",
		"downloadUrl": "https://api..whatspos.com/p-market-web/v1/common/files/f7af52e72e8a5780f1895b3c2d997c7a",
		"signature": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJmaWxlTmFtZSI6IkxvZ2NhdC1udWxsLUlNUDgwMDAwMDAwMjAtMjAyN",
		"expireSeconds": 1712474124,
		"keyPairId": "KX8VKGY3RK4NN
	}
}
```

The type of data in result is DownloadTaskDTO. The structure shows below.

Structure of class DownloadTaskDTO

| Property Name | Type   | Description                       |
| ------------- | ------ | --------------------------------- |
| fileName      | String | The file name of log              |
| downloadUrl   | String | The download url of terminal log  |
| signature     | String | The value of CloudFront-Signature |
| expireSeconds | Long   | The value of expireSeconds        |
| keyPairId     | String | The value of keyPairId            |

When you access the download url you must set three cookies, the keys are "CloudFront-Signature", "CloudFront-Expires", "CloudFront-Key-Pair-Id" and the values are signature , expireSeconds and keyPairId.

**Possible client validation errors**

> <font color=red>Parameter terminalId cannot be empty!</font>
>
> <font color=red>Parameter terminalLogId cannot be empty!</font>

**Possible business codes**

| Business Code | Message            | Description |
| :------------ | :----------------- | :---------- |
| 1800          | Terminal not found |             |
| 16111         | File not found     |             |



### Get Terminal Log Download Task By Serial No

get terminal log download task by serial no.

**API**

```
public Result<DownloadTaskDTO> getTerminalLogDownloadTaskBySn(String serialNo, Long terminalLogId)
```

**Input parameter(s) description**

| Parameter Name  | Type   | Nullable | Description                |
| :-------------- | :----- | :------- | :------------------------- |
| serialNo        | String | false    | Serial number of terminal. |
| *terminalLogId* | Long   | false    | Terminal log's id.         |

**Sample codes**

```
TerminalApi terminalApi = new TerminalApi("https://api.whatspos.com/p-market-api", "RCA9MDH6YN3WSSGPW6TJ", "TUNLDZVZECHNKZ4FW07XFCKN2W0N8ZDEA5ENKZYN");
String serialNo = "SUBSN108";
Long terminalLogId = "908624L";
Result<DownloadTaskDTO> result = terminalApi.getTerminalLogDownloadTaskBySn(serialNo,terminalLogId);

```

**Client side validation failed sample result(JSON formatted)**

```
{
	"businessCode": -1,
	"validationErrors": ["The property serinalNo can't be empty!"]
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
		"fileName": "Logcat-A920-IMP8000000020-2024-11-29_15_10_10.zip",
		"downloadUrl": "https://api..whatspos.com/p-market-web/v1/common/files/f7af52e72e8a5780f1895b3c2d997c7a",
		"signature": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJmaWxlTmFtZSI6IkxvZ2NhdC1udWxsLUlNUDgwMDAwMDAwMjAtMjAyN",
		"expireSeconds": 1712474124,
		"keyPairId": "KX8VKGY3RK4NN
	}
}
```

The type of data in result is DownloadTaskDTO. The structure shows below.

Structure of class DownloadTaskDTO

| Property Name | Type   | Description                      |
| ------------- | ------ | -------------------------------- |
| fileName      | String | The file name of log             |
| downloadUrl   | String | The download url of terminal log |
| signature     | String | CloudFront-Signature             |
| expireSeconds | Long   | CloudFront-Expires               |
| keyPairId     | String | CloudFront-Key-Pair-Id           |

When you access the download url you must set three cookies, the keys are "CloudFront-Signature", "CloudFront-Expires", "CloudFront-Key-Pair-Id" and the values are signature , expireSeconds and keyPairId.

**Possible client validation errors**

> <font color=red>Parameter serialNo cannot be empty!</font>
>
> <font color=red>Parameter terminalLogId cannot be empty!</font>

**Possible business codes**

| Business Code | Message            | Description |
| :------------ | :----------------- | :---------- |
| 1800          | Terminal not found |             |
| 16111         | File not found     |             |


### Change terminal model by id

Change terminal model by id, modelName.

**API**

```
public Result<EmptyResponse> changeModel(Long terminalId, String modelName) 
```

**Path variable(s) description**

| Parameter Name | Type   | Nullable | Description     |
|:---------------|:-------| :------- |:----------------|
| terminalId     | Long   | true     | Terminal id.    |

**Input parameter(s) description**

| Parameter Name | Type   | Nullable | Description                   |
| :------------- | :----- |:---------|:------------------------------|
| modelName            | String | false    | new model name of terminal.   |

**Sample codes**

```
TerminalApi terminalApi = new TerminalApi("https://api.whatspos.com/p-market-api", "RCA9MDH6YN3WSSGPW6TJ", "TUNLDZVZECHNKZ4FW07XFCKN2W0N8ZDEA5ENKZYN");
Result<EmptyResponse> responseResult = terminalApi.changeModelBySN("SUBSN108", "A920");
```

**Client side validation failed sample result(JSON formatted)**

```
{
	"businessCode": -1,
	"validationErrors": ["The property terminalId can't be empty!"]
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
	"message":""
}
```

The type in dataSet of result is EmptyResponse. The structure shows below.

Structure of class EmptyResponse

| Property Name | Type   | Description   |
| :------------ |:-------|:--------------|
| businessCode           | Long   | 0 is success  |
| message      | String | error message |

**Possible client validation errors**

> <font color=red>Parameter terminalId cannot be empty!</font>

**Possible business codes**

| Business Code | Message                    | Description |
| :------------ | :------------------------- | :---------- |
| 2028          | Terminal not found         |             |



### Change terminal model by Serial No

Change terminal model by serialNo, modelName.

**API**

```
public Result<EmptyResponse> changeModelBySN(String serialNo, String modelName)
```

**Input parameter(s) description**

| Parameter Name | Type   | Nullable | Description                   |
|:---------------| :----- |:---------|:------------------------------|
| serialNo       | String | false    | serial No of terminal.        |
| modelName      | String | false    | new model name of terminal.   |


**Sample codes**

```
TerminalApi terminalApi = new TerminalApi("https://api.whatspos.com/p-market-api", "RCA9MDH6YN3WSSGPW6TJ", "TUNLDZVZECHNKZ4FW07XFCKN2W0N8ZDEA5ENKZYN");
Result<EmptyResponse> responseResult = terminalApi.changeModel("POS10008", "A920");
```

**Client side validation failed sample result(JSON formatted)**

```
{
	"businessCode": -1,
	"validationErrors": ["The property serinalNo can't be empty!"]
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
	"message": ""
}
```

The type in dataSet of result is EmptyResponse. The structure shows below.

Structure of class EmptyResponse

| Property Name | Type   | Description   |
| :------------ |:-------|:--------------|
| businessCode           | Long   | 0 is success  |
| message      | String | error message |

**Possible client validation errors**

> <font color=red>Parameter terminalId cannot be empty!</font>

**Possible business codes**

| Business Code | Message                    | Description |
| :------------ | :------------------------- | :---------- |
| 2028          | Terminal not found         |             |

