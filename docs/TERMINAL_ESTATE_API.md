## Terminal Estate APIs

Terminal Estate APIs allow thirdParty system verify terminal estate by serialNo.

All the terminal estate APIs are in the class *com.pax.market.api.sdk.java.api.terminalEstate.TerminalEstateApi*.   

**Constructors of TerminalEstateApi**

```
public TerminalEstateApi(String baseUrl, String apiKey, String apiSecret);
```

**Constructor parameters description**

|Name|Type|Description|
|:----|:----|:----|
|baseUrl|String|the base url of REST API|
|apiKey|String|the apiKey of marketplace, get this key from PAXSTORE admin console, refer to chapter Apply access rights|
|apiSecret|String|apiSecret, get api secret from PAXSTORE admin console, refer to chapter Apply access rights|



### Verify Terminal Estate  

The verify terminal estate API allows the thirdParty system verify terminal estate by terminal serialNo.
If terminal estate exist there's not response content from remote server.

**API**

```
public Result<String> verifyTerminalEstate(terminalSerialNo)
```


**Input parameter(s) description**

|Parameter Name|Type|Nullable|Description|
|:---|:---|:---|:---|
|serialNo|String|false|The terminal serialNo.|


**Sample codes**

```
TerminalEstateApi terminalEstateApi = new TerminalEstateApi("https://api.whatspos.com/p-market-api", "RCA9MDH6YN3WSSGPW6TJ", "TUNLDZVZECHNKZ4FW07XFCKN2W0N8ZDEA5ENKZYN");
Result<String> result = terminalEstateApi.verifyTerminalEstate("1350001853");
```

If the property BusinessCode of result is 0, then means the terminal exist in terminal estate.

**Client validation failed sample result(JSON formatted)**

```
{
	"businessCode": -1,
	"validationErrors": ["Parameter serialNo cannot be null and cannot be less than 1!"]
}
```

**Server side validation failed sample result(JSON formatted)**

```
{
	"businessCode": 2412,
	"message": "Your terminal (SN:1350001853) does not exist in estate list"
}
```

**Successful sample result(JSON formatted)**

```
{
	"businessCode": 0
}
```

**Possible client validation errors**

> <font color=red>Parameter serialNo cannot be null and cannot be less than 1!</font>  

**Possible business codes**

|Business Code|Message|Description|
|:---|:---|:---|
|2412|Your terminal (SN:{0}) does not exist in estate list|&nbsp;|
