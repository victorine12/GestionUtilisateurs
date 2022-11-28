package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import forms.UpdateUserForm;

/**
 * Servlet implementation class Update
 */
@WebServlet("/update")
public class Update extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	private static final String VUE_UPDATE_USER= "/WEB-INF/modifierUtilisateur.jsp";
    public Update() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			UpdateUserForm form =  new UpdateUserForm(request);
			if(form.control() != null) {
				request.setAttribute("utilisateur", form.control());
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(VUE_UPDATE_USER);
				dispatcher.forward(request, response);
				return;
			}
		response.sendRedirect("list");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		UpdateUserForm form = new UpdateUserForm(request);
		form.modifier();
		
		if(!form.isStatus()) {
		request.setAttribute("status",form.isStatus());
		request.setAttribute("statusMessage", form.getStatusMessage());
		request.setAttribute("utilisateur", form.getUtilisateur());
		request.setAttribute("erreurs", form.getErreurs());
		getServletContext().getRequestDispatcher(VUE_UPDATE_USER).forward(request, response);
		}
		else {
			response.sendRedirect("list");
		}
	}

}
