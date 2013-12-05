package com.jeeproject.Services;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import java.util.Map;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.google.inject.Inject;
import com.jeeproject.Entities.Album;
import com.jeeproject.Entities.Artist;
import com.jeeproject.Entities.Comment;
import com.jeeproject.Entities.Song;
import com.jeeproject.Entities.User;
import com.jeeproject.Models.AlbumDAO;
import com.jeeproject.Models.ArtistDAO;
import com.jeeproject.Models.CommentDAO;
import com.jeeproject.Models.CommentDAOImpl;
import com.jeeproject.Models.LikeDAO;
import com.jeeproject.Models.SongDAO;
import com.jeeproject.Models.UserDAO;


@Path("/api")
public class APIService {

	
	 private AlbumDAO albumdao;
	 private LikeDAO likedao;
	 private ArtistDAO artistdao;
	 private SongDAO songdao;
	 private UserDAO userdao;
	 private CommentDAO commentdao;
	@Inject 
	public APIService(AlbumDAO albumDAO, LikeDAO likeDAO, ArtistDAO artistDAO, SongDAO songDAO,UserDAO userDAO, CommentDAO commentDAO) { 
		this.albumdao = albumDAO;
		this.likedao=likeDAO;
		this.artistdao=artistDAO;
		this.songdao=songDAO;
		this.userdao=userDAO;
		this.commentdao=commentDAO;
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
	@POST
	@Path("/comments/add")
	@Produces(MediaType.APPLICATION_JSON)
	public Map<String, String> addComment(
			 @FormParam("comment_value") String comment_value,
			 @FormParam("album") String album_id,
			 @FormParam("loggeduser") String loggeduser_name) {
		System.out.println("service started...");
		
		System.out.println("loggedyser"+loggeduser_name);
		Map<String, String> results = new HashMap<String, String>();
		 if (comment_value.trim().length() > 0 )
			{System.out.println("not null");
	
			Album chosen_album=albumdao.find(Integer.parseInt(album_id));
			User loggeduser = userdao.findUserByName(loggeduser_name.trim());
	        Comment newComment = new Comment(loggeduser,comment_value, new Date(), chosen_album);
		    
		    commentdao.addComment(newComment);
		
		    results.put("success", "1");
			}
		    else
		    {
		    	results.put("success", "0");
		    	
		    	
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
        List <Album> AlbumsByAuth = albumdao.getAlbumsByAuthor(name); 
		AlbumsMapList= ConvertToMapList(AlbumsByAuth);
	}
	else if(param.equals("byalbumtitle"))
	{
        List <Album> AlbumsByAuth = albumdao.getAlbumsByAlbumTitle(name); 
		AlbumsMapList= ConvertToMapList(AlbumsByAuth);
	}
	else if(param.equals("bysongtitle"))
	{
        List <Album> AlbumsByAuth = albumdao.getAlbumsBySongTitle(name,null); 
		AlbumsMapList= ConvertToMapList(AlbumsByAuth);
	}
	return AlbumsMapList;
	
}   
	@GET
	@Path("/artists/byname")
	@Produces(MediaType.APPLICATION_JSON)
	public List <Map<String, String>> getComposerByName(
			@QueryParam("name") String name) {
		System.out.println("service started...");
		 List <Map<String, String>> ArtistMapList= new ArrayList <Map<String, String>> ();
	
		List <Artist> Artists = artistdao.getArtistByName(name); 
		for(int i=0; i<Artists.size();i++)
		{
			Map <String, String> map = new HashMap <String,String>();
		
			map.put("name", Artists.get(i).getName());
			ArtistMapList.add(map);
		
		}
		return ArtistMapList;
	}
	@GET
	@Path("/songs/bytitle")
	@Produces(MediaType.APPLICATION_JSON)
	public List <Map<String, String>> getSongListByTitle(
			@QueryParam("name") String name) {
		System.out.println("service started...");
		 List <Map<String, String>> SongMapList= new ArrayList <Map<String, String>> ();
	
		List <Song> Songs = songdao.getSongByTitle(name); 
		for(int i=0; i<Songs.size();i++)
		{
			Map <String, String> map = new HashMap <String,String>();
		
			map.put("title", Songs.get(i).getTitle());
			map.put("artist", Songs.get(i).getComposer().getName());
			SongMapList.add(map);
		
		}
		return SongMapList;
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
