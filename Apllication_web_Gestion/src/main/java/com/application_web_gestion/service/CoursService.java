package com.application_web_gestion.service;

import com.application_web_gestion.classe.Cours;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

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

    public void supprimerCours(int id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Cours cours = session.get(Cours.class, id);
        if (cours != null) {
            session.delete(cours);
        }
        transaction.commit();
        session.close();
    }

    public Cours getCours(int id) {
        Session session = sessionFactory.openSession();
        Cours cours = session.get(Cours.class, id);
        session.close();
        return cours;
    }

    public List<Cours> getAllCours() {
        Session session = sessionFactory.openSession();
        List<Cours> cours = session.createQuery("from Cours", Cours.class).list();
        session.close();
        return cours;
    }
}
