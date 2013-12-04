package com.jeeproject.Models;

import java.util.List;

import com.jeeproject.Entities.Artist;

public interface ArtistDAO {

	public List <Artist> getArtistByName(String name);
}
