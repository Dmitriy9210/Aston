package lesson2;

import java.util.Scanner;


public class HibernateTest {

    private static final Scanner scanner = new Scanner(System.in);
    private static final DAO userDao = new UserDaoImpl();

    public static void main(String[] args) {
        while (true) {
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
                case 0 -> System.exit(0);
            }
        }
    }

    private static void createUser() {
        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Age: ");
        int age = scanner.nextInt();

        User user = new User();
        user.setUsername(name);
        user.setEmail(email);
        user.setAge(age);

        userDao.create(user);
        System.out.println("User created!");
    }

    private static void readUser() {
        System.out.print("ID: ");
        int id = scanner.nextInt();

        User user = new User();
        user.setId(id);

        System.out.println("User: " + userDao.read(id).toString());

    }

    private static void updateUser() {
        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Age: ");
        int age = scanner.nextInt();

        User user = new User();
        user.setUsername(name);
        user.setEmail(email);
        user.setAge(age);

        userDao.update(user);
        System.out.println("User updated!");
    }

    private static void deleteUser() {
        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Age: ");
        int age = scanner.nextInt();

        User user = new User();
        user.setUsername(name);
        user.setEmail(email);
        user.setAge(age);

        userDao.delete(user);
        System.out.println("User deleted!");
    }

    private static void listUsers() {
    }
}

