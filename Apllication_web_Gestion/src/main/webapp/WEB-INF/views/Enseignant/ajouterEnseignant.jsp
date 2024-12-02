<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="fr">
<head>
  <meta charset="UTF-8">
  <title>Ajouter un Enseignant</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/ajouter.css">
</head>
<body>
<div class="container">
<h1>Ajouter un Enseignant</h1>

<form action="enseignantservlet?action=create" method="post">
  <label for="nom">Nom :</label>
  <input type="text" id="nom" name="nom" required><br>

  <label for="prenom">Prénom :</label>
  <input type="text" id="prenom" name="prenom" required><br>

  <label for="dateNaissance">Date de Naissance :</label>
  <input type="date" id="dateNaissance" name="dateNaissance" required><br>

  <label for="contact">Contact :</label>
  <input type="text" id="contact" name="contact"><br>

  <label for="mdp">Mot de passe :</label>
  <input type="text" id="mdp" name="mdp"><br>

  <button type="submit">Ajouter</button>
</form>
  <br>
  <a href="enseignantservlet">Retour</a>
</div>
</body>
</html>