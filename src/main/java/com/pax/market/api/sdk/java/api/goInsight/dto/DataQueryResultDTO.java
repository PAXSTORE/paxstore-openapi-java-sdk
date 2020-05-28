package com.pax.market.api.sdk.java.api.goInsight.dto;

import lombok.*;

import java.io.Serializable;
import java.util.List;

/**
 * @author liukai
 * @date 2020/3/23
 */
@Getter
@Setter
@ToString
public class DataQueryResultDTO implements Serializable {

    private String worksheetName;
    private List<Column> columns;
    private List<List<Row>> rows;
    private boolean hasNext;
    private long offset;
    private int limit;

    @Getter
    @Setter
    @ToString
    public static class Row {
        String colName;
        String value;
    }

    @Getter
    @Setter
    @ToString
    public static class Column {
        String colName;
        String displayName;
        String type;
    }
}
