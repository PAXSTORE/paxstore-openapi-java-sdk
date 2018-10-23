# Changelog

## 6.1.0 (Not released)

### Improvement

* Separate replace merchant email from update merchant API, add additional replace merchant email API
* Separate replace reseller email from update merchant API, add additional replace reseller email API
* Do not create user when create reseller but in activate reseller step. Won't affect code.

### Bug fixes

### Breaking changes

* Add a new property createUserFlag in the MerchantCreateRequest and MerchantUpdateRequest to indicate whether to create user, the default value is false. The old version API will create user when create merchant step, if use udpate SDK to this version and does not do any code change it won't create user when create merchant. If createUserFlag is true then when activate the merchant API will create user according to the email.
 
* Remove the property *parentResellerName* from the class *ResellerUpdateRequest*, since version 6.1.0 the update reseller API cannot change the reseller's parent.


## 6.0.2  

### New features  

* Add merchant category releated APIs
* Add terminal apk API

### Bug fixes  


### Breaking changes  
NA

