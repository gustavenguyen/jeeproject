package com.jeeproject.Models;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.jeeproject.Entities.Album;



public class AlbumDAOImpl implements AlbumDAO{
	EntityManager em;
	@Override
	public Album find(Integer id) {
		// TODO Auto-generated method stub
		em = new EMProvider().getEM(); 
		Album album = em.find(Album.class, id);
		return album;
	}

	@Override
	public List<Album> getAlbums() {
		em = new EMProvider().getEM(); 
	    Query query = em.createQuery("select a from Album as a");
	    @SuppressWarnings("unchecked")
		List<Album> results = query.getResultList();
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

 
}
   