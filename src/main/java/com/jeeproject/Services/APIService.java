package com.jeeproject.Services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.google.inject.Inject;
import com.jeeproject.AlbumDAO;
import com.jeeproject.Entities.Album;
import com.jeeproject.Entities.Like;
import com.jeeproject.Entities.User;
import com.jeeproject.Models.AlbumDAOImpl;
import com.jeeproject.Models.EMProvider;


import org.zdevra.guice.mvc.annotations.Controller;
import org.zdevra.guice.mvc.annotations.Model;
//import org.zdevra.guice.mvc.annotations.Path;
import org.zdevra.guice.mvc.annotations.View;
             
/**
 * User: marco Date: 14/10/13 Time: 12:36
 */
@Controller
@Path("/api")
public class APIService {

	/*
	 * 
	 * private AlbumDAO albumDAO;
	 * 
	 * @Inject public APIService(AlbumDAO albumDAO) { this.albumDAO = albumDAO;
	 * }
	 * 
	 * @Path("/([0-9]+)")
	 * 
	 * @Model("album")
	 * 
	 * @View("WEB-INF/jsp/album.jsp") public Album getAlbumByID(Integer id) {
	 * return albumDAO.find(id); }
	 */
	 private AlbumDAO albumdao;
	@Inject 
	public APIService(AlbumDAO albumDAO) { 
		this.albumdao = albumDAO;
	 }
	@GET
	@Path("/like")
	@Produces(MediaType.APPLICATION_JSON)
	public Map<String, Object> getIfUserAlreadyLiked(
			@QueryParam("album") String album_id,
			@QueryParam("user") String username) {
		System.out.println("service started...");
		EntityManager em = new EMProvider().getEM();
		Like CheckUserAlreadyLiked = null;
		int albumRating = 0;
		try {
			Query query = em.createQuery("SELECT l FROM Like l WHERE l.album.id= :albumid AND l.user.id=(Select u.id from User u where u.username= :username)");
			query.setParameter("albumid", Integer.parseInt(album_id));
			query.setParameter("username", username);

			CheckUserAlreadyLiked = (Like) query.getSingleResult();

		} catch (NoResultException e) {
			Album albumToUpdate =em.find(Album.class,Integer.parseInt(album_id));
			em.getTransaction().begin();
	       
	       
			albumRating = albumToUpdate.getRating();
			albumRating=albumRating+1;
			albumToUpdate.setRating(albumRating);
			  em.getTransaction().commit();	
                     
		         em.close(); 
				System.out.println("cest bon"+albumToUpdate.getRating());
			                                                                                                                         
		} catch (Exception e) {
			e.printStackTrace();
		}
		Map<String, Object> results = new HashMap<String, Object>();
		if (CheckUserAlreadyLiked != null) {

			results.put("success", 0);

		} else {
			results.put("success", 1);
            results.put("rating", albumRating);
		}

		return results;
	}
	
	@GET
	@Path("/albumbycat")
	@Produces(MediaType.APPLICATION_JSON)
	public List <Map<String, String>> getAlbumByCategory(
			@QueryParam("cat") String category) {
		System.out.println("service started...");
		
		
		List <Album> AlbumsByCat = albumdao.getAlbumsByCategory(category); 
		List <Map<String, String>> AlbumsByCatList= new ArrayList <Map<String, String>> ();
		
		for(int i=0; i<AlbumsByCat.size();i++)
		{
			
			System.out.println(AlbumsByCat.size()+"");
			Map <String, String> map = new HashMap <String,String>();
			map.put("title", AlbumsByCat.get(i).getTitle());
			map.put("category", AlbumsByCat.get(i).getCategory());
	    	map.put("title", AlbumsByCat.get(i).getYear()+"");
	    	map.put("Artist", AlbumsByCat.get(i).getArtist().getName());
	    	AlbumsByCatList.add(map);
		
		}
	    
		return AlbumsByCatList;

}
	@GET
	@Path("/albumbyauthor")
	@Produces(MediaType.APPLICATION_JSON)
	public List <Map<String, String>> getAlbumByAuthor(
			@QueryParam("author") String author) {
		System.out.println("service started...");
		
		AlbumDAO albumdao = new AlbumDAOImpl();
		List <Album> AlbumsByCat = albumdao.getAlbumsByAuthor(author,"all"); 
		List <Map<String, String>> AlbumsByCatList= new ArrayList <Map<String, String>> ();
		
		for(int i=0; i<AlbumsByCat.size();i++)
		{
			
			System.out.println(AlbumsByCat.size()+"");
			Map <String, String> map = new HashMap <String,String>();
			map.put("title", AlbumsByCat.get(i).getTitle());
			map.put("category", AlbumsByCat.get(i).getCategory());
	    	map.put("title", AlbumsByCat.get(i).getYear()+"");
	    	map.put("Artist", AlbumsByCat.get(i).getArtist().getName());
	    	AlbumsByCatList.add(map);
		
		}
	    
		return AlbumsByCatList;

}
}
