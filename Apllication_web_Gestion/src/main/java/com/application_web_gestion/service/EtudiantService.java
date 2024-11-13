package com.application_web_gestion.service;

import com.application_web_gestion.classe.Etudiant;
import org.hibernate.Cache;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class EtudiantService {
    private SessionFactory sessionFactory;

    public EtudiantService() {
        sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    public void ajouterEtudiant(Etudiant etudiant) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(etudiant);
        transaction.commit();
        session.close();
    }

    public void modifierEtudiant(Etudiant etudiant) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(etudiant);
        transaction.commit();
        session.close();
    }

    public void supprimerEtudiant(Long id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Etudiant etudiant = session.get(Etudiant.class, id);
        if (etudiant != null) {
            session.delete(etudiant);
        }
        transaction.commit();
        session.close();
    }

    public Etudiant getEtudiant(Long id) {
        Session session = sessionFactory.openSession();
        Etudiant etudiant = session.get(Etudiant.class, id);
        session.close();
        return etudiant;
    }

    public List<Etudiant> getAllEtudiants() {
        Session session = sessionFactory.openSession();
        List<Etudiant> etudiants = session.createQuery("from Etudiant", Etudiant.class).list();
        session.close();
        return etudiants;
    }

    public List<Etudiant> rechercherEtudiantsParNom(String nomRecherche) {
        Session session = sessionFactory.openSession();
        List<Etudiant> etudiants = session.createQuery("FROM Etudiant WHERE nom LIKE :nom", Etudiant.class)
                .setParameter("nom", "%" + nomRecherche + "%")
                .getResultList();
        session.close();
        return etudiants;
    }


}
