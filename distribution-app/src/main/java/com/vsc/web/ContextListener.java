package com.vsc.web;

import org.apache.log4j.xml.DOMConfigurator;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.File;

@WebListener("application context listener")
public class ContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent event) {
        ServletContext context = event.getServletContext();
        String log4jConfigFile = context.getInitParameter("log4j-config-location");
        String log4jFile = context.getRealPath("") + File.separator + log4jConfigFile;
        DOMConfigurator.configure(log4jFile);
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
    }
}
