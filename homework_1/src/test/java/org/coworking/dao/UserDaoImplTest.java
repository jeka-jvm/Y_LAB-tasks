package org.coworking.dao;

import org.coworking.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class UserDaoImplTest {

    private UserDaoImpl userDao;

    @BeforeEach
    void setUp() {
        userDao = new UserDaoImpl();
    }

    @Test
    void testAddUser() {
        User user = new User("testUser", "password");

        userDao.addUser(user);

        User retrievedUser = userDao.getUserByUsername("testUser");

        assertNotNull(retrievedUser);
        assertEquals("testUser", retrievedUser.getUsername());
        assertEquals("password", retrievedUser.getPassword());
    }

    @Test
    void testGetUserByUsername() {
        User user = new User("testUser", "password");

        userDao.addUser(user);

        User retrievedUser = userDao.getUserByUsername("testUser");

        assertNotNull(retrievedUser);
        assertEquals("testUser", retrievedUser.getUsername());
        assertEquals("password", retrievedUser.getPassword());
    }

    @Test
    void testGetUserByUsername_NotFound() {
        User retrievedUser = userDao.getUserByUsername("nonExistingUser");

        assertNull(retrievedUser);
    }
}
