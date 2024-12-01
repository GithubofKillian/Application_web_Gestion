<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="fr">
<head>
  <meta charset="UTF-8">
  <title>Modifier Enseignant</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/modifier.css">

</head>
<body>
<div class="container">
<h1>Modifier l'enseignant</h1>

<form action="enseignantservlet?action=update" method="post">
  <input type="hidden" name="id" value="${enseignant.id}">

  <label for="nom">Nom :</label>
  <input type="text" id="nom" name="nom" value="${enseignant.nom}" required><br>

  <label for="prenom">Prénom :</label>
  <input type="text" id="prenom" name="prenom" value="${enseignant.prenom}" required><br>

  <label for="dateNaissance">Date de Naissance :</label>
  <input type="date" id="dateNaissance" name="dateNaissance" value="${enseignant.dateNaissance}" required><br>

  <label for="contact">Contact :</label>
  <input type="text" id="contact" name="contact" value="${enseignant.contact}"required><br>

  <label for="mdp">Mot de passe :</label>
  <input type="text" id="mdp" name="mdp" value="${enseignant.mdp}"required><br>

  <button type="submit">Modifier</button>
</form>
  <br>
  <a href="enseignantservlet">Retour</a>
</div>
</body>
</html>
