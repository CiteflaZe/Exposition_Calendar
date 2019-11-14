package com.project.command.user;

import com.project.command.Command;
import com.project.domain.exposition.Exposition;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ProcessExpositionCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        final List<Exposition> expositions = (List<Exposition>) request.getSession().getAttribute("expositions");
        for (int i = 0; i < expositions.size(); i++) {
            if(request.getParameter("exposition" + i) != null){
                request.getSession().setAttribute("exposition", expositions.get(i));
                return "choose-date.jsp";
            }
        }
        return "/";
    }
}
