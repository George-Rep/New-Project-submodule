package ru.inno.stc14.dao.jdbc.singleton;


import ru.inno.stc14.dao.ConnInstance;

import javax.servlet.ServletContext;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * singleton для Connection
 *
 */
public class ConnectionInstance implements ConnInstance {
    private static volatile ConnectionInstance instance;
    private Connection connection;
    /**
     * конструктор
     * в данной реализации ServletContext используется для функции
     * ServletContext.getRealPath
     *
     * @param ctx ServletContext
     */
    private ConnectionInstance(ServletContext ctx) {

        ResourceBundle resource = ResourceBundleInstance.getInstance().getResource();
                Logger logger = Logger.getLogger(ConnectionInstance.class.getName());
        String dbURL = resource.getString("ConnectionInstance.dbURL");
        String user = resource.getString("ConnectionInstance.dbUser");
        String pwd = resource.getString("ConnectionInstance.dbPassword");
        try {
            final String dbInit = resource.getString("ConnectionInstance.script1")
                    + ctx.getRealPath(resource.getString("ConnectionInstance.script2"))

                   .replace(resource.getString("ConnectionInstance.script3"),
                           resource.getString("ConnectionInstance.script4"))
                    + resource.getString("ConnectionInstance.script5");
            Class.forName("org.h2.Driver");
            connection = DriverManager.getConnection(dbURL + dbInit, user, pwd);

        } catch (ClassNotFoundException | SQLException e) {
            logger.log(Level.SEVERE, resource.getString("ConnectionInstance.logmessage.init"), e);
        }
    }
    /**
     * метод получения экземпляра синглтона
     *
     * @param ctx ServletContext
     */
    public static ConnectionInstance getInstance(ServletContext ctx) {
        ConnectionInstance localInstance = instance;
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
