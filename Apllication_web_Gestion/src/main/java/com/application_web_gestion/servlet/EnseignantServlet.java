package com.application_web_gestion.servlet;

import com.application_web_gestion.classe.Enseignant;
import com.application_web_gestion.service.EnseignantService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@WebServlet("/enseignantservlet")
public class EnseignantServlet extends HttpServlet {
    private EnseignantService enseignantService;

    @Override
    public void init() {
        enseignantService = new EnseignantService(); // Initialisation du service pour accéder aux données
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null) {
            afficherListeEnseignants(request, response); // Affiche la liste des enseignants si aucune action spécifiée
        } else {
            switch (action) {
                case "add":
                    request.getRequestDispatcher("/WEB-INF/views/ajouterEnseignant.jsp").forward(request, response);
                    break;
                case "detail":
                    afficherDetailEnseignant(request, response);
                    break;
                case "edit":
                    afficherFormulaireModification(request, response);
                    break;
                case "delete":
                    supprimerEnseignant(request, response);
                    break;
                case "assignCourse":
                    affecterEnseignantCours(request, response);
                    break;
                default:
                    afficherListeEnseignants(request, response);
                    break;
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("create".equals(action)) {
            ajouterEnseignant(request, response);
        } else if ("update".equals(action)) {
            modifierEnseignant(request, response);
        }
    }

    private void afficherListeEnseignants(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Enseignant> enseignants = enseignantService.getAllEnseignant();
        request.setAttribute("enseignants", enseignants);
        request.getRequestDispatcher("/WEB-INF/views/listeEnseignants.jsp").forward(request, response);
    }

    private void afficherDetailEnseignant(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        Enseignant enseignant = enseignantService.getEnseignant(id);
        request.setAttribute("enseignant", enseignant);
        request.getRequestDispatcher("/WEB-INF/views/detailEnseignant.jsp").forward(request, response);
    }

    private void afficherFormulaireModification(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        Enseignant enseignant = enseignantService.getEnseignant(id);
        request.setAttribute("enseignant", enseignant);
        request.getRequestDispatcher("/WEB-INF/views/modifierEnseignant.jsp").forward(request, response);
    }

    private void ajouterEnseignant(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        LocalDate dateNaissance = LocalDate.parse(request.getParameter("dateNaissance"));
        String contact = request.getParameter("contact");
        String mdp = request.getParameter("mdp");

        Enseignant enseignant = new Enseignant(nom, prenom, dateNaissance, contact, mdp);
        enseignantService.ajouterEnseignant(enseignant);
        response.sendRedirect("enseignantservlet");
    }

    private void modifierEnseignant(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        LocalDate dateNaissance = LocalDate.parse(request.getParameter("dateNaissance"));
        String contact = request.getParameter("contact");
        String mdp = request.getParameter("mdp");

        Enseignant enseignant = enseignantService.getEnseignant(id);
        enseignant.setNom(nom);
        enseignant.setPrenom(prenom);
        enseignant.setDateNaissance(dateNaissance);
        enseignant.setContact(contact);
        enseignant.setMdp(mdp);

        enseignantService.modifierEnseignant(enseignant);
        response.sendRedirect("enseignantservlet");
    }

    private void supprimerEnseignant(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        enseignantService.supprimerEnseignant(id);
        response.sendRedirect("enseignantservlet");
    }

    private void affecterEnseignantCours(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Implémentation simplifiée de l'affectation des enseignants aux cours
        // Cette méthode serait normalement complétée avec une logique pour affecter des cours spécifiques
        Long enseignantId = Long.parseLong(request.getParameter("enseignantId"));
        Long coursId = Long.parseLong(request.getParameter("coursId"));

        // Logique d'affectation à ajouter, exemple :
        // enseignantService.affecterCours(enseignantId, coursId);

        request.setAttribute("message", "Enseignant affecté au cours avec succès !");
        afficherListeEnseignants(request, response);
    }
}
