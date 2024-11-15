package com.application_web_gestion.service;

import com.application_web_gestion.classe.Etudiant;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class EtudiantService {
    private final SessionFactory sessionFactory;

    public EtudiantService() {
        sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    public void ajouterEtudiant(Etudiant etudiant) {
        executeTransaction(session -> session.save(etudiant));
    }

    public void modifierEtudiant(Etudiant etudiant) {
        executeTransaction(session -> session.update(etudiant));
    }

    public void supprimerEtudiant(Long id) {
        executeTransaction(session -> {
            Etudiant etudiant = session.get(Etudiant.class, id);
            if (etudiant != null) {
                session.delete(etudiant);
            }
        });
    }

    public Etudiant getEtudiant(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Etudiant.class, id);
        }
    }

    public List<Etudiant> getAllEtudiants() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from Etudiant", Etudiant.class).list();
        }
    }

    public List<Etudiant> rechercherEtudiantsParNom(String nomRecherche) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Etudiant WHERE nom LIKE :nom", Etudiant.class)
                    .setParameter("nom", "%" + nomRecherche + "%")
                    .getResultList();
        }
    }

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
