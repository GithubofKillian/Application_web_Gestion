<nav class="menu">
  <ul>
    <%
      String userRole = (String) session.getAttribute("userRole");
      String userContact = (String) session.getAttribute("userContact");
      if ("Admin".equals(userRole)) {
    %>
    <li><a href="${pageContext.request.contextPath}/etudiantservlet">Etudiant</a></li>
    <li><a href="${pageContext.request.contextPath}/enseignantservlet">Enseignant</a></li>
    <li><a href="${pageContext.request.contextPath}/coursservlet">Cours</a></li>
    <li><a href="${pageContext.request.contextPath}/resultatservlet">Résultat</a></li>
    <%
    } else if ("Enseignant".equals(userRole)) {
    %>
    <li><a href="${pageContext.request.contextPath}/etudiantservlet">Etudiant</a></li>
    <li><a href="${pageContext.request.contextPath}/coursservlet">Cours</a></li>
    <li><a href="${pageContext.request.contextPath}/resultatservlet">Résultat</a></li>
    <%
    } else if ("Etudiant".equals(userRole)) {
    %>
    <li><a href="${pageContext.request.contextPath}/coursservlet">Cours</a></li>
    <li><a href="${pageContext.request.contextPath}/resultatservlet">Résultat</a></li>
    <%
    } else {
    %>
    <li>Rôle inconnu. Veuillez vous reconnecter.</li>
    <%
      }
    %>
    <li><a href="${pageContext.request.contextPath}/LogoutServlet">Déconnexion</a></li>
  </ul>
</nav>