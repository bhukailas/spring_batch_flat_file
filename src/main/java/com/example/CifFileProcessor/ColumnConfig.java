package com.example.CifFileProcessor.generator;

import java.util.List;

public class ColumnConfig {
    private List<Column> columns;

    public List<Column> getColumns() {
        return columns;
    }

    public void setColumns(List<Column> columns) {
        this.columns = columns;
    }

    public static class Column {
        private String attributeName;
        private int length;
        private String format;
        private String dateFormat;

        private String padding;

        public String getAttributeName() {
            return this.attributeName;
        }

        public int getLength() {
            return this.length;
        }

        public String getFormat() {
            return this.format;
        }

        public String getDateFormat() {
            return this.dateFormat;
        }

        public String getPadding() {
            return this.padding;
        }

        public void setAttributeName(String attributeName) {
            this.attributeName = attributeName;
        }

        public void setLength(int length) {
            this.length = length;
        }

        public void setFormat(String format) {
            this.format = format;
        }

        public void setDateFormat(String dateFormat) {
            this.dateFormat = dateFormat;
        }

        public void setPadding(String padding) {
            this.padding = padding;
        }
    }
}
