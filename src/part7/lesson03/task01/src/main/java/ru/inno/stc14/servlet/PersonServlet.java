package ru.inno.stc14.servlet;

import ru.inno.stc14.service.PersonService;
import ru.inno.stc14.service.PersonServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.util.ResourceBundle;

/**
 *
 * добавление новых person в список
 *
 */
@WebServlet(
        name = "person",
        loadOnStartup = 1,
        urlPatterns = "/person"
)
public class PersonServlet extends HttpServlet {
    private PersonService person;

    @Override
    public void init() throws ServletException {
        Connection connection = (Connection) getServletContext().getAttribute("DBConnection");
        person = new PersonServiceImpl(connection);
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/form.jsp")
                .forward(req, resp);
    }
    /**
     *
     * добавление person из формы в БД.
     * Редирект на список person
     *
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ResourceBundle resource= (ResourceBundle) getServletContext().getAttribute("Properties");
        req.setCharacterEncoding(resource.getString("PersonServlet.encoding"));
        String name = req.getParameter("name");
        String birth = req.getParameter("birth");
        String email = req.getParameter("email");
        String phone = req.getParameter("phone");
        person.addPerson(name, birth, email,phone);

        resp.sendRedirect(req.getContextPath() + "/person/list");
    }
}
