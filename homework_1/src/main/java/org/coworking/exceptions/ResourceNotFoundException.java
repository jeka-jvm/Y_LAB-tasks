package org.coworking.exceptions;


/**
 * Исключение, выбрасываемое при отсутствии ресурса.
 */
public class ResourceNotFoundException extends RuntimeException {

    /**
     * Конструктор с сообщением об ошибке.
     *
     * @param message сообщение об ошибке
     */
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
