<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Liste des Enseignants</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/liste.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/menu.css">
</head>
<body>
<%@ include file="../menu.jsp" %>
<h1>Liste des Enseignants</h1>

<table>
    <thead>
    <tr>
        <th>Nom</th>
        <th>Prénom</th>
        <th>Contact</th>
        <th>Actions</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="enseignant" items="${enseignants}">
        <tr>
            <td>${enseignant.nom}</td>
            <td>${enseignant.prenom}</td>
            <td>${enseignant.contact}</td>
            <td>
                <a href="enseignantservlet?action=detail&id=${enseignant.id}" class="btn btn-view">Détails</a>
                <a href="enseignantservlet?action=edit&id=${enseignant.id}" class="btn btn-edit">Modifier</a>
                <a href="enseignantservlet?action=delete&id=${enseignant.id}" class="btn btn-delete">Supprimer</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<br>

<a href="enseignantservlet?action=add">
    <button type="button" class="btn add-btn">Ajouter un enseignant</button>
</a>

</body>
</html>
