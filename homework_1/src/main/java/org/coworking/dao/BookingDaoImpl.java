package org.coworking.dao;

import org.coworking.model.Booking;

import java.util.ArrayList;
import java.util.List;


/**
 * Реализация интерфейса {@link BookingDao}, предоставляющая методы для работы с бронированиями.
 */
public class BookingDaoImpl implements BookingDao {

    /** Список всех бронирований */
    private final List<Booking> bookings = new ArrayList<>();

    /**
     * Добавляет новое бронирование в список.
     *
     * @param booking объект бронирования для добавления
     */
    @Override
    public void addBooking(Booking booking) {
        bookings.add(booking);
    }

    /**
     * Удаляет бронирование по указанному идентификатору.
     *
     * @param bookingId идентификатор бронирования для удаления
     */
    @Override
    public void deleteBooking(int bookingId) {
        bookings.removeIf(booking -> booking.getId() == bookingId);
    }

    /**
     * Возвращает бронирование по его идентификатору.
     *
     * @param bookingId идентификатор бронирования
     * @return объект бронирования или {@code null}, если бронирование не найдено
     */
    @Override
    public Booking getBookingById(int bookingId) {
        for (Booking booking : bookings) {
            if (booking.getId() == bookingId) {
                return booking;
            }
        }
        return null;
    }

    /**
     * Возвращает список всех бронирований.
     *
     * @return список всех бронирований
     */
    @Override
    public List<Booking> getAllBookings() {
        return new ArrayList<>(bookings);
    }
}
