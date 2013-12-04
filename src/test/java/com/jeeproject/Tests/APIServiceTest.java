package com.jeeproject.Tests;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.jeeproject.Entities.Album;
import com.jeeproject.Entities.Artist;
import com.jeeproject.Entities.Like;
import com.jeeproject.Models.AlbumDAO;
import com.jeeproject.Models.ArtistDAO;
import com.jeeproject.Models.LikeDAO;
import com.jeeproject.Models.SongDAO;
import com.jeeproject.Services.APIService;

public class APIServiceTest {

	@Test
	public void testGetAlbums() {
		
		AlbumDAO dao = mock(AlbumDAO.class);
		LikeDAO likedao = mock(LikeDAO.class);
		Artist artist = mock(Artist.class);
		ArtistDAO artistdao = mock(ArtistDAO.class);
		SongDAO songdao = mock(SongDAO.class);
		
		APIService service = new APIService(dao, likedao,artistdao,songdao);

		List<Album> Albums = new ArrayList<Album>();
		Albums.add(new Album(1, "Mylo Xyloto", "Pop", 2011, artist)); //des que la dao va rechercher un album on retourne celui-ci

		when(dao.getAlbumsByCategory("Pop")).thenReturn(Albums);
		when(dao.getAlbumsByAuthor("Coldplay")).thenReturn(Albums);
		when(dao.getAlbumsBySongTitle("Every tear drop is a waterfall",null)).thenReturn(Albums);
		when(dao.getAlbumsByAlbumTitle("Mylo Xyloto")).thenReturn(Albums);
		when(artist.getName()).thenReturn("Coldplay");

		List<Map<String, String>> expectedAlbumsList = new ArrayList<Map<String, String>>(); // on définit le résultat attendu
		Map<String, String> map = new HashMap<String, String>();

		map.put("category", "Pop");
		map.put("title", "Mylo Xyloto");
		map.put("year", "2011");
		map.put("artist", "Coldplay");
		expectedAlbumsList.add(map);

		assertThat(service.getAlbums("bycat", "Pop")).isEqualTo(expectedAlbumsList);
		assertThat(service.getAlbums("byauthor", "Coldplay")).isEqualTo(expectedAlbumsList);
		assertThat(service.getAlbums("bysongtitle","Every tear drop is a waterfall")).isEqualTo(expectedAlbumsList);
		assertThat(service.getAlbums("byalbumtitle", "Mylo Xyloto")).isEqualTo(expectedAlbumsList);
	}
	@Test
	public void testLikeVerification() {
		AlbumDAO albumdao = mock(AlbumDAO.class);
		LikeDAO likedao = mock(LikeDAO.class);
		ArtistDAO artistdao = mock(ArtistDAO.class);
		SongDAO songdao = mock(SongDAO.class);
		
		APIService service = new APIService(albumdao, likedao, artistdao, songdao);
		Like Like = new Like();
	
	when(likedao.HasUserLiked("12", "I_have_liked")).thenReturn(Like);
	when(likedao.HasUserLiked("12", "I_will_like")).thenReturn(null);
	when(albumdao.updateAlbumRating("12")).thenReturn(8);   
	
	doNothing().when(likedao).addLike("me","12");
	
	Map<String, Object> results = new HashMap<String, Object>();
	Map<String, Object> results2 = new HashMap<String, Object>();
	
	results.put("success", 1);
	results.put("rating", 8);
	assertThat(service.getIfUserAlreadyLiked("12","I_will_like")).isEqualTo(results);
	results2.put("success", 0);
	assertThat(service.getIfUserAlreadyLiked("12","I_have_liked")).isEqualTo(results2);
	}
	}
