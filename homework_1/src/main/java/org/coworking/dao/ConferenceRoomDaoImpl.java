package org.coworking.dao;

import org.coworking.exceptions.ResourceNotFoundException;
import org.coworking.model.ConferenceRoom;

import java.util.ArrayList;
import java.util.List;


/**
 * Реализация интерфейса ConferenceRoomDao для доступа к данным о конференц-залах в памяти.
 */
public class ConferenceRoomDaoImpl implements ConferenceRoomDao {
    private final List<ConferenceRoom> conferenceRooms = new ArrayList<>();

    /**
     * Добавляет новый конференц-зал в список.
     *
     * @param conferenceRoom объект конференц-зала для добавления
     */
    @Override
    public void addConferenceRoom(ConferenceRoom conferenceRoom) {
        conferenceRooms.add(conferenceRoom);
    }

    /**
     * Обновляет информацию о конференц-зале.
     *
     * @param updatedRoom обновленный объект конференц-зала
     * @throws ResourceNotFoundException если конференц-зал не найден
     */
    @Override
    public void updateConferenceRoom(ConferenceRoom updatedRoom) throws ResourceNotFoundException {
        for (ConferenceRoom room : conferenceRooms) {
            if (room.getId() == updatedRoom.getId()) {
                room.setName(updatedRoom.getName());
                room.setAvailable(updatedRoom.isAvailable());
                return;
            }
        }
        throw new ResourceNotFoundException("Conference room not found");
    }

    /**
     * Удаляет конференц-зал по его идентификатору.
     *
     * @param id идентификатор конференц-зала для удаления
     */
    @Override
    public void deleteConferenceRoom(int id) {
        conferenceRooms.removeIf(room -> room.getId() == id);
    }

    /**
     * Возвращает конференц-зал по его идентификатору.
     *
     * @param id идентификатор конференц-зала
     * @return объект конференц-зала или {@code null}, если конференц-зал не найден
     */
    @Override
    public ConferenceRoom getConferenceRoomById(int id) {
        for (ConferenceRoom room : conferenceRooms) {
            if (room.getId() == id) {
                return room;
            }
        }
        return null;
    }

    /**
     * Возвращает список всех конференц-залов.
     *
     * @return список всех конференц-залов
     */
    @Override
    public List<ConferenceRoom> getAllConferenceRooms() {
        return new ArrayList<>(conferenceRooms);
    }
}
