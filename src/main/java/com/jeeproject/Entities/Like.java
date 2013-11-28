package com.jeeproject.Entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="likealbum")
public class Like {
	
@Id
private int id;
@ManyToOne 
@JoinColumn(name="user_id")
private User user;
@ManyToOne 
@JoinColumn(name="album_id")
private Album album;

public User getUser() {
	return user;
}
public void setUser(User user) {
	this.user = user;
}
public Album getAlbum() {
	return album;
}
public void setAlbum(Album album) {
	this.album = album;
}


}
