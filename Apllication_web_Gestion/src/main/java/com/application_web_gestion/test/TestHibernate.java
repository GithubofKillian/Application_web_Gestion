package com.application_web_gestion.test;

import com.application_web_gestion.classe.*;
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
                .addAnnotatedClass(Resultat.class)
                .addAnnotatedClass(Admin.class)
                .buildSessionFactory();

        // Création de la session Hibernate
        Session session = factory.getCurrentSession();

        try {
            Admin admin = new Admin("Ratio","Boufon","tonpere@tamere.fr","JPP");
            // 1. Créer un nouvel enseignant
            Enseignant enseignant = new Enseignant("Martin", "Pierre", LocalDate.of(1980, 3, 25), "pierre.martin@example.com","abcd");

            // 2. Créer un nouvel étudiant
            Etudiant etudiant = new Etudiant("Dupont", "Jean", LocalDate.of(1998, 5, 14), "jean.dupont@example.com","abcd");

            // 3. Créer un cours et assigner l'enseignant à ce cours
            Cours cours = new Cours("Mathématiques", enseignant);

            // 5. Créer un résultat
            Resultat resultat = new Resultat(etudiant, cours, 15.5);

            // Démarrer une transaction
            session.beginTransaction();

            // Sauvegarder l'enseignant, l'étudiant, le cours, l'inscription, et le résultat
            session.save(enseignant);  // N'oubliez pas de sauvegarder l'enseignant !
            session.save(etudiant);
            session.save(cours);
            session.save(resultat);
            session.save(admin);

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
