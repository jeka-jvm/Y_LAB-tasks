package org.coworking.service;

import org.coworking.exceptions.BookingNotFoundException;
import org.coworking.model.Booking;

import java.time.LocalDateTime;
import java.util.List;


/**
 * Сервис управления бронированиями ресурсов.
 */
public interface BookingService {

    /**
     * Забронировать ресурс (рабочее пространство или конференц-зал) для пользователя.
     *
     * @param resourceId   ID ресурса
     * @param resourceType тип ресурса ("Workspace" или "ConferenceRoom")
     * @param username     имя пользователя, который бронирует ресурс
     * @param startTime    время начала бронирования
     * @param endTime      время окончания бронирования
     * @throws RuntimeException если бронирование не удалось
     */
    void bookResource(int resourceId, String resourceType, String username, LocalDateTime startTime, LocalDateTime endTime) throws RuntimeException;

    /**
     * Отменить бронирование по его ID.
     *
     * @param bookingId ID бронирования для отмены
     * @throws BookingNotFoundException если бронирование не найдено
     */
    void cancelBooking(int bookingId) throws BookingNotFoundException;

    /**
     * Получить список всех бронирований.
     *
     * @return список всех бронирований
     */
    List<Booking> getAllBookings();

    /**
     * Получить список бронирований на указанную дату.
     *
     * @param date дата для поиска бронирований
     * @return список бронирований на указанную дату
     */
    List<Booking> getBookingsByDate(LocalDateTime date);

    /**
     * Получить список бронирований пользователя.
     *
     * @param username имя пользователя для поиска его бронирований
     * @return список бронирований пользователя
     */
    List<Booking> getBookingsByUsername(String username);

    /**
     * Получить список бронирований ресурса по его ID.
     *
     * @param resourceId ID ресурса для поиска его бронирований
     * @return список бронирований ресурса
     */
    List<Booking> getBookingsByResourceId(int resourceId);

}

