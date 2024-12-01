<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <title>Relevé de Notes</title>
</head>
<body>
<h1>Relevé de Notes</h1>

<!-- Affichage du message d'erreur si nécessaire -->
<c:if test="${not empty message}">
    <p style="color: red;">${message}</p>
</c:if>

<!-- Vérification de la disponibilité des données de l'étudiant -->
<c:if test="${not empty etudiant}">
    <h2>Étudiant : ${etudiant.nom} ${etudiant.prenom}</h2>
    <p>Matricule : ${etudiant.id}</p>
    <p>Moyenne générale : ${moyenne}</p>

    <!-- Tableau des résultats -->
    <table border="1">
        <thead>
        <tr>
            <th>Cours</th>
            <th>Note</th>
        </tr>
        </thead>
        <tbody>
        <!-- Parcours des résultats de l'étudiant -->
        <c:forEach var="resultat" items="${resultats}">
            <tr>
                <td>${resultat.cours.nom}</td>
                <td>${resultat.note}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <!-- Lien pour télécharger le relevé PDF -->
    <p><a href="${filePath}" download>Télécharger le relevé PDF</a></p>
</c:if>
</body>
</html>
