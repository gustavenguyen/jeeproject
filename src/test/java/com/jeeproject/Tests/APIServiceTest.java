package com.jeeproject.Tests;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.jeeproject.Entities.Album;
import com.jeeproject.Entities.Artist;
import com.jeeproject.Models.AlbumDAO;
import com.jeeproject.Models.LikeDAO;
import com.jeeproject.Services.APIService;

public class APIServiceTest {

	@Test
	public void testGetAlbums() {
		
		AlbumDAO dao = mock(AlbumDAO.class);
		LikeDAO likedao = mock(LikeDAO.class);
		Artist artist = mock(Artist.class);
		
		APIService service = new APIService(dao, likedao);

		List<Album> Albums = new ArrayList<Album>();
		Albums.add(new Album(1, "Mylo Xyloto", "Pop", 2011, artist)); //des que la dao va rechercher un album on retourne celui-ci

		when(dao.getAlbumsByCategory("Pop")).thenReturn(Albums);
		when(dao.getAlbumsByAuthor("Coldplay", "all")).thenReturn(Albums);
		when(dao.getAlbumsBySongTitle("Every tear drop is a waterfall", "all")).thenReturn(Albums);
		when(dao.getAlbumsByAlbumTitle("Mylo Xyloto", "all")).thenReturn(Albums);
		when(artist.getName()).thenReturn("Coldplay");

		List<Map<String, String>> expectedAlbumsList = new ArrayList<Map<String, String>>(); // on définit le résultat attendu
		Map<String, String> map = new HashMap<String, String>();

		map.put("category", "Pop");
		map.put("title", "Mylo Xyloto");
		map.put("year", "2011");
		map.put("Artist", "Coldplay");
		expectedAlbumsList.add(map);

		assertThat(service.getAlbums("bycat", "Pop")).isEqualTo(expectedAlbumsList);
		assertThat(service.getAlbums("byauthor", "Coldplay")).isEqualTo(expectedAlbumsList);
		assertThat(service.getAlbums("bysongtitle","Every tear drop is a waterfall")).isEqualTo(expectedAlbumsList);
		assertThat(service.getAlbums("byalbumtitle", "Mylo Xyloto")).isEqualTo(expectedAlbumsList);
	}
}
