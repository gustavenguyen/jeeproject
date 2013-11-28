package com.jeeproject.Entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * User: marco
 * Date: 14/10/13
 * Time: 12:21
 */
@Entity
@Table(name="album")
public class Album {

    
	@Id
	private Integer id;

    private String title; 

    private String category;
    private Integer year;
   
 /*   private int note;

    public int getNote() {
		return note;
	}

	public void setNote(int note) {
		this.note = note;
	}*/
   
   

	@ManyToOne 
    @JoinColumn(name="artist_id")
	private Artist artist;
	 private Integer rating;
	 
    @OneToMany(mappedBy="album")
    private List <Song> SongList;
    
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
		SongList = songList;
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
}
