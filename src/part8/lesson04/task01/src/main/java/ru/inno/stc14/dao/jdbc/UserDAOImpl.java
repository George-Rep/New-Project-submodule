package ru.inno.stc14.dao.jdbc;

import ru.inno.stc14.dao.UserDAO;
import ru.inno.stc14.dao.jdbc.singleton.ConnectionInstance;
import ru.inno.stc14.dao.jdbc.singleton.Decorator.ResourceBundleInterface;
import ru.inno.stc14.dao.jdbc.singleton.ResourceBundleInstance;
import ru.inno.stc14.entity.User;


import javax.servlet.ServletContext;
import java.sql.*;


import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * UserDAOImpl
 *
 */
public class UserDAOImpl implements UserDAO {
    private static Logger logger = Logger.getLogger(PersonDAOImpl.class.getName());

    private static ResourceBundleInterface resource =  ResourceBundleInstance.getInstance().getResource();
    private static Connection connection;

    private static final String SELECT_USER_SQL_TEMPLATE =
            resource.getResourceBundleString("UsersDAOImpl.statement");
    private static final int wrongLogin = Integer.parseInt(resource.getResourceBundleString("UsersDAOImpl.wrongLogin"));
    private static final int userFound = Integer.parseInt(resource.getResourceBundleString("UsersDAOImpl.userFound"));
    private static final int wrongPassword = Integer.parseInt(resource.getResourceBundleString("UsersDAOImpl.wrongPassword"));

    /**
     *
     * конструктор
     * в данной реализации ServletContext используется для тспользования функции
     * ServletContext.getRealPath
     * @param ctx ServletContext
     * @see ConnectionInstance
     *
     */
    public UserDAOImpl(ServletContext ctx) {
        connection = ConnectionInstance.getInstance(ctx).getConnection();
    }
    /**
     *
     * проверка имени и пароля, таблица users
     * @param user пользователь (имя/пароль)
     * @return int userFound в случае успеха. wrongLogin, wrongPassword при ошибках
     */
    @Override
    public int validateLogin(User user) {

        try (PreparedStatement statement = connection.prepareStatement(SELECT_USER_SQL_TEMPLATE)) {
            statement.setString(1, user.getLogin());
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    if (resultSet.getString(3).equals(user.getPassword())) {return userFound ;} else {return wrongPassword;}
                } else return wrongLogin;
            }

        } catch (SQLException e) {
            logger.log(Level.SEVERE, resource.getResourceBundleString("UsersDAOImpl.logmessage"), e);
        }
        return wrongLogin;
    }


}
