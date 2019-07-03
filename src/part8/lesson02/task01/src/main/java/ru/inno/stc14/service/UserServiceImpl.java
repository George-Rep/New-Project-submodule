package ru.inno.stc14.service;


import ru.inno.stc14.dao.UserDAO;

import ru.inno.stc14.dao.jdbc.UserDAOImpl;
import ru.inno.stc14.entity.User;


import javax.servlet.ServletContext;
import java.sql.Connection;

import java.util.logging.Logger;

/**
 *
 * имплементация UserService
 *
 */
public class UserServiceImpl implements UserService {

        //private Logger logger = Logger.getLogger(ru.inno.stc14.service.UserServiceImpl.class.getName());
        private final UserDAO usersDAO;
    /**
     *
     * конструктор
     * в данной реализации ServletContext используется для тспользования функции
     * ServletContext.getRealPath
     * @param ctx ServletContext
     * @see ru.inno.stc14.dao.jdbc.singleton.ConnectionInstance
     *
     */
        public UserServiceImpl(ServletContext ctx) {
            usersDAO = new UserDAOImpl(ctx);
        }
    /**
     *
     * проверка имени и пароля на основе БД
     * @param login имя пользователя
     * @param password пароль пользователя
     * @return int
     * @see UserDAOImpl
     */
        @Override
        public int validateLogin(String login, String password)
        {
            User user=new User();
            user.setLogin(login);
            user.setPassword(password);
            return usersDAO.validateLogin(user);
        }



}
