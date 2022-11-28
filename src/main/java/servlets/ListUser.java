package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Utilisateur;
import forms.ListUserForm;

//import dao.UtilisateurDao;

/**
 * Servlet implementation class ListUser
 */
@WebServlet("/list")
public class ListUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static final String VUE_LIST_UTILISATEUR ="/WEB-INF/listerUtilisateur.jsp";   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListUser() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(VUE_LIST_UTILISATEUR);
		ArrayList<Utilisateur> utilisateurs = ListUserForm.lister();
		//setAttribute(utilisateurs);
		request.setAttribute("utilisateurs",utilisateurs);
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
