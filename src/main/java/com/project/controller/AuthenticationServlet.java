package com.project.controller;

import javax.servlet.annotation.WebServlet;

@WebServlet(urlPatterns = {"/login", "/register", "/logout"})
public class AuthenticationServlet extends AbstractServlet {
    public AuthenticationServlet() {
        super("authentication");
    }
}
