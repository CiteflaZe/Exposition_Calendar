package com.project.controller;

import javax.servlet.annotation.WebServlet;

@WebServlet("/admin")
public class AdminServlet extends AbstractServlet {
    public AdminServlet() {
        super("admin");
    }
}
