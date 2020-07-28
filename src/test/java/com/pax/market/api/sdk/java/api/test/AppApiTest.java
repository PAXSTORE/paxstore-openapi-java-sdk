package com.pax.market.api.sdk.java.api.test;

import com.pax.market.api.sdk.java.api.app.AppApi;
import com.pax.market.api.sdk.java.api.app.AppApi.AppSearchOrderBy;
import com.pax.market.api.sdk.java.api.app.AppApi.ApkStatus;
import com.pax.market.api.sdk.java.api.app.AppApi.AppStatus;
import com.pax.market.api.sdk.java.api.app.AppApi.AppBaseType;
import com.pax.market.api.sdk.java.api.app.AppApi.AppChargeType;
import com.pax.market.api.sdk.java.api.app.AppApi.AppOsType;
import com.pax.market.api.sdk.java.api.app.dto.AppPageDTO;
import com.pax.market.api.sdk.java.api.base.dto.Result;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



/**
 * @author liukai
 * @date 2019/5/23
 */
public class AppApiTest  {

    private static final Logger logger = LoggerFactory.getLogger(AppApiTest.class.getSimpleName());

    private AppApi appApi;

    @Before
    public void init() {
        appApi = new AppApi(TestConstants.API_BASE_URL, TestConstants.API_KEY, TestConstants.API_SECRET);
    }

    @Test
    public void testSearchApp() {
        Result<AppPageDTO> result = appApi.searchApp(1,12, AppSearchOrderBy.UpdatedDate_desc,
                "", AppOsType.Android, AppChargeType.Free, AppBaseType.Normal, AppStatus.Active,
                ApkStatus.Online, false, null);
        logger.debug("Result of search app:{}", result.toString());
        Assert.assertTrue(result.getBusinessCode() == 0);
    }

    @Test
    public void testSearchAppWithSubscribedApp() {
        Result<AppPageDTO> result = appApi.searchApp(1,1, AppSearchOrderBy.UpdatedDate_desc,
                "", AppOsType.Android, AppChargeType.Free, AppBaseType.Normal, AppStatus.Active,
                ApkStatus.Online, false, null,true);
        logger.debug("Result of search app:{}", result.toString());
        Assert.assertTrue(result.getBusinessCode() == 0);
    }
}
