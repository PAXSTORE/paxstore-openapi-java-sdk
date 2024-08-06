package com.pax.market.api.sdk.java.api.goinsight.dto;

public class GoInsightCustomFilter {
    private String cloName;
    private String filterValue;

    public GoInsightCustomFilter() {
    }

    public String getCloName() {
        return cloName;
    }

    public void setCloName(String cloName) {
        this.cloName = cloName;
    }

    public void setCloName(CustomColName cloName) {
        this.cloName = cloName.val();
    }

    public String getFilterValue() {
        return filterValue;
    }

    public void setFilterValue(String filterValue) {
        this.filterValue = filterValue;
    }
}
