package forms;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import beans.Utilisateur;


public class UpdateUserForm {
	
	private static final String CHAMP_ID="id";
	private static final String CHAMP_NOM="nom";
	private static final String CHAMP_PRENOM="prenom";
	private static final String CHAMP_LOGIN="login";
	private static final String CHAMP_PASSWORD="password";
	private static final String CHAMP_PASSWORD_BIS="passwordBis";
	
	private static final String ECHEC_AJOUT_MESSAGE ="Echec de l'ajout";
	private static final String SUCCES_AJOUT_MESSAGE ="Ajout effectué";
	private static final String EMPTY_FIELD_ERROR_MESSAGE = "Vous devez renseigner ce champs";
	private static final String DIFFERENT_PASSWORD_ERROR_MESSAGE = "Les mots de passe ne sont pas identiques";
	
	private HttpServletRequest request;
	private Map<String, String> erreurs;
	private Utilisateur utilisateur;
	private boolean status;
	private String statusMessage;
	
	public UpdateUserForm(HttpServletRequest request){
		this.request = request;
		this.status = false;
		this.statusMessage = ECHEC_AJOUT_MESSAGE;
		this.erreurs = new HashMap<String, String>();
	}
	
	public void modifier() {
		int id = Integer.parseInt(request.getParameter(CHAMP_ID));
		String nom = this.getParameter(CHAMP_NOM);
		String prenom = this.getParameter(CHAMP_PRENOM);
		String login = this.getParameter(CHAMP_LOGIN);
		String password = this.getParameter(CHAMP_PASSWORD);
		//String passwordBis = this.getParameter(CHAMP_PASSWORD_BIS);
		
		this.utilisateur = new Utilisateur(id, nom, prenom, login, password);
		this.validerChamps(CHAMP_NOM, CHAMP_PRENOM, CHAMP_LOGIN,CHAMP_PASSWORD);
		
		if(this.erreurs.isEmpty()) {
			/*
			UtilisateurDao.ajouter(utilisateur);
			this.status = true;
			this.statusMessage = SUCCES_AJOUT_MESSAGE;
			*/
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/gestionusers" ,"root","");
				Statement stmt = conn.createStatement();
				String sql = "update utilisateurs set nom = '"+ utilisateur.getNom() + "', prenom = '" + utilisateur.getPrenom() +"', login = '" + utilisateur.getLogin() + "', password= '" + utilisateur.getPassword() + "' where id = '"+ utilisateur.getId() + "'";
				stmt.executeUpdate(sql);
				this.status = true;
				this.statusMessage = SUCCES_AJOUT_MESSAGE;
				
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			
		}
		
	}
			
	
	public Map<String, String> getErreurs() {
		return erreurs;
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public String getStatusMessage() {
		return statusMessage;
	}
	
	public boolean isStatus() {
		return status;
	}

	public String getParameter(String parametre) {
		String valeur = this.request.getParameter(parametre);
		return (valeur == null || valeur.trim().isEmpty()) ? null : valeur.trim();
	}
	
	public void validerChamps(String...champs) {
		for(String champ : champs)
		{	if(this.getParameter(champ) == null) {
			erreurs.put(champ, EMPTY_FIELD_ERROR_MESSAGE);
			
			}
		}
	}
	public void validerPasswords() {
		String password = this.getParameter(CHAMP_PASSWORD);
		String passwordBis = this.getParameter(CHAMP_PASSWORD_BIS);
		if(password != null && !password.equals(passwordBis)) {
			erreurs.put(CHAMP_PASSWORD, DIFFERENT_PASSWORD_ERROR_MESSAGE);
			erreurs.put(CHAMP_PASSWORD_BIS, DIFFERENT_PASSWORD_ERROR_MESSAGE);
		}
		
	}	
	
	
	public Utilisateur control() {
		String id = request.getParameter("id");
		if(id !=null && id.matches("[0-9]+")) {
			utilisateur =  this.getUser(Integer.parseInt(id));
			if(utilisateur != null) {
				
			return utilisateur;
			}
		}
		return null ;

	}
	
	public Utilisateur getUser(int id) {
		try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/gestionusers" ,"root","");
		PreparedStatement ps = conn.prepareStatement("select * from Utilisateurs where id = "+ id);
		ResultSet resultat = ps.executeQuery();
		ResultSetMetaData resultatEntete = resultat.getMetaData();
		while(resultat.next()){
			int i = 1;
			utilisateur = new Utilisateur(resultat.getInt(resultatEntete.getColumnName(i)),resultat.getString(resultatEntete.getColumnName(++i)), resultat.getString(resultatEntete.getColumnName(++i)), resultat.getString(resultatEntete.getColumnName(++i)), resultat.getString(resultatEntete.getColumnName(++i)) );
		}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return utilisateur;
		
	}
}
