package org.coworking.service;

import org.coworking.dao.UserDao;
import org.coworking.exceptions.UserNotFoundException;
import org.coworking.model.User;


/**
 * Реализация сервиса управления пользователями.
 */
public class UserServiceImpl implements UserService {
    private final UserDao userDao;

    private String loggedInUser;

    /**
     * Конструктор для инициализации UserServiceImpl с указанием UserDao.
     *
     * @param userDao объект DAO для взаимодействия с данными пользователей
     */
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    /**
     * Регистрация нового пользователя с указанным именем и паролем.
     *
     * @param username имя пользователя для регистрации
     * @param password пароль пользователя для регистрации
     * @throws RuntimeException если пользователь с таким именем уже существует
     */
    @Override
    public void registerUser(String username, String password) throws RuntimeException {
        if (userDao.getUserByUsername(username) != null) {
            throw new RuntimeException("User already exists");
        }
        User newUser = new User(username, password);
        userDao.addUser(newUser);
    }

    /**
     * Аутентификация пользователя по указанному имени и паролю.
     *
     * @param username имя пользователя для аутентификации
     * @param password пароль пользователя для аутентификации
     * @throws UserNotFoundException если пользователь с указанным именем не найден или пароль неверен
     */
    @Override
    public void login(String username, String password) throws UserNotFoundException {
        User user = userDao.getUserByUsername(username);
        if (user == null || !user.getPassword().equals(password)) {
            throw new UserNotFoundException("Invalid username or password");
        }
        loggedInUser = username;
    }

    /**
     * Проверяет, аутентифицирован ли пользователь с указанным именем.
     *
     * @param username имя пользователя для проверки статуса входа
     * @return true, если пользователь аутентифицирован; в противном случае - false
     */
    @Override
    public boolean isLoggedIn(String username) {
        return username.equals(loggedInUser);
    }

    /**
     * Получает имя пользователя, который в данный момент аутентифицирован в системе.
     *
     * @return имя текущего аутентифицированного пользователя или null, если никто не вошел
     */
    public String getLoggedInUser() {
        return loggedInUser;
    }
}
