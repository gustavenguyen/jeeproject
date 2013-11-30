package com.jeeproject.Models;

import java.util.ArrayList;
import java.util.List;

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
	public List<Album> getAlbumsByAuthor(String author_name, String category) {
		em = new EMProvider().getEM(); 
		System.out.println(author_name);
	    String selectquery = "from Album a where a.artist.name like :authorname ";
		if(!category.equals("all"))
			selectquery=selectquery+"and a.category= :category ";
	    Query query = em.createQuery(selectquery);
		
	    query.setParameter("authorname", "%"+author_name+"%" );
	    if(!category.equals("all"))
		{
	    query.setParameter("category", category );
		}
		@SuppressWarnings("unchecked")
		List<Album> results = query.getResultList();
		 em.close();	
		return results;
       
	}
	public List<Album> getAlbumsByAlbumTitle(String album_title, String category) {
		em = new EMProvider().getEM(); 
		
	    String selectquery = "from Album a where a.title like :title ";
		if(!category.equals("all"))
			selectquery=selectquery+"and a.category= :category ";
	    Query query = em.createQuery(selectquery);
		
	    query.setParameter("title", "%"+album_title+"%" );
	    if(!category.equals("all"))
		{
	    query.setParameter("category", category );
		}
		@SuppressWarnings("unchecked")
		List<Album> results = query.getResultList();
		 em.close();	
		return results;
       
	}
	public List<Album> getAlbumsBySongTitle(String song_title, String category) {
		em = new EMProvider().getEM(); 
		
	    String selectquery = "from Song s where s.title like :songtitle ";
		
	    Query query = em.createQuery(selectquery);
		
	    query.setParameter("songtitle", "%"+song_title+"%" );
	   
		@SuppressWarnings("unchecked")
		List<Song> results = query.getResultList();
		System.out.println("size"+results.size());
		List <Album> AlbumsResults = new ArrayList <Album>();
		
		for (int i=0; i<results.size();i++ )
		{
			if(!category.equals("all"))
			{   if(results.get(i).getAlbum().getCategory().equals(category))
			    	 AlbumsResults.add(results.get(i).getAlbum());
			}    
			else
		      AlbumsResults.add(results.get(i).getAlbum());
			    	 
		}
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
   