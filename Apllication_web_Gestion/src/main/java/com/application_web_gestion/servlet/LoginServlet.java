package com.application_web_gestion.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.application_web_gestion.classe.Enseignant;
import com.application_web_gestion.classe.Etudiant;
import com.application_web_gestion.classe.Admin;
import com.application_web_gestion.classe.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;


@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String role = request.getParameter("role");

        // Vérification avec Hibernate
        if (authenticateUser(username, password, role)) {
            switch (role.toLowerCase()) {
                case "admin":
                    response.sendRedirect(request.getContextPath() + "/index.jsp");
                    break;
                case "etudiant":
                    response.sendRedirect(request.getContextPath() + "/index.jsp");
                    break;
                case "enseignant":
                    response.sendRedirect(request.getContextPath() + "/index.jsp");
                    break;
                default:
                    response.sendRedirect(request.getContextPath() + "/views/login.jsp?error=roleinvalide");
                    break;
            }
        } else {
            // Redirection en cas d'échec d'authentification
            response.sendRedirect(request.getContextPath() + "/views/login.jsp?error=authentification");
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