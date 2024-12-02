<%--
  Created by IntelliJ IDEA.
  User: CYTech Student
  Date: 11/25/2024
  Time: 4:26 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Modifier un Résultat</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/modifier.css">

</head>
<body>
<div class="container">
<h1>Modifier un Résultat</h1>
<form action="resultatservlet" method="post">
    <input type="hidden" name="action" value="update">
    <input type="hidden" name="id" value="${resultat.id}">
    <label>Étudiant :</label>
    <select name="etudiantId">
        <c:forEach var="etudiant" items="${etudiants}">
            <option value="${etudiant.id}" ${etudiant.id == resultat.etudiant.id ? 'selected' : ''}>${etudiant.nom} ${etudiant.prenom}</option>
        </c:forEach>
    </select><br><br>
    <label>Cours :</label>
    <select name="coursId">
        <c:forEach var="cours" items="${cours}">
            <option value="${cours.id}" ${cours.id == resultat.cours.id ? 'selected' : ''}>${cours.nom}</option>
        </c:forEach>
    </select><br><br>
    <label>Note :</label>
    <input type="text" name="note" value="${resultat.note}"><br><br>
    <button type="submit">Modifier</button>
</form>
    <br>
    <a href="resultatservlet">Retour</a>
</div>
</body>
</html>

