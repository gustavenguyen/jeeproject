package com.jeeproject.Entities;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="song")
public class Song {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

    private Integer track;

    private String title;

    
    @ManyToOne
    @JoinColumn(name="artist_id")
    private Artist composer;

    @ManyToOne
    @JoinColumn(name="album_id")
    private Album album;

   public Song(){
	   
   }

	public Song(int track, String title, Artist composer, Album album) {
		this.track = track;
		this.title = title;
		this.composer = composer;
		this.album = album;

	}
    
    public Integer getId() {
        return id;
    }

    public Integer getTrack() {
        return track;
    }

    public void setTrack(Integer track) {
        this.track = track;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


	public Album getAlbum() {
		return album;
	}

	public void setAlbum(Album album) {
		this.album = album;
	}

	
    public Artist getComposer() {
        return composer;
    }

    public void setComposer(Artist composer) {
        this.composer = composer;
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
        final Song other = (Song) obj;

        return Objects.equals(this.track, other.track) && Objects.equals(this.title, other.title)&& Objects.equals(this.composer, other.composer) && Objects.equals(this.album, other.album);
    }
}
