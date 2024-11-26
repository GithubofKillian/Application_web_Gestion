package com.application_web_gestion.servlet;

import com.application_web_gestion.classe.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Par défaut, afficher la page de connexion
        request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String role = request.getParameter("role");

        System.out.println("Username: " + username);
        System.out.println("Password: " + password);
        System.out.println("Role: " + role);

        // Vérification avec Hibernate
        if (authenticateUser(username, password, role)) {
            // Redirection selon le rôle
            response.sendRedirect(request.getContextPath() + "/index.jsp");
        } else {
            // Échec d'authentification : afficher un message d'erreur
            request.setAttribute("error", "Identifiants incorrects ou rôle invalide.");
            request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
        }
    }

    private boolean authenticateUser(String username, String password, String role) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "FROM " + role + " WHERE contact = :username AND mdp = :password";
            Query<?> query = session.createQuery(hql);
            query.setParameter("username", username);
            query.setParameter("password", password);

            return !query.list().isEmpty(); // Retourne true si un utilisateur est trouvé
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
