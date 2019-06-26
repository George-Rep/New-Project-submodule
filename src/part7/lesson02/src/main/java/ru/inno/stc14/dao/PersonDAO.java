package ru.inno.stc14.dao;

import ru.inno.stc14.entity.Person;

import java.util.List;
/**
 *
 * PersonDAO
 *
 */
public interface PersonDAO {

    List<Person> getList();

    boolean addPerson(Person person);
}
