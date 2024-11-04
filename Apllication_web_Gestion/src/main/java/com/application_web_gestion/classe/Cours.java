package com.application_web_gestion.classe;

import java.util.ArrayList;
import java.util.List;

public class Cours {

    private String nom;
    private Enseignant enseignant;
    private List<Etudiant> etudiants;

    // Constructeur sans paramètres
    public Cours() {
        this.etudiants = new ArrayList<>();
    }

    // Constructeur avec paramètres
    public Cours(String nom, Enseignant enseignant) {
        this.nom = nom;
        this.enseignant = enseignant;
        this.etudiants = new ArrayList<>();
    }

    // Getters et Setters
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Enseignant getEnseignant() {
        return enseignant;
    }

    public void setEnseignant(Enseignant enseignant) {
        this.enseignant = enseignant;
    }

    public List<Etudiant> getEtudiants() {
        return etudiants;
    }

    public void setEtudiants(List<Etudiant> etudiants) {
        this.etudiants = etudiants;
    }

    // Méthode pour ajouter un étudiant à la liste des étudiants
    public void ajouterEtudiant(Etudiant etudiant) {
        this.etudiants.add(etudiant);
    }

    // Méthode pour supprimer un étudiant de la liste des étudiants
    public void supprimerEtudiant(Etudiant etudiant) {
        this.etudiants.remove(etudiant);
    }

    // Méthode pour afficher les informations du cours
    @Override
    public String toString() {
        return "Cours{" +
                "nom='" + nom + '\'' +
                ", enseignant=" + enseignant +
                ", etudiants=" + etudiants +
                '}';
    }
}
