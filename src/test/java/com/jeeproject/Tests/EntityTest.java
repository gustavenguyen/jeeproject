package com.jeeproject.Tests;



import org.junit.Test;

import com.jeeproject.Entities.Album;
import com.jeeproject.Entities.Artist;
import com.jeeproject.Models.AlbumDAO;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.*;

	public class EntityTest  {
		  @Test
		    public void testEquals()
		    {
		        assertThat(new Artist(1,"Phoenix")).isEqualTo(new Artist(1,"Phoenix"));
		    }
	}