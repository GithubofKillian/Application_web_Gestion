package com.application_web_gestion.servlet;

import com.application_web_gestion.service.LoginService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

    private final LoginService loginService = new LoginService(); // Initialisation du service

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Par défaut, afficher la page de connexion
        request.getRequestDispatcher("/login.jsp").forward(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String contact = request.getParameter("contact");
        String password = request.getParameter("password");
        String role = request.getParameter("role");

        boolean isAuthenticated = loginService.authenticateUser(contact, password, role);

        if (isAuthenticated) {
            // Stocker le rôle dans la session
            request.getSession().setAttribute("userRole", role);

            // Rediriger vers index.jsp
            response.sendRedirect(request.getContextPath() + "/index.jsp");
        } else {
            request.setAttribute("error", "Identifiants incorrects ou rôle invalide.");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }
    }
}
