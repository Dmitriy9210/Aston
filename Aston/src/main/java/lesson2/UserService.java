package lesson2;

import java.util.List;

public class UserService {

    private final DAO userDao;

    public UserService(DAO userDao) {
        this.userDao = userDao;
    }

    public void createUser(String username, String email, int age) {
        if (username == null || username.isEmpty()) {
            throw new IllegalArgumentException("Имя пользователя не может быть пустым");
        }
        User user = new User(username, email, age);
        userDao.create(user);
    }

    public User getUser(int id) {
        User user = userDao.read(id);
        if (user == null) {
            throw new IllegalArgumentException("Пользователь с id=" + id + " не найден");
        }
        return user;
    }

    public void updateUser(User user) {
        if (user.getId() == null) {
            throw new IllegalArgumentException("ID обязателен для обновления");
        }
        userDao.update(user);
    }

    public void deleteUser(int id) {
        User user = userDao.read(id);
        if (user == null) {
            throw new IllegalArgumentException("Нельзя удалить несуществующего пользователя");
        }
        userDao.delete(user);
    }

    public List<User> listAll() {
        return userDao.findAll();
    }
}
