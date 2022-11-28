package forms;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import java.util.ArrayList;

import beans.Utilisateur;

public class ListUserForm {

	//private static ArrayList<Utilisateur> utilisateurs = new ArrayList<Utilisateur>();
	
	public static ArrayList<Utilisateur> lister(){
		ArrayList<Utilisateur> utilisateurs = new ArrayList<Utilisateur>();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/gestionusers" ,"root","");
			PreparedStatement ps = conn.prepareStatement("select * from utilisateurs");
			ResultSet resultat = ps.executeQuery();
			ResultSetMetaData resultatEntete = resultat.getMetaData();
			//Statement stmt = conn.createStatement();
			while(resultat.next()){
				int i = 1;
				//for(int i=1; i<resultatEntete.getColumnCount();i++) {
					utilisateurs.add(new Utilisateur(resultat.getInt(resultatEntete.getColumnName(i)),resultat.getString(resultatEntete.getColumnName(++i)), resultat.getString(resultatEntete.getColumnName(++i)), resultat.getString(resultatEntete.getColumnName(++i)), resultat.getString(resultatEntete.getColumnName(++i)) ));
				
				//}
				
			}
			
			
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}	
		return utilisateurs;
	}
	
}
