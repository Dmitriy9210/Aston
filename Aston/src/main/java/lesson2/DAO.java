package lesson2;

import java.util.List;

public interface DAO {

    void create(User user);

    User read(int id);

    void update(User user);

    void delete(User user);

    List<User> findAll();
}
