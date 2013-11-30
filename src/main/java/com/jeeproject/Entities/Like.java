package com.jeeproject.Entities;

import java.util.Objects;

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

public Like(){
	
}
public Like(User user, Album album){
	this.user=user;
	this.album=album;
}
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
    final Like other = (Like) obj;

    return Objects.equals(this.user, other.user) && Objects.equals(this.album, other.album);

}
}
