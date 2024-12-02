<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="fr">
<head>
  <meta charset="UTF-8">
  <title>Détails de l'Enseignant</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/detail.css">

</head>
<body>
<div class="container">
<h1>Détails de l'Enseignant</h1>

<p><strong>ID :</strong> ${enseignant.id}</p>
<p><strong>Nom :</strong> ${enseignant.nom}</p>
<p><strong>Prénom :</strong> ${enseignant.prenom}</p>
<p><strong>Date de Naissance :</strong> ${enseignant.dateNaissance}</p>
<p><strong>Contact :</strong> ${enseignant.contact}</p>
<p><strong>Mot de passe :</strong> ${enseignant.mdp}</p>
  <a href="enseignantservlet?action=edit&id=${enseignant.id}">
    <button type="button">Modifier</button>
  </a>
<br>
  <br>
<a href="enseignantservlet">Retour</a>
</div>
</body>
</html>
