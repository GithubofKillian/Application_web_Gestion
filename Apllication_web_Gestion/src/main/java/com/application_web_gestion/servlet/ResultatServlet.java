package com.application_web_gestion.servlet;

import com.application_web_gestion.classe.*;
import com.application_web_gestion.service.ResultatService;
import com.application_web_gestion.service.EtudiantService;
import com.application_web_gestion.service.CoursService;
import com.application_web_gestion.service.EmailService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.hibernate.Session;

import java.io.IOException;
import java.util.List;

@WebServlet("/resultatservlet")
public class ResultatServlet extends HttpServlet {
    private ResultatService resultatService;
    private EtudiantService etudiantService;
    private CoursService coursService;
    private EmailService emailService;

    Session session = HibernateUtil.getSessionFactory().openSession();

    @Override
    public void init() {
        resultatService = new ResultatService();
        etudiantService = new EtudiantService();
        coursService = new CoursService();
        emailService = new EmailService("nestifyweb@gmail.com", "myuw ztfg gmhv joes");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null) {
            // Récupération de la variable de session 'role'
            HttpSession sessionweb = request.getSession();
            String role = (String) sessionweb.getAttribute("userRole");
            String contact = (String) sessionweb.getAttribute("userContact");
            switch (role) {
                case "Etudiant":
                    afficherListeResultatsUnEleve(request, response, contact);
                    return;

                case "Enseignant":
                    afficherListeResultats(request, response);
                    return;

                case "Admin":
                    afficherListeResultats(request, response);
                    return;

                default:
                    // Action par défaut si le rôle n'est pas reconnu
                    response.sendError(HttpServletResponse.SC_FORBIDDEN, "Accès non autorisé.");
                    return;
            }
        }

        switch (action) {
            case "add":
                afficherFormulaireAjout(request, response);
                break;
            case "edit":
                afficherFormulaireModification(request, response);
                break;
            case "delete":
                supprimerResultat(request, response);
                break;
            case "detail":
                afficherDetailResultat(request, response);
                break;
            case "moyenneEtudiant":
                afficherMoyenneEtudiant(request, response);
                break;
            case "moyenneCours":
                afficherMoyenneCours(request, response);
                break;
            default:
                afficherListeResultats(request, response);
                break;
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String action = request.getParameter("action");

        if ("create".equals(action)) {
            ajouterResultat(request, response);
        } else if ("update".equals(action)) {
            modifierResultat(request, response);
        }
    }


    private void afficherListeResultats(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Récupération de tous les résultats
        List<Resultat> resultats = resultatService.getAllResultats();
        // Récupération de tous les étudiants
        List<Etudiant> etudiants = etudiantService.getAllEtudiants();

        // Passage des résultats et des étudiants à la JSP
        request.setAttribute("resultats", resultats);
        request.setAttribute("etudiants", etudiants);  // Ajouter la liste des étudiants ici

        // Affichage de la JSP
        request.getRequestDispatcher("/WEB-INF/views/Resultat/listeResultats.jsp").forward(request, response);
    }

    private void afficherListeResultatsUnEleve(HttpServletRequest request, HttpServletResponse response,String contact) throws ServletException, IOException {

        Etudiant etudiant = session.createQuery("FROM Etudiant WHERE contact = :email", Etudiant.class)
                .setParameter("email", contact)
                .uniqueResult();

        if (etudiant == null) {
            response.getWriter().write("Erreur : Étudiant non trouvé !");
            return;
        }
        List<Resultat> resultats = resultatService.getResultatsParEtudiant(session, etudiant.getId());

        // Passage des résultats et des étudiants à la JSP
        request.setAttribute("resultats", resultats);

        // Affichage de la JSP
        request.getRequestDispatcher("/WEB-INF/views/Resultat/listeResultats.jsp").forward(request, response);
    }

    private void afficherFormulaireAjout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Etudiant> etudiants = etudiantService.getAllEtudiants();
        List<Cours> cours = coursService.getAllCours();
        request.setAttribute("etudiants", etudiants);
        request.setAttribute("cours", cours);
        request.getRequestDispatcher("/WEB-INF/views/Resultat/ajouterResultat.jsp").forward(request, response);
    }

    private void afficherFormulaireModification(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long resultatId = Long.parseLong(request.getParameter("id"));
        Resultat resultat = resultatService.getResultat(resultatId);
        if (resultat != null) {
            List<Etudiant> etudiants = etudiantService.getAllEtudiants();
            List<Cours> cours = coursService.getAllCours();
            request.setAttribute("resultat", resultat);
            request.setAttribute("etudiants", etudiants);
            request.setAttribute("cours", cours);
            request.getRequestDispatcher("/WEB-INF/views/Resultat/modifierResultat.jsp").forward(request, response);
        } else {
            response.getWriter().write("Résultat introuvable !");
        }
    }

    private void modifierResultat(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Long resultatId = Long.parseLong(request.getParameter("id"));
        Double nouvelleNote = Double.parseDouble(request.getParameter("note"));

        Resultat resultat = resultatService.getResultat(resultatId);
        if (resultat != null) {
            resultat.setNote(nouvelleNote);
            resultatService.modifierResultat(resultat);
            response.sendRedirect("resultatservlet?action=list");
        } else {
            response.getWriter().write("Résultat introuvable !");
        }
    }

    private void supprimerResultat(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Long resultatId = Long.parseLong(request.getParameter("id"));
        resultatService.supprimerResultat(resultatId);
        response.sendRedirect("resultatservlet?action=list");
    }

    private void afficherDetailResultat(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long resultatId = Long.parseLong(request.getParameter("id"));
        Resultat resultat = resultatService.getResultat(resultatId);
        if (resultat != null) {
            request.setAttribute("resultat", resultat);
            request.getRequestDispatcher("/WEB-INF/views/Resultat/detailResultat.jsp").forward(request, response);
        } else {
            response.getWriter().write("Résultat introuvable !");
        }
    }

    private void ajouterResultat(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Long etudiantId = Long.parseLong(request.getParameter("etudiantId"));
        Long coursId = Long.parseLong(request.getParameter("coursId"));
        double note = Double.parseDouble(request.getParameter("note"));

        Etudiant etudiant = etudiantService.getEtudiant(etudiantId);
        Cours cours = coursService.getCours(coursId);

        if (etudiant != null && cours != null) {
            Resultat resultat = new Resultat(etudiant, cours, note);
            resultatService.ajouterResultat(resultat);

            // Envoi de l'email via EmailService
            String destinataire = etudiant.getContact(); // Email de l'étudiant
            String sujet = "Nouvelle Note Ajoutée";
            String contenu = "Bonjour " + etudiant.getNom() + " " + etudiant.getPrenom() +
                    ",\n\nVotre note pour le cours " + cours.getNom() +
                    " a été enregistrée avec succès : " + note + ".\n\nCordialement,\nL'équipe de Gestion Scolarité.";
            emailService.envoyerEmail(destinataire, sujet, contenu);
        }

        response.sendRedirect("resultatservlet");
    }



    private void afficherMoyenneEtudiant(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Long etudiantId = Long.parseLong(request.getParameter("etudiantId"));
        double moyenne = resultatService.calculerMoyenneParEtudiant(session, etudiantId);
        response.getWriter().write("Moyenne de l'étudiant : " + moyenne);
    }

    private void afficherMoyenneCours(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Long coursId = Long.parseLong(request.getParameter("coursId"));
        double moyenne = resultatService.calculerMoyenneParCours(coursId);
        response.getWriter().write("Moyenne du cours : " + moyenne);
    }

}
