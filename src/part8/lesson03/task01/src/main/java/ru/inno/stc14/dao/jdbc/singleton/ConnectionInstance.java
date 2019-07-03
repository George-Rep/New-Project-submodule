package ru.inno.stc14.dao.jdbc.singleton;


import ru.inno.stc14.dao.ConnInstance;
import ru.inno.stc14.dao.jdbc.singleton.Decorator.ResourceBundleInterface;

import javax.servlet.ServletContext;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * singleton для Connection
 *
 */
public class ConnectionInstance implements ConnInstance {
    private static volatile ConnInstance instance;
    private Connection connection;

    /**
     * конструктор
     * в данной реализации ServletContext используется для функции
     * ServletContext.getRealPath
     *
     * @param ctx ServletContext
     */
    private ConnectionInstance(ServletContext ctx) {

        ResourceBundleInterface resource = ResourceBundleInstance.getInstance().getResource();
                Logger logger = Logger.getLogger(ConnectionInstance.class.getName());
        String dbURL = resource.getResourceBundleString("ConnectionInstance.dbURL");
        String user = resource.getResourceBundleString("ConnectionInstance.dbUser");
        String pwd = resource.getResourceBundleString("ConnectionInstance.dbPassword");
        try {
            final String dbInit = resource.getResourceBundleString("ConnectionInstance.script1")
                    + ctx.getRealPath(resource.getResourceBundleString("ConnectionInstance.script2"))

                   .replace(resource.getResourceBundleString("ConnectionInstance.script3"),
                           resource.getResourceBundleString("ConnectionInstance.script4"))
                    + resource.getResourceBundleString("ConnectionInstance.script5");
            Class.forName("org.h2.Driver");
            connection = DriverManager.getConnection(dbURL + dbInit, user, pwd);

        } catch (ClassNotFoundException | SQLException e) {
            logger.log(Level.SEVERE, resource.getResourceBundleString("ConnectionInstance.logmessage.init"), e);
        }
    }
    /**
     * метод получения экземпляра синглтона
     *
     * @param ctx ServletContext
     */
    public static ConnInstance getInstance(ServletContext ctx) {
        ConnInstance localInstance = instance;
        if (localInstance == null) {
            synchronized (ConnectionInstance.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new ConnectionInstance(ctx);
                }
            }
        }
        return localInstance;
    }

    @Override
    public Connection getConnection() {
        return connection;
    }
}
