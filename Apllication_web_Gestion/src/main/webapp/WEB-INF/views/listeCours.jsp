<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Liste des Cours</title>
</head>
<body>
<h2>Liste des Cours</h2>

<a href="coursservlet?action=add">Ajouter un Cours</a>
<table border="1">
    <tr>
        <th>ID</th>
        <th>Nom</th>
        <th>Enseignant</th>
        <th>Actions</th>
    </tr>
    <c:forEach var="cours" items="${cours}">
        <tr>
            <td>${cours.id}</td>
            <td>${cours.nom}</td>
            <td>${cours.enseignant.nom}</td>
            <td>
                <a href="coursservlet?action=detail&id=${cours.id}">Détails</a> |
                <a href="coursservlet?action=edit&id=${cours.id}">Modifier</a> |
                <a href="coursservlet?action=delete&id=${cours.id}">Supprimer</a>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
