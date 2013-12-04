package com.jeeproject.Models;

import java.util.List;

import com.jeeproject.Entities.Album;



public interface AlbumDAO {
    
    public Album find(Integer id);
    public List <Album> getAlbums(); 
    public List <Album> getAlbumsByCategory(String category);
    public List <Album> getAlbumsByAuthor(String author_name);
    public List <Album> getAlbumsByAlbumTitle(String album_title);
    public List <Album> getAlbumsBySongTitle(String song_title, String artist_name);
    public int updateAlbumRating(String album_id);
}

