<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Connexion</title>
</head>
<body>
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
</body>
</html>
