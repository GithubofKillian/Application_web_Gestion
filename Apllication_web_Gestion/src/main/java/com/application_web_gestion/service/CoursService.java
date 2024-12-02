package com.application_web_gestion.service;

import com.application_web_gestion.classe.Cours;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.Hibernate;
import org.hibernate.query.Query;

import java.util.List;
import java.util.ArrayList;



public class CoursService {
    private SessionFactory sessionFactory;

    public CoursService() {
        sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    public void ajouterCours(Cours cours) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(cours);
        transaction.commit();
        session.close();
    }

    public void modifierCours(Cours cours) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(cours);
        transaction.commit();
        session.close();
    }

    public void supprimerCours(Long id) {  // Change int to Long
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Cours cours = session.get(Cours.class, id);
        if (cours != null) {
            session.delete(cours);
        }
        transaction.commit();
        session.close();
    }

    public Cours getCours(Long id) {
        Session session = sessionFactory.openSession();
        Cours cours = session.get(Cours.class, id);
        if (cours != null) {
            Hibernate.initialize(cours.getEtudiants()); // Initialiser explicitement la collection
        }
        session.close();
        return cours;
    }

    public List<Cours> getAllCours() {
        Session session = sessionFactory.openSession();
        List<Cours> cours = session.createQuery("from Cours", Cours.class).list();
        session.close();
        return cours;
    }

    public List<Cours> getAllCoursEnseignant(String contact) {
        Session session = sessionFactory.openSession();
        try {
            // HQL pour récupérer les cours de l'enseignant selon son contact
            String hql = "FROM Cours c WHERE c.enseignant.contact = :contact";
            Query<Cours> query = session.createQuery(hql, Cours.class);
            query.setParameter("contact", contact);

            List<Cours> cours = query.getResultList();
            return cours;
        } catch (Exception e) {
            System.err.println("[ERROR] getAllCoursEnseignant - Exception : " + e.getMessage());
            e.printStackTrace();
            return new ArrayList<>();
        } finally {
            session.close();
        }
    }

    public List<Cours> getAllCoursEtudiant(String contact) {
        Session session = sessionFactory.openSession();
        try {
            // HQL pour récupérer les cours de l'étudiant selon son contact
            String hql = "SELECT c FROM Cours c JOIN c.etudiants e WHERE e.contact = :contact";
            Query<Cours> query = session.createQuery(hql, Cours.class);
            query.setParameter("contact", contact);

            List<Cours> cours = query.getResultList();
            return cours;
        } catch (Exception e) {
            System.err.println("[ERROR] getAllCoursEtudiant - Exception : " + e.getMessage());
            e.printStackTrace();
            return new ArrayList<>();
        } finally {
            session.close();
        }
    }
}
