## EMM Device Detail APIs

EMM Device Detail APIs allow thirdparty system get EMM device dashboard detail, get EMM device dashboard monitor, search EMM device installed apps.

All the EMM device detail APIs are in the class *com.pax.market.api.sdk.java.api.emm.emmDeviceDetail.EmmDeviceDetailApi*.

**Constructors of EmmDeviceDetailAPI**

```
public EmmDeviceDetailApi(String baseUrl, String apiKey, String apiSecret);
```

Constructor parameters description


| Name      | Type   | Description                                                                                               |
| :---------- | :------- | :---------------------------------------------------------------------------------------------------------- |
| baseUrl   | String | the base url of REST API                                                                                  |
| apiKey    | String | the apiKey of marketplace, get this key from PAXSTORE admin console, refer to chapter Apply access rights |
| apiSecret | String | apiSecret, get api secret from PAXSTORE admin console, refer to chapter Apply access rights               |

### Get EMM device dashboard detail

The get EMM device dashboard detail API allows thirdparty system get EMM device dashboard detail.

**API**

```
public Result<EmmDeviceDashboardDetailDTO> getEmmDeviceDashboardDetail(Long deviceId);
```

**Input parameter(s) description**


| Name     | Type | Nullable | Description          |
| :--------- | :----- | :--------- | :--------------------- |
| deviceId | Long | false    | The id of EMM device |

**Sample codes**

```
EmmAppApi emmAppApi = new  EmmAppApi("https://api.whatspos.com/p-market-api", "RCA9MDH6YN3WSSGPW6TJ", "TUNLDZVZECHNKZ4FW07XFCKN2W0N8ZDEA5ENKZYN");
Result<EmmDeviceDashboardDetailDTO> result = emmDeviceDetailApi.getEmmDeviceDashboardDetail(1644892713386019L);
```

**Client side validation failed sample result(JSON formatted)**

```
{
	"businessCode": -1,
	"validationErrors": ["Parameter deviceId cannot be null and cannot be less than 1!"]
}
```

**Server side validation failed sample result(JSON formatted)**

```
{
	"businessCode": 61617,
	"message": "Device not found"
}
```

**Successful sample result**

```
{
    "businessCode": 0,
    "pageInfo": {
        "pageNo": 1,
        "limit": 1,
        "totalCount": 1,
        "hasNext": false,
        "dataSet": [
            {
                "terminalId": 1644892713386019,
                "key": "RAM_TOTAL",
                "value": "1024",
                "syncDate": 1735008909000
            }
        ]
    }
}
```

The type in dataSet is EmmDeviceDashboardDetailDTO. The structure like below.


| Property Name | Type   | Description                                  |
| :-------------- | :------- | :--------------------------------------------- |
| terminalId    | Long   | The id of EMM device                         |
| key           | String | The key of EMM device dashboard detail       |
| value         | String | The value of EMM device dashboard detail     |
| syncDate      | Date   | The sync date of EMM device dashboard detail |

**Possible client validation errors**

> <font color=red>Parameter deviceId cannot be null and cannot be less than 1!</font>

**Possible business codes**


| Business Code | Message                                    | Description |
| :-------------- | :------------------------------------------- | :------------ |
| 61617         | Device not found                           |             |
| 61654         | EMM for Android not subscribed             |             |
| 61655         | EMM for Android not distributed            |             |
| 61656         | The market is not bound to EMM for Android |             |

### Get EMM device dashboard monitor

The get EMM device dashboard monitor API allows thirdparty system get EMM device dashboard monitor.

**API**

```
public Result<EmmDeviceDashboardMonitorDTO> getEmmDeviceDashboardMonitor(Long deviceId)
```

**Input parameter(s) description**


| Name     | Type | Nullable | Description          |
| :--------- | :----- | :--------- | :--------------------- |
| deviceId | Long | false    | The id of EMM device |

**Sample codes**

```
EmmAppApi emmAppApi = new  EmmAppApi("https://api.whatspos.com/p-market-api", "RCA9MDH6YN3WSSGPW6TJ", "TUNLDZVZECHNKZ4FW07XFCKN2W0N8ZDEA5ENKZYN");
Result<EmmDeviceDashboardMonitorDTO> result = emmDeviceDetailApi.getEmmDeviceDashboardMonitor(1644892713386019L);
```

**Client side validation failed sample result(JSON formatted)**

```
{
	"businessCode": -1,
	"validationErrors": ["Parameter deviceId cannot be null and cannot be less than 1!"]
}
```

**Server side validation failed sample result(JSON formatted)**

```
{
	"businessCode": 61617,
	"message": "Device not found"
}
```

**Successful sample result**

```
{
    "businessCode": 0,
    "data": {
        "terminalId": 1644892713386019,
        "battery": 11.0,
        "ramUsed": 1024,
        "storageUsed": 1024,
        "syncDate": 17350087230001
    }
}
```

The type in data is EmmDeviceDashboardMonitorDTO. The structure like below.


| Property Name | Type  | Description                                       |
| :-------------- | :------ | :-------------------------------------------------- |
| terminalId    | Long  | The id of EMM device                              |
| battery       | Float | The battery of EMM device dashboard monitor       |
| ramUsed       | Long  | The ram usage of EMM device dashboard monitor     |
| storageUsed   | Long  | The storage usage of EMM device dashboard monitor |
| syncDate      | Date  | The sync date of EMM device dashboard monitor     |

**Possible client validation errors**

> <font color=red>Parameter deviceId cannot be null and cannot be less than 1!</font>

**Possible business codes**


| Business Code | Message                                    | Description |
| :-------------- | :------------------------------------------- | :------------ |
| 61617         | Device not found                           |             |
| 61654         | EMM for Android not subscribed             |             |
| 61655         | EMM for Android not distributed            |             |
| 61656         | The market is not bound to EMM for Android |             |

### Search EMM device installed app

The EMM search device installed apps API allows thirdparty system search EMM device installed apps.

**API**

```
Result<EmmDeviceInstalledAppDTO> searchDeviceInstalledApp(int pageNo, int pageSize, EmmDeviceInstalledAppOrderBy orderBy, Long deviceId)
```

**Input parameter(s) description**


| Name     | Type                         | Nullable | Description                                                                                                                                                                                                                                                                                                                                                                                                                         |
| :--------- | :----------------------------- | :--------- | :------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ |
| pageNo   | int                          | false    | page number, value must >=1                                                                                                                                                                                                                                                                                                                                                                                                         |
| pageSize | int                          | false    | the record number per page, range is 1 to 100                                                                                                                                                                                                                                                                                                                                                                                       |
| orderBy  | EmmDeviceInstalledAppOrderBy | true     | the sort order by field name, if this parameter is null the search result will order by install time descend. The value of this parameter can be one of EmmDeviceInstalledAppOrderBy.AppName_desc, EmmDeviceInstalledAppOrderBy.AppName_asc, EmmDeviceInstalledAppOrderBy.AppSize_desc, EmmDeviceInstalledAppOrderBy.AppSize_asc, EmmDeviceInstalledAppOrderBy.AppInstallTime_desc, EmmDeviceInstalledAppOrderBy.AppInstallTime_asc |
| deviceId | Long                         | false    | The id of EMM device                                                                                                                                                                                                                                                                                                                                                                                                                |

**Sample codes**

```
EmmAppApi emmAppApi = new  EmmAppApi("https://api.whatspos.com/p-market-api", "RCA9MDH6YN3WSSGPW6TJ", "TUNLDZVZECHNKZ4FW07XFCKN2W0N8ZDEA5ENKZYN");
Result<EmmDeviceInstalledAppDTO> result = emmDeviceDetailApi.searchDeviceInstalledApp(1, 5, EmmDeviceDetailApi.EmmDeviceInstalledAppOrderBy.AppInstallTime_asc, 1644892713386019L);
```

**Client side validation failed sample result(JSON formatted)**

```
{
	"businessCode": -1,
	"validationErrors": ["Parameter pageNo Minimum cannot be less than 1!"]
}
```

**Server side validation failed sample result(JSON formatted)**

```
{
	"businessCode": 61617,
	"message": "Device not found"
}
```

**Successful sample result**

```
{
    "businessCode": 0,
    "pageInfo": {
        "pageNo": 1,
        "limit": 5,
        "totalCount": 1,
        "hasNext": false,
        "dataSet": [
            {
                "id": 1647075584376890,
                "terminalId": 1644892713386019,
                "name": "MAXSTORE for EMM",
                "packageName": "com.zolon.maxstore.emm",
                "version": "1.0.8-emm22-SNAPSHOT",
                "size": 4325132,
                "installTime": 1735008718000,
                "lastTimeUpdate": 1735008723000,
                "isLauncher": false,
                "isDefaultLauncher": false,
                "iconUrl": "https://example/test.png"
            }
        ]
    }
}
```

The type in dataSet is EmmDeviceInstalledAppDTO. And the structure like below.


| Property Name     | Type    | Description                               |
| :------------------ | :-------- | :------------------------------------------ |
| id                | Long    | The id of installation app                |
| terminalId        | Long    | The EMM device id of installation app     |
| name              | String  | The name of installation app              |
| packageName       | String  | The package name of installation app      |
| version           | String  | The version of installation app           |
| type              | String  | The type of installation app              |
| size              | Long    | The size of installation app              |
| iconUrl           | String  | The icon url of installation app          |
| installTime       | Date    | The installation time of installation app |
| lastTimeUpdate    | Date    | The update time of installation app       |
| isLauncher        | Boolean | Whether a startup application             |
| isDefaultLauncher | Boolean | Whether a default startup application     |

**Possible client validation errors**

> <font color=red>Parameter pageNo Minimum cannot be less than 1!</font>
> <font color=red>Parameter pageSize must be range is 1 to 100!</font>
> <font color=red>Parameter deviceId cannot be null and cannot be less than 1!</font>

**Possible business codes**


| Business Code | Message                                    | Description |
| :-------------- | :------------------------------------------- | :------------ |
| 61617         | Device not found                           |             |
| 61654         | EMM for Android not subscribed             |             |
| 61655         | EMM for Android not distributed            |             |
| 61656         | The market is not bound to EMM for Android |             |
