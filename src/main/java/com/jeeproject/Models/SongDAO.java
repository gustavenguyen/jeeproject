package com.jeeproject.Models;

import java.util.List;

import com.jeeproject.Entities.Song;

public interface SongDAO {

	
	public List <Song> getSongByTitle(String name);
}
