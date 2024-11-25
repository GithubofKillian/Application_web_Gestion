<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="fr">
<head>
  <meta charset="UTF-8">
  <title>Ajouter un Enseignant</title>
</head>
<body>
<h2>Ajouter un Enseignant</h2>

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

  <input type="submit" value="Ajouter Enseignant">
</form>

<a href="enseignantservlet">Retour à la liste des enseignants</a>
</body>
</html>
