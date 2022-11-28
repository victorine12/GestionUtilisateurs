package forms;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

import javax.servlet.http.HttpServletRequest;

import beans.Utilisateur;

public class DeleteUserForm {
	
	private HttpServletRequest request;
	private Utilisateur utilisateur;
	
	public DeleteUserForm(HttpServletRequest request) {
		this.request = request;
	}
	
	public boolean supprimer() {
		String id = request.getParameter("id");
		if(id !=null && id.matches("[0-9]+")) {
			utilisateur =  this.getUser(Integer.parseInt(id));
			if(utilisateur != null) {
				
				try {
					
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/gestionusers" ,"root","");
					Statement stmt = conn.createStatement();
					String sql = "delete from Utilisateurs where id = '"+ utilisateur.getId() + "'";
					stmt.executeUpdate(sql);
					
				}
				catch(Exception e) {
					e.printStackTrace();
				}
			return true;
			}
		}
		return false ;

	}
	
	public Utilisateur getUser(int id) {
		try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/gestionusers" ,"root","");
		PreparedStatement ps = conn.prepareStatement("select * from utilisateurs where id = "+ id);
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
