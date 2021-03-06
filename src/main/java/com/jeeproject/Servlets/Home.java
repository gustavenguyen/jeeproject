package com.jeeproject.Servlets;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;


import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jeeproject.Entities.Album;
import com.jeeproject.Entities.Artist;
import com.jeeproject.Entities.User;
import com.jeeproject.Models.AlbumDAO;
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
			TreeSet <String> CategoriesList=new TreeSet<String>(String.CASE_INSENSITIVE_ORDER); //retrouve les diff�rentes cat�gories qu'il y a dans la bdd
			for (int i=0;i<AlbumsList.size();i++)
			{CategoriesList.add(AlbumsList.get(i).getCategory());
			
			}
			request.setAttribute( "CategoriesList", CategoriesList);
			  	
			
			// en fonction de la recherche de l'utilisateur, renvoie une liste d'albums sp�cifique
	        if(request.getParameter("search_type")!=null && request.getParameter("search_type").equals("category") && request.getParameter("category_selected")!=null && !request.getParameter("category_selected").equals("all"))
	
		    {
		    
		    AlbumsList = albumdao.getAlbumsByCategory(request.getParameter("category_selected"));
		    
		    }
		   
		

	else if(request.getParameter("search_type")!= null && request.getParameter("search_name")!=null && request.getParameter("search_name").trim().length()>0){
		String search_type = request.getParameter("search_type");
		
		String search_name = request.getParameter("search_name");
		
		if(search_type.equals("author"))
			AlbumsList = albumdao.getAlbumsByAuthor(search_name);
		if(search_type.equals("albumtitle"))
			AlbumsList = albumdao.getAlbumsByAlbumTitle(search_name);
		if(search_type.equals("songtitle"))
		{
			if(request.getParameter("artist")!=null && request.getParameter("artist").trim().length()>0) 
			AlbumsList = albumdao.getAlbumsBySongTitle(search_name,request.getParameter("artist").trim());
			else
			AlbumsList = albumdao.getAlbumsBySongTitle(search_name,null);
		}
		}
	
	int AlbumsListSize = AlbumsList.size();
	/*if(request.getParameter("keywords_checkbox")!=null && request.getParameter("search_name").trim().length()==0)
		AlbumsListSize=0;
	*/

	
	if(AlbumsListSize>0)                                     //g�re l'affichage par page
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
	
	//g�re le log in
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
				System.out.println("vous etes connect�");
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