package com.application_web_gestion.servlet;

import com.application_web_gestion.classe.Enseignant;
import com.application_web_gestion.service.EnseignantService;
import jakarta.servlet.http.HttpServlet;

public class EnseignantServlet extends HttpServlet {
    private EnseignantService enseignantService;

    @Override
    public void init() {
        enseignantService = new EnseignantService(); // Initialisation du service pour accéder aux données
    }
}
