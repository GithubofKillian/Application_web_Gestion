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
        request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String contact = request.getParameter("contact");
        String password = request.getParameter("password");
        String role = request.getParameter("role");

        System.out.println("Contact: " + contact);
        System.out.println("Password: " + password);
        System.out.println("Role: " + role);

        // Vérification avec LoginService
        if (loginService.authenticateUser(contact, password, role)) {
            System.out.println("La méthode authentificate renvoie true");
            // Redirection selon le rôle
            response.sendRedirect(request.getContextPath() + "/index.jsp");
        } else {
            System.out.println("La méthode authentificate renvoie false");
            // Échec d'authentification : afficher un message d'erreur
            request.setAttribute("error", "Identifiants incorrects ou rôle invalide.");
            request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
        }
    }
}
