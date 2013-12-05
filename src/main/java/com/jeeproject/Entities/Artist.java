package com.jeeproject.Entities;


import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="artist") 
public class Artist {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
                                                          
    private String name;
    
public Artist (){
    	
    	
    }
    public Artist (String name){
    	
    	this.name=name;
    }
public Artist (int id, String name){
    	this.id=id;
    	this.name=name;
    }        
    
    public Integer getId() {
        return id;
    }

    public String getName() { 
        return name;
    }

     
	public void setName(String name) {
        this.name = name;
    }
	 @Override
	    public boolean equals(Object obj)
	    {
	        if (obj == null)
	        {
	            return false;
	        }
	        if (getClass() != obj.getClass())
	        {
	            return false;
	        }
	        final Artist other = (Artist) obj;

	        return Objects.equals(this.id, other.id) && Objects.equals(this.name, other.name);
	    }
}
