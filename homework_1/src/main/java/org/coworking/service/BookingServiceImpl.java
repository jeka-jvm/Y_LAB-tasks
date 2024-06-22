package org.coworking.service;

import org.coworking.dao.BookingDao;
import org.coworking.dao.ConferenceRoomDao;
import org.coworking.dao.WorkspaceDao;
import org.coworking.exceptions.BookingConflictException;
import org.coworking.exceptions.BookingNotFoundException;
import org.coworking.model.Booking;
import org.coworking.model.ConferenceRoom;
import org.coworking.model.Workspace;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


/**
 * Реализация сервиса бронирования ресурсов.
 */
public class BookingServiceImpl implements BookingService {

    private final BookingDao bookingDao;
    private final WorkspaceDao workspaceDao;
    private final ConferenceRoomDao conferenceRoomDao;

    /**
     * Конструктор для инициализации объекта сервиса.
     *
     * @param bookingDao        DAO для управления бронированиями
     * @param workspaceDao      DAO для управления рабочими местами
     * @param conferenceRoomDao DAO для управления конференц-залами
     */
    public BookingServiceImpl(BookingDao bookingDao, WorkspaceDao workspaceDao, ConferenceRoomDao conferenceRoomDao) {
        this.bookingDao = bookingDao;
        this.workspaceDao = workspaceDao;
        this.conferenceRoomDao = conferenceRoomDao;
    }

    /**
     * Забронировать ресурс (рабочее место или конференц-зал) на указанный период времени.
     *
     * @param resourceId  идентификатор ресурса
     * @param resourceType тип ресурса ("Workspace" или "ConferenceRoom")
     * @param username    имя пользователя, который бронирует ресурс
     * @param startTime   время начала бронирования
     * @param endTime     время окончания бронирования
     * @throws RuntimeException если возникла ошибка при бронировании
     */
    @Override
    public void bookResource(int resourceId, String resourceType, String username, LocalDateTime startTime, LocalDateTime endTime) throws RuntimeException {
        if (resourceType.equalsIgnoreCase("Workspace")) {
            Workspace workspace = workspaceDao.getWorkspaceById(resourceId);
            if (workspace == null) {
                throw new BookingNotFoundException("Workspace not found");
            }
            checkBookingConflict(workspace.getId(), "Workspace", startTime, endTime);
        } else if (resourceType.equalsIgnoreCase("ConferenceRoom")) {
            ConferenceRoom conferenceRoom = conferenceRoomDao.getConferenceRoomById(resourceId);
            if (conferenceRoom == null) {
                throw new BookingNotFoundException("Conference Room not found");
            }
            checkBookingConflict(conferenceRoom.getId(), "ConferenceRoom", startTime, endTime);
        } else {
            throw new IllegalArgumentException("Invalid resource type");
        }

        Booking booking = new Booking(bookingDao.getAllBookings().size() + 1, resourceId, resourceType, username, startTime, endTime);
        bookingDao.addBooking(booking);
    }

    /**
     * Отменить бронирование по идентификатору брони.
     *
     * @param bookingId идентификатор бронирования для отмены
     * @throws BookingNotFoundException если бронирование с указанным идентификатором не найдено
     */
    @Override
    public void cancelBooking(int bookingId) throws BookingNotFoundException {
        Booking booking = bookingDao.getBookingById(bookingId);
        if (booking == null) {
            throw new BookingNotFoundException("Booking not found");
        }
        bookingDao.deleteBooking(bookingId);
    }

    /**
     * Получить список всех существующих бронирований.
     *
     * @return список всех бронирований
     */
    @Override
    public List<Booking> getAllBookings() {
        return bookingDao.getAllBookings();
    }

    /**
     * Получить список бронирований на указанную дату.
     *
     * @param date дата для поиска бронирований
     * @return список бронирований на указанную дату
     */
    @Override
    public List<Booking> getBookingsByDate(LocalDateTime date) {
        List<Booking> bookings = new ArrayList<>();
        for (Booking booking : bookingDao.getAllBookings()) {
            if (booking.getStartTime().toLocalDate().equals(date.toLocalDate())) {
                bookings.add(booking);
            }
        }
        return bookings;
    }

    /**
     * Получить список бронирований, сделанных указанным пользователем.
     *
     * @param username имя пользователя для поиска его бронирований
     * @return список бронирований пользователя
     */
    @Override
    public List<Booking> getBookingsByUsername(String username) {
        List<Booking> bookings = new ArrayList<>();
        for (Booking booking : bookingDao.getAllBookings()) {
            if (booking.getUsername().equals(username)) {
                bookings.add(booking);
            }
        }
        return bookings;
    }

    /**
     * Получить список бронирований для указанного ресурса.
     *
     * @param resourceId идентификатор ресурса для поиска бронирований
     * @return список бронирований для указанного ресурса
     */
    @Override
    public List<Booking> getBookingsByResourceId(int resourceId) {
        List<Booking> bookings = new ArrayList<>();
        for (Booking booking : bookingDao.getAllBookings()) {
            if (booking.getResourceId() == resourceId) {
                bookings.add(booking);
            }
        }
        return bookings;
    }

    /**
     * Проверить конфликт бронирования для указанного ресурса, времени начала и времени окончания.
     *
     * @param resourceId  идентификатор ресурса
     * @param resourceType тип ресурса ("Workspace" или "ConferenceRoom")
     * @param startTime   время начала бронирования
     * @param endTime     время окончания бронирования
     * @throws BookingConflictException если обнаружен конфликт бронирования
     */
    private void checkBookingConflict(int resourceId, String resourceType, LocalDateTime startTime, LocalDateTime endTime) throws BookingConflictException {
        for (Booking booking : bookingDao.getAllBookings()) {
            if (booking.getResourceId() == resourceId && booking.getResourceType().equalsIgnoreCase(resourceType)) {
                if (startTime.isBefore(booking.getEndTime()) && endTime.isAfter(booking.getStartTime())) {
                    throw new BookingConflictException("Booking conflict detected");
                }
            }
        }
    }
}
