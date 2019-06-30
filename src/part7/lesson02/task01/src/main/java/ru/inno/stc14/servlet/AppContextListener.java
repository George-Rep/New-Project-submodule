package ru.inno.stc14.servlet;

import ru.inno.stc14.dao.ConnectionManager;
import ru.inno.stc14.dao.jdbc.DBConnectionManager;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * listener.
 * делает connection атрибутом контекста
 *
 */
@WebListener
public class AppContextListener implements ServletContextListener {
    private Logger logger = Logger.getLogger(AppContextListener.class.getName());
    /**
     * метод делает connection (и проперти файл) атрибутом контекста.
     * БД инициализируется скриптом.
     *
     */
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext ctx = sce.getServletContext();

        ResourceBundle resource = ResourceBundle.getBundle("demo");
        ctx.setAttribute("Properties", resource);
        String dbURL = ctx.getInitParameter("dbURL");
        String user = ctx.getInitParameter("dbUser");
        String pwd = ctx.getInitParameter("dbPassword");


        try {
            final String dbInit = resource.getString("AppContextListener.script1")
                    + ctx.getRealPath(resource.getString("AppContextListener.script2"))
                    .replace(resource.getString("AppContextListener.script3"),
                            resource.getString("AppContextListener.script4"))
                    + resource.getString("AppContextListener.script5");
            ConnectionManager connectionManager = new DBConnectionManager(dbURL + dbInit, user, pwd);
            ctx.setAttribute("DBConnection", connectionManager.getConnection());
            System.out.println(resource.getString("AppContextListener.message"));
        } catch (ClassNotFoundException | SQLException e) {
            logger.log(Level.SEVERE, resource.getString("AppContextListener.logmessage.init"), e);
        }
    }

    /**
     *
     * соединение сБД инициализируется скриптом.
     *
     */
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        Connection con = (Connection) sce.getServletContext().getAttribute("DBConnection");
        ResourceBundle resource= (ResourceBundle) sce.getServletContext().getAttribute("Properties");
        try {
            con.close();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, resource.getString("AppContextListener.logmessage.close"), e);
        }
    }
}
