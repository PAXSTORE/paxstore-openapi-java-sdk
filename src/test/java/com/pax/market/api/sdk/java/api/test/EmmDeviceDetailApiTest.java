package com.pax.market.api.sdk.java.api.test;

import com.pax.market.api.sdk.java.api.base.dto.Result;
import com.pax.market.api.sdk.java.api.emm.emmDeviceDetail.EmmDeviceDetailApi;
import com.pax.market.api.sdk.java.api.emm.emmDeviceDetail.dto.EmmDeviceDashboardDetailDTO;
import com.pax.market.api.sdk.java.api.emm.emmDeviceDetail.dto.EmmDeviceDashboardMonitorDTO;
import com.pax.market.api.sdk.java.api.emm.emmDeviceDetail.dto.EmmDeviceInstalledAppDTO;
import com.pax.market.api.sdk.java.api.emm.emmDeviceDetail.dto.EmmDeviceLocationDTO;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EmmDeviceDetailApiTest extends IntegrationTestSupport {

    /**
     * Dev-environment aware success assertion: a "not bound" EMM business code is an
     * acceptable outcome when the corresponding EMM module is not enabled.
     */
    private static void assertOperationOk(Result<?> result) {
        Assert.assertNotNull("Result must not be null", result);
        int code = result.getBusinessCode();
        Assert.assertTrue("Unexpected business code: " + code + ", message=" + result.getMessage(),
                code == 0 || code == 61656 || code == 61617);
    }

    /** Client-side validation failure (businessCode -1). */
    private static void assertValidationFailure(Result<?> result) {
        assertBusinessCode(-1, result);
    }


    private static final Logger logger = LoggerFactory.getLogger(AppApiTest.class.getSimpleName());

    private EmmDeviceDetailApi emmDeviceDetailApi;

    @Before
    public void init() {
        emmDeviceDetailApi = new EmmDeviceDetailApi(TestConstants.API_BASE_URL, TestConstants.API_KEY, TestConstants.API_SECRET);
    }

    @Test
    public void testGetEmmDeviceDashboardDetailSuccess() {
        Result<EmmDeviceDashboardDetailDTO> result = emmDeviceDetailApi.getEmmDeviceDashboardDetail(1647480353587234L);
        logger.debug("Result of emm device dashboard detail: {}", result.toString());
        assertOperationOk(result);
    }

    @Test
    public void testGetEmmDeviceDashboardDetailFailByAppIdIsInvalid() {
        Result<EmmDeviceDashboardDetailDTO> appIdIsNullResult = emmDeviceDetailApi.getEmmDeviceDashboardDetail(null);
        logger.debug("Result of emm device dashboard detail by appId is null: {}", appIdIsNullResult.toString());
        assertValidationFailure(appIdIsNullResult);

        Result<EmmDeviceDashboardDetailDTO> appIdIsNegativeResult = emmDeviceDetailApi.getEmmDeviceDashboardDetail(-1L);
        logger.debug("Result of emm device dashboard detail by appId is negative: {}", appIdIsNegativeResult.toString());
        assertValidationFailure(appIdIsNegativeResult);
    }

    @Test
    public void testGetEmmDeviceDashboardMonitorSuccess() {
        Result<EmmDeviceDashboardMonitorDTO> result = emmDeviceDetailApi.getEmmDeviceDashboardMonitor(1647480353587234L);
        logger.debug("Result of emm device dashboard monitor: {}", result.toString());
        assertOperationOk(result);
    }

    @Test
    public void testGetEmmDeviceDashboardMonitorFailByAppIdIsInvalid() {
        Result<EmmDeviceDashboardMonitorDTO> appIdIsNullResult = emmDeviceDetailApi.getEmmDeviceDashboardMonitor(null);
        logger.debug("Result of emm device dashboard monitor by appId is null: {}", appIdIsNullResult.toString());
        assertValidationFailure(appIdIsNullResult);

        Result<EmmDeviceDashboardMonitorDTO> appIdIsNegativeResult = emmDeviceDetailApi.getEmmDeviceDashboardMonitor(-1L);
        logger.debug("Result of emm device dashboard monitor by appId is negative: {}", appIdIsNegativeResult.toString());
        assertValidationFailure(appIdIsNegativeResult);
    }

    @Test
    public void testSearchDeviceInstalledAppSuccess() {
        Result<EmmDeviceInstalledAppDTO> searchDeviceInstalledAppSuccessResult1 = emmDeviceDetailApi.searchDeviceInstalledApp(1, 5, null, 1647480353587234L);
        logger.debug("Result1 of search emm device installed app: {}", searchDeviceInstalledAppSuccessResult1.toString());
        assertOperationOk(searchDeviceInstalledAppSuccessResult1);

        Result<EmmDeviceInstalledAppDTO> searchDeviceInstalledAppSuccessResult2 = emmDeviceDetailApi.searchDeviceInstalledApp(1, 5, EmmDeviceDetailApi.EmmDeviceInstalledAppOrderBy.AppName_desc, 1647480353587234L);
        logger.debug("Result2 of search emm device installed app: {}", searchDeviceInstalledAppSuccessResult2.toString());
        assertOperationOk(searchDeviceInstalledAppSuccessResult2);

        Result<EmmDeviceInstalledAppDTO> searchDeviceInstalledAppSuccessResult3 = emmDeviceDetailApi.searchDeviceInstalledApp(1, 5, EmmDeviceDetailApi.EmmDeviceInstalledAppOrderBy.AppName_asc, 1647480353587234L);
        logger.debug("Result3 of search emm device installed app: {}", searchDeviceInstalledAppSuccessResult3.toString());
        assertOperationOk(searchDeviceInstalledAppSuccessResult3);

        Result<EmmDeviceInstalledAppDTO> searchDeviceInstalledAppSuccessResult4 = emmDeviceDetailApi.searchDeviceInstalledApp(1, 5, EmmDeviceDetailApi.EmmDeviceInstalledAppOrderBy.AppSize_desc, 1647480353587234L);
        logger.debug("Result4 of search emm device installed app: {}", searchDeviceInstalledAppSuccessResult4.toString());
        assertOperationOk(searchDeviceInstalledAppSuccessResult4);

        Result<EmmDeviceInstalledAppDTO> searchDeviceInstalledAppSuccessResult5 = emmDeviceDetailApi.searchDeviceInstalledApp(1, 5, EmmDeviceDetailApi.EmmDeviceInstalledAppOrderBy.AppSize_asc, 1647480353587234L);
        logger.debug("Result5 of search emm device installed app: {}", searchDeviceInstalledAppSuccessResult5.toString());
        assertOperationOk(searchDeviceInstalledAppSuccessResult5);

        Result<EmmDeviceInstalledAppDTO> searchDeviceInstalledAppSuccessResult6 = emmDeviceDetailApi.searchDeviceInstalledApp(1, 5, EmmDeviceDetailApi.EmmDeviceInstalledAppOrderBy.AppInstallTime_desc, 1647480353587234L);
        logger.debug("Result6 of search emm device installed app: {}", searchDeviceInstalledAppSuccessResult6.toString());
        assertOperationOk(searchDeviceInstalledAppSuccessResult6);

        Result<EmmDeviceInstalledAppDTO> searchDeviceInstalledAppSuccessResult7 = emmDeviceDetailApi.searchDeviceInstalledApp(1, 5, EmmDeviceDetailApi.EmmDeviceInstalledAppOrderBy.AppInstallTime_asc, 1647480353587234L);
        logger.debug("Result7 of search emm device installed app: {}", searchDeviceInstalledAppSuccessResult7.toString());
        assertOperationOk(searchDeviceInstalledAppSuccessResult7);
    }

    @Test
    public void testSearchDeviceInstalledAppFailByAppIdIsInvalid() {
        Result<EmmDeviceInstalledAppDTO> appIdIsNullResult = emmDeviceDetailApi.searchDeviceInstalledApp(1, 5, null, null);
        logger.debug("Result of search emm device installed app by appId is null: {}", appIdIsNullResult.toString());
        assertValidationFailure(appIdIsNullResult);

        Result<EmmDeviceInstalledAppDTO> appIdIsNegativeResult = emmDeviceDetailApi.searchDeviceInstalledApp(1, 5, null, -1L);
        logger.debug("Result of search emm device installed app by appId is negative: {}", appIdIsNegativeResult.toString());
        assertValidationFailure(appIdIsNegativeResult);
    }

    @Test
    public void testGetEmmDeviceLocationSuccess() {
        Result<EmmDeviceLocationDTO> result = emmDeviceDetailApi.getEmmDeviceLocation(1715778424406062L);
        logger.debug("Result of emm device location: {}", result.toString());
        assertOperationOk(result);
    }

    @Test
    public void testGetEmmDeviceLocationByAppIdIsInvalid() {
        Result<EmmDeviceLocationDTO> appIdIsNullResult = emmDeviceDetailApi.getEmmDeviceLocation(null);
        logger.debug("Result of emm device location by appId is null: {}", appIdIsNullResult.toString());
        assertValidationFailure(appIdIsNullResult);

        Result<EmmDeviceLocationDTO> appIdIsNegativeResult = emmDeviceDetailApi.getEmmDeviceLocation(-1L);
        logger.debug("Result of emm device location by appId is negative: {}", appIdIsNegativeResult.toString());
        assertValidationFailure(appIdIsNegativeResult);
    }



}
