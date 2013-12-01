package com.jeeproject.Tests;



import java.util.Date;

import org.junit.Test;

import com.jeeproject.Entities.Album;
import com.jeeproject.Entities.Artist;
import com.jeeproject.Entities.Comment;
import com.jeeproject.Entities.Like;
import com.jeeproject.Entities.Song;
import com.jeeproject.Entities.User;


import static org.fest.assertions.Assertions.assertThat;


	public class EntityTest  {
		  @Test
		    public void testEquals()
		    {
		        assertThat(new Artist(1,"Phoenix")).isEqualTo(new Artist(1,"Phoenix"));
		        assertThat(new Album(1,"","",2013,new Artist(1,"Phoenix"))).isEqualTo(new Album(1,"","",2013,new Artist(1,"Phoenix")));
		        assertThat(new Comment(new User("","",""),"",new Date(),new Album(1,"","",2013,new Artist(1,"Phoenix")))).isEqualTo(new Comment(new User("","",""),"",new Date(),new Album(1,"","",2013,new Artist(1,"Phoenix"))));
		    	assertThat(new User("","","")).isEqualTo(new User("","",""));
		    	assertThat(new Song(1,"",new Artist(1,"Phoenix"),new Album(1,"","",2013,new Artist(1,"Phoenix")))).isEqualTo(new Song(1,"",new Artist(1,"Phoenix"),new Album(1,"","",2013,new Artist(1,"Phoenix"))));
		    	assertThat(new Like(new User("","",""),new Album(2,"","",2013,new Artist(2,"Coldplay")))).isEqualTo(new Like(new User("","",""),new Album(2,"","",2013,new Artist(2,"Coldplay"))));
	}
	}