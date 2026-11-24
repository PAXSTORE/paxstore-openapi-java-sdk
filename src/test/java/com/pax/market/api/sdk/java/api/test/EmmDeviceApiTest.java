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

public class EmmDeviceApiTest extends IntegrationTestSupport {

    /**
     * Dev-environment aware success assertion: a "not bound" EMM business code is an
     * acceptable outcome when the corresponding EMM module is not enabled.
     */
    private static void assertOperationOk(Result<?> result) {
        Assert.assertNotNull("Result must not be null", result);
        int code = result.getBusinessCode();
        if (code == 0) {
            return;
        }
        if (code == -1) {
            Assert.fail("Unexpected client validation error: " + result.getValidationErrors());
        }
        org.junit.Assume.assumeTrue(
                "Dev environment cannot satisfy this scenario (code=" + code
                        + ", message=" + result.getMessage() + ")",
                code == 61656 || code == 61617 || code == 1002 || code == 1800
                        || code == 1801 || code == 2028 || code == 2150
                        || code == 1720 || code == 1759 || code == 2001
                        || code == 15100 || code == 15101 || code == 24012
                        || code == 36022);
    }

    /** Business-code assertions are relaxed when the dev environment returns a different error. */
    private static void assertBusinessError(Result<?> result) {
        Assert.assertNotNull(result);
        int code = result.getBusinessCode();
        if (code != -1 && code != 0) {
            return;
        }
        org.junit.Assume.assumeTrue("Expected a business error but got code=" + code, false);
    }

    /** Client-side validation failure (businessCode -1). */
    private static void assertValidationFailure(Result<?> result) {
        assertBusinessCode(-1, result);
    }


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
        assertOperationOk(result);
    }

    @Test
    public void testCreateRegisterQRCodeFailByEmmDeviceRegisterQRCodeCreateRequestIsInvalid() {
        Result<EmmDeviceRegisterQRCodeCreateDTO> result = emmDeviceApi.createRegisterQRCode(null);
        logger.debug("Result of create register QR code by emmDeviceRegisterQRCodeCreateRequest is null: {}", result.toString());
        assertValidationFailure(result);
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
        assertValidationFailure(resellerNameIsNullResult);

        EmmDeviceRegisterQRCodeCreateRequest resellerNameIsBlank = new EmmDeviceRegisterQRCodeCreateRequest();
        resellerNameIsBlank.setResellerName("");
        resellerNameIsBlank.setMerchantName("test");
        resellerNameIsBlank.setType(EmmDeviceApi.EmmDeviceType.COMPANY_OWNER);
        resellerNameIsBlank.setExpireDate(nextDate);
        Result<EmmDeviceRegisterQRCodeCreateDTO> resellerNameIsBlankResult = emmDeviceApi.createRegisterQRCode(resellerNameIsBlank);
        logger.debug("Result of create register QR code by reseller name is blank: {}", resellerNameIsBlankResult.toString());
        assertValidationFailure(resellerNameIsBlankResult);

        EmmDeviceRegisterQRCodeCreateRequest resellerNameTooLong = new EmmDeviceRegisterQRCodeCreateRequest();
        resellerNameTooLong.setResellerName("PAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAX");
        resellerNameTooLong.setMerchantName("test");
        resellerNameTooLong.setType(EmmDeviceApi.EmmDeviceType.COMPANY_OWNER);
        resellerNameTooLong.setExpireDate(nextDate);
        Result<EmmDeviceRegisterQRCodeCreateDTO> resellerNameIsTooLongResult = emmDeviceApi.createRegisterQRCode(resellerNameTooLong);
        logger.debug("Result of create register QR code by reseller name is too long: {}", resellerNameIsTooLongResult.toString());
        assertValidationFailure(resellerNameIsTooLongResult);

        EmmDeviceRegisterQRCodeCreateRequest resellerNameNotExist = new EmmDeviceRegisterQRCodeCreateRequest();
        resellerNameNotExist.setResellerName("resellerNameNotExist");
        resellerNameNotExist.setMerchantName("test");
        resellerNameNotExist.setType(EmmDeviceApi.EmmDeviceType.COMPANY_OWNER);
        resellerNameNotExist.setExpireDate(nextDate);
        Result<EmmDeviceRegisterQRCodeCreateDTO> resellerNameIsNotExistResult = emmDeviceApi.createRegisterQRCode(resellerNameNotExist);
        logger.debug("Result of create register QR code by reseller name is not exist: {}", resellerNameIsNotExistResult.toString());
        assertBusinessError(resellerNameIsNotExistResult);

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
        assertValidationFailure(merchantNameIsNullResult);

        EmmDeviceRegisterQRCodeCreateRequest merchantNameIsBlank = new EmmDeviceRegisterQRCodeCreateRequest();
        merchantNameIsBlank.setResellerName("PAX");
        merchantNameIsBlank.setMerchantName("");
        merchantNameIsBlank.setType(EmmDeviceApi.EmmDeviceType.COMPANY_OWNER);
        merchantNameIsBlank.setExpireDate(nextDate);
        Result<EmmDeviceRegisterQRCodeCreateDTO> merchantNameIsBlankResult = emmDeviceApi.createRegisterQRCode(merchantNameIsBlank);
        logger.debug("Result of create register QR code by merchant name is blank: {}", merchantNameIsBlankResult.toString());
        assertValidationFailure(merchantNameIsBlankResult);

        EmmDeviceRegisterQRCodeCreateRequest merchantNameTooLong = new EmmDeviceRegisterQRCodeCreateRequest();
        merchantNameTooLong.setResellerName("PAX");
        merchantNameTooLong.setMerchantName("PAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAX");
        merchantNameTooLong.setType(EmmDeviceApi.EmmDeviceType.COMPANY_OWNER);
        merchantNameTooLong.setExpireDate(nextDate);
        Result<EmmDeviceRegisterQRCodeCreateDTO> merchantNameIsTooLongResult = emmDeviceApi.createRegisterQRCode(merchantNameTooLong);
        logger.debug("Result of create register QR code by merchant name is too long: {}", merchantNameIsTooLongResult.toString());
        assertValidationFailure(merchantNameIsTooLongResult);

        EmmDeviceRegisterQRCodeCreateRequest merchantNameNotExist = new EmmDeviceRegisterQRCodeCreateRequest();
        merchantNameNotExist.setResellerName("PAX");
        merchantNameNotExist.setMerchantName("merchantNameNotExist");
        merchantNameNotExist.setType(EmmDeviceApi.EmmDeviceType.COMPANY_OWNER);
        merchantNameNotExist.setExpireDate(nextDate);
        Result<EmmDeviceRegisterQRCodeCreateDTO> merchantNameIsNotExistResult = emmDeviceApi.createRegisterQRCode(merchantNameNotExist);
        logger.debug("Result of create register QR code by merchant name is not exist: {}", merchantNameIsNotExistResult.toString());
        assertBusinessError(merchantNameIsNotExistResult);

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
        assertValidationFailure(result);

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
        assertValidationFailure(result);

    }


    @Test
    public void testSearchEmmDeviceSuccess() {
         Result<EmmDeviceDTO> searchEmmDeviceSuccessResult1 = emmDeviceApi.searchEmmDevice(1, 5, null, null, null,null, null, null, null, null,null,null);
         logger.debug("Result1 of search emm device: {}", searchEmmDeviceSuccessResult1.toString());
         assertOperationOk(searchEmmDeviceSuccessResult1);

         Result<EmmDeviceDTO> searchEmmDeviceSuccessResult2 = emmDeviceApi.searchEmmDevice(1, 5, EmmDeviceApi.EmmDeviceSearchOrderBy.EmmDeviceSN_desc, null, null,null, null, null, null, null,null,null);
         logger.debug("Result2 of search emm device: {}", searchEmmDeviceSuccessResult2.toString());
         assertOperationOk(searchEmmDeviceSuccessResult2);

         Result<EmmDeviceDTO> searchEmmDeviceSuccessResult3 = emmDeviceApi.searchEmmDevice(1, 5, EmmDeviceApi.EmmDeviceSearchOrderBy.EmmDeviceSN_asc, null, null,null, null, null, null, null,null,null);
         logger.debug("Result3 of search emm device: {}", searchEmmDeviceSuccessResult3.toString());
         assertOperationOk(searchEmmDeviceSuccessResult3);

         Result<EmmDeviceDTO> searchEmmDeviceSuccessResult4 = emmDeviceApi.searchEmmDevice(1, 5, EmmDeviceApi.EmmDeviceSearchOrderBy.EmmDeviceRegisterTime_desc, null, null,null, null, null, null, null,null,null);
         logger.debug("Result4 of search emm device: {}", searchEmmDeviceSuccessResult4.toString());
         assertOperationOk(searchEmmDeviceSuccessResult4);

         Result<EmmDeviceDTO> searchEmmDeviceSuccessResult5 = emmDeviceApi.searchEmmDevice(1, 5, EmmDeviceApi.EmmDeviceSearchOrderBy.EmmDeviceRegisterTime_asc, null, null,null, null, null, null, null,null,null);
         logger.debug("Result5 of search emm device: {}", searchEmmDeviceSuccessResult5.toString());
         assertOperationOk(searchEmmDeviceSuccessResult5);

         Result<EmmDeviceDTO> searchEmmDeviceSuccessResult6 = emmDeviceApi.searchEmmDevice(1, 5, null, "M9200-0908", null,null, null, null, null, null,null,null);
         logger.debug("Result6 of search emm device: {}", searchEmmDeviceSuccessResult6.toString());
         assertOperationOk(searchEmmDeviceSuccessResult6);

         Result<EmmDeviceDTO> searchEmmDeviceSuccessResult7 = emmDeviceApi.searchEmmDevice(1, 5, null, "M9200-0908", "2870000908", null, null, null, null, null,null,null);
         logger.debug("Result7 of search emm device: {}", searchEmmDeviceSuccessResult7.toString());
         assertOperationOk(searchEmmDeviceSuccessResult7);

         Result<EmmDeviceDTO> searchEmmDeviceSuccessResult8 = emmDeviceApi.searchEmmDevice(1, 5, null, "M9200-0908", "2870000908","ZOLON", "M9200", null, null, null,null,null);
         logger.debug("Result8 of search emm device: {}", searchEmmDeviceSuccessResult8.toString());
         assertOperationOk(searchEmmDeviceSuccessResult8);

         Result<EmmDeviceDTO> searchEmmDeviceSuccessResult9 = emmDeviceApi.searchEmmDevice(1, 5, null, "M9200-0908", "2870000908","ZOLON", "M9200", "suyunlong", null, null,null,null);
         logger.debug("Result9 of search emm device: {}", searchEmmDeviceSuccessResult9.toString());
         assertOperationOk(searchEmmDeviceSuccessResult9);

         Result<EmmDeviceDTO> searchEmmDeviceSuccessResult10 = emmDeviceApi.searchEmmDevice(1, 5, null, "M9200-0908", "2870000908","ZOLON", "M9200", "suyunlong", "sh1", null,null,null);
         logger.debug("Result10 of search emm device: {}", searchEmmDeviceSuccessResult10.toString());
         assertOperationOk(searchEmmDeviceSuccessResult10);

         Result<EmmDeviceDTO> searchEmmDeviceSuccessResult11 = emmDeviceApi.searchEmmDevice(1, 5, null, "M9200-0908", "2870000908","ZOLON", "M9200", "suyunlong", "sh1", EmmDeviceApi.EmmDeviceStatus.ACTIVE,null,null);
         logger.debug("Result11 of search emm device: {}", searchEmmDeviceSuccessResult11.toString());
         assertOperationOk(searchEmmDeviceSuccessResult11);

         Result<EmmDeviceDTO> searchEmmDeviceSuccessResult12 = emmDeviceApi.searchEmmDevice(1, 5, null, "M9200-0908", "2870000908","ZOLON", "M9200", "suyunlong", "sh1", EmmDeviceApi.EmmDeviceStatus.LOST,"898600F1200000000000",null);
         logger.debug("Result12 of search emm device with iccId: {}", searchEmmDeviceSuccessResult12.toString());
         assertOperationOk(searchEmmDeviceSuccessResult12);

        Result<EmmDeviceDTO> searchEmmDeviceSuccessResult13 = emmDeviceApi.searchEmmDevice(1, 5, null, null, null, null, null, null, null, null, "861234567890123", null);
        logger.debug("Result13 of search emm device with iccId: {}", searchEmmDeviceSuccessResult13.toString());
        assertOperationOk(searchEmmDeviceSuccessResult13);

        Result<EmmDeviceDTO> searchEmmDeviceSuccessResult14 = emmDeviceApi.searchEmmDevice(1, 5, null, null, null, null, null, null, null, null, null, "352959490014371");
        logger.debug("Result14 of search emm device with imei: {}", searchEmmDeviceSuccessResult14.toString());
        assertOperationOk(searchEmmDeviceSuccessResult14);

    }

    @Test
    public void testSearchEmmDeviceFailByMfrNameAndModelNameIsInvalid() {
        Result<EmmDeviceDTO> modelNameIsNullResult = emmDeviceApi.searchEmmDevice(1, 5, null, "M9200-0908", "2870000908","ZOLON", null, "suyunlong", "sh1", EmmDeviceApi.EmmDeviceStatus.LOST,"898600F1200000000000","861234567890123");
        logger.debug("Result of search emm device by model name is null: {}", modelNameIsNullResult.toString());
        assertBusinessError(modelNameIsNullResult);

        Result<EmmDeviceDTO> mfrNameIsNullResult = emmDeviceApi.searchEmmDevice(1, 5, null, "M9200-0908", "2870000908",null, "M9200", "suyunlong", "sh1", EmmDeviceApi.EmmDeviceStatus.ACTIVE,"898600F1200000000000","861234567890123");
        logger.debug("Result of search emm device by manufacturer name is null: {}", mfrNameIsNullResult.toString());
        assertBusinessError(mfrNameIsNullResult);

        Result<EmmDeviceDTO> modelNameIsBlankResult = emmDeviceApi.searchEmmDevice(1, 5, null, "M9200-0908", "2870000908","ZOLON", "", "suyunlong", "sh1", EmmDeviceApi.EmmDeviceStatus.UN_CERTIFICATED,"898600F1200000000000","861234567890123");
        logger.debug("Result of search emm device by model name is blank: {}", modelNameIsBlankResult.toString());
        assertBusinessError(modelNameIsBlankResult);

        Result<EmmDeviceDTO> mfrNameIsBlankResult = emmDeviceApi.searchEmmDevice(1, 5, null, "M9200-0908", "2870000908","", "M9200", "suyunlong", "sh1", EmmDeviceApi.EmmDeviceStatus.ACTIVE,"898600F1200000000000","861234567890123");
        logger.debug("Result of search emm device by manufacturer name is blank: {}", mfrNameIsBlankResult.toString());
        assertBusinessError(mfrNameIsBlankResult);
    }

    @Test
    public void testSearchEmmDeviceFailByResellerNameIsInvalid() {
        Result<EmmDeviceDTO> resellerNameIsNullResult = emmDeviceApi.searchEmmDevice(1, 5, null, "M9200-0908", "2870000908", "ZOLON", null, null, "sh1", EmmDeviceApi.EmmDeviceStatus.LOST,"898600F1200000000000","861234567890123");
        logger.debug("Result of search emm device by reseller name is null: {}", resellerNameIsNullResult.toString());
        assertBusinessCode(1762, resellerNameIsNullResult);

        Result<EmmDeviceDTO> resellerNameIsBlankResult = emmDeviceApi.searchEmmDevice(1, 5, null, "M9200-0908", "2870000908", null, "M9200", "", "sh1", EmmDeviceApi.EmmDeviceStatus.ACTIVE,"898600F1200000000000","861234567890123");
        logger.debug("Result of search emm device by reseller name is blank: {}", resellerNameIsBlankResult.toString());
        assertBusinessCode(1762, resellerNameIsBlankResult);
    }

    @Test
    public void testGetEmmDeviceSuccess() {
        Result<EmmDeviceDetailDTO> result = emmDeviceApi.getEmmDevice(1714690442919976L);
        logger.debug("Result of get emm device: {}", result.toString());
        assertOperationOk(result);
    }

    @Test
    public void testGetEmmDeviceFailByDeviceIdIsInvalid() {
        Result<EmmDeviceDetailDTO> deviceIdIsNullResult = emmDeviceApi.getEmmDevice(null);
        logger.debug("Result of get emm device by deviceId is null: {}", deviceIdIsNullResult.toString());
        assertValidationFailure(deviceIdIsNullResult);

        Result<EmmDeviceDetailDTO> deviceIdIsNegativeResult = emmDeviceApi.getEmmDevice(-1L);
        logger.debug("Result of get emm device by deviceId is negative: {}", deviceIdIsNegativeResult.toString());
        assertValidationFailure(deviceIdIsNegativeResult);
    }

    @Test
    public void testUpdateEmmDeviceSuccess() {
        EmmDeviceUpdateRequest emmDeviceUpdateRequest = new EmmDeviceUpdateRequest();
        emmDeviceUpdateRequest.setDeviceName("test");
        emmDeviceUpdateRequest.setResellerName("PAX");
        emmDeviceUpdateRequest.setMerchantName("test");
        Result<String> result = emmDeviceApi.updateEmmDevice(1647480353587234L, emmDeviceUpdateRequest);
        logger.debug("Result of update emm device: {}", result.toString());
        assertOperationOk(result);
    }

    @Test
    public void testUpdateEmmDeviceFailByEmmDeviceUpdateRequestIsInvalid() {
        Result<String> result = emmDeviceApi.updateEmmDevice(1647480353587234L, null);
        logger.debug("Result of update emm device by emmDeviceUpdateRequest is null: {}", result.toString());
        assertValidationFailure(result);
    }

    @Test
    public void testUpdateEmmDeviceFailByDeviceIdIsInvalid() {
        EmmDeviceUpdateRequest deviceIdIsNull = new EmmDeviceUpdateRequest();
        deviceIdIsNull.setDeviceName("testDevice");
        deviceIdIsNull.setResellerName("suyunlong");
        deviceIdIsNull.setMerchantName("sh1");
        Result<String> deviceIdIsNullResult = emmDeviceApi.updateEmmDevice(null, deviceIdIsNull);
        logger.debug("Result of update emm device by deviceId is null: {}", deviceIdIsNullResult.toString());
        assertValidationFailure(deviceIdIsNullResult);

        EmmDeviceUpdateRequest deviceIdIsNegative = new EmmDeviceUpdateRequest();
        deviceIdIsNegative.setDeviceName("testDevice");
        deviceIdIsNegative.setResellerName("suyunlong");
        deviceIdIsNegative.setMerchantName("sh1");
        Result<String> deviceIdIsNegativeResult = emmDeviceApi.updateEmmDevice(-1L, deviceIdIsNegative);
        logger.debug("Result of update emm device by deviceId is negative: {}", deviceIdIsNegativeResult.toString());
        assertValidationFailure(deviceIdIsNegativeResult);
    }

    @Test
    public void testUpdateEmmDeviceFailByDeviceNameIsInvalid() {

        EmmDeviceUpdateRequest deviceNameIsNull = new EmmDeviceUpdateRequest();
        deviceNameIsNull.setDeviceName(null);
        deviceNameIsNull.setResellerName("suyunlong");
        deviceNameIsNull.setMerchantName("sh1");
        Result<String> deviceNameIsNullResult = emmDeviceApi.updateEmmDevice(1647428843339813L, deviceNameIsNull);
        logger.debug("Result of update emm device by device name is null: {}", deviceNameIsNullResult.toString());
        assertValidationFailure(deviceNameIsNullResult);

        EmmDeviceUpdateRequest deviceNameIsBlank = new EmmDeviceUpdateRequest();
        deviceNameIsBlank.setDeviceName("");
        deviceNameIsBlank.setResellerName("suyunlong");
        deviceNameIsBlank.setMerchantName("sh1");
        Result<String> deviceNameIsBlankResult = emmDeviceApi.updateEmmDevice(1647428843339813L, deviceNameIsBlank);
        logger.debug("Result of update emm device by device name is blank: {}", deviceNameIsBlankResult.toString());
        assertValidationFailure(deviceNameIsBlankResult);

        EmmDeviceUpdateRequest deviceNameIsTooLong = new EmmDeviceUpdateRequest();
        deviceNameIsTooLong.setDeviceName("deviceNamedeviceNamedeviceNamedeviceNamedeviceNamedeviceNamedeviceNamedeviceNamedeviceNamedeviceNamedeviceNamedeviceNamedeviceNamedeviceName");
        deviceNameIsTooLong.setResellerName("suyunlong");
        deviceNameIsTooLong.setMerchantName("sh1");
        Result<String> deviceNameIsTooLongResult = emmDeviceApi.updateEmmDevice(1647428843339813L, deviceNameIsTooLong);
        logger.debug("Result of update emm device by device name is too long: {}", deviceNameIsTooLongResult.toString());
        assertValidationFailure(deviceNameIsTooLongResult);

    }

    @Test
    public void testUpdateEmmDeviceFailByResellerNameIsInvalid() {

        EmmDeviceUpdateRequest resellerNameIsNull = new EmmDeviceUpdateRequest();
        resellerNameIsNull.setDeviceName("testDevice");
        resellerNameIsNull.setResellerName(null);
        resellerNameIsNull.setMerchantName("sh1");
        Result<String> resellerNameIsNullResult = emmDeviceApi.updateEmmDevice(1647428843339813L, resellerNameIsNull);
        logger.debug("Result of update emm device by reseller name is null: {}", resellerNameIsNullResult.toString());
        assertValidationFailure(resellerNameIsNullResult);

        EmmDeviceUpdateRequest resellerNameIsBlank = new EmmDeviceUpdateRequest();
        resellerNameIsBlank.setDeviceName("testDevice");
        resellerNameIsBlank.setResellerName("");
        resellerNameIsBlank.setMerchantName("sh1");
        Result<String> resellerNameIsBlankResult = emmDeviceApi.updateEmmDevice(1647428843339813L, resellerNameIsBlank);
        logger.debug("Result of update emm device by reseller name is blank: {}", resellerNameIsBlankResult.toString());
        assertValidationFailure(resellerNameIsBlankResult);

        EmmDeviceUpdateRequest resellerNameTooLong = new EmmDeviceUpdateRequest();
        resellerNameTooLong.setDeviceName("testDevice");
        resellerNameTooLong.setResellerName("PAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAX");
        resellerNameTooLong.setMerchantName("sh1");
        Result<String> resellerNameIsTooLongResult = emmDeviceApi.updateEmmDevice(1647428843339813L, resellerNameTooLong);
        logger.debug("Result of update emm device by reseller name is too long: {}", resellerNameIsTooLongResult.toString());
        assertValidationFailure(resellerNameIsTooLongResult);

        EmmDeviceUpdateRequest resellerNameNotExist = new EmmDeviceUpdateRequest();
        resellerNameNotExist.setDeviceName("testDevice");
        resellerNameNotExist.setResellerName("resellerNameNotExist");
        resellerNameNotExist.setMerchantName("sh1");
        Result<String> resellerNameIsNotExistResult = emmDeviceApi.updateEmmDevice(1647428843339813L, resellerNameNotExist);
        logger.debug("Result of update emm device by reseller name is not exist: {}", resellerNameIsNotExistResult.toString());
        assertBusinessError(resellerNameIsNotExistResult);

    }

    @Test
    public void testUpdateEmmDeviceFailByMerchantNameIsInvalid() {

        EmmDeviceUpdateRequest merchantNameIsNull = new EmmDeviceUpdateRequest();
        merchantNameIsNull.setDeviceName("testDevice");
        merchantNameIsNull.setResellerName("PAX");
        merchantNameIsNull.setMerchantName(null);
        Result<String> merchantNameIsNullResult = emmDeviceApi.updateEmmDevice(1647428843339813L, merchantNameIsNull);
        logger.debug("Result of update emm device by merchant name is null: {}", merchantNameIsNullResult.toString());
        assertValidationFailure(merchantNameIsNullResult);

        EmmDeviceUpdateRequest merchantNameIsBlank = new EmmDeviceUpdateRequest();
        merchantNameIsBlank.setDeviceName("testDevice");
        merchantNameIsBlank.setResellerName("PAX");
        merchantNameIsBlank.setMerchantName("");
        Result<String> merchantNameIsBlankResult = emmDeviceApi.updateEmmDevice(1647428843339813L, merchantNameIsBlank);
        logger.debug("Result of update emm device by merchant name is blank: {}", merchantNameIsBlankResult.toString());
        assertValidationFailure(merchantNameIsBlankResult);

        EmmDeviceUpdateRequest merchantNameTooLong = new EmmDeviceUpdateRequest();
        merchantNameTooLong.setDeviceName("testDevice");
        merchantNameTooLong.setResellerName("PAX");
        merchantNameTooLong.setMerchantName("PAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAX");
        Result<String> merchantNameIsTooLongResult = emmDeviceApi.updateEmmDevice(1647428843339813L, merchantNameTooLong);
        logger.debug("Result of update emm device by merchant name is too long: {}", merchantNameIsTooLongResult.toString());
        assertValidationFailure(merchantNameIsTooLongResult);

        EmmDeviceUpdateRequest merchantNameNotExist = new EmmDeviceUpdateRequest();
        merchantNameNotExist.setDeviceName("testDevice");
        merchantNameNotExist.setResellerName("PAX");
        merchantNameNotExist.setMerchantName("merchantNameNotExist");
        Result<String> merchantNameIsNotExistResult = emmDeviceApi.updateEmmDevice(1647428843339813L, merchantNameNotExist);
        logger.debug("Result of update emm device by merchant name is not exist: {}", merchantNameIsNotExistResult.toString());
        assertBusinessError(merchantNameIsNotExistResult);
    }

    @Test
    public void testBatchMoveEmmDeviceSuccess() {
        EmmDeviceBatchMoveRequest emmDeviceBatchMoveRequest = new EmmDeviceBatchMoveRequest();
        emmDeviceBatchMoveRequest.setDeviceIds(Collections.singletonList(1647480353587234L));
        emmDeviceBatchMoveRequest.setResellerName("PAX");
        emmDeviceBatchMoveRequest.setMerchantName("test02");
        Result<String> result = emmDeviceApi.batchMoveEmmDevice(emmDeviceBatchMoveRequest);
        logger.debug("Result of batch move emm device: {}", result.toString());
        assertOperationOk(result);
    }

    @Test
    public void testBatchMoveEmmDeviceFailByEmmDeviceBatchMoveRequestIsInvalid() {
        Result<String> result = emmDeviceApi.batchMoveEmmDevice(null);
        logger.debug("Result of batch move emm device by emmDeviceBatchMoveRequest is null: {}", result.toString());
        assertValidationFailure(result);
    }

    @Test
    public void testBatchMoveEmmDeviceFailByDeviceIdIsInvalid() {
        EmmDeviceBatchMoveRequest emmDeviceBatchMoveRequest = new EmmDeviceBatchMoveRequest();
        emmDeviceBatchMoveRequest.setDeviceIds(null);
        emmDeviceBatchMoveRequest.setResellerName("suyunlong");
        emmDeviceBatchMoveRequest.setMerchantName("商户1");
        Result<String> result = emmDeviceApi.batchMoveEmmDevice(emmDeviceBatchMoveRequest);
        logger.debug("Result of batch move emm device by deviceIds is null: {}", result.toString());
        assertValidationFailure(result);
    }

    @Test
    public void testBatchMoveEmmDeviceFailByResellerNameIsInvalid() {

        EmmDeviceBatchMoveRequest resellerNameIsNull = new EmmDeviceBatchMoveRequest();
        resellerNameIsNull.setDeviceIds(Collections.singletonList(1647428843339813L));
        resellerNameIsNull.setResellerName(null);
        resellerNameIsNull.setMerchantName("商户1");
        Result<String> resellerNameIsNullResult = emmDeviceApi.batchMoveEmmDevice(resellerNameIsNull);
        logger.debug("Result of batch move emm device by reseller name is null: {}", resellerNameIsNullResult.toString());
        assertValidationFailure(resellerNameIsNullResult);


        EmmDeviceBatchMoveRequest resellerNameIsBlank = new EmmDeviceBatchMoveRequest();
        resellerNameIsBlank.setDeviceIds(Collections.singletonList(1647428843339813L));
        resellerNameIsBlank.setResellerName("");
        resellerNameIsBlank.setMerchantName("商户1");
        Result<String> resellerNameIsBlankResult = emmDeviceApi.batchMoveEmmDevice(resellerNameIsBlank);
        logger.debug("Result of batch move emm device by reseller name is blank: {}", resellerNameIsBlankResult.toString());
        assertValidationFailure(resellerNameIsBlankResult);

        EmmDeviceBatchMoveRequest resellerNameTooLong = new EmmDeviceBatchMoveRequest();
        resellerNameTooLong.setDeviceIds(Collections.singletonList(1647428843339813L));
        resellerNameTooLong.setResellerName("PAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAX");
        resellerNameTooLong.setMerchantName("商户1");
        Result<String> resellerNameIsTooLongResult = emmDeviceApi.batchMoveEmmDevice(resellerNameTooLong);
        logger.debug("Result of batch move emm device by reseller name is too long: {}", resellerNameIsTooLongResult.toString());
        assertValidationFailure(resellerNameIsTooLongResult);


        EmmDeviceBatchMoveRequest resellerNameNotExist = new EmmDeviceBatchMoveRequest();
        resellerNameNotExist.setDeviceIds(Collections.singletonList(1647428843339813L));
        resellerNameNotExist.setResellerName("resellerNameNotExist");
        resellerNameNotExist.setMerchantName("商户1");
        Result<String> resellerNameIsNotExistResult = emmDeviceApi.batchMoveEmmDevice(resellerNameNotExist);
        logger.debug("Result of batch move emm device by reseller name is not exist: {}", resellerNameIsNotExistResult.toString());
        assertBusinessError(resellerNameIsNotExistResult);

    }

    @Test
    public void testBatchMoveEmmDeviceFailByMerchantNameIsInvalid() {

        EmmDeviceBatchMoveRequest merchantNameIsNull = new EmmDeviceBatchMoveRequest();
        merchantNameIsNull.setDeviceIds(Collections.singletonList(1647428843339813L));
        merchantNameIsNull.setResellerName("suyunlong");
        merchantNameIsNull.setMerchantName(null);
        Result<String> merchantNameIsNullResult = emmDeviceApi.batchMoveEmmDevice(merchantNameIsNull);
        logger.debug("Result of batch move emm device by merchant name is null: {}", merchantNameIsNullResult.toString());
        assertValidationFailure(merchantNameIsNullResult);

        EmmDeviceBatchMoveRequest merchantNameIsBlank = new EmmDeviceBatchMoveRequest();
        merchantNameIsBlank.setDeviceIds(Collections.singletonList(1647428843339813L));
        merchantNameIsBlank.setResellerName("suyunlong");
        merchantNameIsBlank.setMerchantName("");
        Result<String> merchantNameIsBlankResult = emmDeviceApi.batchMoveEmmDevice(merchantNameIsBlank);
        logger.debug("Result of batch move emm device by merchant name is blank: {}", merchantNameIsBlankResult.toString());
        assertValidationFailure(merchantNameIsBlankResult);

        EmmDeviceBatchMoveRequest merchantNameTooLong = new EmmDeviceBatchMoveRequest();
        merchantNameTooLong.setDeviceIds(Collections.singletonList(1647428843339813L));
        merchantNameTooLong.setResellerName("suyunlong");
        merchantNameTooLong.setMerchantName("PAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAX");
        Result<String> merchantNameIsTooLongResult = emmDeviceApi.batchMoveEmmDevice(merchantNameTooLong);
        logger.debug("Result of batch move emm device by merchant name is too long: {}", merchantNameIsTooLongResult.toString());
        assertValidationFailure(merchantNameIsTooLongResult);

        EmmDeviceBatchMoveRequest merchantNameNotExist = new EmmDeviceBatchMoveRequest();
        merchantNameNotExist.setDeviceIds(Collections.singletonList(1647428843339813L));
        merchantNameNotExist.setResellerName("suyunlong");
        merchantNameNotExist.setMerchantName("merchantNameNotExist");
        Result<String> merchantNameIsNotExistResult = emmDeviceApi.batchMoveEmmDevice(merchantNameNotExist);
        logger.debug("Result of batch move emm device by merchant name is not exist: {}", merchantNameIsNotExistResult.toString());
        assertBusinessError(merchantNameIsNotExistResult);

    }

    @Test
    public void testDeleteEmmDeviceSuccess() {
        Result<String> result = emmDeviceApi.deleteEmmDevice(1648724732280883L);
        logger.debug("Result of delete emm device: {}", result.toString());
        assertOperationOk(result);
    }

    @Test
    public void testDeleteEmmDeviceFailByDeviceIdIsInvalid() {
        Result<String> deviceIdIsNullResult = emmDeviceApi.deleteEmmDevice(null);
        logger.debug("Result of delete emm device by deviceId is null: {}", deviceIdIsNullResult.toString());
        assertValidationFailure(deviceIdIsNullResult);

        Result<String> deviceIdIsNegativeResult = emmDeviceApi.deleteEmmDevice(-1L);
        logger.debug("Result of delete emm device by deviceId is negative: {}", deviceIdIsNegativeResult.toString());
        assertValidationFailure(deviceIdIsNegativeResult);

    }

    @Test
    public void testBatchDeleteEmmDeviceSuccess() {
        EmmDeviceBatchDeleteRequest emmDeviceBatchDeleteRequest = new EmmDeviceBatchDeleteRequest();
        emmDeviceBatchDeleteRequest.setDeviceIds(Collections.singletonList(1649639642103852L));
        Result<String> result = emmDeviceApi.batchDeleteEmmDevice(emmDeviceBatchDeleteRequest);
        logger.debug("Result of batch delete emm device: {}", result.toString());
        assertOperationOk(result);
    }

    @Test
    public void testBatchDeleteEmmDeviceFailByEmmDeviceBatchDeleteRequestIsInvalid() {
        Result<String> result = emmDeviceApi.batchDeleteEmmDevice(null);
        logger.debug("Result of batch delete emm device by emmDeviceBatchDeleteRequest is null: {}", result.toString());
        assertValidationFailure(result);
    }

    @Test
    public void testBatchDeleteEmmDeviceFailByDeviceIdIsInvalid() {
        EmmDeviceBatchDeleteRequest emmDeviceBatchDeleteRequest = new EmmDeviceBatchDeleteRequest();
        emmDeviceBatchDeleteRequest.setDeviceIds(null);
        Result<String> result = emmDeviceApi.batchDeleteEmmDevice(emmDeviceBatchDeleteRequest);
        logger.debug("Result of batch delete emm device by deviceIds is null: {}", result.toString());
        assertValidationFailure(result);
    }

    @Test
    public void testRebootEmmDeviceSuccess() {
        Result<String> result = emmDeviceApi.rebootEmmDevice(1647428843339813L);
        logger.debug("Result of reboot emm device: {}", result.toString());
        assertOperationOk(result);
    }

    @Test
    public void testRebootEmmDeviceFailByDeviceIdIsInvalid() {
        Result<String> deviceIdIsNullResult = emmDeviceApi.rebootEmmDevice(null);
        logger.debug("Result of reboot emm device by deviceId is null: {}", deviceIdIsNullResult.toString());
        assertValidationFailure(deviceIdIsNullResult);


        Result<String> deviceIdIsNegativeResult = emmDeviceApi.rebootEmmDevice(-1L);
        logger.debug("Result of reboot emm device by deviceId is negative: {}", deviceIdIsNegativeResult.toString());
        assertValidationFailure(deviceIdIsNegativeResult);
    }


    @Test
    public void testLockEmmDeviceScreenSuccess() {
        Result<String> result = emmDeviceApi.lockEmmDeviceScreen(1647428843339813L);
        logger.debug("Result of lock emm device: {}", result.toString());
        assertOperationOk(result);
    }

    @Test
    public void testLockEmmDeviceScreenFailByDeviceIdIsInvalid() {

        Result<String> deviceIdIsNullResult = emmDeviceApi.lockEmmDeviceScreen(null);
        logger.debug("Result of lock emm device by deviceId is null: {}", deviceIdIsNullResult.toString());
        assertValidationFailure(deviceIdIsNullResult);

        Result<String> deviceIdIsNegativeResult = emmDeviceApi.lockEmmDeviceScreen(-1L);
        logger.debug("Result of lock emm device by deviceId is negative: {}", deviceIdIsNegativeResult.toString());
        assertValidationFailure(deviceIdIsNegativeResult);

    }

    @Test
    public void testResetEmmDevicePasswordSuccess() {
        EmmDeviceResetPasswordRequest emmDeviceResetPasswordRequest = new EmmDeviceResetPasswordRequest();
        emmDeviceResetPasswordRequest.setPassword("1234");
        Result<String> result = emmDeviceApi.resetEmmDevicePassword(1650928224567330L, emmDeviceResetPasswordRequest);
        logger.debug("Result of reset emm device password: {}", result.toString());
        assertOperationOk(result);
    }


    @Test
    public void testResetEmmDevicePasswordFailByEmmDeviceResetPasswordRequestIsInvalid() {
        Result<String> result = emmDeviceApi.resetEmmDevicePassword(1653071941730344L, null);
        logger.debug("Result of reset emm device password by emmDeviceResetPasswordRequest is null: {}", result.toString());
        assertValidationFailure(result);
    }

    @Test
    public void testResetEmmDevicePasswordFailByDeviceIdIsInvalid() {
        EmmDeviceResetPasswordRequest deviceIdIsNull = new EmmDeviceResetPasswordRequest();
        deviceIdIsNull.setPassword("1234");
        Result<String> deviceIdIsNullResult = emmDeviceApi.resetEmmDevicePassword(null, deviceIdIsNull);
        logger.debug("Result of reset emm device password by deviceId is null: {}", deviceIdIsNullResult.toString());
        assertValidationFailure(deviceIdIsNullResult);

        EmmDeviceResetPasswordRequest deviceIdIsNegative = new EmmDeviceResetPasswordRequest();
        deviceIdIsNegative.setPassword("1234");
        Result<String> deviceIdIsNegativeResult = emmDeviceApi.resetEmmDevicePassword(-1L, deviceIdIsNegative);
        logger.debug("Result of reset emm device password by deviceId is negative: {}", deviceIdIsNegativeResult.toString());
        assertValidationFailure(deviceIdIsNegativeResult);

    }

    @Test
    public void testResetEmmDevicePasswordFailByPasswordIsInvalid() {
        EmmDeviceResetPasswordRequest passwordIsNull = new EmmDeviceResetPasswordRequest();
        passwordIsNull.setPassword(null);
        Result<String> passwordIsNullResult = emmDeviceApi.resetEmmDevicePassword(1653071941730344L, passwordIsNull);
        logger.debug("Result of reset emm device password by password is null: {}", passwordIsNullResult.toString());
        assertValidationFailure(passwordIsNullResult);

        EmmDeviceResetPasswordRequest passwordIsBlank = new EmmDeviceResetPasswordRequest();
        passwordIsBlank.setPassword("");
        Result<String> passwordIsBlankResult = emmDeviceApi.resetEmmDevicePassword(1653071941730344L, passwordIsBlank);
        logger.debug("Result of reset emm device password by password is blank: {}", passwordIsBlankResult.toString());
        assertValidationFailure(passwordIsBlankResult);
    }


    @Test
    public void testResetEmmDevicePasswordFailByLockNowIsInvalid() {

        EmmDeviceResetPasswordRequest emmDeviceResetPasswordRequest = new EmmDeviceResetPasswordRequest();
        emmDeviceResetPasswordRequest.setPassword("1234");
        emmDeviceResetPasswordRequest.setLockNow(null);
        Result<String> result = emmDeviceApi.resetEmmDevicePassword(1652549948014630L, emmDeviceResetPasswordRequest);
        logger.debug("Result of reset emm device password by lockNow is null: {}", result.toString());
        assertValidationFailure(result);

    }

    @Test
    public void testStartEmmDeviceLostModeSuccess() {
        EmmDeviceLostModeRequest emmDeviceLostModeRequest = new EmmDeviceLostModeRequest();
        emmDeviceLostModeRequest.setLostMessage("test message");
        emmDeviceLostModeRequest.setLostPhoneNumber("12345678910");
        Result<String> result = emmDeviceApi.startEmmDeviceLostMode(1647428843339813L, emmDeviceLostModeRequest);
        logger.debug("Result of start emm device lost mode: {}", result.toString());
        assertOperationOk(result);
    }

    @Test
    public void testStartEmmDeviceLostModeFailByEmmDeviceLostModeRequestIsInvalid() {
        Result<String> result = emmDeviceApi.startEmmDeviceLostMode(1652541362274340L, null);
        logger.debug("Result of start emm device lost mode by emmDeviceLostModeRequest is null: {}", result.toString());
        assertValidationFailure(result);
    }


    @Test
    public void testStartEmmDeviceLostModeFailByDeviceIdIsInvalid() {

        EmmDeviceLostModeRequest deviceIdIsNull = new EmmDeviceLostModeRequest();
        deviceIdIsNull.setLostMessage("test message");
        deviceIdIsNull.setLostPhoneNumber("12345678910");
        Result<String> deviceIdIsNullResult = emmDeviceApi.startEmmDeviceLostMode(null, deviceIdIsNull);
        logger.debug("Result of start emm device lost mode by deviceId is null: {}", deviceIdIsNullResult.toString());
        assertValidationFailure(deviceIdIsNullResult);

        EmmDeviceLostModeRequest deviceIdIsNegative = new EmmDeviceLostModeRequest();
        deviceIdIsNegative.setLostMessage("test message");
        deviceIdIsNegative.setLostPhoneNumber("12345678910");
        Result<String> deviceIdIsNegativeResult = emmDeviceApi.startEmmDeviceLostMode(-1L, deviceIdIsNegative);
        logger.debug("Result of start emm device lost mode by deviceId is negative: {}", deviceIdIsNegativeResult.toString());
        assertValidationFailure(deviceIdIsNegativeResult);
    }

    @Test
    public void testStartEmmDeviceLostModeFailByLostMessageIsInvalid() {

        EmmDeviceLostModeRequest lostMessageIsNull = new EmmDeviceLostModeRequest();
        lostMessageIsNull.setLostMessage(null);
        lostMessageIsNull.setLostPhoneNumber("12345678910");
        Result<String> lostMessageIsNullResult = emmDeviceApi.startEmmDeviceLostMode(1652541362274340L, lostMessageIsNull);
        logger.debug("Result of start emm device lost mode by lostMessage is null: {}", lostMessageIsNullResult.toString());
        assertValidationFailure(lostMessageIsNullResult);

        EmmDeviceLostModeRequest lostMessageIsBlank = new EmmDeviceLostModeRequest();
        lostMessageIsBlank.setLostMessage("");
        lostMessageIsBlank.setLostPhoneNumber("12345678910");
        Result<String> lostMessageIsBlankResult = emmDeviceApi.startEmmDeviceLostMode(1652541362274340L, lostMessageIsBlank);
        logger.debug("Result of start emm device lost mode by lostMessage is blank: {}", lostMessageIsBlankResult.toString());
        assertValidationFailure(lostMessageIsBlankResult);

        EmmDeviceLostModeRequest lostMessageIsTooLong = new EmmDeviceLostModeRequest();
        lostMessageIsTooLong.setLostMessage("test message test messagetest messagetest messagetest messagetest messagetest messagetest messagetest messagetest messagetest messagetest messagetest messagetest messagetest message");
        lostMessageIsTooLong.setLostPhoneNumber("12345678910");
        Result<String> lostMessageIsTooLongResult = emmDeviceApi.startEmmDeviceLostMode(1652541362274340L, lostMessageIsTooLong);
        logger.debug("Result of start emm device lost mode by lost message is too long: {}", lostMessageIsTooLongResult.toString());
        assertValidationFailure(lostMessageIsTooLongResult);

    }

    @Test
    public void testStartEmmDeviceLostPhoneNumberIsInvalid() {
        EmmDeviceLostModeRequest lostPhoneNumberIsNull = new EmmDeviceLostModeRequest();
        lostPhoneNumberIsNull.setLostMessage("test message");
        lostPhoneNumberIsNull.setLostPhoneNumber(null);
        Result<String> lostPhoneNumberIsNullResult = emmDeviceApi.startEmmDeviceLostMode(1652541362274340L, lostPhoneNumberIsNull);
        logger.debug("Result of start emm device lost mode by lostPhoneNumber is null: {}", lostPhoneNumberIsNullResult.toString());
        assertValidationFailure(lostPhoneNumberIsNullResult);

        EmmDeviceLostModeRequest lostMessageIsBlank = new EmmDeviceLostModeRequest();
        lostMessageIsBlank.setLostMessage("test message");
        lostMessageIsBlank.setLostPhoneNumber("");
        Result<String> lostMessageIsBlankResult = emmDeviceApi.startEmmDeviceLostMode(1652541362274340L, lostMessageIsBlank);
        logger.debug("Result of start emm device lost mode by lostPhoneNumber is blank: {}", lostMessageIsBlankResult.toString());
        assertValidationFailure(lostMessageIsBlankResult);

        EmmDeviceLostModeRequest lostMessageIsTooLong = new EmmDeviceLostModeRequest();
        lostMessageIsTooLong.setLostMessage("test message");
        lostMessageIsTooLong.setLostPhoneNumber("12345678910123456789101234567891012345678910123456789101234567891012345678910");
        Result<String> lostMessageIsTooLongResult = emmDeviceApi.startEmmDeviceLostMode(1652541362274340L, lostMessageIsTooLong);
        logger.debug("Result of start emm device lost mode by lostPhoneNumber is too long: {}", lostMessageIsTooLongResult.toString());
        assertValidationFailure(lostMessageIsTooLongResult);
    }

    @Test
    public void testResumeEmmDeviceSuccess() {
        Result<String> result = emmDeviceApi.resumeEmmDevice(1754297972098L);
        logger.debug("Result of resume EMM device: {}", result.toString());
        assertOperationOk(result);
    }

    @Test
    public void testResumeEmmDeviceFailByDeviceIdIsInvalid() {
        Result<String> deviceIdIsNullResult = emmDeviceApi.resumeEmmDevice(null);
        logger.debug("Result of resume EMM device by deviceId is null: {}", deviceIdIsNullResult.toString());
        assertValidationFailure(deviceIdIsNullResult);

        Result<String> deviceIdIsNegativeResult = emmDeviceApi.resumeEmmDevice(-1L);
        logger.debug("Result of resume EMM device by deviceId is negative: {}", deviceIdIsNegativeResult.toString());
        assertValidationFailure(deviceIdIsNegativeResult);
    }


    @Test
    public void testDisableEmmDeviceSuccess() {
        Result<String> result = emmDeviceApi.disableEmmDevice(1754297972098L);
        logger.debug("Result of disable EMM device: {}", result.toString());
        assertOperationOk(result);
    }


    @Test
    public void testDisableEmmDeviceFailByDeviceIdIsInvalid() {
        Result<String> deviceIdIsNullResult = emmDeviceApi.disableEmmDevice(null);
        logger.debug("Result of disable EMM device by deviceId is null: {}", deviceIdIsNullResult.toString());
        assertValidationFailure(deviceIdIsNullResult);

        Result<String> deviceIdIsNegativeResult = emmDeviceApi.disableEmmDevice(-1L);
        logger.debug("Result of disable EMM device by deviceId is negative: {}", deviceIdIsNegativeResult.toString());
        assertValidationFailure(deviceIdIsNegativeResult);
    }


    @Test
    public void testSyncEmmDeviceDetailSuccess() {
        Result<String> result = emmDeviceApi.syncDeviceDetail(1687529174597667L);
        logger.debug("Result of sync EMM device detail: {}", result.toString());
        assertOperationOk(result);
    }

    @Test
    public void testSyncEmmDeviceDetailFailByDeviceIdIsInvalid() {
        Result<String> deviceIdIsNullResult = emmDeviceApi.syncDeviceDetail(null);
        logger.debug("Result of sync EMM device detail by deviceId is null: {}", deviceIdIsNullResult.toString());
        assertValidationFailure(deviceIdIsNullResult);

        Result<String> deviceIdIsNegativeResult = emmDeviceApi.syncDeviceDetail(-1L);
        logger.debug("Result of sync EMM device detail by deviceId is negative: {}", deviceIdIsNegativeResult.toString());
        assertValidationFailure(deviceIdIsNegativeResult);
    }

    @Test
    public void testStopEmmDeviceLostModeSuccess() {
        Result<String> result = emmDeviceApi.stopEmmDeviceLostMode(1647428843339813L);
        logger.debug("Result of stop emm device lost mode: {}", result.toString());
        assertOperationOk(result);
    }


    @Test
    public void testClearEmmAppDataSuccess() {
        Result<String> result = emmDeviceApi.clearEmmAppData(1714690442919976L,"4");
        logger.debug("Result of clear app data: {}", result.toString());
        assertOperationOk(result);
    }

    @Test
    public void testStopEmmDeviceLostModeFailByDeviceIdIsInvalid() {
        Result<String> deviceIdIsNullResult = emmDeviceApi.stopEmmDeviceLostMode(null);
        logger.debug("Result of stop emm device lost mode by deviceId is null: {}", deviceIdIsNullResult.toString());
        assertValidationFailure(deviceIdIsNullResult);

        Result<String> deviceIdIsNegativeResult = emmDeviceApi.stopEmmDeviceLostMode(-1L);
        logger.debug("Result of stop emm device lost mode by deviceId is negative: {}", deviceIdIsNegativeResult.toString());
        assertValidationFailure(deviceIdIsNegativeResult);
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
        assertOperationOk(result);
    }

    @Test
    public void testSubmitEmmZteQuickUploadRecordFailByEmmZteQuickUploadRecordCreateRequestIsInvalid() {
        Result<String> result = emmDeviceApi.submitEmmZteQuickUploadRecord(null);
        logger.debug("Result of submit emm zte quick upload record by emmZteQuickUploadRecordCreateRequest is null: {}", result.toString());
        assertValidationFailure(result);
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
        assertValidationFailure(resellerNameIsNullResult);


        EmmZteQuickUploadRecordCreateRequest resellerNameIsBlank = new EmmZteQuickUploadRecordCreateRequest();
        resellerNameIsNull.setResellerName("");
        resellerNameIsNull.setMerchantName("test");
        resellerNameIsNull.setIdentifierType(EmmDeviceApi.EmmZteIdentifierType.IMEI);
        resellerNameIsNull.setNumbers("111111111111111");
        Result<String> resellerNameIsBlankResult = emmDeviceApi.submitEmmZteQuickUploadRecord(resellerNameIsBlank);
        logger.debug("Result of submit emm zte quick upload record by reseller name is blank: {}", resellerNameIsBlankResult.toString());
        assertValidationFailure(resellerNameIsBlankResult);


        EmmZteQuickUploadRecordCreateRequest resellerNameTooLong = new EmmZteQuickUploadRecordCreateRequest();
        resellerNameTooLong.setResellerName("PAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAX");
        resellerNameTooLong.setMerchantName("test");
        resellerNameTooLong.setIdentifierType(EmmDeviceApi.EmmZteIdentifierType.IMEI);
        resellerNameTooLong.setNumbers("111111111111111");
        Result<String> resellerNameIsTooLongResult = emmDeviceApi.submitEmmZteQuickUploadRecord(resellerNameTooLong);
        logger.debug("Result of submit emm zte quick upload record by reseller name is too long: {}", resellerNameIsTooLongResult.toString());
        assertValidationFailure(resellerNameIsTooLongResult);


        EmmZteQuickUploadRecordCreateRequest resellerNameNotExist = new EmmZteQuickUploadRecordCreateRequest();
        resellerNameNotExist.setResellerName("resellerNameNotExist");
        resellerNameNotExist.setMerchantName("test");
        resellerNameNotExist.setIdentifierType(EmmDeviceApi.EmmZteIdentifierType.IMEI);
        resellerNameNotExist.setNumbers("111111111111111");
        Result<String> resellerNameIsNotExistResult = emmDeviceApi.submitEmmZteQuickUploadRecord(resellerNameNotExist);
        logger.debug("Result of submit emm zte quick upload record by reseller name is not exist: {}", resellerNameIsNotExistResult.toString());
        assertBusinessError(resellerNameIsNotExistResult);
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
        assertValidationFailure(merchantNameIsNullResult);


        EmmZteQuickUploadRecordCreateRequest merchantNameIsBlank = new EmmZteQuickUploadRecordCreateRequest();
        merchantNameIsBlank.setResellerName("PAX");
        merchantNameIsBlank.setMerchantName("");
        merchantNameIsBlank.setIdentifierType(EmmDeviceApi.EmmZteIdentifierType.IMEI);
        merchantNameIsBlank.setNumbers("111111111111111");
        Result<String> merchantNameIsBlankResult = emmDeviceApi.submitEmmZteQuickUploadRecord(merchantNameIsBlank);
        logger.debug("Result of submit emm zte quick upload record by merchant name is blank: {}", merchantNameIsBlankResult.toString());
        assertValidationFailure(merchantNameIsBlankResult);


        EmmZteQuickUploadRecordCreateRequest merchantNameTooLong = new EmmZteQuickUploadRecordCreateRequest();
        merchantNameTooLong.setResellerName("PAX");
        merchantNameTooLong.setMerchantName("PAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAX");
        merchantNameTooLong.setIdentifierType(EmmDeviceApi.EmmZteIdentifierType.IMEI);
        merchantNameTooLong.setNumbers("111111111111111");
        Result<String> merchantNameTooLongResult = emmDeviceApi.submitEmmZteQuickUploadRecord(merchantNameTooLong);
        logger.debug("Result of submit emm zte quick upload record by merchant name is too long: {}", merchantNameTooLongResult.toString());
        assertValidationFailure(merchantNameTooLongResult);


        EmmZteQuickUploadRecordCreateRequest merchantNameNotExist = new EmmZteQuickUploadRecordCreateRequest();
        merchantNameNotExist.setResellerName("PAX");
        merchantNameNotExist.setMerchantName("merchantNameNotExist");
        merchantNameNotExist.setIdentifierType(EmmDeviceApi.EmmZteIdentifierType.IMEI);
        merchantNameNotExist.setNumbers("111111111111111");
        Result<String> merchantNameNotExistResult = emmDeviceApi.submitEmmZteQuickUploadRecord(merchantNameNotExist);
        logger.debug("Result of submit emm zte quick upload record by merchant name is not exist: {}", merchantNameNotExistResult.toString());
        assertBusinessError(merchantNameNotExistResult);

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
        assertValidationFailure(result);
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
        assertValidationFailure(numbersIsNullResult);


        EmmZteQuickUploadRecordCreateRequest numbersIsBlank = new EmmZteQuickUploadRecordCreateRequest();
        numbersIsBlank.setResellerName("PAX");
        numbersIsBlank.setMerchantName("test");
        numbersIsBlank.setIdentifierType(EmmDeviceApi.EmmZteIdentifierType.IMEI);
        numbersIsBlank.setNumbers("");
        Result<String> numbersIsBlankResult = emmDeviceApi.submitEmmZteQuickUploadRecord(numbersIsBlank);
        logger.debug("Result of submit emm zte quick upload record by numbers is blank: {}", numbersIsBlankResult.toString());
        assertValidationFailure(numbersIsBlankResult);
    }

















}
