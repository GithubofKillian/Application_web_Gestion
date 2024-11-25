package com.application_web_gestion.classe;

import javax.persistence.*;


@Entity
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Génère un ID unique auto-incrémenté
    private Long id;

    @Column(name = "nom", nullable = false) // Champ obligatoire
    private String nom;

    @Column(name = "prenom", nullable = false) // Champ obligatoire
    private String prenom;

    @Column(name = "contact")
    private String contact;

    @Column(name = "mdp", nullable = false)
    private String mdp;

    public Admin(String nom, String prenom, String contact, String mdp) {
        this.nom = nom;
        this.prenom = prenom;
        this.contact = contact;
        this.mdp = mdp;
    }
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

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    // Méthode toString pour afficher les informations de l'admin
    @Override
    public String toString() {
        return "Admin{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", contact='" + contact + '\'' +
                '}';
    }
}
