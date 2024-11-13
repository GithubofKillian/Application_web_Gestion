<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Liste des Enseignants</title>
</head>
<body>
<h2>Liste des Enseignants</h2>

<a href="enseignantservlet?action=add">Ajouter un nouvel enseignant</a>

<table border="1">
    <tr>
        <th>ID</th>
        <th>Nom</th>
        <th>Prénom</th>
        <th>Contact</th>
        <th>Actions</th>
    </tr>
    <c:forEach var="enseignant" items="${enseignants}">
        <tr>
            <td>${enseignant.id}</td>
            <td>${enseignant.nom}</td>
            <td>${enseignant.prenom}</td>
            <td>${enseignant.contact}</td>
            <td>
                <a href="enseignantservlet?action=detail&id=${enseignant.id}">Détails</a> |
                <a href="enseignantservlet?action=edit&id=${enseignant.id}">Modifier</a> |
                <a href="enseignantservlet?action=delete&id=${enseignant.id}">Supprimer</a> |
                <a href="enseignantservlet?action=assignCourse&enseignantId=${enseignant.id}">Affecter Cours</a>
            </td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
