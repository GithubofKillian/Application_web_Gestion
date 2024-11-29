<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Page Admin</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/index.css">
</head>
<body>
<div class="container">
<h1><%= "Page Administrateur" %>
</h1>
    <div class="button-box">
        <a href="etudiantservlet" class="btn">Etudiant</a>
        <a href="enseignantservlet" class="btn">Enseignant</a>
        <a href="coursservlet" class="btn">Cours</a>
        <a href="resultatservlet" class="btn">Résultat</a>
        <a href="LoginServlet" class="btn">Authentification</a>
    </div>
</div>
</body>
</html>