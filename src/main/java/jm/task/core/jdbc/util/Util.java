package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {

    public static class ConnectionUtils {

        private static Connection connection;
        private static final String URL = "jdbc:mysql://localhost:3306/mydb?useSSL=false&serverTimezone=UTC";
        private static final String USER = "root";
        private static final String PASS = "root";

        public static Connection getConnection() {

            try {
                Driver driver = new com.mysql.cj.jdbc.Driver();
                DriverManager.registerDriver(driver);
                connection = DriverManager.getConnection(URL, USER, PASS);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            return connection;
        }
    }

    public static class HibernateSessionFactoryUtil {
        private static SessionFactory sessionFactory;

        private HibernateSessionFactoryUtil() {
        }

        public static SessionFactory getSessionFactory() {
            if (sessionFactory == null) {
                try {
                    Configuration configuration = new Configuration()
                            .setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect")
                            .setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver")
                            .setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/" +
                                    "mydb?useSSL=false&serverTimezone=UTC")
                            .setProperty("hibernate.connection.username", "root")
                            .setProperty("hibernate.connection.password", "root")
                            .setProperty("hibernate.connection.autocommit", "true");
                    /*//configuration.addAnnotatedClass(User.class);
                    StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
                            .applySettings(configuration.getProperties());*/
                    sessionFactory = configuration.buildSessionFactory();

                } catch (Exception ignore) {

                }
            }
            return sessionFactory;
        }
    }
}
