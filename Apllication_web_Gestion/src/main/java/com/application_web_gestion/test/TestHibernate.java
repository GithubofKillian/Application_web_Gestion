package com.application_web_gestion.test;

import com.application_web_gestion.classe.Cours;
import com.application_web_gestion.classe.Enseignant;
import com.application_web_gestion.classe.Etudiant;
import com.application_web_gestion.classe.Inscription;
import com.application_web_gestion.classe.Resultat;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.time.LocalDate;

public class TestHibernate {

    public static void main(String[] args) {
        // Configuration de Hibernate
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")  // Charge la configuration Hibernate
                .addAnnotatedClass(Etudiant.class) // Ajout des classes annotées
                .addAnnotatedClass(Cours.class)
                .addAnnotatedClass(Enseignant.class) // Ajoutez ici Enseignant
                .addAnnotatedClass(Inscription.class)
                .addAnnotatedClass(Resultat.class)
                .buildSessionFactory();

        // Création de la session Hibernate
        Session session = factory.getCurrentSession();

        try {
            // 1. Créer un nouvel enseignant
            Enseignant enseignant = new Enseignant("Martin", "Pierre", LocalDate.of(1980, 3, 25), "pierre.martin@example.com");

            // 2. Créer un nouvel étudiant
            Etudiant etudiant = new Etudiant("Dupont", "Jean", LocalDate.of(1998, 5, 14), "jean.dupont@example.com");

            // 3. Créer un cours et assigner l'enseignant à ce cours
            Cours cours = new Cours("Mathématiques", enseignant);

            // 4. Créer une inscription
            Inscription inscription = new Inscription(etudiant, cours, LocalDate.now());

            // 5. Créer un résultat
            Resultat resultat = new Resultat(etudiant, cours, 15.5);

            // Démarrer une transaction
            session.beginTransaction();

            // Sauvegarder l'enseignant, l'étudiant, le cours, l'inscription, et le résultat
            session.save(enseignant);  // N'oubliez pas de sauvegarder l'enseignant !
            session.save(etudiant);
            session.save(cours);
            session.save(inscription);
            session.save(resultat);

            // Commit la transaction
            session.getTransaction().commit();

            System.out.println("Test réussi ! Données insérées dans la base de données.");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Fermer la session et le factory
            factory.close();
        }
    }
}
