<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Modifier le Cours</title>
    <script>
        // Fonction pour filtrer la liste des enseignants
        function rechercherEnseignant() {
            const recherche = document.getElementById('rechercheEnseignant').value.toLowerCase();
            const options = document.querySelectorAll('#enseignant option');
            let resultat = false;
            options.forEach(option => {
                if (option.textContent.toLowerCase().includes(recherche)) {
                    option.style.display = '';
                    resultat = true;
                } else {
                    option.style.display = 'none';
                }
            });
            document.getElementById('enseignant-message').style.display = resultat ? 'none' : 'block';
        }

        // Fonction pour filtrer la liste des étudiants
        function rechercherEtudiant() {
            const recherche = document.getElementById('rechercheEtudiant').value.toLowerCase();
            const checkboxLabels = document.querySelectorAll('.etudiant-label');
            let resultat = false;
            checkboxLabels.forEach(label => {
                if (label.textContent.toLowerCase().includes(recherche)) {
                    label.parentElement.style.display = '';
                    resultat = true;
                } else {
                    label.parentElement.style.display = 'none';
                }
            });
            document.getElementById('etudiant-message').style.display = resultat ? 'none' : 'block';
        }
    </script>
</head>
<body>
<h2>Modifier le Cours</h2>

<form action="coursservlet" method="post">
    <input type="hidden" name="action" value="update">
    <input type="hidden" name="id" value="${cours.id}">

    <label for="nom">Nom du cours:</label>
    <input type="text" name="nom" id="nom" value="${cours.nom}" required><br><br>

    <!-- Recherche et sélection d'enseignant -->
    <label for="enseignant">Enseignant:</label>
    <input type="text" id="rechercheEnseignant" placeholder="Rechercher un enseignant..." onkeyup="rechercherEnseignant()">
    <select name="enseignant" id="enseignant" required>
        <c:forEach var="enseignant" items="${enseignants}">
            <option value="${enseignant.id}" ${enseignant.id == cours.enseignant.id ? 'selected' : ''}>${enseignant.nom}</option>
        </c:forEach>
    </select>
    <p id="enseignant-message" style="color: red; display: none;">Aucun enseignant trouvé</p>
    <br><br>

    <!-- Recherche et sélection d'étudiants -->
    <label>Étudiants:</label><br>
    <input type="text" id="rechercheEtudiant" placeholder="Rechercher un étudiant..." onkeyup="rechercherEtudiant()">
    <div id="etudiants-list">
        <c:forEach var="etudiant" items="${etudiants}">
            <div>
                <input type="checkbox" name="etudiants" value="${etudiant.id}" id="etudiant-${etudiant.id}" ${cours.etudiants.contains(etudiant) ? 'checked' : ''}>
                <label for="etudiant-${etudiant.id}" class="etudiant-label">${etudiant.nom} ${etudiant.prenom}</label>
            </div>
        </c:forEach>
    </div>
    <p id="etudiant-message" style="color: red; display: none;">Aucun étudiant trouvé</p>
    <br>

    <button type="submit">Mettre à jour</button>
</form>

<a href="coursservlet">Retour à la liste des cours</a>
</body>
</html>
