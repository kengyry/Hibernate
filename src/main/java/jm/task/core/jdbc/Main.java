package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {

        // Создание таблицы User(ов) через Hibernate
        UserService userService = new UserServiceImpl();
        userService.dropUsersTable();
        userService.createUsersTable();

        userService.saveUser("Корвин", "Из Амбера", (byte) 40);
        System.out.println("User с именем " + userService.getAllUsers().get(0).getName() + " добавлен в базу данных.");
        userService.saveUser("Рэндом", "Из Амбера", (byte) 40);
        System.out.println("User с именем " + userService.getAllUsers().get(1).getName() + " добавлен в базу данных.");
        userService.saveUser("Бенедикт", "Из Амбера", (byte) 40);
        System.out.println("User с именем " + userService.getAllUsers().get(2).getName() + " добавлен в базу данных.");
        userService.saveUser("Каин", "Из Амбера", (byte) 40);
        System.out.println("User с именем " + userService.getAllUsers().get(3).getName() + " добавлен в базу данных.");

        System.out.println(userService.getAllUsers().toString());

        userService.removeUserById(2);

        /*userService.cleanUsersTable();

        userService.dropUsersTable();*/
    }
}
