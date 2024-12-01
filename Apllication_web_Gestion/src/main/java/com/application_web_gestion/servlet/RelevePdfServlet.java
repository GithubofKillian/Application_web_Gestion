package com.application_web_gestion.servlet;

import com.application_web_gestion.classe.Etudiant;
import com.application_web_gestion.classe.Resultat;
import com.application_web_gestion.classe.HibernateUtil;
import com.application_web_gestion.service.RelevePdfService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.hibernate.Session;

import java.io.IOException;
import java.util.List;

@WebServlet("/relevepdfservlet")
public class RelevePdfServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        String etudiantEmail = request.getParameter("etudiantEmail");

        if (etudiantEmail == null || etudiantEmail.isEmpty()) {
            response.getWriter().write("Erreur : L'email de l'étudiant est requis !");
            return;
        }

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Récupération de l'étudiant par son email
            Etudiant etudiant = session.createQuery("FROM Etudiant WHERE contact = :email", Etudiant.class)
                    .setParameter("email", etudiantEmail)
                    .uniqueResult();

            if (etudiant == null) {
                response.getWriter().write("Erreur : Étudiant non trouvé !");
                return;
            }

            // Utilisation du service RelevePdfService pour récupérer les résultats
            RelevePdfService relevePdfService = new RelevePdfService();
            List<Resultat> resultats = relevePdfService.getResultatsParEtudiant(session, etudiant.getId());

            if (resultats == null || resultats.isEmpty()) {
                request.setAttribute("message", "Aucun résultat trouvé pour cet étudiant.");
                request.getRequestDispatcher("/WEB-INF/views/releve.jsp").forward(request, response);
                return;
            }

            // Calcul de la moyenne
            double moyenne = resultats.stream().mapToDouble(Resultat::getNote).average().orElse(0.0);

            // Génération du PDF
            String filePath = getServletContext().getRealPath("/") + "Releve_" + etudiant.getNom() + "_" + etudiant.getPrenom() + ".pdf";
            relevePdfService.genererRelevePdf(session, etudiant.getId(), filePath, moyenne);

            // Envoi de données à la JSP pour afficher les résultats
            request.setAttribute("etudiant", etudiant);
            request.setAttribute("resultats", resultats);
            request.setAttribute("moyenne", moyenne);
            request.setAttribute("filePath", "Releve_" + etudiant.getNom() + "_" + etudiant.getPrenom() + ".pdf");
            request.getRequestDispatcher("/WEB-INF/views/releve.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().write("Erreur lors de la génération du relevé : " + e.getMessage());
        }
    }
}
