## TerminalGeoFenceWhiteList  API

All terminal GeoFence WhiteList related APIs are encapsulated in classes *
com.pax.market.api.sdk.java.api.terminalGeoFenceWhiteList;.TerminalGeoFenceWhiteListApi*.

**Constructors of TerminalGeoFenceWhiteListApi**

```
public TerminalGeoFenceWhiteListApi(String baseUrl, String apiKey, String apiSecret);
```

**Constructor parameters description**

| Name      | Type   | Description                                                  |
| :-------- | :----- | :----------------------------------------------------------- |
| baseUrl   | String | the base url of REST API                                     |
| apiKey    | String | the apiKey of marketplace, get this key from PAXSTORE admin console, refer to chapter Apply access rights |
| apiSecret | String | apiSecret, get api secret from PAXSTORE admin console, refer to chapter Apply access rights |



### Search geoFence whiteList Api

The search geoFence whiteList API allow the thirdparty system search  geoFence whiteList by page.

**API**

```
public Result<TerminalGeoFenceWhiteListDTO> searchGeoFenceWhiteList(int pageNo, int pageSize, SearchOrderBy orderBy, String serialNo)
```

**Input parameter(s) description**

| Name     | Type                  | Nullable | Description                                                  |
| :------- | :-------------------- | :------- | :----------------------------------------------------------- |
| pageNo   | int                   | false    | page number, value must >=1                                  |
| pageSize | int                   | false    | the record number per page, range is 1 to 100                |
| orderBy  | TerminalSearchOrderBy | true     | the sort order by field name, value can be one of TerminalGeoFenceWhiteListApi.SearchOrderBy.CreatedDate_desc, TerminalGeoFenceWhiteListApi.SearchOrderBy.CreatedDate_asc. If pass null parameter the search result will order by  CreatedDate_desc by default. |
| serialNo | String                | true     | the serial number of terminal                                |

**Sample codes**

```
TerminalGeoFenceWhiteListApi terminalGeoFenceWhiteListApi = new TerminalGeoFenceWhiteListApi("https://api.whatspos.com/p-market-api", "RCA9MDH6YN3WSSGPW6TJ", "TUNLDZVZECHNKZ4FW07XFCKN2W0N8ZDEA5ENKZYN");
String serialNo = "";
Result<TerminalGeoFenceWhiteListDTO> result = terminalGeoFenceWhiteListApi.searchGeoFenceWhiteList(1,10, TerminalGeoFenceWhiteListApi.SearchOrderBy.CreatedDate_desc, serialNo);
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
    "totalCount": 2,
    "hasNext": false,
    "dataSet": [
      {
        "terminalId": 1592562028642346,
        "serialNo": "1123321123sn"
      },
      {
        "terminalId": 1592379775647784,
        "serialNo": "6688888822"
      }
    ]
  }
}
```

The type in dataSet of result is TerminalGeoFenceWhiteListDTO. The structure shows below.

Structure of class TerminalGeoFenceWhiteListDTO

| Property Name | Type   | Description                |
| :------------ | :----- | :------------------------- |
| terminalId    | Long   | Terminal's id.             |
| serialNo      | String | Serial number of terminal. |

**Possible validation errors**

> <font color=red>pageSize:must be greater than or equal to 1</font>   
> <font color=red>pageNo:must be greater than or equal to 1</font>   
> <font color=red>pageSize:must be less than or equal to 100</font>





### CreateGeoFenceWhiteList

**API**

```
public Result<String> createGeoFenceWhiteList(TerminalGeoFenceWhiteListRequest createRequest)
```

**Input parameter(s) description**

| Parameter Name | Type                             | Nullable | Description                                         |
| :------------- | :------------------------------- | :------- | :-------------------------------------------------- |
| createRequest  | TerminalGeoFenceWhiteListRequest | false    | the create request object, the structure like below |

Structure of class TerminalGeoFenceWhiteListRequest

| Property Name | Type   | Nullable | Description                   |
| :------------ | :----- | :------- | :---------------------------- |
| serialNo      | String | fasle    | the serial number of terminal |

**Sample codes**

```
TerminalGeoFenceWhiteListApi terminalGeoFenceWhiteListApi = new TerminalGeoFenceWhiteListApi("https://api.whatspos.com/p-market-api", "RCA9MDH6YN3WSSGPW6TJ", "TUNLDZVZECHNKZ4FW07XFCKN2W0N8ZDEA5ENKZYN");
TerminalGeoFenceWhiteListRequest createRequest = new TerminalGeoFenceWhiteListRequest();
createRequest.setSerialNo("1123321123sn");
Result<String> result =  terminalGeoFenceWhiteListApi.createGeoFenceWhiteList(createRequest);
```

**Client side validation failed sample result(JSON formatted)**

```
{
	"businessCode": -1,
	"validationErrors": ["Parameter createRequest cannot be null!"]
}
```

**Server side validation failed sample result(JSON formatted)**

```
{
	"businessCode": 28500,
	"message": "The terminal has been configured with a geofencing whitelist"
}
```

**Successful sample result**

```
{
	"businessCode": 0
}
```

**Possible validation errors**

> <font color=red>Parameter createRequest cannot be null !</font> 
> <font color=red>Parameter serialNo cannot be null !</font>



**Possible business codes**

| Business Code | Message                                                      | Description |
| :------------ | :----------------------------------------------------------- | :---------- |
| 113           | Your request is invalid, please try again or contact marketplace administrator |             |
| 2028          | Terminal not found                                           |             |
| 28500         | The terminal has been configured with a geofencing whitelist |             |



### DeleteGeoFenceWhiteList

Delete geoFence whiteList  by serialNo.

**API**

```
public Result<String> deleteGeoFenceWhiteList(TerminalGeoFenceWhiteListRequest deleteRequest)
```

**Input parameter(s) description**

| Parameter Name | Type                             | Nullable | Description                                         |
| :------------- | :------------------------------- | :------- | :-------------------------------------------------- |
| deleteRequest  | TerminalGeoFenceWhiteListRequest | false    | the delete request object, the structure like below |

Structure of class TerminalGeoFenceWhiteListRequest

| Property Name | Type   | Nullable | Description                   |
| :------------ | :----- | :------- | :---------------------------- |
| serialNo      | String | false    | the serial number of terminal |

Note: deleteRequest cannot be empty

**Sample codes**

```
TerminalGeoFenceWhiteListApi terminalGeoFenceWhiteListApi = new TerminalGeoFenceWhiteListApi("https://api.whatspos.com/p-market-api", "RCA9MDH6YN3WSSGPW6TJ", "TUNLDZVZECHNKZ4FW07XFCKN2W0N8ZDEA5ENKZYN");
TerminalGeoFenceWhiteListRequest deleteRequest = new TerminalGeoFenceWhiteListRequest();
deleteRequest.setSerialNo("1123321123sn");
Result<String> result = terminalGeoFenceWhiteListApi.deleteGeoFenceWhiteList(deleteRequest);
```

**Client side validation failed sample result(JSON formatted)**

```
{
	"businessCode": -1,
	"validationErrors": ["Parameter deleteRequest cannot be null!"]
}
```

**Server side validation failed sample result(JSON formatted)**

```
{
	"businessCode": 28500,
	"message": "The terminal has been configured with a geofencing whitelist"
}
```

**Successful sample result(JSON formatted)**

```
{
	"businessCode": 0
}
```

> <font color=red>Parameter createRequest cannot be null !</font> 
> <font color=red>Parameter serialNo cannot be null !</font>

**Possible business codes**

| Business Code | Message                                                      | Description |
| :------------ | :----------------------------------------------------------- | :---------- |
| 113           | Your request is invalid, please try again or contact marketplace administrator | &nbsp;      |
| 2028          | Terminal not found                                           |             |

