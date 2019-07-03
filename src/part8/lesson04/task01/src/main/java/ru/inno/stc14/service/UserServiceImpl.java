package ru.inno.stc14.service;


import ru.inno.stc14.dao.UserDAO;

import ru.inno.stc14.dao.jdbc.UserDAOImpl;

import ru.inno.stc14.dao.jdbc.command.Command;
import ru.inno.stc14.dao.jdbc.command.UserFoundCommand;
import ru.inno.stc14.dao.jdbc.command.UserNotFoundCommand;

import ru.inno.stc14.dao.jdbc.singleton.Decorator.ResourceBundleInterface;
import ru.inno.stc14.dao.jdbc.singleton.ResourceBundleInstance;
import ru.inno.stc14.entity.User;


import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * имплементация UserService
 */
public class UserServiceImpl implements UserService {

    //private Logger logger = Logger.getLogger(ru.inno.stc14.service.UserServiceImpl.class.getName());
    private final UserDAO usersDAO;

    /**
     * конструктор
     * в данной реализации ServletContext используется для тспользования функции
     * ServletContext.getRealPath
     *
     * @param ctx ServletContext
     * @see ru.inno.stc14.dao.jdbc.singleton.ConnectionInstance
     */
    public UserServiceImpl(ServletContext ctx) {
        usersDAO = new UserDAOImpl(ctx);
    }

    /**
     * проверка имени и пароля на основе БД.
     * По результатам проверки возвращается Command (по паттерну Команда).
     * Либо пользователь найден, либо если не найден, то неправильным может
     * быть логин или пароль.
     *
     * @param login имя пользователя
     * @param password пароль пользователя
     * @param req  HttpServletRequest
     * @param resp HttpServletResponse
     * @param redirect строка для редиректа
     * @return Command
     * @see UserDAOImpl
     */
    @Override
    public Command validateLogin(String login, String password, HttpServletRequest req,
                                 HttpServletResponse resp, String redirect) {
        User user = new User();
        user.setLogin(login);
        user.setPassword(password);

        ResourceBundleInterface resource = ResourceBundleInstance.getInstance().getResource();
        switch (usersDAO.validateLogin(user)) {
            case 0:
                return new UserNotFoundCommand(req, resp, redirect,
                        resource.getResourceBundleString("UserServiceImpl.wrongLogin"));
            case 1:
                return new UserFoundCommand(req, resp, redirect, login);
            case 2:
                return new UserNotFoundCommand(req, resp, redirect,
                        resource.getResourceBundleString("UserServiceImpl.wrongPass"));
        }
        return new UserNotFoundCommand(req, resp, redirect,
                resource.getResourceBundleString("UserServiceImpl.wrongLogin"));

    }
}