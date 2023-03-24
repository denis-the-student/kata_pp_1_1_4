package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        UserServiceImpl userService = new UserServiceImpl();
        userService.saveUser("Name1", "Lastname1", (byte) 11);
        userService.saveUser("Name2", "Lastname2", (byte) 22);
        userService.saveUser("Name3", "Lastname3", (byte) 33);
        userService.saveUser("Name4", "Lastname4", (byte) 44);
        System.out.println(userService.getAllUsers());
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
