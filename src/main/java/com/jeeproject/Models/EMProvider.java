package com.jeeproject.Models;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EMProvider {                         // permet de r�cup�rer un entity manager

	
	private static EntityManagerFactory emf;
	
	public EMProvider(){


	}

	public void createEMF() {
            emf = Persistence.createEntityManagerFactory("JPAmanager");
          System.out.println("cr�ation EMF");
        }
        
    
	

public EntityManager getEM() {
	 if(emf == null) 
	 createEMF();
      return emf.createEntityManager();
    } 
}
