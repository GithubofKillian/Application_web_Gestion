package com.application_web_gestion.service;

import com.application_web_gestion.classe.Resultat;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

public class ResultatService {

    private final SessionFactory sessionFactory;

    public ResultatService() {
        sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    // Ajouter un résultat
    public void ajouterResultat(Resultat resultat) {
        executeTransaction(session -> session.save(resultat));
    }

    // Modifier un résultat
    public void modifierResultat(Resultat resultat) {
        executeTransaction(session -> session.update(resultat));
    }

    // Supprimer un résultat
    public void supprimerResultat(Long id) {
        executeTransaction(session -> {
            Resultat resultat = session.get(Resultat.class, id);
            if (resultat != null) {
                session.delete(resultat);
            }
        });
    }

    // Récupérer un résultat par ID
    public Resultat getResultat(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Resultat.class, id);
        }
    }

    // Récupérer tous les résultats
    public List<Resultat> getAllResultats() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from Resultat", Resultat.class).list();
        }
    }

    // Récupérer les résultats d'un étudiant
    public List<Resultat> getResultatsParEtudiant(Session session, Long etudiantId) {
        System.out.println("[DEBUG] getResultatsParEtudiant - Étudiant ID : " + etudiantId);
        String hql = "FROM Resultat r WHERE r.etudiant.id = :etudiantId";

        System.out.println("[DEBUG] HQL : " + hql);
        try {
            Query<Resultat> query = session.createQuery(hql, Resultat.class);
            query.setParameter("etudiantId", etudiantId);

            List<Resultat> resultats = query.getResultList();
            System.out.println("[DEBUG] Nombre de résultats trouvés : " + resultats.size());
            return resultats;
        } catch (Exception e) {
            System.err.println("[ERROR] getResultatsParEtudiant - Exception : " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    // Récupérer les résultats d'un cours
    public List<Resultat> getResultatsParCours(Long coursId) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Resultat WHERE cours.id = :coursId", Resultat.class)
                    .setParameter("coursId", coursId)
                    .list();
        }
    }

    // Calculer la moyenne des résultats d'un étudiant
    public double calculerMoyenneParEtudiant(Session session,Long etudiantId) {
        List<Resultat> resultats = getResultatsParEtudiant(session, etudiantId);
        return resultats.stream()
                .mapToDouble(Resultat::getNote)
                .average()
                .orElse(0.0);
    }

    // Calculer la moyenne des résultats d'un cours
    public double calculerMoyenneParCours(Long coursId) {
        List<Resultat> resultats = getResultatsParCours(coursId);
        return resultats.stream()
                .mapToDouble(Resultat::getNote)
                .average()
                .orElse(0.0);
    }

    // Méthode utilitaire pour les transactions
    private void executeTransaction(SessionAction action) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            action.execute(session);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw e;
        }
    }

    @FunctionalInterface
    private interface SessionAction {
        void execute(Session session);
    }
}
