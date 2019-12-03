package com.project.command.admin;

import com.project.command.Command;
import com.project.domain.User;
import com.project.service.UserService;
import com.project.service.util.PaginationUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ShowUsersCommand implements Command {
    private final UserService userService;
    private final PaginationUtil paginationUtil;

    public ShowUsersCommand(UserService userService, PaginationUtil paginationUtil) {
        this.userService = userService;
        this.paginationUtil = paginationUtil;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        final String currentPageString = request.getParameter("currentPage");
        final String rowCountString = request.getParameter("rowCount");
        final Long entriesAmount = userService.showEntriesAmount();
        final Integer[] validPagination = paginationUtil.checkPagination(currentPageString, rowCountString, entriesAmount);

        final Integer currentPage = validPagination[0];
        final Integer rowCount = validPagination[1];
        final Integer numberOfPages = validPagination[2];
        final Integer startFrom = currentPage*rowCount - rowCount;

        final List<User> users = userService.showAll(startFrom, rowCount);
        request.setAttribute("users", users);
        request.setAttribute("command", "showUsers");
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("rowCount", rowCount);
        request.setAttribute("numberOfPages", numberOfPages);

        return "admin-show-users.jsp";
    }
}
