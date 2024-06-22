package org.coworking.service;

import org.coworking.exceptions.ResourceNotFoundException;
import org.coworking.model.ConferenceRoom;

import java.util.List;


/**
 * Сервисный интерфейс для управления конференц-залами.
 */
public interface ConferenceRoomService {

    /**
     * Добавляет новый конференц-зал с указанным именем и доступностью.
     *
     * @param name      имя конференц-зала
     * @param available доступность конференц-зала
     */
    void addConferenceRoom(String name, boolean available);

    /**
     * Обновляет существующий конференц-зал с указанным идентификатором.
     *
     * @param id        идентификатор конференц-зала для обновления
     * @param name      новое имя конференц-зала
     * @param available новая доступность конференц-зала
     * @throws ResourceNotFoundException если конференц-зал с указанным идентификатором не найден
     */
    void updateConferenceRoom(int id, String name, boolean available) throws ResourceNotFoundException;

    /**
     * Удаляет конференц-зал с указанным идентификатором.
     *
     * @param id идентификатор конференц-зала для удаления
     * @throws ResourceNotFoundException если конференц-зал с указанным идентификатором не найден
     */
    void deleteConferenceRoom(int id) throws ResourceNotFoundException;

    /**
     * Возвращает список всех доступных конференц-залов.
     *
     * @return список всех конференц-залов
     */
    List<ConferenceRoom> getAllConferenceRooms();
}

