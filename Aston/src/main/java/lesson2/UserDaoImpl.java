package lesson2;

import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class UserDaoImpl implements DAO {

    @Override
    public void create(User user) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            session.persist(user);
            tx.commit();
        }
    }

    @Override
    public User read(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            if (isNull(session.get(User.class, id)))
                return session.get(User.class, id);
            else {
                return new User(0);
            }
        }
    }

    @Override
    public void update(User user) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            session.merge(user);
            tx.commit();
        }
    }

    @Override
    public void delete(User user) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            session.remove(user);
            tx.commit();
        }
    }

    @Override
    public List<User> findAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from User", User.class).list();
        }
    }


    private boolean isNull(User user) {
        if (user != null) {
            return true;
        } else {
            System.out.println("Пользователь с таким ID не найден");
            return false;
        }
    }
}
