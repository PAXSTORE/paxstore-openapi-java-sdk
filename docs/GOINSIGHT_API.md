## GoInsight APIs

GoInsight APIs allow third party system search app bizData form goInsight.

All the GoInsight related APIs are in the class *com.pax.market.api.sdk.java.api.goInsight.GoInsightApi*.

**Constructors of GoInsightApi**

```  
public GoInsightApi(String baseUrl, String apiKey, String apiSecret);

public GoInsightApi(String baseUrl, String apiKey, String apiSecret, TimeZone timeZone)
```

**Constructor parameters description**

|Name|Type|Description|
|:----|:----|:----|
|baseUrl|String|the base url of REST API|
|apiKey|String|the apiKey of marketplace, get this key from PAXSTORE admin/reseller console, refer to chapter Apply access rights|
|apiSecret|String|apiSecret, get api secret from PAXSTORE admin/reseller console, refer to chapter Apply access rights|
|timeZone|TimeZone|the timeZone, the default timeZone is TimeZone.getTimeZone("UTC"), the biz data in return object depend on timeZone|

Part of the timeZone available ids
| ID | Time Zone Offset(s) |
|:---- |:----|
|UTC|0|
|Etc/GMT+12|-43200|
|Etc/GMT+11|-39600|
|Etc/GMT+10|-36000|
|Etc/GMT+9|-32400|
|Etc/GMT+8|-28800|
|Etc/GMT+7|-25200|
|Etc/GMT+6|-21600|
|Etc/GMT+5|-18000|
|Etc/GMT+4|-14400|
|Etc/GMT+3|-10800|
|Etc/GMT+2|-7200|
|Etc/GMT+1|-3600|
|Etc/GMT|0|
|Etc/GMT-1|3600|
|Etc/GMT-2|7200|
|Etc/GMT-3|10800|
|Etc/GMT-4|14400|
|Etc/GMT-5|18000|
|Etc/GMT-6|21600|
|Etc/GMT-7|25200|
|Etc/GMT-8|28800|
|Etc/GMT-9|32400|
|Etc/GMT-10|36000|
|Etc/GMT-11|39600|
|Etc/GMT-12|43200|
|Etc/GMT-13|46800|
|Etc/GMT-14|50400|

### Search APP BizData

The search app bizData API allow the third party system search data.<br/>
Note: This result of this API depends on the API query settings in GoInsight. Paging needs to be set when the query
result set type is a details data.

**API**

```
public Result<DataQueryResultDTO> findDataFromInsight(String queryCode)
public Result<DataQueryResultDTO> findDataFromInsight(String queryCode, TimestampRangeType rangeType)
public Result<DataQueryResultDTO> findDataFromInsight(String queryCode, Integer pageNo, Integer pageSize)
public Result<DataQueryResultDTO> findDataFromInsight(String queryCode, TimestampRangeType rangeType, Integer pageNo, Integer pageSize)
public Result<DataQueryResultDTO> findDataFromInsight(String queryCode, TimestampRangeType rangeType, List<GoInsightCustomFilter> customFilterList, Integer pageNo, Integer pageSize)
```

**Input parameter(s) description**

| Name| Type | Nullable| Description                                                                                                        |
|:---- | :----|:----|:-------------------------------------------------------------------------------------------------------------------|
|queryCode|String|false| search by GoInsight api query code                                                                                 |
|rangeType|TimestampRangeType|true| you can choose the range of data results for search                                                                |
|customFilterList|List<GoInsightCustomFilter>|true| you can add custom filter for search                                                                               |
|pageNo|int|true| page number, value must >= 1                                                                                       |
|pageSize|int|true| the record number per page, range is 1 to 100 for details data query, range is 1 to 1000 for statistics data query |

Note: The pageNo param will be ignore when your query result set type is statistics chart.

Value of enum TimestampRangeType

| Value | Description      |
|:---- |:-----------------|
|LAST_HOUR| Last Hour        |
|YESTERDAY| Yesterday        |
|LAST_WEEK| Previous Week    |
|LAST_MONTH| Previous Month   |
|LAST_QUARTER| Previous Quarter |
|LAST_YEAR| Previous Year    |
|RECENT_5_MIN| Last 5 Minutes   |
|RECENT_30_MIN| Last 30 Minutes  |
|RECENT_HOUR| Last 60 Minutes  |
|RECENT_3_HOUR| Last 3 Hours     |
|RECENT_DAY| Last 24 Hours    |
|RECENT_2_DAY| Last 2 Days      |
|RECENT_5_DAY| Last 5 Days      |
|RECENT_WEEK| Last 7 Days      |
|RECENT_MONTH| Last 30 Days     |
|RECENT_3_MONTH| Last 90 Days     |
|RECENT_6_MONTH| Last 180 Days    |
|RECENT_YEAR| Last 365 Days    |
|THIS_HOUR| This Hour        |
|TODAY| Today            |
|THIS_WEEK| This Week        |
|THIS_MONTH| This Month       |
|THIS_QUARTER| This Quarter     |
|THIS_YEAR| This Year        |

The structure of class GoInsightCustomFilter

|Property Name|Type| Description                                                               |
|:---|:---|:--------------------------------------------------------------------------|
|cloName|String| Filter by this column.                                                    |
|filterValue|String| The filter value, Separated by commas. Supports a maximum of 100 filters. |

Value of enum CustomColName

| Value | Description            |
|:---- |:-----------------------|
|RESELLER| Filter by reseller     |
|MERCHANT| Filter by mechant      |
|TERMINAL| Filter by terminal     |
|FACTORY| Filter by manufacturer |
|MODEL| Filter by model |

**Sample codes**

```
String[] ids = TimeZone.getAvailableIDs();
//TimeZone tz1 = TimeZone.getTimeZone(ids[2]);
TimeZone tz = TimeZone.getTimeZone("Etc/GMT-1");

//build custom filter
List<GoInsightCustomFilter> customFilterList = new ArrayList<>();
GoInsightCustomFilter terminalSNs = new GoInsightCustomFilter();
terminalSNs.setCloName(CustomColName.TERMINAL);
terminalSNs.setFilterValue("SN123,SN456,SN789");
customFilterList.add(terminalSNs);

GoInsightApi goInsightApi = new GoInsightApi("https://api.whatspos.com/p-market-api", "RCA9MDH6YN3WSSGPW6TJ", "TUNLDZVZECHNKZ4FW07XFCKN2W0N8ZDEA5ENKZYN", tz);
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
			"displayName": "Acquirer Type",
            "type": "Dimension"
		}, {
			"colName": "currency",
			"displayName": "Currency",
            "type": "Dimension"
		}, {
			"colName": "purchase_id",
			"displayName": "Purchase ID",
            "type": "Dimension"
		}, {
			"colName": "amount",
			"displayName": "Amount",
            "type": "Measure"
		}, {
			"colName": "tax",
			"displayName": "Tax",
            "type": "Measure"
		}, {
			"colName": "_sys_marketid",
			"displayName": "Marketplace",
            "type": "Dimension"
		}, {
			"colName": "_sys_merchantid",
			"displayName": "Merchant",
            "type": "Dimension"
		}, {
			"colName": "_sys_terminalid",
			"displayName": "Terminal",
            "type": "Dimension"
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

Structure of class DataQueryResultDTO

|Property Name|Type|Description|
|:---|:---|:---|
|worksheetName|String|The result set worksheet name.|
|columns|List\<Column\>|The result set column.|
|rows|List\<List\<Row\>\>|The result set.|
|hasNext|Boolean|Is there any data.|
|offset|int|Rows offset if exit page info.|
|limit|int|Rows page size if exit page info.|

Structure of class Column

|Property Name|Type|Description|
|:---|:---|:---|
|colName|String|The dataset filed name in GoInsight|
|displayName|String|The dataset filed's display name|
|type|String|Data analysis type of dataset field. Value can be one of Dimension, Measure and Image|

Structure of class Row

|Property Name|Type|Description|
|:---|:---|:---|
|colName|String|The dataset filed name in GoInsight|
|value|String|The dataset filed's value|

**Possible client validation errors**

> <font color=red>Parameter queryCode cannot be null</font>  
> <font color=red>Parameter queryCode length must is 8</font>
> <font color=red>Parameter pageSize must be range is 1 to 1000</font>

**Possible business codes**

|Business Code|Message|Description|
|:---|:---|:---|
|36000|The query code is not found|&nbsp;|
|36001|The query code is not active|&nbsp;|
|36002|Query failed, please try again|&nbsp;|
|36003|The query is timeout, please try again|&nbsp;|
|36004|Insufficient permissions|&nbsp;|
|36005|Invalid pageNo|&nbsp;|
|36006|Invalid pageSize|&nbsp;|
|36008|Query failed, please contact administrator|&nbsp;|
|36009|Too many request, please try again later|&nbsp;

**Possible abnormal http codes**

|Http Code|Message|Description|
|:---|:---|:---|
|429|Too many request, please try again in one minute, two minutes or whatever|&nbsp;
