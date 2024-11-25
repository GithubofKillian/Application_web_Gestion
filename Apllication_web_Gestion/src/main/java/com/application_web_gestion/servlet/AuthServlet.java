package com.application_web_gestion.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/AuthServlet")
public class AuthServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        switch (action) {
            case "login" -> request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
            case "signup" -> request.getRequestDispatcher("/WEB-INF/views/signup.jsp").forward(request, response);
            default -> request.getRequestDispatcher("/WEB-INF/views/auth.jsp").forward(request, response);
        }
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null) {
            afficher(request,response);
            return;
        }

        switch (action) {
            default -> afficher(request, response);
        }
    }
    private void afficher(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/views/auth.jsp").forward(request, response);
    }
}

