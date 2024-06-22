package org.coworking.dao;

import org.coworking.model.Booking;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


public class BookingDaoImplTest {

    @InjectMocks
    private BookingDaoImpl bookingDao;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddBooking() {
        Booking booking = new Booking(1, 1, "Workspace", "testUser", LocalDateTime.now(), LocalDateTime.now().plusHours(1));

        bookingDao.addBooking(booking);

        List<Booking> bookings = bookingDao.getAllBookings();
        assertThat(bookings).hasSize(1);
        assertThat(bookings.get(0)).isEqualTo(booking);
    }

    @Test
    public void testDeleteBooking() {
        Booking booking1 = new Booking(1, 1, "Workspace", "testUser", LocalDateTime.now(), LocalDateTime.now().plusHours(1));
        Booking booking2 = new Booking(2, 1, "Workspace", "anotherUser", LocalDateTime.now(), LocalDateTime.now().plusHours(2));
        bookingDao.addBooking(booking1);
        bookingDao.addBooking(booking2);

        bookingDao.deleteBooking(1);

        List<Booking> bookings = bookingDao.getAllBookings();
        assertThat(bookings).hasSize(1);
        assertThat(bookings.get(0)).isEqualTo(booking2);
    }

    @Test
    public void testGetBookingById() {
        Booking booking = new Booking(1, 1, "Workspace", "testUser", LocalDateTime.now(), LocalDateTime.now().plusHours(1));
        bookingDao.addBooking(booking);

        Booking result = bookingDao.getBookingById(1);

        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(booking);
    }

    @Test
    public void testGetAllBookings() {
        Booking booking1 = new Booking(1, 1, "Workspace", "testUser", LocalDateTime.now(), LocalDateTime.now().plusHours(1));
        Booking booking2 = new Booking(2, 1, "Workspace", "anotherUser", LocalDateTime.now(), LocalDateTime.now().plusHours(2));
        bookingDao.addBooking(booking1);
        bookingDao.addBooking(booking2);

        List<Booking> bookings = bookingDao.getAllBookings();

        assertThat(bookings).hasSize(2);
        assertThat(bookings).containsExactlyInAnyOrder(booking1, booking2);
    }
}
