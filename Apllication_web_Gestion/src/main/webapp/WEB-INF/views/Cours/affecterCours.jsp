<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="fr">
<head>
  <meta charset="UTF-8">
  <title>Affecter Enseignant à un Cours</title>
</head>
<body>
<h2>Affecter Enseignant à un Cours</h2>

<form action="enseignantservlet?action=assignCourse" method="post">
  <input type="hidden" name="enseignantId" value="${enseignant.id}">

  <label for="coursId">ID du Cours :</label>
  <input type="text" id="coursId" name="coursId" required><br>

  <input type="submit" value="Affecter Cours">
</form>

<a href="enseignantservlet">Retour à la liste des enseignants</a>
</body>
</html>
