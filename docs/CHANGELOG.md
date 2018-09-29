# Changelog

## 6.1.0

### Improvement

* Separate replace merchant email from update merchant API, add additional replace merchant email API
* Separate replace reseller email from update merchant API, add additional replace reseller email API
* Do not create user when create reseller but in activate reseller step. Won't affect code.

### Bug fixed

### Breaking changes

* Add a new property createUserFlag in the MerchantCreateRequest and MerchantUpdateRequest to indicate whether to create user, the default value is false. The old version API will create user when create merchant step, if use udpate SDK to this version and does not do any code change it won't create user when create merchant.
 

## 6.0.2  

### New features  

* Add merchant category releated APIs
* Add terminal apk API

### Bug fixes  


### Breaking changes  
NA

