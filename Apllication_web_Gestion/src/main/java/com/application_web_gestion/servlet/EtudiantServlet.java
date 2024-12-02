package com.application_web_gestion.servlet;

import com.application_web_gestion.classe.Etudiant;
import com.application_web_gestion.service.EtudiantService;
import com.application_web_gestion.service.EmailService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@WebServlet("/etudiantservlet")
public class EtudiantServlet extends HttpServlet {
    private EtudiantService etudiantService;
    private EmailService emailService;

    @Override
    public void init() {
        etudiantService = new EtudiantService(); // Initialisation du service pour accéder aux données
        etudiantService = new EtudiantService();
        emailService = new EmailService("nestifyweb@gmail.com", "myuw ztfg gmhv joes");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null) {
            afficherListeEtudiants(request, response);
            return;
        }

        switch (action) {
            case "detail" -> afficherDetailEtudiant(request, response);
            case "edit" -> afficherFormulaireModification(request, response);
            case "delete" -> supprimerEtudiant(request, response);
            case "search" -> rechercherEtudiantsParNom(request, response);
            case "add" -> request.getRequestDispatcher("/WEB-INF/views/Etudiant/ajouterEtudiant.jsp").forward(request, response);
            default -> afficherListeEtudiants(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("create".equals(action)) {
            ajouterEtudiant(request, response);
        } else if ("update".equals(action)) {
            modifierEtudiant(request, response);
        }
    }

    private void rechercherEtudiantsParNom(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nomRecherche = request.getParameter("nomRecherche");
        List<Etudiant> etudiants = etudiantService.rechercherEtudiantsParNom(nomRecherche.trim());
        request.setAttribute("etudiants", etudiants);
        request.getRequestDispatcher("/WEB-INF/views/Etudiant/listeEtudiants.jsp").forward(request, response);
    }

    private void afficherDetailEtudiant(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id = parseLongParameter(request.getParameter("id"));
        Etudiant etudiant = etudiantService.getEtudiant(id);
        request.setAttribute("etudiant", etudiant);
        request.getRequestDispatcher("/WEB-INF/views/Etudiant/detailEtudiant.jsp").forward(request, response);
    }

    private void afficherListeEtudiants(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Etudiant> etudiants = etudiantService.getAllEtudiants();
        request.setAttribute("etudiants", etudiants);
        request.getRequestDispatcher("/WEB-INF/views/Etudiant/listeEtudiants.jsp").forward(request, response);
    }

    private void afficherFormulaireModification(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id = parseLongParameter(request.getParameter("id"));
        Etudiant etudiant = etudiantService.getEtudiant(id);
        request.setAttribute("etudiant", etudiant);
        request.getRequestDispatcher("/WEB-INF/views/Etudiant/modifierEtudiant.jsp").forward(request, response);
    }

    private void ajouterEtudiant(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Etudiant etudiant = new Etudiant(
                request.getParameter("nom"),
                request.getParameter("prenom"),
                LocalDate.parse(request.getParameter("dateNaissance")),
                request.getParameter("contact"),
                request.getParameter("mdp")
        );
        etudiantService.ajouterEtudiant(etudiant);

        // Envoi d'un email de bienvenue
        String destinataire = etudiant.getContact();
        String sujet = "Bienvenue à notre université !";
        String contenu = "Bonjour " + etudiant.getPrenom() + " " + etudiant.getNom() + ",\n\n" +
                "Nous vous souhaitons la bienvenue dans notre université. Votre compte a été créé avec succès.\n\n" +
                "Cordialement,\nL'équipe de Gestion Scolarité.";
        emailService.envoyerEmail(destinataire, sujet, contenu);

        response.sendRedirect("etudiantservlet");
    }


    private void modifierEtudiant(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Long id = parseLongParameter(request.getParameter("id"));
        Etudiant etudiant = etudiantService.getEtudiant(id);

        if (etudiant != null) {
            String ancienContact = etudiant.getContact(); // Enregistrer l'email précédent, si nécessaire

            etudiant.setNom(request.getParameter("nom"));
            etudiant.setPrenom(request.getParameter("prenom"));
            etudiant.setContact(request.getParameter("contact").trim());
            etudiant.setMdp(request.getParameter("mdp"));

            etudiantService.modifierEtudiant(etudiant);

            // Envoi d'un email de notification pour modification
            String destinataire = etudiant.getContact();
            String sujet = "Mise à jour de vos informations";
            String contenu = "Bonjour " + etudiant.getPrenom() + " " + etudiant.getNom() + ",\n\n" +
                    "Vos informations ont été mises à jour avec succès.\n" +
                    "Si ce changement n'a pas été effectué par vous, veuillez nous contacter immédiatement.\n\n" +
                    "Cordialement,\nL'équipe de Gestion Scolarité.";
            emailService.envoyerEmail(destinataire, sujet, contenu);
        }

        response.sendRedirect("etudiantservlet");
    }


    private void supprimerEtudiant(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Long id = parseLongParameter(request.getParameter("id"));
        etudiantService.supprimerEtudiant(id);
        response.sendRedirect("etudiantservlet");
    }

    private Long parseLongParameter(String parameter) {
        try {
            return Long.parseLong(parameter);
        } catch (NumberFormatException e) {
            return null; // Peut être adapté pour rediriger vers une page d'erreur.
        }
    }
}
