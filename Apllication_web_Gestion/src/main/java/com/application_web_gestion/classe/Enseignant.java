package com.application_web_gestion.classe;

import java.time.LocalDate;
import javax.persistence.*;

@Entity
public class Enseignant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Génère un ID unique auto-incrémenté
    private Long id;

    @Column(name = "nom", nullable = false) // Champ obligatoire
    private String nom;

    @Column(name = "prenom", nullable = false) // Champ obligatoire
    private String prenom;

    private LocalDate dateNaissance;

    @Column(name = "contact")
    private String contact;

    // Constructeur sans paramètres
    public Enseignant() {}

    // Constructeur avec paramètres
    public Enseignant(String nom, String prenom, LocalDate dateNaissance, String contact) {
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.contact = contact;
    }

    // Getters et Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public LocalDate getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(LocalDate dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    // Méthode toString pour afficher les informations de l'enseignant
    @Override
    public String toString() {
        return "Enseignant{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", dateNaissance=" + dateNaissance +
                ", contact='" + contact + '\'' +
                '}';
    }
}
