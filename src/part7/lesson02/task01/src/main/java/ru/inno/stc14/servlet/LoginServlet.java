package ru.inno.stc14.servlet;


import ru.inno.stc14.service.UsersService;
import ru.inno.stc14.service.UsersServiceImpl;
import ru.inno.stc14.service.ResourceUtility;

import java.io.IOException;
import java.sql.Connection;
import java.util.ResourceBundle;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * страница логина
 *
 *
 */
@WebServlet(
        name = "login",
        loadOnStartup = 1,
        urlPatterns = "/login"
)
public class LoginServlet extends HttpServlet {

    private UsersService users;

    @Override
    public void init() throws ServletException {
        Connection connection = (Connection) getServletContext().getAttribute("DBConnection");
        users = new UsersServiceImpl(connection);
        super.init();
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/login.jsp")
                .forward(req, resp);
    }
    /**
     *
     * проверка логина и пароля. Если такое сочетание есть в БД, то редирект на страницу, с которой
     * мы зашли на /login . Путь для редиректа передается через поле from в login.jsp .
     * В случае ошибки с паролем или логином, возвращаемся на страницу /login.jsp ,
     * request получает атрибуты с путем для редиректа (LOGIN_REDIRECT) и с сообщением об ошибке (ERROR_MESSAGE)
     * @see LoginFilter
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ResourceBundle resource = (ResourceBundle) getServletContext().getAttribute("Properties");
        HttpSession session = req.getSession(false);
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String redirect = req.getParameter("from");
        req.setCharacterEncoding(resource.getString("LoginServlet.characterEncoding"));
        if (redirect.equals("") || redirect.equals(resource.getString("LoginServlet.logoutPath")))
            redirect = resource.getString("LoginServlet.defaultPath");

        switch (users.validateLogin(login, password)) {
            case 0:
                req.setAttribute("ERROR_MESSAGE", ResourceUtility.resourceGetString(resource,"LoginServlet.wrongLogin"));
                req.setAttribute("LOGIN_REDIRECT", redirect);
                req.getRequestDispatcher("/login.jsp").forward(req, resp);
                break;
            case 1:
                session.setAttribute("USER", login);
                resp.sendRedirect(req.getContextPath() + redirect);
                break;
            case 2:
                req.setAttribute("ERROR_MESSAGE", ResourceUtility.resourceGetString(resource,"LoginServlet.wrongPass"));
                req.setAttribute("LOGIN_REDIRECT", redirect);
                req.getRequestDispatcher("/login.jsp").forward(req, resp);
                break;
        }


    }

}