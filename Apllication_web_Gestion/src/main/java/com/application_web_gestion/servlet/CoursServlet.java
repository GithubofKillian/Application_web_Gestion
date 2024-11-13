package com.application_web_gestion.servlet;

import com.application_web_gestion.service.CoursService;
import jakarta.servlet.http.HttpServlet;

public class CoursServlet extends HttpServlet {
    private CoursService coursService;

    @Override
    public void init() {
        coursService = new CoursService(); // Initialisation du service pour accéder aux données
    }
}
