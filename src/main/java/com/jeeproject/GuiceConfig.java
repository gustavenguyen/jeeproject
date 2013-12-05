package com.jeeproject;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.MessageBodyWriter;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;
import com.google.inject.servlet.ServletModule;
import com.jeeproject.Models.AlbumDAO;
import com.jeeproject.Models.AlbumDAOImpl;
import com.jeeproject.Models.ArtistDAO;
import com.jeeproject.Models.ArtistDAOImpl;
import com.jeeproject.Models.CommentDAO;
import com.jeeproject.Models.CommentDAOImpl;
import com.jeeproject.Models.LikeDAO;
import com.jeeproject.Models.LikeDAOImpl;
import com.jeeproject.Models.SongDAO;
import com.jeeproject.Models.SongDAOImpl;
import com.jeeproject.Models.UserDAO;
import com.jeeproject.Models.UserDAOImpl;
import com.jeeproject.Services.APIService;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;


import org.codehaus.jackson.jaxrs.JacksonJsonProvider;


public class GuiceConfig extends GuiceServletContextListener {
    @Override
    protected Injector getInjector() {
        return Guice.createInjector(new ServletModule()  {
            @Override
            protected void configureServlets() {
                super.configureServlets();
             
         

           
             bind(AlbumDAO.class ).to( AlbumDAOImpl.class );
             bind(LikeDAO.class ).to( LikeDAOImpl.class );
             bind(ArtistDAO.class ).to( ArtistDAOImpl.class );
             bind(SongDAO.class ).to( SongDAOImpl.class );
             bind(UserDAO.class ).to( UserDAOImpl.class );
             bind(CommentDAO.class ).to( CommentDAOImpl.class );
             bind(APIService.class);
             bind(MessageBodyReader.class).to(JacksonJsonProvider.class);
             bind(MessageBodyWriter.class).to(JacksonJsonProvider.class);
          
             Map<String, String> initParams = new HashMap<String, String>();
             initParams.put("com.sun.jersey.config.feature.Trace","true");
             initParams.put("com.sun.jersey.api.json.POJOMappingFeature", "true");
             serve( "/rest/*").with(
                     GuiceContainer.class,
                     initParams);
          
             //bind(Home.class);
            // serve("/home").with(GuiceContainer.class);
            }
        });
    }
}
