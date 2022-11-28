package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import forms.AuthentificationForm;

//import beans.Utilisateur;

/**
 * Servlet implementation class Login
 */
@WebServlet({ "/login", "/logout" })
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VUE_LOGIN = "/WEB-INF/login.jsp";
	private static final String VUE_LIST = "/WEB-INF/listerUtilisateur.jsp";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Login() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		switch (request.getServletPath()) {
		case "/logout":
			request.getSession().invalidate();
			response.sendRedirect("login");
			break;
		case "/login":
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(VUE_LOGIN);
			dispatcher.forward(request, response);
			break;

		}
		/*
		 * RequestDispatcher dispatcher =
		 * getServletContext().getRequestDispatcher(VUE_LOGIN);
		 * dispatcher.forward(request, response);
		 */
		// response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// RequestDispatcher dispatcher =
		// getServletContext().getRequestDispatcher(VUE_LIST);
		/*
		 * String nameUser = (String)request.getParameter("nameUser"); String passUser =
		 * (String)request.getParameter("passe"); if ("passe".equals(passUser) &&
		 * "admin".equals(nameUser)) { request.getSession().setAttribute("utilisateur",
		 * new Utilisateur(1,"THIOMBIANO","Bedel",nameUser,passUser));
		 * dispatcher.forward(request, response);
		 * 
		 * request.getSession().setAttribute("isConnected", true);
		 * 
		 * response.sendRedirect("list"); } else { RequestDispatcher dispatcher =
		 * getServletContext().getRequestDispatcher(VUE_LIST);
		 * request.setAttribute("connexionFailed", true); request.setAttribute("login",
		 * nameUser); dispatcher = getServletContext().getRequestDispatcher(VUE_LOGIN);
		 * dispatcher.forward(request, response);
		 * 
		 * }
		 */

		AuthentificationForm form = new AuthentificationForm();
		boolean connexionFailed = form.connexion(request);
		if (connexionFailed) {
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(VUE_LIST);
			request.setAttribute("connexionFailed", true);
			request.setAttribute("login", request.getParameter("nameUser"));
			dispatcher = getServletContext().getRequestDispatcher(VUE_LOGIN);
			dispatcher.forward(request, response);
		} else {

			request.getSession().setAttribute("isConnected", true);
			response.sendRedirect("list");

		}

	}

}
