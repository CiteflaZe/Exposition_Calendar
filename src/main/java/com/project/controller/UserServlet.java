package com.project.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class UserServlet extends HttpServlet {

    private List<String> emails = new CopyOnWriteArrayList<>();
    private List<String> passwords = new CopyOnWriteArrayList<>();

    @Override
    public void init() throws ServletException {
        emails.add("email1");
        emails.add("email2");

        passwords.add("pass1");
        passwords.add("pass2");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("emails", emails);
        req.setAttribute("passwords", passwords);
        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = (String) req.getParameter("email");
        String password = (String) req.getParameter("password");

        emails.add(email);
        passwords.add(password);

        doGet(req, resp);
    }
}
