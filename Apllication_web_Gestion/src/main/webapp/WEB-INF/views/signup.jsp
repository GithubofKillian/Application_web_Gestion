<%--
  Created by IntelliJ IDEA.
  User: CYTech Student
  Date: 11/23/2024
  Time: 5:40 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <title>Inscription</title>
</head>
<body>
<h1>Inscription</h1>
<form action="SignupServlet" method="post">
  <label for="username">Nom d'utilisateur :</label>
  <input type="text" id="username" name="username" required><br><br>

  <label for="password">Mot de passe :</label>
  <input type="password" id="password" name="password" required><br><br>

  <label for="role">Rôle :</label>
  <select id="role" name="role">
    <option value="Enseignant">Enseignant</option>
    <option value="Etudiant">Etudiant</option>
    <option value="Administrateur">Administrateur</option>
  </select><br><br>

  <button type="submit">S'inscrire</button>
</form>
</body>
</html>

