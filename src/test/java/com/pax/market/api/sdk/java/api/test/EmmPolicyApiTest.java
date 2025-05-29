package com.pax.market.api.sdk.java.api.test;

import com.google.common.collect.Lists;
import com.pax.market.api.sdk.java.api.base.dto.Result;
import com.pax.market.api.sdk.java.api.emm.emmPolicy.EmmPolicyApi;
import com.pax.market.api.sdk.java.api.emm.emmPolicy.dto.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EmmPolicyApiTest {

    private static final Logger logger = LoggerFactory.getLogger(EmmPolicyApiTest.class.getSimpleName());

    private EmmPolicyApi emmPolicyApi;

    @Before
    public void init() {
        emmPolicyApi = new EmmPolicyApi(TestConstants.API_BASE_URL, TestConstants.API_KEY, TestConstants.API_SECRET);
    }

    @Test
    public void testGetResellerEmmPolicySuccess() {
        Result<EmmPolicyDTO> result = emmPolicyApi.getResellerEmmPolicy("PAX");
        logger.debug("Result of get reseller emm policy: {}", result.toString());
        Assert.assertEquals(0, result.getBusinessCode());
    }

    @Test
    public void testGetResellerEmmPolicyFailByResellerNameIsInvalid() {

        Result<EmmPolicyDTO> resellerNameIsNullResult = emmPolicyApi.getResellerEmmPolicy(null);
        logger.debug("Result of get reseller emm policy by reseller name is null: {}", resellerNameIsNullResult.toString());
        Assert.assertEquals(-1, resellerNameIsNullResult.getBusinessCode());

        Result<EmmPolicyDTO> resellerNameIsBlankResult = emmPolicyApi.getResellerEmmPolicy("");
        logger.debug("Result of get reseller emm policy by reseller name is blank: {}", resellerNameIsBlankResult.toString());
        Assert.assertEquals(-1, resellerNameIsBlankResult.getBusinessCode());

        Result<EmmPolicyDTO> resellerNameIsTooLongResult = emmPolicyApi.getResellerEmmPolicy("PAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAX");
        logger.debug("Result of get reseller emm policy by reseller name is too long: {}", resellerNameIsTooLongResult.toString());
        Assert.assertEquals(-1, resellerNameIsTooLongResult.getBusinessCode());

        Result<EmmPolicyDTO> resellerNameNotExist = emmPolicyApi.getResellerEmmPolicy("resellerNameNotExist");
        logger.debug("Result of get reseller emm policy by reseller name is not exist: {}", resellerNameNotExist.toString());
        Assert.assertEquals(1759, resellerNameNotExist.getBusinessCode());

    }

    @Test
    public void testCreateResellerEmmPolicySuccess() {
        ResellerEmmPolicyCreateRequest request = new ResellerEmmPolicyCreateRequest();
        request.setResellerName("PAX");
        request.setInheritFlag(Boolean.FALSE);

        PolicyUpdatedContentDTO policyUpdatedContentDTO = new PolicyUpdatedContentDTO();
        policyUpdatedContentDTO.setAdjustVolumeDisabled(Boolean.TRUE);
        policyUpdatedContentDTO.setEnableRemoteControl(Boolean.TRUE);

        PolicyUpdatedContentDTO.ApplicationPolicy applicationPolicy = new PolicyUpdatedContentDTO.ApplicationPolicy();
        applicationPolicy.setPackageName("com.zolon.signrotatetest.com.zolon.signrotatetest");
        applicationPolicy.setAutoUpdateMode("AUTO_UPDATE_DEFAULT");
        applicationPolicy.setDefaultPermissionPolicy("PROMPT");
        applicationPolicy.setInstallPriority("DEFAULT");
        applicationPolicy.setAccessibleTrackId(1681160455323739L);
        applicationPolicy.setInstallType("AVAILABLE");
        policyUpdatedContentDTO.setApplications(Lists.newArrayList(applicationPolicy));
        request.setContentInfo(policyUpdatedContentDTO);

        LockedPolicyUpdateDTO lockedPolicyUpdateDTO = new LockedPolicyUpdateDTO();
        lockedPolicyUpdateDTO.setKey("adjustVolumeDisabled");
        lockedPolicyUpdateDTO.setLockFlag(Boolean.TRUE);
        request.setLockedPolicyList(Lists.newArrayList(lockedPolicyUpdateDTO));

        Result<String> result = emmPolicyApi.createResellerEmmPolicy(request);
        logger.debug("Result of create reseller emm policy: {}", result.toString());
        Assert.assertEquals(0, result.getBusinessCode());
    }

    @Test
    public void testCreateResellerEmmPolicyFailByResellerEmmPolicyCreateRequestIsInvalid() {
        Result<String> result = emmPolicyApi.createResellerEmmPolicy(null);
        logger.debug("Result of create reseller emm policy by resellerEmmPolicyCreateRequest is null: {}", result.toString());
        Assert.assertEquals(-1, result.getBusinessCode());
    }

    @Test
    public void testCreateResellerEmmPolicyFailByResellerNameIsInvalid() {
        ResellerEmmPolicyCreateRequest resellerNameIsNull = new ResellerEmmPolicyCreateRequest();
        resellerNameIsNull.setResellerName(null);
        PolicyUpdatedContentDTO resellerNameIsNullPolicyUpdatedContentDTO = new PolicyUpdatedContentDTO();
        resellerNameIsNullPolicyUpdatedContentDTO.setAdjustVolumeDisabled(Boolean.TRUE);
        resellerNameIsNull.setContentInfo(resellerNameIsNullPolicyUpdatedContentDTO);
        resellerNameIsNull.setInheritFlag(Boolean.FALSE);
        Result<String> resellerNameIsNullResult = emmPolicyApi.createResellerEmmPolicy(resellerNameIsNull);
        logger.debug("Result of create reseller emm policy by reseller name is null: {}", resellerNameIsNullResult.toString());
        Assert.assertEquals(-1, resellerNameIsNullResult.getBusinessCode());

        ResellerEmmPolicyCreateRequest resellerNameIsBlank = new ResellerEmmPolicyCreateRequest();
        resellerNameIsBlank.setResellerName("");
        PolicyUpdatedContentDTO resellerNameIsBlankPolicyUpdatedContentDTO = new PolicyUpdatedContentDTO();
        resellerNameIsBlankPolicyUpdatedContentDTO.setAdjustVolumeDisabled(Boolean.TRUE);
        resellerNameIsBlank.setContentInfo(resellerNameIsBlankPolicyUpdatedContentDTO);
        resellerNameIsBlank.setInheritFlag(Boolean.FALSE);
        Result<String> resellerNameIsBlankResult = emmPolicyApi.createResellerEmmPolicy(resellerNameIsBlank);
        logger.debug("Result of create reseller emm policy by reseller name is blank: {}", resellerNameIsBlankResult.toString());
        Assert.assertEquals(-1, resellerNameIsBlankResult.getBusinessCode());

        ResellerEmmPolicyCreateRequest resellerNameIsTooLong = new ResellerEmmPolicyCreateRequest();
        resellerNameIsTooLong.setResellerName("PAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAX");
        PolicyUpdatedContentDTO resellerNameIsTooLongPolicyUpdatedContentDTO = new PolicyUpdatedContentDTO();
        resellerNameIsBlankPolicyUpdatedContentDTO.setAdjustVolumeDisabled(Boolean.TRUE);
        resellerNameIsTooLong.setContentInfo(resellerNameIsTooLongPolicyUpdatedContentDTO);
        resellerNameIsTooLong.setInheritFlag(Boolean.FALSE);
        Result<String> resellerNameIsTooLongResult = emmPolicyApi.createResellerEmmPolicy(resellerNameIsTooLong);
        logger.debug("Result of create reseller emm policy by reseller name is too long: {}", resellerNameIsTooLongResult.toString());
        Assert.assertEquals(-1, resellerNameIsTooLongResult.getBusinessCode());

        ResellerEmmPolicyCreateRequest resellerNameNotExist = new ResellerEmmPolicyCreateRequest();
        resellerNameNotExist.setResellerName("resellerNameNotExist");
        PolicyUpdatedContentDTO resellerNameNotExistPolicyUpdatedContentDTO = new PolicyUpdatedContentDTO();
        resellerNameNotExistPolicyUpdatedContentDTO.setAdjustVolumeDisabled(Boolean.TRUE);
        resellerNameNotExist.setContentInfo(resellerNameNotExistPolicyUpdatedContentDTO);
        resellerNameNotExist.setInheritFlag(Boolean.FALSE);
        Result<String> resellerNameNotExistResult = emmPolicyApi.createResellerEmmPolicy(resellerNameNotExist);
        logger.debug("Result of create reseller emm policy by reseller name is not exist: {}", resellerNameNotExistResult.toString());
        Assert.assertEquals(1759, resellerNameNotExistResult.getBusinessCode());
    }

    @Test
    public void testCreateResellerEmmPolicyFailByContentInfoIsInvalid() {
        ResellerEmmPolicyCreateRequest request = new ResellerEmmPolicyCreateRequest();
        request.setResellerName("PAX");
        request.setContentInfo(null);
        request.setInheritFlag(Boolean.FALSE);
        Result<String> result = emmPolicyApi.createResellerEmmPolicy(request);
        logger.debug("Result of create reseller emm policy by contentInfo is null: {}", result.toString());
        Assert.assertEquals(-1, result.getBusinessCode());
    }

    @Test
    public void testCreateResellerEmmPolicyFailByInheritFlagIsInvalid() {
        ResellerEmmPolicyCreateRequest request = new ResellerEmmPolicyCreateRequest();
        request.setResellerName("PAX");
        PolicyUpdatedContentDTO policyUpdatedContentDTO = new PolicyUpdatedContentDTO();
        policyUpdatedContentDTO.setAdjustVolumeDisabled(Boolean.TRUE);
        request.setContentInfo(policyUpdatedContentDTO);
        request.setInheritFlag(null);
        Result<String> result = emmPolicyApi.createResellerEmmPolicy(request);
        logger.debug("Result of create reseller emm policy by inheritFlag is null: {}", result.toString());
        Assert.assertEquals(-1, result.getBusinessCode());
    }

    @Test
    public void testCreateResellerEmmPolicyFailByLockedPolicyKeyIsInvalid() {
        ResellerEmmPolicyCreateRequest request = new ResellerEmmPolicyCreateRequest();
        request.setResellerName("PAX");

        PolicyUpdatedContentDTO policyUpdatedContentDTO = new PolicyUpdatedContentDTO();
        policyUpdatedContentDTO.setAdjustVolumeDisabled(Boolean.TRUE);
        request.setContentInfo(policyUpdatedContentDTO);
        request.setInheritFlag(Boolean.FALSE);

        LockedPolicyUpdateDTO lockedPolicyUpdateDTO = new LockedPolicyUpdateDTO();
        lockedPolicyUpdateDTO.setKey(null);
        lockedPolicyUpdateDTO.setLockFlag(Boolean.TRUE);
        request.setLockedPolicyList(Lists.newArrayList(lockedPolicyUpdateDTO));
        Result<String> lockedPolicyKeyIsInvalidResult = emmPolicyApi.createResellerEmmPolicy(request);
        logger.debug("Result of create reseller emm policy by locked policy key is invalid: {}", lockedPolicyKeyIsInvalidResult.toString());
        Assert.assertEquals(61663, lockedPolicyKeyIsInvalidResult.getBusinessCode());
    }


    @Test
    public void testGetMerchantEmmPolicySuccess() {
        Result<EmmPolicyDTO> result = emmPolicyApi.getMerchantEmmPolicy("PAX","test");
        logger.debug("Result of get merchant emm policy: {}", result.toString());
        Assert.assertEquals(0, result.getBusinessCode());
    }

    @Test
    public void testGetMerchantEmmPolicyByResellerNameIsInvalid() {
        Result<EmmPolicyDTO> resellerNameIsNullResult = emmPolicyApi.getMerchantEmmPolicy(null,"test");
        logger.debug("Result of get merchant emm policy by reseller name is null: {}", resellerNameIsNullResult.toString());
        Assert.assertEquals(-1, resellerNameIsNullResult.getBusinessCode());

        Result<EmmPolicyDTO> resellerNameIsBlankResult = emmPolicyApi.getMerchantEmmPolicy("","test");
        logger.debug("Result of get merchant emm policy by reseller name is blank: {}", resellerNameIsBlankResult.toString());
        Assert.assertEquals(-1, resellerNameIsBlankResult.getBusinessCode());

        Result<EmmPolicyDTO> resellerNameIsTooLongResult = emmPolicyApi.getMerchantEmmPolicy("PAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAX","test");
        logger.debug("Result of get merchant emm policy by reseller name is too long: {}", resellerNameIsTooLongResult.toString());
        Assert.assertEquals(-1, resellerNameIsTooLongResult.getBusinessCode());

        Result<EmmPolicyDTO> resellerNameNotExist = emmPolicyApi.getMerchantEmmPolicy("resellerNameNotExist","test");
        logger.debug("Result of get merchant emm policy by reseller name is not exist: {}", resellerNameNotExist.toString());
        Assert.assertEquals(1759, resellerNameNotExist.getBusinessCode());
    }

    @Test
    public void testGetMerchantEmmPolicyByMerchantNameIsInvalid() {

        Result<EmmPolicyDTO> merchantNameIsNullResult = emmPolicyApi.getMerchantEmmPolicy("PAX",null);
        logger.debug("Result of get merchant emm policy by merchant name is null: {}", merchantNameIsNullResult.toString());
        Assert.assertEquals(-1, merchantNameIsNullResult.getBusinessCode());

        Result<EmmPolicyDTO> merchantNameIsBlankResult = emmPolicyApi.getMerchantEmmPolicy("PAX","");
        logger.debug("Result of get merchant emm policy by merchant name is blank: {}", merchantNameIsBlankResult.toString());
        Assert.assertEquals(-1, merchantNameIsBlankResult.getBusinessCode());

        Result<EmmPolicyDTO> merchantNameIsTooLongResult = emmPolicyApi.getMerchantEmmPolicy("PAX","PAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAX");
        logger.debug("Result of get merchant emm policy by merchant name is too long: {}", merchantNameIsTooLongResult.toString());
        Assert.assertEquals(-1, merchantNameIsTooLongResult.getBusinessCode());

        Result<EmmPolicyDTO> merchantNameNotExist = emmPolicyApi.getMerchantEmmPolicy("PAX","merchantNameNotExist");
        logger.debug("Result of get merchant emm policy by merchant name is not exist: {}", merchantNameNotExist.toString());
        Assert.assertEquals(1720, merchantNameNotExist.getBusinessCode());
    }

    @Test
    public void testCreateMerchantEmmPolicySuccess() {
        MerchantEmmPolicyCreateRequest request = new MerchantEmmPolicyCreateRequest();
        request.setResellerName("PAX");
        request.setMerchantName("test");

        PolicyUpdatedContentDTO policyUpdatedContentDTO = new PolicyUpdatedContentDTO();
        policyUpdatedContentDTO.setAdjustVolumeDisabled(Boolean.TRUE);
        policyUpdatedContentDTO.setEnableRemoteControl(Boolean.TRUE);
        request.setContentInfo(policyUpdatedContentDTO);
        request.setInheritFlag(Boolean.FALSE);

        PolicyUpdatedContentDTO.ApplicationPolicy applicationPolicy = new PolicyUpdatedContentDTO.ApplicationPolicy();
        applicationPolicy.setPackageName("com.zolon.signrotatetest.com.zolon.signrotatetest");
        applicationPolicy.setAutoUpdateMode("AUTO_UPDATE_DEFAULT");
        applicationPolicy.setDefaultPermissionPolicy("PROMPT");
        applicationPolicy.setInstallPriority("DEFAULT");
        applicationPolicy.setAccessibleTrackId(1681160455323739L);
        applicationPolicy.setInstallType("AVAILABLE");
        policyUpdatedContentDTO.setApplications(Lists.newArrayList(applicationPolicy));
        request.setContentInfo(policyUpdatedContentDTO);

        Result<String> result = emmPolicyApi.createMerchantEmmPolicy(request);
        logger.debug("Result of create merchant emm policy: {}", result.toString());
        Assert.assertEquals(0, result.getBusinessCode());
    }

    @Test
    public void testCreateMerchantEmmPolicyFailByMerchantEmmPolicyCreateRequestIsInvalid() {
        Result<String> result = emmPolicyApi.createMerchantEmmPolicy(null);
        logger.debug("Result of create merchant emm policy by merchantEmmPolicyCreateRequest is null: {}", result.toString());
        Assert.assertEquals(-1, result.getBusinessCode());
    }

    @Test
    public void testCreateMerchantEmmPolicyFailByResellerNameIsInvalid() {
        MerchantEmmPolicyCreateRequest resellerNameIsNull = new MerchantEmmPolicyCreateRequest();
        resellerNameIsNull.setResellerName(null);
        resellerNameIsNull.setMerchantName("test");
        PolicyUpdatedContentDTO resellerNameIsNullPolicyUpdatedContentDTO = new PolicyUpdatedContentDTO();
        resellerNameIsNullPolicyUpdatedContentDTO.setAdjustVolumeDisabled(Boolean.TRUE);
        resellerNameIsNull.setContentInfo(resellerNameIsNullPolicyUpdatedContentDTO);
        resellerNameIsNull.setInheritFlag(Boolean.FALSE);
        Result<String> resellerNameIsNullResult = emmPolicyApi.createMerchantEmmPolicy(resellerNameIsNull);
        logger.debug("Result of create merchant emm policy by reseller name is null: {}", resellerNameIsNullResult.toString());
        Assert.assertEquals(-1, resellerNameIsNullResult.getBusinessCode());

        MerchantEmmPolicyCreateRequest resellerNameIsBlank = new MerchantEmmPolicyCreateRequest();
        resellerNameIsBlank.setResellerName("");
        resellerNameIsBlank.setMerchantName("test");
        PolicyUpdatedContentDTO resellerNameIsBlankPolicyUpdatedContentDTO = new PolicyUpdatedContentDTO();
        resellerNameIsBlankPolicyUpdatedContentDTO.setAdjustVolumeDisabled(Boolean.TRUE);
        resellerNameIsBlank.setContentInfo(resellerNameIsBlankPolicyUpdatedContentDTO);
        resellerNameIsBlank.setInheritFlag(Boolean.FALSE);
        Result<String> resellerNameIsBlankResult = emmPolicyApi.createMerchantEmmPolicy(resellerNameIsBlank);
        logger.debug("Result of create merchant emm policy by reseller name is blank: {}", resellerNameIsBlankResult.toString());
        Assert.assertEquals(-1, resellerNameIsBlankResult.getBusinessCode());

        MerchantEmmPolicyCreateRequest resellerNameIsTooLong = new MerchantEmmPolicyCreateRequest();
        resellerNameIsTooLong.setResellerName("PAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAX");
        resellerNameIsTooLong.setMerchantName("test");
        PolicyUpdatedContentDTO resellerNameIsTooLongPolicyUpdatedContentDTO = new PolicyUpdatedContentDTO();
        resellerNameIsBlankPolicyUpdatedContentDTO.setAdjustVolumeDisabled(Boolean.TRUE);
        resellerNameIsTooLong.setContentInfo(resellerNameIsTooLongPolicyUpdatedContentDTO);
        resellerNameIsTooLong.setInheritFlag(Boolean.FALSE);
        Result<String> resellerNameIsTooLongResult = emmPolicyApi.createMerchantEmmPolicy(resellerNameIsTooLong);
        logger.debug("Result of create merchant emm policy by reseller name is too long: {}", resellerNameIsTooLongResult.toString());
        Assert.assertEquals(-1, resellerNameIsTooLongResult.getBusinessCode());

        MerchantEmmPolicyCreateRequest resellerNameNotExist = new MerchantEmmPolicyCreateRequest();
        resellerNameNotExist.setResellerName("resellerNameNotExist");
        resellerNameNotExist.setMerchantName("test");
        PolicyUpdatedContentDTO resellerNameNotExistPolicyUpdatedContentDTO = new PolicyUpdatedContentDTO();
        resellerNameNotExistPolicyUpdatedContentDTO.setAdjustVolumeDisabled(Boolean.TRUE);
        resellerNameNotExist.setContentInfo(resellerNameNotExistPolicyUpdatedContentDTO);
        resellerNameNotExist.setInheritFlag(Boolean.FALSE);
        Result<String> resellerNameNotExistResult = emmPolicyApi.createMerchantEmmPolicy(resellerNameNotExist);
        logger.debug("Result of create merchant emm policy by reseller name is not exist: {}", resellerNameNotExistResult.toString());
        Assert.assertEquals(1759, resellerNameNotExistResult.getBusinessCode());
    }

    @Test
    public void testCreateMerchantEmmPolicyFailByMerchantNameIsInvalid() {
        MerchantEmmPolicyCreateRequest merchantNameIsNull = new MerchantEmmPolicyCreateRequest();
        merchantNameIsNull.setResellerName("PAX");
        merchantNameIsNull.setMerchantName(null);
        PolicyUpdatedContentDTO merchantNameIsNullPolicyUpdatedContentDTO = new PolicyUpdatedContentDTO();
        merchantNameIsNullPolicyUpdatedContentDTO.setAdjustVolumeDisabled(Boolean.TRUE);
        merchantNameIsNull.setContentInfo(merchantNameIsNullPolicyUpdatedContentDTO);
        merchantNameIsNull.setInheritFlag(Boolean.FALSE);
        Result<String> merchantNameIsNullResult = emmPolicyApi.createMerchantEmmPolicy(merchantNameIsNull);
        logger.debug("Result of create merchant emm policy by merchant name is null: {}", merchantNameIsNullResult.toString());
        Assert.assertEquals(-1, merchantNameIsNullResult.getBusinessCode());

        MerchantEmmPolicyCreateRequest merchantNameIsBlank = new MerchantEmmPolicyCreateRequest();
        merchantNameIsBlank.setResellerName("PAX");
        merchantNameIsNull.setMerchantName("");
        PolicyUpdatedContentDTO merchantNameIsBlankPolicyUpdatedContentDTO = new PolicyUpdatedContentDTO();
        merchantNameIsBlankPolicyUpdatedContentDTO.setAdjustVolumeDisabled(Boolean.TRUE);
        merchantNameIsBlank.setContentInfo(merchantNameIsBlankPolicyUpdatedContentDTO);
        merchantNameIsBlank.setInheritFlag(Boolean.FALSE);
        Result<String> merchantNameIsBlankResult = emmPolicyApi.createMerchantEmmPolicy(merchantNameIsBlank);
        logger.debug("Result of create merchant emm policy by merchant name is blank: {}", merchantNameIsBlankResult.toString());
        Assert.assertEquals(-1, merchantNameIsBlankResult.getBusinessCode());

        MerchantEmmPolicyCreateRequest merchantNameIsTooLong = new MerchantEmmPolicyCreateRequest();
        merchantNameIsTooLong.setResellerName("PAX");
        merchantNameIsTooLong.setMerchantName("PAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAX");
        PolicyUpdatedContentDTO merchantNameIsTooLongPolicyUpdatedContentDTO = new PolicyUpdatedContentDTO();
        merchantNameIsTooLongPolicyUpdatedContentDTO.setAdjustVolumeDisabled(Boolean.TRUE);
        merchantNameIsTooLong.setContentInfo(merchantNameIsTooLongPolicyUpdatedContentDTO);
        merchantNameIsTooLong.setInheritFlag(Boolean.FALSE);
        Result<String> merchantNameIsTooLongResult = emmPolicyApi.createMerchantEmmPolicy(merchantNameIsTooLong);
        logger.debug("Result of create merchant emm policy by merchant name is too long: {}", merchantNameIsTooLongResult.toString());
        Assert.assertEquals(-1, merchantNameIsTooLongResult.getBusinessCode());

        MerchantEmmPolicyCreateRequest merchantNameIsNotExist = new MerchantEmmPolicyCreateRequest();
        merchantNameIsNotExist.setResellerName("PAX");
        merchantNameIsNotExist.setMerchantName("merchantNameIsNotExist");
        PolicyUpdatedContentDTO merchantNameIsNotExistPolicyUpdatedContentDTO = new PolicyUpdatedContentDTO();
        merchantNameIsNotExistPolicyUpdatedContentDTO.setAdjustVolumeDisabled(Boolean.TRUE);
        merchantNameIsNotExist.setContentInfo(merchantNameIsNotExistPolicyUpdatedContentDTO);
        merchantNameIsNotExist.setInheritFlag(Boolean.FALSE);
        Result<String> merchantNameIsNotExistResult = emmPolicyApi.createMerchantEmmPolicy(merchantNameIsNotExist);
        logger.debug("Result of create merchant emm policy by merchant name is not exist: {}", merchantNameIsNotExistResult.toString());
        Assert.assertEquals(1720, merchantNameIsNotExistResult.getBusinessCode());
    }

    @Test
    public void testCreateMerchantEmmPolicyFailByContentInfoIsInvalid() {
        MerchantEmmPolicyCreateRequest request = new MerchantEmmPolicyCreateRequest();
        request.setResellerName("PAX");
        request.setMerchantName("test");
        request.setContentInfo(null);
        request.setInheritFlag(Boolean.FALSE);
        Result<String> result = emmPolicyApi.createMerchantEmmPolicy(request);
        logger.debug("Result of create merchant emm policy by contentInfo is null: {}", result.toString());
        Assert.assertEquals(-1, result.getBusinessCode());
    }

    @Test
    public void testCreateMerchantEmmPolicyFailByInheritFlagIsInvalid() {
        MerchantEmmPolicyCreateRequest request = new MerchantEmmPolicyCreateRequest();
        request.setResellerName("PAX");
        request.setMerchantName("test");
        PolicyUpdatedContentDTO policyUpdatedContentDTO = new PolicyUpdatedContentDTO();
        policyUpdatedContentDTO.setAdjustVolumeDisabled(Boolean.TRUE);
        request.setContentInfo(policyUpdatedContentDTO);
        request.setInheritFlag(null);
        Result<String> result = emmPolicyApi.createMerchantEmmPolicy(request);
        logger.debug("Result of create merchant emm policy by inheritFlag is null: {}", result.toString());
        Assert.assertEquals(-1, result.getBusinessCode());
    }

    @Test
    public void testCreateMerchantEmmPolicyFailByLockInvalid() {
        MerchantEmmPolicyCreateRequest request = new MerchantEmmPolicyCreateRequest();
        request.setResellerName("PAX");
        request.setMerchantName("test");

        PolicyUpdatedContentDTO policyUpdatedContentDTO = new PolicyUpdatedContentDTO();
        policyUpdatedContentDTO.setAdjustVolumeDisabled(Boolean.TRUE);
        request.setContentInfo(policyUpdatedContentDTO);

        LockedPolicyUpdateDTO lockedPolicyUpdateDTO = new LockedPolicyUpdateDTO();
        lockedPolicyUpdateDTO.setKey("adjustVolumeDisabled");
        lockedPolicyUpdateDTO.setLockFlag(Boolean.TRUE);
        request.setLockedPolicyList(Lists.newArrayList(lockedPolicyUpdateDTO));

        Result<String> result = emmPolicyApi.createMerchantEmmPolicy(request);
        logger.debug("Result of create merchant emm policy by lock is invalid: {}", result.toString());
        Assert.assertEquals(-1, result.getBusinessCode());
    }






}
