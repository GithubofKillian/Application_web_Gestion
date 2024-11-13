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

<!-- Formulaire de recherche -->
<form action="etudiantservlet" method="get">
    <input type="text" name="nomRecherche" placeholder="Rechercher par nom">
    <input type="hidden" name="action" value="search">
    <button type="submit">Rechercher</button>
</form>

<table border="1">
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
                <a href="etudiantservlet?action=detail&id=${etudiant.id}">Voir</a>
                <a href="etudiantservlet?action=edit&id=${etudiant.id}">Modifier</a>
                <a href="etudiantservlet?action=delete&id=${etudiant.id}">Supprimer</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<br>

<!-- Lien pour ajouter un nouvel étudiant -->
<a href="etudiantservlet?action=add"><button type="button">Ajouter un étudiant</button></a>

</body>
</html>
