package com.project.command;

import com.project.exception.IllegalPaginationValuesException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.apache.commons.lang3.math.NumberUtils.isParsable;

public interface Command {
    String execute(HttpServletRequest request, HttpServletResponse response);

    default void paginate(int page, int rowCount, long entries, HttpServletRequest request, String url){
        int numberOfPages = (int) Math.ceil(entries * 1.0 / rowCount);

        request.setAttribute("currentPage", page);
        request.setAttribute("rowCount", rowCount);
        request.setAttribute("numberOfPages", numberOfPages);
        request.setAttribute("command", url);
    }

    default void validatePagination(String page, String rowCount) {
        if (!isParsable(page) || !isParsable(rowCount) ||
        page.contains("-") || rowCount.contains("-")) {
            throw new IllegalPaginationValuesException("Illegal pagination values");
        }
    }
}
