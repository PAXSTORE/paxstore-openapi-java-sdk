package com.pax.market.api.sdk.java.api.test;

/**
 * Centralised test data for the dev environment.
 *
 * <p>Identifiers below refer to long-lived records in the dev marketplace.
 * Read-only tests use them directly; write tests create their own records with
 * unique names and clean up afterwards. If the dev data set is refreshed,
 * update the constants in this file only.</p>
 */
public final class TestData {

    private TestData() {
    }

    public static final String RESELLER_GLOBAL = "PAX";

    public static final String MODEL_A920 = "A920";

    public static final String APP_PACKAGE_ZOLON_SIGN = "com.zolon.signrotatetest1";
    public static final String APK_TEMPLATE_JESSE_PASSWORD = "jesse_password.xml";
    public static final String APK_VERSION_1_0_13 = "1.0.13";

    public static final String TERMINAL_SN = "SN7231605";
    public static final String TERMINAL_TID = "HISGRL8G";

    public static final Long MERCHANT_ID = 1000057433L;
    public static final String MERCHANT_NAME = "lifecycle-merchant-1820474410878458-1";

    public static final Long EMM_DEVICE_ID_1 = 1647480353587234L;

    public static final String GOINSIGHT_QUERY_CODE = "GAPXQNMQ";

    public static final String COUNTRY_CN = "CN";
    public static final String PROVINCE_CN_JL = "CN_JL";
    public static final String EMAIL_DOMAIN = "@pax.com";
}
