package com.jeeproject.Models;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import com.jeeproject.Entities.Comment;



public class CommentDAOImpl implements CommentDAO{

	private EntityManager em;
	public CommentDAOImpl(){
		
	}
	@Override
	public List <Comment> getCommentsByAlbum(int album_id) {
	try{	em = new EMProvider().getEM(); 
	    Query query = em.createQuery("from Comment as c where album.id= :albumid");
	query.setParameter("albumid", album_id );
	    @SuppressWarnings("unchecked")
		List<Comment> results = query.getResultList();
	
	    return results;
	} catch ( NoResultException e ) {
        return null;
    } catch ( Exception e ) {
       e.printStackTrace();
       return null;
    }
	}

	@Override
	public void addComment(Comment newComment) {
		 try {
				
			 em = new EMProvider().getEM();	
			em.getTransaction().begin();
	         em.persist(newComment);
	         em.getTransaction().commit();
	         em.close(); 
	         } catch ( Exception e ) {
	      e.printStackTrace();
	     };
		

	}

}
