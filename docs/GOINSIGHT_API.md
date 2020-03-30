## GoInsight APIs

GoInsight APIs allow third party system search app bizData form goInsight.

All the GoInsight related APIs are in the class *com.pax.market.api.sdk.java.api.goInsight.GoInsightApi*.   

**Constructors of GoInsightApi**

```
public GoInsightApi(String baseUrl, String apiKey, String apiSecret);
public GoInsightApi(String baseUrl, String apiKey, String apiSecret, Locale locale)
```

**Constructor parameters description**

|Name|Type|Description|
|:----|:----|:----|
|baseUrl|String|the base url of REST API|
|apiKey|String|the apiKey of marketplace, get this key from PAXSTORE admin/reseller console, refer to chapter Apply access rights|
|apiSecret|String|apiSecret, get api secret from PAXSTORE admin/reseller console, refer to chapter Apply access rights|
|locale|Locale|the locale, the default locale is Locale.ENGLISH, the language of message and errors in return object depend on locale|


### Search APP BizData

The search app bizData API allow the third party system search data.<br/>
Note: This result of this API depends on the API query settings in GoInsight. Paging needs to be set when the query result set type is a table.

**API**

```
public Result<DataQueryResultDTO> findDataFromInsight(String queryCode)
public Result<DataQueryResultDTO> findDataFromInsight(String queryCode, Integer pageNo, Integer pageSize)
```

**Input parameter(s) description**

| Name| Type | Nullable|Description |
|:---- | :----|:----|:----|
|queryCode|String|false|search by serial number,name and TID|
|pageNo|int|true|page number, value must >=1|
|pageSize|int|true|the record number per page, range in 10, 25, 50, 100|

**Sample codes**

```
GoInsightApi goInsightApi = new GoInsightApi("https://api.whatspos.com/p-market-api", "RCA9MDH6YN3WSSGPW6TJ", "TUNLDZVZECHNKZ4FW07XFCKN2W0N8ZDEA5ENKZYN");
Result<DataQueryResultDTO> resultData = goInsightApi.findDataFromInsight("ahh3y62t");
```

**Client side validation failed sample result(JSON formatted)**

```
{
	"businessCode": 36000,
	"validationErrors": ["The query code is not found"]
}
```

**Succssful sample result(JSON formatted)**

```
{
	"businessCode": 0,
	"data": {
        "worksheetName": "Merchant transaction amount trend (This Year)",
		"columns": [{
			"colName": "acquirer_type",
			"displayName": "Acquirer Type"
		}, {
			"colName": "currency",
			"displayName": "Currency"
		}, {
			"colName": "purchase_id",
			"displayName": "Purchase ID"
		}, {
			"colName": "amount",
			"displayName": "Amount"
		}, {
			"colName": "tax",
			"displayName": "Tax"
		}, {
			"colName": "_sys_marketid",
			"displayName": "Marketplace"
		}, {
			"colName": "_sys_merchantid",
			"displayName": "Merchant"
		}, {
			"colName": "_sys_terminalid",
			"displayName": "Terminal"
		}],
		"rows": [
			[{
				"colName": "acquirer_type",
				"value": "ZTO"
			}, {
				"colName": "currency",
				"value": "USD"
			}, {
                "colName": "purchase_id",
				"value": "15851195134847"
			}, {
                "colName": "amount",
				"value": "169.15"
			}, {
                "colName": "tax",
				"value": "64.38"
			}, {
				"colName": "_sys_marketid",
				"value": "demo"
			}, {
				"colName": "_sys_merchantid",
				"value": "Macy’s"
			}, {
				"colName": "_sys_terminalid",
				"value": "0820087295"
			}],
            [{
				"colName": "acquirer_type",
				"value": "ZTO"
			}, {
				"colName": "currency",
				"value": "USD"
			}, {
                "colName": "purchase_id",
				"value": "15851135975100"
			}, {
                "colName": "amount",
				"value": "2990.09"
			}, {
                "colName": "tax",
				"value": "64.12"
			}, {
				"colName": "_sys_marketid",
				"value": "demo"
			}, {
				"colName": "_sys_merchantid",
				"value": "Macy’s"
			}, {
				"colName": "_sys_terminalid",
				"value": "0820087295"
			}]
		],
        "offset": 10,
		"limit": 10,
		"hasNext": true,
	},
	"rateLimitRemain": ""
}
```

The type in dataSet of result is DataQueryResultDTO. The structure shows below.

Structure of class TerminalDTO

|Property Name|Type|Description|
|:---|:---|:---|
|worksheetName|String|The result set worksheet name.|
|columns|List<Column>|The result set column.|
|rows|List<List<Row>>|The result set.|
|hasNext|Boolean|Is there any data.|
|offset|int|Rows offset if exit page info.|
|limit|int|Rows page size if exit page info.|

Structure of class Column

|Property Name|Type|Description|
|:---|:---|:---|
|colName|String|The dataset filed name in GoInsight|
|displayName|String|The dataset filed's display name|

Structure of class Row  

|Property Name|Type|Description|
|:---|:---|:---|
|colName|String|The dataset filed name in GoInsight|
|value|Object|The dataset filed's value|

**Possible validation errors**
 
> <font color=red>pageSize:must be less than or equal to 1000</font>  

**Possible business codes**

|Business Code|Message|Description|
|:---|:---|:---|
|36000|The query code is not found|&nbsp;|
|36001|The query code is not active|&nbsp;|
|36002|Query failed, please try again|&nbsp;|
|36003|The query is timeout, please try again|&nbsp;|
|36004|Insufficient permissions|&nbsp;|
