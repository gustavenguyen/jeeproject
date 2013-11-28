package com.jeeproject.Servlets;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jeeproject.AlbumDAO;
import com.jeeproject.Entities.Album;
import com.jeeproject.Entities.User;
import com.jeeproject.Models.AlbumDAOImpl;
import com.jeeproject.Models.UserDAO;
import com.jeeproject.Models.UserDAOImpl;

/**
 * Servlet implementation class Home
 */

public class Home extends HttpServlet {
	private static final long serialVersionUID = 1L;
	  public static final String VUE = "/WEB-INF/jsp/index.jsp";
	AlbumDAO albumdao = new AlbumDAOImpl();     
	 List <Album> AlbumsList ;
	/**
     * @see HttpServlet#HttpServlet()
     */
 

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		 AlbumsList = albumdao.getAlbums();
			TreeSet <String> CategoriesList=new TreeSet<String>(String.CASE_INSENSITIVE_ORDER);
			for (int i=0;i<AlbumsList.size();i++)
			{CategoriesList.add(AlbumsList.get(i).getCategory());
			
			}
			request.setAttribute( "CategoriesList", CategoriesList);
			  	
	if(request.getParameter("keywords_checkbox")==null && request.getParameter("category_selected")!= null && !request.getParameter("category_selected").equals("all"))
		    {
		    System.out.println("get null"+ request.getParameter("category_selected"));
		    AlbumsList = albumdao.getAlbumsByCategory(request.getParameter("category_selected"));
		   	System.out.println(AlbumsList.get(0));    
		    }
		   
		
	
	else if(request.getParameter("keywords_checkbox")!=null){
		String search_type = request.getParameter("search_type");
		
		String search_name = request.getParameter("search_name");
		String category_selected = request.getParameter("category_selected");
		if(search_type.equals("author"))
			AlbumsList = albumdao.getAlbumsByAuthor(search_name, category_selected);
	}
	 request.setAttribute( "AlbumsList", AlbumsList);
		this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	
	
		String username = request.getParameter("username");
		String passwd = request.getParameter("psswd");
		
		System.out.println("user:"+username);
		PrintWriter out = response.getWriter();
		if(username.trim().length()>0 && passwd.trim().length()>0)
			{
			
			UserDAO userDao = new UserDAOImpl();
			User userConnected = userDao.ConnectUser(username,passwd);
			if(userConnected!=null)
			{	
				System.out.println("vous etes connecté");
				HttpSession session = request.getSession();  
				session.setAttribute( "session_user", userConnected);
                  User userco = (User) session.getAttribute("session_user");
		        System.out.println(userco.getUsername());
				
				
			}
			else
			{  out.println("wrong");
		
			}
		
	}
		else
		{  out.println("wrong");
	
		}

} }  