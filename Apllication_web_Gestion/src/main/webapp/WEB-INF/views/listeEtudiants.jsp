<%--
  Created by IntelliJ IDEA.
  User: CYTech Student
  Date: 07/11/2024
  Time: 00:50
  To change this template use File | Settings | File Templates.
--%>
<!-- listeEtudiants.jsp -->

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Liste des Etudiants</title>
</head>
<body>
<h1>Liste des Etudiants</h1>

<table border="1">
    <thead>
    <tr>
        <th>ID</th>
        <th>Nom</th>
        <th>Prenom</th>
        <th>Date de Naissance</th>
        <th>Contact</th>
        <th>Actions</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="etudiant" items="${etudiants}">
        <tr>
            <td>${etudiant.id}</td>
            <td>${etudiant.nom}</td>
            <td>${etudiant.prenom}</td>
            <td>${etudiant.dateNaissance}</td>
            <td>${etudiant.contact}</td>
            <td>
                <a href="etudiants?action=detail&id=${etudiant.id}">Voir</a>
                <a href="etudiants?action=edit&id=${etudiant.id}">Modifier</a>
                <a href="etudiants?action=delete&id=${etudiant.id}">Supprimer</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<br>
<!-- Lien pour ajouter un nouvel étudiant -->
<a href="etudiants?action=add">Ajouter un etudiant</a>

</body>
</html>
