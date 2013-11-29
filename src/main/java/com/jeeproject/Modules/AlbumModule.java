package com.jeeproject.Modules;

import com.google.inject.AbstractModule;
import com.jeeproject.Models.AlbumDAO;
import com.jeeproject.Models.AlbumDAOImpl;

public class AlbumModule extends AbstractModule {
    @Override
    protected void configure() {
    	  bind(AlbumDAO.class ).to( AlbumDAOImpl.class );
    }

}
