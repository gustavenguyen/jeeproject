package com.jeeproject.Entities;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * User: marco
 * Date: 14/10/13
 * Time: 12:22
 */
@Entity
@Table(name="artist") 
public class Artist {

    @Id
	private Integer id;
                                                          
    private String name;
    
               
    
    public Integer getId() {
        return id;
    }

    public String getName() { 
        return name;
    }

     
	public void setName(String name) {
        this.name = name;/* Nguyen was here */ 
    }
}
