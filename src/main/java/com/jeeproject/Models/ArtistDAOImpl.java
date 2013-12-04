package com.jeeproject.Models;



import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;


import com.jeeproject.Entities.Album;
import com.jeeproject.Entities.Artist;

public class ArtistDAOImpl implements ArtistDAO{
	EntityManager em;
	@Override
	public List <Artist> getArtistByName(String name) {
        em = new EMProvider().getEM(); 
		
	    String selectquery = "from Artist a where a.name like :name ";
		
	    Query query = em.createQuery(selectquery);
		
	    query.setParameter("name", "%"+name+"%" );
	    @SuppressWarnings("unchecked")
		List<Artist> results = query.getResultList();
		em.close();	
		return results;
	}

}
