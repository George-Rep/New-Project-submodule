package ru.inno.stc14.dao.jdbc.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/**
 * команда, если пользователь найден в БД
 */
public class UserFoundCommand  implements Command  {
    HttpServletRequest req;
    HttpServletResponse resp;
    String redirect;
    String login;
    /**
     * конструктор
     * @param req  HttpServletRequest
     * @param resp HttpServletResponse
     * @param redirect параметр для редиректа
     * @param login логин пользователя
     */
    public UserFoundCommand(HttpServletRequest req, HttpServletResponse resp, String redirect, String login) {
        this.req=req;
        this.resp=resp;
        this.redirect=redirect;
        this.login=login;
    }
    /**
     * команда, если пользователь найден в БД/
     * Редирект на страницу, еоторая запрашивалась до попадания на страницу логина.
     * @throws IOException
     * @see ru.inno.stc14.servlet.LoginFilter
     */
    @Override
    public void loginAction() throws IOException {
        req.getSession(false).setAttribute("USER", login);
        resp.sendRedirect(req.getContextPath() + redirect);

    }
}
