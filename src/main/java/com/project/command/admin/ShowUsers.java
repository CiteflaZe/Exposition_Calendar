package com.project.command.admin;

import com.project.command.Command;
import com.project.domain.user.User;
import com.project.service.UserService;
import com.project.service.validator.PaginationValidator;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ShowUsers implements Command {
    private final UserService userService;
    private final PaginationValidator paginationValidator;

    public ShowUsers(UserService userService, PaginationValidator paginationValidator) {
        this.userService = userService;
        this.paginationValidator = paginationValidator;
    }

    @Override
    public String execute(HttpServletRequest request) {
        final String rowCountString = request.getParameter("rowCount");
        final String startFromString = request.getParameter("startFrom");
        final Integer entriesAmount = userService.showEntriesAmount();
        final String action = request.getParameter("page");
        System.out.println(action);
        final Integer[] validValues = paginationValidator.validate(rowCountString, startFromString, entriesAmount, action);

        Integer rowCount = validValues[0];
        Integer startFrom = validValues[1];

        final List<User> users = userService.showAll(rowCount, startFrom);
        request.setAttribute("users", users);
        request.setAttribute("rowCount", rowCount);
        request.setAttribute("startFrom", startFrom);

        return "show.jsp";
    }
}
