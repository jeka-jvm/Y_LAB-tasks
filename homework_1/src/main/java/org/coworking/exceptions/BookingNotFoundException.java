package org.coworking.exceptions;


/**
 * Исключение, выбрасываемое при отсутствии бронирования.
 */
public class BookingNotFoundException extends RuntimeException {

    /**
     * Конструктор с сообщением об ошибке.
     *
     * @param message сообщение об ошибке
     */
    public BookingNotFoundException(String message) {
        super(message);
    }
}
