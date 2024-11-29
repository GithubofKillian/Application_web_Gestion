package com.application_web_gestion.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.Properties;

@WebServlet("/MailingServlet")
public class MailingServlet extends HttpServlet {

    // Configuration SMTP
    private static final String SMTP_HOST = "smtp.gmail.com";
    private static final String SMTP_PORT = "587";
    private static final String EMAIL_FROM = "votre_email@gmail.com"; // Remplacez par votre adresse email
    private static final String EMAIL_PASSWORD = "votre_mot_de_passe"; // Remplacez par votre mot de passe

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Rediriger vers une page JSP pour envoyer un email
        request.getRequestDispatcher("/WEB-INF/views/sendMail.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Récupérer les données du formulaire
        String recipientEmail = request.getParameter("email");
        String subject = request.getParameter("subject");
        String messageContent = request.getParameter("message");

        try {
            // Envoyer l'email
            sendEmail(recipientEmail, subject, messageContent);

            // Rediriger avec succès
            request.setAttribute("success", "Email envoyé avec succès à " + recipientEmail);
        } catch (MessagingException e) {
            // Gérer les erreurs
            e.printStackTrace();
            request.setAttribute("error", "Échec de l'envoi de l'email : " + e.getMessage());
        }

        // Retourner sur la page d'envoi
        request.getRequestDispatcher("/WEB-INF/views/sendMail.jsp").forward(request, response);
    }

    private void sendEmail(String toEmail, String subject, String messageContent) throws MessagingException {
        // Configuration des propriétés SMTP
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", SMTP_HOST);
        properties.put("mail.smtp.port", SMTP_PORT);

        // Authentification avec l'email et le mot de passe
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(EMAIL_FROM, EMAIL_PASSWORD);
            }
        });

        // Création du message
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(EMAIL_FROM));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
        message.setSubject(subject);
        message.setText(messageContent);

        // Envoyer l'email
        Transport.send(message);
    }
}
