package com.application_web_gestion.classe;

import javax.persistence.*;

@Entity
public class Resultat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Génère un ID unique auto-incrémenté
    private Long id;

    @ManyToOne(optional = false) // Un résultat est lié à un étudiant (obligatoire)
    private Etudiant etudiant;

    @ManyToOne(optional = false) // Un résultat est lié à un cours (obligatoire)
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
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
                "id=" + id +
                ", etudiant=" + etudiant.getNom() + " " + etudiant.getPrenom() +
                ", cours=" + cours.getNom() +
                ", note=" + note +
                '}';
    }
}
