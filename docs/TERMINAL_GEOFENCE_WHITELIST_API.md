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

