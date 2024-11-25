<%--
  Created by IntelliJ IDEA.
  User: CYTech Student
  Date: 11/23/2024
  Time: 5:39 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <title>Authentification</title>
</head>
<body>
<h1>Bienvenue</h1>
<form action="AuthServlet" method="post">
  <button type="submit" name="action" value="login">Se connecter</button>
  <button type="submit" name="action" value="signup">S'inscrire</button>
</form>

</body>
</html>

