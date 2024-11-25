
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Connexion</title>
</head>
<body>
<h1>Connexion</h1>
<form action="LoginServlet" method="post">
    <label for="username">Adresse mail :</label>
    <input type="text" id="username" name="username" required><br><br>

    <label for="password">Mot de passe :</label>
    <input type="password" id="password" name="password" required><br><br>

    <label for="role">Rôle :</label>
    <select id="role" name="role">
        <option value="Enseignant">Enseignant</option>
        <option value="Etudiant">Etudiant</option>
        <option value="Admin">Administrateur</option>
    </select><br><br>

    <button type="submit">Se connecter</button>
</form>
</body>
</html>

