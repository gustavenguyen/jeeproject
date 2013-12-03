package com.jeeproject.Services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.google.inject.Inject;
import com.jeeproject.Entities.Album;
import com.jeeproject.Models.AlbumDAO;
import com.jeeproject.Models.LikeDAO;


@Path("/api")
public class APIService {

	
	 private AlbumDAO albumdao;
	 private LikeDAO likedao;
	@Inject 
	public APIService(AlbumDAO albumDAO, LikeDAO likeDAO) { 
		this.albumdao = albumDAO;
		this.likedao=likeDAO;
	 }
	@GET
	@Path("/like")
	@Produces(MediaType.APPLICATION_JSON)
	public Map<String, Object> getIfUserAlreadyLiked(
			@QueryParam("album") String album_id,
			@QueryParam("user") String username) {
		System.out.println("service started...");
		int albumRating =0;
		
		Map<String, Object> results = new HashMap<String, Object>();
		if (likedao.HasUserLiked(album_id, username) != null) {
			System.out.println("user has already liked this album...");
			results.put("success", 0);

		} else {
		   albumRating = albumdao.updateAlbumRating(album_id);   
			likedao.addLike(username,album_id);
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
	    	map.put("artist", AlbumsList.get(i).getArtist().getName());
	    	AlbumMapList.add(map);
		
		}
		return AlbumMapList;
	}
}
