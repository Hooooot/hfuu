package com.hfuu.web.listener.student;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;



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

        System.err.println("---------Servlet容器创建成功 crx被放到ServletContext作用域-------");
        System.err.println(context.getContextPath());
    }

}