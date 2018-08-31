## Terminal APIs  

Terminal APIs allow thirdparty system search terminals, get a terminal, create a terminal, update a terminal, activate a terminal, disable a terminal and delete a exist terminal.


<br>

### Search terminals  

The search terminal API allow the thirdparty system search terminals by page according to serial number and status. 

<table>
<tr>
<td>Endpoint</td>
<td>/v1/3rdsys/terminals</td>
</tr>
<tr>
<td>Request Method</td>
<td>GET</td>
</tr>
<tr>
<td>Path Parameter(s)</td>
<td></td>
</tr>
<tr>
<td>Query Parameter(s)</td>
<td>pageNo, limit, orderBy, status, serialNo, sysKey, timestamp</td>
</tr>
<tr>
<td>Response Content Type</td>
<td>application/json</td>
</tr>
<tr>
<td>Success Response Code</td>
<td>200</td>
</tr>
</table>

**Query Parameter(s) Description**  

| Name| Type | Nullable|Description |
|:--- | :---|:---|:---|
|pageNo|int|false|page number, value must >=1|
|limit|int|false|the record number per page, range is 1 to 1000|
|orderBy|String|true|result sort by field. the value can be one of name, contact and phone|
|serialNo|String|true|search by serial number|
|status|String|true|the terminalstatus<br/> the value can be A(Active), (P)Pendding, S(Suspend)|
|sysKey|String|false|the apiKey|
|timestamp|String|false|currentTimeMillis|

**Success Response JSON Sample**

```
{
	"businessCode": 0,
	"dataset": [{
		"id": 846405,
		"name": "1",
		"tid": "NYF4BRK7",
		"serialNo": "",
		"status": "P",
		"merchantName": null,
		"modelName": "update activeD800_207728",
		"resellerName": "PAX"
	}, {
		"id": 335237,
		"name": "1",
		"tid": "OB4MSQJH",
		"serialNo": "AG0705",
		"status": "P",
		"merchantName": "cstore",
		"modelName": "A920",
		"resellerName": "Paris"
	}],
	"limit": 10,
	"pageNo": 1,
	"totalCount": 2,
	"hasNext": true
}
```

<br>

### Get a terminal  

Get terminal by id

<table>
<tr>
<td>Endpoint</td>
<td>/v1/3rdsys/terminals/{terminalId}</td>
</tr>
<tr>
<td>Request Method</td>
<td>GET</td>
</tr>
<tr>
<td>Path Parameter(s)</td>
<td>terminalId</td>
</tr>
<tr>
<td>Query Parameter(s)</td>
<td>sysKey, timestamp</td>
</tr>
<tr>
<td>Response Content Type</td>
<td>application/json</td>
</tr>
<tr>
<td>Success Response Code</td>
<td>200</td>
</tr>
</table>

**Query Parameter(s) Description**  

| Name| Type | Nullable|Description |
|:--- | :---|:---|:---|
|sysKey|String|false|the apiKey|
|timestamp|String|false|currentTimeMillis|


**Path Parameter(s) Description**

|Parameter Name|Type|Nullable|Description|
|:--|:--|:--|:--|
|terminalId|Long|false|the id of terminal|

**Success Response Sample**  

```
{
	"businessCode": 0,
	"data": {
		"id": 375977,
		"name": "111",
		"tid": "KPYMZFTA",
		"serialNo": "SNC953",
		"status": "A",
		"merchantName": "FLFuu",
		"modelName": "A920",
		"resellerName": "heibai80551uu"
	}
}
```

**Error Response Sample**  

```
{
	"businessCode": 1801,
	"message": "Terminal doesn't exist"
}
```


<br>
**Success Response JSON Description**  
Note: As the businessCode for success response always 0, so the below table only describs the values in data field of the JSON.

|Key Name|Type|Description|
|:--|:--|:--|
|id|Long|Terminal's id.|
|name|String|Terminal's name.|
|tid|String|Terminal's tid.|
|serialNo|String|Serial number of terminal.|
|status|String|Status of terminal. Value can be one of A(active), P(Pendding) and S(Suspend).|
|merchantName|String|The merchant of terminal belongs to.|
|modelName|String|Model name of terminal.|
|resellerName|String|The reseller of terminal belongs to.|
<br>




**Possible business codes**

|Business Code|Message|Description|
|:--|:--|:--|
|1801|Terminal doesn't exist|&nbsp;|

<br>

### Create a terminal  

<table>
<tr>
<td>Endpoint</td>
<td>/v1/3rdsys/terminals</td>
</tr>
<tr>
<td>Request Method</td>
<td>POST</td>
</tr>
<tr>
<td>Path Parameter(s)</td>
<td></td>
</tr>
<tr>
<td>Query Parameter(s)</td>
<td>sysKey, timestamp</td>
</tr>
<tr>
<td>Request Body Type</td>
<td>application/json</td>
</tr>
<tr>
<td>Response Body Type</td>
<td>application/json</td>
</tr>
<tr>
<td>Success Response Code</td>
<td>201</td>
</tr>
</table>

**Query Parameter(s) Description**  

| Name| Type | Nullable|Description |
|:--- | :---|:---|:---|
|sysKey|String|false|the apiKey|
|timestamp|String|false|currentTimeMillis|


**Request Body Sample**  

```
{
	"status": "A",
	"name": "KFC-TML-03",
	"serialNo": "sn0101012240",
	"merchantName": "KFC_Nanjing",
	"resellerName": "New York",
	"modelName": "A920",
	"location": "USA"
}
```

**Request Body Description**



|Key Name|Type|Nullable|Description|
|:--|:--|:--|:--|
|name|String|false|The name of terminal, max length is 64.|
|tid|String|true|The tid of terminal. If it is empty system will generate a tid when creating. And the length range is from 8 to 15.|
|serialNo|String|true|The serial number of terminal. If the status is active the serial number is mandatory.|
|merchantName|String|true|The merchant of terminal belongs to. If the initial is active then merchantName is mandatory. The max length is 64. Make sure the merchant belongs to the given reseller|
|resellerName|String|false|The reseller of terminal belongs to. Max length is 64.|
|modelName|String|false|The model name of terminal. Max length is 64.|
|location|String|true|The location of terminal, max length is 32.|
|status|String|true|Status of terminal, valus can be one of A(Active) and P(Pendding). If status is null the initial status is P(Pendding) when creating.|



**Successful Response Sample**

```
{
	"businessCode": 0,
	"data": {
		"id": 907534,
		"name": "KFC-TML-03",
		"tid": "XBKKOL3L",
		"serialNo": "sn0101012240",
		"status": "A",
		"merchantName": "KFC_Nanjing",
		"modelName": "A920",
		"resellerName": "New York"
	}
}
```


**Failed Response  JSON Sample**  

```
{
	"businessCode": 1740,
	"message": "Your terminal (SN:sn0101012237) already exists"
}
```

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

<br>

### Update a terminal  

<table>
<tr>
<td>Endpoint</td>
<td>/v1/3rdsys/terminals/{terminalId}</td>
</tr>
<tr>
<td>Request Method</td>
<td>PUT</td>
</tr>
<tr>
<td>Path Parameter(s)</td>
<td>terminalId</td>
</tr>
<tr>
<td>Query Parameter(s)</td>
<td>sysKey, timestamp</td>
</tr>
<tr>
<td>Request Body Type</td>
<td>application/json</td>
</tr>
<tr>
<td>Response Body Type</td>
<td>application/json</td>
</tr>
<tr>
<td>Success Response Code</td>
<td>200</td>
</tr>
</table>

**Query Parameter(s) Description**  

| Name| Type | Nullable|Description |
|:--- | :---|:---|:---|
|sysKey|String|false|the apiKey|
|timestamp|String|false|currentTimeMillis|



**Path Parameter Description**


|Parameter Name|Type|Nullable|Description|
|:--|:--|:--|:--|
|terminalId|Long|false|id of terminal|


**Request Body Sample**  

```
{
	"name": "KFC-TML-JS",
	"serialNo": "AD0099",
	"merchantName": "KFC",
	"resellerName": "New York",
	"modelName": "A920",
	"location": "CN"
}
```

**Request JSON Description**

|Key Name|Type|Nullable|Description|
|:--|:--|:--|:--|
|name|String|false|The name of terminal, max length is 64.|
|tid|String|true|The tid of terminal. If it is empty system will generate a tid when creating. And the length range is from 8 to 15.|
|serialNo|String|true|The serial number of terminal. If the status is active the serial number is mandatory.|
|merchantName|String|true|The merchant of terminal belongs to. If the initial is active then merchantName is mandatory. The max length is 64. Make sure the merchant belongs to the given reseller|
|resellerName|String|false|The reseller of terminal belongs to. Max length is 64.|
|modelName|String|false|The model name of terminal. Max length is 64.|
|location|String|true|The location of terminal, max length is 32.|




**Successful Response Sample**

```
{
	"businessCode": 0,
	"data": {
		"id": 316995,
		"name": "KFC-TML-JS",
		"tid": "8QPMSNMV",
		"serialNo": "AD0099",
		"status": "P",
		"merchantName": "KFC",
		"modelName": "A920",
		"resellerName": "New York"
	}
}
```


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

<br>

### Activate a terminal  

Activate terminal API allows the thirdparty system to activate a terminal by terminal id.
If activate successfully there's not response content from remote server.  


<table>
<tr>
<td>Endpoint</td>
<td>/v1/3rdsys/terminals/{terminalId}/active</td>
</tr>
<tr>
<td>Request Method</td>
<td>PUT</td>
</tr>
<tr>
<td>Path Parameter(s)</td>
<td>terminalId</td>
</tr>
<tr>
<td>Query Parameter(s)</td>
<td>sysKey, timestamp</td>
</tr>
<tr>
<td>Success Response Code</td>
<td>204</td>
</tr>
</table>

If activate terminal successfully there's no response content from remote server. 


**Query Parameter(s) Description**  

| Name| Type | Nullable|Description |
|:--- | :---|:---|:---|
|sysKey|String|false|the apiKey|
|timestamp|String|false|currentTimeMillis|



**Path Parameter Description**

|Parameter Name|Type|Nullable|Description|
|:--|:--|:--|:--|
|terminalId|Long|false|id of terminal|


**Failed Response JSON Sample**

```
{
	"businessCode": 1800,
	"message": "Terminal not found"
}
```

**Possible business codes**

|Business Code|Message|Description|
|:--|:--|:--|
|1800|Terminal not found|&nbsp;|
|1893|The terminal has already been activated!|&nbsp;|
|1802|Terminal SN is mandatory|&nbsp;|
|1804|Terminal merchant is mandatory|&nbsp;|
|1700|Model doesn't exist|&nbsp;|
|1713|The associated model is not activate|&nbsp;|

<br>


### Activate a terminal(terminal id as query parameter instead of path parameter)

Activate terminal API allows the thirdparty system to activate a terminal by terminal id.
If activate successfully there's not response content from remote server.  


<table>
<tr>
<td>Endpoint</td>
<td>/v1/3rdsys/terminals/active</td>
</tr>
<tr>
<td>Request Method</td>
<td>PUT</td>
</tr>
<tr>
<td>Path Parameter(s)</td>
<td></td>
</tr>
<tr>
<td>Query Parameter(s)</td>
<td>terminalId, sysKey, timestamp</td>
</tr>
<tr>
<td>Success Response Code</td>
<td>204</td>
</tr>
</table>

If activate terminal successfully there's no response content from remote server. 


**Query Parameter(s) Description**  

| Name| Type | Nullable|Description |
|:--- | :---|:---|:---|
|terminalId|Long|false|terminal id|
|sysKey|String|false|the apiKey|
|timestamp|String|false|currentTimeMillis|




**Failed Response JSON Sample**

```
{
	"businessCode": 1800,
	"message": "Terminal not found"
}
```

**Possible business codes**

|Business Code|Message|Description|
|:--|:--|:--|
|1800|Terminal not found|&nbsp;|
|1893|The terminal has already been activated!|&nbsp;|
|1802|Terminal SN is mandatory|&nbsp;|
|1804|Terminal merchant is mandatory|&nbsp;|
|1700|Model doesn't exist|&nbsp;|
|1713|The associated model is not activate|&nbsp;|

<br>

### Disable a terminal  

<table>
<tr>
<td>Endpoint</td>
<td>/v1/3rdsys/terminals/{terminalId}/disable</td>
</tr>
<tr>
<td>Request Method</td>
<td>PUT</td>
</tr>
<tr>
<td>Path Parameter(s)</td>
<td>terminalId</td>
</tr>
<tr>
<td>Query Parameter(s)</td>
<td>sysKey, timestamp</td>
</tr>
<tr>
<td>Success Response Code</td>
<td>204</td>
</tr>
</table>



If disable successfully there's no response content from remote server. 


**Query Parameter(s) Description**  

| Name| Type | Nullable|Description |
|:--- | :---|:---|:---|
|sysKey|String|false|the apiKey|
|timestamp|String|false|currentTimeMillis|


**Path Parameter Description**

|Parameter Name|Type|Nullable|Description|
|:--|:--|:--|:--|
|terminalId|Long|false|id of terminal|


**Failed Response JSON Sample **

```
{
	"businessCode": 1888,
	"message": "The terminal is not active,unable to disable!"
}
```

**Possible business codes**

|Business Code|Message|Description|
|:--|:--|:--|
|1800|Terminal not found|&nbsp;|
|1888|The terminal is not active,unable to disable!|&nbsp;|

<br>

### Delete a terminal  

<table>
<tr>
<td>Endpoint</td>
<td>/v1/3rdsys/terminals/{terminalId}</td>
</tr>
<tr>
<td>Request Method</td>
<td>DELETE</td>
</tr>
<tr>
<td>Path Parameter(s)</td>
<td>terminalId</td>
</tr>
<tr>
<td>Query Parameter(s)</td>
<td>sysKey, timestamp</td>
</tr>
<tr>
<td>Success Response Code</td>
<td>204</td>
</tr>
</table>


If delete terminal successfully there's not response content from remote server. There's response content which include the business code and message only when delete terminal failed.  

**Query Parameter(s) Description**  

| Name| Type | Nullable|Description |
|:--- | :---|:---|:---|
|sysKey|String|false|the apiKey|
|timestamp|String|false|currentTimeMillis|

**Path Parameter Description**

|Parameter Name|Type|Nullable|Description|
|:--|:--|:--|:--|
|terminalId|Long|false|id of terminal|


**Failed Response JSON Sample**
```
{
	"businessCode": 1877,
	"message": "The terminal is active,unable to delete!"
}
```


**Possible business codes**

|Business Code|Message|Description|
|:--|:--|:--|
|1800|Terminal not found|&nbsp;|
|1877|The terminal is active,unable to delete!|&nbsp;|
