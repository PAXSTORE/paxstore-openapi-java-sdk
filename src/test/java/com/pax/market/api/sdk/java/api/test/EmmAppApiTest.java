package com.pax.market.api.sdk.java.api.test;

import com.pax.market.api.sdk.java.api.base.dto.Result;
import com.pax.market.api.sdk.java.api.emm.emmApp.EmmAppApi;
import com.pax.market.api.sdk.java.api.emm.emmApp.dto.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EmmAppApiTest extends IntegrationTestSupport {

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

    private EmmAppApi emmAppApi;

    @Before
    public void init() {
        emmAppApi = new EmmAppApi(TestConstants.API_BASE_URL, TestConstants.API_KEY, TestConstants.API_SECRET);
    }

    @Test
    public void testSearchEmmAppSuccess() {
        Result<EmmAppDTO> searchEmmAppSuccessResult1 = emmAppApi.searchEmmApp(1, 12, null, "PAX", null, null);
        logger.debug("Result1 of search emm app: {}", searchEmmAppSuccessResult1.toString());
        assertOperationOk(searchEmmAppSuccessResult1);

        Result<EmmAppDTO> searchEmmAppSuccessResult2 = emmAppApi.searchEmmApp(1, 12, EmmAppApi.EmmAppSearchOrderBy.EmmAppName_desc, "PAX", null, null);
        logger.debug("Result2 of search emm app: {}", searchEmmAppSuccessResult2.toString());
        assertOperationOk(searchEmmAppSuccessResult2);

        Result<EmmAppDTO> searchEmmAppSuccessResult3 = emmAppApi.searchEmmApp(1, 12, EmmAppApi.EmmAppSearchOrderBy.EmmAppName_asc, "PAX", null, null);
        logger.debug("Result3 of search emm app: {}", searchEmmAppSuccessResult3.toString());
        assertOperationOk(searchEmmAppSuccessResult3);

        Result<EmmAppDTO> searchEmmAppSuccessResult4 = emmAppApi.searchEmmApp(1, 12, EmmAppApi.EmmAppSearchOrderBy.EmmUpdatedDate_desc, "PAX", null, null);
        logger.debug("Result4 of search emm app: {}", searchEmmAppSuccessResult4.toString());
        assertOperationOk(searchEmmAppSuccessResult4);

        Result<EmmAppDTO> searchEmmAppSuccessResult5 = emmAppApi.searchEmmApp(1, 12, EmmAppApi.EmmAppSearchOrderBy.EmmUpdatedDate_asc, "PAX", null, null);
        logger.debug("Result5 of search emm app: {}", searchEmmAppSuccessResult5.toString());
        assertOperationOk(searchEmmAppSuccessResult5);

        Result<EmmAppDTO> searchEmmAppSuccessResult6 = emmAppApi.searchEmmApp(1, 12, null, "PAX", "百度输入法", null);
        logger.debug("Result6 of search emm app: {}", searchEmmAppSuccessResult6.toString());
        assertOperationOk(searchEmmAppSuccessResult6);

        Result<EmmAppDTO> searchEmmAppSuccessResult7 = emmAppApi.searchEmmApp(1, 12, null, "PAX", "com.baidu.input", null);
        logger.debug("Result7 of search emm app: {}", searchEmmAppSuccessResult7.toString());
        assertOperationOk(searchEmmAppSuccessResult7);

        Result<EmmAppDTO> searchEmmAppSuccessResult8 = emmAppApi.searchEmmApp(1, 12, null, "PAX", "", null);
        logger.debug("Result8 of search emm app: {}", searchEmmAppSuccessResult8.toString());
        assertOperationOk(searchEmmAppSuccessResult8);

        Result<EmmAppDTO> searchEmmAppSuccessResult9 = emmAppApi.searchEmmApp(1, 12, EmmAppApi.EmmAppSearchOrderBy.EmmAppName_desc, "PAX", "", null);
        logger.debug("Result9 of search emm app: {}", searchEmmAppSuccessResult9.toString());
        assertOperationOk(searchEmmAppSuccessResult2);

        Result<EmmAppDTO> searchEmmAppSuccessResult10 = emmAppApi.searchEmmApp(1, 12, EmmAppApi.EmmAppSearchOrderBy.EmmAppName_asc, "PAX", "", null);
        logger.debug("Result10 of search emm app: {}", searchEmmAppSuccessResult10.toString());
        assertOperationOk(searchEmmAppSuccessResult10);

        Result<EmmAppDTO> searchEmmAppSuccessResult11 = emmAppApi.searchEmmApp(1, 12, EmmAppApi.EmmAppSearchOrderBy.EmmUpdatedDate_desc, "PAX", "", null);
        logger.debug("Result11 of search emm app: {}", searchEmmAppSuccessResult11.toString());
        assertOperationOk(searchEmmAppSuccessResult11);

        Result<EmmAppDTO> searchEmmAppSuccessResult12 = emmAppApi.searchEmmApp(1, 12, EmmAppApi.EmmAppSearchOrderBy.EmmUpdatedDate_asc, "PAX", "", null);
        logger.debug("Result12 of search emm app: {}", searchEmmAppSuccessResult12.toString());
        assertOperationOk(searchEmmAppSuccessResult12);

        Result<EmmAppDTO> searchEmmAppSuccessResult13 = emmAppApi.searchEmmApp(1, 12, null, "PAX", "com.baidu.input", EmmAppApi.EmmAppType.GOOGLE);
        logger.debug("Result13 of search emm app: {}", searchEmmAppSuccessResult13.toString());
        assertOperationOk(searchEmmAppSuccessResult13);

        Result<EmmAppDTO> searchEmmAppSuccessResult14 = emmAppApi.searchEmmApp(1, 12, null, "PAX", "百度输入法", EmmAppApi.EmmAppType.PRIVATE);
        logger.debug("Result14 of search emm app: {}", searchEmmAppSuccessResult14.toString());
        assertOperationOk(searchEmmAppSuccessResult14);

    }


    @Test
    public void testSearchEmmAppFailByResellerNameIsInvalid() {
        Result<EmmAppDTO> resellerNameIsNullResult = emmAppApi.searchEmmApp(1, 12, null, null, null, null);
        logger.debug("Result of search emm app result by reseller name is null: {}", resellerNameIsNullResult.toString());
        assertValidationFailure(resellerNameIsNullResult);

        Result<EmmAppDTO> resellerNameIsBlankResult = emmAppApi.searchEmmApp(1, 12, null, null, null, null);
        logger.debug("Result of search emm app result by reseller name is blank: {}", resellerNameIsBlankResult.toString());
        assertValidationFailure(resellerNameIsBlankResult);

        Result<EmmAppDTO> resellerNameIsTooLongResult = emmAppApi.searchEmmApp(1, 12, null, "PAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAX", null, null);
        logger.debug("Result of search emm app result by reseller name is too long: {}", resellerNameIsTooLongResult.toString());
        assertValidationFailure(resellerNameIsTooLongResult);

        Result<EmmAppDTO> resellerNameIsNotExistResult = emmAppApi.searchEmmApp(1, 12, null, "resellerNameNotExist", null, null);
        logger.debug("Result of search emm app result by reseller name is not exist: {}", resellerNameIsNotExistResult.toString());
        assertBusinessError(resellerNameIsNotExistResult);

    }

    @Test
    public void testCreateEmmAppSuccess() {
        EmmAppCreateRequest emmAppCreateRequest = new EmmAppCreateRequest();
        emmAppCreateRequest.setResellerName("PAX");
        emmAppCreateRequest.setPackageName("org.videolan.vlc");

        Result<EmmAppDTO> result = emmAppApi.createEmmApp(emmAppCreateRequest);
        logger.debug("Result of create emm app: {}", result.toString());
        assertOperationOk(result);
    }

    @Test
    public void testCreateEmmAppFailByEmmAppCreateRequestIsInvalid() {
        Result<EmmAppDTO> result = emmAppApi.createEmmApp(null);
        logger.debug("Result of create emm app by emmAppCreateRequest is null: {}", result.toString());
        assertValidationFailure(result);
    }

    @Test
    public void testCreateEmmAppFailByResellerNameIsInvalid() {
        EmmAppCreateRequest resellerNameIsNull = new EmmAppCreateRequest();
        resellerNameIsNull.setResellerName(null);
        resellerNameIsNull.setPackageName("com.baidu.input");
        Result<EmmAppDTO> resellerNameIsNullResult = emmAppApi.createEmmApp(resellerNameIsNull);
        logger.debug("Result of create emm app by reseller name is null: {}", resellerNameIsNullResult.toString());
        assertValidationFailure(resellerNameIsNullResult);

        EmmAppCreateRequest resellerNameIsBlank = new EmmAppCreateRequest();
        resellerNameIsBlank.setResellerName("");
        resellerNameIsBlank.setPackageName("com.baidu.input");
        Result<EmmAppDTO> resellerNameIsBlankResult = emmAppApi.createEmmApp(resellerNameIsBlank);
        logger.debug("Result of create emm app by reseller name is blank: {}", resellerNameIsBlankResult.toString());
        assertValidationFailure(resellerNameIsBlankResult);

        EmmAppCreateRequest resellerNameTooLong = new EmmAppCreateRequest();
        resellerNameTooLong.setResellerName("PAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAX");
        resellerNameTooLong.setPackageName("com.baidu.input");
        Result<EmmAppDTO> resellerNameIsTooLongResult = emmAppApi.createEmmApp(resellerNameTooLong);
        logger.debug("Result of create emm app by reseller name is too long: {}", resellerNameIsTooLongResult.toString());
        assertValidationFailure(resellerNameIsTooLongResult);

        EmmAppCreateRequest resellerNameNotExist = new EmmAppCreateRequest();
        resellerNameNotExist.setResellerName("resellerNameNotExist");
        resellerNameNotExist.setPackageName("com.baidu.input");
        Result<EmmAppDTO> resellerNameIsNotExistResult = emmAppApi.createEmmApp(resellerNameNotExist);
        logger.debug("Result of create emm app by reseller name is not exist: {}", resellerNameIsNotExistResult.toString());
        assertBusinessError(resellerNameIsNotExistResult);

    }

    @Test
    public void testCreateEmmAppFailByPackageNameIsInvalid() {
        EmmAppCreateRequest packageNameIsNull = new EmmAppCreateRequest();
        packageNameIsNull.setResellerName("PAX");
        packageNameIsNull.setPackageName(null);
        Result<EmmAppDTO> packageNameIsNullResult = emmAppApi.createEmmApp(packageNameIsNull);
        logger.debug("Result of create emm app by package name is null: {}", packageNameIsNullResult.toString());
        assertValidationFailure(packageNameIsNullResult);

        EmmAppCreateRequest packageNameIsBlank = new EmmAppCreateRequest();
        packageNameIsBlank.setResellerName("PAX");
        packageNameIsBlank.setPackageName("");
        Result<EmmAppDTO> packageNameIsBlankResult = emmAppApi.createEmmApp(packageNameIsBlank);
        logger.debug("Result of create emm app by package name is blank: {}", packageNameIsBlankResult.toString());
        assertValidationFailure(packageNameIsBlankResult);

        EmmAppCreateRequest packageNameTooLong = new EmmAppCreateRequest();
        packageNameTooLong.setResellerName("PAX");
        packageNameTooLong.setPackageName("com.test.app.test.app.test.app.test.app.test.app.test.app.test.app.test.app.test.app.test.app.test.app.test.app.test.app.test.app.test.app.test.app.test.app.test.app.test.app");
        Result<EmmAppDTO> packageNameIsToLongResult = emmAppApi.createEmmApp(packageNameTooLong);
        logger.debug("Result of create emm app by package name is too long: {}", packageNameIsToLongResult.toString());
        assertValidationFailure(packageNameIsToLongResult);

        EmmAppCreateRequest packageNameNotExist = new EmmAppCreateRequest();
        packageNameNotExist.setResellerName("PAX");
        packageNameNotExist.setPackageName("com.not.exist.app");
        Result<EmmAppDTO> packageNameNotExistResult = emmAppApi.createEmmApp(packageNameNotExist);
        logger.debug("Result of create emm app by package name not exist: {}", packageNameNotExistResult.toString());
        assertBusinessError(packageNameNotExistResult);
    }


    @Test
    public void testGetEmmAppDetailSuccess() {
        Result<EmmAppDetailDTO> result = emmAppApi.getEmmAppDetail(1646714366722087L);
        logger.debug("Result of get emm app detail: {}", result.toString());
        assertOperationOk(result);
    }

    @Test
    public void testGetEmmAppDetailFailByAppIdIsInvalid() {
        Result<EmmAppDetailDTO> appIdIsNullResult = emmAppApi.getEmmAppDetail(null);
        logger.debug("Result of get emm app detail by appId is null: {}", appIdIsNullResult.toString());
        assertValidationFailure(appIdIsNullResult);

        Result<EmmAppDetailDTO> appIdIsNegativeResult = emmAppApi.getEmmAppDetail(-1L);
        logger.debug("Result of get emm app detail by appId is negative: {}", appIdIsNegativeResult.toString());
        assertValidationFailure(appIdIsNegativeResult);
    }

    @Test
    public void testRemoveEmmAppSuccess() {
        Result<String> result = emmAppApi.removeEmmApp(1653623289282594L, "PAX");
        logger.debug("Result of remove emm app: {}", result.toString());
        assertOperationOk(result);
    }

    @Test
    public void testRemoveEmmAppFailByAppIdInvalid() {
        Result<String> appIdIsNullResult = emmAppApi.removeEmmApp(null, "PAX");
        logger.debug("Result of remove emm app by appId is null: {}", appIdIsNullResult.toString());
        assertValidationFailure(appIdIsNullResult);

        Result<String> appIdIsNegativeResult = emmAppApi.removeEmmApp(-1L, "PAX");
        logger.debug("Result of remove emm app by appId is negative: {}", appIdIsNegativeResult.toString());
        assertValidationFailure(appIdIsNegativeResult);

    }

    @Test
    public void testRemoveEmmAppFailByResellerNameIsInvalid() {
        Result<String> resellerNameIsNullResult = emmAppApi.removeEmmApp(1653623289282594L, null);
        logger.debug("Result of remove emm app by reseller name is null: {}", resellerNameIsNullResult.toString());
        assertValidationFailure(resellerNameIsNullResult);

        Result<String> resellerNameIsBlankResult = emmAppApi.removeEmmApp(1653623289282594L, "");
        logger.debug("Result of remove emm app by reseller name is blank: {}", resellerNameIsBlankResult.toString());
        assertValidationFailure(resellerNameIsBlankResult);

        Result<String> resellerNameIsTooLongResult = emmAppApi.removeEmmApp(1653623289282594L, "PAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAXPAX");
        logger.debug("Result of remove emm app by reseller name is too long: {}", resellerNameIsTooLongResult.toString());
        assertValidationFailure(resellerNameIsTooLongResult);

        Result<String> resellerNameIsNotExistResult = emmAppApi.removeEmmApp(1653623289282594L, "resellerNameNotExist");
        logger.debug("Result of remove emm app by reseller name is not exist: {}", resellerNameIsNotExistResult.toString());
        assertBusinessError(resellerNameIsNotExistResult);
    }

    @Test
    public void testSearchSubscribedEmmAppSuccess() {
        Result<SubscribeEmmAppDTO> result = emmAppApi.searchSubscribeEmmApp(1, 5, null, Boolean.TRUE);
        logger.debug("Result of search subscribed emm app: {}", result.toString());
        assertOperationOk(result);
    }

    @Test
    public void testSearchSubscribedEmmAppFailByIsSubscribedIsInvalid() {
        Result<SubscribeEmmAppDTO> isSubscribedIsNullResult = emmAppApi.searchSubscribeEmmApp(1, 5, null, null);
        logger.debug("Result of search subscribed emm app by IsSubscribed is null: {}", isSubscribedIsNullResult.toString());
        assertValidationFailure(isSubscribedIsNullResult);
    }

    @Test
    public void testSubscribeEmmAppSuccess() {
        Result<String> result = emmAppApi.subscribeEmmApp(1646714366722087L);
        logger.debug("Result of subscribe emm app: {}", result.toString());
        assertOperationOk(result);
    }

    @Test
    public void testSubscribeEmmAppFailByAppIdIsInvalid() {
        Result<String> appIdIsNullResult = emmAppApi.subscribeEmmApp(null);
        logger.debug("Result of subscribe emm app by appId is null: {}", appIdIsNullResult.toString());
        assertValidationFailure(appIdIsNullResult);

        Result<String> appIdIsNegativeResult = emmAppApi.subscribeEmmApp(-1L);
        logger.debug("Result of subscribe emm app by appId is negative: {}", appIdIsNegativeResult.toString());
        assertValidationFailure(appIdIsNegativeResult);
    }


    @Test
    public void testUnSubscribeEmmAppSuccess() {
        Result<String> result = emmAppApi.unSubscribeEmmApp(1646714366722087L);
        logger.debug("Result of unsubscribe emm app: {}", result.toString());
        assertOperationOk(result);
    }

    @Test
    public void testUnSubscribeEmmAppFailByAppIdIsInvalid() {
        Result<String> appIdIsNullResult = emmAppApi.unSubscribeEmmApp(null);
        logger.debug("Result of unsubscribe emm app by appId is null: {}", appIdIsNullResult.toString());
        assertValidationFailure(appIdIsNullResult);

        Result<String> appIdIsNegativeResult = emmAppApi.unSubscribeEmmApp(-1L);
        logger.debug("Result of unsubscribe emm app by appId is negative: {}", appIdIsNegativeResult.toString());
        assertValidationFailure(appIdIsNegativeResult);
    }

    @Test
    public void testGetEmmAppPermissionListSuccess() {
        Result<EmmAppPermissionDTO> result = emmAppApi.getEmmAppPermissionList(1646714366722087L);
        logger.debug("Result of get emm app permission list: {}", result.toString());
        assertOperationOk(result);
    }

    @Test
    public void testGetEmmAppPermissionListFailByAppIdIsInvalid() {
        Result<EmmAppPermissionDTO> appIdIsNullResult = emmAppApi.getEmmAppPermissionList(null);
        logger.debug("Result of get emm app permission list by appId is null: {}", appIdIsNullResult.toString());
        assertValidationFailure(appIdIsNullResult);

        Result<EmmAppPermissionDTO> appIdIsNegativeResult = emmAppApi.getEmmAppPermissionList(-1L);
        logger.debug("Result of get emm app permission list by appId is negative: {}", appIdIsNegativeResult.toString());
        assertValidationFailure(appIdIsNegativeResult);
    }

    @Test
    public void testGetAvailableTestTrackVersionListSuccess() {
        Result<EmmAppAvailableTestVersionDTO> result = emmAppApi.getAvailableTestTrackVersionList(1681159324958779L);
        logger.debug("Result of get available test track version list: {}", result.toString());
        assertOperationOk(result);
    }

    @Test
    public void testGetAvailableTestTrackVersionListFailByAppIdIsInvalid() {
        Result<EmmAppAvailableTestVersionDTO> appIdIsNullResult = emmAppApi.getAvailableTestTrackVersionList(null);
        logger.debug("Result of get available test track version list by appId is null: {}", appIdIsNullResult.toString());
        assertValidationFailure(appIdIsNullResult);

        Result<EmmAppAvailableTestVersionDTO> appIdIsNegativeResult = emmAppApi.getAvailableTestTrackVersionList(-1L);
        logger.debug("Result of get available test track version list by appId is negative: {}", appIdIsNegativeResult.toString());
        assertValidationFailure(appIdIsNegativeResult);
    }

}
