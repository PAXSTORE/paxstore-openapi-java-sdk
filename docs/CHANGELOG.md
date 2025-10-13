# Changelog  

## 10.0.0
### New Feature
* Add parameter query `includeMasterTerminal` and return in TerminalApi-`getTerminal`, `getTerminalBySn` API
* Add AppApi API `searchApkParameterPidList` and return pidList
* Add TerminalApi API `pushTerminalSetLauncherAction`, `pushTerminalSetLauncherActionBySN`
* Add EmmDeviceApi - `resumeEmmDevice` API
* Add EmmDeviceApi - `disableEmmDevice` API
* Add EmmDeviceApi - `syncDeviceDetail` API
* Add EmmPolicyApi - `getDeviceEmmPolicy` API
* Add EmmPolicyApi - `createDeviceEmmPolicy` API
* Add the return field `securityStatus` in EmmDeviceApi - `getEmmDevice` API
* Add the return field `lockedByMerchant` in EmmPolicyApi - `getDeviceEmmPolicy` API
* Add the return field `apnPolicy` in EmmPolicyApi - `getResellerEmmPolicy`,`getMerchantEmmPolicy`,`getDeviceEmmPolicy`,`createResellerEmmPolicy`,`createMerchantEmmPolicy`,`createDeviceEmmPolicy` API
* Apps uploaded outside the developer console also support test tracks
* Optimize documentation and some return fields
  * a. appPageDTO add `text` field - The app price, when app cost use

### Dependency Change
* Update guava from 31.1-jre to 33.4.8-jre
* Update commons-lang3 3.6 to 3.18.0

## 9.8.0
### New Feature
* Add parameter query `includeInstalledFirmware` and return in TerminalApi-`getTerminal`、`getTerminalBySn` API
* Add APPENDIX.md - Add the description of the timeZone  
* Add EmmAppApi - `getAvailableTestTrackVersionList` API
* Add the return field `accessibleTrackId` in EmmPolicyApi - `getResellerEmmPolicy`,`getMerchantEmmPolicy`,`createResellerEmmPolicy`,`createMerchantEmmPolicy` API
* Add parameter query `serialNo` 、`pidList` and  return in TerminalApkApi-`searchTerminalApk` API
* Add parameter query `serialNo` and  return in TerminalRkiApi-`searchPushRkiTasks` API
* Add TerminalApkApi - `deleteTerminalApk` API
* Add TerminalFirmwareApi - `deleteTerminalFirmware` API
* Add TerminalRkiApi - `deleteTerminalRki` API
* Add the parameter `validateUndefinedParameter` in TerminalApkParameterApi - `createApkParameter`,`updateApkParameter`, TerminalGroupApkApi - `createAndActiveGroupApk`, TerminalApkApi - `createTerminalApk` API


## 9.7.1
### Bug Fix
* Add the return field `enableRemoteControl` in EmmPolicyApi - `getResellerEmmPolicy`,`getMerchantEmmPolicy`,`createResellerEmmPolicy`,`createMerchantEmmPolicy` API

## 9.7.0
### New Feature
* Add the return field `iccid2` in TerminalApi - `getTerminal` or `getTerminalBySn` API
* Remove parameter factoryName from TerminalApi - `changeModel`,`changeModelBySN` API
* Add FactoryModelApi - `searchFactoryModels` API
* TerminalApi - `pushCmdToTerminal`, `pushCmdToTerminalBySn` API, Optimize the error message for terminal locking
* Add the return field `packageName` in EmmDeviceDetailApi - `searchDeviceInstalledApp` API
* Modify the parameters `name` and `packageName` to `keyWords` in EmmAppApi - `searchEmmApp` API
* Modify EMM policy related APIs, Please refer to the corresponding API doc for details  

### Breaking  
* The method `searchEmmApp` in EmmAppApi in the new SDK is not compatible with old previous SDK. Please refer to the new feature.

## 9.6.1  
### Bug Fix  
* Fix EMM related API issue, please upgrade to SDK version 9.6.1

## 9.6.0
### New Feature
* Add the return field `updatedDate` and `lastAccessTime` in Search Terminal API
* Add TerminalApi - `pushTerminalMessage` API
* Add TerminalApi - `pushTerminalMessageBySn` API
* Add TerminalApi - `getTerminalSystemUsageById` API
* Add TerminalApi - `getTerminalSystemUsageBySn` API
* Add TerminalApi - `collectTerminalLog` API
* Add TerminalApi - `collectTerminalLogBySn` API
* Add TerminalApi - `searchTerminalLog` API
* Add TerminalApi - `searchTerminalLogBySn` API
* Add TerminalApi - `getTerminalLogDownloadTask` API
* Add TerminalApi - `getTerminalLogDownloadTaskBySn` API
* Add TerminalApi - `changeModel` API
* Add TerminalApi - `changeModelBySN` API
* Add EmmAppApi - `searchEmmApp` API
* Add EmmAppApi - `createEmmApp` API
* Add EmmAppApi - `getEmmAppDetail` API
* Add EmmAppApi - `removeEmmApp` API
* Add EmmAppApi - `searchSubscribeEmmApp` API
* Add EmmAppApi - `subscribeEmmApp` API
* Add EmmAppApi - `unSubscribeEmmApp` API
* Add EmmAppApi - `getEmmAppPermissionList` API
* Add EmmDeviceApi - `createRegisterQRCode` API
* Add EmmDeviceApi - `searchEmmDevice` API
* Add EmmDeviceApi - `getEmmDevice` API
* Add EmmDeviceApi - `updateEmmDevice` API
* Add EmmDeviceApi - `batchMoveEmmDevice` API
* Add EmmDeviceApi - `deleteEmmDevice` API
* Add EmmDeviceApi - `batchDeleteEmmDevice` API
* Add EmmDeviceApi - `rebootEmmDevice` API
* Add EmmDeviceApi - `lockEmmDeviceScreen` API
* Add EmmDeviceApi - `resetEmmDevicePassword` API
* Add EmmDeviceApi - `startEmmDeviceLostMode` API
* Add EmmDeviceApi - `stopEmmDeviceLostMode` API
* Add EmmDeviceApi - `submitEmmZteQuickUploadRecord` API
* Add EmmDeviceDetailApi - `getEmmDeviceDashboardDetail` API
* Add EmmDeviceDetailApi - `getEmmDeviceDashboardMonitor` API
* Add EmmDeviceDetailApi - `searchDeviceInstalledApp` API
* Add EmmPolicyApi - `getResellerEmmPolicy` API
* Add EmmPolicyApi - `getMerchantEmmPolicy` API
* Add EmmPolicyApi - `createResellerEmmPolicy` API
* Add EmmPolicyApi - `createMerchantEmmPolicy` API
* Add AppApi - `getAppCost` API

## 9.5.0
### New Feature
* New support for GoInsight API - `findDataFromInsight` queries through parameter `customFilterList`
* Modify `README.md` - Explanation of Problems Encountered in Optimizing the SDK Authentication Process and the Enhancement of Error Codes
* Version greater than or equal to 9.5.0 authentication signature encryption upgrade

## 9.4.0
### New Feature
* New support for terminal API based on serialNo

### Bug Fix
* Fix the handling for HTTP status 204


## 9.3.1
### Bug Fix
* Fix AppApi.AppSearchOrderBy.AppName_desc and AppApi.AppSearchOrderBy.AppName_asc

## 9.3.0
### New Feature
* Add terminal time zone configuration.
* Add terminal geoFence whiteList configuration Api: search，create,delete Api.


## 9.2.2
### Bug Fix
* Fix default type conversion:  TerminalFirmwareApi.pushFirmware2Terminal,TerminalRkiApi.pushRkiKey2Terminal,TerminalGroupRkiApi.pushRkiKey2Group


## 9.2.1
### Bug Fix
* Compatible with older versions, TerminalApkApi, TerminalRkiApi, and TerminalFirmwareApi all return A(Active) for parameters or responses related to status when it is C(Completed).

### Improvement
* Add possible error codes for TERMINAL_API doc.


## 9.2.0  
### Improvement  
* Improved retry strategy. When server return 502 response code the retry will work too.


## 9.1.0
### New Feature
* Add `filteredCount` field to the Terminal Group Apk  document.

## 9.0.0
### New Feature
* Add get terminal network information API

## 8.8.3  
### Improvement  
* Update guava and gson version  

## 8.8.2
### Bug Fix
* Fix isValidEmailAddress method in custom validation.
* Fix method removeTerminalOutGroup in TerminalGroupApi.
* Add field description to the merchant API document.

## 8.8.1

### Bug Fix

* Fix validateRange method in custom validation.


## 8.8.0
### New Feature
* Add search field modelName to app API

### Improvement
* Remove dependency `hibernate-validator` and use custom validation.  
  There may be some error messages in the custom validation process that are different from the error messages provided by the original dependency
  

## 8.7.0
### New Feature
* Add merchant variable APIs
* terminal API support copy a exist terminal


## 8.6.0
### Improvement
* The fields such as effectiveTime and expiredTime are added respectively for terminal and group push related tasks.
* Search terminals API is updated, add resellerName and merchantName parameters.


## 8.5.1

### Bug Fix

* Make fields contact, country and phone are not mandatory in update merchant API.
  Please upgrade SDK to 8.5.1 if developer is using version 8.5.0.

## 8.5.0

### Improvement

* Updated get terminal details API to support return accessory list.
* Updated create merchant API, fields email, contact, country and phone are not mandatory anymore.

### Breaking

* The API TerminalAPI.getTerminal(Long terminalId, boolean includeDetailInfoList) is not compatible with or old API,
  the structure of the return result is not same as before. It will cause compile fail if developer do not change codes
  after upgrading SDK to 8.5.
  Please refer to the API document.

## 8.3.0

### New Feature

* Add group RKI related APIs

### Improvement

* Remove log4j dependency, developers need to choose the log implementations by self

## 8.2.0

### New Feature

* Terminal added new property 'Remark'

### Improvement

* The max length of Location property in terminal increased to 64

## 8.1.0

Please note all the changes is based on PAXStore version 8.1.0.

### New Feature

* PushHistoryApi supports search latest parameter push history
* PushHistoryApi supports search optimized parameter push history
* PushHistoryApi supports search latest optimized parameter push history

### Improvement

* Add new search app API to support search apps filter by reseller
* Add new search push firmware task API to support search by tasks filter by serial number
* TerminalApkApi supports push a parameter app inherite parameters from the lastest success push history
* EntityAttributeApi does not support regular expression anymore

### Breaking Changes

* GROUP is removed from enum VariableSource, if you are using it when search terminal variables you need to change codes

## 8.0

### New Feature

* Terminal API supports get details when get single terminal

### Improvement

* Provide the get method for the properties rateLimit, rateLimitRemain and rateLimitReset in the class Result

## 7.4.0

### New Feature

* Terminal API supports push command(lock, unlock, restart) to terminal

## 7.3.1

### Improvement

* Add name and fileSize properties to ApkDTO in search application result

## 7.3

### Improvement

* Change the SDK default connection timeout and read timeout value from 30 seconds to 5 seconds

### Breaking Changes

* Max records per page is reduced from 1000 to 100 (except GoInsightApi). If pass a value greate than 100 SDK validation
  will fail
* Developer inforamtion is removed from search result of AppApi

## 7.2.0

### New Features(To support these new features server side application must upgrade to version 7.2.0)

* Merchant supports new properties 'city' and 'province'
* Add new terminal related APIs in TerminalApi
    1. Move terminal to another reseller and
       merchant  ```moveTerminal(Long terminalId, String resellerName, String merchantName) ```
    2. Get terminal PED information  ```getTerminalPed(Long terminalId)```
    3. Get terminal configuration(allow replacement)  ```getTerminalConfig(Long terminalId)```
    4. Update terminal configuration(allow
       replacement)  ```updateTerminalConfig(Long terminalId, TerminalConfigUpdateRequest terminalConfigUpdateRequest)```

### Dependency Change

* Remove lombok dependency
* Update guava from 20.0 to 28.2
* Update hibernate-validator to 5.3.5.Final

## 7.1.1

### New Features(To support these new features Paxstore must upgrade to version 7.1.1)

* Add GoInsight related APIs
    1. Find app business data by query code  ```findDataFromInsight(String queryCode)```
    2. Find app business data by query code and date
       range  ```findDataFromInsight(String queryCode, TimestampRangeType rangeType)```
    3. Find app business data by query code, date range and
       page  ```findDataFromInsight(String queryCode, TimestampRangeType rangeType, Integer pageNo, Integer pageSize)```

## 7.1.0

### New Features(To support these new features Paxstore must upgrade to version 7.1)

* Add entity attribute APIs (EntityAttributeApi)
    1. Get entity attribute by id  ```getEntityAttribute(Long attributeId)```
    2. Search entity
       attribute  ```searchEntityAttributes(int pageNo, int pageSize, SearchOrderBy orderBy, String key , EntityAttributeType entityType)```
    3. Create entity attribute(this API is market level, reseller has not
       permission)  ```createEntityAttribute(EntityAttributeCreateRequest createRequest)```
    4. Update entity attribute(this API is market level, reseller has not
       permission)  ```updateEntityAttribute(Long attributeId, EntityAttributeUpdateRequest updateRequest)```
    5. Update entity attribute label(this API is market level, reseller has not
       permission)  ```updateEntityAttributeLabel(Long attributeId, EntityAttributeLabelUpdateRequest updateLabelRequest)```
    6. Delete entity attribute(this API is market level, reseller has not
       permission)  ```deleteEntityAttribute(Long attributeId)```  
       <br>
* Add terminal group related APIs (TerminalGroupApi)
    1. Search terminal
       group  ```searchTerminalGroup(int pageNo, int pageSize,TerminalGroupSearchOrderBy orderBy, TerminalGroupStatus status, String name,String resellerNames,String modelNames, Boolean isDynamic)```
    2. Get terminal group by id  ```getTerminalGroup(Long groupId)```
    3. Create terminal group  ```createTerminalGroup(CreateTerminalGroupRequest createRequest)```
    4. Search
       terminal  ```searchTerminal(int pageNo, int pageSize, TerminalApi.TerminalSearchOrderBy orderBy, TerminalStatus status, String modelName, String resellerName, String serialNo, String excludeGroupId)```
    5. Update terminal group  ```updateTerminalGroup(Long groupId ,UpdateTerminalGroupRequest updateRequest)```
    6. Activate terminal group```activeGroup(Long groupId)```
    7. Disable terminal group  ```disableGroup(Long groupId)```
    8. Delete terminal group  ```deleteGroup(Long groupId)```
    9. Search terminals in
       group  ```searchTerminalsInGroup(int pageNo, int pageSize, TerminalApi.TerminalSearchOrderBy orderBy, Long groupId, String serialNo, String merchantNames)```
    10. Add terminal(s) to group  ```addTerminalToGroup(Long groupId, Set<Long> terminalIds)```
    11. Remove terminal(s) out of group  ```removeTerminalOutGroup(Long groupId, Set<Long> terminalIds)```  
        <br>
* Add terminal group push related APIs (TerminalGroupApkApi)
    1. Get terminal group push task  ```getTerminalGroupApk(Long groupApkId)```
    2. Get terminal group push task and include specified parameters in
       result  ```getTerminalGroupApk(Long groupApkId, List<String> pidList)```
    3. Search terminal group push
       task  ```searchTerminalGroupApk(int pageNo, int pageSize, SearchOrderBy orderBy , Long groupId, Boolean pendingOnly, Boolean historyOnly, String keyWords)```
    4. Create terminal group push task  ```createAndActiveGroupApk(CreateTerminalGroupApkRequest createRequest)```
    5. Suspend terminal group push task  ```suspendTerminalGroupApk(Long groupApkId)```
    6. Delete terminal group push task  ```deleteTerminalGroupApk(Long groupApkId)```  
       <br>
* Add terminal RKI(remote key injection) APIs (TerminalRkiApi)
    1. Push RKI key to terminal  ```pushRkiKey2Terminal(PushRki2TerminalRequest pushRki2TerminalRequest)```
    2. Search RKI push
       task  ```searchPushRkiTasks(int pageNo, int pageSize, SearchOrderBy orderBy, String terminalTid, String rkiKey, PushStatus status)```
    3. Get RKI push task  ```getPushRkiTask(Long pushRkiTaskId)```
    4. Disable RKI push task  ```disablePushRkiTask(DisablePushRkiTask disablePushRkiTask)```  
       <br>
* Update terminal APIs (TerminalApi)
    1. Batch add terminal(s) to group(s)  ```batchAddTerminalToGroup(TerminalGroupRequest groupRequest)```
       <br>
* Update reseller APIs (ResellerApi)
    1. Search reseller RKI
       keys  ```searchResellerRkiKeyList(Long resellerId, int pageNo, int pageSize, String rkiKey)```

## 7.0

### New Features

* Add new search terminal API to include geo location, firmware and installed app in search result

* Add push template related APIs (TerminalApkParameterApi)

  Get push template(s)

  Create a push template

  Update a exist push template by id

  Delete a exist push template by id

* Add terminal variable related APIs (TerminalVariableApi)

  Get terminal variable by tid , serialNo , packageName, key, source

  Create a terminal variable

  Update terminal variable by id

  Delete terminal variable by terminal variable id

  Batch delete terminalvariable

* Add search push history result API (PushHistoryApi)

  SearchAppPushHistory

* Add API to verify terminal estate (TerminalEstateApi)

* Support file type parameter when push app and parameter to terminal

## 6.3.1

### New Features

* Add get single push firmware history API
* Add search push firmware history API
* Add Suspend push firmware API

### Breaking Changes

* The return object type change for pushFirmware2Terminal API

## 6.3

### New Features

* Add search app API
* Add push firmware to terminal API
* Add disable APK push API
* Add uninstall APP API

### Improvement

* Support set connection timeout and read timeout time in API
* Support retry when API encounter network issue
* Add API request rate limit

## 6.2.1

### Improvement

Add location field to response for terminal related APIs

## 6.2

### Improvement

* Support activate the reseller when create reseller, this improvement only works with PAXSTORE v6.2 and later.
* Support activate the merchant when create merchant, this improvement only works with PAXSTORE v6.2 and later.

### Bug Fixes

### Breaking Changes

NA

## 6.1

### Improvement

* Separate replace merchant email from update merchant API, add additional replace merchant email API.
* Separate replace reseller email from update merchant API, add additional replace reseller email API.
* Do not create user when create reseller but in activate reseller step. Won't affect code.
* *email* is not mandatory for updating merchant and reseller. If *email* is empty when call update merchant or reseller
  API the email won't be updated.
* *resellerName* is not mandatory for updating merchant. If *resellerName* is empty when call update merchant API the
  merchant's reseller won't be updated.
* *parentResellerName* is not mandatory for updating reseller and we suggest the developers pass null when updating
  reseller as the API does not support changing reseller's parent. To reserve this parameter is to make sure SDK is
  compatible with old version.

### Bug Fixes

### Breaking Changes

* Add a new property *createUserFlag* in the MerchantCreateRequest and MerchantUpdateRequest to indicate whether to
  create user, the default value is false. The old version API will create user in create merchant step. The impact is
  if user udpated SDK to this version and does not do any code change, the API won't create user when creating merchant
  and activating merchant. If user still need create user for the created merchant he need to set *createuserFlag* to
  true when creating or updating a merchant.

* The update reseller API cannot update the parent anymore.

## 6.0.2

### New Features

* Add merchant category releated APIs
* Add terminal apk API

### Bug Fixes

### Breaking Changes

NA

