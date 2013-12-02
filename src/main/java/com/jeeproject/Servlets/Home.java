package com.jeeproject.Servlets;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jeeproject.Entities.Album;
import com.jeeproject.Entities.Song;
import com.jeeproject.Entities.User;
import com.jeeproject.Models.AlbumDAO;
import com.jeeproject.Models.AlbumDAOImpl;
import com.jeeproject.Models.EMProvider;
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
		
	/*	List<Album> phoenix = albumdao.getAlbumsByAlbumTitle("amad", "all");
		Song lisztomania = new Song(1,"Lisztomania",phoenix.get(0).getArtist(),phoenix.get(0));
		try {
				
			EntityManager em = new EMProvider().getEM();	
			em.getTransaction().begin();
	         em.persist(lisztomania);
	         em.getTransaction().commit();
	         em.close(); 
	         } catch ( Exception e ) {
	      e.printStackTrace();
	     };*/
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
		   
		
	
	else if(request.getParameter("keywords_checkbox")!=null && request.getParameter("search_name").trim().length()>0){
		String search_type = request.getParameter("search_type");
		
		String search_name = request.getParameter("search_name");
		String category_selected = request.getParameter("category_selected");
		if(search_type.equals("author"))
			AlbumsList = albumdao.getAlbumsByAuthor(search_name, category_selected);
		if(search_type.equals("albumtitle"))
			AlbumsList = albumdao.getAlbumsByAlbumTitle(search_name, category_selected);
		if(search_type.equals("songtitle"))
			AlbumsList = albumdao.getAlbumsBySongTitle(search_name, category_selected);
	}
	int AlbumsListSize = AlbumsList.size();
	if(request.getParameter("keywords_checkbox")!=null && request.getParameter("search_name").trim().length()==0)
		AlbumsListSize=0;
	

	
	if(AlbumsListSize>0)
	{int iItemsByPage = 10;
	double itemsByPage = (double) iItemsByPage;
	int page=1;
	if(request.getParameter("page")!=null)
	{
	page =Integer.parseInt(request.getParameter("page"));
	String s=request.getQueryString();
	String str[]=s.split("&");
	String querystring = "";
	for (int i =0; i< str.length;i++)
	{if(!str[i].matches("page=[0-9]*"))
		if(i==0)
	    querystring=str[i];
		else
	    querystring=querystring+"&"+str[i];
	}
	System.out.println(querystring);
	request.setAttribute( "QueryString", querystring);
	}
	else
	{
		String s=request.getQueryString();
		request.setAttribute( "QueryString", s);
	}
	System.out.println(page+"");
//	double dAlbumsListSize = (double) AlbumsListSize ;
	int AlbumsListSizeModulo = AlbumsListSize%iItemsByPage;
	double dNumberOfPages = Math.ceil(AlbumsListSize/itemsByPage);
	int iNumberOfPages = (int)dNumberOfPages;
	System.out.println("size"+AlbumsListSize + "reste" + AlbumsListSizeModulo+"numberofpages"+iNumberOfPages);
	List <Album> AlbumsByPage = new ArrayList <Album>(); 
	
	 if(page == iNumberOfPages &&  AlbumsListSizeModulo!=0)
	{
		
		for (int i=((page-1)*iItemsByPage);i<(((page-1)*iItemsByPage)+AlbumsListSizeModulo);i++) 
			AlbumsByPage.add(AlbumsList.get(i));
	}
	else if(page < iNumberOfPages ||  AlbumsListSizeModulo==0)
	{
		
		for (int i=((page-1)*iItemsByPage);i<(page*iItemsByPage);i++) 
			AlbumsByPage.add(AlbumsList.get(i));
	}
	 System.out.println(request.getQueryString());
	request.setAttribute( "AlbumsByPage", AlbumsByPage);
	request.setAttribute( "numPages", iNumberOfPages);
	request.setAttribute( "rest", AlbumsListSizeModulo );
	}
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