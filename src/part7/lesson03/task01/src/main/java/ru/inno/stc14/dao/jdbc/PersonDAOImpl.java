package ru.inno.stc14.dao.jdbc;

import ru.inno.stc14.dao.PersonDAO;
import ru.inno.stc14.entity.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * PersonDAOImpl
 *
 */
public class PersonDAOImpl implements PersonDAO {
    private static Logger logger = Logger.getLogger(PersonDAOImpl.class.getName());
    private final Connection connection;
    private static ResourceBundle resource = ResourceBundle.getBundle("demo");
    public PersonDAOImpl(Connection con) {
        this.connection = con;
    }

    private static final String INSERT_PERSON_SQL_TEMPLATE =
            resource.getString("PersonDAOImpl.statement.insert");
    private static final String SELECT_PERSON_SQL_TEMPLATE =
            resource.getString("PersonDAOImpl.statement.select");
    /**
     *
     * получение списка person
     *
     */
    @Override
    public List<Person> getList() {
        List<Person> result = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SELECT_PERSON_SQL_TEMPLATE)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Person person = new Person();
                    person.setId(resultSet.getInt(1));
                    person.setName(resultSet.getString(2));
                    Date date = new Date(resultSet.getLong(3));
                    person.setBirthDate(date);
                    person.setEmail(resultSet.getString(4));
                    person.setPhone(resultSet.getString(5));
                    result.add(person);
                }
            }

        } catch (SQLException e) {
            logger.log(Level.SEVERE, resource.getString("PersonDAOImpl.logmessage"), e);
        }
        return result;
    }
    /**
     *
     * добавление person в БД
     *
     */
    @Override
    public boolean addPerson(Person person) {
        try (PreparedStatement statement = connection.prepareStatement(INSERT_PERSON_SQL_TEMPLATE)) {
            statement.setString(1, person.getName());
            if (person.getBirthDate() == null) {
                statement.setNull(2, Types.BIGINT);
            } else {
                statement.setLong(2, person.getBirthDate().getTime());
            }
            statement.setString(3, person.getEmail());
            statement.setString(4, person.getPhone());
            statement.execute();
            return true;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, resource.getString("PersonDAOImpl.logmessage"), e);
            return false;
        }
    }
}
