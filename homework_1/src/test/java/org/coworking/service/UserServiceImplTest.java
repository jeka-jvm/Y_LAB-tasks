package org.coworking.service;

import org.coworking.dao.UserDao;
import org.coworking.exceptions.UserNotFoundException;
import org.coworking.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


public class UserServiceImplTest {

    @Mock
    private UserDao userDaoMock;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        userDaoMock = mock(UserDao.class);
        userService = new UserServiceImpl(userDaoMock);
    }

    @Test
    void testRegisterUser_UserAlreadyExists() {
        String username = "testUser";
        String password = "password";
        User existingUser = new User(username, password);

        when(userDaoMock.getUserByUsername(username)).thenReturn(existingUser);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> userService.registerUser(username, password));
        assertEquals("User already exists", exception.getMessage());

        verify(userDaoMock, times(1)).getUserByUsername(username);
        verify(userDaoMock, never()).addUser(any());
    }

    @Test
    void testLogin_SuccessfulLogin() {
        String username = "testUser";
        String password = "password";
        User existingUser = new User(username, password);

        when(userDaoMock.getUserByUsername(username)).thenReturn(existingUser); // Пользователь существует

        assertDoesNotThrow(() -> userService.login(username, password));

        assertEquals(username, userService.getLoggedInUser());
    }

    @Test
    void testLogin_InvalidCredentials() {
        String username = "testUser";
        String password = "password";
        User existingUser = new User(username, "wrongPassword");

        when(userDaoMock.getUserByUsername(username)).thenReturn(existingUser); // Пользователь существует

        UserNotFoundException exception = assertThrows(UserNotFoundException.class, () -> userService.login(username, password));
        assertEquals("Invalid username or password", exception.getMessage());

        assertNull(userService.getLoggedInUser());
    }

    @Test
    void testIsLoggedIn() {
        String loggedInUser = "loggedInUser";
        String password = "password";
        User existingUser = new User(loggedInUser, password);

        when(userDaoMock.getUserByUsername(loggedInUser)).thenReturn(existingUser);

        assertDoesNotThrow(() -> userService.login(loggedInUser, password));

        assertTrue(userService.isLoggedIn(loggedInUser));
    }
}
