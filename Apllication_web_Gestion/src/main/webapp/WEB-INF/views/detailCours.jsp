<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="fr">
<head>
  <meta charset="UTF-8">
  <title>Détails du Cours</title>
</head>
<body>
<h2>Détails du Cours</h2>

<p><strong>Nom du cours:</strong> ${cours.nom}</p>
<p><strong>Enseignant:</strong> ${cours.enseignant.nom}</p>

<h3>Étudiants inscrits:</h3>
<ul>
  <c:forEach var="etudiant" items="${cours.etudiants}">
    <li>${etudiant.nom} ${etudiant.prenom}</li>
  </c:forEach>
</ul>

<a href="coursservlet">Retour à la liste des cours</a>
</body>
</html>
