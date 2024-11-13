package com.application_web_gestion.service;

import com.application_web_gestion.classe.Enseignant;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class EnseignantService {
    private SessionFactory sessionFactory;

    public EnseignantService() {
        sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    public void ajouterEnseignant(Enseignant enseignant) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(enseignant);
        transaction.commit();
        session.close();
    }
    public void modifierEnseignant(Enseignant enseignant) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(enseignant);
        transaction.commit();
        session.close();
    }

    public void supprimerEnseignant(long id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Enseignant enseignant = session.get(Enseignant.class, id);
        if (enseignant != null) {
            session.delete(enseignant);
        }
        transaction.commit();
        session.close();
    }

    public Enseignant getEnseignant(long id) {
        Session session = sessionFactory.openSession();
        Enseignant enseignant = session.get(Enseignant.class, id);
        session.close();
        return enseignant;
    }

    public List<Enseignant> getAllEnseignant() {
        Session session = sessionFactory.openSession();
        List<Enseignant> enseignants = session.createQuery("from Enseignant", Enseignant.class).list();
        session.close();
        return enseignants;
    }
}
