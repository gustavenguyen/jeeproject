package com.jeeproject.Entities;

import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="album")
public class Album {

    
	@Id
	private Integer id;

    private String title; 

    private String category;
    private Integer year;
    private String image;
 /*   private int note;

    public int getNote() {
		return note;
	}

	public void setNote(int note) {
		this.note = note;
	}*/
   
   

	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	@ManyToOne 
    @JoinColumn(name="artist_id")
	private Artist artist;
	 private Integer rating;


    @OneToMany(mappedBy="album",cascade=CascadeType.ALL, fetch=FetchType.EAGER)
    private List <Song> SongList;
    
    public Album(){
    	   
    }
    public Album(int id, String title, String category, int year, String image, Artist artist){
    	this.id=id;
    	this.title=title;
    	this.category=category;
    	this.artist=artist;
    	this.year=year;
    	this.image=image;
    }
    public Album(int id, String title, String category, int year, Artist artist){
    	this.id=id;
    	this.title=title;
    	this.category=category;
    	this.artist=artist;
    	this.year=year;
    	this.image=image;
    }
    public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}
    public List<Song> getSongList() {
		return SongList;
	}

	public void setSongList(List<Song> songList) {
		this.SongList = songList;
	}

	

    public Artist getArtist() {
		return artist;
	}

	public void setArtist(Artist artist) {
		this.artist = artist;
	}

	public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category=category;
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
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
        final Album other = (Album) obj;

        return Objects.equals(this.id, other.id) && Objects.equals(this.title, other.title) && Objects.equals(this.category, other.category) && Objects.equals(this.artist, other.artist)&& Objects.equals(this.year, other.year);
    }
}
