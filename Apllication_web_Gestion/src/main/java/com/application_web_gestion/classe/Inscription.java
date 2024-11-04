package com.application_web_gestion.classe;

import java.time.LocalDate;

public class Inscription {

    private Etudiant etudiant;
    private Cours cours;
    private LocalDate dateInscription;

    // Constructeur sans paramètres
    public Inscription() {}

    // Constructeur avec paramètres
    public Inscription(Etudiant etudiant, Cours cours, LocalDate dateInscription) {
        this.etudiant = etudiant;
        this.cours = cours;
        this.dateInscription = dateInscription;
    }

    // Getters et Setters
    public Etudiant getEtudiant() {
        return etudiant;
    }

    public void setEtudiant(Etudiant etudiant) {
        this.etudiant = etudiant;
    }

    public Cours getCours() {
        return cours;
    }

    public void setCours(Cours cours) {
        this.cours = cours;
    }

    public LocalDate getDateInscription() {
        return dateInscription;
    }

    public void setDateInscription(LocalDate dateInscription) {
        this.dateInscription = dateInscription;
    }

    // Méthode toString pour afficher les informations de l'inscription
    @Override
    public String toString() {
        return "Inscription{" +
                "etudiant=" + etudiant.getNom() + " " + etudiant.getPrenom() +
                ", cours=" + cours.getNom() +
                ", dateInscription=" + dateInscription +
                '}';
    }
}
