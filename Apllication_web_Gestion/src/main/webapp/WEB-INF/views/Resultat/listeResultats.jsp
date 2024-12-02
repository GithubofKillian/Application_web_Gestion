<%--
  Created by IntelliJ IDEA.
  User: CYTech Student
  Date: 11/25/2024
  Time: 5:00 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Liste des Résultats</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/liste.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/menu.css">
</head>
<body>
<%@ include file="../menu.jsp" %>
<h1>Liste des Résultats</h1>
<%

    if ("Admin".equals(userRole)) {
%>
<table>
    <thead>
    <tr>
        <th>Étudiant</th>
        <th>Cours</th>
        <th>Note</th>
        <th>Actions</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="resultat" items="${resultats}">
        <tr>
            <td>${resultat.etudiant.nom} ${resultat.etudiant.prenom}</td>
            <td>${resultat.cours.nom}</td>
            <td>${resultat.note}</td>
            <td>
                <a href="resultatservlet?action=edit&id=${resultat.id}" class="btn btn-edit">Modifier</a>
                <a href="resultatservlet?action=delete&id=${resultat.id}" class="btn btn-delete" onclick="return confirm('Confirmer la suppression ?');">Supprimer</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<br>

<a href="resultatservlet?action=add">
    <button type="button" class="btn add-btn">Ajouter un Résultat</button>
</a>
<div>
    <h2>Relevé de notes</h2>
    <form action="relevepdfservlet" method="get">
        <label for="etudiantEmail">Sélectionnez un étudiant :</label>
        <select id="etudiantEmail" name="etudiantEmail" required>
            <!-- Boucle pour afficher les étudiants -->
            <c:forEach var="etudiant" items="${etudiants}">
                <option value="${etudiant.contact}">${etudiant.nom} ${etudiant.prenom} (${etudiant.contact})</option>
            </c:forEach>
        </select>
        <button type="submit" class="btn btn-pdf">Générer le relevé PDF</button>
    </form>
</div>
<%
} else if ("Enseignant".equals(userRole)) {
%>
<table>
    <thead>
    <tr>
        <th>ID</th>
        <th>Étudiant</th>
        <th>Cours</th>
        <th>Note</th>
        <th>Actions</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="resultat" items="${resultats}">
        <tr>
            <td>${resultat.id}</td>
            <td>${resultat.etudiant.nom} ${resultat.etudiant.prenom}</td>
            <td>${resultat.cours.nom}</td>
            <td>${resultat.note}</td>
            <td>
                <a href="resultatservlet?action=edit&id=${resultat.id}" class="btn btn-edit">Modifier</a>
                <a href="resultatservlet?action=delete&id=${resultat.id}" class="btn btn-delete" onclick="return confirm('Confirmer la suppression ?');">Supprimer</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<br>

<a href="resultatservlet?action=add">
    <button type="button" class="btn add-btn">Ajouter un Résultat</button>
</a>
<div>
    <h2>Relevé de notes</h2>
    <form action="relevepdfservlet" method="get">
        <label for="etudiantEmail">Sélectionnez un étudiant :</label>
        <select id="etudiantEmail" name="etudiantEmail" required>
            <!-- Boucle pour afficher les étudiants -->
            <c:forEach var="etudiant" items="${etudiants}">
                <option value="${etudiant.contact}">${etudiant.nom} ${etudiant.prenom} (${etudiant.contact})</option>
            </c:forEach>
        </select>
        <button type="submit" class="btn btn-pdf">Générer le relevé PDF</button>
    </form>
</div>
<%
} else if ("Etudiant".equals(userRole)) {
%>
<table>
    <thead>
    <tr>
        <th>ID</th>
        <th>Étudiant</th>
        <th>Cours</th>
        <th>Note</th>
        <th>Actions</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="resultat" items="${resultats}">
        <tr>
            <td>${resultat.id}</td>
            <td>${resultat.etudiant.nom} ${resultat.etudiant.prenom}</td>
            <td>${resultat.cours.nom}</td>
            <td>${resultat.note}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<div>
    <h2>Relevé de notes</h2>
    <form action="relevepdfservlet" method="get">
        <input type="hidden" id="etudiantEmail" name="etudiantEmail" value="${userContact}">

        <p>Étudiant sélectionné :${userContact}</p>

        <button type="submit" class="btn btn-pdf">Générer le relevé PDF</button>
    </form>
</div>
<%
} else {
%>
<p>Rôle inconnu. Veuillez vous reconnecter.</p>
<%
    }
%>

<br>
<!-- Section pour générer le relevé PDF -->



</body>
</html>