package com.application_web_gestion.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/AuthServlet")
public class AuthServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if ("login".equals(action)) {
            response.sendRedirect("WEB-INF/views/login.jsp");
        } else if ("signup".equals(action)) {
            response.sendRedirect("WEB-INF/views/signup.jsp");
        } else {
            response.sendRedirect("WEB-INF/views/auth.jsp");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            afficher(request,response);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        }
    }
    private void afficher(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.getRequestDispatcher("WEB-INF/views/auth.jsp").forward(request, response);

    }
}

