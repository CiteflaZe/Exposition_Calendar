package com.project.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class DownloadServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        String file = "ticket.pdf";
        resp.setContentType("APPLICATION/OCTET-STREAM");
        resp.setHeader("Content-Disposition", "attachment; filename=\""
                + file + "\"");

        FileInputStream fileInputStream = new FileInputStream("tickets/" + file);

        System.out.println(req.getRequestURL());

        int i;
        while ((i = fileInputStream.read()) != -1) {
            out.write(i);
        }
        fileInputStream.close();
        out.close();
    }
}
