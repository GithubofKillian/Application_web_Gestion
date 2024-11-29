<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Connexion</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/login.css">
</head>
<body>
<img src="${pageContext.request.contextPath}/resources/images/logo_cyu.png" alt="Logo" class="logo">
<div class="container">
<h1>Connexion</h1>

<%-- Affichage du message d'erreur, s'il existe --%>
<% if (request.getAttribute("error") != null) { %>
<p style="color: red;"><%= request.getAttribute("error") %></p>
<% } %>

<form action="LoginServlet" method="post">
    <label for="contact">Adresse mail :</label>
    <input type="text" id="contact" name="contact" required><br><br>

    <label for="password">Mot de passe :</label>
    <input type="password" id="password" name="password" required><br><br>

    <label for="role">Rôle :</label>
    <select id="role" name="role">
        <option value="Enseignant">Enseignant</option>
        <option value="Etudiant">Etudiant</option>
        <option value="Admin">Administrateur</option>
    </select><br><br>

    <button type="submit">Se connecter</button>
</form>
</div>
</body>
</html>