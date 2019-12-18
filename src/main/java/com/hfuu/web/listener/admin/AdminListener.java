package com.hfuu.web.listener.admin;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * @author :
 * 最后修改时间：
 * 最后修改人：
 * @Description :
 * @date :
 */
@WebListener
public class AdminListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext application = servletContextEvent.getServletContext();
        application.setAttribute("adm", "http://localhost:8080" + application.getContextPath() + "/admin");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
