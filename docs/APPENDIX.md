## <a id="country-codes">Country Codes</a>

For creating/updating reseller or merchant the country property is mandatory. The API of this SDK requires the user pass a country code when create/update reseller or merchant. Below table listed all the country codes used in PAXSTORE admin console. The first column is country code, the value in this column is needed for the API of this SDK. And the label column is what you see in the dropdown list of country field in create/update merchant and reseller page in PAXSTORE admin console. And the description column is the description of the country code. Both label column and description column have English and Chinese language.  
If more country codes added to PAXSTORE we will update this table in time.

<table>
<tr>
<th rowspan="2">
Country Code
</th>
<th colspan="2">
Label
</th>
<th colspan="2">
Description
</th>
</tr>
<tr>
<th>English</th><th>Chinese</th><th>English</th><th>Chinese</th>
</tr>
<tr>
<td>CN</td><td>China</td><td>中国</td><td>China</td><td>中国</td>
</tr>
<tr>
<td>ITA</td><td>Italy</td><td>意大利</td><td>Italy</td><td>意大利</td>
</tr>
<tr>
<td>USA</td><td>U.S.A</td><td>美国</td><td>America</td><td>美国</td>
</tr>
<tr>
<td>IRN</td><td>Iran</td><td>伊朗</td><td>Iran</td><td>伊朗</td>
</tr>
<tr>
<td>BRA</td><td>Bresil</td><td>巴西</td><td>Bresil</td><td>巴西</td>
</tr>
<tr>
<td>GBR</td><td>United Kingdom</td><td>英国</td><td>United Kingdom</td><td>英国</td>
</tr>
<tr>
<td>AUS</td><td>Australia</td><td>澳大利亚</td><td>Australia</td><td>澳大利亚</td>
</tr>
<tr>
<td>MYS</td><td>Malaysia</td><td>马来西亚</td><td>Malaysia</td><td>马来西亚</td>
</tr>
<tr>
<td>JP</td><td>Japan</td><td>日本</td><td>Japan</td><td>日本</td>
</tr>
<tr>
<td>IND</td><td>India</td><td>印度</td><td>India</td><td>印度</td>
</tr>
</table>


## <a id="action-status">Action Status</a>

|Error Code|Description|
|:--|:--|
|0|Push task not started|
|1|Push task started|
|2|Push task success|
|3|Push task fail|
|4|Push task is waiting, no need push|


## <a id="action-error-codes">Action Error Codes</a>

|Error Code|Description|
|:--|:--|
|1|Download Failed|
|2|Install Failed|
|3|App Exist|
|4|Application Out Of Date|
|5|Application Param Duplicate|
|6|Application Not Exist|
|7|Application Version Mismatch|
|8|Uninstall Error|
|9|Task Expired|
|10|MD5 Validation Error|
|11|Uninstall Local Higher Version Failed|
|12|Task Disabled|
|13|Push Duplicate|
|14|Invalid File Status|
|17|Model Mismatch|
|18|Reseller Mismatch|
|19|File Already Installed|
|20|File Version Too Low|
|21|Set PED Failed|
|22|File Deleted By User|
|23|Invalid Parameter Variable|
|24|AIP ID Not Defined|
|25|Firmware Resource Mismatch|
|26|Task Invalid|
|27|Unable To Bind Terminal RKI Key|
|28|Parameter File Parse Failed|
|29|Terminal Removed From Group|
|30|Task Deleted|
|31|Application Deleted|
|32|Firmware Deleted|
|33|Terminal Group Deleted|
|34|Terminal Deleted|
|35|Param Task Cancelled As App Push Failed|
|36|Application can not be uninstalled|
|37|Filter failed|