<%--
  Created by IntelliJ IDEA.
  User: CYTech Student
  Date: 07/11/2024
  Time: 00:50
  To change this template use File | Settings | File Templates.
--%>
<!-- listeEtudiants.jsp -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>

<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Liste des Etudiants</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/liste.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/menu.css">
</head>
<body>
<%@ include file="menu.jsp" %>
<h1>Liste des Etudiants</h1>

<!-- Formulaire de recherche -->
<form action="etudiantservlet" method="get">
    <input type="text" name="nomRecherche" placeholder="Rechercher par nom">
    <input type="hidden" name="action" value="search">
    <button type="submit">Rechercher</button>
</form>

<table>
    <thead>
    <tr>
        <th>Nom</th>
        <th>Prenom</th>
        <th>Actions</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="etudiant" items="${etudiants}">
        <tr>
            <td>${etudiant.nom}</td>
            <td>${etudiant.prenom}</td>
            <td>
                <a href="etudiantservlet?action=detail&id=${etudiant.id}" class="btn btn-view">Détails</a>
                <a href="etudiantservlet?action=edit&id=${etudiant.id}" class="btn btn-edit">Modifier</a>
                <a href="etudiantservlet?action=delete&id=${etudiant.id}" class="btn btn-delete">Supprimer</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<br>

<!-- Lien pour ajouter un nouvel étudiant -->
<a href="etudiantservlet?action=add">
    <button type="button" class="btn add-btn">Ajouter un étudiant</button>
</a>
</body>
</html>
