package lesson2;

import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class UserDaoImpl implements DAO {

    @Override
    public void create(User user) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            session.save(user);
            tx.commit();
        }
    }

    @Override
    public User read(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            if (isNull(session.get(User.class, id)))
                return session.get(User.class, id);
            else {
                return new User();
            }
        }
    }

    @Override
    public void update(User user) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();

            if (isNull((User) session.get(user.getUsername(), user.getId())))
                session.update(user);
            else {
                session.update(new User());

//                isNull(session.get(User.class, user.getId()));
//                session.update(user);
                tx.commit();
            }
        }
    }

    @Override
    public void delete(User user) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            session.delete(user);
            tx.commit();
        }
    }

    @Override
    public List<User> findAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
//            session.get(user);
            tx.commit();
            return List.of();
        }
    }


    private <T> boolean isNull(User u) {
        if (u != null) {
            return true;
        } else {
            System.out.println("Пользователь с таким ID не найден");
            return false;
        }
    }
}
