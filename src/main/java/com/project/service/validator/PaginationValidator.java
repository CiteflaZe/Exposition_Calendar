package com.project.service.validator;

public class PaginationValidator {
    private static final Integer DEFAULT_START_FROM = 0;
    private static final Integer DEFAULT_ROW_COUNT = 15;

    public Integer[] validate(String rowCountString, String startFromString, Integer entriesAmount, String action) {
        if (rowCountString == null || startFromString == null
                || rowCountString.isEmpty() || startFromString.isEmpty()) {
            throw new IllegalArgumentException("Such values are not allowed");
        }

        Integer rowCount = Integer.valueOf(rowCountString);
        Integer startFrom = Integer.valueOf(startFromString);

        if (action != null) {
            if (action.equals("next")) {
                startFrom += rowCount;
            }
            if (action.equals("previous")) {
                startFrom -= rowCount;
            }
        }

        if (rowCount <= 0) {
            rowCount = DEFAULT_ROW_COUNT;
        }
        if (startFrom < 0) {
            startFrom = DEFAULT_START_FROM;
        }
        if (rowCount > entriesAmount) {
            rowCount = entriesAmount;
        }
        if (startFrom > entriesAmount - rowCount) {
            startFrom = entriesAmount - rowCount;
        }

        return new Integer[]{rowCount, startFrom};

    }
}
