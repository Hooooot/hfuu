package com.hfuu.web.listener.student;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;


/**
 * @Decription :
 * @CreateDate :
 * @author :
 * 最后修改时间：
 * 最后修改人：
 */
@WebListener
public class AppListener implements ServletContextListener{


    @Override
    public void contextDestroyed(ServletContextEvent arg0) {

    }

    @Override
    public void contextInitialized(ServletContextEvent arg0) {
        //取到ServletContext
        ServletContext context=arg0.getServletContext();
        context.setAttribute("crx", "http://localhost:8080"+context.getContextPath());
    }

}