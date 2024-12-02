<%--
  Created by IntelliJ IDEA.
  User: CYTech Student
  Date: 11/25/2024
  Time: 4:27 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Détails du Résultat</title>

</head>
<body>
<h1>Détails du Résultat</h1>
<p><strong>Étudiant :</strong> ${resultat.etudiant.nom} ${resultat.etudiant.prenom}</p>
<p><strong>Cours :</strong> ${resultat.cours.nom}</p>
<p><strong>Note :</strong> ${resultat.note}</p>
<br>
<a href="resultatservlet?action=list">Retour à la liste</a>
</body>
</html>

