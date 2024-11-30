<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Ajouter un Résultat</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/ajouter.css">
    <script>
        // Fonction de validation du formulaire
        function validerFormulaire() {
            var note = document.getElementById('note').value;
            if (isNaN(note) || note < 0 || note > 20) {
                alert("La note doit être un nombre entre 0 et 20.");
                return false; // Empêche l'envoi du formulaire
            }
            return true; // Permet l'envoi du formulaire
        }
    </script>
</head>
<body>
<div class="container">
    <h1>Ajouter un Résultat</h1>
    <form action="resultatservlet" method="post" onsubmit="return validerFormulaire()">
        <input type="hidden" name="action" value="create">

        <label>Étudiant :</label>
        <select name="etudiantId" required>
            <c:forEach var="etudiant" items="${etudiants}">
                <option value="${etudiant.id}">${etudiant.nom} ${etudiant.prenom}</option>
            </c:forEach>
        </select><br><br>

        <label>Cours :</label>
        <select name="coursId" required>
            <c:forEach var="cours" items="${cours}">
                <option value="${cours.id}">${cours.nom}</option>
            </c:forEach>
        </select><br><br>

        <label>Note :</label>
        <input type="number" name="note" id="note" min="0" max="20" step="0.1" required><br><br>

        <button type="submit">Ajouter</button>
    </form>
</div>
</body>
</html>
