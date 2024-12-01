<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Liste des Cours</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/liste.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/menu.css">
</head>
<body>
<%@ include file="menu.jsp" %>
<h1>Liste des Cours</h1>

<table>
    <thead>
    <tr>
        <th>Nom</th>
        <th>Enseignant</th>
        <th>Actions</th>
    </tr>
    </thead>
    <c:forEach var="cours" items="${cours}">
        <tr>
            <td>${cours.nom}</td>
            <td>${cours.enseignant.nom}</td>
            <td>
                <a href="coursservlet?action=detail&id=${cours.id}" class="btn btn-view">Détails</a>
                <a href="coursservlet?action=edit&id=${cours.id}" class="btn btn-edit">Modifier</a>
                <a href="coursservlet?action=delete&id=${cours.id}" class="btn btn-delete">Supprimer</a>
            </td>
        </tr>
    </c:forEach>
</table>
<br>

<!-- Lien pour ajouter un nouvel étudiant -->
<a href="coursservlet?action=add">
    <button type="button" class="btn add-btn">Ajouter un cours</button>
</a>
</body>
</html>
