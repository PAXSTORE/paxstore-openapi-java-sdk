## <a id="country-codes">Country Codes</a>

For creating/updating reseller or merchant the country property is mandatory. The API of this SDK requires the user pass
a country code when create/update reseller or merchant. Below table listed all the country codes used in PAXSTORE admin
console. The first column is country code, the value in this column is needed for the API of this SDK. And the label
column is what you see in the dropdown list of country field in create/update merchant and reseller page in PAXSTORE
admin console. And the description column is the description of the country code. Both label column and description
column have English and Chinese language.  
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
<td>BRA</td><td>Brazil</td><td>巴西</td><td>Brazil</td><td>巴西</td>
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
<td>JPN</td><td>Japan</td><td>日本</td><td>Japan</td><td>日本</td>
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
|0|Default Value|
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


## <a id="time-zone">Time Zone</a>
<div>
    <table class="tableblock frame-all grid-all spread">
        <colgroup>
            <col style="width: 35%;">
            <col style="width: 65%;">
        </colgroup>
        <thead>
        <tr>
            <th class="tableblock halign-left valign-middle">Label</th>
            <th class="tableblock halign-left valign-middle">Value</th>
        </tr>
        </thead>
        <tbody>
        <tr><td>(GMT-11:00) Niue Time</td><td>Pacific/Niue</td></tr>
        <tr><td>(GMT-11:00) Samoa Standard Time</td><td>Pacific/Pago_Pago</td></tr>
        <tr><td>(GMT-10:00) Cook Islands Standard Time</td><td>Pacific/Rarotonga</td></tr>
        <tr><td>(GMT-10:00) Hawaii-Aleutian Standard Time</td><td>Pacific/Honolulu</td></tr>
        <tr><td>(GMT-10:00) Tahiti Time</td><td>Pacific/Tahiti</td></tr>
        <tr><td>(GMT-09:30) Marquesas Time</td><td>Pacific/Marquesas</td></tr>
        <tr><td>(GMT-09:00) Gambier Time</td><td>Pacific/Gambier</td></tr>
        <tr><td>(GMT-09:00) Hawaii-Aleutian Time (Adak)</td><td>America/Adak</td></tr>
        <tr><td>(GMT-08:00) Alaska Time - Anchorage</td><td>America/Anchorage</td></tr>
        <tr><td>(GMT-08:00) Alaska Time - Juneau</td><td>America/Juneau</td></tr>
        <tr><td>(GMT-08:00) Alaska Time - Metlakatla</td><td>America/Metlakatla</td></tr>
        <tr><td>(GMT-08:00) Alaska Time - Nome</td><td>America/Nome</td></tr>
        <tr><td>(GMT-08:00) Alaska Time - Sitka</td><td>America/Sitka</td></tr>
        <tr><td>(GMT-08:00) Alaska Time - Yakutat</td><td>America/Yakutat</td></tr>
        <tr><td>(GMT-08:00) Pitcairn Time</td><td>Pacific/Pitcairn</td></tr>
        <tr><td>(GMT-07:00) Mexican Pacific Standard Time</td><td>America/Hermosillo</td></tr>
        <tr><td>(GMT-07:00) Mountain Standard Time - Creston</td><td>America/Creston</td></tr>
        <tr><td>(GMT-07:00) Mountain Standard Time - Dawson Creek</td><td>America/Dawson_Creek</td></tr>
        <tr><td>(GMT-07:00) Mountain Standard Time - Fort Nelson</td><td>America/Fort_Nelson</td></tr>
        <tr><td>(GMT-07:00) Mountain Standard Time - Phoenix</td><td>America/Phoenix</td></tr>
        <tr><td>(GMT-07:00) Pacific Time - Dawson</td><td>America/Dawson</td></tr>
        <tr><td>(GMT-07:00) Pacific Time - Los Angeles</td><td>America/Los_Angeles</td></tr>
        <tr><td>(GMT-07:00) Pacific Time - Tijuana</td><td>America/Tijuana</td></tr>
        <tr><td>(GMT-07:00) Pacific Time - Vancouver</td><td>America/Vancouver</td></tr>
        <tr><td>(GMT-07:00) Pacific Time - Whitehorse</td><td>America/Whitehorse</td></tr>
        <tr><td>(GMT-06:00) Central Standard Time - Belize</td><td>America/Belize</td></tr>
        <tr><td>(GMT-06:00) Central Standard Time - Costa Rica</td><td>America/Costa_Rica</td></tr>
        <tr><td>(GMT-06:00) Central Standard Time - El Salvador</td><td>America/El_Salvador</td></tr>
        <tr><td>(GMT-06:00) Central Standard Time - Guatemala</td><td>America/Guatemala</td></tr>
        <tr><td>(GMT-06:00) Central Standard Time - Managua</td><td>America/Managua</td></tr>
        <tr><td>(GMT-06:00) Central Standard Time - Regina</td><td>America/Regina</td></tr>
        <tr><td>(GMT-06:00) Central Standard Time - Swift Current</td><td>America/Swift_Current</td></tr>
        <tr><td>(GMT-06:00) Central Standard Time - Tegucigalpa</td><td>America/Tegucigalpa</td></tr>
        <tr><td>(GMT-06:00) Easter Island Time</td><td>Pacific/Easter</td></tr>
        <tr><td>(GMT-06:00) Galapagos Time</td><td>Pacific/Galapagos</td></tr>
        <tr><td>(GMT-06:00) Mexican Pacific Time - Chihuahua</td><td>America/Chihuahua</td></tr>
        <tr><td>(GMT-06:00) Mexican Pacific Time - Mazatlan</td><td>America/Mazatlan</td></tr>
        <tr><td>(GMT-06:00) Mountain Time - Boise</td><td>America/Boise</td></tr>
        <tr><td>(GMT-06:00) Mountain Time - Cambridge Bay</td><td>America/Cambridge_Bay</td></tr>
        <tr><td>(GMT-06:00) Mountain Time - Denver</td><td>America/Denver</td></tr>
        <tr><td>(GMT-06:00) Mountain Time - Edmonton</td><td>America/Edmonton</td></tr>
        <tr><td>(GMT-06:00) Mountain Time - Inuvik</td><td>America/Inuvik</td></tr>
        <tr><td>(GMT-06:00) Mountain Time - Ojinaga</td><td>America/Ojinaga</td></tr>
        <tr><td>(GMT-06:00) Mountain Time - Yellowknife</td><td>America/Yellowknife</td></tr>
        <tr><td>(GMT-05:00) Acre Standard Time - Eirunepe</td><td>America/Eirunepe</td></tr>
        <tr><td>(GMT-05:00) Acre Standard Time - Rio Branco</td><td>America/Rio_Branco</td></tr>
        <tr><td>(GMT-05:00) Central Time - Bahia Banderas</td><td>America/Bahia_Banderas</td></tr>
        <tr><td>(GMT-05:00) Central Time - Beulah, North Dakota</td><td>America/North_Dakota/Beulah</td></tr>
        <tr><td>(GMT-05:00) Central Time - Center, North Dakota</td><td>America/North_Dakota/Center</td></tr>
        <tr><td>(GMT-05:00) Central Time - Chicago</td><td>America/Chicago</td></tr>
        <tr><td>(GMT-05:00) Central Time - Knox, Indiana</td><td>America/Indiana/Knox</td></tr>
        <tr><td>(GMT-05:00) Central Time - Matamoros</td><td>America/Matamoros</td></tr>
        <tr><td>(GMT-05:00) Central Time - Menominee</td><td>America/Menominee</td></tr>
        <tr><td>(GMT-05:00) Central Time - Merida</td><td>America/Merida</td></tr>
        <tr><td>(GMT-05:00) Central Time - Mexico City</td><td>America/Mexico_City</td></tr>
        <tr><td>(GMT-05:00) Central Time - Monterrey</td><td>America/Monterrey</td></tr>
        <tr><td>(GMT-05:00) Central Time - New Salem, North Dakota</td><td>America/North_Dakota/New_Salem</td></tr>
        <tr><td>(GMT-05:00) Central Time - Rainy River</td><td>America/Rainy_River</td></tr>
        <tr><td>(GMT-05:00) Central Time - Rankin Inlet</td><td>America/Rankin_Inlet</td></tr>
        <tr><td>(GMT-05:00) Central Time - Resolute</td><td>America/Resolute</td></tr>
        <tr><td>(GMT-05:00) Central Time - Tell City, Indiana</td><td>America/Indiana/Tell_City</td></tr>
        <tr><td>(GMT-05:00) Central Time - Winnipeg</td><td>America/Winnipeg</td></tr>
        <tr><td>(GMT-05:00) Colombia Standard Time</td><td>America/Bogota</td></tr>
        <tr><td>(GMT-05:00) Eastern Standard Time - Atikokan</td><td>America/Atikokan</td></tr>
        <tr><td>(GMT-05:00) Eastern Standard Time - Cancun</td><td>America/Cancun</td></tr>
        <tr><td>(GMT-05:00) Eastern Standard Time - Jamaica</td><td>America/Jamaica</td></tr>
        <tr><td>(GMT-05:00) Eastern Standard Time - Panama</td><td>America/Panama</td></tr>
        <tr><td>(GMT-05:00) Ecuador Time</td><td>America/Guayaquil</td></tr>
        <tr><td>(GMT-05:00) Peru Standard Time</td><td>America/Lima</td></tr>
        <tr><td>(GMT-04:00) Amazon Standard Time - Boa Vista</td><td>America/Boa_Vista</td></tr>
        <tr><td>(GMT-04:00) Amazon Standard Time - Manaus</td><td>America/Manaus</td></tr>
        <tr><td>(GMT-04:00) Amazon Standard Time - Porto Velho</td><td>America/Porto_Velho</td></tr>
        <tr><td>(GMT-04:00) Amazon Time - Campo Grande</td><td>America/Campo_Grande</td></tr>
        <tr><td>(GMT-04:00) Amazon Time - Cuiaba</td><td>America/Cuiaba</td></tr>
        <tr><td>(GMT-04:00) Atlantic Standard Time - Barbados</td><td>America/Barbados</td></tr>
        <tr><td>(GMT-04:00) Atlantic Standard Time - Blanc-Sablon</td><td>America/Blanc-Sablon</td></tr>
        <tr><td>(GMT-04:00) Atlantic Standard Time - Cura&ccedil;ao</td><td>America/Curacao</td></tr>
        <tr><td>(GMT-04:00) Atlantic Standard Time - Martinique</td><td>America/Martinique</td></tr>
        <tr><td>(GMT-04:00) Atlantic Standard Time - Port of Spain</td><td>America/Port_of_Spain</td></tr>
        <tr><td>(GMT-04:00) Atlantic Standard Time - Puerto Rico</td><td>America/Puerto_Rico</td></tr>
        <tr><td>(GMT-04:00) Atlantic Standard Time - Santo Domingo</td><td>America/Santo_Domingo</td></tr>
        <tr><td>(GMT-04:00) Bolivia Time</td><td>America/La_Paz</td></tr>
        <tr><td>(GMT-04:00) Chile Time</td><td>America/Santiago</td></tr>
        <tr><td>(GMT-04:00) Cuba Time</td><td>America/Havana</td></tr>
        <tr><td>(GMT-04:00) Eastern Time - Detroit</td><td>America/Detroit</td></tr>
        <tr><td>(GMT-04:00) Eastern Time - Grand Turk</td><td>America/Grand_Turk</td></tr>
        <tr><td>(GMT-04:00) Eastern Time - Indianapolis</td><td>America/Indiana/Indianapolis</td></tr>
        <tr><td>(GMT-04:00) Eastern Time - Iqaluit</td><td>America/Iqaluit</td></tr>
        <tr><td>(GMT-04:00) Eastern Time - Louisville</td><td>America/Kentucky/Louisville</td></tr>
        <tr><td>(GMT-04:00) Eastern Time - Marengo, Indiana</td><td>America/Indiana/Marengo</td></tr>
        <tr><td>(GMT-04:00) Eastern Time - Monticello, Kentucky</td><td>America/Kentucky/Monticello</td></tr>
        <tr><td>(GMT-04:00) Eastern Time - Nassau</td><td>America/Nassau</td></tr>
        <tr><td>(GMT-04:00) Eastern Time - New York</td><td>America/New_York</td></tr>
        <tr><td>(GMT-04:00) Eastern Time - Nipigon</td><td>America/Nipigon</td></tr>
        <tr><td>(GMT-04:00) Eastern Time - Pangnirtung</td><td>America/Pangnirtung</td></tr>
        <tr><td>(GMT-04:00) Eastern Time - Petersburg, Indiana</td><td>America/Indiana/Petersburg</td></tr>
        <tr><td>(GMT-04:00) Eastern Time - Port-au-Prince</td><td>America/Port-au-Prince</td></tr>
        <tr><td>(GMT-04:00) Eastern Time - Thunder Bay</td><td>America/Thunder_Bay</td></tr>
        <tr><td>(GMT-04:00) Eastern Time - Toronto</td><td>America/Toronto</td></tr>
        <tr><td>(GMT-04:00) Eastern Time - Vevay, Indiana</td><td>America/Indiana/Vevay</td></tr>
        <tr><td>(GMT-04:00) Eastern Time - Vincennes, Indiana</td><td>America/Indiana/Vincennes</td></tr>
        <tr><td>(GMT-04:00) Eastern Time - Winamac, Indiana</td><td>America/Indiana/Winamac</td></tr>
        <tr><td>(GMT-04:00) Guyana Time</td><td>America/Guyana</td></tr>
        <tr><td>(GMT-04:00) Paraguay Time</td><td>America/Asuncion</td></tr>
        <tr><td>(GMT-04:00) Venezuela Time</td><td>America/Caracas</td></tr>
        <tr><td>(GMT-03:00) Argentina Standard Time - Buenos Aires</td><td>America/Argentina/Buenos_Aires</td></tr>
        <tr><td>(GMT-03:00) Argentina Standard Time - Catamarca</td><td>America/Argentina/Catamarca</td></tr>
        <tr><td>(GMT-03:00) Argentina Standard Time - Cordoba</td><td>America/Argentina/Cordoba</td></tr>
        <tr><td>(GMT-03:00) Argentina Standard Time - Jujuy</td><td>America/Argentina/Jujuy</td></tr>
        <tr><td>(GMT-03:00) Argentina Standard Time - La Rioja</td><td>America/Argentina/La_Rioja</td></tr>
        <tr><td>(GMT-03:00) Argentina Standard Time - Mendoza</td><td>America/Argentina/Mendoza</td></tr>
        <tr><td>(GMT-03:00) Argentina Standard Time - Rio Gallegos</td><td>America/Argentina/Rio_Gallegos</td></tr>
        <tr><td>(GMT-03:00) Argentina Standard Time - Salta</td><td>America/Argentina/Salta</td></tr>
        <tr><td>(GMT-03:00) Argentina Standard Time - San Juan</td><td>America/Argentina/San_Juan</td></tr>
        <tr><td>(GMT-03:00) Argentina Standard Time - Tucuman</td><td>America/Argentina/Tucuman</td></tr>
        <tr><td>(GMT-03:00) Argentina Standard Time - Ushuaia</td><td>America/Argentina/Ushuaia</td></tr>
        <tr><td>(GMT-03:00) Atlantic Time - Bermuda</td><td>Atlantic/Bermuda</td></tr>
        <tr><td>(GMT-03:00) Atlantic Time - Glace Bay</td><td>America/Glace_Bay</td></tr>
        <tr><td>(GMT-03:00) Atlantic Time - Goose Bay</td><td>America/Goose_Bay</td></tr>
        <tr><td>(GMT-03:00) Atlantic Time - Halifax</td><td>America/Halifax</td></tr>
        <tr><td>(GMT-03:00) Atlantic Time - Moncton</td><td>America/Moncton</td></tr>
        <tr><td>(GMT-03:00) Atlantic Time - Thule</td><td>America/Thule</td></tr>
        <tr><td>(GMT-03:00) Brasilia Standard Time - Araguaina</td><td>America/Araguaina</td></tr>
        <tr><td>(GMT-03:00) Brasilia Standard Time - Bahia</td><td>America/Bahia</td></tr>
        <tr><td>(GMT-03:00) Brasilia Standard Time - Belem</td><td>America/Belem</td></tr>
        <tr><td>(GMT-03:00) Brasilia Standard Time - Fortaleza</td><td>America/Fortaleza</td></tr>
        <tr><td>(GMT-03:00) Brasilia Standard Time - Maceio</td><td>America/Maceio</td></tr>
        <tr><td>(GMT-03:00) Brasilia Standard Time - Recife</td><td>America/Recife</td></tr>
        <tr><td>(GMT-03:00) Brasilia Standard Time - Santarem</td><td>America/Santarem</td></tr>
        <tr><td>(GMT-03:00) Brasilia Time</td><td>America/Sao_Paulo</td></tr>
        <tr><td>(GMT-03:00) Falkland Islands Standard Time</td><td>Atlantic/Stanley</td></tr>
        <tr><td>(GMT-03:00) French Guiana Time</td><td>America/Cayenne</td></tr>
        <tr><td>(GMT-03:00) Palmer Time</td><td>Antarctica/Palmer</td></tr>
        <tr><td>(GMT-03:00) Punta Arenas Time</td><td>America/Punta_Arenas</td></tr>
        <tr><td>(GMT-03:00) Rothera Time</td><td>Antarctica/Rothera</td></tr>
        <tr><td>(GMT-03:00) Suriname Time</td><td>America/Paramaribo</td></tr>
        <tr><td>(GMT-03:00) Uruguay Standard Time</td><td>America/Montevideo</td></tr>
        <tr><td>(GMT-03:00) Western Argentina Standard Time</td><td>America/Argentina/San_Luis</td></tr>
        <tr><td>(GMT-02:30) Newfoundland Time</td><td>America/St_Johns</td></tr>
        <tr><td>(GMT-02:00) Fernando de Noronha Standard Time</td><td>America/Noronha</td></tr>
        <tr><td>(GMT-02:00) South Georgia Time</td><td>Atlantic/South_Georgia</td></tr>
        <tr><td>(GMT-02:00) St. Pierre &amp;amp; Miquelon Time</td><td>America/Miquelon</td></tr>
        <tr><td>(GMT-02:00) West Greenland Time</td><td>America/Godthab</td></tr>
        <tr><td>(GMT-01:00) Cape Verde Standard Time</td><td>Atlantic/Cape_Verde</td></tr>
        <tr><td>(GMT+00:00) Azores Time</td><td>Atlantic/Azores</td></tr>
        <tr><td>(GMT+00:00) Coordinated Universal Time</td><td>UTC</td></tr>
        <tr><td>(GMT+00:00) East Greenland Time</td><td>America/Scoresbysund</td></tr>
        <tr><td>(GMT+00:00) Greenwich Mean Time</td><td>Etc/GMT</td></tr>
        <tr><td>(GMT+00:00) Greenwich Mean Time - Abidjan</td><td>Africa/Abidjan</td></tr>
        <tr><td>(GMT+00:00) Greenwich Mean Time - Accra</td><td>Africa/Accra</td></tr>
        <tr><td>(GMT+00:00) Greenwich Mean Time - Bissau</td><td>Africa/Bissau</td></tr>
        <tr><td>(GMT+00:00) Greenwich Mean Time - Danmarkshavn</td><td>America/Danmarkshavn</td></tr>
        <tr><td>(GMT+00:00) Greenwich Mean Time - Monrovia</td><td>Africa/Monrovia</td></tr>
        <tr><td>(GMT+00:00) Greenwich Mean Time - Reykjavik</td><td>Atlantic/Reykjavik</td></tr>
        <tr><td>(GMT+00:00) Greenwich Mean Time - S&atilde;o Tom&eacute;</td><td>Africa/Sao_Tome</td></tr>
        <tr><td>(GMT+01:00) Central European Standard Time - Algiers</td><td>Africa/Algiers</td></tr>
        <tr><td>(GMT+01:00) Central European Standard Time - Tunis</td><td>Africa/Tunis</td></tr>
        <tr><td>(GMT+01:00) Ireland Time</td><td>Europe/Dublin</td></tr>
        <tr><td>(GMT+01:00) Morocco Time</td><td>Africa/Casablanca</td></tr>
        <tr><td>(GMT+01:00) United Kingdom Time</td><td>Europe/London</td></tr>
        <tr><td>(GMT+01:00) West Africa Standard Time - Lagos</td><td>Africa/Lagos</td></tr>
        <tr><td>(GMT+01:00) West Africa Standard Time - Ndjamena</td><td>Africa/Ndjamena</td></tr>
        <tr><td>(GMT+01:00) Western European Time - Canary</td><td>Atlantic/Canary</td></tr>
        <tr><td>(GMT+01:00) Western European Time - Faroe</td><td>Atlantic/Faroe</td></tr>
        <tr><td>(GMT+01:00) Western European Time - Lisbon</td><td>Europe/Lisbon</td></tr>
        <tr><td>(GMT+01:00) Western European Time - Madeira</td><td>Atlantic/Madeira</td></tr>
        <tr><td>(GMT+01:00) Western Sahara Time</td><td>Africa/El_Aaiun</td></tr>
        <tr><td>(GMT+02:00) Central Africa Time - Khartoum</td><td>Africa/Khartoum</td></tr>
        <tr><td>(GMT+02:00) Central Africa Time - Maputo</td><td>Africa/Maputo</td></tr>
        <tr><td>(GMT+02:00) Central Africa Time - Windhoek</td><td>Africa/Windhoek</td></tr>
        <tr><td>(GMT+02:00) Central European Time - Amsterdam</td><td>Europe/Amsterdam</td></tr>
        <tr><td>(GMT+02:00) Central European Time - Andorra</td><td>Europe/Andorra</td></tr>
        <tr><td>(GMT+02:00) Central European Time - Belgrade</td><td>Europe/Belgrade</td></tr>
        <tr><td>(GMT+02:00) Central European Time - Berlin</td><td>Europe/Berlin</td></tr>
        <tr><td>(GMT+02:00) Central European Time - Brussels</td><td>Europe/Brussels</td></tr>
        <tr><td>(GMT+02:00) Central European Time - Budapest</td><td>Europe/Budapest</td></tr>
        <tr><td>(GMT+02:00) Central European Time - Ceuta</td><td>Africa/Ceuta</td></tr>
        <tr><td>(GMT+02:00) Central European Time - Copenhagen</td><td>Europe/Copenhagen</td></tr>
        <tr><td>(GMT+02:00) Central European Time - Gibraltar</td><td>Europe/Gibraltar</td></tr>
        <tr><td>(GMT+02:00) Central European Time - Luxembourg</td><td>Europe/Luxembourg</td></tr>
        <tr><td>(GMT+02:00) Central European Time - Madrid</td><td>Europe/Madrid</td></tr>
        <tr><td>(GMT+02:00) Central European Time - Malta</td><td>Europe/Malta</td></tr>
        <tr><td>(GMT+02:00) Central European Time - Monaco</td><td>Europe/Monaco</td></tr>
        <tr><td>(GMT+02:00) Central European Time - Oslo</td><td>Europe/Oslo</td></tr>
        <tr><td>(GMT+02:00) Central European Time - Paris</td><td>Europe/Paris</td></tr>
        <tr><td>(GMT+02:00) Central European Time - Prague</td><td>Europe/Prague</td></tr>
        <tr><td>(GMT+02:00) Central European Time - Rome</td><td>Europe/Rome</td></tr>
        <tr><td>(GMT+02:00) Central European Time - Stockholm</td><td>Europe/Stockholm</td></tr>
        <tr><td>(GMT+02:00) Central European Time - Tirane</td><td>Europe/Tirane</td></tr>
        <tr><td>(GMT+02:00) Central European Time - Vienna</td><td>Europe/Vienna</td></tr>
        <tr><td>(GMT+02:00) Central European Time - Warsaw</td><td>Europe/Warsaw</td></tr>
        <tr><td>(GMT+02:00) Central European Time - Zurich</td><td>Europe/Zurich</td></tr>
        <tr><td>(GMT+02:00) Eastern European Standard Time - Cairo</td><td>Africa/Cairo</td></tr>
        <tr><td>(GMT+02:00) Eastern European Standard Time - Kaliningrad</td><td>Europe/Kaliningrad</td></tr>
        <tr><td>(GMT+02:00) Eastern European Standard Time - Tripoli</td><td>Africa/Tripoli</td></tr>
        <tr><td>(GMT+02:00) South Africa Standard Time</td><td>Africa/Johannesburg</td></tr>
        <tr><td>(GMT+02:00) Troll Time</td><td>Antarctica/Troll</td></tr>
        <tr><td>(GMT+03:00) Arabian Standard Time - Baghdad</td><td>Asia/Baghdad</td></tr>
        <tr><td>(GMT+03:00) Arabian Standard Time - Qatar</td><td>Asia/Qatar</td></tr>
        <tr><td>(GMT+03:00) Arabian Standard Time - Riyadh</td><td>Asia/Riyadh</td></tr>
        <tr><td>(GMT+03:00) East Africa Time - Juba</td><td>Africa/Juba</td></tr>
        <tr><td>(GMT+03:00) East Africa Time - Nairobi</td><td>Africa/Nairobi</td></tr>
        <tr><td>(GMT+03:00) Eastern European Time - Amman</td><td>Asia/Amman</td></tr>
        <tr><td>(GMT+03:00) Eastern European Time - Athens</td><td>Europe/Athens</td></tr>
        <tr><td>(GMT+03:00) Eastern European Time - Beirut</td><td>Asia/Beirut</td></tr>
        <tr><td>(GMT+03:00) Eastern European Time - Bucharest</td><td>Europe/Bucharest</td></tr>
        <tr><td>(GMT+03:00) Eastern European Time - Chisinau</td><td>Europe/Chisinau</td></tr>
        <tr><td>(GMT+03:00) Eastern European Time - Damascus</td><td>Asia/Damascus</td></tr>
        <tr><td>(GMT+03:00) Eastern European Time - Gaza</td><td>Asia/Gaza</td></tr>
        <tr><td>(GMT+03:00) Eastern European Time - Hebron</td><td>Asia/Hebron</td></tr>
        <tr><td>(GMT+03:00) Eastern European Time - Helsinki</td><td>Europe/Helsinki</td></tr>
        <tr><td>(GMT+03:00) Eastern European Time - Kiev</td><td>Europe/Kiev</td></tr>
        <tr><td>(GMT+03:00) Eastern European Time - Nicosia</td><td>Asia/Nicosia</td></tr>
        <tr><td>(GMT+03:00) Eastern European Time - Riga</td><td>Europe/Riga</td></tr>
        <tr><td>(GMT+03:00) Eastern European Time - Sofia</td><td>Europe/Sofia</td></tr>
        <tr><td>(GMT+03:00) Eastern European Time - Tallinn</td><td>Europe/Tallinn</td></tr>
        <tr><td>(GMT+03:00) Eastern European Time - Uzhhorod</td><td>Europe/Uzhgorod</td></tr>
        <tr><td>(GMT+03:00) Eastern European Time - Vilnius</td><td>Europe/Vilnius</td></tr>
        <tr><td>(GMT+03:00) Eastern European Time - Zaporozhye</td><td>Europe/Zaporozhye</td></tr>
        <tr><td>(GMT+03:00) Famagusta Time</td><td>Asia/Famagusta</td></tr>
        <tr><td>(GMT+03:00) Israel Time</td><td>Asia/Jerusalem</td></tr>
        <tr><td>(GMT+03:00) Kirov Time</td><td>Europe/Kirov</td></tr>
        <tr><td>(GMT+03:00) Moscow Standard Time - Minsk</td><td>Europe/Minsk</td></tr>
        <tr><td>(GMT+03:00) Moscow Standard Time - Moscow</td><td>Europe/Moscow</td></tr>
        <tr><td>(GMT+03:00) Moscow Standard Time - Simferopol</td><td>Europe/Simferopol</td></tr>
        <tr><td>(GMT+03:00) Syowa Time</td><td>Antarctica/Syowa</td></tr>
        <tr><td>(GMT+03:00) Turkey Time</td><td>Europe/Istanbul</td></tr>
        <tr><td>(GMT+04:00) Armenia Standard Time</td><td>Asia/Yerevan</td></tr>
        <tr><td>(GMT+04:00) Astrakhan Time</td><td>Europe/Astrakhan</td></tr>
        <tr><td>(GMT+04:00) Azerbaijan Standard Time</td><td>Asia/Baku</td></tr>
        <tr><td>(GMT+04:00) Georgia Standard Time</td><td>Asia/Tbilisi</td></tr>
        <tr><td>(GMT+04:00) Gulf Standard Time</td><td>Asia/Dubai</td></tr>
        <tr><td>(GMT+04:00) Mauritius Standard Time</td><td>Indian/Mauritius</td></tr>
        <tr><td>(GMT+04:00) R&eacute;union Time</td><td>Indian/Reunion</td></tr>
        <tr><td>(GMT+04:00) Samara Standard Time</td><td>Europe/Samara</td></tr>
        <tr><td>(GMT+04:00) Saratov Time</td><td>Europe/Saratov</td></tr>
        <tr><td>(GMT+04:00) Seychelles Time</td><td>Indian/Mahe</td></tr>
        <tr><td>(GMT+04:00) Ulyanovsk Time</td><td>Europe/Ulyanovsk</td></tr>
        <tr><td>(GMT+04:00) Volgograd Standard Time</td><td>Europe/Volgograd</td></tr>
        <tr><td>(GMT+04:30) Afghanistan Time</td><td>Asia/Kabul</td></tr>
        <tr><td>(GMT+04:30) Iran Time</td><td>Asia/Tehran</td></tr>
        <tr><td>(GMT+05:00) French Southern &amp;amp; Antarctic Time</td><td>Indian/Kerguelen</td></tr>
        <tr><td>(GMT+05:00) Maldives Time</td><td>Indian/Maldives</td></tr>
        <tr><td>(GMT+05:00) Mawson Time</td><td>Antarctica/Mawson</td></tr>
        <tr><td>(GMT+05:00) Pakistan Standard Time</td><td>Asia/Karachi</td></tr>
        <tr><td>(GMT+05:00) Tajikistan Time</td><td>Asia/Dushanbe</td></tr>
        <tr><td>(GMT+05:00) Turkmenistan Standard Time</td><td>Asia/Ashgabat</td></tr>
        <tr><td>(GMT+05:00) Uzbekistan Standard Time - Samarkand</td><td>Asia/Samarkand</td></tr>
        <tr><td>(GMT+05:00) Uzbekistan Standard Time - Tashkent</td><td>Asia/Tashkent</td></tr>
        <tr><td>(GMT+05:00) West Kazakhstan Time - Aqtau</td><td>Asia/Aqtau</td></tr>
        <tr><td>(GMT+05:00) West Kazakhstan Time - Aqtobe</td><td>Asia/Aqtobe</td></tr>
        <tr><td>(GMT+05:00) West Kazakhstan Time - Atyrau</td><td>Asia/Atyrau</td></tr>
        <tr><td>(GMT+05:00) West Kazakhstan Time - Oral</td><td>Asia/Oral</td></tr>
        <tr><td>(GMT+05:00) West Kazakhstan Time - Qyzylorda</td><td>Asia/Qyzylorda</td></tr>
        <tr><td>(GMT+05:00) Yekaterinburg Standard Time</td><td>Asia/Yekaterinburg</td></tr>
        <tr><td>(GMT+05:30) India Standard Time - Colombo</td><td>Asia/Colombo</td></tr>
        <tr><td>(GMT+05:30) India Standard Time - Kolkata</td><td>Asia/Kolkata</td></tr>
        <tr><td>(GMT+05:45) Nepal Time</td><td>Asia/Kathmandu</td></tr>
        <tr><td>(GMT+06:00) Bangladesh Standard Time</td><td>Asia/Dhaka</td></tr>
        <tr><td>(GMT+06:00) Bhutan Time</td><td>Asia/Thimphu</td></tr>
        <tr><td>(GMT+06:00) Indian Ocean Time</td><td>Indian/Chagos</td></tr>
        <tr><td>(GMT+06:00) Kyrgyzstan Time</td><td>Asia/Bishkek</td></tr>
        <tr><td>(GMT+06:00) Omsk Standard Time</td><td>Asia/Omsk</td></tr>
        <tr><td>(GMT+06:00) Urumqi Time</td><td>Asia/Urumqi</td></tr>
        <tr><td>(GMT+06:00) Vostok Time</td><td>Antarctica/Vostok</td></tr>
        <tr><td>(GMT+06:30) Cocos Islands Time</td><td>Indian/Cocos</td></tr>
        <tr><td>(GMT+06:30) Myanmar Time</td><td>Asia/Yangon</td></tr>
        <tr><td>(GMT+07:00) Barnaul Time</td><td>Asia/Barnaul</td></tr>
        <tr><td>(GMT+07:00) Christmas Island Time</td><td>Indian/Christmas</td></tr>
        <tr><td>(GMT+07:00) Davis Time</td><td>Antarctica/Davis</td></tr>
        <tr><td>(GMT+07:00) Hovd Standard Time</td><td>Asia/Hovd</td></tr>
        <tr><td>(GMT+07:00) Indochina Time - Bangkok</td><td>Asia/Bangkok</td></tr>
        <tr><td>(GMT+07:00) Indochina Time - Ho Chi Minh City</td><td>Asia/Ho_Chi_Minh</td></tr>
        <tr><td>(GMT+07:00) Krasnoyarsk Standard Time - Krasnoyarsk</td><td>Asia/Krasnoyarsk</td></tr>
        <tr><td>(GMT+07:00) Krasnoyarsk Standard Time - Novokuznetsk</td><td>Asia/Novokuznetsk</td></tr>
        <tr><td>(GMT+07:00) Novosibirsk Standard Time</td><td>Asia/Novosibirsk</td></tr>
        <tr><td>(GMT+07:00) Tomsk Time</td><td>Asia/Tomsk</td></tr>
        <tr><td>(GMT+07:00) Western Indonesia Time - Jakarta</td><td>Asia/Jakarta</td></tr>
        <tr><td>(GMT+07:00) Western Indonesia Time - Pontianak</td><td>Asia/Pontianak</td></tr>
        <tr><td>(GMT+08:00) Australian Western Standard Time - Casey</td><td>Antarctica/Casey</td></tr>
        <tr><td>(GMT+08:00) Australian Western Standard Time - Perth</td><td>Australia/Perth</td></tr>
        <tr><td>(GMT+08:00) Brunei Darussalam Time</td><td>Asia/Brunei</td></tr>
        <tr><td>(GMT+08:00) Central Indonesia Time</td><td>Asia/Makassar</td></tr>
        <tr><td>(GMT+08:00) China Standard Time - Macao</td><td>Asia/Macau</td></tr>
        <tr><td>(GMT+08:00) China Standard Time - Shanghai</td><td>Asia/Shanghai</td></tr>
        <tr><td>(GMT+08:00) Choibalsan Standard Time</td><td>Asia/Choibalsan</td></tr>
        <tr><td>(GMT+08:00) Hong Kong Standard Time</td><td>Asia/Hong_Kong</td></tr>
        <tr><td>(GMT+08:00) Irkutsk Standard Time</td><td>Asia/Irkutsk</td></tr>
        <tr><td>(GMT+08:00) Malaysia Time - Kuala Lumpur</td><td>Asia/Kuala_Lumpur</td></tr>
        <tr><td>(GMT+08:00) Malaysia Time - Kuching</td><td>Asia/Kuching</td></tr>
        <tr><td>(GMT+08:00) Philippine Standard Time</td><td>Asia/Manila</td></tr>
        <tr><td>(GMT+08:00) Singapore Standard Time</td><td>Asia/Singapore</td></tr>
        <tr><td>(GMT+08:00) Taipei Standard Time</td><td>Asia/Taipei</td></tr>
        <tr><td>(GMT+08:00) Ulaanbaatar Standard Time</td><td>Asia/Ulaanbaatar</td></tr>
        <tr><td>(GMT+08:45) Australian Central Western Standard Time</td><td>Australia/Eucla</td></tr>
        <tr><td>(GMT+09:00) East Timor Time</td><td>Asia/Dili</td></tr>
        <tr><td>(GMT+09:00) Eastern Indonesia Time</td><td>Asia/Jayapura</td></tr>
        <tr><td>(GMT+09:00) Japan Standard Time</td><td>Asia/Tokyo</td></tr>
        <tr><td>(GMT+09:00) Korean Standard Time - Pyongyang</td><td>Asia/Pyongyang</td></tr>
        <tr><td>(GMT+09:00) Korean Standard Time - Seoul</td><td>Asia/Seoul</td></tr>
        <tr><td>(GMT+09:00) Palau Time</td><td>Pacific/Palau</td></tr>
        <tr><td>(GMT+09:00) Yakutsk Standard Time - Chita</td><td>Asia/Chita</td></tr>
        <tr><td>(GMT+09:00) Yakutsk Standard Time - Khandyga</td><td>Asia/Khandyga</td></tr>
        <tr><td>(GMT+09:00) Yakutsk Standard Time - Yakutsk</td><td>Asia/Yakutsk</td></tr>
        <tr><td>(GMT+09:30) Australian Central Standard Time</td><td>Australia/Darwin</td></tr>
        <tr><td>(GMT+09:30) Central Australia Time - Adelaide</td><td>Australia/Adelaide</td></tr>
        <tr><td>(GMT+09:30) Central Australia Time - Broken Hill</td><td>Australia/Broken_Hill</td></tr>
        <tr><td>(GMT+10:00) Australian Eastern Standard Time - Brisbane</td><td>Australia/Brisbane</td></tr>
        <tr><td>(GMT+10:00) Australian Eastern Standard Time - Lindeman</td><td>Australia/Lindeman</td></tr>
        <tr><td>(GMT+10:00) Chamorro Standard Time</td><td>Pacific/Guam</td></tr>
        <tr><td>(GMT+10:00) Chuuk Time</td><td>Pacific/Chuuk</td></tr>
        <tr><td>(GMT+10:00) Dumont-d&rsquo;Urville Time</td><td>Antarctica/DumontDUrville</td></tr>
        <tr><td>(GMT+10:00) Eastern Australia Time - Currie</td><td>Australia/Currie</td></tr>
        <tr><td>(GMT+10:00) Eastern Australia Time - Hobart</td><td>Australia/Hobart</td></tr>
        <tr><td>(GMT+10:00) Eastern Australia Time - Melbourne</td><td>Australia/Melbourne</td></tr>
        <tr><td>(GMT+10:00) Eastern Australia Time - Sydney</td><td>Australia/Sydney</td></tr>
        <tr><td>(GMT+10:00) Papua New Guinea Time</td><td>Pacific/Port_Moresby</td></tr>
        <tr><td>(GMT+10:00) Vladivostok Standard Time - Ust-Nera</td><td>Asia/Ust-Nera</td></tr>
        <tr><td>(GMT+10:00) Vladivostok Standard Time - Vladivostok</td><td>Asia/Vladivostok</td></tr>
        <tr><td>(GMT+10:30) Lord Howe Time</td><td>Australia/Lord_Howe</td></tr>
        <tr><td>(GMT+11:00) Bougainville Time</td><td>Pacific/Bougainville</td></tr>
        <tr><td>(GMT+11:00) Kosrae Time</td><td>Pacific/Kosrae</td></tr>
        <tr><td>(GMT+11:00) Macquarie Island Time</td><td>Antarctica/Macquarie</td></tr>
        <tr><td>(GMT+11:00) Magadan Standard Time</td><td>Asia/Magadan</td></tr>
        <tr><td>(GMT+11:00) New Caledonia Standard Time</td><td>Pacific/Noumea</td></tr>
        <tr><td>(GMT+11:00) Norfolk Island Time</td><td>Pacific/Norfolk</td></tr>
        <tr><td>(GMT+11:00) Ponape Time</td><td>Pacific/Pohnpei</td></tr>
        <tr><td>(GMT+11:00) Sakhalin Standard Time</td><td>Asia/Sakhalin</td></tr>
        <tr><td>(GMT+11:00) Solomon Islands Time</td><td>Pacific/Guadalcanal</td></tr>
        <tr><td>(GMT+11:00) Srednekolymsk Time</td><td>Asia/Srednekolymsk</td></tr>
        <tr><td>(GMT+11:00) Vanuatu Standard Time</td><td>Pacific/Efate</td></tr>
        <tr><td>(GMT+12:00) Anadyr Standard Time</td><td>Asia/Anadyr</td></tr>
        <tr><td>(GMT+12:00) Fiji Time</td><td>Pacific/Fiji</td></tr>
        <tr><td>(GMT+12:00) Gilbert Islands Time</td><td>Pacific/Tarawa</td></tr>
        <tr><td>(GMT+12:00) Marshall Islands Time - Kwajalein</td><td>Pacific/Kwajalein</td></tr>
        <tr><td>(GMT+12:00) Marshall Islands Time - Majuro</td><td>Pacific/Majuro</td></tr>
        <tr><td>(GMT+12:00) Nauru Time</td><td>Pacific/Nauru</td></tr>
        <tr><td>(GMT+12:00) New Zealand Time</td><td>Pacific/Auckland</td></tr>
        <tr><td>(GMT+12:00) Petropavlovsk-Kamchatski Standard Time</td><td>Asia/Kamchatka</td></tr>
        <tr><td>(GMT+12:00) Tuvalu Time</td><td>Pacific/Funafuti</td></tr>
        <tr><td>(GMT+12:00) Wake Island Time</td><td>Pacific/Wake</td></tr>
        <tr><td>(GMT+12:00) Wallis &amp;amp; Futuna Time</td><td>Pacific/Wallis</td></tr>
        <tr><td>(GMT+12:45) Chatham Time</td><td>Pacific/Chatham</td></tr>
        <tr><td>(GMT+13:00) Apia Time</td><td>Pacific/Apia</td></tr>
        <tr><td>(GMT+13:00) Phoenix Islands Time</td><td>Pacific/Enderbury</td></tr>
        <tr><td>(GMT+13:00) Tokelau Time</td><td>Pacific/Fakaofo</td></tr>
        <tr><td>(GMT+13:00) Tonga Standard Time</td><td>Pacific/Tongatapu</td></tr>
        <tr><td>(GMT+14:00) Line Islands Time</td><td>Pacific/Kiritimati</td></tr>
        </tbody>
    </table>
</div>