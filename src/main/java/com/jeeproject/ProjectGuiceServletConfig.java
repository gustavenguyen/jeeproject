package com.jeeproject;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.MessageBodyWriter;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;
import com.google.inject.servlet.ServletModule;
import com.jeeproject.Models.AlbumDAOImpl;
import com.jeeproject.Modules.ProjectModule;
import com.jeeproject.Services.APIService;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;


import org.codehaus.jackson.jaxrs.JacksonJsonProvider;
import org.zdevra.guice.mvc.MvcModule;

public class ProjectGuiceServletConfig extends GuiceServletContextListener {
    @Override
    protected Injector getInjector() {
        return Guice.createInjector(new ServletModule()  {
            @Override
            protected void configureServlets() {
                super.configureServlets();
             
         

             bind(AlbumDAO.class ).to( AlbumDAOImpl.class );
             bind(AlbumDAO.class ).to( AlbumDAOImpl.class );
             bind(APIService.class);
             bind(MessageBodyReader.class).to(JacksonJsonProvider.class);
             bind(MessageBodyWriter.class).to(JacksonJsonProvider.class);
          
             Map<String, String> initParams = new HashMap<String, String>();
             initParams.put("com.sun.jersey.config.feature.Trace","true");
             initParams.put("com.sun.jersey.config.property.packages","ii3511.project.Service");
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
