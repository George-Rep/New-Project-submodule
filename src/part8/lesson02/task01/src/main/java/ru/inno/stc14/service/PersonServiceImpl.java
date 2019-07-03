package ru.inno.stc14.service;

import ru.inno.stc14.dao.PersonDAO;
import ru.inno.stc14.dao.jdbc.PersonDAOImpl;
import ru.inno.stc14.dao.jdbc.singleton.ConnectionInstance;
import ru.inno.stc14.dao.jdbc.singleton.ResourceBundleInstance;
import ru.inno.stc14.entity.Person;
import ru.inno.stc14.servlet.AppContextListener;

import javax.servlet.ServletContext;
import java.sql.Connection;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * имплементация PersonService
 *
 */
public class PersonServiceImpl implements PersonService {
    private Logger logger = Logger.getLogger(PersonServiceImpl.class.getName());
    private final PersonDAO personDAO;
    private static ResourceBundle resource = ResourceBundleInstance.getInstance().getResource();
    /**
     *
     * конструктор
     * в данной реализации ServletContext используется для тспользования функции
     * ServletContext.getRealPath
     * @param ctx ServletContext
     * @see ConnectionInstance
     *
     */
    public PersonServiceImpl(ServletContext ctx) {
        personDAO = new PersonDAOImpl(ctx);
    }
    /**
     *
     * получение списка person
     *
     */
    @Override
    public List<Person> getList() {
        return personDAO.getList();
    }

    /**
     *
     * добавление person в БД
     *
     */
    @Override
    public boolean addPerson(String name, String birth, String email,String phone) {
        Person person = new Person();
        person.setName(name);

        Date date = safeParseDate(birth);
        person.setEmail(email);
        person.setPhone(phone);
        person.setBirthDate(date);
        return personDAO.addPerson(person);
    }
    /**
     *
     * получение Date из строки
     *
     */
    private Date safeParseDate(String birthStr) {
        DateFormat format = new SimpleDateFormat(resource.getString("PersonServiceImpl.dateformat"));
        try {
            return format.parse(birthStr);
        } catch (ParseException e) {
            logger.log(Level.SEVERE, resource.getString("PersonServiceImpl.logmessage"), e);
            throw new RuntimeException(e);
        }
    }

}
