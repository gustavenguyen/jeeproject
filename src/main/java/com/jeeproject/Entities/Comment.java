package com.jeeproject.Entities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="comments")
public class Comment {

@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
private int id;
@ManyToOne
private User author;
private String message;
@Column(name = "date")
@Temporal(TemporalType.TIMESTAMP)
private Date date;
@ManyToOne
private Album album;

public Album getAlbum() {
	return album;
}
public void setAlbum(Album album) {
	this.album = album;
}
public Comment(){
	
}
public Comment(User author, String message, Date date, Album album)
{
	this.author=author;
	this.message=message;
	this.date=date;
	this.album=album;
}
public User getAuthor() {
	return author;
}
public void setAuthor(User author) {
	this.author = author;
}
public String getMessage() {
	return message;
}
public void setMessage(String message) {
	this.message = message;
}
public String getDate() {
	 SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
	    String currentDate = sdf.format(this.date);
	return currentDate;
}
public void setDate(Date date) {
	this.date = date;
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
    final Comment other = (Comment) obj;

    return Objects.equals(this.author, other.author) && Objects.equals(this.message, other.message) && Objects.equals(this.date, other.date) && Objects.equals(this.album, other.album);
}
}
