package lesson2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

public class HibernateTest {

    private static final Logger log = LoggerFactory.getLogger(HibernateTest.class);
    private static final Scanner scanner = new Scanner(System.in);
    private static final DAO userDao = new UserDaoImpl();

    public static void main(String[] args) {
        log.info("Запуск приложения User Service");

        while (true) {
            log.info("Отображение меню пользователю");
            System.out.println("1. Create user");
            System.out.println("2. Read user");
            System.out.println("3. Update user");
            System.out.println("4. Delete user");
            System.out.println("5. Show all users");
            System.out.println("0. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> createUser();
                case 2 -> readUser();
                case 3 -> updateUser();
                case 4 -> deleteUser();
                case 5 -> listUsers();
                case 0 -> {
                    log.info("Завершение работы приложения");
                    System.exit(0);
                }
                default -> {
                    log.warn("Выбран неизвестный пункт меню: {}", choice);
                    System.exit(0);
                }
            }
        }
    }

    private static void createUser() {
        log.debug("Начало создания пользователя");
        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Age: ");
        int age = scanner.nextInt();

        User user = new User(name, email, age);
        userDao.create(user);

        log.info("Пользователь создан: {}", user);
        System.out.println("User created!");
    }

    private static void readUser() {
        System.out.print("ID: ");
        int id = scanner.nextInt();
        User user = userDao.read(id);
        if (user != null) {
            log.info("Найден пользователь: {}", user);
            System.out.println("User: " + user);
        } else {
            log.warn("Пользователь с id={} не найден", id);
            System.out.println("User not found!");
        }
    }

    private static void updateUser() {
        System.out.print("Id: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Age: ");
        int age = scanner.nextInt();

        User existing = userDao.read(id);
        if (existing != null) {
            existing.setUsername(name);
            existing.setEmail(email);
            existing.setAge(age);
            userDao.update(existing);
            log.info("Пользователь обновлён: {}", existing);
            System.out.println("User updated!");
        } else {
            log.warn("Не удалось обновить: пользователь с id={} не найден", id);
            System.out.println("User NOT updated!");
        }
    }

    private static void deleteUser() {
        System.out.print("Id: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        User user = userDao.read(id);
        if (user != null) {
            userDao.delete(user);
            log.info("Пользователь удалён: {}", user);
            System.out.println("User deleted!");
        } else {
            log.warn("Не удалось удалить: пользователь с id={} не найден", id);
            System.out.println("User NOT EXIST!");
        }
    }

    private static void listUsers() {
        if (userDao.read(1) != null) {
            log.info("Вывод всех пользователей: " + userDao.findAll().toString());
        } else {
            log.warn("Попытка вывода списка, но пользователей нет");
        }
    }
}
