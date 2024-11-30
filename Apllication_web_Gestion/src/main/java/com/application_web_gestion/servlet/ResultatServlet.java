package com.application_web_gestion.servlet;

import com.application_web_gestion.classe.Resultat;
import com.application_web_gestion.classe.Etudiant;
import com.application_web_gestion.classe.Cours;
import com.application_web_gestion.service.ResultatService;
import com.application_web_gestion.service.EtudiantService;
import com.application_web_gestion.service.CoursService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/resultatservlet")
public class ResultatServlet extends HttpServlet {
    private ResultatService resultatService;
    private EtudiantService etudiantService;
    private CoursService coursService;

    @Override
    public void init() {
        resultatService = new ResultatService();
        etudiantService = new EtudiantService();
        coursService = new CoursService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null) {
            afficherListeResultats(request, response);
            return;
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
        List<Resultat> resultats = resultatService.getAllResultats();
        request.setAttribute("resultats", resultats);
        request.getRequestDispatcher("/WEB-INF/views/listeResultats.jsp").forward(request, response);
    }

    private void afficherFormulaireAjout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Etudiant> etudiants = etudiantService.getAllEtudiants();
        List<Cours> cours = coursService.getAllCours();
        request.setAttribute("etudiants", etudiants);
        request.setAttribute("cours", cours);
        request.getRequestDispatcher("/WEB-INF/views/ajouterResultat.jsp").forward(request, response);
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
            request.getRequestDispatcher("/WEB-INF/views/modifierResultat.jsp").forward(request, response);
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
            request.getRequestDispatcher("/WEB-INF/views/detailResultat.jsp").forward(request, response);
        } else {
            response.getWriter().write("Résultat introuvable !");
        }
    }

    private void ajouterResultat(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Récupérer les informations du formulaire
        Long etudiantId = Long.parseLong(request.getParameter("etudiantId"));
        Long coursId = Long.parseLong(request.getParameter("coursId"));
        double note = Double.parseDouble(request.getParameter("note"));

        // Vérification que la note est valide
        if (note < 0 || note > 20) {
            response.getWriter().write("La note doit être entre 0 et 20.");
            return;
        }

        // Récupérer l'étudiant et le cours à partir de leurs IDs
        Etudiant etudiant = etudiantService.getEtudiant(etudiantId);
        Cours cours = coursService.getCours(coursId);

        if (etudiant != null && cours != null) {
            // Créer un résultat et l'ajouter
            Resultat resultat = new Resultat(etudiant, cours, note);
            resultatService.ajouterResultat(resultat);
            response.sendRedirect("resultatservlet?action=list");
        } else {
            response.getWriter().write("Étudiant ou Cours introuvable !");
        }
    }


    private void afficherMoyenneEtudiant(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Long etudiantId = Long.parseLong(request.getParameter("etudiantId"));
        double moyenne = resultatService.calculerMoyenneParEtudiant(etudiantId);
        response.getWriter().write("Moyenne de l'étudiant : " + moyenne);
    }

    private void afficherMoyenneCours(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Long coursId = Long.parseLong(request.getParameter("coursId"));
        double moyenne = resultatService.calculerMoyenneParCours(coursId);
        response.getWriter().write("Moyenne du cours : " + moyenne);
    }
}
