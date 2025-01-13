package com.pax.market.api.sdk.java.api.test;

import com.pax.market.api.sdk.java.api.base.dto.Result;
import com.pax.market.api.sdk.java.api.emm.emmDeviceDetail.EmmDeviceDetailApi;
import com.pax.market.api.sdk.java.api.emm.emmDeviceDetail.dto.EmmDeviceDashboardDetailDTO;
import com.pax.market.api.sdk.java.api.emm.emmDeviceDetail.dto.EmmDeviceDashboardMonitorDTO;
import com.pax.market.api.sdk.java.api.emm.emmDeviceDetail.dto.EmmDeviceInstalledAppDTO;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EmmDeviceDetailApiTest {

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
        Assert.assertEquals(0, result.getBusinessCode());
    }

    @Test
    public void testGetEmmDeviceDashboardDetailFailByAppIdIsInvalid() {
        Result<EmmDeviceDashboardDetailDTO> appIdIsNullResult = emmDeviceDetailApi.getEmmDeviceDashboardDetail(null);
        logger.debug("Result of emm device dashboard detail by appId is null: {}", appIdIsNullResult.toString());
        Assert.assertEquals(-1, appIdIsNullResult.getBusinessCode());

        Result<EmmDeviceDashboardDetailDTO> appIdIsNegativeResult = emmDeviceDetailApi.getEmmDeviceDashboardDetail(-1L);
        logger.debug("Result of emm device dashboard detail by appId is negative: {}", appIdIsNegativeResult.toString());
        Assert.assertEquals(-1, appIdIsNegativeResult.getBusinessCode());
    }

    @Test
    public void testGetEmmDeviceDashboardMonitorSuccess() {
        Result<EmmDeviceDashboardMonitorDTO> result = emmDeviceDetailApi.getEmmDeviceDashboardMonitor(1647480353587234L);
        logger.debug("Result of emm device dashboard monitor: {}", result.toString());
        Assert.assertEquals(0, result.getBusinessCode());
    }

    @Test
    public void testGetEmmDeviceDashboardMonitorFailByAppIdIsInvalid() {
        Result<EmmDeviceDashboardMonitorDTO> appIdIsNullResult = emmDeviceDetailApi.getEmmDeviceDashboardMonitor(null);
        logger.debug("Result of emm device dashboard monitor by appId is null: {}", appIdIsNullResult.toString());
        Assert.assertEquals(-1, appIdIsNullResult.getBusinessCode());

        Result<EmmDeviceDashboardMonitorDTO> appIdIsNegativeResult = emmDeviceDetailApi.getEmmDeviceDashboardMonitor(-1L);
        logger.debug("Result of emm device dashboard monitor by appId is negative: {}", appIdIsNegativeResult.toString());
        Assert.assertEquals(-1, appIdIsNegativeResult.getBusinessCode());
    }

    @Test
    public void testSearchDeviceInstalledAppSuccess() {
        Result<EmmDeviceInstalledAppDTO> searchDeviceInstalledAppSuccessResult1 = emmDeviceDetailApi.searchDeviceInstalledApp(1, 5, null, 1647480353587234L);
        logger.debug("Result1 of search emm device installed app: {}", searchDeviceInstalledAppSuccessResult1.toString());
        Assert.assertEquals(0, searchDeviceInstalledAppSuccessResult1.getBusinessCode());

        Result<EmmDeviceInstalledAppDTO> searchDeviceInstalledAppSuccessResult2 = emmDeviceDetailApi.searchDeviceInstalledApp(1, 5, EmmDeviceDetailApi.EmmDeviceInstalledAppOrderBy.AppName_desc, 1647480353587234L);
        logger.debug("Result2 of search emm device installed app: {}", searchDeviceInstalledAppSuccessResult2.toString());
        Assert.assertEquals(0, searchDeviceInstalledAppSuccessResult2.getBusinessCode());

        Result<EmmDeviceInstalledAppDTO> searchDeviceInstalledAppSuccessResult3 = emmDeviceDetailApi.searchDeviceInstalledApp(1, 5, EmmDeviceDetailApi.EmmDeviceInstalledAppOrderBy.AppName_asc, 1647480353587234L);
        logger.debug("Result3 of search emm device installed app: {}", searchDeviceInstalledAppSuccessResult3.toString());
        Assert.assertEquals(0, searchDeviceInstalledAppSuccessResult3.getBusinessCode());

        Result<EmmDeviceInstalledAppDTO> searchDeviceInstalledAppSuccessResult4 = emmDeviceDetailApi.searchDeviceInstalledApp(1, 5, EmmDeviceDetailApi.EmmDeviceInstalledAppOrderBy.AppSize_desc, 1647480353587234L);
        logger.debug("Result4 of search emm device installed app: {}", searchDeviceInstalledAppSuccessResult4.toString());
        Assert.assertEquals(0, searchDeviceInstalledAppSuccessResult4.getBusinessCode());

        Result<EmmDeviceInstalledAppDTO> searchDeviceInstalledAppSuccessResult5 = emmDeviceDetailApi.searchDeviceInstalledApp(1, 5, EmmDeviceDetailApi.EmmDeviceInstalledAppOrderBy.AppSize_asc, 1647480353587234L);
        logger.debug("Result5 of search emm device installed app: {}", searchDeviceInstalledAppSuccessResult5.toString());
        Assert.assertEquals(0, searchDeviceInstalledAppSuccessResult5.getBusinessCode());

        Result<EmmDeviceInstalledAppDTO> searchDeviceInstalledAppSuccessResult6 = emmDeviceDetailApi.searchDeviceInstalledApp(1, 5, EmmDeviceDetailApi.EmmDeviceInstalledAppOrderBy.AppInstallTime_desc, 1647480353587234L);
        logger.debug("Result6 of search emm device installed app: {}", searchDeviceInstalledAppSuccessResult6.toString());
        Assert.assertEquals(0, searchDeviceInstalledAppSuccessResult6.getBusinessCode());

        Result<EmmDeviceInstalledAppDTO> searchDeviceInstalledAppSuccessResult7 = emmDeviceDetailApi.searchDeviceInstalledApp(1, 5, EmmDeviceDetailApi.EmmDeviceInstalledAppOrderBy.AppInstallTime_asc, 1647480353587234L);
        logger.debug("Result7 of search emm device installed app: {}", searchDeviceInstalledAppSuccessResult7.toString());
        Assert.assertEquals(0, searchDeviceInstalledAppSuccessResult7.getBusinessCode());
    }

    @Test
    public void testSearchDeviceInstalledAppFailByAppIdIsInvalid() {
        Result<EmmDeviceInstalledAppDTO> appIdIsNullResult = emmDeviceDetailApi.searchDeviceInstalledApp(1, 5, null, null);
        logger.debug("Result of search emm device installed app by appId is null: {}", appIdIsNullResult.toString());
        Assert.assertEquals(-1, appIdIsNullResult.getBusinessCode());

        Result<EmmDeviceInstalledAppDTO> appIdIsNegativeResult = emmDeviceDetailApi.searchDeviceInstalledApp(1, 5, null, -1L);
        logger.debug("Result of search emm device installed app by appId is negative: {}", appIdIsNegativeResult.toString());
        Assert.assertEquals(-1, appIdIsNegativeResult.getBusinessCode());
    }

}
