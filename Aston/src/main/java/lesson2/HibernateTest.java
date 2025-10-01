package lesson2;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateTest {
    public static void main(String[] args) {
        // Загружаем конфигурацию и создаём фабрику сессий
        Configuration cfg = new Configuration().configure(); // hibernate.cfg.xml в resources
        SessionFactory sessionFactory = cfg.buildSessionFactory();

        // Открываем сессию
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        // Пример: создаём нового пользователя
        User user = new User("testuser", "test@example.com", 4);
        session.save(user);

        session.getTransaction().commit();
        session.close();
        sessionFactory.close();

        System.out.println("Пользователь сохранён с id: " + user.getId());
    }
}
