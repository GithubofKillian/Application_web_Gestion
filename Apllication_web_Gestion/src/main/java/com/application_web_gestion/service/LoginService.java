package com.application_web_gestion.service;

import com.application_web_gestion.classe.Admin;
import com.application_web_gestion.classe.Enseignant;
import com.application_web_gestion.classe.Etudiant;
import com.application_web_gestion.classe.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class LoginService {

    public boolean authenticateUser(String contact, String password, String role) {
        System.out.println("[DEBUG] authenticateUser - Début");
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            System.out.println("[DEBUG] authenticateUser - Session ouverte");

            boolean userExists = switch (role.toLowerCase()) {
                case "etudiant" -> checkUserExists(session, Etudiant.class, contact, password);
                case "enseignant" -> checkUserExists(session, Enseignant.class, contact, password);
                case "admin" -> checkUserExists(session, Admin.class, contact, password);
                default -> false;
            };

            System.out.println("[DEBUG] authenticateUser - Résultat : " + userExists);
            return userExists;
        } catch (Exception e) {
            System.err.println("[ERROR] authenticateUser - Exception : " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    private <T> boolean checkUserExists(Session session, Class<T> entityClass, String contact, String password) {
        System.out.println("[DEBUG] checkUserExists - Classe : " + entityClass.getSimpleName());
        String hql = "SELECT COUNT(u) FROM " + entityClass.getSimpleName() +
                " u WHERE u.contact = :contact AND u.mdp = :password";

        System.out.println("[DEBUG] HQL : " + hql);
        try {
            Query<Long> query = session.createQuery(hql, Long.class);
            query.setParameter("contact", contact);
            query.setParameter("password", password);

            long count = query.uniqueResult(); // Retourne le nombre d'entrées correspondantes
            System.out.println("[DEBUG] Résultat du COUNT : " + count);
            return count > 0;
        } catch (Exception e) {
            System.err.println("[ERROR] checkUserExists - Exception : " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}
