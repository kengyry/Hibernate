package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import jm.task.core.jdbc.util.Util;

public class UserServiceImpl implements UserService {
    public void createUsersTable() {
        //UserDao userDao = new UserDaoJDBCImpl();
        UserDao userDao = new UserDaoHibernateImpl();
        userDao.createUsersTable();
    }

    public void dropUsersTable() {
        //UserDao userDao = new UserDaoJDBCImpl();
        UserDao userDao = new UserDaoHibernateImpl();
        userDao.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) {
        //UserDao userDao = new UserDaoJDBCImpl();
        UserDao userDao = new UserDaoHibernateImpl();
        userDao.saveUser(name, lastName, age);

    }

    public void removeUserById(long id) {
        UserDao userDao = new UserDaoHibernateImpl();
        userDao.removeUserById(id);
    }

    public List<User> getAllUsers() {
        //UserDao userDao = new UserDaoJDBCImpl();
        UserDao userDao = new UserDaoHibernateImpl();
        return userDao.getAllUsers();
    }

    public void cleanUsersTable() {
        //UserDao userDao = new UserDaoJDBCImpl();
        UserDao userDao = new UserDaoHibernateImpl();
        userDao.cleanUsersTable();
    }
}