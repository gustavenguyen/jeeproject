package com.jeeproject.Models;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.jeeproject.Entities.Song;

public class SongDAOImpl implements SongDAO{
    EntityManager em;
	@Override
	public List <Song> getSongByTitle(String name) {
		  em = new EMProvider().getEM(); 
			
		    String selectquery = "from Song s where s.title like :name ";
			
		    Query query = em.createQuery(selectquery);
			
		    query.setParameter("name", "%"+name+"%" );
		   
			@SuppressWarnings("unchecked")
			List <Song> results = query.getResultList();
			 em.close();	
			return results;
		}
}
