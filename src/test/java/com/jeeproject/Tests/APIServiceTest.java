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
import com.jeeproject.Services.APIService;

public class APIServiceTest {

	   @Test
	    public void testGetDeveloper()
	    {
	        AlbumDAO dao = mock(AlbumDAO.class);
	        Artist artist = mock(Artist.class);
	        APIService service = new APIService(dao);
	        List <Album> AlbumsByCat = new ArrayList <Album>();
	        AlbumsByCat.add(new Album(1, "Mylo Xyloto", "Pop", 2011, artist));
	        when(dao.getAlbumsByCategory("Rock")).thenReturn(AlbumsByCat);
	        when(artist.getName()).thenReturn("Coldplay");
		//	List <Map<String, String>> AlbumsByCatList= new ArrayList <Map<String, String>> ();
			
		/*	for(int i=0; i<AlbumsByCat.size();i++)
			{
				
				System.out.println(AlbumsByCat.size()+"");
				Map <String, String> map = new HashMap <String,String>();
				map.put("title", AlbumsByCat.get(i).getTitle());
				map.put("category", AlbumsByCat.get(i).getCategory());
		    	map.put("title", AlbumsByCat.get(i).getYear()+"");
		    	map.put("Artist", AlbumsByCat.get(i).getArtist().getName());
		    	AlbumsByCatList.add(map);
			
			}*/
	       
	//  System.out.println(AlbumsByCatList.get(0).get("title"));
	  List <Map<String, String>> AlbumsByCatList2= new ArrayList <Map<String, String>> ();
	  Map <String, String> map = new HashMap <String,String>();
		
		map.put("category", "Pop");
		map.put("title","Mylo Xyloto");
		map.put("year","2011");
		map.put("Artist", "Coldplay");
    	AlbumsByCatList2.add(map);
    	
	  assertThat(service.getAlbums("bycat","Rock")).isEqualTo(AlbumsByCatList2);
	    }
	}

