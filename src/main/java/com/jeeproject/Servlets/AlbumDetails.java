package com.jeeproject.Servlets;


import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TreeSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jeeproject.Entities.Album;
import com.jeeproject.Entities.Comment;
import com.jeeproject.Entities.User;
import com.jeeproject.Models.AlbumDAO;
import com.jeeproject.Models.AlbumDAOImpl;
import com.jeeproject.Models.CommentDAO;
import com.jeeproject.Models.CommentDAOImpl;
import com.jeeproject.Models.LikeDAO;
import com.jeeproject.Models.LikeDAOImpl;

/**
 * Servlet implementation class AlbumDetails
 */

public class AlbumDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String VUE = "/WEB-INF/jsp/albumdetails.jsp";
	String commentText;
	int album_id;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AlbumDetails() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		album_id= Integer.parseInt(request.getParameter("album"));
		AlbumDAO albumdao = new AlbumDAOImpl();
		LikeDAO likedao = new LikeDAOImpl();
		Album chosen_album=albumdao.find(album_id);
		System.out.println("chosen"+chosen_album.getTitle());
		// TODO Auto-generated method stub
		
		
		 request.setAttribute( "chosen_album", chosen_album);
		
		 CommentDAO commentdao = new CommentDAOImpl();  
		 List <Comment> CommentsList =commentdao.getCommentsByAlbum(album_id);
			
		 if(request.getSession(false) != null){
			    HttpSession session = request.getSession();
			  User loggedUser = (User) session.getAttribute("session_user");
			if(loggedUser!=null)
			{ System.out.println("connecté");
			  if(likedao.HasUserLiked(request.getParameter("album"), loggedUser.getUsername())!=null)
				  request.setAttribute( "hasliked", "yes");
			  else
				  request.setAttribute( "hasliked", "no");
			}
			else
				  System.out.println("pas connecté");
			}
			
		 /*	for (int i=0;i<CommentsList.size();i++)
			{System.out.println(CommentsList.get(i).getMessage());
			
			}
			
		*/	   
			 request.setAttribute( "CommentsList", CommentsList);
		 
		 
		 this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		album_id= Integer.parseInt(request.getParameter("album"));
		AlbumDAO albumdao = new AlbumDAOImpl();
		Album chosen_album=albumdao.find(album_id);
		HttpSession session = request.getSession(true);   
		User userconnected = (User) session.getAttribute("session_user");
   
	
			
	    if (request.getParameter("comment_value").trim().length() > 0 )
		{System.out.println("not null");
	    commentText = request.getParameter("comment_value");
	    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
	    String currentDate = sdf.format(new Date());
	    Comment newComment = new Comment(userconnected,commentText, new Date(), chosen_album);
	    CommentDAO commentDao = new CommentDAOImpl();
	    commentDao.addComment(newComment);
	
	    response.sendRedirect(request.getContextPath()+"/albumdetails?album="+album_id);  
		}
	    else
	    {
	    	
	    	
	    	request.setAttribute( "error", "empty text" );
	    	   this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
	    	
	    	
	}
	    }
}
