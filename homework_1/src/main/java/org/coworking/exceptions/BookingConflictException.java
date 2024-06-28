package org.coworking.exceptions;


/**
 * Исключение, выбрасываемое при конфликте бронирования.
 */
public class BookingConflictException extends RuntimeException {

    /**
     * Конструктор с сообщением об ошибке.
     *
     * @param message сообщение об ошибке
     */
    public BookingConflictException(String message) {
        super(message);
    }
}
