package lesson2;

import org.hibernate.SessionFactory;

public class HibernateUtilForTest extends HibernateUtil {

    private static SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
