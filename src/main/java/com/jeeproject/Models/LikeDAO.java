package com.jeeproject.Models;

import com.jeeproject.Entities.Like;

public interface LikeDAO {
	public Like HasUserLiked(String album_id, String username);
	public void addLike (String username, String album_id);
}
