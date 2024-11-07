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
            afficherListeEtudiants(request, response); // Si aucune action, on affiche la liste
        } else {
            switch (action) {
                case "detail": // Affichage des détails d'un étudiant
                    afficherDetailEtudiant(request, response);
                    break;
                case "edit": // Affichage du formulaire de modification
                    afficherFormulaireModification(request, response);
                    break;
                case "delete": // Suppression d'un étudiant
                    supprimerEtudiant(request, response);
                    break;
                default:
                    afficherListeEtudiants(request, response); // Action par défaut, afficher la liste
                    break;
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("create".equals(action)) {
            ajouterEtudiant(request, response); // Ajouter un étudiant
        } else if ("update".equals(action)) {
            modifierEtudiant(request, response); // Modifier un étudiant
        }
    }

    private void afficherListeEtudiants(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Etudiant> etudiants = etudiantService.getAllEtudiants(); // Récupérer tous les étudiants
        request.setAttribute("etudiants", etudiants); // Mettre la liste des étudiants en attribut
        request.getRequestDispatcher("/WEB-INF/views/listeEtudiants.jsp").forward(request, response); // Rediriger vers la JSP
    }

    private void afficherDetailEtudiant(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Etudiant etudiant = etudiantService.getEtudiant(id); // Récupérer l'étudiant par son ID
        request.setAttribute("etudiant", etudiant); // Mettre l'étudiant en attribut
        request.getRequestDispatcher("/WEB-INF/views/detailEtudiant.jsp").forward(request, response); // Afficher les détails
    }

    private void afficherFormulaireModification(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Etudiant etudiant = etudiantService.getEtudiant(id); // Récupérer l'étudiant à modifier
        request.setAttribute("etudiant", etudiant); // Mettre l'étudiant en attribut pour pré-remplir le formulaire
        request.getRequestDispatcher("/WEB-INF/views/formulaireEtudiant.jsp").forward(request, response); // Afficher le formulaire de modification
    }

    private void ajouterEtudiant(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        LocalDate dateNaissance = LocalDate.parse(request.getParameter("dateNaissance"));
        String contact = request.getParameter("contact");

        Etudiant etudiant = new Etudiant(nom, prenom, dateNaissance, contact); // Créer un nouvel étudiant
        etudiantService.ajouterEtudiant(etudiant); // Ajouter l'étudiant à la base de données
        response.sendRedirect("etudiants"); // Rediriger vers la liste des étudiants
    }

    private void modifierEtudiant(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        LocalDate dateNaissance = LocalDate.parse(request.getParameter("dateNaissance"));
        String contact = request.getParameter("contact");

        Etudiant etudiant = etudiantService.getEtudiant(id); // Récupérer l'étudiant existant
        etudiant.setNom(nom);
        etudiant.setPrenom(prenom);
        etudiant.setDateNaissance(dateNaissance);
        etudiant.setContact(contact);

        etudiantService.modifierEtudiant(etudiant); // Mettre à jour les informations de l'étudiant
        response.sendRedirect("etudiants"); // Rediriger vers la liste des étudiants
    }

    private void supprimerEtudiant(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        etudiantService.supprimerEtudiant(id); // Supprimer l'étudiant
        response.sendRedirect("etudiants"); // Rediriger vers la liste des étudiants
    }
}
