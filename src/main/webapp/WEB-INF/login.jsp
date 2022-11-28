<%@include file="inc/entete.jsp" %>

		<div id="corps">
			<h1 id="titre-principal"> Connexion</h1>
			<c:if test="${ connexionFailed }">
				<p class="errorMessage"> Login et/ou mot de passe incorrect</p>
			</c:if>
			<form method="post" action="login">
				<fieldset>
					<div class="formItem">
						<label for="login">login</label>
						<input type="text" name="admin" id="login" value="${login}" /> <br />
					</div>
					
					<div class="formItem">
						<label for="passe">Mot de passe</label>
						<input type="password" name="passer" id="passer"/> <br />
					</div>
					
					<div class="formItem">
						<input type="submit" value="Connexion" />
					</div>
				</fieldset>
			</form>	
		</div>
