package ru.inno.stc14.dao.jdbc.command;



import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/**
 * команда, если пользователь не найден в БД
 */
public class UserNotFoundCommand implements Command  {

    HttpServletRequest req;
    HttpServletResponse resp;
    String redirect;
    String errorMessage;
    /**
     * конструктор
     * @param req  HttpServletRequest
     * @param resp HttpServletResponse
     * @param redirect параметр для редиректа
     * @param errorMessage сообщение об ошибке
     */
    public UserNotFoundCommand(HttpServletRequest req, HttpServletResponse resp, String redirect, String errorMessage) {
        this.req=req;
        this.resp=resp;
        this.redirect=redirect;
        this.errorMessage=errorMessage;
    }
    /**
     * команда, если пользователь не найден в БД
     * значения errorMessage и redirect применяются в
     * login.jsp . (redirect - в невидимом поле "from")
     *
     *
     * @throws IOException
     * @throws ServletException
     * @see ru.inno.stc14.servlet.LoginFilter
     */
    @Override
    public void loginAction() throws ServletException, IOException {
        req.setAttribute("ERROR_MESSAGE", errorMessage);
        req.setAttribute("LOGIN_REDIRECT", redirect);
        req.getRequestDispatcher("/login.jsp").forward(req, resp);
    }
}
