<%@page import="beans.Utilisateur"%>
<%@page import="java.util.ArrayList"%>
<%@include file="inc/entete.jsp" %>
<div id="corps">
			<h1 id="titre-principal">Liste des utilisateurs</h1>
			<c:choose>
				<c:when test="${ empty utilisateurs }">
					<p>La liste des utilisateurs est vide !</p>				
				</c:when>
				<c:otherwise>
					<table border="1">
						<tr>
							<th>ID</th>
							<th>Nom</th>
							<th>Prenom (s)</th>
							<th>Login</th>
							<th>Password</th>
							<th colspan="2"> Actions </th>
						</tr>
					<c:forEach var="utilisateur" items="${ utilisateurs }">
						<tr>
							<td><c:out value="${utilisateur.id}"></c:out></td>
							<td><c:out value="${utilisateur.nom}"></c:out></td>
							<td><c:out value="${utilisateur.prenom}"></c:out></td>
							<td><c:out value="${utilisateur.login}"></c:out></td>	
							<td><c:out value="${utilisateur.password}"></c:out></td>
							<td><a href="update?id=${utilisateur.id}">Modifier</a></td>
							<td><a href="delete?id=${utilisateur.id}" onclick="return confirmerSuppression()">Supprimer</a></td>
						</tr>
					</c:forEach>
					</table>
				</c:otherwise>
			</c:choose>

</div>
	<%@include file="inc/pied.jsp" %>