package dao;

import java.util.ArrayList;

import beans.Utilisateur;

public class UtilisateurDao {

	private static int lastId = 1;
	private static ArrayList<Utilisateur> utilisateurs = new ArrayList<Utilisateur>();
	public static boolean ajouter(Utilisateur utilisateur) {
		utilisateur.setId(lastId++);
		utilisateurs.add(utilisateur);
		
		return true;
		
	}
	
	public static ArrayList<Utilisateur> lister(){
		return utilisateurs;
	}
	
	public static boolean supprimer(int id) {
		for(Utilisateur utilisateur : utilisateurs) {
			if(utilisateur.getId() == id) {
				utilisateurs.remove(utilisateur);
				return true;
			}
		}
		return false;
	}
	
	public static boolean modifier(Utilisateur user) {
		for(Utilisateur utilisateur : utilisateurs) {
			if(utilisateur.getId() == user.getId()) {
				utilisateur.setNom(user.getNom());
				utilisateur.setPrenom(user.getPrenom());
				utilisateur.setLogin(user.getLogin());
				utilisateur.setPassword(user.getPassword());
				
				return true;
			}
		}
		return false;
	}
	
	public static Utilisateur get(int id) {
		for(Utilisateur utilisateur : utilisateurs) {
			if(utilisateur.getId() == id) {
				return utilisateur;
			}
		}
		return null;
	}
}
