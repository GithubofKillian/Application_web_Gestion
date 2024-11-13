<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="fr">
<head>
  <meta charset="UTF-8">
  <title>Modifier Enseignant</title>
</head>
<body>
<h2>Modifier Enseignant</h2>

<form action="enseignantservlet?action=update" method="post">
  <input type="hidden" name="id" value="${enseignant.id}">

  <label for="nom">Nom :</label>
  <input type="text" id="nom" name="nom" value="${enseignant.nom}" required><br>

  <label for="prenom">Prénom :</label>
  <input type="text" id="prenom" name="prenom" value="${enseignant.prenom}" required><br>

  <label for="dateNaissance">Date de Naissance :</label>
  <input type="date" id="dateNaissance" name="dateNaissance" value="${enseignant.dateNaissance}" required><br>

  <label for="contact">Contact :</label>
  <input type="text" id="contact" name="contact" value="${enseignant.contact}"><br>

  <input type="submit" value="Enregistrer les modifications">
</form>

<a href="enseignantservlet">Retour à la liste des enseignants</a>
</body>
</html>
