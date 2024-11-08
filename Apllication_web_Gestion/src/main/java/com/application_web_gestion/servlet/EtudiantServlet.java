package com.application_web_gestion.servlet;

import com.application_web_gestion.classe.Etudiant;
import com.application_web_gestion.service.EtudiantService;
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

    @Override
    public void init() {
        etudiantService = new EtudiantService(); // Initialisation du service pour accéder aux données
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null) {
            afficherListeEtudiants(request, response); // Si aucune paramètre (action), on affiche la liste des étudiants
        } else {
            switch (action) {
                case "add": // Envoie vers le formulaire de création  d'un étudiant
                    request.getRequestDispatcher("/WEB-INF/views/ajouterEtudiant.jsp").forward(request, response);
                    break;
                case "detail": // Affichage des détails d'un étudiant (Pas fait)
                    afficherDetailEtudiant(request, response);
                    break;
                case "edit": // Affichage du formulaire de modification
                    afficherFormulaireModification(request, response);
                    break;
                case "delete": // Suppression d'un étudiant
                    supprimerEtudiant(request, response);
                    break;
                default:
                    afficherListeEtudiants(request, response); // Si action inconnu, afficher la liste
                    break;
            }
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

    /// Méthode de base, recoit toute les données avec EtudiantService et les envoie au jsp
    private void afficherListeEtudiants(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Etudiant> etudiants = etudiantService.getAllEtudiants(); // Récupérer tous les étudiants
        request.setAttribute("etudiants", etudiants); // Mettre la liste des étudiants en attribut
        request.getRequestDispatcher("/WEB-INF/views/listeEtudiants.jsp").forward(request, response); // Rediriger vers la JSP
    }

    private void afficherDetailEtudiant(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        Etudiant etudiant = etudiantService.getEtudiant(id); // Récupérer l'étudiant par son ID
        request.setAttribute("etudiant", etudiant); // Mettre l'étudiant en attribut
        request.getRequestDispatcher("/WEB-INF/views/detailEtudiant.jsp").forward(request, response); // Afficher les détails
    }

    /// Envoie vers le form de modification avec les données de l'étudiant
    private void afficherFormulaireModification(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        Etudiant etudiant = etudiantService.getEtudiant(id);
        request.setAttribute("etudiant", etudiant);
        request.getRequestDispatcher("/WEB-INF/views/modifierEtudiant.jsp").forward(request, response);
    }

    /// Récupère le form de création créé l'étudiant et l'envoie dans la bdd avec EtudiantService
    private void ajouterEtudiant(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        LocalDate dateNaissance = LocalDate.parse(request.getParameter("dateNaissance"));
        String contact = request.getParameter("contact");

        Etudiant etudiant = new Etudiant(nom, prenom, dateNaissance, contact);
        etudiantService.ajouterEtudiant(etudiant);
        response.sendRedirect("etudiantservlet");
    }

    /// Récupère le form de modification de l'étudiant et l'envoie dans la bdd avec EtudiantService
    private void modifierEtudiant(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        LocalDate dateNaissance = LocalDate.parse(request.getParameter("dateNaissance"));
        String contact = request.getParameter("contact");

        Etudiant etudiant = etudiantService.getEtudiant(id);
        etudiant.setNom(nom);
        etudiant.setPrenom(prenom);
        etudiant.setDateNaissance(dateNaissance);
        etudiant.setContact(contact);

        etudiantService.modifierEtudiant(etudiant);
        response.sendRedirect("etudiantservlet");
    }

    /// Récupère l'id de l'étudiant et le supprime avec EtudiantService
    private void supprimerEtudiant(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        etudiantService.supprimerEtudiant(id);
        response.sendRedirect("etudiantservlet");
    }
}
