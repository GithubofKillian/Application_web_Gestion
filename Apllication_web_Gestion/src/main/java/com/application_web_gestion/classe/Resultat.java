package com.application_web_gestion.classe;

import java.util.ArrayList;
import java.util.List;

public class Resultat {

    private Etudiant etudiant;
    private Cours cours;
    private double note;

    // Constructeur sans paramètres
    public Resultat() {}

    // Constructeur avec paramètres
    public Resultat(Etudiant etudiant, Cours cours, double note) {
        this.etudiant = etudiant;
        this.cours = cours;
        this.note = note;
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

    public double getNote() {
        return note;
    }

    public void setNote(double note) {
        this.note = note;
    }

    // Méthode toString pour afficher les informations du résultat
    @Override
    public String toString() {
        return "Resultat{" +
                "etudiant=" + etudiant.getNom() + " " + etudiant.getPrenom() +
                ", cours=" + cours.getNom() +
                ", note=" + note +
                '}';
    }
}
