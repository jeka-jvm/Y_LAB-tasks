package org.coworking.service;

import org.coworking.dao.BookingDao;
import org.coworking.dao.ConferenceRoomDao;
import org.coworking.dao.WorkspaceDao;
import org.coworking.exceptions.BookingConflictException;
import org.coworking.exceptions.BookingNotFoundException;
import org.coworking.model.Booking;
import org.coworking.model.Workspace;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


public class BookingServiceImplTest {

    @Mock
    private BookingDao bookingDaoMock;

    @Mock
    private WorkspaceDao workspaceDaoMock;

    @Mock
    private ConferenceRoomDao conferenceRoomDaoMock;

    private BookingServiceImpl bookingService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        bookingService = new BookingServiceImpl(bookingDaoMock, workspaceDaoMock, conferenceRoomDaoMock);
    }

    @Test
    public void testBookWorkspace_SuccessfulBooking() throws Exception {
        Workspace workspace = new Workspace(1, "Workspace 1");
        when(workspaceDaoMock.getWorkspaceById(1)).thenReturn(workspace);

        LocalDateTime startTime = LocalDateTime.now().plusHours(1);
        LocalDateTime endTime = startTime.plusHours(2);

        bookingService.bookResource(1, "Workspace", "user1", startTime, endTime);

        verify(bookingDaoMock, times(1)).addBooking(any(Booking.class));

        ArgumentCaptor<Booking> captor = ArgumentCaptor.forClass(Booking.class);
        verify(bookingDaoMock).addBooking(captor.capture());
        Booking capturedBooking = captor.getValue();

        assertNotNull(capturedBooking);
        assertEquals(1, capturedBooking.getId());
        assertEquals(1, capturedBooking.getResourceId());
        assertEquals("Workspace", capturedBooking.getResourceType());
        assertEquals("user1", capturedBooking.getUsername());
        assertEquals(startTime, capturedBooking.getStartTime());
        assertEquals(endTime, capturedBooking.getEndTime());
    }

    @Test
    public void testBookWorkspace_WorkspaceNotFound() {
        when(workspaceDaoMock.getWorkspaceById(anyInt())).thenReturn(null);

        LocalDateTime startTime = LocalDateTime.now().plusHours(1);
        LocalDateTime endTime = startTime.plusHours(2);

        assertThrows(BookingNotFoundException.class, () -> {
            bookingService.bookResource(1, "Workspace", "user1", startTime, endTime);
        });

        verify(bookingDaoMock, never()).addBooking(any(Booking.class));
    }

    @Test
    public void testCancelBooking_SuccessfulCancellation() throws BookingNotFoundException {
        Booking booking = new Booking(1, 1, "Workspace", "user1", LocalDateTime.now(), LocalDateTime.now().plusHours(1));
        when(bookingDaoMock.getBookingById(1)).thenReturn(booking);

        bookingService.cancelBooking(1);

        verify(bookingDaoMock).deleteBooking(1);
    }

    @Test
    public void testCancelBooking_BookingNotFound() {
        when(bookingDaoMock.getBookingById(anyInt())).thenReturn(null);

        assertThrows(BookingNotFoundException.class, () -> {
            bookingService.cancelBooking(1);
        });

        verify(bookingDaoMock, never()).deleteBooking(anyInt());
    }

    @Test
    public void testGetAllBookings() {
        List<Booking> mockBookings = Arrays.asList(
                new Booking(1, 1, "Workspace", "user1", LocalDateTime.now(), LocalDateTime.now().plusHours(1)),
                new Booking(2, 2, "ConferenceRoom", "user2", LocalDateTime.now().plusDays(1), LocalDateTime.now().plusDays(1).plusHours(2))
        );
        when(bookingDaoMock.getAllBookings()).thenReturn(mockBookings);

        List<Booking> result = bookingService.getAllBookings();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(mockBookings.get(0), result.get(0));
        assertEquals(mockBookings.get(1), result.get(1));
    }

    @Test
    public void testGetBookingsByDate() {
        LocalDateTime date = LocalDateTime.now().toLocalDate().atStartOfDay();
        List<Booking> mockBookings = Arrays.asList(
                new Booking(1, 1, "Workspace", "user1", date, date.plusHours(1)),
                new Booking(2, 2, "ConferenceRoom", "user2", date.plusDays(1), date.plusDays(1).plusHours(2))
        );
        when(bookingDaoMock.getAllBookings()).thenReturn(mockBookings);

        List<Booking> result = bookingService.getBookingsByDate(date);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(mockBookings.get(0), result.get(0));
    }

    @Test
    public void testGetBookingsByUsername() {
        List<Booking> mockBookings = Arrays.asList(
                new Booking(1, 1, "Workspace", "user1", LocalDateTime.now(), LocalDateTime.now().plusHours(1)),
                new Booking(2, 2, "ConferenceRoom", "user2", LocalDateTime.now().plusDays(1), LocalDateTime.now().plusDays(1).plusHours(2))
        );
        when(bookingDaoMock.getAllBookings()).thenReturn(mockBookings);

        List<Booking> result = bookingService.getBookingsByUsername("user1");

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(mockBookings.get(0), result.get(0));
    }

    @Test
    public void testGetBookingsByResourceId() {
        int resourceId = 1;
        List<Booking> mockBookings = Arrays.asList(
                new Booking(1, resourceId, "Workspace", "user1", LocalDateTime.now(), LocalDateTime.now().plusHours(1)),
                new Booking(2, resourceId, "ConferenceRoom", "user2", LocalDateTime.now().plusDays(1), LocalDateTime.now().plusDays(1).plusHours(2))
        );
        when(bookingDaoMock.getAllBookings()).thenReturn(mockBookings);

        List<Booking> result = bookingService.getBookingsByResourceId(resourceId);

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(mockBookings.get(0), result.get(0));
        assertEquals(mockBookings.get(1), result.get(1));
    }

    @Test
    public void testCheckBookingConflict_NoConflict() {
        Booking existingBooking = new Booking(1, 1, "Workspace", "user1", LocalDateTime.now(), LocalDateTime.now().plusHours(1));
        when(bookingDaoMock.getAllBookings()).thenReturn(Arrays.asList(existingBooking));

        Workspace workspace = new Workspace(1, "Workspace1");
        when(workspaceDaoMock.getWorkspaceById(1)).thenReturn(workspace);

        LocalDateTime startTime = existingBooking.getStartTime().plusHours(2); // No overlap with existing booking
        LocalDateTime endTime = existingBooking.getEndTime().plusHours(2); // No overlap with existing booking

        assertDoesNotThrow(() -> {
            bookingService.bookResource(1, "Workspace", "user2", startTime, endTime);
        });
    }

    @Test
    void testCheckBookingConflict_Conflict() {
        Workspace existingWorkspace = new Workspace(1, "Workspace1");
        when(workspaceDaoMock.getWorkspaceById(1)).thenReturn(existingWorkspace);

        LocalDateTime startTime = LocalDateTime.now().plusHours(1);
        LocalDateTime endTime = LocalDateTime.now().plusHours(2);

        Booking existingBooking = new Booking(1, 1, "Workspace", "user1", startTime.minusMinutes(30), endTime.plusMinutes(30));
        when(bookingDaoMock.getAllBookings()).thenReturn(Collections.singletonList(existingBooking));

        assertThrows(BookingConflictException.class, () -> {
            bookingService.bookResource(1, "Workspace", "user2", startTime, endTime);
        });
    }
}
