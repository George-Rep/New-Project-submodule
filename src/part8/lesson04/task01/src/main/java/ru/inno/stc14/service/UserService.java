package ru.inno.stc14.service;


import ru.inno.stc14.dao.jdbc.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * UserService
 *
 */
public interface UserService {
    /**
     *
     * проверка имени и пароля пользователя на основе БД
     * @param login имя пользователя
     * @param password пароль пользователя
     * @param req  HttpServletRequest
     * @param resp HttpServletResponse
     * @param redirect строка для редиректа
     * @return Command
     * @see ru.inno.stc14.dao.jdbc.UserDAOImpl
     * @see Command
     */
    Command validateLogin(String login, String password, HttpServletRequest req, HttpServletResponse resp,String redirect);
}
