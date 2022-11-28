package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import forms.AddUserForm;

/**
 * Servlet implementation class Ajouter
 */
@WebServlet("/ajouter")
public class Ajouter extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	private static final String VUE_AJOUT_USER= "/WEB-INF/ajouterUtilisateur.jsp";
    public Ajouter() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(VUE_AJOUT_USER);
		dispatcher.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		AddUserForm form = new AddUserForm(request);
		form.Ajouter();
		
		request.setAttribute("status",form.isStatus());
		request.setAttribute("statusMessage", form.getStatusMessage());
		request.setAttribute("utilisateur", form.getUtilisateur());
		request.setAttribute("erreurs", form.getErreurs());
		
		
		getServletContext().getRequestDispatcher(VUE_AJOUT_USER).forward(request, response);
		
		/*
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String password = request.getParameter("password");
		String login = request.getParameter("login");
		Utilisateur utilisateur = new Utilisateur(nom, prenom, login, password);
		//UtilisateurDao utilisateurs = new UtilisateurDao();
		UtilisateurDao.ajouter(utilisateur);
		response.sendRedirect("list");
		*/
	}

}
