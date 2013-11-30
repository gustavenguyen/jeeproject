package com.jeeproject.Models;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import com.jeeproject.Entities.Album;
import com.jeeproject.Entities.Like;
import com.jeeproject.Entities.User;

public class LikeDAOImpl implements LikeDAO{
private EntityManager em;

	public Like HasUserLiked(String album_id, String username){
		try {
			em = new EMProvider().getEM();
			System.out.println(album_id+" " + username);
			Query query = em.createQuery("SELECT l FROM Like l WHERE l.album.id= :albumid AND l.user.id=(Select u.id from User u where u.username= :username)");
			query.setParameter("albumid", Integer.parseInt(album_id));
			query.setParameter("username", username);

			Like HasLiked = (Like) query.getSingleResult();
            em.close();
			return HasLiked;
		} catch (NoResultException e) {
	   return null;
		}
		catch (Exception e) {
			e.printStackTrace();
		return null;
		}

}
	public void addLike (String username, String album_id){
		
		 try {
			
			 em = new EMProvider().getEM();	
		
			 em.getTransaction().begin();
			 Query queryForUser = em.createQuery("from User u where u.username= :username)");
			
				queryForUser.setParameter("username", username);

				User user  = (User) queryForUser.getSingleResult();
				Query queryForAlbum = em.createQuery("from Album a where a.id= :albumid)");
				
				queryForAlbum.setParameter("albumid", Integer.parseInt(album_id));

				Album album  = (Album) queryForAlbum.getSingleResult();
	        Like newLike = new Like(user, album);
				em.persist(newLike);
	         em.getTransaction().commit();
	         em.close(); 
	         } catch ( Exception e ) {
	      e.printStackTrace();
	     };
		

	}
	
}
