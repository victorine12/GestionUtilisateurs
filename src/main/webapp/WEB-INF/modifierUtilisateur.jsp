<%@page import="beans.Utilisateur"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@include file="inc/entete.jsp" %>
		<div id="corps">
		<h1 id="titre-principal">Modification d'un utilisateur</h1>
		<form method="post" >
			<fieldset>
					<div class="formItem">
						<label for="nom"> Nom </label>
						<input type="text" id="nom" name ="nom" value ="${utilisateur.nom}" /> <br />
						<span class="errorMessage">${erreurs.nom}</span>
					</div>
					
					<div class="formItem">
						<label for="prenom"> Prénom </label>
						<input type="text" id="prenom" name="prenom" value ="${utilisateur.prenom}" /> <br />
						<span class="errorMessage">${erreurs.prenom}</span>
					</div>
					
					<div class="formItem">
						<label for="login"> Login </label>
						<input type="text" id="login" name="login" value ="${utilisateur.login}" /> <br>
						<span class="errorMessage">${erreurs.login}</span>
					</div>
									
					<div class="formItem">
						<label for="password"> Password </label>
						<input type="password" id="password" name="password" value ="${utilisateur.password}" /> <br />
						<span class="errorMessage">${erreurs.password}</span>
					</div>
					
					
					<div class="formItem">
						<label></label>	
						<input type="submit" value ="Modifier" />
					</div>
			</fieldset>
		</form>	
		</div>
<%@include file="inc/pied.jsp" %>