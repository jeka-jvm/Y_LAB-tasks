package org.coworking.model;


/**
 * Представляет пользователя системы с указанным именем пользователя и паролем.
 */
public class User {

    private String username;
    private String password;

    /**
     * Конструирует новый объект пользователя с заданным именем пользователя и паролем.
     *
     * @param username имя пользователя
     * @param password пароль пользователя
     */
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * Возвращает имя пользователя.
     *
     * @return имя пользователя
     */
    public String getUsername() {
        return username;
    }

    /**
     * Устанавливает имя пользователя.
     *
     * @param username имя пользователя для установки
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Возвращает пароль пользователя.
     *
     * @return пароль пользователя
     */
    public String getPassword() {
        return password;
    }

    /**
     * Устанавливает пароль пользователя.
     *
     * @param password пароль для установки
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
