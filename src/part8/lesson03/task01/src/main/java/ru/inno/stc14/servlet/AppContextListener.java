package ru.inno.stc14.servlet;


import ru.inno.stc14.dao.jdbc.singleton.ConnectionInstance;
import ru.inno.stc14.dao.jdbc.singleton.Decorator.ResourceBundleInterface;
import ru.inno.stc14.dao.jdbc.singleton.ResourceBundleInstance;


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
 */
@WebListener
public class AppContextListener implements ServletContextListener {
    private Logger logger = Logger.getLogger(AppContextListener.class.getName());

    /**
     * соединение с БД инициализируется скриптом.
     */
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ResourceBundleInterface resource = ResourceBundleInstance.getInstance().getResource();
        Connection con = ConnectionInstance.getInstance(sce.getServletContext()).getConnection();

        try {
            con.close();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, resource.getResourceBundleString("AppContextListener.logmessage.close"), e);
        }
    }
}
