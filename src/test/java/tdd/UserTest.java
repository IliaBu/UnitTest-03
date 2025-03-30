package tdd;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

//������� 3.* �������� ������� � ����� UserRepository, ������� ������������� ���� �������������,
//����� ���������������. ��� �����, ��� ����������� ��������� ����� User ����� ���������,
//�����������, �������� �� ������������ ���������� �������. ������������� ������ �������.

class UserTest {

    private static User user1;
    private static User user2;
    private static User admin;
    private final UserRepository userRepository = new UserRepository();

    @BeforeEach
    public void setUp() {
        user1 = new User("����","12345",false);
        user2 = new User("������","1234",false);
        admin = new User("����","45273",true);
    }

    @Test
    void userAuthenticate() {
        assertTrue(user1.authenticate("����","12345"));
    }

    @Test
    void userAuthenticateFailed() {
        assertFalse(user2.authenticate("������", "12346"));
    }

    @Test
    void addUser() {

        admin.authenticate("����","45273");
        user1.authenticate("����", "12345");
        user2.authenticate("������", "1234");

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

        admin.authenticate("����","45273");
        user1.authenticate("����", "12345");
        user2.authenticate("������", "1234");
        userRepository.addUser(admin);
        userRepository.addUser(user1);
        userRepository.addUser(user2);

        userRepository.logoutNonAdminUsers();

        assertTrue(admin.isAuthenticate);
        assertFalse(user1.isAuthenticate);
        assertFalse(user2.isAuthenticate);
    }


}