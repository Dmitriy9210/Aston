package lesson3;

import lesson2.DAO;
import lesson2.User;
import lesson2.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTests {

    @Mock
    private DAO userDao;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createUser_ShouldCallDaoCreate() {
        userService.createUser("Alex", "alex@mail.com", 25);
        verify(userDao, times(1)).create(any(User.class));
    }

    @Test
    void createUser_ShouldThrow_WhenNameIsEmpty() {
        assertThrows(IllegalArgumentException.class, () ->
                userService.createUser("", "test@mail.com", 20));
    }

    @Test
    void getUserTest() {
        User user = new User("Bob", "bob@mail.com", 30);
        user.setId(1);
        when(userDao.read(1)).thenReturn(user);
        when(userDao.read(2)).thenReturn(new User());

        User result = userService.getUser(1);
        User result2 = userService.getUser(2);

        assertEquals("Bob", result.getUsername());
        verify(userDao, times(2)).read(anyInt());
    }

    @Test
    void getUserNotExistTest() {
        when(userDao.read(99)).thenReturn(null);
        assertThrows(IllegalArgumentException.class, () -> userService.getUser(99));
    }

    @Test
    void deleteUserTest() {
        User user = new User("Ann", "ann@mail.com", 28);
        user.setId(2);
        when(userDao.read(2)).thenReturn(user);

        userService.deleteUser(2);

        verify(userDao).delete(user);
    }

    @Test
    void deleteUserNotExistTest() {
        when(userDao.read(999)).thenReturn(null);
        assertThrows(IllegalArgumentException.class, () -> userService.deleteUser(999));
    }

    @Test
    void listAllTest() {
        List<User> users = List.of(new User("Tom", "tom@mail.com", 11),
                                   new User("Tom2", "tom@mail.com2", 22),
                                   new User("Tom3", "tom@mail.com3", 33),
                                   new User("Tom4", "tom@mail.com4", 44));
        when(userDao.findAll()).thenReturn(users);

        List<User> result = userService.listAll();

        assertEquals(4, result.size());
        verify(userDao).findAll();
    }
}
