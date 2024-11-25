package com.application_web_gestion.servlet;

import com.application_web_gestion.classe.Cours;
import com.application_web_gestion.classe.Enseignant;
import com.application_web_gestion.classe.Etudiant;
import com.application_web_gestion.service.CoursService;
import com.application_web_gestion.service.EnseignantService;
import com.application_web_gestion.service.EtudiantService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/coursservlet")
public class CoursServlet extends HttpServlet {
    private CoursService coursService;
    private EnseignantService enseignantService;
    private EtudiantService etudiantService;

    @Override
    public void init() {
        coursService = new CoursService();
        enseignantService = new EnseignantService();
        etudiantService = new EtudiantService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null) {
            afficherListeCours(request, response);
        } else {
            switch (action) {
                case "add":
                    afficherFormulaireCreation(request, response);
                    break;
                case "detail":
                    afficherDetailCours(request, response);
                    break;
                case "edit":
                    afficherFormulaireModification(request, response);
                    break;
                case "delete":
                    supprimerCours(request, response);
                    break;
                default:
                    afficherListeCours(request, response);
                    break;
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        switch (action) {
            case "create":
                ajouterCours(request, response);
                break;
            case "update":
                modifierCours(request, response);
                break;
        }
    }


    private void afficherListeCours(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Cours> cours = coursService.getAllCours();
        request.setAttribute("cours", cours);
        request.getRequestDispatcher("/WEB-INF/views/listeCours.jsp").forward(request, response);
    }

    private void afficherFormulaireCreation(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Enseignant> enseignants = enseignantService.getAllEnseignant();
        List<Etudiant> etudiants = etudiantService.getAllEtudiants();
        request.setAttribute("enseignants", enseignants);
        request.setAttribute("etudiants", etudiants);
        request.getRequestDispatcher("/WEB-INF/views/ajouterCours.jsp").forward(request, response);
    }

    private void afficherDetailCours(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        Cours cours = coursService.getCours(id);
        request.setAttribute("cours", cours);
        request.getRequestDispatcher("/WEB-INF/views/detailCours.jsp").forward(request, response);
    }

    private void afficherFormulaireModification(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        Cours cours = coursService.getCours(id);
        List<Enseignant> enseignants = enseignantService.getAllEnseignant();
        List<Etudiant> etudiants = etudiantService.getAllEtudiants();
        request.setAttribute("cours", cours);
        request.setAttribute("enseignants", enseignants);
        request.setAttribute("etudiants", etudiants);
        request.getRequestDispatcher("/WEB-INF/views/modifierCours.jsp").forward(request, response);
    }

    private void ajouterCours(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String nom = request.getParameter("nom");
        Long enseignantId = Long.parseLong(request.getParameter("enseignant"));
        Enseignant enseignant = enseignantService.getEnseignant(enseignantId);

        List<Etudiant> etudiants = new ArrayList<>();
        for (String etudiantId : request.getParameterValues("etudiants")) {
            Etudiant etudiant = etudiantService.getEtudiant(Long.parseLong(etudiantId));
            etudiants.add(etudiant);
        }

        Cours cours = new Cours(nom, enseignant);
        cours.setEtudiants(etudiants);
        coursService.ajouterCours(cours);

        response.sendRedirect("coursservlet");
    }

    private void modifierCours(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        String nom = request.getParameter("nom");
        Long enseignantId = Long.parseLong(request.getParameter("enseignant"));
        Enseignant enseignant = enseignantService.getEnseignant(enseignantId);

        List<Etudiant> etudiants = new ArrayList<>();
        for (String etudiantId : request.getParameterValues("etudiants")) {
            Etudiant etudiant = etudiantService.getEtudiant(Long.parseLong(etudiantId));
            etudiants.add(etudiant);
        }

        Cours cours = coursService.getCours(id);
        cours.setNom(nom);
        cours.setEnseignant(enseignant);
        cours.setEtudiants(etudiants);
        coursService.modifierCours(cours);

        response.sendRedirect("coursservlet");
    }

    private void supprimerCours(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        coursService.supprimerCours(id);
        response.sendRedirect("coursservlet");
    }
}
