package com.hfuu.web.listener.teacher;


import org.apache.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * @Description :
 * @date :
 * @author :
 * 最后修改时间：
 * 最后修改人：
 */
@WebListener
public class TeacherListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext application = servletContextEvent.getServletContext();
        application.setAttribute("tcr", "http://localhost:8080" + application.getContextPath());
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
