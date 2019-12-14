# Changelog  

## 7.0  
### New features
* Add new search terminal API to include geo location, firmware and installed app in search result
* Add push template related APIs
* Add terminal variable related APIs
* Add search push history result API
* Add API to verify terminal estate

## 6.3.1  

### New features  
* Add get single push firmware history API
* Add search push firmware history API
* Add Suspend push firmware API


### Breaking changes  
* The return object type change for pushFirmware2Terminal API


## 6.3  

### New features  

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

### Bug fixes  

### Breaking changes  
NA

## 6.1 

### Improvement

* Separate replace merchant email from update merchant API, add additional replace merchant email API.
* Separate replace reseller email from update merchant API, add additional replace reseller email API.
* Do not create user when create reseller but in activate reseller step. Won't affect code.
* *email* is not mandatory for updating merchant and reseller. If *email* is empty when call update merchant or reseller API the email won't be updated.
* *resellerName* is not mandatory for updating merchant. If *resellerName* is empty when call update merchant API the merchant's reseller won't be updated.
* *parentResellerName* is not mandatory for updating reseller and we suggest the developers pass null when updating reseller as the API does not support changing reseller's parent. To reserve this parameter is to make sure SDK is compatible with old version. 

### Bug fixes

### Breaking changes

* Add a new property *createUserFlag* in the MerchantCreateRequest and MerchantUpdateRequest to indicate whether to create user, the default value is false. The old version API will create user in create merchant step. The impact is if user udpated SDK to this version and does not do any code change, the API won't create user when creating merchant and activating merchant. If user still need create user for the created merchant he need to set *createuserFlag* to true when creating or updating a merchant.
 
* The update reseller API cannot update the parent anymore. 


## 6.0.2  

### New features  

* Add merchant category releated APIs
* Add terminal apk API

### Bug fixes  


### Breaking changes  
NA

