<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Page principale</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/index.css">
</head>
<body>
<div class="container">
    <h1>Bienvenue dans l'application</h1>
    <div class="button-box">
        <%
            String userRole = (String) session.getAttribute("userRole");

            if ("Admin".equals(userRole)) {
        %>
        <a href="etudiantservlet" class="btn">Etudiant</a>
        <a href="enseignantservlet" class="btn">Enseignant</a>
        <a href="coursservlet" class="btn">Cours</a>
        <a href="resultatservlet" class="btn">Résultat</a>
        <%
        } else if ("Enseignant".equals(userRole)) {
        %>
        <a href="etudiantservlet" class="btn">Etudiant</a>
        <a href="coursservlet" class="btn">Cours</a>
        <a href="resultatservlet" class="btn">Résultat</a>
        <%
        } else if ("Etudiant".equals(userRole)) {
        %>
        <a href="coursservlet" class="btn">Cours</a>
        <a href="resultatservlet" class="btn">Résultat</a>
        <%
        } else {
        %>
        <p>Rôle inconnu. Veuillez vous reconnecter.</p>
        <%
            }
        %>
    </div>
</div>
<a href="${pageContext.request.contextPath}/LogoutServlet" class="btn">Déconnexion</a>
</body>
</html>
