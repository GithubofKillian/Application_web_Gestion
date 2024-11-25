<%--
  Created by IntelliJ IDEA.
  User: CYTech Student
  Date: 11/25/2024
  Time: 4:11 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Ajouter un Résultat</title>
</head>
<body>
<h1>Ajouter un Résultat</h1>
<form action="resultatservlet" method="post">
    <input type="hidden" name="action" value="create">
    <label>Étudiant :</label>
    <select name="etudiantId">
        <c:forEach var="etudiant" items="${etudiants}">
            <option value="${etudiant.id}">${etudiant.nom} ${etudiant.prenom}</option>
        </c:forEach>
    </select><br><br>
    <label>Cours :</label>
    <select name="coursId">
        <c:forEach var="cours" items="${cours}">
            <option value="${cours.id}">${cours.nom}</option>
        </c:forEach>
    </select><br><br>
    <label>Note :</label>
    <input type="text" name="note"><br><br>
    <button type="submit">Ajouter</button>
</form>
</body>
</html>

