package com.pax.market.api.sdk.java.api.goinsight.dto;

public enum CustomColName {
    RESELLER("_sys_resellerid"),
    MERCHANT("_sys_merchantid"),
    TERMINAL("_sys_terminalid"),
    FACTORY("_sys_factoryid"),
    MODEL("_sys_modelid");

    CustomColName(String val) {
        this.val = val;
    }

    private final String val;
    public String val() {
        return this.val;
    }
}
