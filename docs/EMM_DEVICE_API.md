## EMM Device APIs

EMM Device APIs allow thirdparty system create register QR code, search EMM device, get EMM device, update EMM device, batch move EMM device, delete EMM device,
batch delete EMM device, reboot EMM device, lock EMM device screen, reset EMM device password, start EMM device lost mode, stop EMM device lost mode, submit EMM zte quick upload record.

All the EMM device APIs are in the class *com.pax.market.api.sdk.java.api.emm.emmDevice.EmmDeviceApi*.

**Constructors of EmmDeviceAPI**

```
public EmmDeviceApi(String baseUrl, String apiKey, String apiSecret);
```

Constructor parameters description


| Name      | Type   | Description                                                                                               |
| :---------- | :------- | :---------------------------------------------------------------------------------------------------------- |
| baseUrl   | String | the base url of REST API                                                                                  |
| apiKey    | String | the apiKey of marketplace, get this key from PAXSTORE admin console, refer to chapter Apply access rights |
| apiSecret | String | apiSecret, get api secret from PAXSTORE admin console, refer to chapter Apply access rights               |

### Create register QR code

The create register QR code API allows thirdparty system create register QR code.

**API**

```
public Result<EmmDeviceRegisterQRCodeCreateDTO> createRegisterQRCode(EmmDeviceRegisterQRCodeCreateRequest emmDeviceRegisterQRCodeCreateRequest);
```

**Input parameter(s) description**


| Name                                 | Type                                 | Nullable | Description                                          |
| :------------------------------------- | :------------------------------------- | :--------- | :----------------------------------------------------- |
| emmDeviceRegisterQRCodeCreateRequest | EmmDeviceRegisterQRCodeCreateRequest | false    | The create request object. The structure shows below |

Structure of class EmmDeviceRegisterQRCodeCreateRequest


| Property Name | Type   | Nullable | Description                                                                            |
| :-------------- | :------- | :--------- | :--------------------------------------------------------------------------------------- |
| resellerName  | String | false    | The name of the reseller associated with the device register QR code. Max length is 64 |
| merchantName  | String | false    | The name of the merchant associated with the device register QR code. Max length is 64 |
| type          | String | false    | The type of the register EMM device QR code. The value is EmmDeviceType.COMPANY_OWNER  |
| expireDate    | Date   | false    | The expired date of EMM device QR code                                                 |

**Sample codes**

```
EmmDeviceApi emmDeviceApi = new EmmDeviceApi("https://api.whatspos.com/p-market-api", "RCA9MDH6YN3WSSGPW6TJ", "TUNLDZVZECHNKZ4FW07XFCKN2W0N8ZDEA5ENKZYN");
EmmDeviceRegisterQRCodeCreateRequest emmDeviceRegisterQRCodeCreateRequest = new EmmDeviceRegisterQRCodeCreateRequest();
emmDeviceRegisterQRCodeCreateRequest.setResellerName("PAX");
emmDeviceRegisterQRCodeCreateRequest.setMerchantName("test");
emmDeviceRegisterQRCodeCreateRequest.setType(EmmDeviceApi.EmmDeviceType.COMPANY_OWNER);
Date currentDate = new Date();
Calendar calendar = Calendar.getInstance();
calendar.setTime(currentDate);
calendar.add(Calendar.DAY_OF_MONTH, 1);
Date nextDate = calendar.getTime();
emmDeviceRegisterQRCodeCreateRequest.setExpireDate(nextDate);
Result<EmmDeviceRegisterQRCodeCreateDTO> result = emmDeviceApi.createRegisterQRCode(emmDeviceRegisterQRCodeCreateRequest);
```

**Client side validation failed sample result(JSON formatted)**

```
{
	"businessCode": -1,
	"validationErrors": ["Parameter resellerName cannot be null!"]
}
```

**Server side validation failed sample result(JSON formatted)**

```
{
	"businessCode": 1759,
	"message": "Reseller doesn't exist"
}
```

**Successful sample result**

```
{
	"businessCode": 0,
	"data": {
		"id": 1647490564620323,
		"marketName": "PAX",
		"resellerName": "PAX",
		"merchantName": "test",
		"deviceType": "C",
		"registerQRCode": "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",
		"expireDate": 173134079999911
	}
}
```

The type in data is EmmDeviceRegisterQRCodeCreateDTO. The structure like below.


| Property Name  | Type   | Description                                                          |
| :--------------- | :------- | :--------------------------------------------------------------------- |
| id             | Long   | The register EMM device QR code id                                   |
| marketName     | String | The market name                                                      |
| resellerName   | String | The name of the reseller associated with the device register QR code |
| merchantName   | String | The name of the merchant associated with the device register QR code |
| deviceType     | String | The type of the register EMM device QR code. The value is C          |
| registerQRCode | String | The content of the register EMM device QR code                       |
| expireDate     | Long   | The expired date of EMM device QR code                               |

**Possible client validation errors**

> <font color=red>Parameter emmDeviceRegisterQRCodeCreateRequest cannot be null!</font>
> <font color=red>Parameter resellerName cannot be null!</font>
> <font color=red>Parameter resellerName is too long, maxlength is 64!</font>
> <font color=red>Parameter merchantName cannot be null!</font>
> <font color=red>Parameter merchantName is too long, maxlength is 64!</font>
> <font color=red>Parameter type cannot be null!</font>
> <font color=red>Parameter expireDate cannot be null!</font>

**Possible business codes**


| Business Code | Message                                                                        | Description |
| :-------------- | :------------------------------------------------------------------------------- | :------------ |
| 113           | Your request is invalid, please try again or contact marketplace administrator |             |
| 1720          | Merchant doesn't exist                                                         |             |
| 1723          | Merchant name is mandatory                                                     |             |
| 1729          | Merchant name is too long                                                      |             |
| 1737          | The associated merchant is not activate                                        |             |
| 1759          | Reseller doesn't exist                                                         |             |
| 1762          | Reseller name is mandatory                                                     |             |
| 1767          | Reseller name is too long                                                      |             |
| 1773          | The associated reseller is not activate                                        |             |
| 1895          | The merchant is in migrating                                                   |             |
| 61611         | Device type is mandatory                                                       |             |
| 61612         | Device type is not supported                                                   |             |
| 61613         | The expiration time is invalid                                                 |             |
| 61614         | The expiration time is mandatory                                               |             |
| 61654         | EMM for Android not subscribed                                                 |             |
| 61655         | EMM for Android not distributed                                                |             |
| 61656         | The market is not bound to EMM for Android                                     |             |

### Search EMM device

The search EMM devices API allows thirdparty system search EMM devices by page.

**API**

```
public Result<EmmDeviceDTO> searchEmmDevice(int pageNo, int pageSize, EmmDeviceSearchOrderBy orderBy, String name, String serialNo, String modelName, String resellerName, String merchantName, EmmDeviceStatus status)
```

**Input parameter(s) description**


| Name         | Type                   | Nullable | Description                                                                                                                                                                                                                                                  |
| :------------- | :----------------------- | :--------- | :------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| pageNo       | int                    | false    | page number, value must >=1                                                                                                                                                                                                                                  |
| pageSize     | int                    | false    | the record number per page, range is 1 to 100                                                                                                                                                                                                                |
| orderBy      | EmmDeviceSearchOrderBy | true     | the sort order by field name. The value of this parameter can be one of EmmDeviceSearchOrderBy.EmmDeviceSN_desc, EmmDeviceSearchOrderBy.EmmDeviceSN_asc, EmmDeviceSearchOrderBy.EmmDeviceRegisterTime_desc, EmmDeviceSearchOrderBy.EmmDeviceRegisterTime_asc |
| name         | String                 | true     | search by device name                                                                                                                                                                                                                                        |
| serialNo     | String                 | true     | search by device serial number                                                                                                                                                                                                                               |
| mfrName      | String                 | true     | search by device manufacturer, manufacturer and modelName must exist simultaneously                                                                                                                                                                          |
| modelName    | String                 | true     | search by device model name, manufacturer and modelName must exist simultaneously                                                                                                                                                                            |
| resellerName | String                 | true     | search EMM devices under the reseller or it's sub-resellers                                                                                                                                                                                                  |
| merchantName | String                 | true     | search EMM devices under the merchant                                                                                                                                                                                                                        |
| status       | EmmDeviceStatus        | true     | search by device status. The value of this parameter can be one of EmmDeviceStatus.UN_CERTIFICATED, EmmDeviceStatus.ACTIVE, EmmDeviceStatus.LOST                                                                                                             |

**Sample codes**

```
EmmAppApi emmAppApi = new  EmmAppApi("https://api.whatspos.com/p-market-api", "RCA9MDH6YN3WSSGPW6TJ", "TUNLDZVZECHNKZ4FW07XFCKN2W0N8ZDEA5ENKZYN");
Result<EmmDeviceDTO> result = emmDeviceApi.searchEmmDevice(1, 5, EmmDeviceApi.EmmDeviceSearchOrderBy.EmmDeviceSN_asc, "M9200-0908", "2870000908","ZOLON", "M9200", "suyunlong", "sh1", EmmDeviceApi.EmmDeviceStatus.ACTIVE);
```

**Client side validation failed sample result(JSON formatted)**

```
{
	"businessCode": -1,
	"validationErrors": ["Parameter pageNo Minimum cannot be less than 1!"]
}
```

**Server side validation failed sample result(JSON formatted)**

```
{
	"businessCode": 61666,
	"message": "The EMM device status is invalid"
}
```

**Successful sample result**

```
{
    "businessCode": 0,
    "dataset": [
        {
            "id": 1649639642103852,
            "name": "M9200-0908",
            "serialNo": "2870000908",
            "model": {
                "id": 15,
                "name": "M9200",
                "mfrName": "ZOLON"
            },
            "reseller": {
                "id": 1000050847,
                "name": "suyunlong",
                "parentIds": ",1,"
            },
            "merchant": {
                "id": 1000055178,
                "name": "sh1"
            },
            "registerTime": 1733294274000,
            "status": "A"
        }
    ],
    "limit": 10,
    "pageNo": 1,
    "totalCount": 1,
    "hasNext": false
}
```

The type in dataSet is EmmDeviceDTO. The structure like below.


| Property Name | Type           | Description                     |
| :-------------- | :--------------- | :-------------------------------- |
| id            | Long           | The id of EMM device            |
| name          | String         | The name of EMM device          |
| serialNo      | String         | The serial number of EMM device |
| model         | EmmModelDTO    | The model of EMM device         |
| reseller      | EmmResellerDTO | The reseller of EMM device      |
| merchant      | EmmMerchantDTO | The merchant of EMM device      |
| registerTime  | Date           | The register time of EMM device |
| status        | String         | The status of EMM device        |

Structure of class EmmModelDTO


| Property Name | Type   | Description                               |
| :-------------- | :------- | :------------------------------------------ |
| id            | Long   | The id of EMM device model                |
| name          | String | The name of EMM device model              |
| mfrName       | String | The manufacturer name of EMM device model |

Structure of class EmmResellerDTO


| Property Name | Type   | Description                                |
| :-------------- | :------- | :------------------------------------------- |
| id            | Long   | The reseller id of EMM device belongs to   |
| name          | String | The reseller name of EMM device belongs to |
| parentIds     | String | The reseller parent id list of EMM device  |

Structure of class EmmMerchantDTO


| Property Name | Type   | Description                                |
| :-------------- | :------- | :------------------------------------------- |
| id            | Long   | The merchant id of EMM device belongs to   |
| name          | String | The merchant name of EMM device belongs to |

**Possible client validation errors**

> <font color=red>Parameter pageNo Minimum cannot be less than 1!</font>
> <font color=red>Parameter pageSize must be range is 1 to 100!</font>

**Possible business codes**


| Business Code | Message                                                                        | Description |
| :-------------- | :------------------------------------------------------------------------------- | :------------ |
| 113           | Your request is invalid, please try again or contact marketplace administrator |             |
| 998           | Bad request                                                                    |             |
| 1759          | Reseller doesn't exist                                                         |             |
| 1762          | Reseller name is mandatory                                                     |             |
| 61615         | Please enter three characters at least                                         |             |
| 61616         | The input character is too long                                                |             |
| 61654         | EMM for Android not subscribed                                                 |             |
| 61656         | The market is not bound to EMM for Android                                     |             |
| 61662         | Both the manufacturer name and the model name must exist                       |             |
| 61666         | The EMM device status is invalid                                               |             |

### Get EMM device

The get EMM device API allows thirdparty system get EMM device.

**API**

```
Result<EmmDeviceDetailDTO> getEmmDevice(Long deviceId)
```

**Input parameter(s) description**


| Name     | Type | Nullable | Description          |
| :--------- | :----- | :--------- | :--------------------- |
| deviceId | Long | false    | The id of emm device |

**Sample codes**

```
EmmAppApi emmAppApi = new  EmmAppApi("https://api.whatspos.com/p-market-api", "RCA9MDH6YN3WSSGPW6TJ", "TUNLDZVZECHNKZ4FW07XFCKN2W0N8ZDEA5ENKZYN");
Result<EmmDeviceDetailDTO> result = emmDeviceApi.getEmmDevice(1647428843339813L);
```

**Client side validation failed sample result(JSON formatted)**

```
{
	"businessCode": -1,
	"validationErrors": ["Parameter deviceId cannot be null and cannot be less than 1!"]
}
```

**Server side validation failed sample result(JSON formatted)**

```
{
	"businessCode": 61617,
	"message": "Device not found"
}
```

**Successful sample result**

```
{
	"businessCode": 0,
	"data": {
		"id": 1647428843339813,
		"name": "M50-0022",
		"serialNo": "2252020022",
		"type": "C",
		"status": "A",
		"securityStatus": "RU",
		"registerTime": 1732240055000,
		"model": {
			"id": 13,
			"name": "M50",
			"mfrName": "PAX"
		},
		"reseller": {
			"id": 1,
			"name": "PAX",
			"parentIds": ","
		},
		"merchant": {
			"id": 1000055154,
			"name": "qin_merchant"
		}
	}
}
```

The type in data is EmmDeviceDetailDTO. The structure like below.


| Property Name  | Type           | Description                       |
| :--------------- | :--------------- | :---------------------------------- |
| id             | Long           | The id of EMM device              |
| name           | String         | The name of EMM device            |
| serialNo       | String         | The serial number of EMM device   |
| type           | String         | The type of EMM device            |
| model          | EmmModelDTO    | The model of EMM device           |
| reseller       | EmmResellerDTO | The reseller of EMM device        |
| merchant       | EmmMerchantDTO | The merchant of EMM device        |
| registerTime   | Date           | The register time of EMM device   |
| status         | String         | The status of EMM device          |
| securityStatus | String         | The security status of EMM device |

Structure of class EmmModelDTO


| Property Name | Type   | Description                               |
| :-------------- | :------- | :------------------------------------------ |
| id            | Long   | The id of EMM device model                |
| name          | String | The name of EMM device model              |
| mfrName       | String | The manufacturer name of EMM device model |

Structure of class EmmResellerDTO


| Property Name | Type   | Description                                |
| :-------------- | :------- | :------------------------------------------- |
| id            | Long   | The reseller id of EMM device belongs to   |
| name          | String | The reseller name of EMM device belongs to |
| parentIds     | String | The reseller parent id list of EMM device  |

Structure of class EmmMerchantDTO


| Property Name | Type   | Description                                |
| :-------------- | :------- | :------------------------------------------- |
| id            | Long   | The merchant id of EMM device belongs to   |
| name          | String | The merchant name of EMM device belongs to |

**Possible client validation errors**

> <font color=red>Parameter deviceId cannot be null and cannot be less than 1!</font>

**Possible business codes**


| Business Code | Message                                    | Description |
| :-------------- | :------------------------------------------- | :------------ |
| 61617         | Device not found                           |             |
| 61654         | EMM for Android not subscribed             |             |
| 61656         | The market is not bound to EMM for Android |             |

### Update EMM device

The update EMM device API allows the thirdparty system update EMM device.

**API**

```
public Result<String> updateEmmDevice(Long deviceId, EmmDeviceUpdateRequest emmDeviceUpdateRequest)
```

**Input parameter(s) description**


| Parameter Name         | Type                   | Nullable | Description                                          |
| :----------------------- | :----------------------- | :--------- | :----------------------------------------------------- |
| deviceId               | Long                   | false    | The id of EMM device                                 |
| emmDeviceUpdateRequest | EmmDeviceUpdateRequest | false    | The update request object. The structure shows below |

Structure of class EmmDeviceUpdateRequest


| Property Name | Type   | Nullable | Description                                       |
| :-------------- | :------- | :--------- | :-------------------------------------------------- |
| deviceName    | String | false    | The name of the EMM device. Max length is 64      |
| resellerName  | String | false    | The reseller name of EMM device. Max length is 64 |
| merchantName  | String | false    | The merchant name of EMM device. Max length is 64 |

**Sample codes**

```
EmmAppApi emmAppApi = new  EmmAppApi("https://api.whatspos.com/p-market-api", "RCA9MDH6YN3WSSGPW6TJ", "TUNLDZVZECHNKZ4FW07XFCKN2W0N8ZDEA5ENKZYN");
EmmDeviceUpdateRequest emmDeviceUpdateRequest = new EmmDeviceUpdateRequest();
emmDeviceUpdateRequest.setDeviceName("testDevice");
emmDeviceUpdateRequest.setResellerName("suyunlong");
emmDeviceUpdateRequest.setMerchantName("sh1");
Result<String> result = emmDeviceApi.updateEmmDevice(1647428843339813L, emmDeviceUpdateRequest);
```

**Client side validation failed sample result(JSON formatted)**

```
{
	"businessCode": -1,
	 validationErrors=["Parameter deviceId cannot be null and cannot be less than 1!"]
}
```

**Server side validation failed sample result(JSON formatted)**

```
{
	"businessCode": 61617,
	"message": "Device not found"
}
```

**Successful sample result(JSON formatted)**

```
{
	"businessCode": 0,
}
```

**Possible client validation errors**

> <font color=red>Parameter emmDeviceUpdateRequest cannot be null!</font>
> <font color=red>Parameter deviceId cannot be null and cannot be less than 1!</font>
> <font color=red>Parameter deviceName cannot be null!</font>
> <font color=red>Parameter deviceName is too long, maxlength is 64</font>
> <font color=red>Parameter resellerName cannot be null!</font>
> <font color=red>Parameter resellerName is too long, maxlength is 64</font>
> <font color=red>Parameter merchantName cannot be null!</font>
> <font color=red>Parameter merchantName is too long, maxlength is 64</font>

**Possible business codes**


| Business Code | Message                                                                                       | Description |
| :-------------- | :---------------------------------------------------------------------------------------------- | :------------ |
| 1720          | Merchant doesn't exist                                                                        |             |
| 1723          | Merchant name is mandatory                                                                    |             |
| 1729          | Merchant name is too long                                                                     |             |
| 1737          | The associated merchant is not activate                                                       |             |
| 1759          | Reseller doesn't exist                                                                        |             |
| 1762          | Reseller name is mandatory                                                                    |             |
| 1767          | Reseller name is too long                                                                     |             |
| 1773          | The associated reseller is not activate                                                       |             |
| 1895          | The merchant is in migrating                                                                  |             |
| 1937          | Merchant is not belong to the given Reseller                                                  |             |
| 61617         | Device not found                                                                              |             |
| 61622         | Device name is mandatory                                                                      |             |
| 61623         | Device name is too long                                                                       |             |
| 61624         | The reseller is mandatory                                                                     |             |
| 61625         | The merchant is mandatory                                                                     |             |
| 61653         | The target reseller has not subscribed to EMM for Android service, and is not allowed to move |             |
| 61654         | EMM for Android not subscribed                                                                |             |
| 61656         | The market is not bound to EMM for Android                                                    |             |

### Batch move EMM device

The batch move EMM device API allows the thirdparty system batch move EMM device

**API**

```
public Result<String> updateEmmDevice(Long deviceId, EmmDeviceUpdateRequest emmDeviceUpdateRequest)
```

**Input parameter(s) description**


| Parameter Name            | Type                      | Nullable | Description                                   |
| :-------------------------- | :-------------------------- | :--------- | :---------------------------------------------- |
| emmDeviceBatchMoveRequest | EmmDeviceBatchMoveRequest | false    | The request object. The structure shows below |

Structure of class EmmDeviceBatchMoveRequest


| Property Name | Type   | Nullable | Description                                       |
| :-------------- | :------- | :--------- | :-------------------------------------------------- |
| deviceIds     | Long   | false    | The id list of the EMM device                     |
| resellerName  | String | false    | The reseller name of EMM device. Max length is 64 |
| merchantName  | String | false    | The merchant name of EMM device. Max length is 64 |

**Sample codes**

```
EmmDeviceBatchMoveRequest emmDeviceBatchMoveRequest = new EmmDeviceBatchMoveRequest();
emmDeviceBatchMoveRequest.setDeviceIds(Collections.singletonList(1647428843339813L));
emmDeviceBatchMoveRequest.setResellerName("suyunlong");
emmDeviceBatchMoveRequest.setMerchantName("商户1");
Result<String> result = emmDeviceApi.batchMoveEmmDevice(emmDeviceBatchMoveRequest);
```

**Client side validation failed sample result(JSON formatted)**

```
{
	"businessCode": -1,
	 validationErrors=["Parameter deviceIds cannot be null!"]
}
```

**Server side validation failed sample result(JSON formatted)**

```
{
	"businessCode": 61617,
	"message": "Device not found"
}
```

**Successful sample result(JSON formatted)**

```
{
	"businessCode": 0,
}
```

**Possible client validation errors**

> <font color=red>Parameter emmDeviceBatchMoveRequest cannot be null!</font>
> <font color=red>Parameter deviceIds cannot be null!</font>
> <font color=red>Parameter resellerName cannot be null!</font>
> <font color=red>Parameter resellerName is too long, maxlength is 64</font>
> <font color=red>Parameter merchantName cannot be null!</font>
> <font color=red>Parameter merchantName is too long, maxlength is 64</font>

**Possible business codes**


| Business Code | Message                                                                                       | Description |
| :-------------- | :---------------------------------------------------------------------------------------------- | :------------ |
| 998           | Bad request                                                                                   |             |
| 1720          | Merchant doesn't exist                                                                        |             |
| 1723          | Merchant name is mandatory                                                                    |             |
| 1729          | Merchant name is too long                                                                     |             |
| 1737          | The associated merchant is not activate                                                       |             |
| 1759          | Reseller doesn't exist                                                                        |             |
| 1762          | Reseller name is mandatory                                                                    |             |
| 1767          | Reseller name is too long                                                                     |             |
| 1773          | The associated reseller is not activate                                                       |             |
| 1895          | The merchant is in migrating                                                                  |             |
| 1937          | Merchant is not belong to the given Reseller                                                  |             |
| 61617         | Device not found                                                                              |             |
| 61618         | Not able to create/update/delete over {0} devices at one time                                 |             |
| 61624         | The reseller is mandatory                                                                     |             |
| 61625         | The merchant is mandatory                                                                     |             |
| 61653         | The target reseller has not subscribed to EMM for Android service, and is not allowed to move |             |
| 61654         | EMM for Android not subscribed                                                                |             |
| 61656         | The market is not bound to EMM for Android                                                    |             |

### Delete EMM device

The delete EMM device API allows the thirdparty system delete EMM device by deviceId.

**API**

```
public Result<String> deleteEmmDevice(Long deviceId)
```

**Input parameter(s) description**


| Parameter Name | Type | Nullable | Description              |
| :--------------- | :----- | :--------- | :------------------------- |
| deviceId       | Long | false    | The id of the EMM device |

**Sample codes**

```
EmmAppApi emmAppApi = new  EmmAppApi("https://api.whatspos.com/p-market-api", "RCA9MDH6YN3WSSGPW6TJ", "TUNLDZVZECHNKZ4FW07XFCKN2W0N8ZDEA5ENKZYN");
Result<String> result = emmDeviceApi.deleteEmmDevice(1647428843339813L);
```

**Client side validation failed sample result(JSON formatted)**

```
{
	"businessCode": -1,
	 validationErrors=["Parameter deviceId cannot be null and cannot be less than 1!"]
}
```

**Server side validation failed sample result(JSON formatted)**

```
{
	"businessCode": 61617,
	"message": "Device not found"
}
```

**Successful sample result(JSON formatted)**

```
{
	"businessCode": 0,
}
```

**Possible client validation errors**

> <font color=red>Parameter deviceId cannot be null and cannot be less than 1!</font>

**Possible business codes**


| Business Code | Message                                    | Description |
| :-------------- | :------------------------------------------- | :------------ |
| 1895          | The merchant is in migrating               |             |
| 61617         | Device not found                           |             |
| 61654         | EMM for Android not subscribed             |             |
| 61656         | The market is not bound to EMM for Android |             |

### Batch delete EMM device

The batch delete EMM device API allows the thirdparty system batch delete EMM device

**API**

```
public Result<String> batchDeleteEmmDevice(EmmDeviceBatchDeleteRequest emmDeviceBatchDeleteRequest)
```

**Input parameter(s) description**


| Parameter Name              | Type                        | Nullable | Description                                   |
| :---------------------------- | :---------------------------- | :--------- | :---------------------------------------------- |
| emmDeviceBatchDeleteRequest | EmmDeviceBatchDeleteRequest | false    | The request object. The structure shows below |

Structure of class EmmDeviceBatchDeleteRequest


| Property Name | Type         | Nullable | Description                   |
| :-------------- | :------------- | :--------- | :------------------------------ |
| deviceIds     | List\<Long\> | false    | The id list of the EMM device |

**Sample codes**

```
EmmAppApi emmAppApi = new  EmmAppApi("https://api.whatspos.com/p-market-api", "RCA9MDH6YN3WSSGPW6TJ", "TUNLDZVZECHNKZ4FW07XFCKN2W0N8ZDEA5ENKZYN");
EmmDeviceBatchDeleteRequest emmDeviceBatchDeleteRequest = new EmmDeviceBatchDeleteRequest();
emmDeviceBatchDeleteRequest.setDeviceIds(Collections.singletonList(1647428843339813L));
Result<String> result = emmDeviceApi.batchDeleteEmmDevice(emmDeviceBatchDeleteRequest);
```

**Client side validation failed sample result(JSON formatted)**

```
{
	"businessCode": -1,
	 validationErrors=["Parameter deviceIds cannot be null!"]
}
```

**Server side validation failed sample result(JSON formatted)**

```
{
	"businessCode": 1895,
	"message": "The merchant is in migrating"
}
```

**Successful sample result(JSON formatted)**

```
{
	"businessCode": 0,
}
```

**Possible client validation errors**

> <font color=red>Parameter emmDeviceBatchDeleteRequest cannot be null!</font>
> <font color=red>Parameter deviceIds cannot be null!</font>

**Possible business codes**


| Business Code | Message                                                       | Description |
| :-------------- | :-------------------------------------------------------------- | :------------ |
| 998           | Bad request                                                   |             |
| 1895          | The merchant is in migrating                                  |             |
| 61618         | Not able to create/update/delete over {0} devices at one time |             |
| 61654         | EMM for Android not subscribed                                |             |
| 61656         | The market is not bound to EMM for Android                    |             |

### Reboot EMM device

The reboot EMM device API allows the thirdparty system reboot EMM device.

**API**

```
public Result<String> rebootEmmDevice(Long deviceId)
```

**Input parameter(s) description**


| Parameter Name | Type | Nullable | Description          |
| :--------------- | :----- | :--------- | :--------------------- |
| deviceId       | Long | false    | The id of EMM device |

**Sample codes**

```
EmmAppApi emmAppApi = new  EmmAppApi("https://api.whatspos.com/p-market-api", "RCA9MDH6YN3WSSGPW6TJ", "TUNLDZVZECHNKZ4FW07XFCKN2W0N8ZDEA5ENKZYN");
Result<String> result = emmDeviceApi.rebootEmmDevice(1647428843339813L);
```

**Client side validation failed sample result(JSON formatted)**

```
{
	"businessCode": -1,
	 validationErrors=["Parameter deviceId cannot be null and cannot be less than 1!"]
}
```

**Server side validation failed sample result(JSON formatted)**

```
{
	"businessCode": 61617,
	"message": "Device not found"
}
```

**Successful sample result(JSON formatted)**

```
{
	"businessCode": 0,
}
```

**Possible client validation errors**

> <font color=red>Parameter deviceId cannot be null and cannot be less than 1!</font>

**Possible business codes**


| Business Code | Message                                    | Description |
| :-------------- | :------------------------------------------- | :------------ |
| 61617         | Device not found                           |             |
| 61626         | Reboot control unsupported on this device  |             |
| 61638         | Device not certified                       |             |
| 61654         | EMM for Android not subscribed             |             |
| 61656         | The market is not bound to EMM for Android |             |

### Lock EMM device screen

The lock EMM device screen API allows the thirdparty system lock EMM device screen.

**API**

```
public Result<String> lockEmmDeviceScreen(Long deviceId)
```

**Input parameter(s) description**


| Parameter Name | Type | Nullable | Description          |
| :--------------- | :----- | :--------- | :--------------------- |
| deviceId       | Long | false    | The id of EMM device |

**Sample codes**

```
EmmAppApi emmAppApi = new  EmmAppApi("https://api.whatspos.com/p-market-api", "RCA9MDH6YN3WSSGPW6TJ", "TUNLDZVZECHNKZ4FW07XFCKN2W0N8ZDEA5ENKZYN");
Result<String> result = emmDeviceApi.lockEmmDeviceScreen(1647428843339813L);
```

**Client side validation failed sample result(JSON formatted)**

```
{
	"businessCode": -1,
	 validationErrors=["Parameter deviceId cannot be null and cannot be less than 1!"]
}
```

**Server side validation failed sample result(JSON formatted)**

```
{
	"businessCode": 61617,
	"message": "Device not found"
}
```

**Successful sample result(JSON formatted)**

```
{
	"businessCode": 0,
}
```

**Possible client validation errors**

> <font color=red>Parameter deviceId cannot be null and cannot be less than 1!</font>

**Possible business codes**


| Business Code | Message                                    | Description |
| :-------------- | :------------------------------------------- | :------------ |
| 61617         | Device not found                           |             |
| 61638         | Device not certified                       |             |
| 61654         | EMM for Android not subscribed             |             |
| 61656         | The market is not bound to EMM for Android |             |

### Reset EMM device password

The reset EMM device password API allows the thirdparty system reset EMM device password.

**API**

```
public Result<String> resetEmmDevicePassword(Long deviceId, EmmDeviceResetPasswordRequest emmDeviceResetPasswordRequest)
```

**Input parameter(s) description**


| Parameter Name                | Type                          | Nullable | Description                                   |
| :------------------------------ | :------------------------------ | :--------- | :---------------------------------------------- |
| deviceId                      | Long                          | false    | The id of emm device                          |
| emmDeviceResetPasswordRequest | EmmDeviceResetPasswordRequest | false    | The request object. The structure shows below |

Structure of class EmmDeviceResetPasswordRequest


| Property Name | Type    | Nullable | Description                                                                                                                                                                                                                                          |
| :-------------- | :-------- | :--------- | :----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| password      | String  | false    | Password, Android 14 and above devices (including Android 14) require a minimum password length of 6 characters, while Android 14 and below devices require a minimum password length of 4 characters and a maximum password length of 16 characters |
| lockNow       | Boolean | false    | Lock the device after the password has been reset                                                                                                                                                                                                    |

**Sample codes**

```
EmmAppApi emmAppApi = new  EmmAppApi("https://api.whatspos.com/p-market-api", "RCA9MDH6YN3WSSGPW6TJ", "TUNLDZVZECHNKZ4FW07XFCKN2W0N8ZDEA5ENKZYN");
EmmDeviceResetPasswordRequest emmDeviceResetPasswordRequest = new EmmDeviceResetPasswordRequest();
emmDeviceResetPasswordRequest.setPassword("1234");
Result<String> result = emmDeviceApi.resetEmmDevicePassword(1647428843339813L, emmDeviceResetPasswordRequest);
```

**Client side validation failed sample result(JSON formatted)**

```
{
	"businessCode": -1,
	 validationErrors=["Parameter deviceId cannot be null and cannot be less than 1!"]
}
```

**Server side validation failed sample result(JSON formatted)**

```
{
	"businessCode": 61617,
	"message": "Device not found"
}
```

**Successful sample result(JSON formatted)**

```
{
	"businessCode": 0,
}
```

**Possible client validation errors**

> <font color=red>Parameter emmDeviceResetPasswordRequest cannot be null!</font>
> <font color=red>Parameter deviceId cannot be null and cannot be less than 1!</font>
> <font color=red>Parameter password cannot be null!</font>
> <font color=red>Parameter lockNow cannot be null!</font>

**Possible business codes**


| Business Code | Message                                       | Description |
| :-------------- | :---------------------------------------------- | :------------ |
| 998           | Bad request                                   |             |
| 62021         | Device password is too long                   |             |
| 62028         | The password is mandatory                     |             |
| 61617         | Device not found                              |             |
| 61627         | Password must be at least {0} characters long |             |
| 61638         | Device not certified                          |             |
| 61654         | EMM for Android not subscribed                |             |
| 61656         | The market is not bound to EMM for Android    |             |

### Start EMM device lost mode

The start EMM device lost mode API allows the thirdparty system start EMM device lost mode.

**API**

```
public Result<String> startEmmDeviceLostMode(Long deviceId, EmmDeviceLostModeRequest emmDeviceLostModeRequest)
```

**Input parameter(s) description**


| Parameter Name           | Type                     | Nullable | Description                                   |
| :------------------------- | :------------------------- | :--------- | :---------------------------------------------- |
| deviceId                 | Long                     | false    | The id of EMM device                          |
| emmDeviceLostModeRequest | EmmDeviceLostModeRequest | false    | The request object. The structure shows below |

Structure of class EmmDeviceLostModeRequest


| Property Name   | Type   | Nullable | Description                         |
| :---------------- | :------- | :--------- | :------------------------------------ |
| lostMessage     | String | false    | Lost message, max length is 64      |
| lostPhoneNumber | String | false    | Lost phone number, max length is 32 |

**Sample codes**

```
EmmAppApi emmAppApi = new  EmmAppApi("https://api.whatspos.com/p-market-api", "RCA9MDH6YN3WSSGPW6TJ", "TUNLDZVZECHNKZ4FW07XFCKN2W0N8ZDEA5ENKZYN");
EmmDeviceLostModeRequest emmDeviceLostModeRequest = new EmmDeviceLostModeRequest();
emmDeviceLostModeRequest.setLostMessage("test message");
emmDeviceLostModeRequest.setLostPhoneNumber("12345678910");
Result<String> result = emmDeviceApi.startEmmDeviceLostMode(1647428843339813L, emmDeviceLostModeRequest);
```

**Client side validation failed sample result(JSON formatted)**

```
{
	"businessCode": -1,
	 validationErrors=["Parameter deviceId cannot be null and cannot be less than 1!"]
}
```

**Server side validation failed sample result(JSON formatted)**

```
{
	"businessCode": 61617,
	"message": "Device not found"
}
```

**Successful sample result(JSON formatted)**

```
{
	"businessCode": 0,
}
```

**Possible client validation errors**

> <font color=red>Parameter emmDeviceLostModeRequest cannot be null!</font>
> <font color=red>Parameter deviceId cannot be null and cannot be less than 1!</font>
> <font color=red>Parameter lostMessage cannot be null!</font>
> <font color=red>Parameter lostMessage is too long, maxlength is 64!</font>
> <font color=red>Parameter lostPhoneNumber cannot be null!</font>
> <font color=red>Parameter lostPhoneNumber is too long, maxlength is 32!</font>

**Possible business codes**


| Business Code | Message                                                                                                         | Description |
| :-------------- | :---------------------------------------------------------------------------------------------------------------- | :------------ |
| 62024         | The lostMessage is mandatory                                                                                    |             |
| 62025         | The password is mandatory                                                                                       |             |
| 62026         | The lostMessage is too long                                                                                     |             |
| 62027         | The lostPhoneNumber is too long                                                                                 |             |
| 61617         | Device not found                                                                                                |             |
| 61628         | Failed to set the device to lost mode, because the device password has been reset by admin in the last 12 hours |             |
| 61629         | Failed to set the device to lost mode, because the user manually exited lost mode in the last 12 hours          |             |
| 61630         | Failed to set the device to lost mode. The device is already in the lost state                                  |             |
| 61632         | Failed to set the device to lost mode                                                                           |             |
| 61638         | Device not certified                                                                                            |             |
| 61654         | EMM for Android not subscribed                                                                                  |             |
| 61656         | The market is not bound to EMM for Android                                                                      |             |

### Resume EMM device

The resume EMM device API allows the thirdparty system resume EMM device.

**API**

```
public Result<String> resumeEmmDevice(Long deviceId)
```

**Input parameter(s) description**


| Parameter Name | Type | Nullable | Description          |
| :--------------- | :----- | :--------- | :--------------------- |
| deviceId       | Long | false    | The id of EMM device |

**Sample codes**

```
EmmAppApi emmAppApi = new  EmmAppApi("https://api.whatspos.com/p-market-api", "RCA9MDH6YN3WSSGPW6TJ", "TUNLDZVZECHNKZ4FW07XFCKN2W0N8ZDEA5ENKZYN");
Result<String> result = emmDeviceApi.resumeEmmDevice(1647428843339813L);
```

**Client side validation failed sample result(JSON formatted)**

```
{
	"businessCode": -1,
	 validationErrors=["Parameter deviceId cannot be null and cannot be less than 1!"]
}
```

**Server side validation failed sample result(JSON formatted)**

```
{
	"businessCode": 61617,
	"message": "Device not found"
}
```

**Successful sample result(JSON formatted)**

```
{
	"businessCode": 0,
}
```

**Possible client validation errors**

> <font color=red>Parameter deviceId cannot be null and cannot be less than 1!</font>

**Possible business codes**


| Business Code | Message                                                              | Description |
| :-------------- | :--------------------------------------------------------------------- | :------------ |
| 61617         | Device not found                                                     |             |
| 61654         | EMM for Android not subscribed                                       |             |
| 61656         | The market is not bound to EMM for Android                           |             |
| 62045         | Failed to resume the device. The device is not in the disabled state |             |

### Disable EMM device

The disable EMM device API allows the thirdparty system disable EMM device.

**API**

```
public Result<String> disableEmmDevice(Long deviceId)
```

**Input parameter(s) description**


| Parameter Name | Type | Nullable | Description          |
| :--------------- | :----- | :--------- | :--------------------- |
| deviceId       | Long | false    | The id of EMM device |

**Sample codes**

```
EmmAppApi emmAppApi = new  EmmAppApi("https://api.whatspos.com/p-market-api", "RCA9MDH6YN3WSSGPW6TJ", "TUNLDZVZECHNKZ4FW07XFCKN2W0N8ZDEA5ENKZYN");
Result<String> result = emmDeviceApi.disableEmmDevice(1647428843339813L);
```

**Client side validation failed sample result(JSON formatted)**

```
{
	"businessCode": -1,
	 validationErrors=["Parameter deviceId cannot be null and cannot be less than 1!"]
}
```

**Server side validation failed sample result(JSON formatted)**

```
{
	"businessCode": 61617,
	"message": "Device not found"
}
```

**Successful sample result(JSON formatted)**

```
{
	"businessCode": 0,
}
```

**Possible client validation errors**

> <font color=red>Parameter deviceId cannot be null and cannot be less than 1!</font>

**Possible business codes**


| Business Code | Message                                                                | Description |
| :-------------- | :----------------------------------------------------------------------- | :------------ |
| 61617         | Device not found                                                       |             |
| 61654         | EMM for Android not subscribed                                         |             |
| 61656         | The market is not bound to EMM for Android                             |             |
| 62046         | Failed to disable the device. The device is not in the activated state |             |

### Sync EMM device detail

The sync EMM device detail API allows the thirdparty system sync EMM device detail.

**API**

```
public Result<String> syncDeviceDetail(Long deviceId)
```

**Input parameter(s) description**


| Parameter Name | Type | Nullable | Description          |
| :--------------- | :----- | :--------- | :--------------------- |
| deviceId       | Long | false    | The id of EMM device |

**Sample codes**

```
EmmAppApi emmAppApi = new  EmmAppApi("https://api.whatspos.com/p-market-api", "RCA9MDH6YN3WSSGPW6TJ", "TUNLDZVZECHNKZ4FW07XFCKN2W0N8ZDEA5ENKZYN");
Result<String> result = emmDeviceApi.syncDeviceDetail(1647428843339813L);
```

**Client side validation failed sample result(JSON formatted)**

```
{
	"businessCode": -1,
	 validationErrors=["Parameter deviceId cannot be null and cannot be less than 1!"]
}
```

**Server side validation failed sample result(JSON formatted)**

```
{
	"businessCode": 61617,
	"message": "Device not found"
}
```

**Successful sample result(JSON formatted)**

```
{
	"businessCode": 0,
}
```

**Possible client validation errors**

> <font color=red>Parameter deviceId cannot be null and cannot be less than 1!</font>

**Possible business codes**


| Business Code | Message                                            | Description |
| :-------------- | :--------------------------------------------------- | :------------ |
| 61617         | Device not found                                   |             |
| 61654         | EMM for Android not subscribed                     |             |
| 61656         | The market is not bound to EMM for Android         |             |
| 62047         | Sync failed. Please try again later                |             |
| 62048         | Manual sync not supported in current device status |             |

### Stop EMM device lost mode

The stop EMM device lost mode API allows the thirdparty system stop EMM device lost mode.

**API**

```
public Result<String> stopEmmDeviceLostMode(Long deviceId)
```

**Input parameter(s) description**


| Parameter Name | Type | Nullable | Description          |
| :--------------- | :----- | :--------- | :--------------------- |
| deviceId       | Long | false    | The id of emm device |

**Sample codes**

```
EmmAppApi emmAppApi = new  EmmAppApi("https://api.whatspos.com/p-market-api", "RCA9MDH6YN3WSSGPW6TJ", "TUNLDZVZECHNKZ4FW07XFCKN2W0N8ZDEA5ENKZYN");
Result<String> result = emmDeviceApi.stopEmmDeviceLostMode(1647428843339813L);
```

**Client side validation failed sample result(JSON formatted)**

```
{
	"businessCode": -1,
	 validationErrors=["Parameter deviceId cannot be null and cannot be less than 1!"]
}
```

**Server side validation failed sample result(JSON formatted)**

```
{
	"businessCode": 61617,
	"message": "Device not found"
}
```

**Successful sample result(JSON formatted)**

```
{
	"businessCode": 0,
}
```

**Possible client validation errors**

> <font color=red>Parameter deviceId cannot be null and cannot be less than 1!</font>

**Possible business codes**


| Business Code | Message                                                                         | Description |
| :-------------- | :-------------------------------------------------------------------------------- | :------------ |
| 61617         | Device not found                                                                |             |
| 61631         | Failed to turn off lost mode of the device. The device is not in the lost state |             |
| 61654         | EMM for Android not subscribed                                                  |             |
| 61656         | The market is not bound to EMM for Android                                      |             |

### Submit EMM zte quick upload record

The submit EMM zte quick upload record API allows the thirdparty system submit EMM zte quick upload record

**API**

```
public Result<String> submitEmmZteQuickUploadRecord(EmmZteQuickUploadRecordCreateRequest emmZteQuickUploadRecordCreateRequest)
```

**Input parameter(s) description**


| Parameter Name                       | Type                                 | Nullable | Description                                          |
| :------------------------------------- | :------------------------------------- | :--------- | :----------------------------------------------------- |
| emmZteQuickUploadRecordCreateRequest | EmmZteQuickUploadRecordCreateRequest | false    | The create request object. The structure shows below |

Structure of class EmmZteQuickUploadRecordCreateRequest


| Property Name  | Type   | Nullable | Description                                                                                                                                                                                                                                                                                 |
| :--------------- | :------- | :--------- | :-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| resellerName   | String | false    | The name of the reseller associated with the emm device. Max length is 64                                                                                                                                                                                                                   |
| merchantName   | String | false    | The name of the merchant associated with the emm device. Max length is 64                                                                                                                                                                                                                   |
| identifierType | String | false    | Batch device registration type, value can be one of EmmDeviceApi.EmmZteIdentifierType.IMEI, EmmDeviceApi.EmmZteIdentifierType.SERIAL_NUMBER                                                                                                                                                 |
| manufacturer   | String | true     | Device manufacturer, value is ZOLON. When the value of identifierType is EmmDeviceApi.EmmZteIdentifierType.IMEI, the manufacturer parameter is ignored. When the value of identifierType is EmmDeviceApi.EmmZteIdentifierType.SERIAL_NUMBER, the manufacturer parameter is mandatory        |
| model          | String | true     | Device model, value can be one of L16xx, M30, M50, M9200. When the value of identifierType is EmmDeviceApi.EmmZteIdentifierType.IMEI, the model parameter is ignored. When the value of identifierType is EmmDeviceApi.EmmZteIdentifierType.SERIAL_NUMBER, the model parameter is mandatory |
| numbers        | String | false    | When the value of identifierType is EmmDeviceApi.EmmZteIdentifierType.IMEI, the numbers is IMEI number of multiple devices with commas. When the value of identifierType is EmmDeviceApi.EmmZteIdentifierType.SERIAL_NUMBER, the numbers is serial number of multiple devices with commas   |

**Sample codes**

```
EmmAppApi emmAppApi = new  EmmAppApi("https://api.whatspos.com/p-market-api", "RCA9MDH6YN3WSSGPW6TJ", "TUNLDZVZECHNKZ4FW07XFCKN2W0N8ZDEA5ENKZYN");
EmmZteQuickUploadRecordCreateRequest emmZteQuickUploadRecordCreateRequest = new EmmZteQuickUploadRecordCreateRequest();
emmZteQuickUploadRecordCreateRequest.setResellerName("PAX");
emmZteQuickUploadRecordCreateRequest.setMerchantName("test");
emmZteQuickUploadRecordCreateRequest.setIdentifierType(EmmDeviceApi.EmmZteIdentifierType.IMEI);
emmZteQuickUploadRecordCreateRequest.setNumbers("111111111111111");
Result<String> result = emmDeviceApi.submitEmmZteQuickUploadRecord(emmZteQuickUploadRecordCreateRequest);
```

**Client side validation failed sample result(JSON formatted)**

```
{
	"businessCode": -1,
	 validationErrors=["Parameter resellerName cannot be null!"]
}
```

**Server side validation failed sample result(JSON formatted)**

```
{
	"businessCode": 1759,
	"message": "Reseller doesn't exist"
}
```

**Successful sample result(JSON formatted)**

```
{
	"businessCode": 0,
}
```

**Possible client validation errors**

> <font color=red>Parameter emmZteQuickUploadRecordCreateRequest cannot be null!</font>
> <font color=red>Parameter resellerName cannot be null!</font>
> <font color=red>Parameter resellerName is too long, maxlength is 64!</font>
> <font color=red>Parameter merchantName cannot be null!</font>
> <font color=red>Parameter merchantName is too long, maxlength is 64!</font>
> <font color=red>Parameter identifierType cannot be null!</font>
> <font color=red>Parameter numbers cannot be null!</font>

**Possible business codes**


| Business Code | Message                                                  | Description |
| :-------------- | :--------------------------------------------------------- | :------------ |
| 998           | Bad request                                              |             |
| 1720          | Merchant doesn't exist                                   |             |
| 1729          | Merchant name is too long                                |             |
| 1737          | The associated merchant is not activate                  |             |
| 1740          | Your terminal (SN:{0}) already exists                    |             |
| 1759          | Merchant name is mandatory                               |             |
| 1762          | Reseller name is mandatory                               |             |
| 1767          | Reseller name is too long                                |             |
| 1773          | The associated reseller is not activate                  |             |
| 1895          | The merchant is in migrating                             |             |
| 62000         | The manufacturer is not supported                        |             |
| 62001         | The model is not supported                               |             |
| 62002         | The IMEI numbers is too long                             |             |
| 62003         | The IMEI numbers is invalid                              |             |
| 62004         | The serial numbers is too long                           |             |
| 62005         | The serial numbers is invalid                            |             |
| 62006         | Maximum of {0} devices can be enrolled at one time       |             |
| 62007         | Duplicated IMEI                                          |             |
| 62012         | IMEI or serial number is mandatory                       |             |
| 62014         | No reseller access allowed                               |             |
| 62017         | Your terminal (IMEI:{0}) has been registered             |             |
| 62018         | Identifiertype is mandatory                              |             |
| 62019         | Identifiertype is invalid                                |             |
| 61624         | The reseller is mandatory                                |             |
| 61625         | The merchant is mandatory                                |             |
| 61654         | EMM for Android not subscribed                           |             |
| 61655         | EMM for Android not distributed                          |             |
| 61656         | The market is not bound to EMM for Android               |             |
| 61662         | Both the manufacturer name and the model name must exist |             |
