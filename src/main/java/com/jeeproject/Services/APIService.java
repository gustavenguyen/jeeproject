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
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.google.inject.Inject;
import com.jeeproject.Entities.Album;
import com.jeeproject.Entities.Like;
import com.jeeproject.Entities.User;
import com.jeeproject.Models.AlbumDAO;
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
	@Path("/albums/{param}")
	@Produces(MediaType.APPLICATION_JSON)
	public List <Map<String, String>> getAlbums(@PathParam("param") String param,
			@QueryParam("name") String name) {
		System.out.println("service started...");
		List <Map<String, String>> AlbumsMapList = new ArrayList <Map<String, String>>();
	if(param.equals("bycat"))
	{
		List <Album> AlbumsByCat = albumdao.getAlbumsByCategory(name); 
		AlbumsMapList= ConvertToMapList(AlbumsByCat);
		
		return AlbumsMapList;
	}
	else if(param.equals("byauthor"))
	{
        List <Album> AlbumsByAuth = albumdao.getAlbumsByAuthor(name,"all"); 
		AlbumsMapList= ConvertToMapList(AlbumsByAuth);
	}
	else if(param.equals("byalbumtitle"))
	{
        List <Album> AlbumsByAuth = albumdao.getAlbumsByAlbumTitle(name,"all"); 
		AlbumsMapList= ConvertToMapList(AlbumsByAuth);
	}
	else if(param.equals("bysongtitle"))
	{
        List <Album> AlbumsByAuth = albumdao.getAlbumsBySongTitle(name,"all"); 
		AlbumsMapList= ConvertToMapList(AlbumsByAuth);
	}
	return AlbumsMapList;
	
}
	
	public List <Map<String, String>> ConvertToMapList(List <Album> AlbumsList){
		
    List <Map<String, String>> AlbumMapList= new ArrayList <Map<String, String>> ();
		
		for(int i=0; i<AlbumsList.size();i++)
		{
			
			System.out.println(AlbumsList.size()+"");
			Map <String, String> map = new HashMap <String,String>();
			map.put("title", AlbumsList.get(i).getTitle());
			map.put("category", AlbumsList.get(i).getCategory());
	    	map.put("year", Integer.toString(AlbumsList.get(i).getYear()));
	    	map.put("Artist", AlbumsList.get(i).getArtist().getName());
	    	AlbumMapList.add(map);
		
		}
		return AlbumMapList;
	}
}
