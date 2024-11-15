<%--
  Created by IntelliJ IDEA.
  User: CYTech Student
  Date: 13/11/2024
  Time: 15:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Détails de l'Étudiant</title>
</head>
<body>
<h2>Détails de l'Étudiant</h2>

<p><strong>Nom :</strong> ${etudiant.nom}</p>
<p><strong>Prénom :</strong> ${etudiant.prenom}</p>
<p><strong>Date de naissance :</strong> ${etudiant.dateNaissance}</p>
<p><strong>Contact :</strong> ${etudiant.contact}</p>

<a href="etudiantservlet?action=edit&id=${etudiant.id}">
    <button type="button">Modifier</button>
</a>

<a href="etudiantservlet"><button type="button">Retour à la liste des etudiants</button></a>

</body>
</html>