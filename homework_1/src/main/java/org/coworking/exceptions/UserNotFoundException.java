package org.coworking.exceptions;


/**
 * Исключение, выбрасываемое при отсутствии пользователя.
 */
public class UserNotFoundException extends RuntimeException {

    /**
     * Конструктор с сообщением об ошибке.
     *
     * @param message сообщение об ошибке
     */
    public UserNotFoundException(String message) {
        super(message);
    }
}
