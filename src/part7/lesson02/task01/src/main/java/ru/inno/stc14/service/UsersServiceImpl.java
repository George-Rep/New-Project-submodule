package ru.inno.stc14.service;


import ru.inno.stc14.dao.UsersDAO;

import ru.inno.stc14.dao.jdbc.UsersDAOImpl;


import java.sql.Connection;

import java.util.logging.Logger;

/**
 *
 * имплементация UsersService
 *
 */
public class UsersServiceImpl implements UsersService {

        private Logger logger = Logger.getLogger(ru.inno.stc14.service.PersonServiceImpl.class.getName());
        private final UsersDAO usersDAO;

        public UsersServiceImpl(Connection con) {
            usersDAO = new UsersDAOImpl(con);
        }

        @Override
        public int validateLogin(String login, String password){
            return usersDAO.validateLogin(login, password);
        }



}
