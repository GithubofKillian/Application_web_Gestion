<%--
  Created by IntelliJ IDEA.
  User: CYTech Student
  Date: 07/11/2024
  Time: 13:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Ajouter un étudiant</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/ajouter.css">
</head>
<body>
<div class="container">
<h1>Ajouter un étudiant</h1>

<form action="etudiantservlet" method="post">
    <input type="hidden" name="action" value="create">

    <label for="nom">Nom :</label>
    <input type="text" id="nom" name="nom" required><br>

    <label for="prenom">Prénom :</label>
    <input type="text" id="prenom" name="prenom" required><br>

    <label for="dateNaissance">Date de naissance :</label>
    <input type="date" id="dateNaissance" name="dateNaissance" required><br>

    <label for="contact">Contact :</label>
    <input type="text" id="contact" name="contact" required><br>

    <label for="mdp">Mot de passe :</label>
    <input type="text" id="mdp" name="mdp"><br>

    <button type="submit">Ajouter</button>
</form>
    <br>
    <a href="etudiantservlet">Retour</a>
</div>
</body>
</html>