<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Modifier le Cours</title>
</head>
<body>
<h2>Modifier le Cours</h2>

<form action="coursservlet" method="post">
    <input type="hidden" name="action" value="update">
    <input type="hidden" name="id" value="${cours.id}">

    <label for="nom">Nom du cours:</label>
    <input type="text" name="nom" id="nom" value="${cours.nom}" required><br><br>

    <label for="enseignant">Enseignant:</label>
    <select name="enseignant" id="enseignant">
        <c:forEach var="enseignant" items="${enseignants}">
            <option value="${enseignant.id}" ${enseignant.id == cours.enseignant.id ? 'selected' : ''}>${enseignant.nom}</option>
        </c:forEach>
    </select><br><br>

    <label>Étudiants:</label><br>
    <c:forEach var="etudiant" items="${etudiants}">
        <input type="checkbox" name="etudiants" value="${etudiant.id}" ${cours.etudiants.contains(etudiant) ? 'checked' : ''}>
        ${etudiant.nom} ${etudiant.prenom}<br>
    </c:forEach>

    <button type="submit">Mettre à jour</button>
</form>

<a href="coursservlet">Retour à la liste des cours</a>
</body>
</html>
