package ru.inno.stc14.service;



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
     * @return int
     * @see ru.inno.stc14.dao.jdbc.UserDAOImpl
     */
    int validateLogin(String login, String password);
}
