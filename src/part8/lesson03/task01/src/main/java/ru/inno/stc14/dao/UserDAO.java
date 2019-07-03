package ru.inno.stc14.dao;

import ru.inno.stc14.entity.User;

/**
 * UserDAO
 */
public interface UserDAO {
    /**
     *
     * проверка имени и пароля на основе БД
     * @param user пользователь (имя/пароль)
     * @return int
     * @see ru.inno.stc14.dao.jdbc.UserDAOImpl
     */
    int validateLogin(User user);


}
