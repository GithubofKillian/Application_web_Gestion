<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="fr">
<head>
  <meta charset="UTF-8">
  <title>Détails du Cours</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/detail.css">

</head>
<body>
<div class="container">
<h1>Détails du Cours</h1>

<p><strong>Nom du cours:</strong> ${cours.nom}</p>
<p><strong>Enseignant:</strong> ${cours.enseignant.nom}</p>

<h3>Étudiants inscrits:</h3>
<ul>
  <c:forEach var="etudiant" items="${cours.etudiants}">
    <li>${etudiant.nom} ${etudiant.prenom}</li>
  </c:forEach>
</ul>
  <a href="coursservlet?action=edit&id=${cours.id}">
    <button type="button">Modifier</button>
  </a>
  <br>
  <br>
<a href="coursservlet">Retour</a>
</div>
</body>
</html>