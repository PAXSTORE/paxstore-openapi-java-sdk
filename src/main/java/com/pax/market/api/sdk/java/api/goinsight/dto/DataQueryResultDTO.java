package com.pax.market.api.sdk.java.api.goinsight.dto;

import java.io.Serializable;
import java.util.List;

/**
 * @author liukai
 * @date 2020/3/23
 */
public class DataQueryResultDTO implements Serializable {

    private String worksheetName;
    private List<Column> columns;
    private List<List<Row>> rows;
    private boolean hasNext;
    private long offset;
    private int limit;

    public String getWorksheetName() {
        return worksheetName;
    }

    public void setWorksheetName(String worksheetName) {
        this.worksheetName = worksheetName;
    }

    public List<Column> getColumns() {
        return columns;
    }

    public void setColumns(List<Column> columns) {
        this.columns = columns;
    }

    public List<List<Row>> getRows() {
        return rows;
    }

    public void setRows(List<List<Row>> rows) {
        this.rows = rows;
    }

    public boolean isHasNext() {
        return hasNext;
    }

    public void setHasNext(boolean hasNext) {
        this.hasNext = hasNext;
    }

    public long getOffset() {
        return offset;
    }

    public void setOffset(long offset) {
        this.offset = offset;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    @Override
    public String toString() {
        return "DataQueryResultDTO{" +
                "worksheetName='" + worksheetName + '\'' +
                ", columns=" + columns +
                ", rows=" + rows +
                ", hasNext=" + hasNext +
                ", offset=" + offset +
                ", limit=" + limit +
                '}';
    }

    public static class Row {
        String colName;
        String value;

        public String getColName() {
            return colName;
        }

        public void setColName(String colName) {
            this.colName = colName;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "Row{" +
                    "colName='" + colName + '\'' +
                    ", value='" + value + '\'' +
                    '}';
        }
    }

    public static class Column {
        String colName;
        String displayName;
        String type;

        public String getColName() {
            return colName;
        }

        public void setColName(String colName) {
            this.colName = colName;
        }

        public String getDisplayName() {
            return displayName;
        }

        public void setDisplayName(String displayName) {
            this.displayName = displayName;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        @Override
        public String toString() {
            return "Column{" +
                    "colName='" + colName + '\'' +
                    ", displayName='" + displayName + '\'' +
                    ", type='" + type + '\'' +
                    '}';
        }
    }
}
