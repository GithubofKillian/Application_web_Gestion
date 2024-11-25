<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="fr">
<head>
  <meta charset="UTF-8">
  <title>Détails de l'Enseignant</title>
</head>
<body>
<h2>Détails de l'Enseignant</h2>

<p><strong>ID :</strong> ${enseignant.id}</p>
<p><strong>Nom :</strong> ${enseignant.nom}</p>
<p><strong>Prénom :</strong> ${enseignant.prenom}</p>
<p><strong>Date de Naissance :</strong> ${enseignant.dateNaissance}</p>
<p><strong>Contact :</strong> ${enseignant.contact}</p>
<p><strong>Mot de passe :</strong> ${enseignant.mdp}</p>

<a href="enseignantservlet">Retour à la liste des enseignants</a>
</body>
</html>
