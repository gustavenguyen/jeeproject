package com.jeeproject.Models;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.jeeproject.Entities.Album;
import com.jeeproject.Entities.Song;



public class AlbumDAOImpl implements AlbumDAO{
	EntityManager em;
	@Override
	public Album find(Integer id) {
		// TODO Auto-generated method stub
		em = new EMProvider().getEM(); 
		Album album = em.find(Album.class, id);
		em.close();
		return album;
	}

	@Override
	public List<Album> getAlbums() {
		em = new EMProvider().getEM(); 
	    Query query = em.createQuery("select a from Album as a");
	    @SuppressWarnings("unchecked")
		List<Album> results = query.getResultList();
	    em.close();
	    return results;
	}
	@Override
	public List<Album> getAlbumsByCategory(String category) {
		em = new EMProvider().getEM(); 
		System.out.println(category);
	    Query query = em.createQuery("from Album  where category= :category");
	query.setParameter("category", category );
	    @SuppressWarnings("unchecked")
		List<Album> results = query.getResultList();
	    em.close();
	    return results;
	}
	public List<Album> getAlbumsByAuthor(String author_name) {
		em = new EMProvider().getEM(); 
		System.out.println(author_name);
	    String selectquery = "from Album a where a.artist.name like :authorname ";
		
	    Query query = em.createQuery(selectquery);
		
	    query.setParameter("authorname", "%"+author_name+"%" );
	   
		@SuppressWarnings("unchecked")
		List<Album> results = query.getResultList();
		 em.close();	
		return results;
       
	}
	public List<Album> getAlbumsByAlbumTitle(String album_title) {
		em = new EMProvider().getEM(); 
		
	    String selectquery = "from Album a where a.title like :title ";
		
	    Query query = em.createQuery(selectquery);
		
	    query.setParameter("title", "%"+album_title+"%" );
	    
		@SuppressWarnings("unchecked")
		List<Album> results = query.getResultList();
		 em.close();	
		return results;
       
	}
	public List<Album> getAlbumsBySongTitle(String song_title, String artist_name) {
		em = new EMProvider().getEM(); 
		
	    String selectquery = "from Song s where s.title like :songtitle ";
		
	   
		System.out.println(artist_name);
	    if(artist_name!=null){
	    	selectquery=selectquery+"and s.composer.name= :composername ";
	  
	    }
	    Query query = em.createQuery(selectquery);
	    query.setParameter("songtitle", "%"+song_title+"%" );
	    if(artist_name!=null){
	    query.setParameter("composername", artist_name );
	    }   
		@SuppressWarnings("unchecked")
		List<Song> results = query.getResultList();
		System.out.println("size"+results.size());
		List <Album> AlbumsResults = new ArrayList <Album>();
		for (int i=0;i<results.size();i++)
		{AlbumsResults.add(results.get(i).getAlbum());
		
		}
		Set <Album> SetAlbums=new HashSet<Album>(); //le set va permettre d'enlever les doublons
		for (int i=0;i<AlbumsResults.size();i++)
		{SetAlbums.add(AlbumsResults.get(i));
		
		}
		AlbumsResults = new ArrayList<Album>(SetAlbums);
		 em.close();	
		return AlbumsResults;
       
	}
public int updateAlbumRating(String album_id){

	em = new EMProvider().getEM();
	em.getTransaction().begin();
   
	Album albumToUpdate = em.find(Album.class, Integer.parseInt(album_id));
	int albumRating = albumToUpdate.getRating();
	System.out.println(""+ albumRating);
	albumRating=albumRating+1;
	albumToUpdate.setRating(albumRating);
	em.getTransaction().commit();	
             
    em.close(); 
    return albumRating;
}
}
   