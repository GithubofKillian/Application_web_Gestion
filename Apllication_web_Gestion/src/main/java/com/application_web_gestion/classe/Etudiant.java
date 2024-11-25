package com.application_web_gestion.classe;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "etudiant")
public class Etudiant implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false) // Ajout de contraintes pour éviter les noms vides
    private String nom;

    @Column(nullable = false) // Ajout de contraintes pour éviter les prénoms vides
    private String prenom;

    private LocalDate dateNaissance;

    @Column(nullable = false, unique = true) // Ajout d'unicité pour le contact
    private String contact;

    @ManyToMany(mappedBy = "etudiants", cascade = CascadeType.PERSIST) // Suppression de CascadeType.ALL
    private List<Cours> coursList = new ArrayList<>();

    @OneToMany(mappedBy = "etudiant", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Resultat> resultats = new ArrayList<>();

    @Column(name = "mdp", nullable = false) // Champ obligatoire
    private String mdp;

    public Etudiant() {}

    public Etudiant(String nom, String prenom, LocalDate dateNaissance, String contact,String mdp) {
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
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
        if (nom == null || nom.trim().isEmpty()) {
            throw new IllegalArgumentException("Le nom ne peut pas être vide.");
        }
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        if (prenom == null || prenom.trim().isEmpty()) {
            throw new IllegalArgumentException("Le prénom ne peut pas être vide.");
        }
        this.prenom = prenom;
    }

    public LocalDate getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(LocalDate dateNaissance) {
        if (dateNaissance != null && dateNaissance.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("La date de naissance ne peut pas être dans le futur.");
        }
        this.dateNaissance = dateNaissance;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        if (contact == null || !contact.trim().matches("^[\\w.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
            throw new IllegalArgumentException("Le contact \"" + contact + "\" n'est pas une adresse e-mail valide.");
        }
        this.contact = contact.trim();
    }

    public List<Cours> getCoursList() {
        return coursList;
    }

    public void setCoursList(List<Cours> coursList) {
        this.coursList = coursList;
    }

    public List<Resultat> getResultats() {
        return resultats;
    }

    public void setResultats(List<Resultat> resultats) {
        this.resultats = resultats;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }
    @Override
    public String toString() {
        String coursNoms = coursList.stream()
                .map(Cours::getNom)
                .collect(Collectors.joining(", "));
        return "Etudiant{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", dateNaissance=" + dateNaissance +
                ", contact='" + contact + '\'' +
                ", cours=[" + (coursNoms.isEmpty() ? "Aucun cours" : coursNoms) + "]" +
                '}';
    }
}
