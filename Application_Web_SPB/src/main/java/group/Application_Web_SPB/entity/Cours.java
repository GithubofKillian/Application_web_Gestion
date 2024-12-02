package group.Application_Web_SPB.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Cours {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;

    @ManyToOne  // Relation Many-to-One
    @JoinColumn(name = "enseignant_id")  // La colonne dans la table Cours pour la clé étrangère
    private Enseignant enseignant;

    @ManyToMany(cascade = CascadeType.ALL) // Relation Many-to-Many
    @JoinTable(
            name = "inscription", // Nom de la table de jonction
            joinColumns = @JoinColumn(name = "cours_id"), // Clé étrangère vers la table Cours
            inverseJoinColumns = @JoinColumn(name = "etudiant_id") // Clé étrangère vers la table Etudiant
    )
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
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", enseignant=" + enseignant +
                ", etudiants=" + etudiants +
                '}';
    }
}
