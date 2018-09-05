## Reseller APIs



### Search resellers  

Search reseller by page according to name and status.

<table>
<tr>
<td>Endpoint</td>
<td>/v1/3rdsys/resellers</td>
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
<td>pageNo, limit, orderBy, name, status, sysKey, timestamp</td>
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
|name|String|true|search by name|
|status|String|true|the reseller status<br/> the value can be A(Active), P(Pending), S(Suspend)|
|sysKey|String|false|the apiKey|
|timestamp|String|false|currentTimeMillis|


**Sample Code**  

```
public static void testSearchReseller() {
	RestClient client = new RestClient("https://api.whatspos.cn/p-market-api/");
	RestRequest request = new RestRequest("/v1/3rdsys/resellers", Method.GET);
	request.AddParameter("sysKey", "ZJFXJAG7SJXPPESKVAPO");
	request.AddParameter("pageNo","1");
	request.AddParameter("limit","20");
	request.AddParameter("orderBy","name");
	request.AddParameter("name","reseller");
	request.AddParameter("status","P");
	String querystr = getQueryString(client, request);
	String signature = generateSignature("AXN5ES2BFYYY8FRMSAPXKQ2ZMT22WYTQGCOGGFM9", querystr);
	request.AddHeader("signature", signature);
	IRestResponse response = client.Execute(request);
	var responseContent = response.Content;
	Console.WriteLine(responseContent);
}

public static string Hex(byte[] data) {
	var sb = new StringBuilder();
	foreach (var b in data)
	sb.Append(b.ToString("x2"));
	return sb.ToString();
}

public static String generateSignature(String secret, String strToSign) {
	String signature = Hex(new HMACMD5(Encoding.UTF8.GetBytes(secret)).ComputeHash(
	                           Encoding.UTF8.GetBytes(strToSign))).ToUpper();
	return signature;
}

```



**Success Response Sample**

```
{
	"businessCode": 0,
	"dataset": [{
		"id": 17850,
		"name": "FVFFF",
		"phone": "87879696",
		"country": "CN",
		"postcode": null,
		"address": null,
		"company": null,
		"contact": "FFF",
		"email": "FF@1234.COM",
		"status": "S"
	}, {
		"id": 8736,
		"name": "Max",
		"phone": "123",
		"country": "CN",
		"postcode": "123",
		"address": null,
		"company": null,
		"contact": "Max",
		"email": "zhihao_w@qq.com",
		"status": "S"
	}],
	"limit": 10,
	"pageNo": 1,
	"totalCount": 2,
	"hasNext": false
}
```

<br>  

### Get a reseller  

Get reseller by reseller id. 

<table>
<tr>
<td>Endpoint</td>
<td>/v1/3rdsys/resellers/{resellerId}</td>
</tr>
<tr>
<td>Request Method</td>
<td>GET</td>
</tr>
<tr>
<td>Path Parameter(s)</td>
<td>resellerId</td>
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
|resellerId|Long|false|the id of reseller|


**Sample Code**  

```
public static void testGetResellerById() {
	RestClient client = new RestClient("https://api.whatspos.cn/p-market-api/");
	RestRequest requestGet = new RestRequest("/v1/3rdsys/resellers/{resellerId}", Method.GET);
	requestGet.AddParameter("sysKey", "ZJFXJAG7SJXPPESKVAPO");
	requestGet.AddUrlSegment("resellerId", "1000000211");
	String querystr = getQueryString(client, requestGet);
	String signature = generateSignature("AXN5ES2BFYYY8FRMSAPXKQ2ZMT22WYTQGCOGGFM9", querystr);
	requestGet.AddHeader("signature", signature);
	IRestResponse response = client.Execute(requestGet);
	var responseContent = response.Content;
	Console.WriteLine(responseContent);
}

public static string Hex(byte[] data) {
	var sb = new StringBuilder();
	foreach (var b in data)
	sb.Append(b.ToString("x2"));
	return sb.ToString();
}

public static String generateSignature(String secret, String strToSign) {
	String signature = Hex(new HMACMD5(Encoding.UTF8.GetBytes(secret)).ComputeHash(
	                           Encoding.UTF8.GetBytes(strToSign))).ToUpper();
	return signature;
}

```


**Success Response Sample**  

```
{
	"businessCode": 0,
	"data": {
		"id": 17850,
		"name": "FVFFF",
		"phone": "87879696",
		"country": "CN",
		"postcode": null,
		"address": null,
		"company": null,
		"contact": "FFF",
		"email": "FF@1234.COM",
		"status": "S",
		"entityAttributeValues": {
			"111": "tan2"
		},
		"parent": {
			"id": 4151,
			"name": "New York"
		}
	}
}
```

**Error Response Sample**  

```
{
	"businessCode": 1759,
	"message": "Reseller doesn't exist"
}
```


<br>

**Success Response JSON Description**  

Note: As the businessCode for success response always 0, so the below table only describs the values in data field of the JSON.

|Key Name|Value Type|Description|
|:--|:--|:--|
|id|Long|the id of reseller|
|name|String|the name of reseller|
|phone|String|the phone number of reseller|
|country|String|the country code|
|postcode|String|the postcode of reseller|
|email|String|the email of reseller|
|status|String|the status of reseller, value can be one of A(Active), P(Pendding) and S(Suspend)|
|parent|Object|reseller's parent|
|parent.id|Long|id of parent reseller|
|parent.name|String|name of parent reseller|
|entityAttributeValues|LinkedHashMap&lt;String, String&gt;|dynamic attributes|
<br>




**Possible business codes**

|Business Code|Message|Description|
|:--|:--|:--|
|1759|Reseller doesn't exist|&nbsp;|

<br>

### Create a reseller  


<table>
<tr>
<td>Endpoint</td>
<td>/v1/3rdsys/resellers</td>
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


**Sample Code**  

```
public static void testCreateReseller() {
	RestClient client = new RestClient("https://api.whatspos.cn/p-market-api/");
	RestRequest request = new RestRequest("/v1/3rdsys/resellers",Method.POST);
	request.AddParameter("sysKey", "ZJFXJAG7SJXPPESKVAPO", ParameterType.QueryString);
	var reseller = new {
		name = "reseller_003",
		email = "sam2@gmail.com",
		country = "CN",
		contact = "sam",
		phone = "89894545",
		postcode = "8954",
		address = "JiangSu Suzhou city xinghujie 203#",
		company = "pax"
	};
	var resellerJson = request.JsonSerializer.Serialize(reseller);
	request.AddParameter("application/json; charset=utf-8", resellerJson, ParameterType.RequestBody);
	String querystr = getQueryString(client, request);
	String signature = generateSignature("AXN5ES2BFYYY8FRMSAPXKQ2ZMT22WYTQGCOGGFM9", querystr);
	request.AddHeader("signature", signature);
	IRestResponse response = client.Execute(request);
	var responseContent = response.Content;
	Console.WriteLine(responseContent);
}

public static string Hex(byte[] data) {
	var sb = new StringBuilder();
	foreach (var b in data)
	sb.Append(b.ToString("x2"));
	return sb.ToString();
}

public static String generateSignature(String secret, String strToSign) {
	String signature = Hex(new HMACMD5(Encoding.UTF8.GetBytes(secret)).ComputeHash(
	                           Encoding.UTF8.GetBytes(strToSign))).ToUpper();
	return signature;
}

```



**Request Body Sample**  

```
{
	"name": "reseller_001",
	"email": "sam2@gmail.com",
	"country": "CN",
	"contact": "Sam",
	"phone": "87879696",
	"postcode": "850212",
	"address": "JiangSu Suzhou city xinghujie 203#",
	"company": "Cam",
	"parentResellerName": "New York",
	"entityAttributeValues": {
		"111": "tan2"
	}
}
```

**Request Body Description**



|Key Name|Value Type|Nullable|Description|
|:--|:--|:--|:--|
|name|String|false|Name of reseller, max length is 64.|
|email|String|false|Email of reseller, max length is 255.|
|country|String|false|Country code of reseller, max length is 64.|
|contact|String|false|contact of reseller, max length is 64.|
|phone|String|true|Phone number of reseller, max length is 32. Sample value 400-86554555.|
|postcode|String|true|Post code, max length is 32. Sample value 510250.|
|address|String|true|Address of reseller, max length is 255.|
|company|String|true|Company of reseller, max length is 255.|
|parentResellerName|String|true|Parent reseller name, if it is empty will set the root reseller of current marketplace as the parent reseller|
|entityAttributeValues|Map&lt;String, String&gt;|false|Dynamic attributes. Whether the attributes is required or not depends on the attributes configuration.|



**Successful Response Sample**

```
{
	"businessCode": 0,
	"data": {
		"id": 51745,
		"name": "reseller_001",
		"phone": "87879696",
		"country": "CN",
		"postcode": "850212",
		"address": "JiangSu Suzhou city xinghujie 203#",
		"company": "Cam",
		"contact": "Sam",
		"email": "sam2@gmail.com",
		"status": "P",
		"entityAttributeValues": {
			"111": "tan2"
		},
		"parent": {
			"id": 4151,
			"name": "New York"
		}
	}
}
```



**Possible business codes**

|Business Code|Message|Description|
|:--|:--|:--|
|1778|Parent reseller not found|&nbsp;|
|1779|Parent reseller is not active|&nbsp;|
|1782|At most 10 level resellers are supported|&nbsp;|
|1762|Reseller name is mandatory|&nbsp;|
|1763|Reseller contact is mandatory|&nbsp;|
|1764|Reseller phone is mandatory|&nbsp;|
|1765|Reseller email is mandatory|&nbsp;|
|1606|Country is mandatory|&nbsp;|
|1767|Reseller name is too long|&nbsp;|
|1768|Reseller contact is too long|&nbsp;|
|1769|Reseller phone is too long|&nbsp;|
|1770|Reseller email is too long|&nbsp;|
|1618|Postcode is too long|&nbsp;|
|1619|Address is too long|&nbsp;|
|1771|Reseller company is too long|&nbsp;|
|1105|Email is invalid|&nbsp;|
|1112|Phone No. is invalid|&nbsp;|
|1624|The name cannot contain special characters|&nbsp;|
|3400|Country code is invalid|&nbsp;|

<br>

### Update a reseller  

<table>
<tr>
<td>Endpoint</td>
<td>/v1/3rdsys/resellers/{resellerId}</td>
</tr>
<tr>
<td>Request Method</td>
<td>PUT</td>
</tr>
<tr>
<td>Path Parameter(s)</td>
<td>resellerId</td>
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
|resellerId|Long|false|Reseller's id.|


```
public static void testUpdateReseller() {
	RestClient client = new RestClient("https://api.whatspos.cn/p-market-api/");
	RestRequest request = new RestRequest("/v1/3rdsys/resellers/{resellerId}",Method.PUT);
	request.AddParameter("sysKey", "ZJFXJAG7SJXPPESKVAPO", ParameterType.QueryString);
	request.AddUrlSegment("resellerId","1000000225");
	var reseller = new {
		name = "reseller_002_ext",
		email = "sam2@gmail.com",
		country = "CN",
		contact = "sam",
		phone = "89894545",
		postcode = "8954",
		address = "JiangSu Suzhou city xinghujie 203#",
		parentResellerName = "reseller_001",
		company = "pax"
	};
	var resellerJson = request.JsonSerializer.Serialize(reseller);
	request.AddParameter("application/json; charset=utf-8", resellerJson, ParameterType.RequestBody);
	String querystr = getQueryString(client, request);
	String signature = generateSignature("AXN5ES2BFYYY8FRMSAPXKQ2ZMT22WYTQGCOGGFM9", querystr);
	request.AddHeader("signature", signature);
	IRestResponse response = client.Execute(request);
	var responseContent = response.Content;
}

public static string Hex(byte[] data) {
	var sb = new StringBuilder();
	foreach (var b in data)
	sb.Append(b.ToString("x2"));
	return sb.ToString();
}

public static String generateSignature(String secret, String strToSign) {
	String signature = Hex(new HMACMD5(Encoding.UTF8.GetBytes(secret)).ComputeHash(
	                           Encoding.UTF8.GetBytes(strToSign))).ToUpper();
	return signature;
}

```


**Request JSON Sample**  

```
{
	"name": "FVFFF",
	"email": "FF@1234.COM",
	"country": "CN",
	"contact": "FFF",
	"phone": "87879696",
	"postcode": "850212",
	"address": "JiangSu Suzhou city xinghujie 203#",
	"company": "Cam",
	"parentResellerName": "New York",
	"entityAttributeValues": {
		"111": "tan2"
	}
}
```

**Request JSON Description**

|Key Name|Value Type|Nullable|Description|
|:--|:--|:--|:--|
|name|String|false|Name of reseller, max length is 64.|
|email|String|false|Email of reseller, max length is 255.|
|country|String|false|Country code of reseller, max length is 64.|
|contact|String|false|contact of reseller, max length is 64.|
|phone|String|true|Phone number of reseller, max length is 32. Sample value 400-86554555.|
|postcode|String|true|Post code, max length is 32. Sample value 510250.|
|address|String|true|Address of reseller, max length is 255.|
|company|String|true|Company of reseller, max length is 255.|
|parentResellerName|String|true|Parent reseller name, if it is empty will set the root reseller of current marketplace as the parent reseller. If the status of the updated reseller is active or suspend the parent cannot be changed.|
|entityAttributeValues|Map&lt;String, String&gt;|false|Dynamic attributes. Whether the attributes is required or not depends on the attributes configuration.|  





**Successful Response Sample**

```
{
	"businessCode": 0,
	"data": {
		"id": 51745,
		"name": "FVFFF",
		"phone": "87879696",
		"country": "CN",
		"postcode": "850212",
		"address": "JiangSu Suzhou city xinghujie 203#",
		"company": "Cam",
		"contact": "FFF",
		"email": "FF@1234.COM",
		"status": "P",
		"entityAttributeValues": {
			"111": "tan2"
		},
		"parent": {
			"id": 4151,
			"name": "New York"
		}
	}
}
```


**Possible business codes**

|Business Code|Message|Description|
|:--|:--|:--|
|1759|Reseller doesn't exist|&nbsp;|
|1778|Parent reseller not found|&nbsp;|
|1762|Reseller name is mandatory|&nbsp;|
|1764|Reseller phone is mandatory|&nbsp;|
|1606|Country is mandatory|&nbsp;|
|1763|Reseller contact is mandatory|&nbsp;|
|1765|Reseller email is mandatory|&nbsp;|
|1767|Reseller name is too long|&nbsp;|
|1769|Reseller phone is too long|&nbsp;|
|1768|Reseller contact is too long|&nbsp;|
|1618|Postcode is too long|&nbsp;|
|1619|Address is too long|&nbsp;|
|1771|Reseller company is too long|&nbsp;|
|1770|Reseller email is too long|&nbsp;|
|1105|Email is invalid|&nbsp;|
|1112|Phone No. is invalid|&nbsp;|
|1624|The name cannot contain special characters|&nbsp;|
|3400|Country code is invalid|&nbsp;|
|1938|Reseller is active or suspend, parent cannot be updated!|&nbsp;|

<br>

### Activate a reseller  

<table>
<tr>
<td>Endpoint</td>
<td>/v1/3rdsys/resellers/{resellerId}/active</td>
</tr>
<tr>
<td>Request Method</td>
<td>PUT</td>
</tr>
<tr>
<td>Path Parameter(s)</td>
<td>resellerId</td>
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

If activate reseller successfully there's no response content from remote server.   

**Query Parameter(s) Description**  

| Name| Type | Nullable|Description |
|:--- | :---|:---|:---|
|sysKey|String|false|the apiKey|
|timestamp|String|false|currentTimeMillis|



**Path Parameter Description**

|Parameter Name|Type|Nullable|Description|
|:--|:--|:--|:--|
|resellerId|Long|false|The reseller's id.|


```
public static void testActivateReseller() {
	RestClient client = new RestClient("https://api.whatspos.cn/p-market-api/");
	RestRequest request = new RestRequest("/v1/3rdsys/resellers/{resellerId}/active",Method.PUT);
	request.AddParameter("sysKey", "ZJFXJAG7SJXPPESKVAPO", ParameterType.QueryString);
	request.AddUrlSegment("resellerId","1000000225");
	String querystr = getQueryString(client, request);
	String signature = generateSignature("AXN5ES2BFYYY8FRMSAPXKQ2ZMT22WYTQGCOGGFM9", querystr);
	request.AddHeader("signature", signature);
	IRestResponse response = client.Execute(request);
	Console.WriteLine(response.StatusCode==HttpStatusCode.NoContent?"Activate reseller success":"Activate reseller fail");
	if(response.StatusCode!=HttpStatusCode.NoContent) {
		Console.WriteLine(response.Content);
	}
}

public static string Hex(byte[] data) {
	var sb = new StringBuilder();
	foreach (var b in data)
	sb.Append(b.ToString("x2"));
	return sb.ToString();
}

public static String generateSignature(String secret, String strToSign) {
	String signature = Hex(new HMACMD5(Encoding.UTF8.GetBytes(secret)).ComputeHash(
	                           Encoding.UTF8.GetBytes(strToSign))).ToUpper();
	return signature;
}

```


**Possible business codes**

|BusinessCode|Message|Description|
|:--|:--|:--|
|1759|Reseller doesn't exist|The input reseller id not correct.|
|1891|The reseller has already been activated!|&nbsp;|
|1894|The reseller's parent is not active|&nbsp;|

<br>

### Disable a reseller  

<table>
<tr>
<td>Endpoint</td>
<td>/v1/3rdsys/resellers/{resellerId}/disable</td>
</tr>
<tr>
<td>Request Method</td>
<td>PUT</td>
</tr>
<tr>
<td>Path Parameter(s)</td>
<td>resellerId</td>
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
|resellerId|Long|false|The reseller's id.|



```
public static void testDisableReseller() {
	RestClient client = new RestClient("https://api.whatspos.cn/p-market-api/");
	RestRequest request = new RestRequest("/v1/3rdsys/resellers/{resellerId}/disable",Method.PUT);
	request.AddParameter("sysKey", "ZJFXJAG7SJXPPESKVAPO", ParameterType.QueryString);
	request.AddUrlSegment("resellerId","1000000225");
	String querystr = getQueryString(client, request);
	String signature = generateSignature("AXN5ES2BFYYY8FRMSAPXKQ2ZMT22WYTQGCOGGFM9", querystr);
	request.AddHeader("signature", signature);
	IRestResponse response = client.Execute(request);
	Console.WriteLine(response.StatusCode==HttpStatusCode.NoContent?"Disable reseller success":"Disable reseller fail");
	if(response.StatusCode!=HttpStatusCode.NoContent) {
		Console.WriteLine(response.Content);
	}
}

public static string Hex(byte[] data) {
	var sb = new StringBuilder();
	foreach (var b in data)
	sb.Append(b.ToString("x2"));
	return sb.ToString();
}

public static String generateSignature(String secret, String strToSign) {
	String signature = Hex(new HMACMD5(Encoding.UTF8.GetBytes(secret)).ComputeHash(
	                           Encoding.UTF8.GetBytes(strToSign))).ToUpper();
	return signature;
}

```



**Possible business codes**

|BusinessCode|Message|Description|
|:--|:--|:--|
|1759|Reseller doesn't exist|The input reseller id not correct.|
|1886|The reseller is not active,unable to disable!|&nbsp;|
|1793|The reseller has active merchants|&nbsp;|
|1794|The reseller has active terminals|&nbsp;|
|1795|The reseller has active terminal groups|&nbsp;|
|1781|The reseller has active sub-resellers|&nbsp;|

<br>

### Delete a reseller  

<table>
<tr>
<td>Endpoint</td>
<td>/v1/3rdsys/resellers/{resellerId}</td>
</tr>
<tr>
<td>Request Method</td>
<td>DELETE</td>
</tr>
<tr>
<td>Path Parameter(s)</td>
<td>resellerId</td>
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


If delete reseller successfully there's not response content from remote server. There's response content which include the business code and message only when delete reseller failed.  

**Query Parameter(s) Description**  

| Name| Type | Nullable|Description |
|:--- | :---|:---|:---|
|sysKey|String|false|the apiKey|
|timestamp|String|false|currentTimeMillis|

**Path Parameter Description**

|Parameter Name|Type|Nullable|Description|
|:--|:--|:--|:--|
|resellerId|Long|false|The reseller's id.|


```
public static void testDeleteReseller() {
	RestClient client = new RestClient("https://api.whatspos.cn/p-market-api/");
	RestRequest request = new RestRequest("/v1/3rdsys/resellers/{resellerId}",Method.DELETE);
	request.AddParameter("sysKey", "ZJFXJAG7SJXPPESKVAPO", ParameterType.QueryString);
	request.AddUrlSegment("resellerId","1000000218");
	String querystr = getQueryString(client, request);
	String signature = generateSignature("AXN5ES2BFYYY8FRMSAPXKQ2ZMT22WYTQGCOGGFM9", querystr);
	request.AddHeader("signature", signature);
	IRestResponse response = client.Execute(request);
	Console.WriteLine(response.StatusCode==HttpStatusCode.NoContent?"Delete reseller success":"Delete reseller fail");
	if(response.StatusCode!=HttpStatusCode.NoContent) {
		Console.WriteLine(response.Content);
	}
}

public static string Hex(byte[] data) {
	var sb = new StringBuilder();
	foreach (var b in data)
	sb.Append(b.ToString("x2"));
	return sb.ToString();
}

public static String generateSignature(String secret, String strToSign) {
	String signature = Hex(new HMACMD5(Encoding.UTF8.GetBytes(secret)).ComputeHash(
	                           Encoding.UTF8.GetBytes(strToSign))).ToUpper();
	return signature;
}

```


**Failed Response JSON Sample**
```
{
	"businessCode": 1759,
	"message": "Reseller doesn't exist"
}
```


**Possible business codes**

|BusinessCode|Message|Description|
|:--|:--|:--|
|1759|Reseller doesn't exist|The input reseller id not correct.|
|1875|The reseller is active,unable to delete!|&nbsp;|
|1775|Not allowed to delete the reseller of current user|&nbsp;|
|1761|Reseller has been used by merchant|&nbsp;|
|1785|The reseller has been used by terminal|&nbsp;|
|1788|The reseller has been used by terminal group|&nbsp;|
|1780|The reseller has sub-resellers|&nbsp;|
