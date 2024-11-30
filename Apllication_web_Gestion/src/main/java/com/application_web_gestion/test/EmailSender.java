package com.application_web_gestion.test;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

public class EmailSender {
    public static void main(String[] args) {
        // Configuration des propriétés SMTP
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        props.put("mail.smtp.ssl.protocols", "TLSv1.2"); // Forcer TLS 1.2

        // Activer les logs SSL pour débogage
        System.setProperty("javax.net.debug", "ssl,handshake");

        // Identifiants Gmail
        String username = "nestifyweb@gmail.com";
        String password = "myuw ztfg gmhv joes";

        // Créer la session avec authentification
        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        session.setDebug(true); // Activer le mode debug

        try {
            System.out.println("[INFO] Création du message...");
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("killian.jonneaux@gmail.com"));
            message.setSubject("Test Email");
            message.setText("Ceci est un test d'envoi avec JavaMail.");

            System.out.println("[INFO] Envoi de l'email...");
            Transport.send(message);
            System.out.println("[INFO] Email envoyé avec succès !");
        } catch (MessagingException e) {
            System.err.println("[ERROR] Une erreur s'est produite lors de l'envoi de l'email.");
            e.printStackTrace();
        }
    }
}
