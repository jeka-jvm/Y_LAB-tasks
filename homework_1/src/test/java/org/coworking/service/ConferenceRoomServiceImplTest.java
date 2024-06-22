package org.coworking.service;

import org.coworking.dao.ConferenceRoomDao;
import org.coworking.dao.ConferenceRoomDaoImpl;
import org.coworking.exceptions.ResourceNotFoundException;
import org.coworking.model.ConferenceRoom;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class ConferenceRoomServiceImplTest {

    private ConferenceRoomService conferenceRoomService;
    private ConferenceRoomDao conferenceRoomDao;

    @BeforeEach
    void setUp() {
        conferenceRoomDao = new ConferenceRoomDaoImpl();
        conferenceRoomService = new ConferenceRoomServiceImpl(conferenceRoomDao);
        conferenceRoomDao.addConferenceRoom(new ConferenceRoom(1, "Room A", true));
        conferenceRoomDao.addConferenceRoom(new ConferenceRoom(2, "Room B", false));
    }

    @Test
    void testAddConferenceRoom() {
        conferenceRoomService.addConferenceRoom("Room C", true);
        List<ConferenceRoom> rooms = conferenceRoomService.getAllConferenceRooms();
        assertEquals(3, rooms.size());
        assertEquals("Room C", rooms.get(2).getName());
    }

    @Test
    void testUpdateExistingConferenceRoom() throws ResourceNotFoundException {
        conferenceRoomService.updateConferenceRoom(2, "Updated Room B", true);
        ConferenceRoom updatedRoom = conferenceRoomService.getAllConferenceRooms().get(1);
        assertEquals("Updated Room B", updatedRoom.getName());
        assertTrue(updatedRoom.isAvailable());
    }

    @Test
    void testUpdateNonExistingConferenceRoom() {
        assertThrows(ResourceNotFoundException.class, () -> conferenceRoomService.updateConferenceRoom(3, "Updated Room C", true));
    }

    @Test
    void testDeleteConferenceRoom() throws ResourceNotFoundException {
        conferenceRoomService.deleteConferenceRoom(1);
        assertNull(conferenceRoomService.getAllConferenceRooms().stream().filter(room -> room.getId() == 1).findFirst().orElse(null));
    }

    @Test
    void testGetAllConferenceRooms() {
        List<ConferenceRoom> rooms = conferenceRoomService.getAllConferenceRooms();
        assertEquals(2, rooms.size());
    }

    @Test
    void testGetAllConferenceRoomsAfterDelete() throws ResourceNotFoundException {
        conferenceRoomService.deleteConferenceRoom(1);
        List<ConferenceRoom> rooms = conferenceRoomService.getAllConferenceRooms();
        assertEquals(1, rooms.size());
    }

    @Test
    void testDeleteNonExistingConferenceRoom() {
        assertThrows(ResourceNotFoundException.class, () -> conferenceRoomService.deleteConferenceRoom(3));
    }
}
