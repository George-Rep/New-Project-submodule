package ru.inno.stc14.servlet;


import ru.inno.stc14.entity.Person;
import ru.inno.stc14.service.PersonService;
import ru.inno.stc14.service.PersonServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import java.util.List;

/**
 * выдача списка person
 */
@WebServlet(
        name = "personList",
        loadOnStartup = 1,
        urlPatterns = "/person/list"
)
public class ListPersonServlet extends HttpServlet {
    private PersonService person;

    @Override
    public void init() throws ServletException {
        person = new PersonServiceImpl(getServletContext());
        super.init();
    }

    /**
     * выдача списка person
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Person> res = person.getList();
        req.setAttribute("persons", res);
        req.getRequestDispatcher("/persons.jsp")
                .forward(req, resp);
    }
}
