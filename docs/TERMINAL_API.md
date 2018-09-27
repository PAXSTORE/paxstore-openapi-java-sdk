## Terminal APIs

Terminal APIs allow thirdparty system search terminals, get a terminal, create a terminal, update a terminal, activate a terminal, disable a terminal and delete a exist terminal.

All the terminal APIs are in the class *com.pax.market.api.sdk.java.api.terminal.TerminalApi*.   

**Constructors of TerminalApi**

```
public TerminalApi(String baseUrl, String apiKey, String apiSecret);
public TerminalApi(String baseUrl, String apiKey, String apiSecret, Locale locale)
```

**Constructor parameters description**

|Name|Type|Description|
|:--|:--|:--|
|baseUrl|String|the base url of REST API|
|apiKey|String|the apiKey of marketplace, get this key from PAXSTORE admin console, refe to chapter Apply access rights|
|apiSecret|String|apiSecret, get api secret from PAXSTORE admin console, refer to chapter Apply access rights|
|locale|Locale|the locale, the default locale is Locale.ENGLISH, the language of message and errors in return object depend on locale|


### Search terminals

The search terminal API allow the thirdparty system search terminals by page. 

**API**

```
public Result<TerminalDTO> searchTerminal(int pageNo, int pageSize, TerminalSearchOrderBy orderBy, TerminalStatus status, String snNameTID)
```

**Input parameter(s) description**

| Name| Type | Nullable|Description |
|:--- | :---|:---|:---|
|pageNo|int|false|page number, value must >=1|
|pageSize|int|false|the record number per page, range is 1 to 1000|
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
			"resellerName": "New York"
		}]
	}
}
```

The type in dataSet of result is TerminalDTO. The structure shows below.

Structure of class TerminalDTO

|Property Name|Type|Description|
|:--|:--|:--|
|id|Long|Terminal's id.|
|name|String|Terminal's name.|
|tid|String|Terminal's tid.|
|serialNo|String|Serial number of terminal.|
|status|String|Status of terminal. Value can be one of A(active), P(Pendding) and S(Suspend).|
|merchantName|String|The merchant of terminal belongs to.|
|modelName|String|Model name of terminal.|
|resellerName|String|The reseller of terminal belongs to.|

**Possible validation errors**

> <font color=red>pageSize:must be greater than or equal to 1</font>   
> <font color=red>pageNo:must be greater than or equal to 1</font>   
> <font color=red>pageSize:must be less than or equal to 1000</font>  


### Get a terminal

The get terminal API allows the thirdparty system get a terminal by terminal id. If the termianl does not exist the data field in result is null.

**API**

```
public Result<TerminalDTO> getTerminal(Long terminalId)
```

**Input parameter(s) description**

|Parameter Name|Type|Nullable|Description|
|:--|:--|:--|:--|
|terminalId|Long|false|The terminal id.|


**Sample codes**

```
TerminalApi terminalApi = new TerminalApi("https://api.whatspos.com/p-market-api", "RCA9MDH6YN3WSSGPW6TJ", "TUNLDZVZECHNKZ4FW07XFCKN2W0N8ZDEA5ENKZYN");
Result<TerminalDTO> result = terminalApi.getTerminal(907554L);
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
		"id": 907554,
		"name": "testcreateterminal_023",
		"tid": "FNI9W6IX",
		"serialNo": "sn0101012",
		"status": "S",
		"merchantName": "haoxy",
		"modelName": "A920",
		"resellerName": "PAX"
	}
}
```

The type of data in result is TerminalDTO. Its structure already shows in search terminal API.


**Possible validation errors**

> <font color=red>Parameter terminalId cannot be null and cannot be less than 1!</font>  

**Possible business codes**

|Business Code|Message|Description|
|:--|:--|:--|
|1801|Terminal doesn't exist|&nbsp;|


### Create a terminal

Create merchant API allow the thirdparty system create a terminal remotely.


**API**

```
public Result<TerminalDTO> createTerminal(TerminalCreateRequest terminalCreateRequest)
```

**Input parameter(s) description**

|Parameter Name|Type|Nullable|Description|
|:--|:--|:--|:--|
|terminalCreateRequest|TerminalCreateRequest|false|The create request object. The structure shows below.|

Structure of class TerminalCreateRequest

|Property Name|Type|Nullable|Description|
|:--|:--|:--|:--|
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
		"resellerName": "New York"
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
|:--|:--|:--|
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


### Update a terminal

Update terminal API allows the thirdparty system update a exist terminal by terminal id.

**API**

```
public Result<TerminalDTO> updateTerminal(Long terminalId,TerminalUpdateRequest terminalUpdateRequest)
```

**Input parameter(s) description**

|Parameter Name|Type|Nullable|Description|
|:--|:--|:--|:--|
|terminalId|Long|false|Terminal's id.|
|terminalUpdateRequest|TerminalUpdateRequest|false|Update terminal request object. The structure shows below.|

Structure of class TerminalUpdateRequest

|Property Name|Type|Nullable|Description|
|:--|:--|:--|:--|
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
		"resellerName": "New York"
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
|:--|:--|:--|
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


### Activate a terminal

Activate terminal API allows the thirdparty system to activate a terminal by terminal id.


**API**

```
public Result<String> activateTerminal(Long terminalId) 
```

**Input parameter(s) description**

|Parameter Name|Type|Nullable|Description|
|:--|:--|:--|:--|
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
|:--|:--|:--|
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
|:--|:--|:--|:--|
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
|:--|:--|:--|
|1800|Terminal not found|&nbsp;|
|1888|The terminal is not active,unable to disable!|&nbsp;|


### Delete a terminal  

Delete terminal API allows the thirdparty system delete a exist terminal by terminal id.
If delete successfully there's no response content from remote server.

**API**

```
public Result<String> deleteTerminal(Long terminalId)
```


**Input parameter(s) description**

|Parameter Name|Type|Nullable|Description|
|:--|:--|:--|:--|
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
|:--|:--|:--|
|1800|Terminal not found|&nbsp;|
|1877|The terminal is active,unable to delete!|&nbsp;|





