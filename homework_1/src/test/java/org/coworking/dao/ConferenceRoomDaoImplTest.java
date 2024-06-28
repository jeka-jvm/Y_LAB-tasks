package org.coworking.dao;

import org.coworking.exceptions.ResourceNotFoundException;
import org.coworking.model.ConferenceRoom;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class ConferenceRoomDaoImplTest {

    private ConferenceRoomDao conferenceRoomDao;

    @BeforeEach
    void setUp() {
        conferenceRoomDao = new ConferenceRoomDaoImpl();

        conferenceRoomDao.addConferenceRoom(new ConferenceRoom(1, "Room A", true));
        conferenceRoomDao.addConferenceRoom(new ConferenceRoom(2, "Room B", false));
    }

    @Test
    void testAddConferenceRoom() {
        ConferenceRoom newRoom = new ConferenceRoom(3, "Room C", true);
        conferenceRoomDao.addConferenceRoom(newRoom);
        List<ConferenceRoom> rooms = conferenceRoomDao.getAllConferenceRooms();
        assertTrue(rooms.contains(newRoom));
    }

    @Test
    void testUpdateExistingConferenceRoom() throws ResourceNotFoundException {
        ConferenceRoom roomToUpdate = new ConferenceRoom(2, "Updated Room B", true);
        conferenceRoomDao.updateConferenceRoom(roomToUpdate);
        ConferenceRoom updatedRoom = conferenceRoomDao.getConferenceRoomById(2);
        assertEquals("Updated Room B", updatedRoom.getName());
        assertTrue(updatedRoom.isAvailable());
    }

    @Test
    void testUpdateNonExistingConferenceRoom() {
        assertThrows(ResourceNotFoundException.class, () -> {
            ConferenceRoom roomToUpdate = new ConferenceRoom(3, "Updated Room C", true);
            conferenceRoomDao.updateConferenceRoom(roomToUpdate);
        });
    }

    @Test
    void testDeleteConferenceRoom() throws ResourceNotFoundException {
        conferenceRoomDao.deleteConferenceRoom(1);
        assertNull(conferenceRoomDao.getConferenceRoomById(1));
    }

    @Test
    void testGetConferenceRoomById() {
        ConferenceRoom room = conferenceRoomDao.getConferenceRoomById(1);
        assertNotNull(room);
        assertEquals("Room A", room.getName());
    }

    @Test
    void testGetAllConferenceRooms() {
        List<ConferenceRoom> rooms = conferenceRoomDao.getAllConferenceRooms();
        assertEquals(2, rooms.size());
    }
}
