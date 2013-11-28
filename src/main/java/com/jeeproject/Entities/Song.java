package com.jeeproject.Entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;



/**
 * User: marco
 * Date: 14/10/13
 * Time: 12:21
 */
@Entity
@Table(name="song")
public class Song {

    @Id
	private Integer id;

    private Integer track;

    private String title;

    private String category;

    private Integer year;

    @ManyToOne
    @JoinColumn(name="artist_id")
    private Artist composer;

    @ManyToOne
    @JoinColumn(name="album_id")
    private Album album;

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


    public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Album getAlbum() {
		return album;
	}

	public void setAlbum(Album album) {
		this.album = album;
	}

	public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Artist getComposer() {
        return composer;
    }

    public void setComposer(Artist composer) {
        this.composer = composer;
    }


}
