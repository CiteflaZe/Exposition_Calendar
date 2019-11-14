package com.project.command.admin;

import com.project.command.Command;
import com.project.domain.user.User;
import com.project.service.UserService;
import com.project.service.validator.PaginationValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ShowUsers implements Command {
    private final UserService userService;
    private final PaginationValidator paginationValidator;

    public ShowUsers(UserService userService, PaginationValidator paginationValidator) {
        this.userService = userService;
        this.paginationValidator = paginationValidator;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        final Integer currentPage = Integer.valueOf(request.getParameter("currentPage"));
        final Integer rowCount = Integer.valueOf(request.getParameter("rowCount"));
        final Integer startFrom = currentPage*rowCount - rowCount;
        final Integer entriesAmount = userService.showEntriesAmount();
        final Integer numberOfPages = ((Double) Math.ceil(entriesAmount*1.0/rowCount)).intValue();

        final List<User> users = userService.showAll(startFrom, rowCount);
        request.setAttribute("users", users);
        request.setAttribute("command", "show");
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("rowCount", rowCount);
        request.getSession().setAttribute("numberOfPages", numberOfPages);

        return "show.jsp";
    }
}
