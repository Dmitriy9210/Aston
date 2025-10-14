package lesson3;

import lesson2.HibernateUtilForTest;
import lesson2.User;
import lesson2.UserDaoImpl;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.*;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Testcontainers
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserDaoTests {

    @Container
    private static final PostgreSQLContainer<?> postgres =
            new PostgreSQLContainer<>("postgres:16")
                    .withDatabaseName("testdb1")
                    .withUsername("testuser1")
                    .withPassword("secret1");

    private static UserDaoImpl userDao;

    @BeforeAll
    static void setUp() {

        Configuration configuration = new Configuration()
                .configure("hibernate.cfg.xml")
                .setProperty("hibernate.connection.url", postgres.getJdbcUrl())
                .setProperty("hibernate.connection.username", postgres.getUsername())
                .setProperty("hibernate.connection.password", postgres.getPassword());

        SessionFactory sessionFactory = configuration.buildSessionFactory();
        new HibernateUtilForTest().setSessionFactory(sessionFactory);

        userDao = new UserDaoImpl();
    }

    @Test
    @Order(1)
    void createUserTest() {
        User user = new User("Alex", "alex@test.ru", 25);
        userDao.create(user);
        System.out.println("🚀 Using DB: " + postgres.getJdbcUrl());

        List<User> users = userDao.findAll();
        assertEquals("Alex", users.get(users.size()-1).getUsername());
    }

    @Test
    @Order(2)
    void readUserTest() {
        User user = userDao.findAll().get(0);
        User found = userDao.read(user.getId());

        assertNotNull(found);
        assertEquals(user.getEmail(), found.getEmail());
    }

    @Test
    @Order(3)
    void updateUserTest() {
        User user = userDao.findAll().get(0);
        user.setUsername("Alex Updated");

        userDao.update(user);

        User updated = userDao.read(user.getId());
        assertEquals("Alex Updated", updated.getUsername());
    }

    @Test
    @Order(4)
    void deleteUserTest() {
        User user = userDao.findAll().get(0);
        userDao.delete(user);

        List<User> users = userDao.findAll();
        assertFalse(users.contains(user));
    }
}
