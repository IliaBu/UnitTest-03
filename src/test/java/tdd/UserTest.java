package tdd;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

//Задание 3.* Добавьте функцию в класс UserRepository, которая разлогинивает всех пользователей,
//кроме администраторов. Для этого, вам потребуется расширить класс User новым свойством,
//указывающим, обладает ли пользователь админскими правами. Протестируйте данную функцию.

class UserTest {

    private static User user1;
    private static User user2;
    private static User admin;
    private final UserRepository userRepository = new UserRepository();

    @BeforeEach
    public void setUp() {
        user1 = new User("Иван","12345",false);
        user2 = new User("Сергей","1234",false);
        admin = new User("Илья","45273",true);
    }

    @Test
    void userAuthenticate() {
        assertTrue(user1.authenticate("Иван","12345"));
    }

    @Test
    void userAuthenticateFailed() {
        assertFalse(user2.authenticate("Сергей", "12346"));
    }

    @Test
    void addUser() {

        admin.authenticate("Илья","45273");
        user1.authenticate("Иван", "12345");
        user2.authenticate("Сергей", "1234");

        userRepository.addUser(admin);
        userRepository.addUser(user1);
        userRepository.addUser(user2);

        assertTrue(userRepository.findByName(admin.name));
        assertTrue(userRepository.findByName(user1.name));
        assertTrue(userRepository.findByName(user2.name));

    }

    @Test
    void isAdminUsers() {
        assertTrue(admin.isAdmin);
        assertFalse(user1.isAdmin);
        assertFalse(user2.isAdmin);
    }

    @Test
    void logoutNonAdminUsers() {

        admin.authenticate("Илья","45273");
        user1.authenticate("Иван", "12345");
        user2.authenticate("Сергей", "1234");
        userRepository.addUser(admin);
        userRepository.addUser(user1);
        userRepository.addUser(user2);

        userRepository.logoutNonAdminUsers();

        assertTrue(admin.isAuthenticate);
        assertFalse(user1.isAuthenticate);
        assertFalse(user2.isAuthenticate);
    }


}