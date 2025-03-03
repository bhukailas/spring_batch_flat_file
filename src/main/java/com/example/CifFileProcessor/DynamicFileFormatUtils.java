package com.example.CifFileProcessor.generator;

import java.lang.reflect.Field;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class DynamicFileFormatUtils {

    public static String formatRow(Object obj, List<ColumnConfig.Column> columnConfigList, int lineNumber) throws IllegalAccessException, NoSuchFieldException {
        StringBuilder formattedRow = new StringBuilder();

        for (ColumnConfig.Column column : columnConfigList) {
            String attributeName = column.getAttributeName();
            int length = column.getLength();
            String format = column.getFormat();
            String padding = column.getPadding();
            String dateFormat = column.getDateFormat();

            // Special handling for the line number column
            if ("lineNumber".equals(format)) {
                // Ensure the line number is padded to the specified length
                String paddedLineNumber = String.format("%" + (padding != null ? padding : " ") + length + "d", lineNumber);
                formattedRow.append(paddedLineNumber);
            } else {
                // Use reflection to get the value of the attribute from the object
                Field field = obj.getClass().getDeclaredField(attributeName);
                field.setAccessible(true);
                Object value = field.get(obj);

                if (value != null) {
                    switch (format) {
                        case "left":
                            formattedRow.append(String.format("%-" + length + "s", value.toString().substring(0, Math.min(value.toString().length(), length))));
                            break;
                        case "right":
                            formattedRow.append(String.format("%" + length + "d", Integer.parseInt(value.toString())));
                            break;
                        case "date":
                            if (value instanceof java.time.LocalDate) {
                                formattedRow.append(String.format("%-" + length + "s", ((java.time.LocalDate) value).format(DateTimeFormatter.ofPattern(dateFormat))));
                            }
                            break;
                        default:
                            formattedRow.append(String.format("%-" + length + "s", value.toString().substring(0, Math.min(value.toString().length(), length))));
                    }
                } else {
                    // In case of null value, append empty space
                    formattedRow.append(" ".repeat(length));
                }
            }
        }

        return formattedRow.toString();
    }
}
