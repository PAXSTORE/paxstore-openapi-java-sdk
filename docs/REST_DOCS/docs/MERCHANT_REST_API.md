## Manage merchants  

The merchant APIs allow thirdparty system search resellers, get a reseller, create/update a reseller, activate/disable a reseller and delete a exist reseller.   




### Search merchants  

The search merchants API allows thirdparty system to search merchants by page according to merchant name, status and resellerId.

<table>
<tr>
<td>Endpoint</td>
<td>/v1/3rdsys/merchants</td>
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
<td>pageNo, limit, orderBy, name, status, resellerId, sysKey, timestamp</td>
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
|name|String|true|search by merchant name|
|status|String|true|the merchant status<br/> the value can be A(Active), P(Pending), S(Suspend)|
|resellerId|String|true|the resellerId of merchant|
|sysKey|String|false|the apiKey|
|timestamp|String|false|currentTimeMillis|

**Success Response Sample**

```
{
	"businessCode": 0,
	"dataset": [{
		"id": 72590,
		"name": "merchant_kfc",
		"reseller": {
			"id": 4151,
			"name": "New York"
		},
		"country": "CN",
		"postcode": null,
		"address": null,
		"contact": "Jay",
		"email": "jay@pax.com",
		"phone": "444888",
		"status": "A",
		"description": null
	}],
	"limit": 10,
	"pageNo": 1,
	"totalCount": 1,
	"hasNext": false
}
```

<br>

### Get a merchant

Get merchant by merchant id. 

<table>
<tr>
<td>Endpoint</td>
<td>/v1/3rdsys/merchants/{merchantId}</td>
</tr>
<tr>
<td>Request Method</td>
<td>GET</td>
</tr>
<tr>
<td>Path Parameter(s)</td>
<td>merchantId</td>
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
|merchantId|Long|false|the id of merchant|

**Success Response Sample**  

```
{
	"businessCode": 0,
	"data": {
		"id": 72590,
		"name": "merchant_20180704_2fecc",
		"reseller": {
			"id": 4151,
			"name": "New York"
		},
		"country": "CN",
		"postcode": null,
		"address": null,
		"contact": "tanjie",
		"email": "tanjie2456@pax.com",
		"phone": "444888",
		"status": "A",
		"description": null,
		"entityAttributeValues": {
			"456": "tan2"
		},
		"merchantCategory": [{
			"id": 1,
			"name": "Fast Food"
		}]
	}
}
```

**Error Response Sample**  

```
{
	"businessCode": 1720,
	"message": "Merchant doesn't exist"
}
```


<br>
**Success Response JSON Description**  
Note: As the businessCode for success response always 0, so the below table only describs the values in data field of the JSON.

|Key Name|Type|Description|
|:--|:--|:--|
|id|Long|The id of merchant.|
|name|String|The name of merchant.|
|reseller|Json Object|The reseller of the merchant, reseller has id and name|
|country|String|Country code of merchant.|
|contact|String|Contact of merchant.|
|email|String|Email of merchant.|
|phone|String|Phone number of merchant.|
|status|String|Status of merchant. Value can be one of A(Active), P(Pendding) and S(Suspend)|
|entityAttributeValues|Json Object|Dynamic attributes of merchant. It is a set of key value pair|
|merchantCategory|Json Array|Categories of merchant, category has id and name|
<br>




**Possible business codes**

|Business Code|Message|Description|
|:--|:--|:--|
|1720|Merchant doesn't exist|&nbsp;|

<br>

### Create a merchant  

<table>
<tr>
<td>Endpoint</td>
<td>/v1/3rdsys/merchants</td>
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
	"name": "KFC_Nanjing",
	"email": "ta@pax.com",
	"resellerName": "New York",
	"contact": "sam",
	"country": "CN",
	"phone": "444888",
	"postcode": "5652",
	"address": "Jiangsu suzhou city Xinghongjie 328#",
	"description": "Merchant KFC Nanjing",
	"merchantCategoryNames": ["Fast Food"],
	"entityAttributeValues": {
		"456": "tan"
	}
}
```

**Request Body Description**


|Key Name|Type|Nullable|Description|
|:--|:--|:--|:--|
|name|String|false|Merchant name, max length is 64.|
|email|String|false|Email of merchant, max length is 255.|
|resellerName|String|false|Reseller name of merchant, max length is 64. Make sure the reseller exist.|
|contact|String|false|Contact of merchant, max length is 64.|
|country|String|false|Country code of merchant, max length is 64. Please refer to country codes table.|
|phone|String|true|Phone number of merchant, max length is 32.|
|postcode|String|true|Postcode of merchant, max length is 16.|
|address|String|true|Address of merchant, max length is 255.|
|description|String|true|Description of merchant, max length is 3000.|
|merchantCategoryNames|Array|true|Merchant categories. Make sure the categories are available.|
|entityAttributeValues|Json Object|true|Dynamic attributes of merchant. It is a set of key value pair. Whether the attribute is required or not depend on the configuration of attribute.|



**Successful Response Sample**

```
{
	"businessCode": 0,
	"data": {
		"id": 72513,
		"name": "KFC_Nanjing",
		"reseller": {
			"id": 4151,
			"name": "New York"
		},
		"country": "CN",
		"postcode": "5652",
		"address": "Jiangsu suzhou city Xinghongjie 328#",
		"contact": "sam",
		"email": "ta@pax.com",
		"phone": "444888",
		"status": "P",
		"description": "Merchant KFC Nanjing",
		"entityAttributeValues": {
			"456": "tan"
		},
		"merchantCategory": [{
			"id": 2,
			"name": "Fast Food"
		}]
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

**Failed Response Sample**

```
{
	"businessCode": 1721,
	"message": "Merchant name already exists"
}
```

<br>

### Update a merchant  


<table>
<tr>
<td>Endpoint</td>
<td>/v1/3rdsys/merchants/{merchantId}</td>
</tr>
<tr>
<td>Request Method</td>
<td>PUT</td>
</tr>
<tr>
<td>Path Parameter(s)</td>
<td>merchantId</td>
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
|merchantId|Long|false|id of merchant|


**Request JSON Sample**  

```
{
	"name": "KFC_Nanjing",
	"email": "jack@kfc.com.cn",
	"resellerName": "New York",
	"contact": "Jack",
	"country": "CN",
	"phone": "444866",
	"postcode": "5652",
	"address": "Jiangsu suzhou city Xinghongjie 328#",
	"description": "Merchant KFC Nanjing",
	"merchantCategoryNames": ["Fast Food"],
	"entityAttributeValues": {
		"456": "tan2"
	}
}
```

**Request JSON Description**

|Key Name|Value Type|Nullable|Description|
|:--|:--|:--|:--|
|name|String|false|Merchant name, max length is 64.|
|email|String|false|Email of merchant, max length is 255.|
|resellerName|String|false|Reseller name of merchant, max length is 64. Make sure the reseller exist.|
|contact|String|false|Contact of merchant, max length is 64.|
|country|String|false|Country code of merchant, max length is 64. Please refer to country codes table.|
|phone|String|true|Phone number of merchant, max length is 32.|
|postcode|String|true|Postcode of merchant, max length is 16.|
|address|String|true|Address of merchant, max length is 255.|
|description|String|true|Description of merchant, max length is 3000.|
|merchantCategoryNames|List&lt;String&gt;|true|Merchant categories. Make sure the categories are available.|
|entityAttributeValues|LinkedHashMap&lt;String, String&gt;|true|Dynamic attributes of merchant. Whether the attribute is required or not depend on the configuration of attribute.|





**Successful Response Sample**

```
{
	"businessCode": 0,
	"data": {
		"id": 72513,
		"name": "KFC_Nanjing",
		"reseller": {
			"id": 4151,
			"name": "New York"
		},
		"country": "CN",
		"postcode": "5652",
		"address": "Jiangsu suzhou city Xinghongjie 328#",
		"contact": "Jack",
		"email": "jack@kfc.com.cn",
		"phone": "444866",
		"status": "P",
		"description": "Merchant KFC Nanjing",
		"entityAttributeValues": {
			"456": "tan2"
		},
		"merchantCategory": [{
			"id": 2,
			"name": "Fast Food"
		}]
	}
}
```


**Failed Response Sample**

```
{
	"businessCode": 1112,
	"message": "Phone No. is invalid"
}
```


**Possible business codes**

|Business Code|Message|Description|
|:--|:--|:--|
|1720|Merchant doesn't exist|&nbsp;|
|1721|Merchant name already exists|&nbsp;|
|1759|Reseller doesn't exist|&nbsp;|
|16000|Merchant category not found|&nbsp;|
|1723|Merchant name is mandatory|&nbsp;|
|1606|Country is mandatory|&nbsp;|
|1726|Merchant contact is mandatory|&nbsp;|
|1727|Merchant email is mandatory|&nbsp;|
|1728|Merchant phone is mandatory|&nbsp;|
|1725|Merchant reseller is mandatory|&nbsp;|
|1729|Merchant name is too long|&nbsp;|
|1618|Postcode is too long|&nbsp;|
|1619|Address is too long|&nbsp;|
|1732|Merchant contact is too long|&nbsp;|
|1733|Merchant email is too long|&nbsp;|
|1734|Merchant phone is too long|&nbsp;|
|1736|Merchant description is too long|&nbsp;|
|1105|Email is invalid|&nbsp;|
|1112|Phone No. is invalid|&nbsp;|
|3400|Country code is invalid|&nbsp;|
|1927|The merchant is not inactive,reseller cannot be updated!|&nbsp;|
|1759|Reseller doesn't exist|&nbsp;|
|1773|The associated reseller is not activate|&nbsp;|


<br>

### Activate a merchant

<table>
<tr>
<td>Endpoint</td>
<td>/v1/3rdsys/merchants/{merchantId}/active</td>
</tr>
<tr>
<td>Request Method</td>
<td>PUT</td>
</tr>
<tr>
<td>Path Parameter(s)</td>
<td>merchantId</td>
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

If activate merchant successfully there's no response content from remote server. 

**Query Parameter(s) Description**  

| Name| Type | Nullable|Description |
|:--- | :---|:---|:---|
|sysKey|String|false|the apiKey|
|timestamp|String|false|currentTimeMillis|



**Path Parameter Description**

|Parameter Name|Type|Nullable|Description|
|:--|:--|:--|:--|
|merchantId|Long|false|id of merchant|

**Failed Response JSON Sample**

```
{
	"businessCode": 1892,
	"message": "The merchant has already been activated!"
}
```



**Possible business codes**

|Business Code|Message|Description|
|:--|:--|:--|
|1720|Merchant doesn't exist|&nbsp;|
|1759|Reseller doesn't exist|&nbsp;|
|1773|The associated reseller is not activate|&nbsp;|
|1892|The merchant has already been activated!|&nbsp;|

<br>

### Disable a merchant

<table>
<tr>
<td>Endpoint</td>
<td>/v1/3rdsys/merchants/{merchantId}/disable</td>
</tr>
<tr>
<td>Request Method</td>
<td>PUT</td>
</tr>
<tr>
<td>Path Parameter(s)</td>
<td>merchantId</td>
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
|merchantId|Long|false|id of merchant|



**Possible business codes**

|Business Code|Message|Description|
|:--|:--|:--|
|1720|Merchant doesn't exist|&nbsp;|
|1887|The merchant is not active,unable to disable!|&nbsp;|
|1797|The merchant has active terminals|&nbsp;|

<br>

### Delete a merchant  

<table>
<tr>
<td>Endpoint</td>
<td>/v1/3rdsys/merchants/{merchantId}</td>
</tr>
<tr>
<td>Request Method</td>
<td>DELETE</td>
</tr>
<tr>
<td>Path Parameter(s)</td>
<td>merchantId</td>
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


If delete merchant successfully there's not response content from remote server. There's response content which include the business code and message only when delete merchant failed.  

**Query Parameter(s) Description**  

| Name| Type | Nullable|Description |
|:--- | :---|:---|:---|
|sysKey|String|false|the apiKey|
|timestamp|String|false|currentTimeMillis|


**Failed Response JSON Sample**
```
{
	"businessCode": 1720,
	"message": "Merchant doesn't exist"
}
```


**Possible business codes**


|Business Code|Message|Description|
|:--|:--|:--|
|1720|Merchant doesn't exist|&nbsp;|
|1876|The merchant is active,unable to delete!|&nbsp;|
|1786|The merchant has been used by terminal|&nbsp;|



