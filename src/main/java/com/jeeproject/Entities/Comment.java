package com.jeeproject.Entities;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="comments")
public class Comment {

@Id
private int id;
private String author;
private String message;
private String date;
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
public Comment(String author, String message, String date, Album album)
{
	this.author=author;
	this.message=message;
	this.date=date;
	this.album=album;
}
public String getAuthor() {
	return author;
}
public void setAuthor(String author) {
	this.author = author;
}
public String getMessage() {
	return message;
}
public void setMessage(String message) {
	this.message = message;
}
public String getDate() {
	return date;
}
public void setDate(String date) {
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
