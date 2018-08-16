# PAXSTORE RESTful Open API  

PAXSTORE exposes reseller, merchant and terminal related APIs for thirdparty system convenience. So that the authorized thirdparty system can do operations for reseller, merchant and terminal without logging into PAXSTORE's admin console. The exposed API is RESTful formatted. 



## Apply access rights

If the thirdparty systems want to call the REST APIs they must enable external system access in PAXSTORE admin console for the certain marketplaces and get the access key and access secret.  

Below are the step for enabling external system access and get access key and access secret.

### Step 1

Log in to PAXSTORE admin console as Super Admin or Market Admin and click General Setting in left menu.

![](/docs/assets/sc-1.png)

### Step 2

Click the left tab External System to show the external system configuration page like below screenshot.

![](/docs/assets/sc-2.png)

From above screenshot we know the external system access is disabled by default. To enable it please click the enable/disable switch. And once user clicked the switch it will pop up a confirm dialog to let user confirm.

![](/docs/assets/sc-3.png)

Click OK button to continue enabling the external system access. Click the CANCEL button to cancel current operation to keep external system access disabled.

After click OK button the external system access is enabled and the access key is shown in the page. But the access secret is replaced by asterisks for security purpose.

![](/docs/assets/sc-4.png)

### Step 3

Click the eye icon in external system configuration page to get the access secret.  It will show a dialog like below screenshot. 

![](/docs/assets/sc-5.png)

Please click OK button. And it will show the access secret instead of asterisks.

![](/docs/assets/sc-6.png)

For security purpose it only allow user to see the access secret one time. When user next time log in the access secret is replaced by asterisks again and no eye icon beside it. If user want to get the access secret again he/she must click the RESET button to get the new access key and access secret.  

Please keep the access key and access secret safely. Once the access key or access secret leaks please goto external system configuration page to disable external system access or reset the access key and access secret.



<br>
<br>

## Intergrate with RESTful API  


All the RESTful APIs of PAXSTORE Open API are secured by PAXSTORE, so before call the API the developer need to add a signature to http header.  

Steps of generating the signature:


**Step 1:**  

Make sure you have get the correct apiKey and apiSecret, and also you need to know all the query paramters of the API you want to call, please refer to the API description.  

For example the search terminal API needs the query parameters pageNo, limit, orderBy, serialNo, status, sysKey and timestamp.  
Note: the parameter sysKey(apiKey) and timestamp is not business related. But all the APIs need the 2 parameters. 


**Step 2:**   

Prepare the query string
The query string is combined by the query parameters of the API and the additional 2 parameters apiKey and timestamp.

Here still use search terminal API as a sample.  
 
Assume pageNo=1, limit=100, orderBy=name, status=A and serialNo=SN0001. The apiKey=RCA9MDH6YN3WSSGPW6TJ, apiSecret=I8PTHOZ9FIG6B39IWKDQBWDOJVQ7OGUSGATVG418 and the timestamp is 1533701655925.
So the query string may be like this 'pageNo=1&limit=100&orderBy=name&status=A&serialNo=SN0001&sysKey=RCA9MDH6YN3WSSGPW6TJ&timestamp=1533701655925'  

**Step 3:**  
Use HmacMD5 algorithm to encrypt the above query string. And the result is F1A4540D9D894C7DE996DFA2F86EF2FB.

**Step 4:**  

Add the result to http header. The header name is signature.

Below is the sample code of generate the signature using C#  

```
using System;
using System.Security.Cryptography;
using System.Net;
using System.Text;

namespace console
{
    class Program
    {
        private static string Hex(byte[] data)
        {
            var sb = new StringBuilder();
            foreach (var b in data)
                sb.Append(b.ToString("x2"));
            return sb.ToString();
        }
        
        static void Main(string[] args)
        {
            String queryStr = "pageNo=1&limit=100&orderBy=name&status=A&serialNo=SN0001&sysKey=RCA9MDH6YN3WSSGPW6TJ&timestamp=1533701655925";
            var signature =
              Hex(new HMACMD5 (Encoding.UTF8.GetBytes("I8PTHOZ9FIG6B39IWKDQBWDOJVQ7OGUSGATVG418")).ComputeHash(
                Encoding.UTF8.GetBytes(queryStr))).ToUpper();
        }
    }
}
```


For the success response JSON format please refer to API description. By the way the businessCode always 0 for success response JSON. And message is empty.

The failed response JSON format is like below.   

```
{
	"businessCode": 1801,
	"message": "Terminal doesn't exist"
}
```

Below are the possible businessCodes for all the APIs. These businessCodes won't be listed in the API description anymore.  

|Business Code|Message|Description|
|:--|:--|:--|
|129|Authentication failed||
|104|Client key is missing or invalid||
|108|Marketplace is not available||
|109|Marketplace is not active||
|105|Client key is blocked||
|103|Access token is invalid|Signature in http header is not correct|
|102|Access token is missing|Signature is not in http header|
|101|Invalid request method|The request method is not correct|
|113|Request parameter is missing or invalid||
|997|Malformed or illegal request|The JSON in request body is not a valid JSON|
|998|Bad request||
|999|Unknown error|Unknow error, please contact with support.|


<br/>

## Test Environment

Log into Admin Center of demo marketplace using marketplace admin account. Enable the 3rd system access for current marketplace and get key and secret.  

Base URL of API: https://api.whatspos.com/p-market-api  

## APIs

### [Reseller APIs](docs/RESELLER_REST_API.md)  

### [Merchant APIs](docs/MERCHANT_REST_API.md)  

### [Terminal APIs](docs/TERMINAL_REST_API.md)

### [Merchant Category APIs](docs/MERCHANT_CATEGORY_REST_API.md)

### [Country Codes](docs/COUNTRY_CODE.md)

<br>

## [Changelog](docs/CHANGELOG.md)




## License

See the [Apache 2.0 license](LICENSE) file for details.

    Copyright 2018 PAX Computer Technology(Shenzhen) CO., LTD ("PAX")

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at following link.

         http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

<br/>





