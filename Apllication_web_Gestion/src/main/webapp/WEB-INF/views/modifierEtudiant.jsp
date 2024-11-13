<%--
  Created by IntelliJ IDEA.
  User: CYTech Student
  Date: 07/11/2024
  Time: 14:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="fr">
<head>
  <meta charset="UTF-8">
  <title>Modifier un étudiant</title>
</head>
<body>
<h2>Modifier l'étudiant</h2>

<form action="etudiantservlet?action=update" method="post">
  <input type="hidden" name="id" value="${etudiant.id}">

  <label for="nom">Nom :</label>
  <input type="text" id="nom" name="nom" value="${etudiant.nom}" required><br>

  <label for="prenom">Prénom :</label>
  <input type="text" id="prenom" name="prenom" value="${etudiant.prenom}" required><br>

  <label for="dateNaissance">Date de naissance :</label>
  <input type="date" id="dateNaissance" name="dateNaissance" value="${etudiant.dateNaissance}" required><br>

  <label for="contact">Contact :</label>
  <input type="text" id="contact" name="contact" value="${etudiant.contact}" required><br>

  <button type="submit">Modifier</button>
</form>

<a href="etudiantservlet"><button type="button">Retour à la liste des etudiants</button></a>

</body>
</html>
