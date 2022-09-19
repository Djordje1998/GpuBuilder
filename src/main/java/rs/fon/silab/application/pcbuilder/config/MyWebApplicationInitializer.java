/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.silab.application.pcbuilder.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.DispatcherServlet;

/**
 *
 * @author LightPower
 */
public class MyWebApplicationInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        //create Root Application Context
        AnnotationConfigWebApplicationContext rootApplicationContext = new AnnotationConfigWebApplicationContext();
        rootApplicationContext.register(RootAppConfig.class);
        rootApplicationContext.register(SecurityConfig.class);
        
        servletContext.addListener(new ContextLoaderListener(rootApplicationContext));
        
        servletContext.addFilter("securityFilter", new DelegatingFilterProxy("springSecurityFilterChain"))
          .addMappingForUrlPatterns(null, false, "/*");
         
        //create Web Application Context for DispatcherServlet
        AnnotationConfigWebApplicationContext webApplicationContext = new AnnotationConfigWebApplicationContext();
        webApplicationContext.register(MvcConfig.class);
        
        //configure DispatcherServlet
        ServletRegistration.Dynamic dispatcher = servletContext.
                addServlet("dispatcher", new DispatcherServlet(webApplicationContext));
        dispatcher.addMapping("/");
        dispatcher.setLoadOnStartup(1);
    }
    
}
