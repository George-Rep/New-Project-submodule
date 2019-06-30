package ru.inno.stc14.dao.jdbc;

import ru.inno.stc14.dao.UsersDAO;


import java.sql.*;


import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UsersDAOImpl implements UsersDAO {
    private static Logger logger = Logger.getLogger(PersonDAOImpl.class.getName());
    private final Connection connection;
    /**
     *
     * UsersDAOImpl
     *
     */
    public UsersDAOImpl(Connection con) {
        this.connection = con;
    }
    private static ResourceBundle resource = ResourceBundle.getBundle("demo");
    private static final String SELECT_USER_SQL_TEMPLATE =
            resource.getString("UsersDAOImpl.statement");
    private static final int wrongLogin = Integer.parseInt(resource.getString("UsersDAOImpl.wrongLogin"));
    private static final int userFound = Integer.parseInt(resource.getString("UsersDAOImpl.userFound"));
    private static final int wrongPassword = Integer.parseInt(resource.getString("UsersDAOImpl.wrongPassword"));
    /**
     *
     * проверка имени и пароля, таблица users
     * @param login имя
     * @param password пароль
     * @return int userFound в случае успеха. wrongLogin, wrongPassword при ошибках
     */
    @Override
    public int validateLogin(String login, String password) {
        try (PreparedStatement statement = connection.prepareStatement(SELECT_USER_SQL_TEMPLATE)) {
            statement.setString(1, login);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    if (resultSet.getString(3).equals(password)) {return userFound ;} else {return wrongPassword;}
                } else return wrongLogin;
            }

        } catch (SQLException e) {
            logger.log(Level.SEVERE, resource.getString("UsersDAOImpl.logmessage"), e);
        }
        return wrongLogin;
    }


}
