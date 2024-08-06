package com.pax.market.api.sdk.java.api.goinsight.dto;

import java.io.Serializable;
import java.util.List;

public class DataQueryRequest implements Serializable {

    private String timeRangeType;
    private List<GoInsightCustomFilter> customFilterList;

    private Integer pageNo = 1;
    private Integer pageSize;

    public DataQueryRequest() {
    }

    public String getTimeRangeType() {
        return timeRangeType;
    }

    public void setTimeRangeType(String timeRangeType) {
        this.timeRangeType = timeRangeType;
    }

    public List<GoInsightCustomFilter> getCustomFilterList() {
        return customFilterList;
    }

    public void setCustomFilterList(List<GoInsightCustomFilter> customFilterList) {
        this.customFilterList = customFilterList;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
