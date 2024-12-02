<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <title>Relevé de Notes</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/releve.css">
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
    <table>
        <thead>
        <tr>
            <th>Cours</th>
            <th>Note</th>
        </tr>
        </thead>

        <tbody>
        <c:forEach var="resultat" items="${resultats}">
            <tr>
                <td>${resultat.cours.nom}</td>
                <td>${resultat.note}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <br>
    <!-- Lien pour télécharger le relevé PDF -->
    <a href="${filePath}" download>
        <button type="button" class="btn dl-btn">Télécharger le relevé PDF</button>
    </a>
</c:if>
<br>
<a href="resultatservlet">Retour</a>
</body>
</html>