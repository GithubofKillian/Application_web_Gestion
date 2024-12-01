<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Envoi d'un Email</title>

</head>
<body>
<h1>Envoyer un Email</h1>

<%-- Afficher les messages de succès ou d'erreur --%>
<% if (request.getAttribute("success") != null) { %>
<p style="color: green;"><%= request.getAttribute("success") %></p>
<% } %>
<% if (request.getAttribute("error") != null) { %>
<p style="color: red;"><%= request.getAttribute("error") %></p>
<% } %>

<form action="MailingServlet" method="post">
    <label for="email">Destinataire :</label>
    <input type="email" id="email" name="email" required><br><br>

    <label for="subject">Sujet :</label>
    <input type="text" id="subject" name="subject" required><br><br>

    <label for="message">Message :</label><br>
    <textarea id="message" name="message" rows="5" cols="30" required></textarea><br><br>

    <button type="submit">Envoyer</button>
</form>
</body>
</html>

