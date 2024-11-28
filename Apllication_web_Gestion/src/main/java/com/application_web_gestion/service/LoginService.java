package com.application_web_gestion.service;

import com.application_web_gestion.classe.Admin;
import com.application_web_gestion.classe.Enseignant;
import com.application_web_gestion.classe.Etudiant;
import com.application_web_gestion.classe.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class LoginService {

    public boolean authenticateUser(String contact, String password, String role) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            switch (role.toLowerCase()) {
                case "etudiant" -> findUser(session, Etudiant.class, contact, password);
                case "enseignant" -> findUser(session, Enseignant.class, contact, password);
                case "admin" -> findUser(session, Admin.class, contact, password);
            };
            System.out.println("On rentre dans la méthode findUser");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private <T> T findUser(Session session, Class<T> entityClass, String contact, String password) {
        System.out.println("Recherche d'utilisateur dans : " + entityClass.getSimpleName().toLowerCase());
        String hql = "FROM " + entityClass.getSimpleName() + " u WHERE u.contact = :contact AND u.mdp = :password";
        Query<T> query = session.createQuery(hql, entityClass);
        query.setParameter("contact", contact);
        query.setParameter("password", password);

        T user = query.uniqueResult();
        System.out.println("Utilisateur trouvé : " + (user != null));
        return user; // Retourne l'utilisateur trouvé ou null
    }



}

