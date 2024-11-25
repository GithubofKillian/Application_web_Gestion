package com.application_web_gestion.servlet;

import org.hibernate.Session;
import org.hibernate.query.Query;
import com.application_web_gestion.classe.Etudiant;
import com.application_web_gestion.classe.Enseignant;
import com.application_web_gestion.classe.Admin;
import com.application_web_gestion.classe.HibernateUtil;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String role = request.getParameter("role");

        // Simulation de l'appel à la base de données en fonction du rôle
        boolean isAuthenticated = authenticateUser(username, password, role);

        if (isAuthenticated) {
            // Redirection en fonction du rôle
            switch (role.toLowerCase()) {
                case "admin":
                    response.sendRedirect(request.getContextPath() +"/index.jsp");
                    break;
                case "etudiant":
                    response.sendRedirect(request.getContextPath() +"/index.jsp");
                    break;
                case "enseignant":
                    response.sendRedirect(request.getContextPath() +"/index.jsp");
                    break;
            }
        } else {
            // Échec d'authentification
            response.sendRedirect(request.getContextPath() +"/views/login.jsp?error=authentification");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
    }

    private boolean authenticateUser(String username, String password, String role) {
        // Utiliser une session Hibernate
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "FROM " + role + " WHERE contact = :username AND mdp = :password";
            Query query = session.createQuery(hql);
            query.setParameter("username", username);
            query.setParameter("password", password);

            List<?> result = query.list(); // Exécute la requête
            return !result.isEmpty(); // Retourne true si un utilisateur est trouvé
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
