## EntityAttributeAPI

All the entity attribute related APIs are encapsulated in the class *
com.pax.market.api.sdk.java.api.entityAttribute.EntityAttributeApi*.

**Constructors of EntityAttribute**

```
public EntityAttributeApi(String baseUrl, String apiKey, String apiSecret);
```

**Constructor parameters description**

|Name|Type|Description|
|:---|:---|:---|
|baseUrl|String|the base url of REST API|
|apiKey|String|the apiKey of marketplace, get this key from PAXSTORE admin console, refer to chapter Apply access rights|
|apiSecret|String|apiSecret, get api secret from PAXSTORE admin console, refer to chapter Apply access rights|

### Get entity attribute

Get entity attribute by attributeId .

**API**

```
public Result<EntityAttributeDTO> getEntityAttribute(Long attributeId)
```

**Input parameter(s) description**

|Parameter Name|Type|Nullable|Description|
|:---|:---|:---|:---|
|attributeId|Long|false|the id of entity attribute|

**Sample codes**

```
EntityAttributeApi entityAttributeApi = new EntityAttributeApi("https://api.whatspos.com/p-market-api", "RCA9MDH6YN3WSSGPW6TJ", "TUNLDZVZECHNKZ4FW07XFCKN2W0N8ZDEA5ENKZYN");
Result<EntityAttributeDTO> result = entityAttributeApi.getEntityAttribute(1000000218L);
```

**Client side validation failed sample result(JSON formatted)**

```
{
	"businessCode": -1,
	"validationErrors": ["Entity attributeId cannot be null and cannot be less than 1!"]
}
```

**Server side validation failed sample result(JSON formatted)**

```
{
	"businessCode": 11000,
	"message": "Entity attribute not found"
}
```

**Successful sample result(JSON formatted)**

```
{
	"businessCode": 0,
	"data":  {
		"entityType": "Merchant",
		"minLength": 2,
		"entityAttributeLabelList": [{
			"label": "testCreateApi-label-en-test",
			"locale": "en"
		}, {
			"label": "テストラベル-jp",
			"locale": "jp"
		}, {
			"label": "testCreateApi-label-update",
			"locale": "zh_CN"
		}],
		"index": 0,
		"inputType": "TEXT",
		"defaultLabel": "testCreateApi-label-update",
		"id": 1000000218,
		"maxLength": 6,
		"required": false,
		"key": "123"
	}
}
```

The type in dataSet is EntityAttributeDTO. And the structure like below.

| Name                     | Type                           | Description                                        |
| :----------------------- | :----------------------------- | :------------------------------------------------- |
| id                       | Long                           | the id of entity attribute                         |
| entityType               | String                         | the type of entity attribute type                  |
| inputType                | String                         | the type of entity input type                      |
| minLength                | Integer                        |                                                    |
| maxLength                | Integer                        |                                                    |
| required                 | boolean                        |                                                    |
| selector                 | String                         |                                                    |
| key                      | String                         |                                                    |
| index                    | int                            |                                                    |
| defaultLabel             | String                         |                                                    |
| entityAttributeLabelList | List\<EntityAttributeLabelInfo\> | the EntityAttributeLabelInfo structure like below. |

The type in data is EntityAttributeLabelInfo. And the structure like below.

| Name   | Type   | Description                 |
| :----- | :----- | :-------------------------- |
| locale | String | the locale of language type |
| label  | String |                             |

**Possible validation errors**

> <font color="red">Parameter attributeId cannot be null and cannot be less than 1!</font>

**Possible business codes**

| Business Code | Message                    | Description |
| :------------ | :------------------------- | :---------- |
| 11000         | Entity attribute not found |             |

### Search entity attributes

The search entity attributes API allows third party system to search entity attribute to the current market by page.
**API**

```
public Result<EntityAttributeDTO> searchEntityAttributes(int pageNo, int pageSize, SearchOrderBy orderBy, String key , EntityAttributeType entityType)
```

**Input parameter(s) description**

| Name| Type | Nullable|Description |
|:--- | :---|:---|:---|
|pageNo|int|false|page number, value must >=1|
|pageSize|int|false|the record number per page, range is 1 to 100|
|orderBy|SearchOrderBy|true|the sort order by field name, if this parameter is null the search result will order by created date descend. The value of this parameter can be one of EntityAttributeApi.SearchOrderBy.EntityType_desc and EntityAttributeApi.SearchOrderBy.EntityType_asc.|
|key|String|true|the value of the enity attribute key|
|entityType|EntityAttributeType|true|The value of this parameter can be one of EntityAttributeApi.EntityAttributeType.Merchant,  EntityAttributeApi.EntityAttributeType.Reseller and  EntityAttributeApi.EntityAttributeType.App|

**Sample codes**

```
EntityAttributeApi entityAttributeApi = new EntityAttributeApi("https://api.whatspos.com/p-market-api", "RCA9MDH6YN3WSSGPW6TJ", "TUNLDZVZECHNKZ4FW07XFCKN2W0N8ZDEA5ENKZYN");
Result<EntityAttributeDTO> result = entityAttributeApi.searchEntityAttributes(1,1,EntityAttributeApi.SearchOrderBy.EntityType_asc,null,null);
```

**Client side validation failed sample result(JSON formatted)**

```
{
	"businessCode": -1,
	"validationErrors": ["pageNo:must be greater than or equal to 1"]
}
```

**Server side validation failed sample result(JSON formatted)**

```
{
	"businessCode": 11002,
	"message": "Invalid entity type"
}
```

**Successful sample result**

```
{
	"businessCode": 0,
	"pageInfo": {
		"pageNo": 1,
		"limit": 1,
		"totalCount": 14,
		"hasNext": true,
		"dataSet": [{
		"entityType": "Merchant",
		"minLength": 2,
		"entityAttributeLabelList": [{
			"label": "testCreateApi-label-en-test",
			"locale": "en"
		}, {
			"label": "テストラベル-jp",
			"locale": "jp"
		}, {
			"label": "testCreateApi-label-update",
			"locale": "zh_CN"
		}],
		"index": 0,
		"inputType": "TEXT",
		"defaultLabel": "testCreateApi-label-update",
		"id": 1000000218,
		"maxLength": 6,
		"required": false,
		"key": "123"
	}]
	}
}
```

The type of data is EntityAttributeDTO,EntityAttributeLabelInfo, and Refer to get entity attribute Api for structure .

**Possible client validation errors**

> <font color=red>pageNo:must be greater than or equal to 1</font>   
> <font color=red>pageSize:must be greater than or equal to 1</font>   
> <font color=red>pageSize:must be less than or equal to 100</font>

**Possible business codes**

| Business Code | Message             | Description |
| :------------ | :------------------ | :---------- |
| 11002         | Invalid entity type |             |

### Create entity attribute

This api allows the third party system create entity attribute by EntityAttributeCreateRequest.

**API**

```
public Result<EntityAttributeDTO> createEntityAttribute(EntityAttributeCreateRequest createRequest)
```

**Input parameter(s) description**

|Parameter Name|Type|Nullable|Description|
|:---|:---|:---|:---|
|createRequest|EntityAttributeCreateRequest|false||

Structure of class EntityAttributeCreateRequest.

| Property Name | Type                | Nullable | Description                                                  |
| :------------ | :------------------ | :------- | :----------------------------------------------------------- |
| entityType    | EntityAttributeType | false    | The value of this parameter can be one of EntityAttributeApi.EntityAttributeType.Merchant,  EntityAttributeApi.EntityAttributeType.Reseller and  EntityAttributeApi.EntityAttributeType.App |
| inputType     | EntityInputType     | false    | The value of this parameter can be one of EntityAttributeApi.EntityInputType.Text and EntityAttributeApi.EntityInputType.Selector. |
| minLength     | Integer             | true     |                                                              |
| maxLength     | Integer             | true     |                                                              |
| required      | boolean             | false    |                                                              |
| selector      | String              | true     | the value of json format                                     |
| key           | String              | false    | the key of the attribute value                               |
| defaultLabel  | String              | false    |                                                              |

**Sample codes**

```
EntityAttributeApi entityAttributeApi = new EntityAttributeApi("https://api.whatspos.com/p-market-api", "RCA9MDH6YN3WSSGPW6TJ", "TUNLDZVZECHNKZ4FW07XFCKN2W0N8ZDEA5ENKZYN");
EntityAttributeCreateRequest createRequest = new EntityAttributeCreateRequest();       createRequest.setEntityType(EntityAttributeApi.EntityAttributeType.Reseller);
createRequest.setDefaultLabel("testCreateApi-label-1");
createRequest.setInputType(EntityAttributeApi.EntityInputType.Text);
createRequest.setKey("testCreateApi-key-01");
createRequest.setMaxLength(5);
createRequest.setMinLength(1);
createRequest.setRequired(false);
//createRequest.setSelector("{\n" +
//                "\t\"1\": \"1\",\n" +
 //               "\t\"te1212121st\": \"test\"\n" +
 //               "}");
Result<EntityAttributeDTO> result = entityAttributeApi.createEntityAttribute(createRequest);
```

**Client side validation failed sample result(JSON formatted)**

```
{
	"businessCode": -1,
	"validationErrors": ["Entity attributeCreateRequest cannot be null!"]
}
```

**Server side validation failed sample result(JSON formatted)**

```
{
	"businessCode": 11001,
	"message": "Entity type is mandatory"
}
```

**Successful sample result(JSON formatted)**

```
{
	"businessCode": 0,
	"data": {
		"entityType": "Reseller",
		"minLength": 1,
		"entityAttributeLabelList": [{
			"label": "testCreateApi-label-1",
			"locale": "en"
		}, {
			"label": "testCreateApi-label-1",
			"locale": "zh_CN"
		}, {
			"label": "testCreateApi-label-1",
			"locale": "jp"
		}],
		"index": 0,
		"inputType": "TEXT",
		"defaultLabel": "testCreateApi-label-1",
		"id": 1000000416,
		"maxLength": 5,
		"required": false,
		"key": "testCreateApi-key-01"
	}
}
```

<br>
The type of data is EntityAttributeDTO,EntityAttributeLabelInfo, and Refer to get entity attribute Api for structure .

**Possible client validation errors**


> <font color="red">Parameter EntityAttributeCreateRequest cannot be null!</font>

**Possible business codes**

|Business Code|Message|Description|
|:---|:---|:---|
| 135           |Request parameter is missing or invalid|&nbsp;|
| 5000 |Language not supported||
| 11001     |Entity type is mandatory||
| 11002     |Invalid entity type||
| 11003     |Input type is mandatory||
| 11004     |Invalid input type||
| 11005 |Entity attribute label is mandatory||
| 11006 |Entity attribute label is too long||
| 11007 |Select options is mandatory||
| 11008 |Invalid select options||
| 11009 |Invalid min or max length||
| 11010     |Entity attribute key is mandatory||
| 11011     |Entity attribute key is too long||
| 11012 |Entity attribute key is already exist||
| 11013 |Invalid regular expression||
| 11005 |Entity attribute label is mandatory||

### Update entity attribute

This api allows the third party system update entity attribute by attributeId and EntityAttributeUpdateRequest.

**API**

```
public Result<EntityAttributeDTO>  updateEntityAttribute(Long attributeId, EntityAttributeUpdateRequest updateRequest)
```

**Input parameter(s) description**

|Parameter Name|Type|Nullable|Description|
|:---|:---|:---|:---|
|attributeId|Long|false|the id of the entity attribute|
|updateRequest|EntityAttributeUpdateRequest|false||

Structure of class EntityAttributeUpdateRequest.

| Property Name | Type            | Nullable | Description                                                  |
| :------------ | :-------------- | :------- | :----------------------------------------------------------- |
| inputType     | EntityInputType | false    | The value of this parameter can be one of EntityAttributeApi.EntityInputType.Text and EntityAttributeApi.EntityInputType.Selector. |
| minLength     | Integer         | true     |                                                              |
| maxLength     | Integer         | true     |                                                              |
| required      | boolean         | false    |                                                              |
| selector      | String          | true     | the value of json format                                     |
| defaultLabel  | String          | false    |                                                              |

**Sample codes**

```
EntityAttributeApi entityAttributeApi = new EntityAttributeApi("https://api.whatspos.com/p-market-api", "RCA9MDH6YN3WSSGPW6TJ", "TUNLDZVZECHNKZ4FW07XFCKN2W0N8ZDEA5ENKZYN");
EntityAttributeUpdateRequest updateRequest = new EntityAttributeUpdateRequest();
updateRequest.setDefaultLabel("testCreateApi-label-update");
updateRequest.setInputType(EntityAttributeApi.EntityInputType.Text);
updateRequest.setMaxLength(6);
updateRequest.setMinLength(2);
updateRequest.setRequired(false);
Result<EntityAttributeDTO> result = entityAttributeApi.updateEntityAttribute(1000000416L,updateRequest);
```

**Client side validation failed sample result(JSON formatted)**

```
{
	"businessCode": -1,
	"validationErrors": ["Entity attributeId cannot be null and cannot be less than 1!"]
}
```

**Server side validation failed sample result(JSON formatted)**

```
{
	"businessCode": 11003,
	"message": "Input type is mandatory"
}
```

**Successful sample result(JSON formatted)**

```
{
	"businessCode": 0
}
```

**Possible business codes**

|Business Code|Message|Description|
|:---|:---|:---|
|135| Request parameter is missing or invalid ||
|5000| Language not supported                  ||
|11000| Entity attribute not found              ||
|11001| Entity type is mandatory                ||
|11002| Invalid entity type                     ||
| 11003         |Input type is mandatory||
| 11004         |Invalid input type||
| 11005         |Entity attribute label is mandatory||
| 11006         |Entity attribute label is too long||
| 11007         |Select options is mandatory||
| 11008         |Invalid select options||
| 11009         |Invalid min or max length||
| 11010         |Entity attribute key is mandatory||
| 11011         |Entity attribute key is too long||
| 11012         |Entity attribute key is already exist||
| 11013         |Invalid regular expression||

### Update entity attribute label

This api allows the third party system update entity attribute label.

**API**

```
public Result<String>  updateEntityAttributeLabel(Long attributeId, EntityAttributeLabelUpdateRequest updateLabelRequest)
```

**Input parameter(s) description**

|Parameter Name|Type|Nullable|Description|
|:---|:---|:---|:---|
|attributeId|Long|false|the id of the entity attribute|
|updateLabelRequest|EntityAttributeLabelUpdateRequest|false||

Structure of class EntityAttributeLabelUpdateRequest.

| Property Name            | Type                           | Nullable | Description |
| :----------------------- | :----------------------------- | :------- | :---------- |
| entityAttributeLabelList | List<EntityAttributeLabelInfo> | false    |             |

Structure of class EntityAttributeLabelInfo.

| Property Name | Type   | Nullable | Description |
| :------------ | :----- | :------- | :---------- |
| locale        | String | false    |             |
| label         | String | false    |             |

**Sample codes**

```
EntityAttributeApi entityAttributeApi = new EntityAttributeApi("https://api.whatspos.com/p-market-api", "RCA9MDH6YN3WSSGPW6TJ", "TUNLDZVZECHNKZ4FW07XFCKN2W0N8ZDEA5ENKZYN");

EntityAttributeLabelInfo labelInfoJp = new EntityAttributeLabelInfo();
EntityAttributeLabelInfo labelInfoEn = new EntityAttributeLabelInfo();
EntityAttributeLabelInfo labelInfoZh = new EntityAttributeLabelInfo();
labelInfoJp.setLabel("テストラベル-jp");
labelInfoJp.setLocale("jp");
labelInfoEn.setLabel("testCreateApi-label-en");
labelInfoEn.setLocale("en");
labelInfoZh.setLabel("测试updateAPI-zh");
labelInfoZh.setLocale("zh_CN");

List<EntityAttributeLabelInfo> entityAttributeLabelList = new ArrayList<>();
entityAttributeLabelList.add(labelInfoJp);
entityAttributeLabelList.add(labelInfoEn);
entityAttributeLabelList.add(labelInfoZh);
EntityAttributeLabelUpdateRequest updateRequest = new EntityAttributeLabelUpdateRequest();
updateRequest.setEntityAttributeLabelList(entityAttributeLabelList);

Result<String> result = entityAttributeApi.updateEntityAttributeLabel(1000000416L,updateRequest);
```

**Client side validation failed sample result(JSON formatted)**

```
{
	"businessCode": -1,
	"validationErrors": ["Entity attributeId cannot be null and cannot be less than 1!"]
}
```

**Server side validation failed sample result(JSON formatted)**

```
{
	"businessCode": 11000,
	"message": "Entity attribute not found"
}
```

**Successful sample result(JSON formatted)**

```
{
	"businessCode": 0
}
```

**Possible business codes**

|Business Code|Message|Description|
|:---|:---|:---|
|135|Request parameter is missing or invalid||
|5000|Language not supported||
|11000|Entity attribute not found||
|11005|Entity attribute label is mandatory||
|11006|Entity attribute label is too long||

### Delete entity attribute

This api allows the third party system delete entity attribute.

**API**

```
public Result<String> deleteEntityAttribute(Long attributeId)
```

**Input parameter(s) description**

| Parameter Name | Type | Nullable | Description                    |
| :------------- | :--- | :------- | :----------------------------- |
| attributeId    | Long | false    | the id of the entity attribute |

**Sample codes**

```
EntityAttributeApi entityAttributeApi = new EntityAttributeApi("https://api.whatspos.com/p-market-api", "RCA9MDH6YN3WSSGPW6TJ", "TUNLDZVZECHNKZ4FW07XFCKN2W0N8ZDEA5ENKZYN");
Result<String> result = entityAttributeApi.deleteEntityAttribute(1000000416L);
```

**Client side validation failed sample result(JSON formatted)**

```
{
	"businessCode": -1,
	"validationErrors": ["Entity attributeId cannot be null and cannot be less than 1!"]
}
```

**Server side validation failed sample result(JSON formatted)**

```
{
	"businessCode": 11000,
	"message": "Entity attribute not found"
}
```

**Successful sample result(JSON formatted)**

```
{
	"businessCode": 0
}
```