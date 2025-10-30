package com.pax.market.api.sdk.java.api.test;

import com.pax.market.api.sdk.java.api.base.dto.Result;
import com.pax.market.api.sdk.java.api.emm.emmDevice.EmmDeviceApi;
import com.pax.market.api.sdk.java.api.emm.emmDevice.dto.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Calendar;
import java.util.Collections;
import java.util.Date;

public class EmmDeviceApiTest {

    private static final Logger logger = LoggerFactory.getLogger(AppApiTest.class.getSimpleName());

    private EmmDeviceApi emmDeviceApi;

    @Before
    public void init() {
        emmDeviceApi = new EmmDeviceApi(TestConstants.API_BASE_URL, TestConstants.API_KEY, TestConstants.API_SECRET);
    }

    @Test
    public void testCreateRegisterQRCodeSuccess() {
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
        logger.debug("Result of create register QR code: {}", result.toString());
        Assert.assertEquals(0, result.getBusinessCode());
    }

    @Test
    public void testCreateRegisterQRCodeFailByEmmDeviceRegisterQRCodeCreateRequestIsInvalid() {
        Result<EmmDeviceRegisterQRCodeCreateDTO> result = emmDeviceApi.createRegisterQRCode(null);
        logger.debug("Result of create register QR code by emmDeviceRegisterQRCodeCreateRequest is null: {}", result.toString());
        Assert.assertEquals(-1, result.getBusinessCode());
    }

    @Test
    public void testCreateRegisterQRCodeFailByResellerNameIsInvalid() {
        EmmDeviceRegisterQRCodeCreateRequest resellerNameIsNull = new EmmDeviceRegisterQRCodeCreateRequest();
        resellerNameIsNull.setResellerName(null);
        resellerNameIsNull.setMerchantName("test");
        resellerNameIsNull.setType(EmmDeviceApi.EmmDeviceType.COMPANY_OWNER);
        Date currentDate = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        Date nextDate = calendar.getTime();
        resellerNameIsNull.setExpireDate(nextDate);
        Result<EmmDeviceRegisterQRCodeCreateDTO> resellerNameIsNullResult = emmDeviceApi.createRegisterQRCode(resellerNameIsNull);
        logger.debug("Result of create register QR code by reseller name is null: {}", resellerNameIsNullResult.toString());
        Assert.assertEquals(-1, resellerNameIsNullResult.getBusinessCode());

        EmmDeviceRegisterQRCodeCreateRequest resellerNameIsBlank = new EmmDeviceRegisterQRCodeCreateRequest();
        resellerNameIsBlank.setResellerName("");
        resellerNameIsBlank.setMerchantName("test");
        resellerNameIsBlank.setType(EmmDeviceApi.EmmDeviceType.COMPANY_OWNER);
        resellerNameIsBlank.setExpireDate(nextDate);
        Result<EmmDeviceRegisterQRCodeCreateDTO> resellerNameIsBlankResult = emmDeviceApi.createRegisterQRCode(resellerNameIsBlank);
        logger.debug("Result of create register QR code by reseller name is blank: {}", resellerNameIsBlankResult.toString());
        Assert.assertEquals(-1, resellerNameIsBlankResult.getBusinessCode());

        EmmDeviceRegisterQRCodeCreateRequest resellerNameTooLong = new EmmDeviceRegisterQRCodeCreateRequest();
        resellerNameTooLong.setResellerName("PAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAX");
        resellerNameTooLong.setMerchantName("test");
        resellerNameTooLong.setType(EmmDeviceApi.EmmDeviceType.COMPANY_OWNER);
        resellerNameTooLong.setExpireDate(nextDate);
        Result<EmmDeviceRegisterQRCodeCreateDTO> resellerNameIsTooLongResult = emmDeviceApi.createRegisterQRCode(resellerNameTooLong);
        logger.debug("Result of create register QR code by reseller name is too long: {}", resellerNameIsTooLongResult.toString());
        Assert.assertEquals(-1, resellerNameIsTooLongResult.getBusinessCode());

        EmmDeviceRegisterQRCodeCreateRequest resellerNameNotExist = new EmmDeviceRegisterQRCodeCreateRequest();
        resellerNameNotExist.setResellerName("resellerNameNotExist");
        resellerNameNotExist.setMerchantName("test");
        resellerNameNotExist.setType(EmmDeviceApi.EmmDeviceType.COMPANY_OWNER);
        resellerNameNotExist.setExpireDate(nextDate);
        Result<EmmDeviceRegisterQRCodeCreateDTO> resellerNameIsNotExistResult = emmDeviceApi.createRegisterQRCode(resellerNameNotExist);
        logger.debug("Result of create register QR code by reseller name is not exist: {}", resellerNameIsNotExistResult.toString());
        Assert.assertEquals(1759, resellerNameIsNotExistResult.getBusinessCode());

    }

    @Test
    public void testCreateRegisterQRCodeFailByMerchantNameIsInvalid() {
        EmmDeviceRegisterQRCodeCreateRequest merchantNameIsNull = new EmmDeviceRegisterQRCodeCreateRequest();
        merchantNameIsNull.setResellerName("PAX");
        merchantNameIsNull.setMerchantName(null);
        merchantNameIsNull.setType(EmmDeviceApi.EmmDeviceType.COMPANY_OWNER);
        Date currentDate = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        Date nextDate = calendar.getTime();
        merchantNameIsNull.setExpireDate(nextDate);
        Result<EmmDeviceRegisterQRCodeCreateDTO> merchantNameIsNullResult = emmDeviceApi.createRegisterQRCode(merchantNameIsNull);
        logger.debug("Result of create register QR code by merchant name is null: {}", merchantNameIsNullResult.toString());
        Assert.assertEquals(-1, merchantNameIsNullResult.getBusinessCode());

        EmmDeviceRegisterQRCodeCreateRequest merchantNameIsBlank = new EmmDeviceRegisterQRCodeCreateRequest();
        merchantNameIsBlank.setResellerName("PAX");
        merchantNameIsBlank.setMerchantName("");
        merchantNameIsBlank.setType(EmmDeviceApi.EmmDeviceType.COMPANY_OWNER);
        merchantNameIsBlank.setExpireDate(nextDate);
        Result<EmmDeviceRegisterQRCodeCreateDTO> merchantNameIsBlankResult = emmDeviceApi.createRegisterQRCode(merchantNameIsBlank);
        logger.debug("Result of create register QR code by merchant name is blank: {}", merchantNameIsBlankResult.toString());
        Assert.assertEquals(-1, merchantNameIsBlankResult.getBusinessCode());

        EmmDeviceRegisterQRCodeCreateRequest merchantNameTooLong = new EmmDeviceRegisterQRCodeCreateRequest();
        merchantNameTooLong.setResellerName("PAX");
        merchantNameTooLong.setMerchantName("PAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAX");
        merchantNameTooLong.setType(EmmDeviceApi.EmmDeviceType.COMPANY_OWNER);
        merchantNameTooLong.setExpireDate(nextDate);
        Result<EmmDeviceRegisterQRCodeCreateDTO> merchantNameIsTooLongResult = emmDeviceApi.createRegisterQRCode(merchantNameTooLong);
        logger.debug("Result of create register QR code by merchant name is too long: {}", merchantNameIsTooLongResult.toString());
        Assert.assertEquals(-1, merchantNameIsTooLongResult.getBusinessCode());

        EmmDeviceRegisterQRCodeCreateRequest merchantNameNotExist = new EmmDeviceRegisterQRCodeCreateRequest();
        merchantNameNotExist.setResellerName("PAX");
        merchantNameNotExist.setMerchantName("merchantNameNotExist");
        merchantNameNotExist.setType(EmmDeviceApi.EmmDeviceType.COMPANY_OWNER);
        merchantNameNotExist.setExpireDate(nextDate);
        Result<EmmDeviceRegisterQRCodeCreateDTO> merchantNameIsNotExistResult = emmDeviceApi.createRegisterQRCode(merchantNameNotExist);
        logger.debug("Result of create register QR code by merchant name is not exist: {}", merchantNameIsNotExistResult.toString());
        Assert.assertEquals(1720, merchantNameIsNotExistResult.getBusinessCode());

    }

    @Test
    public void testCreateRegisterQRCodeFailByTypeIsInvalid() {
        EmmDeviceRegisterQRCodeCreateRequest emmDeviceRegisterQRCodeCreateRequest = new EmmDeviceRegisterQRCodeCreateRequest();
        emmDeviceRegisterQRCodeCreateRequest.setResellerName("PAX");
        emmDeviceRegisterQRCodeCreateRequest.setMerchantName("test");
        emmDeviceRegisterQRCodeCreateRequest.setType(null);
        Date currentDate = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        Date nextDate = calendar.getTime();
        emmDeviceRegisterQRCodeCreateRequest.setExpireDate(nextDate);
        Result<EmmDeviceRegisterQRCodeCreateDTO> result = emmDeviceApi.createRegisterQRCode(emmDeviceRegisterQRCodeCreateRequest);
        logger.debug("Result of create register QR code by type is null: {}", result.toString());
        Assert.assertEquals(-1, result.getBusinessCode());

    }

    @Test
    public void testCreateRegisterQRCodeFailByExpireDateIsInvalid() {
        EmmDeviceRegisterQRCodeCreateRequest emmDeviceRegisterQRCodeCreateRequest = new EmmDeviceRegisterQRCodeCreateRequest();
        emmDeviceRegisterQRCodeCreateRequest.setResellerName("PAX");
        emmDeviceRegisterQRCodeCreateRequest.setMerchantName("test");
        emmDeviceRegisterQRCodeCreateRequest.setType(EmmDeviceApi.EmmDeviceType.COMPANY_OWNER);
        emmDeviceRegisterQRCodeCreateRequest.setExpireDate(null);
        Result<EmmDeviceRegisterQRCodeCreateDTO> result = emmDeviceApi.createRegisterQRCode(emmDeviceRegisterQRCodeCreateRequest);
        logger.debug("Result of create emm register QR code by expireDate is null: {}", result.toString());
        Assert.assertEquals(-1, result.getBusinessCode());

    }


    @Test
    public void testSearchEmmDeviceSuccess() {
        Result<EmmDeviceDTO> searchEmmDeviceSuccessResult1 = emmDeviceApi.searchEmmDevice(1, 5, null, null, null,null, null, null, null, null);
        logger.debug("Result1 of search emm device: {}", searchEmmDeviceSuccessResult1.toString());
        Assert.assertEquals(0, searchEmmDeviceSuccessResult1.getBusinessCode());

        Result<EmmDeviceDTO> searchEmmDeviceSuccessResult2 = emmDeviceApi.searchEmmDevice(1, 5, EmmDeviceApi.EmmDeviceSearchOrderBy.EmmDeviceSN_desc, null, null,null, null, null, null, null);
        logger.debug("Result2 of search emm device: {}", searchEmmDeviceSuccessResult2.toString());
        Assert.assertEquals(0, searchEmmDeviceSuccessResult2.getBusinessCode());

        Result<EmmDeviceDTO> searchEmmDeviceSuccessResult3 = emmDeviceApi.searchEmmDevice(1, 5, EmmDeviceApi.EmmDeviceSearchOrderBy.EmmDeviceSN_asc, null, null,null, null, null, null, null);
        logger.debug("Result3 of search emm device: {}", searchEmmDeviceSuccessResult3.toString());
        Assert.assertEquals(0, searchEmmDeviceSuccessResult3.getBusinessCode());

        Result<EmmDeviceDTO> searchEmmDeviceSuccessResult4 = emmDeviceApi.searchEmmDevice(1, 5, EmmDeviceApi.EmmDeviceSearchOrderBy.EmmDeviceRegisterTime_desc, null, null,null, null, null, null, null);
        logger.debug("Result4 of search emm device: {}", searchEmmDeviceSuccessResult4.toString());
        Assert.assertEquals(0, searchEmmDeviceSuccessResult4.getBusinessCode());

        Result<EmmDeviceDTO> searchEmmDeviceSuccessResult5 = emmDeviceApi.searchEmmDevice(1, 5, EmmDeviceApi.EmmDeviceSearchOrderBy.EmmDeviceRegisterTime_asc, null, null,null, null, null, null, null);
        logger.debug("Result5 of search emm device: {}", searchEmmDeviceSuccessResult5.toString());
        Assert.assertEquals(0, searchEmmDeviceSuccessResult5.getBusinessCode());

        Result<EmmDeviceDTO> searchEmmDeviceSuccessResult6 = emmDeviceApi.searchEmmDevice(1, 5, null, "M9200-0908", null,null, null, null, null, null);
        logger.debug("Result6 of search emm device: {}", searchEmmDeviceSuccessResult6.toString());
        Assert.assertEquals(0, searchEmmDeviceSuccessResult6.getBusinessCode());

        Result<EmmDeviceDTO> searchEmmDeviceSuccessResult7 = emmDeviceApi.searchEmmDevice(1, 5, null, "M9200-0908", "2870000908", null, null, null, null, null);
        logger.debug("Result7 of search emm device: {}", searchEmmDeviceSuccessResult7.toString());
        Assert.assertEquals(0, searchEmmDeviceSuccessResult7.getBusinessCode());

        Result<EmmDeviceDTO> searchEmmDeviceSuccessResult8 = emmDeviceApi.searchEmmDevice(1, 5, null, "M9200-0908", "2870000908","ZOLON", "M9200", null, null, null);
        logger.debug("Result8 of search emm device: {}", searchEmmDeviceSuccessResult8.toString());
        Assert.assertEquals(0, searchEmmDeviceSuccessResult8.getBusinessCode());

        Result<EmmDeviceDTO> searchEmmDeviceSuccessResult9 = emmDeviceApi.searchEmmDevice(1, 5, null, "M9200-0908", "2870000908","ZOLON", "M9200", "suyunlong", null, null);
        logger.debug("Result9 of search emm device: {}", searchEmmDeviceSuccessResult9.toString());
        Assert.assertEquals(0, searchEmmDeviceSuccessResult9.getBusinessCode());

        Result<EmmDeviceDTO> searchEmmDeviceSuccessResult10 = emmDeviceApi.searchEmmDevice(1, 5, null, "M9200-0908", "2870000908","ZOLON", "M9200", "suyunlong", "sh1", null);
        logger.debug("Result10 of search emm device: {}", searchEmmDeviceSuccessResult10.toString());
        Assert.assertEquals(0, searchEmmDeviceSuccessResult10.getBusinessCode());

        Result<EmmDeviceDTO> searchEmmDeviceSuccessResult11 = emmDeviceApi.searchEmmDevice(1, 5, null, "M9200-0908", "2870000908","ZOLON", "M9200", "suyunlong", "sh1", EmmDeviceApi.EmmDeviceStatus.ACTIVE);
        logger.debug("Result11 of search emm device: {}", searchEmmDeviceSuccessResult11.toString());
        Assert.assertEquals(0, searchEmmDeviceSuccessResult11.getBusinessCode());

        Result<EmmDeviceDTO> searchEmmDeviceSuccessResult12 = emmDeviceApi.searchEmmDevice(1, 5, null, "M9200-0908", "2870000908","ZOLON", "M9200", "suyunlong", "sh1", EmmDeviceApi.EmmDeviceStatus.LOST);
        logger.debug("Result12 of search emm device: {}", searchEmmDeviceSuccessResult12.toString());
        Assert.assertEquals(0, searchEmmDeviceSuccessResult12.getBusinessCode());

    }

    @Test
    public void testSearchEmmDeviceFailByMfrNameAndModelNameIsInvalid() {
        Result<EmmDeviceDTO> modelNameIsNullResult = emmDeviceApi.searchEmmDevice(1, 5, null, "M9200-0908", "2870000908","ZOLON", null, "suyunlong", "sh1", EmmDeviceApi.EmmDeviceStatus.LOST);
        logger.debug("Result of search emm device by model name is null: {}", modelNameIsNullResult.toString());
        Assert.assertEquals(61662, modelNameIsNullResult.getBusinessCode());

        Result<EmmDeviceDTO> mfrNameIsNullResult = emmDeviceApi.searchEmmDevice(1, 5, null, "M9200-0908", "2870000908",null, "M9200", "suyunlong", "sh1", EmmDeviceApi.EmmDeviceStatus.ACTIVE);
        logger.debug("Result of search emm device by manufacturer name is null: {}", mfrNameIsNullResult.toString());
        Assert.assertEquals(61662, mfrNameIsNullResult.getBusinessCode());

        Result<EmmDeviceDTO> modelNameIsBlankResult = emmDeviceApi.searchEmmDevice(1, 5, null, "M9200-0908", "2870000908","ZOLON", "", "suyunlong", "sh1", EmmDeviceApi.EmmDeviceStatus.UN_CERTIFICATED);
        logger.debug("Result of search emm device by model name is blank: {}", modelNameIsBlankResult.toString());
        Assert.assertEquals(61662, modelNameIsBlankResult.getBusinessCode());

        Result<EmmDeviceDTO> mfrNameIsBlankResult = emmDeviceApi.searchEmmDevice(1, 5, null, "M9200-0908", "2870000908","", "M9200", "suyunlong", "sh1", EmmDeviceApi.EmmDeviceStatus.ACTIVE);
        logger.debug("Result of search emm device by manufacturer name is blank: {}", mfrNameIsBlankResult.toString());
        Assert.assertEquals(61662, mfrNameIsBlankResult.getBusinessCode());
    }

    @Test
    public void testSearchEmmDeviceFailByResellerNameIsInvalid() {
        Result<EmmDeviceDTO> resellerNameIsNullResult = emmDeviceApi.searchEmmDevice(1, 5, null, "M9200-0908", "2870000908", "ZOLON", null, null, "sh1", EmmDeviceApi.EmmDeviceStatus.LOST);
        logger.debug("Result of search emm device by reseller name is null: {}", resellerNameIsNullResult.toString());
        Assert.assertEquals(1762, resellerNameIsNullResult.getBusinessCode());

        Result<EmmDeviceDTO> resellerNameIsBlankResult = emmDeviceApi.searchEmmDevice(1, 5, null, "M9200-0908", "2870000908", null, "M9200", "", "sh1", EmmDeviceApi.EmmDeviceStatus.ACTIVE);
        logger.debug("Result of search emm device by reseller name is blank: {}", resellerNameIsBlankResult.toString());
        Assert.assertEquals(1762, resellerNameIsBlankResult.getBusinessCode());
    }

    @Test
    public void testGetEmmDeviceSuccess() {
        Result<EmmDeviceDetailDTO> result = emmDeviceApi.getEmmDevice(1687529174597667L);
        logger.debug("Result of get emm device: {}", result.toString());
        Assert.assertEquals(0, result.getBusinessCode());
    }

    @Test
    public void testGetEmmDeviceFailByDeviceIdIsInvalid() {
        Result<EmmDeviceDetailDTO> deviceIdIsNullResult = emmDeviceApi.getEmmDevice(null);
        logger.debug("Result of get emm device by deviceId is null: {}", deviceIdIsNullResult.toString());
        Assert.assertEquals(-1, deviceIdIsNullResult.getBusinessCode());

        Result<EmmDeviceDetailDTO> deviceIdIsNegativeResult = emmDeviceApi.getEmmDevice(-1L);
        logger.debug("Result of get emm device by deviceId is negative: {}", deviceIdIsNegativeResult.toString());
        Assert.assertEquals(-1, deviceIdIsNegativeResult.getBusinessCode());
    }

    @Test
    public void testUpdateEmmDeviceSuccess() {
        EmmDeviceUpdateRequest emmDeviceUpdateRequest = new EmmDeviceUpdateRequest();
        emmDeviceUpdateRequest.setDeviceName("test");
        emmDeviceUpdateRequest.setResellerName("PAX");
        emmDeviceUpdateRequest.setMerchantName("test");
        Result<String> result = emmDeviceApi.updateEmmDevice(1647480353587234L, emmDeviceUpdateRequest);
        logger.debug("Result of update emm device: {}", result.toString());
        Assert.assertEquals(0, result.getBusinessCode());
    }

    @Test
    public void testUpdateEmmDeviceFailByEmmDeviceUpdateRequestIsInvalid() {
        Result<String> result = emmDeviceApi.updateEmmDevice(1647480353587234L, null);
        logger.debug("Result of update emm device by emmDeviceUpdateRequest is null: {}", result.toString());
        Assert.assertEquals(-1, result.getBusinessCode());
    }

    @Test
    public void testUpdateEmmDeviceFailByDeviceIdIsInvalid() {
        EmmDeviceUpdateRequest deviceIdIsNull = new EmmDeviceUpdateRequest();
        deviceIdIsNull.setDeviceName("testDevice");
        deviceIdIsNull.setResellerName("suyunlong");
        deviceIdIsNull.setMerchantName("sh1");
        Result<String> deviceIdIsNullResult = emmDeviceApi.updateEmmDevice(null, deviceIdIsNull);
        logger.debug("Result of update emm device by deviceId is null: {}", deviceIdIsNullResult.toString());
        Assert.assertEquals(-1, deviceIdIsNullResult.getBusinessCode());

        EmmDeviceUpdateRequest deviceIdIsNegative = new EmmDeviceUpdateRequest();
        deviceIdIsNegative.setDeviceName("testDevice");
        deviceIdIsNegative.setResellerName("suyunlong");
        deviceIdIsNegative.setMerchantName("sh1");
        Result<String> deviceIdIsNegativeResult = emmDeviceApi.updateEmmDevice(-1L, deviceIdIsNegative);
        logger.debug("Result of update emm device by deviceId is negative: {}", deviceIdIsNegativeResult.toString());
        Assert.assertEquals(-1, deviceIdIsNegativeResult.getBusinessCode());
    }

    @Test
    public void testUpdateEmmDeviceFailByDeviceNameIsInvalid() {

        EmmDeviceUpdateRequest deviceNameIsNull = new EmmDeviceUpdateRequest();
        deviceNameIsNull.setDeviceName(null);
        deviceNameIsNull.setResellerName("suyunlong");
        deviceNameIsNull.setMerchantName("sh1");
        Result<String> deviceNameIsNullResult = emmDeviceApi.updateEmmDevice(1647428843339813L, deviceNameIsNull);
        logger.debug("Result of update emm device by device name is null: {}", deviceNameIsNullResult.toString());
        Assert.assertEquals(-1, deviceNameIsNullResult.getBusinessCode());

        EmmDeviceUpdateRequest deviceNameIsBlank = new EmmDeviceUpdateRequest();
        deviceNameIsBlank.setDeviceName("");
        deviceNameIsBlank.setResellerName("suyunlong");
        deviceNameIsBlank.setMerchantName("sh1");
        Result<String> deviceNameIsBlankResult = emmDeviceApi.updateEmmDevice(1647428843339813L, deviceNameIsBlank);
        logger.debug("Result of update emm device by device name is blank: {}", deviceNameIsBlankResult.toString());
        Assert.assertEquals(-1, deviceNameIsBlankResult.getBusinessCode());

        EmmDeviceUpdateRequest deviceNameIsTooLong = new EmmDeviceUpdateRequest();
        deviceNameIsTooLong.setDeviceName("deviceNamedeviceNamedeviceNamedeviceNamedeviceNamedeviceNamedeviceNamedeviceNamedeviceNamedeviceNamedeviceNamedeviceNamedeviceNamedeviceName");
        deviceNameIsTooLong.setResellerName("suyunlong");
        deviceNameIsTooLong.setMerchantName("sh1");
        Result<String> deviceNameIsTooLongResult = emmDeviceApi.updateEmmDevice(1647428843339813L, deviceNameIsTooLong);
        logger.debug("Result of update emm device by device name is too long: {}", deviceNameIsTooLongResult.toString());
        Assert.assertEquals(-1, deviceNameIsTooLongResult.getBusinessCode());

    }

    @Test
    public void testUpdateEmmDeviceFailByResellerNameIsInvalid() {

        EmmDeviceUpdateRequest resellerNameIsNull = new EmmDeviceUpdateRequest();
        resellerNameIsNull.setDeviceName("testDevice");
        resellerNameIsNull.setResellerName(null);
        resellerNameIsNull.setMerchantName("sh1");
        Result<String> resellerNameIsNullResult = emmDeviceApi.updateEmmDevice(1647428843339813L, resellerNameIsNull);
        logger.debug("Result of update emm device by reseller name is null: {}", resellerNameIsNullResult.toString());
        Assert.assertEquals(-1, resellerNameIsNullResult.getBusinessCode());

        EmmDeviceUpdateRequest resellerNameIsBlank = new EmmDeviceUpdateRequest();
        resellerNameIsBlank.setDeviceName("testDevice");
        resellerNameIsBlank.setResellerName("");
        resellerNameIsBlank.setMerchantName("sh1");
        Result<String> resellerNameIsBlankResult = emmDeviceApi.updateEmmDevice(1647428843339813L, resellerNameIsBlank);
        logger.debug("Result of update emm device by reseller name is blank: {}", resellerNameIsBlankResult.toString());
        Assert.assertEquals(-1, resellerNameIsBlankResult.getBusinessCode());

        EmmDeviceUpdateRequest resellerNameTooLong = new EmmDeviceUpdateRequest();
        resellerNameTooLong.setDeviceName("testDevice");
        resellerNameTooLong.setResellerName("PAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAX");
        resellerNameTooLong.setMerchantName("sh1");
        Result<String> resellerNameIsTooLongResult = emmDeviceApi.updateEmmDevice(1647428843339813L, resellerNameTooLong);
        logger.debug("Result of update emm device by reseller name is too long: {}", resellerNameIsTooLongResult.toString());
        Assert.assertEquals(-1, resellerNameIsTooLongResult.getBusinessCode());

        EmmDeviceUpdateRequest resellerNameNotExist = new EmmDeviceUpdateRequest();
        resellerNameNotExist.setDeviceName("testDevice");
        resellerNameNotExist.setResellerName("resellerNameNotExist");
        resellerNameNotExist.setMerchantName("sh1");
        Result<String> resellerNameIsNotExistResult = emmDeviceApi.updateEmmDevice(1647428843339813L, resellerNameNotExist);
        logger.debug("Result of update emm device by reseller name is not exist: {}", resellerNameIsNotExistResult.toString());
        Assert.assertEquals(1759, resellerNameIsNotExistResult.getBusinessCode());

    }

    @Test
    public void testUpdateEmmDeviceFailByMerchantNameIsInvalid() {

        EmmDeviceUpdateRequest merchantNameIsNull = new EmmDeviceUpdateRequest();
        merchantNameIsNull.setDeviceName("testDevice");
        merchantNameIsNull.setResellerName("PAX");
        merchantNameIsNull.setMerchantName(null);
        Result<String> merchantNameIsNullResult = emmDeviceApi.updateEmmDevice(1647428843339813L, merchantNameIsNull);
        logger.debug("Result of update emm device by merchant name is null: {}", merchantNameIsNullResult.toString());
        Assert.assertEquals(-1, merchantNameIsNullResult.getBusinessCode());

        EmmDeviceUpdateRequest merchantNameIsBlank = new EmmDeviceUpdateRequest();
        merchantNameIsBlank.setDeviceName("testDevice");
        merchantNameIsBlank.setResellerName("PAX");
        merchantNameIsBlank.setMerchantName("");
        Result<String> merchantNameIsBlankResult = emmDeviceApi.updateEmmDevice(1647428843339813L, merchantNameIsBlank);
        logger.debug("Result of update emm device by merchant name is blank: {}", merchantNameIsBlankResult.toString());
        Assert.assertEquals(-1, merchantNameIsBlankResult.getBusinessCode());

        EmmDeviceUpdateRequest merchantNameTooLong = new EmmDeviceUpdateRequest();
        merchantNameTooLong.setDeviceName("testDevice");
        merchantNameTooLong.setResellerName("PAX");
        merchantNameTooLong.setMerchantName("PAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAX");
        Result<String> merchantNameIsTooLongResult = emmDeviceApi.updateEmmDevice(1647428843339813L, merchantNameTooLong);
        logger.debug("Result of update emm device by merchant name is too long: {}", merchantNameIsTooLongResult.toString());
        Assert.assertEquals(-1, merchantNameIsTooLongResult.getBusinessCode());

        EmmDeviceUpdateRequest merchantNameNotExist = new EmmDeviceUpdateRequest();
        merchantNameNotExist.setDeviceName("testDevice");
        merchantNameNotExist.setResellerName("PAX");
        merchantNameNotExist.setMerchantName("merchantNameNotExist");
        Result<String> merchantNameIsNotExistResult = emmDeviceApi.updateEmmDevice(1647428843339813L, merchantNameNotExist);
        logger.debug("Result of update emm device by merchant name is not exist: {}", merchantNameIsNotExistResult.toString());
        Assert.assertEquals(1720, merchantNameIsNotExistResult.getBusinessCode());
    }

    @Test
    public void testBatchMoveEmmDeviceSuccess() {
        EmmDeviceBatchMoveRequest emmDeviceBatchMoveRequest = new EmmDeviceBatchMoveRequest();
        emmDeviceBatchMoveRequest.setDeviceIds(Collections.singletonList(1647480353587234L));
        emmDeviceBatchMoveRequest.setResellerName("PAX");
        emmDeviceBatchMoveRequest.setMerchantName("test02");
        Result<String> result = emmDeviceApi.batchMoveEmmDevice(emmDeviceBatchMoveRequest);
        logger.debug("Result of batch move emm device: {}", result.toString());
        Assert.assertEquals(0, result.getBusinessCode());
    }

    @Test
    public void testBatchMoveEmmDeviceFailByEmmDeviceBatchMoveRequestIsInvalid() {
        Result<String> result = emmDeviceApi.batchMoveEmmDevice(null);
        logger.debug("Result of batch move emm device by emmDeviceBatchMoveRequest is null: {}", result.toString());
        Assert.assertEquals(-1, result.getBusinessCode());
    }

    @Test
    public void testBatchMoveEmmDeviceFailByDeviceIdIsInvalid() {
        EmmDeviceBatchMoveRequest emmDeviceBatchMoveRequest = new EmmDeviceBatchMoveRequest();
        emmDeviceBatchMoveRequest.setDeviceIds(null);
        emmDeviceBatchMoveRequest.setResellerName("suyunlong");
        emmDeviceBatchMoveRequest.setMerchantName("商户1");
        Result<String> result = emmDeviceApi.batchMoveEmmDevice(emmDeviceBatchMoveRequest);
        logger.debug("Result of batch move emm device by deviceIds is null: {}", result.toString());
        Assert.assertEquals(-1, result.getBusinessCode());
    }

    @Test
    public void testBatchMoveEmmDeviceFailByResellerNameIsInvalid() {

        EmmDeviceBatchMoveRequest resellerNameIsNull = new EmmDeviceBatchMoveRequest();
        resellerNameIsNull.setDeviceIds(Collections.singletonList(1647428843339813L));
        resellerNameIsNull.setResellerName(null);
        resellerNameIsNull.setMerchantName("商户1");
        Result<String> resellerNameIsNullResult = emmDeviceApi.batchMoveEmmDevice(resellerNameIsNull);
        logger.debug("Result of batch move emm device by reseller name is null: {}", resellerNameIsNullResult.toString());
        Assert.assertEquals(-1, resellerNameIsNullResult.getBusinessCode());


        EmmDeviceBatchMoveRequest resellerNameIsBlank = new EmmDeviceBatchMoveRequest();
        resellerNameIsBlank.setDeviceIds(Collections.singletonList(1647428843339813L));
        resellerNameIsBlank.setResellerName("");
        resellerNameIsBlank.setMerchantName("商户1");
        Result<String> resellerNameIsBlankResult = emmDeviceApi.batchMoveEmmDevice(resellerNameIsBlank);
        logger.debug("Result of batch move emm device by reseller name is blank: {}", resellerNameIsBlankResult.toString());
        Assert.assertEquals(-1, resellerNameIsBlankResult.getBusinessCode());

        EmmDeviceBatchMoveRequest resellerNameTooLong = new EmmDeviceBatchMoveRequest();
        resellerNameTooLong.setDeviceIds(Collections.singletonList(1647428843339813L));
        resellerNameTooLong.setResellerName("PAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAX");
        resellerNameTooLong.setMerchantName("商户1");
        Result<String> resellerNameIsTooLongResult = emmDeviceApi.batchMoveEmmDevice(resellerNameTooLong);
        logger.debug("Result of batch move emm device by reseller name is too long: {}", resellerNameIsTooLongResult.toString());
        Assert.assertEquals(-1, resellerNameIsTooLongResult.getBusinessCode());


        EmmDeviceBatchMoveRequest resellerNameNotExist = new EmmDeviceBatchMoveRequest();
        resellerNameNotExist.setDeviceIds(Collections.singletonList(1647428843339813L));
        resellerNameNotExist.setResellerName("resellerNameNotExist");
        resellerNameNotExist.setMerchantName("商户1");
        Result<String> resellerNameIsNotExistResult = emmDeviceApi.batchMoveEmmDevice(resellerNameNotExist);
        logger.debug("Result of batch move emm device by reseller name is not exist: {}", resellerNameIsNotExistResult.toString());
        Assert.assertEquals(1759, resellerNameIsNotExistResult.getBusinessCode());

    }

    @Test
    public void testBatchMoveEmmDeviceFailByMerchantNameIsInvalid() {

        EmmDeviceBatchMoveRequest merchantNameIsNull = new EmmDeviceBatchMoveRequest();
        merchantNameIsNull.setDeviceIds(Collections.singletonList(1647428843339813L));
        merchantNameIsNull.setResellerName("suyunlong");
        merchantNameIsNull.setMerchantName(null);
        Result<String> merchantNameIsNullResult = emmDeviceApi.batchMoveEmmDevice(merchantNameIsNull);
        logger.debug("Result of batch move emm device by merchant name is null: {}", merchantNameIsNullResult.toString());
        Assert.assertEquals(-1, merchantNameIsNullResult.getBusinessCode());

        EmmDeviceBatchMoveRequest merchantNameIsBlank = new EmmDeviceBatchMoveRequest();
        merchantNameIsBlank.setDeviceIds(Collections.singletonList(1647428843339813L));
        merchantNameIsBlank.setResellerName("suyunlong");
        merchantNameIsBlank.setMerchantName("");
        Result<String> merchantNameIsBlankResult = emmDeviceApi.batchMoveEmmDevice(merchantNameIsBlank);
        logger.debug("Result of batch move emm device by merchant name is blank: {}", merchantNameIsBlankResult.toString());
        Assert.assertEquals(-1, merchantNameIsBlankResult.getBusinessCode());

        EmmDeviceBatchMoveRequest merchantNameTooLong = new EmmDeviceBatchMoveRequest();
        merchantNameTooLong.setDeviceIds(Collections.singletonList(1647428843339813L));
        merchantNameTooLong.setResellerName("suyunlong");
        merchantNameTooLong.setMerchantName("PAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAX");
        Result<String> merchantNameIsTooLongResult = emmDeviceApi.batchMoveEmmDevice(merchantNameTooLong);
        logger.debug("Result of batch move emm device by merchant name is too long: {}", merchantNameIsTooLongResult.toString());
        Assert.assertEquals(-1, merchantNameIsTooLongResult.getBusinessCode());

        EmmDeviceBatchMoveRequest merchantNameNotExist = new EmmDeviceBatchMoveRequest();
        merchantNameNotExist.setDeviceIds(Collections.singletonList(1647428843339813L));
        merchantNameNotExist.setResellerName("suyunlong");
        merchantNameNotExist.setMerchantName("merchantNameNotExist");
        Result<String> merchantNameIsNotExistResult = emmDeviceApi.batchMoveEmmDevice(merchantNameNotExist);
        logger.debug("Result of batch move emm device by merchant name is not exist: {}", merchantNameIsNotExistResult.toString());
        Assert.assertEquals(1720, merchantNameIsNotExistResult.getBusinessCode());

    }

    @Test
    public void testDeleteEmmDeviceSuccess() {
        Result<String> result = emmDeviceApi.deleteEmmDevice(1648724732280883L);
        logger.debug("Result of delete emm device: {}", result.toString());
        Assert.assertEquals(0, result.getBusinessCode());
    }

    @Test
    public void testDeleteEmmDeviceFailByDeviceIdIsInvalid() {
        Result<String> deviceIdIsNullResult = emmDeviceApi.deleteEmmDevice(null);
        logger.debug("Result of delete emm device by deviceId is null: {}", deviceIdIsNullResult.toString());
        Assert.assertEquals(-1, deviceIdIsNullResult.getBusinessCode());

        Result<String> deviceIdIsNegativeResult = emmDeviceApi.deleteEmmDevice(-1L);
        logger.debug("Result of delete emm device by deviceId is negative: {}", deviceIdIsNegativeResult.toString());
        Assert.assertEquals(-1, deviceIdIsNegativeResult.getBusinessCode());

    }

    @Test
    public void testBatchDeleteEmmDeviceSuccess() {
        EmmDeviceBatchDeleteRequest emmDeviceBatchDeleteRequest = new EmmDeviceBatchDeleteRequest();
        emmDeviceBatchDeleteRequest.setDeviceIds(Collections.singletonList(1649639642103852L));
        Result<String> result = emmDeviceApi.batchDeleteEmmDevice(emmDeviceBatchDeleteRequest);
        logger.debug("Result of batch delete emm device: {}", result.toString());
        Assert.assertEquals(0, result.getBusinessCode());
    }

    @Test
    public void testBatchDeleteEmmDeviceFailByEmmDeviceBatchDeleteRequestIsInvalid() {
        Result<String> result = emmDeviceApi.batchDeleteEmmDevice(null);
        logger.debug("Result of batch delete emm device by emmDeviceBatchDeleteRequest is null: {}", result.toString());
        Assert.assertEquals(-1, result.getBusinessCode());
    }

    @Test
    public void testBatchDeleteEmmDeviceFailByDeviceIdIsInvalid() {
        EmmDeviceBatchDeleteRequest emmDeviceBatchDeleteRequest = new EmmDeviceBatchDeleteRequest();
        emmDeviceBatchDeleteRequest.setDeviceIds(null);
        Result<String> result = emmDeviceApi.batchDeleteEmmDevice(emmDeviceBatchDeleteRequest);
        logger.debug("Result of batch delete emm device by deviceIds is null: {}", result.toString());
        Assert.assertEquals(-1, result.getBusinessCode());
    }

    @Test
    public void testRebootEmmDeviceSuccess() {
        Result<String> result = emmDeviceApi.rebootEmmDevice(1647428843339813L);
        logger.debug("Result of reboot emm device: {}", result.toString());
        Assert.assertEquals(0, result.getBusinessCode());
    }

    @Test
    public void testRebootEmmDeviceFailByDeviceIdIsInvalid() {
        Result<String> deviceIdIsNullResult = emmDeviceApi.rebootEmmDevice(null);
        logger.debug("Result of reboot emm device by deviceId is null: {}", deviceIdIsNullResult.toString());
        Assert.assertEquals(-1, deviceIdIsNullResult.getBusinessCode());


        Result<String> deviceIdIsNegativeResult = emmDeviceApi.rebootEmmDevice(-1L);
        logger.debug("Result of reboot emm device by deviceId is negative: {}", deviceIdIsNegativeResult.toString());
        Assert.assertEquals(-1, deviceIdIsNegativeResult.getBusinessCode());
    }


    @Test
    public void testLockEmmDeviceScreenSuccess() {
        Result<String> result = emmDeviceApi.lockEmmDeviceScreen(1647428843339813L);
        logger.debug("Result of lock emm device: {}", result.toString());
        Assert.assertEquals(0, result.getBusinessCode());
    }

    @Test
    public void testLockEmmDeviceScreenFailByDeviceIdIsInvalid() {

        Result<String> deviceIdIsNullResult = emmDeviceApi.lockEmmDeviceScreen(null);
        logger.debug("Result of lock emm device by deviceId is null: {}", deviceIdIsNullResult.toString());
        Assert.assertEquals(-1, deviceIdIsNullResult.getBusinessCode());

        Result<String> deviceIdIsNegativeResult = emmDeviceApi.lockEmmDeviceScreen(-1L);
        logger.debug("Result of lock emm device by deviceId is negative: {}", deviceIdIsNegativeResult.toString());
        Assert.assertEquals(-1, deviceIdIsNegativeResult.getBusinessCode());

    }

    @Test
    public void testResetEmmDevicePasswordSuccess() {
        EmmDeviceResetPasswordRequest emmDeviceResetPasswordRequest = new EmmDeviceResetPasswordRequest();
        emmDeviceResetPasswordRequest.setPassword("1234");
        Result<String> result = emmDeviceApi.resetEmmDevicePassword(1650928224567330L, emmDeviceResetPasswordRequest);
        logger.debug("Result of reset emm device password: {}", result.toString());
        Assert.assertEquals(0, result.getBusinessCode());
    }


    @Test
    public void testResetEmmDevicePasswordFailByEmmDeviceResetPasswordRequestIsInvalid() {
        Result<String> result = emmDeviceApi.resetEmmDevicePassword(1653071941730344L, null);
        logger.debug("Result of reset emm device password by emmDeviceResetPasswordRequest is null: {}", result.toString());
        Assert.assertEquals(-1, result.getBusinessCode());
    }

    @Test
    public void testResetEmmDevicePasswordFailByDeviceIdIsInvalid() {
        EmmDeviceResetPasswordRequest deviceIdIsNull = new EmmDeviceResetPasswordRequest();
        deviceIdIsNull.setPassword("1234");
        Result<String> deviceIdIsNullResult = emmDeviceApi.resetEmmDevicePassword(null, deviceIdIsNull);
        logger.debug("Result of reset emm device password by deviceId is null: {}", deviceIdIsNullResult.toString());
        Assert.assertEquals(-1, deviceIdIsNullResult.getBusinessCode());

        EmmDeviceResetPasswordRequest deviceIdIsNegative = new EmmDeviceResetPasswordRequest();
        deviceIdIsNegative.setPassword("1234");
        Result<String> deviceIdIsNegativeResult = emmDeviceApi.resetEmmDevicePassword(-1L, deviceIdIsNegative);
        logger.debug("Result of reset emm device password by deviceId is negative: {}", deviceIdIsNegativeResult.toString());
        Assert.assertEquals(-1, deviceIdIsNegativeResult.getBusinessCode());

    }

    @Test
    public void testResetEmmDevicePasswordFailByPasswordIsInvalid() {
        EmmDeviceResetPasswordRequest passwordIsNull = new EmmDeviceResetPasswordRequest();
        passwordIsNull.setPassword(null);
        Result<String> passwordIsNullResult = emmDeviceApi.resetEmmDevicePassword(1653071941730344L, passwordIsNull);
        logger.debug("Result of reset emm device password by password is null: {}", passwordIsNullResult.toString());
        Assert.assertEquals(-1, passwordIsNullResult.getBusinessCode());

        EmmDeviceResetPasswordRequest passwordIsBlank = new EmmDeviceResetPasswordRequest();
        passwordIsBlank.setPassword("");
        Result<String> passwordIsBlankResult = emmDeviceApi.resetEmmDevicePassword(1653071941730344L, passwordIsBlank);
        logger.debug("Result of reset emm device password by password is blank: {}", passwordIsBlankResult.toString());
        Assert.assertEquals(-1, passwordIsBlankResult.getBusinessCode());
    }


    @Test
    public void testResetEmmDevicePasswordFailByLockNowIsInvalid() {

        EmmDeviceResetPasswordRequest emmDeviceResetPasswordRequest = new EmmDeviceResetPasswordRequest();
        emmDeviceResetPasswordRequest.setPassword("1234");
        emmDeviceResetPasswordRequest.setLockNow(null);
        Result<String> result = emmDeviceApi.resetEmmDevicePassword(1652549948014630L, emmDeviceResetPasswordRequest);
        logger.debug("Result of reset emm device password by lockNow is null: {}", result.toString());
        Assert.assertEquals(-1, result.getBusinessCode());

    }

    @Test
    public void testStartEmmDeviceLostModeSuccess() {
        EmmDeviceLostModeRequest emmDeviceLostModeRequest = new EmmDeviceLostModeRequest();
        emmDeviceLostModeRequest.setLostMessage("test message");
        emmDeviceLostModeRequest.setLostPhoneNumber("12345678910");
        Result<String> result = emmDeviceApi.startEmmDeviceLostMode(1647428843339813L, emmDeviceLostModeRequest);
        logger.debug("Result of start emm device lost mode: {}", result.toString());
        Assert.assertEquals(0, result.getBusinessCode());
    }

    @Test
    public void testStartEmmDeviceLostModeFailByEmmDeviceLostModeRequestIsInvalid() {
        Result<String> result = emmDeviceApi.startEmmDeviceLostMode(1652541362274340L, null);
        logger.debug("Result of start emm device lost mode by emmDeviceLostModeRequest is null: {}", result.toString());
        Assert.assertEquals(-1, result.getBusinessCode());
    }


    @Test
    public void testStartEmmDeviceLostModeFailByDeviceIdIsInvalid() {

        EmmDeviceLostModeRequest deviceIdIsNull = new EmmDeviceLostModeRequest();
        deviceIdIsNull.setLostMessage("test message");
        deviceIdIsNull.setLostPhoneNumber("12345678910");
        Result<String> deviceIdIsNullResult = emmDeviceApi.startEmmDeviceLostMode(null, deviceIdIsNull);
        logger.debug("Result of start emm device lost mode by deviceId is null: {}", deviceIdIsNullResult.toString());
        Assert.assertEquals(-1, deviceIdIsNullResult.getBusinessCode());

        EmmDeviceLostModeRequest deviceIdIsNegative = new EmmDeviceLostModeRequest();
        deviceIdIsNegative.setLostMessage("test message");
        deviceIdIsNegative.setLostPhoneNumber("12345678910");
        Result<String> deviceIdIsNegativeResult = emmDeviceApi.startEmmDeviceLostMode(-1L, deviceIdIsNegative);
        logger.debug("Result of start emm device lost mode by deviceId is negative: {}", deviceIdIsNegativeResult.toString());
        Assert.assertEquals(-1, deviceIdIsNegativeResult.getBusinessCode());
    }

    @Test
    public void testStartEmmDeviceLostModeFailByLostMessageIsInvalid() {

        EmmDeviceLostModeRequest lostMessageIsNull = new EmmDeviceLostModeRequest();
        lostMessageIsNull.setLostMessage(null);
        lostMessageIsNull.setLostPhoneNumber("12345678910");
        Result<String> lostMessageIsNullResult = emmDeviceApi.startEmmDeviceLostMode(1652541362274340L, lostMessageIsNull);
        logger.debug("Result of start emm device lost mode by lostMessage is null: {}", lostMessageIsNullResult.toString());
        Assert.assertEquals(-1, lostMessageIsNullResult.getBusinessCode());

        EmmDeviceLostModeRequest lostMessageIsBlank = new EmmDeviceLostModeRequest();
        lostMessageIsBlank.setLostMessage("");
        lostMessageIsBlank.setLostPhoneNumber("12345678910");
        Result<String> lostMessageIsBlankResult = emmDeviceApi.startEmmDeviceLostMode(1652541362274340L, lostMessageIsBlank);
        logger.debug("Result of start emm device lost mode by lostMessage is blank: {}", lostMessageIsBlankResult.toString());
        Assert.assertEquals(-1, lostMessageIsBlankResult.getBusinessCode());

        EmmDeviceLostModeRequest lostMessageIsTooLong = new EmmDeviceLostModeRequest();
        lostMessageIsTooLong.setLostMessage("test message test messagetest messagetest messagetest messagetest messagetest messagetest messagetest messagetest messagetest messagetest messagetest messagetest messagetest message");
        lostMessageIsTooLong.setLostPhoneNumber("12345678910");
        Result<String> lostMessageIsTooLongResult = emmDeviceApi.startEmmDeviceLostMode(1652541362274340L, lostMessageIsTooLong);
        logger.debug("Result of start emm device lost mode by lost message is too long: {}", lostMessageIsTooLongResult.toString());
        Assert.assertEquals(-1, lostMessageIsTooLongResult.getBusinessCode());

    }

    @Test
    public void testStartEmmDeviceLostPhoneNumberIsInvalid() {
        EmmDeviceLostModeRequest lostPhoneNumberIsNull = new EmmDeviceLostModeRequest();
        lostPhoneNumberIsNull.setLostMessage("test message");
        lostPhoneNumberIsNull.setLostPhoneNumber(null);
        Result<String> lostPhoneNumberIsNullResult = emmDeviceApi.startEmmDeviceLostMode(1652541362274340L, lostPhoneNumberIsNull);
        logger.debug("Result of start emm device lost mode by lostPhoneNumber is null: {}", lostPhoneNumberIsNullResult.toString());
        Assert.assertEquals(-1, lostPhoneNumberIsNullResult.getBusinessCode());

        EmmDeviceLostModeRequest lostMessageIsBlank = new EmmDeviceLostModeRequest();
        lostMessageIsBlank.setLostMessage("test message");
        lostMessageIsBlank.setLostPhoneNumber("");
        Result<String> lostMessageIsBlankResult = emmDeviceApi.startEmmDeviceLostMode(1652541362274340L, lostMessageIsBlank);
        logger.debug("Result of start emm device lost mode by lostPhoneNumber is blank: {}", lostMessageIsBlankResult.toString());
        Assert.assertEquals(-1, lostMessageIsBlankResult.getBusinessCode());

        EmmDeviceLostModeRequest lostMessageIsTooLong = new EmmDeviceLostModeRequest();
        lostMessageIsTooLong.setLostMessage("test message");
        lostMessageIsTooLong.setLostPhoneNumber("12345678910123456789101234567891012345678910123456789101234567891012345678910");
        Result<String> lostMessageIsTooLongResult = emmDeviceApi.startEmmDeviceLostMode(1652541362274340L, lostMessageIsTooLong);
        logger.debug("Result of start emm device lost mode by lostPhoneNumber is too long: {}", lostMessageIsTooLongResult.toString());
        Assert.assertEquals(-1, lostMessageIsTooLongResult.getBusinessCode());
    }

    @Test
    public void testResumeEmmDeviceSuccess() {
        Result<String> result = emmDeviceApi.resumeEmmDevice(1754297972098L);
        logger.debug("Result of resume EMM device: {}", result.toString());
        Assert.assertEquals(0, result.getBusinessCode());
    }

    @Test
    public void testResumeEmmDeviceFailByDeviceIdIsInvalid() {
        Result<String> deviceIdIsNullResult = emmDeviceApi.resumeEmmDevice(null);
        logger.debug("Result of resume EMM device by deviceId is null: {}", deviceIdIsNullResult.toString());
        Assert.assertEquals(-1, deviceIdIsNullResult.getBusinessCode());

        Result<String> deviceIdIsNegativeResult = emmDeviceApi.resumeEmmDevice(-1L);
        logger.debug("Result of resume EMM device by deviceId is negative: {}", deviceIdIsNegativeResult.toString());
        Assert.assertEquals(-1, deviceIdIsNegativeResult.getBusinessCode());
    }


    @Test
    public void testDisableEmmDeviceSuccess() {
        Result<String> result = emmDeviceApi.disableEmmDevice(1754297972098L);
        logger.debug("Result of disable EMM device: {}", result.toString());
        Assert.assertEquals(0, result.getBusinessCode());
    }


    @Test
    public void testDisableEmmDeviceFailByDeviceIdIsInvalid() {
        Result<String> deviceIdIsNullResult = emmDeviceApi.disableEmmDevice(null);
        logger.debug("Result of disable EMM device by deviceId is null: {}", deviceIdIsNullResult.toString());
        Assert.assertEquals(-1, deviceIdIsNullResult.getBusinessCode());

        Result<String> deviceIdIsNegativeResult = emmDeviceApi.disableEmmDevice(-1L);
        logger.debug("Result of disable EMM device by deviceId is negative: {}", deviceIdIsNegativeResult.toString());
        Assert.assertEquals(-1, deviceIdIsNegativeResult.getBusinessCode());
    }


    @Test
    public void testSyncEmmDeviceDetailSuccess() {
        Result<String> result = emmDeviceApi.syncDeviceDetail(1687529174597667L);
        logger.debug("Result of sync EMM device detail: {}", result.toString());
        Assert.assertEquals(0, result.getBusinessCode());
    }

    @Test
    public void testSyncEmmDeviceDetailFailByDeviceIdIsInvalid() {
        Result<String> deviceIdIsNullResult = emmDeviceApi.syncDeviceDetail(null);
        logger.debug("Result of sync EMM device detail by deviceId is null: {}", deviceIdIsNullResult.toString());
        Assert.assertEquals(-1, deviceIdIsNullResult.getBusinessCode());

        Result<String> deviceIdIsNegativeResult = emmDeviceApi.syncDeviceDetail(-1L);
        logger.debug("Result of sync EMM device detail by deviceId is negative: {}", deviceIdIsNegativeResult.toString());
        Assert.assertEquals(-1, deviceIdIsNegativeResult.getBusinessCode());
    }

    @Test
    public void testStopEmmDeviceLostModeSuccess() {
        Result<String> result = emmDeviceApi.stopEmmDeviceLostMode(1647428843339813L);
        logger.debug("Result of stop emm device lost mode: {}", result.toString());
        Assert.assertEquals(0, result.getBusinessCode());
    }

    @Test
    public void testStopEmmDeviceLostModeFailByDeviceIdIsInvalid() {
        Result<String> deviceIdIsNullResult = emmDeviceApi.stopEmmDeviceLostMode(null);
        logger.debug("Result of stop emm device lost mode by deviceId is null: {}", deviceIdIsNullResult.toString());
        Assert.assertEquals(-1, deviceIdIsNullResult.getBusinessCode());

        Result<String> deviceIdIsNegativeResult = emmDeviceApi.stopEmmDeviceLostMode(-1L);
        logger.debug("Result of stop emm device lost mode by deviceId is negative: {}", deviceIdIsNegativeResult.toString());
        Assert.assertEquals(-1, deviceIdIsNegativeResult.getBusinessCode());
    }

    @Test
    public void testSubmitEmmZteQuickUploadRecordSuccess() {
        EmmZteQuickUploadRecordCreateRequest emmZteQuickUploadRecordCreateRequest = new EmmZteQuickUploadRecordCreateRequest();

        emmZteQuickUploadRecordCreateRequest.setResellerName("PAX");
        emmZteQuickUploadRecordCreateRequest.setMerchantName("test");
        emmZteQuickUploadRecordCreateRequest.setIdentifierType(EmmDeviceApi.EmmZteIdentifierType.IMEI);
        emmZteQuickUploadRecordCreateRequest.setNumbers("350968371000724");

        Result<String> result = emmDeviceApi.submitEmmZteQuickUploadRecord(emmZteQuickUploadRecordCreateRequest);

        logger.debug("Result of submit emm zte quick upload record: {}", result.toString());
        Assert.assertEquals(0, result.getBusinessCode());
    }

    @Test
    public void testSubmitEmmZteQuickUploadRecordFailByEmmZteQuickUploadRecordCreateRequestIsInvalid() {
        Result<String> result = emmDeviceApi.submitEmmZteQuickUploadRecord(null);
        logger.debug("Result of submit emm zte quick upload record by emmZteQuickUploadRecordCreateRequest is null: {}", result.toString());
        Assert.assertEquals(-1, result.getBusinessCode());
    }

    @Test
    public void testSubmitEmmZteQuickUploadRecordFailByResellerNameIsInvalid() {

        EmmZteQuickUploadRecordCreateRequest resellerNameIsNull = new EmmZteQuickUploadRecordCreateRequest();
        resellerNameIsNull.setResellerName(null);
        resellerNameIsNull.setMerchantName("test");
        resellerNameIsNull.setIdentifierType(EmmDeviceApi.EmmZteIdentifierType.IMEI);
        resellerNameIsNull.setNumbers("111111111111111");
        Result<String> resellerNameIsNullResult = emmDeviceApi.submitEmmZteQuickUploadRecord(resellerNameIsNull);
        logger.debug("Result of submit emm zte quick upload record by reseller name is null: {}", resellerNameIsNullResult.toString());
        Assert.assertEquals(-1, resellerNameIsNullResult.getBusinessCode());


        EmmZteQuickUploadRecordCreateRequest resellerNameIsBlank = new EmmZteQuickUploadRecordCreateRequest();
        resellerNameIsNull.setResellerName("");
        resellerNameIsNull.setMerchantName("test");
        resellerNameIsNull.setIdentifierType(EmmDeviceApi.EmmZteIdentifierType.IMEI);
        resellerNameIsNull.setNumbers("111111111111111");
        Result<String> resellerNameIsBlankResult = emmDeviceApi.submitEmmZteQuickUploadRecord(resellerNameIsBlank);
        logger.debug("Result of submit emm zte quick upload record by reseller name is blank: {}", resellerNameIsBlankResult.toString());
        Assert.assertEquals(-1, resellerNameIsBlankResult.getBusinessCode());


        EmmZteQuickUploadRecordCreateRequest resellerNameTooLong = new EmmZteQuickUploadRecordCreateRequest();
        resellerNameTooLong.setResellerName("PAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAX");
        resellerNameTooLong.setMerchantName("test");
        resellerNameTooLong.setIdentifierType(EmmDeviceApi.EmmZteIdentifierType.IMEI);
        resellerNameTooLong.setNumbers("111111111111111");
        Result<String> resellerNameIsTooLongResult = emmDeviceApi.submitEmmZteQuickUploadRecord(resellerNameTooLong);
        logger.debug("Result of submit emm zte quick upload record by reseller name is too long: {}", resellerNameIsTooLongResult.toString());
        Assert.assertEquals(-1, resellerNameIsTooLongResult.getBusinessCode());


        EmmZteQuickUploadRecordCreateRequest resellerNameNotExist = new EmmZteQuickUploadRecordCreateRequest();
        resellerNameNotExist.setResellerName("resellerNameNotExist");
        resellerNameNotExist.setMerchantName("test");
        resellerNameNotExist.setIdentifierType(EmmDeviceApi.EmmZteIdentifierType.IMEI);
        resellerNameNotExist.setNumbers("111111111111111");
        Result<String> resellerNameIsNotExistResult = emmDeviceApi.submitEmmZteQuickUploadRecord(resellerNameNotExist);
        logger.debug("Result of submit emm zte quick upload record by reseller name is not exist: {}", resellerNameIsNotExistResult.toString());
        Assert.assertEquals(1759, resellerNameIsNotExistResult.getBusinessCode());
    }

    @Test
    public void testSubmitEmmZteQuickUploadRecordFailByMerchantNameIsInvalid() {

        EmmZteQuickUploadRecordCreateRequest merchantNameIsNull = new EmmZteQuickUploadRecordCreateRequest();
        merchantNameIsNull.setResellerName("PAX");
        merchantNameIsNull.setMerchantName(null);
        merchantNameIsNull.setIdentifierType(EmmDeviceApi.EmmZteIdentifierType.IMEI);
        merchantNameIsNull.setNumbers("111111111111111");
        Result<String> merchantNameIsNullResult = emmDeviceApi.submitEmmZteQuickUploadRecord(merchantNameIsNull);
        logger.debug("Result of submit emm zte quick upload record by merchant name is null: {}", merchantNameIsNullResult.toString());
        Assert.assertEquals(-1, merchantNameIsNullResult.getBusinessCode());


        EmmZteQuickUploadRecordCreateRequest merchantNameIsBlank = new EmmZteQuickUploadRecordCreateRequest();
        merchantNameIsBlank.setResellerName("PAX");
        merchantNameIsBlank.setMerchantName("");
        merchantNameIsBlank.setIdentifierType(EmmDeviceApi.EmmZteIdentifierType.IMEI);
        merchantNameIsBlank.setNumbers("111111111111111");
        Result<String> merchantNameIsBlankResult = emmDeviceApi.submitEmmZteQuickUploadRecord(merchantNameIsBlank);
        logger.debug("Result of submit emm zte quick upload record by merchant name is blank: {}", merchantNameIsBlankResult.toString());
        Assert.assertEquals(-1, merchantNameIsBlankResult.getBusinessCode());


        EmmZteQuickUploadRecordCreateRequest merchantNameTooLong = new EmmZteQuickUploadRecordCreateRequest();
        merchantNameTooLong.setResellerName("PAX");
        merchantNameTooLong.setMerchantName("PAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAX");
        merchantNameTooLong.setIdentifierType(EmmDeviceApi.EmmZteIdentifierType.IMEI);
        merchantNameTooLong.setNumbers("111111111111111");
        Result<String> merchantNameTooLongResult = emmDeviceApi.submitEmmZteQuickUploadRecord(merchantNameTooLong);
        logger.debug("Result of submit emm zte quick upload record by merchant name is too long: {}", merchantNameTooLongResult.toString());
        Assert.assertEquals(-1, merchantNameTooLongResult.getBusinessCode());


        EmmZteQuickUploadRecordCreateRequest merchantNameNotExist = new EmmZteQuickUploadRecordCreateRequest();
        merchantNameNotExist.setResellerName("PAX");
        merchantNameNotExist.setMerchantName("merchantNameNotExist");
        merchantNameNotExist.setIdentifierType(EmmDeviceApi.EmmZteIdentifierType.IMEI);
        merchantNameNotExist.setNumbers("111111111111111");
        Result<String> merchantNameNotExistResult = emmDeviceApi.submitEmmZteQuickUploadRecord(merchantNameNotExist);
        logger.debug("Result of submit emm zte quick upload record by merchant name is not exist: {}", merchantNameNotExistResult.toString());
        Assert.assertEquals(1720, merchantNameNotExistResult.getBusinessCode());

    }

    @Test
    public void testSubmitEmmZteQuickUploadRecordFailByIdentifierTypeIsInvalid() {
        EmmZteQuickUploadRecordCreateRequest emmZteQuickUploadRecordCreateRequest = new EmmZteQuickUploadRecordCreateRequest();
        emmZteQuickUploadRecordCreateRequest.setResellerName("PAX");
        emmZteQuickUploadRecordCreateRequest.setMerchantName("test");
        emmZteQuickUploadRecordCreateRequest.setIdentifierType(null);
        emmZteQuickUploadRecordCreateRequest.setNumbers("111111111111111");
        Result<String> result = emmDeviceApi.submitEmmZteQuickUploadRecord(emmZteQuickUploadRecordCreateRequest);
        logger.debug("Result of submit emm zte quick upload record by identifierType is null: {}", result.toString());
        Assert.assertEquals(-1, result.getBusinessCode());
    }

    @Test
    public void testSubmitEmmZteQuickUploadRecordFailByNumbersIsInvalid() {

        EmmZteQuickUploadRecordCreateRequest numbersIsNull = new EmmZteQuickUploadRecordCreateRequest();
        numbersIsNull.setResellerName("PAX");
        numbersIsNull.setMerchantName("test");
        numbersIsNull.setIdentifierType(EmmDeviceApi.EmmZteIdentifierType.IMEI);
        numbersIsNull.setNumbers(null);
        Result<String> numbersIsNullResult = emmDeviceApi.submitEmmZteQuickUploadRecord(numbersIsNull);
        logger.debug("Result of submit emm zte quick upload record by numbers is null: {}", numbersIsNullResult.toString());
        Assert.assertEquals(-1, numbersIsNullResult.getBusinessCode());


        EmmZteQuickUploadRecordCreateRequest numbersIsBlank = new EmmZteQuickUploadRecordCreateRequest();
        numbersIsBlank.setResellerName("PAX");
        numbersIsBlank.setMerchantName("test");
        numbersIsBlank.setIdentifierType(EmmDeviceApi.EmmZteIdentifierType.IMEI);
        numbersIsBlank.setNumbers("");
        Result<String> numbersIsBlankResult = emmDeviceApi.submitEmmZteQuickUploadRecord(numbersIsBlank);
        logger.debug("Result of submit emm zte quick upload record by numbers is blank: {}", numbersIsBlankResult.toString());
        Assert.assertEquals(-1, numbersIsBlankResult.getBusinessCode());
    }

















}
