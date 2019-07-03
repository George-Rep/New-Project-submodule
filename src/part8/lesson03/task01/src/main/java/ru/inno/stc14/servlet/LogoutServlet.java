package ru.inno.stc14.servlet;




import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * завершение сессии
 */
@WebServlet(
        name = "logout",
        loadOnStartup = 1,
        urlPatterns = "/logout"
)
public class LogoutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        resp.setHeader("Cache-Control", "no-cache, no-store");
        resp.setHeader("Pragma", "no-cache");
        req.getSession().invalidate();
        resp.sendRedirect(req.getContextPath());

    }


}