## Merchant Category APIs 

The merchant category APIs allow thirdparty system get merchant categories by name, create merchante category, update merchant category, delete merchant category and batch create merchant categories.


### Search merchant categories  

The search merchant category API allows thirdparty system to search the merchant categories by name. 


<table>
<tr>
<td>Endpoint</td>
<td>/v1/3rdsys/merchantCategories</td>
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
<td>name, sysKey, timestamp</td>
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
|name|String|true|name of merchant category, if name is null API will return all the merchant categories|
|sysKey|String|false|the apiKey|
|timestamp|String|false|currentTimeMillis|



**Success Response JSON Sample**

```
{
    "businessCode": 0,
    "data": [
        {
            "id": 1,
            "name": "restaurant",
            "remarks": "restaurant"
        },
        {
            "id": 2,
            "name": "Fast Food",
            "remarks": "Fast Food"
        }
    ]
}
```

<br>

### Create a merchant category


<table>
<tr>
<td>Endpoint</td>
<td>/v1/3rdsys/merchantCategories</td>
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
	"name": "Retail",
	"remarks": "Retail"
}
```

**Request Body Description**


|Key Name|Type|Nullable|Description|
|:--|:--|:--|:--|
|name|String|false|Merchant category name, max length is 128|
|remarks|String|true|Merchant category remarks, max length is 255|


**Successful Response JSON Sample**  

```
{
	"businessCode": 0,
	"data": {
		"id": 3,
		"name": "Retail",
		"remarks": "Retail"
	}
}
```

**Success Response JSON Description**  
Note: As the businessCode for success response always 0, so the below table only describs the values in data field of the JSON.

|Key Name|Type|Description|
|:--|:--|:--|
|id|Long|Id of merchant category.|
|name|String|Name of merchant category|
|remarks|String|Remark of merchant category|



**Error Response Sample**  

```
{
	"businessCode": 16001,
	"message": "Merchant category name already exists"
}
```



**Possible business codes**

|Business Code|Message|Description|
|:--|:--|:--|
|16001|Merchant category name already exists|&nbsp;|
|16002|Merchant category name is mandatory|&nbsp;|
|16003|Merchant category name is too long|&nbsp;|
|16004|Merchant category remarks is too long|&nbsp;|




<br>


<br>

### Update merchant category  


<table>
<tr>
<td>Endpoint</td>
<td>/v1/3rdsys/merchantCategories/{merchantCategoryId}</td>
</tr>
<tr>
<td>Request Method</td>
<td>PUT</td>
</tr>
<tr>
<td>Path Parameter(s)</td>
<td>merchantCategoryId</td>
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
|merchantCategoryId|Long|false|id of merchant category|


**Request JSON Sample**  

```
{
	"name": "Fast Foods",
	"remarks": "Fast Food remarks"
}
```

**Request JSON Description**

|Key Name|Value Type|Nullable|Description|
|:--|:--|:--|:--|
|name|String|false|name of merchant category, max length is 128|
|remarks|String|true|remarks of merchant category, max length is 255.|






**Successful Response Sample**

```
{
	"businessCode": 0,
	"data": {
		"id": 2,
		"name": "Fast Foods",
		"remarks": "Fast Food remarks"
	}
}
```


**Failed Response Sample**

```
{
	"businessCode": 16001,
	"message": "Merchant category name already exists"
}
```


**Possible business codes**

|Business Code|Message|Description|
|:--|:--|:--|
|16000|Merchant category not found|&nbsp;|
|16001|Merchant category name already exists|&nbsp;|
|16002|Merchant category name is mandatory|&nbsp;|
|16003|Merchant category name is too long|&nbsp;|
|16004|Merchant category remarks is too long|&nbsp;|


<br>

### Batch create merchant categories


<table>
<tr>
<td>Endpoint</td>
<td>/v1/3rdsys/merchantCategories/batch</td>
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
<td>skipExist, sysKey, timestamp</td>
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
|skipExist|boolean|true|if the value is true, then the name of category in create list exist in system will skip when create, if the value is false then all the categories in create list won't created in system. The default value is false.|
|sysKey|String|false|the apiKey|
|timestamp|String|false|currentTimeMillis|






**Request Body Sample**  

```
[{
	"name": "Retail",
	"remarks": "Retail remarks"
}, {
	"name": "Fast food"
}, {
	"name": "Realty",
	"remarks": "Realty"
}]
```

**Successful Response JSON Sample**  

```
{
	"businessCode": 0,
	"data": [{
		"id": 11,
		"name": "Retail11",
		"remarks": "Retail remarks"
	}, {
		"id": 12,
		"name": "Fast food22",
		"remarks": "Retail remarks"
	}, {
		"id": 13,
		"name": "Realty33",
		"remarks": "Realty"
	}]
}
```


**Failed Response JSON Sample**

```
{
	"businessCode": 16009,
	"message": "Merchant name(s) Retail in create list already exists in system"
}
```  





**Possible business codes**

|Business Code|Message|Description|
|:--|:--|:--|
|16005|Batch create merchant category list is empty|&nbsp;|
|16006|All the names in batch create list cannot be empty|&nbsp;|
|16007|Merchant category names {0} in create list is too long|Note: {0} in message will be replaced by category name(s)|
|16008|Merchant category remarks {0} in create list is too longg|Note: {0} in message will be replaced by category remarks(s)|
|16009|Merchant category name(s) {0} in create list already exists in system|Note: {0} in the message will be replaced by merchante category name in the create list which is already exist in system.|




<br>

### Delete a merchant category 

<table>
<tr>
<td>Endpoint</td>
<td>/v1/3rdsys/merchantCategories/{merchantCategoryId}</td>
</tr>
<tr>
<td>Request Method</td>
<td>DELETE</td>
</tr>
<tr>
<td>Path Parameter(s)</td>
<td>merchantCategoryId</td>
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

**Path Parameter Description**


|Parameter Name|Type|Nullable|Description|
|:--|:--|:--|:--|
|merchantCategoryId|Long|false|id of merchant category|


If delete merchant category successfully there's not response content from remote server. There's response content which include the business code and message only when delete merchant category failed.  

**Query Parameter(s) Description**  

| Name| Type | Nullable|Description |
|:--- | :---|:---|:---|
|sysKey|String|false|the apiKey|
|timestamp|String|false|currentTimeMillis|

**Failed Response JSON Sample**  

```
{
	"businessCode": 16000,
	"message": "Merchant category not found"
}
```


**Possible business codes**


|Business Code|Message|Description|
|:--|:--|:--|
|16000|Merchant category not found|&nbsp;|




