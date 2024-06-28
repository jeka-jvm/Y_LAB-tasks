package org.coworking.dao;

import org.coworking.model.Booking;

import java.util.List;


/**
 * Интерфейс определяет операции для управления бронированиями в источнике данных.
 */
public interface BookingDao {

    /**
     * Добавляет новое бронирование в источник данных.
     *
     * @param booking Объект бронирования для добавления.
     */
    void addBooking(Booking booking);

    /**
     * Удаляет бронирование из источника данных на основе его идентификатора.
     *
     * @param bookingId Идентификатор бронирования для удаления.
     */
    void deleteBooking(int bookingId);

    /**
     * Извлекает бронирование из источника данных на основе его идентификатора.
     *
     * @param bookingId Идентификатор бронирования для извлечения.
     * @return Объект бронирования, связанный с указанным идентификатором, или null, если не найден.
     */
    Booking getBookingById(int bookingId);

    /**
     * Извлекает все бронирования, хранящиеся в источнике данных.
     *
     * @return Список, содержащий все бронирования, хранящиеся в источнике данных.
     */
    List<Booking> getAllBookings();

}
