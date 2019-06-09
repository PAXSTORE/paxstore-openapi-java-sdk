## TerminalFirmware API

All the terminalFirmware related APIs are encapsulated in the class *com.pax.market.api.sdk.java.api.terminalFirmware.TerminalFirmwareApi*.

**Constructors of TerminalFirmwareApi**

```
public TerminalFirmwareApi(String baseUrl, String apiKey, String apiSecret);
public TerminalFirmwareApi(String baseUrl, String apiKey, String apiSecret, Locale locale);
```

**Constructor parameters description**

| Name | Type | Description |
| :--- | :--- | :--- |
| baseUrl | String | the base url of REST API |
|apiKey|String|the apiKey of marketplace, get this key from PAXSTORE admin console, refe to chapter Apply access rights|
|apiSecret|String|apiSecret, get api secret from PAXSTORE admin console, refer to chapter Apply access rights|
|locale|Locale|the locale, the default locale is Locale.ENGLISH, the language of message and errors in return object depend on locale|


### Create terminalFirmware

Create terminalFirmware API allow the thirdparty system create a terminalFirmware.


**API**

```
public Result<String> createTerminalFirmware(CreateTerminalFirmwareRequest createTerminalFirmwareRequest)
```

**Input parameter(s) description**  


|Parameter Name|Type|Nullable|Description|
|:---|:---|:---|:---|
|createTerminalFirmwareRequest|CreateTerminalFirmwareRequest|false|The create request object. The structure shows below.|


Structure of class CreateTerminalFirmwareRequest

|Property Name|Type|Nullable|Description|
|:---|:---|:---|:---|
|tid|String|true|The tid of terminal|
|serialNo|String|true|The serial number of terminal|
|fmName|String|false|The fmName which indicate the firmware you want to push to the terminal|

Note: tid and serialNo cannot be empty at same time.


**Sample codes**

```
TerminalFirmwareApi terminalFirmwareApi = new TerminalFirmwareApi("https://api.whatspos.com/p-market-api", "RCA9MDH6YN3WSSGPW6TJ", "TUNLDZVZECHNKZ4FW07XFCKN2W0N8ZDEA5ENKZYN");
CreateTerminalFirmwareRequest createTerminalFirmwareRequest = new CreateTerminalFirmwareRequest();
createTerminalFirmwareRequest.setTid("ABC09098989");
createTerminalFirmwareRequest.setFmName("A920_PayDroid_4.4.2_Capricorn_V01.1.10_20171226_OTA");
terminalFirmwareApi.createTerminalFirmware(createTerminalFirmwareRequest);
```

**Client side validation failed sample result(JSON formatted)**

```
{
	"businessCode": -1,
	"validationErrors": ["The property serialNo and tid in createTerminalFirmwareRequest cannot be blank at same time!"]
}
```

**Server side validation failed sample result(JSON formatted)**

```
{
	"businessCode": 2028,
	"message": "Terminal not found"
}
```

**Successful sample result(JSON formatted)**

```
{
	"businessCode": 0
}
```



**Possible validation errors**

> <font color=red>Parameter createTerminalFirmwareRequest cannot be null!</font>  
> <font color=red>The property serialNo and tid in createTerminalFirmwareRequest cannot be blank at same time!</font> 
> <font color=red>fmName:may not be empty</font> 


**Possible business codes**

|Business Code|Message|Description|
|:---|:---|:---|
|2028|Terminal not found|Please check the value of tid or serialNo|
|2033|Firmware name cannot be empty|&nbsp;|
|2034|Firmware not found|Cannot find firmware by fmName|
|2035|Firmware status not online|&nbsp;|
|2036|Firmware model mismatch with terminal model|&nbsp;|
|2026|Tid and serialNo cannot empty at same time|&nbsp;|
|8112|Same version of pending terminal firmware already exists|&nbsp;|
|8113|Same version of active terminal firmware already exists|&nbsp;|