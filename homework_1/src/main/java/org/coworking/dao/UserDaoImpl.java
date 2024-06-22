package org.coworking.dao;

import org.coworking.model.User;

import java.util.HashMap;
import java.util.Map;


/**
 * Реализация интерфейса UserDao для хранения пользователей в виде коллекции HashMap.
 */
public class UserDaoImpl implements UserDao {

    // Хранилище пользователей, где ключ - это имя пользователя (логин), а значение - объект пользователя
    private final Map<String, User> users = new HashMap<>();

    /**
     * Добавляет нового пользователя в хранилище.
     *
     * @param user объект пользователя для добавления
     */
    @Override
    public void addUser(User user) {
        users.put(user.getUsername(), user);
    }

    /**
     * Возвращает пользователя по его имени пользователя (логину).
     *
     * @param username имя пользователя (логин)
     * @return объект пользователя, если пользователь найден, или {@code null}, если пользователь не найден
     */
    @Override
    public User getUserByUsername(String username) {
        return users.get(username);
    }
}
