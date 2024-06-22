package org.coworking.service;

import org.coworking.dao.ConferenceRoomDao;
import org.coworking.exceptions.ResourceNotFoundException;
import org.coworking.model.ConferenceRoom;

import java.util.List;


/**
 * Реализация сервиса управления конференц-залами.
 */
public class ConferenceRoomServiceImpl implements ConferenceRoomService {
    private final ConferenceRoomDao conferenceRoomDao;

    /**
     * Конструктор для инициализации объекта сервиса.
     *
     * @param conferenceRoomDao DAO для управления конференц-залами
     */
    public ConferenceRoomServiceImpl(ConferenceRoomDao conferenceRoomDao) {
        this.conferenceRoomDao = conferenceRoomDao;
    }

    /**
     * Добавить новый конференц-зал с указанными параметрами.
     *
     * @param name      имя конференц-зала
     * @param available доступность конференц-зала
     */
    @Override
    public void addConferenceRoom(String name, boolean available) {
        ConferenceRoom conferenceRoom = new ConferenceRoom(conferenceRoomDao.getAllConferenceRooms().size() + 1, name, available);
        conferenceRoomDao.addConferenceRoom(conferenceRoom);
    }

    /**
     * Обновить данные конференц-зала с указанным идентификатором.
     *
     * @param id        идентификатор конференц-зала для обновления
     * @param name      новое имя конференц-зала
     * @param available новая доступность конференц-зала
     * @throws ResourceNotFoundException если конференц-зал с указанным идентификатором не найден
     */
    @Override
    public void updateConferenceRoom(int id, String name, boolean available) throws ResourceNotFoundException {
        ConferenceRoom existingRoom = conferenceRoomDao.getConferenceRoomById(id);
        if (existingRoom == null) {
            throw new ResourceNotFoundException("Conference room not found");
        }
        ConferenceRoom updatedRoom = new ConferenceRoom(id, name, available);
        conferenceRoomDao.updateConferenceRoom(updatedRoom);
    }

    /**
     * Удалить конференц-зал с указанным идентификатором.
     *
     * @param id идентификатор конференц-зала для удаления
     * @throws ResourceNotFoundException если конференц-зал с указанным идентификатором не найден
     */
    @Override
    public void deleteConferenceRoom(int id) throws ResourceNotFoundException {
        ConferenceRoom conferenceRoom = conferenceRoomDao.getConferenceRoomById(id);
        if (conferenceRoom == null) {
            throw new ResourceNotFoundException("Conference room not found");
        }
        conferenceRoomDao.deleteConferenceRoom(id);
    }

    /**
     * Получить список всех существующих конференц-залов.
     *
     * @return список всех конференц-залов
     */
    @Override
    public List<ConferenceRoom> getAllConferenceRooms() {
        return conferenceRoomDao.getAllConferenceRooms();
    }
}
