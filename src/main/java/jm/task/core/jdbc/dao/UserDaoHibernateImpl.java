package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        SessionFactory sessionFactory = Util.HibernateSessionFactoryUtil.getSessionFactory();
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            String sql = "CREATE TABLE USERS " +
                    "(id BIGINT not NULL PRIMARY KEY AUTO_INCREMENT, " +
                    " name VARCHAR(255) not NULL, " +
                    " lastName VARCHAR(255) not NULL, " +
                    " age TINYINT UNSIGNED not NULL)";
            session.createSQLQuery(sql).executeUpdate();
            session.getTransaction().commit();
    }

    @Override
    public void dropUsersTable() {
        SessionFactory sessionFactory = Util.HibernateSessionFactoryUtil.getSessionFactory();
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.createSQLQuery("DROP TABLE IF EXISTS USERS").executeUpdate();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        User user = new User(name, lastName, age);
        SessionFactory sessionFactory = Util.HibernateSessionFactoryUtil.getSessionFactory();
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        SessionFactory sessionFactory = Util.HibernateSessionFactoryUtil.getSessionFactory();
        try (Session session = sessionFactory.openSession()) {
            Object user = session.load(User.class, id);
            if (user != null) {
                session.beginTransaction();
                session.delete(user);
                session.getTransaction().commit();
            }
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        SessionFactory sessionFactory = Util.HibernateSessionFactoryUtil.getSessionFactory();
        List <User> list = new ArrayList<>();
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            list = session.createQuery("from User").list();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void cleanUsersTable() {
        SessionFactory sessionFactory = Util.HibernateSessionFactoryUtil.getSessionFactory();
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.createSQLQuery("TRUNCATE TABLE USERS").executeUpdate();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }
}
