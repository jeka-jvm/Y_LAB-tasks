package org.coworking.dao;

import org.coworking.model.User;


/**
 * Интерфейс для доступа к данным пользователей.
 */
public interface UserDao {

    /**
     * Добавляет нового пользователя в хранилище.
     *
     * @param user объект пользователя для добавления
     */
    void addUser(User user);

    /**
     * Возвращает пользователя по его имени пользователя (логину).
     *
     * @param username имя пользователя (логин)
     * @return объект пользователя, если пользователь найден, или {@code null}, если пользователь не найден
     */
    User getUserByUsername(String username);

}
