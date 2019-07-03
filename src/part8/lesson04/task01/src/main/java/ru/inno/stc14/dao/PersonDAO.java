package ru.inno.stc14.dao;

import ru.inno.stc14.entity.Person;

import java.util.List;
/**
 *
 * PersonDAO
 *
 */
public interface PersonDAO {
    /**
     *
     * получение списка person
     *
     */
    List<Person> getList();
    /**
     *
     * добавление person в БД
     *
     */
    boolean addPerson(Person person);
}
