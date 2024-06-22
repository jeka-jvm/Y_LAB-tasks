package org.coworking.service;

import org.coworking.exceptions.UserNotFoundException;


/**
 * Интерфейс для управления пользователями в системе.
 */
public interface UserService {

    /**
     * Регистрация нового пользователя с указанным именем и паролем.
     *
     * @param username имя пользователя для регистрации
     * @param password пароль пользователя для регистрации
     * @throws RuntimeException если происходит ошибка в процессе регистрации
     */
    void registerUser(String username, String password) throws RuntimeException;

    /**
     * Аутентификация пользователя по указанному имени и паролю.
     *
     * @param username имя пользователя для аутентификации
     * @param password пароль пользователя для аутентификации
     * @throws UserNotFoundException если пользователь с указанным именем не найден
     */
    void login(String username, String password) throws UserNotFoundException;

    /**
     * Проверяет, аутентифицирован ли пользователь с указанным именем.
     *
     * @param username имя пользователя для проверки статуса входа
     * @return true, если пользователь аутентифицирован; в противном случае - false
     */
    boolean isLoggedIn(String username);

    /**
     * Получает имя пользователя, который в данный момент аутентифицирован в системе.
     *
     * @return имя текущего аутентифицированного пользователя или null, если никто не вошел
     */
    String getLoggedInUser();

}
