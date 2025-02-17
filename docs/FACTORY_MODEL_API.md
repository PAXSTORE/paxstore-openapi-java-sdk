### Factory Model APIs

All the Manufacturer And Model related APIs are encapsulated in the class *com.pax.market.api.sdk.java.api.reseller.FactoryModelApi*.

**Constructors of FactoryModelApi**

```
public FactoryModelApi(String baseUrl, String apiKey, String apiSecret)
```

**Constructor parameters description**

|Name|Type|Description|
|:---|:---|:---|
|baseUrl|String|the base url of REST API|
|apiKey|String|the apiKey of marketplace, get this key from PAXSTORE admin console, refer to chapter Apply access rights|
|apiSecret|String|apiSecret, get api secret from PAXSTORE admin console, refer to chapter Apply access rights|

<br>

### Search searchFactoryModels

**API**

```
  public Result<FactoryDTO> searchFactoryModels(int pageNo, int pageSize,SearchOrderBy orderBy, String factoryName, 
  String modelName, String productType)
```

<br>

**Input parameter(s) description**

| Name| Type | Nullable|Description |
|:--- | :---|:---|:---|
|pageNo|int|false|page number, value must >=1|
|pageSize|int|false|the record number per page, range is 1 to 100|
|orderBy|SearchOrderBy|true| the field name of sort order by, if it is null the search result will sort by id by default, and the value of the parameter can be one of  FactoryModelApi.SearchOrderBy.name_desc, FactoryModelApi.SearchOrderBy.name_asc |
|factoryName|String|true|search filter by factory name|
|modelName|String|true|search filter by model name|
|productType|String|true|search filter by product type|

<br/>

**Sample codes**

```
FactoryModelApi factoryModelApi = new  FactoryModelApi("https://api.whatspos.com/p-market-api", "RCA9MDH6YN3WSSGPW6TJ", "TUNLDZVZECHNKZ4FW07XFCKN2W0N8ZDEA5ENKZYN");
Result<FactoryDTO> result = factoryModelApi.searchFactoryModels(1,5, FactoryModelApi.SearchOrderBy.name_asc, null, null, null);
```

**Client side validation failed sample result(JSON formatted)**

```
{
	"businessCode": -1,
	"validationErrors": ["pageSize:must be greater than or equal to 1"]
}
```

**Successful sample result(JSON formatted)**

```
{
    "businessCode": 0,
    "dataset": [
        {
            "id": 14557,
            "name": "A_Multivendor",
            "modelList": []
        },
        {
            "id": 14556,
            "name": "A_Shawn_Multivendor",
            "modelList": [
                {
                    "id": 7775,
                    "name": "Shawn_Multiendor_01",
                    "productTypeLabel": "Smart POS",
                    "parentProductTypeLabel": "Payment Device",
                    "productType": "smart",
                    "parentProductType": "payment"
                }
            ]
        },
        {
            "id": 14572,
            "name": "KDS_FACTORY",
            "modelList": [
                {
                    "id": 7845,
                    "name": "kds_un",
                    "productTypeLabel": "KDS Device",
                    "parentProductTypeLabel": "Commercial Device",
                    "productType": "kds",
                    "parentProductType": "commercial"
                }
            ]
        },
        {
            "id": 14570,
            "name": "mobile",
            "modelList": [
                {
                    "id": 7837,
                    "name": "mobile_model",
                    "productTypeLabel": "Mobile Device",
                    "parentProductTypeLabel": "Commercial Device",
                    "productType": "mobile",
                    "parentProductType": "commercial"
                }
            ]
        },
        {
            "id": 14569,
            "name": "oem",
            "modelList": [
                {
                    "id": 7836,
                    "name": "oemtest",
                    "productTypeLabel": "Android OEM",
                    "parentProductTypeLabel": "Phone and Tablet",
                    "productType": "android",
                    "parentProductType": "phone_tablet"
                }
            ]
        }
    ],
    "limit": 5,
    "pageNo": 1,
    "totalCount": 11,
    "hasNext": true,
    "rateLimit": "5",
    "rateLimitRemain": "4",
    "rateLimitReset": "1739780387121"
}
```

<br>

The type in dataSet of is FactoryDTO. And the structure shows like below.

|Property Name|Type|Description|
|:---|:---|:---|
|id|Long|the id of Factory|
|name|String|the name of Factory|
|modelList|List<ModelDTO>|Model list|

<br>

The type in dataSet of is ModelDTO. And the structure shows like below.

| Property Name          | Type   | Description                                                  |
| :--------------------- | :----- | :----------------------------------------------------------- |
| id                     | Long   | the id of model                                              |
| name                   | String | the name of model                                            |
| productTypeLabel       | String | the label of product type, example = "Smart POS"             |
| parentProductTypeLabel | String | the label of parent product type, example = "Payment Device" |
| productType            | String | the value of product type,example = "smart"                  |
| parentProductType      | String | the value of parent product type,example = "payment"         |

<br>

**Possible client validation errors**

> <font color="red">pageSize:must be greater than or equal to 1</font><br>
> <font color="red">pageNo:must be greater than or equal to 1</font><br>
> <font color="red">pageSize:must be less than or equal to 100</font>
