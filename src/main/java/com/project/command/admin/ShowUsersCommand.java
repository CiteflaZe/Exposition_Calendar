package com.project.command.admin;

import com.project.command.Command;
import com.project.domain.User;
import com.project.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ShowUsersCommand implements Command {
    private final UserService userService;

    public ShowUsersCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        final String stringPage = request.getParameter("currentPage");
        final String stringRowCount = request.getParameter("rowCount");

        validatePagination(stringPage, stringRowCount);

        final int page = Integer.parseInt(stringPage);
        final int rowCount = Integer.parseInt(stringRowCount);
        final List<User> users = userService.showAll(page, rowCount);

        paginate(page, rowCount, userService.showEntriesAmount(), request, "showUsers");

        request.setAttribute("users", users);

        return "admin-show-users.jsp";
    }
}
