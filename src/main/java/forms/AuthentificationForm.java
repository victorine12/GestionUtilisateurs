package forms;

import javax.servlet.http.HttpServletRequest;

public class AuthentificationForm {

	private boolean ConnexionFailed = true;
	
	public AuthentificationForm() {
		
	}
	
	public boolean connexion(HttpServletRequest request) {

		String login = (String)request.getParameter("admin");
		String password = (String)request.getParameter("passer");
		if ("admin".equals(login) && "passer".equals(password)) {

			ConnexionFailed = false;
			
		}
		
		return ConnexionFailed;
			
		}		
		
}
