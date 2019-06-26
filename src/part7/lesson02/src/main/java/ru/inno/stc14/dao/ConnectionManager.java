package ru.inno.stc14.dao;

import java.sql.Connection;
/**
 *
 * ConnectionManager
 *
 */
public interface ConnectionManager {

    Connection getConnection();
}
