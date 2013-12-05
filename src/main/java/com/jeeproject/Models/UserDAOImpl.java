package com.jeeproject.Models;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import com.jeeproject.Entities.Album;
import com.jeeproject.Entities.User;



public class UserDAOImpl implements UserDAO{

	private EntityManager em;
	
	public UserDAOImpl(){}
	
	
	public void AddUser(User newUser){
		
		 try {
			
			 em = new EMProvider().getEM();	
			em.getTransaction().begin();
	         em.persist(newUser);
	         em.getTransaction().commit();
	         em.close(); 
	         } catch ( Exception e ) {
	      e.printStackTrace();
	      em.close(); 
	     };
		

	}
	
	@Override
	public User findUserByName(String username) {
		em = new EMProvider().getEM(); 
		try{
		Query query = em.createQuery("from User u where u.username= :username ");
		query.setParameter("username", username);
		
			User user = (User) query.getSingleResult();
			 em.close(); 
		    return user;

		} catch ( NoResultException e ) {
			 em.close(); 
	        return null;
	    } catch ( Exception e ) {
	       e.printStackTrace();
	       em.close(); 
	       return null;
	    }
	}

	@Override
	public User ConnectUser(String username, String psswd) {
		// TODO Auto-generated method stub
		em = new EMProvider().getEM(); 
	try{
	    Query query = em.createQuery("from User u where u.username= :username and u.password =:password");
	query.setParameter("username", username);
	query.setParameter("password", sha1(psswd));
		User userConnected = (User) query.getSingleResult();
		 em.close(); 
	    return userConnected;

	} catch ( NoResultException e ) {
		 em.close(); 
        return null;
    } catch ( Exception e ) {
    	 em.close(); 
       e.printStackTrace();
       return null;
    }
	}
	
	@Override
	public boolean CheckUsernameAvailable(String username) {
		em = new EMProvider().getEM(); 
		try{
		    Query query = em.createQuery("from User u where u.username= :username");
		query.setParameter("username", username);
		
			User user = (User) query.getSingleResult();
			 em.close(); 
			if(user!=null)
			return false;
		   else 
			   return true;

		} catch ( NoResultException e ) {
			 em.close(); 
	        return true;
	    } catch ( Exception e ) {
	       e.printStackTrace();
	       em.close(); 
	       return false;
	    }
	}


	@Override
	public boolean CheckEmailAvailable(String email) {
		em = new EMProvider().getEM(); 
		try{
		    Query query = em.createQuery("from User u where u.email= :email");
		query.setParameter("email", email);
		
			User user = (User) query.getSingleResult();
			 em.close(); 
		   if(user!=null)
			return false;
		   else 
			   return true;

		} catch ( NoResultException e ) {
			 em.close(); 
	        return true;
	    } catch ( Exception e ) {
	       e.printStackTrace();
	       em.close(); 
	       return false;
	    }
	}
	public static String sha1(String input) throws NoSuchAlgorithmException {
		MessageDigest mDigest = MessageDigest.getInstance("SHA1");
		byte[] result = mDigest.digest(input.getBytes());
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < result.length; i++) {
			sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16)
					.substring(1));
		}

		return sb.toString();
	}


	
	 
}
