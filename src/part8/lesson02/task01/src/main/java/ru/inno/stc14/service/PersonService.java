package ru.inno.stc14.service;

import ru.inno.stc14.entity.Person;

import java.util.List;

/**
 *
 * PersonService
 *
 */
public interface PersonService {
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
    boolean addPerson(String name, String birth, String email,String phone);
}
