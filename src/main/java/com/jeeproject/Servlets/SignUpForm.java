package com.jeeproject.Servlets;


import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jeeproject.Entities.User;
import com.jeeproject.Models.FormModel;

/**
 * Servlet implementation class Formular
 */

public class SignUpForm extends HttpServlet {
	private static final long serialVersionUID = 1L;
	  public static final String VUE = "/WEB-INF/jsp/signup.jsp";
	  
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignUpForm() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 HttpSession session = request.getSession();   
			FormModel Form = new FormModel();
			  User newUser = Form.subscribeUser(request);
			  Map <String,String> MapErrors = Form.getErrors();
			  if(MapErrors.size()==0 )
			  {
					 session.setAttribute( "session_user", newUser);
				 
			        request.setAttribute( "user", newUser );
			        response.sendRedirect(request.getContextPath());  
			        
			  
			  }
			  else
			  {
				
				 System.out.println(MapErrors.get("username"));
				 System.out.println(MapErrors.get("password"));
				 System.out.println(MapErrors.get("email"));
				 request.setAttribute( "user", newUser );
				 request.setAttribute("MapErrors", MapErrors);
			  session.setAttribute( "session_user", null );
			  this.getServletContext().getRequestDispatcher(VUE).forward( request, response );
		//	  response.sendRedirect(request.getContextPath());
		
			  }		 
	}

}
