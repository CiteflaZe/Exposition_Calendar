package com.project.service.util;

import com.project.exception.IllegalPaginationValuesException;
import org.apache.log4j.Logger;

public class    PaginationUtil {
    private static final Logger LOGGER = Logger.getLogger(PaginationUtil.class);
    private static final Integer DEFAULT_ROW_COUNT = 15;
    private static final Integer DEFAULT_PAGE = 1;

    public Integer[] checkPagination(String currentPageString, String rowCountString, Long entriesAmount){
        if (currentPageString == null || currentPageString.isEmpty() ||
                rowCountString == null || rowCountString.isEmpty()
                || entriesAmount == null) {
            LOGGER.warn("Invalid pagination values");
            throw new IllegalPaginationValuesException("Such values are not allowed");
        }

        Integer currentPage = Integer.parseInt(currentPageString);
        Integer rowCount = Integer.parseInt(rowCountString);

        if(!rowCount.equals(DEFAULT_ROW_COUNT)){
            rowCount = DEFAULT_ROW_COUNT;
        }

        Integer numberOfPages = ((Double) Math.ceil(entriesAmount*1.0/rowCount)).intValue();

        if(!numberOfPages.equals(0)){
            if(currentPage > numberOfPages){
                currentPage = numberOfPages;
            }else if(currentPage <= 0){
                currentPage = DEFAULT_PAGE;
            }
        }else {
            currentPage = DEFAULT_PAGE;
        }


        return new Integer[]{currentPage, rowCount, numberOfPages};
    }

}
